/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.controller;

import com.ideal.hrMaster.dao.ProcurementDao;
import com.ideal.hrMaster.dao.ProcurementDaoImpl;
import com.ideal.hrMaster.dto.EmployeeDetailsDto;
import com.ideal.hrMaster.dto.ProcurementDto;
import com.ideal.hrMaster.dto.ProcurementPackagesDto;
import com.ideal.hrMaster.service.ProcurementService;
import com.ideal.hrMaster.service.ProcurementServiceImpl;
import com.ideal.hrMaster.util.CommonConfig;
import com.ideal.hrMaster.util.MailMessages;
import com.ideal.hrMaster.util.SendMail;
import com.lowagie.text.Phrase;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class PurchaseProcurementController extends MultiActionController{
    
    public ModelAndView addProcurement(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        if(employee_id!=null){
            List<ProcurementPackagesDto> procurement_type = service.getProcurementType();
            List<ProcurementPackagesDto> billable_type = service.getBillableType();
            List<ProcurementPackagesDto> service_type = service.getServiceType();
            List<ProcurementPackagesDto> order_type = service.getOrderType();
            List<ProcurementPackagesDto> document_type = service.getDocumentType();
            List<ProcurementPackagesDto> delivery_location = service.getdeliveryLocation();
            List<ProcurementPackagesDto> currency = service.getCurrency();
            List<EmployeeDetailsDto> employee_details = service.getEmployeeDetails(employee_id);
            mv = new ModelAndView("/addProcurement");
            mv.addObject("employeeDetails", employee_details);
            mv.addObject("procurementType", procurement_type);
            mv.addObject("ServiceType", service_type);
            mv.addObject("BillableType", billable_type);
            mv.addObject("OrderType", order_type);
            mv.addObject("DocumentType", document_type);
            mv.addObject("DeliveryLocation", delivery_location);
            mv.addObject("currency", currency);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView procurementList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView("/procurementList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        String employee_id = (String) session.getAttribute("EMP_ID");
        String successMessage = request.getParameter("successMessage");
        String errorMessage = request.getParameter("errorMessage");
        List<ProcurementDto> procurement_list = service.getProcurementList(employee_id);
        mv.addObject("details", procurement_list);
        mv.addObject("approverType", "e");
        mv.addObject("successMessage", successMessage);
        mv.addObject("errorMessage", errorMessage);
        return mv;
    }
    
    public ModelAndView editProcurement(HttpServletRequest request, HttpServletResponse response)throws Exception{
        ModelAndView mv = new ModelAndView("/editProcurement");
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String procurement_id = request.getParameter("pp_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        List<ProcurementPackagesDto> procurement_type = service.getProcurementType();
        List<ProcurementPackagesDto> billable_type = service.getBillableType();
        List<ProcurementPackagesDto> service_type = service.getServiceType();
        List<ProcurementPackagesDto> order_type = service.getOrderType();
        List<ProcurementPackagesDto> document_type = service.getDocumentType();
        List<ProcurementPackagesDto> delivery_location = service.getdeliveryLocation();
        List<ProcurementPackagesDto> currency = service.getCurrency();
        List<ProcurementDto> procurement_details = service.getProcurementDetails(procurement_id);
        List<ProcurementPackagesDto> iteam_details = service.getIteamDetails(procurement_details.get(0).getId());
        List<ProcurementPackagesDto> attachment_details = service.getAttachmentDetails(procurement_details.get(0).getId());
        List<EmployeeDetailsDto> employee_details = service.getEmployeeDetails(employee_id);
        mv.addObject("employeeDetails", employee_details);
        mv.addObject("result", procurement_details);
        mv.addObject("procurementType", procurement_type);
        mv.addObject("ServiceType", service_type);
        mv.addObject("BillableType", billable_type);
        mv.addObject("OrderType", order_type);
        mv.addObject("DocumentType", document_type);
        mv.addObject("DeliveryLocation", delivery_location);
        mv.addObject("currency", currency);
        mv.addObject("IteamDetails", iteam_details);
        mv.addObject("AttachmentDetails", attachment_details);
        return mv;
    }
    public ModelAndView saveProcurement(HttpServletRequest request, HttpServletResponse response, ProcurementDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String last_insert_id = null;
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        ProcurementDao dao = (ProcurementDaoImpl) ctx.getBean("ProcurementDao");
        if(employee_id!=null){
            filterData.setRequestor_id(Integer.parseInt(filterData.getRequestorId()));
            filterData.setSbu_id(Integer.parseInt(filterData.getSbuId()));
            filterData.setSub_sbu_id(Integer.parseInt(filterData.getSubSbuId()));
            System.out.println("iddddddd "+filterData.getId());
            last_insert_id = service.insertProcurementDetails(filterData);
            filterData.setLastInsertId(last_insert_id);
            if(last_insert_id!=null){
                for(int i=0;i<filterData.getIteams_ids().length;i++){
                    if((filterData.getIteams_deleted()[i]=="0" || filterData.getIteams_deleted()[i].equals("0")) &&
                        (filterData.getIteams_ids()[i]=="0" || filterData.getIteams_ids()[i].equals("0"))){
                        filterData.setIteam_description(filterData.getIteams_description()[i]);
                        filterData.setIteam_quantity(filterData.getIteams_quantity()[i]);
                        filterData.setIteam_amount(filterData.getIteams_amount()[i]);
                        service.insertProcurementIteams(filterData);
                    }
                }
                for(int j=0;j<filterData.getAttachments_id().length;j++){
                    String attchedFileName = null;
                    String lastFileInsertId = null;
                    if((filterData.getAttachments_deleted()[j]=="0" || filterData.getAttachments_deleted()[j].equals("0")) &&
                        (filterData.getAttachments_id()[j]=="0" || filterData.getAttachments_id()[j].equals("0")) &&
                        (filterData.getAttachments_type()[j]!=null && filterData.getAttachments_type()[j].length()>0)){
                        MultipartFile uploadedFile =null;
                        if(j==0){
                             uploadedFile= filterData.getAttachmentValue1();
                        }else if(j==1){
                            uploadedFile = filterData.getAttachmentValue2();
                        }else if(j==2){
                            uploadedFile = filterData.getAttachmentValue3();
                        }else if(j==3){
                            uploadedFile = filterData.getAttachmentValue4();
                        }else if(j==4){
                            uploadedFile = filterData.getAttachmentValue5();
                        }else{
                            uploadedFile = filterData.getAttachmentValue1();
                        }
                        if(uploadedFile!=null){
                            attchedFileName = uploadedFile.getOriginalFilename();
                            filterData.setAttachment_name(attchedFileName);
                            int referenceId = Integer.parseInt(CommonConfig.ProcurementReferenceId);
                            lastFileInsertId = CommonConfig.fileUpload(uploadedFile, referenceId, filterData.getAttachments_type()[j], CommonConfig.ProcurementModuleName, service);
                        }
                        filterData.setAttachmentType(filterData.getAttachments_type()[j]);
                        filterData.setAttachment_name(attchedFileName);
                        filterData.setAttachment_description(filterData.getAttachments_description()[j]);
                        filterData.setFile_upload_id(lastFileInsertId);
                        service.insertProcurementAttachmentDetails(filterData);
                    }else{

                    }   
                }
                
                if(last_insert_id!=null && filterData.getStatus().equals("m")){
                    String manager_id = service.getManagerId(Integer.toString(filterData.getRequestor_id()));
                    if(manager_id.equals(Integer.toString(filterData.getRequestor_id()))){
                        filterData.setRm_id(employee_id);
                        filterData.setRemarks("Auto Approved");
                        filterData.setStatus("a");
                        filterData.setId(last_insert_id);
                        service.approveRMProcurementDetails(filterData);
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
                            List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(Integer.toString(filterData.getRequestor_id()));
                            String pr_code = service.getProcurementCode(last_insert_id);
                            HttpServletRequest requestObj = null;
                            String mailSubject = pr_code + " - New Purchase Request is waiting for approval";
                            String mailMessage = mailMessageObj.getMessage(requestObj, "", approverDetails.get(0).getEmployee_name(), approverDetails.get(0).getRm_name(), filterData.getStatus(), approverDetails.get(0).getBuh_name());
                            mailObj.smtpMail(approverDetails.get(0).getRm_email_id(), mailSubject, mailMessage, approverDetails.get(0).getEmployee_email_id());
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mv = new ModelAndView("redirect:procurementList.htm");
                    mv.addObject("successMessage", "Details Submitted Successfully");
                }else{
                    mv = new ModelAndView("redirect:procurementList.htm");
                    mv.addObject("successMessage", "Details Saved Successfully");
                }
            }else{
                mv = new ModelAndView("redirect:procurementList.htm");
                mv.addObject("errorMessage", "Details not saved");
            }
            
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView updateProcurement(HttpServletRequest request, HttpServletResponse response, ProcurementDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        ProcurementDao dao = (ProcurementDaoImpl) ctx.getBean("ProcurementDao");
        if(employee_id!=null){
            String pr_code_check = service.getProcurementCode(filterData.getId());
            filterData.setRequestor_id(Integer.parseInt(filterData.getRequestorId()));
            filterData.setSbu_id(Integer.parseInt(filterData.getSbuId()));
            filterData.setSub_sbu_id(Integer.parseInt(filterData.getSubSbuId()));
            service.updateProcurementDetails(filterData);
            filterData.setLastInsertId(filterData.getId());
            for(int i=0;i<filterData.getIteams_ids().length;i++){
                if((filterData.getIteams_deleted()[i]=="0" || filterData.getIteams_deleted()[i].equals("0")) &&
                    (filterData.getIteams_ids()[i]=="0" || filterData.getIteams_ids()[i].equals("0"))){
                    filterData.setIteam_description(filterData.getIteams_description()[i]);
                    filterData.setIteam_quantity(filterData.getIteams_quantity()[i]);
                    filterData.setIteam_amount(filterData.getIteams_amount()[i]);
                    service.insertProcurementIteams(filterData);
                }else{
                    filterData.setIteam_id(filterData.getIteams_ids()[i]);
                    filterData.setLastInsertId(filterData.getId());
                    filterData.setIteam_description(filterData.getIteams_description()[i]);
                    filterData.setIteam_quantity(filterData.getIteams_quantity()[i]);
                    filterData.setIteam_amount(filterData.getIteams_amount()[i]);
                    filterData.setIteam_deleted(filterData.getIteams_deleted()[i]);
                    service.updateProcurementIteams(filterData);
                }
                
            }
            for(int j=0;j<filterData.getAttachments_id().length;j++){
                String attchedFileName = null;
                String lastFileInsertId = null;
                if((filterData.getAttachments_deleted()[j]=="0" || filterData.getAttachments_deleted()[j].equals("0")) &&
                    (filterData.getAttachments_id()[j]=="0" || filterData.getAttachments_id()[j].equals("0")) &&
                    (filterData.getAttachments_type()[j]!=null && filterData.getAttachments_type()[j].length()>0)){
                    MultipartFile uploadedFile =null;
                    if(j==0){
                         uploadedFile = filterData.getAttachmentValue1();
                    }else if(j==1){
                        uploadedFile = filterData.getAttachmentValue2();
                    }else if(j==2){
                        uploadedFile = filterData.getAttachmentValue3();
                    }else if(j==3){
                        uploadedFile = filterData.getAttachmentValue4();
                    }else if(j==4){
                        uploadedFile = filterData.getAttachmentValue5();
                    }else{
                        uploadedFile = filterData.getAttachmentValue1();
                    }
                    if(uploadedFile!=null){
                        attchedFileName = uploadedFile.getOriginalFilename();
                        filterData.setAttachment_name(attchedFileName);
                        int referenceId = Integer.parseInt(CommonConfig.ProcurementReferenceId);
                        lastFileInsertId = CommonConfig.fileUpload(uploadedFile, referenceId, filterData.getAttachments_type()[j], CommonConfig.ProcurementModuleName, service);
                    }
                    filterData.setAttachmentType(filterData.getAttachments_type()[j]);
                    filterData.setLastInsertId(filterData.getId());
                    filterData.setAttachment_name(attchedFileName);
                    filterData.setAttachment_description(filterData.getAttachments_description()[j]);
                    filterData.setFile_upload_id(lastFileInsertId);
                    service.insertProcurementAttachmentDetails(filterData);
                }else{
                
                }
            }
            
            if(filterData.getId()!=null && filterData.getStatus().equals("m")){
                String manager_id = service.getManagerId(Integer.toString(filterData.getRequestor_id()));
                if(manager_id.equals(filterData.getRequestorId())){
                    filterData.setRm_id(employee_id);
                    filterData.setRemarks("Auto Approved");
                    filterData.setStatus("a");
                    filterData.setId(filterData.getId());
                    service.approveRMProcurementDetails(filterData);
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
                        List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(Integer.toString(filterData.getRequestor_id()));
                        String pr_code = service.getProcurementCode(filterData.getId());
                        HttpServletRequest requestObj = null;
                        String mailSubject = null;
                        if(pr_code_check!=null&&pr_code_check.length()>0){
                            mailSubject = pr_code + " - Purchase Request is resubmitted for approval";
                        }else{
                            mailSubject = pr_code + " - New Purchase Request is waiting for approval";
                        }

                        String mailMessage = mailMessageObj.getMessage(requestObj, "", approverDetails.get(0).getEmployee_name(), approverDetails.get(0).getRm_name(), filterData.getStatus(), approverDetails.get(0).getBuh_name());
                        mailObj.smtpMail(approverDetails.get(0).getRm_email_id(), mailSubject, mailMessage, approverDetails.get(0).getEmployee_email_id());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
            }
            mv = new ModelAndView("redirect:procurementList.htm");
            mv.addObject("successMessage", "Details Submitted Successfully");
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView viewProcurementDetails(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String pp_id = (String) request.getParameter("Id");
        String approverType = (String) request.getParameter("approverType");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        System.out.println("approver typeeee "+approverType);
        if(pp_id!=null){
            if(approverType=="e" || approverType.equals("e")){
                mv = new ModelAndView("/viewProcurement");
            }else if(approverType=="r" || approverType.equals("r")){
                mv = new ModelAndView("/rmApprove");
            }else if(approverType=="b" || approverType.equals("b")){
                mv = new ModelAndView("/buhApprove");
            }else if(approverType=="a" || approverType.equals("a")){
                mv = new ModelAndView("/adminView");
            }else{
                mv = new ModelAndView("/unauthorised");
            }
            List<ProcurementDto> procurement_details = service.getProcurementDetailsView(pp_id);
            List<ProcurementPackagesDto> iteam_details = service.getIteamDetails(pp_id);
            List<ProcurementPackagesDto> attachment_details = service.getAttachmentDetails(pp_id);
            if(approverType=="a" || approverType.equals("a")){
                List<ProcurementPackagesDto> po_details = service.getPoAttachmentDetails(pp_id);
                mv.addObject("PoDetails", po_details);
            }
            mv.addObject("procurementDetails", procurement_details);
            mv.addObject("IteamDetails", iteam_details);
            mv.addObject("AttachmentDetails", attachment_details);
            mv.addObject("approverType", approverType);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    } 
    
    public ModelAndView attachmentDownload(HttpServletRequest request, HttpServletResponse response, ProcurementPackagesDto filterData) throws Exception {
        String fileName = request.getParameter("fileName");
        String filePath = CommonConfig.fileUploadPath;
        String fileType = request.getParameter("fileType");
        System.out.println("file name " + fileName + " file path " + filePath + " file type " + fileType);
        CommonConfig.fileDownload(fileName, filePath, fileType, response);
        return null;
    }
    
    public ModelAndView procurementRMList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView("/rmApprovalList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        String employee_id = (String) session.getAttribute("EMP_ID");
        String status = (String) request.getParameter("status");
        ProcurementDto filterData = null;
        filterData = new ProcurementDto();
        filterData.setRm_id(employee_id);
        filterData.setStatus(status);
        List<ProcurementDto> procurement_list = service.getProcurementRMList(filterData);
        mv.addObject("details", procurement_list);
        mv.addObject("status", status);
        mv.addObject("approverType", "r");
        return mv;
    }
    
    public ModelAndView procurementRMProcessedList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView("/rmApprovalList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        String employee_id = (String) session.getAttribute("EMP_ID");
        String status = (String) request.getParameter("status");
        ProcurementDto filterData = null;
        filterData = new ProcurementDto();
        filterData.setRm_id(employee_id);
        filterData.setStatus(status);
        List<ProcurementDto> procurement_list = service.getProcurementRMProcessedList(filterData);
        mv.addObject("details", procurement_list);
        mv.addObject("status", "a");
        mv.addObject("approverType", "r");
        return mv;
    }
    
    public ModelAndView procurementBUHList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView("/buhApprovalList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        String employee_id = (String) session.getAttribute("EMP_ID");
        String status = (String) request.getParameter("status");
        ProcurementDto filterData = null;
        filterData = new ProcurementDto();
        filterData.setBuh_id(employee_id);
        filterData.setStatus(status);
        List<ProcurementDto> procurement_list = service.getProcurementBUHList(filterData);
        mv.addObject("details", procurement_list);
        mv.addObject("status", status);
        mv.addObject("approverType", "b");
        return mv;
    }
    
    public ModelAndView procurementBUHProcessedList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView("/buhApprovalList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        String employee_id = (String) session.getAttribute("EMP_ID");
        String status = (String) request.getParameter("status");
        ProcurementDto filterData = null;
        filterData = new ProcurementDto();
        filterData.setBuh_id(employee_id);
        filterData.setStatus(status);
        List<ProcurementDto> procurement_list = service.getProcurementBUHProcessedList(filterData);
        mv.addObject("details", procurement_list);
        mv.addObject("status", "e");
        mv.addObject("approverType", "b");
        return mv;
    }
    
    public ModelAndView procurementAdminList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView("/adminList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        String employee_id = (String) session.getAttribute("EMP_ID");
        String status = (String) request.getParameter("status");
        ProcurementDto filterData = null;
        filterData = new ProcurementDto();
        filterData.setBuh_id(employee_id);
        filterData.setStatus(status);
        List<ProcurementDto> procurement_list = service.getProcurementAdminList(filterData);
        mv.addObject("details", procurement_list);
        mv.addObject("status", status);
        mv.addObject("approverType", "a");
        return mv;
    }
    
    public ModelAndView approveRMProcurement(HttpServletRequest request, HttpServletResponse response, ProcurementDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String approverType= request.getParameter("approverType");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        if(employee_id!=null){
            System.out.println("iddddddd "+filterData.getId());
            filterData.setRm_id(employee_id);
            for(int i=0;i<filterData.getIteams_ids().length;i++){
                filterData.setIteam_id(filterData.getIteams_ids()[i]);
                filterData.setLastInsertId(filterData.getId());
                filterData.setIteam_description(filterData.getIteams_description()[i]);
                filterData.setIteam_quantity(filterData.getIteams_quantity()[i]);
                filterData.setIteam_amount(filterData.getIteams_amount()[i]);
                filterData.setIteam_deleted(filterData.getIteams_deleted()[i]);
                service.updateProcurementIteams(filterData);
                
            }
            service.approveRMProcurementDetails(filterData);
            mv = new ModelAndView("redirect:procurementRMList.htm?status=m");
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView approveBUHProcurement(HttpServletRequest request, HttpServletResponse response, ProcurementDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String approverType= request.getParameter("approverType");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        if(employee_id!=null){
            System.out.println("iddddddd "+filterData.getId());
            filterData.setBuh_id(employee_id);
            for(int i=0;i<filterData.getIteams_ids().length;i++){
                filterData.setIteam_id(filterData.getIteams_ids()[i]);
                filterData.setLastInsertId(filterData.getId());
                filterData.setIteam_description(filterData.getIteams_description()[i]);
                filterData.setIteam_quantity(filterData.getIteams_quantity()[i]);
                filterData.setIteam_amount(filterData.getIteams_amount()[i]);
                filterData.setIteam_deleted(filterData.getIteams_deleted()[i]);
                service.updateProcurementIteams(filterData);
                
            }
            service.approveBUHProcurementDetails(filterData);
            mv = new ModelAndView("redirect:procurementBUHList.htm?status=a");
            
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView approveAdminProcurement(HttpServletRequest request, HttpServletResponse response, ProcurementDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String approverType= request.getParameter("approverType");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        if(employee_id!=null){
            System.out.println("iddddddd "+filterData.getId());
            service.approveAdminProcurement(filterData);
            filterData.setLastInsertId(filterData.getId());
            String attchedFileName = null;
            String lastFileInsertId = null;
            MultipartFile uploadedFile =null;
            uploadedFile= filterData.getAttachmentValue1();
            if(uploadedFile!=null){
                attchedFileName = uploadedFile.getOriginalFilename();
                filterData.setAttachment_name(attchedFileName);
                int referenceId = Integer.parseInt(CommonConfig.ProcurementReferenceId);
                lastFileInsertId = CommonConfig.fileUpload(uploadedFile, referenceId, "PO", CommonConfig.ProcurementModuleName, service);
            }
            filterData.setAttachmentType("PO");
            filterData.setAttachment_name(attchedFileName);
            filterData.setAttachment_description(filterData.getAttachments_description()[0]);
            filterData.setFile_upload_id(lastFileInsertId);
            service.insertProcurementAttachmentDetails(filterData);
            mv = new ModelAndView("redirect:procurementAdminList.htm?status=e");
            
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView procurementExcelExport(HttpServletRequest request, HttpServletResponse response, ProcurementDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String status = request.getParameter("status");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProcurementService service = (ProcurementServiceImpl) ctx.getBean("ProcurementService");
        if(employee_id!=null){
            List<ProcurementDto> report_list = service.getProcurementReport(status);
            ArrayList entireList = new ArrayList();
            for (int i = 0; i < report_list.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + report_list.get(i).getPp_code()));
                rowDataList.add(new String("" + report_list.get(i).getEmployee_name()));
                rowDataList.add(new String("" + report_list.get(i).getRecipient_contact_number()));
                rowDataList.add(new String("" + report_list.get(i).getSbu_name()));
                rowDataList.add(new String("" + report_list.get(i).getSub_sbu_name()));
                rowDataList.add(new String("" + report_list.get(i).getProcurement_type_name()));
                rowDataList.add(new String("" + report_list.get(i).getService_type()));
                rowDataList.add(new String("" + report_list.get(i).getBillable_name()));
                rowDataList.add(new String("" + report_list.get(i).getOrder_name()));
                rowDataList.add(new String("" + report_list.get(i).getDelivery_location_name()));
                rowDataList.add(new String("" + report_list.get(i).getDelivery_address()));
                rowDataList.add(new String("" + report_list.get(i).getRequested_date()));
                rowDataList.add(new String("" + report_list.get(i).getExpected_delivery_date()));
                rowDataList.add(new String("" + report_list.get(i).getRemarks()));
                rowDataList.add(new String("" + report_list.get(i).getCurrency_name()));
                rowDataList.add(new String("" + report_list.get(i).getTotal()));
                rowDataList.add(new String("" + report_list.get(i).getRm_name()));
                rowDataList.add(new String("" + report_list.get(i).getRm_approved_date()));
                rowDataList.add(new String("" + report_list.get(i).getRm_comments()));
                rowDataList.add(new String("" + report_list.get(i).getBuh_name()));
                rowDataList.add(new String("" + report_list.get(i).getBuh_approved_date()));
                rowDataList.add(new String("" + report_list.get(i).getBuh_comments()));
                rowDataList.add(new String("" + report_list.get(i).getIteam_description()));
                rowDataList.add(new String("" + report_list.get(i).getStatus_name()));
                entireList.add(rowDataList);
            }
            CommonConfig.exportToExcel(response, entireList, "Procurement_report", "procurement_list", "");
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
}
