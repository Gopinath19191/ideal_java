/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.requestor.dao;

import com.defiance.ideal.application.util.CommonConfigurations;
import com.defiance.requestor.dto.RequestorDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


/**
 *
 * @author 14355
 */
public class RequestorDAOImpl extends SqlMapClientDaoSupport {
 public List<RequestorDTO> getrequestList(String requestorId,String referenceNo) {
        List<RequestorDTO> requestDetails = null;
        RequestorDTO requestData = new RequestorDTO();
        try {
           
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestDetails;
    }
   




 public List<RequestorDTO> getReferenceNo() {
        List<RequestorDTO> referenceNo = null;
        try {
            System.out.println("INside DAOIMPL");
//            referenceNo = getSqlMapClientTemplate().queryForList("RequestMap.getReferenceNo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return referenceNo;
    }
// public void insertRequestDetails(RequestorDTO requestformData) {
//        try {
//            //employeeFormData.setSubmitDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
//            System.out.println(":::"+requestformData.getRequestDate()+"----"+requestformData.getButtonName());
//            //employeeFormData.setResignedDate(CommonFunctions.changeDateFormatToDB(employeeFormData.getResignedDate()));
//            if (requestformData.getButtonName().equals("Save")) {
//                requestformData.setRequestStatus(Integer.toString(CommonConfigurations.REQUEST_SAVE_STATUS));
//            }else if(requestformData.getButtonName().equals("Submit")){
//                requestformData.setRequestStatus(Integer.toString(CommonConfigurations.REQUEST_SUBMIT_STATUS));
//            }// To check the new record or existing
//            if(requestformData.getEmpId()==null || requestformData.getEmpId().equals("")){
//                getSqlMapClientTemplate().insert("requestDetails.insertRequestDetails", requestformData);
//            }
////            else{
////                getSqlMapClientTemplate().update("requestDetails.updateExitEmpDetails", requestformData);
////            }// Condtion to check the mail part
//            if (requestformData.getButtonName().equals("Submit")) {
//                // Here Mail Code has to come
//                List rmModuleList = new ArrayList();
//               // String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
//               // this.triggerMail(rmModuleList, toMailApprovalModules, employeeFormData, "empToRM", "empToRM", "empToRM", CommonConfigurations.rmModuleName, employeeFormData.getEmpId());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
 public void insertRequestDetails(RequestorDTO requestformData) {
        try {
            if(requestformData.getEmpId()== null || requestformData.getEmpId().equals("")){
               getSqlMapClientTemplate().insert("requestDetails.insertRequestDetails", requestformData);
            }
//            else{
//                getSqlMapClientTemplate().update("requestDetails.insertRequestDetails", requestformData);
//            }// Condtion to check the mail part
            if (requestformData.getButtonName() != null) {
                // Here Mail Code has to come
//                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
//                this.triggerMail(rmModuleList, toMailApprovalModules, employeeFormData, "empToRM", "empToRM", "empToRM", CommonConfigurations.rmModuleName, employeeFormData.getEmpId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 public RequestorDTO getRequestDetails(String employeeId) {
        RequestorDTO requestDetails = null;
        try {
           // requestDetails = (RequestorDTO) getSqlMapClientTemplate().queryForObject("requestDetails.getRequestDetails",employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestDetails;
    }
   public String getRequestorName(String employeeId) {
        String RequestorName = null;
        try {
           // RequestorName = (String) getSqlMapClientTemplate().queryForObject("requestDetails.RequestorName",employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RequestorName;
    }
}
