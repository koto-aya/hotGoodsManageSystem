package com.kotoaya.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsVo implements Serializable {
    private static final long serialVersionUID=1145141919810L;
    private String gno;
    private String goodsName;
    private Integer isHot;
}
