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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "/goods")
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
     * @param goodsVo 查询条件
     */
    @PostMapping("/list")
    public Result<Map<String,Object>> getGoodsList(@RequestParam Integer current,
                                            @RequestParam Integer limit,
                                            @RequestBody GoodsVo goodsVo){
        ParameterVerificationUtil.checkMultiParam(current,limit,goodsVo);
        if (!ObjectUtils.isEmpty(goodsVo.getIsHot()) && goodsVo.getIsHot()==126){
            goodsVo.setIsHot(null);
        }
        Map<String, Object> allGoods = goodsService.findAllGoods(limit, current, goodsVo);
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

    /**
     * @author wzw
     * @param gno 商品编号
     * 删除商品
     */
    @DeleteMapping("/delete")
    public Result<?> deleteByGno(@RequestParam String gno){
        ParameterVerificationUtil.checkStringIsNull(gno);
        boolean res = goodsService.deleteGoods(gno);
        return res?Result.ok():Result.fail();
    }

    /**
     * @author wzw
     * 修改商品信息
     */
    @PutMapping("/update")
    public Result updateGoods(@RequestBody Goods goods){
        if (StringUtils.hasLength(goods.getGoodsImg())){
            //TODO 删除旧的图片
        }
        Boolean update = goodsService.updateGoods(goods);
        return update?Result.ok():Result.fail();
    }
}
