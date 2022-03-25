package com.gsls.gtlibrary.ui.adapters;

import android.content.Context;
import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gt_databinding.annotation.GT_DataBinding;
import com.gsls.gtlibrary.R;
import com.gsls.gtlibrary.model.bean.DemoBean;

/**
 * @Author PlayfulKing
 * @Date 2022/3/7 21:24
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description: 适配器
 */

@GT_DataBinding(setLayout = "item_title",setBindingType = GT_DataBinding.Adapter)
@GT.Annotations.GT_AnnotationAdapter(R.layout.item_title)
public class DemoAdapter extends DemoAdapterBinding<DemoBean> {

    public OnAdapterClick onAdapterClick;

    public DemoAdapter(Context context, OnAdapterClick onAdapterClick) {
        super(context);
        this.onAdapterClick = onAdapterClick;
    }

    @Override
    protected void initView(DemoAdapterViewHolder holder, View itemView, int position, DemoBean demoBean) {
        super.initView(holder, itemView, position, demoBean);
        holder.tv_1.setText(demoBean.getName());
        holder.tv_2.setText(demoBean.getSex());
        holder.tv_3.setText("年龄:" + demoBean.getAge());

        //单击加载
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdapterClick.onClick(demoBean);
            }
        });

        //长按操作
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onAdapterClick.onLongClick(demoBean);
                return true;
            }
        });

    }

    public interface OnAdapterClick {
        void onClick(DemoBean demoBean);
        void onLongClick(DemoBean demoBean);
    }

}
