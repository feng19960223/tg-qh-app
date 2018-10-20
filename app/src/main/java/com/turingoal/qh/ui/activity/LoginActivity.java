package com.turingoal.qh.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.RegexUtils;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.app.TgUserPreferences;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.widget.ClearEditText;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 */

@Route(path = ConstantActivityPath.LOGIN)
public class LoginActivity extends TgBaseActivity {

    @BindView(R.id.ivStart)
    ImageView ivStart; // 返回按钮，隐藏
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.etUsername)
    ClearEditText etUsername; // 带删除按钮的输入框，用户名
    @BindView(R.id.tilUsername)
    TextInputLayout tilUsername; // 用户名控制
    @BindView(R.id.etPassword)
    EditText etPassword; // 密码
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword; // 密码控制
    @BindView(R.id.btnLogin)
    Button btnLogin; // 登录
    private static final int MAX_USERNAME_LEN = 45; // 用户名最大长度
    private static final int MIN_USERNAME_LEN = 5; // 用户名最小长度
    private static final int MAX_PASSWORD_LEN = 24; // 密码最大长度
    private static final int MIN_PASSWORD_LEN = 6; // 密码最小长度
    private boolean isUsernameEnable = false; // 用户名是否正确
    private boolean isPasswordEnable = false; // 密码是否正确

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initialized() {
        EventBus.getDefault().register(this);
        ivStart.setVisibility(View.GONE);
        tvTitle.setText(R.string.activity_login);
        etUsername.addTextChangedListener(userNameTextWatcher); // 用户名内容监
        etPassword.addTextChangedListener(passwordTextWatcher); // 密码内容监听
        tilPassword.setPasswordVisibilityToggleEnabled(true); // 点击显示密码
        etUsername.setOnTextClearListener(new ClearEditText.OnTextClearListener() { // 点击了清空按鈕
            @Override
            public void textClear() {
                tilUsername.setErrorEnabled(false);
                isUsernameEnable = false;
                btnLogin.setEnabled(false);
            }
        });
        if (!TextUtils.isEmpty(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_NAME))) { // 用户名不为空
            etUsername.setText(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_NAME)); // 设置用户名
            isUsernameEnable = true;
        }
        etUsername.setSelection(etUsername.getText().toString().trim().length()); // 光标移动到文本框末尾
    }

    /**
     * event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        etUsername.setText(event); // 设置用户名
        isUsernameEnable = true;
        tilUsername.setErrorEnabled(false);
        etPassword.setText("");
        isPasswordEnable = false;
        tilUsername.setErrorEnabled(false);
        btnLogin.setEnabled(false); // 按钮不可点击
        etPassword.requestFocus(); // 获取光标
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 用户名EditText监听
     */
    private TextWatcher userNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
        }

        @Override
        public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
        }

        @Override
        public void afterTextChanged(final Editable editable) {
            if (editable.toString().trim().length() != 0 && editable.toString().trim().length() <= MAX_USERNAME_LEN) { // 3-45个字
                isUsernameEnable = true;
            } else {
                isUsernameEnable = false;
            }
            if (editable.toString().trim().length() > MAX_USERNAME_LEN) { // 字数大于最大限制，提示错误
                tilUsername.setError(getString(R.string.string_username_max_hint));
            } else {
                tilUsername.setErrorEnabled(false);
            }
            if (isUsernameEnable && isPasswordEnable) { // 用户名和密码同时正确才可以点
                btnLogin.setEnabled(true); // 启用提交按钮
            } else {
                btnLogin.setEnabled(false); // 按钮不可点击
            }
        }
    };

    /**
     * 密码EditText监听
     */
    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
        }

        @Override
        public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
        }

        @Override
        public void afterTextChanged(final Editable editable) {
            if (editable.toString().trim().length() != 0 && editable.toString().trim().length() <= MAX_PASSWORD_LEN) { // 3-24个字
                isPasswordEnable = true;
            } else {
                isPasswordEnable = false;
            }
            if (editable.toString().trim().length() > MAX_PASSWORD_LEN) { // 字数大于最大限制，提示错误
                tilPassword.setError(getString(R.string.string_password_max_hint));
            } else {
                tilPassword.setErrorEnabled(false);
            }
            if (isUsernameEnable && isPasswordEnable) { // 用户名和密码同时正确才可以点
                btnLogin.setEnabled(true); // 启用提交按钮
            } else {
                btnLogin.setEnabled(false); // 按钮不可点击
            }
        }
    };

    /**
     * 登录
     */
    private void login() {
        if (etUsername.getText().toString().trim().length() < MIN_USERNAME_LEN) {
            tilUsername.setError(getString(R.string.string_username_min_hint));
            isUsernameEnable = false;
            btnLogin.setEnabled(false); // 按钮不可点击
            return;
        }
        if (!RegexUtils.isEmail(etUsername.getText().toString().trim())) { // 格式不对
            tilUsername.setError(getString(R.string.string_mail_verify_hint));
            isUsernameEnable = false;
            btnLogin.setEnabled(false); // 按钮不可点击
            return;
        }
        if (etPassword.getText().toString().trim().length() < MIN_PASSWORD_LEN) {
            tilPassword.setError(getString(R.string.string_password_min_hint));
            isPasswordEnable = false;
            btnLogin.setEnabled(false); // 按钮不可点击
            return;
        }
        TgSystemHelper.handleIntentAndFinish(ConstantActivityPath.MAIN, LoginActivity.this); // 跳转到主页面,关闭当前页面
    }

    /**
     * OnClick
     */
    @OnClick({R.id.btnLogin, R.id.tvForget, R.id.tvRegister})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.tvForget:
                TgSystemHelper.handleIntent(ConstantActivityPath.FORGET);
                break;
            case R.id.tvRegister:
                TgSystemHelper.handleIntent(ConstantActivityPath.REGISTER);
                break;
            default:
                break;
        }
    }
}
