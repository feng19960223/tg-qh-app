package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.BillboardBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 公告牌详情
 */

@Route(path = ConstantActivityPath.BILLBOARD_DETAILS)
public class BillboardDetailsActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @Autowired
    BillboardBean billboardBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_billboard_details;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_billboard_details);
        Log.i(TAG, "initialized: " + billboardBean.getStr());
    }

    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
