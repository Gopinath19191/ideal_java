/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.approval.services;

import com.defiance.ideal.exitMgmt.approval.dto.ApprovalDTO;
import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 14583
 */
public interface ApprovalService {

    public void saveRmData(ApprovalDTO rmFormData);
    public ApprovalDTO getRmActionData(String exitEmpId);
    public List<EmployeeDTO> getRegnEmpList(String employeeId,String moduleId,String approveType);

    public void addFileDb(String fileName, String contentType, String referenceName, int refId, String moduleName);

    public ApprovalDTO getFile(int exitEmpId, String EXIT_MODULE_CODE);
    public List<ApprovalDTO> getExitEmployeeStatus();
    public void updateFileDb(String fileName, String contentType, String referenceName, int refId, String moduleName);
    public List<ApprovalDTO> getEmploymentStatus();
    public void publishServLetter(ApprovalDTO hrFormData,HttpServletRequest request, HttpServletResponse response);
}
