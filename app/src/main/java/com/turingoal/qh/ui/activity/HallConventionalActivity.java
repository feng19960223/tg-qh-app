package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HallConventionalBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 常规任务
 */

@Route(path = ConstantActivityPath.HALL_CONVENTIONAL)
public class HallConventionalActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @Autowired
    HallConventionalBean hallConventionalBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hall_conventional;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_hall_conventional);
        Log.i(TAG, "initialized: " + hallConventionalBean.getStr());
    }

    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
