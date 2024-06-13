package com.kotoaya.entity;

import com.kotoaya.entity.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @author wzw
 * 管理员类
 */
@Data
@ToString(callSuper = true)
public class Administrator extends BaseEntity {
    private String adminName;
    private String passwd;
    private String avatarPath;
}
