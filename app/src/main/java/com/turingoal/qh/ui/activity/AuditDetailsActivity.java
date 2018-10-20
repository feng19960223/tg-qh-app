package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.AuditBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 审核任务详情
 */

@Route(path = ConstantActivityPath.AUDIT_DETAILS)
public class AuditDetailsActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @Autowired
    AuditBean auditBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_audit_details;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_audit_details);
        Log.i(TAG, "initialized: " + auditBean.getStr());
    }

    /**
     * OnClick
     */
    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
