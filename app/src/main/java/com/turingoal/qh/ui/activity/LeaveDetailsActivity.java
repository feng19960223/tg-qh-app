package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.LeaveBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 请假查看页面
 */

@Route(path = ConstantActivityPath.LEAVE_DETAILS)
public class LeaveDetailsActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 删除
    @Autowired
    LeaveBean leaveBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_leave_details;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_leave_details);
        tvEnd.setText(R.string.delete);
        tvEnd.setVisibility(View.VISIBLE); // 如果审核通过不显示，不能刪除
        Log.i(TAG, "initialized: " + leaveBean.getStr());
    }

    /**
     * OnClick
     */
    @OnClick({R.id.ivStart, R.id.tvEnd})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivStart:
                defaultFinish();
                break;
            case R.id.tvEnd: // 删除

                break;
            default:
                break;
        }
    }
}
