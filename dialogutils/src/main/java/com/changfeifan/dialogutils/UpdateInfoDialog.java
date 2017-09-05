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
public class UpdateInfoDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private ImageView iv_close;
    private int mDialogType;
    private int mSeries1Index;
    private TextView tv_info_one;
    private TextView tv_info_two;
    private TextView tv_have_person_number;
    private ImageView iv_update;

    private String titleStr = "";
    private String subTitleStr = "";
    //    private String havePersonNum = "";
    private int metalId = 0;
    private boolean isSuccess = false;
    private boolean isNormal = false;
    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;

    public static final int DIALOG_TYPE_SINGLE_BUTTON = 1;

    public UpdateInfoDialog(Context context) {
        this(context, DIALOG_TYPE_SINGLE_BUTTON);
    }


    public UpdateInfoDialog(Context context, int dialogType) {
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
                        UpdateInfoDialog.super.dismiss();
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
        setContentView(R.layout.dialog_update_info);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_update = (ImageView) findViewById(R.id.iv_update);
        iv_close.setOnClickListener(this);
        tv_info_one = (TextView) findViewById(R.id.tv_info_one);
        tv_info_two = (TextView) findViewById(R.id.tv_info_two);
//        tv_have_person_number = (TextView) findViewById(R.id.tv_have_person_number);

//        tv_info_one.setText(titleStr);
//        tv_have_person_number.setText(havePersonNum);
//        tv_subtitle.setText(subTitleStr);
        iv_update.setImageResource(metalId);
        // set dialog type based on number of button
        if (isSuccess) {
//            if (isNormal()) {
            tv_info_one.setText(R.string.update_success_one);
            tv_info_two.setText(R.string.update_success_two);
//            } else {
//                iv_update.setImageResource(R.drawable.ic_update_unnormal);
//                tv_info_one.setText("当前数据同步成功（存在异常数据）");
//                tv_info_two.setText("");
//            }

        } else {
            tv_info_one.setText(R.string.update_failed_one);
            tv_info_two.setText(R.string.update_failed_two);
        }


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


    public UpdateInfoDialog setTitle(String titleStr) {
        this.titleStr = titleStr;
        return this;
    }

    public UpdateInfoDialog setSubTitle(String subTitleStr) {
        this.subTitleStr = subTitleStr;
        return this;
    }

    public UpdateInfoDialog isSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }

    public boolean isNormal() {
        return isNormal;
    }

    public UpdateInfoDialog setNormal(boolean normal) {
        isNormal = normal;
        return this;
    }

    public UpdateInfoDialog setUpdateResourceId(int metalId) {
        this.metalId = metalId;
        return this;
    }

}
