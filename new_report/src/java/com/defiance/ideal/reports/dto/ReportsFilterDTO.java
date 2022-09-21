/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

import java.io.Serializable;

/**
 *
 * @author 14053
 */
public class ReportsFilterDTO implements Serializable {

    private String reportMonth;
    private String monthFilter;
    private String yearFilter;
    private String projectFilter;
    private String dayFilter;
    private int assocId;
    private int lastDate;
    private String sbuFilter;
    private String subSbu;
    private String nameFilter;
    private String employeeNameFilter;
    private String projectType;
    private String monthStartDate;
    private String monthEndDate;
    private String yearStartDate;
    private String yearEndDate;
    private String yearAndMonth;
    private String projectManager;
    private String submitButton;
    private String exportButton;
    private int start_page;
    private int end_page;

    public String getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }

    public String getMonthFilter() {
        return monthFilter;
    }

    public void setMonthFilter(String monthFilter) {
        this.monthFilter = monthFilter;
    }

    public String getProjectFilter() {
        return projectFilter;
    }

    public void setProjectFilter(String projectFilter) {
        this.projectFilter = projectFilter;
    }

    public String getYearFilter() {
        return yearFilter;
    }

    public void setYearFilter(String yearFilter) {
        this.yearFilter = yearFilter;
    }

    public int getAssocId() {
        return assocId;
    }

    public void setAssocId(int assocId) {
        this.assocId = assocId;
    }

    public String getDayFilter() {
        return dayFilter;
    }

    public void setDayFilter(String dayFilter) {
        this.dayFilter = dayFilter;
    }

    public int getLastDate() {
        return lastDate;
    }

    public void setLastDate(int lastDate) {
        this.lastDate = lastDate;
    }

    public String getSbuFilter() {
        return sbuFilter;
    }

    public void setSbuFilter(String sbuFilter) {
        this.sbuFilter = sbuFilter;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getEmployeeNameFilter() {
        return employeeNameFilter;
    }

    public void setEmployeeNameFilter(String employeeNameFilter) {
        this.employeeNameFilter = employeeNameFilter;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getMonthStartDate() {
        return monthStartDate;
    }

    public void setMonthStartDate(String monthStartDate) {
        this.monthStartDate = monthStartDate;
    }

    public String getMonthEndDate() {
        return monthEndDate;
    }

    public void setMonthEndDate(String monthEndDate) {
        this.monthEndDate = monthEndDate;
    }

    public String getYearStartDate() {
        return yearStartDate;
    }

    public void setYearStartDate(String yearStartDate) {
        this.yearStartDate = yearStartDate;
    }

    public String getYearEndDate() {
        return yearEndDate;
    }

    public void setYearEndDate(String yearEndDate) {
        this.yearEndDate = yearEndDate;
    }

    public String getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(String yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(String submitButton) {
        this.submitButton = submitButton;
    }

    public String getExportButton() {
        return exportButton;
    }

    public void setExportButton(String exportButton) {
        this.exportButton = exportButton;
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

}
