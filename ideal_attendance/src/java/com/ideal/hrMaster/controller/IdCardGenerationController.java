/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.controller;

import com.ideal.hrMaster.dto.EmployeeDto;
import com.ideal.hrMaster.dto.IdCardDto;
import com.ideal.hrMaster.dto.ProcurementPackagesDto;
import com.ideal.hrMaster.service.IdCardService;
import com.ideal.hrMaster.service.IdCardServiceImpl;
import com.ideal.hrMaster.util.CommonConfig;
import com.ideal.hrMaster.util.MailMessages;
import com.ideal.hrMaster.util.SendMail;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class IdCardGenerationController extends MultiActionController{
    
    public ModelAndView getEmployeeIdCardList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String type = request.getParameter("type");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            List<EmployeeDto> employee_list = service.getEmployeeList();
            if(type.equals("i")){
                mv = new ModelAndView("/idCardList");
            }else if(type.equals("n")){
                mv = new ModelAndView("/ndaFormB");
            }else if(type.equals("b")){
                mv = new ModelAndView("/bonafiedList");
            }
            mv.addObject("employee_list", employee_list);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView getEmployeeDetails(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetails(search_employee_id);
            ArrayList  blood_group = service.getBloodGroup();
            mv = new ModelAndView("/viewDetails");
            mv.addObject("employee_details", employee_details);
            mv.addObject("blood_group", blood_group);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView updateEmployeeDetails(HttpServletRequest request, HttpServletResponse response, IdCardDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            service.updateEmployeeDetails(filterData);
            insertAttachments(request,filterData);
            IdCardDto employee_details = service.getEmployeeDetails(search_employee_id);
            ArrayList  blood_group = service.getBloodGroup();
            mv = new ModelAndView("/viewDetails");
            mv.addObject("employee_details", employee_details);
            mv.addObject("blood_group", blood_group);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    public void insertAttachments(HttpServletRequest request, IdCardDto formData) throws IOException{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        HashMap uploadData = new HashMap();
        Date createdDate = new Date();
        HttpSession session = request.getSession();
        MultipartFile files = multipartRequest.getFile("attached_photo");
        if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
            String ext = files.getOriginalFilename();
            String file_name = formData.getEmployee_number()+"_"+createdDate.getTime()+ext.substring(ext.lastIndexOf("."));
            uploadData.put("file_name", file_name);
            uploadData.put("created_by", (String) session.getAttribute("EMP_ID"));
            uploadData.put("employee_id", formData.getEmployee_id());
            File path = new File(CommonConfig.idCardPhoto + file_name);
            files.transferTo(path);
            final WebApplicationContext ctx = getWebApplicationContext();
            IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
            service.updateFileDetails(uploadData);
        }
    }
    
    public ModelAndView getEmployeeDetailsForNda(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetails(search_employee_id);
            mv = new ModelAndView("/ndaMailTrigger");
            mv.addObject("employee_details", employee_details);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView getPdfDownload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetails(search_employee_id);
            attachmentDownload(request,response,employee_details);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    public ModelAndView getIdDislay(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetails(search_employee_id);
            String fileName = generateIdCard(request, response, employee_details.getEmployee_number(),employee_details);
            mv = new ModelAndView("/displayIdCard");
            mv.addObject("file_name", fileName+".pdf");
            mv.addObject("employee_id", search_employee_id);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getEmployeeIdCardDownload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        mv = new ModelAndView("/viewIdCard");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetails(employee_id);
            mv.addObject("employee_details", employee_details);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView downloadNda(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetails(search_employee_id);
            employee_details.setAddress(request.getParameter("address"));
            employee_details.setIssued_by(employee_id);
            employee_details.setType("NDA");
            service.updateDwnloadDetails(employee_details);
            downloadNdaPdf(request, response, employee_details.getEmployee_number(),employee_details);
            mv = new ModelAndView("/ndaMailTrigger");
            mv.addObject("employee_details", employee_details);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView downloadFormB(HttpServletRequest request, HttpServletResponse response, IdCardDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetails(search_employee_id);
            employee_details.setIssued_by(employee_id);
            employee_details.setType("Form B");
            service.updateDwnloadDetails(employee_details);
            filterData.setEmployee_name(employee_details.getEmployee_name());
            downloadFormBPdf(request, response, employee_details.getEmployee_number(),filterData);
            mv = new ModelAndView("/ndaMailTrigger");
            mv.addObject("employee_details", employee_details);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getEmployeeDetailsForBonafied(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeBonafiedDetails(search_employee_id);
            mv = new ModelAndView("/triggerBonafied");
            mv.addObject("employee_details", employee_details);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView getEmployeeDetailsForDeputation(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetailsForDeputation(search_employee_id);
            List<IdCardDto> customer_details = service.getCustomerDetails();
            List<IdCardDto> customer_hr_name = service.getCustomerHrName();
            List<IdCardDto> customer_depatrments = service.getCustomerDepartments();
            List<IdCardDto> ht_representative = service.getHtRepresentative();
            List<IdCardDto> customer_locations = service.getCustomerLocations();
            mv = new ModelAndView("/triggerDeputation");
            mv.addObject("employee_details", employee_details);
            mv.addObject("customer_details", customer_details);
            mv.addObject("customer_hr_name", customer_hr_name);
            mv.addObject("customer_depatrments", customer_depatrments);
            mv.addObject("ht_representative", ht_representative);
            mv.addObject("customer_locations", customer_locations);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        
        return mv;
    }
    
    public ModelAndView downloadBonafied(HttpServletRequest request, HttpServletResponse response, IdCardDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeBonafiedDetails(search_employee_id);
            employee_details.setIssued_by(employee_id);
            employee_details.setType("Bonafied");
            service.updateDwnloadDetails(employee_details);
            filterData.setEmployee_name(employee_details.getEmployee_name());
            employee_details.setAddress(filterData.getAddress());
            downloadBonafiedPdf(request, response, employee_details.getEmployee_number(),employee_details);
            mv = new ModelAndView("/triggerBonafied");
            mv.addObject("employee_details", employee_details);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView downloadDeputation(HttpServletRequest request, HttpServletResponse response, IdCardDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        String effective_date = request.getParameter("effective_date");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getEmployeeDetailsForDeputation(search_employee_id);
            employee_details.setIssued_by(employee_id);
            employee_details.setType("Deputation");
            service.updateDwnloadDetails(employee_details);
            filterData.setEmployee_name(employee_details.getEmployee_name());
            filterData.setDesignation(employee_details.getDesignation());
            filterData.setIssued_on(effective_date);
            downloadDeputationPdf(request, response, employee_details.getEmployee_number(),filterData);
            mv = new ModelAndView("/triggerBonafied");
            mv.addObject("employee_details", employee_details);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getReport(HttpServletRequest request, HttpServletResponse response, IdCardDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String type = request.getParameter("type");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            List<IdCardDto> report_list = service.getReport(filterData);
            List<IdCardDto> report_type = service.getReportTypes();
            mv = new ModelAndView("/report");
            mv.addObject("report_list", report_list);
            mv.addObject("report_type", report_type);
            mv.addObject("type", type);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getExcelDownload(HttpServletRequest request, HttpServletResponse response, IdCardDto filterData) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String type = request.getParameter("type");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            List<IdCardDto> report_list = service.getReport(filterData);
            ArrayList entireList = new ArrayList();
            for (int i = 0; i < report_list.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + report_list.get(i).getEmployee_number()));
                rowDataList.add(new String("" + report_list.get(i).getEmployee_name()));
                rowDataList.add(new String("" + report_list.get(i).getType()));
                rowDataList.add(new String("" + report_list.get(i).getIssued_on()));
                rowDataList.add(new String("" + report_list.get(i).getIssued_by()));
                entireList.add(rowDataList);
            }
            CommonConfig.exportToExcel(response, entireList, "certifiace_issued_list", "employee_list", "");
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView triggerMailtoFacility(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String filePath = CommonConfig.idCardPhoto;
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        IdCardDto employee_details = service.getEmployeeDetails(search_employee_id);
        String file_name = generateIdCard(request, response, employee_details.getEmployee_number(),employee_details);
        
        ArrayList<IdCardDto> connectionRes;
        connectionRes =  service.getMailDetails();
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
            HttpServletRequest requestObj = null;
            String mailSubject = "New Joiner Details for ID card creation";
            String mailTo = service.getFacilityMailDetails("8");
            String mailCC = service.getFacilityMailDetails("3");
            String emp_mail = service.getEmployeePersonalMailId(employee_details.getEmployee_id());
            String mailMessage = "Dear Admin/Facility team,<br><br>"
                            + "Please find the new joiner details for ID card creation<br><br>"
                            + "<table style='border: 1px solid #808080; border-collapse: collapse;'>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Full Name in Block Letter :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getEmployee_name() +" </td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Employee/Consultant ID :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getEmployee_number() + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;' ><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Hinduja Tech Mail Id :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getEmployee_official_mail_id() + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Date Of Birth :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getBirth_date() + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Date of Joining :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getDate_of_joining() + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Designation :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getDesignation() + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Location :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getBase_location()+ "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Residential Address :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getAddress() + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Blood Group :</b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> " + employee_details.getBlood_group() + "</td></tr>"
                            + "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><b>Emergency Contact :<br>Name: <br>Relationship :<br> Mobile Number: <br></b></td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><br>" + employee_details.getEmergency_name() + "<br>"+employee_details.getEmergency_relationship()+"<br>"+employee_details.getEmergency_number()+"</td></tr>"
                            + "</table>"
                            + "<br><br>"
                            + "Regards,<br>"
                            + "iDeal Admin<br>"
                            + "http://ideal.hindujatech.com";
            mailObj.smtpMailAttachmentIdCard(mailTo, mailSubject, mailMessage, mailCC+","+emp_mail, file_name+".pdf", employee_details.getConfigValue());
            
        }catch(Exception e){
        }
        ArrayList  blood_group = service.getBloodGroup();
        mv = new ModelAndView("/viewDetails");
        mv.addObject("employee_details", employee_details);
        mv.addObject("blood_group", blood_group);
        return mv;
    }
    public ModelAndView attachmentDownload(HttpServletRequest request, HttpServletResponse response, IdCardDto employee_details) throws Exception {
        String filePath = CommonConfig.idCardPhoto;
        String fileType = "pdf";
        String fileName = generateIdCard(request, response, employee_details.getEmployee_number(),employee_details);
        CommonConfig.fileDownload(fileName+".pdf", filePath, fileType, response);
        return null;
    }
    public String generateIdCard(HttpServletRequest req, HttpServletResponse responseObj, String fileName,IdCardDto employee_details) throws Exception {
        PdfReader reader = new PdfReader("id_card.pdf");
        try {
            if(employee_details.getType().equals("46")){
                reader = new PdfReader("id_card_chennai.pdf");
                PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(CommonConfig.idCardPhoto+fileName+".pdf"));
                PdfContentByte pagecontent;
                pagecontent = pdfStamper.getOverContent(1);
                Font font = FontFactory.getFont("Calibri Regular.ttf", BaseFont.WINANSI, 0, 0, Color.GRAY);
                BaseFont base = font.getBaseFont();
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_CENTER,new Phrase(employee_details.getEmployee_name(), new Font(base, 11, Font.BOLD)), 80, 68, 0);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_CENTER,new Phrase(employee_details.getEmployee_number(), new Font(base, 10, Font.BOLD)), 80, 55, 0);
                String imgurl = "/wamp/www/app/webroot/uploads/id_card_photo/"+employee_details.getConfigValue();
                Image headerImage = Image.getInstance(imgurl);
                pagecontent.addImage(headerImage,81,0,0,80,40,86);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_LEFT,new Phrase(employee_details.getBlood_group(), new Font(base, 9)), 231, 228.25f, 0);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_LEFT,new Phrase(employee_details.getEmergency_number(), new Font(base, 9)), 220, 217.25f, 0);
                pdfStamper.close();
            }
            if(employee_details.getType().equals("42")){
                reader = new PdfReader("id_card_banglore.pdf");
                PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(CommonConfig.idCardPhoto+fileName+".pdf"));
                PdfContentByte pagecontent;
                pagecontent = pdfStamper.getOverContent(1);
                Font font = FontFactory.getFont("Calibri Regular.ttf", BaseFont.WINANSI, 0, 0, Color.GRAY);
                BaseFont base = font.getBaseFont();
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_CENTER,new Phrase(employee_details.getEmployee_name(), new Font(base, 11, Font.BOLD)), 80, 68, 0);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_CENTER,new Phrase(employee_details.getEmployee_number(), new Font(base, 10, Font.BOLD)), 80, 55, 0);
                String imgurl = "/wamp/www/app/webroot/uploads/id_card_photo/"+employee_details.getConfigValue();
                Image headerImage = Image.getInstance(imgurl);
                pagecontent.addImage(headerImage,81,0,0,80,40,86);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_LEFT,new Phrase(employee_details.getBlood_group(), new Font(base, 9)), 231, 228.25f, 0);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_LEFT,new Phrase(employee_details.getEmergency_number(), new Font(base, 9)), 220, 217.25f, 0);
                pdfStamper.close();
            }
            if(employee_details.getType().equals("44")){
                reader = new PdfReader("id_card_pune.pdf");
                PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(CommonConfig.idCardPhoto+fileName+".pdf"));
                PdfContentByte pagecontent;
                pagecontent = pdfStamper.getOverContent(1);
                Font font = FontFactory.getFont("Calibri Regular.ttf", BaseFont.WINANSI, 0, 0, Color.GRAY);
                BaseFont base = font.getBaseFont();
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_CENTER,new Phrase(employee_details.getEmployee_name(), new Font(base, 11, Font.BOLD)), 80, 68, 0);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_CENTER,new Phrase(employee_details.getEmployee_number(), new Font(base, 10, Font.BOLD)), 80, 55, 0);
                String imgurl = "/wamp/www/app/webroot/uploads/id_card_photo/"+employee_details.getConfigValue();
                Image headerImage = Image.getInstance(imgurl);
                pagecontent.addImage(headerImage,81,0,0,80,40,86);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_LEFT,new Phrase(employee_details.getBlood_group(), new Font(base, 9)), 227, 232.25f, 0);
                ColumnText.showTextAligned(pagecontent, Element.ALIGN_LEFT,new Phrase(employee_details.getEmergency_number(), new Font(base, 9)), 218, 221.25f, 0);
                pdfStamper.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
    
    public static void exportToPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName,IdCardDto employee_details) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "_" + employee_details.getEmployee_name()+".pdf\"");
            Document document = new Document(PageSize.A4, 30, 30, 50, 30);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/logo.JPG");
            document.add(headerImage);
                                
            Paragraph para = new Paragraph();
            para.add(new Phrase("ID Card Form Request\n",new Font(Font.TIMES_ROMAN, 16, Font.BOLD)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell pfcell;
            table.setTotalWidth(540);
            table.setLockedWidth(true);
            table.setWidths(new float[]{1, 2});
            pfcell = new PdfPCell(new Phrase("Full Name in Block Letters :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Employee/Consultant ID:",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getEmployee_number(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Hinduja Tech Mail Id :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getEmployee_official_mail_id(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Date Of Birth :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getBirth_date(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Date of Joining :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getDate_of_joining(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Designation :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getDesignation(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Location :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getBase_location(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Residential Address :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getAddress(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Blood Group :",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            pfcell.setPadding(10);table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getBlood_group(),new Font(Font.TIMES_ROMAN, 11)));
            pfcell.setPadding(10);table.addCell(pfcell);
            
            Paragraph para1 = new Paragraph();
            para1.add(new Phrase("  Emergency Contact :\n\n",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para1.add(new Phrase("  Name :\n\n",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para1.add(new Phrase("  Relationship :\n\n",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para1.add(new Phrase("  Phone Number :\n\n",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            table.addCell(para1);
            Paragraph para2 = new Paragraph();
            para2.add(new Phrase("\n\n  ",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para2.add(new Phrase(employee_details.getEmergency_name()+"\n\n  ",new Font(Font.TIMES_ROMAN, 11)));
            para2.add(new Phrase(employee_details.getEmergency_relationship()+"\n\n  ",new Font(Font.TIMES_ROMAN)));
            para2.add(new Phrase(employee_details.getEmergency_number()+"\n\n",new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(para2);
            Paragraph para3 = new Paragraph();
            para3.add(new Phrase("  For Official Use :\n\n",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para3.add(new Phrase("  Request Received on :\n\n",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para3.add(new Phrase("  Request processed on :\n\n",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para3.add(new Phrase("  Admin & Facilities :\n\n",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            table.addCell(para3);
            Paragraph para4 = new Paragraph();
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            para4.add(new Phrase("\n\n  ",new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para4.add(new Phrase(df.format(new Date())+"\n\n  ",new Font(Font.TIMES_ROMAN, 11)));
            table.addCell(para4);
            pfcell.setPadding(10);table.addCell(pfcell);
            document.add(table);
            
            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void downloadNdaPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName,IdCardDto employee_details) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "_" + employee_details.getEmployee_name()+"_NDA.pdf\"");
            Document document = new Document(PageSize.A4, 50, 50, 30, 50);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            HeaderFooter footer = new HeaderFooter(new Phrase("Page "+document.getPageNumber(),new Font(Font.TIMES_ROMAN, 10)), new Phrase(" of 3\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Confidential",new Font(Font.TIMES_ROMAN, 10)));
            footer.setAlignment(Element.ALIGN_LEFT);
            footer.setBorder(Rectangle.NO_BORDER);
            document.setFooter(footer);
            document.open();

            Paragraph para = new Paragraph();
            para.add(new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",new Font(Font.TIMES_ROMAN, 16, Font.BOLD)));
            para.add(new Phrase("Non-Disclosure Agreement",new Font(Font.TIMES_ROMAN, 16, Font.BOLD|Font.UNDERLINE)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell pfcell;
            table.setTotalWidth(500);
            table.setLockedWidth(true);
            pfcell = new PdfPCell(new Phrase("Name of the Organization                      :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell.setPadding(10);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase("Hinduja Tech Ltd",new Font(Font.TIMES_ROMAN, 14)));
            pfcell.setPadding(10);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Name of the Authorized Representative :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell.setPadding(10);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 14)));
            pfcell.setPadding(10);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Address                                                   :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell.setPadding(10);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(employee_details.getAddress(), new Font(Font.TIMES_ROMAN, 14)));
            pfcell.setPadding(10);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            document.add(table);
            
            Paragraph para1 = new Paragraph();
            para1.add(new Phrase("ASHOK LEYLAND LIMITED, hereafter referred to as AL, having its Registered office No.1, Sardar Patel Road, Guindy, Chennai 600 032, has / shall have extensive confidential information, which includes but not limited to, all business / functional processes and data contained in ERP, processed or stored in any form and any information, technical or commercial relating to Data Center and automotive technology, vehicle engineering & technology, manufacturing processes, design of aggregates and components, advanced engineering processes, design and engineering systems, and the like related to AL’s products, processes, facilities, services, R & D activities, investment plans, technology and product upgradation plans, new products, sales and marketing and business plans.",new Font(Font.TIMES_ROMAN, 13)));
            para1.setSpacingAfter(10f);
            para1.setSpacingBefore(10f);
            para1.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para1);
            
            Paragraph para2 = new Paragraph();
            para2.add(new Phrase("In consideration of the service with AL and / or any Group Company as defined during the term of the service, I/We hereby undertake as follows:",new Font(Font.TIMES_ROMAN, 13)));
            para2.setSpacingAfter(10f);
            para2.setSpacingBefore(10f);
//            para1.setIndentationLeft(10f);
            para2.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para2);
            
            Paragraph para3 = new Paragraph();
            para3.add(new Phrase("(1)	THAT during and after the service with AL, I/We undertake not to disclose to any person not authorised by AL, or use for my/our personal benefit, or for the benefit of any person outside of AL or for any purposes other than the fulfillment of the service obligations with AL, any confidential information without the prior written consent of AL.  I/We will return to AL, upon the end of the service, all copies of confidential information as well as other property of AL, including files and records, both in physical and electronic or any other forms and I/We undertake not to retain any part thereof nor to keep any copies of the same. I/We understand that confidential information is included in the definition expressed above. ",new Font(Font.TIMES_ROMAN, 13)));
            para3.setSpacingAfter(10f);
            para3.setSpacingBefore(10f);
            para3.setIndentationLeft(20f);
            para3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para3);
            
            Paragraph para4 = new Paragraph();
            para4.add(new Phrase("(2)	(a) Unless otherwise restricted by applicable law, I/We acknowledge that AL is solely and exclusively entitled to all the rights, title, interest and benefit to and arising from any technical/business/functional processes including developments, discoveries and / or inventions which I/We have or may have conceived or made or improved or reduced to practice or may hereafter conceive or improve or reduce to practice during the period of the service with AL either solely or jointly with another or others, and in the course of such service, or with the use of AL resources, time, material or facilities, or relating to any subject matter with which my work with and for AL is or may be directly or indirectly concerned, or relating to any problem arising in or from AL business of which I/We have been or may become informed by reason of my/our service with AL. I/We hereby agree that all my rights, title and interest or any potential rights, title or interest in any such invention whether or not capable of patent, or registered design or similar protection shall vest in AL or to any other party designated by AL.",new Font(Font.TIMES_ROMAN, 13)));
            para4.setSpacingAfter(10f);
            para4.setSpacingBefore(10f);
            para4.setIndentationLeft(20f);
            para4.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para4);
            
            Paragraph para5 = new Paragraph();
            para5.add(new Phrase("(b)	I/We will immediately disclose to AL any and all discoveries and / or inventions coming within the above definition, which I/We may conceive or execute during the service with AL.",new Font(Font.TIMES_ROMAN, 13)));
            para5.setSpacingAfter(10f);
            para5.setSpacingBefore(10f);
            para5.setIndentationLeft(20f);
            para5.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para5);
            
            Paragraph para6 = new Paragraph();
            para6.add(new Phrase("(c) I/We will execute, acknowledge and deliver and I further agree for myself, my/ours heirs, administrators, executors and assigns, without charge to AL, but at its expenses, to do and perform, execute, acknowledge and deliver all such further acts, deeds, matters and things paper or other documents including assignments and / or applications for patents, designs, copyrights or other industrial or intellectual property, as may be necessary, desirable or appropriate to obtain patents, designs and the like for said development/ inventions in any and all countries and to vest title thereto in AL, its successors and assigns and nominees and I/We will take such other action as may be necessary, desirable or appropriate to protect the interest of AL in such development/ inventions in any manner whatsoever.",new Font(Font.TIMES_ROMAN, 13)));
            para6.setSpacingAfter(10f);
            para6.setSpacingBefore(10f);
            para6.setIndentationLeft(20f);
            para6.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para6);
            
            Paragraph para7 = new Paragraph();
            para7.add(new Phrase("(d) I/We further agree that, I/We will not, except as required in the conduct of AL’s business directly or indirectly, publish or disclose or authorise any one else to publish or disclose, during the period of service with AL or subsequent thereto, any secret or confidential knowledge concerning any development/invention or other matter relating to AL business which I/We may in any way directly or indirectly acquire by reason of my/our service by AL.",new Font(Font.TIMES_ROMAN, 13)));
            para7.setSpacingAfter(10f);
            para7.setSpacingBefore(10f);
            para7.setIndentationLeft(20f);
            para7.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para7);
            
            Paragraph para8 = new Paragraph();
            para8.add(new Phrase("(3)	I/We hereby undertake to use all the assets like PCs / Laptops/Pen Drive etc. and services like Email, Internet, ERP, Portal, Software based applications and any other data / information / process provided by AL for official use only and will not use for personal or unauthorized activities. I/We will abide by the policies and procedures issued from time to time in this regard.",new Font(Font.TIMES_ROMAN, 13)));
            para8.setSpacingAfter(10f);
            para8.setSpacingBefore(10f);
            para8.setIndentationLeft(20f);
            para8.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para8);
            
            Paragraph para9 = new Paragraph();
            para9.add(new Phrase("I/We do not have any rights to any development/inventions made prior to the service with AL except those listed on the signed attached to this Undertaking.",new Font(Font.TIMES_ROMAN, 13)));
            para9.setSpacingAfter(10f);
            para9.setSpacingBefore(10f);
            para9.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para9);
            
            Paragraph para10 = new Paragraph();
            para10.add(new Phrase("\nI/We have read and understood the terms of this Undertaking and irrevocably agree to the conditionalities prescribed by this Undertaking.  I/We also acknowledge having received a copy of this Undertaking duly signed.  I/We further acknowledge that my obligations of non-disclosure and non-use under this Undertaking shall continue to be in effect and bind me even after the cessation of the service with AL.  I/We understand that this Undertaking shall be interpreted under the laws of the Republic of India.  This is the entire and only Undertaking with AL on these subjects.",new Font(Font.TIMES_ROMAN, 13)));
            para10.setSpacingAfter(10f);
            para10.setSpacingBefore(10f);
            para10.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para10);
            
            Paragraph para11 = new Paragraph();
            para11.add(new Phrase("If any provision or provisions of this Undertaking shall be held to be unenforceable by any Court, the remaining provisions shall be unaffected and shall continue to have full force and effect",new Font(Font.TIMES_ROMAN, 13)));
            para11.setSpacingAfter(10f);
            para11.setSpacingBefore(10f);
            para11.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para11);
            
            Paragraph para12 = new Paragraph();
            para12.add(new Phrase("Signed at Chennai this _______ day of _____________ 20   .",new Font(Font.TIMES_ROMAN, 13)));
            para12.add(new Phrase("\n\n\nSignature by Authorized Representative:",new Font(Font.TIMES_ROMAN, 13)));
            para12.add(new Phrase("\n\n\nName	 :",new Font(Font.TIMES_ROMAN, 13)));
            para12.add(new Phrase("\n\n\nDate	 :",new Font(Font.TIMES_ROMAN, 13)));
            para12.add(new Phrase("\n\n\nWITNESS     :",new Font(Font.TIMES_ROMAN, 13)));
            para12.setSpacingAfter(10f);
            para12.setSpacingBefore(10f);
            para12.setLeading(10f,2f);
            para12.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para12);
            
            PdfPTable table2 = new PdfPTable(2);
            PdfPCell pfcell1;
            table2.setTotalWidth(500);
            table2.setLockedWidth(true);
            pfcell1 = new PdfPCell(new Phrase("1) Name :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell1.setPadding(10);
            pfcell1.setBorder(Rectangle.NO_BORDER);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase("2) Name :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell1.setPadding(10);
            pfcell1.setBorder(Rectangle.NO_BORDER);
            table2.addCell(pfcell1);
            
            pfcell1 = new PdfPCell(new Phrase("   Signature :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell1.setPadding(10);
            pfcell1.setBorder(Rectangle.NO_BORDER);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase("   Signature :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell1.setPadding(10);
            pfcell1.setBorder(Rectangle.NO_BORDER);
            table2.addCell(pfcell1);
            
            pfcell1 = new PdfPCell(new Phrase("   Full Address :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell1.setPadding(10);
            pfcell1.setBorder(Rectangle.NO_BORDER);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase("   Full Address :",new Font(Font.TIMES_ROMAN, 14)));
            pfcell1.setPadding(10);
            pfcell1.setBorder(Rectangle.NO_BORDER);
            table2.addCell(pfcell1);
            document.add(table2);
            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void downloadFormBPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName,IdCardDto employee_details) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "_" + employee_details.getEmployee_name()+"_FormB.pdf\"");
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            Paragraph para = new Paragraph();
            para.add(new Phrase("ENGAGEMENT OF CONTRACT LABOUR / EXECUTIVE",new Font(Font.TIMES_ROMAN, 14, Font.BOLD|Font.UNDERLINE)));
            para.add(new Phrase("\n",new Font(Font.TIMES_ROMAN, 8, Font.BOLD)));
            para.add(new Phrase("\n(FORM – “B”)",new Font(Font.TIMES_ROMAN, 14, Font.BOLD)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell pfcell;
            table.setTotalWidth(500);
            table.setLockedWidth(true);
            pfcell = new PdfPCell(new Phrase("Name of Contractor",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": Hinduja Tech Ltd",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Nature of work",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employee_details.getNature_of_work(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Area of work",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employee_details.getArea_of_work(), new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Number of workers to be engaged",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employee_details.getNo_of_workers_engaged(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("ESI Code No./ Insurance Details",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employee_details.getInsurance_details(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("PF Code No",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employee_details.getPf_code_no(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Sub Contract Name",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employee_details.getSub_contrac_name(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Sub Contract ESI Code No.",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employee_details.getSub_contract_esi_code(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Sub Contract PF Code No.",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employee_details.getSub_contract_pf_code(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase("",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setPadding(5);
            pfcell.setBorder(Rectangle.NO_BORDER);
            table.addCell(pfcell);
            document.add(table);
            
            PdfPTable table2 = new PdfPTable(4);
            PdfPCell pfcell1;
            table2.setTotalWidth(500);
            table2.setWidths(new float[]{3, 3, 1, 4});
            table2.setLockedWidth(true);
            pfcell1 = new PdfPCell(new Phrase("Name of worker",new Font(Font.TIMES_ROMAN, 12, Font.BOLD)));
            pfcell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell1.setPadding(5);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase("S/o",new Font(Font.TIMES_ROMAN, 12, Font.BOLD)));
            pfcell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell1.setPadding(5);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase("Age",new Font(Font.TIMES_ROMAN, 12, Font.BOLD)));
            pfcell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell1.setPadding(5);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase("ESI & PF (UAN) No./ Address",new Font(Font.TIMES_ROMAN, 12, Font.BOLD)));
            pfcell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pfcell1.setPadding(5);
            table2.addCell(pfcell1);
            
            pfcell1 = new PdfPCell(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell1.setPadding(5);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase(employee_details.getFather_name(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell1.setPadding(5);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase(employee_details.getAge(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell1.setPadding(5);
            table2.addCell(pfcell1);
            pfcell1 = new PdfPCell(new Phrase(employee_details.getEsi_pf_code(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell1.setPadding(5);
            table2.addCell(pfcell1);
            document.add(table2);
            
            Paragraph para6 = new Paragraph();
            para6.add(new Phrase("DECLARATION",new Font(Font.TIMES_ROMAN, 12, Font.BOLD|Font.UNDERLINE)));
            para6.setSpacingAfter(10f);
            para6.setSpacingBefore(10f);
            para6.setAlignment(Element.ALIGN_CENTER);
            document.add(para6);
                        
            Paragraph para1 = new Paragraph();
            para1.add(new Phrase("I/We assure that I/We will follow all the statutory requirements/remittance of our workmen /sub contract workmen who are deputed to work at Ashok Leyland Ennore Factory premises and hereby indemnify Ashok Leyland from any claim",new Font(Font.TIMES_ROMAN, 12)));
            para1.setSpacingAfter(10f);
            para1.setSpacingBefore(10f);
            para1.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para1);
            
            Paragraph para2 = new Paragraph();
            para2.add(new Phrase("\n\nSignature of Contractor                                             Signature of Dept. Head",new Font(Font.TIMES_ROMAN, 12)));
            para2.setSpacingAfter(10f);
            para2.setSpacingBefore(10f);
//            para1.setIndentationLeft(10f);
            para2.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para2);
            
            Paragraph para3 = new Paragraph();
            para3.add(new Phrase("Comments of Safety Dept :",new Font(Font.TIMES_ROMAN, 12)));
            para3.setSpacingAfter(10f);
            para3.setSpacingBefore(10f);
            para3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para3);
            
            Paragraph para4 = new Paragraph();
            para4.add(new Phrase("Comments of HR Dept    :",new Font(Font.TIMES_ROMAN, 12)));
            para4.setSpacingAfter(10f);
            para4.setSpacingBefore(10f);
            para4.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para4);
            
            Paragraph para5 = new Paragraph();
            para5.add(new Phrase("\nNote: This copy has to be handed over to HR Deptartment on day one of engagement of contract labour inside the Plant for grant of access.",new Font(Font.TIMES_ROMAN, 12)));
            para5.setSpacingAfter(10f);
            para5.setSpacingBefore(10f);
            para5.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para5);

            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void downloadBonafiedPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName, IdCardDto employee_details) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "_" + employee_details.getEmployee_name()+"_Bonafied.pdf\"");
            Document document = new Document(PageSize.A4, 0, 0, 0, 0);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/bonafied_header.JPG");
            headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            document.add(headerImage);
            
            Image footerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/bonafied_footer.JPG");
            footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            footerImage.setAbsolutePosition(0, 0);
            document.add(footerImage);
            
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("MMMMM dd, yyyy");
            Paragraph para = new Paragraph();
            para.add(new Phrase(df.format(new Date()),new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            para.setIndentationRight(50f);
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            
            Paragraph para1 = new Paragraph();
            para1.add(new Phrase("TO WHOMSOEVER IT MAY CONCERN",new Font(Font.TIMES_ROMAN, 14, Font.BOLD|Font.UNDERLINE)));
            para1.setSpacingAfter(10f);
            para1.setSpacingBefore(10f);
            para1.setAlignment(Element.ALIGN_CENTER);
            document.add(para1);
            
            Paragraph para6 = new Paragraph();
            para6.add(new Phrase("This is to confirm that ",new Font(Font.TIMES_ROMAN, 12)));
            para6.add(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para6.add(new Phrase(" is working with Hinduja Tech Ltd and ",new Font(Font.TIMES_ROMAN, 12)));
            if(employee_details.getGender().equals("m")){
                para6.add(new Phrase("his designation is ",new Font(Font.TIMES_ROMAN, 12)));
            }else if(employee_details.getGender().equals("f")){
                para6.add(new Phrase("her designation is ",new Font(Font.TIMES_ROMAN, 12)));
            }
            para6.add(new Phrase(employee_details.getDesignation(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para6.setSpacingAfter(10f);
            para6.setSpacingBefore(10f);
            para6.setIndentationLeft(50f);
            para6.setIndentationRight(50f);
            para6.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para6);
            
            Paragraph para2 = new Paragraph();
            para2.add(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para2.add(new Phrase(" date of joining is ",new Font(Font.TIMES_ROMAN, 12)));
            para2.add(new Phrase(employee_details.getDate_of_joining(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            if(employee_details.getGender().equals("m")){
                para2.add(new Phrase(" and his employee code is ",new Font(Font.TIMES_ROMAN, 12)));
            }else if(employee_details.getGender().equals("f")){
                para2.add(new Phrase(" and her employee code is ",new Font(Font.TIMES_ROMAN, 12)));
            }
            para2.add(new Phrase(employee_details.getEmployee_number(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para2.setSpacingAfter(10f);
            para2.setSpacingBefore(10f);
            para2.setIndentationLeft(50f);
            para2.setIndentationRight(50f);
            para2.setAlignment(Element.ALIGN_LEFT);
            document.add(para2);
            
            
            Paragraph para3 = new Paragraph();
            if(employee_details.getGender().equals("m")){
                para3.add(new Phrase("His address as per our records:\n",new Font(Font.TIMES_ROMAN, 12)));
            }else if(employee_details.getGender().equals("f")){
                para3.add(new Phrase("Her address as per our records:\n",new Font(Font.TIMES_ROMAN, 12)));
            }
            para3.add(new Phrase(employee_details.getAddress(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para3.setSpacingAfter(10f);
            para3.setSpacingBefore(10f);
            para3.setIndentationLeft(50f);
            para3.setIndentationRight(50f);
            para3.setAlignment(Element.ALIGN_LEFT);
            document.add(para3);
            
            Paragraph para4 = new Paragraph();
            para4.add(new Phrase("This letter has been issued based on request from ",new Font(Font.TIMES_ROMAN, 12)));
            para4.add(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para4.setSpacingAfter(10f);
            para4.setSpacingBefore(10f);
            para4.setIndentationLeft(50f);
            para4.setIndentationRight(50f);
            para4.setAlignment(Element.ALIGN_LEFT);
            document.add(para4);
            
            Paragraph para5 = new Paragraph();
            para5.add(new Phrase("This letter will allow the verifier to obtain proof of residence only. In the course of any event, if this letter is being used for reasons other than the intended information regarding proof of residence, Hinduja Tech shall not be liable for any consequences thereof.",new Font(Font.TIMES_ROMAN, 12)));
            para5.setSpacingAfter(10f);
            para5.setSpacingBefore(10f);
            para5.setIndentationLeft(50f);
            para5.setIndentationRight(50f);
            para5.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para5);
            
            Paragraph para7 = new Paragraph();
            para7.add(new Phrase("Yours Truly,",new Font(Font.TIMES_ROMAN, 12)));
            para7.add(new Phrase("\n\nFor Hinduja Tech Ltd.,",new Font(Font.TIMES_ROMAN, 12, Font.BOLD)));
            para7.setSpacingAfter(10f);
            para7.setSpacingBefore(10f);
            para7.setIndentationLeft(50f);
            para7.setAlignment(Element.ALIGN_LEFT);
            document.add(para7);
            
            Image signImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/hr_signature.jpg");
            signImage.setAbsolutePosition(45, signImage.absoluteY());
            document.add(signImage);
            
            Paragraph para8 = new Paragraph();
            para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(Font.TIMES_ROMAN, 12)));
            para8.add(new Phrase("\nGeneral Manager - HR",new Font(Font.TIMES_ROMAN, 12)));
            para8.setSpacingBefore(10f);
            para8.setIndentationLeft(50f);
            para8.setAlignment(Element.ALIGN_LEFT);
            document.add(para8);
            
            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            System.out.println(""+e);
            e.printStackTrace();
        }
    }
    
    public ModelAndView triggerBonafiedtoEmployee(HttpServletRequest request, HttpServletResponse response, IdCardDto employee_details) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            
            //
            String filePath = CommonConfig.fileUploadPath;
            String file_name = employee_details.getEmployee_number() + "_" + employee_details.getEmployee_name()+"_Bonafied.pdf";
            File file = new File (filePath+file_name);
            if(file.delete()){
                System.out.println("file deleted "+filePath+file_name);
            }else{
            }
            Document document = new Document(PageSize.A4, 0, 0, 0, 0);
            PdfWriter.getInstance(document, new FileOutputStream(new File(filePath+"//"+file_name)));
            document.open();
            
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_header.JPG");
            headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            document.add(headerImage);
            
            Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_footer.JPG");
            footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            footerImage.setAbsolutePosition(0, 0);
            document.add(footerImage);
            
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("MMMMM dd, yyyy");
            Paragraph para = new Paragraph();
            para.add(new Phrase(df.format(new Date()),new Font(Font.TIMES_ROMAN, 11, Font.BOLD)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            para.setIndentationRight(50f);
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            
            Paragraph para1 = new Paragraph();
            para1.add(new Phrase("TO WHOMSOEVER IT MAY CONCERN",new Font(Font.TIMES_ROMAN, 14, Font.BOLD|Font.UNDERLINE)));
            para1.setSpacingAfter(10f);
            para1.setSpacingBefore(10f);
            para1.setAlignment(Element.ALIGN_CENTER);
            document.add(para1);
            
            Paragraph para6 = new Paragraph();
            para6.add(new Phrase("This is to confirm that ",new Font(Font.TIMES_ROMAN, 12)));
            para6.add(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para6.add(new Phrase(" is working with Hinduja Tech Ltd and ",new Font(Font.TIMES_ROMAN, 12)));
            if(employee_details.getGender().equals("m")){
                para6.add(new Phrase("his designation is ",new Font(Font.TIMES_ROMAN, 12)));
            }else if(employee_details.getGender().equals("f")){
                para6.add(new Phrase("her designation is ",new Font(Font.TIMES_ROMAN, 12)));
            }
            para6.add(new Phrase(employee_details.getDesignation(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para6.setSpacingAfter(10f);
            para6.setSpacingBefore(10f);
            para6.setIndentationLeft(50f);
            para6.setIndentationRight(50f);
            para6.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para6);
            
            Paragraph para2 = new Paragraph();
            para2.add(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para2.add(new Phrase(" date of joining is ",new Font(Font.TIMES_ROMAN, 12)));
            para2.add(new Phrase(employee_details.getDate_of_joining(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            if(employee_details.getGender().equals("m")){
                para2.add(new Phrase(" and his employee code is ",new Font(Font.TIMES_ROMAN, 12)));
            }else if(employee_details.getGender().equals("f")){
                para2.add(new Phrase(" and her employee code is ",new Font(Font.TIMES_ROMAN, 12)));
            }
            para2.add(new Phrase(employee_details.getEmployee_number(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para2.setSpacingAfter(10f);
            para2.setSpacingBefore(10f);
            para2.setIndentationLeft(50f);
            para2.setIndentationRight(50f);
            para2.setAlignment(Element.ALIGN_LEFT);
            document.add(para2);
            
            
            Paragraph para3 = new Paragraph();
            if(employee_details.getGender().equals("m")){
                para3.add(new Phrase("His address as per our records:\n",new Font(Font.TIMES_ROMAN, 12)));
            }else if(employee_details.getGender().equals("f")){
                para3.add(new Phrase("Her address as per our records:\n",new Font(Font.TIMES_ROMAN, 12)));
            }
            para3.add(new Phrase(employee_details.getAddress(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para3.setSpacingAfter(10f);
            para3.setSpacingBefore(10f);
            para3.setIndentationLeft(50f);
            para3.setIndentationRight(50f);
            para3.setAlignment(Element.ALIGN_LEFT);
            document.add(para3);
            
            Paragraph para4 = new Paragraph();
            para4.add(new Phrase("This letter has been issued based on request from ",new Font(Font.TIMES_ROMAN, 12)));
            para4.add(new Phrase(employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 12,Font.BOLD)));
            para4.setSpacingAfter(10f);
            para4.setSpacingBefore(10f);
            para4.setIndentationLeft(50f);
            para4.setIndentationRight(50f);
            para4.setAlignment(Element.ALIGN_LEFT);
            document.add(para4);
            
            Paragraph para5 = new Paragraph();
            para5.add(new Phrase("This letter will allow the verifier to obtain proof of residence only. In the course of any event, if this letter is being used for reasons other than the intended information regarding proof of residence, Hinduja Tech shall not be liable for any consequences thereof.",new Font(Font.TIMES_ROMAN, 12)));
            para5.setSpacingAfter(10f);
            para5.setSpacingBefore(10f);
            para5.setIndentationLeft(50f);
            para5.setIndentationRight(50f);
            para5.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para5);
            
            Paragraph para7 = new Paragraph();
            para7.add(new Phrase("Yours Truly,",new Font(Font.TIMES_ROMAN, 12)));
            para7.add(new Phrase("\n\nFor Hinduja Tech Ltd.,",new Font(Font.TIMES_ROMAN, 12, Font.BOLD)));
            para7.setSpacingAfter(10f);
            para7.setSpacingBefore(10f);
            para7.setIndentationLeft(50f);
            para7.setAlignment(Element.ALIGN_LEFT);
            document.add(para7);
            
            Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/hr_signature.jpg");
            signImage.setAbsolutePosition(45, signImage.absoluteY());
            document.add(signImage);
            
            Paragraph para8 = new Paragraph();
            para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(Font.TIMES_ROMAN, 12)));
            para8.add(new Phrase("\nGeneral Manager - HR",new Font(Font.TIMES_ROMAN, 12)));
            para8.setSpacingBefore(10f);
            para8.setIndentationLeft(50f);
            para8.setAlignment(Element.ALIGN_LEFT);
            document.add(para8);
            
            document.close();
            //
            
            ArrayList<IdCardDto> connectionRes;
            connectionRes =  service.getMailDetails();
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
                HttpServletRequest requestObj = null;
                IdCardDto employee_mail = service.getEmployeeMailId(employee_details.getEmployee_id());
                String mailSubject = employee_mail.getEmployee_name()+" - Bonafied Certificate";
                String mailTo = employee_mail.getEmployee_official_mail_id();
                String mailCC = service.getFacilityMailDetails("3");
                String mailMessage = "Dear "+employee_mail.getEmployee_name()+" ,<br><br>"
                                + "Please find attached the bonafied certificate requested.<br><br>"
                                + "<br><br>"
                                + "Regards,<br>"
                                + "Human Resources<br>";
                                
                mailObj.smtpMailAttachment(mailTo, mailSubject, mailMessage, mailCC, file_name);
                IdCardDto employee_details_new = service.getEmployeeBonafiedDetails(search_employee_id);
                employee_details_new.setEmployee_id(search_employee_id);
                employee_details_new.setIssued_by(employee_id);
                employee_details_new.setType("Bonafied");
                service.updateDwnloadDetails(employee_details_new);
                mv = new ModelAndView("/triggerBonafied");
                mv.addObject("employee_details", employee_details);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public static void downloadDeputationPdf(HttpServletRequest req, HttpServletResponse responseObj, String fileName, IdCardDto employee_details) throws Exception {
        InputStream fis = null;
        int count = 0;

        try {
            BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
            responseObj.setContentType("application/pdf");
            responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "_" + employee_details.getEmployee_name()+"_Deputation.pdf\"");
            Document document = new Document(PageSize.A4, 0, 0, 0, 0);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/bonafied_header.JPG");
            headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            document.add(headerImage);
            
            Image footerImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/bonafied_footer.JPG");
            footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            footerImage.setAbsolutePosition(0, 0);
            document.add(footerImage);
            
            SimpleDateFormat df = new SimpleDateFormat("MMMMM dd, yyyy");
            SimpleDateFormat dfe = new SimpleDateFormat("EEEE, dd MMMMM yyyy");
            Paragraph para = new Paragraph();
            para.add(new Phrase(df.format(new Date()),new Font(Font.TIMES_ROMAN, 13)));
            para.setSpacingAfter(10f);
            para.setSpacingBefore(10f);
            para.setIndentationRight(50f);
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            
            Paragraph para1 = new Paragraph();
            para1.add(new Phrase(employee_details.getCustomer_hr()+"\n",new Font(Font.TIMES_ROMAN, 13)));
            para1.add(new Phrase(employee_details.getCustomer_name(),new Font(Font.TIMES_ROMAN, 13)));
            para1.setSpacingAfter(10f);
            para1.setSpacingBefore(10f);
            para1.setIndentationLeft(50f);
            para1.setIndentationRight(50f);
            para1.setAlignment(Element.ALIGN_LEFT);
            document.add(para1);
            
            Paragraph para6 = new Paragraph();
            para6.add(new Phrase("Sub: Deputation Letter ",new Font(Font.TIMES_ROMAN, 13, Font.BOLD)));
            para6.setSpacingAfter(10f);
            para6.setSpacingBefore(10f);
            para6.setIndentationLeft(50f);
            para6.setIndentationRight(50f);
            para6.setAlignment(Element.ALIGN_LEFT);
            document.add(para6);
            
            Paragraph para2 = new Paragraph();
            para2.add(new Phrase("Dear Customer,\n\n",new Font(Font.TIMES_ROMAN, 13)));
            para2.add(new Phrase("We are pleased to confirm ",new Font(Font.TIMES_ROMAN, 13)));
            if(employee_details.getGender().equals("m")){
                para2.add(new Phrase("Mr "+employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 13, Font.BOLD)));
            }else if(employee_details.getGender().equals("f")){
                para2.add(new Phrase("Mrs "+employee_details.getEmployee_name(),new Font(Font.TIMES_ROMAN, 13, Font.BOLD)));
            }
            para2.add(new Phrase(" titled ",new Font(Font.TIMES_ROMAN, 13)));
            para2.add(new Phrase(employee_details.getDesignation(),new Font(Font.TIMES_ROMAN, 13,Font.BOLD)));
            para2.add(new Phrase(" at Hinduja Tech, is being deputed at ",new Font(Font.TIMES_ROMAN, 13)));
            para2.add(new Phrase(employee_details.getCustomer_location(),new Font(Font.TIMES_ROMAN, 13,Font.BOLD)));
            para2.add(new Phrase(" with the ",new Font(Font.TIMES_ROMAN, 13)));
            para2.add(new Phrase(employee_details.getDepartment(),new Font(Font.TIMES_ROMAN, 13,Font.BOLD)));
            para2.add(new Phrase(" department. He will be reporting for project allocation at ",new Font(Font.TIMES_ROMAN, 13)));
            para2.add(new Phrase(employee_details.getCustomer_location(),new Font(Font.TIMES_ROMAN, 13,Font.BOLD)));
            para2.add(new Phrase(" on ",new Font(Font.TIMES_ROMAN, 13)));
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
            Date effective_date = sdf1.parse(employee_details.getIssued_on());
            System.out.println("sdfi "+effective_date);
            para2.add(new Phrase(dfe.format(effective_date)+".",new Font(Font.TIMES_ROMAN, 13,Font.BOLD)));
            para2.setSpacingAfter(10f);
            para2.setSpacingBefore(10f);
            para2.setIndentationLeft(50f);
            para2.setIndentationRight(50f);
            para2.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para2);
            
            Paragraph para3 = new Paragraph();
            para3.add(new Phrase("Thank you again for your confidence in Hinduja Tech. We are looking forward to your continuous support, in building a mutually successful partnership.",new Font(Font.TIMES_ROMAN, 13)));
            para3.setSpacingAfter(10f);
            para3.setSpacingBefore(10f);
            para3.setIndentationLeft(50f);
            para3.setIndentationRight(50f);
            para3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para3);
            
            Paragraph para4 = new Paragraph();
            para4.add(new Phrase("Hinduja Tech Representative Contact\n",new Font(Font.TIMES_ROMAN, 13)));
            for(int i=0;i<employee_details.getHt_representative_name().length;i++){
                para4.add(new Phrase(employee_details.getHt_representative_name()[i]+"\n",new Font(Font.TIMES_ROMAN, 13)));
            }
            para4.setSpacingAfter(10f);
            para4.setSpacingBefore(10f);
            para4.setIndentationLeft(50f);
            para4.setIndentationRight(50f);
            para4.setAlignment(Element.ALIGN_LEFT);
            document.add(para4);
                        
            Paragraph para7 = new Paragraph();
            para7.add(new Phrase("Regards,",new Font(Font.TIMES_ROMAN, 13)));
            para7.add(new Phrase("\nFor Hinduja Tech Ltd.,",new Font(Font.TIMES_ROMAN, 13, Font.BOLD)));
            para7.setSpacingAfter(10f);
            para7.setSpacingBefore(10f);
            para7.setIndentationLeft(50f);
            para7.setAlignment(Element.ALIGN_LEFT);
            document.add(para7);
            
            Image signImage = com.lowagie.text.Image.getInstance("http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/css/images/hr_signature.jpg");
            signImage.setAbsolutePosition(45, signImage.absoluteY());
            document.add(signImage);
            
            Paragraph para8 = new Paragraph();
            para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(Font.TIMES_ROMAN, 13)));
            para8.add(new Phrase("\nGeneral Manager - HR",new Font(Font.TIMES_ROMAN, 13,Font.BOLD)));
            para8.setSpacingBefore(10f);
            para8.setIndentationLeft(50f);
            para8.setAlignment(Element.ALIGN_LEFT);
            document.add(para8);
            
            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            System.out.println(""+e);
            e.printStackTrace();
        }
    }
    public ModelAndView getDcEmployeeList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String type = request.getParameter("type");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            List<EmployeeDto> employee_list = service.getDcEmployeeList(type);
            mv = new ModelAndView("/dcEmployeeList");
            mv.addObject("employee_list", employee_list);
            mv.addObject("type", type);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getDcEmployeeDetails(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        String type = request.getParameter("type");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            IdCardDto employee_details = service.getDcEmployeeDetails(search_employee_id);
            mv = new ModelAndView("/dcEmployeeExtension");
            mv.addObject("employee_details", employee_details);
            mv.addObject("type", type);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getExtensionLetterDownload(HttpServletRequest request, HttpServletResponse responseObj) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        String to_date = request.getParameter("toDate");
        Date toDate = new SimpleDateFormat("dd-MM-yyyy").parse(to_date); 
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        SimpleDateFormat dbd = new SimpleDateFormat("yyyy-MM-dd");
        Font font = FontFactory.getFont("Calibri Regular.ttf", BaseFont.WINANSI);
        BaseFont base = font.getBaseFont();
        if(employee_id!=null){
            service.updateContractDate(search_employee_id, dbd.format(toDate));
            IdCardDto employee_details = service.getDcEmployeeDetails(search_employee_id);
            mv = new ModelAndView("/dcEmployeeExtension");
            mv.addObject("employee_details", employee_details);
            String fileName = "extension_letter";
            InputStream fis = null;
            int count = 0;
            try {
                BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
                responseObj.setContentType("application/pdf");
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + employee_details.getEmployee_number()+"_"+fileName+".pdf\"");
                Document document = new Document(PageSize.A4, 0, 0, 0, 0);
                PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
                document.open();

                Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_header.JPG");
                headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                document.add(headerImage);

                Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_footer.JPG");
                footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                footerImage.setAbsolutePosition(0, 0);
                document.add(footerImage);

                SimpleDateFormat df = new SimpleDateFormat("MMMMM dd, yyyy");
                Paragraph para = new Paragraph();
                para.add(new Phrase(df.format(new Date()),new Font(base, 12, Font.BOLD)));
                para.setSpacingAfter(10f);
                para.setSpacingBefore(10f);
                para.setIndentationRight(60f);
                para.setAlignment(Element.ALIGN_RIGHT);
                document.add(para);

                Paragraph para1 = new Paragraph();
                para1.add(new Phrase("\n\n",new Font(base, 16)));
                para1.add(new Phrase("Contract Extension",new Font(base, 16, Font.BOLD|Font.UNDERLINE)));
                para1.setSpacingAfter(10f);
                para1.setSpacingBefore(10f);
                para1.setIndentationLeft(60f);
                para1.setIndentationRight(60f);
                para1.setAlignment(Element.ALIGN_CENTER);
                document.add(para1);

                Paragraph para6 = new Paragraph();
                para6.add(new Phrase("\n\nDear ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getEmployee_name(),new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(",\n\n",new Font(base, 12)));
                para6.add(new Phrase("We are pleased to inform you that your consultancy services has been extended till ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getToDate()+"\n",new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase("\nAll other terms and conditions as detailed in your ",new Font(base, 12)));
                para6.add(new Phrase("consultant appointment ",new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase("letter remain unchanged. ",new Font(base, 12)));
                para6.add(new Phrase("\n\nKindly return the duplicate copy of this letter duly signed in token of your acceptance.\n\n\n ",new Font(base, 12)));
                para6.setSpacingAfter(10f);
                para6.setSpacingBefore(10f);
                para6.setIndentationLeft(60f);
                para6.setIndentationRight(60f);
                para6.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para6);

                
                Paragraph para7 = new Paragraph();
                para7.add(new Phrase("Yours Truly,",new Font(base, 12)));
                para7.add(new Phrase("\nFor Hinduja Tech Ltd.,",new Font(base, 12, Font.BOLD)));
                para7.setSpacingAfter(10f);
                para7.setSpacingBefore(10f);
                para7.setIndentationLeft(60f);
                para7.setIndentationRight(60f);
                para7.setAlignment(Element.ALIGN_LEFT);
                document.add(para7);

                Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/hr_signature.jpg");
                signImage.setAbsolutePosition(60, signImage.absoluteY());
                signImage.scaleToFit(115,80);
                document.add(signImage);
                
                PdfPTable table = new PdfPTable(2);
                PdfPCell pfcell;
                table.setTotalWidth(480);
                table.setLockedWidth(true);
                table.setWidths(new float[]{2.5f, 1.5f});
                
                Paragraph para8 = new Paragraph();
                para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(base, 12, Font.BOLD)));
                para8.add(new Phrase("\n\nGeneral Manager - HR",new Font(base, 12)));
                para8.setSpacingBefore(10f);
                para8.setIndentationLeft(190f);
                para8.setLeading(18f);
                para8.setAlignment(Element.ALIGN_LEFT);
                pfcell = new PdfPCell(para8);
                pfcell.setBorder(0);
                table.addCell(pfcell);
                
                Paragraph para9 = new Paragraph();
                para9.add(new Phrase("Accepted - "+employee_details.getEmployee_name(),new Font(base, 12)));
                para9.add(new Phrase("\n\nDirect Consultant ID - "+employee_details.getEmployee_number(),new Font(base, 12)));
                para9.setSpacingBefore(10f);
                para9.setLeading(18f);
                para9.setAlignment(Element.ALIGN_LEFT);
                pfcell = new PdfPCell(para9);
                pfcell.setBorder(0);
                table.addCell(pfcell);
                
                document.add(table);

                document.close();
                bos.flush();
                bos.close();
            } catch (Exception e) {
                System.out.println(""+e);
                e.printStackTrace();
            }
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getIncrementLetterDownload(HttpServletRequest request, HttpServletResponse responseObj) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        String to_date = request.getParameter("toDate");
        String inc_amt = request.getParameter("inc_amount");
        String eff_date = request.getParameter("eff_date");
        Date toDate = new SimpleDateFormat("dd-MM-yyyy").parse(to_date); 
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        SimpleDateFormat dbd = new SimpleDateFormat("yyyy-MM-dd");
        Font font = FontFactory.getFont("Calibri Regular.ttf", BaseFont.WINANSI);
        BaseFont base = font.getBaseFont();
        if(employee_id!=null){
            service.updateContractDate(search_employee_id, dbd.format(toDate));
            IdCardDto employee_details = service.getDcEmployeeDetails(search_employee_id);
            mv = new ModelAndView("/dcEmployeeExtension");
            mv.addObject("employee_details", employee_details);
            String fileName = "increment_and_extension_letter";
            InputStream fis = null;
            int count = 0;
            try {
                BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
                responseObj.setContentType("application/pdf");
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + employee_details.getEmployee_number()+"_"+fileName+".pdf\"");
                Document document = new Document(PageSize.A4, 0, 0, 0, 0);
                PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
                document.open();

                Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_header.JPG");
                headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                document.add(headerImage);

                Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_footer.JPG");
                footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                footerImage.setAbsolutePosition(0, 0);
                document.add(footerImage);

                SimpleDateFormat df = new SimpleDateFormat("MMMMM dd, yyyy");
                Paragraph para = new Paragraph();
                para.add(new Phrase(df.format(new Date()),new Font(base, 12, Font.BOLD)));
                para.setSpacingAfter(10f);
                para.setSpacingBefore(10f);
                para.setIndentationRight(60f);
                para.setAlignment(Element.ALIGN_RIGHT);
                document.add(para);

                Paragraph para1 = new Paragraph();
                para1.add(new Phrase("\n\n",new Font(base, 16)));
                para1.add(new Phrase("Contract Extension and Consultant Fee Revision",new Font(base, 16,Font.BOLD|Font.UNDERLINE)));
                para1.setSpacingAfter(10f);
                para1.setSpacingBefore(10f);
                para1.setIndentationLeft(60f);
                para1.setIndentationRight(60f);
                para1.setAlignment(Element.ALIGN_CENTER);
                document.add(para1);
                Paragraph para6 = new Paragraph();
                para6.add(new Phrase("\n\nDear ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getEmployee_name(),new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(",\n\n",new Font(base, 12)));
                para6.add(new Phrase("We are pleased to inform you that your consultancy services has been extended till ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getToDate(),new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(" and the fees for consultancy has been revised to ",new Font(base, 12)));
                para6.add(new Phrase("INR "+fmt(inc_amt,"INR"),new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(" /- ( "+CommonConfig.convert(Integer.parseInt(inc_amt))+"Only ) inclusive of tax per month with effective from ",new Font(base, 12)));
                para6.add(new Phrase(eff_date,new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(".\n\nAll other terms and conditions as detailed in your consultant appointment letter remain unchanged. ",new Font(base, 12)));
                para6.add(new Phrase("\n\nKindly return the duplicate copy of this letter duly signed in token of your acceptance.\n\n ",new Font(base, 12)));
                para6.setSpacingAfter(10f);
                para6.setSpacingBefore(10f);
                para6.setIndentationLeft(60f);
                para6.setIndentationRight(60f);
                para6.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para6);

                
                Paragraph para7 = new Paragraph();
                para7.add(new Phrase("Yours Truly,",new Font(base, 12)));
                para7.add(new Phrase("\nFor Hinduja Tech Ltd.,",new Font(base, 12, Font.BOLD)));
                para7.setSpacingAfter(10f);
                para7.setSpacingBefore(10f);
                para7.setIndentationLeft(60f);
                para7.setAlignment(Element.ALIGN_LEFT);
                document.add(para7);

                Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/hr_signature.jpg");
                signImage.setAbsolutePosition(60, signImage.absoluteY());
                signImage.scaleToFit(115,80);
                document.add(signImage);
                
                PdfPTable table = new PdfPTable(2);
                PdfPCell pfcell;
                table.setTotalWidth(480);
                table.setLockedWidth(true);
                table.setWidths(new float[]{2.5f, 1.5f});
                
                Paragraph para8 = new Paragraph();
                para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(base, 12,Font.BOLD)));
                para8.add(new Phrase("\n\nGeneral Manager - HR",new Font(base, 12)));
                para8.setSpacingBefore(10f);
                para8.setIndentationLeft(100f);
                para8.setLeading(18f);
                para8.setAlignment(Element.ALIGN_LEFT);
                pfcell = new PdfPCell(para8);
                pfcell.setBorder(0);
                table.addCell(pfcell);
                
                Paragraph para9 = new Paragraph();
                para9.add(new Phrase("Accepted - "+employee_details.getEmployee_name(),new Font(base, 12)));
                para9.add(new Phrase("\n\nDirect Consultant ID - "+employee_details.getEmployee_number(),new Font(base, 12)));
                para9.setSpacingBefore(10f);
                para9.setLeading(18f);
                para9.setAlignment(Element.ALIGN_LEFT);
                pfcell = new PdfPCell(para9);
                pfcell.setBorder(0);
                table.addCell(pfcell);
                
                document.add(table);

                document.close();
                bos.flush();
                bos.close();
            } catch (Exception e) {
                System.out.println(""+e);
                e.printStackTrace();
            }
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getServiceLetterDownload(HttpServletRequest request, HttpServletResponse responseObj) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        String type = request.getParameter("type");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        SimpleDateFormat dbd = new SimpleDateFormat("yyyy-MM-dd");
        Font font = FontFactory.getFont("Calibri Regular.ttf", BaseFont.WINANSI);
        BaseFont base = font.getBaseFont();
        if(employee_id!=null){
            IdCardDto employee_details = service.getDcEmployeeDetails(search_employee_id);
            mv = new ModelAndView("/dcEmployeeExtension");
            mv.addObject("employee_details", employee_details);
            mv.addObject("type", type);
            String fileName = "service_letter";
            try {
                BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
                responseObj.setContentType("application/pdf");
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + employee_details.getEmployee_number()+"_"+fileName+".pdf\"");
                Document document = new Document(PageSize.A4, 0, 0, 0, 0);
                PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
                document.open();

                Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_header.JPG");
                headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                document.add(headerImage);

                Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_footer.JPG");
                footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                footerImage.setAbsolutePosition(0, 0);
                document.add(footerImage);

                SimpleDateFormat df = new SimpleDateFormat("MMMMM dd, yyyy");
                Paragraph para = new Paragraph();
                para.add(new Phrase(df.format(new Date()),new Font(base, 12, Font.BOLD)));
                para.setSpacingAfter(10f);
                para.setSpacingBefore(10f);
                para.setIndentationRight(50f);
                para.setAlignment(Element.ALIGN_RIGHT);
                document.add(para);

                Paragraph para1 = new Paragraph();
                para1.add(new Phrase("Service Letter",new Font(base, 12, Font.BOLD|Font.UNDERLINE)));
                para1.setSpacingAfter(10f);
                para1.setSpacingBefore(10f);
                para1.setAlignment(Element.ALIGN_CENTER);
                document.add(para1);
                
                Paragraph para2 = new Paragraph();
                para2.add(new Phrase("Employee Name : "+employee_details.getEmployee_name(),new Font(base, 12, Font.BOLD)));
                para2.add(new Phrase("\nEmployee ID : "+employee_details.getEmployee_number(),new Font(base, 12, Font.BOLD)));
                para2.add(new Phrase("\nDesignation : "+employee_details.getDesignation()+"\n\n",new Font(base, 12, Font.BOLD)));
                para2.setSpacingAfter(10f);
                para2.setSpacingBefore(10f);
                para2.setIndentationLeft(50f);
                para2.setIndentationRight(50f);
                para2.setAlignment(Element.ALIGN_LEFT);
                document.add(para2);
                
                Paragraph para3 = new Paragraph();
                para3.add(new Phrase("TO WHOM IT MAY CONCERN",new Font(base, 12, Font.BOLD|Font.UNDERLINE)));
                para3.setSpacingAfter(10f);
                para3.setSpacingBefore(10f);
                para3.setIndentationLeft(50f);
                para3.setIndentationRight(50f);
                para3.setAlignment(Element.ALIGN_CENTER);
                document.add(para3);
                
                Paragraph para6 = new Paragraph();
                para6.add(new Phrase("This is to confirm that ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getGender()+" "+employee_details.getEmployee_name(),new Font(base, 12, Font.BOLD)));
                para6.add(new Phrase(" was working with Hinduja Tech Limited as a Consultant from ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getDate_of_joining()+" to "+employee_details.getConfigKey(),new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(". We wish you all the very best in your future endeavors.",new Font(base, 12)));
                para6.setSpacingAfter(10f);
                para6.setSpacingBefore(10f);
                para6.setIndentationLeft(50f);
                para6.setIndentationRight(50f);
                para6.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para6);

                
                Paragraph para7 = new Paragraph();
                para7.add(new Phrase("Yours Truly,",new Font(base, 12)));
                para7.add(new Phrase("\nFor Hinduja Tech Ltd.,",new Font(base, 12, Font.BOLD)));
                para7.setSpacingAfter(10f);
                para7.setSpacingBefore(10f);
                para7.setIndentationLeft(50f);
                para7.setAlignment(Element.ALIGN_LEFT);
                document.add(para7);

                Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/hr_signature.jpg");
                signImage.setAbsolutePosition(45, signImage.absoluteY());
                signImage.scaleToFit(125,80);
                document.add(signImage);
                
                Paragraph para8 = new Paragraph();
                para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(base, 12)));
                para8.add(new Phrase("\n\nGeneral Manager - HR",new Font(base, 12)));
                para8.setSpacingBefore(10f);
                para8.setIndentationLeft(50f);
                para8.setAlignment(Element.ALIGN_LEFT);
                document.add(para8);

                document.close();
                bos.flush();
                bos.close();
            } catch (Exception e) {
                System.out.println(""+e);
                e.printStackTrace();
            }
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView triggerServiceLetter(HttpServletRequest request, HttpServletResponse responseObj) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        String type = request.getParameter("type");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        SimpleDateFormat dbd = new SimpleDateFormat("yyyy-MM-dd");
        Font font = FontFactory.getFont("Calibri Regular.ttf", BaseFont.WINANSI);
        BaseFont base = font.getBaseFont();
        if(employee_id!=null){
            IdCardDto employee_details = service.getDcEmployeeDetails(search_employee_id);
            mv = new ModelAndView("/dcEmployeeExtension");
            mv.addObject("employee_details", employee_details);
            mv.addObject("type", type);
            try {
                String filePath = CommonConfig.fileUploadPath;
                String file_name = employee_details.getEmployee_number() + "_" + employee_details.getEmployee_name()+"_Service_letter.pdf";
                File file = new File (filePath+file_name);
                if(file.delete()){
                    System.out.println("file deleted "+filePath+file_name);
                }else{
                }
                Document document = new Document(PageSize.A4, 0, 0, 0, 0);
                PdfWriter.getInstance(document, new FileOutputStream(new File(filePath+"//"+file_name)));
                document.open();

                Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_header.JPG");
                headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                document.add(headerImage);

                Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_footer.JPG");
                footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                footerImage.setAbsolutePosition(0, 0);
                document.add(footerImage);

                SimpleDateFormat df = new SimpleDateFormat("MMMMM dd, yyyy");
                Paragraph para = new Paragraph();
                para.add(new Phrase(df.format(new Date()),new Font(base, 12, Font.BOLD)));
                para.setSpacingAfter(10f);
                para.setSpacingBefore(10f);
                para.setIndentationRight(50f);
                para.setAlignment(Element.ALIGN_RIGHT);
                document.add(para);

                Paragraph para1 = new Paragraph();
                para1.add(new Phrase("Service Letter",new Font(base, 12, Font.BOLD|Font.UNDERLINE)));
                para1.setSpacingAfter(10f);
                para1.setSpacingBefore(10f);
                para1.setAlignment(Element.ALIGN_CENTER);
                document.add(para1);
                
                Paragraph para2 = new Paragraph();
                para2.add(new Phrase("Employee Name : "+employee_details.getEmployee_name(),new Font(base, 12, Font.BOLD)));
                para2.add(new Phrase("\nEmployee ID : "+employee_details.getEmployee_number(),new Font(base, 12, Font.BOLD)));
                para2.add(new Phrase("\nDesignation : "+employee_details.getDesignation()+"\n\n",new Font(base, 12, Font.BOLD)));
                para2.setSpacingAfter(10f);
                para2.setSpacingBefore(10f);
                para2.setIndentationLeft(50f);
                para2.setIndentationRight(50f);
                para2.setAlignment(Element.ALIGN_LEFT);
                document.add(para2);
                
                Paragraph para3 = new Paragraph();
                para3.add(new Phrase("TO WHOM IT MAY CONCERN",new Font(base, 12, Font.BOLD|Font.UNDERLINE)));
                para3.setSpacingAfter(10f);
                para3.setSpacingBefore(10f);
                para3.setIndentationLeft(50f);
                para3.setIndentationRight(50f);
                para3.setAlignment(Element.ALIGN_CENTER);
                document.add(para3);
                
                Paragraph para6 = new Paragraph();
                para6.add(new Phrase("This is to confirm that ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getGender()+" "+employee_details.getEmployee_name(),new Font(base, 12, Font.BOLD)));
                para6.add(new Phrase(" was working with Hinduja Tech Limited as a Consultant from ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getDate_of_joining()+" to "+employee_details.getConfigKey(),new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(". We wish you all the very best in your future endeavors.",new Font(base, 12)));
                para6.setSpacingAfter(10f);
                para6.setSpacingBefore(10f);
                para6.setIndentationLeft(50f);
                para6.setIndentationRight(50f);
                para6.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para6);

                
                Paragraph para7 = new Paragraph();
                para7.add(new Phrase("Yours Truly,",new Font(base, 12)));
                para7.add(new Phrase("\nFor Hinduja Tech Ltd.,",new Font(base, 12, Font.BOLD)));
                para7.setSpacingAfter(10f);
                para7.setSpacingBefore(10f);
                para7.setIndentationLeft(50f);
                para7.setAlignment(Element.ALIGN_LEFT);
                document.add(para7);

                Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/hr_signature.jpg");
                signImage.setAbsolutePosition(45, signImage.absoluteY());
                signImage.scaleToFit(125,80);
                document.add(signImage);
                
                Paragraph para8 = new Paragraph();
                para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(base, 12)));
                para8.add(new Phrase("\nGeneral Manager - HR",new Font(base, 12)));
                para8.setSpacingBefore(10f);
                para8.setIndentationLeft(50f);
                para8.setAlignment(Element.ALIGN_LEFT);
                document.add(para8);

                document.close();
//                bos.flush();
//                bos.close();
                
                ArrayList<IdCardDto> connectionRes;
                connectionRes =  service.getMailDetails();
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
                    HttpServletRequest requestObj = null;
                    IdCardDto employee_mail = service.getEmployeePersonalDetails(employee_details.getEmployee_id());
                    String mailSubject = employee_mail.getEmployee_name()+" - Service Letter";
                    String mailTo = employee_mail.getEmployee_official_mail_id();
                    String mailCC = service.getFacilityMailDetails("3");
                    String mailMessage = "Dear "+employee_mail.getEmployee_name()+" ,<br><br>"
                                    + "Please find attached DC service letter.<br><br>"
                                    + "We wish you all the best in future endeavours.<br><br>"
                                    + "<br><br>"
                                    + "Regards,<br>"
                                    + "Human Resources<br>";

                    mailObj.smtpMailAttachment(mailTo, mailSubject, mailMessage, mailCC, file_name);
                }catch(Exception e){
                
                }
            } catch (Exception e) {
                System.out.println(""+e);
                e.printStackTrace();
            }
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getActiveEmployeeList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String type = request.getParameter("type");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        if(employee_id!=null){
            List<EmployeeDto> employee_list = service.getActiveEmployeeList();
            mv = new ModelAndView("/dcEmployeeList");
            mv.addObject("employee_list", employee_list);
            mv.addObject("type", type);
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public ModelAndView getProbationLetter(HttpServletRequest request, HttpServletResponse responseObj) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employee_id = (String) session.getAttribute("EMP_ID");
        String search_employee_id = request.getParameter("employee_id");
        String type = request.getParameter("type");
        String to_date = request.getParameter("toDate");
        final WebApplicationContext ctx = getWebApplicationContext();
        IdCardService service = (IdCardServiceImpl) ctx.getBean("IdCardService");
        Font font = FontFactory.getFont("Calibri Regular.ttf", BaseFont.WINANSI);
        BaseFont base = font.getBaseFont();
        if(employee_id!=null){
            IdCardDto employee_details = service.getDcEmployeeDetails(search_employee_id);
            mv = new ModelAndView("/dcEmployeeExtension");
            mv.addObject("employee_details", employee_details);
            mv.addObject("type", type);
            String fileName = "probation_letter";
            InputStream fis = null;
            int count = 0;
            try {
                BufferedOutputStream bos = new BufferedOutputStream(responseObj.getOutputStream());
                responseObj.setContentType("application/pdf");
                responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + employee_details.getEmployee_number()+"_"+fileName+".pdf\"");
                Document document = new Document(PageSize.A4, 0, 0, 0, 0);
                PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
                document.open();

                Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_header.JPG");
                headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                document.add(headerImage);

                Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/bonafied_footer.JPG");
                footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
                footerImage.setAbsolutePosition(0, 0);
                document.add(footerImage);

                SimpleDateFormat df = new SimpleDateFormat("MMMMM dd, yyyy");
                Paragraph para = new Paragraph();
                para.add(new Phrase(df.format(new Date()),new Font(base, 12, Font.BOLD)));
                para.setSpacingAfter(10f);
                para.setSpacingBefore(10f);
                para.setIndentationRight(50f);
                para.setAlignment(Element.ALIGN_RIGHT);
                document.add(para);

                Paragraph para1 = new Paragraph();
                para1.add(new Phrase("Employee Name :",new Font(base, 12)));
                para1.add(new Phrase(employee_details.getEmployee_name(),new Font(base, 12)));
                para1.add(new Phrase("\nEmployee Id :",new Font(base, 12)));
                para1.add(new Phrase(employee_details.getEmployee_number(),new Font(base, 12)));
                para1.setSpacingAfter(10f);
                para1.setSpacingBefore(10f);
                para1.setIndentationLeft(50f);
                para1.setIndentationRight(50f);
                para1.setAlignment(Element.ALIGN_LEFT);
                document.add(para1);
                
                Paragraph para2 = new Paragraph();
                para2.add(new Phrase("Sub: Confirmation of Employment",new Font(base, 12,Font.BOLD)));
                para2.setSpacingAfter(10f);
                para2.setSpacingBefore(10f);
                para2.setIndentationLeft(50f);
                para2.setIndentationRight(50f);
                para2.setAlignment(Element.ALIGN_CENTER);
                document.add(para2);

                Paragraph para6 = new Paragraph();
                para6.add(new Phrase("Dear ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getEmployee_name(),new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(",\n\n",new Font(base, 12)));
                para6.add(new Phrase("We are happy to inform you that you have been confirmed to the position of ",new Font(base, 12)));
                para6.add(new Phrase(employee_details.getDesignation(),new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(" effective ",new Font(base, 12)));
                para6.add(new Phrase(to_date,new Font(base, 12,Font.BOLD)));
                para6.add(new Phrase(". This confirmation is based on your performance review by your Reporting Manager and Project Manager. ",new Font(base, 12)));
                para6.add(new Phrase("\n\nAll other terms and conditions as detailed in your appointment letter remain unchanged.",new Font(base, 12)));
                para6.add(new Phrase("\n\nWe sincerely look forward to your association with Hinduja Tech Limited and are confident that you will use the opportunity with our company for a mutually beneficial relationship.",new Font(base, 12)));
                para6.setSpacingAfter(10f);
                para6.setSpacingBefore(10f);
                para6.setIndentationLeft(50f);
                para6.setIndentationRight(50f);
                para6.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para6);

                
                Paragraph para7 = new Paragraph();
                para7.add(new Phrase("Yours Truly,",new Font(base, 12)));
                para7.add(new Phrase("\nFor Hinduja Tech Ltd.,",new Font(base, 12, Font.BOLD)));
                para7.setSpacingAfter(10f);
                para7.setSpacingBefore(10f);
                para7.setIndentationLeft(50f);
                para7.setAlignment(Element.ALIGN_LEFT);
                document.add(para7);

                Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/css/images/hr_signature.jpg");
                signImage.setAbsolutePosition(45, signImage.absoluteY());
                signImage.scaleToFit(125,80);
                document.add(signImage);
                                
                Paragraph para8 = new Paragraph();
                para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(base, 12)));
                para8.add(new Phrase("\nGeneral Manager - HR",new Font(base, 12)));
                para8.setSpacingBefore(10f);
                para8.setIndentationLeft(50f);
                para8.setLeading(18f);
                para8.setAlignment(Element.ALIGN_LEFT);
                document.add(para8);

                document.close();
                bos.flush();
                bos.close();
            } catch (Exception e) {
                System.out.println(""+e);
                e.printStackTrace();
            }
        }else{
            mv = new ModelAndView("/unauthorised");
        }
        return mv;
    }
    
    public static String fmt(String value, String currency) {
        String[] curr_value = value.split("-");
        String s = "";
        if (curr_value.length == 2) {
            s = curr_value[1];
        } else {
            s = curr_value[0];
        }
        String formatted = "";
        String[] spl = s.split("\\.");
        s = spl[0];
        if (!currency.equals("INR")) {
            if (s.length() < 4) {
                formatted = s;
            } else if (s.length() == 4) {
                formatted = s.substring(0, 1) + "," + s.substring(1);
            } else if (s.length() == 5) {
                formatted = s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 6) {
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else if (s.length() == 7) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else if (s.length() == 8) {
                formatted = s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else if (s.length() == 9) {
                formatted = s.substring(0, 3) + ",";
                s = s.substring(3);
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else if (s.length() == 10) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 3) + ",";
                s = s.substring(3);
                formatted += s.substring(0, 3) + "," + s.substring(3);
            } else {
                formatted = s;
            }
        } else {
            if (s.length() < 4) {
                formatted = s;
            } else if (s.length() == 4) {
                formatted = s.substring(0, 1) + "," + s.substring(1);
            } else if (s.length() == 5) {
                formatted = s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 6) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 7) {
                formatted = s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 8) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 9) {
                formatted = s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else if (s.length() == 10) {
                formatted = s.substring(0, 1) + ",";
                s = s.substring(1);
                formatted += s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + ",";
                s = s.substring(2);
                formatted += s.substring(0, 2) + "," + s.substring(2);
            } else {
                formatted = s;
            }
        }
        if (curr_value.length == 2) {
            if (spl.length == 2) {
                return "-" + formatted + "." + spl[1];
            } else {
                return "-" + formatted;
            }
        } else {
            if (spl.length == 2) {
                return formatted + "." + spl[1];
            } else {
                return formatted;
            }
        }
    }
}
