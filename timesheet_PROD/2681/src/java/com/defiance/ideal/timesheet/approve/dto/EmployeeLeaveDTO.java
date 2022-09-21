/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.dto;

/**
 *
 * @author 16365
 */
public class EmployeeLeaveDTO {
    private String from_date;
    private String to_date;
    private String length_days;
    private String from_half_day;
    private String to_half_day;
    private String canceled_dates;
    private String leave;
    private String leave_status;

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getLength_days() {
        return length_days;
    }

    public void setLength_days(String length_days) {
        this.length_days = length_days;
    }

    public String getFrom_half_day() {
        return from_half_day;
    }

    public void setFrom_half_day(String from_half_day) {
        this.from_half_day = from_half_day;
    }

    public String getTo_half_day() {
        return to_half_day;
    }

    public void setTo_half_day(String to_half_day) {
        this.to_half_day = to_half_day;
    }

    public String getCanceled_dates() {
        return canceled_dates;
    }

    public void setCanceled_dates(String canceled_dates) {
        this.canceled_dates = canceled_dates;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getLeave_status() {
        return leave_status;
    }

    public void setLeave_status(String leave_status) {
        this.leave_status = leave_status;
    }
    
}
