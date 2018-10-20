package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.LevelBean;

/**
 * 等级规则adapter
 */

public class LevelAdapter extends BaseQuickAdapter<LevelBean, TgBaseViewHolder> {
    public LevelAdapter() {
        super(R.layout.item_level);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final LevelBean levelBean) {
        helper.setText(R.id.tv, levelBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + levelBean.getStr());
            }
        });
    }
}
