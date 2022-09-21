/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

/**
 *
 * @author 16365
 */
public class CalendarDTO {
    
    //For search
    
    private String customer_id;
    private String is_customer;
    private String location_id;

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }
        
    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getIs_customer() {
        return is_customer;
    }

    public void setIs_customer(String is_customer) {
        this.is_customer = is_customer;
    }
    
    //Result class    
    
    private String calendar_id;
    private String calendar_name;

    public String getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(String calendar_id) {
        this.calendar_id = calendar_id;
    }

    public String getCalendar_name() {
        return calendar_name;
    }

    public void setCalendar_name(String calendar_name) {
        this.calendar_name = calendar_name;
    }
    
    
        
}
