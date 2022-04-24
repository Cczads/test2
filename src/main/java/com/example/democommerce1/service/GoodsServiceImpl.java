package com.example.democommerce1.service;

import com.example.democommerce1.constant.CommonConstant;
import com.example.democommerce1.dao.CyGoodsMapper;
import com.example.democommerce1.vo.Customer;
import com.example.democommerce1.vo.CustomerAddress;
import com.example.democommerce1.vo.selectGoodsSortCityVO;
import io.netty.util.internal.StringUtil;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service

/*
*
* 输入
*   接口：商品列表查询
*
*       为了确定出 使用哪个城市的排序规则
*       1.客户会话，进而获取到默认收货地址
*       2.ip地址获取
*
*       得到城市之后，就可以执行sql了。
*
*
* 输出
*       商品列表
*
*
* */
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private CyGoodsMapper cyGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private SimulateService simulateService = new SimulateService();

    /**
     * 判断是否要得进行新的方法
     * 得到用户id,和redis的key(存该新功能要使用的用户id)
     * 是否查得到该id,
     * @return
     */
    public boolean checkId(HttpServletRequest request){
        Integer customerId =simulateService.getCurrentCustomer(request).getCustomerId();
        return redisTemplate.opsForSet().isMember(CommonConstant.USE_CITY_PRIORITY_KEY, customerId);


    }
    /**
     * 旧的查询方法
     * @return
     */
    public List<selectGoodsSortCityVO> selectGoods(){
        return cyGoodsMapper.selectGoods();
    }

    /**
     * 新的查询方法（城市优先排序）
     * @param request
     * @return
     */
    public List<selectGoodsSortCityVO> selectGoodsSortCity(HttpServletRequest request){

        //调用方法得到city
        Integer cityId = getCityId(request);

        // 根据city 查询商品列表
        return cyGoodsMapper.selectGoodsSortCity(cityId);
    }

    /**
     * 尝试根据默认收货地址 获取对应的城市
     *
     * @param request
     * @return
     */
    private Integer getCityIdByCustomerAddress(HttpServletRequest request){

        // 尝试获取会话
        Customer customer = simulateService.getCurrentCustomer(request);

        // 如果客户已经登录
        if (customer != null){

            // 获取客户的默认收货地址
            CustomerAddress customerAddress = simulateService.getDefaultAddress(); // todo 先调用模拟方法，后续用实际的service或者dao代替。

            if (customerAddress != null){

                // 成功获取到
                return customerAddress.getCityId();
            }
        }

        return null;
    }

    /**
     * 尝试使用ip地址获取对应城市
     * @param request
     * @return
     */
    private Integer getCityIdByIp(HttpServletRequest request){

        //得到ip
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null){

            return null;
        }

        //在redis中查是否缓存过city
        Integer cityId = (Integer) redisTemplate.opsForValue().get(ip);

        if(cityId == null){

            //没有缓存，调用外面提供的由IP得到城市的接口，并缓存在redis中
            cityId = simulateService.getCityIdFromAlibaba(ip);

            // 如果获取到了
            if (cityId != null){

                //将得到的结果缓存一份 redis中
                //根据具体情况设置过期时间，比如30天
                redisTemplate.opsForValue().set(ip, cityId, 60*60*24*30);
            }
        }

        return cityId;
    }
    private Integer getCityId(HttpServletRequest request){

        // 是否传递了城市参数（手机端可以app定位，并转换为cityId）
        String cityIdStr = request.getParameter("cityId");

        if (!StringUtil.isNullOrEmpty(cityIdStr)){

            return Integer.parseInt(cityIdStr);
        }

        // 尝试根据默认收货地址 获取对应的城市
        Integer cityId = getCityIdByCustomerAddress(request);

        if (cityId != null){

            return cityId;
        }

        // 如果未成功获取到城市，则尝试使用ip地址获取对应城市
        cityId = getCityIdByIp(request);

        if (cityId != null){

            return cityId;
        }

        // 若ip地址也无法获取准确城市，则选取默认城市
        return CommonConstant.DEFAULT_CITY_ID;
    }



}



