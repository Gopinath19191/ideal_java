/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.controller;

import com.htl.ideal.mom.dao.MomDao;
import com.htl.ideal.mom.dao.MomDaoImpl;
import com.htl.ideal.mom.dto.MomDto;
import com.htl.ideal.mom.service.MomService;
import com.htl.ideal.mom.utils.commonMethods;
import com.htl.ideal.mom.utils.MailMessages;
import com.htl.ideal.mom.utils.actionmailMessage;
import com.htl.ideal.mom.utils.SendMail;
import com.htl.ideal.mom.utils.actionmail;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import java.util.Map;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author 16656
 */
public class MomController extends MultiActionController {

    public ModelAndView getAllDetails(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        int[] paging = null;
        ModelAndView mv = new ModelAndView("index");
        System.out.println("qqqqqqqqq");
        WebApplicationContext context = getWebApplicationContext();
        MomService service = (MomService) context.getBean("MomService");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("employee_id" + employee_id);
       dto.setId(employee_id);

        String id = request.getParameter("mom_edit");
        System.out.println("id     " + id);
        
        String viewid = request.getParameter("mom_view");
        System.out.println("Edit id is : " + viewid);

        String mom_status = request.getParameter("mom_status");
        System.out.println("mom_status     " + mom_status);

        String act_status = request.getParameter("mom_action_status");
        System.out.println("act_status     " + act_status);

        String momtitle = request.getParameter("mom_title");
        System.out.println("momtitle     " + momtitle);
        String mom_date = request.getParameter("mom_date");
        System.out.println("mom_date     " + mom_date);

        dto.setMom_id(id);
        dto.setMom_status(mom_status);
        dto.setAction_point_status(act_status);
        dto.setMom_date(mom_date);
        int page = 0;
//            String mom_status = request.getParameter("mom_status");
        if (mom_status != null && !mom_status.equals("")) {
            dto.setMom_status(mom_status);
        } else {
            dto.setMom_status("o");
        }
         if (dto.getMom_date() != null && !dto.getMom_date().equals("")) {
            Date date = new Date(mom_date);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);
                dto.setMom_date(strDate);
         }
        String pg = request.getParameter("page");
        String momPaging = request.getParameter("for_paging");
        
        if (pg == null || pg.equals("")) {
            page = 1;
        } else {
            page = Integer.parseInt(pg);
        }
        int rows = 15;
        int start;
        start = ((page - 1) * rows);
//             List<MomDto> list = service.getallMom(dto);
        dto.setStart_page(Integer.toString(start));
        dto.setEnd_page(Integer.toString(rows));
        int recordCount = service.getMomCount(dto);//list.size();
        //        int recordCount = services.getMomCount(dto);

        int[] pageNo = paging(rows, Integer.toString(recordCount), page);
        System.out.println("page no is :" + page);
        List<MomDto> mom_List = service.getallMom(dto);
        mv.addObject("details", mom_List);
        System.out.println("present" +dto.getMembers_present());
            
//        int statuscount = service.getstatuscount(dto);
//        mv.addObject("statuscount", statuscount);
        
        List<MomDto> status = service.getMomStatus();
        mv.addObject("statusData", status);
        
//        MomDto list = service.editMom(id);
//        mv.addObject("list", list);
        
        mv.addObject("selectedStatus", dto.getMom_status());
        mv.addObject("mom_title_search",dto.getMom_title());
        mv.addObject("mom_date", mom_date);
        mv.addObject("momPaging", momPaging);
        mv.addObject("paging", pageNo);
        
        return mv;
    }

    public ModelAndView ajaxsearch(HttpServletRequest request, HttpServletResponse response, MomDto filterData) {
        ModelAndView mvc = null;
        HttpSession session = request.getSession();
        mvc = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            MomService attendanceService = (MomService) ctx.getBean("MomService");
            String empVal = request.getParameter("q");
            System.out.println("ajax search----" + empVal);
            List<MomDto> empRes = attendanceService.getSearchList(empVal);
            mvc.addObject("empRes", empRes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

    public ModelAndView actionajaxsearch(HttpServletRequest request, HttpServletResponse response, MomDto filterData) {
        ModelAndView mvc = null;
        MomDto dto = new MomDto();
//        HttpSession session = request.getSession();
        mvc = new ModelAndView("/actionajaxsearch");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);
        dto.setId(employee_id);
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            MomService attendanceService = (MomService) ctx.getBean("MomService");
            String empVal = request.getParameter("q");
            System.out.println("ajax search----" + empVal);

            List<MomDto> momRes = attendanceService.getSearchmom(empVal, employee_id);
            mvc.addObject("momRes", momRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

    public ModelAndView ajaxsearchMomName(HttpServletRequest request, HttpServletResponse response, MomDto filterData) {
        ModelAndView mvc = null;
        MomDto dto = new MomDto();
        HttpSession session = request.getSession();
        mvc = new ModelAndView("/ajaxsearchMomName");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            MomService service = (MomService) ctx.getBean("MomService");
            String momid = (String) session.getAttribute("EMP_ID");
            dto.setId(momid);
            String momVal = request.getParameter("q");
            System.out.println("ajax search----" + momVal);
            List<MomDto> momRes = service.getSearchMomList(momVal, momid);
            mvc.addObject("momRes", momRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
//        mvc.addObject("selectedMomName", dto.getMom_title());
        return mvc;
    }

    public ModelAndView addMoM(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        ModelAndView mv = null;
        mv = new ModelAndView("addMoM");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        String minuted_name = service.getEmployeeName(employee_id);
        mv.addObject("minuted_name", minuted_name);
        List<MomDto> status = service.getStatus();
        mv.addObject("action_statusData", status);
         ArrayList timing = getTimevalues();
          mv.addObject("timing", timing);
            System.out.println("timing" +timing);
         
        return mv;
    }

    public ModelAndView insertMom(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {

        ModelAndView mv = null;
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        MomDao dao = (MomDaoImpl) contaxt.getBean("MomDao");
        HttpSession session = request.getSession(true);
        String minuted_by = (String) session.getAttribute("EMP_ID");
        if (minuted_by != null) {
            mv = new ModelAndView("redirect:getAllDetails.htm?page=1");
            dto.setMinuted_by(minuted_by);
            dto.setEmployee_id(minuted_by);
            System.out.println("momdate"+dto.getMom_date());
            
            Date date = new Date(dto.getMom_date());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            System.out.println("Converted String: " + strDate);
            dto.setMom_date(strDate);
            String strDate1 =null;
            String strDate2 =null;
            String mom_id = service.addMoM(dto);
            dto.getMom_status();
            String status = dto.getMom_status();
            System.out.println("MoM Status is: "+status);
            String typecheck=request.getParameter("typecheck");
            System.out.println("typecheck"+typecheck);
            String typecheck2=request.getParameter("typecheck2");
            System.out.println("typecheck"+typecheck2);
            String members_present=request.getParameter("members_present");
            System.out.println("members_present"+members_present);
            String members_absent=request.getParameter("members_absent");
            System.out.println("members_absent"+members_absent);
//            String present = request.getParameter("members_present");
//            System.out.println("present" + present);
//            dto.setMembers_present(present);
//            dto.getAction_point_status();
//            getTimevalues();
            dto.setMom_id(mom_id);
             
//             if(dto.getMembers_present().length >1){
             String mail_search="";
              String mail_search2="";
                System.out.println("present"+dto.getMembers_present());
               
                
                 System.out.println("present"+mail_search);
               
               if(typecheck.equals("0")){
                   int mailCcLength = request.getParameter("as_values_test").length();
                   System.out.println("mailCcLength"+mailCcLength);
                        if(mailCcLength!=0){
                            mail_search= request.getParameter("as_values_test").substring(0, (mailCcLength - 1));
//                            logger.info("mail_search" + mail_search);
                        }
                    if(mail_search !=null && !mail_search.equals("")){
                     
                        String[] member=mail_search.split(",");
                        for(int i=0;i<member.length;i++){
                           System.out.println("present "+member[i]);
                       String[] words = member[i].split("-");
                       String present= words[1].trim();
                       dto.setEmployee_no(present);
                       System.out.println("present " +present);
                    if (present != null) {
                        dto.setAttendance_status("p");
                        service.insertMomMembers2(dto);
                    }   
                   }
                }
               }            
//                for (int m = 0; m < dto.getTest().length; m++) {
//                   if((dto.getTest()[m] !=null && !dto.getTest()[m].equals("")) && !dto.getTest()[m].contains("<")){
//                      String member = (String) mail_search;//dto.getTest()[m]; 
                      
//              }
               else if(typecheck.equals("1")){
                   
             if(members_present!= null && !members_present.equals("")){
//                for (int m = 0; m < dto.getMembers_present().length; m++) {
//                   if(dto.getMembers_present() !=null && dto.getMembers_present()[m].contains("<")){
                 String[] member=members_present.split(";");
                 for(int i=0;i<member.length;i++){
//                     System.out.println("length"+dto.getMembers_present().length);
                    String[] mem= member[i].split("<");
//                    String[] words = mem.split("<");
                    String[] emp_number = mem[1].split(">");
                    String word=emp_number[0].trim();
                    dto.setWork_email_address(word);
                    if (word != null) {
                        dto.setAttendance_status("p");
                        service.insertMomMembers(dto);
                    } 
                }
            } 
                 }
                   
//  }   
//             if(dto.getMembers_absent().length >1){
               if(typecheck2.equals("0")){
                int mailCcLength2 = request.getParameter("as_values_test2").length();
                   System.out.println("mailCcLength2"+mailCcLength2);
                    if(mailCcLength2!=0){
                        mail_search2= request.getParameter("as_values_test2").substring(0, (mailCcLength2 - 1));
                    }
                logger.info("mail_search2" + mail_search2);
                  if(mail_search2 !=null && !mail_search2.equals("")){
                 String[] member=mail_search2.split(",");
                        for(int i=0;i<member.length;i++){
                           System.out.println("absent "+member[i]);
                       String[] words = member[i].split("-");
                       String absent= words[1].trim();
                       dto.setEmployee_no(absent);
                       System.out.println("absent " +absent);
                    if (absent != null) {
                        dto.setAttendance_status("a");
                        service.insertMomMembers2(dto);
                    }   
                   }
                }
               }
//               }
               else if(typecheck2.equals("1")){
//                if(dto.getMembers_absent()!= null && !dto.getMembers_absent().equals("")){
//                for (int n = 0; n < dto.getMembers_absent().length; n++) {
//                    if(dto.getMembers_absent() !=null && dto.getMembers_absent()[n].contains("<")){
                 if(members_absent!= null && !members_absent.equals("")){
                     
                String[] member = members_absent.split(";");
                for(int i=0;i<member.length;i++){
                   String[] words = member[i].split("<");
                String[] emp_number = words[1].split(">");
                String word=emp_number[0].trim();
                dto.setWork_email_address(word);
                if (word != null) {
                    dto.setAttendance_status("a");
                    service.insertMomMembers(dto);
                }
            }
            } 
                }
                 
//                }
               
            for (int i = 0; i < dto.getMom_agenda_points().length; i++) {
                dto.setAgenda_point(dto.getMom_agenda_points()[i]);
                service.insertMomAgenda(dto);
            }
            for (int j = 0; j < dto.getMom_discussion_points().length; j++) {
                dto.setDiscussion_point(dto.getMom_discussion_points()[j]);
                service.insertMomDiscussions(dto);
            }
            for (int k = 0; k < dto.getMom_action_point().length; k++) {
                String dat = dto.getMom_planned_close_date()[k];  
                String dat2 = dto.getMom_actual_close_date()[k];  
                if(dat != null && !dat.equals("")){
                Date date1 = new Date(dat);
                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                strDate1 = dateFormat1.format(date1);
                }
                if(dat2 != null && !dat2.equals("")){
                Date date2 = new Date(dat2);
                DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                strDate2 = dateFormat2.format(date2);
                System.out.println("Converted String: " + strDate1);
                dto.setPlanned_close_date(strDate1);
                dto.setActual_close_date(strDate2);
                }
                
                String emp_number = null;
                String member = (String) dto.getMom_action_point_employee_id()[k];
                System.out.println("member"+member);
                if(member != null && !member.equals("")){
                    String[] words = member.split(" ");
                    emp_number = words[0];
                    System.out.println("emp_number"+emp_number);
                    dto.setEmployee_no(emp_number);
                }
                if (emp_number != null) {
                    System.out.println("emp_number"+emp_number);
                    dto.setAction_point(dto.getMom_action_point()[k]);
                    dto.setAction_point_employee_id(emp_number);
                    dto.setPlanned_close_date(strDate1);
                     dto.setActual_close_date(strDate2);
                    dto.setTracking_comments(dto.getMom_tracking_comments()[k]);
                    dto.setAction_point_status(dto.getMom_action_point_status()[k]);
                    service.insertActionPoints(dto);
                }
            }

            if (mom_id != null){
                System.out.println("mom_id"+mom_id);
                if(status.equals("dr")  || status.equals("o")){
                     
//                String manager_id = service.getManagerId(Integer.toString(dto.getRequestor_id()));
                ArrayList<MomDto> connectionRes;
                connectionRes = dao.getMailDetails();
                String con_username = connectionRes.get(0).getConfigValue();
                String con_password = connectionRes.get(1).getConfigValue();
                String con_host = connectionRes.get(2).getConfigValue();
                String con_port = connectionRes.get(3).getConfigValue();
                SendMail mailObj = null;
                try {
                    mailObj = new SendMail(con_username, con_password, con_host, con_port);
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
                MailMessages mailMessageObj = new MailMessages();
                try {
                    System.out.println("Check Mail");
//                        List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(Integer.toString(filterData.getRequestor_id()));
                    String momCreatorMail = dao.getMomCreatorMail(minuted_by);
                    String empPrimaryKey = dao.getEmployeesPK(mom_id);
                    String employeesMail = dao.getEmployeesMail(empPrimaryKey);
                    String minuted_name = service.getEmployeeName(minuted_by);
                    String title = request.getParameter("mom_title");
                    HttpServletRequest requestObj = null;
                    String mailSubject = "REG: New MoM Created";
                    String mailMessage = mailMessageObj.getMessage(requestObj, "", minuted_name, title);
                    System.out.println("Mail 1...");
                    mailObj.smtpMail(momCreatorMail, mailSubject, mailMessage, employeesMail);
                    System.out.println("Mail 222...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 try {
                    System.out.println("Check Mail");
//                        List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(Integer.toString(filterData.getRequestor_id()));
                    String momCreatorMail = dao.getMomCreatorMail(minuted_by);
                    String empPrimaryKey = dao.getActionEmployeesPK(mom_id);
                    String employeesMail = dao.getEmployeesMail(empPrimaryKey);
                    String minuted_name = service.getEmployeeName(minuted_by);
                    String title = request.getParameter("mom_title");
                    HttpServletRequest requestObj = null;
                    String mailSubject = "REG: New MoM Created";
                    String mailMessage = mailMessageObj.getMessage(requestObj, "", minuted_name, title);
                    System.out.println("Mail 1...");
                    mailObj.smtpMail(momCreatorMail, mailSubject, mailMessage, employeesMail);
                    System.out.println("Mail 222...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }
            //service.addMoM(dto);
            mv.addObject("addMoM", "Record insertion has been successful");
             } else {
            ModelAndView mav = new ModelAndView("unauthorised");
            mav.addObject("ErrorMessage", "employee Id invalid");
        }           
        return mv;
    }
    

    public ModelAndView editMoM(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        ModelAndView mv = new ModelAndView("editMoM");
        String id = request.getParameter("mom_edit");
        System.out.println("Edit id is : " + id);

        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("employee id" + employee_id);
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        dto.getMom_status();
        dto.getAction_point_status();
         ArrayList timing = getTimevalues();
          mv.addObject("timing", timing);
            System.out.println("timing" +timing);

            String present=dto.getMemPresent();
        MomDto list = service.editMom(id);
        mv.addObject("list", list);
//        String getempno = service.getempno(employee_id);
        String minuted_name = service.getEmployeeName(employee_id);
        mv.addObject("minuted_name", minuted_name);
        
        List<MomDto> presentMember = service.getPresentMembers(id);
        mv.addObject("membersPresentList", presentMember);
        
        List<MomDto> absentMember = service.getAbsentMembers(id);
        mv.addObject("membersAbsentList", absentMember);
        
        List<MomDto> agendaList = service.getallAgenda(id);
         mv.addObject("momAgendaList", agendaList);
         
        List<MomDto> discussionList = service.getAllDiscussion(id);
        mv.addObject("momDiscussionList", discussionList);
        
        List<MomDto> actionList = service.getAllActions(id);
        mv.addObject("actionPointList", actionList);
        
        List<MomDto> actionStatus = service.getStatus();
         mv.addObject("action_statusData", actionStatus);
     
        mv.addObject("PresentList", dto.getMembers_present());
        mv.addObject("AbsentList", dto.getMembers_absent());
        
        List<MomDto> editemp = service.geteditemployees(id);
//         mv.addObject("editemp", editemp);
         request.setAttribute("editemp", editemp);
         System.out.println("editemp"+editemp);
        List<MomDto> editemp2 = service.geteditemployees2(id);
//         mv.addObject("editemp2", editemp2); 
        request.setAttribute("editemp2", editemp2);
          System.out.println("editemp2"+editemp2);
        dto.setMom_id(id);
        dto.setEmployee_id(employee_id);
        
//        List<MomDto> action = service.getActionPoint(id);
//        mv.addObject("actionItems", action);
        
        List<MomDto> history = service.getHistories(dto);
        mv.addObject("history", history);
        
        return mv;
    }

    public ModelAndView updateMom(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:getAllDetails.htm?page=1");
        String id = request.getParameter("mom_edit");
        System.out.println("Update id is : " + id);
//        String id = request.getParameter("employeeid");
//        System.out.println("Update id is :"+id);
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        String agenda=request.getParameter("mom_agenda_points");
            System.out.println("agenda"+agenda);
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        dto.setId(id);
        dto.setMom_status("o");
        dto.getAction_point_status();
        service.updateMom(dto);
        String actionid=request.getParameter("actionid");
            System.out.println("actionid"+actionid);
          String discussion=request.getParameter("mom_discussion_points");
            System.out.println("discussion"+discussion);  
             String mom_agenda_id=request.getParameter("mom_agenda_id");
            System.out.println("mom_agenda_id"+mom_agenda_id);  
             String mom_agenda_deleted=request.getParameter("mom_agenda_deleted");
            System.out.println("mom_agenda_deleted"+mom_agenda_deleted); 
              String mom_discussion_id=request.getParameter("mom_discussion_id");
            System.out.println("mom_discussion_id"+mom_discussion_id); 
              String mom_discussion_deleted=request.getParameter("mom_discussion_deleted");
            System.out.println("mom_discussion_deleted"+mom_discussion_deleted); 
            dto.setAgenda_point(agenda);
            dto.setAgenda_id(mom_agenda_id);
            dto.setDiscussion_id(mom_discussion_id);
            dto.setAgenda_deleted(mom_agenda_deleted);
            dto.setDiscussion_deleted(mom_discussion_deleted);
            dto.setDiscussion_point(discussion);
//        System.out.println("agenda"+dto.getMom_agenda_points());
//        System.out.println("discussion"+dto.getMom_discussion_points());
        if(dto.getMom_agenda_points()!=null && !dto.getMom_agenda_points().equals("")){
             dto.setMom_id(id);
            dto.setMom_agenda_id(dto.getMom_agenda_id());
            dto.setMom_agenda_points(dto.getMom_agenda_points());
            dto.setMom_agenda_deleted(dto.getMom_agenda_deleted());
            service.updateAgenda(dto);
        }
        if(dto.getMom_discussion_points()!= null && !dto.getMom_agenda_points().equals("")){
             dto.setMom_id(id);
            dto.setMom_discussion_id(dto.getMom_discussion_id());
            dto.setMom_discussion_points(dto.getMom_discussion_points());
            dto.setMom_discussion_deleted(dto.getMom_discussion_deleted());
            service.updateDiscussion(dto);
        }
        System.out.println("length"+dto.getMom_action_point().length);
        String[] rowid = actionid.split(",");
                System.out.println("rowid"+rowid);
                String strDate2=null;
        for (int k = 0; k < dto.getMom_action_point().length; k++) {
            System.out.println("testting"+k);
            System.out.println("planned_close_date"+dto.getMom_planned_close_date()[k]);
            
            if(dto.getMom_planned_close_date()[k] !=null && !dto.getMom_planned_close_date()[k].equals("")){
            Date date = new Date(dto.getMom_planned_close_date()[k]);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            
            System.out.println("Converted String: " + strDate);
//               dto.setAction_point_id(dto.getMom_action_point_id()[k]);
            dto.setPlanned_close_date(strDate);
            
            if(dto.getMom_actual_close_date()[k] !=null && !dto.getMom_actual_close_date()[k].equals("")){
            Date date2 = new Date(dto.getMom_actual_close_date()[k]);
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            strDate2 = dateFormat2.format(date2);
            
            System.out.println("Converted String: " + strDate2);
//               dto.setAction_point_id(dto.getMom_action_point_id()[k]);
            dto.setPlanned_close_date(strDate2);
            }
//            String minuted_name = service.getEmployeeName(employee_id);
            String member = (String) dto.getMom_action_point_employee_id()[k];
            System.out.println("members is:" + member);
            String[] words = member.split("-");
            System.out.println("number is :" + words);
            String emp_number = words[0];
            System.out.println("number is :" +emp_number);
            dto.setEmployee_no(emp_number);
            dto.setUpdated_by(employee_id);
             
//            if(dto.getAgenda_point() !=null && !dto.getAgenda_point().equals("")){
//                service.updateAgenda(dto);
//            }
//             if(dto.getDiscussion_point() !=null && !dto.getDiscussion_point().equals("")) {
//                 service.updateDiscussion(dto);
//             }
            if (emp_number != null) {
                System.out.println("checking1"+rowid.length);
                 for(int i=0;i<rowid.length;i++){
                       System.out.println("check"+dto.getMom_action_point_id()[k]);
                if (dto.getMom_action_point_id()[k].equals("0") && rowid[i].equals("0")){
                    System.out.println("id"+dto.getMom_action_point_id()[k]);
                    dto.setMom_id(id);
                    dto.setAction_point(dto.getMom_action_point()[k]);
                    dto.setAction_point_employee_id(emp_number);
                    dto.setPlanned_close_date(strDate);
                     dto.setActual_close_date(strDate2);
                    dto.setTracking_comments(dto.getMom_tracking_comments()[k]);
                    dto.setAction_point_status(dto.getMom_action_point_status()[k]);
                    dto.setUpdated_by(employee_id);
                    service.insertActionPoints(dto);
                }
                else if(dto.getMom_action_point_id()[k].equals(rowid[i])){
                     System.out.println("rowid1"+ rowid[i]);
                         System.out.println("checking3"+dto.getMom_action_point_id()[k]);
                    dto.setAction_point_id(dto.getMom_action_point_id()[k]);
                    dto.setAction_point(dto.getMom_action_point()[k]);
                    dto.setAction_point_employee_id(emp_number);
                    dto.setPlanned_close_date(strDate);
                     dto.setActual_close_date(strDate2);
                    dto.setTracking_comments(dto.getMom_tracking_comments()[k]);
                    dto.setAction_point_status(dto.getMom_action_point_status()[k]);
//                    dto.setActual_close_date(dto.getMom_actual_close_date()[k]);
                    dto.setAction_point_deleted(dto.getMom_action_point_deleted()[k]);
                    service.updateActionPoint(dto);
                }
            }
        }
        }
        }
            
        
        String minuted_name = service.getEmployeeName(employee_id);
        mv.addObject("minuted_name", minuted_name);
        List<MomDto> list = service.getallMom(dto);
        mv.addObject("details", list);
        List<MomDto> presentMember = service.getPresentMembers(id);
        mv.addObject("membersPresentList", presentMember);
        List<MomDto> absentMember = service.getAbsentMembers(id);
        mv.addObject("membersAbsentList", absentMember);
        List<MomDto> agendaList = service.getallAgenda(id);
        mv.addObject("momAgendaList", agendaList);
        List<MomDto> discussionList = service.getAllDiscussion(id);
        mv.addObject("momDiscussionList", discussionList);
        List<MomDto> actionList = service.getAllActions(id);
        mv.addObject("actionPointList", actionList);
        List<MomDto> actionStatus = service.getStatus();
        mv.addObject("action_statusData", actionStatus);
        
        
        mv.addObject("index", "Record has been updated successfully");
        return mv;
    }

    public ModelAndView updateMomwithSave(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:getAllDetails.htm?page=1");
        String id = request.getParameter("mom_edit");
        System.out.println("mom_edit " + id);
        
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        MomDao dao = (MomDaoImpl) contaxt.getBean("MomDao");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        
        String actionid=request.getParameter("actionid");
            System.out.println("actionid"+actionid);
        
        dto.setId(id);
        dto.setMinuted_by(employee_id);
        dto.setMom_status("s");
        
        String test = request.getParameter("test");
        System.out.println("test : " + test);
        String test2 = request.getParameter("test2");
        System.out.println("test2 : " + test2);
        System.out.println("current status is "+dto.getMom_status());
        dto.getAction_point_status();
        dto.setMom_test(test);
        dto.setMom_test2(test2);
//        String mom_id = service.addMomWithSave(dto);
       // String prevStatus = service.getCurrentMomStatus(id);
        //System.out.println("Previous status is "+prevStatus);
        service.updateMomwithSave(dto);
        dto.setMom_id(id);
        
        String typecheck=request.getParameter("typecheck");
        System.out.println("typecheck"+typecheck);
        String typecheck2=request.getParameter("typecheck2");
        System.out.println("typecheck"+typecheck2);
        String members_present=request.getParameter("members_present");
        System.out.println("members_present"+members_present);
        String members_absent=request.getParameter("members_absent");
        System.out.println("members_absent"+members_absent);

        String mail_search="";
        String mail_search2="";
//        for (int m = 0; m < dto.getTest().length; m++) {
//            System.out.println("presntmembers"+dto.getTest().length);
//            String member1 = (String) dto.getTest()[m];
//            System.out.println("presnt"+dto.getTest()[m]);
//            String[] words1 = member1.split("-");
//            String emp_number = words1[0];
//            dto.setEmployee_no(emp_number);
//            
//        if (emp_number != null) {
////             System.out.println("dffffffffgb"+ dto.getMom_present_attendance_id()[m]);
//                
//             if ((dto.getMom_present_attendance_id()[m].equals("0"))) {
//                System.out.println("present"+dto.getMembers_present());
//                System.out.println("present"+mail_search);
              
               if(typecheck.equals("0")){
                   int mailCcLength = request.getParameter("as_values_test").length();
                   System.out.println("mailCcLength"+request.getParameter("as_values_test"));
                    System.out.println("mailCcLength"+mailCcLength);
                     if(mailCcLength!=0){
                        mail_search= request.getParameter("as_values_test");//.substring(0, (mailCcLength - 1));
                        logger.info("mail_search" + mail_search);
                     }
                     if(mail_search !=null && !mail_search.equals("")){
                          String[] member2=mail_search.split(",");
                          for(int i=0;i<member2.length;i++){
                                System.out.println("present "+member2[i]);
                                String[] words2 = member2[i].split("-");
                                String present= words2[1].trim();
                                dto.setEmployee_no(present);
                                System.out.println("present " +present);
                          if (present != null) {
                                dto.setAttendance_status("p");
                                service.insertMomMembers2(dto);
                                }   
                         }
                    }
               }            
//                for (int m = 0; m < dto.getTest().length; m++) {
//                   if((dto.getTest()[m] !=null && !dto.getTest()[m].equals("")) && !dto.getTest()[m].contains("<")){
//                      String member = (String) mail_search;//dto.getTest()[m]; 
                      
//              }
           else if(typecheck.equals("1")){
                   
             if(members_present!= null && !members_present.equals("")){
//                for (int m = 0; m < dto.getMembers_present().length; m++) {
//                   if(dto.getMembers_present() !=null && dto.getMembers_present()[m].contains("<")){
                 String[] member3=members_present.split(";");
                 for(int i=0;i<member3.length;i++){
//                     System.out.println("length"+dto.getMembers_present().length);
                    String[] mem= member3[i].split("<");
//                    String[] words = mem.split("<");
                    String[] emp_number3 = mem[1].split(">");
                    String word=emp_number3[0].trim();
                    dto.setWork_email_address(word);
                    if (word != null) {
                        dto.setAttendance_status("p");
                        service.insertMomMembers(dto);
                        } 
                  }
              } 
            }
//        }
//    else {
//                    dto.setPresent_attendance_id(dto.getMom_present_attendance_id()[m]);
//                    dto.setAttendance_status("p");
//                    dto.setAttendance_deleted(dto.getMom_attendance_deleted()[m]);
//                    service.updateMomMembers(dto);
//                }
//            }
//        }

//        for (int n = 0; n < dto.getTest2().length; n++) {
//            String member = (String) dto.getTest2()[n];
//            String[] words = member.split("-");
//            String emp_number1 = words[0];
//            dto.setEmployee_no(emp_number1);
//            if (emp_number1 != null) {
//                if ((dto.getMom_absent_attendance_id()[n].equals("0"))) {
                     if(typecheck2.equals("0")){
                        int mailCcLength2 = request.getParameter("as_values_test2").length();
                          System.out.println("mailCcLength2"+mailCcLength2);
                            if(mailCcLength2!=0){
                                mail_search2= request.getParameter("as_values_test2").substring(0, (mailCcLength2 - 1));
                            }
                            logger.info("mail_search2" + mail_search2);
                            if(mail_search2 !=null && !mail_search2.equals("")){
                           String[] member4=mail_search2.split(",");
                                for(int i=0;i<member4.length;i++){
                                    System.out.println("absent "+member4[i]);
                                    String[] words4 = member4[i].split("-");
                                    String absent= words4[1].trim();
                                    dto.setEmployee_no(absent);
                                    System.out.println("absent " +absent);
                            if (absent != null) {
                                dto.setAttendance_status("a");
                                service.insertMomMembers2(dto);
                            }   
                        }
                    }
               }
//               }
               else if(typecheck2.equals("1")){
//                if(dto.getMembers_absent()!= null && !dto.getMembers_absent().equals("")){
//                for (int n = 0; n < dto.getMembers_absent().length; n++) {
//                    if(dto.getMembers_absent() !=null && dto.getMembers_absent()[n].contains("<")){
                    if(members_absent!= null && !members_absent.equals("")){

                       String[] member5 = members_absent.split(";");
                       for(int i=0;i<member5.length;i++){
                           String[] words5= member5[i].split("<");
                           String[] emp_number5 = words5[1].split(">");
                           String word=emp_number5[0].trim();
                           dto.setWork_email_address(word);
                           if (word != null) {
                               dto.setAttendance_status("a");
                               service.insertMomMembers(dto);
                           }
                       }
                   } 
            }
//        }else {
//                    dto.setPresent_attendance_id(dto.getMom_absent_attendance_id()[n]);
//                    dto.setAttendance_status("a");
//                    dto.setAttendance_deleted(dto.getMom_absent_attendance_deleted()[n]);
//                    service.updateMomMembers(dto);
//                }
//            }
//        }
             
    
                   
//  }   
//             if(dto.getMembers_absent().length >1){
               
        for (int i = 0; i < dto.getMom_agenda_points().length; i++) {

            String dsf = request.getParameter("mom_agenda_deleted");
            System.out.println("delete id is: " + dsf);
            if (dto.getMom_agenda_id()[i].equals("0")) {
                dto.setMom_id(id);
                dto.setAgenda_point(dto.getMom_agenda_points()[i]);
                service.insertMomAgenda(dto);
            } else {
                dto.setId(id);
                dto.setAgenda_id(dto.getMom_agenda_id()[i]);
                dto.setAgenda_point(dto.getMom_agenda_points()[i]);
                dto.setAgenda_deleted(dto.getMom_agenda_deleted()[i]);
                service.updateAgenda(dto);
//            }
        }
        }
        for (int j = 0; j < dto.getMom_discussion_points().length; j++) {

            if ( dto.getMom_discussion_id()[j].equals("0")) {
                dto.setMom_id(id);
                dto.setDiscussion_point(dto.getMom_discussion_points()[j]);
                service.insertMomDiscussions(dto);
            } else {
                dto.setId(id);
                dto.setDiscussion_id(dto.getMom_discussion_id()[j]);
                dto.setDiscussion_point(dto.getMom_discussion_points()[j]);
                dto.setDiscussion_deleted(dto.getMom_discussion_deleted()[j]);
                service.updateDiscussion(dto);
            }
        }
         String strDate2=null;
         String[] rowid = actionid.split(",");
                System.out.println("rowid"+rowid); 
        for (int k = 0; k < dto.getMom_action_point().length; k++) {
            
            if(dto.getMom_planned_close_date()[k] !=null && !dto.getMom_planned_close_date()[k].equals("")){ 
                Date date = new Date(dto.getMom_planned_close_date()[k]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);
            
            if(dto.getMom_actual_close_date()[k] !=null && !dto.getMom_actual_close_date()[k].equals("")){
                Date date2 = new Date(dto.getMom_actual_close_date()[k]);
                DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
               strDate2 = dateFormat2.format(date2);
                System.out.println("Converted String: " + strDate2);
             }
//               dto.setAction_point_id(dto.getMom_action_point_id()[k]);
                dto.setPlanned_close_date(strDate);
                dto.setActual_close_date(strDate2);
                String member8 = (String) dto.getMom_action_point_employee_id()[k];
                String[] words8 = member8.split("-");
                String emp_number8 = words8[0];
                dto.setEmployee_no(emp_number8);
            if (emp_number8 != null) {
                  for(int i=0;i<rowid.length;i++){
                    if (dto.getMom_action_point_id()[k].equals("0") && rowid[i].equals("0")){
                       dto.setMom_id(id);
                       dto.setAction_point(dto.getMom_action_point()[k]);
                       dto.setAction_point_employee_id(emp_number8);
                       dto.setPlanned_close_date(strDate);
                       dto.setActual_close_date(strDate2);
                       dto.setTracking_comments(dto.getMom_tracking_comments()[k]);
                       dto.setAction_point_status(dto.getMom_action_point_status()[k]);
                       service.insertActionPoints(dto);
                    }  
                else if(dto.getMom_action_point_id()[k].equals(rowid[i])){
                     System.out.println("rowid1"+ rowid[i]);
                    dto.setAction_point_id(dto.getMom_action_point_id()[k]);
                    dto.setAction_point(dto.getMom_action_point()[k]);
                    dto.setAction_point_employee_id(emp_number8);
                    dto.setPlanned_close_date(strDate);
                    dto.setTracking_comments(dto.getMom_tracking_comments()[k]);
                    dto.setAction_point_status(dto.getMom_action_point_status()[k]);
                    dto.setActual_close_date(strDate2);
                    dto.setAction_point_deleted(dto.getMom_action_point_deleted()[k]);
                    service.updateActionPoint(dto);
                }
            }
        }
      }
        }
        String minuted_name = service.getEmployeeName(employee_id);
        mv.addObject("minuted_name", minuted_name);
        List<MomDto> list = service.getallMom(dto);
        mv.addObject("details", list);
        List<MomDto> presentMember = service.getPresentMembers(id);
        mv.addObject("membersPresentList", presentMember);
        List<MomDto> absentMember = service.getAbsentMembers(id);
        mv.addObject("membersAbsentList", absentMember);
        List<MomDto> agendaList = service.getallAgenda(id);
        mv.addObject("momAgendaList", agendaList);
        List<MomDto> discussionList = service.getAllDiscussion(id);
        mv.addObject("momDiscussionList", discussionList);
        List<MomDto> actionList = service.getAllActions(id);
        mv.addObject("actionPointList", actionList);
        List<MomDto> actionStatus = service.getStatus();
        mv.addObject("action_statusData", actionStatus);
//        List<MomDto> history  =service.getHistory(dto);
//            mv.addObject("history",history);
       ArrayList<MomDto> connectionRes;
                connectionRes = dao.getMailDetails();
                String con_username = connectionRes.get(0).getConfigValue();
                String con_password = connectionRes.get(1).getConfigValue();
                String con_host = connectionRes.get(2).getConfigValue();
                String con_port = connectionRes.get(3).getConfigValue();
                SendMail mailObj = null;
                try {
                    mailObj = new SendMail(con_username, con_password, con_host, con_port);
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
                MailMessages mailMessageObj = new MailMessages();
                try {
                    System.out.println("Check Mail");
//                        List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(Integer.toString(filterData.getRequestor_id()));
                    String momCreatorMail = dao.getMomCreatorMail(employee_id);
                    String empPrimaryKey = dao.getEmployeesPK(id);
                    String employeesMail = dao.getEmployeesMail(empPrimaryKey);
                    String minuted_name2 = service.getEmployeeName(employee_id);
                    String title = request.getParameter("mom_title");
                    HttpServletRequest requestObj = null;
                    String mailSubject = "REG: New MoM Created";
                    String mailMessage = mailMessageObj.getMessage(requestObj, "", minuted_name2, title);
                    System.out.println("Mail 1...");
                    mailObj.smtpMail(momCreatorMail, mailSubject, mailMessage, employeesMail);
                    System.out.println("Mail 222...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 try {
                    System.out.println("Check Mail");
//                        List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(Integer.toString(filterData.getRequestor_id()));
                    String momCreatorMail = dao.getMomCreatorMail(employee_id);
                    String empPrimaryKey = dao.getActionEmployeesPK(id);
                    String employeesMail = dao.getEmployeesMail(empPrimaryKey);
                    String minuted_name2 = service.getEmployeeName(employee_id);
                    String title = request.getParameter("mom_title");
                    HttpServletRequest requestObj = null;
                    String mailSubject = "REG: New MoM Created";
                    String mailMessage = mailMessageObj.getMessage(requestObj, "", minuted_name2, title);
                    System.out.println("Mail 1...");
                    mailObj.smtpMail(momCreatorMail, mailSubject, mailMessage, employeesMail);
                    System.out.println("Mail 222...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        mv.addObject("index", "Record has been updated successfully");
        return mv;
    }
        
    

    public ModelAndView viewMomDetails(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        ModelAndView mv = new ModelAndView("viewMomDetails");
        String id = request.getParameter("mom_view");
        System.out.println("Edit id is : " + id);

        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("employee_id" + employee_id);

        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        dto.setId(employee_id);
        dto.setMom_id(id);
        dto.setAbsent_attendance_id(employee_id);
        dto.setAgenda_id(id);
        dto.setAction_point_id(employee_id);
        dto.setAttendance_id(employee_id);
        dto.setDiscussion_id(id);
        dto.setParent_id(employee_id);
        MomDto list = service.editMom(id);
//         List<MomDto> mom_List = service.getallMom(dto);
//        mv.addObject("details", mom_List);
        String minuted_name = service.getEmployeeName(employee_id);
        List<MomDto> presentMember = service.getPresentMembers(id);
        List<MomDto> absentMember = service.getAbsentMembers(id);
        List<MomDto> agendaList = service.getallAgenda(id);
        List<MomDto> discussionList = service.getAllDiscussion(id);
//        List<MomDto> actionList = service.getAllActions(id);
        List<MomDto> actionStatus = service.getStatus();


        mv.addObject("list", list);
        mv.addObject("minuted_name", minuted_name);
        mv.addObject("membersPresentList", presentMember);
        mv.addObject("membersAbsentList", absentMember);
        mv.addObject("momAgendaList", agendaList);
        mv.addObject("momDiscussionList", discussionList);
//        mv.addObject("actionPointList", actionList);
        mv.addObject("action_statusData", actionStatus);
        List<MomDto> history = service.getHistory(dto);
        mv.addObject("history", history);
        List<MomDto> action = service.getActionPoint(id);
        mv.addObject("actionData", action);
        return mv;
    }

    public ModelAndView updateMyMomActionItems(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:getAllDetails.htm?page=1");

        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
//        dto.setMom_id(id);
        String actionid = request.getParameter("mom_action_point_id");
        System.out.println("length of action     " + dto.getMom_tracking_comments().length);
        System.out.println("id     " + actionid);
        String id = request.getParameter("mom_edit");
        System.out.println("mom_id " + id);

        for (int p = 0; p < dto.getMom_actual_close_date().length; p++) {
            Date date = new Date(dto.getMom_actual_close_date()[p]);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            System.out.println("Converted String: " + strDate);

            dto.setAction_point_id(dto.getMom_action_point_id()[p]);
            dto.setActual_close_date(strDate);
            dto.setTracking_comments(dto.getMom_tracking_comments()[p]);
            dto.setAction_point_status(dto.getMom_action_point_status()[p]);
//            dto.setAction_point_deleted(dto.getMom_action_point_deleted()[p]);
            System.out.println("actual" + dto.getMom_action_point_id()[p]);
            service.updateActionPointItems(dto);
        }

        List<MomDto> action = service.getActionPoint(id);
        mv.addObject("actionItems", action);
        List<MomDto> status = service.getStatus();
        mv.addObject("statusData", status);

        int count = 0;

        for (int p = 0; p < dto.getMom_actual_close_date().length; p++) {
            Date date = new Date(dto.getMom_actual_close_date()[p]);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);
            System.out.println("Converted String: " + strDate);

            if (dto.getMom_previous_planned_close_date()[p].equals("null") || dto.getMom_previous_planned_close_date()[p].equals("")) {
                System.out.println("actual date" + dto.getMom_actual_close_date()[p]);
                System.out.println("comments" + dto.getMom_tracking_comments()[p]);
            } else if (!dto.getMom_previous_planned_close_date()[p].equals("strDate") || !dto.getMom_remarks()[p].equals(dto.getMom_tracking_comments()[p])) {
                dto.setActual_close_date(strDate);
                dto.setAction_point_id(dto.getMom_action_point_id()[p]);
                dto.setTracking_comments(dto.getMom_tracking_comments()[p]);
                System.out.println(" date " + dto.getMom_actual_close_date()[p] + "   id  " + dto.getMom_action_point_id()[p]);
                System.out.println("1st " + dto.getMom_previous_planned_close_date()[p] + "  2nd  " + dto.getMom_actual_close_date()[p]);
                System.out.println("date" + dto.getMom_previous_planned_close_date()[p]);
                System.out.println(" " + count++);
                service.updateHistory(dto);
            } else {
                System.out.println("gggg");
            }
        }
        return mv;
    }

    public ModelAndView getActionListDetails(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        int[] paging = null;
        ModelAndView mv = new ModelAndView("myActionPointList");
        System.out.println("qqqqqqqqq");
        WebApplicationContext context = getWebApplicationContext();
        MomService service = (MomService) context.getBean("MomService");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("employee_id" + employee_id);
        dto.setId(employee_id);

        String id = request.getParameter("mom_edit");
        System.out.println("id     " + id);

        String viewid = request.getParameter("mom_view");
        System.out.println("Edit id is : " + viewid);

        String act_status = request.getParameter("mom_action_status");
        System.out.println("act_status     " + act_status);

        String actionstatus = request.getParameter("mom_action_point_status");
        System.out.println("actionstatus     " + actionstatus);

        String momtitle = request.getParameter("mom_title");
        System.out.println("momtitle     " + momtitle);
        String action_mom_title = request.getParameter("action_mom_title");
        System.out.println("action_mom_title     " + action_mom_title);

        if (act_status != null) {
            dto.setAction_point_status(act_status);
        } else {
            dto.setAction_point_status("o");
        }
        dto.setMom_id(id);
//            dto.setMom_status(mom_status); 
        dto.setMom_action_point_status(dto.getMom_action_point_status());
        int page_action = 0;
        String pgact = request.getParameter("page_action");
        System.out.println("page " + pgact);
        if (pgact == null || pgact.equals("")) {
            page_action = 1;
        } else {
            page_action = Integer.parseInt(pgact);
        }
        int rows = 15;
        int start_action;
        start_action = ((page_action - 1) * rows);
        //int recordCountAction = service.getMomCount(dto);
        dto.setStart_page(null);
        dto.setEnd_page(null);
//             List<MomDto> record_list = service.getActionListDetails(dto);
        dto.setStart_page(Integer.toString(start_action));
        dto.setEnd_page(Integer.toString(rows));
        int recordCountAction = service.getActionRecordCount(dto);//record_list.size();
        System.out.println("recordCountAction  " + recordCountAction);
        int[] pageNoAction = paging(rows, Integer.toString(recordCountAction), page_action);
        List<MomDto> mom_list = service.getActionListDetails(dto);
        mv.addObject("mom_list", mom_list);
        System.out.println("page no is :" + page_action);

        List<MomDto> actionStatus = service.getStatus();
        mv.addObject("action_statusData", actionStatus);
        mv.addObject("employee_id", employee_id);
        mv.addObject("selectedActionStatus", dto.getAction_point_status());
        mv.addObject("mom_title_Actionsearch", dto.getAction_mom_title());
        //            mv.addObject("mom_id_search", dto.getId());
        mv.addObject("paging_actions", pageNoAction);

        return mv;

    }

    public ModelAndView editActionPoints(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("editActionpoint");
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        System.out.println("dfgfhfgjhg");
        String id = request.getParameter("mom_edit");
        System.out.println("id     " + id);

        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);
        String minuted_name = service.getEmployeeName(employee_id);
        mv.addObject("minuted_name", minuted_name);
        MomDto dto = new MomDto();
        dto.setMom_id(id);
        dto.setEmployee_id(employee_id);


        mv.addObject("employee_id", employee_id);

        MomDto meet = service.getMeetingDetails(id);
        mv.addObject("meet", meet);

        List<MomDto> agenda = service.getAgenda(id);
        mv.addObject("agenda", agenda);

        List<MomDto> discuss = service.getDiscussion(id);
        mv.addObject("discuss", discuss);

        List<MomDto> action = service.getActionPoint(id);
        mv.addObject("actionData", action);

        List<MomDto> present = service.getPresentDetails(id);
        mv.addObject("present", present);
        List<MomDto> absent = service.getAbsentDetails(id);
        mv.addObject("absent", absent);

        List<MomDto> status = service.getStatus();
        mv.addObject("statusData", status);

        List<MomDto> history = service.getHistory(dto);
        mv.addObject("history", history);

        return mv;
    }

    public ModelAndView viewMomList(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {

        ModelAndView mv = new ModelAndView("viewMomList");
        WebApplicationContext contaxt = getWebApplicationContext();
        String id = request.getParameter("mom_edit");
        System.out.println("id     " + id);
        String actionid = request.getParameter("mom_action_point_id");
        System.out.println("id     " + actionid);

        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);
        MomService service = (MomService) contaxt.getBean("MomService");

        dto.setId(employee_id);
        dto.setMom_id(id);
        mv.addObject("employee_id", employee_id);

        MomDto meet = service.getMeetingDetails(id);
        mv.addObject("meet", meet);

        List<MomDto> agenda = service.getAgenda(id);
        mv.addObject("agenda", agenda);

        List<MomDto> action = service.getActionPoint(id);
        mv.addObject("actionData", action);

        List<MomDto> discuss = service.getDiscussion(id);
        mv.addObject("discuss", discuss);

        List<MomDto> present = service.getPresentDetails(id);
        mv.addObject("present", present);

        List<MomDto> absent = service.getAbsentDetails(id);
        mv.addObject("absent", absent);

        List<MomDto> history = service.getHistory(dto);
        mv.addObject("history", history);
        
        String minuted_name = service.getEmployeeName(employee_id);
        mv.addObject("minuted_name", minuted_name);
        return mv;
    }

    public ModelAndView updateActionPoints(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:getActionListDetails.htm?page=1");
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        MomDao dao = (MomDaoImpl) contaxt.getBean("MomDao");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);

        String id = request.getParameter("mom_edit_first");
        System.out.println("id     " + id);
        String act_status = request.getParameter("actionstatus");
        System.out.println("act_status     " + act_status);
        String actionid = request.getParameter("action_point_employee_id");
        System.out.println("actionid     " + actionid);
        String minuted_name = service.getEmployeeName(employee_id);
        dto.setMom_id(id);

        dto.setId(employee_id);
//        dto.setAction_point_employee_id(actionid);
        dto.setAction_point_status(act_status);
        dto.setMinuted_by(minuted_name);
        dto.setUpdated_by(employee_id);

        String selectedRows = request.getParameter("selectedRows");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<MomDto> actiondetails = Arrays.asList(mapper.readValue(selectedRows.toString(), MomDto[].class));
        if (actiondetails != null && actiondetails.size() > 0) {
            for (int iTSEntry = 0; iTSEntry < actiondetails.size(); iTSEntry++) {
                MomDto actionlist = actiondetails.get(iTSEntry);
                System.out.println("*******************         " + actionlist.getActual_close_date());
                Date date = new Date(actionlist.getActual_close_date());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);
                actionlist.setActual_close_date(strDate);
                String a = actionlist.getAction_point_status();
                actionlist.setUpdated_by(employee_id);
                service.updateActionPoints(actionlist);

                if (a.equals("b")) {
                    service.updatesentback(id);
                    ArrayList<MomDto> connectionRes;
                    connectionRes = dao.getMailDetails();
                    String con_username = connectionRes.get(0).getConfigValue();
                    String con_password = connectionRes.get(1).getConfigValue();
                    String con_host = connectionRes.get(2).getConfigValue();
                    String con_port = connectionRes.get(3).getConfigValue();
                    actionmail mailObj = null;
                    try {
                        mailObj = new actionmail(con_username, con_password, con_host, con_port);
                    } catch (FileNotFoundException ex) {
                    } catch (IOException ex) {
                    }
                    actionmailMessage mailMessageObj = new actionmailMessage();
                    try {

                        System.out.println("Check Mail");
//                        List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(Integer.toString(filterData.getRequestor_id()));
                        String momCreatorMail = dao.getMomCreatorMail(employee_id);
//                        String empPrimaryKeyy = dao.getempPK(minuted_name);
//                        String employeesMail = dao.getEmployeesMail(empPrimaryKeyy);
                        String minuted = service.getEmployeeName(employee_id);
                        String title = request.getParameter("mom_title");
                        HttpServletRequest requestObj = null;
                        String mailSubject = "REG: Not applicable for me";
                        String mailMessage = mailMessageObj.getMessage(requestObj, "", minuted, title);
                        System.out.println("Mail 1...");
                        mailObj.smtpMail(momCreatorMail, mailSubject, mailMessage);
                        System.out.println("Mail 222...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        mv.addObject("employee_id", employee_id);
//        List<MomDto> action = service.getActionPoint(id);
//        mv.addObject("actionData", action);
        mv.addObject("minuted_name", minuted_name);
        int count = 0;
        if (actiondetails != null && actiondetails.size() > 0) {
            System.out.println("list before history "+actiondetails.size());
            for (int i = 0; i < actiondetails.size(); i++) {
                MomDto actionlist = actiondetails.get(i);
//                if (actionlist.getPrevious_planned_close_date() == null || actionlist.getPrevious_planned_close_date().equals("")) {
//                } 
              if (!actionlist.getPrevious_planned_close_date().equals("strDate") || !actionlist.getRemarks().equals(actionlist.getTracking_comments())) {
                    service.updateHistory(actionlist);
                } else {
                    System.out.println("gggg");
                }
            }
        }
        List<MomDto> history = service.getHistory(dto);
        mv.addObject("history", history);
       return mv;
    }
    public ModelAndView updateMomActionPoints(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:editMomActionPoints.htm?page=1");
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        MomDao dao = (MomDaoImpl) contaxt.getBean("MomDao");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);

        String id = request.getParameter("mom_edit_first");
        System.out.println("momid     " + id);
        
        String act_status = request.getParameter("actionstatus");
        System.out.println("act_status     " + act_status);

        String minuted_name = service.getEmployeeName(employee_id);
        dto.setMom_id(id);

        dto.setId(employee_id);
        dto.setAction_point_status(act_status);
        dto.setMinuted_by(minuted_name);
        dto.setUpdated_by(employee_id);

        String selectedRows = request.getParameter("selectedRows");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<MomDto> actiondetails = Arrays.asList(mapper.readValue(selectedRows.toString(), MomDto[].class));
        if (actiondetails != null && actiondetails.size() > 0) {
            for (int iTSEntry = 0; iTSEntry < actiondetails.size(); iTSEntry++) {
                MomDto actionlist = actiondetails.get(iTSEntry);
                System.out.println("*******************         " + actionlist.getActual_close_date());
                Date date = new Date(actionlist.getActual_close_date());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);
                actionlist.setActual_close_date(strDate);
                String a = actionlist.getAction_point_status();
                actionlist.setUpdated_by(employee_id);
                service.updateActionPoints(actionlist);

                if (a.equals("b")) {
                    service.updatesentback(id);
                    ArrayList<MomDto> connectionRes;
                    connectionRes = dao.getMailDetails();
                    String con_username = connectionRes.get(0).getConfigValue();
                    String con_password = connectionRes.get(1).getConfigValue();
                    String con_host = connectionRes.get(2).getConfigValue();
                    String con_port = connectionRes.get(3).getConfigValue();
                    actionmail mailObj = null;
                    try {
                        mailObj = new actionmail(con_username, con_password, con_host, con_port);
                    } catch (FileNotFoundException ex) {
                    } catch (IOException ex) {
                    }
                    actionmailMessage mailMessageObj = new actionmailMessage();
                    try {

                        System.out.println("Check Mail");
//                        List<ProcurementPackagesDto> approverDetails = dao.getApproverDetails(Integer.toString(filterData.getRequestor_id()));
                        String momCreatorMail = dao.getMomCreatorMail(employee_id);
//                        String empPrimaryKeyy = dao.getempPK(minuted_name);
//                        String employeesMail = dao.getEmployeesMail(empPrimaryKeyy);
                        String minuted = service.getEmployeeName(employee_id);
                        String title = request.getParameter("mom_title");
                        HttpServletRequest requestObj = null;
                        String mailSubject = "REG: Not applicable for me";
                        String mailMessage = mailMessageObj.getMessage(requestObj, "", minuted, title);
                        System.out.println("Mail 1...");
                        mailObj.smtpMail(momCreatorMail, mailSubject, mailMessage);
                        System.out.println("Mail 222...");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        mv.addObject("employee_id", employee_id);
//        List<MomDto> action = service.getActionPoint(id);
//        mv.addObject("actionData", action);
        mv.addObject("minuted_name", minuted_name);
        int count = 0;
        if (actiondetails != null && actiondetails.size() > 0) {
            System.out.println("list before history "+actiondetails.size());
            for (int i = 0; i < actiondetails.size(); i++) {
                MomDto actionlist = actiondetails.get(i);
//                if (actionlist.getPrevious_planned_close_date() == null || actionlist.getPrevious_planned_close_date().equals("")) {
//                    System.out.println("required");
//                } 
                 if (!actionlist.getPrevious_planned_close_date().equals("strDate") || !actionlist.getRemarks().equals(actionlist.getTracking_comments())) {
                    System.out.println("required1");
                    service.updateHistory(actionlist);
                } else {
                    System.out.println("gggg");
                }
            }
        }
        List<MomDto> history = service.getHistory(dto);
        mv.addObject("history", history);
       return mv;
    }

//    public ModelAndView minutesOfMeeting(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
//        int[] paging = null;
//        ModelAndView mv = new ModelAndView("minutesOfMeeting");
//        System.out.println("qqqqqqqqq");
//        WebApplicationContext context = getWebApplicationContext();
//        MomService service = (MomService) context.getBean("MomService");
//        HttpSession session = request.getSession(true);
//        String employee_id = (String) session.getAttribute("EMP_ID");
//        System.out.println("employee_id" + employee_id);
//        dto.setId(employee_id);
//
//        String id = request.getParameter("mom_edit");
//        System.out.println("id     " + id);
//
//        String viewid = request.getParameter("mom_view");
//        System.out.println("Edit id is : " + viewid);
//
//        String mom_status = request.getParameter("mom_status");
//        System.out.println("mom_status     " + mom_status);
//
//        String act_status = request.getParameter("mom_action_status");
//        System.out.println("act_status     " + act_status);
//
//        String actionstatus = request.getParameter("mom_action_point_status");
//        System.out.println("actionstatus     " + actionstatus);
//        String momtitle = request.getParameter("mom_title");
//        System.out.println("momtitle     " + momtitle);
//
//        if (act_status != null && !act_status.equals("")) {
//            dto.setAction_point_status(act_status);
//        } else {
//            dto.setAction_point_status("o");
//        }
//        dto.setMom_id(id);
//        dto.setMom_status(mom_status);
//        dto.setMom_action_point_status(dto.getMom_action_point_status());
////                List<MomDto> details= service.getallMom(dto);
////                mv.addObject("details",details);
////            int page_action = 0;
////            String pg = request.getParameter("page_action");
////            if (pgact == null) {
////                page_action = 1;
////            } else {
////                page_action = Integer.parseInt(pgact);
////            }
////            int rows = 15;
////            int start_action;
////            start_action = ((page_action - 1) * rows);
////            List<MomDto> record_list= service.getActionListDetails(dto);
////            dto.setStart_page(Integer.toString(start_action));
////            dto.setEnd_page(Integer.toString(rows));
////            int recordCount = record_list.size();
////            List<MomDto> mom_list= service.getActionListDetails(dto);
////            mv.addObject("mom_list",mom_list);
////            int[] pageNo = paging(rows, Integer.toString(recordCount),page);
////
////
////            List<MomDto> momstatusData= service.getMomStatus();
////            mv.addObject("momstatusData",momstatusData);   
////
////            List<MomDto> status= service.getStatus();
////            mv.addObject("statusData",status);
////
////            mv.addObject("employee_id",employee_id);
////            mv.addObject("selectedStatus",dto.getAction_point_status());
////            mv.addObject("selectedMomStatus",dto.getMom_status());
////            mv.addObject("mom_title_search", dto.getMom_title());
////            mv.addObject("mom_title_Actionsearch", dto.getAction_mom_title());
////
////            mv.addObject("paging", paging(rows, Integer.toString(recordCount), page));  
//        int page = 0;
//        String pg = request.getParameter("page");
//        //        String filter = request.getParameter("mom_flag");
//        //        System.out.println("flag is "+filter);
//        String momPaging = request.getParameter("for_paging");
//        if (pg == null || pg.equals("")) {
//            page = 1;
//        } else {
//            page = Integer.parseInt(pg);
//        }
//        int rows = 15;
//        int start;
//        start = ((page - 1) * rows);
//        List<MomDto> list = service.getallMom(dto);
//        dto.setStart_page(Integer.toString(start));
//        dto.setEnd_page(Integer.toString(rows));
//        int recordCount = list.size();
//        //        int recordCount = service.getMomCount(dto);
//        List<MomDto> mom_List = service.getallMom(dto);
//
//        int[] pageNo = paging(rows, Integer.toString(recordCount), page);
//        System.out.println("page no is :" + page);
//        
//        mv.addObject("details", mom_List);
//        List<MomDto> status = service.getMomStatus();
//        mv.addObject("statusData", status);
//        mv.addObject("selectedStatus", dto.getMom_status());
//        mv.addObject("mom_title_search", dto.getMom_title());
//        mv.addObject("momPaging", momPaging);
//        mv.addObject("paging", pageNo);
//        //String id = request.getParameter("mom_edit");
//        //        System.out.println("id     " + id);
//        //            String filter1 = request.getParameter("mom_title");
//        //            System.out.println("flag is "+filter1);
//        //            String mom_status = request.getParameter("mom_status"); 
//        //            System.out.println("mom_status     "+mom_status);
//
//        //            dto.setMom_action_status(action_status);
//        //            int page_action = Integer.parseInt(request.getParameter("page_action"));
//        String action_point_status = request.getParameter("mom_action_point_status");
//        System.out.println("id     " + action_point_status);
//        String action_status = request.getParameter("mom_action_status");
//        System.out.println("id saddasdfsafdasf    " + action_status);
//        if (action_status != null && !action_status.equals("")) {
//            dto.setAction_point_status(action_status);
//        } else {
//            dto.setAction_point_status("o");
//        }
//        dto.setMom_id(id);
//        dto.setMom_action_point_status(dto.getMom_action_point_status());
//        int page_action = 0;
//        String pgact = request.getParameter("page_action");
//        System.out.println("page " + pgact);
//        if (pgact == null || pgact.equals("")) {
//            page_action = 1;
//        } else {
//            page_action = Integer.parseInt(pgact);
//        }
//
//        int start_action;
//        start_action = ((page_action - 1) * rows);
//        //int recordCountAction = service.getMomCount(dto);
//        dto.setStart_page(null);
//        dto.setEnd_page(null);
//        List<MomDto> record_list = service.getActionListDetails(dto);
//        dto.setStart_page(Integer.toString(start_action));
//        dto.setEnd_page(Integer.toString(rows));
//        int recordCountAction = record_list.size();
//        System.out.println("recordCountAction  " + recordCountAction);
//        List<MomDto> mom_list = service.getActionListDetails(dto);
//        
//        int[] pageNoAction = paging(rows, Integer.toString(recordCountAction), page_action);
//        mv.addObject("mom_list", mom_list);
//        System.out.println("page no is :" + page_action);
//        //            dto.getMom_status();            
//        List<MomDto> actionStatus = service.getStatus();
//        mv.addObject("action_statusData", actionStatus);
//        mv.addObject("employee_id", employee_id);
//        mv.addObject("selectedActionStatus", dto.getAction_point_status());
//        mv.addObject("mom_title_Actionsearch", dto.getAction_mom_title());
//        //            mv.addObject("mom_id_search", dto.getId());
//        mv.addObject("paging_actions", pageNoAction);
//
//        return mv;
//    }

    public ModelAndView getMyMomActionPoints(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("myMomActionItems");
        String id = request.getParameter("mom_edit");
        System.out.println("mom id " + id);

        String actionid = request.getParameter("mom_action_point_id");
        System.out.println("id     " + actionid);

        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);

        MomDto dto = new MomDto();
        dto.setMom_id(id);
        dto.setEmployee_id(employee_id);

        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");

        List<MomDto> action = service.getActionPoint(id);
        mv.addObject("actionItems", action);

        List<MomDto> status = service.getStatus();
        mv.addObject("statusData", status);
//        mv.addObject("selectedStatus", dto.getAction_point_status());
        List<MomDto> history = service.getHistories(dto);
        mv.addObject("history", history);
         MomDto list = service.editMom(id);
         mv.addObject("list", list);
//        String minuted_name = service.getEmployeeName(employee_id);
//        mv.addObject("minuted_name",minuted_name);
        return mv;
    }

    public ModelAndView getMomXL(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {

        ModelAndView mv = null;
        System.out.println("afkmsejherkugb");
        List<MomDto> empDetails = new ArrayList();
        final WebApplicationContext ctx = getWebApplicationContext();
        MomService service = (MomService) ctx.getBean("MomService");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);
        dto.setId(employee_id);
        List<MomDto> empList = service.getallMom(dto);
        System.out.println("" + request.getParameter("baselocationId"));
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < empList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + empList.get(i).getMom_title()));
            rowDataList.add(new String("" + empList.get(i).getMom_date()));
            rowDataList.add(new String("" + empList.get(i).getOpen_count()));
            rowDataList.add(new String("" + empList.get(i).getClosed_count()));
            rowDataList.add(new String("" + empList.get(i).getProgress_count()));
//            rowDataList.add(new String("" + empList.get(i).getSaved_count()));
            rowDataList.add(new String("" + empList.get(i).getHold_count()));
            rowDataList.add(new String("" + empList.get(i).getDeferred_count()));

            entireList.add(rowDataList);
        }
        commonMethods.exportToExcel(response, entireList, "List_Of_Mom.xls", "List_Of_Mom", null);
        return mv;
    }

    public ModelAndView getActionXL(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {

        ModelAndView mv = null;
        System.out.println("gertfrfgf");
        List<MomDto> empDetails = new ArrayList();
        final WebApplicationContext ctx = getWebApplicationContext();
        MomService service = (MomService) ctx.getBean("MomService");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);
        dto.setId(employee_id);
        String action_status = request.getParameter("mom_action_status");
        dto.setAction_point_status(action_status);
        List<MomDto> empList = service.getActionListDetails(dto);
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < empList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + empList.get(i).getMom_title()));
            rowDataList.add(new String("" + empList.get(i).getAction_point()));
            rowDataList.add(new String("" + empList.get(i).getPlanned_close_date()));
            rowDataList.add(new String("" + empList.get(i).getActual_close_date()));
            rowDataList.add(new String("" + empList.get(i).getTracking_comments()));
            rowDataList.add(new String("" + empList.get(i).getAp_status_name()));

            entireList.add(rowDataList);
        }
        commonMethods.exportToExcel(response, entireList, "List_Of_Actions.xls", "List_Of_Actions", null);
        return mv;
    }
public ModelAndView getActionMomXL(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {

        ModelAndView mv = null;
        System.out.println("gertfrfgf");
        List<MomDto> empDetails = new ArrayList();
        final WebApplicationContext ctx = getWebApplicationContext();
        MomService service = (MomService) ctx.getBean("MomService");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("id" + employee_id);
        dto.setId(employee_id);
        String action_status = request.getParameter("mom_action_status");
        dto.setAction_point_status(action_status);
        List<MomDto> empList = service.editMomActionPoints(dto);
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < empList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + empList.get(i).getMom_title()));
            rowDataList.add(new String("" + empList.get(i).getAction_point()));
            rowDataList.add(new String("" + empList.get(i).getPlanned_close_date()));
            rowDataList.add(new String("" + empList.get(i).getActual_close_date()));
            rowDataList.add(new String("" + empList.get(i).getTracking_comments()));
            rowDataList.add(new String("" + empList.get(i).getAp_status_name()));

            entireList.add(rowDataList);
        }
        commonMethods.exportToExcel(response, entireList, "Mom_actions.xls", "Mom_actions", null);
        return mv;
    }
    public ModelAndView viewActionMomDetails(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        ModelAndView mv = new ModelAndView("viewActionMomDetails");
        String id = request.getParameter("mom_view");
        System.out.println("Edit id is : " + id);

        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("employee_id" + employee_id);

        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        dto.setId(employee_id);
        dto.setMom_id(id);
        dto.setAbsent_attendance_id(employee_id);
        dto.setAgenda_id(id);
        dto.setAction_point_id(employee_id);
        dto.setAttendance_id(employee_id);
        dto.setDiscussion_id(id);
        dto.setParent_id(employee_id);
        MomDto list = service.editMom(id);

        String minuted_name = service.getEmployeeName(employee_id);
        List<MomDto> presentMember = service.getPresentMembers(id);
        List<MomDto> absentMember = service.getAbsentMembers(id);
        List<MomDto> agendaList = service.getallAgenda(id);
        List<MomDto> discussionList = service.getAllDiscussion(id);
        List<MomDto> actionList = service.getAllActions(id);
        List<MomDto> actionStatus = service.getStatus();

        

        mv.addObject("list", list);
        mv.addObject("minuted_name", minuted_name);
        mv.addObject("membersPresentList", presentMember);
        mv.addObject("membersAbsentList", absentMember);
        mv.addObject("momAgendaList", agendaList);
        mv.addObject("momDiscussionList", discussionList);
        mv.addObject("actionPointList", actionList);
        mv.addObject("action_statusData", actionStatus);
        List<MomDto> history = service.getHistory(dto);
        mv.addObject("history", history);
        List<MomDto> action = service.getActionPoint(id);
        mv.addObject("actionData", action);
        return mv;
    }

    public ModelAndView duplicateMom(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws IOException {
//        ModelAndView mvc = null;
        final WebApplicationContext ctx = getWebApplicationContext();
        MomService service = (MomService) ctx.getBean("MomService");
        dto.setMomTitleRefId(request.getParameter("mom_title"));
        String momCount = service.getMomNames(dto);
        System.out.println("duplicate is:" + request.getParameter("mom_title"));
        System.out.println("Count is:" + momCount);
        response.getWriter().println(momCount);
        return null;
    }
     public ModelAndView editMomActionPoints(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
        int[] paging = null;
        ModelAndView mv = new ModelAndView("editMomActionPoints");
        System.out.println("qqqqqqqqq");
        WebApplicationContext context = getWebApplicationContext();
        MomService service = (MomService) context.getBean("MomService");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        System.out.println("employee_id" + employee_id);
        dto.setId(employee_id);

        String id = request.getParameter("mom_edit_first");
        System.out.println("momid     " + id);
        dto.setMom_id(id);
        
        String viewid = request.getParameter("mom_view");
        System.out.println("Edit id is : " + viewid);

        String act_status = request.getParameter("mom_action_status");
        System.out.println("act_status     " + act_status);

        String actionstatus = request.getParameter("mom_action_point_status");
        System.out.println("actionstatus     " + actionstatus);

        String momtitle = request.getParameter("mom_title");
        System.out.println("momtitle     " + momtitle);
        String action_mom_title = request.getParameter("action_mom_title");
        System.out.println("action_mom_title     " + action_mom_title);

        if (act_status != null) {
            dto.setAction_point_status(act_status);
        } else {
            dto.setAction_point_status("o");
        }
        
//            dto.setMom_status(mom_status); 
        dto.setMom_action_point_status(dto.getMom_action_point_status());
//        String selectedRows = request.getParameter("selectedRows");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        List<MomDto> actiondetails = Arrays.asList(mapper.readValue(selectedRows.toString(), MomDto[].class));
//        if (actiondetails != null && actiondetails.size() > 0) {
//            for (int iTSEntry = 0; iTSEntry < actiondetails.size(); iTSEntry++) {
//                MomDto actionlist = actiondetails.get(iTSEntry);
//                List<MomDto> history = service.getHistory(actionlist);
//                mv.addObject("history", history);
//            }
//        }
        int page_action = 0;
        String pgact = request.getParameter("page_action");
        System.out.println("page " + pgact);
        if (pgact == null || pgact.equals("")) {
            page_action = 1;
        } else {
            page_action = Integer.parseInt(pgact);
        }
        int rows = 15;
        int start_action;
        start_action = ((page_action - 1) * rows);
        //int recordCountAction = service.getMomCount(dto);
        dto.setStart_page(null);
        dto.setEnd_page(null);
//             List<MomDto> record_list = service.getActionListDetails(dto);
        dto.setStart_page(Integer.toString(start_action));
        dto.setEnd_page(Integer.toString(rows));
        int recordCountAction = service.getActionRecordCount(dto);//record_list.size();
        System.out.println("recordCountAction  " + recordCountAction);
        int[] pageNoAction = paging(rows, Integer.toString(recordCountAction), page_action);
       
        List<MomDto> mom_list = service.editMomActionPoints(dto);
        mv.addObject("mom_list", mom_list);
        System.out.println("page no is :" + page_action);
//        MomDto list = service.editMom(id);
//        mv.addObject("list", list);
        List<MomDto> actionStatus = service.getStatus();
        mv.addObject("action_statusData", actionStatus);
        
        mv.addObject("employee_id", employee_id);
        mv.addObject("selectedActionStatus", dto.getAction_point_status());
        mv.addObject("mom_title_Actionsearch", dto.getAction_mom_title());
        //            mv.addObject("mom_id_search", dto.getId());
        mv.addObject("paging_actions", pageNoAction);
        
        List<MomDto> history = service.getHistory(dto);
        mv.addObject("history", history);
        
        return mv;

    }

    public int[] paging(int rows, String recordCount, int current_page) {
        int start = 1;
        int end = 1;
        int next = current_page + 1;
        int prev = current_page - 1;
        int[] pageArr = new int[10];
        int totalPage = Integer.parseInt(recordCount) / rows;
        if (Integer.parseInt(recordCount) % rows != 0) {
            totalPage = totalPage + 1;
        }
        if (totalPage > 9) {
            int minus = current_page - 4;
            int plus = current_page + 4;
            if (minus > 0) {
                start = minus;
            } else {
                start = 1;
            }
            if ((plus - start) < 8) {
                plus = start + 8;
            }
            if (plus > totalPage) {
                end = totalPage;
            } else {
                end = plus;
            }
        } else {
            start = 1;
            end = totalPage;
        }
        if (prev < 1) {
            prev = 1;
        }
        if (next > totalPage) {
            next = totalPage;
        }
        pageArr[0] = totalPage;
        pageArr[1] = current_page;
        pageArr[2] = start;
        pageArr[3] = end;
        pageArr[4] = prev;
        pageArr[5] = next;
        return pageArr;
    }
    public ModelAndView getEmployeeName(HttpServletRequest request, HttpServletResponse response, MomDto dto) {
        logger.info("run on getEmployeeNameForCC ");
        final WebApplicationContext ctx = getWebApplicationContext();
        MomService service = (MomService) ctx.getBean("MomService");
        String refVal = request.getParameter("q");
        logger.info("queryString Typed = " + refVal);
        List<MomDto> empNameList = null;
        empNameList = service.getEmpDetails(refVal);
        logger.info("empNameList "+empNameList);
        request.setAttribute("employeeName", empNameList);
        request.setAttribute("getDataFor", "employeeName");
        return new ModelAndView("ajax");

    }
    public ModelAndView updateMomwithDraft(HttpServletRequest request, HttpServletResponse response, MomDto dto) throws Exception {
       ModelAndView mv = new ModelAndView("redirect:getAllDetails.htm?page=1");
        String id = request.getParameter("mom_edit");
        System.out.println("mom_edit " + id);
        
        WebApplicationContext contaxt = getWebApplicationContext();
        MomService service = (MomService) contaxt.getBean("MomService");
        MomDao dao = (MomDaoImpl) contaxt.getBean("MomDao");
        HttpSession session = request.getSession(true);
        String employee_id = (String) session.getAttribute("EMP_ID");
        
        String actionid=request.getParameter("actionid");
            System.out.println("actionid"+actionid);
        
        dto.setId(id);
        dto.setMinuted_by(employee_id);
        dto.setMom_status("dr");
        
        String test = request.getParameter("test");
        System.out.println("test : " + test);
        String test2 = request.getParameter("test2");
        System.out.println("test2 : " + test2);
        System.out.println("current status is "+dto.getMom_status());
        dto.getAction_point_status();
        dto.setMom_test(test);
        dto.setMom_test2(test2);
//        String mom_id = service.addMomWithSave(dto);
       // String prevStatus = service.getCurrentMomStatus(id);
        //System.out.println("Previous status is "+prevStatus);
        service.updateMomwithSave(dto);
        dto.setMom_id(id);
        
        String typecheck=request.getParameter("typecheck");
        System.out.println("typecheck"+typecheck);
        String typecheck2=request.getParameter("typecheck2");
        System.out.println("typecheck"+typecheck2);
        String members_present=request.getParameter("members_present");
        System.out.println("members_present"+members_present);
        String members_absent=request.getParameter("members_absent");
        System.out.println("members_absent"+members_absent);

        String mail_search="";
        String mail_search2="";
//        for (int m = 0; m < dto.getTest().length; m++) {
//            System.out.println("presntmembers"+dto.getTest().length);
//            String member1 = (String) dto.getTest()[m];
//            System.out.println("presnt"+dto.getTest()[m]);
//            String[] words1 = member1.split("-");
//            String emp_number = words1[0];
//            dto.setEmployee_no(emp_number);
//            
//        if (emp_number != null) {
////             System.out.println("dffffffffgb"+ dto.getMom_present_attendance_id()[m]);
//                
//             if ((dto.getMom_present_attendance_id()[m].equals("0"))) {
//                System.out.println("present"+dto.getMembers_present());
//                System.out.println("present"+mail_search);
              
               if(typecheck.equals("0")){
                   int mailCcLength = request.getParameter("as_values_test").length();
                   System.out.println("mailCcLength"+request.getParameter("as_values_test"));
                     if(mailCcLength!=0){
                        mail_search= request.getParameter("as_values_test");//.substring(0, (mailCcLength - 1));
                        logger.info("mail_search" + mail_search);
                     }
                     if(mail_search !=null && !mail_search.equals("")){
                          String[] member2=mail_search.split(",");
                          for(int i=0;i<member2.length;i++){
                                System.out.println("present "+member2[i]);
                                String[] words2 = member2[i].split("-");
                                String present= words2[1].trim();
                                dto.setEmployee_no(present);
                                System.out.println("present " +present);
                          if (present != null) {
                                dto.setAttendance_status("p");
                                service.insertMomMembers2(dto);
                                }   
                         }
                    }
               }            
//                for (int m = 0; m < dto.getTest().length; m++) {
//                   if((dto.getTest()[m] !=null && !dto.getTest()[m].equals("")) && !dto.getTest()[m].contains("<")){
//                      String member = (String) mail_search;//dto.getTest()[m]; 
                      
//              }
           else if(typecheck.equals("1")){
                   
             if(members_present!= null && !members_present.equals("")){
//                for (int m = 0; m < dto.getMembers_present().length; m++) {
//                   if(dto.getMembers_present() !=null && dto.getMembers_present()[m].contains("<")){
                 String[] member3=members_present.split(";");
                 for(int i=0;i<member3.length;i++){
//                     System.out.println("length"+dto.getMembers_present().length);
                    String[] mem= member3[i].split("<");
//                    String[] words = mem.split("<");
                    String[] emp_number3 = mem[1].split(">");
                    String word=emp_number3[0].trim();
                    dto.setWork_email_address(word);
                    if (word != null) {
                        dto.setAttendance_status("p");
                        service.insertMomMembers(dto);
                        } 
                  }
              } 
            }
//        }
//    else {
//                    dto.setPresent_attendance_id(dto.getMom_present_attendance_id()[m]);
//                    dto.setAttendance_status("p");
//                    dto.setAttendance_deleted(dto.getMom_attendance_deleted()[m]);
//                    service.updateMomMembers(dto);
//                }
//            }
//        }

//        for (int n = 0; n < dto.getTest2().length; n++) {
//            String member = (String) dto.getTest2()[n];
//            String[] words = member.split("-");
//            String emp_number1 = words[0];
//            dto.setEmployee_no(emp_number1);
//            if (emp_number1 != null) {
//                if ((dto.getMom_absent_attendance_id()[n].equals("0"))) {
                     if(typecheck2.equals("0")){
                        int mailCcLength2 = request.getParameter("as_values_test2").length();
                            if(mailCcLength2!=0){
                                mail_search2= request.getParameter("as_values_test2").substring(0, (mailCcLength2 - 1));
                            }
                            logger.info("mail_search2" + mail_search2);
                            if(mail_search2 !=null && !mail_search2.equals("")){
                           String[] member4=mail_search2.split(",");
                                for(int i=0;i<member4.length;i++){
                                    System.out.println("absent "+member4[i]);
                                    String[] words4 = member4[i].split("-");
                                    String absent= words4[1].trim();
                                    dto.setEmployee_no(absent);
                                    System.out.println("absent " +absent);
                            if (absent != null) {
                                dto.setAttendance_status("a");
                                service.insertMomMembers2(dto);
                            }   
                        }
                    }
               }
//               }
               else if(typecheck2.equals("1")){
//                if(dto.getMembers_absent()!= null && !dto.getMembers_absent().equals("")){
//                for (int n = 0; n < dto.getMembers_absent().length; n++) {
//                    if(dto.getMembers_absent() !=null && dto.getMembers_absent()[n].contains("<")){
                    if(members_absent!= null && !members_absent.equals("")){

                       String[] member5 = members_absent.split(";");
                       for(int i=0;i<member5.length;i++){
                           String[] words5= member5[i].split("<");
                           String[] emp_number5 = words5[1].split(">");
                           String word=emp_number5[0].trim();
                           dto.setWork_email_address(word);
                           if (word != null) {
                               dto.setAttendance_status("a");
                               service.insertMomMembers(dto);
                           }
                       }
                   } 
            }
//        }else {
//                    dto.setPresent_attendance_id(dto.getMom_absent_attendance_id()[n]);
//                    dto.setAttendance_status("a");
//                    dto.setAttendance_deleted(dto.getMom_absent_attendance_deleted()[n]);
//                    service.updateMomMembers(dto);
//                }
//            }
//        }
             
    
                   
//  }   
//             if(dto.getMembers_absent().length >1){
               
        for (int i = 0; i < dto.getMom_agenda_points().length; i++) {

            String dsf = request.getParameter("mom_agenda_deleted");
            System.out.println("delete id is: " + dsf);
            if (dto.getMom_agenda_id()[i].equals("0")) {
                dto.setMom_id(id);
                dto.setAgenda_point(dto.getMom_agenda_points()[i]);
                service.insertMomAgenda(dto);
            } else {
                dto.setId(id);
                dto.setAgenda_id(dto.getMom_agenda_id()[i]);
                dto.setAgenda_point(dto.getMom_agenda_points()[i]);
                dto.setAgenda_deleted(dto.getMom_agenda_deleted()[i]);
                service.updateAgenda(dto);
//            }
        }
        }
        for (int j = 0; j < dto.getMom_discussion_points().length; j++) {

            if ( dto.getMom_discussion_id()[j].equals("0")) {
                dto.setMom_id(id);
                dto.setDiscussion_point(dto.getMom_discussion_points()[j]);
                service.insertMomDiscussions(dto);
            } else {
                dto.setId(id);
                dto.setDiscussion_id(dto.getMom_discussion_id()[j]);
                dto.setDiscussion_point(dto.getMom_discussion_points()[j]);
                dto.setDiscussion_deleted(dto.getMom_discussion_deleted()[j]);
                service.updateDiscussion(dto);
            }
        }
         String strDate2=null;
         String[] rowid = actionid.split(",");
                System.out.println("rowid"+rowid); 
        for (int k = 0; k < dto.getMom_action_point().length; k++) {
            
            if(dto.getMom_planned_close_date()[k] !=null && !dto.getMom_planned_close_date()[k].equals("")){ 
                Date date = new Date(dto.getMom_planned_close_date()[k]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);
            
            if(dto.getMom_actual_close_date()[k] !=null && !dto.getMom_actual_close_date()[k].equals("")){
                Date date2 = new Date(dto.getMom_actual_close_date()[k]);
                DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
               strDate2 = dateFormat2.format(date2);
                System.out.println("Converted String: " + strDate2);
             }
//               dto.setAction_point_id(dto.getMom_action_point_id()[k]);
                dto.setPlanned_close_date(strDate);
                dto.setActual_close_date(strDate2);
                String member8 = (String) dto.getMom_action_point_employee_id()[k];
                String[] words8 = member8.split("-");
                String emp_number8 = words8[0];
                dto.setEmployee_no(emp_number8);
            if (emp_number8 != null) {
                  for(int i=0;i<rowid.length;i++){
                    if (dto.getMom_action_point_id()[k].equals("0") && rowid[i].equals("0")){
                       dto.setMom_id(id);
                       dto.setAction_point(dto.getMom_action_point()[k]);
                       dto.setAction_point_employee_id(emp_number8);
                       dto.setPlanned_close_date(strDate);
                       dto.setActual_close_date(strDate2);
                       dto.setTracking_comments(dto.getMom_tracking_comments()[k]);
                       dto.setAction_point_status(dto.getMom_action_point_status()[k]);
                       service.insertActionPoints(dto);
                    }  
                else if(dto.getMom_action_point_id()[k].equals(rowid[i])){
                     System.out.println("rowid1"+ rowid[i]);
                    dto.setAction_point_id(dto.getMom_action_point_id()[k]);
                    dto.setAction_point(dto.getMom_action_point()[k]);
                    dto.setAction_point_employee_id(emp_number8);
                    dto.setPlanned_close_date(strDate);
                    dto.setTracking_comments(dto.getMom_tracking_comments()[k]);
                    dto.setAction_point_status(dto.getMom_action_point_status()[k]);
                    dto.setActual_close_date(strDate2);
                    dto.setAction_point_deleted(dto.getMom_action_point_deleted()[k]);
                    service.updateActionPoint(dto);
                }
            }
        }
      }
        }

        String minuted_name = service.getEmployeeName(employee_id);
        mv.addObject("minuted_name", minuted_name);
        List<MomDto> list = service.getallMom(dto);
        mv.addObject("details", list);
        List<MomDto> presentMember = service.getPresentMembers(id);
        mv.addObject("membersPresentList", presentMember);
        List<MomDto> absentMember = service.getAbsentMembers(id);
        mv.addObject("membersAbsentList", absentMember);
        List<MomDto> agendaList = service.getallAgenda(id);
        mv.addObject("momAgendaList", agendaList);
        List<MomDto> discussionList = service.getAllDiscussion(id);
        mv.addObject("momDiscussionList", discussionList);
        List<MomDto> actionList = service.getAllActions(id);
        mv.addObject("actionPointList", actionList);
        List<MomDto> actionStatus = service.getStatus();
        mv.addObject("action_statusData", actionStatus);

        mv.addObject("index", "Record has been updated successfully");
        return mv;
    }
    public  ArrayList getTimevalues() throws Exception { 
//     ArrayList entireList = new ArrayList();
//        String hrs;
//        String mts;
       ArrayList timeDataList = new ArrayList();
        for(int hrs=0;hrs<24;hrs++){
            System.out.println("hours"+hrs);
            for(int mts=0;mts<60;mts++){
                String hours=null;
                String minutes=null;
//                String[] time;
                if (Integer.toString(hrs).length()<2){
                    hours="0"+Integer.toString(hrs);
                }else{
                    hours=Integer.toString(hrs);
                }
                if (Integer.toString(mts).length()<2){
                    minutes="0"+Integer.toString(mts);
                }else{
                    minutes=Integer.toString(mts);
                }
                timeDataList.add(new String("" +hours+":"+minutes));
               mts=mts+29;
                System.out.println("minutes"+mts);
             }
            }
        return timeDataList ;
    }
        
   
}
