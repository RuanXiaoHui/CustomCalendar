package com.forms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Date;
import java.util.List;

/**
 * Created by forms on 2018/1/9.
 */

public class CalendarBaseAdapter extends BaseAdapter {

    private List<DateBean> datas;
    private LayoutInflater mInflate;

    public CalendarBaseAdapter(Context context, List<DateBean> datas) {
        this.datas = datas;
        mInflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        calViewHolder holder = null;
        if (convertView == null) {
            holder = new calViewHolder();
            convertView = mInflate.inflate(R.layout.item_calendar_day, parent, false);
            holder.cdv = (CalendarDayView) convertView.findViewById(R.id.tvDay);
            convertView.setTag(holder);
        } else {
            holder = (calViewHolder) convertView.getTag();
        }
        DateBean dateBean = datas.get(position);
        Date date=dateBean.getDate();
        holder.cdv.setText(date.getDate() + "");
        Date now = new Date();
        boolean isTheSameMonth;
        if (now.getMonth() == date.getMonth() && now.getYear() == date.getYear()) {
            isTheSameMonth = true;
        } else {
            isTheSameMonth = false;
        }

        if (isTheSameMonth) {
            holder.cdv.setTextColor(0XFF000000);
        } else {
            holder.cdv.setTextColor(0XFF666666);
        }

        if (dateBean.isSelected()){
            holder.cdv.setToday(true);
        }else{
            holder.cdv.setToday(false);
        }
        return convertView;
    }

    class calViewHolder {

        CalendarDayView cdv;
    }
}
