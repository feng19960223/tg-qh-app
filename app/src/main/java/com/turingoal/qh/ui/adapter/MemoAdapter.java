package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.MemoBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 备忘录adapter
 */

public class MemoAdapter extends BaseQuickAdapter<MemoBean, TgBaseViewHolder> {
    public MemoAdapter() {
        super(R.layout.item_memo);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final MemoBean memoBean) {
        helper.setText(R.id.tv, memoBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + memoBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.MEMO, "memoBean", memoBean);
            }
        });
    }
}
