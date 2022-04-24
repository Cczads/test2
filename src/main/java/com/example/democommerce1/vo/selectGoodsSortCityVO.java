package com.example.democommerce1.vo;
import lombok.Data;

@Data
public class selectGoodsSortCityVO {
    private Integer id;

    private String goodsName;

    private Integer cateId;

    private Long price;

    private Long original;

    private String tags;

    private String content;

    private String summary;

    private Byte isSale;
}
