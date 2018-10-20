package com.turingoal.qh.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
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
 * 电话
 */

@Route(path = ConstantActivityPath.TELEPHONE)
public class TelephoneActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 确定
    @BindView(R.id.etTelephone)
    EditText etTelephone; // 电话

    @Override
    protected int getLayoutId() {
        return R.layout.activity_telephone;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_telephone);
        tvEnd.setVisibility(View.VISIBLE);
        etTelephone.setText(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_TELEPHONE));
        etTelephone.setSelection(etTelephone.getText().toString().trim().length()); // 光标移动到文本框末尾
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
                if (RegexUtils.isTel(etTelephone.getText().toString().trim())) {
                    TgSystemHelper.setSP(etTelephone.getText().toString().trim(), TgUserPreferences.SharedPreferencesKey.USER_TELEPHONE);
                    EventBus.getDefault().post(new MessageEvent("telephone", etTelephone.getText().toString().trim()));
                    defaultFinish();
                } else {
                    ToastUtils.showShort(R.string.telephone_no);
                }
                break;
            default:
                break;
        }
    }
}
