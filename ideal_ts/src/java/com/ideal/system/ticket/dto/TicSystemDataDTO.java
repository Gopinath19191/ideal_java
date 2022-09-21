package com.ideal.system.ticket.dto;

import org.springframework.web.multipart.MultipartFile;

public class TicSystemDataDTO {

    private int start_page;
    private int end_page;
    private int page;
    private int recordCount;
    private int id;
    private int employee_idd;
    private String mailId;
    private String requestorName;
    private String parent_id;
    private String highest_ref;
    private String statusKey;
    private String statusValue;
    private String responseId;
    private MultipartFile attachment_multipartfile_path;
    private String fullFileName;
    private String attachment_full_name;
    private String priority;
    private String issue_type;
    private String application_area;
    private String subject;
    private String created_date;
    private String status;
    private String replied_date;
    private String closed_date;
    private String time_taken;
    private String location;
    private String contact_no;
    private String system;
    private String empNumber;
    private String empName;
    private String response;
    private MultipartFile file;
    private int maxId;
    private String attachment_file_path;
    private String configuration_key;
    private String configuration_value;
    private String mail_cc_id;
    private String empId;
    private String employeeName;
    private String employeeNumber;
    private String finalStatus;
    private String assignEngineerName;
    private String supportId;
    private String supportName;
    private String support_type;
    private String userStatus;    
    private String subUnit;
    private String subUnitId;
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
    
    public void setSubUnitId(String subUnitId) {
        this.subUnitId = subUnitId;
    }

    public String getSubUnitId() {
        return subUnitId;
    }
    private String sub_Unit_Name;
    private String supportUnit;
    private String subSupportUnit;
    
    private String sub_unit;

    public void setSub_unit(String sub_unit) {
        this.sub_unit = sub_unit;
    }

    public String getSub_unit() {
        return sub_unit;
    }

    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit;
    }

    
    public void setSub_Unit_Name(String sub_Unit_Name) {
        this.sub_Unit_Name = sub_Unit_Name;
    }

    public void setSupportUnit(String supportUnit) {
        this.supportUnit = supportUnit;
    }

    public void setSubSupportUnit(String subSupportUnit) {
        this.subSupportUnit = subSupportUnit;
    }

    public String getSubUnit() {
        return subUnit;
    }

    public String getSub_Unit_Name() {
        return sub_Unit_Name;
    }

    public String getSupportUnit() {
        return supportUnit;
    }

    public String getSubSupportUnit() {
        return subSupportUnit;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    public String getUserStatus() {
        return userStatus;
    }
    public void setSupport_type(String support_type) {
        this.support_type = support_type;
    }

    public String getSupport_type() {
        return support_type;
    }
    public void setSupportId(String supportId) {
        this.supportId = supportId;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public String getSupportId() {
        return supportId;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setAssignEngineerName(String assignEngineerName) {
        this.assignEngineerName = assignEngineerName;
    }

    public String getAssignEngineerName() {
        return assignEngineerName;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }
 
    public String getEmpId() {
        return empId;
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

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMail_cc_id() {
        return mail_cc_id;
    }

    public void setMail_cc_id(String mail_cc_id) {
        this.mail_cc_id = mail_cc_id;
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

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public int getEmployee_idd() {
        return employee_idd;
    }

    public void setEmployee_idd(int employee_idd) {
        this.employee_idd = employee_idd;
    }
    

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
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

    public String getHighest_ref() {
        return highest_ref;
    }

    public void setHighest_ref(String highest_ref) {
        this.highest_ref = highest_ref;
    }
    

    public MultipartFile getAttachment_multipartfile_path() {
        return attachment_multipartfile_path;
    }

    public void setAttachment_multipartfile_path(MultipartFile attachment_multipartfile_path) {
        this.attachment_multipartfile_path = attachment_multipartfile_path;
    }
    

    public String getAttachment_file_path() {
        return attachment_file_path;
    }

    public void setAttachment_file_path(String attachment_file_path) {
        this.attachment_file_path = attachment_file_path;
    }
    public String getAttachment_full_name() {
        return attachment_full_name;
    }

    public void setAttachment_full_name(String attachment_full_name) {
        this.attachment_full_name = attachment_full_name;
    }
    private int uniqueReqId;

    public int getUniqueReqId() {
        return uniqueReqId;
    }

    public void setUniqueReqId(int uniqueReqId) {
        this.uniqueReqId = uniqueReqId;
    }
    
    public String getFullFileName() {
        return fullFileName;
    }

    public void setFullFileName(String fullFileName) {
        this.fullFileName = fullFileName;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    private String refId;

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
    private String ref_no;

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }
    
    public String getTime_taken() {
        return time_taken;
    }

    public String getClosed_date() {
        return closed_date;
    }

    public void setClosed_date(String closed_date) {
        this.closed_date = closed_date;
    }

    public void setTime_taken(String time_taken) {
        this.time_taken = time_taken;
    }
    

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
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
    public int highest_refNo;

    public int getHighest_refNo() {
        return highest_refNo;
    }

    public void setHighest_refNo(int highest_refNo) {
        this.highest_refNo = highest_refNo;
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
