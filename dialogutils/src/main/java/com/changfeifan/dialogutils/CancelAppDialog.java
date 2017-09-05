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
public class CancelAppDialog extends Dialog implements View.OnClickListener {

    private View mDialogView;
    private ImageView iv_close;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    int orderId = 0;

    OnCancelSuccessListener onCancelSuccessListener;

    // animation
    private AnimationSet mInAnim;
    private AnimationSet mOutAnim;

    public static final int DIALOG_TYPE_SINGLE_BUTTON = 1;

    public CancelAppDialog(Context context) {
        this(context, DIALOG_TYPE_SINGLE_BUTTON);
    }


    public CancelAppDialog(Context context, int dialogType) {
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
                        CancelAppDialog.super.dismiss();
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
        setContentView(R.layout.dialog_cancel_app);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }

    private void cancelApp(String string) {
//        new DeleteAppointment(orderId, string) {
//            @Override
//            protected void onPostExecute(JSONObject object) {
//                super.onPostExecute(object);
//                if (object.optInt("status") == 200) {
//                    Toast.makeText(getContext(), object.optString("msg"), Toast.LENGTH_SHORT).show();
//                    dismiss();
//                    if (onCancelSuccessListener != null)
//                        onCancelSuccessListener.onSuccess();
//                } else {
//                    Toast.makeText(getContext(), object.optString("msg"), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }.execute();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_close) {
            dismiss();
        } else if (id == R.id.btn1) {
            cancelApp(btn1.getText().toString());

        } else if (id == R.id.btn2) {
            cancelApp(btn2.getText().toString());

        } else if (id == R.id.btn3) {
            cancelApp(btn3.getText().toString());

        } else if (id == R.id.btn4) {
            cancelApp(btn4.getText().toString());

        }
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public interface OnCancelSuccessListener {
        void onSuccess();
    }

    public OnCancelSuccessListener getOnCancelSuccessListener() {
        return onCancelSuccessListener;
    }

    public void setOnCancelSuccessListener(OnCancelSuccessListener onCancelSuccessListener) {
        this.onCancelSuccessListener = onCancelSuccessListener;
    }
}
