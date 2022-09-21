/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.ApprovalDTO;
import java.util.List;

/**
 *
 * @author 16364
 */
public interface ApprovalService {
    public List<ApprovalDTO> getProjectList(ApprovalDTO empDet);
    public List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO);
    public List<ApprovalDTO> filterDataList(ApprovalDTO appDTO);
    public List<ApprovalDTO> filterDataListExport(ApprovalDTO appDTO);
}
