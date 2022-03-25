package com.gsls.gtlibrary.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gt_databinding.annotation.GT_DataBinding;
import com.gsls.gtlibrary.R;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 21:54
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
@GT_DataBinding(setLayout = "fragment_c", setBindingType = GT_DataBinding.Fragment)
@GT.Annotations.GT_AnnotationFragment(R.layout.fragment_c)
public class FragmentC extends GT.GT_Fragment.AnnotationFragment {

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);

    }
}
