package com.gsls.gtlibrary.ui.activity;

import android.os.Bundle;

import com.gsls.gt.GT;
import com.gsls.gt_databinding.annotation.GT_DataBinding;
import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.ui.fragment.FragmentA;
import com.gsls.gtlibrary.view_model.MainViewModel;

@GT_DataBinding(setLayout = "activity_main", setBindingType = GT_DataBinding.Activity)
@GT.Annotations.GT_AnnotationActivity(R.layout.activity_main)
public class MainActivity extends MainActivityBinding<MainViewModel> {

    @GT.GT_Fragment.Build(setClass = FragmentA.class, setLayoutMain = R.id.fl)
    public GT.GT_Fragment gt_fragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        GT.logs("viewModel:" + viewModel);
        if(viewModel != null){
            viewModel.show();
        }

    }

}
