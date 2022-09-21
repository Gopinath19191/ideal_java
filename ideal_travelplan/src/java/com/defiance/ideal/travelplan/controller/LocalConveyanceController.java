
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.controller;

import com.defiance.ideal.travelplan.dao.LocalConveyanceDaoImpl;
import com.defiance.ideal.travelplan.dto.CommonDto;
import com.defiance.ideal.travelplan.dto.LocalConveyanceDto;
import com.defiance.ideal.travelplan.utils.CommonConfigurations;
import com.defiance.ideal.travelplan.utils.CommonFunctions;
import com.defiance.ideal.travelplan.utils.SendMail;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.defiance.ideal.travelplan.dao.CommonDaoImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 14578
 */
public class LocalConveyanceController extends MultiActionController {

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
    private ModelAndView mv;

    public ModelAndView requestList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            mv = new ModelAndView("/localConveyance/list");
            System.out.println("Hello to local Conveyance");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView addNewLocalConveyance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        try {
            HttpSession session = request.getSession();
            String currEmpStatus = (String) session.getAttribute("currEmpStatus");
            String employeeId = (String) session.getAttribute("employeeId");
            String billable_list = CommonConfigurations.BILLABLELIST;
            String countryId = CommonConfigurations.COUNTRYID;
            LocalConveyanceDto lcViewDetails = null;
            List<CommonDto> attachmentData = new ArrayList<CommonDto>();
            CommonDto travelData = new CommonDto();
            List<CommonDto> workflowList = new ArrayList();
            List<LocalConveyanceDto> historyDetails = null;

            CommonDaoImpl commonDao = (CommonDaoImpl) getWebApplicationContext().getBean("CommonDao");
            LocalConveyanceDaoImpl daoImplObj = (LocalConveyanceDaoImpl) getWebApplicationContext().getBean("LocalConveyanceDao");

            LocalConveyanceDto empDetails = daoImplObj.getEmpDetails(employeeId);
            List<LocalConveyanceDto> billableList = (List<LocalConveyanceDto>) daoImplObj.getConfigValues(billable_list);
            List<LocalConveyanceDto> projectList = (List<LocalConveyanceDto>) daoImplObj.getProjectList(employeeId);
            List<LocalConveyanceDto> cityList = (List<LocalConveyanceDto>) daoImplObj.getCityList(countryId);
            String uniqueId = request.getParameter("request_id");
            mv = new ModelAndView("/localConveyance/newLocalConveyance");
            mv.addObject("empDetails", empDetails);
            mv.addObject("billableList", billableList);
            mv.addObject("projectList", projectList);
            mv.addObject("cityList", cityList);
            mv.addObject("uniqueId", uniqueId);
            if (currEmpStatus != null && currEmpStatus.equals(CommonConfigurations.indiaEmpStatus)) {
                lcViewDetails = uniqueId != null ? daoImplObj.getLCViewDetails(uniqueId) : null;
                travelData = uniqueId != null ? commonDao.getTravelData(uniqueId) : null;
            }

            String cityId = "";
            if (lcViewDetails == null) {
                lcViewDetails = new LocalConveyanceDto();
                cityId = empDetails.getCityId();
                lcViewDetails.setCity(cityId);
            } else if (lcViewDetails != null) {
                attachmentData = commonDao.getTravelAttachment(uniqueId);
                mv.addObject("lcViewDetails", lcViewDetails);
                mv.addObject("attachmentData", attachmentData);
                cityId = lcViewDetails.getCity();
            }
            List<LocalConveyanceDto> travelPointsList = (List<LocalConveyanceDto>) daoImplObj.getTravelPoints(cityId);

            if (travelData != null && travelData.getWorkFlow() != null) {
                String workflow[] = travelData.getWorkFlow().split(",");
                System.out.println("workflow.length" + workflow.length);

                for (int i = 0; i < workflow.length; i++) {
                    CommonDto obj = new CommonDto();
                    obj.setStatus(workflow[i]);

                    if (workflow[i].equals("r")) {
                        obj.setApprover("RM/PM");
                        if (!"00-00-0000".equals(travelData.getRm_approved_date())) {
                            obj.setApproved_date(travelData.getRm_approved_date());
                        } else {
                            obj.setApproved_date("");
                        }
                        if (travelData.getRm_approved_by() != null) {
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getRm_approved_by()));
                        } else {
                            obj.setApproved_by("");
                        }
                        obj.setApproved_status(travelData.getRm_approved_status());
                        obj.setApprover_remarks(travelData.getRm_remarks());
                        workflowList.add(obj);
                    }
                    if (workflow[i].equals("b")) {
                        obj.setApprover("BUH");
                        if (!"00-00-0000".equals(travelData.getBuh_approved_date())) {
                            obj.setApproved_date(travelData.getBuh_approved_date());
                        } else {
                            obj.setApproved_date("");
                        }
                        if (travelData.getBuh_approved_by() != null) {
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getBuh_approved_by()));
                        } else {
                            obj.setApproved_by("");
                        }
                        obj.setApproved_status(travelData.getBuh_approved_status());
                        obj.setApprover_remarks(travelData.getBuh_remarks());
                        workflowList.add(obj);
                    }
                    if (workflow[i].equals("m")) {
                        obj.setApprover("MD");
                        if (!travelData.getMd_approved_date().equals("00-00-0000")) {
                            obj.setApproved_date(travelData.getMd_approved_date());
                        } else {
                            obj.setApproved_date("");
                        }
                        if (travelData.getMd_approved_by() != null) {
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getMd_approved_by()));
                        } else {
                            obj.setApproved_by("");
                        }
                        obj.setApproved_status(travelData.getMd_approved_status());
                        obj.setApprover_remarks(travelData.getMd_remarks());
                        workflowList.add(obj);
                    }
                    if (workflow[i].equals("f")) {
                        obj.setApprover("Finance");
                        if (travelData.getCfo_action_required() != null && travelData.getCfo_action_required().equals("Y")) {
                            if (!"00-00-0000".equals(travelData.getCfo_approved_date())) {
                                obj.setApproved_date(travelData.getCfo_approved_date());
                            } else {
                                obj.setApproved_date("");
                            }
                            if (travelData.getCfo_approved_by() != null) {
                                obj.setApproved_by(commonDao.getEmployeeName(travelData.getCfo_approved_by()));
                            } else {
                                obj.setApproved_by("");
                            }
                            obj.setApproved_status(travelData.getCfo_approved_status());
                            obj.setApprover_remarks(travelData.getCfo_remarks());
                            workflowList.add(obj);
                        } else {
                            if (!"00-00-0000".equals(travelData.getFinance_approved_date())) {
                                obj.setApproved_date(travelData.getFinance_approved_date());
                            } else {
                                obj.setApproved_date("");
                            }
                            if (travelData.getFinance_approved_by() != null) {
                                obj.setApproved_by(commonDao.getEmployeeName(travelData.getFinance_approved_by()));
                            } else {
                                obj.setApproved_by("");
                            }
                            obj.setApproved_status(travelData.getFinance_approved_status());
                            obj.setApprover_remarks(travelData.getFinance_remarks());
                            workflowList.add(obj);
                        }
                    }
                    if (workflow[i].equals("a")) {
                        obj.setApprover("Admin");
                        if (travelData.getAdmin_approved_date() != null && travelData.getAdmin_approved_date().equals("00-00-0000")) {
                            obj.setApproved_date(travelData.getAdmin_approved_date());
                        } else {
                            obj.setApproved_date("");
                        }
                        if (travelData.getAdmin_approved_by() != null) {
                            obj.setApproved_by(commonDao.getEmployeeName(travelData.getAdmin_approved_by()));
                        } else {
                            obj.setApproved_by("");
                        }
                        obj.setApproved_status(travelData.getAdmin_approved_status());
                        obj.setApprover_remarks(travelData.getAdmin_remarks());
                        workflowList.add(obj);
                    }
                }
            }
            mv.addObject("travelPointsList", travelPointsList);
            mv.addObject("workflowList", workflowList);
            mv.addObject("system", currEmpStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView getCityTravelPoints(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.getWriter().println("<option value=''>--Select Point--</option>");
            if (request.getParameter("id") == null ? "" != null : !request.getParameter("id").equals("")) {
                for (LocalConveyanceDto dTO : ((LocalConveyanceDaoImpl) getWebApplicationContext().getBean("LocalConveyanceDao")).getTravelPoints((request.getParameter("id")))) {
                    response.getWriter().println("<option value='" + dTO.getAddressId() + "'>" + dTO.getTravelPoints() + "</option>");
                }
                response.getWriter().println("<option value='-3'>" + "Customer Office" + "</option>");
                response.getWriter().println("<option value='-2'>" + "Hotel" + "</option>");
                response.getWriter().println("<option value='-1'>" + "Others" + "</option>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ModelAndView requestorSubmit(HttpServletRequest request, HttpServletResponse response, LocalConveyanceDto dtoObject) throws Exception {
        ModelAndView mv = null;
        try {
            HttpSession session = request.getSession();

            // String localConveyanceKeyWord = CommonConfigurations.LOCALCONVEYANCE;
            CommonDto empDetails = new CommonDto();
            String employeeId = (String) session.getAttribute("employeeId");
            String currEmpStatus = (String) session.getAttribute("currEmpStatus");
            String moduleId = (String) session.getAttribute("moduleId");
            String btnName = request.getParameter("btnValue");
            boolean mailFlag = false;
            String tpLastInsertId = null;
            System.out.println("Btn Name=====" + btnName);
            String onwardpickupTime = request.getParameter("onwardPickupHrs") + ":" + request.getParameter("onwardPickupMins");
            String returnPickupTime = request.getParameter("returnPickupHrs") + ":" + request.getParameter("returnPickupMins");
            System.out.println("returnPickupTime===================" + returnPickupTime);
            System.out.println("returnPickUpDate===========********" + dtoObject.getReturnPickUpDate());
            dtoObject.setOnwardPickUpTime(onwardpickupTime);
            dtoObject.setReturnPickUpTime(returnPickupTime);
            CommonDaoImpl commonDaoIndia = (CommonDaoImpl) getWebApplicationContext().getBean("CommonDao");

            LocalConveyanceDaoImpl localConveyanceDaoIndia = (LocalConveyanceDaoImpl) getWebApplicationContext().getBean("LocalConveyanceDao");

            CommonDto commonFormData = new CommonDto();
            commonFormData.setEmployee_id(employeeId);
            dtoObject.setEmployee_id(employeeId);
            commonFormData.setSalesGroupId(CommonConfigurations.salesGroupId);
            if (dtoObject.getSubmitAction() != null) {
                if (dtoObject.getTpReferenceId() == null || dtoObject.getTpReferenceId().equals("")) {
                    String india_lastId = commonDaoIndia.getLastRequestId();
                    String tp_request_id = CommonFunctions.getRequestId(india_lastId, CommonConfigurations.LOCALCONVEYANCE, dtoObject.getReqDate());
                    dtoObject.setTpReferenceId(tp_request_id);
                    dtoObject.setMail_status("Submit");
                } else {
                    if (!dtoObject.getCurrentStatus().equals("o")) {
                        dtoObject.setMail_status("ReSubmit");
                    }
                }
                commonFormData.setSalesGroupId(CommonConfigurations.salesGroupId);
                empDetails = CommonFunctions.commonManipulationCode(commonDaoIndia.getEmpDetails(commonFormData), moduleId, employeeId, "q");

            }

            if (currEmpStatus != null && currEmpStatus.equals(CommonConfigurations.indiaEmpStatus)) {
                commonFormData.setSystem(CommonConfigurations.indiaEmpStatus);
                if (dtoObject.getSubmitAction() != null) {
                    CommonDto statusAndFlow = commonDaoIndia.getStatusFlowInfo(empDetails);
                    if (statusAndFlow != null) {
                        dtoObject.setWorkFlowId(statusAndFlow.getTpWorkFlowId());
                        dtoObject.setStatus(statusAndFlow.getStatus());
                        dtoObject.setPrevious_status("q");
                        mailFlag = true;
                    }
                } else if (dtoObject.getSaveAction() != null) {
                    dtoObject.setWorkFlowId("0");
                    dtoObject.setPrevious_status("");
                    dtoObject.setStatus("s");
                } else {
                    dtoObject.setPrevious_status("");
                    dtoObject.setStatus("x");
                    dtoObject.setMail_status("Cancel");
                    mailFlag = true;
                }
                if (dtoObject.getNewLCNumber() == null || dtoObject.getNewLCNumber().equals("")) {
                    tpLastInsertId = localConveyanceDaoIndia.insertLocalConveyance(dtoObject);
                    commonFormData.setMaster_id(tpLastInsertId);
                } else {
                    tpLastInsertId = dtoObject.getNewLCNumber();
                    localConveyanceDaoIndia.updateLocalConveyance(tpLastInsertId, dtoObject);
                    commonFormData.setMaster_id(tpLastInsertId);
                }
                commonDaoIndia.uploadFile(request, commonFormData);
                commonFormData.setTpId(tpLastInsertId);
            }
            if (mailFlag) {
                mailProcess(commonFormData, dtoObject);
            }
            mv = new ModelAndView("redirect:getDashBoardList.htm?page=1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView getLCViewDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        try {
//            System.out.println("Inside View PAge Controller");
            HttpSession session = request.getSession();
            String currEmpStatus = (String) session.getAttribute("currEmpStatus");
            String employeeId = (String) session.getAttribute("employeeId");
            String moduleId = (String) session.getAttribute("moduleId");
            String approveType = request.getParameter("approveType");
            String billable_list = CommonConfigurations.BILLABLELIST;
            String countryId = CommonConfigurations.COUNTRYID;
            LocalConveyanceDto lcViewDetails = null;

            CommonDto travelData = new CommonDto();
            List<CommonDto> workflowList = new ArrayList();

            LocalConveyanceDaoImpl daoImplObj = (LocalConveyanceDaoImpl) getWebApplicationContext().getBean("LocalConveyanceDao");
            CommonDaoImpl commonDao = (CommonDaoImpl) getWebApplicationContext().getBean("CommonDao");

            List<LocalConveyanceDto> billableList = (List<LocalConveyanceDto>) daoImplObj.getConfigValues(billable_list);
            List<LocalConveyanceDto> projectList = (List<LocalConveyanceDto>) daoImplObj.getProjectList(employeeId);
            List<LocalConveyanceDto> cityList = (List<LocalConveyanceDto>) daoImplObj.getCityList(countryId);

            String uniqueId = request.getParameter("request_id");
            if (currEmpStatus != null && currEmpStatus.equals(CommonConfigurations.indiaEmpStatus)) {
                lcViewDetails = daoImplObj.getLCViewDetails(uniqueId);
                travelData = commonDao.getTravelData(uniqueId);
            }

            List<LocalConveyanceDto> travelPoints = (List<LocalConveyanceDto>) daoImplObj.getTravelPoints(lcViewDetails.getCity());

            System.out.println("travelData.getWorkFlow()" + travelData.getWorkFlow());
            String workflow[] = travelData.getWorkFlow().split(",");
            System.out.println("workflow.length" + workflow.length);
            for (int i = 0; i < workflow.length; i++) {
                CommonDto obj = new CommonDto();
                obj.setStatus(workflow[i]);
                if (workflow[i].equals("r")) {
                    System.out.println("RM Approved Date : " + travelData.getRm_approved_date());
                    obj.setApprover("RM/PM");
                    if (travelData.getRm_approved_date() != null && !travelData.getRm_approved_date().equals("00-00-0000")) {
                        obj.setApproved_date(travelData.getRm_approved_date());
                    } else {
                        obj.setApproved_date("");
                    }

                    if (travelData.getRm_approved_by() != null) {
                        obj.setApproved_by(commonDao.getEmployeeName(travelData.getRm_approved_by()));
                    } else {
                        obj.setApproved_by("");
                    }
                    obj.setApproved_status(travelData.getRm_approved_status());
                    obj.setApprover_remarks(travelData.getRm_remarks());
                    workflowList.add(obj);
                }
                if (workflow[i].equals("b")) {
                    obj.setApprover("BUH");
                    obj.setApproved_date(travelData.getBuh_approved_date());
                    if (travelData.getBuh_approved_by() != null) {
                        obj.setApproved_by(commonDao.getEmployeeName(travelData.getBuh_approved_by()));
                    } else {
                        obj.setApproved_by("");
                    }
                    obj.setApproved_status(travelData.getBuh_approved_status());
                    obj.setApprover_remarks(travelData.getBuh_remarks());
                    workflowList.add(obj);
                }
                if (workflow[i].equals("m")) {
                    obj.setApprover("MD");
                    obj.setApproved_date(travelData.getMd_approved_date());
                    if (travelData.getMd_approved_by() != null) {
                        obj.setApproved_by(commonDao.getEmployeeName(travelData.getMd_approved_by()));
                    } else {
                        obj.setApproved_by("");
                    }
                    obj.setApproved_status(travelData.getMd_approved_status());
                    obj.setApprover_remarks(travelData.getMd_remarks());
                    workflowList.add(obj);
                }
                if (workflow[i].equals("f")) {
                    obj.setApprover("Finance");
                    obj.setApproved_date(travelData.getFinance_approved_date());
                    if (travelData.getFinance_approved_by() != null) {
                        obj.setApproved_by(commonDao.getEmployeeName(travelData.getFinance_approved_by()));
                    } else {
                        obj.setApproved_by("");
                    }
                    obj.setApproved_status(travelData.getFinance_approved_status());
                    obj.setApprover_remarks(travelData.getFinance_remarks());
                    workflowList.add(obj);
                }
                if (workflow[i].equals("a")) {
                    obj.setApprover("Admin");
                    obj.setApproved_date(travelData.getAdmin_approved_date());
                    if (travelData.getAdmin_approved_by() != null) {
                        obj.setApproved_by(commonDao.getEmployeeName(travelData.getAdmin_approved_by()));
                    } else {
                        obj.setApproved_by("");
                    }
                    obj.setApproved_status(travelData.getAdmin_approved_status());
                    obj.setApprover_remarks(travelData.getAdmin_remarks());
                    workflowList.add(obj);
                }
            }
            if (travelData.getCfo_action_required() != null && travelData.getCfo_action_required().equals("Y")) {
                CommonDto obj = new CommonDto();
                obj.setApprover("CFO");
                obj.setApproved_date(travelData.getCfo_approved_date());
                if (travelData.getCfo_approved_by() != null) {
                    obj.setApproved_by(commonDao.getEmployeeName(travelData.getCfo_approved_by()));
                } else {
                    obj.setApproved_by("");
                }
                obj.setApproved_status(travelData.getCfo_approved_status());
                obj.setApprover_remarks(travelData.getCfo_remarks());
                workflowList.add(obj);
            }

            mv = new ModelAndView("/localConveyance/viewDetails");
            mv.addObject("lcViewDetails", lcViewDetails);
            mv.addObject("billableList", billableList);
            mv.addObject("projectList", projectList);
            mv.addObject("cityList", cityList);
            mv.addObject("travelPoints", travelPoints);
            mv.addObject("uniqueId", uniqueId);
            mv.addObject("moduleId", moduleId);
            mv.addObject("approveType", approveType);
            mv.addObject("workflowList", workflowList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView getHistoryDetails(HttpServletRequest request, HttpServletResponse response, LocalConveyanceDto formData) throws Exception {

        HttpSession session = request.getSession();
        LocalConveyanceDaoImpl daoImplObj = (LocalConveyanceDaoImpl) getWebApplicationContext().getBean("LocalConveyanceDao");
        String tpUniqueId = request.getParameter("newLCNumber");
        List<LocalConveyanceDto> historyDetails = daoImplObj.getHistoryDetails(tpUniqueId);
        mv = new ModelAndView("/localConveyance/viewHistory");
        mv.addObject("historyDetails", historyDetails);
        return mv;
    }

    public Object getBean(String beanName) {
        Object o = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            return ctx.getBean(beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public void mailProcess(CommonDto interFormData, LocalConveyanceDto approvalData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDao = (CommonDaoImpl) ctx.getBean("CommonDao");
        CommonDto travelData = new CommonDto();
        SendMail mailObj = null;
        List<CommonDto> connectionRes = new ArrayList();
        connectionRes = (ArrayList<CommonDto>) commonDao.getConfigValueData("10");
        String con_username = connectionRes.get(0).getConfigValue();
        String con_password = connectionRes.get(1).getConfigValue();
        String con_host = connectionRes.get(2).getConfigValue();
        String con_port = connectionRes.get(3).getConfigValue();
        String ToName = null;
        String mailHeader = null;
        String mailFooter = "";
        String ccAddress = null;
        String toAddress = "";
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

        System.out.println("TPPPPPPPPPPPPPPPPIDDDDDDDDD" + interFormData.getTpId());
        if (interFormData.getSystem() != null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)) {
            travelData = commonDao.getTravelDetails(interFormData.getTpId());
        }

        if (travelData != null) {

            if (travelData.getCustomer_id() != null && !travelData.getCustomer_id().equals("") && !travelData.getCustomer_id().equals("-1")) {
                travelData.setCustomerName(commonDao.getCustomerName(travelData.getCustomer_id()));
            } else if (travelData.getCustomer_others() != null && !travelData.getCustomer_others().equals("")) {
                travelData.setCustomerName(travelData.getCustomer_others());
            } else {
                travelData.setCustomerName("--");
            }

            if (travelData.getProject_id() != null && !travelData.getProject_id().equals("") && !travelData.getProject_id().equals("-1")) {
                travelData.setProjectName(commonDao.getProjectName(travelData.getProject_id()));
            } else if (!travelData.getProject_others().equals("")) {
                travelData.setProjectName(travelData.getProject_others());
            } else {
                travelData.setProjectName("--");
            }

            mailContent += "Requestor Name/Id : " + travelData.getEmployeeName() + "<br>";
            mailContent += "Request Type : " + travelData.getTravel_text() + "<br>";
            mailContent += "Request Id : " + travelData.getTpReferenceId() + "<br>";
            mailContent += "Project Travel : " + travelData.getTravel_billable() + "<br>";
            mailContent += "Project Name : " + travelData.getProjectName() + "<br>";
            mailContent += "Customer Name : " + travelData.getCustomerName() + "<br>";
            mailContent += "Client Reimbursable : " + travelData.getClient_reimbursable() + "<br>";

            if (approvalData.getMail_status().equals("Submit")) {
                if (approvalData.getStatus().equals("r")) {
                    toAddress = commonDao.getApproverEmail(travelData.getManager());
                } else {
                    List<CommonDto> approvers = new ArrayList();
                    Map address = new HashMap();
                    int addressCount = 0;
                    if (!approvalData.getStatus().equals("b")) {
                        travelData.setRequestorPracticeGroup("0");
                    }
                    travelData.setNextLevel(approvalData.getStatus());
                    if (interFormData.getSystem() != null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)) {
                        approvers = commonDao.getApprovers(travelData);
                    }
                    for (int k = 0; k < approvers.size(); k++) {
                        String approverEmail = commonDao.getApproverEmail(approvers.get(k).getApproverId());
                        if (!address.containsValue(approverEmail)) {
                            address.put(addressCount, approverEmail);
                            addressCount++;
                        }
                    }
                    toAddress = "";
                    for (int i = 0; i < address.size(); i++) {
                        if (i == 0) {
                            toAddress += address.get(i);
                        } else {
                            toAddress += "," + address.get(i);
                        }
                    }
                }
                String mailSubject = "TravelID : " + travelData.getTpReferenceId() + " - " + travelData.getTravel_text() + " Request";
                ccAddress = travelData.getEmail_address();
                ToName = CommonFunctions.getRole(approvalData.getStatus());
                mailHeader = "A New " + travelData.getTravel_text() + " request has been raised. Please find the details below.<br><br>";
                String mailMessage = "";
                mailMessage += "Dear " + ToName + ",<br><br>";
                mailMessage += mailHeader;
                mailMessage += mailContent;
                mailMessage += mailFooter;
                System.out.println("mailMessage===========>" + mailMessage);
                boolean smtpMail = mailObj.smtpMail(toAddress, mailSubject, mailMessage, ccAddress);

            } else if (approvalData.getMail_status().equals("ReSubmit") || approvalData.getMail_status().equals("Cancel")) {

                String mailSubject = "TravelID : " + travelData.getTpReferenceId() + " - " + travelData.getTravel_text() + " Request";
                if (approvalData.getMail_status().equals("ReSubmit")) {
                    mailHeader = travelData.getEmployeeName() + " has made changes in " + travelData.getTravel_text() + " request - " + travelData.getTpReferenceId() + ". Please find the details below.<br><br>";
                } else {
                    mailHeader = travelData.getEmployeeName() + " has cancelled his/her " + travelData.getTravel_text() + " request - " + travelData.getTpReferenceId() + ". Please find the details below.<br><br>";
                    mailContent += "Cancelled remarks : " + approvalData.getCancel_remarks() + "<br><br>";
                }
                String workflow[] = travelData.getWorkFlow().split(",");
                Map address = new HashMap();
                int addressCount = 0;
                List<CommonDto> approvers = new ArrayList();
                for (int j = 0; j < workflow.length; j++) {
                    if (!workflow[j].equals("r")) {

                        if (!workflow[j].equals("b")) {
                            travelData.setRequestorPracticeGroup("0");
                        }

                        travelData.setNextLevel(workflow[j]);
                        if (interFormData.getSystem() != null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)) {
                            approvers = commonDao.getApprovers(travelData);
                        }
                        for (int k = 0; k < approvers.size(); k++) {
                            String approverEmail = commonDao.getApproverEmail(approvers.get(k).getApproverId());
                            if (!address.containsValue(approverEmail)) {
                                address.put(addressCount, approverEmail);
                                addressCount++;
                            }
                        }
                    } else {
                        String approverEmail = commonDao.getApproverEmail(travelData.getManager());
                        if (!address.containsValue(approverEmail)) {
                            address.put(addressCount, approverEmail);
                            addressCount++;
                        }
                    }

                    if (workflow[j].equals(travelData.getStatus())) {
                        break;
                    }
                }
                String to = "";
                for (int i = 0; i < address.size(); i++) {
                    if (i == 0) {
                        to += address.get(i);
                    } else {
                        to += "," + address.get(i);
                    }
                }
                System.out.println("ccAddress" + to);
                String mailMessage = "";
                if (!approvalData.getMail_status().equals("ReSubmit")) {
                    mailMessage += "Dear " + travelData.getRequestor() + ",<br><br>";
                } else {
                    mailMessage += "Dear All,<br><br>";
                }
                mailMessage += mailHeader;
                mailMessage += mailContent;
                mailMessage += mailFooter;
                if (approvalData.getMail_status().equals("ReSubmit")) {
                    boolean smtpMail = mailObj.smtpMail(travelData.getEmail_address(), mailSubject, mailMessage, to);
                } else {
                    boolean smtpMail = mailObj.smtpMail(to, mailSubject, mailMessage, travelData.getEmail_address());
                }
            }
        }
    }
}
