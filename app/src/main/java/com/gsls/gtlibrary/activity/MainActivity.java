package com.gsls.gtlibrary.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.util.GT;

@SuppressLint("NonConstantResourceId")
@GT.Annotations.GT_AnnotationActivity(R.layout.activity_main)
public class MainActivity extends GT.GT_Activity.AnnotationActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

    }


    @SuppressLint({"MissingSuperCall", "NewApi"})
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }
}
