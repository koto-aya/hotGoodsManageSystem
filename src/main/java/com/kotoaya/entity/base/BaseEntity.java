package com.kotoaya.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID=1145141919810L;
    protected Integer id;
    protected Timestamp createTime;
    protected Timestamp updateTime;
    protected Integer isDeleted;
}
