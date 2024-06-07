package com.kotoaya.mapper;

import com.kotoaya.entity.Goods;
import com.kotoaya.entity.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsMapper {
    //添加商品
    @Insert("insert into goods(gno,goods_name,goods_desc,price,is_hot) values(#{goods.gno},#{goods.goodsName},#{goods.goodsDesc},#{goods.price},#{goods.isHot})")
    public int addGoods(@Param("goods") Goods goods);
    //根据商品编号查询商品
    @Select("select * from goods where gno=#{gno}")
    public Goods getGoodsByGno(@Param("gno")String gno);
    //修改商品信息
    @Update("update goods set goods_name=#{goods.goodsName},goods_desc=#{goods.goodsDesc},price=#{goods.price},is_hot=#{goods.isHot}")
    public int updateGoods(@Param("goods")Goods goods);
    //获取所有商品
    @Results(value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "goodsName",column = "goods_name"),
            @Result(property = "goodsDesc",column = "goods_desc"),
            @Result(property = "isHot",column = "is_hot"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "isDeleted",column = "is_deleted")
    }
    )
    @Select("<script>" +
            "select * from goods where 1=1" +
            "<if test='goods.gno!=\"\" and goods.gno!=null '> and gno=#{goods.gno} </if> " +
            "<if test='goods.goodsName!=\"\" and goods.goodsName!=null'> and goods_name=#{goods.goodsName}</if>" +
            "<if test='goods.isHot!=null'> and is_hot=#{goods.isHot}</if> " +
            "limit #{limit} offset #{offset}" +
            "</script>")
    public List<Goods> getAllGoods(@Param("limit")int limit, @Param("offset")int offset, @Param("goods")GoodsVo goodsVo);

    //设置商品是否热门
    @Update("update goods set is_hot=#{isHot} where gno=#{gno}")
    public int setHot(@Param("gno")String gno,@Param("isHot") int isHot);
}
