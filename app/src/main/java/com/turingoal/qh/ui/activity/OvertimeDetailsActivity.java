package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.OvertimeBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 加班申请查看页面
 */

@Route(path = ConstantActivityPath.OVERTIME_DETAILS)
public class OvertimeDetailsActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 删除
    @Autowired
    OvertimeBean overtimeBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_overtime_details;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_overtime_details);
        tvEnd.setText(R.string.delete);
        tvEnd.setVisibility(View.VISIBLE); // 如果审核通过不显示，不能刪除
        Log.i(TAG, "initialized: " + overtimeBean.getStr());
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
