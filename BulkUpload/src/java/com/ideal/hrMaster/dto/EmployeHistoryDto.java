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
    private String employeeName;
     
 private String oldManager;
 
     


    public String getOldManager() {
        return oldManager;
    }

    public void setOldManager(String oldManager) {
        this.oldManager = oldManager;
    }
 
 
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    
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
    private String manager_name;
 private String managerName;

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
 
 
    public String getManager_id() {
        return manager_id;
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
   // private static long serialVersionUID = 1L;;
//     @Override
//	   public String toString() {
//        return "Employee"+employee_number+employee_name;
//         
//     }
}
