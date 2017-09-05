//package com.changfeifan.dialogutils;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.TypedValue;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationSet;
//import android.view.animation.Interpolator;
//import android.view.animation.OvershootInterpolator;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.hookedonplay.decoviewlib.DecoView;
//import com.hookedonplay.decoviewlib.charts.SeriesItem;
//import com.hookedonplay.decoviewlib.events.DecoEvent;
//
///**
// * Created by changfeifan on 16/8/18.
// */
//public class MainDialog extends Dialog implements View.OnClickListener {
//
//    private View mDialogView;
//    private ImageView iv_close;
//    private int mDialogType;
//    private int mSeries1Index;
//    private TextView tv_center_content;
//    private TextView tv_sub_num;
//
//    private String centerText = "0";
//    private String cententText = "";
//
//    // animation
//    private AnimationSet mInAnim;
//    private AnimationSet mOutAnim;
//
//    public static final int DIALOG_TYPE_SINGLE_BUTTON = 1;
//
//    public MainDialog(Context context) {
//        this(context, DIALOG_TYPE_SINGLE_BUTTON);
//    }
//
//
//    public MainDialog(Context context, int dialogType) {
//        super(context, R.style.simple_dialog_style);
//
//        setCancelable(true);
//        setDialogType(dialogType);
//
//        mInAnim = (AnimationSet) AnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
//        mOutAnim = (AnimationSet) AnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
//        mOutAnim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                mDialogView.setVisibility(View.GONE);
//                mDialogView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        MainDialog.super.dismiss();
//                    }
//                });
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dialog_main_fragment);
//
//        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
//        iv_close = (ImageView) findViewById(R.id.iv_close);
//        iv_close.setOnClickListener(this);
//        tv_center_content = (TextView) findViewById(R.id.tv_center_content);
//        tv_center_content.setText(centerText);
//        tv_sub_num = (TextView) findViewById(R.id.tv_sub_num);
//        tv_sub_num.setText(centerText);
//        // set dialog type based on number of button
//        createTracks(R.id.dynamicArcView1, new OvershootInterpolator(),
//                getContext().getResources().getColor(R.color.main_bar_blue));
//
//        final DecoView decoView = (DecoView) mDialogView.findViewById(R.id.dynamicArcView1);
//        decoView.executeReset();
//        decoView.addEvent(new DecoEvent.Builder(360 * Integer.valueOf(centerText) / 7).setIndex(mSeries1Index).setDelay(300).build());
//
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_close:
//                dismiss();
//                break;
//        }
//    }
//
//    public static interface OnSimpleClickListener {
//        public void onClick(SimpleStyleDialog simpleStyleDialog);
//    }
//
//    private void setDialogType(int dialogType) {
//        mDialogType = dialogType;
//    }
//
//    protected void onStart() {
//        mDialogView.startAnimation(mInAnim);
//    }
//
//    public void dismiss() {
//        mDialogView.startAnimation(mOutAnim);
//    }
//
//    private void createTracks(int arcViewId, Interpolator interpolator, int color) {
//        final View view = mDialogView;
//        if (view == null) {
//            return;
//        }
//
//        final DecoView decoView = (DecoView) view.findViewById(arcViewId);
//        if (decoView == null) {
//            return;
//        }
//
//        decoView.deleteAll();
//        decoView.configureAngles(360, 0);
//
//        final float mSeriesMax = 50f;
//        SeriesItem arcBackTrack = new SeriesItem.Builder(Color.argb(255, 228, 228, 228))
//                .setRange(0, 360f, 360f)
//                .setLineWidth(getDimension(2f))
//                .build();
//
//        decoView.addSeries(arcBackTrack);
//
//        SeriesItem seriesItem1 = new SeriesItem.Builder(color)
//                .setRange(0, 360f, 0)
//                .setInterpolator(interpolator)
//                .setLineWidth(getDimension(12f))
//                .setSpinDuration(5000)
//                .build();
////        .setSpinClockwise(false) 这句话为设置逆时针方向。
//
//        mSeries1Index = decoView.addSeries(seriesItem1);
//
//    }
//
//
//    protected float getDimension(float base) {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, base, getContext().getResources().getDisplayMetrics());
//    }
//
//    protected DecoView getDecoView() {
//        if (mDialogView == null) {
//            return null;
//        }
//        try {
//            return (DecoView) mDialogView.findViewById(R.id.dynamicArcView1);
//        } catch (NullPointerException npe) {
//            //Log.e("DocView", "Unable to resolve view " + npe.getMessage());
//        }
//        return null;
//    }
//
//    public MainDialog setCenterText(String centerText) {
//        this.centerText = centerText;
//        //Log.e("centerText", centerText);
//        return this;
//    }
//
//    public MainDialog setContentText(String cententText) {
//        this.cententText = cententText;
//        return this;
//    }
//
//}
