package com.changfeifan.dialogutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by changfeifan on 16/8/18.
 */
public class RankDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private ImageView iv_close;
    private Button btn_score;
    private Button btn_out;
    private Button btn_light;
    private Button btn_confirm;


    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;
    public static final int EYE_SCORE = 121;
    public static final int OUTDOOR = 122;
    public static final int LIGHT_INTAKE = 123;

    public int type = EYE_SCORE;
    public int lastType = EYE_SCORE;
    public TypeListener typeListener;

    public RankDialog(Context context) {
        this(context, EYE_SCORE);
    }


    public RankDialog(Context context, int type) {
        super(context, R.style.simple_dialog_style);

        setCancelable(true);

        this.type = type;
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
                        RankDialog.super.dismiss();
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
        setContentView(R.layout.dialog_rank);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);

        btn_score = (Button) findViewById(R.id.btn_score);
        btn_out = (Button) findViewById(R.id.btn_out);
        btn_light = (Button) findViewById(R.id.btn_light);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);

        btn_score.setOnClickListener(this);
        btn_out.setOnClickListener(this);
        btn_light.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);

        setView(getContext());
    }


    public void setView(Context context) {
        btn_score.setBackground(context.getResources().getDrawable(R.drawable.sharp_gray_deep_stroke));
        btn_out.setBackground(context.getResources().getDrawable(R.drawable.sharp_gray_deep_stroke));
        btn_light.setBackground(context.getResources().getDrawable(R.drawable.sharp_gray_deep_stroke));
        btn_score.setTextColor(context.getResources().getColor(R.color.text_gray));
        btn_out.setTextColor(context.getResources().getColor(R.color.text_gray));
        btn_light.setTextColor(context.getResources().getColor(R.color.text_gray));
        switch (type) {
            case EYE_SCORE:
                btn_score.setBackground(context.getResources().getDrawable(R.drawable.sharp_blue_stroke_rank));
                btn_score.setTextColor(context.getResources().getColor(R.color.blue_week_report_text));
                break;
            case OUTDOOR:
                btn_out.setBackground(context.getResources().getDrawable(R.drawable.sharp_blue_stroke_rank));
                btn_out.setTextColor(context.getResources().getColor(R.color.blue_week_report_text));
                break;
            case LIGHT_INTAKE:
                btn_light.setBackground(context.getResources().getDrawable(R.drawable.sharp_blue_stroke_rank));
                btn_light.setTextColor(context.getResources().getColor(R.color.blue_week_report_text));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_close) {
            dismiss();
        } else if (id == R.id.btn_score) {
            type = EYE_SCORE;
            setView(getContext());
        } else if (id == R.id.btn_out) {
            type = OUTDOOR;
            setView(getContext());
        } else if (id == R.id.btn_light) {
            type = LIGHT_INTAKE;
            setView(getContext());
        } else if (id == R.id.btn_confirm) {
            typeListener.type(type);
            dismiss();
        }
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TypeListener getTypeListener() {
        return typeListener;
    }

    public void setTypeListener(TypeListener typeListener) {
        this.typeListener = typeListener;
    }

    public interface TypeListener {

        void type(int type);
    }
}
