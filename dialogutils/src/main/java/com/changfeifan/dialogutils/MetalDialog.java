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
public class MetalDialog extends Dialog implements View.OnClickListener {

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

    public static final int DIALOG_TYPE_SINGLE_BUTTON = 1;

    public MetalDialog(Context context) {
        this(context, DIALOG_TYPE_SINGLE_BUTTON);
    }


    public MetalDialog(Context context, int dialogType) {
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
                        MetalDialog.super.dismiss();
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
        setContentView(R.layout.dialog_medel);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_metal = (ImageView) findViewById(R.id.iv_metal);
        iv_close.setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_subtitle = (TextView) findViewById(R.id.tv_subtitle);
        tv_have_person_number = (TextView) findViewById(R.id.tv_have_person_number);

        tv_title .setText(titleStr);
        tv_have_person_number.setText(havePersonNum);
        tv_subtitle.setText(subTitleStr);
        iv_metal.setImageResource(metalId);
        // set dialog type based on number of button


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

    private void setDialogType(int dialogType) {
        mDialogType = dialogType;
    }

    protected void onStart() {
        mDialogView.startAnimation(mInAnim);
    }

    public void dismiss() {
        mDialogView.startAnimation(mOutAnim);
    }


    public MetalDialog setTitle(String titleStr) {
        this.titleStr = titleStr;
        return this;
    }

    public MetalDialog setSubTitle(String subTitleStr) {
        this.subTitleStr = subTitleStr;
        return this;
    }

    public MetalDialog setHavePerson(String havePersonNum) {
        this.havePersonNum = havePersonNum;
        return this;
    }

    public MetalDialog setMetalResourceId(int metalId) {
        this.metalId = metalId;
        return this;
    }

}
