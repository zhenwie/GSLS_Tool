package com.gsls.gtlibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.util.GT;

@GT.Annotations.GT_AnnotationActivity(R.layout.activity_android)
public class AndroidActivity extends GT.GT_Activity.AnnotationActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        build(this);


    }

    @Override
    protected void updateUi(Context context, Intent intent) {
        super.updateUi(context, intent);


    }
}
