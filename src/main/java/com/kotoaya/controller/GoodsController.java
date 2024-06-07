package com.kotoaya.controller;

import com.kotoaya.common.CustomException;
import com.kotoaya.common.Result;
import com.kotoaya.common.ResultEnum;
import com.kotoaya.common.utils.JWTUtil;
import com.kotoaya.common.utils.ParameterVerificationUtil;
import com.kotoaya.entity.Goods;
import com.kotoaya.entity.vo.GoodsVo;
import com.kotoaya.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private JWTUtil jwtUtil;

    /**
     * @author wzw
     * 添加商品信息
     * @param goods 要添加的商品信息
     * @param token 用户token
     */
    @PostMapping("/add")
    public Result<?> addGoods(@RequestBody Goods goods,
                              @RequestParam(required = false) String token){
        ParameterVerificationUtil.checkMultiParam(goods,token);
        //TODO 查询登录态
        String username = jwtUtil.getUsernameFromToken(token);
        if (!StringUtils.hasLength(username)){
            throw new CustomException(ResultEnum.USER_IS_NOT_LOGIN);
        }
        Boolean success = goodsService.addGoods(goods);
        return success?Result.ok():Result.fail();
    }

    /**
     * 分页获取商品信息
     * @author wzw
     * @param current 当前页
     * @param limit 每页记录数
     * @param token 用户登录凭证
     * @param goodsVo 查询条件
     */
    @PostMapping("/list")
    public Result<List<Goods>> getGoodsList(@RequestParam Integer current,
                                            @RequestParam Integer limit,
                                            @RequestParam(required = false) String token,
                                            @RequestBody GoodsVo goodsVo){
        ParameterVerificationUtil.checkMultiParam(current,limit,token,goodsVo);
        String username = jwtUtil.getUsernameFromToken(token);
        if (!StringUtils.hasLength(username)){
            throw new CustomException(ResultEnum.USER_IS_NOT_LOGIN);
        }
        //计算偏移量
        int offset=(current-1)*limit;
        List<Goods> allGoods = goodsService.findAllGoods(limit, offset,goodsVo);
        return Result.ok(allGoods);
    }

    /**
     * @author wzw
     * 设置商品是否热门
     */
    @GetMapping("/status/hot")
    public Result<?> setGoodsIsHot(@RequestParam String gno,
                                @RequestParam Integer isHot){
        ParameterVerificationUtil.checkMultiParam(gno,isHot);
        Boolean success = goodsService.updateGoodsHot(gno, isHot);
        return success?Result.ok():Result.fail();
    }
}
