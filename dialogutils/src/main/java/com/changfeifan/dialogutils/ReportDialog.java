package com.changfeifan.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by changfeifan on 16/8/18.
 */
public class ReportDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private ImageView iv_close;
    private TextView tv_info_one;
    private ImageView iv_update;

    private String infoStr = "";
    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;

    public ReportDialog(Context context) {
        this(context, "");
    }


    public ReportDialog(Context context, String infoStr) {
        super(context, R.style.simple_dialog_style);

        this.infoStr = infoStr;

        setCancelable(true);

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
                        ReportDialog.super.dismiss();
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
        setContentView(R.layout.dialog_report);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_update = (ImageView) findViewById(R.id.iv_update);
        iv_close.setOnClickListener(this);
        tv_info_one = (TextView) findViewById(R.id.tv_info_one);

        tv_info_one.setText(infoStr);


    }


    @Override
    public void onClick(View v) {
        dismiss();
//        switch (v.getId()) {
//            case R.id.iv_close:
//                dismiss();
//                break;
//        }
    }

    public static interface OnSimpleClickListener {
        public void onClick(SimpleStyleDialog simpleStyleDialog);
    }

    protected void onStart() {
        mDialogView.startAnimation(mInAnim);
    }

    public void dismiss() {
        mDialogView.startAnimation(mOutAnim);
    }

    public String getInfoStr() {
        return infoStr;
    }

    public void setInfoStr(String infoStr) {
        this.infoStr = infoStr;
    }
}
