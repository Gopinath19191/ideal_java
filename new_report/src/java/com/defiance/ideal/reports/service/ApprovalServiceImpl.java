/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.ApprovalDAOImpl;
import com.defiance.ideal.reports.dto.ApprovalDTO;
import java.util.List;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16364
 */
public class ApprovalServiceImpl extends MultiActionController implements ApprovalService{
    
     private ApprovalDAOImpl approvalDaoImplObj;

    public ApprovalDAOImpl getApprovalDaoImplObj() {
        return approvalDaoImplObj;
    }

    public void setApprovalDaoImplObj(ApprovalDAOImpl approvalDaoImplObj) {
        this.approvalDaoImplObj = approvalDaoImplObj;
    }

    
     
     
     
    public List<ApprovalDTO> getProjectList(ApprovalDTO empDet) {
        List<ApprovalDTO> projectList = null;
        try {
            projectList = approvalDaoImplObj.getProjectList(empDet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
    public List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO) {
        List<ApprovalDTO> projectList = null;
        try {
            projectList = approvalDaoImplObj.getEmployeeList(appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
    public List<ApprovalDTO> filterDataList(ApprovalDTO appDTO) {
        List<ApprovalDTO> filterDataList = null;
        try {
            filterDataList = approvalDaoImplObj.filterDataList(appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterDataList;
    }
    public List<ApprovalDTO> filterDataListExport(ApprovalDTO appDTO) {
        List<ApprovalDTO> filterDataList = null;
        try {
            filterDataList = approvalDaoImplObj.filterDataListExport(appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterDataList;
    }
    public List<ApprovalDTO> getWorkTypeList(ApprovalDTO empDet) {
        List<ApprovalDTO> workTypeList = null;
        try {
            workTypeList = approvalDaoImplObj.getWorkTypeList(empDet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workTypeList;
    }
    
    public String recordCount(ApprovalDTO appDTO) {
        String recordCount = null;
        try {
            recordCount = approvalDaoImplObj.recordCount(appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }
}
