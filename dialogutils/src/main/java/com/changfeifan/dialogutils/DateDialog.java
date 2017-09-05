package com.changfeifan.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by changfeifan on 16/8/18.
 */
public class DateDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private MaterialCalendarView calendarView;
    private int mDialogType;
    private int mSeries1Index;
    private long dateLong = 0;
    private ImageView iv_close;
    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;

    public static final int DIALOG_TYPE_SINGLE_BUTTON = 1;

    public DateDialog(Context context) {
        this(context, DIALOG_TYPE_SINGLE_BUTTON);
    }


    public DateDialog(Context context, int dialogType) {
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
                        DateDialog.super.dismiss();
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
        setContentView(R.layout.dialog_date);
        Calendar calendar = Calendar.getInstance();
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendarView.setSelectionColor(getContext().getResources().getColor(R.color.main_bar_blue));
        calendarView.setShowOtherDates(0);
        calendarView.setDateSelected(new Date(dateLong), true);
        calendarView.state().edit().setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from((calendar.get(Calendar.YEAR) - 1), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)))
                .setMaximumDate(CalendarDay.from(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)))
                .setCalendarDisplayMode(CalendarMode.MONTHS).commit();

//        calendarView.setDate();
        iv_close = (ImageView) findViewById(R.id.iv_close);
//        iv_metal = (ImageView) findViewById(R.id.iv_metal);
        iv_close.setOnClickListener(this);
//        tv_title = (TextView) findViewById(R.id.tv_title);
//        tv_subtitle = (TextView) findViewById(R.id.tv_subtitle);
//        tv_have_person_number = (TextView) findViewById(R.id.tv_have_person_number);
//
//        tv_title .setText(titleStr);
//        tv_have_person_number.setText(havePersonNum);
//        tv_subtitle.setText(subTitleStr);
//        iv_metal.setImageResource(metalId);
        // set dialog type based on number of button


    }


    @Override
    public void onClick(View v) {
        dismiss();

    }

    public MaterialCalendarView getCalendarView() {
        return calendarView;
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

    public DateDialog setDate(long dateLong) {
        this.dateLong = dateLong;
        return this;
    }
}
