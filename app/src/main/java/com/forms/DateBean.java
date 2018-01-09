package com.forms;

import java.util.Date;

/**
 * Created by forms on 2018/1/9.
 */

public class DateBean {
    private Date date;
    private boolean isSelected;

    public DateBean(Date date, boolean isSelected) {
        this.date = date;
        this.isSelected = isSelected;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
