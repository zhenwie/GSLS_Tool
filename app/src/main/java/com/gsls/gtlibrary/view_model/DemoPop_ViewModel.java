package com.gsls.gtlibrary.view_model;

import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.model.Model;
import com.gsls.gtlibrary.ui.popupwindow.DemoPopupWindow;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 22:25
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
public class DemoPop_ViewModel extends GT.Frame.GT_BindingViewModel<DemoPopupWindow, Model<?>> {

    @Override
    protected void initView() {
        super.initView();
        bindingView.tv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GT.logt("单击了 单击2");
            }
        });

        bindingView.tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingView.finish();
            }
        });
    }
}
