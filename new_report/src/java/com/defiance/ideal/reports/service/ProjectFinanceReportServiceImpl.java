/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import java.util.List;

import com.defiance.ideal.reports.dao.ProjectFinanceReportDaoImpl;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDetails;

/**
 * 
 * @author 15261
 */
public class ProjectFinanceReportServiceImpl implements
		ProjectFinanceReportService { 

	public ProjectFinanceReportDaoImpl projectFinanceReportDaoImpl;

	public ProjectFinanceReportDaoImpl getProjectFinanceReportDaoImpl() {
		return projectFinanceReportDaoImpl;
	}

	public void setProjectFinanceReportDaoImpl(
			ProjectFinanceReportDaoImpl projectFinanceReportDaoImpl) {
		this.projectFinanceReportDaoImpl = projectFinanceReportDaoImpl;
	}

	public List<ProjectFinanceReportDetails> getProjectDetails(String searchKey) {
		return projectFinanceReportDaoImpl.getProjectDetailsList(searchKey);
	}
    public List<ProjectFinanceReportDetails> getSbuList(){
        return projectFinanceReportDaoImpl.getSbuList();
    }
    public List<ProjectFinanceReportDetails> getRbuList(){
        return projectFinanceReportDaoImpl.getRbuList();
    }
    public List<ProjectFinanceReportDetails> getProjectModelList(){
        return projectFinanceReportDaoImpl.getProjectModelList();
    }
    public List<ProjectFinanceReportDetails> getCustomerDetails(String searchKey){
    	return projectFinanceReportDaoImpl.getCustomerDetails(searchKey);
    }
        
    public List<ProjectFinanceReportDetails> getPMList(String searchKey){
    	return  projectFinanceReportDaoImpl.getPMList(searchKey);
    }
    public List<ProjectFinanceReportDetails> getReportDetails(ProjectFinanceReportDTO dto){
    	return  projectFinanceReportDaoImpl.getReportDetails(dto);
    }
        
    public List<ProjectFinanceReportDetails> getSubRbu(String parentId)
    {
        List<ProjectFinanceReportDetails> subRbu = projectFinanceReportDaoImpl.getSubRbu(parentId);
        return subRbu;
    }

    public int getReportDetailsCount(ProjectFinanceReportDTO dto) {
        int recordCount = 0;
        try{
            recordCount = projectFinanceReportDaoImpl.getReportDetailsCount(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return recordCount;
    }

}
