package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.WeeklyBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 周报adapter
 */

public class WeeklyAdapter extends BaseQuickAdapter<WeeklyBean, TgBaseViewHolder> {
    public WeeklyAdapter() {
        super(R.layout.item_weekly);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final WeeklyBean weeklyBean) {
        helper.setText(R.id.tv, weeklyBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + weeklyBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.WEEKLY_DETAILS, "weeklyBean", weeklyBean);
            }
        });
    }
}
