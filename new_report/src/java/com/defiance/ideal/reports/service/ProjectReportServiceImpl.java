/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.ProjectReportDaoImpl;
import com.defiance.ideal.reports.dto.ProjectDetails;
import com.defiance.ideal.reports.dto.ProjectReportFilterDTO;
import java.util.List;

/**
 * 
 * @author 15261
 */
public class ProjectReportServiceImpl implements ProjectReportService {

    public ProjectReportDaoImpl projectReportDaoImpl;

    public ProjectReportDaoImpl getProjectReportDaoImpl() {
        return projectReportDaoImpl;
    }

    public void setProjectReportDaoImpl(ProjectReportDaoImpl projectReportDaoImpl) {
        this.projectReportDaoImpl = projectReportDaoImpl;
    }

    public List<ProjectDetails> getProjectDetails(ProjectDetails dto) {
        return projectReportDaoImpl.getProjectDetailsList(dto);
    }
    public List<ProjectDetails> getEntireProjectDetail(ProjectReportFilterDTO dto){
        return projectReportDaoImpl.getEntireProjectDetail(dto);
    }

    public List<ProjectDetails> getCustomerName(ProjectReportFilterDTO dto){
        return projectReportDaoImpl.getCustomerName(dto);
    }

    public List<ProjectDetails> getTeamAllocationList(ProjectReportFilterDTO dto){
        return projectReportDaoImpl.getTeamAllocationList(dto);
    }

    public List<ProjectDetails> getTotalEffort(ProjectReportFilterDTO dto){
        return projectReportDaoImpl.getTotalEffort(dto);
    }
    public List<ProjectDetails> getAccruedEffort(ProjectReportFilterDTO dto){
        return projectReportDaoImpl.getAccruedEffort(dto);
    }
    public List<ProjectDetails> getBillableAmtSum(ProjectReportFilterDTO dto){
        return projectReportDaoImpl.getBillableAmtSum(dto);
    }
    public List<ProjectDetails> getPoValue(ProjectReportFilterDTO dto){
        return projectReportDaoImpl.getPoValue(dto);
    }


}
