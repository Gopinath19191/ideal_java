/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.travelplan.dto;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 15065
 */
public class LocalConveyanceDto implements Serializable {
   private String empId;
   private String employeeNumber;
   private String employeeName;
   private String designationId;
   private String businessgroupName;
   private String practicegroupName;
   private String bandId;
   private String bandName;
   private String cityName;
   private String designationName;
   private String configKey;
   private String configValue;
   private String parentId;
   private String projectId;
   private String projectName;
   private String projectCode;
   private String cityId;
   private String travelPoints;
   private String addressId;
   private String tpRefId;
   private String rm_id;
   private String client_reimbursable;
   //Form Submit
   private Date reqDate;
   private String empName;
   private String guestBooking;
   private String projectTravel;
   private String project;
   private String travelPurpose;
   private String city;
   private String cityOthers;
   private String onwardPickUpPoint;
   private Date onwardPickUpDate;
   private String onwardPickUpTime;
   private String onwardPickupDoorNo;
   private String onwardPickupStreetName;
   private String onwardPickupArea;
   private String onwardPickupLandMark;
   private String onwardDropJourney;
   private String onwardDropDoorNo;
   private String onwardDropStreetName;
   private String onwardDropArea;
   private String onwardDropLandMark;
   private String returnCab ;
   private String returnPickUpPoint;
   private Date returnPickUpDate;
   private String returnPickUpTime;
   private String returnPickupDoorNo;
   private String returnPickupStreetName;
   private String returnPickupArea;
   private String returnPickupLandMark;
   private String returnDropJourney;
   private String returnDropDoorNo;
   private String returnDropStreetName;
   private String returnDropArea;
   private String returnDropLandMark;
   private String mobileNumber;
   private String alternateMobileNumber;
   private String saveBtn;
   private String submitBtn;
   private String cancelBtn;
   private String lastLcId; // Last Insert Local Conveyance Id
   private String workFlowId; // WorkFlow Id
   private String status; // Refer where the request is
   private String lastInsertId; // Last Inser Id from tp_master
   private String tpReferenceId; // Tp Reference Id
   private String localConveyanceKeyword; // Tp Reference Id
   private String tpMasterId; //Primary Key of tp_master table
   private String newLCNumber; //Newly generted LC number
   private String projectOthers; //Newly generted LC number

   //Approver Remarks,ActionDate,ApprovedId
   private String rmRemarks;
   private String rmApprovedId;
   private String rmActionDate;
   private String rmApproverName;
   private String financeRemarks;
   private String financeApprovedId;
   private String financeActionDate;
   private String financeApproverName;
   private String cfoRemarks;
   private String cfoApprovedId;
   private String cfoActionDate;
   private String cfoName;
   private String adminRemarks;
   private String adminApprovedId;
   private String adminActionDate;
   private String adminApproverName;
   private String guestName;
   private String tpUniqueId;
   private String tpWorkFlowId;

   private String system;
   private String remarks;
   private String approveAction;
   private String sendBackAction;
   private String retuenPickUpDate;
   private String previous_status;
   private String last_id;
   private String employee_id;
   private String mail_status;
   private String currentStatus;
   private String workflow_id;
   private String cancel_remarks;
   private String saveAction;
   private String submitAction;
   private String cancelAction;
   private String reqRemarks;
   private String remarks_lc;

    public String getCancelAction() {
        return cancelAction;
    }

    public void setCancelAction(String cancelAction) {
        this.cancelAction = cancelAction;
    }

    public String getSaveAction() {
        return saveAction;
    }

    public void setSaveAction(String saveAction) {
        this.saveAction = saveAction;
    }

    public String getSubmitAction() {
        return submitAction;
    }

    public void setSubmitAction(String submitAction) {
        this.submitAction = submitAction;
    }

    public String getCancel_remarks() {
        return cancel_remarks;
    }

    public void setCancel_remarks(String cancel_remarks) {
        this.cancel_remarks = cancel_remarks;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getWorkflow_id() {
        return workflow_id;
    }

    public void setWorkflow_id(String workflow_id) {
        this.workflow_id = workflow_id;
    }

    public String getMail_status() {
        return mail_status;
    }

    public void setMail_status(String mail_status) {
        this.mail_status = mail_status;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getLast_id() {
        return last_id;
    }

    public void setLast_id(String last_id) {
        this.last_id = last_id;
    }

    public String getTpRefId() {
        return tpRefId;
    }

    public void setTpRefId(String tpRefId) {
        this.tpRefId = tpRefId;
    }

    public String getRetuenPickUpDate() {
        return retuenPickUpDate;
    }

    public void setRetuenPickUpDate(String retuenPickUpDate) {
        this.retuenPickUpDate = retuenPickUpDate;
    }

    public String getPrevious_status() {
        return previous_status;
    }

    public void setPrevious_status(String previous_status) {
        this.previous_status = previous_status;
    }

    public String getReternPickUpDate() {
        return retuenPickUpDate;
    }

    public void setReternPickUpDate(String retuenPickUpDate) {
        this.retuenPickUpDate = retuenPickUpDate;
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
   
    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBusinessgroupName() {
        return businessgroupName;
    }

    public void setBusinessgroupName(String businessgroupName) {
        this.businessgroupName = businessgroupName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getPracticegroupName() {
        return practicegroupName;
    }

    public void setPracticegroupName(String practicegroupName) {
        this.practicegroupName = practicegroupName;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getTravelPoints() {
        return travelPoints;
    }

    public void setTravelPoints(String travelPoints) {
        this.travelPoints = travelPoints;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAlternateMobileNumber() {
        return alternateMobileNumber;
    }

    public void setAlternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }

    public String getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(String cancelBtn) {
        this.cancelBtn = cancelBtn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGuestBooking() {
        return guestBooking;
    }

    public void setGuestBooking(String guestBooking) {
        this.guestBooking = guestBooking;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOnwardDropArea() {
        return onwardDropArea;
    }

    public void setOnwardDropArea(String onwardDropArea) {
        this.onwardDropArea = onwardDropArea;
    }

    public String getOnwardDropDoorNo() {
        return onwardDropDoorNo;
    }

    public void setOnwardDropDoorNo(String onwardDropDoorNo) {
        this.onwardDropDoorNo = onwardDropDoorNo;
    }

    public String getOnwardDropJourney() {
        return onwardDropJourney;
    }

    public void setOnwardDropJourney(String onwardDropJourney) {
        this.onwardDropJourney = onwardDropJourney;
    }

    public String getOnwardDropLandMark() {
        return onwardDropLandMark;
    }

    public void setOnwardDropLandMark(String onwardDropLandMark) {
        this.onwardDropLandMark = onwardDropLandMark;
    }

    public String getOnwardDropStreetName() {
        return onwardDropStreetName;
    }

    public void setOnwardDropStreetName(String onwardDropStreetName) {
        this.onwardDropStreetName = onwardDropStreetName;
    }

    public String getOnwardPickUpPoint() {
        return onwardPickUpPoint;
    }

    public void setOnwardPickUpPoint(String onwardPickUpPoint) {
        this.onwardPickUpPoint = onwardPickUpPoint;
    }

    public String getOnwardPickUpTime() {
        return onwardPickUpTime;
    }

    public void setOnwardPickUpTime(String onwardPickUpTime) {
        this.onwardPickUpTime = onwardPickUpTime;
    }

    public String getOnwardPickupArea() {
        return onwardPickupArea;
    }

    public void setOnwardPickupArea(String onwardPickupArea) {
        this.onwardPickupArea = onwardPickupArea;
    }

    public String getOnwardPickupDoorNo() {
        return onwardPickupDoorNo;
    }

    public void setOnwardPickupDoorNo(String onwardPickupDoorNo) {
        this.onwardPickupDoorNo = onwardPickupDoorNo;
    }

    public String getOnwardPickupLandMark() {
        return onwardPickupLandMark;
    }

    public void setOnwardPickupLandMark(String onwardPickupLandMark) {
        this.onwardPickupLandMark = onwardPickupLandMark;
    }

    public String getOnwardPickupStreetName() {
        return onwardPickupStreetName;
    }

    public void setOnwardPickupStreetName(String onwardPickupStreetName) {
        this.onwardPickupStreetName = onwardPickupStreetName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectTravel() {
        return projectTravel;
    }

    public void setProjectTravel(String projectTravel) {
        this.projectTravel = projectTravel;
    }

    public String getReturnCab() {
        return returnCab;
    }

    public void setReturnCab(String returnCab) {
        this.returnCab = returnCab;
    }

    public String getReturnDropArea() {
        return returnDropArea;
    }

    public void setReturnDropArea(String returnDropArea) {
        this.returnDropArea = returnDropArea;
    }

    public String getReturnDropDoorNo() {
        return returnDropDoorNo;
    }

    public void setReturnDropDoorNo(String returnDropDoorNo) {
        this.returnDropDoorNo = returnDropDoorNo;
    }

    public String getReturnDropLandMark() {
        return returnDropLandMark;
    }

    public void setReturnDropLandMark(String returnDropLandMark) {
        this.returnDropLandMark = returnDropLandMark;
    }

    public String getReturnDropStreetName() {
        return returnDropStreetName;
    }

    public void setReturnDropStreetName(String returnDropStreetName) {
        this.returnDropStreetName = returnDropStreetName;
    }

     public String getReturnDropJourney() {
        return returnDropJourney;
    }

    public void setReturnDropJourney(String returnDropJourney) {
        this.returnDropJourney = returnDropJourney;
    }

    public String getReturnPickUpPoint() {
        return returnPickUpPoint;
    }

    public void setReturnPickUpPoint(String returnPickUpPoint) {
        this.returnPickUpPoint = returnPickUpPoint;
    }

    public String getReturnPickupArea() {
        return returnPickupArea;
    }

    public void setReturnPickupArea(String returnPickupArea) {
        this.returnPickupArea = returnPickupArea;
    }

    public String getReturnPickupDoorNo() {
        return returnPickupDoorNo;
    }

    public void setReturnPickupDoorNo(String returnPickupDoorNo) {
        this.returnPickupDoorNo = returnPickupDoorNo;
    }

    public String getReturnPickupLandMark() {
        return returnPickupLandMark;
    }

    public void setReturnPickupLandMark(String returnPickupLandMark) {
        this.returnPickupLandMark = returnPickupLandMark;
    }

    public String getReturnPickupStreetName() {
        return returnPickupStreetName;
    }

    public void setReturnPickupStreetName(String returnPickupStreetName) {
        this.returnPickupStreetName = returnPickupStreetName;
    }

    public String getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(String saveBtn) {
        this.saveBtn = saveBtn;
    }

    public String getSubmitBtn() {
        return submitBtn;
    }

    public void setSubmitBtn(String submitBtn) {
        this.submitBtn = submitBtn;
    }

    
   
    public String getLastLcId() {
        return lastLcId;
    }

    public String getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public Date getOnwardPickUpDate() {
        return onwardPickUpDate;
    }

    public void setOnwardPickUpDate(Date onwardPickUpDate) {
        this.onwardPickUpDate = onwardPickUpDate;
    }

    public Date getReturnPickUpDate() {
        return returnPickUpDate;
    }

    public void setReturnPickUpDate(Date returnPickUpDate) {
        this.returnPickUpDate = returnPickUpDate;
    }

    public void setLastLcId(String lastLcId) {
        this.lastLcId = lastLcId;
    }

    public String getTravelPurpose() {
        return travelPurpose;
    }

    public void setTravelPurpose(String travelPurpose) {
        this.travelPurpose = travelPurpose;
    }

    public String getWorkFlowId() {
        return workFlowId;
    }

    public void setWorkFlowId(String workFlowId) {
        this.workFlowId = workFlowId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTpReferenceId() {
        return tpReferenceId;
    }

    public void setTpReferenceId(String tpReferenceId) {
        this.tpReferenceId = tpReferenceId;
    }

    public String getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(String lastInsertId) {
        this.lastInsertId = lastInsertId;
    }

    public String getLocalConveyanceKeyword() {
        return localConveyanceKeyword;
    }

    public void setLocalConveyanceKeyword(String localConveyanceKeyword) {
        this.localConveyanceKeyword = localConveyanceKeyword;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public String getTpMasterId() {
        return tpMasterId;
    }

    public void setTpMasterId(String tpMasterId) {
        this.tpMasterId = tpMasterId;
    }

    public String getReturnPickUpTime() {
        return returnPickUpTime;
    }

    public void setReturnPickUpTime(String returnPickUpTime) {
        this.returnPickUpTime = returnPickUpTime;
    }

    public String getNewLCNumber() {
        return newLCNumber;
    }

    public void setNewLCNumber(String newLCNumber) {
        this.newLCNumber = newLCNumber;
    }

    public String getProjectOthers() {
        return projectOthers;
    }

    public void setProjectOthers(String projectOthers) {
        this.projectOthers = projectOthers;
    }

    public String getCfoActionDate() {
        return cfoActionDate;
    }

    public void setCfoActionDate(String cfoActionDate) {
        this.cfoActionDate = cfoActionDate;
    }

    public String getCfoApprovedId() {
        return cfoApprovedId;
    }

    public void setCfoApprovedId(String cfoApprovedId) {
        this.cfoApprovedId = cfoApprovedId;
    }

    public String getCfoRemarks() {
        return cfoRemarks;
    }

    public void setCfoRemarks(String cfoRemarks) {
        this.cfoRemarks = cfoRemarks;
    }

    public String getFinanceActionDate() {
        return financeActionDate;
    }

    public void setFinanceActionDate(String financeActionDate) {
        this.financeActionDate = financeActionDate;
    }

    public String getFinanceApprovedId() {
        return financeApprovedId;
    }

    public void setFinanceApprovedId(String financeApprovedId) {
        this.financeApprovedId = financeApprovedId;
    }

    public String getFinanceRemarks() {
        return financeRemarks;
    }

    public void setFinanceRemarks(String financeRemarks) {
        this.financeRemarks = financeRemarks;
    }

    public String getRmActionDate() {
        return rmActionDate;
    }

    public void setRmActionDate(String rmActionDate) {
        this.rmActionDate = rmActionDate;
    }

    public String getRmApprovedId() {
        return rmApprovedId;
    }

    public void setRmApprovedId(String rmApprovedId) {
        this.rmApprovedId = rmApprovedId;
    }

    public String getRmRemarks() {
        return rmRemarks;
    }

    public void setRmRemarks(String rmRemarks) {
        this.rmRemarks = rmRemarks;
    }

    public String getCityOthers() {
        return cityOthers;
    }

    public void setCityOthers(String cityOthers) {
        this.cityOthers = cityOthers;
    }

    public String getAdminActionDate() {
        return adminActionDate;
    }

    public void setAdminActionDate(String adminActionDate) {
        this.adminActionDate = adminActionDate;
    }

    public String getAdminApprovedId() {
        return adminApprovedId;
    }

    public void setAdminApprovedId(String adminApprovedId) {
        this.adminApprovedId = adminApprovedId;
    }

    public String getAdminRemarks() {
        return adminRemarks;
    }

    public void setAdminRemarks(String adminRemarks) {
        this.adminRemarks = adminRemarks;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public String getTpUniqueId() {
        return tpUniqueId;
    }

    public void setTpUniqueId(String tpUniqueId) {
        this.tpUniqueId = tpUniqueId;
    }

    public String getTpWorkFlowId() {
        return tpWorkFlowId;
    }

    public void setTpWorkFlowId(String tpWorkFlowId) {
        this.tpWorkFlowId = tpWorkFlowId;
    }

    public String getApproveAction() {
        return approveAction;
    }

    public void setApproveAction(String approveAction) {
        this.approveAction = approveAction;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSendBackAction() {
        return sendBackAction;
    }

    public void setSendBackAction(String sendBackAction) {
        this.sendBackAction = sendBackAction;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getRmApproverName() {
        return rmApproverName;
    }

    public void setRmApproverName(String rmApproverName) {
        this.rmApproverName = rmApproverName;
    }

    public String getAdminApproverName() {
        return adminApproverName;
    }

    public void setAdminApproverName(String adminApproverName) {
        this.adminApproverName = adminApproverName;
    }

    public String getCfoName() {
        return cfoName;
    }

    public void setCfoName(String cfoName) {
        this.cfoName = cfoName;
    }

    public String getFinanceApproverName() {
        return financeApproverName;
    }

    public void setFinanceApproverName(String financeApproverName) {
        this.financeApproverName = financeApproverName;
    }

    public String getReqRemarks() {
        return reqRemarks;
    }

    public void setReqRemarks(String reqRemarks) {
        this.reqRemarks = reqRemarks;
    }

    public String getRemarks_lc() {
        return remarks_lc;
    }

    public void setRemarks_lc(String remarks_lc) {
        this.remarks_lc = remarks_lc;
    }

    public String getRm_id() {
        return rm_id;
    }

    public void setRm_id(String rm_id) {
        this.rm_id = rm_id;
    }

    public String getClient_reimbursable() {
        return client_reimbursable;
    }

    public void setClient_reimbursable(String client_reimbursable) {
        this.client_reimbursable = client_reimbursable;
    }
       
}
