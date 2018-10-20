package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.HallAssignedBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 指派任务
 */

@Route(path = ConstantActivityPath.HALL_ASSIGNED)
public class HallAssignedActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @Autowired
    HallAssignedBean hallAssignedBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hall_assigned;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_hall_assigned);
        Log.i(TAG, "initialized: " + hallAssignedBean.getStr());
    }

    @OnClick(R.id.ivStart)
    public void onViewClicked() {
        defaultFinish();
    }
}
