package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 排行榜实体类
 */
@Data
public class RankBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String avatar; // 头像
    private String name;
    private int value;
}
