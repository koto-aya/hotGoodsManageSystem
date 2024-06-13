package com.kotoaya.service;

import com.kotoaya.entity.Goods;
import com.kotoaya.entity.vo.GoodsVo;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    public Boolean addGoods(Goods goods);
    public Boolean updateGoods(Goods goods);
    public Goods findGoodsByGno(String gno);
    public Map<String,Object> findAllGoods(int limit, int current, GoodsVo goodsVo);
    public Boolean updateGoodsHot(String gno,int isHot);
    public int getRecordsTotal();
    public boolean deleteGoods(String gno);
}
