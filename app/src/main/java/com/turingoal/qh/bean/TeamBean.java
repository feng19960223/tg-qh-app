package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 团队成员实体类
 */
@Data
public class TeamBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}