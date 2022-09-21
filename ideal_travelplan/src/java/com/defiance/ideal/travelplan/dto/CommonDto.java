/*
 * To change this template;private String  choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dto;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 14583
 */
public class CommonDto implements Serializable {
    
    private String advanceRemarks;
    private String change_buh_id;
    private String advanceTpId;
    private String advanceWorkFlowId;
    private String advanceWorkFlowName;
    private String advancePreviousStatus;
    private String advanceStatus;
    private String tpIdForAdvance;
    private String systemForAdvance;
    private String furtherAdvance;
    private String furtherAdvanceRemarks;
    private String requestorPracticeGroup;
    private String buhid;
    private String rejectCode;
    private String remarks;
    private Date actionDate;
    private Date travelAdvanceEndDate;
    private Date travelAdvanceRequestedDate;
    private String approvedBy;
    private String columnName;
    private String system;
    private String tpId;
    private String tpRequestorId;
    private String tpReferenceId;
    private Date tpRequestedDate;
    private String tpRequestedDateDisplay;
    private String exportDesc;
    private String customerId;
    private String customerName;
    private String customerOthers;
    private String projectId;
    
    private String projectName;
    private String projectOthers;
    private String tpStatus;
    private String tpWorkFlowId;
    private String travelType;
    private String workFlow;
    private String changeBuh;
    private String employeeStatus;
    private String rmStatus;
    private String buhStatus;
    private String mdStatus;
    private String financeStatus;
    private String adminStatus;
    private String treasuryStatus;
    private String employee_id;
    private int start_page;
    private int end_page;
    private String keyRes;
    private String valueRes;
    private String previous_status;
    
    //To Get the Workflow and the status..
    private String tpWorkFlowName;
    private String status;
    private String empBandId;
    private String billable;
    private String deviation;
    private String sales;
    private String vetri;
    private String actionName;
    private String salesGroupId;
    
    // For Dashboard Approval
    private String travelActionCode;
    private String employeeNumber;
    private String employeeName;
    private Date tpStartDate;
    private Date tpEndDate;
    private Date rmApprovedDate;
    private Date buhApprovedDate;
    private Date mdApprovedDate;
    private Date finApprovedDate;
    private Date cfoApprovedDate;
    private Date adminApprovedDate;
    private Date treasuryApprovedDate;
    private String currentStatus;
    private String nextLevel;
    private String firstNextLevel;
    private String secondNextLevel;
    
    private String moduleId;
    private String approveType;

    private String employee_number;
    private String employee_name;
    private String designation;
    private String group_name;
    private String sub_group_name;
    private String city_name;
    private String band_name;
    private String customer_id;
    private String buhName;
    private String buhsName;
    private String sbuName;
    private String emp_id;
    private String buh_id;
    private String customer_name;
    private String project_id;
    private String key;
    private String project_name;
private String project_code;
    //Travel Ticket
    private String[] from_country;
    private String[] from_city;
    private String[] from_city_id;
    private String[] from_city_others;
    private String[] to_country;
    private String[] to_city;
    private String[] to_city_id;
    private String[] to_city_others;
    private Date[] travel_date;
    private String[] travel_preference;
    private String[] travel_mode;
    private String[] ticket_remarks;
    private String[] ticketdeleted;
    private String[] ticket_id;

    private String out_from_country;
    private String out_from_city;
    private String out_from_city_id;
    private String out_from_city_others;
    private String out_to_country;
    private String out_to_city;
    private String out_to_city_id;
    private String out_to_city_others;
    private String out_travel_date;
    private Date travel_ticket_date;
    private String out_out_travel_date;
    private String out_travel_preference;
    private String out_travel_mode;
    private String out_ticket_remarks;
    private String out_ticket_id;
    private String out_ticketdeleted;
    private int ticketCount;

    //Hotel Hotel
    private String[] country;
    private String[] city;
    private String[] city_id;
    private String[] city_others;
    private Date[] from_date;
    private Date[] to_date;
    private String[] location;
    private String[] hotel_remarks;
    private String[] hoteldeleted;
    private String[] hotel_id;

    private String out_country;
    private String out_city;
    private String out_city_id;
    private String out_city_others;
    private String out_from_date;
    private String out_to_date;
    private Date hotel_from_date;
    private Date hotel_to_date;
    private String out_location;
    private String out_hotel_remarks;
    private String out_hoteldeleted;
    private String out_hotel_id;
    private int hotelCount;

    //Conveyance
    private String[] pickup;
    private String[] dropto;
    private Date[] start_date;
    private Date[] end_date;
    private String[] time_hr;
    private String[] time_min;
    private String[] conveyance_remarks;
    private String[] conveyance_country;
    private String[] conveyance_city;
    private String[] conveyance_city_id;
    private String[] conveyance_city_others;
    private String[] conveyancedeleted;
    private String[] conveyance_id;

    private String out_pickup;
    private String out_dropto;
    private Date conveyance_start_date;
    private Date conveyance_end_date;
    private String out_start_date;
    private String out_end_date;
    private String out_travel_time;
    private String out_conveyance_remarks;
    private String out_conveyance_country;
    private String out_conveyance_city;
    private String out_conveyance_city_id;
    private String out_conveyance_city_others;
    private String out_conveyancedeleted;
    private String out_conveyance_id;
    private int conveyanceCount;

    //Domestic/International

    private String advance_required;
    private String approved_advance;
    private String travel_purpose;
    private Date travel_start_date;
    private Date travel_end_date;
    private String out_travel_start_date;
    private String out_travel_end_date;
    private Date travel_requested_date;
    private String out_travel_requested_date;
    private String travel_billable;
    private String client_reimbursable;
    private String travel_term;
    private String customer_others;
    private String project_others;
    private String currency_type;
    private String emp_remarks;
    private String mobileNo;
    private String rm_remarks;
    private String rm_approved_by;
    private String rm_action_date;
    private String buh_remarks;
    private String buh_approved_by;
    private String buh_action_date;
    private String md_remarks;
    private String md_approved_by;
    private String md_action_date;
    private String finance_remarks;
    private String finance_approved_by;
    private String finance_action_date;
    private String cfo_remarks;
    private String cfo_approved_by;
    private String cfo_action_date;
    private String admin_remarks;
    private String admin_approved_by;
    private String admin_action_date;
    private String treasury_remarks;
    private String treasury_approved_by;
    private String treasury_action_date;
    private String guest_booking;
    private String master_id;
    private String last_id;
    private String action;

    //Attachment

    private int attachmentCount;
    private String attachmentdeleted;
    private String fileName;
    private String attachment_id;

    //Visa

    private String currency_id;
    private String currency_code;
    private String countryId;
    private String countryName;
    private String visaId;
    private String visaType;
    private String reference_number;
    private String visa_type;
    private Date valid_from;
    private Date valid_to;
    private String out_valid_from;
    private String out_valid_to;
    private String country_issue;
    private String place_of_issue;
    private String visa_visit;
    private String visa_remarks;
    private String visa_id;

    private String approved_by;
    private String approved_date;
    private String approver_remarks;
    private String approval_status;
    private String approver;
    private String approveAction;
    private String sendBackAction;
    private String cfoAction;
    private String approverId;
    private String admin_action_required;
    private String rm_approved_status;
    private String buh_approved_status;
    private String md_approved_status;
    private String cfo_approved_status;
    private String finance_approved_status;
    private String admin_approved_status;
    private String treasury_approved_status;
    private String approved_status;
    private String cfo_action_required;
    private String configValue;
    private String travel_text;
    private String email_address;
    private String rm_email_address;
    private String rm_name;
    private String requestor;
    private String manager;
    private String travel_other_country;
    private String band_id;
    private String mail_status;
    private String cancel_remarks;
    private String workflow_id;
    private String amount_deposited;
    private String rm_approved_date;
    private String buh_approved_date;
    private String md_approved_date;
    private String cfo_approved_date;
    private String finance_approved_date;
    private String admin_approved_date;
    private String treasury_approved_date;
    private String filter_employeeId;
    private String filter_travelType;
    private String submitButton;
    private String disclaimerId;
    private String disclaimerName;
    private String exportButton;
    private String empManager;
    private String printAction;
    private String startDate;
    private String endDate;
    private String amount;
    
    
    private String trApprovedDate;
    private String approvedAdvance;
    private String currencyCode;
    private String buh_change;
    private String group_id;

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    
    
    public String getTrApprovedDate() {
        return trApprovedDate;
    }

    public void setTrApprovedDate(String trApprovedDate) {
        this.trApprovedDate = trApprovedDate;
    }

    public String getApprovedAdvance() {
        return approvedAdvance;
    }

    public void setApprovedAdvance(String approvedAdvance) {
        this.approvedAdvance = approvedAdvance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    

    public String getExportButton() {
        return exportButton;
    }

    public void setExportButton(String exportButton) {
        this.exportButton = exportButton;
    }

    public String getDisclaimerId() {
        return disclaimerId;
    }

    public void setDisclaimerId(String disclaimerId) {
        this.disclaimerId = disclaimerId;
    }

    public String getDisclaimerName() {
        return disclaimerName;
    }

    public void setDisclaimerName(String disclaimerName) {
        this.disclaimerName = disclaimerName;
    }

    public String getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(String submitButton) {
        this.submitButton = submitButton;
    }

    public String getFilter_travelType() {
        return filter_travelType;
    }

    public void setFilter_travelType(String filter_travelType) {
        this.filter_travelType = filter_travelType;
    }

    public String getFilter_employeeId() {
        return filter_employeeId;
    }

    public void setFilter_employeeId(String filter_employeeId) {
        this.filter_employeeId = filter_employeeId;
    }

    public String getAdmin_approved_date() {
        return admin_approved_date;
    }

    public void setAdmin_approved_date(String admin_approved_date) {
        this.admin_approved_date = admin_approved_date;
    }

    public String getBuh_approved_date() {
        return buh_approved_date;
    }

    public void setBuh_approved_date(String buh_approved_date) {
        this.buh_approved_date = buh_approved_date;
    }

    public String getCfo_approved_date() {
        return cfo_approved_date;
    }

    public void setCfo_approved_date(String cfo_approved_date) {
        this.cfo_approved_date = cfo_approved_date;
    }

    public String getFinance_approved_date() {
        return finance_approved_date;
    }

    public void setFinance_approved_date(String finance_approved_date) {
        this.finance_approved_date = finance_approved_date;
    }

    public String getMd_approved_date() {
        return md_approved_date;
    }

    public void setMd_approved_date(String md_approved_date) {
        this.md_approved_date = md_approved_date;
    }

    public String getRm_approved_date() {
        return rm_approved_date;
    }

    public void setRm_approved_date(String rm_approved_date) {
        this.rm_approved_date = rm_approved_date;
    }

    public String getTreasury_approved_date() {
        return treasury_approved_date;
    }

    public void setTreasury_approved_date(String treasury_approved_date) {
        this.treasury_approved_date = treasury_approved_date;
    }

    public String getAmount_deposited() {
        return amount_deposited;
    }

    public void setAmount_deposited(String amount_deposited) {
        this.amount_deposited = amount_deposited;
    }

    public String getWorkflow_id() {
        return workflow_id;
    }

    public void setWorkflow_id(String workflow_id) {
        this.workflow_id = workflow_id;
    }

    public String getCancel_remarks() {
        return cancel_remarks;
    }

    public void setCancel_remarks(String cancel_remarks) {
        this.cancel_remarks = cancel_remarks;
    }

    public String getMail_status() {
        return mail_status;
    }

    public void setMail_status(String mail_status) {
        this.mail_status = mail_status;
    }

    public String getBand_id() {
        return band_id;
    }

    public void setBand_id(String band_id) {
        this.band_id = band_id;
    }

    public String getTravel_other_country() {
        return travel_other_country;
    }

    public void setTravel_other_country(String travel_other_country) {
        this.travel_other_country = travel_other_country;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getRm_email_address() {
        return rm_email_address;
    }

    public void setRm_email_address(String rm_email_address) {
        this.rm_email_address = rm_email_address;
    }

    public String getRm_name() {
        return rm_name;
    }

    public void setRm_name(String rm_name) {
        this.rm_name = rm_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getTravel_text() {
        return travel_text;
    }

    public void setTravel_text(String travel_text) {
        this.travel_text = travel_text;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getCfo_action_required() {
        return cfo_action_required;
    }

    public void setCfo_action_required(String cfo_action_required) {
        this.cfo_action_required = cfo_action_required;
    }

    public String getApproved_status() {
        return approved_status;
    }

    public void setApproved_status(String approved_status) {
        this.approved_status = approved_status;
    }

    public String getAdmin_approved_status() {
        return admin_approved_status;
    }

    public void setAdmin_approved_status(String admin_approved_status) {
        this.admin_approved_status = admin_approved_status;
    }

    public String getBuh_approved_status() {
        return buh_approved_status;
    }

    public void setBuh_approved_status(String buh_approved_status) {
        this.buh_approved_status = buh_approved_status;
    }

    public String getCfo_approved_status() {
        return cfo_approved_status;
    }

    public void setCfo_approved_status(String cfo_approved_status) {
        this.cfo_approved_status = cfo_approved_status;
    }

    public String getFinance_approved_status() {
        return finance_approved_status;
    }

    public void setFinance_approved_status(String finance_approved_status) {
        this.finance_approved_status = finance_approved_status;
    }

    public String getMd_approved_status() {
        return md_approved_status;
    }

    public void setMd_approved_status(String md_approved_status) {
        this.md_approved_status = md_approved_status;
    }

    public String getRm_approved_status() {
        return rm_approved_status;
    }

    public void setRm_approved_status(String rm_approved_status) {
        this.rm_approved_status = rm_approved_status;
    }

    public String getTreasury_approved_status() {
        return treasury_approved_status;
    }

    public void setTreasury_approved_status(String treasury_approved_status) {
        this.treasury_approved_status = treasury_approved_status;
    }

    public String getAdmin_action_required() {
        return admin_action_required;
    }

    public void setAdmin_action_required(String admin_action_required) {
        this.admin_action_required = admin_action_required;
    }

    public String getPrevious_status() {
        return previous_status;
    }

    public void setPrevious_status(String previous_status) {
        this.previous_status = previous_status;
    }

    public String getApproveAction() {
        return approveAction;
    }

    public void setApproveAction(String approveAction) {
        this.approveAction = approveAction;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getCfoAction() {
        return cfoAction;
    }

    public void setCfoAction(String cfoAction) {
        this.cfoAction = cfoAction;
    }

    public String getSendBackAction() {
        return sendBackAction;
    }

    public void setSendBackAction(String sendBackAction) {
        this.sendBackAction = sendBackAction;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(String approval_status) {
        this.approval_status = approval_status;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public String getApproved_date() {
        return approved_date;
    }

    public void setApproved_date(String approved_date) {
        this.approved_date = approved_date;
    }

    public String getApprover_remarks() {
        return approver_remarks;
    }

    public void setApprover_remarks(String approver_remarks) {
        this.approver_remarks = approver_remarks;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String[] getConveyance_city() {
        return conveyance_city;
    }

    public void setConveyance_city(String[] conveyance_city) {
        this.conveyance_city = conveyance_city;
    }

    public String[] getConveyance_city_id() {
        return conveyance_city_id;
    }

    public void setConveyance_city_id(String[] conveyance_city_id) {
        this.conveyance_city_id = conveyance_city_id;
    }

    public String[] getConveyance_city_others() {
        return conveyance_city_others;
    }

    public void setConveyance_city_others(String[] conveyance_city_others) {
        this.conveyance_city_others = conveyance_city_others;
    }

    public String[] getConveyance_country() {
        return conveyance_country;
    }

    public void setConveyance_country(String[] conveyance_country) {
        this.conveyance_country = conveyance_country;
    }

    public Date getConveyance_end_date() {
        return conveyance_end_date;
    }

    public void setConveyance_end_date(Date conveyance_end_date) {
        this.conveyance_end_date = conveyance_end_date;
    }

    public String[] getConveyance_id() {
        return conveyance_id;
    }

    public void setConveyance_id(String[] conveyance_id) {
        this.conveyance_id = conveyance_id;
    }

    public String[] getConveyance_remarks() {
        return conveyance_remarks;
    }

    public void setConveyance_remarks(String[] conveyance_remarks) {
        this.conveyance_remarks = conveyance_remarks;
    }

    public Date getConveyance_start_date() {
        return conveyance_start_date;
    }

    public void setConveyance_start_date(Date conveyance_start_date) {
        this.conveyance_start_date = conveyance_start_date;
    }

    public String[] getConveyancedeleted() {
        return conveyancedeleted;
    }

    public void setConveyancedeleted(String[] conveyancedeleted) {
        this.conveyancedeleted = conveyancedeleted;
    }

    public String[] getDropto() {
        return dropto;
    }

    public void setDropto(String[] dropto) {
        this.dropto = dropto;
    }

    public Date[] getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date[] end_date) {
        this.end_date = end_date;
    }

    public String getOut_conveyance_city_id() {
        return out_conveyance_city_id;
    }

    public void setOut_conveyance_city_id(String out_conveyance_city_id) {
        this.out_conveyance_city_id = out_conveyance_city_id;
    }

    public String getOut_conveyance_city_others() {
        return out_conveyance_city_others;
    }

    public void setOut_conveyance_city_others(String out_conveyance_city_others) {
        this.out_conveyance_city_others = out_conveyance_city_others;
    }

    public String getOut_conveyance_country() {
        return out_conveyance_country;
    }

    public void setOut_conveyance_country(String out_conveyance_country) {
        this.out_conveyance_country = out_conveyance_country;
    }

    public String getOut_conveyance_id() {
        return out_conveyance_id;
    }

    public void setOut_conveyance_id(String out_conveyance_id) {
        this.out_conveyance_id = out_conveyance_id;
    }

    public String getOut_conveyance_remarks() {
        return out_conveyance_remarks;
    }

    public void setOut_conveyance_remarks(String out_conveyance_remarks) {
        this.out_conveyance_remarks = out_conveyance_remarks;
    }

    public String getOut_conveyancedeleted() {
        return out_conveyancedeleted;
    }

    public void setOut_conveyancedeleted(String out_conveyancedeleted) {
        this.out_conveyancedeleted = out_conveyancedeleted;
    }

    public String getOut_dropto() {
        return out_dropto;
    }

    public void setOut_dropto(String out_dropto) {
        this.out_dropto = out_dropto;
    }

    public String getOut_pickup() {
        return out_pickup;
    }

    public void setOut_pickup(String out_pickup) {
        this.out_pickup = out_pickup;
    }

    public String getOut_travel_time() {
        return out_travel_time;
    }

    public void setOut_travel_time(String out_travel_time) {
        this.out_travel_time = out_travel_time;
    }

    public String[] getPickup() {
        return pickup;
    }

    public void setPickup(String[] pickup) {
        this.pickup = pickup;
    }

    public Date[] getStart_date() {
        return start_date;
    }

    public void setStart_date(Date[] start_date) {
        this.start_date = start_date;
    }

    public String[] getTime_hr() {
        return time_hr;
    }

    public void setTime_hr(String[] time_hr) {
        this.time_hr = time_hr;
    }

    public String[] getTime_min() {
        return time_min;
    }

    public void setTime_min(String[] time_min) {
        this.time_min = time_min;
    }

    public String[] getCity() {
        return city;
    }

    public void setCity(String[] city) {
        this.city = city;
    }

    public String[] getCity_id() {
        return city_id;
    }

    public void setCity_id(String[] city_id) {
        this.city_id = city_id;
    }

    public String[] getCity_others() {
        return city_others;
    }

    public void setCity_others(String[] city_others) {
        this.city_others = city_others;
    }

    public String[] getCountry() {
        return country;
    }

    public void setCountry(String[] country) {
        this.country = country;
    }

    public Date[] getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date[] from_date) {
        this.from_date = from_date;
    }

    public Date getHotel_from_date() {
        return hotel_from_date;
    }

    public void setHotel_from_date(Date hotel_from_date) {
        this.hotel_from_date = hotel_from_date;
    }

    public String[] getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String[] hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String[] getHotel_remarks() {
        return hotel_remarks;
    }

    public void setHotel_remarks(String[] hotel_remarks) {
        this.hotel_remarks = hotel_remarks;
    }

    public Date getHotel_to_date() {
        return hotel_to_date;
    }

    public void setHotel_to_date(Date hotel_to_date) {
        this.hotel_to_date = hotel_to_date;
    }

    public String[] getHoteldeleted() {
        return hoteldeleted;
    }

    public void setHoteldeleted(String[] hoteldeleted) {
        this.hoteldeleted = hoteldeleted;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public String getOut_city_id() {
        return out_city_id;
    }

    public void setOut_city_id(String out_city_id) {
        this.out_city_id = out_city_id;
    }

    public String getOut_city_others() {
        return out_city_others;
    }

    public void setOut_city_others(String out_city_others) {
        this.out_city_others = out_city_others;
    }

    public String getOut_country() {
        return out_country;
    }

    public void setOut_country(String out_country) {
        this.out_country = out_country;
    }

    public String getOut_hotel_id() {
        return out_hotel_id;
    }

    public void setOut_hotel_id(String out_hotel_id) {
        this.out_hotel_id = out_hotel_id;
    }

    public String getOut_hotel_remarks() {
        return out_hotel_remarks;
    }

    public void setOut_hotel_remarks(String out_hotel_remarks) {
        this.out_hotel_remarks = out_hotel_remarks;
    }

    public String getOut_hoteldeleted() {
        return out_hoteldeleted;
    }

    public void setOut_hoteldeleted(String out_hoteldeleted) {
        this.out_hoteldeleted = out_hoteldeleted;
    }

    public String getOut_location() {
        return out_location;
    }

    public void setOut_location(String out_location) {
        this.out_location = out_location;
    }

    public Date[] getTo_date() {
        return to_date;
    }

    public void setTo_date(Date[] to_date) {
        this.to_date = to_date;
    }

    public Date getTravel_ticket_date() {
        return travel_ticket_date;
    }

    public void setTravel_ticket_date(Date travel_ticket_date) {
        this.travel_ticket_date = travel_ticket_date;
    }

    public String getOut_ticketdeleted() {
        return out_ticketdeleted;
    }

    public void setOut_ticketdeleted(String out_ticketdeleted) {
        this.out_ticketdeleted = out_ticketdeleted;
    }

    public String getOut_travel_date() {
        return out_travel_date;
    }

    public void setOut_travel_date(String out_travel_date) {
        this.out_travel_date = out_travel_date;
    }

    public String[] getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String[] from_city) {
        this.from_city = from_city;
    }

    public String[] getFrom_city_id() {
        return from_city_id;
    }

    public void setFrom_city_id(String[] from_city_id) {
        this.from_city_id = from_city_id;
    }

    public String[] getFrom_city_others() {
        return from_city_others;
    }

    public void setFrom_city_others(String[] from_city_others) {
        this.from_city_others = from_city_others;
    }

    public String[] getFrom_country() {
        return from_country;
    }

    public void setFrom_country(String[] from_country) {
        this.from_country = from_country;
    }

    public String getOut_from_city_id() {
        return out_from_city_id;
    }

    public void setOut_from_city_id(String out_from_city_id) {
        this.out_from_city_id = out_from_city_id;
    }

    public String getOut_from_city_others() {
        return out_from_city_others;
    }

    public void setOut_from_city_others(String out_from_city_others) {
        this.out_from_city_others = out_from_city_others;
    }

    public String getOut_from_country() {
        return out_from_country;
    }

    public void setOut_from_country(String out_from_country) {
        this.out_from_country = out_from_country;
    }

    public String getOut_out_travel_date() {
        return out_out_travel_date;
    }

    public void setOut_out_travel_date(String out_out_travel_date) {
        this.out_out_travel_date = out_out_travel_date;
    }

    public String getOut_ticket_id() {
        return out_ticket_id;
    }

    public void setOut_ticket_id(String out_ticket_id) {
        this.out_ticket_id = out_ticket_id;
    }

    public String getOut_ticket_remarks() {
        return out_ticket_remarks;
    }

    public void setOut_ticket_remarks(String out_ticket_remarks) {
        this.out_ticket_remarks = out_ticket_remarks;
    }

    public String getOut_to_city_id() {
        return out_to_city_id;
    }

    public void setOut_to_city_id(String out_to_city_id) {
        this.out_to_city_id = out_to_city_id;
    }

    public String getOut_to_city_others() {
        return out_to_city_others;
    }

    public void setOut_to_city_others(String out_to_city_others) {
        this.out_to_city_others = out_to_city_others;
    }

    public String getOut_to_country() {
        return out_to_country;
    }

    public void setOut_to_country(String out_to_country) {
        this.out_to_country = out_to_country;
    }

    public String getOut_travel_mode() {
        return out_travel_mode;
    }

    public void setOut_travel_mode(String out_travel_mode) {
        this.out_travel_mode = out_travel_mode;
    }

    public String getOut_travel_preference() {
        return out_travel_preference;
    }

    public void setOut_travel_preference(String out_travel_preference) {
        this.out_travel_preference = out_travel_preference;
    }

    public String[] getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String[] ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String[] getTicket_remarks() {
        return ticket_remarks;
    }

    public void setTicket_remarks(String[] ticket_remarks) {
        this.ticket_remarks = ticket_remarks;
    }

    public String[] getTicketdeleted() {
        return ticketdeleted;
    }

    public void setTicketdeleted(String[] ticketdeleted) {
        this.ticketdeleted = ticketdeleted;
    }

    public String[] getTo_city() {
        return to_city;
    }

    public void setTo_city(String[] to_city) {
        this.to_city = to_city;
    }

    public String[] getTo_city_id() {
        return to_city_id;
    }

    public void setTo_city_id(String[] to_city_id) {
        this.to_city_id = to_city_id;
    }

    public String[] getTo_city_others() {
        return to_city_others;
    }

    public void setTo_city_others(String[] to_city_others) {
        this.to_city_others = to_city_others;
    }

    public String[] getTo_country() {
        return to_country;
    }

    public void setTo_country(String[] to_country) {
        this.to_country = to_country;
    }

    public Date[] getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(Date[] travel_date) {
        this.travel_date = travel_date;
    }

    public String[] getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String[] travel_mode) {
        this.travel_mode = travel_mode;
    }

    public String[] getTravel_preference() {
        return travel_preference;
    }

    public void setTravel_preference(String[] travel_preference) {
        this.travel_preference = travel_preference;
    }

    //Employee Profile
    private String nationality;
    private String nationalityId;
    private String empId;
    private String surName;
    private String givenName;
    private String passportNumber;
    private String issuePlace;
    private Date issuedDate;
    private Date expiryDate;
    private String birthPlace;
    private Date birthDate;
    private String ecnrStatus;
    private String nominee;
    private String relationShip;
    private String doorNo;
    private String streetName;
    private String area;
    private String place;
    private String mealPreference;
    private String officialMailId;
    private String alternateMailId;
    private String gender;
    private String birth_date;
    private String guest_name;

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getOut_valid_from() {
        return out_valid_from;
    }

    public void setOut_valid_from(String out_valid_from) {
        this.out_valid_from = out_valid_from;
    }

    public String getOut_valid_to() {
        return out_valid_to;
    }

    public void setOut_valid_to(String out_valid_to) {
        this.out_valid_to = out_valid_to;
    }

    public String getVisa_id() {
        return visa_id;
    }

    public void setVisa_id(String visa_id) {
        this.visa_id = visa_id;
    }

    public String getCountry_issue() {
        return country_issue;
    }

    public void setCountry_issue(String country_issue) {
        this.country_issue = country_issue;
    }

    public String getPlace_of_issue() {
        return place_of_issue;
    }

    public void setPlace_of_issue(String place_of_issue) {
        this.place_of_issue = place_of_issue;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public Date getValid_from() {
        return valid_from;
    }

    public void setValid_from(Date valid_from) {
        this.valid_from = valid_from;
    }

    public Date getValid_to() {
        return valid_to;
    }

    public void setValid_to(Date valid_to) {
        this.valid_to = valid_to;
    }

    public String getVisa_remarks() {
        return visa_remarks;
    }

    public void setVisa_remarks(String visa_remarks) {
        this.visa_remarks = visa_remarks;
    }

    public String getVisa_type() {
        return visa_type;
    }

    public void setVisa_type(String visa_type) {
        this.visa_type = visa_type;
    }

    public String getVisa_visit() {
        return visa_visit;
    }

    public void setVisa_visit(String visa_visit) {
        this.visa_visit = visa_visit;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getVisaId() {
        return visaId;
    }

    public void setVisaId(String visaId) {
        this.visaId = visaId;
    }

    public String getVisaType() {
        return visaType;
    }

    public void setVisaType(String visaType) {
        this.visaType = visaType;
    }

    public String getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(String attachment_id) {
        this.attachment_id = attachment_id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getAttachmentCount() {
        return attachmentCount;
    }

    public void setAttachmentCount(int attachmentCount) {
        this.attachmentCount = attachmentCount;
    }

    public String getAttachmentdeleted() {
        return attachmentdeleted;
    }

    public void setAttachmentdeleted(String attachmentdeleted) {
        this.attachmentdeleted = attachmentdeleted;
    }

    public int getConveyanceCount() {
        return conveyanceCount;
    }

    public void setConveyanceCount(int conveyanceCount) {
        this.conveyanceCount = conveyanceCount;
    }

    public int getHotelCount() {
        return hotelCount;
    }

    public void setHotelCount(int hotelCount) {
        this.hotelCount = hotelCount;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getOut_travel_requested_date() {
        return out_travel_requested_date;
    }

    public void setOut_travel_requested_date(String out_travel_requested_date) {
        this.out_travel_requested_date = out_travel_requested_date;
    }

    public String getOut_end_date() {
        return out_end_date;
    }

    public void setOut_end_date(String out_end_date) {
        this.out_end_date = out_end_date;
    }

    public String getOut_from_date() {
        return out_from_date;
    }

    public void setOut_from_date(String out_from_date) {
        this.out_from_date = out_from_date;
    }

    public String getOut_start_date() {
        return out_start_date;
    }

    public void setOut_start_date(String out_start_date) {
        this.out_start_date = out_start_date;
    }

    public String getOut_to_date() {
        return out_to_date;
    }

    public void setOut_to_date(String out_to_date) {
        this.out_to_date = out_to_date;
    }

    public String getOut_travel_end_date() {
        return out_travel_end_date;
    }

    public void setOut_travel_end_date(String out_travel_end_date) {
        this.out_travel_end_date = out_travel_end_date;
    }

    public String getOut_travel_start_date() {
        return out_travel_start_date;
    }

    public void setOut_travel_start_date(String out_travel_start_date) {
        this.out_travel_start_date = out_travel_start_date;
    }

    public String getLast_id() {
        return last_id;
    }

    public void setLast_id(String last_id) {
        this.last_id = last_id;
    }

    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public String getAdmin_action_date() {
        return admin_action_date;
    }

    public void setAdmin_action_date(String admin_action_date) {
        this.admin_action_date = admin_action_date;
    }

    public String getAdmin_approved_by() {
        return admin_approved_by;
    }

    public void setAdmin_approved_by(String admin_approved_by) {
        this.admin_approved_by = admin_approved_by;
    }

    public String getAdmin_remarks() {
        return admin_remarks;
    }

    public void setAdmin_remarks(String admin_remarks) {
        this.admin_remarks = admin_remarks;
    }

    public String getAdvance_required() {
        return advance_required;
    }

    public void setAdvance_required(String advance_required) {
        this.advance_required = advance_required;
    }
    
    public String getApproved_advance() {
        return approved_advance;
    }

    public void setApproved_advance(String approved_advance) {
        this.approved_advance = approved_advance;
    }

    public String getBuh_action_date() {
        return buh_action_date;
    }

    public void setBuh_action_date(String buh_action_date) {
        this.buh_action_date = buh_action_date;
    }

    public String getBuh_approved_by() {
        return buh_approved_by;
    }

    public void setBuh_approved_by(String buh_approved_by) {
        this.buh_approved_by = buh_approved_by;
    }

    public String getBuh_remarks() {
        return buh_remarks;
    }

    public void setBuh_remarks(String buh_remarks) {
        this.buh_remarks = buh_remarks;
    }

    public String getCfo_action_date() {
        return cfo_action_date;
    }

    public void setCfo_action_date(String cfo_action_date) {
        this.cfo_action_date = cfo_action_date;
    }

    public String getCfo_approved_by() {
        return cfo_approved_by;
    }

    public void setCfo_approved_by(String cfo_approved_by) {
        this.cfo_approved_by = cfo_approved_by;
    }

    public String getCfo_remarks() {
        return cfo_remarks;
    }

    public void setCfo_remarks(String cfo_remarks) {
        this.cfo_remarks = cfo_remarks;
    }

    public String getClient_reimbursable() {
        return client_reimbursable;
    }

    public void setClient_reimbursable(String client_reimbursable) {
        this.client_reimbursable = client_reimbursable;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public String getCustomer_others() {
        return customer_others;
    }

    public void setCustomer_others(String customer_others) {
        this.customer_others = customer_others;
    }

    public String getEmp_remarks() {
        return emp_remarks;
    }

    public void setEmp_remarks(String emp_remarks) {
        this.emp_remarks = emp_remarks;
    }

    public String getFinance_action_date() {
        return finance_action_date;
    }

    public void setFinance_action_date(String finance_action_date) {
        this.finance_action_date = finance_action_date;
    }

    public String getFinance_approved_by() {
        return finance_approved_by;
    }

    public void setFinance_approved_by(String finance_approved_by) {
        this.finance_approved_by = finance_approved_by;
    }

    public String getFinance_remarks() {
        return finance_remarks;
    }

    public void setFinance_remarks(String finance_remarks) {
        this.finance_remarks = finance_remarks;
    }

    public String getGuest_booking() {
        return guest_booking;
    }

    public void setGuest_booking(String guest_booking) {
        this.guest_booking = guest_booking;
    }

    public String getMd_action_date() {
        return md_action_date;
    }

    public void setMd_action_date(String md_action_date) {
        this.md_action_date = md_action_date;
    }

    public String getMd_approved_by() {
        return md_approved_by;
    }

    public void setMd_approved_by(String md_approved_by) {
        this.md_approved_by = md_approved_by;
    }

    public String getMd_remarks() {
        return md_remarks;
    }

    public void setMd_remarks(String md_remarks) {
        this.md_remarks = md_remarks;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getProject_others() {
        return project_others;
    }

    public void setProject_others(String project_others) {
        this.project_others = project_others;
    }

    public String getRm_action_date() {
        return rm_action_date;
    }

    public void setRm_action_date(String rm_action_date) {
        this.rm_action_date = rm_action_date;
    }

    public String getRm_approved_by() {
        return rm_approved_by;
    }

    public void setRm_approved_by(String rm_approved_by) {
        this.rm_approved_by = rm_approved_by;
    }

    public String getRm_remarks() {
        return rm_remarks;
    }

    public void setRm_remarks(String rm_remarks) {
        this.rm_remarks = rm_remarks;
    }

    public String getTravel_billable() {
        return travel_billable;
    }

    public void setTravel_billable(String travel_billable) {
        this.travel_billable = travel_billable;
    }

    public String getTravel_purpose() {
        return travel_purpose;
    }

    public void setTravel_purpose(String travel_purpose) {
        this.travel_purpose = travel_purpose;
    }

    public String getTravel_term() {
        return travel_term;
    }

    public void setTravel_term(String travel_term) {
        this.travel_term = travel_term;
    }

    public String getTreasury_action_date() {
        return treasury_action_date;
    }

    public void setTreasury_action_date(String treasury_action_date) {
        this.treasury_action_date = treasury_action_date;
    }

    public String getTreasury_approved_by() {
        return treasury_approved_by;
    }

    public void setTreasury_approved_by(String treasury_approved_by) {
        this.treasury_approved_by = treasury_approved_by;
    }

    public String getTreasury_remarks() {
        return treasury_remarks;
    }

    public void setTreasury_remarks(String treasury_remarks) {
        this.treasury_remarks = treasury_remarks;
    }

    public String getOut_conveyance_city() {
        return out_conveyance_city;
    }

    public void setOut_conveyance_city(String out_conveyance_city) {
        this.out_conveyance_city = out_conveyance_city;
    }

    public String getOut_city() {
        return out_city;
    }

    public void setOut_city(String out_city) {
        this.out_city = out_city;
    }

    public String getOut_from_city() {
        return out_from_city;
    }

    public void setOut_from_city(String out_from_city) {
        this.out_from_city = out_from_city;
    }

    public String getOut_to_city() {
        return out_to_city;
    }

    public void setOut_to_city(String out_to_city) {
        this.out_to_city = out_to_city;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
   public String getBuhName() {
        return buhName;
    }

    public void setBuhName(String buhName) {
        this.buhName = buhName;
    }

    public String getSbuName() {
        return sbuName;
    }

    public void setSbuName(String sbuName) {
        this.sbuName = sbuName;
    }

      public String getBuh_id() {
        return buh_id;
    }

    public void setBuh_id(String buh_id) {
        this.buh_id = buh_id;
    }

     public String getBuhsName() {
        return buhsName;
    }

    public void setBuhsName(String buhsName) {
        this.buhsName = buhsName;
    }

     public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }
     

    public String getCustomer_name() {
        return customer_name;
    }


    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getSub_group_name() {
        return sub_group_name;
    }

    public void setSub_group_name(String sub_group_name) {
        this.sub_group_name = sub_group_name;
    }
    
    public String getKeyRes() {
        return keyRes;
    }

    public void setKeyRes(String keyRes) {
        this.keyRes = keyRes;
    }

    public String getValueRes() {
        return valueRes;
    }

    public void setValueRes(String valueRes) {
        this.valueRes = valueRes;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getBuhStatus() {
        return buhStatus;
    }

    public void setBuhStatus(String buhStatus) {
        this.buhStatus = buhStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerOthers() {
        return customerOthers;
    }

    public void setCustomerOthers(String customerOthers) {
        this.customerOthers = customerOthers;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public int getEnd_page() {
        return end_page;
    }

    public void setEnd_page(int end_page) {
        this.end_page = end_page;
    }

    public String getFinanceStatus() {
        return financeStatus;
    }

    public void setFinanceStatus(String financeStatus) {
        this.financeStatus = financeStatus;
    }

    public String getMdStatus() {
        return mdStatus;
    }

    public void setMdStatus(String mdStatus) {
        this.mdStatus = mdStatus;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectOthers() {
        return projectOthers;
    }

    public void setProjectOthers(String projectOthers) {
        this.projectOthers = projectOthers;
    }

    public String getRmStatus() {
        return rmStatus;
    }

    public void setRmStatus(String rmStatus) {
        this.rmStatus = rmStatus;
    }

    public int getStart_page() {
        return start_page;
    }

    public void setStart_page(int start_page) {
        this.start_page = start_page;
    }

    public String getTpId() {
        return tpId;
    }

    public void setTpId(String tpId) {
        this.tpId = tpId;
    }

    public String getTpReferenceId() {
        return tpReferenceId;
    }

    public void setTpReferenceId(String tpReferenceId) {
        this.tpReferenceId = tpReferenceId;
    }

    public Date getTpRequestedDate() {
        return tpRequestedDate;
    }

    public void setTpRequestedDate(Date tpRequestedDate) {
        this.tpRequestedDate = tpRequestedDate;
    }

    public String getTpStatus() {
        return tpStatus;
    }

    public void setTpStatus(String tpStatus) {
        this.tpStatus = tpStatus;
    }

    public String getTpWorkFlowId() {
        return tpWorkFlowId;
    }

    public void setTpWorkFlowId(String tpWorkFlowId) {
        this.tpWorkFlowId = tpWorkFlowId;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getTreasuryStatus() {
        return treasuryStatus;
    }

    public void setTreasuryStatus(String treasuryStatus) {
        this.treasuryStatus = treasuryStatus;
    }

    public String getWorkFlow() {
        return workFlow;
    }

    public void setWorkFlow(String workFlow) {
        this.workFlow = workFlow;
    }

    public String getChangeBuh() {
        return changeBuh;
    }

    public void setChangeBuh(String changeBuh) {
        this.changeBuh = changeBuh;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getDeviation() {
        return deviation;
    }

    public void setDeviation(String deviation) {
        this.deviation = deviation;
    }

    public String getEmpBandId() {
        return empBandId;
    }

    public void setEmpBandId(String empBandId) {
        this.empBandId = empBandId;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getSalesGroupId() {
        return salesGroupId;
    }

    public void setSalesGroupId(String salesGroupId) {
        this.salesGroupId = salesGroupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTpWorkFlowName() {
        return tpWorkFlowName;
    }

    public void setTpWorkFlowName(String tpWorkFlowName) {
        this.tpWorkFlowName = tpWorkFlowName;
    }

    public String getVetri() {
        return vetri;
    }

    public void setVetri(String vetri) {
        this.vetri = vetri;
    }

    public String getTravelActionCode() {
        return travelActionCode;
    }

    public void setTravelActionCode(String travelActionCode) {
        this.travelActionCode = travelActionCode;
    }

    public Date getAdminApprovedDate() {
        return adminApprovedDate;
    }

    public void setAdminApprovedDate(Date adminApprovedDate) {
        this.adminApprovedDate = adminApprovedDate;
    }

    public Date getBuhApprovedDate() {
        return buhApprovedDate;
    }

    public void setBuhApprovedDate(Date buhApprovedDate) {
        this.buhApprovedDate = buhApprovedDate;
    }

    public Date getCfoApprovedDate() {
        return cfoApprovedDate;
    }

    public void setCfoApprovedDate(Date cfoApprovedDate) {
        this.cfoApprovedDate = cfoApprovedDate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getFinApprovedDate() {
        return finApprovedDate;
    }

    public void setFinApprovedDate(Date finApprovedDate) {
        this.finApprovedDate = finApprovedDate;
    }

    public Date getMdApprovedDate() {
        return mdApprovedDate;
    }

    public void setMdApprovedDate(Date mdApprovedDate) {
        this.mdApprovedDate = mdApprovedDate;
    }

    public Date getRmApprovedDate() {
        return rmApprovedDate;
    }

    public void setRmApprovedDate(Date rmApprovedDate) {
        this.rmApprovedDate = rmApprovedDate;
    }

    public Date getTpEndDate() {
        return tpEndDate;
    }

    public void setTpEndDate(Date tpEndDate) {
        this.tpEndDate = tpEndDate;
    }

    public Date getTpStartDate() {
        return tpStartDate;
    }

    public void setTpStartDate(Date tpStartDate) {
        this.tpStartDate = tpStartDate;
    }

    public Date getTreasuryApprovedDate() {
        return treasuryApprovedDate;
    }

    public void setTreasuryApprovedDate(Date treasuryApprovedDate) {
        this.treasuryApprovedDate = treasuryApprovedDate;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getApproveType() {
        return approveType;
    }

    public void setApproveType(String approveType) {
        this.approveType = approveType;
    }

    public Date getTravel_end_date() {
        return travel_end_date;
    }

    public void setTravel_end_date(Date travel_end_date) {
        this.travel_end_date = travel_end_date;
    }

    public Date getTravel_requested_date() {
        return travel_requested_date;
    }

    public void setTravel_requested_date(Date travel_requested_date) {
        this.travel_requested_date = travel_requested_date;
    }

    public Date getTravel_start_date() {
        return travel_start_date;
    }

    public void setTravel_start_date(Date travel_start_date) {
        this.travel_start_date = travel_start_date;
    }


    public String getTpRequestorId() {
        return tpRequestorId;
    }

    public void setTpRequestorId(String tpRequestorId) {
        this.tpRequestorId = tpRequestorId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRejectCode() {
        return rejectCode;
    }

    public void setRejectCode(String rejectCode) {
        this.rejectCode = rejectCode;
    }

    public String getRequestorPracticeGroup() {
        return requestorPracticeGroup;
    }

    public void setRequestorPracticeGroup(String requestorPracticeGroup) {
        this.requestorPracticeGroup = requestorPracticeGroup;
    }

     public String getBuhid() {
        return buhid;
    }

    public void setBuhid(String buhid) {
        this.buhid = buhid;
    }

    public String getFirstNextLevel() {
        return firstNextLevel;
    }

    public void setFirstNextLevel(String firstNextLevel) {
        this.firstNextLevel = firstNextLevel;
    }

    public String getSecondNextLevel() {
        return secondNextLevel;
    }

    public void setSecondNextLevel(String secondNextLevel) {
        this.secondNextLevel = secondNextLevel;
    }

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getAlternateMailId() {
        return alternateMailId;
    }

    public void setAlternateMailId(String alternateMailId) {
        this.alternateMailId = alternateMailId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

 
    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getEcnrStatus() {
        return ecnrStatus;
    }

    public void setEcnrStatus(String ecnrStatus) {
        this.ecnrStatus = ecnrStatus;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

 
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public String getMealPreference() {
        return mealPreference;
    }

    public void setMealPreference(String mealPreference) {
        this.mealPreference = mealPreference;
    }

    public String getNominee() {
        return nominee;
    }

    public void setNominee(String nominee) {
        this.nominee = nominee;
    }

    public String getOfficialMailId() {
        return officialMailId;
    }

    public void setOfficialMailId(String officialMailId) {
        this.officialMailId = officialMailId;
    }


    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFurtherAdvance() {
        return furtherAdvance;
    }

    public void setFurtherAdvance(String furtherAdvance) {
        this.furtherAdvance = furtherAdvance;
    }

    public String getFurtherAdvanceRemarks() {
        return furtherAdvanceRemarks;
    }

    public void setFurtherAdvanceRemarks(String furtherAdvanceRemarks) {
        this.furtherAdvanceRemarks = furtherAdvanceRemarks;
    }

    public String getSystemForAdvance() {
        return systemForAdvance;
    }

    public void setSystemForAdvance(String systemForAdvance) {
        this.systemForAdvance = systemForAdvance;
    }

    public String getTpIdForAdvance() {
        return tpIdForAdvance;
    }

    public void setTpIdForAdvance(String tpIdForAdvance) {
        this.tpIdForAdvance = tpIdForAdvance;
    }

    public String getAdvanceStatus() {
        return advanceStatus;
    }

    public void setAdvanceStatus(String advanceStatus) {
        this.advanceStatus = advanceStatus;
    }

    public String getAdvanceWorkFlowId() {
        return advanceWorkFlowId;
    }

    public void setAdvanceWorkFlowId(String advanceWorkFlowId) {
        this.advanceWorkFlowId = advanceWorkFlowId;
    }

    public String getAdvanceWorkFlowName() {
        return advanceWorkFlowName;
    }

    public void setAdvanceWorkFlowName(String advanceWorkFlowName) {
        this.advanceWorkFlowName = advanceWorkFlowName;
    }

    public String getAdvancePreviousStatus() {
        return advancePreviousStatus;
    }

    public void setAdvancePreviousStatus(String advancePreviousStatus) {
        this.advancePreviousStatus = advancePreviousStatus;
    }

    public String getAdvanceTpId() {
        return advanceTpId;
    }

    public void setAdvanceTpId(String advanceTpId) {
        this.advanceTpId = advanceTpId;
    }

    public Date getTravelAdvanceEndDate() {
        return travelAdvanceEndDate;
    }

    public void setTravelAdvanceEndDate(Date travelAdvanceEndDate) {
        this.travelAdvanceEndDate = travelAdvanceEndDate;
    }

    public Date getTravelAdvanceRequestedDate() {
        return travelAdvanceRequestedDate;
    }

    public void setTravelAdvanceRequestedDate(Date travelAdvanceRequestedDate) {
        this.travelAdvanceRequestedDate = travelAdvanceRequestedDate;
    }

    public String getAdvanceRemarks() {
        return advanceRemarks;
    }

    public void setAdvanceRemarks(String advanceRemarks) {
        this.advanceRemarks = advanceRemarks;
    }

    public String getTpRequestedDateDisplay() {
        return tpRequestedDateDisplay;
    }

    public void setTpRequestedDateDisplay(String tpRequestedDateDisplay) {
        this.tpRequestedDateDisplay = tpRequestedDateDisplay;
    }

    public String getExportDesc() {
        return exportDesc;
    }

    public void setExportDesc(String exportDesc) {
        this.exportDesc = exportDesc;
    }

    public String getChange_buh_id() {
        return change_buh_id;
    }

    public void setChange_buh_id(String change_buh_id) {
        this.change_buh_id = change_buh_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmpManager() {
        return empManager;
    }

    public void setEmpManager(String empManager) {
        this.empManager = empManager;
    }

    public String getPrintAction() {
        return printAction;
    }

    public void setPrintAction(String printAction) {
        this.printAction = printAction;
    }

    public String getBuh_change() {
        return buh_change;
    }

    public void setBuh_change(String buh_change) {
        this.buh_change = buh_change;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

}
