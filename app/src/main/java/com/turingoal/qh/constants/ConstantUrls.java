package com.turingoal.qh.constants;

import com.turingoal.common.app.TgSystemConfig;

/**
 * 网络请求url路径
 */
public interface ConstantUrls {
    String URL_TOKEN_CHECK = TgSystemConfig.getServerBaseUrl() + "/tg/app/checkToken.app"; // 校验token 欢迎

    String URL_LOGIN = TgSystemConfig.getServerBaseUrl() + "/tg/app/login.app"; // 登录 username+password

    String URL_ATTRIBUTE = TgSystemConfig.getServerBaseUrl() + "/tg/app/attribute.app"; // 成长记录
    String URL_AUDIT = TgSystemConfig.getServerBaseUrl() + "/tg/app/audit.app"; // 审核任务
    String URL_BILLBOARD = TgSystemConfig.getServerBaseUrl() + "/tg/app/billboard.app"; // 公告牌
    String URL_DAILY_HISTORY = TgSystemConfig.getServerBaseUrl() + "/tg/app/dailyHistory.app"; // 日报
    String URL_DYNAMIC = TgSystemConfig.getServerBaseUrl() + "/tg/app/dynamic.app"; // 公司动态
    String URL_LEAVE = TgSystemConfig.getServerBaseUrl() + "/tg/app/leave.app"; // 请假
    String URL_OVERTIME = TgSystemConfig.getServerBaseUrl() + "/tg/app/overtime.app"; // 加班
    String URL_TEAM = TgSystemConfig.getServerBaseUrl() + "/tg/app/team.app"; // 团队成员
    String URL_WEB = TgSystemConfig.getServerBaseUrl() + "/tg/app/web.app"; // 常用网址
    String URL_WEEKLY_HISTORY = TgSystemConfig.getServerBaseUrl() + "/tg/app/weeklyHistory.app"; // 周报
    String URL_HALL_ALL = TgSystemConfig.getServerBaseUrl() + "/tg/app/hallAll.app"; // 全部任务
    String URL_HALL_ASSIGNED = TgSystemConfig.getServerBaseUrl() + "/tg/app/hallAssigned.app"; // 指派任务
    String URL_HALL_CONVENTIONAL = TgSystemConfig.getServerBaseUrl() + "/tg/app/hallConventional.app"; // 常规任务
    String URL_HALL_REWARD = TgSystemConfig.getServerBaseUrl() + "/tg/app/hallReward.app"; // 悬赏任务
    String URL_TASK = TgSystemConfig.getServerBaseUrl() + "/tg/app/hallReward.app"; // 我的任务
    String URL_MEMO = TgSystemConfig.getServerBaseUrl() + "/tg/app/memo.app"; // 备忘录

    String URL_MAIN_BILLBOARD = TgSystemConfig.getServerBaseUrl() + "tg/app/main/billboard.app"; // 主页公告牌
    String URL_MAIN_WEB = TgSystemConfig.getServerBaseUrl() + "tg/app/main/web.app"; // 主页常用网址
    String URL_MAIN_DYNAMIC = TgSystemConfig.getServerBaseUrl() + "tg/app/main/dynamic.app"; // 主页公司动态
}
