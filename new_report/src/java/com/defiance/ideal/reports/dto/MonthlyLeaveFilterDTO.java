/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14517
 */
public class MonthlyLeaveFilterDTO {
 private String filter_year;
    private String filter_month;
      private String startDate;
    private String endDate;
    private String startYear;
    private String startMonth;
    private int sbuId;
    private String sbuName;
    private String sbuFilter;
    private String yearFilter;
    private String monthFilter;
    private String from_date;
    private String to_date;
    private String employee_id;
    private String leaveFilter;
    private String structure;
    private int sbu[];
    private int ssu[];

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

    
    public String getFilter_year() {
        return filter_year;
    }

    public void setFilter_year(String filter_year) {
        this.filter_year = filter_year;
    }

    public String getFilter_month() {
        return filter_month;
    }

    public void setFilter_month(String filter_month) {
        this.filter_month = filter_month;
    }

    public int[] getSbu() {
        return sbu;
    }

    public void setSbu(int[] sbu) {
        this.sbu = sbu;
    }

    public int[] getSsu() {
        return ssu;
    }

    public void setSsu(int[] ssu) {
        this.ssu = ssu;
    }
    
    
    
    public String getLeaveFilter() {
        return leaveFilter;
    }

    public void setLeaveFilter(String leaveFilter) {
        this.leaveFilter = leaveFilter;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
    
    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }
    
    public String getMonthFilter() {
        return monthFilter;
    }

    public void setMonthFilter(String monthFilter) {
        this.monthFilter = monthFilter;
    }

    public String getYearFilter() {
        return yearFilter;
    }

    public void setYearFilter(String yearFilter) {
        this.yearFilter = yearFilter;
    }

    public String getSbuFilter() {
        return sbuFilter;
    }

    public void setSbuFilter(String sbuFilter) {
        this.sbuFilter = sbuFilter;
    }

    public int getSbuId() {
        return sbuId;
    }

    public void setSbuId(int sbuId) {
        this.sbuId = sbuId;
    }

    public String getSbuName() {
        return sbuName;
    }

    public void setSbuName(String sbuName) {
        this.sbuName = sbuName;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }
    
    
}
