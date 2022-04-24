package com.example.democommerce1.service;

import com.example.democommerce1.vo.selectGoodsSortCityVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodsService {
    List<selectGoodsSortCityVO> selectGoodsSortCity(HttpServletRequest request);
    boolean checkId(HttpServletRequest request);
    List<selectGoodsSortCityVO> selectGoods();
}
