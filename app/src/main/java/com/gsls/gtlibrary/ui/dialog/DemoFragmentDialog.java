package com.gsls.gtlibrary.ui.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.gsls.gt.GT;
import com.gsls.gt_databinding.annotation.GT_DataBinding;
import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.view_model.DialogFragmentViewModel;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 23:30
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
@GT_DataBinding(setLayout = "dialog_demo", setBindingType = GT_DataBinding.DialogFragment)
@GT.Annotations.GT_AnnotationDialogFragment(R.layout.dialog_demo)
public class DemoFragmentDialog extends DemoFragmentDialogBinding<DialogFragmentViewModel> {

    public static DemoFragmentDialog newInstance(String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        DemoFragmentDialog fragment = new DemoFragmentDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        setHideBackground();//设置隐藏背景
        Bundle arguments = getArguments();
        if (arguments != null) {
            String type = arguments.getString("type");
            GT.logt("type:" + type);
        }

        GT.logs("viewModel:" + viewModel);

    }

    @Override
    protected boolean onBackPressed() {
        GT.logt("返回监听");
        return true;
    }


}
