package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 公告牌实体类
 */
@Data
public class BillboardBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String str;
}
