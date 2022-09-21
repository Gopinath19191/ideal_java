/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;



import java.io.Serializable;
/**
 *
 * @author 16113
 */
public class LeaveReportDTO implements Serializable {
    private String employee_number;
    private String employee_name;
    private String opening_balence;
    private String lop_days;
    private String leave_accrued;
    private String leave_taken;
    private String leave_cancelled;
    private String leave_arrear;
    private String leave_encashed;
    private String closing_balence;
    private String month;
    private String year;
    private String login_emp_id;    
    private int start_page;
    private int end_page;
    private int page;
    private int recordCount;
    private String id;
    private String name;
    private String sbu;
    private String subSbu;

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    public String getLogin_emp_id() {
        return login_emp_id;
    }

    public void setLogin_emp_id(String login_emp_id) {
        this.login_emp_id = login_emp_id;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getOpening_balence() {
        return opening_balence;
    }

    public void setOpening_balence(String opening_balence) {
        this.opening_balence = opening_balence;
    }

    public String getLop_days() {
        return lop_days;
    }

    public void setLop_days(String lop_days) {
        this.lop_days = lop_days;
    }

    public String getLeave_accrued() {
        return leave_accrued;
    }

    public void setLeave_accrued(String leave_accrued) {
        this.leave_accrued = leave_accrued;
    }

    public String getLeave_taken() {
        return leave_taken;
    }

    public void setLeave_taken(String leave_taken) {
        this.leave_taken = leave_taken;
    }

    public String getLeave_cancelled() {
        return leave_cancelled;
    }

    public void setLeave_cancelled(String leave_cancelled) {
        this.leave_cancelled = leave_cancelled;
    }

    public String getLeave_arrear() {
        return leave_arrear;
    }

    public void setLeave_arrear(String leave_arrear) {
        this.leave_arrear = leave_arrear;
    }

    public String getLeave_encashed() {
        return leave_encashed;
    }

    public void setLeave_encashed(String leave_encashed) {
        this.leave_encashed = leave_encashed;
    }

    public String getClosing_balence() {
        return closing_balence;
    }

    public void setClosing_balence(String closing_balence) {
        this.closing_balence = closing_balence;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
