/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.controller;

import com.defiance.ideal.dto.ECardDto;
import com.defiance.ideal.dto.EmpEngagementDto;
import com.defiance.ideal.service.ECardService;
import com.defiance.ideal.service.ECardServiceImpl;
import com.defiance.ideal.util.CommonFunctions;
import com.defiance.ideal.util.MailMessages;
import com.defiance.ideal.util.SendMail;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16221
 */
public class ECard extends MultiActionController{
    private ModelAndView mv;
    
    public ModelAndView eCardPage (HttpServletRequest request, HttpServletResponse response, ECardDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
        List<ECardDto> EcardList = service.getCardLists();
        //String customerID = request.getParameter("customer");
        mv = new ModelAndView("/com/defiance/eCardAdd");
        mv.addObject("EcardList", EcardList);
        mv.addObject("created_by", created_by);
        return mv;
    }
    
    public ModelAndView getEmployeeSearch(HttpServletRequest request, HttpServletResponse response, ECardDto filterData) {
        ModelAndView mvc = null;
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
            mvc = new ModelAndView("/com/defiance/ajaxEmployeeSearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
            String empVal = request.getParameter("q");
            System.out.println("ajax search----" + empVal);
            List<ECardDto> empRes = service.getEmployeeSearch(empVal);
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }
    
    public ModelAndView giveEcard (HttpServletRequest request, HttpServletResponse response, ECardDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
            System.out.println("aaaa "+created_by);
            final WebApplicationContext ctx = getWebApplicationContext();
            ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
            String new_file_name = service.generateFile(filterData);
            if(new_file_name!=null){
                filterData.setFile_name(new_file_name);
                int insert_id = service.giveEcard(filterData);
                if(insert_id>0){
                    String mail_to = service.getMailTo(filterData.getGiven_to());
                    String mail_cc_id = service.getMailCcList(Integer.toString(insert_id));
                    String mail_cc = service.getMailCc(mail_cc_id);
                    ECardDto card_details = service.getEcardDetails(Integer.toString(insert_id));
                    ArrayList<ECardDto> connectionRes;
                    connectionRes =  service.getMailCredentials();
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
                    try{
                        String mailMessage = "";
                        mailMessage += "<p><span style='font-family: Calibri(Body);font-size: 11pt;'>"
                                    + "Hi <b>"+card_details.getEmployee_name().split("-")[1] +"</b>,<br><br>"
                                    + card_details.getCreated_by().split("-")[1]+" has shown their Gratitude to you by dropping in a Pick Me Card!<br><br>"
                                    + "<b>Here's how you check it out - Ideal >> ESS >> Others >> Pick Me Card </b><br><br>"
                                    + "Hope "+card_details.getCreated_by().split("-")[1] +" made your day!<br><br>"
                                    + "There is nothing more beautiful than someone who goes out of their way to make life beautiful for others. - <b>Mandy Hale</b>"
                                    + "</span></p>";
                                    
                        String mailSubject = "Hi There, Someone is being Grateful to you!";
                        mailObj.smtpMailAttachment(mail_to, mailSubject, mailMessage, mail_cc, card_details.getFile_name());
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            mv = new ModelAndView("redirect:getPickMeCardList.htm?mycard=0");
//            List<ECardDto> GivenCardList = service.getGivenCardLists(created_by);
//            mv = new ModelAndView("/com/defiance/eCardList");
//            mv.addObject("GivenCardList", GivenCardList);
        }
        return mv;
    }
    
    public ModelAndView getPickMeCardList(HttpServletRequest request, HttpServletResponse response, ECardDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
            System.out.println("aaaa "+created_by);
            String list_type = request.getParameter("mycard");
            final WebApplicationContext ctx = getWebApplicationContext();
            ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
            if(list_type.equalsIgnoreCase("0")){
                List<ECardDto> GivenCardList = service.getGivenCardLists(created_by);
                mv = new ModelAndView("/com/defiance/eCardList");
                mv.addObject("GivenCardList", GivenCardList);
            }
            if(list_type.equalsIgnoreCase("1")){
                List<ECardDto> GivenCardList = service.getReceivedCardLists(created_by);
                mv = new ModelAndView("/com/defiance/eCardList");
                mv.addObject("GivenCardList", GivenCardList);
            }
            mv.addObject("list_type", list_type);    
        }
        return mv;
    }
    
    public ModelAndView attachmentDownload(HttpServletRequest request, HttpServletResponse response, ECardDto filterData) throws Exception {
        String fileName = request.getParameter("fileName");
        String filePath = CommonFunctions.fileUploadPath;
        String fileType = request.getParameter("fileType");
        System.out.println("file name " + fileName + " file path " + filePath + " file type " + fileType);
        this.fileDownload(fileName, filePath, "jpg", response);
        return null;
    }
    
    public static void fileDownload(String fileName, String filePath, String fileType, HttpServletResponse response) {
        try{
            response.setContentType(fileType);
            System.out.println("file name>>>>>"+fileName);
            response.setHeader("Content-disposition","attachment; filename=\""+fileName+"\"");
            File file = new File(filePath+"\\"+fileName);
            //prepare input stream
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            //prepare output stream
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            //start reading and writing data
            byte[] buf = new byte[4 * 1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }
            in.close();
            out.close();
        }
        catch(Exception e)
        {
           System.out.println(e.getMessage());
        }
    }
    public ModelAndView getEcardDetails(HttpServletRequest request, HttpServletResponse response, ECardDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
            System.out.println("aaaa "+created_by);
            String list_type = request.getParameter("mycard");
            String card_id = request.getParameter("ecard_id");
            final WebApplicationContext ctx = getWebApplicationContext();
            ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
            ECardDto card_details = service.getEcardDetails(card_id);
            mv = new ModelAndView("/com/defiance/eCardView");
            mv.addObject("card_details", card_details);
            mv.addObject("list_type", list_type);

        }
        return mv;
    }
    
    public ModelAndView eCardReport(HttpServletRequest request, HttpServletResponse response, ECardDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
            System.out.println("aaaa "+created_by);
            final WebApplicationContext ctx = getWebApplicationContext();
            ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
            List<ECardDto> unit_name = service.getUnitName();
            mv = new ModelAndView("/com/defiance/ecardReport");
            mv.addObject("unit_name", unit_name);

        }
        return mv;
    }
    public ModelAndView excelDownload(HttpServletRequest request, HttpServletResponse response, ECardDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setCreated_by(created_by);
            System.out.println("aaaa "+created_by);
            final WebApplicationContext ctx = getWebApplicationContext();
            ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
            List<ECardDto> card_details = service.getEcardReport(filterData);
            
            ArrayList entireList = new ArrayList();
            try{
                if(card_details.size()==0){
                    ArrayList rowData =  new ArrayList();
                    rowData.add(new String(""+"No data found"));
                    entireList.add(rowData);
                }else{
                    for(int i=0;i<card_details.size();i++){
                        ArrayList rowData =  new ArrayList();
                        rowData.add(card_details.get(i).getEmployee_id());
                        rowData.add(new String(""+card_details.get(i).getEmployee_name()));
                        rowData.add(new String(""+card_details.get(i).getBand_name()));
                        rowData.add(new String(""+card_details.get(i).getDesignation()));
                        rowData.add(new String(""+card_details.get(i).getRm_name()));
                        rowData.add(new String(""+card_details.get(i).getUnit_name()));
                        rowData.add(new String(""+card_details.get(i).getSub_unit_name()));
                        rowData.add(new String(""+card_details.get(i).getWork_location()));
                        rowData.add(new String(""+card_details.get(i).getCountry()));
                        rowData.add(new String(""+card_details.get(i).getConfigValue()));
                        rowData.add(new String(""+card_details.get(i).getCreated_by()));
                        rowData.add(new String(""+card_details.get(i).getGiven_on()));
                        rowData.add(new String(""+card_details.get(i).getCard_name()));
                        rowData.add(new String(""+card_details.get(i).getComments()));
                        entireList.add(rowData);
                    }
                }
            }catch(Exception e){
                System.out.println("exception "+e);
            }
            Date date = new Date();
            
            ArrayList Header = new ArrayList();
            ArrayList tableHeader1 = new ArrayList();
            ArrayList tableHeader = new ArrayList();
            tableHeader1.add(new String("" +"Downloaded On"));
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
            tableHeader1.add(new String("" +df.format(date)));
            Header.add(tableHeader1);
            tableHeader.add(new String("" +"Employee Id"));
            tableHeader.add(new String("" +"Employee Name"));
            tableHeader.add(new String("" +"Band"));
            tableHeader.add(new String("" +"Designation"));
            tableHeader.add(new String("" +"RM Name"));
            tableHeader.add(new String("" +"Unit"));
            tableHeader.add(new String("" +"Practice"));
            tableHeader.add(new String("" +"Work Location"));
            tableHeader.add(new String("" +"Country"));
            tableHeader.add(new String("" +"Employment Status"));
            tableHeader.add(new String("" +"Appreciated By"));
            tableHeader.add(new String("" +"Appreciation Given Date"));
            tableHeader.add(new String("" +"Appreciation Card Category"));
            tableHeader.add(new String("" +"Comments"));
            Header.add(tableHeader);
            SimpleDateFormat file_name = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
            CommonFunctions.exportToExcel(response, Header , entireList, "pick_me_card_details_"+file_name.format(date)+".xls", "Pick Me Card Report");
        }
        return mv;
    }
    
    public ModelAndView employeeEngagement (HttpServletRequest request, HttpServletResponse response, EmpEngagementDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setEmployee_id(created_by);
        }
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
        List<EmpEngagementDto> interest_list = null;
        EmpEngagementDto employee_interest = service.getLastSubmittedData(created_by);
//        String insert_id = employee_interest.getEe_id();
        if(employee_interest != null){
            interest_list = service.getInterestLists(employee_interest.getEe_id());
        }else{
            System.out.println("null");
        }
        
        List<EmpEngagementDto> area_list = service.getAreaofInterest();
        List<EmpEngagementDto> interest_level = service.getInterestLevel();
        List<EmpEngagementDto> commitment_list = service.getCommitmentLevel();
        mv = new ModelAndView("/com/defiance/employeeEngagement");
        mv.addObject("employee_interest", employee_interest);
        mv.addObject("employee_list", interest_list);
        mv.addObject("areaList", area_list);
        mv.addObject("interest_level", interest_level);
        mv.addObject("commitment_list", commitment_list);
        mv.addObject("submit", "1");
        mv.addObject("created_by", created_by);
        return mv;
    }
    
    public ModelAndView submitEmployeeEngagement (HttpServletRequest request, HttpServletResponse response, EmpEngagementDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setEmployee_id(created_by);
            System.out.println("aaaa "+created_by);
            final WebApplicationContext ctx = getWebApplicationContext();
            ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
            int insert_id = service.submitEmployeeEngagement(filterData);
            String status = "";
            if(insert_id>0){
                status = "1";
            }else{
                status = "0";
            }
            //mv.addObject("urlToRedirect", CommonFunctions.urlToRedirect);
            //mv = new ModelAndView("redirect:employeeEngagementData.htm?status="+status);
            mv = new ModelAndView("/com/defiance/redirectPage");
            mv.addObject("created_by", created_by);
        }
        
        return mv;
    }
    
    public ModelAndView employeeEngagementData (HttpServletRequest request, HttpServletResponse response, EmpEngagementDto filterData) throws Exception {
        HttpSession session = request.getSession();
        String created_by = (String)session.getAttribute("accessId");
        if(created_by == null ){
            mv = new ModelAndView("/unauthorised");
        }else{
            filterData.setEmployee_id(created_by);
        }
        String status = request.getParameter("status");
        System.out.println("aaaa "+created_by);
        final WebApplicationContext ctx = getWebApplicationContext();
        ECardService service = (ECardServiceImpl) ctx.getBean("ECardService");
        EmpEngagementDto employee_interest = service.getLastSubmittedData(created_by);
        List<EmpEngagementDto> interest_list = service.getInterestLists(employee_interest.getEe_id());
        List<EmpEngagementDto> area_list = service.getAreaofInterest();
        List<EmpEngagementDto> interest_level = service.getInterestLevel();
        List<EmpEngagementDto> commitment_list = service.getCommitmentLevel();
        mv = new ModelAndView("/com/defiance/employeeEngagement");
        mv.addObject("employee_interest", employee_interest);
        mv.addObject("areaList", area_list);
        mv.addObject("employee_list", interest_list);
        mv.addObject("interest_level", interest_level);
        mv.addObject("commitment_list", commitment_list);
        mv.addObject("status", status);
        mv.addObject("submit", "0");
        mv.addObject("created_by", created_by);
        return mv;
    }
}
