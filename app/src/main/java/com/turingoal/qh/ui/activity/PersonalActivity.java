package com.turingoal.qh.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.turingoal.android.photopicker.PhotoPicker;
import com.turingoal.common.app.TgSystemHelper;
import com.turingoal.common.app.TgUserPreferences;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.common.utils.GlideUtil;
import com.turingoal.common.utils.TgDateTimePickUtil;
import com.turingoal.common.utils.TgDateUtil;
import com.turingoal.common.utils.TgStringUtil;
import com.turingoal.common.widget.SquareImageView;
import com.turingoal.qh.R;
import com.turingoal.qh.bean.MessageEvent;
import com.turingoal.qh.constants.ConstantActivityPath;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人信息
 */

@Route(path = ConstantActivityPath.PERSONAL)
public class PersonalActivity extends TgBaseActivity {
    @BindView(R.id.ivBg)
    ImageView ivBg; // 背景
    @BindView(R.id.ivAvatar)
    SquareImageView ivAvatar; // 头像
    @BindView(R.id.tvName)
    TextView tvName; // 名字
    @BindView(R.id.tvAddress)
    TextView tvAddress; // 地址
    @BindView(R.id.tvMail)
    TextView tvMail; // 邮箱
    @BindView(R.id.tvEntryTime)
    TextView tvEntryTime; // 入职时间
    @BindView(R.id.tvStageName)
    TextView tvStageName; // 花名
    @BindView(R.id.tvGender)
    TextView tvGender; // 性别
    @BindView(R.id.tvPhone)
    TextView tvPhone; // 手机
    @BindView(R.id.tvBirthday)
    TextView tvBirthday; // 生日
    @BindView(R.id.tvTencent)
    TextView tvTencent; // QQ号
    @BindView(R.id.tvWechat)
    TextView tvWechat; // 微信号
    @BindView(R.id.tvTelephone)
    TextView tvTelephone; // 电话

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initialized() {
        EventBus.getDefault().register(this);
        updateAvatar(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_AVATAR));
        setText(tvName, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_REAL_NAME));
        setText(tvAddress, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_ADDRESS));
        setText(tvMail, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_MAIL));
        setText(tvEntryTime, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_ENTRY_TIME));
        setText(tvStageName, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_STAGE_NAME));
        updateGender(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_GENDER));
        setText(tvPhone, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_PHONE).replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        setText(tvBirthday, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_BIRTHDAY));
        setText(tvTencent, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_TENCENT));
        setText(tvWechat, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_WECHAT));
        setText(tvTelephone, TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_TELEPHONE));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getKey().equals("address")) { // 地址
            setText(tvAddress, event.getMessage());
        } else if (event.getKey().equals("stageName")) { // 花名
            setText(tvStageName, event.getMessage());
        } else if (event.getKey().equals("phone")) { // 手机
            setText(tvPhone, event.getMessage().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        } else if (event.getKey().equals("tencent")) { // QQ号
            setText(tvTencent, event.getMessage());
        } else if (event.getKey().equals("wechat")) { // 微信号
            setText(tvWechat, event.getMessage());
        } else if (event.getKey().equals("telephone")) { // 电话
            setText(tvTelephone, event.getMessage());
        }
    }

    /**
     * 给TextView设置str，如果str为空，这设置空白
     */
    private void setText(final TextView textView, final String str) {
        if (TgStringUtil.isEmpty(str)) {
            textView.setText(R.string.personal_empty);
        } else {
            textView.setText(str);
        }
    }

    /**
     * 更新头像和背景图片UI
     */
    private void updateAvatar(final String url) {
        GlideUtil.load(this, url, ivBg);
        GlideUtil.loadImage(this, url, ivAvatar);
    }

    /**
     * 更新性别UI
     *
     * @param type 1女性UI，其他为男性UI
     */
    public void updateGender(final String type) {
        if (type.equals(getString(R.string.personal_gender_woman))) {
            Drawable drawableStartWoman = getDrawable(R.drawable.ic_woman);
            drawableStartWoman.setBounds(0, 0, drawableStartWoman.getMinimumWidth(), drawableStartWoman.getMinimumHeight());
            Drawable drawableEndWoman = getDrawable(R.drawable.ic_arrow_end_24dp);
            drawableEndWoman.setBounds(0, 0, drawableEndWoman.getMinimumWidth(), drawableEndWoman.getMinimumHeight());
            tvGender.setCompoundDrawables(drawableStartWoman, null, drawableEndWoman, null);
            tvGender.setText(R.string.personal_gender_woman);
        } else {
            Drawable drawableStartMan = getDrawable(R.drawable.ic_man);
            drawableStartMan.setBounds(0, 0, drawableStartMan.getMinimumWidth(), drawableStartMan.getMinimumHeight());
            Drawable drawableEndMan = getDrawable(R.drawable.ic_arrow_end_24dp);
            drawableEndMan.setBounds(0, 0, drawableEndMan.getMinimumWidth(), drawableEndMan.getMinimumHeight());
            tvGender.setCompoundDrawables(drawableStartMan, null, drawableEndMan, null);
            tvGender.setText(R.string.personal_gender_man);
        }
    }

    /**
     * OnClick
     */
    @OnClick({R.id.ivAvatar, R.id.ivStart, R.id.tvAddress, R.id.llStageName, R.id.llGender, R.id.llPhone, R.id.llBirthday, R.id.llTencent, R.id.llWechat, R.id.llTelephone})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivStart: // 返回
                // 注意这里不使用finish
                ActivityCompat.finishAfterTransition(this);
                break;
            case R.id.ivAvatar: // 头像
                PhotoPicker.selectPic(this, 400, new PhotoPicker.PicCallBack() {
                    @Override
                    public void onPicSelected(String[] strings) {
                        TgSystemHelper.setSP(strings[0], TgUserPreferences.SharedPreferencesKey.USER_AVATAR);
                        updateAvatar(strings[0]);
                        EventBus.getDefault().post(strings[0]);
                    }
                });
                break;
            case R.id.tvAddress: // 地址
                TgSystemHelper.handleIntent(ConstantActivityPath.ADDRESS);
                break;
            case R.id.llStageName: // 花名
                TgSystemHelper.handleIntent(ConstantActivityPath.STAGE_NAME);
                break;
            case R.id.llGender: // 性别
                new MaterialDialog.Builder(this).itemsGravity(GravityEnum.CENTER).dividerColorRes(R.color.colorPrimary).items(R.array.personal_gender).itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        if (tvGender.getText().equals(text)) {
                            return;
                        }
                        TgSystemHelper.setSP(String.valueOf(text), TgUserPreferences.SharedPreferencesKey.USER_GENDER);
                        updateGender(String.valueOf(text));
                    }
                }).show();
                break;
            case R.id.llPhone: // 手机
                TgSystemHelper.handleIntent(ConstantActivityPath.PHONE);
                break;
            case R.id.llBirthday: // 生日
                new TgDateTimePickUtil(this, new Date(), null, new Date(), "").dateTimePickDialog(new TgDateTimePickUtil.OnSubmitSelectDateListener() {
                    @Override
                    public void onSumbitSelect(Date date) {
                        if (!TgDateUtil.isSameDate(date, TgDateUtil.string2Date(TgSystemHelper.getSP(TgUserPreferences.SharedPreferencesKey.USER_BIRTHDAY), TgDateUtil.FORMAT_YYYY_MM_DD))) { // 判断一下，生日和选中的日期是不是通一天，如果是同一天，这不用触发网络请求
                            TgSystemHelper.setSP(TgDateUtil.date2String(date, TgDateUtil.FORMAT_YYYY_MM_DD), TgUserPreferences.SharedPreferencesKey.USER_BIRTHDAY);
                            setText(tvBirthday, TgDateUtil.date2String(date, TgDateUtil.FORMAT_YYYY_MM_DD));
                        }
                    }
                });
                break;
            case R.id.llTencent: // QQ号
                TgSystemHelper.handleIntent(ConstantActivityPath.TENCENT);
                break;
            case R.id.llWechat: //微信
                TgSystemHelper.handleIntent(ConstantActivityPath.WECHAT);
                break;
            case R.id.llTelephone: // 电话
                TgSystemHelper.handleIntent(ConstantActivityPath.TELEPHONE);
                break;
            default:
                break;
        }
    }
}
