/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14517
 */
public class BillableHoursFilterDTO {

    private String monthFilter;
    private String yearFilter;
    private String sbuFilter;
    private String billableFilter;
    private String locationFilter;
    private String conditionFilter;
    private String hiddenFilter;
    private String custFilter;
    private String pricingFilter;
    private String projId;
    private String custName;
    private String projName;
    private String custId;
    private String nameFilter;
    private String splitName;
    private String subSbu;
    private String startDate;
    private String endDate;
    private String startYear;
    private String startMonth;

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
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

    public String getSplitName() {
        return splitName;
    }

    public void setSplitName(String splitName) {
        this.splitName = splitName;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getPricingFilter() {
        return pricingFilter;
    }

    public void setPricingFilter(String pricingFilter) {
        this.pricingFilter = pricingFilter;
    }

    public String getCustFilter() {
        return custFilter;
    }

    public void setCustFilter(String custFilter) {
        this.custFilter = custFilter;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getHiddenFilter() {
        return hiddenFilter;
    }

    public void setHiddenFilter(String hiddenFilter) {
        this.hiddenFilter = hiddenFilter;
    }

    public String getConditionFilter() {
        return conditionFilter;
    }

    public void setConditionFilter(String conditionFilter) {
        this.conditionFilter = conditionFilter;
    }

    public String getLocationFilter() {
        return locationFilter;
    }

    public void setLocationFilter(String locationFilter) {
        this.locationFilter = locationFilter;
    }

    public String getBillableFilter() {
        return billableFilter;
    }

    public void setBillableFilter(String billableFilter) {
        this.billableFilter = billableFilter;
    }

    public String getMonthFilter() {
        return monthFilter;
    }

    public void setMonthFilter(String monthFilter) {
        this.monthFilter = monthFilter;
    }

    public String getSbuFilter() {
        return sbuFilter;
    }

    public void setSbuFilter(String sbuFilter) {
        this.sbuFilter = sbuFilter;
    }

    public String getYearFilter() {
        return yearFilter;
    }

    public void setYearFilter(String yearFilter) {
        this.yearFilter = yearFilter;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }
    
}
