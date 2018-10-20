package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.DailyBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 日报adapter
 */

public class DailyAdapter extends BaseQuickAdapter<DailyBean, TgBaseViewHolder> {
    public DailyAdapter() {
        super(R.layout.item_daily);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final DailyBean dailyBean) {
        helper.setText(R.id.tv, dailyBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + dailyBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.DAILY_DETAILS, "dailyBean", dailyBean);
            }
        });
    }
}
