package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.LeaveBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 请假adapter
 */

public class LeaveAdapter extends BaseQuickAdapter<LeaveBean, TgBaseViewHolder> {
    public LeaveAdapter() {
        super(R.layout.item_leave);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final LeaveBean leaveBean) {
        helper.setText(R.id.tv, leaveBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + leaveBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.LEAVE_DETAILS, "leaveBean", leaveBean);
            }
        });
    }
}
