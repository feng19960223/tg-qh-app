package com.turingoal.qh.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.RegexUtils;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.utils.TgDialogUtil;
import com.turingoal.common.widget.ClearEditText;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 找回密码
 */

@Route(path = ConstantActivityPath.FORGET)
public class ForgetActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.etMail)
    ClearEditText etMail; // 带删除按钮的邮箱输入框
    @BindView(R.id.tilMail)
    TextInputLayout tilMail; // 邮箱控制
    @BindView(R.id.btnForget)
    Button btnForget; // 找回按钮
    private static final int MAX_MAIL_LEN = 45; // 邮箱号码最大长度
    private static final int MIN_MAIL_LEN = 6; // 邮箱号码最小长度

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_forget);
        etMail.addTextChangedListener(mailTextWatcher); // 用户名内容监听
        etMail.setOnTextClearListener(new ClearEditText.OnTextClearListener() {
            @Override
            public void textClear() {
                btnForget.setEnabled(false); // 按钮不可点击
            }
        });
    }

    /**
     * 邮箱EditText监听
     */
    private TextWatcher mailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
        }

        @Override
        public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
        }

        @Override
        public void afterTextChanged(final Editable editable) {
            if (editable.toString().trim().length() > MAX_MAIL_LEN) { // 字数大于最大限制，提示错误
                tilMail.setError(getString(R.string.string_mail_max_hint));
                btnForget.setEnabled(false); // 按钮不可点击
            } else {
                tilMail.setErrorEnabled(false);
                btnForget.setEnabled(true); // 启用提交按钮
            }
        }
    };

    /**
     * 找回密码
     */
    private void verify() {
        if (etMail.getText().toString().trim().length() < MIN_MAIL_LEN) { // 不足6位
            tilMail.setError(getString(R.string.string_mail_min_hint));
            btnForget.setEnabled(false); // 按钮不可点击
            return;
        }
        if (!RegexUtils.isEmail(etMail.getText().toString().trim())) { // 格式不对
            tilMail.setError(getString(R.string.string_mail_verify_hint));
            btnForget.setEnabled(false); // 按钮不可点击
            return;
        }
        TgDialogUtil.showConfirmDialog(this, getString(R.string.app_name), getString(R.string.string_mail_forget), new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                EventBus.getDefault().post(etMail.getText().toString().trim());
                defaultFinish();
            }
        });
    }


    /**
     * OnClick
     */
    @OnClick({R.id.ivStart, R.id.btnForget})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivStart:
                defaultFinish();
                break;
            case R.id.btnForget:
                verify();
                break;
            default:
                break;
        }
    }
}
