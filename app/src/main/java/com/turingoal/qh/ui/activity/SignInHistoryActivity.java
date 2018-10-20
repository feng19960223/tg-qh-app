package com.turingoal.qh.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;
import com.turingoal.qh.ui.fragment.TaskFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 签到历史页面
 */

@Route(path = ConstantActivityPath.SIGN_IN_HISTORY)
public class SignInHistoryActivity extends TgBaseActivity implements CalendarView.OnDateSelectedListener, CalendarView.OnMonthChangeListener {
    @BindView(R.id.tvMonthDay)
    TextView tvMonthDay; // 月 日
    @BindView(R.id.tvYear)
    TextView tvYear; // 年
    @BindView(R.id.tvLunar)
    TextView tvLunar; // 阴历
    @BindView(R.id.tvCurrentDay)
    TextView tvCurrentDay; // 当前日期
    @BindView(R.id.calendarView)
    CalendarView calendarView; // 日历
    @BindView(R.id.tvSignInHint)
    TextView tvSignInHint; // 签到提示
    @BindView(R.id.btnSignIn)
    Button btnSignIn; // 签到

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_in_history;
    }

    @Override
    protected void initialized() {
        calendarView.setOnMonthChangeListener(this); // 滑动改变月份监听
        calendarView.setOnDateSelectedListener(this); // 点击选择日期监听
        tvYear.setText(String.valueOf(calendarView.getCurYear()));
        tvMonthDay.setText(String.format(getString(R.string.history_sign_in_month_day), calendarView.getCurMonth(), calendarView.getCurDay()));
        tvLunar.setText(R.string.history_sign_in_today);
        tvCurrentDay.setText(String.valueOf(calendarView.getCurDay()));
        tvSignInHint.setText(String.format(getString(R.string.history_sign_in_hint), "1", "20"));
        if (TaskFragment.STATE_SIGN_IN) { // 如果已经签到
            btnSignIn.setEnabled(false);
            btnSignIn.setText(R.string.history_sign_in_over);
        }
    }

    /**
     * OnClick
     */
    @OnClick({R.id.flCurrent, R.id.btnSignIn})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.flCurrent: // 回到今天
                calendarView.scrollToCurrent();
                break;
            case R.id.btnSignIn:
                if (!TaskFragment.STATE_SIGN_IN) { // 没有签到
                    TaskFragment.STATE_SIGN_IN = true;
                    btnSignIn.setEnabled(false);
                    btnSignIn.setText(R.string.history_sign_in_over);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 选择一个日期，更新ui
     */
    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        tvLunar.setVisibility(View.VISIBLE);
        tvYear.setVisibility(View.VISIBLE);
        tvMonthDay.setText(String.format(getString(R.string.history_sign_in_month_day), calendar.getMonth(), calendar.getDay()));
        tvYear.setText(String.valueOf(calendar.getYear()));
        tvLunar.setText(calendar.getLunar());
    }

    /**
     * 滑动日历，月份变化
     */
    @Override
    public void onMonthChange(int year, int month) {
        final List<Calendar> schemes = new ArrayList<>();
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFF40DB25, "签"));
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFF40DB25, "签"));
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFF40DB25, "签"));
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFF40DB25, "签"));
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFF40DB25, "签"));
        calendarView.setSchemeDate(schemes);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color); // 如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }
}
