/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.services;

import com.defiance.ideal.timesheet.approve.dto.ApprovalDTO;
import com.defiance.ideal.timesheet.approve.dto.SearchCriteriaDTO;

import java.util.List;

/**
 *
 * @author 
 */
public interface ApprovalService {
    public List<ApprovalDTO> getProjectList(ApprovalDTO empDet);
    public List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO);
    public List<ApprovalDTO> filterDataList(ApprovalDTO appDTO);
    public void  approverSubmit(ApprovalDTO appDTO);
    public ApprovalDTO  getProjectDetails(ApprovalDTO appDTO);
    public int savedDataRemove(String timeSheetId);    
    public String get_WFH_Eligible(SearchCriteriaDTO searchObj);
}
