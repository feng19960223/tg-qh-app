package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 等级规则实体类
 */
@Data
public class LevelBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
