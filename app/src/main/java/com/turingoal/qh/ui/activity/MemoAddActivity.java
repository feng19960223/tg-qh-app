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
 * 新建备忘页面
 */

@Route(path = ConstantActivityPath.MEMO_ADD)
public class MemoAddActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 保存

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memo_add;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_memo_add);
        tvEnd.setText(R.string.save);
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
