package com.gsls.gtlibrary.util;

import android.content.Context;
import android.widget.TextView;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.R;

public class LogAdapter extends GT.Adapters.BaseAdapter<LogBean> {

    public LogAdapter(Context context) {
        super(context);
    }

    @Override
    protected int loadLayout() {
        return R.layout.item_log;
    }

    @Override
    protected void initView(BaseHolder holder, int position, LogBean logBean) {
        TextView tv_data = holder.itemView.findViewById(R.id.tv_data);
        tv_data.setText(logBean.getValue());
    }


}
