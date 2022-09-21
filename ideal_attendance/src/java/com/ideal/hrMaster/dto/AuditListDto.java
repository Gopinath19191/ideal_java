/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dto;

/**
 *
 * @author 16113
 */
public class AuditListDto {
    
    private String employee_number;
    private String first_name;
     private String employee_name;
    private String last_name;
    private String modified_date;
    private String module_name;
    private String file_name;
    private String filterName;
    private String filterModule;

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    

    public String getFilterName() {
        return filterName;
    }

    public String getFilterModule() {
        return filterModule;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public void setFilterModule(String filterModule) {
        this.filterModule = filterModule;
    }
    
    

    public String getEmployee_number() {
        return employee_number;
    }

    public String getModified_date() {
        return modified_date;
    }

    public String getModule_name() {
        return module_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    
    
    
}
