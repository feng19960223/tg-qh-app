package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.OvertimeBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 加班申请adapter
 */

public class OvertimeAdapter extends BaseQuickAdapter<OvertimeBean, TgBaseViewHolder> {
    public OvertimeAdapter() {
        super(R.layout.item_overtime);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final OvertimeBean overtimeBean) {
        helper.setText(R.id.tv, overtimeBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + overtimeBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.OVERTIME_DETAILS, "overtimeBean", overtimeBean);
            }
        });
    }
}
