
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.controller;

import com.defiance.ideal.travelplan.dao.CommonDaoImpl;
import com.defiance.ideal.travelplan.dto.CommonDto;
import com.defiance.ideal.travelplan.utils.CommonConfigurations;
import com.defiance.ideal.travelplan.utils.CommonFunctions;
import com.defiance.ideal.travelplan.utils.SendMail;
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

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
//import org.jsoup.Jsoup;



/**
 *
 * @author 14578
 */
public class CommonController extends MultiActionController {

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
    private ModelAndView mv;

    public synchronized ModelAndView addTravel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        mv = new ModelAndView("/common/addTravel");
        String employeeId = (String) session.getAttribute("employeeId");
        String currEmpStatus = (String) session.getAttribute("currEmpStatus");
        List<CommonDto> customerList = new ArrayList<CommonDto>();
        List<CommonDto> BuhList=new ArrayList<CommonDto>();
        List<CommonDto> projectList = new ArrayList<CommonDto>();
        List<CommonDto> countryList = new ArrayList<CommonDto>();
        Map visaList = new HashMap();
        Map currencyList = new HashMap();
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
        CommonDto travelData = new CommonDto();
        CommonDto data = new CommonDto();
        CommonDto buh=new CommonDto();
        List<CommonDto> hotelData = new ArrayList<CommonDto>();
        List<CommonDto> ticketData = new ArrayList<CommonDto>();
        List<CommonDto> conveyanceData = new ArrayList<CommonDto>();
        List<CommonDto> attachmentData = new ArrayList<CommonDto>();
        List<CommonDto> workflowList = new ArrayList();
        CommonDto visaData = new CommonDto();
        String travel_type = null;
        try {
            String travel_id = request.getParameter("request_id");

            travel_type = request.getParameter("travel_type");
            data = (CommonDto) commonDao.getEmployeeDetails(employeeId);
            buh.setBuhsName(commonDao.getBuhName(data.getBuh_id()));
            customerList = commonDao.getCustomerDetails();
            BuhList=commonDao.getBUHApproverList(data.getBuh_id());
            currencyList = commonDao.getCurrencyList();
            if (travel_type.equals("I")) {
                visaList = commonDao.getVisaList();
                countryList = commonDao.getCountryList();
            }
            if (travel_id != null) {
                 System.out.print("travel_id");
                if(currEmpStatus.equals("I")) {
                    travelData = commonDao.getTravelData(travel_id);
                    System.out.print("zzz"+travelData.getBuh_id());
                    String buhid = commonDao.getBuhid(data.getGroup_id());
                    if(!buhid.equals(travelData.getBuh_id()) && (travelData.getBuh_id() != null) && !"".equals(travelData.getBuh_id())){
                        travelData.setBuh_change("Y");
                    }else{
                        travelData.setBuh_change("N");
                    }
                    hotelData = commonDao.getTravelHotelData(travel_id);
                    ticketData = commonDao.getTravelTicketData(travel_id);
                    conveyanceData = commonDao.getTravelConveyanceData(travel_id);
                    attachmentData = commonDao.getTravelAttachment(travel_id);
                }

                if (!travelData.getProject_id().equals("") && !travelData.getCustomer_id().equals("")) {
                    projectList = commonDao.getProjects(travelData.getCustomer_id());
                }

                if (ticketData.size() > 0) {
                    for (int k = 0; k < ticketData.size(); k++) {
                        ticketData.get(k).setOut_from_city(commonDao.getCityName(ticketData.get(k).getOut_from_city_id()));
                        ticketData.get(k).setOut_to_city(commonDao.getCityName(ticketData.get(k).getOut_to_city_id()));
                    }
                }
                if (hotelData.size() > 0) {
                    for (int k = 0; k < hotelData.size(); k++) {
                        hotelData.get(k).setOut_city(commonDao.getCityName(hotelData.get(k).getOut_city_id()));
                    }
                }
                if (conveyanceData.size() > 0) {
                    for (int k = 0; k < conveyanceData.size(); k++) {
                        conveyanceData.get(k).setOut_conveyance_city(commonDao.getCityName(conveyanceData.get(k).getOut_conveyance_city_id()));
                    }
                }
                if (travel_type.equals("I")) {
                    visaData = commonDao.getTravelVisa(travel_id);
                }
                if(travelData.getWorkFlow() != null ) {
                    System.out.println("travelData.getWorkFlow()"+travelData.getWorkFlow());
                    String workflow[] = travelData.getWorkFlow().split(",");
                    System.out.println("workflow.length"+workflow.length);
                    for(int i=0;i<workflow.length;i++) {
                        CommonDto obj = new CommonDto();
                        obj.setStatus(workflow[i]);
                        if(workflow[i].equals("r")) {
                            obj.setApprover("RM");
                            if(travelData.getRm_approved_date()!=null && !travelData.getRm_approved_date().equals("00-00-0000"))
                                obj.setApproved_date(travelData.getRm_approved_date());
                            else
                                obj.setApproved_date("");
                            if(travelData.getRm_approved_by() != null)
                                obj.setApproved_by(commonDao.getEmployeeName(travelData.getRm_approved_by()));
                            else
                               obj.setApproved_by("");
                            obj.setApproved_status(travelData.getRm_approved_status());
                            obj.setApprover_remarks(travelData.getRm_remarks());
                            workflowList.add(obj);
                        }
                        if(workflow[i].equals("b")) {
                            obj.setApprover("BUH");
                            if(travelData.getBuh_approved_date()!=null && !travelData.getBuh_approved_date().equals("00-00-0000"))
                                obj.setApproved_date(travelData.getBuh_approved_date());
                            else
                                obj.setApproved_date("");
                            if(travelData.getBuh_approved_by() != null)
                                obj.setApproved_by(commonDao.getEmployeeName(travelData.getBuh_approved_by()));
                            else
                               obj.setApproved_by("");
                            obj.setApproved_status(travelData.getBuh_approved_status());
                            obj.setApprover_remarks(travelData.getBuh_remarks());
                            workflowList.add(obj);
                        }
                        if(workflow[i].equals("m")) {
                            obj.setApprover("CEO");
                            if(travelData.getMd_approved_date()!=null && !travelData.getMd_approved_date().equals("00-00-0000"))
                                obj.setApproved_date(travelData.getMd_approved_date());
                            else
                                obj.setApproved_date("");
                            if(travelData.getMd_approved_by() != null)
                                obj.setApproved_by(commonDao.getEmployeeName(travelData.getMd_approved_by()));
                            else
                               obj.setApproved_by("");
                            obj.setApproved_status(travelData.getMd_approved_status());
                            obj.setApprover_remarks(travelData.getMd_remarks());
                            workflowList.add(obj);
                        }
                        if(workflow[i].equals("f")) {
                            obj.setApprover("Finance");
                            if(travelData.getCfo_action_required() != null && travelData.getCfo_action_required().equals("Y")) {
                                if(travelData.getCfo_approved_date()!=null && !travelData.getCfo_approved_date().equals("00-00-0000"))
                                    obj.setApproved_date(travelData.getCfo_approved_date());
                                else
                                    obj.setApproved_date("");
                                if(travelData.getCfo_approved_by() != null)
                                    obj.setApproved_by(commonDao.getEmployeeName(travelData.getCfo_approved_by()));
                                else
                                   obj.setApproved_by("");
                                obj.setApproved_status(travelData.getCfo_approved_status());
                                obj.setApprover_remarks(travelData.getCfo_remarks());
                                workflowList.add(obj);
                            } else {
                                if(travelData.getFinance_approved_date() !=null && !travelData.getFinance_approved_date().equals("00-00-0000"))
                                    obj.setApproved_date(travelData.getFinance_approved_date());
                                else
                                    obj.setApproved_date("");
                                if(travelData.getFinance_approved_by() != null)
                                    obj.setApproved_by(commonDao.getEmployeeName(travelData.getFinance_approved_by()));
                                else
                                   obj.setApproved_by("");
                                obj.setApproved_status(travelData.getFinance_approved_status());
                                obj.setApprover_remarks(travelData.getFinance_remarks());
                                workflowList.add(obj);
                            }
                        }
                    }
                    if((travelData.getAdmin_action_required() != null && travelData.getAdmin_action_required().equals("Y")) || travel_type.equals("L")) {
                        CommonDto obj = new CommonDto();
                        obj.setApprover("Admin");
                        if(travelData.getAdmin_approved_date()!=null && !travelData.getAdmin_approved_date().equals("00-00-0000"))
                            obj.setApproved_date(travelData.getAdmin_approved_date());
                        else
                            obj.setApproved_date("");
                        if(travelData.getAdmin_approved_by() != null)
                                obj.setApproved_by(commonDao.getEmployeeName(travelData.getAdmin_approved_by()));
                            else
                               obj.setApproved_by("");
                        obj.setApproved_status(travelData.getAdmin_approved_status());
                        obj.setApprover_remarks(travelData.getAdmin_remarks());
                        workflowList.add(obj);
                    }
                    if(!travelData.getAdvance_required().equals("") && !travelData.getAdvance_required().equals("0") && !travel_type.equals("L")) {
                        CommonDto obj = new CommonDto();
                        obj.setApprover("Treasury");
                        if(travelData.getTreasury_approved_date()!=null && !travelData.getTreasury_approved_date().equals("00-00-0000"))
                            obj.setApproved_date(travelData.getTreasury_approved_date());
                        else
                            obj.setApproved_date("");
                        if(travelData.getTreasury_approved_by() != null)
                                obj.setApproved_by(commonDao.getEmployeeName(travelData.getTreasury_approved_by()));
                            else
                               obj.setApproved_by("");
                        obj.setApproved_status(travelData.getTreasury_approved_status());
                        obj.setApprover_remarks(travelData.getTreasury_remarks());
                        workflowList.add(obj);
                    }
                }
            }else{
                travelData.setBuh_change("N");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("data", data);
        mv.addObject("hotelData", hotelData);
        mv.addObject("ticketData", ticketData);
        mv.addObject("conveyanceData", conveyanceData);
        mv.addObject("attachmentData", attachmentData);
        mv.addObject("visaData", visaData);
        mv.addObject("travelData", travelData);
        mv.addObject("customerList", customerList);
        mv.addObject("BuhList", BuhList);
        mv.addObject("buh",buh);
        mv.addObject("projectList", projectList);
        mv.addObject("workflowList", workflowList);
        mv.addObject("visaList", visaList);
        mv.addObject("countryList", countryList);
        mv.addObject("currencyList", currencyList);
        mv.addObject("attachmentPath", CommonConfigurations.travelDocumentsPath);
        mv.addObject("travel_type", travel_type);
        mv.addObject("employeeId", employeeId);
        mv.addObject("system", currEmpStatus);
        return mv;
    }

//    public ModelAndView addfurtherAdvance(HttpServletRequest request,HttpServletResponse response) throws IOException{
//        System.out.println(":::"+request.getParameter("location"));
//        CommonDto obj = new CommonDto();
//        obj.setTpId("100");
//        obj.setEmployeeNumber("14583");
//        String output = "{"+"\"tpId\""+":\""+obj.getTpId()+"\","+"\"employeeNumber\""+":\""+obj.getEmployeeNumber()+"\"}";
//        response.setContentType("application/json");
//        response.getWriter().print(output);
//        return null;
//    }
//    public ModelAndView addfurtherAdvance(HttpServletRequest request,HttpServletResponse response) throws IOException{
//       try {
//            final WebApplicationContext ctx = getWebApplicationContext();
//            CommonDaoImpl dao = (CommonDaoImpl) ctx.getBean("CommonDao");
//            String tpId = request.getParameter("location");
////            CommonDto data = dao.getProjects(tpId);
//            CommonDto data = new CommonDto();
//            data.setTpId("100");
//            data.setEmployeeNumber("14583");
//            response.getOutputStream().write(data.getTpId().getBytes());
//            response.getOutputStream().write(":".getBytes());
//            response.getOutputStream().write(data.getEmployeeNumber().getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public ModelAndView addfurtherAdvance(HttpServletRequest request,HttpServletResponse response, CommonDto formData) throws IOException{
       try {
           HttpSession session = request.getSession();
           String currEmpStatus = (String) session.getAttribute("currEmpStatus");
           String employeeId = (String) session.getAttribute("employeeId");
           String moduleId = (String) session.getAttribute("moduleId");
           mv = new ModelAndView("/common/furtherAdvance");
           Map currencyList = new HashMap();
           final WebApplicationContext ctx = getWebApplicationContext();
           CommonDaoImpl dao = (CommonDaoImpl) ctx.getBean("CommonDao");
           currencyList = dao.getCurrencyList();
           CommonDto data = null;
           if(formData.getSystemForAdvance()!=null && formData.getSystemForAdvance().equals(CommonConfigurations.indiaEmpStatus)){
               data = dao.getTpDetailsForAdvance(formData.getTpIdForAdvance());
           }
           mv.addObject("tpDetails",data);
           mv.addObject("currencyList", currencyList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView saveFurtherAdvance(HttpServletRequest request,HttpServletResponse response,CommonDto formData){
        HttpSession session = request.getSession();
        String currEmpStatus = (String) session.getAttribute("currEmpStatus");
        mv = new ModelAndView("/common/furtherAdvance");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
        CommonDto empDetails = new CommonDto();
        CommonDto commonFormData = new CommonDto();
        boolean flag = false;
        boolean mailFlag = false;
        try {
            formData.setActionName("q");
            if (currEmpStatus != null && currEmpStatus.equals(CommonConfigurations.indiaEmpStatus)) {
                commonFormData.setSystem(CommonConfigurations.indiaEmpStatus);
                if (formData.getAction().equals("Submitted")) {
                    CommonDto statusAndFlow = commonDao.getAdvanceStatusFlowInfo(formData);
                    if (statusAndFlow != null) {
                        formData.setAdvanceWorkFlowId(statusAndFlow.getAdvanceWorkFlowId());
                        formData.setAdvanceStatus(statusAndFlow.getAdvanceStatus());
                        formData.setAdvancePreviousStatus("q");
                        mailFlag = true;
                    }
                } else if (formData.getAction().equals("Saved")) {
                    formData.setAdvanceWorkFlowId("");
                    formData.setAdvancePreviousStatus("");
                    formData.setAdvanceStatus("s");
                } else {
                    formData.setAdvancePreviousStatus("");
                    formData.setAdvanceStatus("x");
                    formData.setMail_status("Cancel");
                    mailFlag = true;
                }
                commonDao.updateTravelAdvanceData(request, response, formData);
            }
            if(mailFlag) {
                //mailProcess(commonFormData,formData);
            }
            mv = new ModelAndView("redirect:getAdvanceTpList.htm?page=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }


    public ModelAndView submitTravel(HttpServletRequest request, HttpServletResponse response, CommonDto formData) throws Exception {
        HttpSession session = request.getSession();
        String currEmpStatus = (String) session.getAttribute("currEmpStatus");
        String employeeId = (String) session.getAttribute("employeeId");
        String moduleId = (String) session.getAttribute("moduleId");
        mv = new ModelAndView("/common/addTravel");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
        CommonDto empDetails = new CommonDto();
        CommonDto commonFormData = new CommonDto();
        CommonDto data = new CommonDto();
        boolean flag = false;
        boolean mailFlag = false;
        try {
//            System.out.print("try here");
//            System.out.print("try here"+formData.getChange_buh_id());
            data = (CommonDto) commonDao.getEmployeeDetails(employeeId);
//            System.out.println("data@@"+ data.getEmpManager());
            if(formData.getChange_buh_id()==null)
            {
                  formData.setChange_buh_id(data.getBuh_id());
            }
            if(data.getBuh_id()!=null)
            {
                if(formData.getChange_buh_id()==null)
                {
                  formData.setChange_buh_id(data.getBuh_id());
                }
            }

            if (formData.getAction().equals("Submitted")) {
                String admin_action_required = "N";
                if (formData.getTpReferenceId() == null || formData.getTpReferenceId().equals("")) {
                    String india_lastId = commonDao.getLastRequestId();
                    String tp_request_id = CommonFunctions.getRequestId(india_lastId, formData.getTravelType()+"T",formData.getTravel_requested_date());
                    formData.setTpReferenceId(tp_request_id);
                    formData.setMail_status("Submit");
                } else {
                    System.out.print("prevs"+formData.getPrevious_status());
                    System.out.print("prevs"+formData.getCurrentStatus());
                    if(formData.getCurrentStatus().equals("o")|| formData.getPrevious_status().equals("q") || formData.getPrevious_status().equals("r")||formData.getPrevious_status().equals("b"))
                        formData.setMail_status("ReSubmit");
                }

                commonFormData.setEmployee_id(employeeId);
                commonFormData.setSalesGroupId(CommonConfigurations.salesGroupId);
                empDetails = CommonFunctions.commonManipulationCode(commonDao.getEmpDetails(commonFormData), moduleId, employeeId, "q");

              if(formData.getTravel_other_country() != null ) {
                    if(formData.getTravel_other_country().equals("Y"))
                        empDetails.setVetri("USY");
                    else
                       empDetails.setVetri("USN");
                } else {
                    formData.setTravel_other_country("");
                }

                empDetails.setBillable(formData.getTravel_billable());

                if (formData.getTicket_id().length > 0) {
                    for (int k = 0; k < formData.getTicket_id().length; k++) {
                        if (formData.getTicketdeleted()[k].equals("0") && !formData.getTravel_preference()[k].equals("") && !formData.getTravel_mode()[k].equals("") && formData.getTravel_date()[k] != null) {
                            admin_action_required = "Y";
                            System.out.println("formData.setAdmin_action_required("+formData.getAdmin_action_required());
                            if (formData.getTravelType().equals("D") && formData.getTravel_mode()[k].equals("Air")) {
                                flag = true;

                            }
                        }
                    }
                }
                if (formData.getHotel_id().length > 0) {
                    for (int k = 0; k < formData.getHotel_id().length; k++) {
                        if (formData.getHoteldeleted()[k].equals("0") && formData.getFrom_date()[k] != null && formData.getTo_date()[k] != null && !formData.getLocation()[k].equals("")) {
                            admin_action_required = "Y";
                        }
                    }
                }
                if (formData.getConveyance_id() != null && formData.getConveyance_id().length > 0) {
                    System.out.println("Conveyance"+formData.getConveyance_id());
                    for (int k = 0; k < formData.getConveyance_id().length; k++) {
                        if (formData.getConveyancedeleted()[k].equals("0") && formData.getStart_date()[k] != null && formData.getEnd_date()[k] != null && !formData.getPickup()[k].equals("") && !formData.getDropto()[k].equals("") ) {
                            admin_action_required = "Y";
                        }
                    }
                }
                formData.setAdmin_action_required(admin_action_required);
                if (formData.getTravelType().equals("D")) {
                    if (flag) {
                        empDetails.setDeviation("Y");
                    } else {
                        empDetails.setDeviation("N");
                    }
                } else {
                    empDetails.setDeviation("N");
                }
            }
            if (currEmpStatus != null && currEmpStatus.equals(CommonConfigurations.indiaEmpStatus)) {
                commonFormData.setSystem(CommonConfigurations.indiaEmpStatus);
                if (formData.getAction().equals("Submitted")) {
                    CommonDto statusAndFlow = commonDao.getStatusFlowInfo(empDetails);
                    //System.out.println("empDetails.getManage"+data.getManager());
                    //workflow changes only for employees who is reporting to CEO starts here 
                    if(data.getEmpManager().equals("2405")){                       
                       formData.setTpWorkFlowId((statusAndFlow.getTpWorkFlowId()));
                     // System.out.println("Work Flows:"+statusAndFlow.getTpWorkFlowName());
                       String splitWF[]=statusAndFlow.getTpWorkFlowName().split(",");
                       String status="";
                       if(splitWF[1].equals("r")){
                            status=splitWF[2] ;
                        } else {
                           status=splitWF[1] ;
                       }          
                       formData.setStatus(status);
                        formData.setPrevious_status("q");
                        mailFlag = true;                       
                   }
                    //ends here
                   else if (statusAndFlow != null) {
                        formData.setTpWorkFlowId(statusAndFlow.getTpWorkFlowId());
                        formData.setStatus(statusAndFlow.getStatus());
                        formData.setPrevious_status("q");
                        mailFlag = true;
                    }
                } else if (formData.getAction().equals("Saved")) {
                    formData.setTpWorkFlowId("0");
                    formData.setPrevious_status("");
                    formData.setStatus("s");
                } else {
                    formData.setPrevious_status("");
                    formData.setStatus("x");
                    formData.setMail_status("Cancel");
                    mailFlag = true;
                }

                String last_id = commonDao.updateTravelData(request, response, formData);
                commonFormData.setTpId(last_id);
            }

            System.out.print("mailstatus"+formData.getMail_status());
            System.out.print("prev"+formData.getPrevious_status());
            if(mailFlag) {
                if(formData.getMail_status().equals("Submit")) {
                   mailProcess(commonFormData,formData);
                } else if(formData.getMail_status().equals("ReSubmit")) {
                    
                    System.out.println("inside resubmit");

                    //if(!formData.getPrevious_status().equals("q") || !formData.getCurrentStatus().equals("r")) {
                          mailProcess(commonFormData,formData);
                    //}
                }
            }


               /* if(!formData.getMail_status().equals("ReSubmit") && !formData.getPrevious_status().equals("q"))
                mailProcess(commonFormData,formData);
               }*/
            mv = new ModelAndView("redirect:getDashBoardList.htm?page=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView viewTravel(HttpServletRequest request, HttpServletResponse response, CommonDto formData) throws Exception {
        HttpSession session = request.getSession();
        mv = new ModelAndView("/common/viewTravel");
        String moduleId = (String)session.getAttribute("moduleId");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
        CommonDto travelData = new CommonDto();
        CommonDto data = new CommonDto();
        List<CommonDto> hotelData = new ArrayList<CommonDto>();
        List<CommonDto> ticketData = new ArrayList<CommonDto>();
        List<CommonDto> conveyanceData = new ArrayList<CommonDto>();
        List<CommonDto> attachmentData = new ArrayList<CommonDto>();
        CommonDto visaData = new CommonDto();
        CommonDto genericDetails = new CommonDto();
        Map visaList = new HashMap();
        Map currencyList = new HashMap();
        String travel_type = null;
        String system = null;
        String approveType = null;
        List<CommonDto> workflowList = new ArrayList();
        try {
            String travel_id = request.getParameter("request_id");
            travel_type = request.getParameter("travel_type");
            system = request.getParameter("system");
            approveType = request.getParameter("approveType");
               if (travel_id != null) {
                currencyList = commonDao.getCurrencyList();

                if(system.equals("I")) {
                    travelData = commonDao.getTravelData(travel_id);
                  //travelData = commonDao.getTravelData(travel_id);
                    hotelData = commonDao.getTravelHotelData(travel_id);
                    ticketData = commonDao.getTravelTicketData(travel_id);
                    conveyanceData = commonDao.getTravelConveyanceData(travel_id);
                    attachmentData = commonDao.getTravelAttachment(travel_id);
                    if (travel_type.equals("I")) {
                        visaData = commonDao.getTravelVisa(travel_id);
                        genericDetails = commonDao.getGenericDetails(travelData.getEmployee_id());
                    }
                }
                if (travel_type.equals("I")) {
                    visaList = commonDao.getVisaList();
                    if(visaData != null) {
                        if(visaData.getCountry_issue() != null)
                            visaData.setCountry_issue(commonDao.getCountryName(visaData.getCountry_issue()));
                        if(visaData.getVisa_type() != null)
                            visaData.setVisa_type((String) visaList.get(visaData.getVisa_type()));
                    }
                }
                travelData.setCurrency_type((String) currencyList.get(travelData.getCurrency_type()));
                data = (CommonDto) commonDao.getEmployeeDetails(travelData.getEmployee_id());
                if (!travelData.getCustomer_id().equals("") && !travelData.getCustomer_id().equals("-1")) {
                    travelData.setCustomer_others(commonDao.getCustomerName(travelData.getCustomer_id()));
                }
                if (!travelData.getProject_id().equals("") && !travelData.getProject_id().equals("-1")) {
                    travelData.setProject_others(commonDao.getProjectName(travelData.getProject_id()));
                }
                if (ticketData.size() > 0) {
                    for (int k = 0; k < ticketData.size(); k++) {
                        ticketData.get(k).setOut_from_country(commonDao.getCountryName(ticketData.get(k).getOut_from_country()));
                        ticketData.get(k).setOut_to_country(commonDao.getCountryName(ticketData.get(k).getOut_to_country()));
                        ticketData.get(k).setOut_from_city(commonDao.getCityName(ticketData.get(k).getOut_from_city_id()));
                        ticketData.get(k).setOut_to_city(commonDao.getCityName(ticketData.get(k).getOut_to_city_id()));
                    }
                }
                if (hotelData.size() > 0) {
                    for (int k = 0; k < hotelData.size(); k++) {
                        hotelData.get(k).setOut_country(commonDao.getCountryName(hotelData.get(k).getOut_country()));
                        hotelData.get(k).setOut_city(commonDao.getCityName(hotelData.get(k).getOut_city_id()));
                    }
                }
                if (conveyanceData.size() > 0) {
                    for (int k = 0; k < conveyanceData.size(); k++) {
                        conveyanceData.get(k).setOut_conveyance_country(commonDao.getCountryName(conveyanceData.get(k).getOut_conveyance_country()));
                        conveyanceData.get(k).setOut_conveyance_city(commonDao.getCityName(conveyanceData.get(k).getOut_conveyance_city_id()));
                    }
                }
                if (travel_type.equals("I")) {
                    genericDetails = commonDao.getEmpGenericDetails(travelData.getEmployee_id());
                    if(genericDetails != null)
                        genericDetails.setNationality(commonDao.getCountryName(genericDetails.getNationality()));
                }
                System.out.println("travelData.getWorkFlow()"+travelData.getWorkFlow());
                String workflow[] = travelData.getWorkFlow().split(",");
                System.out.println("workflow.length"+workflow.length);
                for(int i=0;i<workflow.length;i++) {
                    CommonDto obj = new CommonDto();
                    obj.setStatus(workflow[i]);
                    if(workflow[i].equals("r")) {
                        obj.setApprover("RM");

                        if(travelData.getRm_approved_date()!=null && !travelData.getRm_approved_date().equals("00-00-0000"))
                            obj.setApproved_date(travelData.getRm_approved_date());
                        else
                            obj.setApproved_date("");
                        if(travelData.getRm_approved_by() != null)
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getRm_approved_by()));
                        else
                           obj.setApproved_by("");
                        obj.setApproved_status(travelData.getRm_approved_status());
                        obj.setApprover_remarks(travelData.getRm_remarks());
                        workflowList.add(obj);
                    }
                    if(workflow[i].equals("b")) {
                        obj.setApprover("BUH");
                        if(travelData.getBuh_approved_date()!=null && !travelData.getBuh_approved_date().equals("00-00-0000"))
                            obj.setApproved_date(travelData.getBuh_approved_date());
                        else
                            obj.setApproved_date("");
                        if(travelData.getBuh_approved_by() != null)
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getBuh_approved_by()));
                        else
                           obj.setApproved_by("");
                        obj.setApproved_status(travelData.getBuh_approved_status());
                        obj.setApprover_remarks(travelData.getBuh_remarks());
                        workflowList.add(obj);
                    }
                    if(workflow[i].equals("m")) {
                        obj.setApprover("CEO");
                        if(travelData.getMd_approved_date() !=null && !travelData.getMd_approved_date().equals("00-00-0000"))
                            obj.setApproved_date(travelData.getMd_approved_date());
                        else
                            obj.setApproved_date("");
                        if(travelData.getMd_approved_by() != null)
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getMd_approved_by()));
                        else
                           obj.setApproved_by("");
                        obj.setApproved_status(travelData.getMd_approved_status());
                        obj.setApprover_remarks(travelData.getMd_remarks());
                        workflowList.add(obj);
                    }
                    if(workflow[i].equals("f")) {
                        obj.setApprover("Finance");
                        if(travelData.getFinance_approved_date() !=null && !travelData.getFinance_approved_date().equals("00-00-0000"))
                            obj.setApproved_date(travelData.getFinance_approved_date());
                        else
                            obj.setApproved_date("");
                        if(travelData.getFinance_approved_by() != null)
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getFinance_approved_by()));
                        else
                           obj.setApproved_by("");
                        obj.setApproved_status(travelData.getFinance_approved_status());
                        obj.setApprover_remarks(travelData.getFinance_remarks());
                        workflowList.add(obj);
                    }
                }
                if(travelData.getCfo_action_required() != null && travelData.getCfo_action_required().equals("Y")) {
                    CommonDto obj = new CommonDto();
                    obj.setApprover("CFO");
                    if(travelData.getCfo_approved_date() !=null && !travelData.getCfo_approved_date().equals("00-00-0000"))
                        obj.setApproved_date(travelData.getCfo_approved_date());
                    else
                        obj.setApproved_date("");
                    if(travelData.getCfo_approved_by() != null)
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getCfo_approved_by()));
                        else
                           obj.setApproved_by("");
                    obj.setApproved_status(travelData.getCfo_approved_status());
                    obj.setApprover_remarks(travelData.getCfo_remarks());
                    workflowList.add(obj);
                }
                if((travelData.getAdmin_action_required() != null && travelData.getAdmin_action_required().equals("Y")) || travel_type.equals("L")) {
                    CommonDto obj = new CommonDto();
                    obj.setApprover("Admin");
                    if(travelData.getAdmin_approved_date()!=null && !travelData.getAdmin_approved_date().equals("00-00-0000"))
                        obj.setApproved_date(travelData.getAdmin_approved_date());
                    else
                        obj.setApproved_date("");
                    if(travelData.getAdmin_approved_by() != null)
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getAdmin_approved_by()));
                        else
                           obj.setApproved_by("");
                    obj.setApproved_status(travelData.getAdmin_approved_status());
                    obj.setApprover_remarks(travelData.getAdmin_remarks());
                    workflowList.add(obj);
                }
                if(!travelData.getAdvance_required().equals("") && !travelData.getAdvance_required().equals("0") && !travel_type.equals("L")) {
                    CommonDto obj = new CommonDto();
                    obj.setApprover("Treasury");
                    obj.setApproved_date(travelData.getTreasury_approved_date());
                    if(travelData.getTreasury_approved_by() != null)
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getTreasury_approved_by()));
                        else
                           obj.setApproved_by("");
                    obj.setApproved_status(travelData.getTreasury_approved_status());
                    obj.setApprover_remarks(travelData.getTreasury_remarks());
                    workflowList.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.addObject("data", data);
        
        mv.addObject("hotelData", hotelData);
        mv.addObject("ticketData", ticketData);
        mv.addObject("convyanceData", conveyanceData);
        mv.addObject("attacehmentData", attachmentData);
        mv.addObject("visaData", visaData);
        mv.addObject("travelData", travelData);
        mv.addObject("travel_type", travel_type);
        mv.addObject("system", system);
        mv.addObject("approveType", approveType);
        mv.addObject("workflowList",workflowList);
        mv.addObject("moduleId",moduleId);
        mv.addObject("currencyList",currencyList);
        mv.addObject("genericDetails",genericDetails);
        return mv;
    }

    public ModelAndView getInternationalAddPage(HttpServletRequest request, HttpServletResponse response, CommonDto internationalFormData) throws Exception {
        System.out.println(":::Inside the getInternationalAddPage... ");
        mv = new ModelAndView("/common/addEmployeeProfile");
        HttpSession session = request.getSession();
        String system = request.getParameter("system");
        String currEmpStatus = null;
        if(system != null) {
            mv.addObject("system", system);
            currEmpStatus = system;
        } else {
            currEmpStatus = (String) session.getAttribute("currEmpStatus");
            mv.addObject("system", currEmpStatus);
        }
        String empId = (String) session.getAttribute("employeeId");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
       List<CommonDto> nationalityList = commonDao.getNationalityList();
        CommonDto empGenericDetails = null;
        CommonDto empDetails = null;
        if (currEmpStatus != null && currEmpStatus.equals(CommonConfigurations.indiaEmpStatus)) {
             empDetails = commonDao.getGenericDetails(empId);
            empGenericDetails = commonDao.getEmpGenericDetails(empId);
        }
        String request_id = request.getParameter("request_id");
        if(request_id != null) {
            mv.addObject("request_id", request_id);
        } else {
             mv.addObject("request_id", "");
        }
        if (empGenericDetails != null) {
            mv.addObject("empGenericDetails", empGenericDetails);
        }
        mv.addObject("nationalityList", nationalityList);
        mv.addObject("empDetails", empDetails);
        mv.addObject("employeeId", empId);
        return mv;
    }

    public ModelAndView submitGenericDetails(HttpServletRequest request, HttpServletResponse response, CommonDto genericDetailsDto) {
        System.out.println("Sum=bmit generic Details::::::::");
        HttpSession session = request.getSession();
        String currEmpStatus = (String) session.getAttribute("currEmpStatus");
        String empId = (String) session.getAttribute("employeeId");
        String system = request.getParameter("system");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
         genericDetailsDto.setEmpId(empId);
        if (currEmpStatus != null && currEmpStatus.equals(CommonConfigurations.indiaEmpStatus)) {
            commonDao.insertEmpGenericDetails(genericDetailsDto);
        }
        if(!genericDetailsDto.getMaster_id().equals(""))
            mv = new ModelAndView("redirect:addTravel.htm?travel_type=I&request_id="+genericDetailsDto.getMaster_id()+"&system="+system);
        else
            mv = new ModelAndView("redirect:addTravel.htm?travel_type=I&system="+system);

        return mv;
    }

    public ModelAndView approvalAction(HttpServletRequest request,HttpServletResponse response,CommonDto interFormData)throws Exception {
        HttpSession session = request.getSession();
        String employeeId = (String)session.getAttribute("employeeId");
        System.out.println("ddd"+employeeId);
        String moduleId = (String)session.getAttribute("moduleId");
        mv = new ModelAndView("redirect:getApprovalDashBoardList.htm?page=1");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDaoIndia = (CommonDaoImpl) ctx.getBean("CommonDao");
        CommonDto approvalData  = CommonFunctions.commonCodeForApproval(moduleId, employeeId, interFormData);
        CommonDto approvalStatusFlow = new CommonDto();
        CommonDto travelData = new CommonDto();
        String firstLevelEmpId = null;
        String secondLevelEmpId = null;
        if(interFormData.getApproveAction() != null) {
            if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
                approvalStatusFlow = commonDaoIndia.getApprovalStatusFlowInfo(approvalData);

            }
            approvalData.setMail_status("Approve");
            if(approvalStatusFlow != null) {
                System.out.println("moduleId3"+moduleId);
                approvalData.setStatus(approvalStatusFlow.getStatus());
                approvalData.setPrevious_status(approvalData.getActionName());

                // RM & BUH same
                if(approvalData.getActionName().equals(CommonConfigurations.rmActionCode) && approvalStatusFlow.getStatus() != null && approvalStatusFlow.getStatus().equals(CommonConfigurations.buhActionCode)) {
                    approvalData.setNextLevel(approvalStatusFlow.getStatus());
                    approvalData.setRequestorPracticeGroup(approvalStatusFlow.getRequestorPracticeGroup());
                    if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus))
                        //firstLevelEmpId = commonDaoIndia.getNextLevelEmpId(approvalData);
                        firstLevelEmpId=approvalStatusFlow.getBuhid();
                     if(employeeId.equals(firstLevelEmpId)) {
                        approvalData.setStatus(approvalStatusFlow.getFirstNextLevel());
                        approvalData.setPrevious_status(CommonConfigurations.buhActionCode);
                        approvalData.setColumnName(CommonConfigurations.buhColumnName);
                    }
                    if(approvalStatusFlow.getFirstNextLevel() != null && approvalStatusFlow.getFirstNextLevel().equals(CommonConfigurations.mdActionCode)) {
                        approvalData.setRequestorPracticeGroup("0");
                        approvalData.setNextLevel(approvalStatusFlow.getFirstNextLevel());
                        if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus))
                            secondLevelEmpId = commonDaoIndia.getNextLevelEmpId(approvalData);

                        if(secondLevelEmpId.equals(firstLevelEmpId)) {
                            approvalData.setPrevious_status(CommonConfigurations.rmActionCode);
                            approvalData.setStatus(approvalStatusFlow.getStatus());
                            approvalData.setColumnName(CommonConfigurations.rmColumnName);
                        }
                    }
                }
                //mapping for finance
                if(approvalStatusFlow.getStatus() != null && approvalStatusFlow.getStatus().equals(CommonConfigurations.financeActionCode)){
                    System.out.print("PLZZZZZ");
                    approvalData.setNextLevel(approvalStatusFlow.getStatus());
                     approvalData.setRequestorPracticeGroup(approvalStatusFlow.getRequestorPracticeGroup());
                     if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus))
                       // firstLevelEmpId = commonDaoIndia.getNextLevelEmpId(approvalData);
                      approvalData.setStatus(approvalStatusFlow.getStatus());
                }


                // BUH & MD same
                if(approvalData.getActionName().equals(CommonConfigurations.buhActionCode) && approvalStatusFlow.getStatus() != null && approvalStatusFlow.getStatus().equals(CommonConfigurations.mdActionCode)) {
                    System.out.println("buh approval");
                    approvalData.setRequestorPracticeGroup("0");
                    approvalData.setNextLevel(approvalStatusFlow.getStatus());
                    String rm_id = commonDaoIndia.getRMApprover(approvalData.getTpId());
                    if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus))
                        firstLevelEmpId = commonDaoIndia.getNextLevelEmpId(approvalData);

                    if(employeeId.equals(firstLevelEmpId)) {
                        approvalData.setStatus(approvalStatusFlow.getFirstNextLevel());
                        approvalData.setPrevious_status(CommonConfigurations.mdActionCode);
                        approvalData.setColumnName(CommonConfigurations.mdColumnName);
                    }
                    if(rm_id.equals(firstLevelEmpId)){
                        commonDaoIndia.updateApprovalStatus(approvalData);
                        approvalData.setStatus(approvalStatusFlow.getFirstNextLevel());
                        approvalData.setPrevious_status(CommonConfigurations.mdActionCode);
                        approvalData.setColumnName(CommonConfigurations.mdColumnName);
                        approvalData.setRemarks("Auto Approved");
                        approvalData.setApprovedBy(rm_id);
                    }
                }

                //Rm & MD Same

                 if(approvalData.getActionName().equals(CommonConfigurations.rmActionCode) && approvalStatusFlow.getStatus() != null && approvalStatusFlow.getStatus().equals(CommonConfigurations.mdActionCode)) {
                    approvalData.setRequestorPracticeGroup("0");
                    approvalData.setNextLevel(approvalStatusFlow.getStatus());
                    if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus))
                        firstLevelEmpId = commonDaoIndia.getNextLevelEmpId(approvalData);

                    if(employeeId.equals(firstLevelEmpId)) {
                        approvalData.setStatus(approvalStatusFlow.getFirstNextLevel());
                        approvalData.setPrevious_status(CommonConfigurations.mdActionCode);
                        approvalData.setColumnName(CommonConfigurations.mdColumnName);
                    }
                }


                if(moduleId!=null && moduleId.equals(CommonConfigurations.cfoModId)) {
                    approvalData.setPrevious_status("c");
                }
                if(moduleId!=null && moduleId.equals(CommonConfigurations.adminModId)) {
                    approvalData.setPrevious_status("a");
                    approvalData.setStatus("a");
                }
                if(moduleId!=null && moduleId.equals(CommonConfigurations.treausryModId)) {
                    approvalData.setPrevious_status("t");
                    approvalData.setStatus("a");
                    System.out.println("xxx=====>"+approvalData.getPrevious_status());
                    System.out.println("xxx=====>"+approvalData.getStatus());
                        if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus))
                            System.out.println("Currency Type ++++++++ "+interFormData.getCurrency_type());
                            commonDaoIndia.updateTreasuryDetails(interFormData);

                }
                if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus))
                    commonDaoIndia.updateApprovalStatus(approvalData);

            }
        } else if(interFormData.getSendBackAction()!=null){
            approvalData.setStatus("o");
            approvalData.setMail_status("Reject");
            approvalData.setPrevious_status("");
            approvalData.setApproved_status("r");
            if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus))
                commonDaoIndia.updateApprovalStatus(approvalData);

        } else if(interFormData.getCfoAction()!=null && CommonConfigurations.finModId.equals(moduleId)) {
            approvalData.setMail_status("Approve");
            approvalData.setPrevious_status(approvalData.getActionName());
            approvalData.setStatus("c");
            if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)) {
                commonDaoIndia.updateApprovalStatus(approvalData);
                commonDaoIndia.updateFinanceToCfo(approvalData);
            }
        }
        
        else if(interFormData.getPrintAction()!=null) {            
             try{           
            ServletOutputStream servletOutputStream1 = response.getOutputStream();
            ServletContext context = getServletContext();
            InputStream reportStream1;
            reportStream1 = getServletContext().getResourceAsStream("/jasper/TravelReport.jasper");
            Connection connection=commonDaoIndia.getConnectionObject();
            
            HashMap map = new HashMap();                       
            map.put("tpreferenceid",approvalData.getTpId());
            travelData = commonDaoIndia.getTravelData(approvalData.getTpId());
            
            System.out.println("empid"+travelData.getEmployee_id());
            map.put("employeeid", travelData.getEmployee_id());
            //map.put("REPORT_CONNECTION", connection);             
            map.put("SUBREPORT_DIR", context.getRealPath("/jasper/") + "\\");
            map.put("images", context.getRealPath("/css/images/Defilogo.png"));
            System.out.println("Parameters:" + map);
            //System.out.println("Parameters:" + connection);         
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=TravelReport-"+travelData.getTpReferenceId()+".pdf");            
            
            JasperPrint print1 = JasperFillManager.fillReport(reportStream1, map,connection);                
            JRPdfExporter exporterPDF = new JRPdfExporter();
            exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, print1);
            exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream1);
            exporterPDF.exportReport();
            servletOutputStream1.flush();
            servletOutputStream1.close();
             } catch(Exception e){
                 System.out.println("Exception in PrintAction:"+e.getMessage());
             }
            return null;
        }
        
        mailProcess(interFormData,approvalData);
        mv.addObject("approveType", "pending");
        return mv;
    }

    public void mailProcess(CommonDto interFormData,CommonDto approvalData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
        CommonDto travelData = new CommonDto();
        SendMail mailObj = null;
        List<CommonDto> connectionRes = new ArrayList();
        connectionRes =  (ArrayList<CommonDto>) commonDao.getConfigValueData("10");
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();
        String FromName = null;
        String ToName = null;
        String mailHeader = null;
        String mailHeaderRequestor = null;
        String mailFooter = "";
        String ccAddress = null;
        String[] toAddress = new String[7];
        String toEmail = null;
        boolean adminFlag = false;
        boolean requestorFlag = false;
        String mailContent = "";

        mailFooter += "<br><br>Please login to iDeal to view the details.<br><br>";
        mailFooter += "Regards,<br>";
        mailFooter += "iDeal Admin<br>";
        mailFooter += "http://ideal.hindujatech.com";

        try {
            mailObj = new SendMail(con_username, con_password, con_host, con_port);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("interFormData.getTpId()"+interFormData.getTpId());
        System.out.println("interFormData.getSystem()"+interFormData.getSystem());
        if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
            travelData = commonDao.getTravelDetails(interFormData.getTpId());
        }

        if(travelData != null) {
            if(travelData.getCustomer_id() != null && !travelData.getCustomer_id().equals("-1") && !travelData.getCustomer_id().equals("")) {
                travelData.setCustomerName(commonDao.getCustomerName(travelData.getCustomer_id()));
            } else if(travelData.getCustomer_others() != null && !travelData.getCustomer_others().equals("")){
                travelData.setCustomerName(travelData.getCustomer_others());
            } else {
                travelData.setCustomerName("");
            }

            if(travelData.getProject_id() != null && !travelData.getProject_id().equals("-1") && !travelData.getProject_id().equals("")) {
                travelData.setProjectName(commonDao.getProjectName(travelData.getProject_id()));
            } else if(travelData.getProject_others() != null && !travelData.getProject_others().equals("")){
                travelData.setProjectName(travelData.getProject_others());
            } else {
                travelData.setProjectName("");
            }

            mailContent += "Requestor Name/Id : "+travelData.getEmployeeName()+"<br>";
            mailContent += "Request Type : "+travelData.getTravel_text()+"<br>";
            mailContent += "Request Id : "+travelData.getTpReferenceId()+"<br>";
            mailContent += "Project Travel : "+travelData.getTravel_billable()+"<br>";
            mailContent += "Project Name : "+travelData.getProjectName()+"<br>";
            mailContent += "Customer Name : "+travelData.getCustomerName()+"<br>";
            mailContent += "Client Reimbursable : "+travelData.getClient_reimbursable()+"<br>";

            System.out.println("aaaa"+approvalData.getMail_status());
            if(approvalData.getMail_status().equals("Approve") || approvalData.getMail_status().equals("Submit")) {
                //To get To 'Email Address'
                System.out.println("bbbb"+approvalData.getStatus());
                if(approvalData.getStatus() != null && approvalData.getStatus().equals("r") ) {
                    toAddress[0] = commonDao.getApproverEmail(travelData.getManager());
                } else {
                    List<CommonDto> approvers = new ArrayList();
                     List<CommonDto> approver = new ArrayList();
                    String approverEmail = null;
                    if(!approvalData.getStatus().equals("b") && !approvalData.getStatus().equals("f") ) {
                        travelData.setRequestorPracticeGroup("0");
                    }
                    if(!approvalData.getStatus().equals("a") && !approvalData.getStatus().equals("b") && !approvalData.getStatus().equals("f")) {
                        travelData.setNextLevel(approvalData.getStatus());
                        if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
                            approvers = commonDao.getApprovers(travelData);
                        } 
                        toAddress[0] = getCommonApproverEmail(approvers);
                    }

                   else if (approvalData.getStatus() != null && approvalData.getStatus().equals("b")) {
                        travelData.setNextLevel(approvalData.getStatus());
                         if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
                            toAddress[0] = commonDao.getApproverEmail(travelData.getChangeBuh());
                        }
                    }
                     else if (approvalData.getStatus() != null && approvalData.getStatus().equals("f")) {
                         travelData.setNextLevel(approvalData.getStatus());
                         if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
                             approvers = commonDao.getApprovers(travelData);
                             for(int i=0;i<approvers.size();i++){
                                  System.out.println("finance approval id App ID:"+approvers.get(i).getApproverId());
                                  if(Integer.parseInt(approvers.get(i).getApproverId()) !=259 &&  Integer.parseInt(approvers.get(i).getApproverId()) !=5513 && Integer.parseInt(approvers.get(i).getApproverId()) !=1002 && Integer.parseInt(approvers.get(i).getApproverId()) !=1470 && Integer.parseInt(approvers.get(i).getApproverId()) !=3194){
                                     //toAddress[0] = commonDao.getApproverEmail(approvers.get(i).getApproverId());
                                      System.out.println("to finance amil "+travelData.getEmail_address());
                                      toAddress[0] = travelData.getEmail_address();
                                  }
                             }

                         }
                     }

                    else {
                        if(approvalData.getStatus().equals("a") && !approvalData.getPrevious_status().equals("a") && !approvalData.getPrevious_status().equals("t") && ( ( travelData.getAdmin_action_required() != null && travelData.getAdmin_action_required().equals("Y") ) || travelData.getTravelType().equals("L") ) ) {
                            travelData.setNextLevel("a");
                            if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
                                approvers = commonDao.getApprovers(travelData);
                            }
                            String approversEmail = null;
                            String Mail = "";
                                for(int i=0;i<approvers.size();i++){
                                  System.out.println("App ID:"+approvers.get(i).getApproverId());
                                  if(Integer.parseInt(approvers.get(i).getApproverId()) !=5513 && Integer.parseInt(approvers.get(i).getApproverId()) !=1002 && Integer.parseInt(approvers.get(i).getApproverId()) !=1470){                                    
                                      approversEmail=commonDao.getApproverEmail(approvers.get(i).getApproverId());
                                      if(i==0)
                                     Mail += approversEmail;
                                     else
                                     Mail += ","+approversEmail;
                                  }
                             }
                            toAddress[0]=Mail;
                            //toAddress[0] = getCommonApproverEmail(approvers);
                            adminFlag = true;
                        }
                        if(approvalData.getStatus().equals("a") && !approvalData.getPrevious_status().equals("t") && !approvalData.getPrevious_status().equals("a") && travelData.getAdvance_required()!= null && !travelData.getAdvance_required().equals("") && !travelData.getAdvance_required().equals("0") && !travelData.getTravelType().equals("L") ) {
                            travelData.setNextLevel("t");
                            if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
                                approvers = commonDao.getApprovers(travelData);
                            } 
                            if(!adminFlag){
                                 String approversEmail = null;
                                 String Mail = "";
                                for(int i=0;i<approvers.size();i++){
                                  System.out.println("App ID:"+approvers.get(i).getApproverId());
                                  if(Integer.parseInt(approvers.get(i).getApproverId()) !=5513 && Integer.parseInt(approvers.get(i).getApproverId()) !=1002 && Integer.parseInt(approvers.get(i).getApproverId()) !=1470){
                                     approversEmail=commonDao.getApproverEmail(approvers.get(i).getApproverId());
                                      if(i==0)
                                     Mail += approversEmail;
                                     else
                                     Mail += ","+approversEmail;
                                  }
                             }
                                toAddress[0]=Mail;
                             // toAddress[0] = getCommonApproverEmail(approvers);
                            }
                            else{
                                String approversEmail = null;
                                 String Mail = "";
                                 for(int i=0;i<approvers.size();i++){
                                  System.out.println("App ID:"+approvers.get(i).getApproverId());
                                  if(Integer.parseInt(approvers.get(i).getApproverId()) !=5513 && Integer.parseInt(approvers.get(i).getApproverId()) !=1002 && Integer.parseInt(approvers.get(i).getApproverId()) !=1470){
                                       System.out.println("Appzz:"+approvers.get(i).getApproverId());
                                      approversEmail=commonDao.getApproverEmail(approvers.get(i).getApproverId());
                                      if(i==0)
                                     Mail += approversEmail;
                                     else
                                     Mail += ","+approversEmail;
                                  }
                             }
                                toAddress[1]=Mail; 
                             //toAddress[1] = getCommonApproverEmail(approvers);
                                }

                        }
                        if(approvalData.getPrevious_status().equals("a")) {
                            mailHeaderRequestor = "Ticket booked for "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+"<br><br>";
                            requestorFlag = true;
                        }
//                        if(approvalData.getPrevious_status().equals("t")) {
//                            mailHeaderRequestor = "Money deposited for "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+"<br><br>";
//                            requestorFlag = true;
//                        }
                        if(approvalData.getPrevious_status().equals("t")) {
                             if(travelData.getTravelType().equals("I")) {
                       //mailHeaderRequestor = "Money deposited for "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+"<br><br>";
                                 if(!"0".equals(travelData.getApprovedAdvance())){
                         mailHeaderRequestor = "We are pleased inform you that, Travel advance of "+travelData.getCurrencyCode()+" "+travelData.getApprovedAdvance()+" has been arranged to forex agent of Hinduja Tech against Travel plan No."+travelData.getTpReferenceId();
                                 }else{
                                 mailHeaderRequestor = "Forex has not been issued for Travel plan No."+travelData.getTpReferenceId();;
                                 }
                            requestorFlag = true;
                          }else if(travelData.getTravelType().equals("D")){
                              if(!"0".equals(travelData.getApprovedAdvance())){
                               mailHeaderRequestor = "We are pleased inform you that, Travel advance of "+travelData.getCurrencyCode()+" "+travelData.getApprovedAdvance()+" has been deposited on "+travelData.getTrApprovedDate()+" by Hinduja Tech in your bank account registered with payroll data base against Travel plan No."+travelData.getTpReferenceId();
                              }else{
                              mailHeaderRequestor = "Money has not been deposited for Travel plan No."+travelData.getTpReferenceId();
                              }
                               requestorFlag = true;
                          }else{
                              mailHeaderRequestor = "We are pleased inform you that, Travel advance of "+travelData.getCurrencyCode()+" "+travelData.getApprovedAdvance()+" has been deposited on "+travelData.getTrApprovedDate()+" by Hinduja Tech in your bank account registered with payroll data base against Travel plan No."+travelData.getTpReferenceId();
                              requestorFlag = true;
                          } 
                         //mailHeaderRequestor = "Money deposited for "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+"<br><br>";
                        // mailHeaderRequestor = "We are pleased inform you that, Travel advance of "+travelData.getCurrencyCode()+" "+travelData.getApprovedAdvance()+" has been deposited on "+travelData.getTrApprovedDate()+" by Hinduja Tech in your bank account registered with payroll data base against Travel plan No."+travelData.getTpReferenceId();
                          //  requestorFlag = true;
                        }
                    }

                }

                String mailSubject = "TravelID : "+travelData.getTpReferenceId()+" - "+travelData.getTravel_text()+" Request";
                ccAddress = travelData.getEmail_address();

                if(approvalData.getPrevious_status().equals("r") || approvalData.getPrevious_status().equals("b") || approvalData.getPrevious_status().equals("m") || ( approvalData.getPrevious_status().equals("f") && approvalData.getStatus().equals("c") )) {
                    FromName = CommonFunctions.getRole(approvalData.getPrevious_status());
                    ToName = CommonFunctions.getRole(approvalData.getStatus());
                    if(approvalData.getPrevious_status().equals("b")){
                        ToName = " Employee";
                        mailHeader = FromName+" has approved the "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+" and forwarded to Business operations((krishnan.vaithiswaran@hindujatech.com - karthik.ramachandran@hindujatech.com) for their approval.<br><br>";
                    }else{
                        ToName = CommonFunctions.getRole(approvalData.getStatus());
                        mailHeader = FromName+" has sent a "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+" for your approval.<br><br>";
                    }
                    
                    
                }
                if((approvalData.getPrevious_status().equals("f") && !approvalData.getStatus().equals("c") ) || approvalData.getPrevious_status().equals("c") ) {
                    FromName = CommonFunctions.getRole(approvalData.getPrevious_status());
                    ToName = CommonFunctions.getRole(approvalData.getStatus());
                    mailHeader = FromName+" has approved the "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+"<br><br>";
                    mailHeaderRequestor = "Business Operations has approved your "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+"<br><br>";
                    requestorFlag = true;
                }
                if(approvalData.getPrevious_status().equals("q")) {
                    ToName = CommonFunctions.getRole(approvalData.getStatus());
                    mailHeader = "A New "+travelData.getTravel_text()+" request has been raised. Please find the details below.<br><br>";
                }
                for(int k=0;k<toAddress.length;k++) {

                    if(toAddress[k] != null) {
                        String mailMessage = "";
                        mailMessage += "Dear "+ToName+",<br><br>";
                        mailMessage += mailHeader;
                        mailMessage += mailContent;
                        mailMessage += mailFooter;
                        boolean smtpMail = mailObj.smtpMail(toAddress[k], mailSubject, mailMessage, ccAddress);
                    }
                }

                if(requestorFlag) {
                    String mailMessage = "";
                    mailMessage += "Dear "+travelData.getRequestor()+",<br><br>";
                    mailMessage += mailHeaderRequestor;
                    mailMessage += mailFooter;
                    if(approvalData.getPrevious_status().equals("t")){
                    boolean smtpMail = mailObj.smtpMail(ccAddress, mailSubject, mailMessage, CommonConfigurations.TREASURY_EMAIL);
                    }else{
                    boolean smtpMail = mailObj.smtpMail(ccAddress, mailSubject, mailMessage, "");
                    }
                }

            } else if(approvalData.getMail_status().equals("Reject")) {
                if(approvalData.getActionName().equals("c")) {                   
                    approvalData.setActionName("f");
                    FromName = "Finance in-Charge";
                } else {
                    FromName = CommonFunctions.getRole(approvalData.getActionName());
                }
                String mailSubject = "TravelID : "+travelData.getTpReferenceId()+" - "+travelData.getTravel_text()+" Request Send Back";
                mailHeader = FromName+" has sent back the "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+"<br><br>";
                String workflow[] = travelData.getWorkFlow().split(",");
                Map address = new HashMap();
                int addressCount = 0;
                if(!approvalData.getActionName().equals("b")&&!approvalData.getActionName().equals("f"))
                    travelData.setRequestorPracticeGroup("0");
                List<CommonDto> approvers = new ArrayList();
                for(int j=0;j<workflow.length;j++) {
                     System.out.print("jjjj"+workflow[j]);
                    if(!workflow[j].equals("r") && !workflow[j].equals("b")) {
                        travelData.setNextLevel(approvalData.getActionName());
                        if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
                            approvers = commonDao.getApprovers(travelData);
                        }
                        for(int k=0;k<approvers.size();k++) {
                            if(Integer.parseInt(approvers.get(k).getApproverId()) !=259 && Integer.parseInt(approvers.get(k).getApproverId()) !=5513 && Integer.parseInt(approvers.get(k).getApproverId()) !=1002 && Integer.parseInt(approvers.get(k).getApproverId()) !=1470 ){
                            String approverEmail = commonDao.getApproverEmail(approvers.get(k).getApproverId());
                            if(!address.containsValue(approverEmail)) {
                                address.put(addressCount,approverEmail);
                                addressCount++;
                            }
                          }
                       }
                    }
                     else if (workflow[j].equals("b")) {
                        String approver=commonDao.getApproverEmail(travelData.getChangeBuh());
                        if(!address.containsValue(approver)){
                            address.put(addressCount,approver);
                            addressCount++;
                        }
                    }
                   
                    else {
                        String approverEmail = commonDao.getApproverEmail(travelData.getManager());
                        if(!address.containsValue(approverEmail)) {
                            address.put(addressCount,approverEmail);
                            addressCount++;
                        }
                    }

                    if(workflow[j].equals(approvalData.getActionName())) {
                        break;
                    }
                }
                String cc = "";
                for(int i=0;i<address.size();i++) {
                    if(i==0)
                        cc += address.get(i);
                    else
                        cc += ","+address.get(i);
                }
                System.out.println("ccAddress"+cc);
                String mailMessage = "";
                mailMessage += "Dear "+travelData.getRequestor()+",<br><br>";
                mailMessage += mailHeader;
                mailMessage += mailContent;
                mailMessage += mailFooter;
                System.out.println("mailMessage===========>"+mailMessage);
                boolean smtpMail = mailObj.smtpMail(travelData.getEmail_address(), mailSubject, mailMessage, cc);

            } else if(approvalData.getMail_status().equals("ReSubmit") || approvalData.getMail_status().equals("Cancel")) {
                String mailSubject = "TravelID : "+travelData.getTpReferenceId()+" - "+travelData.getTravel_text()+" Request";
                if(approvalData.getMail_status().equals("ReSubmit")) {
                    mailHeader = travelData.getEmployeeName()+" has made changes in "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+". Please find the details below.<br><br>";
                } else {
                    mailHeader = travelData.getEmployeeName()+" has cancelled his/her "+travelData.getTravel_text()+" request - "+travelData.getTpReferenceId()+". Please find the details below.<br><br>";
                    mailContent += "Cancelled remarks : "+approvalData.getCancel_remarks()+"<br><br>";
                }
                String workflow[] = travelData.getWorkFlow().split(",");
                Map address = new HashMap();
                int addressCount = 0;
                String[] arr = new String[2];
                List<CommonDto> approvers = new ArrayList();
                for(int j=0;j<workflow.length;j++) {
                    if(!workflow[j].equals("r") && !workflow[j].equals("b")) {

                            travelData.setRequestorPracticeGroup("0");

                        if(!workflow[j].equals("a")) {
                            arr[0] = workflow[j];
                        } else {
                            if(travelData.getStatus().equals("a") && ( ( travelData.getAdmin_action_required() != null && travelData.getAdmin_action_required().equals("Y") ) || travelData.getTravelType().equals("L") ) ) {
                                arr[0] = "a";
                            }
                            if(travelData.getStatus().equals("a") && travelData.getAdvance_required()!= null && !travelData.getAdvance_required().equals("") && !travelData.getAdvance_required().equals("0") && !travelData.getTravelType().equals("L") ) {
                                arr[1] = "t";
                            }
                        }

                        for(int x=0;x<arr.length;x++) {
                            if(arr[x] != null) {
                                travelData.setNextLevel(arr[x]);
                                if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
                                    approvers = commonDao.getApprovers(travelData);
                                }
                                for(int k=0;k<approvers.size();k++) {
                                    String approverEmail = commonDao.getApproverEmail(approvers.get(k).getApproverId());
                                    if(!address.containsValue(approverEmail)) {
                                        address.put(addressCount,approverEmail);
                                        addressCount++;
                                    }
                                }
                            }
                        }
                    }
                    if(workflow[j].equals("b"))
                    {
                       String approver = commonDao.getApproverEmail(travelData.getManager());
                        if(!address.containsValue(approver)) {
                            address.put(addressCount,approver);
                            addressCount++;
                        }
                    }
                    else {
                        String approverEmail = commonDao.getApproverEmail(travelData.getManager());
                        if(!address.containsValue(approverEmail)) {
                            address.put(addressCount,approverEmail);
                            addressCount++;
                        }
                    }

                    if(workflow[j].equals(travelData.getStatus())) {
                        break;
                    }
                }
                String to = "";
                for(int i=0;i<address.size();i++) {
                    if(i==0)
                        to += address.get(i);
                    else
                        to += ","+address.get(i);
                }
                System.out.println("ccAddress"+to);
                String mailMessage = "";
                if(approvalData.getMail_status().equals("ReSubmit")){
                    String greeting_name = "";
                    if("r".equals(travelData.getStatus())){
                        greeting_name = "RM";
                    }else if("b".equals(travelData.getStatus())){
                        greeting_name = "BUH";
                    }else if("f".equals(travelData.getStatus())){
                        greeting_name = "Business Operations";
                    }else if("a".equals(travelData.getStatus())){
                        greeting_name = "Admin";
                    }else{
                        greeting_name = travelData.getRequestor();
                    }
                    mailMessage += "Dear "+greeting_name+",<br><br>";
                }
                else
                    mailMessage += "Dear All,<br><br>";
                mailMessage += mailHeader;
                mailMessage += mailContent;
                mailMessage += mailFooter;
                if(approvalData.getMail_status().equals("ReSubmit")) {
                    boolean smtpMail = mailObj.smtpMail(travelData.getEmail_address(), mailSubject, mailMessage, to);
                } else {
                    boolean smtpMail = mailObj.smtpMail(to, mailSubject, mailMessage, travelData.getEmail_address());
                }

            }
        }
    }



    public String getCommonApproverEmail(List<CommonDto> approvers)throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
        String approverEmail = null;
        String toEmail = "";
        for(int j=0;j<approvers.size();j++) {
            approverEmail = commonDao.getApproverEmail(approvers.get(j).getApproverId());
            if(j==0)
                toEmail += approverEmail;
            else
                toEmail += ","+approverEmail;
        }
        return toEmail;
    }

}
