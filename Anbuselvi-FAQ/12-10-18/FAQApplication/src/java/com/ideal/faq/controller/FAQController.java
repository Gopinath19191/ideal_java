/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.faq.controller;

import com.ideal.faq.dto.FaqDto;

import com.ideal.faq.service.FAQService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16711
 */
public class FAQController extends MultiActionController {

//    String demoPath = "D:/FaqAnswerFiles/demo.txt";
    String demoPath = null;

    public List<FaqDto> getAllFaqFunctionUnit() {

        List<FaqDto> listFunctionUnit = new ArrayList<FaqDto>();
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        try {
            listFunctionUnit = service.getAllFaqFunctionUnit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listFunctionUnit;
    }

    public String getFunctionUnit(String faq_unit_id) {

        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        String faq_unit = new String();
        try {
            faq_unit = service.getFunctionUnit(faq_unit_id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return faq_unit;
    }

    public String getQuestionCategory(String questionCategoryId) {

        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        String questionCategory = new String();
        try {
            questionCategory = service.getQuestionCategory(questionCategoryId);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return questionCategory;
    }

    public synchronized ModelAndView getAllQuestionCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String faq_unit_id = request.getParameter("faq_unit_id");
        System.out.println("faq_unit_id: " + faq_unit_id);
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        try {
            for (FaqDto questionCategory : service.getAllQuestionCategory(faq_unit_id)) {
                response.getWriter().println("<option value='" + questionCategory.getQuestion_category_id() + "'>" + questionCategory.getQuestion_category() + "</options>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public synchronized ModelAndView listQuestionAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView("viewAdmin");
        List<FaqDto> listQuestionAnswer = new ArrayList<FaqDto>();
        String faq_unit_id = request.getParameter("faq_unit_id");
        String questionCategoryId = request.getParameter("question_category_id");
        System.out.println("faq_unit_id: " + faq_unit_id);
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        try {
            List<FaqDto> listFunctionUnit = getAllFaqFunctionUnit();
            listQuestionAnswer = service.listQuestionAnswer(faq_unit_id, questionCategoryId);
            String faq_unit = service.getFunctionUnit(faq_unit_id);
            String questionCategory = service.getQuestionCategory(questionCategoryId);


            mv.addObject("listQuestionAnswer", listQuestionAnswer);
            mv.addObject("listFunctionUnit", listFunctionUnit);
            mv.addObject("faq_unit_id", faq_unit_id);

            mv.addObject("faq_unit", faq_unit);
            List<FaqDto> listQuestionCategory = service.getAllQuestionCategory(faq_unit_id);
            mv.addObject("listQuestionCategory", listQuestionCategory);

            mv.addObject("questionCategoryId", questionCategoryId);
            mv.addObject("questionCategory", questionCategory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mv;
    }

    public ModelAndView faqScreen(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView mv = new ModelAndView("addScreen");
        List<FaqDto> listFunctionUnit = new ArrayList<FaqDto>();
        try {
            listFunctionUnit = getAllFaqFunctionUnit();
            mv.addObject("listFunctionUnit", listFunctionUnit);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mv;

    }

    public ModelAndView endUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView mv = new ModelAndView("endUser");
        List<FaqDto> listQuestionAnswer = new ArrayList<FaqDto>();
        String faq_unit_id = request.getParameter("faq_unit_id");
        String questionCategoryId = request.getParameter("question_category_id");
        System.out.println("faq_unit_id: " + faq_unit_id);
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        try {
            List<FaqDto> listFunctionUnit = getAllFaqFunctionUnit();
            listQuestionAnswer = service.listQuestionAnswer(faq_unit_id, questionCategoryId);
            String faq_unit = service.getFunctionUnit(faq_unit_id);
            String questionCategory = service.getQuestionCategory(questionCategoryId);


            mv.addObject("listQuestionAnswer", listQuestionAnswer);
            mv.addObject("listFunctionUnit", listFunctionUnit);
            mv.addObject("faq_unit_id", faq_unit_id);

            mv.addObject("faq_unit", faq_unit);
            List<FaqDto> listQuestionCategory = service.getAllQuestionCategory(faq_unit_id);
            mv.addObject("listQuestionCategory", listQuestionCategory);

            mv.addObject("questionCategoryId", questionCategoryId);
            mv.addObject("questionCategory", questionCategory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mv;



    }
    //faqFeedback
    
    public ModelAndView faqFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView mv = new ModelAndView("faqFeedback");
        List<FaqDto> listFaqFeedback = new ArrayList<FaqDto>();
        List<FaqDto> listFaqFeedback1 = new ArrayList<FaqDto>();
        String faq_unit_id = request.getParameter("faq_unit_id");
        String questionCategoryId = request.getParameter("question_category_id");
        System.out.println("faq_unit_id: " + faq_unit_id);
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        //SELECT * FROM employees WHERE employee_number='16221'
        HttpSession session = request.getSession();
        //String employee_no = session.getAttribute("employee_no").toString();
        //String empId = session.getAttribute("EMP_ID").toString();        
        try {
            List<FaqDto> listFunctionUnit = getAllFaqFunctionUnit();
            listFaqFeedback = service.getFaqFeedback(faq_unit_id, questionCategoryId);
            String faq_unit = service.getFunctionUnit(faq_unit_id);
            String questionCategory = service.getQuestionCategory(questionCategoryId);
            
            
            for(FaqDto faqFeedback: listFaqFeedback){
               String employee_id = faqFeedback.getCreated_by();
            String employeeName = service.getEmployeeName(employee_id);
            faqFeedback.setEmployeeName(employeeName);
            listFaqFeedback1.add(faqFeedback);
            
            }


            mv.addObject("listFaqFeedback1", listFaqFeedback1);
            mv.addObject("listFunctionUnit", listFunctionUnit);
            mv.addObject("faq_unit_id", faq_unit_id);

            mv.addObject("faq_unit", faq_unit);
            List<FaqDto> listQuestionCategory = service.getAllQuestionCategory(faq_unit_id);
            mv.addObject("listQuestionCategory", listQuestionCategory);

            mv.addObject("questionCategoryId", questionCategoryId);
            mv.addObject("questionCategory", questionCategory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mv;



    }

    public ModelAndView updateFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("viewAdmin");
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");

        FaqDto faqObj = new FaqDto();

        //session.setAttribute("EMP_ID", authenParams.getEmpId());
        HttpSession session = request.getSession();
        String created_by = session.getAttribute("EMP_ID").toString();



        try {



            System.out.println("faq_unit_id" + request.getParameter("faq_unit_id"));
            System.out.println("question_category_id" + request.getParameter("question_category_id"));
            System.out.println("rowId:" + request.getParameter("rowId"));
            int len = Integer.parseInt(request.getParameter("rowId"));
            for (int i = 1; i <=len; i++) {
                System.out.println("question" + i +":"+ request.getParameter("question" + i));
                System.out.println("answer" + i +":"+ request.getParameter("answer" + i));

                faqObj.setFaq_unit_id(request.getParameter("faq_unit_id"));
                faqObj.setQuestion_category_id(request.getParameter("question_category_id"));
                faqObj.setQuestion(request.getParameter("question" + i));
                faqObj.setAnswer(request.getParameter("answer" + i));
                faqObj.setAnswer_path(demoPath);
                faqObj.setCreated_by(created_by);
                service.insertFaq(faqObj);
            }

            mv = listQuestionAnswer(request, response);




        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mv;

    }

    public ModelAndView updateFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("index");
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");

        FaqDto faqObj = new FaqDto();
        HttpSession session = request.getSession();
        //String created_by = session.getAttribute("employee_no").toString();
        String created_by = session.getAttribute("EMP_ID").toString();

        try {
            String feedback = new String();
            String satisfied = request.getParameter("satisfied");
            if (satisfied.equalsIgnoreCase("y")) {
                feedback = request.getParameter("feedback2");
            } else if (satisfied.equalsIgnoreCase("n")) {
                feedback = request.getParameter("feedback1");
            }

            System.out.println("faq_unit_id" + request.getParameter("faq_unit_id"));
            System.out.println("question_category_id" + request.getParameter("question_category_id"));

            faqObj.setFaq_unit_id(request.getParameter("faq_unit_id"));
            faqObj.setQuestion_category_id(request.getParameter("question_category_id"));
            faqObj.setFeedback_rating_comments(feedback);
            faqObj.setIs_satisfied(satisfied);
            faqObj.setCreated_by(created_by);
            service.addFeedback(faqObj);
            
            mv.addObject("result", "Feedback added successfully");


            //mv = listQuestionAnswer(request, response);
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mv;

    }

    public ModelAndView updateDeleteFlag(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("viewAdmin");
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");

        FaqDto faqObj = new FaqDto();

        HttpSession session = request.getSession();
        //String created_by = session.getAttribute("EMP_ID").toString();
        String modified_by = session.getAttribute("EMP_ID").toString();



        try {

            System.out.println("faq_unit_id" + request.getParameter("faq_unit_id"));
            System.out.println("question_category_id" + request.getParameter("question_category_id"));


            String deleteFlagId = request.getParameter("id");

            faqObj.setDeleted("1");
            faqObj.setModified_by(modified_by);
            faqObj.setQuestion_answer_id(deleteFlagId);

            service.updateDeleteFlag(faqObj);


            mv = listQuestionAnswer(request, response);




        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mv;

    }

    public ModelAndView viewFaqByAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("viewAdmin");

        try {

            List<FaqDto> listFunctionUnit = new ArrayList<FaqDto>();

            listFunctionUnit = getAllFaqFunctionUnit();
            mv.addObject("listFunctionUnit", listFunctionUnit);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mv;

    }

    public synchronized ModelAndView addQuestionCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FaqDto questionCategoryObj = new FaqDto();

        HttpSession session = request.getSession();
        //String created_by = session.getAttribute("employee_no").toString();
        String created_by = session.getAttribute("EMP_ID").toString();
        

        String currentUnit = request.getParameter("currentUnit");
        System.out.println("currentUnit: " + currentUnit);
        String strQuestionCategory = request.getParameter("newQuesCategory");

        questionCategoryObj.setFaq_unit_id(currentUnit);
        questionCategoryObj.setCreated_by(created_by);
        questionCategoryObj.setQuestion_category(strQuestionCategory);
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        try {
            service.addQuestionCategoryForUnit(questionCategoryObj);
            for (FaqDto questionCategory : service.getAllQuestionCategory(currentUnit)) {
                response.getWriter().println("<option value='" + questionCategory.getQuestion_category_id() + "'>" + questionCategory.getQuestion_category() + "</options>");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    public synchronized ModelAndView updateQuestionAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        ModelAndView mv = new ModelAndView("viewAdmin");
        FaqDto questionAnswer = new FaqDto();

        HttpSession session = request.getSession();
        //String created_by = session.getAttribute("employee_no").toString();
        String modified_by = session.getAttribute("EMP_ID").toString();
        
        
        

        //String faq_unit_id = request.getParameter("faq_unit_id");
        //System.out.println("faq_unit_id: " + faq_unit_id);
        //String question_category_id = request.getParameter("question_category_id");
        String currentQuestion = request.getParameter("currentQuestion");
        String currentAnswer = request.getParameter("currentAnswer");
        String question_answer_id = request.getParameter("question_answer_id");

        questionAnswer.setQuestion_answer_id(question_answer_id);
        questionAnswer.setModified_by(modified_by);
        questionAnswer.setQuestion(currentQuestion);
        questionAnswer.setAnswer(currentAnswer);
        WebApplicationContext context = getWebApplicationContext();
        FAQService service = (FAQService) context.getBean("FAQService");
        try {
            service.updateFaq(questionAnswer);
            mv = listQuestionAnswer(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mv;
    }
    
}
