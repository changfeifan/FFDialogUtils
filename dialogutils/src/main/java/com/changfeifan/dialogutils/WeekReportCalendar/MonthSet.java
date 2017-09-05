package com.changfeifan.dialogutils.WeekReportCalendar;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by changfeifan on 2017/5/16.
 */

public class MonthSet extends View implements DayClickListener {

    public MonthSet(Context context) {
        this(context, null);
    }

    public MonthSet(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public MonthSet(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
    public void onDayClick(View view, int day, int dayState) {

    }
}
