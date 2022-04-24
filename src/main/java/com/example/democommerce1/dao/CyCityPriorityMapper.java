package com.example.democommerce1.dao;

import com.example.democommerce1.domain.CyCityPriority;
import com.example.democommerce1.domain.CyCityPriorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CyCityPriorityMapper {
    long countByExample(CyCityPriorityExample example);

    int deleteByExample(CyCityPriorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CyCityPriority record);

    int insertSelective(CyCityPriority record);

    List<CyCityPriority> selectByExample(CyCityPriorityExample example);

    CyCityPriority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CyCityPriority record, @Param("example") CyCityPriorityExample example);

    int updateByExample(@Param("record") CyCityPriority record, @Param("example") CyCityPriorityExample example);

    int updateByPrimaryKeySelective(CyCityPriority record);

    int updateByPrimaryKey(CyCityPriority record);
}