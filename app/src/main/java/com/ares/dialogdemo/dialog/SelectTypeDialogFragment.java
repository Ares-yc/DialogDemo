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

public class SelectTypeDialogFragment extends DialogFragment implements View.OnClickListener{

    private RelativeLayout everyDayRl,workingDayRl,weekendRl,customRl;
    private CheckBox everyDayCb,workingDayCb,weekendCb,customCb;
    private String result = "none";
    private SelectTypeDialogListener mListener;

    public interface SelectTypeDialogListener{
        void selectTypeFinish(String type);
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
        View view = inflater.inflate(R.layout.dialog_select_type,container,false);
        initView(view);
        initListener();
        return view;
    }

    private void initView(View view) {
        everyDayRl = view.findViewById(R.id.rl_dialog_select_type_everyday);
        workingDayRl = view.findViewById(R.id.rl_dialog_select_type_working_day);
        weekendRl = view.findViewById(R.id.rl_dialog_select_type_weekend);
        customRl = view.findViewById(R.id.rl_dialog_select_type_custom);

        everyDayCb = view.findViewById(R.id.cb_dialog_select_type_everyday);
        workingDayCb = view.findViewById(R.id.cb_dialog_select_type_working_day);
        weekendCb = view.findViewById(R.id.cb_dialog_select_type_weekend);
        customCb = view.findViewById(R.id.cb_dialog_select_type_custom);

        try {
            mListener = (SelectTypeDialogListener) getActivity();
        }catch (ClassCastException e){
            Toast.makeText(getActivity(),"请先实现SelectTypeDialogListener监听事件!",Toast.LENGTH_SHORT).show();
        }
    }

    private void initListener() {
        everyDayRl.setOnClickListener(this);
        workingDayRl.setOnClickListener(this);
        weekendRl.setOnClickListener(this);
        customRl.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        releaseCheckState();
        everyDayCb.setChecked(true);
        result = "Everyday";
    }

    @Override
    public void onClick(View v) {
        if (mListener == null){
            Toast.makeText(getActivity(),"SelectTypeDialogListener监听对象为空,请确认!",Toast.LENGTH_SHORT).show();
            return;
        }
        switch (v.getId()){
            case R.id.rl_dialog_select_type_everyday:
                releaseCheckState();
                everyDayCb.setChecked(true);
                result = "Everyday";
                break;
            case R.id.rl_dialog_select_type_working_day:
                releaseCheckState();
                workingDayCb.setChecked(true);
                result = "WorkingDay";
                break;
            case R.id.rl_dialog_select_type_weekend:
                releaseCheckState();
                weekendCb.setChecked(true);
                result = "Weekend";
                break;
            case R.id.rl_dialog_select_type_custom:
                releaseCheckState();
                customCb.setChecked(true);
                result = "Custom";
                break;
        }
        mListener.selectTypeFinish(result);
        dismiss();
    }

    public void releaseCheckState(){
        result = "none";
        everyDayCb.setChecked(false);
        workingDayCb.setChecked(false);
        weekendCb.setChecked(false);
        customCb.setChecked(false);
    }
}
