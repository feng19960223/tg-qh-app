package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.DailyBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 日报详情
 */

@Route(path = ConstantActivityPath.DAILY_DETAILS)
public class DailyDetailsActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @Autowired
    DailyBean dailyBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_details;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_daily_details);
        Log.i(TAG, "initialized: " + dailyBean.getStr());
    }

    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
