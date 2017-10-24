package com.ares.dialogdemo.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class CustomTimeDialogFragment extends DialogFragment implements View.OnClickListener{

    private Button cancelBtn,sureBtn;
    private EditText inputEd;
    private CustomTimeDialogListener mListener;

    public interface CustomTimeDialogListener{
        void customTimeSure(int time);
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
        View view = inflater.inflate(R.layout.dialog_custom_time,container,false);
        initView(view);
        initListener();
        return view;
    }

    private void initView(View view) {
        inputEd = view.findViewById(R.id.et_dialog_custom_time_input);
        cancelBtn = view.findViewById(R.id.btn_dialog_custom_time_cancel);
        sureBtn = view.findViewById(R.id.btn_dialog_custom_time_sure);
        try {
            mListener = (CustomTimeDialogListener) getActivity();
        }catch (ClassCastException e){
            Toast.makeText(getActivity(),"请先实现CustomTimeDialogListener监听事件!",Toast.LENGTH_SHORT).show();
        }
    }

    private void initListener() {
        cancelBtn.setOnClickListener(this);
        sureBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dialog_custom_time_cancel:
                dismiss();
                break;
            case R.id.btn_dialog_custom_time_sure:
                if (mListener == null) break;
                String timeStr = inputEd.getText().toString().trim();
                if (TextUtils.isEmpty(timeStr)) timeStr = "0";
                int time = Integer.valueOf(timeStr);
                mListener.customTimeSure(time);
                dismiss();
                break;
        }
    }
}
