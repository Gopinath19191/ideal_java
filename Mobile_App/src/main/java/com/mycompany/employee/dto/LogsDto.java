package com.mycompany.employee.dto;

import java.io.Serializable;

/**
 *
 * @author 16363
 */
public class LogsDto implements Serializable{
    private String txn_type_name;
    private String module_name;
    private String function_name;
    private String emp_id;
    private String data;
    private String error_code;
    private String error_description;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
    
    public String getTxn_type_name() {
        return txn_type_name;
    }

    public void setTxn_type_name(String txn_type_name) {
        this.txn_type_name = txn_type_name;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
