package com.ares.dialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.ares.dialogdemo.dialog.CustomTimeDialogFragment;
import com.ares.dialogdemo.dialog.CustomTimeDialogFragment.CustomTimeDialogListener;

public class DelaySetActivity extends AppCompatActivity implements View.OnClickListener ,CustomTimeDialogListener{

    private TextView titleTv,stateTv, time15Tv, time30Tv, time60Tv, time90Tv, timeCustomTv, timeCustomTextTv;
    private FrameLayout timeCustomRl;
    private ImageView backIv;
    private Switch stateSw;
    private Button sureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_set);
        initView();
        initListener();
    }

    private void initView() {
        //标题栏
        backIv = (ImageView) findViewById(R.id.iv_base_title_back);
        titleTv = (TextView) findViewById(R.id.tv_base_title_text);
        titleTv.setText("延时设置");
        //内容
        stateSw = (Switch) findViewById(R.id.sw_activity_delay_set_state);
        stateTv = (TextView) findViewById(R.id.tv_activity_delay_set_state);
        time15Tv = (TextView) findViewById(R.id.tv_activity_delay_set_15);
        time30Tv = (TextView) findViewById(R.id.tv_activity_delay_set_30);
        time60Tv = (TextView) findViewById(R.id.tv_activity_delay_set_60);
        time90Tv = (TextView) findViewById(R.id.tv_activity_delay_set_90);
        timeCustomTv = (TextView) findViewById(R.id.tv_activity_delay_set_custom);
        timeCustomTextTv = (TextView) findViewById(R.id.tv_activity_delay_set_custom_text);
        timeCustomRl = (FrameLayout) findViewById(R.id.fl_activity_delay_set_custom);
        sureBtn = (Button) findViewById(R.id.btn_activity_delay_set_sure);

        //根据保存的设置延时的值初始化界面 暂未实现
        setCheckEnable(stateSw.isChecked());
    }

    private void initListener() {
        backIv.setOnClickListener(this);
        titleTv.setOnClickListener(this);
        time15Tv.setOnClickListener(this);
        time30Tv.setOnClickListener(this);
        time60Tv.setOnClickListener(this);
        time90Tv.setOnClickListener(this);
        timeCustomRl.setOnClickListener(this);
        sureBtn.setOnClickListener(this);

        stateSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckEnable(isChecked);
                release();
                if (isChecked){
                    time15Tv.setTextColor(getResources().getColor(R.color.colorSelected));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_base_title_back:
            case R.id.tv_base_title_text:
                finish();
                break;
            case R.id.tv_activity_delay_set_15:
                release();
                time15Tv.setTextColor(getResources().getColor(R.color.colorSelected));
                break;
            case R.id.tv_activity_delay_set_30:
                release();
                time30Tv.setTextColor(getResources().getColor(R.color.colorSelected));
                break;
            case R.id.tv_activity_delay_set_60:
                release();
                time60Tv.setTextColor(getResources().getColor(R.color.colorSelected));
                break;
            case R.id.tv_activity_delay_set_90:
                release();
                time90Tv.setTextColor(getResources().getColor(R.color.colorSelected));
                break;
            case R.id.fl_activity_delay_set_custom:
                CustomTimeDialogFragment customTimeDialogFragment = new CustomTimeDialogFragment();
                customTimeDialogFragment.show(getSupportFragmentManager(),"CustomTimeDialogFragment");
                break;
            case R.id.btn_activity_delay_set_sure:
                //确认操作 暂未实现
                break;
        }
    }

    public void release(){
        time15Tv.setTextColor(getResources().getColor(R.color.colorUnSelect));
        time30Tv.setTextColor(getResources().getColor(R.color.colorUnSelect));
        time60Tv.setTextColor(getResources().getColor(R.color.colorUnSelect));
        time90Tv.setTextColor(getResources().getColor(R.color.colorUnSelect));
        timeCustomTv.setTextColor(getResources().getColor(R.color.colorUnSelect));
        timeCustomTextTv.setText("");
    }

    public void setCheckEnable(boolean enable){
        time15Tv.setEnabled(enable);
        time30Tv.setEnabled(enable);
        time60Tv.setEnabled(enable);
        time90Tv.setEnabled(enable);
        timeCustomRl.setEnabled(enable);
    }

    @Override
    public void customTimeSure(int time) {
        release();
        timeCustomTextTv.setText(time+"分钟后");
        timeCustomTv.setTextColor(getResources().getColor(R.color.colorSelected));
        timeCustomTextTv.setTextColor(getResources().getColor(R.color.colorSelected));
    }
}
