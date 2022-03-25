package com.gsls.gtlibrary.ui.floating;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gt_databinding.annotation.GT_DataBinding;
import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.view_model.DemoFloatingWindowViewModel;

/**
 * @Author PlayfulKing
 * @Date 2022/3/14 19:34
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
@GT_DataBinding(setLayout = "floating_demo", setBindingType = GT_DataBinding.FloatingWindow)
@GT.Annotations.GT_AnnotationFloatingWindow(R.layout.floating_demo)
public class DemoFloatingWindow extends DemoFloatingWindowBinding<DemoFloatingWindowViewModel> {

    @Override
    protected void initView(View view) {
        super.initView(view);
        setDrag(true);
        GT.logs("viewModel:" + viewModel);
        GT.logt("进入 悬浮窗");
    }

    @Override
    protected void loadData(Context context, Intent intent, View view) {
        super.loadData(context, intent, view);
        GT.logt("view_bg:" + view_bg);


    }

}
