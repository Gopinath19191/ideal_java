/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.admin.ticket.dto;

import org.springframework.web.multipart.MultipartFile;

public class TicAdminDataDTO {

    private int start_page;
    private int end_page;
    private int page;
    private int recordCount;
    private int filterIdCount;
    private int id;
    private String refId;
    private String responseConv;
    private String fullFileName;
    private String attachment_file_path;
    private String attachment_full_name;
    private String ref_no;
    private String priority;
    private String issue_type;
    private String application_area;
    private String subject;
    private String created_date;
    private String status;
    private String replied_date;
    private String resolution_time;
    private String response_time;
    private String closed_date;
    private String time_taken;
    private String location;
    private String contact_no;
    private String system;
    private String empNumber;
    private String empName;
    private String response;
    private MultipartFile file;
    private String user_created;
    private String admin_created;
    private String mail_cc_id;
    private String employee_idd;
    private String employeeNumber;
    private String assignEngineerNumber;
    private int    assignEngineerId;
    private String assignEngineerName;
    private String engineerId;
    private String admin_ref_no;
    private String appKey;
    private String appValue;
    private String assignEmpId;
    private String sadmin;
    private String sub_Unit_Name;    
    private String subUnitId;
    private String structid;
    private String empId;
    private String criteria;
    private String refVal;
    private String unit_id;
    private String sub_unit_id;
    private String status_id;
    private String issue_id;
    private String rating;
    private String ip_address;
    private String lastInsertId;

    public String getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(String lastInsertId) {
        this.lastInsertId = lastInsertId;
    }
    
    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
    
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }
    
    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }
    
    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getSub_unit_id() {
        return sub_unit_id;
    }

    public void setSub_unit_id(String sub_unit_id) {
        this.sub_unit_id = sub_unit_id;
    }
    

    public String getRefVal() {
        return refVal;
    }

    public void setRefVal(String refVal) {
        this.refVal = refVal;
    }
    
    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }   

    public String getStructid() {
        return structid;
    }

    public void setStructid(String structid) {
        this.structid = structid;
    }

    
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
    public String getSadmin() {
        return sadmin;
    }

    public void setSadmin(String sadmin) {
        this.sadmin = sadmin;
    }
    
    public void setSubUnitId(String subUnitId) {
        this.subUnitId = subUnitId;
    }

    public void setPlanned_effort(String planned_effort) {
        this.planned_effort = planned_effort;
    }

    public void setActual_effort(String actual_effort) {
        this.actual_effort = actual_effort;
    }

    public String getSubUnitId() {
        return subUnitId;
    }

    public String getPlanned_effort() {
        return planned_effort;
    }

    public String getActual_effort() {
        return actual_effort;
    }
    
    public String getSub_Unit_Name() {
        return sub_Unit_Name;
    }

    public void setSub_Unit_Name(String sub_Unit_Name) {
        this.sub_Unit_Name = sub_Unit_Name;
    }
    
    public void setAssignEmpId(String assignEmpId) {
        this.assignEmpId = assignEmpId;
    }

    public String getAssignEmpId() {
        return assignEmpId;
    }

    public void setSupportId(String supportId) {
        this.supportId = supportId;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public void setSupport_type(String support_type) {
        this.support_type = support_type;
    }
    private String lastappVal;
    
    private int empidd;
    private String statusKey;
    private String statusValue;
    private String configuration_key;
    private String configuration_value;
    private String conv_no;
    private String empFullName;
    private String supportId;
    private String supportName;
    private String support_type;
    
    private String sub_unit;

    public void setSub_unit(String sub_unit) {
        this.sub_unit = sub_unit;
    }

    public String getSub_unit() {
        return sub_unit;
    }
    

    public String getSupportId() {
        return supportId;
    }

    public String getSupportName() {
        return supportName;
    }

    public String getSupport_type() {
        return support_type;
    }
    
    public String getResponse_time() {
        return response_time;
    }

    public void setResponse_time(String response_time) {
        this.response_time = response_time;
    }
    
    private String planned_effort;
    private String actual_effort;
    
    public String getplanned_effort() {
        return planned_effort;
    }

    public void setplanned_effort(String planned_effort) {
        this.planned_effort = planned_effort;
    }
    
    public String getactual_effort() {
        return actual_effort;
    }

    public void setactual_effort(String actual_effort) {
        this.actual_effort = actual_effort;
    }
    public String getResolution_time() {
        return resolution_time;
    }

    public void setResolution_time(String resolution_time) {
        this.resolution_time = resolution_time;
    }

    public String getLastappVal() {
        return lastappVal;
    }

    public void setLastappVal(String lastappVal) {
        this.lastappVal = lastappVal;
    }


    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppValue() {
        return appValue;
    }

    public void setAppValue(String appValue) {
        this.appValue = appValue;
    }

    public String getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }
    
    public String getAssignEngineerName() {
        return assignEngineerName;
    }

    public void setAssignEngineerName(String assignEngineerName) {
        this.assignEngineerName = assignEngineerName;
    }
    
    public String getAssignEngineerNumber() {
        return assignEngineerNumber;
    }

    public void setAssignEngineerNumber(String assignEngineerNumber) {
        this.assignEngineerNumber = assignEngineerNumber;
    }

    public int getAssignEngineerId() {
        return assignEngineerId;
    }

    public void setAssignEngineerId(int assignEngineerId) {
        this.assignEngineerId = assignEngineerId;
    }

    public String getAdmin_ref_no() {
        return admin_ref_no;
    }

    public void setAdmin_ref_no(String admin_ref_no) {
        this.admin_ref_no = admin_ref_no;
    }

    public String getResponderName() {
        return responderName;
    }

    public void setResponderName(String responderName) {
        this.responderName = responderName;
    }
     private String  responderName;
             

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployee_idd() {
        return employee_idd;
    }

    public void setEmployee_idd(String employee_idd) {
        this.employee_idd = employee_idd;
    }

    public String getMail_cc_id() {
        return mail_cc_id;
    }

    public void setMail_cc_id(String mail_cc_id) {
        this.mail_cc_id = mail_cc_id;
    }

    public String getUser_created() {
        return user_created;
    }

    public void setUser_created(String user_created) {
        this.user_created = user_created;
    }

    public String getAdmin_created() {
        return admin_created;
    }

    public void setAdmin_created(String admin_created) {
        this.admin_created = admin_created;
    }

    public String getConv_no() {
        return conv_no;
    }

    public void setConv_no(String conv_no) {
        this.conv_no = conv_no;
    }
    
	
	public String getEmpFullName() {
        return empFullName;
    }

    public void setEmpFullName(String empFullName) {
        this.empFullName = empFullName;
    }

    public String getResponseConv() {
        return responseConv;
    }

    public void setResponseConv(String responseConv) {
        this.responseConv = responseConv;
    }


    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }
    

    public String getConfiguration_key() {
        return configuration_key;
    }

    public void setConfiguration_key(String configuration_key) {
        this.configuration_key = configuration_key;
    }

    public String getConfiguration_value() {
        return configuration_value;
    }

    public void setConfiguration_value(String configuration_value) {
        this.configuration_value = configuration_value;
    }
    

    public String getStatusKey() {
        return statusKey;
    }

    public void setStatusKey(String statusKey) {
        this.statusKey = statusKey;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
   

    public int getFilterIdCount() {
        return filterIdCount;
    }

    public void setFilterIdCount(int filterIdCount) {
        this.filterIdCount = filterIdCount;
    }

    public String getAttachment_full_name() {
        return attachment_full_name;
    }

    public void setAttachment_full_name(String attachment_full_name) {
        this.attachment_full_name = attachment_full_name;
    }
    private String mailId;

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getAttachment_file_path() {
        return attachment_file_path;
    }

    public void setAttachment_file_path(String attachment_file_path) {
        this.attachment_file_path = attachment_file_path;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClosed_date() {
        return closed_date;
    }

    public void setClosed_date(String closed_date) {
        this.closed_date = closed_date;
    }

    public String getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(String time_taken) {
        this.time_taken = time_taken;
    }

    public int getEmpidd() {
        return empidd;
    }

    public void setEmpidd(int empidd) {
        this.empidd = empidd;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIssue_type() {
        return issue_type;
    }

    public void setIssue_type(String issue_type) {
        this.issue_type = issue_type;
    }

    public String getApplication_area() {
        return application_area;
    }

    public void setApplication_area(String application_area) {
        this.application_area = application_area;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReplied_date() {
        return replied_date;
    }

    public void setReplied_date(String replied_date) {
        this.replied_date = replied_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public int getStart_page() {
        return start_page;
    }

    public void setStart_page(int start_page) {
        this.start_page = start_page;
    }

    public int getEnd_page() {
        return end_page;
    }

    public void setEnd_page(int end_page) {
        this.end_page = end_page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
}
