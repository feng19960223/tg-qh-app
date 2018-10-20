package com.turingoal.qh.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 主菜单功能，实体类
 */
@Data
public class FunItemBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private int resId = -1; // 图片
    private int title = -1; // 标题
    private String path = ""; // 路径

    public FunItemBean(final int resIdParm, final int titleParm, final String pathParm) {
        this.resId = resIdParm;
        this.title = titleParm;
        this.path = pathParm;
    }
}
