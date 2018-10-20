package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.MallBackBean;

/**
 * 回城道具adapter
 */

public class MallBackAdapter extends BaseQuickAdapter<MallBackBean, TgBaseViewHolder> {
    public MallBackAdapter() {
        super(R.layout.item_mall_back);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final MallBackBean mallBackBean) {
        helper.setImageResource(R.id.ivPic, mallBackBean.getPicRes()) // 本地图片
                .setText(R.id.tvName, mallBackBean.getName()) // 道具名字
                .setText(R.id.tvDescribe, mallBackBean.getDescribe()) // 道具描述
                .setText(R.id.tvPrice, String.format(mContext.getString(R.string.mall_price), mallBackBean.getPrice())); // 道具价格
        if (!StringUtils.isEmpty(mallBackBean.getPicUrl())) {
            GlideUtil.load(mContext, mallBackBean.getPicUrl(), (ImageView) helper.getView(R.id.ivPic)); // 道具网络图片
        }
        helper.getView(R.id.tvBuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + mallBackBean.getId());
            }
        });
    }
}
