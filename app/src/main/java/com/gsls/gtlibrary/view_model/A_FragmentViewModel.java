package com.gsls.gtlibrary.view_model;

import android.view.View;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.model.Model;
import com.gsls.gtlibrary.model.bean.DemoBean;
import com.gsls.gtlibrary.ui.adapters.DemoAdapter;
import com.gsls.gtlibrary.ui.fragment.FragmentA;
import com.gsls.gtlibrary.ui.fragment.FragmentB;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author PlayfulKing
 * @Date 2022/3/8 22:25
 * @CSDN https://blog.csdn.net/qq_39799899
 * @GitHub https://github.com/1079374315/GT
 * @Description:
 */
public class A_FragmentViewModel extends GT.Frame.GT_BindingViewModel<FragmentA, Model<?>> implements DemoAdapter.OnAdapterClick {

    public A_FragmentViewModel() {
    }

    public A_FragmentViewModel(FragmentA bindingData) {
        super(bindingData);
    }

    private DemoAdapter demoAdapter;
    private List<DemoBean> beanList = new ArrayList<>();

    @Override
    protected void loadData() {
        super.loadData();
        demoAdapter = new DemoAdapter(bindingView.activity, this);
        demoAdapter.setLinearLayoutManager_V(bindingView.rv);
        bindingView.tv_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GT.startFragment(FragmentB.class);
            }
        });

        GT.Thread.runJava(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    int anInt = GT.GT_Random.getInt(1,100);
                    DemoBean demoBean = new DemoBean();
                    demoBean.setName(GT.GT_Random.getName(2));
                    demoBean.setAge(anInt);
                    demoBean.setSex(anInt % 2 == 0 ? "男" : "女");
                    beanList.add(demoBean);
                }

                GT.Thread.runAndroid(new Runnable() {
                    @Override
                    public void run() {
                        demoAdapter.setBeanList(beanList);
                    }
                });
            }
        });
    }


    @Override
    public void onClick(DemoBean demoBean) {
        GT.logt("单击:" + demoBean);
    }

    @Override
    public void onLongClick(DemoBean demoBean) {
        GT.logt("长按:" + demoBean);
    }
}
