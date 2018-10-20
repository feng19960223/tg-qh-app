package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 常用网址实体类
 */
@Data
public class WebBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
    private int type; // 企业还是个人 1 企业 2 个人
}
