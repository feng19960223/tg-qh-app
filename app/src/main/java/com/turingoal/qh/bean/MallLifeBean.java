package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 生命道具实体类
 */
@Data
public class MallLifeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String picUrl; // 照片网络路径
    private int picRes; // 照片本地资源
    private String name; // 名字
    private String describe; // 描述
    private int restore; // 回复值
    private int price; // 价格
}
