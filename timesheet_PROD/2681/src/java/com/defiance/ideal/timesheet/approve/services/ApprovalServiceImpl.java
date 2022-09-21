/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.timesheet.approve.services;

import com.defiance.ideal.timesheet.approve.dao.ApprovalDAOImpl;
import com.defiance.ideal.timesheet.approve.dto.ApprovalDTO;
import com.defiance.ideal.timesheet.approve.dto.SearchCriteriaDTO;
import com.defiance.ideal.timesheet.approve.dto.TimesheetDTO;

import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 
 */
public class ApprovalServiceImpl extends MultiActionController implements ApprovalService {

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
    public String getprojectManager(ApprovalDTO empDet) {
       String prjId = null;
        try {
            prjId = approvalDaoImplObj.getprojectManager(empDet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prjId;
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

    public List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO) {
        List<ApprovalDTO> projectList = null;
        try {
            projectList = approvalDaoImplObj.getEmployeeList(appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
    
    public List<ApprovalDTO> getPrjDetails(ApprovalDTO appDTO) {
        List<ApprovalDTO> projectList = null;
        try {
            projectList = approvalDaoImplObj.getPrjDetails(appDTO);

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

    public String recordCount(ApprovalDTO appDTO) {
        String recordCount = null;
        try {
            recordCount = approvalDaoImplObj.recordCount(appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }

    public void approverSubmit(ApprovalDTO appDTO) {
        try {
            approvalDaoImplObj.approverSubmit(appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApprovalDTO getProjectDetails(ApprovalDTO appDTO) {
        ApprovalDTO prjDetails = null;
        try {
            prjDetails = approvalDaoImplObj.getProjectDetails(appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prjDetails;
    }

    public ApprovalDTO getTimeSheetDetails(ApprovalDTO appDTO) {
        System.out.println("in service impl "+appDTO.getCheckedId());
        ApprovalDTO timesheetDetails = null;
        try {
            timesheetDetails = approvalDaoImplObj.getTimeSheetDetails(appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timesheetDetails;
    }

    public ApprovalDTO getBillingDetails(ApprovalDTO appDTO) {
        ApprovalDTO billingDetails = null;
        try {
            billingDetails = approvalDaoImplObj.getBillingDetails(appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billingDetails;
    }

    public ApprovalDTO getBillingAdviceDetails(ApprovalDTO appDTO) {
        ApprovalDTO billingAdviceDetails = null;
        try {
            billingAdviceDetails = approvalDaoImplObj.getBillingAdviceDetails(appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return billingAdviceDetails;
    }
    
    public void updateBillingDetails(ApprovalDTO appDTO) {
        try {
            approvalDaoImplObj.updateBillingDetails(appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void insertBillingDetails(ApprovalDTO appDTO) {
        try {
            approvalDaoImplObj.insertBillingDetails(appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      public ApprovalDTO getEmployeeStructure(ApprovalDTO appDTO) {
           ApprovalDTO dto = null;
        try {
            dto = approvalDaoImplObj.getEmployeeStructure(appDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
         return dto;
    }
      public List<TimesheetDTO> getStatusList(){
          List<TimesheetDTO> list = null;
          try{
              list = approvalDaoImplObj.getStatusList();
          }catch(Exception e){
              e.printStackTrace();
          }
          return list;
      }
       
      public int savedDataRemove(String timeSheetId){
          try{
              approvalDaoImplObj.savedDataRemove(timeSheetId);
          }catch(Exception e){
              e.printStackTrace();
          }
        return 1;
      }
      
      public String get_WFH_Eligible(SearchCriteriaDTO searchObj){
          String eligible = "";
          try{
              eligible = approvalDaoImplObj.get_WFH_Eligible(searchObj);
          }catch(Exception e){
              e.printStackTrace();
          }
        return eligible;
      }
}