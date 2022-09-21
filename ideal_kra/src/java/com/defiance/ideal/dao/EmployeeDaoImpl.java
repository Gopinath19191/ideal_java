/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.DetailsDto;
import com.defiance.ideal.dto.EmployeeDto;
import com.defiance.ideal.dto.kraDto;
import com.defiance.ideal.util.CommonFunctions;
import com.defiance.ideal.util.SendMail;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 14053
 */
public class EmployeeDaoImpl extends SqlMapClientDaoSupport implements EmployeeDao {
    
    public boolean authenticate(EmployeeDto authenParams) {
        try {
            EmployeeDto userAuthentication = null;
            EmployeeDto groupAuthentication = null;
            EmployeeDto loginAuthentication = null;
            
            loginAuthentication = (EmployeeDto) getSqlMapClientTemplate().queryForObject("queryMap.loginCheck", authenParams);
            
            if (loginAuthentication == null) {
                System.out.println("Not Logged IN");
                return false;
            } else {
                userAuthentication = (EmployeeDto) getSqlMapClientTemplate().queryForObject("queryMap.authenticateUser", authenParams);
                if (userAuthentication == null || userAuthentication.getuCreate() == null || userAuthentication.getuCreate().equals("-1")) {
                    groupAuthentication = (EmployeeDto) getSqlMapClientTemplate().queryForObject("queryMap.authenticateGroup", authenParams);
                    if (groupAuthentication == null || groupAuthentication.getgCreate() == null || groupAuthentication.getgCreate().equals("-1")) {
                        System.out.println("Group Auth Failed");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public EmployeeDto getUserDetails(String empId) {
        System.out.println("Empppp" + empId);
        return (EmployeeDto) getSqlMapClientTemplate().queryForObject("queryMap.getUserDetails", empId);
    }
    
    public List<DetailsDto> getInstituteList() {
        
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getInstituteList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    public List<kraDto> getStructureDetails(int parentId)
    {
        List<kraDto>  list = null;
        
              list = (List<kraDto>) getSqlMapClientTemplate().queryForList("queryMap.getLevelOneStructure", parentId);
        
        return list;
        
    }
    public List<kraDto> getKraList(String employeeId, String kraId){
        List<kraDto> kraList = null;
        System.out.println("employee id "+employeeId);
       
        kraDto details = new kraDto();
        details.setEmployeeId(employeeId);
        if(kraId==null){
            details.setKra_id("");
        }else{
             details.setKra_id(kraId);
        }
        System.out.println("kra id "+kraId);
        try{
            kraList = getSqlMapClientTemplate().queryForList("queryMap.getKraList", details);
        } catch(Exception e){
            e.printStackTrace();
        }
        return kraList;
    }
    
    public List<kraDto> getEmployeeKraDetails(String employeeId, String year, int quarterId){
        List<kraDto> kraList = null;
        kraDto details = new kraDto();
        details.setEmployeeId(employeeId);
        details.setFinancial_year(year);
        details.setQuarter_id(Integer.toString(quarterId));
        try{
            kraList = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeKraDetails", details);
        } catch(Exception e){
            e.printStackTrace();
        }
        return kraList;
    }
     public List<kraDto> checkEmployeeKraDetails(String employeeId, String year, int quarterId){
        List<kraDto> kraList = null;
        kraDto details = new kraDto();
        details.setEmployeeId(employeeId);
        details.setFinancial_year(year);
        details.setQuarter_id(Integer.toString(quarterId));
        try{
            kraList = getSqlMapClientTemplate().queryForList("queryMap.checkEmployeeKraDetails", details);
        } catch(Exception e){
            e.printStackTrace();
        }
        return kraList;
    }
            
    public List<kraDto> getIndividualKraDetails(String kraId){
        List<kraDto> kraList = null;
        try{
            kraList = getSqlMapClientTemplate().queryForList("queryMap.getIndividualKraDetails", kraId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return kraList;
    }
    public String getMaxBandId(){
        String maxBand=null;
        try{
            maxBand = (String) getSqlMapClientTemplate().queryForObject("queryMap.getMaxBandId");
        }catch(Exception e){
            e.printStackTrace();
        }
        return maxBand;
    }
    public kraDto getAppraiseerDetails(EmployeeDto formData){
        kraDto appraisser_details=null;
        try{
            appraisser_details = (kraDto) getSqlMapClientTemplate().queryForObject("queryMap.getAppraiseerDetails",formData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return appraisser_details;
    }
    public String insertIntoAaEligibleAssociates(kraDto appraisee_details){
        String lastInsertId=null;
        try{
            lastInsertId = (String) getSqlMapClientTemplate().insert("queryMap.insertIntoAaEligibleAssociates", appraisee_details);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lastInsertId;
    }
    public String getAaExsting(EmployeeDto formData){
        String count = null;
        try{
            count = (String) getSqlMapClientTemplate().queryForObject("queryMap.getAaExisting",formData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }
    public String insertIntoAaKra(EmployeeDto kraDetails){
        String lastInsertId=null;
        try{
            lastInsertId = (String) getSqlMapClientTemplate().insert("queryMap.insertAaKra", kraDetails);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lastInsertId;
    }
    public String insertIntoAaKraMaster(EmployeeDto kraDetails){
        String lastInsertId=null;
        try{
            lastInsertId = (String) getSqlMapClientTemplate().insert("queryMap.insertAaKraMaster", kraDetails);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lastInsertId;
    }
    public String insertIntoAaKraMapping(EmployeeDto kraDetails){
        String lastInsertId=null;
        try{
            lastInsertId = (String) getSqlMapClientTemplate().insert("queryMap.insertAaKraMapping", kraDetails);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lastInsertId;
    }
    public String getTotalWeightage(String kraId){
        String total_weightage=null;
        try{
            total_weightage = (String) getSqlMapClientTemplate().queryForObject("queryMap.getTotalWeightage", kraId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return total_weightage;
    }
    
    public List<kraDto> getFinancialYearList(String employeeId){
        List<kraDto> financialYearList = null;
        try{
            financialYearList = getSqlMapClientTemplate().queryForList("queryMap.getFinancialYearList", employeeId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return financialYearList;
    }
    @Override
    public List<kraDto> getQuarterList(){
        List<kraDto> quarterList = null;
        try{
            quarterList = getSqlMapClientTemplate().queryForList("queryMap.getQuarterList");
        } catch(Exception e){
            e.printStackTrace();
        }
        return quarterList;
    }
    public List<kraDto> getCmpStructData(String parentId) {
        List<kraDto> cmpStructList = null;
        try {
            cmpStructList = (List<kraDto>) getSqlMapClientTemplate().queryForList("queryMap.getCmpStructData", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmpStructList;
    }
    public List<kraDto> getRmEmployeeList(EmployeeDto formData){
        List<kraDto> rmEmployeeList = null;
        try{
            rmEmployeeList = getSqlMapClientTemplate().queryForList("queryMap.getRmEmployeeList", formData);
        } catch(Exception e){
            e.printStackTrace();
        }
        return rmEmployeeList;
    }
    public List<kraDto> getReportList(EmployeeDto formData){
        List<kraDto> rmEmployeeList = null;
        if(formData.getPracticeGroup()==null){
            System.out.println("inside NULLLL");
            formData.setPracticeGroup("");
        }
        System.out.println("test@@@@@@@ "+formData.getCompanyStructureId());
        if("1".equals(formData.getCompanyStructureId()) && "".equals(formData.getPracticeGroup()) ){
            formData.setPracticeGroup("2,5");
        }
        if("12".equals(formData.getCompanyStructureId()) && "".equals(formData.getPracticeGroup())){
            formData.setPracticeGroup("13,15,19,21,23,43");
        }
        try{
            rmEmployeeList = getSqlMapClientTemplate().queryForList("queryMap.getReportList", formData);
        } catch(Exception e){
            e.printStackTrace();
        }
        return rmEmployeeList;
    }
    public List<kraDto> getRMFinancialYearList(String employeeId){
        List<kraDto> rmFinancialYearList = null;
        try{
            rmFinancialYearList = getSqlMapClientTemplate().queryForList("queryMap.getRMFinancialYearList", employeeId);
        } catch(Exception e){
            e.printStackTrace();
        }
        return rmFinancialYearList;
    }
    public List<kraDto> getEmployeeKraForExcel(String employeeId, String year, String quaterId){
        List<kraDto> getEmployeeKraForExcel = null;
        kraDto details = new kraDto();
        details.setEmployeeId(employeeId);
        details.setFinancial_year(year);
        details.setQuarter_id(quaterId);
        try{
            getEmployeeKraForExcel = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeKraForExcel", details);
        } catch(Exception e){
            e.printStackTrace();
        }
        return getEmployeeKraForExcel;
    }
    public List<DetailsDto> getUniversityList() {
        
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getUniversityList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List<DetailsDto> getDegreeList() {
        
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getDegreeList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List<DetailsDto> getCourseList() {
        
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getCourseList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List<DetailsDto> getCountryList() {
        
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getCountryList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List<DetailsDto> getVisaList() {
        
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getVisaList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List<DetailsDto> getBankList() {
        
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getBankList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List getStreamList() {
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getStreamList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List getSkillList(String val) {
        List<DetailsDto> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("queryMap.getSkillList", val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List getStateList(String val, String region) {
        List<DetailsDto> template = null;
        try {
            if (region.equals("city")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getCityByRegion", val);
            } else {
                template = getSqlMapClientTemplate().queryForList("queryMap.getRegionByCountrty", val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List<DetailsDto> getEmployeeSearchList(String val) {
        
        List<DetailsDto> template = null;
        try {
            String key = val + "%";
            template = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeSearchList", key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    @Override
    public void updateCertification(EmployeeDto formData, String[] certificationId, String[] degree, String[] qualification, String[] year_of_passing, String[] institution, String[] percentage, String[] remarks, String[] university, int[] deletedTR) {
        for (int i = 0; i < certificationId.length; i++) {
            if ((deletedTR[i] == 0 && (!institution[i].equals("") || !percentage[i].equals("") || !qualification[i].equals("") || !year_of_passing[i].equals("") || !university[i].equals(""))) || (deletedTR[i] == 1 && !certificationId[i].equals(""))) {
                formData.setCertificationId(certificationId[i]);
                formData.setDegree(degree[i]);
                formData.setQualification(qualification[i]);
                formData.setYear_of_passing(year_of_passing[i]);
                formData.setInstitution(institution[i]);
                formData.setPercentage(percentage[i]);
                formData.setRemarks(remarks[i]);
                formData.setUniversity(university[i]);
                formData.setDeletedTR(deletedTR[i]);
                if (!formData.getCertificationId().equals("")) {
                    getSqlMapClientTemplate().update("queryMap.updateCertification", formData);
                } else {
                    getSqlMapClientTemplate().insert("queryMap.addCertification", formData);
                }
            }
        }
    }
    
    public void updateEmployeeKra(EmployeeDto formData, String[] certificationId, String[] qualification, String[] krauom, String[] kratarget, String[] percentage, int[] deletedTR, String actionValue) {
        String lastInsertId = null;
        if (formData.getKra_id().equals("")) {
            lastInsertId = (String) getSqlMapClientTemplate().insert("queryMap.addEmployeeKra", formData);
            formData.setKra_id(lastInsertId);
        } else {
            getSqlMapClientTemplate().update("queryMap.updateEmployeeKra", formData);
        }
        
        for (int i = 0; i < qualification.length; i++) {
            if ((deletedTR[i] == 0 && (!percentage[i].equals(""))) || (deletedTR[i] == 1 && !qualification[i].equals(""))) {
                formData.setKra_id(formData.getKra_id());
                formData.setCertificationId(certificationId[i]);
                formData.setQualification(qualification[i]);
                formData.setKrauom(krauom[i]);
                formData.setKratarget(kratarget[i]);
                formData.setPercentage(percentage[i]);
                formData.setDeletedTR(deletedTR[i]);
                formData.setActionValue(actionValue);
                if (!formData.getCertificationId().equals("")) {
                    getSqlMapClientTemplate().update("queryMap.updateEmployeeKraDescription", formData);
                } else {
                    getSqlMapClientTemplate().insert("queryMap.addEmployeeKraDescription", formData);
                }
            }
        }
        if(formData.getActionValue().equals("m") || formData.getActionValue() == "m"){
            ArrayList<kraDto> connectionRes = null;
            List<kraDto> employeeMailDetails = null;
            String quarterName = null;
            try{
                connectionRes =  (ArrayList<kraDto>) getSqlMapClientTemplate().queryForList("queryMap.getConfigValueData","10");
            }catch(Exception e){
            
            }
            try{
               // employeeMailDetails = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeMailDetails",formData.getEmployeeId());
                employeeMailDetails = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeMailDetails",formData.getAssociateId());
            }catch(Exception e){
            
            }
            try{
                quarterName = (String) getSqlMapClientTemplate().queryForObject("queryMap.getQuarterName",formData.getQuarterId());
            }catch(Exception e){
            
            }
            
            String con_username = connectionRes.get(0).getConfigValue();
            String con_password = connectionRes.get(1).getConfigValue();
            String con_host = connectionRes.get(2).getConfigValue();
            String con_port = connectionRes.get(3).getConfigValue();
            SendMail mailObj = null;
            try {
                mailObj = new SendMail(con_username,con_password,con_host,con_port);
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            }
//            if(employeeMailDetails.size()>0){
//                try{
//                    String mailSubject = "KRA Submitted by "+employeeMailDetails.get(0).getEmployee_number()+" - "+employeeMailDetails.get(0).getEmployee_name()+" for Quarter "+quarterName+" Year "+formData.getYear()+".";
//                    String mailMessage = "Dear <b>"+employeeMailDetails.get(0).getManager_name()+"</b>,<br><br>";
//                        mailMessage += "KRA for the financial year "+formData.getYear()+" and quarter "+quarterName+" has been submitted by <b>"+employeeMailDetails.get(0).getEmployee_name()+"</b> is waiting for your approval.";
//                        mailMessage += "<br><br>Click on the link http://ideal.hindujatech.com/users/login to see the details <br>Ideal --> MSS --> KRA Approval";
//                        mailMessage += "<br><br>-Regards,<br><br>Ideal Admin.";
//                    mailObj.smtpMail(employeeMailDetails.get(0).getManager_mail_id(), mailSubject, mailMessage, employeeMailDetails.get(0).getEmployee_mail_id());
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            if(employeeMailDetails.size()>0){
//                try{
//                    String mailSubject = "KRA Submitted for "+employeeMailDetails.get(0).getEmployee_number()+" - "+employeeMailDetails.get(0).getEmployee_name()+" for Quarter "+quarterName+" Year "+formData.getYear()+".";
//                    String mailMessage = "Dear <b>"+employeeMailDetails.get(0).getEmployee_name()+"</b>,<br><br>";
//                        mailMessage += "KRA for the financial year "+formData.getYear()+" and quarter "+quarterName+" has been submitted and it is waiting for your acceptance.";
//                        mailMessage += "<br><br>Click on the link http://ideal.hindujatech.com/users/login to see the details <br>Ideal --> ESS --> Employee KRA";
//                        mailMessage += "<br><br>-Regards,<br><br>Ideal Admin.";
//                    mailObj.smtpMail(employeeMailDetails.get(0).getEmployee_mail_id(), mailSubject, mailMessage, employeeMailDetails.get(0).getManager_mail_id());
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
            
        }
        
    }
    
    public String getEmpid(String empNumber) {
        return (String) getSqlMapClientTemplate().queryForObject("queryMap.getEmpid", empNumber);
    }
    public String getBandid(String empNumber) {
        return (String) getSqlMapClientTemplate().queryForObject("queryMap.getBandid", empNumber);
    }
    public String getKraId(EmployeeDto formData) {
        //return (String) getSqlMapClientTemplate().queryForObject("queryMap.getKraid", formData);
        String kraId = null;
        List<kraDto> kraDetails = getSqlMapClientTemplate().queryForList("queryMap.getKraid", formData);
        if(kraDetails.size()>0){
            kraId = kraDetails.get(0).getKra_id();
        }
        return kraId;
    }
    @Override
    public void updateEmployeeKraByExcel(EmployeeDto formData) {
        String lastInsertId = null;
        formData.setActionValue("m");
        System.out.println("formData KRAID"+formData.getKra_id());
        if ( formData.getKra_id()==null || formData.getKra_id().equals("")) {
            lastInsertId = (String) getSqlMapClientTemplate().insert("queryMap.addEmployeeKraByExcel", formData);
            if(formData.getActionValue().equals("m") || "m".equals(formData.getActionValue())){
            ArrayList<kraDto> connectionRes = null;
            List<kraDto> employeeMailDetails = null;
            String quarterName = null;
            try{
                connectionRes =  (ArrayList<kraDto>) getSqlMapClientTemplate().queryForList("queryMap.getConfigValueData","10");
            }catch(Exception e){
            
            }
            try{
                employeeMailDetails = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeMailDetails",formData.getEmployeeId());
            }catch(Exception e){
            
            }
            try{
                quarterName = (String) getSqlMapClientTemplate().queryForObject("queryMap.getQuarterName",formData.getQuarterId());
            }catch(Exception e){
            
            }
            
            String con_username = connectionRes.get(0).getConfigValue();
            String con_password = connectionRes.get(1).getConfigValue();
            String con_host = connectionRes.get(2).getConfigValue();
            String con_port = connectionRes.get(3).getConfigValue();
            SendMail mailObj = null;
            try {
                mailObj = new SendMail(con_username,con_password,con_host,con_port);
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            }
//            if(employeeMailDetails.size()>0){
//                try{
//                    String mailSubject = "KRA Submitted for "+employeeMailDetails.get(0).getEmployee_number()+" - "+employeeMailDetails.get(0).getEmployee_name()+" for Quarter "+quarterName+" Year "+formData.getYear()+".";
//                    String mailMessage = "Dear <b>"+employeeMailDetails.get(0).getManager_name()+"</b>,<br><br>";
//                        mailMessage += "KRA for the financial year "+formData.getYear()+" and quarter "+quarterName+" has been submitted for <b>"+employeeMailDetails.get(0).getEmployee_name()+"</b> is waiting for your approval.";
//                        mailMessage += "<br><br>Click on the link http://ideal.hindujatech.com/users/login to see the details <br>Ideal --> MSS --> KRA Approval";
//                        mailMessage += "<br><br>-Regards,<br><br>Ideal Admin.";
//                    mailObj.smtpMail(employeeMailDetails.get(0).getManager_mail_id(), mailSubject, mailMessage, employeeMailDetails.get(0).getEmployee_mail_id());
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
            if(employeeMailDetails.size()>0){
                try{
                    String mailSubject = "KRA Submitted for "+employeeMailDetails.get(0).getEmployee_number()+" - "+employeeMailDetails.get(0).getEmployee_name()+" for Quarter "+quarterName+" Year "+formData.getYear()+".";
                    String mailMessage = "Dear <b>"+employeeMailDetails.get(0).getEmployee_name()+"</b>,<br><br>";
                        mailMessage += "KRA for the financial year "+formData.getYear()+" and quarter "+quarterName+" has been submitted and it is waiting for your acceptance.";
                        mailMessage += "<br><br>Click on the link http://ideal.hindujatech.com/users/login to see the details <br>Ideal --> ESS --> Employee KRA";
                        mailMessage += "<br><br>-Regards,<br><br>Ideal Admin.";
                    mailObj.smtpMail(employeeMailDetails.get(0).getEmployee_mail_id(), mailSubject, mailMessage, employeeMailDetails.get(0).getManager_mail_id());
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
        } else {
            System.out.println("here it comes for updation");
            getSqlMapClientTemplate().update("queryMap.updateEmployeeKraByExcel", formData);
        }
    }
    public ArrayList getBands() {
        return (ArrayList) getSqlMapClientTemplate().queryForList("queryMap.getBands");
    }
    public void updateEmployeeKraDescByExcel(EmployeeDto formData ) {
       getSqlMapClientTemplate().insert("queryMap.addEmployeeKraDescription", formData);
      
    }
    
    public void updateRmRemarks(EmployeeDto formData, String[] certificationId, String[] qualification,String actionValue){
        getSqlMapClientTemplate().update("queryMap.updateRmKra", formData);
        for (int i = 0; i < qualification.length; i++) {
            if (!qualification[i].equals("")) {
                formData.setCertificationId(certificationId[i]);
                formData.setQualification(qualification[i]);
                getSqlMapClientTemplate().update("queryMap.updateRmKraDescription", formData);
            }
        }
        
        List<kraDto> kraDetails = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeIdFromKraId", formData.getKra_id());
        ArrayList<kraDto> connectionRes = null;
        List<kraDto> employeeMailDetails = null;
        String quarterName = null;
        String approved_status = null;
        try{
            connectionRes =  (ArrayList<kraDto>) getSqlMapClientTemplate().queryForList("queryMap.getConfigValueData","10");
        }catch(Exception e){

        }
        try{
            employeeMailDetails = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeMailDetails",kraDetails.get(0).getEmployee_number());
        }catch(Exception e){

        }
        try{
            quarterName = (String) getSqlMapClientTemplate().queryForObject("queryMap.getQuarterName",kraDetails.get(0).getQuarter_id());
        }catch(Exception e){

        }
        if(formData.getActionValue().equals("a") || formData.getActionValue() == "a"){
            approved_status = "Approved";
        }else if(formData.getActionValue().equals("p") || formData.getActionValue() == "p"){
            approved_status = "Accepted";
        }else{
            approved_status = "Sent Back";
        }
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();
        SendMail mailObj = null;
        try {
            mailObj = new SendMail(con_username,con_password,con_host,con_port);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        if(employeeMailDetails.size()>0){
            try{
                String mailSubject = "KRA "+approved_status+" for Quarter "+quarterName+" Year "+kraDetails.get(0).getFinancial_year() +".";
                String mailMessage = "Dear <b>"+employeeMailDetails.get(0).getEmployee_name()+"</b>,<br><br>";
                    mailMessage += "KRA for the financial year "+kraDetails.get(0).getFinancial_year()+" and quarter "+quarterName+" has been "+approved_status+" by <b>"+employeeMailDetails.get(0).getManager_name()+"</b>.";
                    mailMessage += "<br><br>Click on the link http://ideal.hindujatech.com/users/login to see the details <br>Ideal --> ESS --> KRA";
                    mailMessage += "<br><br>-Regards,<br><br>Ideal Admin.";
                mailObj.smtpMail(employeeMailDetails.get(0).getEmployee_mail_id(), mailSubject, mailMessage, employeeMailDetails.get(0).getManager_mail_id());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void updateKraAccept(String kraId){
        try{
            getSqlMapClientTemplate().update("queryMap.updateKraAcceptance", kraId);
        }catch(Exception e){
            
        }
        List<kraDto> kraDetails = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeIdFromKraId", kraId);
        ArrayList<kraDto> connectionRes = null;
        List<kraDto> employeeMailDetails = null;
        String quarterName = null;
        try{
            connectionRes =  (ArrayList<kraDto>) getSqlMapClientTemplate().queryForList("queryMap.getConfigValueData","10");
        }catch(Exception e){

        }
        try{
            employeeMailDetails = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeMailDetails",kraDetails.get(0).getEmployee_number());
        }catch(Exception e){

        }
        try{
            quarterName = (String) getSqlMapClientTemplate().queryForObject("queryMap.getQuarterName",kraDetails.get(0).getQuarter_id());
        }catch(Exception e){

        }
        
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();
        SendMail mailObj = null;
        try {
            mailObj = new SendMail(con_username,con_password,con_host,con_port);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        if(employeeMailDetails.size()>0){
            try{
                String mailSubject = "KRA Accepted for Quarter "+quarterName+" Year "+kraDetails.get(0).getFinancial_year() +".";
                String mailMessage = "Dear <b>"+employeeMailDetails.get(0).getManager_name()+"</b>,<br><br>";
                    mailMessage += "KRA for the financial year "+kraDetails.get(0).getFinancial_year()+" and quarter "+quarterName+" has been Accepted by <b>"+employeeMailDetails.get(0).getEmployee_name()+"</b>.";
                    mailMessage += "<br><br>Click on the link http://ideal.hindujatech.com/users/login to see the details <br>Ideal --> ESS --> KRA RM";
                    mailMessage += "<br><br>-Regards,<br><br>Ideal Admin.";
                mailObj.smtpMail(employeeMailDetails.get(0).getManager_mail_id(), mailSubject, mailMessage, employeeMailDetails.get(0).getEmployee_mail_id());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public List getEmployeeDetails(EmployeeDto formData, String action) {
        List template = null;
        EmployeeDto empData = null;
        System.out.println("Access Type   ++++++++++ " + formData.getAccessType());
        System.out.println("Employee id   ++++++++++ " + formData.getEmployeeId());
        System.out.println("excel button name  ++++++++++ " + formData.getExcelbuttonName());
        try {
            if (action.equals("certification")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getCertificationDetails", formData);
            } else if (action.equals("employeeKra")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getEmployeeKraDetails", formData);
            } else if (action.equals("education")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getEducationDetails", formData);
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public String getRecordCount(EmployeeDto formData, String action) {
        String template = null;
        try {
            if (action.equals("certification")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getCertificationCount", formData);
            } else if (action.equals("work_experience")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getWorkExperienceCount", formData);
            } else if (action.equals("passport")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getPassportCount", formData);
            } else if (action.equals("visa")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getVisaCount", formData);
            } else if (action.equals("address")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getAddressCount", formData);
            } else if (action.equals("skills")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getSkillCount", formData);
            } else if (action.equals("education")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getEducationCount", formData);
            } else if (action.equals("bank_details")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getBankCount", formData);
            } else if (action.equals("emergency_contacts")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getEmergencyCount", formData);
            } else if (action.equals("licence_details")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getLicenceCount", formData);
            } else if (action.equals("pan_details")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getPanCount", formData);
            } else if (action.equals("adhar_details")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getAdharCount", formData);
            } else if (action.equals("prev_pf_details")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getPrevPfDetailsCount", formData);
            } else if (action.equals("kyc_details")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getKycDetailsCount", formData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    public int getMaxId() {
        int template = 0;
        try {
            template = (Integer) getSqlMapClientTemplate().queryForObject("queryMap.getMaxId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    public String getEmployeeName(String val) {
        String template = null;
        try {
            template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getEmployeeName", val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    public String getQuarterName(String val) {
        String template = null;
        try {
            template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getQuarterName", val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public String checkUnique(EmployeeDto formData, String value, String type, String requestId) {
        String template = null;
        try {
            formData.setUniqueId(requestId);
            formData.setUniqueValue(value);
            if (type.equals("passport")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getPassportUnique", formData);
            } else if (type.equals("visa")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getVisaUnique", formData);
            } else if (type.equals("account")) {
                template = (String) getSqlMapClientTemplate().queryForObject("queryMap.getAccountUnique", formData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List<DetailsDto> getEmployeeResultCount(String val) {
        List<DetailsDto> template = null;
        try {
            if (val.equals("certification")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getCertificationEmployeeCount");
            }
            if (val.equals("skills")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getSkillsEmployeeCount");
            }
            if (val.equals("education")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getEducationEmployeeCount");
            }
            if (val.equals("address")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getAddressEmployeeCount");
            }
            if (val.equals("bank_details")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getBankDetailsEmployeeCount");
            }
            if (val.equals("passport")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getPassportEmployeeCount");
            }
            if (val.equals("visa")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getVisaEmployeeCount");
            }
            if (val.equals("work_experience")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getWorkExperienceEmployeeCount");
            }
            if (val.equals("emergency_contacts")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getEmergencyContactsEmployeeCount");
            }
            if (val.equals("prev_pf_details")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getPrevPfEmployeeCount");
            }
            if (val.equals("kyc_details")) {
                template = getSqlMapClientTemplate().queryForList("queryMap.getKycEmployeeCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    @Override
    public void updateLicence(EmployeeDto formData, String licence_number, String licence_date_issue, String licence_date_expire, String remarks) {
        if (formData.getEmployeeId() != null) {
            formData.setLicence_number(licence_number);
            formData.setLicence_date_issue(licence_date_issue);
            formData.setLicence_date_expire(licence_date_expire);
            formData.setLicence_remarks(remarks);
            String proofType = "l";
            formData.setProofType(proofType);
            Integer id = null;
            id = (Integer) getSqlMapClientTemplate().queryForObject("queryMap.getLicenceEmpId", formData);
            if (id != null) {
                getSqlMapClientTemplate().update("queryMap.updateLicence", formData);
            } else {
                getSqlMapClientTemplate().insert("queryMap.addLicence", formData);
            }
        }
    }
    
    @Override
    public void updateVoterDetails(EmployeeDto formData, String voter_id, String remarks) {
        if (formData.getEmployeeId() != null) {
            formData.setVoter_id(voter_id);
            formData.setVoter_remarks(remarks);
            String proofType = "v";
            formData.setProofType(proofType);
            Integer id = null;
            id = (Integer) getSqlMapClientTemplate().queryForObject("queryMap.getVoterEmpId", formData);
            if (id != null) {
                getSqlMapClientTemplate().update("queryMap.updateVoterDetails", formData);
            } else {
                getSqlMapClientTemplate().insert("queryMap.addVoterDetails", formData);
            }
        }
    }
    
    @Override
    public void updateAdharDetails(EmployeeDto formData, String adhar_number, String remarks) {
        if (formData.getEmployeeId() != null) {
            formData.setAdhar_number(adhar_number);
            formData.setAdhar_remarks(remarks);
            String proofType = "a";
            formData.setProofType(proofType);
            Integer id = null;
            id = (Integer) getSqlMapClientTemplate().queryForObject("queryMap.getAdharEmpId", formData);
            if (id != null) {
                getSqlMapClientTemplate().update("queryMap.updateAdharDetails", formData);
            } else {
                getSqlMapClientTemplate().insert("queryMap.addAdharDetails", formData);
            }
        }
    }
    
    @Override
    public void updatePrevPfDetails(EmployeeDto formData) {
        try {
            if (formData.getEmployeeId() != null) {
                if (formData.getAlreadyPensionedMember().equals("No")) {
                    formData.setPfAlreadyWithdrawn(null);
                }
                if (formData.getInternationalWorkerStatus().equals("No")) {
                    formData.setCountryOfOrigin(null);
                    formData.setPassportNumber(null);
                    formData.setCountryOfIssue(null);
                    formData.setPassportValidFrom(null);
                    formData.setPassportValidUpto(null);
                }
                if ("".equals(formData.getPassportValidFrom())) {
                    formData.setPassportValidFrom(null);
                }
                if ("".equals(formData.getPassportValidUpto())) {
                    formData.setPassportValidUpto(null);
                }
                System.out.println("PFID " + formData.getPfId());
                if (!"".equals(formData.getPfId()) && formData.getPfId() != null) {
                    getSqlMapClientTemplate().update("queryMap.updatePrevPfDetails", formData);
                } else {
                    getSqlMapClientTemplate().insert("queryMap.addPrevPfDetails", formData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateKycDetails(EmployeeDto formData) {
        try {
            System.out.println("testdao===============>");
            if (formData.getEmployeeId() != null) {
                EmployeeDto idProof = null;
                
                if (formData.getDrivingLicence() != null) {
                    idProof = new EmployeeDto();
                    idProof.setEmployeeId(formData.getEmployeeId());
                    idProof.setProofType("l");
                    idProof.setProofNumber(formData.getDrivingLicence());
                    idProof.setExpireDate(CommonFunctions.changeDateFormatToDB(formData.getDrivingLicenceExpiryDate()));
                    idProof.setNameAsProof(formData.getNameAsdrivingLicence());
                    int count = (Integer) getSqlMapClientTemplate().queryForObject("queryMap.isDataIdProof", idProof);
                    if (count > 0) {
                        
                        getSqlMapClientTemplate().update("queryMap.updateKycDetailsToidproof", idProof);
                    } else {
                        
                        getSqlMapClientTemplate().insert("queryMap.addKycDetailsToidproofs", idProof);
                    }
                    
                }
                if (formData.getVoterId() != null) {
                    idProof = new EmployeeDto();
                    idProof.setEmployeeId(formData.getEmployeeId());
                    idProof.setProofType("v");
                    idProof.setProofNumber(formData.getVoterId());
                    idProof.setNameAsProof(formData.getNameAsVoterId());
                    int count = (Integer) getSqlMapClientTemplate().queryForObject("queryMap.isDataIdProof", idProof);
                    if (count > 0) {
                        getSqlMapClientTemplate().update("queryMap.updateKycDetailsToidproof", idProof);
                    } else {
                        
                        getSqlMapClientTemplate().insert("queryMap.addKycDetailsToidproofs", idProof);
                    }
                }
                if (formData.getAdhar() != null) {
                    idProof = new EmployeeDto();
                    idProof.setEmployeeId(formData.getEmployeeId());
                    idProof.setProofType("a");
                    idProof.setProofNumber(formData.getAdhar());
                    idProof.setNameAsProof(formData.getNameAsAdhar());
                    MultipartFile fileData = null;
                    fileData = formData.getFile();
                    
                    String filePath = CommonFunctions.fileUploadPath;
                    String filename = "";
                    //  if (!fileData.getOriginalFilename().equals("") && fileData != null) {
                    if (fileData != null && !fileData.getOriginalFilename().equals("")) {
                        Random random = new Random();
                        filename = "" + random.nextLong() + "~~" + fileData.getOriginalFilename();
                        if (!filename.equals("")) {
                            File fileToCreate = new File(filePath, filename);
                            if (!fileToCreate.exists()) {
                                FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                                fileOutStream.write(fileData.getBytes());
                                fileOutStream.flush();
                                fileOutStream.close();
                            }
                        }
                    }
                    System.out.println("string filename" + filename);                    
                    if (filename.equals("")) {
                        idProof.setFilename(formData.getFullFileName());
                    } else {
                        idProof.setFilename(filename);
                    }
                    
                    
                    int count = (Integer) getSqlMapClientTemplate().queryForObject("queryMap.isDataIdProof", idProof);
                    if (count > 0) {
                        System.out.println("inside update!!!!!!");
                        getSqlMapClientTemplate().update("queryMap.updateKycDetailsToidproof", idProof);
                    } else {
                        System.out.println("inside insert!!!!!!");
                        getSqlMapClientTemplate().insert("queryMap.addKycDetailsToidproofs", idProof);
                    }
                }
                if (formData.getRationId() != null) {
                    idProof = new EmployeeDto();
                    idProof.setEmployeeId(formData.getEmployeeId());
                    idProof.setProofType("r");
                    idProof.setProofNumber(formData.getRationId());
                    idProof.setNameAsProof(formData.getNameAsRation());
                    int count = (Integer) getSqlMapClientTemplate().queryForObject("queryMap.isDataIdProof", idProof);
                    if (count > 0) {
                        getSqlMapClientTemplate().update("queryMap.updateKycDetailsToidproof", idProof);
                    } else {
                        
                        getSqlMapClientTemplate().insert("queryMap.addKycDetailsToidproofs", idProof);
                    }
                }
                
            }
            getSqlMapClientTemplate().update("queryMap.updateKycDetailsToEmployees", formData);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void fileDownload(String fileName, String fileNameNew, String filePath, String fileType, HttpServletResponse response) {
        try {
            response.setContentType(fileType);
            response.setHeader("Content-disposition", "attachment; filename=\"" + fileNameNew + "\"");
            File file = new File(filePath + "\\" + fileName);
            FileInputStream fileIn = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            
            System.out.println("" + fileIn);
            byte[] outputByte = new byte[4096];
            while (fileIn.read(outputByte, 0, 4096) != -1) {
                out.write(outputByte, 0, 4096);
            }
            
            fileIn.close();
            out.flush();
            out.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String getLicenseNumber(EmployeeDto formData) {
        String license_number = "";
        try {
            license_number = (String) getSqlMapClientTemplate().queryForObject("queryMap.getLicenseNumber", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return license_number;
    }

    @Override
    public List getAssociatesList(EmployeeDto formData) {
        List<EmployeeDto> associatesList = null;
        try {           
            associatesList = getSqlMapClientTemplate().queryForList("queryMap.getAssociatesList", formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return associatesList;
    }
    public List<kraDto> getRMKraForExcel(kraDto filterData){
        List<kraDto> rmKraListForExcel = null;       
        try{
            rmKraListForExcel = getSqlMapClientTemplate().queryForList("queryMap.getRMKraForExcel", filterData);
        } catch(Exception e){
            e.printStackTrace();
        }
        return rmKraListForExcel;
    }
    public List<kraDto> getReportKraForExcel(kraDto filterData){
        List<kraDto> rmKraListForExcel = null;       
        try{
            rmKraListForExcel = getSqlMapClientTemplate().queryForList("queryMap.getReportKraForExcel", filterData);
        } catch(Exception e){
            e.printStackTrace();
        }
        return rmKraListForExcel;
    }
    
    public ArrayList getEmployeNumbers() {
        return (ArrayList) getSqlMapClientTemplate().queryForList("queryMap.getEmployeNumber");
    }
}
