package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.WebBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 常用网址adapter
 */

public class WebAdapter extends BaseQuickAdapter<WebBean, TgBaseViewHolder> {
    public WebAdapter() {
        super(R.layout.item_web);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final WebBean webBean) {
        helper.setText(R.id.tv, webBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + webBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.WEBPAGE, "url", webBean.getStr());
            }
        });
    }
}
