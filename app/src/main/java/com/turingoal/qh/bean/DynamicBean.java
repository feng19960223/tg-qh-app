package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 公司动态实体类
 */
@Data
public class DynamicBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
