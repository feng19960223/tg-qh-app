package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 头衔规则实体类
 */
@Data
public class HonorBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
