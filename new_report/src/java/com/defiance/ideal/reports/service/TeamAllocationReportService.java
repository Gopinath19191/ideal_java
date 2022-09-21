/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.TeamAllocationReportDTO;
import java.util.List;




/**
 *
 * @author 15332
 */
public interface TeamAllocationReportService {
    
public List<TeamAllocationReportDTO> getRollOutreport(TeamAllocationReportDTO formData);
public List<TeamAllocationReportDTO> getprojectList(TeamAllocationReportDTO formData);
public List<TeamAllocationReportDTO> getSbuList(TeamAllocationReportDTO formData);
public List<TeamAllocationReportDTO> getPmNameList();
public List<TeamAllocationReportDTO> getsubSbuList(String sbuId);
public List<TeamAllocationReportDTO> getcustomerList(TeamAllocationReportDTO formData);
    
}
