package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HallAllBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 全部任务adapter
 */

public class HallAllAdapter extends BaseQuickAdapter<HallAllBean, TgBaseViewHolder> {
    public HallAllAdapter() {
        super(R.layout.item_hall_all);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final HallAllBean hallAllBean) {
        helper.setText(R.id.tv, hallAllBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+hallAllBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.HALL_ALL,"hallAllBean",hallAllBean);
            }
        });
    }
}
