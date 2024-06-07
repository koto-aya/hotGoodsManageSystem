package com.kotoaya.service.impl;

import com.kotoaya.common.CustomException;
import com.kotoaya.common.ResultEnum;
import com.kotoaya.common.utils.JWTUtil;
import com.kotoaya.common.utils.MD5Util;
import com.kotoaya.entity.Administrator;
import com.kotoaya.mapper.AdminMapper;
import com.kotoaya.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JWTUtil jwtUtil;
    /**
     * @author wzw
     * 添加用户
     */
    @Override
    public boolean addAdmin(Administrator administrator) {
        String adminName = administrator.getAdminName();
        String passwd = administrator.getPasswd();
        if(!StringUtils.hasLength(adminName)||!StringUtils.hasLength(passwd)){
            throw new CustomException(ResultEnum.USERNAME_OR_PASSWORD_IS_NOT);
        }
        //判断账号是否存在
        if (isExist(adminName)){
            throw new CustomException(ResultEnum.REGISTERED);
        }
        passwd= MD5Util.string2MD5(passwd);//密码加密
        int isSuccess = adminMapper.register(adminName, passwd);
        return isSuccess>0;
    }

    /**
     * @author wzw
     * 管理员登录
     */
    @Override
    public Map<String, Object> login(Administrator administrator) {
        String adminName = administrator.getAdminName();
        String passwd = administrator.getPasswd();
        if(!StringUtils.hasLength(adminName)||!StringUtils.hasLength(passwd)){
            throw new CustomException(ResultEnum.USERNAME_OR_PASSWORD_IS_NOT);
        }
        passwd= MD5Util.string2MD5(passwd);//密码加密
        int loginSuccess = adminMapper.login(adminName, passwd);
        //如果没有找到匹配记录，登录失败
        if (loginSuccess==0){
            throw new CustomException(ResultEnum.LOGIN_FAIL);
        }
        String token = jwtUtil.createToken(adminName);//生成token
        Map<String,Object> result=new HashMap<>();
        result.put("code",200);
        result.put("token",token);
        result.put("adminName",adminName);
        //获取当前时间
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curTime = sf.format(new Date());
        result.put("currentTime",curTime);
        return result;
    }

    /**
     * @author
     * 根据token获取用户信息
     */
    @Override
    public Map<String, Object> getInfo(String token) {
        if (jwtUtil.getExpiration()>=System.currentTimeMillis()) {
            throw new CustomException(ResultEnum.USER_IS_NOT_LOGIN);
        }
        String username = jwtUtil.getUsernameFromToken(token);
        Long expiration = jwtUtil.getExpiration();
        String secret = jwtUtil.getSECRET();
        Map<String,Object> result=new HashMap<>();
        result.put("username",username);
        result.put("expiration",expiration);
        result.put("secret",secret);
        return result;
    }

    /**
     * @author wzw
     * 判断账号是否存在
     */
    @Override
    public boolean isExist(String adminName) {
        int exist = adminMapper.exist(adminName);
        return exist>0;
    }
}
