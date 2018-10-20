package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HallRewardBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 悬赏任务
 */

@Route(path = ConstantActivityPath.HALL_REWARD)
public class HallRewardActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @Autowired
    HallRewardBean hallRewardBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hall_reward;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_hall_reward);
        Log.i(TAG, "initialized: "+hallRewardBean.getStr());
    }

    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
