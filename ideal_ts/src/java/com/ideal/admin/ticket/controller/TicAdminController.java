package com.ideal.admin.ticket.controller;

import com.ideal.admin.ticket.dto.TicAdminDataDTO;
import com.ideal.admin.ticket.service.TicAdminServiceImpl;
import com.ideal.admin.ticket.util.CommonConfigurations;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.ideal.admin.ticket.util.CommonMethods;
import com.ideal.admin.ticket.util.MailMessages;
import com.ideal.admin.ticket.util.SendMailTLS;
import com.ideal.system.ticket.dto.TicSystemDataDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;

public class TicAdminController extends MultiActionController {

    final static Logger logger = Logger.getLogger(TicAdminController.class);
    public static int pageCount = 15;

    public synchronized ModelAndView showEmployees(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/adminhome");
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl bo = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
        String sub_unit_id = null;
        String empid = (String) request.getSession().getAttribute("EMP_ID");
         if (empid == null || empid.equals("")) {
            request.getSession().invalidate();
            mvc = new ModelAndView("/unauthorised");
        
         }else{
            TicAdminDataDTO usercheck = bo.checkUserAdmin(empid);
            if(usercheck != null){
                sub_unit_id = usercheck.getSub_unit_id();
                dto.setUnit_id(usercheck.getUnit_id());
            }
            if (sub_unit_id != null && !sub_unit_id.equals("0")) {
                dto.setSub_unit_id(sub_unit_id);
            } else {
                dto.setSub_unit_id(null);
            }
            dto.setEmpNumber(empid);
            dto.setRef_no(request.getParameter("referenceId"));
            dto.setEmpName(request.getParameter("employee_search"));
            int page;
            if (request.getParameter("page") == null) {
                page = 1;
            } else {
                page = dto.getPage();
                dto.setEngineerId(request.getParameter("assignEngineer"));
            }
            int rows = pageCount;
            int start;
            start = ((page - 1) * rows);
            dto.setStart_page(start);
            dto.setEnd_page(rows);
            int recordCount = 0;
            List<TicAdminDataDTO> details = null;
            if(usercheck !=null){
                details = bo.fetchTicRequest(dto);
                details = CommonMethods.getNewTimeFormat(details);
                recordCount = bo.fetchTicRequestListCount(dto);
            }
            int end = (start + rows) - 1;
            int[] paging = CommonMethods.paging(recordCount, page, rows);
            mvc.addObject("paging", paging);
            mvc.addObject("details", details);
            List<TicAdminDataDTO> statusList = bo.getStatusList();
            mvc.addObject("statusList", statusList);
            List<TicAdminDataDTO> assignEngineerList =null;
            assignEngineerList = bo.getAssignEngineerList(usercheck);
            mvc.addObject("assignEngineerList", assignEngineerList);
            mvc.addObject("filterData", dto);
            mvc.addObject("refNoString", dto.getRef_no());
            mvc.addObject("totalRecords", recordCount);
        }
        
        return mvc;
    }

    public synchronized ModelAndView excelexportTicAdmin(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) throws Exception {
        ModelAndView mvc = new ModelAndView("/adminhome");
        String fs = request.getParameter("feedback_search");
        String es = request.getParameter("employee_search");
        String st = request.getParameter("status");
        String ae = request.getParameter("assignEngineer");
        String empid = (String) request.getSession().getAttribute("EMP_ID");
        if (fs.equalsIgnoreCase("") && es.equalsIgnoreCase("Search by Employee Number or First/Last name") && st.equalsIgnoreCase("") && ae.equalsIgnoreCase("")) {
            final WebApplicationContext ct = getWebApplicationContext();
            TicAdminServiceImpl b = (TicAdminServiceImpl) ct.getBean("TicAdminService");
            String sub_unit_id = null;
            TicAdminDataDTO usercheck = b.checkUserAdmin(empid);
            sub_unit_id = usercheck.getSub_unit_id();
            if (sub_unit_id != null && !sub_unit_id.equals("0")) {
                dto.setSub_unit_id(sub_unit_id);
            } else {
                dto.setUnit_id(usercheck.getUnit_id());
            }
            List<TicAdminDataDTO> detail = b.fetchTicRequest(dto);
            detail = CommonMethods.getNewTimeFormat(detail);
            ArrayList entireLis = new ArrayList();
            for (int i = 0; i < detail.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + detail.get(i).getRef_no()));
                rowDataList.add(new String("" + detail.get(i).getEmpNumber()+" - "+detail.get(i).getEmpName()));
                rowDataList.add(new String("" + detail.get(i).getAssignEngineerName()));
                rowDataList.add(new String("" + detail.get(i).getSubject()));
                rowDataList.add(new String("" + detail.get(i).getSupportName()));
                rowDataList.add(new String("" + detail.get(i).getSub_Unit_Name()));
                rowDataList.add(new String("" + detail.get(i).getIssue_type()));
                rowDataList.add(new String("" + detail.get(i).getApplication_area()));
                rowDataList.add(new String("" + detail.get(i).getPriority()));
                rowDataList.add(new String("" + detail.get(i).getStatus()));
                rowDataList.add(new String("" + detail.get(i).getLocation()));
                rowDataList.add(new String("" + detail.get(i).getContact_no()));
                rowDataList.add(new String("" + detail.get(i).getSystem()));
                rowDataList.add(new String("" + detail.get(i).getCreated_date()));
                rowDataList.add(new String("" + detail.get(i).getResponse_time()));
                rowDataList.add(new String("" + detail.get(i).getResolution_time()));
                rowDataList.add(new String("" + detail.get(i).getReplied_date()));
                rowDataList.add(new String("" + detail.get(i).getTime_taken()));
                entireLis.add(rowDataList);
            }

            CommonMethods.exportToExcel(response, entireLis, "TicAdmin_details.xls", "TicAdmin", null);
            return null;

        }
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl bo = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
//        String sadmin = null;
//        List<String> emplist = bo.getempList(empid);
//        if (emplist != null && emplist.size() > 0) {
//            Iterator itr = emplist.iterator();
//            StringBuffer sb = new StringBuffer();
//            sb.append("'").append(empid).append("',");
//            while (itr.hasNext()) {
//                dto.setEmpNumber(null);
//                String emp = (String) itr.next();
//                sb.append("'");
//                sb.append(emp);
//                sb.append("',");
//            }
//            sadmin = sb.substring(0, sb.length() - 1).toString();
//            System.out.println("emp list " + sadmin);
//            dto.setSadmin(sadmin);
//        } else {
//            dto.setEmpNumber(empid);
//        }
        dto.setRef_no(request.getParameter("feedback_search"));
        dto.setEmpName(request.getParameter("employee_search"));
        dto.setEngineerId(request.getParameter("assignEngineer"));
        List<TicAdminDataDTO> details = bo.search_feedback(dto);
        details = CommonMethods.getNewTimeFormat(details);
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < details.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + details.get(i).getRef_no()));
            rowDataList.add(new String("" + details.get(i).getEmpNumber()+" - "+details.get(i).getEmpName()));
            rowDataList.add(new String("" + details.get(i).getAssignEngineerName()));
            rowDataList.add(new String("" + details.get(i).getSubject()));
            rowDataList.add(new String("" + details.get(i).getSupportName()));
            rowDataList.add(new String("" + details.get(i).getSub_Unit_Name()));
            rowDataList.add(new String("" + details.get(i).getIssue_type()));
            rowDataList.add(new String("" + details.get(i).getApplication_area()));
            rowDataList.add(new String("" + details.get(i).getPriority()));
            rowDataList.add(new String("" + details.get(i).getStatus()));
            rowDataList.add(new String("" + details.get(i).getLocation()));
            rowDataList.add(new String("" + details.get(i).getContact_no()));
            rowDataList.add(new String("" + details.get(i).getSystem()));
            rowDataList.add(new String("" + details.get(i).getCreated_date()));
            rowDataList.add(new String("" + details.get(i).getResponse_time()));
            rowDataList.add(new String("" + details.get(i).getResolution_time()));
            rowDataList.add(new String("" + details.get(i).getReplied_date()));
            rowDataList.add(new String("" + details.get(i).getTime_taken()));
            entireList.add(rowDataList);
        }

        CommonMethods.exportToExcel(response, entireList, "TicAdmin_details.xls", "TicAdmin", null);
        return null;
    }

    public synchronized ModelAndView selectEmpByRef(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/adminresponse");
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl bo = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
        String ref = dto.getRef_no();
        String empName = request.getParameter("empName");
        dto.setRef_no(ref);
        dto.setEmpName(empName);
        String mailCCC = "";
        String assignEngineer = "";
        String mailCcNameList = "";
        String assignEmpName = "";
        List<TicAdminDataDTO> mailCcName = null;
        List<TicAdminDataDTO> details = bo.selectEmpByRef(dto);
        Iterator itr = details.iterator();
        String lastStatus = "";
        String lastIssuetype = "";
        String lastSupportType = "";
        String lastSubUnitType = "";
        String lastApptype = "";
        String plannedEffort = "";
        String actualEffort = "";
        List<TicAdminDataDTO> statusList = null;
        while (itr.hasNext()) {
            TicAdminDataDTO dtoj = (TicAdminDataDTO) itr.next();
            int id = dtoj.getId();
            String sid = String.valueOf(id);
            String ref_no = dtoj.getRef_no();
            if (dtoj.getMail_cc_id() == null || dtoj.getMail_cc_id().equals("")) {
                mailCCC = "";
            } else {
                mailCCC = dtoj.getMail_cc_id();
            }
            if (dtoj.getAssignEmpId() == null || dtoj.getAssignEmpId().equals("")) {
                assignEngineer = "";
            } else {
                assignEngineer = dtoj.getAssignEmpId();
            }
            request.setAttribute("id", id);
            request.setAttribute("ref_no", ref_no);
            lastStatus = dtoj.getStatus();
            lastIssuetype = dtoj.getStatusValue();
            plannedEffort = dtoj.getplanned_effort();
            actualEffort = dtoj.getactual_effort();
            lastApptype = dtoj.getAppValue();
            lastSupportType = dtoj.getSupport_type();
            lastSubUnitType = dtoj.getSub_unit();

        }
        if (mailCCC == null || mailCCC.equals("")) {
            mailCCC = "";
        } else {
            mailCcName = bo.getVerifedCcMailNames(mailCCC);
            mailCcNameList = bo.getVerifedCcMailNamesList(mailCCC);
        }
        if (assignEngineer == null || assignEngineer.equals("")) {
            assignEngineer = "";
        } else {
            assignEmpName = bo.getAssignEmpName(assignEngineer);
        }
        request.setAttribute("mailCCC", mailCCC);
        request.setAttribute("mailCcList", mailCcName);
        request.setAttribute("mailCcNameList", mailCcNameList);
        request.setAttribute("plannedEffort", plannedEffort);
        request.setAttribute("actualEffort", actualEffort);
        TicAdminDataDTO modeDto = bo.getFreqC(dto);
        String ee = modeDto.getStatus();
        request.setAttribute("hide", modeDto.getStatus());
        dto.setStatus_id(details.get(0).getStatus_id());
        dto.setIssue_type(details.get(0).getIssue_id());
        if(!details.get(0).getStatus_id().equals("c")){
            statusList = bo.getNextStatusList(dto);
        }
        dto.setIssue_type(lastIssuetype);
        dto.setApplication_area(lastApptype);
        dto.setSupport_type(lastSupportType);
        dto.setSub_unit(lastSubUnitType);

        mvc.addObject("statusList", statusList);
        List<TicAdminDataDTO> issueTypeList = bo.getIssueTypeList(lastSupportType);
        mvc.addObject("issueTypeList", issueTypeList);
        List<TicAdminDataDTO> supportTypeList = bo.getSupportTypeList();
        mvc.addObject("supportTypeList", supportTypeList);

        List<TicAdminDataDTO> subUnitList = bo.getSubUnitList(lastSupportType);
        mvc.addObject("subUnitList", subUnitList);

        List<TicAdminDataDTO> appTypeList = bo.getApplicationList(lastSupportType, lastSubUnitType);
        mvc.addObject("appTypeList", appTypeList);
        List<TicAdminDataDTO> assignEngineerList = bo.getSupportEngineerList(details.get(0).getSupport_type(),details.get(0).getSub_unit());
        mvc.addObject("assignEngineerList", assignEngineerList);
        String givenRating = details.get(0).getRating();
        if (givenRating == null) {
            givenRating = "0";
        }
        mvc.addObject("item", details);
        mvc.addObject("lastStatus", details.get(0).getStatus_id());
        mvc.addObject("lastIssueType", lastIssuetype);
        mvc.addObject("lastSupportType", lastSupportType);
        mvc.addObject("lastSubUnitType", lastSubUnitType);
        mvc.addObject("lastApptype", lastApptype);
        mvc.addObject("empName", empName);
        mvc.addObject("assignEmpName", assignEmpName);
        mvc.addObject("assignedEmployee", details.get(0).getAssignEmpId());
        mvc.addObject("givenRating", givenRating);
        return mvc;
    }

    public synchronized ModelAndView search_feedback_admin(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/adminhome");
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl bo = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
        String sub_unit_id = null;
        String empid = (String) request.getSession().getAttribute("EMP_ID");
         if (empid == null || empid.equals("")) {
            request.getSession().invalidate();
            mvc = new ModelAndView("/unauthorised");
        }else{
             TicAdminDataDTO usercheck = bo.checkUserAdmin(empid);
        sub_unit_id = usercheck.getSub_unit_id();
        if (sub_unit_id != null && !sub_unit_id.equals("0")) {
            dto.setSub_unit_id(sub_unit_id);
        } else {
            dto.setUnit_id(usercheck.getUnit_id());
        }
        dto.setEmpNumber(empid);
        dto.setRef_no(request.getParameter("referenceNo"));
        dto.setStatus(request.getParameter("status"));
        dto.setEngineerId(request.getParameter("assignEngineer"));
        dto.setEmpName(request.getParameter("employee_search"));
        int page;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            page = dto.getPage();
        }
        int rows = pageCount;
        int start;
        start = ((page - 1) * rows);
        dto.setStart_page(start);
        dto.setEnd_page(rows);
        int recordCount = 0;
        List<TicAdminDataDTO> detail = bo.search_feedback(dto);
        recordCount = bo.fetchTicRequestListCount(dto);
        int end = (start + rows) - 1;
        int[] paging = CommonMethods.paging(recordCount, page, rows);
        mvc.addObject("details", detail);
        if ((dto.getRef_no() == null && dto.getEmpName() == null
                && "Search by Employee Number or First/Last name".equals(dto.getEmpName()) && dto.getRef_no() == "" && dto.getEmpName() == "") || (dto.getStatus() != null || dto.getStatus() != "") || (dto.getIssue_type() != null || dto.getIssue_type() != "")) {
            mvc.addObject("paging", paging);
        }
        List<TicAdminDataDTO> statusList = bo.getStatusList();
        mvc.addObject("statusList", statusList);
        List<TicAdminDataDTO> assignEngineerList = bo.getAssignEngineerList(usercheck);
        mvc.addObject("assignEngineerList", assignEngineerList);
        mvc.addObject("filterData", dto);
        mvc.addObject("refNoString", dto.getRef_no());
        mvc.addObject("totalRecords", recordCount);
         }
        
        return mvc;
    }

    public synchronized ModelAndView feedback_search(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/feedback_search");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            TicAdminServiceImpl service = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
            String refVal = request.getParameter("q");
            List<TicAdminDataDTO> refNoList = service.getRefeenceDetails(refVal);
            mvc.addObject("refNoList", refNoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public synchronized ModelAndView employee_search(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/employee_search");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            TicAdminServiceImpl service = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
            String refVal = request.getParameter("q");
            List<TicAdminDataDTO> empNameList = service.getEmpDetails(refVal);
            mvc.addObject("empNameList", empNameList);
            mvc.addObject("filterData", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }
    
    public synchronized ModelAndView getNextStatus(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto)throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl bo = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
        for(TicAdminDataDTO status : bo.getNextStatusList(dto)){
            response.getWriter().println("<option value='" + status.getStatusKey() + "'>" + status.getStatusValue() + "</option>");
        }
        return null;
    }
    
    public synchronized ModelAndView getSupportEngineerList(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto)throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl bo = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
        response.getWriter().println("<option value=''>-- Select Assign Engineer--</option>");
        for(TicAdminDataDTO assign_engineer : bo.getSupportEngineerList(dto.getSupport_type(),dto.getSub_unit())){
            response.getWriter().println("<option value='" + assign_engineer.getAssignEngineerId() + "'>" + assign_engineer.getAssignEngineerName() + "</option>");
        }
        return null;
    }

    public synchronized ModelAndView assign_engineer(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/assign_engineer");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            TicAdminServiceImpl service = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
            String empid = (String) request.getSession().getAttribute("EMP_ID");
            String struct = service.getstructure(empid);
            String refVal = request.getParameter("q");
            dto.setStructid(struct);
            dto.setCriteria(refVal);
            List<TicAdminDataDTO> assignEngineerList = service.getAssignEngineerDetails(dto);
            mvc.addObject("empNameList", assignEngineerList);
            mvc.addObject("filterData", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public synchronized ModelAndView insertResponse(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) throws UnknownHostException, FileNotFoundException, IOException {
        HttpSession session = request.getSession();
        String waitingMsg = "Note: If you satisfied/ Issue is resolved Please close the Ticket";
        ModelAndView mvc = new ModelAndView("/adminhome");
        String emp = (String) session.getAttribute("EMP_ID");
        if (emp == null || emp.equals("")) {
            mvc = new ModelAndView("/unauthorised");
        }else{
            
        TicAdminDataDTO res = new TicAdminDataDTO();
        String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
        String r_date = CommonMethods.getSystemDate(DATE_FORMAT_NOW);
        res.setStatus(request.getParameter("status"));
        String stat = (String) request.getParameter("status");
        String resTime = (String) request.getParameter("response_time");
        String created_date_mail = request.getParameter("created_date_mail");
        String subSupportUnit = request.getParameter("sub_unit");
        String supportUnit = request.getParameter("support_type");
        Date date2 = null;
        Date date1 = null;
        try {
            date2 = dateFormat.parse(created_date_mail);
            date1 = dateFormat.parse(r_date);
        } catch (ParseException ex) {
            logger.info("exception in insertion " + ex);
        }
        long diff = date1.getTime() - date2.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if ((stat.equals("a")) && (resTime == null || resTime.trim().equals(""))) {
            resTime = "" + diffDays + "day(s) " + diffHours + "hour(s) " + diffMinutes + "mimute(s) " + diffSeconds + "second(s)";
        }
        dto.setResponse_time(resTime);
        if (stat.equals("w")) {
            String resolution_Time = "" + diffDays + "day(s) " + diffHours + "hour(s) " + diffMinutes + "mimute(s) " + diffSeconds + "second(s)";
            dto.setResolution_time(resolution_Time);

        }
        res.setResponse(request.getParameter("response"));
        res.setEmpNumber(request.getParameter("empNumber"));
        dto.setRef_no(request.getParameter("refNo"));
        res.setSub_unit(subSupportUnit);
        dto.setEmpName(request.getParameter("empName"));
        dto.setCreated_date(r_date);
        res.setId(Integer.valueOf(request.getParameter("id")));
        dto.setReplied_date(r_date);        
        if (emp != null) {
            int empidd = Integer.valueOf(emp);
            dto.setEmpidd(empidd);
        }

        String ref_n = request.getParameter("ref_n");
        String[] conv_resp = request.getParameterValues("conv_resp");
        String[] conv_sts = request.getParameterValues("conv_sts");
        String[] user_created = request.getParameterValues("user_created");
        String[] replied_date_mail = request.getParameterValues("replied_date_mail");
        String[] adminName = request.getParameterValues("responder_name");
        String[] admin_created = request.getParameterValues("admin_created");
        String mail_search = "";
        String filePath = CommonConfigurations.fileUploadPath;
        MultipartFile fileData = null;
        fileData = dto.getFile();
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
        dto.setAttachment_file_path(fileName);
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl bo = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
        String assignEnginer = request.getParameter("assign_engineer");
        if (assignEnginer != null && assignEnginer.length() > 0) {
//            String assignEngId[] = assignEnginer.split("-");
//            dto.setAssignEngineerNumber(assignEngId[1]);
//            int asignengineerPk = bo.getAssignEngineerId(assignEngId[1].trim());
            dto.setAssignEngineerId(Integer.parseInt(assignEnginer));
            bo.updateAssignEngineerId(dto);
        } else {
            logger.info("engineer row id " + dto.getId());
        }
        String adminResponseName = bo.getEmpName(dto);
        int mailCcLength = request.getParameter("as_values_mailCc").length();
        if (mailCcLength != 0) {
            mail_search = request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1));
        }
        dto.setMail_cc_id(mail_search);
        String mailCC_add = "";
        if (mailCcLength != 0) {
            mailCC_add = bo.getCcMail(request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
        }
        String s = request.getParameter("status");
        if (s.equalsIgnoreCase("c")) {
            String DATE_FORMAT_NO = "yyyy-MM-dd HH:mm:ss";
            String c_date = CommonMethods.getSystemDate(DATE_FORMAT_NO);
            dto.setClosed_date(c_date);
            bo.updateClosedDate(dto);
        }
        String mailId = request.getParameter("mailId");
        MailMessages mailMessageObj = new MailMessages();
        String mailSubject = mailMessageObj.getResponseSubject("TicketRaised", ref_n);
        String mailTo = mailId;
        String mailCC = "";
        String latestResponseStatus = bo.getLatestResponseStatus(res.getStatus());
        String mailMessage = mailMessageObj.getResponseMessage(request, "TicketRaised", conv_resp, conv_sts, user_created, admin_created, res.getResponse() + "#-#" + ref_n + "#-#" + latestResponseStatus, r_date, created_date_mail, replied_date_mail, adminName, adminResponseName);
        SendMailTLS mailObject = new SendMailTLS();
         ArrayList<TicSystemDataDTO> connectionRes;
        connectionRes =  bo.getMailDetails();
        String con_username = connectionRes.get(0).getConfiguration_value();
        String con_password = connectionRes.get(1).getConfiguration_value();
        String con_host = connectionRes.get(2).getConfiguration_value();
        String con_port = connectionRes.get(3).getConfiguration_value();
        TicAdminDataDTO lstdto = bo.insertResponse(dto);        
            if (lstdto != null && lstdto.getLastInsertId() != null && lstdto.getLastInsertId() != "") {
                bo.updateStatusInRequests(dto);
                if (!supportUnit.equals("3")) {
                    mailCC = bo.getTomailAddress(subSupportUnit) + "," + mailCC_add;
                } else {
                    mailCC = mailCC_add;
                }
                try {
                    mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC, con_username, con_password, con_host, con_port);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        int page;
        if (request.getParameter("page") == null) {
            page = 1;
        } else {
            page = dto.getPage();
        }
        int rows = pageCount;
        int start;
        start = ((page - 1) * rows);
        dto.setStart_page(start);
        dto.setEnd_page(rows);
        int recordCount = 0;
        dto.setStatus("");
        TicAdminDataDTO dto1 = new TicAdminDataDTO();
        dto1.setRef_no(ref_n.trim());
        List<TicAdminDataDTO> details = bo.fetchTicRequest(dto1);

        TicAdminDataDTO usercheck = bo.checkUserAdmin(emp);
        dto.setUnit_id(usercheck.getUnit_id());
        if (usercheck.getSub_unit_id() != null && !usercheck.getSub_unit_id().equals("0")) {
            dto.setSub_unit_id(usercheck.getSub_unit_id());
        } else {
            dto.setSub_unit_id("0");
        }

        recordCount = bo.fetchTicRequestListCount(dto1);
        int end = (start + rows) - 1;
        int[] paging = CommonMethods.paging(recordCount, page, rows);
        mvc.addObject("paging", paging);
        mvc.addObject("details", details);
        List<TicAdminDataDTO> statusList = bo.getStatusList();
        mvc.addObject("statusList", statusList);
        List<TicAdminDataDTO> issueTypeList = bo.getIssueTypeList(supportUnit);
        mvc.addObject("issueTypeList", issueTypeList);
        List<TicAdminDataDTO> appTypeList = bo.getApplicationList(usercheck.getUnit_id(), usercheck.getSub_unit_id());
        mvc.addObject("appTypeList", appTypeList);
        List<TicAdminDataDTO> assignEngineerList = bo.getAssignEngineerList(dto);
        mvc.addObject("assignEngineerList", assignEngineerList);
        mvc.addObject("totalRecords", recordCount);
        }
        
        return mvc;
    }

    public void downloadFile(HttpServletRequest requestObj, HttpServletResponse response, TicAdminDataDTO formData) throws IOException {
        String fileName = requestObj.getParameter("filePath");
        String originalName = requestObj.getParameter("originalName");
        String fileType = requestObj.getParameter("fileType");
        String filePath = CommonConfigurations.fileUploadPath;
        final WebApplicationContext ctx = getWebApplicationContext();
        ((TicAdminServiceImpl) ctx.getBean("TicAdminService")).fileDownload(fileName, originalName, filePath, fileType, response);
    }

    public synchronized ModelAndView getEmployeeNameForCC(HttpServletRequest request, HttpServletResponse response, TicAdminDataDTO dto) {
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl service = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
        String refVal = request.getParameter("q");
        List<TicAdminDataDTO> empNameList = null;
        empNameList = service.getEmpDetails(refVal);
        request.setAttribute("employeeName", empNameList);
        request.setAttribute("getDataFor", "employeeName");
        return new ModelAndView("ajax");

    }

    public synchronized ModelAndView getSelectedStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter pw = response.getWriter();
        final WebApplicationContext ctx = getWebApplicationContext();
        TicAdminServiceImpl service = (TicAdminServiceImpl) ctx.getBean("TicAdminService");
        TicAdminDataDTO dto = new TicAdminDataDTO();
        String issueId = request.getParameter("issueId");
        dto.setIssue_type(issueId);
        List<TicAdminDataDTO> selStatusList = service.getSelectedStatus(dto);
        String result = getJson(selStatusList);
        pw.println(result);
        return null;
    }

    private synchronized String getJson(List<TicAdminDataDTO> list) {
        StringBuffer sb = new StringBuffer("");
        sb.append("[");
        for (TicAdminDataDTO dto : list) {
            sb.append("{");
            sb.append("\"id\":");
            sb.append("\"" + dto.getId() + "\",");
            sb.append("\"statusKey\":");
            sb.append("\"" + dto.getStatusKey() + "\",");
            sb.append("\"statusValue\":");
            sb.append("\"" + dto.getStatusValue() + "\"");
            sb.append("},");
        }
        sb.append("]");
        String result = sb.toString();
        result = result.replace("},]", "}]");
        return result;
    }
}
