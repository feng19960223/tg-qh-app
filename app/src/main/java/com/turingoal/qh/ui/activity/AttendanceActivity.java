package com.turingoal.qh.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.turingoal.common.base.TgBaseActivity;
import com.turingoal.qh.R;
import com.turingoal.qh.constants.ConstantActivityPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 考勤记录
 */

@Route(path = ConstantActivityPath.ATTENDANCE)
public class AttendanceActivity extends TgBaseActivity implements CalendarView.OnDateSelectedListener, CalendarView.OnMonthChangeListener {
    @BindView(R.id.tvTitle)
    TextView tvTitle; // titel
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_attendance;
    }

    @Override
    protected void initialized() {
        tvTitle.setText(R.string.activity_attendance);
        calendarView.setOnMonthChangeListener(this); // 滑动改变月份监听
        calendarView.setOnDateSelectedListener(this); // 点击选择日期监听
        tvYear.setText(String.valueOf(calendarView.getCurYear()));
        tvMonthDay.setText(String.format(getString(R.string.history_sign_in_month_day), calendarView.getCurMonth(), calendarView.getCurDay()));
        tvLunar.setText(R.string.history_sign_in_today);
        tvCurrentDay.setText(String.valueOf(calendarView.getCurDay()));
    }

    /**
     * OnClick
     */
    @OnClick({R.id.ivStart, R.id.flCurrent})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.ivStart:
                defaultFinish();
                break;
            case R.id.flCurrent: // 回到今天
                calendarView.scrollToCurrent();
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
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFFE56DE8, "迟"));
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFFFF833C, "退"));
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFFE8E0A1, "加"));
        schemes.add(getSchemeCalendar(year, month, new Random().nextInt(29), 0xFFABC2FF, "假"));
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
