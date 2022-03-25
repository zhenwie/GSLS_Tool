package com.gsls.gtlibrary.ui.view;

import android.content.Context;
import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gt_databinding.annotation.GT_DataBinding;
import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.view_model.DemoView_ViewModel;

/**
 * @Author PlayfulKing
 * @Date 2022/3/16 19:47
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
@GT_DataBinding(setLayout = "item_title",setBindingType = GT_DataBinding.View)
@GT.Annotations.GT_AnnotationView(R.layout.item_title)
public class DemoView extends DemoViewBinding<DemoView_ViewModel> {

    public DemoView(Context context) {
        super(context);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected void loadData(View view) {
        super.loadData(view);

    }

    public void setIndex(int index) {
        if(tv_1 != null){
            tv_1.setText(index + "号小明");
            tv_2.setText((index + 1) + "岁");
            tv_3.setText((String.valueOf(index % 2)));
        }

    }

}
