package com.gsls.gtlibrary.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.gsls.gt.GT;
import com.gsls.gtlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class TableDataAdapter extends GT.Adapters.BaseAdapter<String> {

    public List<Integer> bytesList = new ArrayList<>();

    public TableDataAdapter(Context context) {
        super(context);
    }

    @Override
    protected int loadLayout() {
        return R.layout.item_table_all_data;
    }

    @Override
    protected void initView(BaseHolder holder, int position, String strings) {
//        GT.log("strings:" + strings);
        LinearLayout ll_allData = (LinearLayout) holder.itemView;

     /*   GT.ViewUtils.MarqueeTextView item_sql = (GT.ViewUtils.MarqueeTextView) LayoutInflater.from(getContext()).inflate(R.layout.item_sql_table_tv, null);
        item_sql.setText(strings);
        ll_allData.addView(item_sql);*/

        String[] split = strings.split("-GT-");
        GT.log("第 " + position + " 条");
        for (int i = 0; i < split.length; i++) {
            GT.ViewUtils.MarqueeTextView item_sql = (GT.ViewUtils.MarqueeTextView) LayoutInflater.from(getContext()).inflate(R.layout.item_sql_table_tv, null);
            String data = adaptationCharacter(bytesList.get(i), split[i]); //字符适配
            item_sql.setText(data);
            ll_allData.addView(item_sql);
        }
    }


    /**
     * 适配字符
     *
     * @param maxLength
     * @param data
     * @return
     */
    private String adaptationCharacter(int maxLength, String data) {
        if (data == null) return data;
//        GT.log("最大字符：" + maxLength + "当前字符：" + data.getBytes().length);
        maxLength = 15;
        if (data.getBytes().length < maxLength) {
            //小于
            while (true) {
                data += " ";
                if (data.getBytes().length >= maxLength) {
                    break;
                }
            }
        } else if (data.getBytes().length > maxLength) {
            //大于
            if (maxLength > 6) {
                data = data.substring(0, maxLength - 2) + "..";
            } else {
                data = data.substring(0, maxLength);
            }
        }
        GT.log("最大长度：" + maxLength + " 返回：" + data + " : " + data.getBytes().length);
        return data;
    }


}
