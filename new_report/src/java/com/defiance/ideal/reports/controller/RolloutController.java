/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.RollOutDTO;
import com.defiance.ideal.reports.service.RollOutService;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author 14578
 */



public class RolloutController extends MultiActionController {

    public ModelAndView rolloutlist(HttpServletRequest request, HttpServletResponse response, RollOutDTO formData) {
        ModelAndView mv = null;
        mv = new ModelAndView("/rolloutList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        RollOutService rolloutService = (RollOutService) ctx.getBean("RollOutService");
//        System.out.println("date Diff  "+request.getParameter("dateDiff"));
        if (request.getParameter("dateDiff") != null && !request.getParameter("dateDiff").equals("")) {
            formData.setDatediff(request.getParameter("dateDiff"));
            formData.setOperators(request.getParameter("operators"));
        } else {
            formData.setDatediff("31");
            formData.setOperators("5");
        }
        List<RollOutDTO> rollOutReport = rolloutService.getRollOutreport(formData);
        List<RollOutDTO> projectList = rolloutService.getprojectList();
        formData.setSbuId(CommonConfigurations.SBU);
        List<RollOutDTO> sbuList = rolloutService.getSbuList(formData);
        List<RollOutDTO> pmNameList = rolloutService.getPmNameList();
      //  List<RollOutDTO> subSbuList = rolloutService.getsubSbuList("9,10,11");
        String PES = CommonConfigurations.PES;
        String TS = CommonConfigurations.TS;
        String parentId = "";
        if("".equals(formData.getSbu()) || formData.getSbu() == null){
            parentId = PES + ',' + TS;
        }else{
            parentId = formData.getSbu();
        }
        // System.out.println("parent id   :::: "+ parentId);
        List<RollOutDTO> subSbuList = rolloutService.getsubSbuList(parentId);
        mv.addObject("rolloutList", rollOutReport);
        mv.addObject("projectLists", projectList);
        mv.addObject("sbuList", sbuList);
        mv.addObject("pmNameList", pmNameList);
        mv.addObject("subSbuList", subSbuList);
        mv.addObject("selectPrj", request.getParameter("projectName"));
        mv.addObject("selectSbu", request.getParameter("sbu"));
        mv.addObject("selectPm", request.getParameter("pmName"));
        mv.addObject("selectSubSbu", request.getParameter("subSbu"));
        mv.addObject("enteredDateDiff", request.getParameter("dateDiff"));
        mv.addObject("selectOperator", request.getParameter("operators"));
        return mv;
    }

    public ModelAndView getrolloutSubSbuList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        RollOutService bo = (RollOutService) ctx.getBean("RollOutService");
        String sbuId = request.getParameter("id");
        response.getWriter().println("<option value=''>- All -</option>");
        for (RollOutDTO dTO : ((bo).getsubSbuList(request.getParameter("id")))) {
//            System.out.println("----" + dTO);
            response.getWriter().println("<option value='" + dTO.getSbuId() + "'>" + dTO.getSbu() + "</option>");
        }
        return null;
    }

    public ModelAndView exportRollout(HttpServletRequest request, HttpServletResponse response, RollOutDTO formData) throws Exception {
        ModelAndView mv = null;
        mv = new ModelAndView("/rolloutList");
        final WebApplicationContext ctx = getWebApplicationContext();
        RollOutService rolloutService = (RollOutService) ctx.getBean("RollOutService");
        if (request.getParameter("dateDiff") != null && !request.getParameter("dateDiff").equals("")) {
            formData.setDatediff(request.getParameter("dateDiff"));
        } else {
            formData.setDatediff("31");
            formData.setOperators("5");
        }
        List<RollOutDTO> rollOutReport = rolloutService.getRollOutreport(formData);
        ArrayList entireList = new ArrayList();
        for(int i=0;i<rollOutReport.size();i++){
            ArrayList rowData = new ArrayList();
            rowData.add(new String(""+rollOutReport.get(i).getEmpNumber()+" - "+rollOutReport.get(i).getEmployeeName()));
            rowData.add(new String(""+rollOutReport.get(i).getPrjStartDate()));
            rowData.add(new String(""+rollOutReport.get(i).getPrjEndDate()));
            rowData.add(new String(""+rollOutReport.get(i).getEmpStatus()));
            rowData.add(new String(""+rollOutReport.get(i).getCustName()));
            rowData.add(new String(""+rollOutReport.get(i).getPmName()));
            rowData.add(new String(""+rollOutReport.get(i).getBillStatus()));
            rowData.add(new String(""+rollOutReport.get(i).getDatediff()));
            rowData.add(new String(""+rollOutReport.get(i).getSbu()));
            rowData.add(new String(""+rollOutReport.get(i).getSubSbu()));
            rowData.add(new String(""+rollOutReport.get(i).getBillingHrs()));
            rowData.add(new String(""+rollOutReport.get(i).getBillingRate()));
            entireList.add(rowData);
        }
        //CommonMethods.exportToExcel(response, entireList, "Project_Release_Reoprt.xls", "project_Release", null);
        CommonMethods.exportToExcel(response, entireList, "Project_Release_Reoprt.xls", "associate", null);
        return mv;
    }
}
