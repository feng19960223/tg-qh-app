package com.turingoal.qh.ui.fragment;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.app.TgUserPreferences;
import com.turingoal.common.base.TgBaseFragment;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.common.utils.TgStringUtil;
import com.turingoal.common.widget.SquareImageView;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.ui.activity.PersonalActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人中心
 */

public class PersonalFragment extends TgBaseFragment {
    @BindView(R.id.ivAvatar)
    SquareImageView ivAvatar; // 头像
    @BindView(R.id.tvLevel)
    TextView tvLevel; // 等级
    @BindView(R.id.tvName)
    TextView tvName; // 名字
    @BindView(R.id.tvWealth)
    TextView tvWealth; // 财富
    @BindView(R.id.tvHonor)
    TextView tvHonor; // 头衔
    @BindView(R.id.tvExperience)
    TextView tvExperience; // 经验
    @BindView(R.id.npbExperience)
    NumberProgressBar npbExperience; // 经验进度条
    @BindView(R.id.tvLife)
    TextView tvLife; // 生命值
    @BindView(R.id.npbLife)
    NumberProgressBar npbLife; // 生命值进度条
    @BindView(R.id.tvStamina)
    TextView tvStamina; // 体力值
    @BindView(R.id.npbStamina)
    NumberProgressBar npbStamina; // 体力值进度条

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initialized() {
        EventBus.getDefault().register(this);
        GlideUtil.loadImage(getContext(), TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_AVATAR), ivAvatar);
        tvLevel.setText(String.format(getString(R.string.personal_level), 2));
        setText(tvName, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_REAL_NAME));
        tvWealth.setText(String.format(getString(R.string.personal_wealth), 88));
        tvHonor.setText(String.format(getString(R.string.personal_honor), "无名小卒"));
        tvExperience.setText(String.format(getString(R.string.personal_experience), 230));
        npbExperience.setMax(500);
        npbExperience.setProgress(230);
        tvLife.setText(String.format(getString(R.string.personal_life), 80));
        npbLife.setMax(100);
        npbLife.setProgress(80);
        tvStamina.setText(String.format(getString(R.string.personal_stamina), 60));
        npbStamina.setMax(100);
        npbStamina.setProgress(60);
    }

    /**
     * 给TextView设置str，如果str为空，这设置空白
     */
    private void setText(final TextView textView, final String str) {
        if (TgStringUtil.isEmpty(str)) {
            textView.setText(R.string.personal_empty);
        } else {
            textView.setText(str);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        GlideUtil.loadImage(getContext(), event, ivAvatar);
    }

    /**
     * OnClick
     */
    @OnClick({R.id.tvLevel, R.id.tvHonor, R.id.llPersonal, R.id.tvWealth, R.id.cvAttribute, R.id.tvAudit, R.id.tvAttendance, R.id.tvMallMe, R.id.tvTeam, R.id.tvSetting})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.tvLevel: // 等级规则
                TgSystemHelper.handleIntent(ConstantActivityPath.LEVEL);
                break;
            case R.id.tvHonor: // 头衔规则
                TgSystemHelper.handleIntent(ConstantActivityPath.HONOR);
                break;
            case R.id.llPersonal: // 个人信息
                // TgSystemHelper.handleIntent(ConstantActivityPath.PERSONAL);
                startActivity(new Intent(view.getContext(), PersonalActivity.class),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                Pair.create((View) tvName, "transitionNameName"),
                                Pair.create((View) ivAvatar, "transitionNameAvatar"))
                                .toBundle());
                break;
            case R.id.tvWealth: // 属性变化，成长记录
            case R.id.cvAttribute: // 属性变化，成长记录
                TgSystemHelper.handleIntent(ConstantActivityPath.ATTRIBUTE);
                break;
            case R.id.tvAudit: // 审核任务
                TgSystemHelper.handleIntent(ConstantActivityPath.AUDIT);
                break;
            case R.id.tvAttendance: // 考勤记录
                TgSystemHelper.handleIntent(ConstantActivityPath.ATTENDANCE);
                break;
            case R.id.tvMallMe: // 我的道具
                TgSystemHelper.handleIntent(ConstantActivityPath.MALL_MY);
                break;
            case R.id.tvTeam: // 团队成员
                TgSystemHelper.handleIntent(ConstantActivityPath.TEAM);
                break;
            case R.id.tvSetting: // 设置
                TgSystemHelper.handleIntent(ConstantActivityPath.SETTING);
                break;
            default:
                break;
        }
    }
}
