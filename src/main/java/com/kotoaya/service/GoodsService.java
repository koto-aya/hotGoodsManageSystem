package com.kotoaya.service;

import com.kotoaya.entity.Goods;
import com.kotoaya.entity.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    public Boolean addGoods(Goods goods);
    public Boolean updateGoods(Goods goods);
    public Goods findGoodsByGno(String gno);
    public List<Goods> findAllGoods(int limit, int offset, GoodsVo goodsVo);
    public Boolean updateGoodsHot(String gno,int isHot);
}
