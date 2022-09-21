/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 15261
 */
public class TravelReportFilterDTO {

    String projectCode;
    String projectId;
    String fromDate;
    String toDate;
    String sbuId;
    String subSbuId;
    String status_search;
    String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    

    public String getStatus_search() {
        return status_search;
    }

    public void setStatus_search(String status_search) {
        this.status_search = status_search;
    }

    public String getSubSbuId() {
        return subSbuId;
    }

    public void setSubSbuId(String subSbuId) {
        this.subSbuId = subSbuId;
    }

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }


    
}
