/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.approval.controller;

import com.defiance.ideal.exitMgmt.approval.dto.ApprovalDTO;
import com.defiance.ideal.exitMgmt.approval.services.ApprovalService;
import com.defiance.ideal.exitMgmt.approval.services.ApprovalServiceImpl;
import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;
import com.defiance.ideal.exitMgmt.employee.services.ExitEmployeeServiceImpl;
import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;
import com.defiance.ideal.exitMgmt.utils.CommonConfigurations;
import com.defiance.ideal.exitMgmt.utils.CommonFunctions;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14583
 */
public class ApprovalController extends MultiActionController {
HttpSession session=null;
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    ModelAndView mv = null;

    public ModelAndView listRegnSubmittedEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String moduleId = request.getParameter("moduleId");
        String approveType = request.getParameter("approveType");
        System.out.println("menu module Id ::::" + moduleId);
        LoginDTO sessionObj = this.getSessionValues(request);
        List<EmployeeDTO> regnEmpList = (List<EmployeeDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRegnEmpList(sessionObj.getEmpId(), moduleId,approveType);
        CommonFunctions.getMenuIdInRequest(request, response);
        mv = new ModelAndView("approval/exitEmployeeList");
        mv.addObject("regnEmpList", regnEmpList);
		mv.addObject("approveType", approveType);
        mv.addObject("moduleId", moduleId);
        return mv;
    }
    
    public ModelAndView showEmpInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApprovalDTO rmActionData = null;
            System.out.println("showEmpInfo 111"+request.getParameter("empId"));
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
        List<String> companyHolidays = (List) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getCompanyHolidays(employeeDetails);
        System.out.println("::::::::::::::::" + companyHolidays.size());
        ApprovalDTO fileData = ((ApprovalService) this.getBean("ApprovalService")).getFile(Integer.parseInt(employeeDetails.getExitEmpId()), CommonConfigurations.EXIT_MODULE_CODE);
        if (employeeDetails.getExitEmpId() != null) {
            rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
        }
        mv = new ModelAndView("approval/exitMgmtRM");
        mv.addObject("fileDetails", fileData);
        mv.addObject("cmpHolidays", companyHolidays);
        mv.addObject("exitEmpInfo", employeeDetails);
        mv.addObject("statusList", CommonConfigurations.statusList);
        mv.addObject("noticeWaiverValueList", CommonConfigurations.noticeWaiverValueList);
        if (rmActionData != null) {
            System.out.println("rmActionData::::::::" + rmActionData.getLastWorkingDate());
            mv.addObject("rmActionData", rmActionData);
        }
        return mv;
    }

    public ModelAndView showFinanceClearnace(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String moduleId = request.getParameter("userModuleId");
            System.out.println("moduleiddddd" + moduleId);
            CommonFunctions.getSurrenderValueList(request, response);
            CommonFunctions.getMenuIdInRequest(request, response);
            EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
            ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
            ApprovalDTO finActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getFinActionData(employeeDetails.getExitEmpId());

            List<ApprovalDTO> finMultipleData = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getfinMultipleData(employeeDetails.getExitEmpId());
//            List<ApprovalDTO> finMultipleData = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).updatefinMultipleData(employeeDetails.getExitEmpId());


            mv = new ModelAndView("approval/exitMgmtFinance");
            mv.addObject("exitEmpInfo", employeeDetails);
            mv.addObject("finMultipleData", finMultipleData);
            mv.addObject("rmActionData", rmActionData);
            mv.addObject("finActionData", finActionData);
            mv.addObject("moduleId", moduleId);
            mv.addObject("monthList", CommonFunctions.getMonthList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView showNetworkClearnace(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String moduleId = request.getParameter("userModuleId");
            System.out.println("moduleiddddd" + moduleId);
            CommonFunctions.getSurrenderValueList(request, response);
            CommonFunctions.getMenuIdInRequest(request, response);
            EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
            ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
            ApprovalDTO nsActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getNSActionData(employeeDetails.getExitEmpId());
            mv = new ModelAndView("approval/exitMgmtNS");
            mv.addObject("exitEmpInfo", employeeDetails);
            mv.addObject("rmActionData", rmActionData);
            mv.addObject("nsActionData", nsActionData);
            mv.addObject("moduleId", moduleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView showRmClearnace(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String moduleId = request.getParameter("userModuleId");
            System.out.println("moduleiddddd" + moduleId);
            CommonFunctions.getSurrenderValueList(request, response);
            CommonFunctions.getMenuIdInRequest(request, response);
            EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
            String Reportees_count = (String) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeReporteesCount(request.getParameter("empId"));
            ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
//            ApprovalDTO rmClrData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmClrData(employeeDetails.getExitEmpId());
            mv = new ModelAndView("approval/exitMgmtRMClr");
            mv.addObject("exitEmpInfo", employeeDetails);
            mv.addObject("Reportees_count", Reportees_count);
            mv.addObject("rmActionData", rmActionData);
//            mv.addObject("rmClrData", rmClrData);
            mv.addObject("moduleId", moduleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView showAdminClearnace(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String moduleId = request.getParameter("userModuleId");
            System.out.println("moduleiddddd" + moduleId);
            CommonFunctions.getSurrenderValueList(request, response);
            CommonFunctions.getMenuIdInRequest(request, response);
            EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
            ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
            ApprovalDTO adActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getAdminActionData(employeeDetails.getExitEmpId());
            mv = new ModelAndView("approval/exitMgmtAdmin");
            mv.addObject("exitEmpInfo", employeeDetails);
            mv.addObject("rmActionData", rmActionData);
            mv.addObject("adActionData", adActionData);
            mv.addObject("moduleId", moduleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView showHrClearnace(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String moduleId = request.getParameter("userModuleId");
            System.out.println("moduleiddddd" + moduleId);
            CommonFunctions.getSurrenderValueList(request, response);
            CommonFunctions.getMenuIdInRequest(request, response);
            EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
            ApprovalDTO fileData = ((ApprovalService) this.getBean("ApprovalService")).getFile(Integer.parseInt(employeeDetails.getExitEmpId()), CommonConfigurations.EXIT_MODULE_CODE);
            ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
            ApprovalDTO finActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getFinActionData(employeeDetails.getExitEmpId());
            ApprovalDTO nsActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getNSActionData(employeeDetails.getExitEmpId());
            ApprovalDTO adActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getAdminActionData(employeeDetails.getExitEmpId());
            ApprovalDTO hrActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getHrActionData(employeeDetails.getExitEmpId());
//            ApprovalDTO rmClrData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmClrData(employeeDetails.getExitEmpId());
            List<ApprovalDTO> finMultipleData = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getfinMultipleData(employeeDetails.getExitEmpId());
            // To Show the employee Exit interiview to HR
            List<ApprovalDTO> surveyQuestions = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getSurveyQuestions(request.getParameter("empId"));
            List<ApprovalDTO> surveyAnswers = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getSurveyAnswers();
            List<ApprovalDTO> employmentStatus= (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getEmploymentStatus();
            mv = new ModelAndView("approval/exitMgmtHR");
            mv.addObject("exitEmpInfo", employeeDetails);
            mv.addObject("fileDetails", fileData);
            mv.addObject("rmActionData", rmActionData);
            mv.addObject("finActionData", finActionData);
            mv.addObject("nsActionData", nsActionData);
            mv.addObject("adActionData", adActionData);
            mv.addObject("hrActionData", hrActionData);
            mv.addObject("finMultipleData", finMultipleData);
//            mv.addObject("rmClrData", rmClrData);
            mv.addObject("moduleId", moduleId);
            mv.addObject("employmentStatus", employmentStatus);
            mv.addObject("statusList", CommonConfigurations.statusList);
            mv.addObject("noticeWaiverValueList", CommonConfigurations.noticeWaiverValueList);
//            mv.addObject("exitTypeList", CommonConfigurations.exitType);
            mv.addObject("exitTypeList", CommonFunctions.getExitType());
            // Needed for survey
            mv.addObject("surveyQuestions", surveyQuestions);
            mv.addObject("surveyAnswers", surveyAnswers);
            mv.addObject("sliderMax", CommonConfigurations.SLIDER_MAX_VALUE);
            mv.addObject("sliderMin", CommonConfigurations.SLIDER_MIN_VALUE);
            mv.addObject("typeRadio", CommonConfigurations.SURVEY_ANSWER_RADIO);
            mv.addObject("typeSlider", CommonConfigurations.SURVEY_ANSWER_SLIDER);
            mv.addObject("typeFreeText", CommonConfigurations.SURVEY_ANSWER_FREE_TEXT);
            mv.addObject("typeMultiple", CommonConfigurations.SURVEY_ANSWER_MULTIPLE);
            mv.addObject("monthList", CommonFunctions.getMonthList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView saveRMApprovedEmpData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO rmFormData) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        rmFormData.setRmId(sessionObj.getEmpId());
        rmFormData.setModuleId(request.getParameter("moduleId"));
        int refId = Integer.parseInt(request.getParameter("exitEmpId"));
        System.out.println("refId #####"+refId);
         String empId = request.getParameter("empId");
        System.out.println("empId #####"+empId); 
        rmFormData.setResEmpId(empId);
        String reasonValue = request.getParameter("reasonValue");
        String fileName = "";
        MultipartFile uploadedFile = rmFormData.getFile();
        System.out.println("button name"+rmFormData.getButtonName());
        ApprovalService approvalService = (ApprovalServiceImpl) this.getBean("ApprovalService");
        CommonFunctions.fileUpload(uploadedFile, refId, "EXIT_FILE", CommonConfigurations.EXIT_MODULE_CODE, approvalService);
       ((ApprovalServiceImpl) this.getBean("ApprovalService")).saveRmData(rmFormData);
        mv = new ModelAndView("redirect:listRegnSubmittedEmp.htm");
        mv.addObject("moduleId", request.getParameter("moduleId"));
        mv.addObject("approveType", "pending");
        return mv;
    }

    public ModelAndView saveFinanceClearanceData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO finFormData) throws Exception {
        String newRow = request.getParameter("newRow");
        String moduleId = request.getParameter("moduleId");
        System.out.println("===== the new row value is === "+newRow+"moduleId:::"+moduleId);
        String[] newRowArray = newRow.split(",");
        String newRow1 = request.getParameter("newRow1");
        String[] primaryKey = newRow1.split(",");
        String[] other = new String[newRowArray.length];
        String[] otherAmt = new String[newRowArray.length];
        String[] otherRemarks = new String[newRowArray.length];
        String[] otherCategory = new String[newRowArray.length];

        for (int i = 0; i < newRowArray.length; i++) {
            other[i] = request.getParameter("otherLoans" + newRowArray[i]);
            otherAmt[i] = request.getParameter("otherLoanAmt" + newRowArray[i]);
            otherRemarks[i] = request.getParameter("otherLoanRemarks" + newRowArray[i]);
            otherCategory[i] = request.getParameter("category" + newRowArray[i]);
            System.out.println("=== The radio value is == " + request.getParameter("otherLoans" + newRowArray[i]));
            System.out.println("=== The amount value is == " + request.getParameter("otherLoanAmt" + newRowArray[i]));
            System.out.println("=== The remarks value is == " + request.getParameter("otherLoanRemarks" + newRowArray[i]));
        }

//        String newRow1 = request.getParameter("newRow1");
//        String[] newRowArray1 = newRow1.split(",");
//        System.out.println("------------------------------------" + newRow1);
//        String[] Primaryother = new String[newRowArray1.length];
//        String[] PrimaryotherAmt = new String[newRowArray1.length];
//        String[] PrimaryotherRemarks = new String[newRowArray1.length];
//        String[] PrimaryotherCategory = new String[newRowArray1.length];
//
//
//        for (int i = 0; i < newRowArray1.length; i++) {
//            Primaryother[i] = request.getParameter("PrimaryotherLoans" + newRowArray1[i]);
//            PrimaryotherAmt[i] = request.getParameter("PrimaryotherLoanAmt" + newRowArray1[i]);
//            PrimaryotherRemarks[i] = request.getParameter("PrimaryotherLoanRemarks" + newRowArray1[i]);
//            PrimaryotherCategory[i] = request.getParameter("PrimaryotherCategory" + newRowArray1[i]);
//            System.out.println("=== The PrimaryotherLoans value is == " + request.getParameter("PrimaryotherLoans" + newRowArray1[i]));
//            System.out.println("=== The PrimaryotherLoans value is == " + request.getParameter("PrimaryotherLoanAmt" + newRowArray1[i]));
//            System.out.println("=== The PrimaryotherLoans value is == " + request.getParameter("PrimaryotherLoanRemarks" + newRowArray1[i]));
//        }

        LoginDTO sessionObj = this.getSessionValues(request);
        finFormData.setFinApproverId(sessionObj.getEmpId());
        finFormData.setModuleId(request.getParameter("moduleId"));
        CommonFunctions.getSurrenderValueList(request, response);
        System.out.println(":::: In Controller::" + request.getParameter("moduleId"));
        
        ((ApprovalServiceImpl) this.getBean("ApprovalService")).saveFinData(finFormData);
        ((ApprovalServiceImpl) this.getBean("ApprovalService")).saveFinMultipleData(primaryKey,otherCategory, other, otherAmt, otherRemarks, finFormData);
//        ((ApprovalServiceImpl) this.getBean("ApprovalService")).deleteFinMultipleData(otherCategory, other, otherAmt, otherRemarks, finFormData);
//        ((ApprovalServiceImpl) this.getBean("ApprovalService")).updateFinMultipleData( finFormData,PrimaryotherCategory, Primaryother, PrimaryotherAmt, PrimaryotherRemarks,otherId);
        mv = new ModelAndView("redirect:listRegnSubmittedEmp.htm");
        mv.addObject("moduleId", request.getParameter("moduleId"));
        mv.addObject("approveType", "pending");
//        mv.addObject("otherCategory", request.getParameter("otherCategory"));
//        mv.addObject("otherLoans", request.getParameter("otherLoans"));
//        mv.addObject("otherLoanAmt", request.getParameter("otherLoanAmt"));
//        mv.addObject("otherLoanRemarks", request.getParameter("otherLoanRemarks"));

        return mv;
    }

    public ModelAndView saveNSClearanceData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO nsFormData) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        nsFormData.setNsApproverId(sessionObj.getEmpId());
        nsFormData.setModuleId(request.getParameter("moduleId"));
        CommonFunctions.getSurrenderValueList(request, response);
        System.out.println(":::: In Controller::" + request.getParameter("moduleId"));
        ((ApprovalServiceImpl) this.getBean("ApprovalService")).saveNsData(nsFormData);
        mv = new ModelAndView("redirect:listRegnSubmittedEmp.htm");
        mv.addObject("moduleId", request.getParameter("moduleId"));
        mv.addObject("approveType", "pending");
        return mv;
    }

    public ModelAndView saveAdminClearanceData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO adFormData) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        adFormData.setAdApproverId(sessionObj.getEmpId());
        adFormData.setModuleId(request.getParameter("moduleId"));
        CommonFunctions.getSurrenderValueList(request, response);
        System.out.println(":::: In Controller::" + request.getParameter("moduleId"));
        ((ApprovalServiceImpl) this.getBean("ApprovalService")).saveAdData(adFormData);
        mv = new ModelAndView("redirect:listRegnSubmittedEmp.htm");
        mv.addObject("moduleId", request.getParameter("moduleId"));
        mv.addObject("approveType", "pending");
        return mv;
    }

    public ModelAndView saveRmClearanceData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO rmClrFormData) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        rmClrFormData.setRmId(sessionObj.getEmpId());
        rmClrFormData.setModuleId(request.getParameter("moduleId"));
        CommonFunctions.getSurrenderValueList(request, response);
        System.out.println(":::: In Controller::" + request.getParameter("moduleId"));
        ((ApprovalServiceImpl) this.getBean("ApprovalService")).saveRmClrData(rmClrFormData);
        mv = new ModelAndView("redirect:listRegnSubmittedEmp.htm");
        mv.addObject("moduleId", request.getParameter("moduleId"));
        mv.addObject("approveType", "pending");
        return mv;
    }

    public ModelAndView saveHRClearanceData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO hrFormData) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        hrFormData.setHrApproverId(sessionObj.getEmpId());
        hrFormData.setModuleId(request.getParameter("moduleId"));
        CommonFunctions.getSurrenderValueList(request, response);
        System.out.println(":::: In Controller::" + request.getParameter("moduleId"));
        hrFormData.setEmploymentStatusId(request.getParameter("employmentStatus"));
        HttpSession session = request.getSession();
        request.setAttribute("exitEmployeeId", request.getParameter("exitEmpId"));
        request.setAttribute("resEmpId", request.getParameter("resEmpId"));
        String c=request.getParameter("checkPF");
        String s=request.getParameter("checkAccpLetter");
        if(c!=null){
            hrFormData.setCheck("true");
        }
        else{
            hrFormData.setCheck("false");
        }
        try{
           if(s!=null){
            hrFormData.setCheckService("true");
        }
           else{
            hrFormData.setCheckService("false");
           }
        }catch(Exception e){
            hrFormData.setCheckService("false");
        }
        ((ApprovalServiceImpl) this.getBean("ApprovalService")).saveHrData(hrFormData,request,response);
        mv = new ModelAndView("redirect:listRegnSubmittedEmp.htm");
        mv.addObject("moduleId", request.getParameter("moduleId"));
        mv.addObject("approveType", "pending");
        return mv;
    }

    public ModelAndView viewStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        CommonFunctions.getSurrenderValueList(request, response);
        CommonFunctions.getMenuIdInRequest(request, response);
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(sessionObj.getEmpId());
        System.out.println(employeeDetails.getExitEmpId());
        ApprovalDTO fileData = null;
        if(employeeDetails.getExitEmpId() != null){
        fileData = ((ApprovalService) this.getBean("ApprovalService")).getFile(Integer.parseInt(employeeDetails.getExitEmpId()), CommonConfigurations.EXIT_MODULE_CODE);
        }
        ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
        ApprovalDTO finActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getFinActionData(employeeDetails.getExitEmpId());
        ApprovalDTO nsActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getNSActionData(employeeDetails.getExitEmpId());
        ApprovalDTO adActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getAdminActionData(employeeDetails.getExitEmpId());
        ApprovalDTO hrActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getHrActionData(employeeDetails.getExitEmpId());
//        ApprovalDTO rmClrData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmClrData(employeeDetails.getExitEmpId());
         List<ApprovalDTO> finMultipleData = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getfinMultipleData(employeeDetails.getExitEmpId());
        mv = new ModelAndView("approval/exitStatus");
        mv.addObject("fileDetails", fileData);
        mv.addObject("exitEmpInfo", employeeDetails);
        mv.addObject("rmActionData", rmActionData);
        mv.addObject("finActionData", finActionData);
        mv.addObject("nsActionData", nsActionData);
        mv.addObject("adActionData", adActionData);
        mv.addObject("hrActionData", hrActionData);
//        mv.addObject("rmClrData", rmClrData);
        mv.addObject("finMultipleData", finMultipleData);
//            mv.addObject("moduleId",moduleId);
        mv.addObject("statusList", CommonConfigurations.statusList);
        mv.addObject("noticeWaiverValueList", CommonConfigurations.noticeWaiverValueList);
//        mv.addObject("exitTypeList", CommonConfigurations.exitType);
        mv.addObject("monthList", CommonFunctions.getMonthList());
        mv.addObject("exitTypeList", CommonFunctions.getExitType());
        return mv;
    }

    public ModelAndView exitSurvey(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CommonFunctions.getMenuIdInRequest(request, response);
        Properties exitSurveyProp = new Properties();
        exitSurveyProp.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));
        List<ApprovalDTO> surveyQuestions = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getSurveyQuestions(this.getSessionValues(request).getEmpId());
        List<ApprovalDTO> surveyAnswers = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getSurveyAnswers();
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(this.getSessionValues(request).getEmpId());
        ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
        mv = new ModelAndView("approval/exitSurvey");
        mv.addObject("surveyQuestions", surveyQuestions);
        mv.addObject("surveyAnswers", surveyAnswers);
        mv.addObject("exitSurveyProp", exitSurveyProp);
        mv.addObject("exitEmpInfo", employeeDetails);
        mv.addObject("rmActionData", rmActionData);
        mv.addObject("sliderMax", CommonConfigurations.SLIDER_MAX_VALUE);
        mv.addObject("sliderMin", CommonConfigurations.SLIDER_MIN_VALUE);
        mv.addObject("typeRadio", CommonConfigurations.SURVEY_ANSWER_RADIO);
        mv.addObject("typeSlider", CommonConfigurations.SURVEY_ANSWER_SLIDER);
        mv.addObject("typeFreeText", CommonConfigurations.SURVEY_ANSWER_FREE_TEXT);
        mv.addObject("typeMultiple", CommonConfigurations.SURVEY_ANSWER_MULTIPLE);
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

    public LoginDTO getSessionValues(HttpServletRequest request) {
        LoginDTO sessionObj = new LoginDTO();
        String employeeId = (String) request.getSession().getAttribute("employeeId");
        String moduleId = (String) request.getSession().getAttribute("moduleId");
        sessionObj.setEmpId(employeeId);
        sessionObj.setModuleId(moduleId);
        return sessionObj;
    }

    public ModelAndView saveSurveyData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO surveyFormData) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        String[] questionId = request.getParameterValues("questionId");
        String[] empAnswer = new String[questionId.length];
        String actionType = request.getParameter("actionType");
        surveyFormData.setEmpId(this.getSessionValues(request).getEmpId());
        for (int i = 0; i < questionId.length; i++) {
            empAnswer[i] = CommonFunctions.ifNull(request.getParameter("answerValue" + questionId[i]), "0");
        }
//        surveyFormData.setExitEmpId(this.getSessionValues(request).getEmpId());
        ((ApprovalServiceImpl) this.getBean("ApprovalService")).saveSurveyData(questionId, empAnswer, surveyFormData);
        System.out.println("::::"+actionType);
        if (actionType.equals("1")) {

//            ((ApprovalServiceImpl) this.getBean("ApprovalService")).submitSurveyData(this.getSessionValues(request).getEmpId());
            ((ApprovalServiceImpl) this.getBean("ApprovalService")).submitSurveyData(surveyFormData);
        }
        return exitSurvey(request, response);
    }

    public ModelAndView resLetterGeneration(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            String resEmpId = request.getParameter("resEmpId");
            EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(resEmpId);
            ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + employeeDetails.getEmployeeNumber()+"_RAL.pdf\"");
            Document document = new Document(PageSize.A4, 0, 0, 0, 0);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/ht_logo.png");
            headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            document.add(headerImage);
            
            Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/bonafied_footer.jpg");
            footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            footerImage.setAbsolutePosition(0, 0);
            document.add(footerImage);
            if(employeeDetails.getEmpLeavingReason().equals("1111") ){
                Paragraph para6 = new Paragraph();
                para6.add(new Phrase("Acceptance of Retirement",new Font(Font.TIMES_ROMAN, 10, Font.BOLD|Font.UNDERLINE)));
                para6.setIndentationLeft(50f);
                para6.setIndentationRight(50f);
                para6.setAlignment(Element.ALIGN_CENTER);
                document.add(para6);

                Paragraph para = new Paragraph();
                para.add(new Phrase(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT),new Font(Font.TIMES_ROMAN, 9)));
                para.setIndentationRight(50f);
                para.setAlignment(Element.ALIGN_RIGHT);
                document.add(para);

                PdfPTable table = new PdfPTable(2);
                PdfPCell pfcell;
                table.setTotalWidth(490);
                table.setLockedWidth(true);
                table.setWidths(new float[]{0.55f, 2});
                pfcell = new PdfPCell(new Phrase("Employee Name",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getEmployeeName(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);

                pfcell = new PdfPCell(new Phrase("Employee Id",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getEmployeeNumber(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);

                pfcell = new PdfPCell(new Phrase("Band",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getBand(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);

                pfcell = new PdfPCell(new Phrase("Sub Band",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getSubBand(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);

                pfcell = new PdfPCell(new Phrase("Designation",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getDesignation(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                document.add(table);

                Paragraph para3 = new Paragraph();
                para3.add(new Phrase("\nDear "+employeeDetails.getEmployeeName()+",\n\n",new Font(Font.TIMES_ROMAN, 10)));
                para3.add(new Phrase("On behalf of Hinduja Tech Ltd, we inform you that your retirement is effective on " +dateFormat.format(rmActionData.getLastWorkingDate())+", according to the policies of Hinduja Tech Ltd.",new Font(Font.TIMES_ROMAN, 10)));
                para3.setIndentationLeft(50f);
                para3.setIndentationRight(50f);
                para3.setLeading(11f);
                para3.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para3);  
                
                Paragraph para5 = new Paragraph();
                para5.add(new Phrase("\nWe have always cherished your association with us. We take this opportunity to "
                        + "place on record our appreciation of your dedicated service and the valuable contribution made by you "
                        + "during your career with us.\n\nOn behalf of the HT family, we wish you a very happy retired life.\n\n",new Font(Font.TIMES_ROMAN, 10)));
                para5.setIndentationLeft(50f);
                para5.setIndentationRight(50f);
                para5.setLeading(11f);
                para5.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para5);

                Paragraph para7 = new Paragraph();
                para7.add(new Phrase("Yours Truly,",new Font(Font.TIMES_ROMAN, 9)));
                para7.add(new Phrase("\nFor Hinduja Tech Ltd.",new Font(Font.TIMES_ROMAN, 9)));
                para7.setSpacingBefore(10f);
                para7.setIndentationLeft(50f);
                para7.setLeading(11f);
                para7.setAlignment(Element.ALIGN_LEFT);
                document.add(para7);

                Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/hr_signature.jpg");
                signImage.setAbsolutePosition(45, signImage.absoluteY());
                document.add(signImage);
    //            
                Paragraph para8 = new Paragraph();
                para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(Font.TIMES_ROMAN, 9)));
                para8.add(new Phrase("\nGeneral Manager - HR",new Font(Font.TIMES_ROMAN, 9)));
                para8.setIndentationLeft(50f);
                para8.setLeading(11f);
                para8.setAlignment(Element.ALIGN_LEFT);
                document.add(para8);
                
            }else{
                Paragraph para6 = new Paragraph();
                para6.add(new Phrase("Acceptance of Resignation Letter",new Font(Font.TIMES_ROMAN, 10, Font.BOLD|Font.UNDERLINE)));
                para6.setIndentationLeft(50f);
                para6.setIndentationRight(50f);
                para6.setAlignment(Element.ALIGN_CENTER);
                document.add(para6);

                Paragraph para = new Paragraph();
                para.add(new Phrase(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT),new Font(Font.TIMES_ROMAN, 9)));
                para.setIndentationRight(50f);
                para.setAlignment(Element.ALIGN_RIGHT);
                document.add(para);

                PdfPTable table = new PdfPTable(2);
                PdfPCell pfcell;
                table.setTotalWidth(490);
                table.setLockedWidth(true);
                table.setWidths(new float[]{0.55f, 2});
                pfcell = new PdfPCell(new Phrase("Employee Name",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getEmployeeName(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);

                pfcell = new PdfPCell(new Phrase("Employee Id",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getEmployeeNumber(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);

                pfcell = new PdfPCell(new Phrase("Band",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getBand(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);

                pfcell = new PdfPCell(new Phrase("Sub Band",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getSubBand(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);

                pfcell = new PdfPCell(new Phrase("Designation",new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getDesignation(),new Font(Font.TIMES_ROMAN, 10)));
                pfcell.setBorder(0);
                table.addCell(pfcell);
                document.add(table);

                Paragraph para3 = new Paragraph();
                para3.add(new Phrase("\nDear "+employeeDetails.getEmployeeName()+",\n\n",new Font(Font.TIMES_ROMAN, 10)));
                para3.add(new Phrase("This is with reference to your resignation letter "+dateFormat.format(rmActionData.getExitTriggerDate())+"."
                        + "Your resignation has been accepted by the company, subject to your completion of necessary formalities including "
                        + "final settlement as per the company policy.On your returning of all the company assets if any and on completion "
                        + "of final settlement of accounts, you would be relieved from the services of the company, effective from "+dateFormat.format(rmActionData.getLastWorkingDate())+".",new Font(Font.TIMES_ROMAN, 10)));
                para3.setIndentationLeft(50f);
                para3.setIndentationRight(50f);
                para3.setLeading(11f);
                para3.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para3);  

                Paragraph para4 = new Paragraph();
                para4.add(new Phrase("Relieving letter and service certificate will be forwarded to your address once the final "
                                    + "settlement details are completed, including recoveries if any. Kindly write to "
                                    + "exit.helpdesk@hindujatech.com for any clarifications.\n",new Font(Font.TIMES_ROMAN, 10)));
                para4.setSpacingBefore(10f);
                para4.setIndentationLeft(50f);
                para4.setIndentationRight(50f);
                para4.setLeading(11f);
                para4.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para4);

                Paragraph para5 = new Paragraph();
                para5.add(new Phrase("\nWe would like to bring to your attention certain provisions contained in the Employee "
                        + "Non-compete and Non-Disclosure Agreement (“Agreement”) dated "+ dateFormat.format(employeeDetails.getEmpDoj())+" entered between you and Hinduja Tech "
                        + "Limited (“HT”) at the time of your employment with us. \nPursuant to the said Agreement, you had "
                        + "undertaken that you shall abide by the following provision/restrictions\n\n"
                        + "\t1. Till the completion of one year post cessation of your employment with HT, you shall not (a) "
                        + "directly or indirectly, own, engage, participate, or be employed in any capacity related or similar to, "
                        + "or requiring knowledge of Confidential Information obtained from your employment with HT; (b) solicit or "
                        + "divert to any Competing Business any individual or entity which is a customer of HT or was a customer at any "
                        + "time during the preceding 12 months; or (c) induce, recruit or solicit any of HT’s employees to terminate their "
                        + "employment or enter into another employment agreement with a competitor to HT.\n\t2. You shall not disclose any "
                        + "Confidential Information without the prior written authorization of HT.\n\t3. You shall disclose in writing to HT, "
                        + "promptly and completely, any invention which you made during your employment with HT or in a period of one (1) "
                        + "year immediately following the end of your employment which relate to your prior work assignment at HT or to any "
                        + "Confidential Information of HT.\n\nThe capitalized words not defined herein shall have the meaning as set out in the "
                        + "Agreement.\n\nPlease note that as per the terms of the Agreement, in the event of breach of these conditions by you, "
                        + "HT shall be entitled to seek preliminary and permanent injunctive relief, without the necessity of proving actual "
                        + "damages, as well as an equitable accounting of all earnings, profits and other benefits arising from any violation of the "
                        + "above terms of the Agreement, which rights shall be in addition to any other rights or remedies to which HT may be "
                        + "entitled.\n\nWe wish to state that the terms of the Agreement will continue to be in force even after the cessation of your "
                        + "employment with HT for the period as stipulated in the Agreement, and any breach of the same will entitle HT to take "
                        + "necessary remedial action as it may deem fit, in order to protect its interests. Wish you success in your future endeavors",new Font(Font.TIMES_ROMAN, 10)));
                para5.setIndentationLeft(50f);
                para5.setIndentationRight(50f);
                para5.setLeading(11f);
                para5.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(para5);

                Paragraph para7 = new Paragraph();
                para7.add(new Phrase("Yours Truly,",new Font(Font.TIMES_ROMAN, 9)));
                para7.add(new Phrase("\nFor Hinduja Tech Ltd.",new Font(Font.TIMES_ROMAN, 9)));
                para7.setSpacingBefore(10f);
                para7.setIndentationLeft(50f);
                para7.setLeading(11f);
                para7.setAlignment(Element.ALIGN_LEFT);
                document.add(para7);

                Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/hr_signature.jpg");
                signImage.setAbsolutePosition(45, signImage.absoluteY());
                document.add(signImage);
    //            
                Paragraph para8 = new Paragraph();
                para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(Font.TIMES_ROMAN, 9)));
                para8.add(new Phrase("\nGeneral Manager - HR",new Font(Font.TIMES_ROMAN, 9)));
                para8.setIndentationLeft(50f);
                para8.setLeading(11f);
                para8.setAlignment(Element.ALIGN_LEFT);
                document.add(para8);
            }
            
            
            document.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
   
    public ModelAndView servLetterGeneration(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
            String resEmpId = request.getParameter("resEmpId");
            EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(resEmpId);
           // System.out.println("Employee ID ---->"+employeeDetails.getExitEmpId());
            ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
            String relieved = "relieved";
            String relieving = "Relieving";
            if(employeeDetails.getEmpLeavingReason().equals("1111")){
                relieved = "retired";
                relieving = "Retirement";
            }
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + employeeDetails.getEmployeeNumber()+"_SRL.pdf\"");
            Document document = new Document(PageSize.A4, 0, 0, 0, 0);
            PdfWriter writer2 =  PdfWriter.getInstance(document, bos);
            document.open();
            
            Image headerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/ht_logo.png");
            headerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            document.add(headerImage);
            
            Image footerImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/bonafied_footer.jpg");
            footerImage.scaleToFit(PageSize.A4.width(), PageSize.A4.height());
            footerImage.setAbsolutePosition(0, 0);
            document.add(footerImage);
            
            Paragraph para6 = new Paragraph();
            para6.add(new Phrase("Service Certificate and Relieving Letter",new Font(Font.TIMES_ROMAN, 14, Font.BOLD|Font.UNDERLINE)));
            para6.setIndentationLeft(50f);
            para6.setIndentationRight(50f);
            para6.setAlignment(Element.ALIGN_CENTER);
            document.add(para6);
            
            Paragraph para = new Paragraph();
            para.add(new Phrase(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_SELECT),new Font(Font.TIMES_ROMAN, 12)));
            para.setIndentationRight(50f);
            para.setAlignment(Element.ALIGN_RIGHT);
            document.add(para);
            
            Paragraph para3 = new Paragraph();
            para3.add(new Phrase("\nDear "+employeeDetails.getEmployeeName()+",\n\n",new Font(Font.TIMES_ROMAN, 13)));
            para3.add(new Phrase("Consequent to the completion of Full and Final settlement process, we would like to "
                    + "inform you that you have been "+relieved+" from the services of Hinduja Tech Limited on "
                    + "close of business hours on "+dateFormat1.format(rmActionData.getLastWorkingDate())+".\n\n"
                    + "Details of your service with the company is as furnished below\n\n ",new Font(Font.TIMES_ROMAN, 13)));
            para3.setIndentationLeft(50f);
            para3.setIndentationRight(50f);
            para3.setLeading(12f);
            para3.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para3);  
            
            PdfPTable table = new PdfPTable(2);
            PdfPCell pfcell;
            table.setTotalWidth(490);
            table.setLockedWidth(true);
            table.setWidths(new float[]{0.75f, 2});
            pfcell = new PdfPCell(new Phrase("Employee Name ",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getEmployeeName(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Employee Id",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getEmployeeNumber(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Band",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getBand(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Sub Band",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getSubBand(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Date of Joining",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+dateFormat1.format(employeeDetails.getEmpDoj()),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Date of "+relieving,new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+dateFormat1.format(rmActionData.getLastWorkingDate()),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Designation at "+relieving,new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": "+employeeDetails.getDesignation(),new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            
            pfcell = new PdfPCell(new Phrase("Character & Conduct",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            pfcell = new PdfPCell(new Phrase(": Good",new Font(Font.TIMES_ROMAN, 12)));
            pfcell.setBorder(0);
            table.addCell(pfcell);
            document.add(table);
                     
            Paragraph para4 = new Paragraph();
            para4.add(new Phrase("Wish you the very best in all your future endeavours.\n",new Font(Font.TIMES_ROMAN, 12)));
            para4.setSpacingBefore(10f);
            para4.setIndentationLeft(50f);
            para4.setIndentationRight(50f);
            para4.setLeading(12f);
            para4.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(para4);
            
            Paragraph para7 = new Paragraph();
            para7.add(new Phrase("Yours Truly,",new Font(Font.TIMES_ROMAN, 12)));
            para7.add(new Phrase("\nFor Hinduja Tech Ltd.",new Font(Font.TIMES_ROMAN, 12)));
            para7.setSpacingBefore(10f);
            para7.setIndentationLeft(50f);
            para7.setLeading(12f);
            para7.setAlignment(Element.ALIGN_LEFT);
            document.add(para7);
            
            Image signImage = com.lowagie.text.Image.getInstance("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/hr_signature.jpg");
            signImage.setAbsolutePosition(45, signImage.absoluteY());
            document.add(signImage);
//            
            Paragraph para8 = new Paragraph();
            para8.add(new Phrase("Jayawanthi Shankar Rao",new Font(Font.TIMES_ROMAN, 12)));
            para8.add(new Phrase("\nGeneral Manager - HR",new Font(Font.TIMES_ROMAN, 12)));
            para8.setIndentationLeft(50f);
            para8.setLeading(12f);
            para8.setAlignment(Element.ALIGN_LEFT);
            document.add(para8);
            
            document.close();
            bos.flush();
            bos.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PdfContentByte placeString(PdfContentByte content, String txtToPlace, float x, float y, float fontSize,String font) {

        try {
            BaseFont bf = BaseFont.createFont(font, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
            content.beginText();
            content.setTextMatrix(30, 30);
            content.setFontAndSize(bf, fontSize);
            content.moveText(x, y);
            content.showText(txtToPlace);
            content.endText();
        } catch (DocumentException ex) {
            Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }
    
//    public PdfContentByte placeString1(PdfContentByte content, String txtToPlace, float x, float y, float fontSize) {
//
//        try {
//            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
//            content.beginText();
//            content.setTextMatrix(30, 30);
//            content.setFontAndSize(bf, fontSize);
//            content.moveText(x, y);
//            content.showText(txtToPlace);
//            content.endText();
//        } catch (DocumentException ex) {
//            Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ApprovalController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return content;
//    }

    public ModelAndView dwnldempdata(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" IN dwnldempdata ");
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
        ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
        ApprovalDTO finActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getFinActionData(employeeDetails.getExitEmpId());
        ApprovalDTO nsActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getNSActionData(employeeDetails.getExitEmpId());
        ApprovalDTO adActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getAdminActionData(employeeDetails.getExitEmpId());
        ApprovalDTO hrActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getHrActionData(employeeDetails.getExitEmpId());
//        ApprovalDTO rmClrData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmClrData(employeeDetails.getExitEmpId());
        List<ApprovalDTO> finMultipleData = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getfinMultipleData(employeeDetails.getExitEmpId());
        CommonFunctions.exportToExcel(response, "Exit_No_Dues_Form", employeeDetails, rmActionData, finActionData, nsActionData, adActionData, hrActionData, finMultipleData);
        return null;
    }

    public ModelAndView dwnldexitempdata(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" IN dwnldexitempdata ");
        System.out.println("=== The employee id is ---- " + request.getParameter("empId"));
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
        System.out.println("=== the exit emp id is === " + employeeDetails.getExitEmpId());
        System.out.println("===== The employee id is === " + employeeDetails.getEmpId());
        Properties exitSurveyProp = new Properties();
        exitSurveyProp.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));
        List<ApprovalDTO> surveyQuestions = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getSurveyQuestions(request.getParameter("empId"));
        List<ApprovalDTO> surveyAnswers = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getSurveyAnswers();
        CommonFunctions.exportToPdf(request, response, "Exit_Interview_Form", employeeDetails, surveyQuestions, surveyAnswers);
        return null;
    }

    public ModelAndView dwnldExitFfsData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(" IN dwnldexitempdata ");
        System.out.println("=== The employee id is ---- " + request.getParameter("empId"));
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
         ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
        ApprovalDTO finActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getFinActionData(employeeDetails.getExitEmpId());
        ApprovalDTO hrActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getHrActionData(employeeDetails.getExitEmpId());
        System.out.println("=== the exit emp id is === " + employeeDetails.getExitEmpId());
        System.out.println("===== The employee id is === " + employeeDetails.getEmpId());
        CommonFunctions.exportFfsToExcel(response, "Employee_FFS", employeeDetails,rmActionData,finActionData,hrActionData);
        return null;
    }
    
    public ModelAndView approvalFileDownload(HttpServletRequest request, HttpServletResponse response, ApprovalDTO formData){
        ModelAndView mv = null;
        String filePath = CommonConfigurations.fileUploadPath;
        CommonFunctions.fileDownload(formData.getFileName(),filePath,formData.getFileType(),response);
        return mv;
    }
   public ModelAndView exitEmployeeSurvey(HttpServletRequest request, HttpServletResponse response, ApprovalDTO formData) throws Exception{
        ModelAndView mv=null;
		LoginDTO sessionObj = this.getSessionValues(request);
      ((ApprovalServiceImpl) this.getBean("ApprovalService")).submitExitSurvey(formData,sessionObj.getEmpId());
	   String moduleId = "500";
        String approveType = "pending";
        List<EmployeeDTO> regnEmpList = (List<EmployeeDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRegnEmpList(sessionObj.getEmpId(), moduleId,approveType);
        CommonFunctions.getMenuIdInRequest(request, response);
        mv = new ModelAndView("approval/exitEmployeeList");
        mv.addObject("regnEmpList", regnEmpList);
        mv.addObject("moduleId", moduleId);
        mv.addObject("approveType", approveType);
        return mv;
		
    } 
   
   public ModelAndView uploadFileFromHrClr(HttpServletRequest request, HttpServletResponse response, ApprovalDTO formData) throws Exception{
        ModelAndView mv=null;
        MultipartFile uploadedFile = formData.getFile();
        int refId = Integer.parseInt(request.getParameter("exitEmpId"));
        ApprovalService approvalService = (ApprovalServiceImpl) this.getBean("ApprovalService");
        CommonFunctions.fileUpdate(uploadedFile, refId, "EXIT_FILE", CommonConfigurations.EXIT_MODULE_CODE, approvalService);  
        String moduleId = request.getParameter("userModuleId");
            System.out.println("moduleiddddd" + moduleId);
            CommonFunctions.getSurrenderValueList(request, response);
            CommonFunctions.getMenuIdInRequest(request, response);
            EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(request.getParameter("empId"));
            ApprovalDTO fileData = ((ApprovalService) this.getBean("ApprovalService")).getFile(Integer.parseInt(employeeDetails.getExitEmpId()), CommonConfigurations.EXIT_MODULE_CODE);
            ApprovalDTO rmActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmActionData(employeeDetails.getExitEmpId());
            ApprovalDTO finActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getFinActionData(employeeDetails.getExitEmpId());
            ApprovalDTO nsActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getNSActionData(employeeDetails.getExitEmpId());
            ApprovalDTO adActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getAdminActionData(employeeDetails.getExitEmpId());
            ApprovalDTO hrActionData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getHrActionData(employeeDetails.getExitEmpId());
//            ApprovalDTO rmClrData = (ApprovalDTO) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getRmClrData(employeeDetails.getExitEmpId());
            List<ApprovalDTO> finMultipleData = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getfinMultipleData(employeeDetails.getExitEmpId());
            // To Show the employee Exit interiview to HR
            List<ApprovalDTO> surveyQuestions = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getSurveyQuestions(request.getParameter("empId"));
            List<ApprovalDTO> surveyAnswers = (List<ApprovalDTO>) ((ApprovalServiceImpl) this.getBean("ApprovalService")).getSurveyAnswers();

            mv = new ModelAndView("approval/exitMgmtHR");
            mv.addObject("exitEmpInfo", employeeDetails);
            mv.addObject("fileDetails", fileData);
            mv.addObject("rmActionData", rmActionData);
            mv.addObject("finActionData", finActionData);
            mv.addObject("nsActionData", nsActionData);
            mv.addObject("adActionData", adActionData);
            mv.addObject("hrActionData", hrActionData);
            mv.addObject("finMultipleData", finMultipleData);
//            mv.addObject("rmClrData", rmClrData);
            mv.addObject("moduleId", moduleId);
            mv.addObject("statusList", CommonConfigurations.statusList);
            mv.addObject("noticeWaiverValueList", CommonConfigurations.noticeWaiverValueList);
//            mv.addObject("exitTypeList", CommonConfigurations.exitType);
            mv.addObject("exitTypeList", CommonFunctions.getExitType());
            // Needed for survey
            mv.addObject("surveyQuestions", surveyQuestions);
            mv.addObject("surveyAnswers", surveyAnswers);
            mv.addObject("sliderMax", CommonConfigurations.SLIDER_MAX_VALUE);
            mv.addObject("sliderMin", CommonConfigurations.SLIDER_MIN_VALUE);
            mv.addObject("typeRadio", CommonConfigurations.SURVEY_ANSWER_RADIO);
            mv.addObject("typeSlider", CommonConfigurations.SURVEY_ANSWER_SLIDER);
            mv.addObject("typeFreeText", CommonConfigurations.SURVEY_ANSWER_FREE_TEXT);
            mv.addObject("typeMultiple", CommonConfigurations.SURVEY_ANSWER_MULTIPLE);
            mv.addObject("monthList", CommonFunctions.getMonthList());
            return mv;
   }
   public ModelAndView publishServLetter(HttpServletRequest request, HttpServletResponse response) throws Exception{
     System.out.println("in publishing");
     ApprovalDTO hrFormData = new ApprovalDTO(); 
     HttpSession session = request.getSession();
     String moduleId = "500";
     LoginDTO sessionObj = this.getSessionValues(request);
     hrFormData.setHrApproverId(sessionObj.getEmpId());
     hrFormData.setModuleId(moduleId);
     hrFormData.setResEmpId(request.getParameter("resEmpId"));
     request.setAttribute("resEmpId", request.getParameter("resEmpId"));
     ((ApprovalServiceImpl) this.getBean("ApprovalService")).publishServLetter(hrFormData,request, response);
     mv = new ModelAndView("redirect:listRegnSubmittedEmp.htm");
     mv.addObject("moduleId", moduleId);
     mv.addObject("approveType", "processed");
     return mv;
 }
//
//   public String resLetter() throws Exception {
//            String path="HelloWorld.pdf";
//       Document document = new Document();
//      try
//      {
//         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
//         document.open();
//         document.add(new Paragraph("A Hello World PDF document."));
//         document.close();
//         writer.close();
//      } catch (DocumentException e)
//      {
//         e.printStackTrace();
//      } catch (FileNotFoundException e)
//      {
//         e.printStackTrace();
//      }
//        return path;
//    }
}
