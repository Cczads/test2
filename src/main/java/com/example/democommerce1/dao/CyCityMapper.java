package com.example.democommerce1.dao;

import com.example.democommerce1.domain.CyCity;
import com.example.democommerce1.domain.CyCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CyCityMapper {
    long countByExample(CyCityExample example);

    int deleteByExample(CyCityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CyCity record);

    int insertSelective(CyCity record);

    List<CyCity> selectByExample(CyCityExample example);

    CyCity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CyCity record, @Param("example") CyCityExample example);

    int updateByExample(@Param("record") CyCity record, @Param("example") CyCityExample example);

    int updateByPrimaryKeySelective(CyCity record);

    int updateByPrimaryKey(CyCity record);
}