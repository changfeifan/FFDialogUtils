package com.changfeifan.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by changfeifan on 16/8/18.
 */
public class UploadProgressDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private ProgressBar progressBar;
    private TextView tvProgress;

    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;
    private OnCloseBtnListener onCloseBtnListener;
    private OnBreakListener onBreakListener;

    public static final int DIALOG_TYPE_SINGLE_BUTTON = 1;
    private int LastTime = 10;
    Handler handler = new Handler();

    public UploadProgressDialog(Context context) {
        this(context, DIALOG_TYPE_SINGLE_BUTTON);
    }


    public UploadProgressDialog(Context context, int progress) {
        super(context, R.style.simple_dialog_style);

        setCancelable(false);

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
                        UploadProgressDialog.super.dismiss();
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
        setContentView(R.layout.dialog_upload_progress);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        tvProgress = (TextView) findViewById(R.id.tv_progress);

//        findViewById(R.id.iv_close).setOnClickListener(this);
        handler.postDelayed(runnable, 1000);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.iv_close){
            onCloseBtnListener.onClick();
        }
//        switch (v.getId()) {
//            case R.id.iv_close:
//                onCloseBtnListener.onClick();
//                break;
//        }
    }

    public static interface OnSimpleClickListener {
        public void onClick(SimpleStyleDialog simpleStyleDialog);
    }

    public interface OnCloseBtnListener {
        void onClick();
    }

    public interface OnBreakListener {
        void onBreak();

        void onTryReconnect();
    }

    public OnBreakListener getOnBreakListener() {
        return onBreakListener;
    }

    public void setOnBreakListener(OnBreakListener onBreakListener) {
        this.onBreakListener = onBreakListener;
    }

    public OnCloseBtnListener getOnCloseBtnListener() {
        return onCloseBtnListener;
    }

    public void setOnCloseBtnListener(OnCloseBtnListener onCloseBtnListener) {
        this.onCloseBtnListener = onCloseBtnListener;
    }

    public void setProgress(int progress) {
        progressBar.setProgress(progress);
        tvProgress.setText(progress + "%");
        LastTime = 10;
        if (progress == 100) {
            LastTime = -1;
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e("Last", LastTime + "s");
            if (LastTime == 0) {
                Toast.makeText(getContext(), "同步失败，请重新同步", Toast.LENGTH_SHORT).show();
                if (onBreakListener != null) {
                    onBreakListener.onBreak();
                }
                dismiss();
            } else if (LastTime == 5) {
                if (onBreakListener != null) {
                    onBreakListener.onTryReconnect();
                }
            } else if (LastTime > 0) {
                LastTime--;
                handler.postDelayed(this, 1000);
            } else {
                handler.removeCallbacks(this);
                LastTime = 10;
            }
        }
    };

    protected void onStart() {
        mDialogView.startAnimation(mInAnim);
    }

    public void dismiss() {
        mDialogView.startAnimation(mOutAnim);
        handler.removeCallbacks(runnable);

    }


}
