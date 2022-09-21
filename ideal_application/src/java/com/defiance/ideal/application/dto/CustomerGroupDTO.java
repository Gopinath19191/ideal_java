/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.dto;

import java.io.Serializable;

/**
 *
 * @author 14053
 */
public class CustomerGroupDTO implements Serializable {
    private String groupId;
    private String groupName;
    private String groupCode;
    private String customerSaveButton;
    private String customerSubmitButton;
    private String currentDate;
    private String currentMonth;
    private String currentYear;
    private String dunningDate;
    private String pdf_file_name;
    private String excel_file_name;
    private String zip_folder_name;
    private String customer_code;
    private String customer_name;
    private String entity_name;
    private String file;
    private String fileName;
    private String fileType;
    private String dunningFolder;
    private String selected_date;
    private String user_type;
    private String bdm_id;
    private String bdm_name;
    private String dunning_status;
    private String customer_id;
    private String invoice_code;
    private String invoice_date;
    private String due_date;
    private String days_count;
    private String currency_name;
    private String total_amount;
    private String balance_amount;
    private String customer_email;

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }
    
    public String getInvoice_code() {
        return invoice_code;
    }

    public void setInvoice_code(String invoice_code) {
        this.invoice_code = invoice_code;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDays_count() {
        return days_count;
    }

    public void setDays_count(String days_count) {
        this.days_count = days_count;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getBalance_amount() {
        return balance_amount;
    }

    public void setBalance_amount(String balance_amount) {
        this.balance_amount = balance_amount;
    }
    
    public String getBdm_name() {
        return bdm_name;
    }

    public void setBdm_name(String bdm_name) {
        this.bdm_name = bdm_name;
    }

    public String getDunning_status() {
        return dunning_status;
    }

    public void setDunning_status(String dunning_status) {
        this.dunning_status = dunning_status;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getBdm_id() {
        return bdm_id;
    }

    public void setBdm_id(String bdm_id) {
        this.bdm_id = bdm_id;
    }
    
    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    
    public String getSelected_date() {
        return selected_date;
    }

    public void setSelected_date(String selected_date) {
        this.selected_date = selected_date;
    }
    
    public String getDunningFolder() {
        return dunningFolder;
    }

    public void setDunningFolder(String dunningFolder) {
        this.dunningFolder = dunningFolder;
    }
    
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public String getCustomerSaveButton() {
        return customerSaveButton;
    }

    public void setCustomerSaveButton(String customerSaveButton) {
        this.customerSaveButton = customerSaveButton;
    }

    public String getCustomerSubmitButton() {
        return customerSubmitButton;
    }

    public void setCustomerSubmitButton(String customerSubmitButton) {
        this.customerSubmitButton = customerSubmitButton;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public String getDunningDate() {
        return dunningDate;
    }

    public void setDunningDate(String dunningDate) {
        this.dunningDate = dunningDate;
    }

    public String getPdf_file_name() {
        return pdf_file_name;
    }

    public void setPdf_file_name(String pdf_file_name) {
        this.pdf_file_name = pdf_file_name;
    }

    public String getExcel_file_name() {
        return excel_file_name;
    }

    public void setExcel_file_name(String excel_file_name) {
        this.excel_file_name = excel_file_name;
    }

    public String getZip_folder_name() {
        return zip_folder_name;
    }

    public void setZip_folder_name(String zip_folder_name) {
        this.zip_folder_name = zip_folder_name;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }
    
}
