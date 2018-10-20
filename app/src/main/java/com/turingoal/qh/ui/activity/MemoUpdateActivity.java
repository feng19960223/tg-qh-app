package com.turingoal.qh.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.MemoBean;
import com.turingoal.qh.constants.ConstantActivityPath;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改备忘页面
 */

@Route(path = ConstantActivityPath.MEMO_UPDATE)
public class MemoUpdateActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 完成
    @Autowired
    MemoBean memoBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memo_update;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_memo_update);
        tvEnd.setText(R.string.complete);
        tvEnd.setVisibility(View.VISIBLE);
        Log.i(TAG, "initialized: " + memoBean.getStr());
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
