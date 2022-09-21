/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.dto;

/**
 *
 * @author 16365
 */
public class WFHpolicyIntegrationDTO {
    
    private String month_count;
    private String year_count;
    private String WHF_Per_Month;
    private String WHF_Per_Year;
    private String Emp_id;

    public String getMonth_count() {
        return month_count;
    }

    public void setMonth_count(String month_count) {
        this.month_count = month_count;
    }

    public String getYear_count() {
        return year_count;
    }

    public void setYear_count(String year_count) {
        this.year_count = year_count;
    }

    public String getWHF_Per_Month() {
        return WHF_Per_Month;
    }

    public void setWHF_Per_Month(String WHF_Per_Month) {
        this.WHF_Per_Month = WHF_Per_Month;
    }

    public String getWHF_Per_Year() {
        return WHF_Per_Year;
    }

    public void setWHF_Per_Year(String WHF_Per_Year) {
        this.WHF_Per_Year = WHF_Per_Year;
    }

    public String getEmp_id() {
        return Emp_id;
    }

    public void setEmp_id(String Emp_id) {
        this.Emp_id = Emp_id;
    }
    
}
