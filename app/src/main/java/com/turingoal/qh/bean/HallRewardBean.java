package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 悬赏任务实体类
 */
@Data
public class HallRewardBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
