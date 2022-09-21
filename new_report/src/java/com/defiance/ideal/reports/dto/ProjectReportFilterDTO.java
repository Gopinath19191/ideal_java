/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dto;

/**
 *
 * @author 15261
 */
public class ProjectReportFilterDTO { 

    String projectCode;
    String projectId;
    String project_search;
    
    
    

    public String getProject_search() {
		return project_search;
	}

	public void setProject_search(String project_search) {
		this.project_search = project_search;
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
