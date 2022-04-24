package com.example.democommerce1.dao;

import com.example.democommerce1.domain.CyCategories;
import com.example.democommerce1.domain.CyCategoriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CyCategoriesMapper {
    long countByExample(CyCategoriesExample example);

    int deleteByExample(CyCategoriesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CyCategories record);

    int insertSelective(CyCategories record);

    List<CyCategories> selectByExample(CyCategoriesExample example);

    CyCategories selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CyCategories record, @Param("example") CyCategoriesExample example);

    int updateByExample(@Param("record") CyCategories record, @Param("example") CyCategoriesExample example);

    int updateByPrimaryKeySelective(CyCategories record);

    int updateByPrimaryKey(CyCategories record);
}