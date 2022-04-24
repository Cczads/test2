package com.example.democommerce1.conntroller;

import com.example.democommerce1.service.GoodsService;
import com.example.democommerce1.vo.selectGoodsSortCityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("selectGoodsSortCity")
    public List<selectGoodsSortCityVO> selectGoodsSortCity(HttpServletRequest request){
        /**
         * 判断是否是使用新功能（城市优先排序）的用户
         */
        //查该用户的id是否是使用新功能的用户
        if(goodsService.checkId(request)){
            //使用新功能
            return goodsService.selectGoodsSortCity(request);
        }else {
            //使用旧功能
            return goodsService.selectGoods();
        }
    }
}
