package com.forms;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by forms on 2018/1/9.
 */

public class CalendarDayView extends AppCompatTextView {

    private Paint mPaint;
    private boolean isToday=false;

    public CalendarDayView(Context context) {
        this(context,null);
    }

    public CalendarDayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CalendarDayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(0X66FF4081);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isToday){
            canvas.translate(getWidth()/2,getHeight()/2);
            canvas.drawCircle(0,0,(getWidth()-6)/2,mPaint);
        }
    }

    public void setToday(boolean today) {
        isToday = today;
    }
}
