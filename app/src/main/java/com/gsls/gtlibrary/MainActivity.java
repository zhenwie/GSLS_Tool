package com.gsls.gtlibrary;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gsls.gt.GT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GT.logt("");

    }

}