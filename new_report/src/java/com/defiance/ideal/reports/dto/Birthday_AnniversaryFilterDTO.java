package com.defiance.ideal.reports.dto;

/**
 *
 * @author 9000337
 */
public class Birthday_AnniversaryFilterDTO {

    private String[] birthday_Anniversary;
    private String fromSelectedDate;
    private int fromSelectedDateDate;
    private int fromSelectedDateMonth;
    private String toSelectedDate;
    private int toSelectedDateDate;
    private int toSelectedDateMonth;

    public String[] getBirthday_Anniversary() {
        return birthday_Anniversary;
    }

    public void setBirthday_Anniversary(String[] Birthday_Anniversary) {
        this.birthday_Anniversary = Birthday_Anniversary;
    }

    public String getFromSelectedDate() {
        return fromSelectedDate;
    }

    public void setFromSelectedDate(String fromSelectedDate) {
        this.fromSelectedDate = fromSelectedDate;
    }

    public int getFromSelectedDateDate() {
        return fromSelectedDateDate;
    }

    public void setFromSelectedDateDate(int fromSelectedDateDate) {
        this.fromSelectedDateDate = fromSelectedDateDate;
    }

    public int getFromSelectedDateMonth() {
        return fromSelectedDateMonth;
    }

    public void setFromSelectedDateMonth(int fromSelectedDateMonth) {
        this.fromSelectedDateMonth = fromSelectedDateMonth;
    }

    public String getToSelectedDate() {
        return toSelectedDate;
    }

    public void setToSelectedDate(String toSelectedDate) {
        this.toSelectedDate = toSelectedDate;
    }

    public int getToSelectedDateDate() {
        return toSelectedDateDate;
    }

    public void setToSelectedDateDate(int toSelectedDateDate) {
        this.toSelectedDateDate = toSelectedDateDate;
    }

    public int getToSelectedDateMonth() {
        return toSelectedDateMonth;
    }

    public void setToSelectedDateMonth(int toSelectedDateMonth) {
        this.toSelectedDateMonth = toSelectedDateMonth;
    }
}
