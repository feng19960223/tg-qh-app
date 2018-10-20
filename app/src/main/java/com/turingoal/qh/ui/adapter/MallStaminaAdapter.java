package com.turingoal.qh.ui.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.turingoal.common.base.TgBaseViewHolder;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.MallStaminaBean;

/**
 * 体力道具adapter
 */

public class MallStaminaAdapter extends BaseQuickAdapter<MallStaminaBean, TgBaseViewHolder> {
    public MallStaminaAdapter() {
        super(R.layout.item_mall_stamina);
    }

    @Override
    protected void convert(TgBaseViewHolder helper, final MallStaminaBean mallStaminaBean) {
        helper.setImageResource(R.id.ivPic, mallStaminaBean.getPicRes()) // 本地图片
                .setText(R.id.tvName, mallStaminaBean.getName()) // 道具名字
                .setText(R.id.tvDescribe, mallStaminaBean.getDescribe()) // 道具描述
                .setText(R.id.tvRestore, String.format(mContext.getString(R.string.mall_restore), mallStaminaBean.getRestore())) // 道具恢复值
                .setText(R.id.tvPrice, String.format(mContext.getString(R.string.mall_price), mallStaminaBean.getPrice())); // 道具价格
        if (!StringUtils.isEmpty(mallStaminaBean.getPicUrl())) {
            GlideUtil.load(mContext, mallStaminaBean.getPicUrl(), (ImageView) helper.getView(R.id.ivPic)); // 道具网络图片
        }
        helper.getView(R.id.tvBuy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: " + mallStaminaBean.getId());
            }
        });
    }
}
