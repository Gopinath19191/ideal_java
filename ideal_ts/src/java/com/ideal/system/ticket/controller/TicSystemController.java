package com.ideal.system.ticket.controller;

import com.ideal.system.ticket.dto.TicSystemDataDTO;
import com.ideal.system.ticket.service.TicSystemServiceImpl;
import javax.servlet.http.HttpSession;
import com.ideal.system.ticket.util.CommonMethods;
import com.ideal.system.ticket.util.CommonConfigurations;
import com.ideal.system.ticket.util.MailMessages;
import com.ideal.system.ticket.util.SendMail;
import com.ideal.system.ticket.util.SendMailTLS;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class TicSystemController extends MultiActionController {

    final static Logger logger = Logger.getLogger(TicSystemController.class);
    static Random random = new Random();
    public static int pageCount = 15;

    public synchronized ModelAndView feedback_user(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO dto) {
        ModelAndView mvc = new ModelAndView("feedbacks_list_user");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
        String emp = (String) session.getAttribute("EMP_ID");
        if (emp == null || emp.equals("")) {
            session.invalidate();
            mvc = new ModelAndView("unauthorised");
        } else {

            int empidd = Integer.parseInt(emp);
            dto.setId(empidd);
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
            recordCount = bo.fetchTicRequestListCount(dto);
            int[] paging = com.ideal.admin.ticket.util.CommonMethods.paging(recordCount, page, rows);
            List<TicSystemDataDTO> details = bo.fetchTicRequest(dto);
            List<TicSystemDataDTO> statusList = bo.getStatusList(dto);
            mvc.addObject("statusList", statusList);
            mvc.addObject("details", details);
            mvc.addObject("paging", paging);
            mvc.addObject("totalRecords", recordCount);
        }

        return mvc;
    }

    public synchronized ModelAndView addFeedback(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO dto) {
        ModelAndView mvc = new ModelAndView("add_feedback");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
        String emp = (String) session.getAttribute("EMP_ID");
        if (emp == null || emp.equals("")) {
            session.invalidate();
            return new ModelAndView("/unauthorised");
        } else {
            String emlpoyeeno = "";
            List<TicSystemDataDTO> emlpoyeenoList = bo.getAdminList();
            emlpoyeeno = emlpoyeenoList.get(0).getConfiguration_value();
            String list[] = emlpoyeeno.split(",");
            for (int i = 0; i < list.length; i++) {
                if (list[i].equals(emp)) {
                    String admin = "admin";
                    request.setAttribute("adminId", admin);
                }
            }

            int empidd = Integer.parseInt(emp);
            dto.setId(empidd);
            List<TicSystemDataDTO> systemList = bo.getSystemList();
            mvc.addObject("systemList", systemList);
            List<TicSystemDataDTO> supportTypeList = bo.getSupportTypeList();
            mvc.addObject("supportTypeList", supportTypeList);
            List<TicSystemDataDTO> requestType = bo.getRequestTypes("");
            mvc.addObject("requestType", requestType);
        }

        return mvc;
    }

    public synchronized ModelAndView search_feedback_user(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/feedbacks_list_user");
        HttpSession session = request.getSession();
        String emp = (String) session.getAttribute("EMP_ID");
        if (emp == null || emp.equals("")) {
            session.invalidate();
            mvc = new ModelAndView("/unauthorised");
        } else {

            try {
                dto.setRef_no(request.getParameter("referenceNo"));
                dto.setEmpName(request.getParameter("employee_search"));
                final WebApplicationContext ctx = getWebApplicationContext();
                TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");

                int empidd = Integer.parseInt(emp);
                dto.setId(empidd);
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
                recordCount = bo.fetchTicRequestListCount(dto);
                int[] paging = com.ideal.admin.ticket.util.CommonMethods.paging(recordCount, page, rows);
                List<TicSystemDataDTO> details = bo.search_feedback_user(dto);
                mvc.addObject("details", details);
                List<TicSystemDataDTO> statusList = bo.getStatusList(dto);
                List<TicSystemDataDTO> issueTypeList = bo.getIssueTypeList();
                mvc.addObject("statusList", statusList);
                mvc.addObject("issueTypeList", issueTypeList);
                mvc.addObject("filterData", dto);
                mvc.addObject("paging", paging);
                mvc.addObject("totalRecords", recordCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return mvc;
    }

    public synchronized ModelAndView feedback_search_user(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/feedback_search_user");
        HttpSession session = request.getSession();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            TicSystemServiceImpl service = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
            String refVal = request.getParameter("q");
            String emp = (String) session.getAttribute("EMP_ID");
            int empidd = Integer.parseInt(emp);
            dto.setId(empidd);
            List<TicSystemDataDTO> refNoList = service.getRefeenceDetails(refVal);
            mvc.addObject("refNoList", refNoList);
            List<TicSystemDataDTO> statusList = service.getStatusList(dto);
            mvc.addObject("statusList", statusList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public synchronized ModelAndView employee_search_user(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO dto) {

        ModelAndView mvc = new ModelAndView("/employee_search_user");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            TicSystemServiceImpl service = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
            String refVal = request.getParameter("q");
            List<TicSystemDataDTO> empNameList = service.getEmpDetails(refVal);
            mvc.addObject("empNameList", empNameList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public synchronized ModelAndView getEmployeeNameForCC(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO dto) {
        final WebApplicationContext ctx = getWebApplicationContext();
        TicSystemServiceImpl service = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
        String refVal = request.getParameter("q");
        List<TicSystemDataDTO> empNameList = null;
        empNameList = service.getEmpDetails(refVal);
        request.setAttribute("employeeName", empNameList);
        request.setAttribute("getDataFor", "employeeName");
        return new ModelAndView("ajax");

    }

    public synchronized ModelAndView saveFeedbackRequest(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO res) throws UnknownHostException, IOException, ServletException {
        ModelAndView mvc = null;
        HttpSession session = request.getSession();
        String emp = (String) session.getAttribute("EMP_ID");
        if (emp == null || emp.equals("")) {
            mvc = new ModelAndView("/unauthorised");
        } else {
            final WebApplicationContext ctx = getWebApplicationContext();
            TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
            int temp = bo.getHighestRefNo(res);
            int j = temp + 1;
            String s = String.valueOf(j);
            while (s.length() < 6) {
                s = "0" + s;
            }
            String highestdetail = "ITS" + s;
            String supportUnit = request.getParameter("support_type");
            String subSupportUnit = request.getParameter("sub_unit");
            res.setRef_no(highestdetail);
            res.setStatus("o");
            res.setResponse(request.getParameter("description"));
            res.setCreated_date(request.getParameter("created_date"));
            String mail_search = "";
            int mailCcLength = request.getParameter("as_values_mailCc").length();
            if (mailCcLength != 0) {
                mail_search = request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1));
            }
            String mailCC_add = "";
            if (mailCcLength != 0) {
                mailCC_add = bo.getCcMail(request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
            }
            res.setMail_cc_id(mail_search);
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
            int empidd = Integer.parseInt(emp);
            res.setId(empidd);
            String refVal = request.getParameter("employee_search");
            if (refVal != null && !"Search by Employee Number or First/Last name".equals(refVal) && refVal != "") {
                List<TicSystemDataDTO> empNameList = bo.getEmpDetails(refVal);
                Iterator itr = empNameList.iterator();
                while (itr.hasNext()) {
                    TicSystemDataDTO tdtoo = (TicSystemDataDTO) itr.next();
                    tdtoo.getEmployee_idd();
                    int employee_id = tdtoo.getEmployee_idd();
                    res.setId(employee_id);
                    String mailCC = tdtoo.getMailId() + "," + mailCC_add;
                    MailMessages mailMessageObj = new MailMessages();
                    String mailSubject = mailMessageObj.getTicketSubject("TicketRaised", res.getRef_no());
                    String mailTo = "";
                    String mailMessage = mailMessageObj.getTicketMessage(request, "TicketRaised", tdtoo.getRequestorName() + "#-#" + res.getRef_no() + "#-#" + res.getResponse());
                    SendMailTLS mailObject = new SendMailTLS();
                    if (!supportUnit.equals("3")) {
                        mailTo = bo.getTomailAddress(subSupportUnit);
                    } else {
                        mailTo = bo.getCcMail(String.valueOf(empidd));
                    }
                    ArrayList<TicSystemDataDTO> connectionRes;
                    connectionRes = bo.getMailDetails();
                    String con_username = connectionRes.get(0).getConfiguration_value();
                    String con_password = connectionRes.get(1).getConfiguration_value();
                    String con_host = connectionRes.get(2).getConfiguration_value();
                    String con_port = connectionRes.get(3).getConfiguration_value();
                     String maxId = null;
                    TicSystemDataDTO lstdto = bo.insertRequest(res);
                    if(lstdto!=null && lstdto.getLastInsertId() !=null && lstdto.getLastInsertId() != ""){
                        maxId = lstdto.getLastInsertId();
                        res.setParent_id(maxId);
                        bo.insertResponse(res);
                        try {
                            mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC, con_username, con_password, con_host, con_port);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //String maxId = bo.getHighestReqId(res);                   
                   
                }
            } else {
                String refVa = String.valueOf(empidd);
                String mailCC = bo.getEmpMail(refVa) + "," + mailCC_add;
                SendMail mailObj = null;
                try {
                    mailObj = new SendMail();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ArrayList<TicSystemDataDTO> connectionRes;
                connectionRes = bo.getMailDetails();
                String con_username = connectionRes.get(0).getConfiguration_value();
                String con_password = connectionRes.get(1).getConfiguration_value();
                String con_host = connectionRes.get(2).getConfiguration_value();
                String con_port = connectionRes.get(3).getConfiguration_value();
                String requestorName = "";
                MailMessages mailMessageObj = new MailMessages();
                requestorName = bo.getEmpName(res);
                String mailSubject = mailMessageObj.getTicketSubject("TicketRaised", res.getRef_no());
                String mailTo = "";
                String mailMessage = mailMessageObj.getTicketMessage(request, "TicketRaised", requestorName + "#-#" + res.getRef_no() + "#-#" + res.getResponse());
                SendMailTLS mailObject = new SendMailTLS();
                if (!supportUnit.equals("3")) {
                    mailTo = bo.getTomailAddress(subSupportUnit);
                } else {
                    mailTo = bo.getCcMail(String.valueOf(empidd));
                }
                String maxId = null;
                 TicSystemDataDTO lstdto = bo.insertRequest(res);
                //String maxId = bo.getHighestReqId(res);
                //res.setParent_id(maxId);
               // bo.insertResponse(res);
                
                 if(lstdto!=null && lstdto.getLastInsertId() !=null && lstdto.getLastInsertId() != ""){
                    maxId = lstdto.getLastInsertId();
                    res.setParent_id(maxId);
                    bo.insertResponse(res);
                    try {
                        mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC, con_username, con_password, con_host, con_port);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                        
                }                
            }
            res.setId(empidd);
            res.setStatus("");
            res.setRef_no("");
            mvc = new ModelAndView("redirect:feedback_user.htm");
        }

        return mvc;
    }

    public synchronized ModelAndView editOnSaveFeedbackRequest(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO res) throws UnknownHostException, FileNotFoundException, IOException {

        ModelAndView mvc = new ModelAndView("redirect:feedback_user.htm");
        HttpSession session = request.getSession();
        String emp = (String) session.getAttribute("EMP_ID");
        if (emp == null || emp.equals("")) {
            mvc = new ModelAndView("/unauthorised");
        } else {
            res.setPriority(request.getParameter("priority"));
            String preStatus = request.getParameter("preStatus");
            String supportUnit = request.getParameter("support_type");
            String subSupportUnit = request.getParameter("sub_Unit");
            res.setIssue_type(request.getParameter("issue_type"));
            res.setApplication_area(request.getParameter("application_area"));
            res.setSubject(request.getParameter("subject"));
            res.setLocation(request.getParameter("location"));
            res.setContact_no(request.getParameter("contact_no"));
            res.setResponse(request.getParameter("response"));
            res.setCreated_date(request.getParameter("replied_date"));
            res.setReplied_date(request.getParameter("replied_date"));
            res.setReplied_date(request.getParameter("replied_date"));
            res.setEmpName(request.getParameter("empNamee"));

            String s = request.getParameter("status");
            if (s != null && s.equalsIgnoreCase("c")) {
                res.setClosed_date(request.getParameter("closed_date"));
                res.setRating(request.getParameter("rating"));
            }
            String filePath = CommonConfigurations.fileUploadPath;
            MultipartFile fileData = null;
            String fileName = "";
            if (res.getFile() != null) {
                fileData = res.getFile();
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

            } else if (res.getFullFileName() != null) {
                fileName = res.getFullFileName();
            }
            res.setAttachment_file_path(fileName);
            int empidd = Integer.parseInt(emp);
            res.setId(empidd);
            final WebApplicationContext ctx = getWebApplicationContext();
            TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
            String mail_search = "";
            String mailCC_add = "";
            int mailCcLength = request.getParameter("as_values_mailCc").length();
            if (mailCcLength != 0) {
                mail_search = request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1));
                mailCC_add = bo.getCcMail(request.getParameter("as_values_mailCc").substring(0, (mailCcLength - 1)));
            }
            res.setMail_cc_id(mail_search);
            String refVal = request.getParameter("employee_search");
            if (refVal != null) {
                List<TicSystemDataDTO> empNameList = bo.getEmpDetails(refVal);
                Iterator itr = empNameList.iterator();
                while (itr.hasNext()) {
                    TicSystemDataDTO tdtoo = (TicSystemDataDTO) itr.next();
                    tdtoo.getEmployee_idd();
                    int employee_id = tdtoo.getEmployee_idd();

                }
            }
            res.setUniqueReqId(Integer.parseInt(request.getParameter("parent_idToId")));

            String refVall = request.getParameter("employee_search");
            if (refVall != null && !"Search by Employee Number or First/Last name".equals(refVall) && refVall != "") {
                List<TicSystemDataDTO> empNameListt = bo.getEmpDetails(refVal);
                Iterator itrr = empNameListt.iterator();
                while (itrr.hasNext()) {
                    TicSystemDataDTO tdtooe = (TicSystemDataDTO) itrr.next();
                    tdtooe.getEmployee_idd();
                    int employee_id = tdtooe.getEmployee_idd();
                    res.setId(employee_id);
                    String mailCC = tdtooe.getMailId();
                    String mailSubject = null;
                    MailMessages mailMessageObj = new MailMessages();
                    if (res.getStatus().equals("c")) {
                        mailSubject = mailMessageObj.getTicketSubject("TicketClosed", res.getRef_no());
                    } else {
                        mailSubject = mailMessageObj.getTicketSubject("TicketReSubmit", res.getRef_no());
                    }

                    String mailTo = "";
                    String mailMessage = "";
                    if (res.getStatus().equals("c")) {
                        mailMessage = mailMessageObj.getTicketMessage(request, "TicketClosed", tdtooe.getRequestorName() + "#-#" + res.getRef_no() + "#-#" + res.getResponse());
                    } else {
                        mailMessage = mailMessageObj.getTicketMessage(request, "TicketReSubmit", tdtooe.getRequestorName() + "#-#" + res.getRef_no() + "#-#" + res.getResponse());
                    }

                    SendMailTLS mailObject = new SendMailTLS();

                    if (subSupportUnit == null || subSupportUnit.equals("")) {
                        mailTo = CommonConfigurations.ticketAdminMail;
                    } else {
                        if (!supportUnit.equals("3")) {
                            mailTo = bo.getTomailAddress(subSupportUnit);
                        } else {
                            mailTo = bo.getCcMail(String.valueOf(empidd));
                        }
                    }
                    if (s == null || s.equals("")) {
                        res.setStatus(preStatus);
                        bo.updateRequest(res);
                    } else {
                        res.setStatus(request.getParameter("status"));
                        bo.updateRequest(res);
                    }
                    TicSystemDataDTO lstdto = bo.insertResponseEdit(res);
                    
                     if(lstdto!=null && lstdto.getLastInsertId() !=null && lstdto.getLastInsertId() != ""){
                        ArrayList<TicSystemDataDTO> connectionRes;
                        connectionRes = bo.getMailDetails();
                        String con_username = connectionRes.get(0).getConfiguration_value();
                        String con_password = connectionRes.get(1).getConfiguration_value();
                        String con_host = connectionRes.get(2).getConfiguration_value();
                        String con_port = connectionRes.get(3).getConfiguration_value();
                        try {
                            mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC, con_username, con_password, con_host, con_port);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                     }   
                }
            } else {
                String refVa = String.valueOf(empidd);
                String mailCC = bo.getEmpMail(refVa);
                SendMail mailObj = null;
                try {
                    mailObj = new SendMail();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String requestorName = "";
                MailMessages mailMessageObj = new MailMessages();
                requestorName = res.getEmpName();
                String mailSubject = null;
                if (res.getStatus().equals("c")) {
                    mailSubject = mailMessageObj.getTicketSubject("TicketClosed", res.getRef_no());
                } else {
                    mailSubject = mailMessageObj.getTicketSubject("TicketReSubmit", res.getRef_no());
                }
                String mailTo = "";
                String mailMessage = null;
                if (res.getStatus().equals("c")) {
                    mailMessage = mailMessageObj.getTicketMessage(request, "TicketClosed", requestorName + "#-#" + res.getRef_no() + "#-#" + res.getResponse());
                } else {
                    mailMessage = mailMessageObj.getTicketMessage(request, "TicketReSubmit", requestorName + "#-#" + res.getRef_no() + "#-#" + res.getResponse());
                }
                SendMailTLS mailObject = new SendMailTLS();
                if (subSupportUnit == null || subSupportUnit.equals("")) {
                    mailTo = CommonConfigurations.ticketAdminMail;
                } else {
                    if (!supportUnit.equals("3")) {
                        mailTo = bo.getTomailAddress(subSupportUnit);
                    } else {
                        mailTo = bo.getCcMail(String.valueOf(empidd));
                    }
                    //mailTo = bo.getTomailAddress(subSupportUnit);
                }
                if (s == null || s.equals("")) {
                    res.setStatus(preStatus);
                    bo.updateRequest(res);

                } else {
                    res.setStatus(request.getParameter("status"));
                    bo.updateRequest(res);
                }
                 ArrayList<TicSystemDataDTO> connectionRes;
                connectionRes = bo.getMailDetails();
                String con_username = connectionRes.get(0).getConfiguration_value();
                String con_password = connectionRes.get(1).getConfiguration_value();
                String con_host = connectionRes.get(2).getConfiguration_value();
                String con_port = connectionRes.get(3).getConfiguration_value();
                
                  TicSystemDataDTO lstdto =bo.insertResponseEdit(res);
                 if (lstdto != null && lstdto.getLastInsertId() != null && lstdto.getLastInsertId() != "") {
                    try {
                        mailObject.smtpMail(mailTo, mailSubject, mailMessage, mailCC, con_username, con_password, con_host, con_port);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }              
               
            }
            res.setStatus("");
            res.setRef_no("");
            List<TicSystemDataDTO> details = bo.fetchTicRequest(res);
            mvc.addObject("details", details);
            String stat = "'Waiting Feedback','Closed'";
            List<TicSystemDataDTO> statusList = bo.getStatus(stat);
            mvc.addObject("statusList", statusList);

        }

        return mvc;
    }

    public synchronized ModelAndView selectEmpByRef_user(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO dto) {
        ModelAndView mvc = new ModelAndView("user_response");
        HttpSession session = request.getSession();
        String admi = (String) session.getAttribute("EMP_ID");
        if (admi == null || admi.equals("")) {
            return new ModelAndView("/unauthorised");
        } else {
            final WebApplicationContext ctx = getWebApplicationContext();
            TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
            String emlpoyeeno = "";
            List<TicSystemDataDTO> emlpoyeenoList = bo.getAdminList();
            emlpoyeeno = emlpoyeenoList.get(0).getConfiguration_value();
            String list[] = emlpoyeeno.split(",");
            for (int i = 0; i < list.length; i++) {
                if (list[i].equals(admi)) {
                    String admin = "admin";
                    request.setAttribute("adminId", admin);
                }
            }

            String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
            String c_date = CommonMethods.getSystemDate(DATE_FORMAT_NOW);
            mvc.addObject("c_date", c_date);
            List<TicSystemDataDTO> detailsRef = bo.getTicRequestByRef(dto);
            String preStatus = detailsRef.get((detailsRef.size() - 1)).getUserStatus();
            String attachedFile = "";
            String resp = "";
            String frId = "";
            String statuss = "";
            String referNo = "";
            String mailCCC = "";
            String mailCcNameList = "";
            List<TicSystemDataDTO> mailCcName = null;
            Iterator itr = detailsRef.iterator();
            while (itr.hasNext()) {
                TicSystemDataDTO filedto = (TicSystemDataDTO) itr.next();
                attachedFile = filedto.getAttachment_file_path();
                resp = filedto.getResponse();
                frId = filedto.getResponseId();
                statuss = filedto.getStatus();
                referNo = filedto.getRef_no();
                if (filedto.getMail_cc_id() == null || filedto.getMail_cc_id().equals("")) {
                    mailCCC = "";
                } else {
                    mailCCC = filedto.getMail_cc_id();
                }
                TicSystemDataDTO modeDto = bo.getFreqC(dto);
                String ee = modeDto.getStatus();
                String c = "c";
                String replied_dat = modeDto.getReplied_date();
                request.setAttribute("hide", modeDto.getStatus());
            }
            if (mailCCC == null || mailCCC.equals("")) {
                mailCCC = "";
            } else {
                mailCcName = bo.getVerifedCcMailNames(mailCCC);
                mailCcNameList = bo.getVerifedCcMailNamesList(mailCCC);
            }
            request.setAttribute("mailCCC", mailCCC);
            request.setAttribute("mailCcList", mailCcName);
            request.setAttribute("mailCcNameList", mailCcNameList);
            mvc.addObject("detailsRef", detailsRef);
//
//        Iterator it = detailsRef.iterator();
//        TicSystemDataDTO dto1 = null;
//        while (it.hasNext()) {
//            dto1 = (TicSystemDataDTO) it.next();
//        }
//        statusList = bo.getNextStatusList(dto);
            List<TicSystemDataDTO> userStatusList = null;
            if (!detailsRef.get(0).getFinalStatus().equals("Closed")) {
                userStatusList = bo.getNextStatusList(Integer.toString(detailsRef.get(0).getId()));
            }
            if (!detailsRef.get(0).getIssue_type().equals("Enhancement request ")) {
            }
            String givenRating = detailsRef.get(0).getRating();
            if (givenRating == null) {
                givenRating = "0";
            }
            mvc.addObject("userStatusList", userStatusList);
            request.setAttribute("resp", resp);
            request.setAttribute("frId", frId);
            request.setAttribute("ref_no", referNo);
            request.setAttribute("preStatus", preStatus);
            request.setAttribute("statuss", statuss);
            request.setAttribute("givenRating", givenRating);
        }

        return mvc;
    }

    public void downloadFile_user(HttpServletRequest requestObj, HttpServletResponse response, TicSystemDataDTO formData) throws IOException {
        String fileName = requestObj.getParameter("filePath");
        String fileNameNew = requestObj.getParameter("originalName");
        String fileType = requestObj.getParameter("fileType");
        String filePath = CommonConfigurations.fileUploadPath;
        final WebApplicationContext ctx = getWebApplicationContext();
        ((TicSystemServiceImpl) ctx.getBean("TicSystemService")).fileDownload(fileName, fileNameNew, filePath, fileType, response);
    }

    public synchronized ModelAndView removeExistingFile(HttpServletRequest requestObj, HttpServletResponse response, TicSystemDataDTO formData) throws IOException {
        String removeParentId = requestObj.getParameter("parent_id");
        final WebApplicationContext ctx = getWebApplicationContext();
        TicSystemServiceImpl service = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
        service.removeFile(removeParentId);
        response.getOutputStream().write("success".getBytes());
        return null;
    }

    public synchronized ModelAndView getRequestTypes(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO formData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
        String selSubUnit = request.getParameter("structureId");
        if(!selSubUnit.equals("3")){
            response.getWriter().println("<option value=''>-- Select Request Type--</option>");
        }
//        List<TicSystemDataDTO> requestTypeList = bo.getRequestTypes(selSubUnit);
        for (TicSystemDataDTO requestTypeList : bo.getRequestTypes(selSubUnit)) {
            response.getWriter().println("<option value='" + requestTypeList.getConfiguration_key() + "'>" + requestTypeList.getConfiguration_value() + "</option>");
        }
        return null;
    }

    public synchronized ModelAndView getRequestAreas(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO formData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
        String selSubUnit = request.getParameter("structureId");
        String issueType = request.getParameter("issue_type");
        List<TicSystemDataDTO> requestAreaList = bo.getRequestAreas(selSubUnit, issueType);
        request.setAttribute("requestAreasList", requestAreaList);
        request.setAttribute("getDataFor", "requestAreas");
        return new ModelAndView("ajax");
    }

    public synchronized ModelAndView getSubUnitList(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO formData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
        String selSubUnit = request.getParameter("structureId");
        List<TicSystemDataDTO> subUnitList = bo.getSubUnitList(selSubUnit);
        request.setAttribute("subUnitList", subUnitList);
        request.setAttribute("getDataFor", "sublist");
        return new ModelAndView("ajax");
    }
}
