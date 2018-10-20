package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.WeeklyBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 周报详情
 */

@Route(path = ConstantActivityPath.WEEKLY_DETAILS)
public class WeeklyDetailsActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @Autowired
    WeeklyBean weeklyBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weekly_details;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_weekly_details);
        Log.i(TAG, "initialized: " + weeklyBean.getStr());
    }

    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
