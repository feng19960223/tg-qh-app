package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HallAssignedBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 指派任务adapter
 */

public class HallAssignedAdapter extends BaseQuickAdapter<HallAssignedBean, TgBaseViewHolder> {
    public HallAssignedAdapter() {
        super(R.layout.item_hall_reward);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final HallAssignedBean hallAssignedBean) {
        helper.setText(R.id.tv, hallAssignedBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + hallAssignedBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.HALL_ASSIGNED, "hallAssignedBean", hallAssignedBean);
            }
        });
    }
}
