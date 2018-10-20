package com.turingoal.common.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.bean.TgResponseBean;
import com.turingoal.common.utils.TgDateUtil;
import com.turingoal.common.utils.TgDialogUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import java.util.Date;
import java.util.Map;

/**
 * 公用方法
 */
public final class TgSystemHelper {
    private static long exitTime = 0; // 退出系统
    private static final long EXIT_DELAY_TIME = 2000; // 再按一次退出系统，间隔时间

    private TgSystemHelper() {
        throw new Error("工具类不能实例化！");
    }

    /**
     * 再按一次退出系统
     */
    public static void dbClickExit(final Context context) {
        if ((System.currentTimeMillis() - exitTime) > EXIT_DELAY_TIME) {
            TgDialogUtil.showToast(TgApplication.getContext().getString(R.string.dbclick_exit));
            exitTime = System.currentTimeMillis();
        } else {
            TgApplication.clearActivitys(); // 清除Activity栈
            System.exit(0);
        }
    }

    /**
     * 处理跳转
     */
    public static void handleIntent(final String path) {
        ARouter.getInstance().build(path).navigation();
    }

    /**
     * 处理跳转
     */
    public static void handleIntent(final String path, final Activity mContext, final int requestCode) {
        ARouter.getInstance().build(path).navigation(mContext, requestCode);
    }

    /**
     * 处理跳转
     */
    public static void handleIntent(final String path, final Activity mContext, final int requestCode, final NavigationCallback callback) {
        ARouter.getInstance().build(path).navigation(mContext, requestCode, callback);
    }

    /**
     * 处理跳转,带参数过去
     */
    public static void handleIntentWithObj(final String path, final String objName, final Object obj) {
        ARouter.getInstance().build(path)
                .withObject(objName, obj)
                .navigation();
    }

    /**
     * 处理跳转,带参数过去
     */
    public static void handleIntentWithObj(final String path, final String key, final String value) {
        ARouter.getInstance().build(path)
                .withString(key, value)
                .navigation();
    }

    /**
     * 处理跳转,带参数过去
     */
    public static void handleIntentWithBundle(final String path, final String objName, final Bundle bundle) {
        ARouter.getInstance().build(path)
                .withBundle(objName, bundle)
                .navigation();
    }

    /**
     * 处理跳转,带参数过去
     */
    public static void handleIntentWithObj(final String path, final String objName, final Object obj, final Activity mContext, final int requestCode) {
        ARouter.getInstance().build(path)
                .withObject(objName, obj)
                .navigation(mContext, requestCode);
    }

    /**
     * 处理跳转,带参数过去
     */
    public static void handleIntentWithObj(final String path, final String objName, final Object obj, final Activity mContext, final int requestCode, final NavigationCallback callback) {
        ARouter.getInstance().build(path)
                .withObject(objName, obj)
                .navigation(mContext, requestCode, callback);
    }

    /**
     * 处理跳转，关闭当前页面
     */
    public static void handleIntentAndFinish(final String path, final Context context) {
        ARouter.getInstance().build(path).navigation(context, new NavCallback() {
            @Override
            public void onArrival(final Postcard postcard) {
                ((TgBaseActivity) context).defaultFinish(); // 关闭当前页面
            }
        });
    }

    /**
     * 处理跳转，关闭当前页面， 带参数过去
     */
    public static void handleIntentAndFinishWithObj(final String path, final String objName, final Object obj, final TgBaseActivity context) {
        ARouter.getInstance().build(path).withObject(objName, obj).navigation(context, new NavCallback() {
            @Override
            public void onArrival(final Postcard postcard) {
                context.defaultFinish(); // 关闭当前页面
            }
        });
    }

    /**
     * 获取用户toekn
     */
    public static String getToken() {
        return TgApplication.getTgUserPreferences().getSp(TgUserPreferences.SharedPreferencesKey.TOKEN);
    }

    public static String getSP(final TgUserPreferences.SharedPreferencesKey key) {
        return TgApplication.getTgUserPreferences().getSp(key);
    }

    public static void setSP(final String data, final TgUserPreferences.SharedPreferencesKey key) {
        TgApplication.getTgUserPreferences().setSP(data, key); // 存储token
    }

    /**
     * 检查token
     */
    public static boolean checkToken(final TgResponseBean result, final Context context) {
        boolean flag = false;
        if (result == null) {
            return false;
        }
        if (result.isTokenValidateResult()) {
            flag = true;
        } else {
            TgDialogUtil.showToast(TgApplication.getContext().getString(R.string.token_expired)); // 弹出错误信息
            if (context != null) {
                logout(context); //注销并跳转到登录页面
            }
        }
        return flag;
    }

    /**
     * 清空用户个人信息
     */
    public static void clearUserInfo() {
        TgApplication.getTgUserPreferences().clear();
    }

    /**
     * 注销
     */
    public static void logout(final Context context) {
        clearUserInfo(); // 清空用户个人信息
        TgApplication.clearActivitys(); // 清空activiti堆栈
        TgSystemHelper.handleIntentAndFinish(ConstantActivityPath.LOGIN, context); // 跳转到登录页面
    }

    /**
     * 获取登录信息
     */
    public static void setUserInfo(final TgResponseBean result) {
        Map<String, Object> map = (Map<String, Object>) result.getData();
        String token = (String) map.get("token");
        TgSystemHelper.setSP(token, TgUserPreferences.SharedPreferencesKey.TOKEN); // 存储token
        String userId = (String) map.get("userId");
        TgSystemHelper.setSP(userId, TgUserPreferences.SharedPreferencesKey.USER_ID); // 存储userId
        String userCodeNum = (String) map.get("userCodeNum");
        TgSystemHelper.setSP(userCodeNum, TgUserPreferences.SharedPreferencesKey.USER_CODE_NUM); // 存储用户编号
        String userName = (String) map.get("username");
        TgSystemHelper.setSP(userName, TgUserPreferences.SharedPreferencesKey.USER_NAME); // 存储username
        String realname = (String) map.get("realname");
        TgSystemHelper.setSP(realname, TgUserPreferences.SharedPreferencesKey.USER_REAL_NAME); // 存储realname
        String cellphoneNumber = (String) map.get("cellphoneNum");
        TgSystemHelper.setSP(cellphoneNumber, TgUserPreferences.SharedPreferencesKey.USER_PHONE); // 存储cellphoneNumber
        String avatar = (String) map.get("avatar");
        TgSystemHelper.setSP(avatar, TgUserPreferences.SharedPreferencesKey.USER_AVATAR); // 存储用户头像
        String address = (String) map.get("address");
        TgSystemHelper.setSP(address, TgUserPreferences.SharedPreferencesKey.USER_ADDRESS); // 存储用户地址
        String stageName = (String) map.get("stageName");
        TgSystemHelper.setSP(stageName, TgUserPreferences.SharedPreferencesKey.USER_STAGE_NAME); // 存储用户花名
        String gender = (String) map.get("gender");
        TgSystemHelper.setSP(gender, TgUserPreferences.SharedPreferencesKey.USER_GENDER); // 存储用户性别
        Date birthday = (Date) map.get("birthday");
        TgSystemHelper.setSP(TgDateUtil.date2String(birthday, TgDateUtil.FORMAT_YYYY_MM_DD), TgUserPreferences.SharedPreferencesKey.USER_BIRTHDAY); // 存储用户生日
        String tencent = (String) map.get("tencent");
        TgSystemHelper.setSP(tencent, TgUserPreferences.SharedPreferencesKey.USER_TENCENT); // 存储用户qq号
        String wechat = (String) map.get("wechat");
        TgSystemHelper.setSP(wechat, TgUserPreferences.SharedPreferencesKey.USER_WECHAT); // 存储用户微信号
        String telephone = (String) map.get("telephone");
        TgSystemHelper.setSP(telephone, TgUserPreferences.SharedPreferencesKey.USER_TELEPHONE); // 存储用户电话
        String mail = (String) map.get("mail");
        TgSystemHelper.setSP(mail, TgUserPreferences.SharedPreferencesKey.USER_MAIL); // 存储用户邮箱
        Date entryTime = (Date) map.get("entryTime");
        TgSystemHelper.setSP(TgDateUtil.date2String(entryTime, TgDateUtil.FORMAT_YYYY_MM_DD), TgUserPreferences.SharedPreferencesKey.USER_ENTRY_TIME); // 存储用户入职时间
    }
}