package com.ares.dialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ares.dialogdemo.dialog.SelectTypeDialogFragment;
import com.ares.dialogdemo.dialog.SelectTypeDialogFragment.SelectTypeDialogListener;

public class TimingSetActivity extends AppCompatActivity implements View.OnClickListener,SelectTypeDialogListener {

    private TextView titleTv,repeatTypeTv;
    private FrameLayout repeatTypeFl;
    private ImageView backIv;
    private Switch stateSw;
    private TimePicker openTimeTp,closeTimeTp;
    private Button sureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing_set);
        initView();
        initListener();
    }

    private void initView() {
        //标题栏
        backIv = (ImageView) findViewById(R.id.iv_base_title_back);
        titleTv = (TextView) findViewById(R.id.tv_base_title_text);
        titleTv.setText("定时设置");

        //内容控件初始化
        stateSw = (Switch) findViewById(R.id.sw_activity_timing_set_state);
        repeatTypeFl = (FrameLayout) findViewById(R.id.fl_activity_timing_set_repeat);
        repeatTypeTv = (TextView) findViewById(R.id.tv_activity_timing_set_repeat_type);
        sureBtn = (Button) findViewById(R.id.btn_activity_timing_set_sure);

        //时间选择器初始化
        openTimeTp = (TimePicker) findViewById(R.id.tp_activity_timing_set_open_time);
        closeTimeTp = (TimePicker) findViewById(R.id.tp_activity_timing_set_close_time);

        openTimeTp.setIs24HourView(true);
        closeTimeTp.setIs24HourView(true);
        openTimeTp.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
        closeTimeTp.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);

        openTimeTp.setCurrentHour(0);
        openTimeTp.setCurrentMinute(0);

        closeTimeTp.setCurrentHour(0);
        closeTimeTp.setCurrentMinute(0);
    }

    private void initListener() {
        backIv.setOnClickListener(this);
        titleTv.setOnClickListener(this);
        repeatTypeFl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_base_title_back:
            case R.id.tv_base_title_text:
                finish();
                break;
            case R.id.fl_activity_timing_set_repeat:
                SelectTypeDialogFragment selectTypeDialogFragment = new SelectTypeDialogFragment();
                selectTypeDialogFragment.show(getSupportFragmentManager(),"SelectTypeDialogFragment");
                break;
            case R.id.btn_activity_timing_set_sure:
                //确认操作 暂未实现
                break;
        }
    }

    @Override
    public void selectTypeFinish(String type){
        switch (type){
            case "none":
                repeatTypeTv.setText("");
                break;
            case "Everyday":
                repeatTypeTv.setText("每天");
                break;
            case "WorkingDay":
                repeatTypeTv.setText("工作日");
                break;
            case "Weekend":
                repeatTypeTv.setText("周末");
                break;
            case "Custom":
                repeatTypeTv.setText("自定义");
                break;
        }
    }
}
