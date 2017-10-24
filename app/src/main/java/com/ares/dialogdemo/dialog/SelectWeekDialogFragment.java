package com.ares.dialogdemo.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ares.dialogdemo.R;

/**
 * ====================================
 * 作    者：Ares(颜崔)
 * 地    址：https://github.com/Ares-yc
 * 描    述：
 * 版    本：1.0.0
 * 创建时间：2017/10/24.
 * 修改时间：2017/10/24.
 * ====================================
 */

public class SelectWeekDialogFragment extends DialogFragment implements View.OnClickListener{

    private RelativeLayout sundayRl,mondayRl,tuesdayRl,wednesdayRl,thursdayRl,fridayRl,saturdayRl;
    private CheckBox sundayCb,mondayCb,tuesdayCb,wednesdayCb,thursdayCb,fridayCb,saturdayCb;
    private Button finishBtn;
    private String result = "none";
    private SelectWeekDialogListener mListener;

    public interface SelectWeekDialogListener{
        void selectWeekFinish(String weekDay);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置弹出框宽屏显示
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (getDialog().getWindow() != null){
            getDialog().getWindow().setLayout(dm.widthPixels,getDialog().getWindow().getAttributes().height);
        }

        WindowManager wm = getActivity().getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();

        //设置弹出框从底部弹出
        WindowManager.LayoutParams wlp = getDialog().getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = width - 80;
        getDialog().getWindow().setAttributes(wlp);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getDialog().getWindow() != null){
            //设置背景为半透明
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置弹出框进场、出场动画
            getDialog().getWindow().setWindowAnimations(R.style.BottomDialogAnimation);
        }
        View view = inflater.inflate(R.layout.dialog_select_week,container,false);
        initView(view);
        initListener();
        return view;
    }

    private void initView(View view) {
        sundayRl = view.findViewById(R.id.rl_dialog_select_week_sunday);
        mondayRl = view.findViewById(R.id.rl_dialog_select_week_monday);
        tuesdayRl = view.findViewById(R.id.rl_dialog_select_week_tuesday);
        wednesdayRl = view.findViewById(R.id.rl_dialog_select_week_wednesday);
        thursdayRl = view.findViewById(R.id.rl_dialog_select_week_thursday);
        fridayRl = view.findViewById(R.id.rl_dialog_select_week_friday);
        saturdayRl = view.findViewById(R.id.rl_dialog_select_week_saturday);
        
        sundayCb = view.findViewById(R.id.cb_dialog_select_week_sunday);
        mondayCb = view.findViewById(R.id.cb_dialog_select_week_monday);
        tuesdayCb = view.findViewById(R.id.cb_dialog_select_week_tuesday);
        wednesdayCb = view.findViewById(R.id.cb_dialog_select_week_wednesday);
        thursdayCb = view.findViewById(R.id.cb_dialog_select_week_thursday);
        fridayCb = view.findViewById(R.id.cb_dialog_select_week_friday);
        saturdayCb = view.findViewById(R.id.cb_dialog_select_week_saturday);

        finishBtn = view.findViewById(R.id.btn_dialog_select_week_finish);
        try {
            mListener = (SelectWeekDialogListener) getActivity();
        }catch (ClassCastException e){
            Toast.makeText(getActivity(),"请先实现SelectWeekDialogListener监听事件!",Toast.LENGTH_SHORT).show();
        }

    }

    private void initListener() {
        sundayRl.setOnClickListener(this);
        mondayRl.setOnClickListener(this);
        tuesdayRl.setOnClickListener(this);
        wednesdayRl.setOnClickListener(this);
        thursdayRl.setOnClickListener(this);
        fridayRl.setOnClickListener(this);
        saturdayRl.setOnClickListener(this);
        finishBtn.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        releaseCheckState();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_dialog_select_week_sunday:
                releaseCheckState();
                sundayCb.setChecked(true);
                result = "Sunday";
                break;
            case R.id.rl_dialog_select_week_monday:
                releaseCheckState();
                mondayCb.setChecked(true);
                result = "Monday";
                break;
            case R.id.rl_dialog_select_week_tuesday:
                releaseCheckState();
                tuesdayCb.setChecked(true);
                result = "Tuesday";
                break;
            case R.id.rl_dialog_select_week_wednesday:
                releaseCheckState();
                wednesdayCb.setChecked(true);
                result = "Wednesday";
                break;
            case R.id.rl_dialog_select_week_thursday:
                releaseCheckState();
                thursdayCb.setChecked(true);
                result = "Thursday";
                break;
            case R.id.rl_dialog_select_week_friday:
                releaseCheckState();
                fridayCb.setChecked(true);
                result = "Friday";
                break;
            case R.id.rl_dialog_select_week_saturday:
                releaseCheckState();
                saturdayCb.setChecked(true);
                result = "Saturday";
                break;
            case R.id.btn_dialog_select_week_finish:
                if (mListener == null) break;
                if (result.equals("none")){
                    Toast.makeText(getContext(),"请选择重复日期!",Toast.LENGTH_SHORT).show();
                    break;
                }
                mListener.selectWeekFinish(result);
                dismiss();
                break;
        }
    }

    public void releaseCheckState(){
        result = "none";
        sundayCb.setChecked(false);
        mondayCb.setChecked(false);
        tuesdayCb.setChecked(false);
        wednesdayCb.setChecked(false);
        thursdayCb.setChecked(false);
        fridayCb.setChecked(false);
        saturdayCb.setChecked(false);
    }
}
