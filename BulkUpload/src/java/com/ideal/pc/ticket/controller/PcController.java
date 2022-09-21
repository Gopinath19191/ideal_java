/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.pc.ticket.controller;

import com.ideal.pc.ticket.dto.PcDataDTO;
import com.ideal.pc.ticket.service.PcServiceImpl;
import com.ideal.system.ticket.util.CommonConfigurations;
import com.ideal.system.ticket.util.CommonMethods;
import com.ideal.system.ticket.util.MailMessages;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16364
 */
public class PcController extends MultiActionController {

    public static int pageCount = 15;
    public static  String ojfGroupMail;
    public ModelAndView pcList(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) throws ParseException {
        ModelAndView mvc = new ModelAndView("/pclist");
        HttpSession session = request.getSession();
        System.out.println(" enter into main PC!!!");
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");
        String emp = (String) session.getAttribute("EMP_ID");
         if (emp == null || emp.equals("")) {

            return new ModelAndView("/unauthorised");
        }
      
         System.out.println("fstartdate ^^^^^"+request.getParameter("fstartdate"));
          String startDateString = request.getParameter("fstartdate");
           System.out.println("fstartdate ^^^^^"+request.getParameter("fstartdate"));
        String endDateString = request.getParameter("fenddate");
        if(startDateString!=null &&  endDateString!=null)
        {
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println("start date " + dateString1); // 2011-04-
        dto.setFstartdate(dateString1);
        dto.setFenddate(dateString2);
        mvc.addObject("fstartdate",startDateString);
      mvc.addObject("fenddate",endDateString);
        }
          List<PcDataDTO> details = bo.getConsultantList(dto);
         
        mvc.addObject("details", details);
        return mvc;
    }
    
    //NS
    public ModelAndView pcListNs(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) throws ParseException {
        ModelAndView mvc = new ModelAndView("/pclistns");
        HttpSession session = request.getSession();
        System.out.println(" enter into main PC!!!");
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");
        String emp = (String) session.getAttribute("EMP_ID");
         if (emp == null || emp.equals("")) {

            return new ModelAndView("/unauthorised");
        }
      
         System.out.println("fstartdate ^^^^^"+request.getParameter("fstartdate"));
          String startDateString = request.getParameter("fstartdate");
           System.out.println("fstartdate ^^^^^"+request.getParameter("fstartdate"));
        String endDateString = request.getParameter("fenddate");
        if(startDateString!=null &&  endDateString!=null)
        {
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println("start date " + dateString1); // 2011-04-
        dto.setFstartdate(dateString1);
        dto.setFenddate(dateString2);
        mvc.addObject("fstartdate",startDateString);
      mvc.addObject("fenddate",endDateString);
        }
          List<PcDataDTO> details = bo.getConsultantList(dto);
         
        mvc.addObject("details", details);
        return mvc;
    }
//search_consultant

    public ModelAndView search_consultant(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) throws ParseException {
        logger.info("search_consultant search_consultant search_consultant Admin");
        ModelAndView mvc = new ModelAndView("/pclist");
        HttpSession session = request.getSession();
        System.out.println(" search_consultant!!!");
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");
  String emp = (String) session.getAttribute("EMP_ID");
         if (emp == null || emp.equals("")) {

            return new ModelAndView("/unauthorised");
        }
        String refVal = request.getParameter("employee_search");
        System.out.println("refVal !!");
        String employee_id = "";
        List<PcDataDTO> empNameList = bo.getPcDetail(refVal);
        Iterator itr = empNameList.iterator();
        while (itr.hasNext()) {
            PcDataDTO tdtoo = (PcDataDTO) itr.next();
            System.out.println("dto.getConsultant_empid() sear^^^^^^"+tdtoo.getConsultant_empid());
            
            employee_id = tdtoo.getConsultant_empid();
            dto.setConsultant_empid(employee_id);
        }
         System.out.println("dto.getConsultant_empid() sear^^^^^^^^^^^^^^^^"+dto.getConsultant_empid());
         System.out.println("fstartdate ^^^^^"+request.getParameter("fstartdate"));
          String startDateString = request.getParameter("fstartdate");
        String endDateString = request.getParameter("fenddate");
        if(startDateString!=null &&  endDateString!=null)
        {
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println("start date " + dateString1); // 2011-04-
        dto.setFstartdate(dateString1);
        dto.setFenddate(dateString2);
        mvc.addObject("fstartdate",startDateString);
      mvc.addObject("fenddate",endDateString);
        }
        List<PcDataDTO> details = null;
        if(dto.getConsultant_empid() != null ) {
            details = bo.getConsultantListByName(dto);
            System.out.println("inside iffff");
        }else{
            details = bo.getConsultantList(dto);
            System.out.println("inside else");
        }
        
     
              
        mvc.addObject("details", details);
        return mvc;
    }
    public ModelAndView search_consultantns(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) throws ParseException {
        logger.info("search_consultant search_consultant search_consultant Admin");
        ModelAndView mvc = new ModelAndView("/pclistns");
        HttpSession session = request.getSession();
        System.out.println(" search_consultant!!!");
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");
  String emp = (String) session.getAttribute("EMP_ID");
         if (emp == null || emp.equals("")) {

            return new ModelAndView("/unauthorised");
        }
        String refVal = request.getParameter("employee_search");
        System.out.println("refVal !!");
        String employee_id = "";
        List<PcDataDTO> empNameList = bo.getPcDetail(refVal);
        Iterator itr = empNameList.iterator();
        while (itr.hasNext()) {
            PcDataDTO tdtoo = (PcDataDTO) itr.next();
            System.out.println("dto.getConsultant_empid() sear^^^^^^"+tdtoo.getConsultant_empid());
            
            employee_id = tdtoo.getConsultant_empid();
            dto.setConsultant_empid(employee_id);
        }
         System.out.println("dto.getConsultant_empid() sear^^^^^^^^^^^^^^^^"+dto.getConsultant_empid());
         System.out.println("fstartdate ^^^^^"+request.getParameter("fstartdate"));
          String startDateString = request.getParameter("fstartdate");
        String endDateString = request.getParameter("fenddate");
        if(startDateString!=null &&  endDateString!=null)
        {
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println("start date " + dateString1); // 2011-04-
        dto.setFstartdate(dateString1);
        dto.setFenddate(dateString2);
        mvc.addObject("fstartdate",startDateString);
      mvc.addObject("fenddate",endDateString);
        }
        List<PcDataDTO> details = null;
        if(dto.getConsultant_empid() != null ) {
            details = bo.getConsultantListByName(dto);
            System.out.println("inside iffff");
        }else{
            details = bo.getConsultantList(dto);
            System.out.println("inside else");
        }
        
     
              
        mvc.addObject("details", details);
        return mvc;
    }

    public ModelAndView add_pc(HttpServletRequest request, HttpServletResponse response, PcDataDTO dtoForm) {

        ModelAndView mvc = new ModelAndView("add_pc");
        HttpSession session = request.getSession();
         String emp = (String) session.getAttribute("EMP_ID");
         if (emp == null || emp.equals("")) {

            return new ModelAndView("/unauthorised");
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");
        List<PcDataDTO> regionList = null, cityList = null, countryList = null,worklocationList=null;
        String country = CommonConfigurations.indiaId;
        String region = CommonConfigurations.tamilNaduId;
        countryList = (List<PcDataDTO>) bo.getCountryList();
        regionList = (List<PcDataDTO>) bo.getRegionList(country);
        cityList = (List<PcDataDTO>) bo.getCityList(country, region);
        worklocationList =(List<PcDataDTO>) bo.getWorklocationList();
        List<PcDataDTO> pgList = (List<PcDataDTO>) bo.getPgData();
        List<PcDataDTO> subPgList = (List<PcDataDTO>) bo.getCmpStructData("" + dtoForm.getPracticeGroup());

        mvc.addObject("pgList", pgList);
        mvc.addObject("subPgList", subPgList);
        mvc.addObject("countryList", countryList); //moved to rrfconfloader
        mvc.addObject("regionList", regionList);
        mvc.addObject("cityList", cityList);
        
        mvc.addObject("worklocationList", worklocationList);
        return mvc;
    }

    public ModelAndView editConsultant(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto)
            throws UnknownHostException, IOException, ServletException {
        ModelAndView mvc = new ModelAndView("/edit_pc");
        HttpSession session = request.getSession();
         String emp = (String) session.getAttribute("EMP_ID");
         if (emp == null || emp.equals("")) {

            return new ModelAndView("/unauthorised");
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");
        List<PcDataDTO> regionList = null, cityList = null, countryList = null,worklocationList=null;
        String country = request.getParameter("countryId");
        System.out.println("country ### "+country);
       // String region = CommonConfigurations.tamilNaduId;
        String region = request.getParameter("regionId");
        System.out.println("REGIOn id#@"+region);
        countryList = (List<PcDataDTO>) bo.getCountryList();
        regionList = (List<PcDataDTO>) bo.getRegionList(country);
        cityList = (List<PcDataDTO>) bo.getCityList(country, region);
         worklocationList =(List<PcDataDTO>) bo.getWorklocationList();
        List<PcDataDTO> pgList = (List<PcDataDTO>) bo.getPgData();
        List<PcDataDTO> subPgList = (List<PcDataDTO>) bo.getCmpStructData("" + dto.getPracticeGroup());
        List<PcDataDTO> details = bo.editConsultantById(dto);
        mvc.addObject("details", details);
        mvc.addObject("countryList", countryList); //moved to rrfconfloader
        mvc.addObject("regionList", regionList);
        mvc.addObject("cityList", cityList);
        mvc.addObject("pgList", pgList);
        mvc.addObject("subPgList", subPgList);
        mvc.addObject("worklocationList", worklocationList);
        return mvc;

    }

    public ModelAndView saveConsultant(HttpServletRequest request, HttpServletResponse response, PcDataDTO res)
            throws UnknownHostException, IOException, ServletException, ParseException {
        System.out.println("insert into save Consultant@@@@@@@@@@@@@@@@@@@@@@@@@@");
        ModelAndView mvc = null;
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");
        String refVal = request.getParameter("employee_search");
        String rmVal = request.getParameter("manager");
        String projectName = request.getParameter("projectName");
        String emp = (String) session.getAttribute("EMP_ID");
        if (emp == null || emp.equals("")) {
            return new ModelAndView("/unauthorised");
        }
        String rmid = "";
        List<PcDataDTO> empNameListRm = bo.getEmpDetails(rmVal);
        Iterator rmitr = empNameListRm.iterator();
        while (rmitr.hasNext()) {
            PcDataDTO tdtoo = (PcDataDTO) rmitr.next();
            rmid = tdtoo.getEmployee_idd();
            res.setRmId(rmid);
        }
        String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
        String c_date = CommonMethods.getSystemDate(DATE_FORMAT_NOW);
        res.setCreated_date(c_date);
        System.out.println("res.getStartdate() !!!" + res.getStartdate());
        String startDateString = res.getStartdate();
        String endDateString = res.getEnddate();

        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println("start date " + dateString1); // 2011-04-
        res.setStartdate(dateString1);
        res.setEnddate(dateString2);

        String projectId = bo.getPrjDetail(projectName);
        res.setProjectId(projectId);

        int temp = bo.getHighestRefNo(res);
        System.out.println("temp ^^"+temp);
        int j = temp + 1;
        String s = String.valueOf(j);
        String highestdetail=null;
        if(temp<=9){
            highestdetail = "PC0000" + s;
        }else{
             highestdetail = "PC000" + s;
        }
        
        res.setPc_code(highestdetail);
        System.out.println("nmae"+request.getParameter("worklocation"));
        res.setWorkLocationId(request.getParameter("worklocation"));
        System.out.println("woklocation id"+res.getWorkLocationId()+"w  name"+res.getWorkLocationName());
        res.setConsultantName(res.getFirstname()+" "+res.getLastname());
        
        //file 
        
        String filePath = CommonConfigurations.fileUploadPath;
        MultipartFile fileData = null;
        fileData = res.getFile();
        String fileName = "";
        if (!fileData.getOriginalFilename().equals("") && fileData != null) {
            Random random = new Random();
            fileName = "" + random.nextLong() + "~~" + fileData.getOriginalFilename();
            if (!fileName.equals("")) {
                File fileToCreate = new File(filePath, fileName);
                if (!fileToCreate.exists()) {
                    FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                    fileOutStream.write(fileData.getBytes());
                    fileOutStream.flush();
                    fileOutStream.close();
                }
            }
        }
        res.setAttachment_file_path(fileName);
        
        //file complete
        bo.insertConsultant(res);
        String pcId=bo.getpcid();
        System.out.println("PCID "+pcId);
        res.setConsultant_empid(pcId);
        bo.insertConsultantAddress(res);
        bo.insertConsultantProof(res);
        bo.insertConsultantContact(res);
      
        //mail
       Properties configFile=new Properties();
       InputStream input = null;
        input = new FileInputStream("D:\\propertyfiles\\pc_configuration.properties");
        configFile.load(input);
         ojfGroupMail= configFile.getProperty("ojfGroupMail");
          System.out.println("ojfGroupMail "+configFile.getProperty("ojfGroupMail"));
          System.out.println("Details **** "+res.getPc_code()+"name : "+res.getConsultantName()+"com :  "+res.getCompany()+" startDateString "+startDateString+" enf "+endDateString);
          String rmmailid=bo.getRMmail(rmid);
          String nsmailid=bo.getNSmail();
          
        SendMailTLS mailObject= new SendMailTLS();
        
         MailMessages mailMessageObj = new MailMessages();
        String mailTo= "";
        String mailSubject= mailMessageObj.getOjfSubject("NewJoinee");
        String mailMessage= mailMessageObj.getOjfMessage(request, "NewJoinee", res.getPc_code()+ "#-#" +res.getConsultantName()+ "#-#" +res.getCompany()+ "#-#" +startDateString+ "#-#" +endDateString+"#-#"+res.getMail());
         
          String groupMail=ojfGroupMail;
           System.out.println("groupMail"+groupMail);
        String mailCC= "";
        mailCC=mailCC+','+rmmailid+','+nsmailid+','+res.getMail();
        mailTo=mailTo+','+groupMail;
        
   // pls uncoment in prod   
        mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
        mvc = new ModelAndView("redirect:pcList.htm");

        return mvc;
    }

    public ModelAndView updateConsultant(HttpServletRequest request, HttpServletResponse response, PcDataDTO res)
            throws UnknownHostException, IOException, ServletException, ParseException {
        System.out.println("upda\te into update  Consultant@@@@@@@@@@@@@@@@@@@@@@@@@@");
        ModelAndView mvc = null;
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");
        String pcId = request.getParameter("pcId");
        
        res.setPcId(pcId);
        String refVal = request.getParameter("employee_search");
       
      //  String rmId = request.getParameter("rmId");
         String rmVal = request.getParameter("manager");
         System.out.println("rmVal "+rmVal);
        String projectName = request.getParameter("projectName");
        System.out.println("Project name!!1"+projectName);
        String emp = (String) session.getAttribute("EMP_ID");
        if (emp == null || emp.equals("")) {

            return new ModelAndView("/unauthorised");
        }
        String rmid = "";
        List<PcDataDTO> empNameListRm = bo.getEmpDetails(rmVal);
        Iterator rmitr = empNameListRm.iterator();
        while (rmitr.hasNext()) {
            PcDataDTO tdtoo = (PcDataDTO) rmitr.next();
            rmid = tdtoo.getEmployee_idd();
            res.setRmId(rmid);
        }
        String startDateString = res.getStartdate();
        String endDateString = res.getEnddate();
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println("start date " + dateString1); // 2011-04-
        res.setStartdate(dateString1);
        res.setEnddate(dateString2);
        
        res.setConsultant_empid(pcId);
        System.out.println("countr $$" + res.getCountryId());
       
        String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
        String c_date = CommonMethods.getSystemDate(DATE_FORMAT_NOW);
        res.setCreated_date(c_date);
        String projectId = bo.getPrjDetail(projectName);
        res.setProjectId(projectId);
        res.setWorkLocationId(request.getParameter("worklocation"));
        //bo.insertConsultant(res);
        bo.updateConsultant(res);
        bo.updateConsultantAddress(res);
        bo.updateConsultantProof(res);
        bo.updateConsultantContact(res);
        //mail update 
         
       Properties configFile=new Properties();
       InputStream input = null;
        input = new FileInputStream("D:\\propertyfiles\\pc_configuration.properties");
        configFile.load(input);
         ojfGroupMail= configFile.getProperty("ojfGroupMail");
          System.out.println("ojfGroupMail "+configFile.getProperty("ojfGroupMail"));
          System.out.println("Details **** "+res.getPc_code()+"name : "+res.getConsultantName()+"com :  "+res.getCompany()+" startDateString "+startDateString+" enf "+endDateString);
          String rmmailid=bo.getRMmail(rmid);
          String nsmailid=bo.getNSmail();
          
        SendMailTLS mailObject= new SendMailTLS();
        
         MailMessages mailMessageObj = new MailMessages();
        String mailTo= "";
        String mailSubject= mailMessageObj.getOjfSubject("NewJoineeUpdate");
        String mailMessage= mailMessageObj.getOjfMessage(request, "NewJoineeUpdate", res.getPc_code()+ "#-#" +res.getConsultantName()+ "#-#" +res.getCompany()+ "#-#" +startDateString+ "#-#" +endDateString+"#-#"+res.getMail());
          System.out.println("rm mail&&&&&&&"+rmmailid);
          System.out.println("NS mail&&&&&&&"+nsmailid);
          String groupMail=ojfGroupMail;
           System.out.println("groupMail"+groupMail);
        String mailCC= "";
        mailCC=mailCC+','+rmmailid+','+nsmailid+','+res.getMail();
        mailTo=mailTo+','+groupMail;
        
     // pls uncommet in prod  
        mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC);
        mvc = new ModelAndView("redirect:pcList.htm");
        return mvc;
    }

    public ModelAndView excelexportPcList(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) throws Exception {
        ModelAndView mvc = new ModelAndView("/pcList");
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");

        String refVal = request.getParameter("employee_search");
        String employee_id = "";
        List<PcDataDTO> empNameList = bo.getEmpDetails(refVal);
        Iterator itr = empNameList.iterator();
        while (itr.hasNext()) {
            PcDataDTO tdtoo = (PcDataDTO) itr.next();
            tdtoo.getEmployee_idd();
            employee_id = tdtoo.getEmployee_idd();
            dto.setConsultant_empid(employee_id);
        }
        System.out.println("dto.getConsultant_empid()"+dto.getConsultant_empid());
        
        System.out.println("dto.getenddate ^^^^^"+dto.getEnddate());
          String startDateString = request.getParameter("fstartdate");
        String endDateString = request.getParameter("fenddate");
        if(startDateString!=null &&  endDateString!=null)
        {
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println("start date " + dateString1); // 2011-04-
        dto.setFstartdate(dateString1);
        dto.setFenddate(dateString2);
        mvc.addObject("fstartdate",dateString1);
      mvc.addObject("fenddate",dateString2);
        }
        List<PcDataDTO> details = null;
        if(dto.getConsultant_empid() != null ) {
            details = bo.getConsultantListByName(dto);
            System.out.println("inside iffff");
        }else{
            details = bo.getConsultantList(dto);
            System.out.println("inside else");
        }

        details = CommonMethods.getNewTimeFormatPc(details);

        ArrayList entireList = new ArrayList();
        for (int i = 0; i < details.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + details.get(i).getPc_code()));
            rowDataList.add(new String("" + details.get(i).getConsultantName()));
            rowDataList.add(new String("" + details.get(i).getCompany()));
            rowDataList.add(new String("" + details.get(i).getWorkLocationName()));
            rowDataList.add(new String("" + details.get(i).getAadhar()));
            rowDataList.add(new String("" + details.get(i).getContactname()));
            rowDataList.add(new String("" + details.get(i).getRelationship()));
            rowDataList.add(new String("" + details.get(i).getMobile()));
            rowDataList.add(new String("" + details.get(i).getRmName()));
            rowDataList.add(new String("" + details.get(i).getPracticeGroupName()));
            rowDataList.add(new String("" + details.get(i).getSubPracticeGroupName()));
            rowDataList.add(new String("" + details.get(i).getStartdate()));
            rowDataList.add(new String("" + details.get(i).getEnddate()));
           // rowDataList.add(new String("" + details.get(i).getCreated_date()));
            rowDataList.add(new String("" + details.get(i).getProjectName()));
            rowDataList.add(new String("" + details.get(i).getMail()));

            entireList.add(rowDataList);
        }

        CommonMethods.exportToExcel(response, entireList, "PcList.xls", "PcList", null);
        return null;
    }
    
    public ModelAndView excelexportPcListns(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) throws Exception {
        ModelAndView mvc = new ModelAndView("/pcListns");
        final WebApplicationContext ctx = getWebApplicationContext();
        PcServiceImpl bo = (PcServiceImpl) ctx.getBean("PcService");

        String refVal = request.getParameter("employee_search");
        String employee_id = "";
        List<PcDataDTO> empNameList = bo.getEmpDetails(refVal);
        Iterator itr = empNameList.iterator();
        while (itr.hasNext()) {
            PcDataDTO tdtoo = (PcDataDTO) itr.next();
            tdtoo.getEmployee_idd();
            employee_id = tdtoo.getEmployee_idd();
            dto.setConsultant_empid(employee_id);
        }
        System.out.println("dto.getConsultant_empid()"+dto.getConsultant_empid());
        
        System.out.println("dto.getenddate ^^^^^"+dto.getEnddate());
          String startDateString = request.getParameter("fstartdate");
        String endDateString = request.getParameter("fenddate");
        if(startDateString!=null &&  endDateString!=null)
        {
        Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
        Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDateString);
        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println("start date " + dateString1); // 2011-04-
        dto.setFstartdate(dateString1);
        dto.setFenddate(dateString2);
        mvc.addObject("fstartdate",dateString1);
      mvc.addObject("fenddate",dateString2);
        }
        List<PcDataDTO> details = null;
        if(dto.getConsultant_empid() != null ) {
            details = bo.getConsultantListByName(dto);
            System.out.println("inside iffff");
        }else{
            details = bo.getConsultantList(dto);
            System.out.println("inside else");
        }

        details = CommonMethods.getNewTimeFormatPc(details);

        ArrayList entireList = new ArrayList();
        for (int i = 0; i < details.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + details.get(i).getPc_code()));
            rowDataList.add(new String("" + details.get(i).getConsultantName()));
            rowDataList.add(new String("" + details.get(i).getCompany()));
            rowDataList.add(new String("" + details.get(i).getWorkLocationName()));
            rowDataList.add(new String("" + details.get(i).getAadhar()));
            rowDataList.add(new String("" + details.get(i).getContactname()));
            rowDataList.add(new String("" + details.get(i).getRelationship()));
            rowDataList.add(new String("" + details.get(i).getMobile()));
            rowDataList.add(new String("" + details.get(i).getRmName()));
            rowDataList.add(new String("" + details.get(i).getPracticeGroupName()));
            rowDataList.add(new String("" + details.get(i).getSubPracticeGroupName()));
            rowDataList.add(new String("" + details.get(i).getStartdate()));
            rowDataList.add(new String("" + details.get(i).getEnddate()));
           // rowDataList.add(new String("" + details.get(i).getCreated_date()));
            rowDataList.add(new String("" + details.get(i).getProjectName()));
            rowDataList.add(new String("" + details.get(i).getMail()));

            entireList.add(rowDataList);
        }

        CommonMethods.exportToExcel(response, entireList, "PcList.xls", "PcList", null);
        return null;
    }

    public ModelAndView getSubPracticeGroups(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("enter into getsubpratic gro !!!");
        ModelAndView modelAndView = new ModelAndView("json.jsp");
        response.getWriter().println("<option value=''>--Select--</option>");
        for (PcDataDTO dTO : ((PcServiceImpl) getWebApplicationContext().getBean("PcService")).getCmpStructData((request.getParameter("id")))) {
            response.getWriter().println("<option value='" + dTO.id + "'>" + dTO.name + "</option>");
        }
        return null;
    }

    public ModelAndView getRegions(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("json.jsp");
        response.getWriter().println("<option value=''>--Select--</option>");
        for (PcDataDTO dTO : ((PcServiceImpl) getWebApplicationContext().getBean("PcService")).getRegionList((request.getParameter("id")))) {
            response.getWriter().println("<option value='" + dTO.id + "'>" + dTO.regionName + "</option>");
        }
        return null;
    }

    public ModelAndView getCities(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("json.jsp");
        response.getWriter().println("<option value=''>--Select--</option>");
        for (PcDataDTO dTO : ((PcServiceImpl) getWebApplicationContext().getBean("PcService")).getCityList(request.getParameter("id"), request.getParameter("subId"))) {
            response.getWriter().println("<option value='" + dTO.id + "'>" + dTO.cityName + "</option>");
        }
        return null;
    }

    public ModelAndView project_search(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/project_search");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PcServiceImpl service = (PcServiceImpl) ctx.getBean("PcService");
            String refVal = request.getParameter("q");
            List<PcDataDTO> projectList = service.getPrjDetails(refVal);
            mvc.addObject("projectList", projectList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }
    
     public ModelAndView employee_search_user(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) {

        ModelAndView mvc = new ModelAndView("/employee_search_pc");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PcServiceImpl service = (PcServiceImpl) ctx.getBean("PcService");
            String refVal = request.getParameter("q");

            List<PcDataDTO> empNameList = service.getEmpDetails(refVal);
            mvc.addObject("empNameList", empNameList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }
     public ModelAndView pc_search_user(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) {

        ModelAndView mvc = new ModelAndView("/pc_search");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PcServiceImpl service = (PcServiceImpl) ctx.getBean("PcService");
            String refVal = request.getParameter("q");

            List<PcDataDTO> pcNameList = service.getPcDetails(refVal);
            mvc.addObject("pcNameList", pcNameList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }
     public ModelAndView pc_search_userns(HttpServletRequest request, HttpServletResponse response, PcDataDTO dto) {

        ModelAndView mvc = new ModelAndView("/pc_search");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PcServiceImpl service = (PcServiceImpl) ctx.getBean("PcService");
            String refVal = request.getParameter("q");

            List<PcDataDTO> pcNameList = service.getPcDetails(refVal);
            mvc.addObject("pcNameList", pcNameList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }
      public void downloadFileRF(HttpServletRequest requestObj, HttpServletResponse response, PcDataDTO formData) throws IOException {
        String fileName = requestObj.getParameter("filePath");
        String originalName = requestObj.getParameter("originalName");
        String fileType = requestObj.getParameter("fileType");
        String filePath = CommonConfigurations.fileUploadPath;
        final WebApplicationContext ctx = getWebApplicationContext();
        ((PcServiceImpl) ctx.getBean("PcService")).fileDownload(fileName, originalName, filePath, fileType, response);
    }
}
