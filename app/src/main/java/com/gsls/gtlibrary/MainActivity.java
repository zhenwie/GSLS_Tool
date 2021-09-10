package com.gsls.gtlibrary;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.delete.DemoFragment;

@SuppressLint("NonConstantResourceId")
@GT.Annotations.GT_AnnotationActivity(R.layout.activity_main)
public class MainActivity extends GT.GT_Activity.AnnotationActivity {

    @GT.GT_Fragment.Build(setLayoutMain = R.id.fl_main, setClass = DemoFragment.class)
    private GT.GT_Fragment gt_fragment;

    private Activity activity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        GT.log("MainActivity");
        activity =  this;


    }

}
