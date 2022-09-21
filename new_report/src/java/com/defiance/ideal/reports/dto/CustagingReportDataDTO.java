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
public class CustagingReportDataDTO {    
    private String customer_code;
    private String cust_name;
    private String legal_entity;
    private String name;
    private String rbu;
    private String business_leader;
    private String bdm;
    private String customer_contact_phone;
    private String project_code;
    private String invoice_number;
    private String invoice_date;
    private String credit_period;
    private String credit_due_date;
    private String invoicing_currency;
    private Float amount_in_ic;
    private String local_currency;
    private String local_currency_value;
    private String currency_code;
    private String exchange_rate;
    private String finalAmount;
    private String balance_in_LC;
    private String balance_in_IC;
    private Float amount_in_lc;
    private Float amount_collected;
    private String days_past_due;
    private Float tds;
    private Float other_charges;
    private String date_of_report;
    private String legal_entity_id;
    private String legal_entity_code;
    private String business_leader_id;
    private String business_leader_name;
    private String bdm_id;
    private String bdm_name;
    private String customer_id;
    private String customer_name;
    private String balance_in_INR;
    private String subRBU;
    private String custagingId;
    private String invoice_date_submission_customer;
    private String expected_collection_date;
    private String project_name;
    private String customer_email_id;
    private String customer_contact_number;
    private String client_manager;

    public String getCustomer_email_id() {
        return customer_email_id;
    }

    public void setCustomer_email_id(String customer_email_id) {
        this.customer_email_id = customer_email_id;
    }

    public String getCustomer_contact_number() {
        return customer_contact_number;
    }

    public void setCustomer_contact_number(String customer_contact_number) {
        this.customer_contact_number = customer_contact_number;
    }

    public String getClient_manager() {
        return client_manager;
    }

    public void setClient_manager(String client_manager) {
        this.client_manager = client_manager;
    }

    

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getInvoice_date_submission_customer() {
        return invoice_date_submission_customer;
    }

    public void setInvoice_date_submission_customer(String invoice_date_submission_customer) {
        this.invoice_date_submission_customer = invoice_date_submission_customer;
    }

    public String getExpected_collection_date() {
        return expected_collection_date;
    }

    public void setExpected_collection_date(String expected_collection_date) {
        this.expected_collection_date = expected_collection_date;
    }

    

    public String getCustagingId() {
        return custagingId;
    }

    public void setCustagingId(String custagingId) {
        this.custagingId = custagingId;
    }

    public String getBalance_in_INR() {
        return balance_in_INR;
    }

    public void setBalance_in_INR(String balance_in_INR) {
        this.balance_in_INR = balance_in_INR;
    }

    public String getBalance_in_IC() {
        return balance_in_IC;
    }

    public void setBalance_in_IC(String balance_in_IC) {
        this.balance_in_IC = balance_in_IC;
    }

    public String getBalance_in_LC() {
        return balance_in_LC;
    }

    public void setBalance_in_LC(String balance_in_LC) {
        this.balance_in_LC = balance_in_LC;
    }
    
    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(String exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public String getLocal_currency_value() {
        return local_currency_value;
    }

    public void setLocal_currency_value(String local_currency_value) {
        this.local_currency_value = local_currency_value;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getLegal_entity() {
        return legal_entity;
    }

    public void setLegal_entity(String legal_entity) {
        this.legal_entity = legal_entity;
    }
    
    public String getName() {
        return name;
    }

    public String getRbu() {
        return rbu;
    }

    public void setRbu(String rbu) {
        this.rbu = rbu;
    }
    
    public String getBdm() {
        return bdm;
    }

    public void setBdm(String bdm) {
        this.bdm = bdm;
    }

    public String getBusiness_leader() {
        return business_leader;
    }

    public void setBusiness_leader(String business_leader) {
        this.business_leader = business_leader;
    }

    public String getCustomer_contact_phone() {
        return customer_contact_phone;
    }

    public void setCustomer_contact_phone(String customer_contact_phone) {
        this.customer_contact_phone = customer_contact_phone;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getBdm_id() {
        return bdm_id;
    }

    public void setBdm_id(String bdm_id) {
        this.bdm_id = bdm_id;
    }

    public String getBdm_name() {
        return bdm_name;
    }

    public void setBdm_name(String bdm_name) {
        this.bdm_name = bdm_name;
    }

    public String getBusiness_leader_id() {
        return business_leader_id;
    }

    public void setBusiness_leader_id(String business_leader_id) {
        this.business_leader_id = business_leader_id;
    }

    public String getBusiness_leader_name() {
        return business_leader_name;
    }

    public void setBusiness_leader_name(String business_leader_name) {
        this.business_leader_name = business_leader_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getLegal_entity_id() {
        return legal_entity_id;
    }

    public void setLegal_entity_id(String legal_entity_id) {
        this.legal_entity_id = legal_entity_id;
    }

    public String getLegal_entity_code() {
        return legal_entity_code;
    }

    public void setLegal_entity_code(String legal_entity_code) {
        this.legal_entity_code = legal_entity_code;
    }

    public Float getTds() {
        return tds;
    }

    public void setTds(Float tds) {
        this.tds = tds;
    }

    public Float getAmount_collected() {
        return amount_collected;
    }

    public void setAmount_collected(Float amount_collected) {
        this.amount_collected = amount_collected;
    }

    public String getDays_past_due() {
        return days_past_due;
    }

    public void setDays_past_due(String days_past_due) {
        this.days_past_due = days_past_due;
    }

    public Float getAmount_in_ic() {
        return amount_in_ic;
    }

    public void setAmount_in_ic(Float amount_in_ic) {
        this.amount_in_ic = amount_in_ic;
    }

    public Float getAmount_in_lc() {
        return amount_in_lc;
    }

    public void setAmount_in_lc(Float amount_in_lc) {
        this.amount_in_lc = amount_in_lc;
    }

    public String getCredit_due_date() {
        return credit_due_date;
    }

    public void setCredit_due_date(String credit_due_date) {
        this.credit_due_date = credit_due_date;
    }

    public String getCredit_period() {
        return credit_period;
    }

    public void setCredit_period(String credit_period) {
        this.credit_period = credit_period;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public String getDate_of_report() {
        return date_of_report;
    }

    public void setDate_of_report(String date_of_report) {
        this.date_of_report = date_of_report;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getInvoicing_currency() {
        return invoicing_currency;
    }

    public void setInvoicing_currency(String invoicing_currency) {
        this.invoicing_currency = invoicing_currency;
    }

    public String getLocal_currency() {
        return local_currency;
    }

    public void setLocal_currency(String local_currency) {
        this.local_currency = local_currency;
    }

    public Float getOther_charges() {
        return other_charges;
    }

    public void setOther_charges(Float other_charges) {
        this.other_charges = other_charges;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getSubRBU() {
        return subRBU;
    }

    public void setSubRBU(String subRBU) {
        this.subRBU = subRBU;
    }
    
}
