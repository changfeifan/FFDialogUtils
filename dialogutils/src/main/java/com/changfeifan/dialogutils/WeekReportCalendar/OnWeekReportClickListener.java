package com.changfeifan.dialogutils.WeekReportCalendar;

import java.util.Calendar;

/**
 * Created by changfeifan on 2017/5/18.
 */

public interface OnWeekReportClickListener {

    /**
     * 点击日期/click the day
     * 返回参数/require param
     *
     * @param dayNum   日期
     * @param dayState 当前日期状态
     * @param calendar 当前日期calendar
     */
    void onDayClick(int dayNum, int dayState, Calendar calendar);

    /**
     * 点击左侧月份按钮
     */
    void onLeftMonthButtonClick(int year, int month);

    /**
     * 点击右侧月份按钮
     */
    void onRightMonthButtonClick(int year, int month);

    /**
     * 点击周报按钮
     *
     * @param year  点击周报年份
     * @param month 点击周报月份
     * @param day   返回当前周的最后一天
     */
    void onWeekReportClick(int year, int month, int day, Calendar calendar);

    /**
     * 当时间改变时通知
     *
     * @param year  年
     * @param month 月
     */
    void onDateChange(int year, int month);
}
