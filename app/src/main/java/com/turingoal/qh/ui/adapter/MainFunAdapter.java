package com.turingoal.qh.ui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.FunItemBean;

/**
 * 主菜单功能adapter
 */

public class MainFunAdapter extends BaseQuickAdapter<FunItemBean,TgBaseViewHolder> {
    public MainFunAdapter(){
        super(R.layout.item_fun);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final FunItemBean funItemBean) {
        helper.setImageResource(R.id.ivImage,funItemBean.getResId()) // 图片
                .setText(R.id.tvTitle,funItemBean.getTitle()); // 内容
        // item点击事件
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TgSystemHelper.handleIntent(funItemBean.getPath());
            }
        });
    }
}
