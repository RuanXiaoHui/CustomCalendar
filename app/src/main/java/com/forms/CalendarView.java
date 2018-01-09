package com.forms;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by forms on 2018/1/9.
 */

public class CalendarView extends LinearLayout implements View.OnClickListener {

    private Button btnPre;
    private Button btnNext;
    private AppCompatTextView tvNowData;
    private GridView gvTimeShow;
    private Calendar calendar = Calendar.getInstance();
    private OnCalenDarItemClick itemClick;
    private CalendarBaseAdapter baseAdapter;
    private List<DateBean> datas;
    private SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
    private Map<String,Date> maps;


    public void setItemClick(OnCalenDarItemClick itemClick) {
        this.itemClick = itemClick;
    }


    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_calendar, this, true);
        btnPre = (Button) view.findViewById(R.id.btnPre);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        tvNowData = (AppCompatTextView) view.findViewById(R.id.tvNowData);
        gvTimeShow = (GridView) view.findViewById(R.id.gvTimeShow);
        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        datas = new ArrayList<>();
        maps=new HashMap<>();
        baseAdapter = new CalendarBaseAdapter(getContext(), datas);
        gvTimeShow.setAdapter(baseAdapter);
        processCalender();
        gvTimeShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DateBean beans = (DateBean) (parent.getItemAtPosition(position));
                Date date = beans.getDate();
                Date nowDate=new Date();
                if (nowDate.getMonth() != date.getMonth()
                        || nowDate.getYear() != date.getYear()) {
                    return;
                }
                if (datas.get(position).isSelected()){
                    maps.remove(String.valueOf(date.getDate()));
                }else{
                    maps.put(String.valueOf(date.getDate()),date);
                }
                datas.get(position).setSelected(!datas.get(position).isSelected());
                if (itemClick != null) {
                    itemClick.ItemClick(maps);
                }
                baseAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPre:
                calendar.add(Calendar.MONTH, -1);
                processCalender();
                break;
            case R.id.btnNext:
                calendar.add(Calendar.MONTH, 1);
                processCalender();
                break;
        }
    }

    private void processCalender() {
        maps.clear();
        tvNowData.setText(sdf.format(calendar.getTime()));

        Calendar cal = (Calendar) calendar.clone();

        cal.set(Calendar.DAY_OF_MONTH, 1);

        int preDays = cal.get(Calendar.DAY_OF_WEEK) - 1;

        cal.add(Calendar.DAY_OF_MONTH, -preDays);

        int maxCount = 6 * 7;

        datas.clear();
        Date nowDate = new Date();
        for (int i = 0; i < maxCount; i++) {
            if (nowDate.getDate() == cal.getTime().getDate()
                    && nowDate.getMonth() == cal.getTime().getMonth()
                    && nowDate.getYear() == cal.getTime().getYear()) {
                datas.add(i, new DateBean(cal.getTime(), true));
                maps.put(String.valueOf(cal.getTime().getDate()),cal.getTime());
            } else {
                datas.add(i, new DateBean(cal.getTime(), false));
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        baseAdapter.notifyDataSetChanged();
    }

    public interface OnCalenDarItemClick {

        void ItemClick(Map<String ,Date> maps);
    }
}
