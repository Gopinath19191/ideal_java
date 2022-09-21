/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

import java.io.Serializable;

/**
 *
 * @author 16221
 */
public class ReimbursementsData implements Serializable{
    public String reimbursement_id;
    public String reference_number;
    public String employee_number;
    public String employee_name;
    public String reimbursement_type;
    public String applied_date;
    public String amount;
    public String currency;
    public String description;
    public String rm_approved_date;
    public String rm_name;
    public String hr_approved_date;
    public String hr_name;
    public String buh_approved_date;
    public String buh_name;
    public String finance_approved_date;
    public String finance_name;
    public String status;
    public String from_date;
    public String to_date;
    public String client_reimbursement;
    
    public String getReimbursement_id() {
        return reimbursement_id;
    }

    public void setReimbursement_id(String reimbursement_id) {
        this.reimbursement_id = reimbursement_id;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getReimbursement_type() {
        return reimbursement_type;
    }

    public void setReimbursement_type(String reimbursement_type) {
        this.reimbursement_type = reimbursement_type;
    }

    public String getApplied_date() {
        return applied_date;
    }

    public void setApplied_date(String applied_date) {
        this.applied_date = applied_date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRm_approved_date() {
        return rm_approved_date;
    }

    public void setRm_approved_date(String rm_approved_date) {
        this.rm_approved_date = rm_approved_date;
    }

    public String getRm_name() {
        return rm_name;
    }

    public void setRm_name(String rm_name) {
        this.rm_name = rm_name;
    }

    public String getHr_approved_date() {
        return hr_approved_date;
    }

    public void setHr_approved_date(String hr_approved_date) {
        this.hr_approved_date = hr_approved_date;
    }

    public String getHr_name() {
        return hr_name;
    }

    public void setHr_name(String hr_name) {
        this.hr_name = hr_name;
    }

    public String getBuh_approved_date() {
        return buh_approved_date;
    }

    public void setBuh_approved_date(String buh_approved_date) {
        this.buh_approved_date = buh_approved_date;
    }

    public String getBuh_name() {
        return buh_name;
    }

    public void setBuh_name(String buh_name) {
        this.buh_name = buh_name;
    }

    public String getFinance_approved_date() {
        return finance_approved_date;
    }

    public void setFinance_approved_date(String finance_approved_date) {
        this.finance_approved_date = finance_approved_date;
    }

    public String getFinance_name() {
        return finance_name;
    }

    public void setFinance_name(String finance_name) {
        this.finance_name = finance_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getClient_reimbursement() {
        return client_reimbursement;
    }

    public void setClient_reimbursement(String client_reimbursement) {
        this.client_reimbursement = client_reimbursement;
    }
        
}
