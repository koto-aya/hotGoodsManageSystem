package com.kotoaya.mapper;

import com.kotoaya.entity.Administrator;
import com.kotoaya.entity.base.BaseEntity;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public interface AdminMapper {
    @Results(value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "adminName",column = "admin_name"),
            @Result(property = "passwd",column = "passwd"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time"),
            @Result(property = "isDeleted",column = "is_deleted")
    }
    )
    @Select("select * from administrator")
    public List<Administrator> getAllAdministrator();

    @Insert("insert into administrator(admin_name,passwd) values(#{adminName},#{passwd})")
    public int register(@Param("adminName") String adminName,@Param("passwd") String passwd);

    @Select("select count(1) from administrator where admin_name=#{adminName}")
    public int exist(@Param("adminName") String adminName);

    @Select("select count(1) from administrator where admin_name=#{adminName} and passwd=#{passwd}")
    public int login(@Param("adminName") String adminName,@Param("passwd") String passwd);
}
