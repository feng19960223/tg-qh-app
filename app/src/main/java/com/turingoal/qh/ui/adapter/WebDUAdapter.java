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
 * 常用网址带删除和修改按钮的adapter
 */

public class WebDUAdapter extends BaseQuickAdapter<WebBean, TgBaseViewHolder> {
    public WebDUAdapter() {
        super(R.layout.item_web_du);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final WebBean webBean) {
        helper.setText(R.id.tv, webBean.getStr())
                .setGone(R.id.tvDelete, webBean.getType() == 1)
                .setGone(R.id.tvUpdate, webBean.getType() == 1);
        helper.getView(R.id.tvUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 更新
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.WEB_UPDATE, "webBean", webBean);
            }
        });
        helper.getView(R.id.tvDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 删除
                mData.remove(webBean);
                notifyDataSetChanged();
            }
        });
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + webBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.WEBPAGE, "url", webBean.getStr());
            }
        });
    }
}
