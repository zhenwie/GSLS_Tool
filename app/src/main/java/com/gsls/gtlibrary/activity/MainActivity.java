package com.gsls.gtlibrary.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.service.FloatingLogService;
import com.gsls.gtlibrary.util.GT;

@GT.Annotations.GT_AnnotationActivity(R.layout.activity_main)
public class MainActivity extends GT.GT_Activity.AnnotationActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startFloatingWindow(FloatingLogService.class);
                Intent startIntent = new Intent(MainActivity.this, FloatingLogService.class);
                startService(startIntent);
            }
        });

        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GT.log("单击停止服务");
                Intent stopIntent = new Intent(MainActivity.this, FloatingLogService.class);
                stopService(stopIntent);
            }
        });

        if (!Settings.canDrawOverlays(this)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//8.0以上
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivityForResult(intent, 0);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0-8.0
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 0);
            } else {//4.4-6.0一下
                //无需处理了
            }
        }

    }


    @SuppressLint({"MissingSuperCall", "NewApi"})
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "授权成功2", Toast.LENGTH_SHORT).show();
                startFloatingWindow(FloatingLogService.class);
            }
        }
    }
}
