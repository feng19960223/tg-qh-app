package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 我的任务实体类
 */
@Data
public class TaskBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
