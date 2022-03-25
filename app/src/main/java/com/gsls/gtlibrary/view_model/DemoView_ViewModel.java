package com.gsls.gtlibrary.view_model;

import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.model.Model;
import com.gsls.gtlibrary.ui.popupwindow.DemoPopupWindow;
import com.gsls.gtlibrary.ui.view.DemoView;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 22:25
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
public class DemoView_ViewModel extends GT.Frame.GT_BindingViewModel<DemoView, Model<?>> {

    @Override
    protected void initView() {
        super.initView();
        bindingView.tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GT.toast_s(bindingView.context, bindingView.tv_1.getText().toString());
                bindingModel.show();
                new DemoPopupWindow(bindingView.context).startPopupWindowBelow(v);
            }
        });
    }
}
