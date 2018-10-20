package com.turingoal.common.app;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 用户数据_参数保存服务
 */

public class TgUserPreferences {
    private SharedPreferences sharedPreferences;

    public TgUserPreferences(final Context context) {
        sharedPreferences = context.getSharedPreferences(TgSystemConfig.SP_NAME, Context.MODE_PRIVATE); //name 在TgSystemConfig中统一配置
    }

    /**
     * 清空信息
     */
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }


    public void setSP(final String data, final SharedPreferencesKey key) {
        sharedPreferences.edit().putString(key.name(), data).apply();
    }

    public String getSp(final SharedPreferencesKey key) {
        return sharedPreferences.getString(key.name(), "");
    }

    public enum SharedPreferencesKey {
        // 系统
        TOKEN, // token
        // 用户
        USER_ID, // 用户id
        USER_CODE_NUM, // 用户编号
        USER_NAME,// 用户名字
        USER_REAL_NAME,// 用户真实姓名
        USER_PHONE,// 用户电话
        USER_AVATAR, // 用户头像
        USER_ADDRESS, // 地址
        USER_STAGE_NAME, // 花名
        USER_GENDER, // 性别
        USER_BIRTHDAY, // 生日
        USER_TENCENT, // QQ号
        USER_WECHAT, // 微信号
        USER_TELEPHONE, // 电话
        USER_MAIL, // 邮箱
        USER_ENTRY_TIME // 入职时间
    }
}
