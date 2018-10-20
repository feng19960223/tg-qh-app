package com.turingoal.qh.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.app.TgUserPreferences;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.MessageEvent;
import com.turingoal.qh.constants.ConstantActivityPath;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 花名
 */

@Route(path = ConstantActivityPath.STAGE_NAME)
public class StageNameActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 确定
    @BindView(R.id.etStageName)
    EditText etStageName; // 花名

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stage_name;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_stage_name);
        tvEnd.setVisibility(View.VISIBLE);
        etStageName.setText(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_STAGE_NAME));
        etStageName.setSelection(etStageName.getText().toString().trim().length()); // 光标移动到文本框末尾
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
                TgSystemHelper.setSP(etStageName.getText().toString().trim(), TgUserPreferences.SharedPreferencesKey.USER_STAGE_NAME);
                EventBus.getDefault().post(new MessageEvent("stageName", etStageName.getText().toString().trim()));
                defaultFinish();
                break;
            default:
                break;
        }
    }
}
