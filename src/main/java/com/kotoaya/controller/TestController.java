package com.kotoaya.controller;

import com.kotoaya.common.Result;
import com.kotoaya.common.utils.JWTUtil;
import com.kotoaya.mapper.AdminMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/hello")
    public String  getHello(){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
        return "Hello World";
    }

    //获取token
    @GetMapping("/admin/aqswdefr/740969457")
    public Result<String> getTokenForTest(@RequestParam String username){
        String token = jwtUtil.createToken(username);
        return Result.ok(token);
    }

    @GetMapping("/getAllAdmin")
    public String mybatisTest(){
        return adminMapper.getAllAdministrator().toString();
    }

    @GetMapping("/")
    public Result<?> test(){
        return Result.ok();
    }
}
