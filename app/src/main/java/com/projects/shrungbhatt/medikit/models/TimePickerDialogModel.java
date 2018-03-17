package com.projects.shrungbhatt.medikit.models;

/**
 * Created by jigsaw on 12/3/18.
 */

public class TimePickerDialogModel extends BaseModel {

    private int mFromSelectedHour;
    private int mFromSelectedMinute;
    private int mToSelectedMinute;
    private int mToSelectedHour;

    public int getFromSelectedHour() {
        return mFromSelectedHour;
    }

    public void setFromSelectedHour(int fromSelectedHour) {
        mFromSelectedHour = fromSelectedHour;
    }

    public int getFromSelectedMinute() {
        return mFromSelectedMinute;
    }

    public void setFromSelectedMinute(int fromSelectedMinute) {
        mFromSelectedMinute = fromSelectedMinute;
    }

    public int getToSelectedMinute() {
        return mToSelectedMinute;
    }

    public void setToSelectedMinute(int toSelectedMinute) {
        mToSelectedMinute = toSelectedMinute;
    }

    public int getToSelectedHour() {
        return mToSelectedHour;
    }

    public void setToSelectedHour(int toSelectedHour) {
        mToSelectedHour = toSelectedHour;
    }
}
