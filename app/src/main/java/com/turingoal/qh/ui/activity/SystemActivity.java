package com.turingoal.qh.ui.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 规章制度页面
 */
@Route(path = ConstantActivityPath.SYSTEM)
public class SystemActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_system);
    }

    /**
     * OnClick
     */
    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
