/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import java.util.List;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDetails;

/**
 *
 * @author 15261
 */
public interface ProjectFinanceReportService {

    public List<ProjectFinanceReportDetails> getProjectDetails(String searchKey);
    public List<ProjectFinanceReportDetails> getSbuList();
    public List<ProjectFinanceReportDetails> getRbuList();
    public List<ProjectFinanceReportDetails> getProjectModelList();
    public List<ProjectFinanceReportDetails> getCustomerDetails(String searchKey);
    public List<ProjectFinanceReportDetails> getPMList(String searchKey);
    public List<ProjectFinanceReportDetails> getReportDetails(ProjectFinanceReportDTO dto);
    public List<ProjectFinanceReportDetails> getSubRbu(String parentId);

    public int getReportDetailsCount(ProjectFinanceReportDTO dto);
    
    

}
