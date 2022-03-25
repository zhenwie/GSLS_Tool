package com.gsls.gtlibrary.view_model;

import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.model.Model;
import com.gsls.gtlibrary.ui.dialog.DemoFragmentDialog;
import com.gsls.gtlibrary.ui.floating.DemoFloatingWindow;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 22:25
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
public class DialogFragmentViewModel extends GT.Frame.GT_BindingViewModel<DemoFragmentDialog, Model<?>> {

    @Override
    protected void loadData() {
        super.loadData();
        bindingView.tv_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingModel.show();
                GT.startFloatingWindow(DemoFloatingWindow.class);
            }
        });
        bindingView.tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindingView.finish();
            }
        });
    }
}
