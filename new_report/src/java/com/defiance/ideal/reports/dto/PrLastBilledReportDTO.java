/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

import java.io.Serializable;

/**
 *
 * @author 18462
 */
public class PrLastBilledReportDTO implements Serializable {

    public String employee_id;
    public String employee_name;
    public String band;
    public String designation;
    public String unit;
    public String practice;
    public String sub_practice;
    public String employment_status;
    public String billable;
    public String last_billed_date;
   
    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getSub_practice() {
        return sub_practice;
    }

    public void setSub_practice(String sub_practice) {
        this.sub_practice = sub_practice;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getLast_billed_date() {
        return last_billed_date;
    }

    public void setLast_billed_date(String last_billed_date) {
        this.last_billed_date = last_billed_date;
    }
   

   
}
