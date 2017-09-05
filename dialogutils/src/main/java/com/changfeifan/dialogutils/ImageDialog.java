package com.changfeifan.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by changfeifan on 16/8/18.
 */
public class ImageDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private ImageView iv_close;
    private ImageView iv_content;
    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;

    private String imageUrl;
    private int imageId;

    public ImageDialog(Context context) {
        super(context, R.style.simple_dialog_style);

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
                        ImageDialog.super.dismiss();
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
        setContentView(R.layout.dialog_image);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);

        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_content = (ImageView) findViewById(R.id.iv_content);
        iv_close.setOnClickListener(this);
        iv_content.setOnClickListener(this);

        Picasso.with(getContext()).load(imageId).into(iv_content);
    }


    @Override
    public void onClick(View v) {
        dismiss();
//        switch (v.getId()) {
//            case R.id.iv_close:
//            case R.id.iv_content:
//                dismiss();
//                break;
//
//        }
    }

    protected void onStart() {
        mDialogView.startAnimation(mInAnim);
    }

    public void dismiss() {
        mDialogView.startAnimation(mOutAnim);
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
