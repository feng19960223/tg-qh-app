package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 加班申请实体类
 */
@Data
public class OvertimeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
