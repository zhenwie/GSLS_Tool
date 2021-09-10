package com.gsls.gtlibrary.delete;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.R;


@GT.Annotations.GT_AnnotationFragment(R.layout.fragment_demo)
public class DemoFragment extends GT.GT_Fragment.AnnotationFragment {

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        GT.log("DemoFragment");


        GT.Permission.init((FragmentActivity) activity)
                .permissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, new GT.Permission.OnPermissionListener() {
            @Override
            public void onExplainRequestReason(GT.Permission.PermissionDescription onPDListener) {

                GT.log("进过 onExplainRequestReason");

                new GT.GT_Dialog
                        .GT_AlertDialog(activity)
                        .dialogButton(
                                R.mipmap.gt_logo,
                                "权限测试",
                                "为了更好好的让应用服务与您，请授予该应用权限",
                                false,
                                "知道了",
                                (dialog, which) -> onPDListener.setAcceptAdvice(true)
                        ).show();
            }

            @Override
            public boolean onForwardToSettings() {
                GT.log("进过 onForwardToSettings");
                return true;
            }

            @Override
            public void request(boolean allGranted, String[] grantedList, String[] deniedList, String message) {
                GT.log("进过 request");

                if (allGranted) {
                    GT.toast("");
                    GT.log("全部授权");
                } else {
                    GT.log("未全部授权");
                    new GT.GT_Dialog
                            .GT_AlertDialog(activity)
                            .dialogButton(R.mipmap.gt_logo,
                                    "授权须知",
                                    "未授予权限，应用无法正常启动。",
                                    false,
                                    "退出", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            activity.finish();
                                        }
                                    }).show();
                }
            }
        });


    }

}
