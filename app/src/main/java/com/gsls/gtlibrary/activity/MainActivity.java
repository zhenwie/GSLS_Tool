package com.gsls.gtlibrary.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.util.GT;

@SuppressLint("NonConstantResourceId")
@GT.Annotations.GT_AnnotationActivity(R.layout.activity_main)
public class MainActivity extends GT.GT_Activity.AnnotationActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        GT.DarknessMagic.GT_FloatingWindow.GT_Floating.setType_screenType(-1);
        startFloatingWindow(GT.DarknessMagic.GT_FloatingWindow.GT_Floating.class);

    }


}
