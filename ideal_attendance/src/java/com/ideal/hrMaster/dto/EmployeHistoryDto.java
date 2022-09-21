/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dto;

import java.io.Serializable;
import java.sql.Blob;

/**
 *
 * @author 16113
 */
public class EmployeHistoryDto implements Serializable{
    private String id;
    private String employee_id;
    private String changed_from;
    private String changed_to;
    private String first_name;
    private String last_name;
    private String modified_time;
    private String created_time;
    private String oldBandId;
    private String oldDesignationId;
    private String employee_number;
    private String employee_name;
    private String empNumber;

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getOldBandId() {
        return oldBandId;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getOldDesignationId() {
        return oldDesignationId;
    }

    public void setOldBandId(String oldBandId) {
        this.oldBandId = oldBandId;
    }

    public void setOldDesignationId(String oldDesignationId) {
        this.oldDesignationId = oldDesignationId;
    }
    private String field_changed; 
    private String band_id;
    private String designation_id;
    private String band;
    private String designation;
    private String reason;
    private String authEmpId;   

    public String getField_changed() {
        return field_changed;
    }

    public void setField_changed(String field_changed) {
        this.field_changed = field_changed;
    }

    public String getChanged_to() {
        return changed_to;
    }

    public void setChanged_to(String changed_to) {
        this.changed_to = changed_to;
    }
    

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public void setChanged_from(String changed_from) {
        this.changed_from = changed_from;
    }    

    public void setModified_time(String modified_time) {
        this.modified_time = modified_time;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getChanged_from() {
        return changed_from;
    }    

    public String getModified_time() {
        return modified_time;
    }

    public String getAuthEmpId() {
        return authEmpId;
    }

    public void setAuthEmpId(String authEmpId) {
        this.authEmpId = authEmpId;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getId() {
        return id;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getBand_id() {
        return band_id;
    }

    public String getDesignation_id() {
        return designation_id;
    }

    public String getBand() {
        return band;
    }

    public String getDesignation() {
        return designation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setBand_id(String band_id) {
        this.band_id = band_id;
    }

    public void setDesignation_id(String designation_id) {
        this.designation_id = designation_id;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    private String manager_id;
     private String loc_id;
    
    private String manager_name;

    public String getManager_id() {
        return manager_id;
    }

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }
    
    
    private String site_code; 
    private String department;
    private String departmentName;
    
    private String type;
    private String date;
    private String first_in;
    private String last_out;
    private String location;   
      private String source;   

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
      
    public String getSite_code() {
        return site_code;
    }

    public void setSite_code(String site_code) {
        this.site_code = site_code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirst_in() {
        return first_in;
    }

    public void setFirst_in(String first_in) {
        this.first_in = first_in;
    }

    public String getLast_out() {
        return last_out;
    }

    public void setLast_out(String last_out) {
        this.last_out = last_out;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    private String carry_fwd_in;
    private String carry_fwd_out;
    private String next_date;

    public String getCarry_fwd_in() {
        return carry_fwd_in;
    }

    public void setCarry_fwd_in(String carry_fwd_in) {
        this.carry_fwd_in = carry_fwd_in;
    }

    public String getCarry_fwd_out() {
        return carry_fwd_out;
    }

    public void setCarry_fwd_out(String carry_fwd_out) {
        this.carry_fwd_out = carry_fwd_out;
    }

    public String getNext_date() {
        return next_date;
    }

    public void setNext_date(String next_date) {
        this.next_date = next_date;
    }
}
