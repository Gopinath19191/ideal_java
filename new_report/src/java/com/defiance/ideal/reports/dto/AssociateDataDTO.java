/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 14517
 */
public class AssociateDataDTO {
    private String sbu;
    private String status;
    private String subSbu;
    private String id;
    private String name;
    private String joinedStartDate;
    private String joinedEndDate;
    private String exitStatus;
    private String finalAdhar;
    private String activeEmpDate;
    private String orgUnit;

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }
    
    public void setActiveEmpDate(String activeEmpDate) {
        this.activeEmpDate = activeEmpDate;
    }

    public String getActiveEmpDate() {
        return activeEmpDate;
    }

    public String getFinalAdhar() {
        return finalAdhar;
    }

    public void setFinalAdhar(String finalAdhar) {
        this.finalAdhar = finalAdhar;
    }


    public String getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(String exitStatus) {
        this.exitStatus = exitStatus;
    }
    
    
    

    public String getJoinedStartDate() {
        return joinedStartDate;
    }

    public void setJoinedStartDate(String joinedStartDate) {
        this.joinedStartDate = joinedStartDate;
    }

    public String getJoinedEndDate() {
        return joinedEndDate;
    }

    public void setJoinedEndDate(String joinedEndDate) {
        this.joinedEndDate = joinedEndDate;
    }
    
    
	
	private int start_page;
	private int end_page;
	private int page;
	private int recordCount;

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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
    

    public String getSbu() {
        return sbu;
    }

    public void setSbu(String sbu) {
        this.sbu = sbu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSubSbu() {
        return subSbu;
    }

    public void setSubSbu(String subSbu) {
        this.subSbu = subSbu;
    }


}
