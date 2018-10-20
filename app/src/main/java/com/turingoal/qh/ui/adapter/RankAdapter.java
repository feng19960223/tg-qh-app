package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.RankBean;
import com.turingoal.qh.ui.activity.RankActivity;

/**
 * 排行榜adapter
 */

public class RankAdapter extends BaseQuickAdapter<RankBean, TgBaseViewHolder> {

    public RankAdapter() {
        super(R.layout.item_rank);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final RankBean rankBean) {
        Log.i(TAG, "convert: " + helper.getAdapterPosition());
        helper.setText(R.id.tvNum, String.format(mContext.getString(R.string.rank_num), helper.getAdapterPosition() + 4)) // 排名
                .setText(R.id.tvName, rankBean.getName()) // 名字
                .setText(R.id.tvValue, String.format(mContext.getString(RankActivity.currentType == RankActivity.TYPE.TYPE_EXPERIENCE ? R.string.rank_value_experience : R.string.rank_value_wealth), rankBean.getValue())); // 值
        GlideUtil.loadImage(mContext, rankBean.getAvatar(), (ImageView) helper.getView(R.id.sivAvatar)); // 头像
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + rankBean.getId());
            }
        });
    }
}
