package com.example.democommerce1.dao;

import com.example.democommerce1.domain.CyGoodsSku;
import com.example.democommerce1.domain.CyGoodsSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CyGoodsSkuMapper {
    long countByExample(CyGoodsSkuExample example);

    int deleteByExample(CyGoodsSkuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CyGoodsSku record);

    int insertSelective(CyGoodsSku record);

    List<CyGoodsSku> selectByExample(CyGoodsSkuExample example);

    CyGoodsSku selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CyGoodsSku record, @Param("example") CyGoodsSkuExample example);

    int updateByExample(@Param("record") CyGoodsSku record, @Param("example") CyGoodsSkuExample example);

    int updateByPrimaryKeySelective(CyGoodsSku record);

    int updateByPrimaryKey(CyGoodsSku record);
}