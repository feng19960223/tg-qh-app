package com.turingoal.qh.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.TeamBean;
import com.turingoal.qh.ui.activity.TeamDetailsActivity;

/**
 * 团队成员adapter
 */

public class TeamAdapter extends BaseQuickAdapter<TeamBean, TgBaseViewHolder> {
    public TeamAdapter() {
        super(R.layout.item_team);
    }

    @Override
    protected void convert(final TgBaseViewHolder helper, final TeamBean teamBean) {
        helper.setText(R.id.tv, "小丑巴基");
        GlideUtil.load(mContext, teamBean.getStr(), (ImageView) helper.getView(R.id.iv));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + teamBean.getStr());
                view.getContext().startActivity(
                        new Intent(view.getContext(), TeamDetailsActivity.class)
                                .putExtra("teamBean", teamBean),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                (Activity) view.getContext(),
                                Pair.create(helper.getView(R.id.tv), "transitionNameTv"),
                                Pair.create(helper.getView(R.id.iv), "transitionNameIv"))
                                .toBundle());
                // TgSystemHelper.handleIntentWithObj(ConstantActivityPath.TEAM_DETAILS, "teamBean", teamBean);
            }
        });
    }
}
