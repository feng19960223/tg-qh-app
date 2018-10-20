package com.turingoal.qh.ui.activity;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.utils.TgDialogUtil;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 */

@Route(path = ConstantActivityPath.REGISTER)
public class RegisterActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.etEnterprise)
    EditText etEnterprise; //　企业全称
    @BindView(R.id.etUsername)
    EditText etUsername; // 管理员姓名
    @BindView(R.id.etMail)
    EditText etMail; // 邮箱
    @BindView(R.id.rgSex)
    RadioGroup rgSex; // 性别
    @BindView(R.id.etPassword)
    EditText etPassword; // 密码
    @BindView(R.id.etPassword2)
    EditText etPassword2; // 确认密码
    @BindView(R.id.cbAgree)
    CheckBox cbAgree; // 同意
    @BindView(R.id.btnRegister)
    Button btnRegister; // 注册

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_register);
    }

    /**
     * OnClick
     */
    @OnClick({R.id.ivStart, R.id.tvAgreement, R.id.btnRegister})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivStart:
                defaultFinish();
                break;
            case R.id.tvAgreement: // 协议
                TgSystemHelper.handleIntent(ConstantActivityPath.AGREEMENT);
                break;
            case R.id.btnRegister: // 注册
                register();
                break;
            default:
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        if (!cbAgree.isChecked()) { // 没勾选同意
            ToastUtils.showShort(R.string.agree_no);
            return;
        }
        String enterprise = etEnterprise.getText().toString().trim();
        if (enterprise.length() < 2) { // 企业名称不对
            ToastUtils.showShort(R.string.enterprise_no);
            return;
        }
        String username = etUsername.getText().toString().trim();
        if (username.length() < 2) { // 管理员名字不对
            ToastUtils.showShort(R.string.username_no);
            return;
        }
        String mail = etMail.getText().toString().trim();
        if (!RegexUtils.isEmail(mail)) { // 邮箱不对
            ToastUtils.showShort(R.string.mail_no);
            return;
        }
        String password = etPassword.getText().toString().trim();
        if (password.length() < 6) { // 密码不对
            ToastUtils.showShort(R.string.password_no);
            return;
        }
        String password2 = etPassword2.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) { // 2次确认密码为空
            ToastUtils.showShort(R.string.password2_no);
            return;
        }
        if (!password.equals(password2)) { // 2次密码不一致
            ToastUtils.showShort(R.string.password12_no);
            return;
        }
        String sex = rgSex.getCheckedRadioButtonId() == R.id.rbMale ? getString(R.string.personal_gender_man) : getString(R.string.personal_gender_woman);
        TgDialogUtil.showConfirmDialog(this, getString(R.string.app_name), getString(R.string.register_success), new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                EventBus.getDefault().post(etMail.getText().toString().trim());
                defaultFinish();
            }
        });
    }
}
