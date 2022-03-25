package com.gsls.gtlibrary.view_model;

import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.model.Model;
import com.gsls.gtlibrary.ui.activity.MainActivity;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 22:25
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
public class MainViewModel extends GT.Frame.GT_BindingViewModel<MainActivity, Model<?>> {

    @Override
    protected void initView() {
        super.initView();
        GT.logt("运行 initView:" + bindingView.btn);
        if(bindingView.btn != null){
            bindingView.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GT.logs("bindingView:" + bindingView);
                    GT.logs("bindingModel:" + bindingModel);
                    if(bindingView.gt_fragment != null){
                        GT.logt(bindingView.gt_fragment.getBackStackFragmentSimpleNames());
                    }
                }
            });
        }
    }

    public void show(){
        GT.logt("执行 ViewModel show 方法");
    }

}
