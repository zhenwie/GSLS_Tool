package com.gsls.gtlibrary.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.util.GT;

@SuppressLint("NonConstantResourceId")
@GT.Annotations.GT_AnnotationActivity(R.layout.activity_main)
public class MainActivity extends GT.GT_Activity.AnnotationActivity {

    @GT.Annotations.GT_View(R.id.tv_server)
    private TextView tv_server;
    @GT.Annotations.GT_View(R.id.tv_client)
    private TextView tv_client;

    @GT.Annotations.GT_View(R.id.et_sendServerData)
    private EditText et_sendServerData;
    @GT.Annotations.GT_View(R.id.et_sendClientData)
    private EditText et_sendClientData;

    @GT.GT_Socket.TCP.Build(setIP = "127.0.0.1", setPort = 1079)
    private GT.GT_Socket.TCP gtSocket_server;
    @GT.GT_Socket.TCP.Build(setPort = 1079)
    private GT.GT_Socket.TCP gtSocket_client;


    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //服务器读取数据
        GT.Thread.runJava(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String readData = gtSocket_server.readData();
                    if (readData != null) {
                        GT.log("服务器读取：" + readData);
                        GT.Thread.runAndroid(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                tv_server.setText(tv_server.getText() + "\n" + readData);
                            }
                        });
                    }
                }

            }
        });

        //客户端读取数据
        GT.Thread.runJava(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String readData = gtSocket_client.readData();
                    if (readData != null) {
                        GT.log("客户端读取：" + readData);
                        GT.Thread.runAndroid(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                tv_client.setText(tv_client.getText() + "\n" + readData);
                            }
                        });
                    }
                }

            }
        });

    }

    @GT.Annotations.GT_Click({R.id.btn_sendServerData, R.id.btn_sendClientData})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sendServerData:
                String sendServerData = et_sendServerData.getText().toString();
                GT.log("单击服务器:" + sendServerData);
                gtSocket_server.sendData(sendServerData);
                break;
            case R.id.btn_sendClientData:
                String sendClientData = et_sendClientData.getText().toString();
                GT.log("单击客户端:" + sendClientData);
                gtSocket_client.sendData(sendClientData);
                break;
        }
    }

}
