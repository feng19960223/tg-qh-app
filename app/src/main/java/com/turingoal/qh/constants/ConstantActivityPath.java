package com.turingoal.qh.constants;

/**
 * 常量-Activity路径
 */
public interface ConstantActivityPath {
    String PACKAGE = "/path/qh/"; // 所有Activity路径，前面都要加上这个字段，防止起名字出错，页面路由必须是2个以上字符串组成的

    String MAIN = PACKAGE + "main/index"; // Main主界面
    String ABOUT = PACKAGE + "about"; // 关于
    String LOGIN = PACKAGE + "login"; // 登录
    String FORGET = PACKAGE + "forget"; // 忘记密码
    String REGISTER = PACKAGE + "register"; // 注册
    String AGREEMENT = PACKAGE + "agreement"; // 服务协议

    String MALL = PACKAGE + "mall"; // 道具商城
    String MALL_MY = PACKAGE + "mall/my"; // 我的道具
    String RANK = PACKAGE + "rank"; // 排行榜
    String LEAVE = PACKAGE + "leave"; // 请假
    String LEAVE_ADD = PACKAGE + "leave/add"; // 请假增加
    String LEAVE_DETAILS = PACKAGE + "leave/details"; // 请假查看
    String OVERTIME = PACKAGE + "overtime"; // 加班申请
    String OVERTIME_ADD = PACKAGE + "overtime/add"; // 加班申请增加
    String OVERTIME_DETAILS = PACKAGE + "overtime/details"; // 加班申请查看
    String BILLBOARD = PACKAGE + "billboard"; // 公告牌
    String BILLBOARD_DETAILS = PACKAGE + "billboard/details"; // 公告牌
    String WEB = PACKAGE + "web"; // 常用网址
    String WEB_ADD = PACKAGE + "web/add"; // 增加常用网址
    String WEB_UPDATE = PACKAGE + "web/update"; // 修改常用网址
    String DYNAMIC = PACKAGE + "dynamic"; // 公司动态
    String SYSTEM = PACKAGE + "system"; // 规章制度
    String SIGN_IN_HISTORY = PACKAGE + "sign/in/history"; // 签到历史
    String DAILY_ADD = PACKAGE + "daily/add"; // 日报增加
    String DAILY_HISTORY = PACKAGE + "dialy/history"; // 日报历史
    String DAILY_DETAILS = PACKAGE + "dialy/details"; // 日报详情
    String WEEKLY_ADD = PACKAGE + "weekly/add"; // 周报增加
    String WEEKLY_HISTORY = PACKAGE + "weekly/history"; // 周报历史
    String WEEKLY_DETAILS = PACKAGE + "weekly/details"; // 周报详情
    String MEMO = PACKAGE + "memo"; // 备忘录
    String MEMO_ADD = PACKAGE + "memo/add"; // 增加备忘录
    String MEMO_UPDATE = PACKAGE + "memo/update"; // 修改备忘录
    String WEBPAGE = PACKAGE + "webpage"; // 网页浏览器
    String HALL_ALL = PACKAGE + "hall/all"; // 全部任务
    String HALL_ASSIGNED = PACKAGE + "hall/assigned"; // 指派任务
    String HALL_CONVENTIONAL = PACKAGE + "hall/conventional"; // 常规任务
    String HALL_REWARD = PACKAGE + "hall/reward"; // 悬赏任务
    String TASK = PACKAGE + "task"; // 我的让我
    String LEVEL = PACKAGE + "level"; // 等级规则
    String HONOR = PACKAGE + "honor"; // 头衔规则
    String PERSONAL = PACKAGE + "personal"; // 个人信息
    String ATTRIBUTE = PACKAGE + "attribute"; // 属性变化，成长记录
    String AUDIT = PACKAGE + "audit"; // 审核任务
    String AUDIT_DETAILS = PACKAGE + "audit/details"; // 审核任务详情
    String ATTENDANCE = PACKAGE + "attendance"; // 考勤记录
    String TEAM = PACKAGE + "team"; // 团队成员
    String TEAM_DETAILS = PACKAGE + "team/details"; // 团队成员详情
    String SETTING = PACKAGE + "setting"; // 设置
    String ADDRESS = PACKAGE + "address"; // 地址
    String STAGE_NAME = PACKAGE + "stage/name"; // 花名
    String PHONE = PACKAGE + "phone"; // 手机
    String TENCENT = PACKAGE + "tencent"; // QQ号
    String WECHAT = PACKAGE + "wechat"; // 微信号
    String TELEPHONE = PACKAGE + "telephone"; // 电话
}
