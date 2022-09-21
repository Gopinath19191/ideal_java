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
public class AdminFilterDTO implements java.io.Serializable{

    private String[] employeeStatus;
    private String[] companyStructure;

    private String appraisalYearFilter;
    private String appraisalPeriodFilter;
    private String appraiserName;
    private String[] employeeStatusFilter;
    private String[] companyStructureFilter;
    private String[] bandDataFilter;
    private String appraiserId;

    public String getAppraiserId() {
        return appraiserId;
    }

    public void setAppraiserId(String appraiserId) {
        this.appraiserId = appraiserId;
    }

    
    public String getAppraiserName() {
        return appraiserName;
    }

    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
    }

    public String[] getBandDataFilter() {
        return bandDataFilter;
    }

    public void setBandDataFilter(String[] bandDataFilter) {
        this.bandDataFilter = bandDataFilter;
    }

    public String getAppraisalPeriodFilter() {
        return appraisalPeriodFilter;
    }

    public void setAppraisalPeriodFilter(String appraisalPeriodFilter) {
        this.appraisalPeriodFilter = appraisalPeriodFilter;
    }

    public String getAppraisalYearFilter() {
        return appraisalYearFilter;
    }

    public void setAppraisalYearFilter(String appraisalYearFilter) {
        this.appraisalYearFilter = appraisalYearFilter;
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




    public String[] getCompanyStructure() {
        return companyStructure;
    }

    public void setCompanyStructure(String[] companyStructure) {
        this.companyStructure = companyStructure;
    }

    public String[] getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String[] employeeStatus) {
        this.employeeStatus = employeeStatus;
    }


}
