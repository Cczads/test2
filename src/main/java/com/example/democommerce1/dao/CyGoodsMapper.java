package com.example.democommerce1.dao;

import com.example.democommerce1.domain.CyGoods;
import com.example.democommerce1.domain.CyGoodsExample;
import java.util.List;

import com.example.democommerce1.vo.selectGoodsSortCityVO;
import org.apache.ibatis.annotations.Param;

public interface CyGoodsMapper {
    List<selectGoodsSortCityVO> selectGoodsSortCity(@Param("cityId") Integer cityId);
    List<selectGoodsSortCityVO> selectGoods();
    long countByExample(CyGoodsExample example);

    int deleteByExample(CyGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CyGoods record);

    int insertSelective(CyGoods record);

    List<CyGoods> selectByExample(CyGoodsExample example);

    CyGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CyGoods record, @Param("example") CyGoodsExample example);

    int updateByExample(@Param("record") CyGoods record, @Param("example") CyGoodsExample example);

    int updateByPrimaryKeySelective(CyGoods record);

    int updateByPrimaryKey(CyGoods record);
}