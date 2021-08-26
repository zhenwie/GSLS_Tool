package com.gsls.gtlibrary.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.R;

public class SQLAdapter extends GT.Adapters.BaseAdapter<String> {

    private SQLAdapter.ClickSqlTable clickSqlTable;

    public SQLAdapter(Context context, SQLAdapter.ClickSqlTable clickSqlTable) {
        super(context);
        this.clickSqlTable = clickSqlTable;
    }


    @Override
    protected int loadLayout() {
        return R.layout.item_sql;
    }

    @Override
    protected void initView(BaseHolder holder, int position, String string) {
        TextView tv_sql = (TextView) holder.itemView;
        tv_sql.setText(string);
        //单击事件
        tv_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSqlTable.clickTable(string);
            }
        });

    }

    public interface ClickSqlTable {
        public void clickTable(String name);
    }

}