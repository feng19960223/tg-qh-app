package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HallRewardBean;
import com.turingoal.qh.constants.ConstantActivityPath;

/**
 * 悬赏任务adapter
 */

public class HallRewardAdapter extends BaseQuickAdapter<HallRewardBean, TgBaseViewHolder> {
    public HallRewardAdapter() {
        super(R.layout.item_hall_reward);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final HallRewardBean hallRewardBean) {
        helper.setText(R.id.tv, hallRewardBean.getStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+hallRewardBean.getStr());
                TgSystemHelper.handleIntentWithObj(ConstantActivityPath.HALL_REWARD, "hallRewardBean", hallRewardBean);
            }
        });
    }
}
