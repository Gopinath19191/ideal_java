/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.admin.dto;

import org.apache.beehive.netui.pageflow.annotations.Jpf;

/**
 *
 * @author HARIHARAN.C
 */
@Jpf.FormBean()
public class AdminDTO implements java.io.Serializable{

    private int csId;
    private int qpdId;
    private String structureName;
    private int appraiseeId;
    private int appraiserId;
    private int reviewerId;
    private int normalizerId;
    private String appraiseeName;
    private String appraiseeNumber;
    private String appraiserNumber;
    private String appraiserName;
    private String normalizerNumber;
    private String normalizerName;
    private String reviewerName;
    private String reviewerNumber;
    private String bandName;
    private String departmentName;
    private String dateOfJoin;

    private int masterBandId;
    private String masterBandName;
    
    private String[] employeeStatusFilter;
    private String[] companyStructureFilter;

    private String saveAppraisal;
    private String submitAppraisal;
    private String excelExport;

    private int submitStatus;
    private int bandId;
    private int depId;

    private int empIdAc;
    private String employeeNumberAc;
    private String employeeNameAc;

    private int[] appraiseeIdForm;
    private int[] appraiserIdForm;
    private int[] reviewerIdForm;
    private int[] normalizerIdForm;
    private int[] triggerCheckForm;
    private int[] bandIdForm;
    private int[] depIdForm;
    private int[] qpdIdForm;

    private int appraisalYearFinal;
    private int appraisalPeriodFinal;

    public String getExcelExport() {
        return excelExport;
    }

    public void setExcelExport(String excelExport) {
        this.excelExport = excelExport;
    }

    public int getMasterBandId() {
        return masterBandId;
    }

    public void setMasterBandId(int masterBandId) {
        this.masterBandId = masterBandId;
    }

    public String getMasterBandName() {
        return masterBandName;
    }

    public void setMasterBandName(String masterBandName) {
        this.masterBandName = masterBandName;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    
    public int getQpdId() {
        return qpdId;
    }

    public String getSaveAppraisal() {
        return saveAppraisal;
    }

    public void setSaveAppraisal(String saveAppraisal) {
        this.saveAppraisal = saveAppraisal;
    }

    public String getSubmitAppraisal() {
        return submitAppraisal;
    }

    public void setSubmitAppraisal(String submitAppraisal) {
        this.submitAppraisal = submitAppraisal;
    }

    public void setQpdId(int qpdId) {
        this.qpdId = qpdId;
    }

    public int[] getQpdIdForm() {
        return qpdIdForm;
    }

    public void setQpdIdForm(int[] qpdIdForm) {
        this.qpdIdForm = qpdIdForm;
    }


    public int getEmpIdAc() {
        return empIdAc;
    }

    public void setEmpIdAc(int empIdAc) {
        this.empIdAc = empIdAc;
    }

    public String getEmployeeNameAc() {
        return employeeNameAc;
    }

    public void setEmployeeNameAc(String employeeNameAc) {
        this.employeeNameAc = employeeNameAc;
    }

    public String getEmployeeNumberAc() {
        return employeeNumberAc;
    }

    public void setEmployeeNumberAc(String employeeNumberAc) {
        this.employeeNumberAc = employeeNumberAc;
    }

    public int getAppraisalPeriodFinal() {
        return appraisalPeriodFinal;
    }

    public void setAppraisalPeriodFinal(int appraisalPeriodFinal) {
        this.appraisalPeriodFinal = appraisalPeriodFinal;
    }

    public int getAppraisalYearFinal() {
        return appraisalYearFinal;
    }

    public void setAppraisalYearFinal(int appraisalYearFinal) {
        this.appraisalYearFinal = appraisalYearFinal;
    }


    public int[] getBandIdForm() {
        return bandIdForm;
    }

    public void setBandIdForm(int[] bandIdForm) {
        this.bandIdForm = bandIdForm;
    }

    public int[] getDepIdForm() {
        return depIdForm;
    }

    public void setDepIdForm(int[] depIdForm) {
        this.depIdForm = depIdForm;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int[] getTriggerCheckForm() {
        return triggerCheckForm;
    }

    public void setTriggerCheckForm(int[] triggerCheckForm) {
        this.triggerCheckForm = triggerCheckForm;
    }

    
    public int[] getAppraiseeIdForm() {
        return appraiseeIdForm;
    }

    public void setAppraiseeIdForm(int[] appraiseeIdForm) {
        this.appraiseeIdForm = appraiseeIdForm;
    }

    public int[] getAppraiserIdForm() {
        return appraiserIdForm;
    }

    public void setAppraiserIdForm(int[] appraiserIdForm) {
        this.appraiserIdForm = appraiserIdForm;
    }

    public int[] getReviewerIdForm() {
        return reviewerIdForm;
    }

    public void setReviewerIdForm(int[] reviewerIdForm) {
        this.reviewerIdForm = reviewerIdForm;
    }

    public int getAppraiseeId() {
        return appraiseeId;
    }

    public void setAppraiseeId(int appraiseeId) {
        this.appraiseeId = appraiseeId;
    }

    public String getAppraiseeName() {
        return appraiseeName;
    }

    public void setAppraiseeName(String appraiseeName) {
        this.appraiseeName = appraiseeName;
    }

    public String getAppraiseeNumber() {
        return appraiseeNumber;
    }

    public void setAppraiseeNumber(String appraiseeNumber) {
        this.appraiseeNumber = appraiseeNumber;
    }

    public int getAppraiserId() {
        return appraiserId;
    }

    public void setAppraiserId(int appraiserId) {
        this.appraiserId = appraiserId;
    }

    public String getAppraiserName() {
        return appraiserName;
    }

    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
    }

    public String getAppraiserNumber() {
        return appraiserNumber;
    }

    public void setAppraiserNumber(String appraiserNumber) {
        this.appraiserNumber = appraiserNumber;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerNumber() {
        return reviewerNumber;
    }

    public void setReviewerNumber(String reviewerNumber) {
        this.reviewerNumber = reviewerNumber;
    }

    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }

    public int getCsId() {
        return csId;
    }

    public void setCsId(int csId) {
        this.csId = csId;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String[] getCompanyStructureFilter() {
        return companyStructureFilter;
    }

    public void setCompanyStructureFilter(String[] companyStructureFilter) {
        this.companyStructureFilter = companyStructureFilter;
    }

    public String[] getEmployeeStatusFilter() {
        return employeeStatusFilter;
    }

    public void setEmployeeStatusFilter(String[] employeeStatusFilter) {
        this.employeeStatusFilter = employeeStatusFilter;
    }

    public String getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(String dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public int getNormalizerId() {
        return normalizerId;
    }

    public void setNormalizerId(int normalizerId) {
        this.normalizerId = normalizerId;
    }

    public int[] getNormalizerIdForm() {
        return normalizerIdForm;
    }

    public void setNormalizerIdForm(int[] normalizerIdForm) {
        this.normalizerIdForm = normalizerIdForm;
    }

    public String getNormalizerName() {
        return normalizerName;
    }

    public void setNormalizerName(String normalizerName) {
        this.normalizerName = normalizerName;
    }

    public String getNormalizerNumber() {
        return normalizerNumber;
    }

    public void setNormalizerNumber(String normalizerNumber) {
        this.normalizerNumber = normalizerNumber;
    }

    public AdminDTO(){
    }

}
