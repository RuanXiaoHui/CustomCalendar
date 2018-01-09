package com.forms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendar;
    private TextView tvShow;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        calendar = (CalendarView) findViewById(R.id.calendar);
        tvShow = (TextView) findViewById(R.id.tvShow);
        calendar.setItemClick(new CalendarView.OnCalenDarItemClick() {
            @Override
            public void ItemClick(Map<String, Date> maps) {
                DataProcess(maps);
            }
        });
    }

    public  void DataProcess(Map<String, Date> maps){
        String strCount="";
        tvShow.setText("");
        Set<Map.Entry<String,Date>> map=maps.entrySet();
        Iterator<Map.Entry<String,Date>> iterator=map.iterator();
        while (iterator.hasNext()){
             Map.Entry<String,Date> value=iterator.next();
             String str=sdf.format(value.getValue());
             strCount=strCount+str+"\n";
             tvShow.setText(strCount);
        }
    }
}
