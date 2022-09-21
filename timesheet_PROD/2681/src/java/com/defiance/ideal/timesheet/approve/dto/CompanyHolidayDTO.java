/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.dto;

/**
 *
 * @author 16365
 */
public class CompanyHolidayDTO {    
    private String holiday_date;
    private String holiday_description;

    public String getHoliday_date() {
        return holiday_date;
    }

    public void setHoliday_date(String holiday_date) {
        this.holiday_date = holiday_date;
    }

    public String getHoliday_description() {
        return holiday_description;
    }

    public void setHoliday_description(String holiday_description) {
        this.holiday_description = holiday_description;
    }
    
}
