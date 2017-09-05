package com.changfeifan.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.changfeifan.dialogutils.WeekReportCalendar.OnWeekReportClickListener;
import com.changfeifan.dialogutils.WeekReportCalendar.WeekReportCalendar;

import org.json.JSONObject;

import java.util.Calendar;


/**
 * Created by changfeifan on 16/8/18.
 */
public class WeekDateDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private ImageView iv_close;
    private int mDialogType;
    private int mSeries1Index;
    private TextView tv_title;
    private TextView tv_subtitle;
    private TextView tv_have_person_number;
    private ImageView iv_metal;

    private String titleStr = "";
    private String subTitleStr = "";
    private String havePersonNum = "";
    private int metalId = 0;
    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;

    WeekReportCalendar weekReportCalendar;
    WeekReportInterface weekReportInterface;

    private int yearCurrent = 0;
    private int monthCurrent = 0;
    private JSONObject objectDate;


    public static final int DIALOG_TYPE_SINGLE_BUTTON = 1;

    public WeekDateDialog(Context context) {
        this(context, DIALOG_TYPE_SINGLE_BUTTON);
    }


    public WeekDateDialog(Context context, int dialogType) {
        super(context, R.style.simple_dialog_style);

        setCancelable(true);
        setDialogType(dialogType);

        mInAnim = (AnimationSet) AnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
        mOutAnim = (AnimationSet) AnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
        mOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mDialogView.setVisibility(View.GONE);
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        WeekDateDialog.super.dismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_week_report);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        weekReportCalendar = (WeekReportCalendar) findViewById(R.id.weekReportCalendar);
        weekReportCalendar.setMonthAndYearToCalculate(yearCurrent, monthCurrent, false);
        weekReportCalendar.setOnWeekReportClickListener(new OnWeekReportClickListener() {
            @Override
            public void onDayClick(int dayNum, int dayState, Calendar calendar) {
                Log.e("onDayClick", "dayNum:" + dayNum + " dayState:" + dayState);
                if (weekReportInterface != null)
                    weekReportInterface.DayClickInfo(yearCurrent, monthCurrent, dayNum, dayState, calendar);
                dismiss();
            }

            @Override
            public void onLeftMonthButtonClick(int year, int month) {
            }

            @Override
            public void onRightMonthButtonClick(int year, int month) {
            }

            @Override
            public void onWeekReportClick(int year, int month, int day, Calendar calendar) {
                Log.e("onWeekReportClick", "year:" + year + " month:" + month + " day:" + day);
                if (weekReportInterface != null)
                    weekReportInterface.WeekReportClickInfo(year, month, day, calendar);
                dismiss();
            }

            @Override
            public void onDateChange(int year, int month) {
                Log.e("onWeekReportClick", "year:" + year + " month:" + month);
                if (year != yearCurrent) {
                    weekReportInterface.onGetNewDailyReportInfo(year, month);
                }
                yearCurrent = year;
                monthCurrent = month;
            }
        });
        if (objectDate != null) {
            weekReportCalendar.setDateInfoObject(objectDate);
        }

    }

    public interface WeekReportInterface {
        /**
         * 获取跨年数据接口
         * 当年改变时通知即可
         */
        void onGetNewDailyReportInfo(int year, int month);

        /**
         * 周报点击接口
         */
        void WeekReportClickInfo(int year, int month, int day, Calendar calendar);

        /**
         * 日点击接口
         */
        void DayClickInfo(int year, int month, int dayNum, int dayState, Calendar calendar);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_close) {
            dismiss();
        }
    }

    public static interface OnSimpleClickListener {
        public void onClick(SimpleStyleDialog simpleStyleDialog);
    }

    private void setDialogType(int dialogType) {
        mDialogType = dialogType;
    }

    protected void onStart() {
        mDialogView.startAnimation(mInAnim);
    }

    public void dismiss() {
        mDialogView.startAnimation(mOutAnim);
    }

    public WeekReportInterface getWeekReportInterface() {
        return weekReportInterface;
    }

    public void setWeekReportInterface(WeekReportInterface weekReportInterface) {
        this.weekReportInterface = weekReportInterface;
    }

    public void setYearAndMonth(int year, int month) {
        this.yearCurrent = year;
        this.monthCurrent = month;
        if (weekReportCalendar != null)
            weekReportCalendar.setMonthAndYearToCalculate(year, month, false);

    }

    public void setYearDailyData(JSONObject object) {
        objectDate = object;
        if (weekReportCalendar != null) {
            weekReportCalendar.setDateInfoObject(object);
            weekReportCalendar.invalidate();
        }
    }

}
