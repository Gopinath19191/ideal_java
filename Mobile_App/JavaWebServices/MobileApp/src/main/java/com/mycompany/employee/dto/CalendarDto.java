
package com.mycompany.employee.dto;

import java.io.Serializable;

public class CalendarDto implements Serializable{
    private String timesheet_date;
    private String available_hours;
    private String is_holiday;
    private String leave_status;
    private String is_weekend;
    private String entries_status;
    private String calendar_id;
    private String attendance_hours;
    private String regularizationHrs;

    public String getRegularizationHrs() {
        return regularizationHrs;
    }

    public void setRegularizationHrs(String regularizationHrs) {
        this.regularizationHrs = regularizationHrs;
    }
    public String getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(String calendar_id) {
        this.calendar_id = calendar_id;
    }

    public String getAttendance_hours() {
        return attendance_hours;
    }

    public void setAttendance_hours(String attendance_hours) {
        this.attendance_hours = attendance_hours;
    }
    
    public String getEntries_status() {
        return entries_status;
    }

    public void setEntries_status(String entries_status) {
        this.entries_status = entries_status;
    }
    
    public String getTimesheet_date() {
        return timesheet_date;
    }

    public void setTimesheet_date(String timesheet_date) {
        this.timesheet_date = timesheet_date;
    }

    public String getAvailable_hours() {
        return available_hours;
    }

    public void setAvailable_hours(String available_hours) {
        this.available_hours = available_hours;
    }

    public String getIs_holiday() {
        return is_holiday;
    }

    public void setIs_holiday(String is_holiday) {
        this.is_holiday = is_holiday;
    }

    public String getLeave_status() {
        return leave_status;
    }

    public void setLeave_status(String leave_status) {
        this.leave_status = leave_status;
    }

    public String getIs_weekend() {
        return is_weekend;
    }

    public void setIs_weekend(String is_weekend) {
        this.is_weekend = is_weekend;
    }
    
}
