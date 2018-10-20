package com.turingoal.qh.ui.activity;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的道具页面
 */

@Route(path = ConstantActivityPath.MALL_MY)
public class MallMyActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mall_my;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_mall_my);
    }

    /**
     * OnClick
     */
    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
