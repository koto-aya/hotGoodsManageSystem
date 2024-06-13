package com.kotoaya.entity;

import com.kotoaya.entity.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * @author wzw
 * 商品实体类
 */
@Data
@ToString(callSuper = true)
public class Goods extends BaseEntity {
    private String gno;
    private String goodsName;
    private String goodsImg;
    private String goodsDesc;
    private Double price;
    private Integer isHot;
}
