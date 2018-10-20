package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HonorBean;

/**
 * 头衔规则adapter
 */

public class HonorAdapter extends BaseQuickAdapter<HonorBean, TgBaseViewHolder> {
    public HonorAdapter() {
        super(R.layout.item_honor);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final HonorBean honorBean) {
        helper.setText(R.id.tv, honorBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + honorBean.getStr());
            }
        });
    }
}
