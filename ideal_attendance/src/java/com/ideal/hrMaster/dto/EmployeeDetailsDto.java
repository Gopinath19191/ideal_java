/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dto;

/**
 *
 * @author 16221
 */
public class EmployeeDetailsDto {
    private String employee_id;
    private String employee_name;
    private String request_date;
    private String sbu_id;
    private String sub_sbu_id;
    private String sbu_name;
    private String sub_sbu_name;
    
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

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getSbu_id() {
        return sbu_id;
    }

    public void setSbu_id(String sbu_id) {
        this.sbu_id = sbu_id;
    }

    public String getSub_sbu_id() {
        return sub_sbu_id;
    }

    public void setSub_sbu_id(String sub_sbu_id) {
        this.sub_sbu_id = sub_sbu_id;
    }

    public String getSbu_name() {
        return sbu_name;
    }

    public void setSbu_name(String sbu_name) {
        this.sbu_name = sbu_name;
    }

    public String getSub_sbu_name() {
        return sub_sbu_name;
    }

    public void setSub_sbu_name(String sub_sbu_name) {
        this.sub_sbu_name = sub_sbu_name;
    }
    
    
}
