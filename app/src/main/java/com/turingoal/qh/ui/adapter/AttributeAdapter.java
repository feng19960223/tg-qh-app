package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.AttributeBean;

/**
 * 成长记录adapter
 */

public class AttributeAdapter extends BaseQuickAdapter<AttributeBean, TgBaseViewHolder> {
    public AttributeAdapter() {
        super(R.layout.item_attribute);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final AttributeBean attributeBean) {
        helper.setText(R.id.tv, attributeBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + attributeBean.getStr());
            }
        });
    }
}
