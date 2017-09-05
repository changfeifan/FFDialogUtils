package com.changfeifan.dialogutils.WeekReportCalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.changfeifan.dialogutils.R;

/**
 * Created by changfeifan on 2017/5/16.
 * this is the day Function.
 * 设置Day控件
 */

public class DaySet extends View {

    DayClickListener dayClickListener;

    public final static int DAY_STATE_HAVE_DATA = 1001;
    public final static int DAY_STATE_NO_DATA = 1002;
    public final static int DAY_STATE_NOT_THIS_MONTH_HAVE_DATA = 1003;
    public final static int DAY_STATE_NOT_THIS_MONTH_NO_DATA = 1004;

    int dayNum = 0;
    int dayState = DAY_STATE_NO_DATA;
    String dayText = "";

    TextPaint dayNumPaint;
    Rect dayRect;


    public DaySet(Context context) {
        this(context, null);
    }

    public DaySet(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DaySet(final Context context, @Nullable AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.DaySet, defStyleAttr, 0);

        dayNumPaint = new TextPaint();
        dayNumPaint.setTextAlign(Paint.Align.CENTER);

        if (a != null) {
//            Log.e("dayState", a.getInt(R.styleable.DaySet_DaySetState, 1001) + "");

            dayNum = a.getInt(R.styleable.DaySet_DaySetNum, 0);
            dayState = a.getInt(R.styleable.DaySet_DaySetState, 1001);
            dayText = a.getString(R.styleable.DaySet_DaySetText);
            dayText = dayNum + "";

            dayNumPaint.setTextSize(a.getDimension(R.styleable.DaySet_DaySetTextSize, 30));
            dayNumPaint.setColor(a.getColor(R.styleable.DaySet_DaySetTextColor, Color.BLACK));
//            dayNumPaint.setTypeface(Typeface.createFromAsset(AkesoKidsApplication.getApp().getAssets(), "OpenSans-Regular.ttf"));
            dayNumPaint.setAntiAlias(true);//抗锯齿
        }

        //当前控件click事件
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dayClickListener != null) {
                    dayClickListener.onDayClick(view, getDayNum(), getDayState());
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sideLong = Math.min(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(sideLong, sideLong);//重新绘制成正方形的控件按照最小边进行绘制。
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        RectF rectBitmap = new RectF(0, 0, getWidth(), getHeight());

        dayRect = new Rect();
        Paint.FontMetricsInt fontMetrics = dayNumPaint.getFontMetricsInt();
        dayNumPaint.getTextBounds(dayText, 0, dayText.length(), dayRect);

        int baseline = (getBottom() + getTop() - fontMetrics.bottom - fontMetrics.top) / 2;
        switch (dayState) {
            case DAY_STATE_HAVE_DATA:
                canvas.drawBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_week_have_data)).getBitmap()
                        , null, rectBitmap, paint);
                canvas.drawText(dayNum + "", getWidth() / 2, baseline, dayNumPaint);
                break;
            case DAY_STATE_NO_DATA:
                canvas.drawBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_week_no_data)).getBitmap()
                        , null, rectBitmap, paint);
                canvas.drawText(dayNum + "", getWidth() / 2, baseline, dayNumPaint);
                break;
            case DAY_STATE_NOT_THIS_MONTH_HAVE_DATA:
                canvas.drawBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_week_have_data)).getBitmap()
                        , null, rectBitmap, paint);
                canvas.drawText(dayNum + "", getWidth() / 2, baseline, dayNumPaint);
                break;
            case DAY_STATE_NOT_THIS_MONTH_NO_DATA:
                // 转载请注明出处：http://blog.csdn.net/hursing
                canvas.drawText(dayNum + "", getWidth() / 2, baseline, dayNumPaint);
                break;
            default:
                canvas.drawText(dayNum + "", getWidth() / 2, baseline, dayNumPaint);
                break;
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    public DayClickListener getDayClickListener() {
        return dayClickListener;
    }

    public void setDayClickListener(DayClickListener dayClickListener) {
        this.dayClickListener = dayClickListener;
    }

    public int getDayState() {
        return dayState;
    }

    public void setDayState(int dayState) {
        this.dayState = dayState;
        invalidate();
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
        invalidate();
    }


}
