package com.changfeifan.ffdialogutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.changfeifan.dialogutils.DateDialog;
import com.changfeifan.dialogutils.WeekDateDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_calendar).setOnClickListener(this);
        findViewById(R.id.btn_calendar_2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_calendar:
                DateDialog dateDialog = new DateDialog(this);
                dateDialog.show();
                break;
            case R.id.btn_calendar_2:
                WeekDateDialog weekDateDialog = new WeekDateDialog(this);
                weekDateDialog.show();
                break;
            default:
                break;
        }
    }
}
