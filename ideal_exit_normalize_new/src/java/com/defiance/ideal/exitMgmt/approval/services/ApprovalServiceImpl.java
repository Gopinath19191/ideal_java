/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.approval.services;

import com.defiance.ideal.exitMgmt.approval.dao.ApprovalDAOImpl;
import com.defiance.ideal.exitMgmt.approval.dto.ApprovalDTO;
import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 14583
 */
public class ApprovalServiceImpl implements ApprovalService {

    private ApprovalDAOImpl approvalDaoImplObj;

    public ApprovalDAOImpl getApprovalDaoImplObj() {
        return approvalDaoImplObj;
    }

    public void setApprovalDaoImplObj(ApprovalDAOImpl approvalDaoImplObj) {
        this.approvalDaoImplObj = approvalDaoImplObj;
    }

     public List<EmployeeDTO> getRegnEmpList(String employeeId,String moduleId,String approveType) {
     List<EmployeeDTO> employeeDetails = null;
     try {
     employeeDetails = approvalDaoImplObj.getRegnEmpList(employeeId,moduleId,approveType);
     } catch (Exception e) {
     e.printStackTrace();
     }
     return employeeDetails;
 }
  public void saveRmData(ApprovalDTO rmFormData)
    {
        try {
            approvalDaoImplObj.saveRmData(rmFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveFinData(ApprovalDTO finFormData)
    {
        try {
            approvalDaoImplObj.saveFinData(finFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void saveFinMultipleData(String[] primaryKey,String[] otherCategory,String[] other,String[] otherAmt,String[] otherRemarks,ApprovalDTO finFormData)
    {
        try {
            approvalDaoImplObj.saveFinMultipleData(primaryKey,otherCategory,other,otherAmt,otherRemarks,finFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//     public void updateFinMultipleData(ApprovalDTO finFormData,String[] PrimaryotherCategory,String[] Primaryother,String[] PrimaryotherAmt,String[] PrimaryotherRemarks,String[] otherId)
//    {
//        try {
//            approvalDaoImplObj.updateFinMultipleData(finFormData,PrimaryotherCategory,Primaryother,PrimaryotherAmt,PrimaryotherRemarks,otherId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

        public void deleteFinMultipleData(String[] otherCategory,String[] other,String[] otherAmt,String[] otherRemarks,ApprovalDTO finFormData)
    {
        try {
            approvalDaoImplObj.deleteFinMultipleData(otherCategory,other,otherAmt,otherRemarks,finFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<ApprovalDTO> getfinMultipleData(String exitEmpId)
    {

    List<ApprovalDTO> finMultipleData = null;
        try {
            finMultipleData =  approvalDaoImplObj.getfinMultipleData(exitEmpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finMultipleData;
    }
    public void saveNsData(ApprovalDTO nsFormData)
    {
        try {
            approvalDaoImplObj.saveNsData(nsFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAdData(ApprovalDTO adFormData)
    {
        try {
            approvalDaoImplObj.saveAdData(adFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveRmClrData(ApprovalDTO rmClrFormData)
    {
        try {
            approvalDaoImplObj.saveRmClrData(rmClrFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveHrData(ApprovalDTO hrFormData,HttpServletRequest request, HttpServletResponse response)
    {
        try {
            approvalDaoImplObj.saveHrData(hrFormData,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApprovalDTO getRmActionData(String exitEmpId)
    {
        ApprovalDTO rmActionData = null;
        try {
            rmActionData = approvalDaoImplObj.getRmActionData(exitEmpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rmActionData;
    }

    public ApprovalDTO getFinActionData(String exitEmpId)
    {
        ApprovalDTO finActionData = null;
        try {
            finActionData = approvalDaoImplObj.getFinActionData(exitEmpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finActionData;
    }
    
    public ApprovalDTO getNSActionData(String exitEmpId)
    {
        ApprovalDTO nsActionData = null;
        try {
            nsActionData = approvalDaoImplObj.getNSActionData(exitEmpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nsActionData;
    }
    
//    public ApprovalDTO getRmClrData(String exitEmpId)
//    {
//        ApprovalDTO rmClrData = null;
//        try {
//            rmClrData = approvalDaoImplObj.getRmClrData(exitEmpId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rmClrData;
//    }
//    
    public ApprovalDTO getAdminActionData(String exitEmpId)
    {
        ApprovalDTO adActionData = null;
        try {
            adActionData = approvalDaoImplObj.getAdminActionData(exitEmpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adActionData;
    }
    
    public ApprovalDTO getHrActionData(String exitEmpId)
    {
        ApprovalDTO hrActionData = null;
        try {
            hrActionData = approvalDaoImplObj.getHrActionData(exitEmpId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hrActionData;
    }
    
    public List<ApprovalDTO> getSurveyQuestions(String exitEmpId){
        List<ApprovalDTO> surveyQuestions=null;
        try{
            surveyQuestions=(List<ApprovalDTO>)  approvalDaoImplObj.getSurveyQuestions(exitEmpId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return  surveyQuestions;
    }
    public List<ApprovalDTO> getSurveyAnswers(){
        List<ApprovalDTO> surveyAnswers=null;
        try{
            surveyAnswers=(List<ApprovalDTO>)  approvalDaoImplObj.getSurveyAnswers();
        } catch(Exception e){
            e.printStackTrace();
        }
        return  surveyAnswers;
    }
   public void saveSurveyData(String[] questionId,String[] empAnswer,ApprovalDTO surveyData){
       try {
            approvalDaoImplObj.saveSurveyData(questionId,empAnswer,surveyData);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
   public void submitSurveyData(ApprovalDTO surveyFormData){
       try {
            approvalDaoImplObj.submitSurveyData(surveyFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    //public List<EmployeeDTO> getRegnEmpList(String employeeId, String moduleId, String approveType) {
        //return approvalDaoImplObj.getRegnEmpList(employeeId, moduleId, approveType);
    //}

    public void addFileDb(String fileName, String contentType, String referenceName, int refId, String moduleName) {
        approvalDaoImplObj.addFileDb(fileName,contentType,referenceName,refId,moduleName);
    }

    public ApprovalDTO getFile(int exitEmpId, String EXIT_MODULE_CODE) {
        return approvalDaoImplObj.getFile(exitEmpId,EXIT_MODULE_CODE);
    }
	public void submitExitSurvey(ApprovalDTO formData,String session_empid)
    {
        try {
            approvalDaoImplObj.submitExitSurvey(formData,session_empid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public List<ApprovalDTO> getExitEmployeeStatus(){
        List<ApprovalDTO> dto=null;
        try {
        dto=approvalDaoImplObj.getExitEmployeeStatus();}
        catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }
    public void updateFileDb(String fileName, String contentType, String referenceName, int refId, String moduleName) {
        approvalDaoImplObj.updateFileDb(fileName,contentType,referenceName,refId,moduleName);
    }
    public List<ApprovalDTO> getEmploymentStatus(){
       List<ApprovalDTO> dto=null;
        try {
        dto=approvalDaoImplObj.getEmploymentStatus();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dto; 
    }
     public void publishServLetter(ApprovalDTO hrFormData,HttpServletRequest request, HttpServletResponse response)
    {
        try {
            approvalDaoImplObj.publishServLetter(hrFormData,request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
       // return null;
    }
}
