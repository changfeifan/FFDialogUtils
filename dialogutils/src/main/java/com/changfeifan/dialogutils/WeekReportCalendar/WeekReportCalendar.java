package com.changfeifan.dialogutils.WeekReportCalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.changfeifan.dialogutils.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.changfeifan.dialogutils.WeekReportCalendar.DaySet.DAY_STATE_HAVE_DATA;
import static com.changfeifan.dialogutils.WeekReportCalendar.DaySet.DAY_STATE_NO_DATA;


/**
 * Created by changfeifan on 2017/5/16.
 */

public class WeekReportCalendar extends FrameLayout implements DayClickListener, View.OnClickListener {

    private int dx = 5;//周报日期偏移单位

    private List<DaySet> listDaySet = new ArrayList<>();
    private List<SquareImageView> listWeekReport = new ArrayList<>();
    private int year = 0;
    private int month = 0;

    private DaySet daySet1;
    private DaySet daySet2;
    private DaySet daySet3;
    private DaySet daySet4;
    private DaySet daySet5;
    private DaySet daySet6;
    private DaySet daySet7;
    private DaySet daySet8;
    private DaySet daySet9;
    private DaySet daySet10;
    private DaySet daySet11;
    private DaySet daySet12;
    private DaySet daySet13;
    private DaySet daySet14;
    private DaySet daySet15;
    private DaySet daySet16;
    private DaySet daySet17;
    private DaySet daySet18;
    private DaySet daySet19;
    private DaySet daySet20;
    private DaySet daySet21;
    private DaySet daySet22;
    private DaySet daySet23;
    private DaySet daySet24;
    private DaySet daySet25;
    private DaySet daySet26;
    private DaySet daySet27;
    private DaySet daySet28;
    private DaySet daySet29;
    private DaySet daySet30;
    private DaySet daySet31;
    private DaySet daySet32;
    private DaySet daySet33;
    private DaySet daySet34;
    private DaySet daySet35;
    private DaySet daySet36;
    private DaySet daySet37;
    private DaySet daySet38;
    private DaySet daySet39;
    private DaySet daySet40;
    private DaySet daySet41;
    private DaySet daySet42;
    private SquareImageView weekReport1;
    private SquareImageView weekReport2;
    private SquareImageView weekReport3;
    private SquareImageView weekReport4;
    private SquareImageView weekReport5;
    private SquareImageView weekReport6;

    private ImageView ivLeft;
    private ImageView ivRight;
    private TextView tvDate;

    private LinearLayout llBottom;

    private JSONObject objectYearDailyData;
    private boolean isNewYear = false;


    private OnWeekReportClickListener onWeekReportClickListener;

    public WeekReportCalendar(Context context) {
        this(context, null);
    }

    public WeekReportCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekReportCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.calendar_week_report, this);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void onDayClick(View view, int day, int dayState) {
        if (onWeekReportClickListener != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);//fixme 需要月减一
            onWeekReportClickListener.onDayClick(day, dayState, calendar);
        }
    }

    @Override
    public void onClick(View view) {
        Calendar calendar = Calendar.getInstance();
        if (view.getId() == R.id.weekReport1) {
            if (onWeekReportClickListener != null) {
                calendar.set(year, month - 1, daySet7.getDayNum());
                onWeekReportClickListener.onWeekReportClick(year, month, daySet7.getDayNum(), calendar);
            }
        } else if (view.getId() == R.id.weekReport2) {
            if (onWeekReportClickListener != null) {
                calendar.set(year, month - 1, daySet14.getDayNum());
                onWeekReportClickListener.onWeekReportClick(year, month, daySet14.getDayNum(), calendar);
            }
        } else if (view.getId() == R.id.weekReport3) {
            if (onWeekReportClickListener != null) {
                calendar.set(year, month - 1, daySet21.getDayNum());
                onWeekReportClickListener.onWeekReportClick(year, month, daySet21.getDayNum(), calendar);
            }
        } else if (view.getId() == R.id.weekReport4) {
            if (onWeekReportClickListener != null) {
                calendar.set(year, month - 1, daySet28.getDayNum());
                onWeekReportClickListener.onWeekReportClick(year, month, daySet28.getDayNum(), calendar);
            }
        } else if (view.getId() == R.id.weekReport5) {
            if (onWeekReportClickListener != null) {
                calendar.set(year, month - 1, daySet35.getDayNum());
                onWeekReportClickListener.onWeekReportClick(year, month, daySet35.getDayNum(), calendar);
            }
        } else if (view.getId() == R.id.weekReport6) {
            if (onWeekReportClickListener != null) {
                calendar.set(year, month - 1, daySet42.getDayNum());
                onWeekReportClickListener.onWeekReportClick(year, month, daySet42.getDayNum(), calendar);
            }
        } else if (view.getId() == R.id.ivLeft) {
            if (month == 1) {
                month = 12;
                year--;
                isNewYear = true;
                objectYearDailyData = null;
                setMonthAndYearToCalculate(year, month, isNewYear);
            } else {
                month--;
                isNewYear = false;
                setMonthAndYearToCalculate(year, month, isNewYear);
            }
            onWeekReportClickListener.onLeftMonthButtonClick(year, month);
            onWeekReportClickListener.onDateChange(year, month);
        } else if (view.getId() == R.id.ivRight) {
            if (month == 12) {
                year++;
                month = 1;
                isNewYear = true;
                objectYearDailyData = null;
                setMonthAndYearToCalculate(year, month, isNewYear);
            } else {
                month++;
                isNewYear = false;
                setMonthAndYearToCalculate(year, month, isNewYear);
            }
            onWeekReportClickListener.onRightMonthButtonClick(year, month);
            onWeekReportClickListener.onDateChange(year, month);
        }


    }

    public OnWeekReportClickListener getOnWeekReportClickListener() {
        return onWeekReportClickListener;
    }

    public void setOnWeekReportClickListener(OnWeekReportClickListener onWeekReportClickListener) {
        this.onWeekReportClickListener = onWeekReportClickListener;
    }

    /**
     * 初始化weekReport
     */
    public void init() {
        ivLeft = (ImageView) findViewById(R.id.ivLeft);
        ivRight = (ImageView) findViewById(R.id.ivRight);
        tvDate = (TextView) findViewById(R.id.tvDate);
        llBottom = (LinearLayout) findViewById(R.id.llBottom);

        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);

        daySet1 = (DaySet) findViewById(R.id.daySet1);
        daySet2 = (DaySet) findViewById(R.id.daySet2);
        daySet3 = (DaySet) findViewById(R.id.daySet3);
        daySet4 = (DaySet) findViewById(R.id.daySet4);
        daySet5 = (DaySet) findViewById(R.id.daySet5);
        daySet6 = (DaySet) findViewById(R.id.daySet6);
        daySet7 = (DaySet) findViewById(R.id.daySet7);
        daySet8 = (DaySet) findViewById(R.id.daySet8);
        daySet9 = (DaySet) findViewById(R.id.daySet9);
        daySet10 = (DaySet) findViewById(R.id.daySet10);
        daySet11 = (DaySet) findViewById(R.id.daySet11);
        daySet12 = (DaySet) findViewById(R.id.daySet12);
        daySet13 = (DaySet) findViewById(R.id.daySet13);
        daySet14 = (DaySet) findViewById(R.id.daySet14);
        daySet15 = (DaySet) findViewById(R.id.daySet15);
        daySet16 = (DaySet) findViewById(R.id.daySet16);
        daySet17 = (DaySet) findViewById(R.id.daySet17);
        daySet18 = (DaySet) findViewById(R.id.daySet18);
        daySet19 = (DaySet) findViewById(R.id.daySet19);
        daySet20 = (DaySet) findViewById(R.id.daySet20);
        daySet21 = (DaySet) findViewById(R.id.daySet21);
        daySet22 = (DaySet) findViewById(R.id.daySet22);
        daySet23 = (DaySet) findViewById(R.id.daySet23);
        daySet24 = (DaySet) findViewById(R.id.daySet24);
        daySet25 = (DaySet) findViewById(R.id.daySet25);
        daySet26 = (DaySet) findViewById(R.id.daySet26);
        daySet27 = (DaySet) findViewById(R.id.daySet27);
        daySet28 = (DaySet) findViewById(R.id.daySet28);
        daySet29 = (DaySet) findViewById(R.id.daySet29);
        daySet30 = (DaySet) findViewById(R.id.daySet30);
        daySet31 = (DaySet) findViewById(R.id.daySet31);
        daySet32 = (DaySet) findViewById(R.id.daySet32);
        daySet33 = (DaySet) findViewById(R.id.daySet33);
        daySet34 = (DaySet) findViewById(R.id.daySet34);
        daySet35 = (DaySet) findViewById(R.id.daySet35);
        daySet36 = (DaySet) findViewById(R.id.daySet36);
        daySet37 = (DaySet) findViewById(R.id.daySet37);
        daySet38 = (DaySet) findViewById(R.id.daySet38);
        daySet39 = (DaySet) findViewById(R.id.daySet39);
        daySet40 = (DaySet) findViewById(R.id.daySet40);
        daySet41 = (DaySet) findViewById(R.id.daySet41);
        daySet42 = (DaySet) findViewById(R.id.daySet42);

        weekReport1 = (SquareImageView) findViewById(R.id.weekReport1);
        weekReport2 = (SquareImageView) findViewById(R.id.weekReport2);
        weekReport3 = (SquareImageView) findViewById(R.id.weekReport3);
        weekReport4 = (SquareImageView) findViewById(R.id.weekReport4);
        weekReport5 = (SquareImageView) findViewById(R.id.weekReport5);
        weekReport6 = (SquareImageView) findViewById(R.id.weekReport6);

        listDaySet.add(daySet1);
        listDaySet.add(daySet2);
        listDaySet.add(daySet3);
        listDaySet.add(daySet4);
        listDaySet.add(daySet5);
        listDaySet.add(daySet6);
        listDaySet.add(daySet7);
        listDaySet.add(daySet8);
        listDaySet.add(daySet9);
        listDaySet.add(daySet10);
        listDaySet.add(daySet11);
        listDaySet.add(daySet12);
        listDaySet.add(daySet13);
        listDaySet.add(daySet14);
        listDaySet.add(daySet15);
        listDaySet.add(daySet16);
        listDaySet.add(daySet17);
        listDaySet.add(daySet18);
        listDaySet.add(daySet19);
        listDaySet.add(daySet20);
        listDaySet.add(daySet21);
        listDaySet.add(daySet22);
        listDaySet.add(daySet23);
        listDaySet.add(daySet24);
        listDaySet.add(daySet25);
        listDaySet.add(daySet26);
        listDaySet.add(daySet27);
        listDaySet.add(daySet28);
        listDaySet.add(daySet29);
        listDaySet.add(daySet30);
        listDaySet.add(daySet31);
        listDaySet.add(daySet32);
        listDaySet.add(daySet33);
        listDaySet.add(daySet34);
        listDaySet.add(daySet35);
        listDaySet.add(daySet36);
        listDaySet.add(daySet37);
        listDaySet.add(daySet38);
        listDaySet.add(daySet39);
        listDaySet.add(daySet40);
        listDaySet.add(daySet41);
        listDaySet.add(daySet42);

        listWeekReport.add(weekReport1);
        listWeekReport.add(weekReport2);
        listWeekReport.add(weekReport3);
        listWeekReport.add(weekReport4);
        listWeekReport.add(weekReport5);
        listWeekReport.add(weekReport6);

        for (DaySet daySet : listDaySet) {
            daySet.setDayClickListener(this);
        }

        for (SquareImageView weekReport : listWeekReport) {
            weekReport.setOnClickListener(this);
        }

    }

    /**
     * set当年数据object
     */
    public void setDateInfoObject(JSONObject object) {
        Log.e("setDateInfoObject", object.toString());
        isNewYear = false;
        objectYearDailyData = object;
        JSONArray array = objectYearDailyData.optJSONArray(month + "");
        if (array != null)
            reDrawDateStateThisMonth(array);
    }

    /**
     * 重绘当前月数据
     */
    public void reDrawDateStateThisMonth(JSONArray array) {
        for (DaySet daySet : listDaySet) {
            Log.e("num", daySet.dayNum + "");
            if (daySet.getDayNum() == 0) continue;
            if (array != null) {
                boolean b = array.optBoolean(daySet.dayNum - 1);
                Log.e("boolean", b + "");

                if (b)
                    daySet.setDayState(DAY_STATE_HAVE_DATA);
                else
                    daySet.setDayState(DAY_STATE_NO_DATA);
            }

        }
        invalidate();
    }

    /**
     * 通过用户设置起始时间来计算显示界面。
     * Month {1-12} 计算时需要在calendar的时候减一
     */
    public void setMonthAndYearToCalculate(int year, int month, boolean isNewYear) {
        resetAllDateSet();
        if (onWeekReportClickListener != null)
            onWeekReportClickListener.onDateChange(year, month);
        this.year = year;
        this.month = month;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);//fixme 计算式需要Month-1设置
        int startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int allDayNum = getCurrentMonthDay(calendar);
        Log.e("startDayOfWeek", ":" + startDayOfWeek);
        Log.e("allDayNum", ":" + allDayNum);

        startDayOfWeek = startDayOfWeek == 1 ? 7 : startDayOfWeek - 1;
        for (int i = 0; i < 42; i++) {
            if (i >= startDayOfWeek - 1 && i < (startDayOfWeek - 1 + allDayNum)) {
                listDaySet.get(i).setDayNum(i - startDayOfWeek + 2);
                Log.e("listDaySet1", listDaySet.get(i).getDayNum() + "");
            } else {
                listDaySet.get(i).setVisibility(INVISIBLE);
                listDaySet.get(i).setDayNum(0);
            }
        }
        tvDate.setText(year + "-" + (month));
        if (daySet36.getVisibility() == INVISIBLE)
            llBottom.setVisibility(GONE);
        else
            llBottom.setVisibility(VISIBLE);
        if (objectYearDailyData != null && !isNewYear)
            reDrawDateStateThisMonth(objectYearDailyData.optJSONArray(month + ""));
        invalidate();
    }

    public void resetAllDateSet() {
        for (DaySet datSet : listDaySet) {
            datSet.setDayNum(0);
            datSet.setVisibility(VISIBLE);
        }
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay(Calendar calendar) {
        Calendar a = calendar;
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
