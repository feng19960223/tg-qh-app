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
 * 手机
 */

@Route(path = ConstantActivityPath.PHONE)
public class PhoneActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 确定
    @BindView(R.id.etPhone)
    EditText etPhone; // 手机

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_phone);
        tvEnd.setVisibility(View.VISIBLE);
        etPhone.setText(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_PHONE));
        etPhone.setSelection(etPhone.getText().toString().trim().length()); // 光标移动到文本框末尾
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
                if (RegexUtils.isMobileExact(etPhone.getText().toString().trim())) {
                    TgSystemHelper.setSP(etPhone.getText().toString().trim(), TgUserPreferences.SharedPreferencesKey.USER_PHONE);
                    EventBus.getDefault().post(new MessageEvent("phone", etPhone.getText().toString().trim()));
                    defaultFinish();
                } else {
                    ToastUtils.showShort(R.string.phone_no);
                }
                break;
            default:
                break;
        }
    }
}
