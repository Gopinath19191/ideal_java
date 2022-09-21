/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.controller;

import com.defiance.ideal.application.util.CommonMethods;
import com.defiance.ideal.application.dto.OJFSurveyDTO;
import com.defiance.ideal.application.dto.LoginDTO;
import com.defiance.ideal.application.service.OJFSurveyServiceImpl;
import com.defiance.ideal.application.util.CommonConfigurations;
import com.defiance.ideal.application.util.Config;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14058
 */

public class OJFSurveyController  extends MultiActionController{

    public OJFSurveyController() {
    }
    public ModelAndView OJFSurveyInfo (HttpServletRequest request, HttpServletResponse response, OJFSurveyDTO filterData) throws Exception {
        Config t = new Config();
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId=null;
        System.out.println("EMP ID:"+request.getParameter("empId"));
        System.out.println("EMP ID 1:"+request.getParameter("empid"));
        //String selectedMonth,selectedYear,;

          Calendar calndr = Calendar.getInstance();
          calndr.get(Calendar.MONTH);
          calndr.get(Calendar.YEAR);

        if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        }else{
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        Calendar calendar = Calendar.getInstance();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            OJFSurveyServiceImpl ojfSurveyProcess=(OJFSurveyServiceImpl) ctx.getBean("OJFSurveyService");
            //List<OJFSurveyDTO> customerData = customerService.fetchCustomerData(filterData);
            filterData.setExitEmpId(employeeId);
            List<OJFSurveyDTO> ojfEmployeeData = ojfSurveyProcess.getOjfEmployeeData(filterData);
            if(ojfEmployeeData.get(0).ojf_survey_status != null && ojfEmployeeData.get(0).ojf_survey_status.equals("1")) {
//                mv = new ModelAndView("redirect: http://10.18.1.54/ideal2/");
//                return mv;
            }
            filterData.setResEmpId(employeeId);
            List<OJFSurveyDTO> surveyQuestionsParent = ojfSurveyProcess.getSurveyQuestionsParent();
            List<OJFSurveyDTO> surveyQuestions = ojfSurveyProcess.getSurveyQuestions(filterData);
            List<OJFSurveyDTO> surveyAnswers = ojfSurveyProcess.getSurveyAnswers();

            Map<String,String> monthsMap = CommonMethods.getMonthsList();
            Map<Integer,Integer> yearsMap = CommonMethods.getYearsList(2);
            Map<String,String> pjctStatsMap = CommonMethods.getPjtStatusList();
            HashMap surveyRatings = CommonConfigurations.rating_values;
            mv = new ModelAndView("/OJFSurveyInfo");
            //String processComplete = request.getParameter("process");
//            if(processComplete != null && processComplete.equals("completed")) {
//                return mv;
//            }
            mv.addObject("monthsMap",monthsMap);
            mv.addObject("surveyRatings",surveyRatings);
            mv.addObject("yearsMap",yearsMap);
            mv.addObject("pjctStatsMap",pjctStatsMap);
            mv.addObject("surveyQuestionsParent", surveyQuestionsParent);
            mv.addObject("surveyQuestions", surveyQuestions);
            mv.addObject("surveyAnswers", surveyAnswers);
            mv.addObject("sliderMax", CommonConfigurations.SLIDER_MAX_VALUE);
            mv.addObject("sliderMin", CommonConfigurations.SLIDER_MIN_VALUE);
            mv.addObject("typeRadio", CommonConfigurations.SURVEY_ANSWER_RADIO);
            mv.addObject("typeSlider", CommonConfigurations.SURVEY_ANSWER_SLIDER);
            mv.addObject("typeFreeText", CommonConfigurations.SURVEY_ANSWER_FREE_TEXT);
            mv.addObject("typeMultiple", CommonConfigurations.SURVEY_ANSWER_MULTIPLE);
            mv.addObject("ojf_survey_end", CommonConfigurations.OJF_SURVEY_LIMIT_DAYS+CommonConfigurations.OJF_SURVEY_LIMIT_DAYS_FLOW);
            //System.out.println(":JOINED DATE:"+ojfEmployeeData.get(0).getJoined_date());
            Long dateDifference = CommonMethods.DateDifference(ojfEmployeeData.get(0).getJoined_date(),"");
            mv.addObject("dateDifference", dateDifference+2);
            mv.addObject("ojfEmployeeData", ojfEmployeeData);
            String urlToRedirect = CommonConfigurations.URL_TO_REDIRECT_FOR_OJF;
            mv.addObject("urlToRedirect",urlToRedirect);
//            System.out.println("SURVEY RATIN::"+surveyRatings.size());
//            for(int i=0;i<surveyRatings.size();i++){
//                System.out.println(":: KEYYYYY::"+surveyRatings.get(Integer.toString(i+1)));
//            }

            //mv.addObject("customerData",customerData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView OJFSurveyList (HttpServletRequest request, HttpServletResponse response, OJFSurveyDTO filterData) throws Exception {
        Config t = new Config();
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId=null;
        if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        }else{
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        Calendar calendar = Calendar.getInstance();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            OJFSurveyServiceImpl ojfSurveyProcess=(OJFSurveyServiceImpl) ctx.getBean("OJFSurveyService");
            filterData.setExitEmpId(employeeId);
            List<OJFSurveyDTO> ojfEmployeeData = ojfSurveyProcess.getOjfSurveyList(filterData);
            HashMap surveyRatings = CommonConfigurations.rating_values;
            mv = new ModelAndView("/OJFSurveyList");
            mv.addObject("surveyRatings",surveyRatings);
            mv.addObject("ojfEmployeeData", ojfEmployeeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    public ModelAndView saveOJFSurveyData(HttpServletRequest request, HttpServletResponse response, OJFSurveyDTO surveyFormData) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        String[] questionId = request.getParameterValues("questionId");
        String[] empAnswer = new String[questionId.length];
        String actionType = request.getParameter("actionType");
        ModelAndView mv = null;
        try {
            //mv = new ModelAndView("/OJFSurveyInfo");
            mv = new ModelAndView("redirect:OJFSurveyInfo.htm?process=completed");
            String urlToRedirect = CommonConfigurations.URL_TO_REDIRECT_FOR_OJF;
            mv.addObject("urlToRedirect",urlToRedirect);
            for (int i = 0; i < questionId.length; i++) {
                empAnswer[i] = CommonMethods.ifNull(request.getParameter("answerValue" + questionId[i]), "0");
                System.out.println(":VALUE:"+empAnswer[i]);
            }
            final WebApplicationContext ctx = getWebApplicationContext();
            OJFSurveyServiceImpl ojfSurveyProcess=(OJFSurveyServiceImpl) ctx.getBean("OJFSurveyService");
            surveyFormData.setOjfEmpId(this.getSessionValues(request).getEmpId());
            ojfSurveyProcess.saveSurveyData(questionId, empAnswer, surveyFormData);
            System.out.println(":ACTION TYPE:"+actionType);
            if (actionType.equals("1")) {
                ojfSurveyProcess.submitSurveyData(surveyFormData);
            }
            //return OJFSurveyInfo(request, response,filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView OJFSurveyRedirect (HttpServletRequest request, HttpServletResponse response, OJFSurveyDTO filterData) throws Exception {
        LoginDTO sessionObj = this.getSessionValues(request);
        ModelAndView mv = null;
        mv = new ModelAndView("redirect:OJFSurveyInfo.htm?process=completed");
        return mv;
    }
    public ModelAndView OJFSurveyView (HttpServletRequest request, HttpServletResponse response, OJFSurveyDTO filterData) throws Exception {
        Config t = new Config();
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        String surveyEmployeeId = request.getParameter("surveyEmployeeId");
        if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        }else{
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        Calendar calendar = Calendar.getInstance();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            OJFSurveyServiceImpl ojfSurveyProcess=(OJFSurveyServiceImpl) ctx.getBean("OJFSurveyService");
            filterData.setExitEmpId(surveyEmployeeId);
            List<OJFSurveyDTO> ojfEmployeeData = ojfSurveyProcess.getOjfEmployeeData(filterData);
            List<OJFSurveyDTO> surveyQuestionsParent = ojfSurveyProcess.getSurveyQuestionsParent();
            List<OJFSurveyDTO> surveyQuestions = ojfSurveyProcess.getSurveyQuestions(filterData);
            List<OJFSurveyDTO> surveyAnswers = ojfSurveyProcess.getSurveyAnswers();

            HashMap surveyRatings = CommonConfigurations.rating_values;
            mv = new ModelAndView("/OJFSurveyView");
            mv.addObject("surveyRatings",surveyRatings);

            mv.addObject("ojfEmployeeData", ojfEmployeeData);
            mv.addObject("surveyQuestionsParent", surveyQuestionsParent);
            mv.addObject("surveyQuestions", surveyQuestions);
            mv.addObject("surveyAnswers", surveyAnswers);
            mv.addObject("typeRadio", CommonConfigurations.SURVEY_ANSWER_RADIO);
            mv.addObject("typeSlider", CommonConfigurations.SURVEY_ANSWER_SLIDER);
            mv.addObject("typeFreeText", CommonConfigurations.SURVEY_ANSWER_FREE_TEXT);
            mv.addObject("typeMultiple", CommonConfigurations.SURVEY_ANSWER_MULTIPLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView downloadEmployeeSurveyData(HttpServletRequest request, HttpServletResponse response, OJFSurveyDTO filterData) throws Exception {

        Config t = new Config();
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        String surveyEmployeeId = request.getParameter("empId");
        if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        }else{
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        Calendar calendar = Calendar.getInstance();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            OJFSurveyServiceImpl ojfSurveyProcess=(OJFSurveyServiceImpl) ctx.getBean("OJFSurveyService");
            Properties ojfSurveyProp = new Properties();
            ojfSurveyProp.load(new FileInputStream(CommonConfigurations.ExternalConfigFile));

            filterData.setExitEmpId(surveyEmployeeId);
            List<OJFSurveyDTO> ojfEmployeeData = ojfSurveyProcess.getOjfEmployeeData(filterData);
            List<OJFSurveyDTO> surveyQuestionsParent = ojfSurveyProcess.getSurveyQuestionsParent();
            List<OJFSurveyDTO> surveyQuestions = ojfSurveyProcess.getSurveyQuestions(filterData);
            List<OJFSurveyDTO> surveyAnswers = ojfSurveyProcess.getSurveyAnswers();
            System.out.println(":TEST TEST :"+ojfEmployeeData.get(0).getEmployee_name());
            CommonMethods.exportToPdf(request, response, "OJF_Survey_Details", ojfEmployeeData, surveyQuestionsParent, surveyQuestions, surveyAnswers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LoginDTO getSessionValues(HttpServletRequest request) {
        LoginDTO sessionObj = new LoginDTO();
        try {
            String employeeId = (String) request.getSession().getAttribute("employeeId");
            String moduleId = (String) request.getSession().getAttribute("moduleId");
            sessionObj.setEmpId(employeeId);
            sessionObj.setModuleId(moduleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionObj;
    }
}
