package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.AuditBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 审核任务adapter
 */

public class AuditAdapter extends BaseQuickAdapter<AuditBean, TgBaseViewHolder> {
    public AuditAdapter() {
        super(R.layout.item_audit);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final AuditBean auditBean) {
        helper.setText(R.id.tv, auditBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + auditBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.AUDIT_DETAILS, "auditBean", auditBean);
            }
        });
    }
}
