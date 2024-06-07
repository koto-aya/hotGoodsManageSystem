package com.kotoaya.service;

import com.kotoaya.entity.Administrator;

import java.util.Map;

public interface AdminService {
    public boolean addAdmin(Administrator administrator);
    public boolean isExist(String adminName);

    public Map<String,Object> login(Administrator administrator);

    public Map<String,Object> getInfo(String token);
}
