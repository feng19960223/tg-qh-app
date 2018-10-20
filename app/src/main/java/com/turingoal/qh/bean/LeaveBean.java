package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 请假实体类
 */
@Data
public class LeaveBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
