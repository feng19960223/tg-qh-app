package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HallConventionalBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 常规任务adapter
 */

public class HallConventionalAdapter extends BaseQuickAdapter<HallConventionalBean, TgBaseViewHolder> {
    public HallConventionalAdapter() {
        super(R.layout.item_hall_conventional);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final HallConventionalBean hallConventionalBean) {
        helper.setText(R.id.tv, hallConventionalBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+hallConventionalBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.HALL_CONVENTIONAL, "hallConventionalBean", hallConventionalBean);
            }
        });
    }
}
