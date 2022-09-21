/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.ProjectDetails;
import com.defiance.ideal.reports.dto.ProjectReportFilterDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDTO;
import java.util.List;

/**
 *
 * @author 15261
 */
public interface ProjectReportDao {

    public List<ProjectDetails> getProjectDetailsList(ProjectDetails dto); 

    public List<ProjectDetails> getEntireProjectDetail(ProjectReportFilterDTO dto);

    public List<ProjectDetails> getCustomerName(ProjectReportFilterDTO dto);

    public List<ProjectDetails> getTeamAllocationList(ProjectReportFilterDTO dto);

    public List<ProjectDetails> getTotalEffort(ProjectReportFilterDTO dto);
     
    public List<ProjectDetails> getAccruedEffort(ProjectReportFilterDTO dto);

    public List<ProjectDetails> getBillableAmtSum(ProjectReportFilterDTO dto);
    
    public List<ProjectDetails> getPoValue(ProjectReportFilterDTO dto);
    
    public List<ProjectDetails> getPoReceivedDetails(ProjectFinanceReportDTO dto);

}


