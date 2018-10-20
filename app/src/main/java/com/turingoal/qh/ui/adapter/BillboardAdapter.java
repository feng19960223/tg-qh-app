package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.BillboardBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 公告牌adapter
 */

public class BillboardAdapter extends BaseQuickAdapter<BillboardBean, TgBaseViewHolder> {
    public BillboardAdapter() {
        super(R.layout.item_billboard);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final BillboardBean billboardBean) {
        helper.setText(R.id.tv, billboardBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + billboardBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.BILLBOARD_DETAILS, "billboardBean", billboardBean);
            }
        });
    }
}
