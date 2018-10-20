package com.turingoal.qh.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.ui.fragment.TaskFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 日报增加页面
 */

@Route(path = ConstantActivityPath.DAILY_ADD)
public class DailyAddActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 提交

    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_add;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_daily_add);
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
                TaskFragment.STATE_DAILY = true;
                break;
            default:
                break;
        }
    }
}
