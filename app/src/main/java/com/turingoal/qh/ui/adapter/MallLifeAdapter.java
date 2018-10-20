package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.MallLifeBean;

/**
 * 生命道具adapter
 */

public class MallLifeAdapter extends BaseQuickAdapter<MallLifeBean, TgBaseViewHolder> {
    public MallLifeAdapter() {
        super(R.layout.item_mall_life);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final MallLifeBean mallLifeBean) {
        helper.setImageResource(R.id.ivPic, mallLifeBean.getPicRes()) // 本地图片
                .setText(R.id.tvName, mallLifeBean.getName()) // 道具名字
                .setText(R.id.tvDescribe, mallLifeBean.getDescribe()) // 道具描述
                .setText(R.id.tvRestore, String.format(mContext.getString(R.string.mall_restore), mallLifeBean.getRestore())) // 道具恢复值
                .setText(R.id.tvPrice, String.format(mContext.getString(R.string.mall_price), mallLifeBean.getPrice())); // 道具价格
        if (!StringUtils.isEmpty(mallLifeBean.getPicUrl())) {
            GlideUtil.load(mContext, mallLifeBean.getPicUrl(), (ImageView) helper.getView(R.id.ivPic)); // 图片网络路径
        }
        helper.getView(R.id.tvBuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + mallLifeBean.getId());
            }
        });
    }
}
