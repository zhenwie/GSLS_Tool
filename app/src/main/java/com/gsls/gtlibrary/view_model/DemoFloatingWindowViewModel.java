package com.gsls.gtlibrary.view_model;

import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.model.Model;
import com.gsls.gtlibrary.ui.floating.DemoFloatingWindow;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 22:25
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
public class DemoFloatingWindowViewModel extends GT.Frame.GT_BindingViewModel<DemoFloatingWindow, Model<?>> {

    @Override
    protected void loadData() {
        super.loadData();
        bindingView.btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GT.logt("悬浮窗 单击了关闭按钮");
                bindingView.finish();
            }
        });
    }
}
