package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HallAllBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 全部任务（有可能没有），全部任务的adapter，根据类型，跳转到不同的页面
 */

@Route(path = ConstantActivityPath.HALL_ALL)
public class HallAllActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @Autowired
    HallAllBean hallAllBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hall_all;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_hall_all);
        Log.i(TAG, "initialized: " + hallAllBean.getStr());
    }

    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
