package com.ares.dialogdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ares.dialogdemo.dialog.CustomTimeDialogFragment;
import com.ares.dialogdemo.dialog.CustomTimeDialogFragment.CustomTimeDialogListener;
import com.ares.dialogdemo.dialog.SelectTypeDialogFragment;
import com.ares.dialogdemo.dialog.SelectWeekDialogFragment;
import com.ares.dialogdemo.dialog.SelectWeekDialogFragment.SelectWeekDialogListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CustomTimeDialogListener, SelectWeekDialogListener{

    private Button customTimeBtn,selectWeekBtn,selectTypeBtn,delaySetBtn,timingSetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        customTimeBtn = (Button) findViewById(R.id.btn_activity_main_custom_time);
        selectWeekBtn = (Button) findViewById(R.id.btn_activity_main_select_week);
        selectTypeBtn = (Button) findViewById(R.id.btn_activity_main_select_type);
        delaySetBtn   = (Button) findViewById(R.id.btn_activity_main_delay_set);
        timingSetBtn   = (Button) findViewById(R.id.btn_activity_main_timing_set);
    }

    private void initListener() {
        customTimeBtn.setOnClickListener(this);
        selectWeekBtn.setOnClickListener(this);
        selectTypeBtn.setOnClickListener(this);
        delaySetBtn.setOnClickListener(this);
        timingSetBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_activity_main_custom_time://自定义时间弹框
                CustomTimeDialogFragment customTimeDialogFragment = new CustomTimeDialogFragment();
                customTimeDialogFragment.show(getSupportFragmentManager(),"CustomTimeDialogFragment");
                break;
            case R.id.btn_activity_main_select_week://选择重复日期弹框
                SelectWeekDialogFragment selectWeekDialogFragment = new SelectWeekDialogFragment();
                selectWeekDialogFragment.show(getSupportFragmentManager(),"SelectWeekDialogFragment");
                break;
            case R.id.btn_activity_main_select_type://选择重复类型弹框
                SelectTypeDialogFragment selectTypeDialogFragment = new SelectTypeDialogFragment();
                selectTypeDialogFragment.show(getSupportFragmentManager(),"SelectTypeDialogFragment");
                break;
            case R.id.btn_activity_main_delay_set://跳转至延时设置界面
                intent = new Intent(MainActivity.this,DelaySetActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_activity_main_timing_set:
                intent = new Intent(MainActivity.this,TimingSetActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void customTimeSure(int time) {
        Log.e("*****","time = "+time);
    }

    @Override
    public void selectWeekFinish(String weekDay) {
        Log.e("*****","weekDay = " + weekDay);
    }
}
