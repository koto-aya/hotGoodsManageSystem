package com.kotoaya.controller;

import com.kotoaya.common.CustomException;
import com.kotoaya.common.Result;
import com.kotoaya.common.ResultEnum;
import com.kotoaya.common.utils.ParameterVerificationUtil;
import com.kotoaya.entity.Administrator;
import com.kotoaya.service.AdminService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wzw
 * 管理员控制层
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;
    //添加管理员
    @PostMapping("/register")
    public Result<?> addAdmin(@RequestBody Administrator administrator){
        ParameterVerificationUtil.checkObjectIsNull(administrator);
        boolean success = adminService.addAdmin(administrator);
        return success?Result.ok():Result.fail();
    }
    //管理员登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody Administrator administrator){
        ParameterVerificationUtil.checkObjectIsNull(administrator);
        Map<String, Object> loginInfo = adminService.login(administrator);
        if (loginInfo.get("code")==null||(int)loginInfo.get("code")!=200){
            return Result.fail();
        }
        return Result.ok(loginInfo);
    }
    //获取用户信息
    //需要返回用户名和头像路径
    @GetMapping("/info")
    public Result<Map<String,Object>> getInfo(@RequestParam String token){
        ParameterVerificationUtil.checkStringIsNull(token);
        Map<String, Object> info = adminService.getInfo(token);
        return Result.ok(info);
    }
}
