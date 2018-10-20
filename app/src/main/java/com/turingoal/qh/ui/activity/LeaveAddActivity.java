package com.turingoal.qh.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 请假增加页面
 */

@Route(path = ConstantActivityPath.LEAVE_ADD)
public class LeaveAddActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 提交

    @Override
    protected int getLayoutId() {
        return R.layout.activity_leave_add;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_leave_add);
        tvEnd.setText(R.string.submit);
        tvEnd.setVisibility(View.VISIBLE);
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
            case R.id.tvEnd:

                break;
            default:
                break;
        }
    }
}
