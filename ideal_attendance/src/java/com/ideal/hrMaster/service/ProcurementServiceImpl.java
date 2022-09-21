/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.service;

import com.ideal.hrMaster.dao.ProcurementDao;
import com.ideal.hrMaster.dao.ProcurementDaoImpl;
import com.ideal.hrMaster.dto.EmployeeDetailsDto;
import com.ideal.hrMaster.dto.ProcurementDto;
import com.ideal.hrMaster.dto.ProcurementPackagesDto;
import com.ideal.hrMaster.util.SendMail;
import com.ideal.hrMaster.util.MailMessages;
import com.lowagie.text.Phrase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 16221
 */
public class ProcurementServiceImpl extends MultiActionController implements ProcurementService{
    ProcurementDao dao;

    public ProcurementDao getDao() {
        return dao;
    }

    public void setDao(ProcurementDao dao) {
        this.dao = dao;
    }
        
    /**
     *
     * @return
     */
    @Override
    public List<ProcurementPackagesDto> getProcurementType(){
        List<ProcurementPackagesDto> procurement_type = dao.getProcurementType();
        return procurement_type;
    }
    @Override
    public List<ProcurementDto> getProcurementList(String filterData){
        List<ProcurementDto> procurement_list = dao.getProcurementList(filterData);
        return procurement_list;
    }
    
    @Override
    public List<EmployeeDetailsDto> getEmployeeDetails(String filterData){
        List<EmployeeDetailsDto> employee_details = dao.getEmployeeDetails(filterData);
        return employee_details;
    }
    public List<ProcurementPackagesDto> getBillableType(){
        List<ProcurementPackagesDto> billable_type = dao.getBillableType();
        return billable_type;
    }
    public List<ProcurementPackagesDto> getServiceType(){
        List<ProcurementPackagesDto> service_type = dao.getServiceType();
        return service_type;
    }
    public List<ProcurementPackagesDto> getOrderType(){
        List<ProcurementPackagesDto> order_type = dao.getOrderType();
        return order_type;
    }
    public List<ProcurementPackagesDto> getDocumentType(){
        List<ProcurementPackagesDto> document_type = dao.getDocumentType();
        return document_type;
    }
    public List<ProcurementPackagesDto> getdeliveryLocation(){
        List<ProcurementPackagesDto> delivery_location = dao.getdeliveryLocation();
        return delivery_location;
    }
    public List<ProcurementPackagesDto> getCurrency(){
        List<ProcurementPackagesDto> currency = dao.getCurrency();
        return currency;
    }
    public List<ProcurementDto> getProcurementDetails(String filterData){
        List<ProcurementDto> procurement_details = dao.getProcurementDetails(filterData);
        return procurement_details;
    }
    public List<ProcurementPackagesDto> getIteamDetails(String filterData){
        List<ProcurementPackagesDto> iteam_details = dao.getIteamDetails(filterData);
        return iteam_details;
    }
    public List<ProcurementPackagesDto> getAttachmentDetails(String filterData){
        List<ProcurementPackagesDto> attachment_details = dao.getAttachmentDetails(filterData);
        return attachment_details;
    }
    public List<ProcurementPackagesDto> getPoAttachmentDetails(String filterData){
        List<ProcurementPackagesDto> attachment_details = dao.getPoAttachmentDetails(filterData);
        return attachment_details;
    }
    public String insertProcurementDetails(ProcurementDto filterData){
        String id = dao.insertProcurementDetails(filterData);
        return id;
    }
    public void insertProcurementIteams(ProcurementDto filterData){
        dao.insertProcurementIteams(filterData);
    }
    public String addFileDb(String fileName, String fileType, String referenceName, int referenceId, String moduleName){
        String lastFileInsertId = null;
        try{
            lastFileInsertId = (String) dao.addFileDb(fileName,fileType,referenceName,referenceId,moduleName);
        }catch(Exception e){
             e.printStackTrace();
        }
        return lastFileInsertId;
    }
    public void insertProcurementAttachmentDetails(ProcurementDto filterData){
        dao.insertProcurementAttachmentDetails(filterData);
    }
    public String updateProcurementDetails(ProcurementDto filterData){
        String id = dao.updateProcurementDetails(filterData);
        return id;
    }
    public void updateProcurementIteams(ProcurementDto filterData){
        dao.updateProcurementIteams(filterData);
    }
    public List<ProcurementDto> getProcurementDetailsView(String pp_id){
        List<ProcurementDto> procurement_details = dao.getProcurementDetailsView(pp_id);
        return procurement_details;
    }
    public List<ProcurementDto> getProcurementRMList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = dao.getProcurementRMList(filterData);
        return procurement_list;
    }
    public List<ProcurementDto> getProcurementRMProcessedList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = dao.getProcurementRMProcessedList(filterData);
        return procurement_list;
    }
    public List<ProcurementDto> getProcurementBUHList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = dao.getProcurementBUHList(filterData);
        return procurement_list;
    }
    public List<ProcurementDto> getProcurementBUHProcessedList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = dao.getProcurementBUHProcessedList(filterData);
        return procurement_list;
    }
    public List<ProcurementDto> getProcurementAdminList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = dao.getProcurementAdminList(filterData);
        return procurement_list;
    }
    public void approveRMProcurementDetails(ProcurementDto filterData){
        dao.approveRMProcurementDetails(filterData);
        String buh_id = dao.getBuhId(filterData.getId());
        String emp_id = dao.getEmployeeId(filterData.getId());
        if((buh_id.equals(filterData.getRm_id()) || buh_id.equals(emp_id)) && filterData.getStatus().equals("a")){
            filterData.setBuh_id(buh_id);
            filterData.setStatus("e");
            approveBUHProcurementDetails(filterData);
        }else{
            ArrayList<ProcurementPackagesDto> connectionRes;
            connectionRes =  dao.getMailDetails();
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
            MailMessages mailMessageObj = new MailMessages();
            try{
                List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(emp_id);
                String pr_code = dao.getProcurementCode(filterData.getId());
                HttpServletRequest requestObj = null;
                String mailSubject =null;
                if(filterData.getStatus().equals("a")){
                    mailSubject = pr_code + " - Purchase Request is waiting for approval";
                    String mailMessage = mailMessageObj.getMessage(requestObj, "", approverDetails.get(0).getEmployee_name(), approverDetails.get(0).getRm_name(), filterData.getStatus(), approverDetails.get(0).getBuh_name());
                    mailObj.smtpMail(approverDetails.get(0).getBuh_email_id(), mailSubject, mailMessage, approverDetails.get(0).getRm_email_id()+","+approverDetails.get(0).getEmployee_email_id());
                }else{
                   mailSubject = pr_code + " - Purchase Request is Sent back";
                    String mailMessage = mailMessageObj.getMessage(requestObj, "", approverDetails.get(0).getEmployee_name(), approverDetails.get(0).getRm_name(), filterData.getStatus(), approverDetails.get(0).getBuh_name());
                    mailObj.smtpMail(approverDetails.get(0).getEmployee_email_id(), mailSubject, mailMessage, approverDetails.get(0).getRm_email_id());
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }
    public void approveBUHProcurementDetails(ProcurementDto filterData){
        dao.approveBUHProcurementDetails(filterData);
        String emp_id = dao.getEmployeeId(filterData.getId());
        ArrayList<ProcurementPackagesDto> connectionRes;
        connectionRes =  dao.getMailDetails();
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
        MailMessages mailMessageObj = new MailMessages();
        try{
            List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(emp_id);
            String pr_code = dao.getProcurementCode(filterData.getId());
            HttpServletRequest requestObj = null;
            String mailSubject =null;
            if(filterData.getStatus().equals("e")){
                List<ProcurementPackagesDto> prAdminDetails = dao.getPrAdminDetails();
                mailSubject = pr_code + " - Purchase Request is approved";
                String mailMessage = mailMessageObj.getMessage(requestObj, "", "Procurement Head", approverDetails.get(0).getEmployee_name(), filterData.getStatus(), approverDetails.get(0).getBuh_name());
                mailObj.smtpMail(prAdminDetails.get(0).getEmployee_email_id(), mailSubject, mailMessage, approverDetails.get(0).getRm_email_id()+","+approverDetails.get(0).getEmployee_email_id()+","+approverDetails.get(0).getBuh_email_id());
            }else{
                mailSubject = pr_code + " - Purchase Request is Sent back";
                String mailMessage = mailMessageObj.getMessage(requestObj, "", approverDetails.get(0).getEmployee_name(), approverDetails.get(0).getRm_name(), filterData.getStatus(), approverDetails.get(0).getBuh_name());
                mailObj.smtpMail(approverDetails.get(0).getEmployee_email_id(), mailSubject, mailMessage, approverDetails.get(0).getRm_email_id());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void approveAdminProcurement(ProcurementDto filterData){
        dao.approveAdminProcurement(filterData);
    }
    
    public String getProcurementCode(String id){
        String pr_code = dao.getProcurementCode(id);
        return pr_code;
    }
    
    public String getManagerId(String emp_id){
        String mng_id = dao.getManagerId(emp_id);
        return mng_id;
    }
    public List<ProcurementDto> getProcurementReport(String status){
        List<ProcurementDto> report_list = dao.getProcurementReport(status);
        return report_list;
    }
}
