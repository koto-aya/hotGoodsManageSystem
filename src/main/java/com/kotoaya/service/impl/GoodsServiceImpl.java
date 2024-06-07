package com.kotoaya.service.impl;

import com.kotoaya.common.CustomException;
import com.kotoaya.common.ResultEnum;
import com.kotoaya.entity.Goods;
import com.kotoaya.entity.vo.GoodsVo;
import com.kotoaya.mapper.GoodsMapper;
import com.kotoaya.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * @author wzw
     * 添加商品
     */
    @Override
    public Boolean addGoods(Goods goods) {
        //TODO 查看商品编号是否存在，若存在则判断数据库中有无对应记录，若有，则修改数据，否则将该商品编号插入数据库
        if (StringUtils.hasLength(goods.getGno())){
            Goods goodsByGno = findGoodsByGno(goods.getGno());
            if (!ObjectUtils.isEmpty(goodsByGno)){
                Boolean isUpdate = updateGoods(goods);
                return isUpdate;
            }
            //如果存在商品编号，但是数据库中没有对应记录，返回"商品信息不存在"信息
            throw new CustomException(ResultEnum.GOODS_IS_NOT_EXIST);
        }
        //判断商品名称和价格是否不为空
        if (!StringUtils.hasLength(goods.getGoodsName())||ObjectUtils.isEmpty(goods.getPrice())){
            throw new CustomException(ResultEnum.INCOMPLETE_GOODS_INFO);
        }
        //生成商品编号
        goods.setGno(generateUUID());
        int isAdd = goodsMapper.addGoods(goods);
        return isAdd>0;
    }

    /**
     * @author wzw
     * 修改商品信息
     */
    @Override
    public Boolean updateGoods(Goods goods) {
        //判断gno对应的商品信息是否存在
        Goods goodsByGno = findGoodsByGno(goods.getGno());
        if(ObjectUtils.isEmpty(goodsByGno)){
            throw new CustomException(ResultEnum.GOODS_IS_NOT_EXIST);
        }
        goods.setId(goodsByGno.getId());//防止修改id
        int updateResult = goodsMapper.updateGoods(goods);
        return updateResult>0;
    }

    /**
     * @author wzw
     * 根据商品编号查询商品
     */
    @Override
    public Goods findGoodsByGno(String gno) {
        if(!StringUtils.hasLength(gno)){
            throw new CustomException(ResultEnum.PARAMATER_INVALID);
        }
        return goodsMapper.getGoodsByGno(gno);
    }

    /**
     * @author wzw
     * 获取所有商品信息
     */
    @Override
    public List<Goods> findAllGoods(int limit, int offset, GoodsVo goodsVo) {
        List<Goods> allGoods = goodsMapper.getAllGoods(limit,offset,goodsVo);
        return allGoods;
    }

    /**
     * @author wzw
     * 设置商品是否热门
     */
    @Override
    public Boolean updateGoodsHot(String gno, int isHot) {
        Goods goodsByGno = goodsMapper.getGoodsByGno(gno);
        if (ObjectUtils.isEmpty(goodsByGno)){
            throw new CustomException(ResultEnum.GOODS_IS_NOT_EXIST);
        }
        int res = goodsMapper.setHot(gno, isHot);
        return res>0;
    }

    /**
     * @author wzw
     * 生成uuid
     */
    private String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
