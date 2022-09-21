/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 15261
 */
public class ProjectFinanceReportDTO {

    String projectCode;
    String projectId;
    String customerCode;    
    String fromDate;
    String toDate;
    String sbuId;
    String subSbuId;
    String rbuId;
    String subRbuId;
        
    String customerId;
    String rbu;
    String subRbu;
    String sbu;
    String subSbu;
    String pmEmpNumber;
    String projectModel;
    String searchkey;
    int start_page;
    int end_page;
    String poFromDate;
    String poToDate;

    public String getPoFromDate() {
        return poFromDate;
    }

    public void setPoFromDate(String poFromDate) {
        this.poFromDate = poFromDate;
    }

    public String getPoToDate() {
        return poToDate;
    }

    public void setPoToDate(String poToDate) {
        this.poToDate = poToDate;
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

    
    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
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
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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
	public String getSbuId() {
		return sbuId;
	}
	public void setSbuId(String sbuId) {
		this.sbuId = sbuId;
	}
	public String getSubSbuId() {
		return subSbuId;
	}
	public void setSubSbuId(String subSbuId) {
		this.subSbuId = subSbuId;
	}
	public String getRbuId() {
		return rbuId;
	}
	public void setRbuId(String rbuId) {
		this.rbuId = rbuId;
	}
	public String getSubRbuId() {
		return subRbuId;
	}
	public void setSubRbuId(String subRbuId) {
		this.subRbuId = subRbuId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getRbu() {
		return rbu;
	}
	public void setRbu(String rbu) {
		this.rbu = rbu;
	}
	public String getSubRbu() {
		return subRbu;
	}
	public void setSubRbu(String subRbu) {
		this.subRbu = subRbu;
	}
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
	public String getPmEmpNumber() {
		return pmEmpNumber;
	}
	public void setPmEmpNumber(String pmEmpNumber) {
		this.pmEmpNumber = pmEmpNumber;
	}

    public String getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(String projectModel) {
        this.projectModel = projectModel;
    }
        
    }
