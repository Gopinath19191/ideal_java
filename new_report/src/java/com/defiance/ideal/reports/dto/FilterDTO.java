/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

import java.util.Date;

/**
 *
 * @author 14517
 */
public class FilterDTO {

    private String code;
    private String name;
    private String start_date;
    private String end_date;
    private String estimated_hours;
    private String customer_name;
    private String pm_number;
    private String manager;
    private String manager_email;
    private String sbu;
    private String subSbu;
    private String rbu;
    private String billable;
    private String project_type;
    private String ownership;
    private String model;
    private String pricing;
    private String status;
    private String last_entered;
    private String lastapproved;
    private String phase;
    private String tasks;
    private String created;
    private String salesPerson;
    private String team_count;
    private String billing_uom;
    private String billable_efforts;
    private String actual_start_date;
    private String actual_end_date;
    private String actual_efforts;
    private String sales_order_type;
    private String last_modified_date;
    private String business_model;
    
    public String getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(String last_modified_date) {
        this.last_modified_date = last_modified_date;
    }
    
    public String getSales_order_type() {
        return sales_order_type;
    }

    public void setSales_order_type(String sales_order_type) {
        this.sales_order_type = sales_order_type;
    }
    
    public String getSalesPerson() {
        return salesPerson;
    }

    public String getTeam_count() {
        return team_count;
    }

    public void setTeam_count(String team_count) {
        this.team_count = team_count;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastapproved() {
        return lastapproved;
    }

    public void setLastapproved(String lastapproved) {
        this.lastapproved = lastapproved;
    }
    private String closed;

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getEstimated_hours() {
        return estimated_hours;
    }

    public void setEstimated_hours(String estimated_hours) {
        this.estimated_hours = estimated_hours;
    }

    public String getLast_entered() {
        return last_entered;
    }

    public void setLast_entered(String last_entered) {
        this.last_entered = last_entered;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager_email() {
        return manager_email;
    }

    public void setManager_email(String manager_email) {
        this.manager_email = manager_email;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getPm_number() {
        return pm_number;
    }

    public void setPm_number(String pm_number) {
        this.pm_number = pm_number;
    }

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getRbu() {
        return rbu;
    }

    public void setRbu(String rbu) {
        this.rbu = rbu;
    }

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }

    public String getBilling_uom() {
        return billing_uom;
    }

    public void setBilling_uom(String billing_uom) {
        this.billing_uom = billing_uom;
    }

    public String getBillable_efforts() {
        return billable_efforts;
    }

    public void setBillable_efforts(String billable_efforts) {
        this.billable_efforts = billable_efforts;
    }

    public String getActual_start_date() {
        return actual_start_date;
    }

    public void setActual_start_date(String actual_start_date) {
        this.actual_start_date = actual_start_date;
    }

    public String getActual_end_date() {
        return actual_end_date;
    }

    public void setActual_end_date(String actual_end_date) {
        this.actual_end_date = actual_end_date;
    }

    public String getActual_efforts() {
        return actual_efforts;
    }

    public void setActual_efforts(String actual_efforts) {
        this.actual_efforts = actual_efforts;
    }

    public String getBusiness_model() {
        return business_model;
    }

    public void setBusiness_model(String business_model) {
        this.business_model = business_model;
    }
    
}
