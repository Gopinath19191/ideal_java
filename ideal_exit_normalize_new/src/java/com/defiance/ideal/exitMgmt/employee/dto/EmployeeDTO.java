/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.employee.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 14583
 */
public class EmployeeDTO {

    //Master Data
    private String pmWorkEmail;
    private String approveType;
    private String bccMailIds;
    private String additionalTriggerMailIds;
    private String exitEmpId;
    private String empStructure;
    private String additionalMailIds;
    private String employeeNumber;
    private String employeeName;
    private String band;
    private String subBand;
    private String bandId;
    private String subBandId;
    private String designation;
    private String practiceGroup;
    private String practiceGroupId;
    private String rmName;
    private String rmId;
    private Date currentDate;
    private Date empDoj;
    private String empId;
    private String submitStatus;
    private String empWorkMail;
    private String rmWorkMail;
    private String rmEmpNumber;
    private String empBaseLocation;
    // Form Data
    private Date resignedDate;
    private String contactNo;
    private String contactAddr;
    private String personalEmail;
    private String leavingReason;
    private String submitDate;
    private Date rmApprovedDate;
    private Date rmClrDate;
    private Date adApprovedDate;
    private Date finApprovedDate;
    private Date nsApprovedDate;
    private Date hrApprovedDate;
    private String buttonName;
    private String deleted;
    private Date lastWorkingDate;
    private String resEmpId;
    private String moduleId;
    private String surveyStatus;
    private String reasonRejection;
     private Date exitTriggerDate;
     private String nsStatus;
     private String adminStatus;
     private String rmStatus;
     private String finStatus;

     //New Variables due to normalization
     private Integer contactAddrId;
     private String addressType;
     private String addressLine1;
     private String addressLine2;
     private Integer country;
     private String countryName;
     private Integer region;
     private String regionName;
     private Integer city;
     private String cityName;
     private String zipCode;
     private Integer empAddressId;
     private String empAddress;
     
     private String leaveBalance;
     private String empStatus;
     private String manager;
     private String EmpToRmHrCC;
     private String resDate;
     private String empLeavingReason;
     private String exitType;
     private String noticePeriod;
     private String noticeWavierType;
private String dlmail;
private String buhmail;
private String rmApprovaldl;
private String adminApprovaldl;
private String finApprovaldl;
private String exitsurveydl;
private String nsApprovaldl;
private String hrApprovaldl;
private String ralPFdl;
private String rmClearancedl;
private String rmApprovaldlto;

    public String getRmApprovaldlto() {
        return rmApprovaldlto;
    }

    public void setRmApprovaldlto(String rmApprovaldlto) {
        this.rmApprovaldlto = rmApprovaldlto;
    }


    public String getRmClearancedl() {
        return rmClearancedl;
    }

    public void setRmClearancedl(String rmClearancedl) {
        this.rmClearancedl = rmClearancedl;
    }


    public String getRalPFdl() {
        return ralPFdl;
    }

    public void setRalPFdl(String ralPFdl) {
        this.ralPFdl = ralPFdl;
    }



    public String getHrApprovaldl() {
        return hrApprovaldl;
    }

    public void setHrApprovaldl(String hrApprovaldl) {
        this.hrApprovaldl = hrApprovaldl;
    }


    public String getNsApprovaldl() {
        return nsApprovaldl;
    }

    public void setNsApprovaldl(String nsApprovaldl) {
        this.nsApprovaldl = nsApprovaldl;
    }


    public String getExitsurveydl() {
        return exitsurveydl;
    }

    public void setExitsurveydl(String exitsurveydl) {
        this.exitsurveydl = exitsurveydl;
    }


    public String getFinApprovaldl() {
        return finApprovaldl;
    }

    public void setFinApprovaldl(String finApprovaldl) {
        this.finApprovaldl = finApprovaldl;
    }


    public String getAdminApprovaldl() {
        return adminApprovaldl;
    }

    public void setAdminApprovaldl(String adminApprovaldl) {
        this.adminApprovaldl = adminApprovaldl;
    }


    public String getRmApprovaldl() {
        return rmApprovaldl;
    }

    public void setRmApprovaldl(String rmApprovaldl) {
        this.rmApprovaldl = rmApprovaldl;
    }



    public String getBuhmail() {
        return buhmail;
    }

    public void setBuhmail(String buhmail) {
        this.buhmail = buhmail;
    }


    public String getDlmail() {
        return dlmail;
    }

    public void setDlmail(String dlmail) {
        this.dlmail = dlmail;
    }


    public String getNoticeWavierType() {
        return noticeWavierType;
    }

    public void setNoticeWavierType(String noticeWavierType) {
        this.noticeWavierType = noticeWavierType;
    }
     
    public String getExitType() {
        return exitType;
    }

    public void setExitType(String exitType) {
        this.exitType = exitType;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }
     
    public String getEmpLeavingReason() {
        return empLeavingReason;
    }

    public void setEmpLeavingReason(String empLeavingReason) {
        this.empLeavingReason = empLeavingReason;
    }
     
    public String getResDate() {
        return resDate;
    }

    public void setResDate(String resDate) {
        this.resDate = resDate;
    }
     
    public String getEmpToRmHrCC() {
        return EmpToRmHrCC;
    }

    public void setEmpToRmHrCC(String EmpToRmHrCC) {
        this.EmpToRmHrCC = EmpToRmHrCC;
    }
    
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
     
    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }
     
    public String getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(String leaveBalance) {
        this.leaveBalance = leaveBalance;
    }
     

    public  String approvalWorkEmail;
    public String approvalEmpName;
public  String rmgWorkEmail;
    public String rmgEmpName;
    
    
    List approvalMenuNames;
    private String cmpHoliday;

    public String getRmgWorkEmail() {
        return rmgWorkEmail;
    }

    public void setRmgWorkEmail(String rmgWorkEmail) {
        this.rmgWorkEmail = rmgWorkEmail;
    }

    public String getRmgEmpName() {
        return rmgEmpName;
    }

    public void setRmgEmpName(String rmgEmpName) {
        this.rmgEmpName = rmgEmpName;
    }

    
    
    public String getCmpHoliday() {
        return cmpHoliday;
    }

    public void setCmpHoliday(String cmpHoliday) {
        this.cmpHoliday = cmpHoliday;
    }
    
    
    
    

    public Date getAdApprovedDate() {
        return adApprovedDate;
    }

    public void setAdApprovedDate(Date adApprovedDate) {
        this.adApprovedDate = adApprovedDate;
    }

    public String getAdditionalMailIds() {
        return additionalMailIds;
    }

    public void setAdditionalMailIds(String additionalMailIds) {
        this.additionalMailIds = additionalMailIds;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getApprovalEmpName() {
        return approvalEmpName;
    }

    public void setApprovalEmpName(String approvalEmpName) {
        this.approvalEmpName = approvalEmpName;
    }

    public List getApprovalMenuNames() {
        return approvalMenuNames;
    }

    public void setApprovalMenuNames(List approvalMenuNames) {
        this.approvalMenuNames = approvalMenuNames;
    }

    public String getApprovalWorkEmail() {
        return approvalWorkEmail;
    }

    public void setApprovalWorkEmail(String approvalWorkEmail) {
        this.approvalWorkEmail = approvalWorkEmail;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public Integer getContactAddrId() {
        return contactAddrId;
    }

    public void setContactAddrId(Integer contactAddrId) {
        this.contactAddrId = contactAddrId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public Integer getEmpAddressId() {
        return empAddressId;
    }

    public void setEmpAddressId(Integer empAddressId) {
        this.empAddressId = empAddressId;
    }

    public String getEmpBaseLocation() {
        return empBaseLocation;
    }

    public void setEmpBaseLocation(String empBaseLocation) {
        this.empBaseLocation = empBaseLocation;
    }

    public Date getEmpDoj() {
        return empDoj;
    }

    public void setEmpDoj(Date empDoj) {
        this.empDoj = empDoj;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpStructure() {
        return empStructure;
    }

    public void setEmpStructure(String empStructure) {
        this.empStructure = empStructure;
    }

    public String getEmpWorkMail() {
        return empWorkMail;
    }

    public void setEmpWorkMail(String empWorkMail) {
        this.empWorkMail = empWorkMail;
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

    public String getExitEmpId() {
        return exitEmpId;
    }

    public void setExitEmpId(String exitEmpId) {
        this.exitEmpId = exitEmpId;
    }

    public Date getExitTriggerDate() {
        return exitTriggerDate;
    }

    public void setExitTriggerDate(Date exitTriggerDate) {
        this.exitTriggerDate = exitTriggerDate;
    }

    public Date getFinApprovedDate() {
        return finApprovedDate;
    }

    public void setFinApprovedDate(Date finApprovedDate) {
        this.finApprovedDate = finApprovedDate;
    }

    public String getFinStatus() {
        return finStatus;
    }

    public void setFinStatus(String finStatus) {
        this.finStatus = finStatus;
    }

    public Date getHrApprovedDate() {
        return hrApprovedDate;
    }

    public void setHrApprovedDate(Date hrApprovedDate) {
        this.hrApprovedDate = hrApprovedDate;
    }

    public Date getLastWorkingDate() {
        return lastWorkingDate;
    }

    public void setLastWorkingDate(Date lastWorkingDate) {
        this.lastWorkingDate = lastWorkingDate;
    }

    public String getLeavingReason() {
        return leavingReason;
    }

    public void setLeavingReason(String leavingReason) {
        this.leavingReason = leavingReason;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Date getNsApprovedDate() {
        return nsApprovedDate;
    }

    public void setNsApprovedDate(Date nsApprovedDate) {
        this.nsApprovedDate = nsApprovedDate;
    }

    public String getNsStatus() {
        return nsStatus;
    }

    public void setNsStatus(String nsStatus) {
        this.nsStatus = nsStatus;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPracticeGroup() {
        return practiceGroup;
    }

    public void setPracticeGroup(String practiceGroup) {
        this.practiceGroup = practiceGroup;
    }

    public String getPracticeGroupId() {
        return practiceGroupId;
    }

    public void setPracticeGroupId(String practiceGroupId) {
        this.practiceGroupId = practiceGroupId;
    }

    public String getReasonRejection() {
        return reasonRejection;
    }

    public void setReasonRejection(String reasonRejection) {
        this.reasonRejection = reasonRejection;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getResEmpId() {
        return resEmpId;
    }

    public void setResEmpId(String resEmpId) {
        this.resEmpId = resEmpId;
    }

    public Date getResignedDate() {
        return resignedDate;
    }

    public void setResignedDate(Date resignedDate) {
        this.resignedDate = resignedDate;
    }

    public Date getRmApprovedDate() {
        return rmApprovedDate;
    }

    public void setRmApprovedDate(Date rmApprovedDate) {
        this.rmApprovedDate = rmApprovedDate;
    }

    public Date getRmClrDate() {
        return rmClrDate;
    }

    public void setRmClrDate(Date rmClrDate) {
        this.rmClrDate = rmClrDate;
    }

    public String getRmEmpNumber() {
        return rmEmpNumber;
    }

    public void setRmEmpNumber(String rmEmpNumber) {
        this.rmEmpNumber = rmEmpNumber;
    }

    public String getRmId() {
        return rmId;
    }

    public void setRmId(String rmId) {
        this.rmId = rmId;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getRmStatus() {
        return rmStatus;
    }

    public void setRmStatus(String rmStatus) {
        this.rmStatus = rmStatus;
    }

    public String getRmWorkMail() {
        return rmWorkMail;
    }

    public void setRmWorkMail(String rmWorkMail) {
        this.rmWorkMail = rmWorkMail;
    }

    public String getSubBand() {
        return subBand;
    }

    public void setSubBand(String subBand) {
        this.subBand = subBand;
    }

    public String getSubBandId() {
        return subBandId;
    }

    public void setSubBandId(String subBandId) {
        this.subBandId = subBandId;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(String surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getApproveType() {
        return approveType;
    }

    public void setApproveType(String approveType) {
        this.approveType = approveType;
    }

    public String getAdditionalTriggerMailIds() {
        return additionalTriggerMailIds;
    }

    public void setAdditionalTriggerMailIds(String additionalTriggerMailIds) {
        this.additionalTriggerMailIds = additionalTriggerMailIds;
    }

    public String getBccMailIds() {
        return bccMailIds;
    }

    public void setBccMailIds(String bccMailIds) {
        this.bccMailIds = bccMailIds;
    }

    public String getPmWorkEmail() {
        return pmWorkEmail;
    }

    public void setPmWorkEmail(String pmWorkEmail) {
        this.pmWorkEmail = pmWorkEmail;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
}
