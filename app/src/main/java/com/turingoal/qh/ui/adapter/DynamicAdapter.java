package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.DynamicBean;

/**
 * 公司动态adapter
 */

public class DynamicAdapter extends BaseQuickAdapter<DynamicBean, TgBaseViewHolder> {
    public DynamicAdapter() {
        super(R.layout.item_dynamic);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final DynamicBean dynamicBean) {
        helper.setText(R.id.tv, dynamicBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+dynamicBean.getStr());
            }
        });
    }
}
