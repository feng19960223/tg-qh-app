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
 * 地址
 */

@Route(path = ConstantActivityPath.ADDRESS)
public class AddressActivity extends TgBaseActivity {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // title
    @BindView(R.id.tvEnd)
    TextView tvEnd; // 确定
    @BindView(R.id.etAddress)
    EditText etAddress; // 地址

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_address);
        tvEnd.setVisibility(View.VISIBLE);
        etAddress.setText(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_ADDRESS));
        etAddress.setSelection(etAddress.getText().toString().trim().length()); // 光标移动到文本框末尾
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
                TgSystemHelper.setSP(etAddress.getText().toString().trim(), TgUserPreferences.SharedPreferencesKey.USER_ADDRESS);
                EventBus.getDefault().post(new MessageEvent("address", etAddress.getText().toString().trim()));
                defaultFinish();
                break;
            default:
                break;
        }
    }
}
