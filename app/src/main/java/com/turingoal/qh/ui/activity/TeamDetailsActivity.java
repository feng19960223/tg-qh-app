package com.turingoal.qh.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.common.utils.TgDateUtil;
import com.turingoal.common.utils.TgStringUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.TeamBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 团队成员详情
 */

@Route(path = ConstantActivityPath.TEAM_DETAILS)
public class TeamDetailsActivity extends TgBaseActivity implements AppBarLayout.OnOffsetChangedListener {
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar; // 头像
    @BindView(R.id.ivBack)
    ImageView ivBack; // 收缩状态的返回
    @BindView(R.id.ivStart)
    ImageView ivStart; // 展开状态的返回
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title，设置名字
    @BindView(R.id.flTitleBar)
    FrameLayout flTitleBar; // 收缩状态的Title视图控制器
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout; // title，设置名字
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout; // 展开和收缩视图
    @BindView(R.id.tvStageName)
    TextView tvStageName; // 花名
    @BindView(R.id.tvGender)
    TextView tvGender; // 性别
    @BindView(R.id.tvEntryTime)
    TextView tvEntryTime; // 入职时间
    @BindView(R.id.tvAddress)
    TextView tvAddress; // 地址
    @BindView(R.id.tvMail)
    TextView tvMail; // 邮箱
    @BindView(R.id.tvPhone)
    TextView tvPhone; // 手机
    @BindView(R.id.tvBirthday)
    TextView tvBirthday; // 生日
    @BindView(R.id.tvTencent)
    TextView tvTencent; // QQ号
    @BindView(R.id.tvWechat)
    TextView tvWechat; // 微信号
    @BindView(R.id.tvTelephone)
    TextView tvTelephone; // 电话
    private CollapsingToolbarLayoutState state; // 当前状态
    TeamBean teamBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_team_details;
    }

    @Override
    protected void initialized() {
        appBarLayout.addOnOffsetChangedListener(this); //　AppBarLayout拉伸监听器
        teamBean = (TeamBean) getIntent().getSerializableExtra("teamBean");
        // 设置名字
        String name = null;
        if (TgStringUtil.isEmpty(name)) { // 用户名
            tvTitle.setText(R.string.activity_team_details);
            collapsingToolbarLayout.setTitle(getString(R.string.activity_team_details));
        } else {
            tvTitle.setText(name);
            collapsingToolbarLayout.setTitle(name);
        }
        GlideUtil.load(this, teamBean.getStr(), ivAvatar); // 设置头像
        setText(tvStageName, "小丑巴基");
        updateGender(2);
        setText(tvEntryTime, TgDateUtil.date2String(new Date(), TgDateUtil.FORMAT_YYYY_MM_DD));
        setText(tvAddress, "北京市昌平区");
        setText(tvMail, "ricco@turingoal.com");
        setText(tvPhone, "18832010846".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        setText(tvBirthday, TgDateUtil.date2String(new Date(), TgDateUtil.FORMAT_YYYY_MM_DD));
        setText(tvTencent, "594878760");
        setText(tvWechat, "fengguoruiwxh");
        setText(tvTelephone, "");
        Log.i(TAG, "initialized: " + teamBean.getStr());
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

    /**
     * 更新性别UI
     *
     * @param type 1女性UI，其他为男性UI
     */
    public void updateGender(final int type) {
        if (type == 1) {
            Drawable drawableStartWoman = getDrawable(R.drawable.ic_woman);
            drawableStartWoman.setBounds(0, 0, drawableStartWoman.getMinimumWidth(), drawableStartWoman.getMinimumHeight());
            Drawable drawableEndWoman = getDrawable(R.drawable.ic_arrow_end_24dp);
            drawableEndWoman.setBounds(0, 0, drawableEndWoman.getMinimumWidth(), drawableEndWoman.getMinimumHeight());
            tvGender.setCompoundDrawables(drawableStartWoman, null, drawableEndWoman, null);
            tvGender.setText(R.string.personal_gender_woman);
        } else {
            Drawable drawableStartMan = getDrawable(R.drawable.ic_man);
            drawableStartMan.setBounds(0, 0, drawableStartMan.getMinimumWidth(), drawableStartMan.getMinimumHeight());
            Drawable drawableEndMan = getDrawable(R.drawable.ic_arrow_end_24dp);
            drawableEndMan.setBounds(0, 0, drawableEndMan.getMinimumWidth(), drawableEndMan.getMinimumHeight());
            tvGender.setCompoundDrawables(drawableStartMan, null, drawableEndMan, null);
            tvGender.setText(R.string.personal_gender_man);
        }
    }

    /**
     * OnClick
     */
    @OnClick({R.id.ivBack, R.id.ivStart})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivBack:
            case R.id.ivStart:
                // 注意这里不使用finish
                ActivityCompat.finishAfterTransition(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            if (state != CollapsingToolbarLayoutState.EXPANDED) {
                state = CollapsingToolbarLayoutState.EXPANDED; // 修改状态标记为展开
            }
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                flTitleBar.setVisibility(View.VISIBLE); // 显示自定义title
                state = CollapsingToolbarLayoutState.COLLAPSED; // 修改状态标记为折叠
            }
        } else {
            if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                    flTitleBar.setVisibility(View.GONE); // 由折叠变为中间状态时隐藏title
                }
                state = CollapsingToolbarLayoutState.INTERNEDIATE; // 修改状态标记为中间
            }
        }
    }

    /**
     * 滑动状态
     */
    private enum CollapsingToolbarLayoutState {
        EXPANDED, // 展开
        COLLAPSED, // 折叠
        INTERNEDIATE // 滑动中间过程
    }
}
