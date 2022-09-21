/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dto;

import com.ibatis.sqlmap.engine.mapping.sql.Sql;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 14355
 */
public class RequestorDTO {
//Session Values
    private String empId;
    private String moduleId;
    private String id;
    private String employeeId;
    private String employeeName;
    private List<RequestorDTO> feedbackValues;
    private List<RequestorDTO> improvementvalues;
    private List<RequestorDTO> processValues;
    private List<RequestorDTO> focusValues;
    private List<RequestorDTO> evalStatusValues;
    private List<RequestorDTO> reqStatusValues;
    private RequestorDTO empInfo;
    private String configKey;
    private String configValue;
//    Form Values
    private String empLocation;
    private String empCurrentLocation;
    private Date reqDate;
    private String feedbackType;
    private String description;
    private String parentId;
    private String focusFilter;
    private String status;
    private String referenceId;
    private String improvementRequest;
    private String processArea;
    private String focusArea;
    private String justification;
    private Date reqRaisedDate;
    private int month;
    private Integer refId;
    private String mailName;
    private String mailSubName;
    private String rootCauseAnalysis;
    
    //New Request  action Button
    private Integer submitButton;
    
    //Mail Data
    private String empWorkMail;
    private String rmWorkMail;
    private String rmName;
    
//    Update Quality SDmin Fileds --- QualityCustomerView Form
    private String evalDescription;
    private String evalStatus;
    private Date expClosureDate;
    private String reqStatus;
    private Date reqClosedDate;
    private String qualtyRemarks;
    private Integer qualityId;
    private String list;
    private String resposerName;
    private String resposerId;
    private String requestorEmployeeID;
    
    public Date qualClosedDate;

    public Date getQualClosedDate() {
        return qualClosedDate;
    }

    public void setQualClosedDate(Date qualClosedDate) {
        this.qualClosedDate = qualClosedDate;
    }
    

    public String getResposerId() {
        return resposerId;
    }

    public void setResposerId(String resposerId) {
        this.resposerId = resposerId;
    }

    public String getResposerName() {
        return resposerName;
    }

    public void setResposerName(String resposerName) {
        this.resposerName = resposerName;
    }


    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
    
    public Integer getQualityId() {
        return qualityId;
    }

    public void setQualityId(Integer qualityId) {
        this.qualityId = qualityId;
    }
    
    
    public String getEvalDescription() {
        return evalDescription;
    }

    public void setEvalDescription(String evalDescription) {
        this.evalDescription = evalDescription;
    }

    public String getEvalStatus() {
        return evalStatus;
    }

    public void setEvalStatus(String evalStatus) {
        this.evalStatus = evalStatus;
    }

    public Date getReqClosedDate() {
        return reqClosedDate;
    }

    public void setReqClosedDate(Date reqClosedDate) {
        this.reqClosedDate = reqClosedDate;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }


    public String getQualtyRemarks() {
        return qualtyRemarks;
    }

    public void setQualtyRemarks(String qualtyRemarks) {
        this.qualtyRemarks = qualtyRemarks;
    }


    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }
    
    public List<RequestorDTO> getEvalStatusValues() {
        return evalStatusValues;
    }

    public void setEvalStatusValues(List<RequestorDTO> evalStatusValues) {
        this.evalStatusValues = evalStatusValues;
    }

    public List<RequestorDTO> getReqStatusValues() {
        return reqStatusValues;
    }

    public void setReqStatusValues(List<RequestorDTO> reqStatusValues) {
        this.reqStatusValues = reqStatusValues;
    }

    public String getEmpWorkMail() {
        return empWorkMail;
    }

    public void setEmpWorkMail(String empWorkMail) {
        this.empWorkMail = empWorkMail;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getRmWorkMail() {
        return rmWorkMail;
    }

    public void setRmWorkMail(String rmWorkMail) {
        this.rmWorkMail = rmWorkMail;
    }

    public Integer getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(Integer submitButton) {
        this.submitButton = submitButton;
    }

    public Date getExpClosureDate() {
        return expClosureDate;
    }

    public void setExpClosureDate(Date expClosureDate) {
        this.expClosureDate = expClosureDate;
    }



   
  

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }
    
    
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    
    //For File Upload
    private MultipartFile fileName;
    public String file;
    public String originalFileName;
    public String contentType;
    public String moduleName;
    public String requestId;
    public String referenceName;

    
    //Report Values
    
    public String selectedValue;
    public String employee_id;

    public Date getReqRaisedDate() {
        return reqRaisedDate;
    }

    public void setReqRaisedDate(Date reqRaisedDate) {
        this.reqRaisedDate = reqRaisedDate;
    }
    
    
    

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }


    

    

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
    
    
    
    public String getEmpId() {
        return empId;
    }

 

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }
    

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

       public MultipartFile getFileName() {
        return fileName;
    }

    public void setFileName(MultipartFile fileName) {
        this.fileName = fileName;
    }


    public String getFocusArea() {
        return focusArea;
    }

    public void setFocusArea(String focusArea) {
        this.focusArea = focusArea;
    }

    public String getImprovementRequest() {
        return improvementRequest;
    }

    public void setImprovementRequest(String improvementRequest) {
        this.improvementRequest = improvementRequest;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getProcessArea() {
        return processArea;
    }

    public void setProcessArea(String processArea) {
        this.processArea = processArea;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFocusFilter() {
        return focusFilter;
    }

    public void setFocusFilter(String focusFilter) {
        this.focusFilter = focusFilter;
    }

//    public String referenceNo;
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<RequestorDTO> getFeedbackValues() {
        return feedbackValues;
    }

    public void setFeedbackValues(List<RequestorDTO> feedbackValues) {
        this.feedbackValues = feedbackValues;
    }

    public List<RequestorDTO> getFocusValues() {
        return focusValues;
    }

    public void setFocusValues(List<RequestorDTO> focusValues) {
        this.focusValues = focusValues;
    }

    public List<RequestorDTO> getImprovementvalues() {
        return improvementvalues;
    }

    public void setImprovementvalues(List<RequestorDTO> improvementvalues) {
        this.improvementvalues = improvementvalues;
    }

    public List<RequestorDTO> getProcessValues() {
        return processValues;
    }

    public void setProcessValues(List<RequestorDTO> processValues) {
        this.processValues = processValues;
    }

    public String getEmpCurrentLocation() {
        return empCurrentLocation;
    }

    public void setEmpCurrentLocation(String empCurrentLocation) {
        this.empCurrentLocation = empCurrentLocation;
    }


//    
    public String getEmpLocation() {
        return empLocation;
    }

    public void setEmpLocation(String empLocation) {
        this.empLocation = empLocation;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public RequestorDTO getEmpInfo() {
        return empInfo;
    }

    public void setEmpInfo(RequestorDTO empInfo) {
        this.empInfo = empInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
//public FileDTO(MultipartFile fileName, String originalFileName, String contentType, Integer requestId) {
//        this.fileName = fileName;
//        this.originalFileName = originalFileName;
//        this.contentType = contentType;
//        this.requestId = requestId;
//    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getMailSubName() {
        return mailSubName;
    }

    public void setMailSubName(String mailSubName) {
        this.mailSubName = mailSubName;
    }

    public String getRootCauseAnalysis() {
        return rootCauseAnalysis;
    }

    public void setRootCauseAnalysis(String rootCauseAnalysis) {
        this.rootCauseAnalysis = rootCauseAnalysis;
    }

    public String getRequestorEmployeeID() {
        return requestorEmployeeID;
    }

    public void setRequestorEmployeeID(String requestorEmployeeID) {
        this.requestorEmployeeID = requestorEmployeeID;
    }
    
    

}
