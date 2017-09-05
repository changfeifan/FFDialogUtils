package com.changfeifan.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by changfeifan on 16/8/18.
 */
public class GlassDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private ImageView iv_close;
    private ImageView iv1;
    private ImageView iv2;
    private TextView tv_title;
    private TextView tv_content;
    private Button btn_next;
    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;

    private SycnListener sycnListener;

    public GlassDialog(Context context) {
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
                        GlassDialog.super.dismiss();
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
        setContentView(R.layout.dialog_glass);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);

        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        iv_close = findViewById(R.id.iv_close);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        btn_next = findViewById(R.id.btn_next);
        iv_close.setOnClickListener(this);
        btn_next.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_close || id == R.id.iv_content) {
            dismiss();
        } else if (id == R.id.btn_next) {
            if (iv1.getVisibility() == View.VISIBLE) {
                iv1.setVisibility(View.GONE);
                tv_content.setText("长按2秒打开智能腿蓝牙开关，\n打开后闪烁绿光3次。");
                tv_title.setText("打开镜腿开关");
                iv2.setVisibility(View.VISIBLE);
            } else {
                sycnListener.sycn();
                dismiss();
            }
        }

    }

    public SycnListener getSycnListener() {
        return sycnListener;
    }

    public void setSycnListener(SycnListener sycnListener) {
        this.sycnListener = sycnListener;
    }

    protected void onStart() {
        mDialogView.startAnimation(mInAnim);
    }

    public void dismiss() {
        mDialogView.startAnimation(mOutAnim);
    }

    public interface SycnListener {
        //同步
        void sycn();
    }

}
