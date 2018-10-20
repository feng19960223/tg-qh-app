package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 成长记录实体类
 */
@Data
public class AttributeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
