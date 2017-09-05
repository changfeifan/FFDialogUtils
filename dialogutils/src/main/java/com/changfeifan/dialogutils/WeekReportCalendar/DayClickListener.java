package com.changfeifan.dialogutils.WeekReportCalendar;

import android.view.View;

/**
 * Created by changfeifan on 2017/5/16.
 */

public interface DayClickListener {
    /**
     * Day点击接口。
     * 本月有数据 DAY_STATE_HAVE_DATA = 1001;
     * 本月无数据 DAY_STATE_NO_DATA = 1002;
     * 非本月有数据 DAY_STATE_NOT_THIS_MONTH_HAVE_DATA = 1003;
     * 非本月无数据 DAY_STATE_NOT_THIS_MONTH_NO_DATA = 1004;
     */

    void onDayClick(View view, int day, int dayState);
}
