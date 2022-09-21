/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.TeamAllocationReportDTO;
import com.defiance.ideal.reports.service.TeamAllocationReportService;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.context.WebApplicationContext;


/**
 *
 * @author 15332
 */
public class TeamAllocationReportController extends MultiActionController{
    public ModelAndView teamAllocationReport(HttpServletRequest request, HttpServletResponse response, TeamAllocationReportDTO formData) {
        
        if(formData.getCustomerName() != null){
            formData.setCustomerId(formData.getCustomerName());
        }
        ModelAndView mv = null;
        //System.out.println("test======>");
        //System.exit(1);
        mv = new ModelAndView("/teamallocationreportList");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        TeamAllocationReportService teamallocationreportService = (TeamAllocationReportService) ctx.getBean("TeamAllocationReportService");
//        System.out.println("date Diff  "+request.getParameter("dateDiff"));
//        if (request.getParameter("dateDiff") != null && !request.getParameter("dateDiff").equals("")) {
//            formData.setDatediff(request.getParameter("dateDiff"));
//            formData.setOperators(request.getParameter("operators"));
//        } else {
//            formData.setDatediff("31");
//            formData.setOperators("5");
//        }
        List<TeamAllocationReportDTO> teamAllocationReport = null;
        
//       if((formData.getProjectName() != null && !"".equals(formData.getProjectName())) || (formData.getSbu() != null && !"".equals(formData.getSbu())) || (formData.getSubSbu() != null && !"".equals(formData.getSubSbu()))
//               || (formData.getCustomerName() != null && !"".equals(formData.getCustomerName())) || (formData.getPmName() != null && !"".equals(formData.getPmName()))){
        teamAllocationReport = teamallocationreportService.getRollOutreport(formData);
//       }
        List<TeamAllocationReportDTO> projectList = teamallocationreportService.getprojectList(formData);
        formData.setSbuId(CommonConfigurations.SBU);
        List<TeamAllocationReportDTO> sbuList = teamallocationreportService.getSbuList(formData);
        List<TeamAllocationReportDTO> pmNameList = teamallocationreportService.getPmNameList();
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
        List<TeamAllocationReportDTO> subSbuList = teamallocationreportService.getsubSbuList(parentId);
        List<TeamAllocationReportDTO>customerList = teamallocationreportService.getcustomerList(formData);
       
        mv.addObject("rolloutList", teamAllocationReport);
        mv.addObject("projectLists", projectList);
        mv.addObject("sbuList", sbuList);
        mv.addObject("pmNameList", pmNameList);
        mv.addObject("subSbuList", subSbuList);
        mv.addObject("customerList",customerList);
        mv.addObject("selectPrj", request.getParameter("projectName"));
        mv.addObject("selectSbu", request.getParameter("sbu"));
        mv.addObject("selectPm", request.getParameter("pmName"));
        mv.addObject("selectSubSbu", request.getParameter("subSbu"));
        mv.addObject("selectCustomer",formData.getCustomerName());
//        mv.addObject("enteredDateDiff", request.getParameter("dateDiff"));
//        mv.addObject("selectOperator", request.getParameter("operators"));
        return mv;
    }
    
    public ModelAndView getteamallocationSubSbuList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        TeamAllocationReportService bo = (TeamAllocationReportService) ctx.getBean("TeamAllocationReportService");
        String sbuId = request.getParameter("id");
        response.getWriter().println("<option value=''>- Select -</option>");
        for (TeamAllocationReportDTO dTO : ((bo).getsubSbuList(request.getParameter("id")))) {
//            System.out.println("----" + dTO);
            response.getWriter().println("<option value='" + dTO.getSbuId() + "'>" + dTO.getSbu() + "</option>");
        }
        return null;
    }
    
   public ModelAndView exportTeamAllocationReport(HttpServletRequest request, HttpServletResponse response, TeamAllocationReportDTO formData) throws Exception {
        ModelAndView mv = null;
       // System.out.println("test======>1");
        //System.exit(1);
        mv = new ModelAndView("/teamallocationreportList");
        final WebApplicationContext ctx = getWebApplicationContext();
        TeamAllocationReportService teamallocationreportService = (TeamAllocationReportService) ctx.getBean("TeamAllocationReportService");
        //System.out.println("test======>2");
//        if (request.getParameter("dateDiff") != null && !request.getParameter("dateDiff").equals("")) {
//            formData.setDatediff(request.getParameter("dateDiff"));
//        } else {
//            formData.setDatediff("31");
//            formData.setOperators("5");
//        }
        List<TeamAllocationReportDTO> teamAllocationReport = teamallocationreportService.getRollOutreport(formData);
        ArrayList entireList = new ArrayList();
            for(int i=0;i<teamAllocationReport.size();i++){
            ArrayList rowData = new ArrayList();
            rowData.add(new String(""+teamAllocationReport.get(i).getEmpNumber()));
            rowData.add(new String(""+teamAllocationReport.get(i).getEmployeeName()));
            rowData.add(new String(""+teamAllocationReport.get(i).getSbu()));
            rowData.add(new String(""+teamAllocationReport.get(i).getSubSbu()));
            rowData.add(new String(""+teamAllocationReport.get(i).getRmName()));
            rowData.add(new String(""+teamAllocationReport.get(i).getCustomerName()));
            rowData.add(new String(""+teamAllocationReport.get(i).getProjectCode()));
            rowData.add(new String(""+teamAllocationReport.get(i).getProjectName()));
            rowData.add(new String(""+teamAllocationReport.get(i).getPrjName()));
            rowData.add(new String(""+teamAllocationReport.get(i).getPrjStartDate()));
            rowData.add(new String(""+teamAllocationReport.get(i).getPrjEndDate()));
            rowData.add(new String(""+teamAllocationReport.get(i).getDatediff()));
            rowData.add(new String(""+teamAllocationReport.get(i).getEmpStatus()));
            rowData.add(new String(""+teamAllocationReport.get(i).getPmName()));
            rowData.add(new String(""+teamAllocationReport.get(i).getRole()));
            rowData.add(new String(""+teamAllocationReport.get(i).getBillingDate()));
            rowData.add(new String(""+teamAllocationReport.get(i).getWorkLocation()));
            //rowData.add(new String(""+teamAllocationReport.get(i).getSubSbu()));
            //rowData.add(new String(""+teamAllocationReport.get(i).getPrjStartDate()));
            //rowData.add(new String(""+teamAllocationReport.get(i).getPrjEndDate()));
//            rowData.add(new String(""+teamAllocationReport.get(i).getDatediff()));
//            rowData.add(new String(""+teamAllocationReport.get(i).getCustomerName()));
//            rowData.add(new String(""+teamAllocationReport.get(i).getProjectCode()));
//            rowData.add(new String(""+teamAllocationReport.get(i).getProjectName()));
//            rowData.add(new String(""+teamAllocationReport.get(i).getProjectStatus()));
//            rowData.add(new String(""+teamAllocationReport.get(i).getPmName()));
            entireList.add(rowData);
    }
        //CommonMethods.exportToExcel(response, entireList, "Project_Release_Reoprt.xls", "project_Release", null);
          CommonMethods.exportToExcel(response, entireList, "Project_Allocation_Report.xls", "allocation", null);
          return mv;
    }
   
//   public ModelAndView loadCustList(HttpServletRequest request, HttpServletResponse response)throws Exception{
//       ModelAndView mv = null;
//       PrintWriter out = response.getWriter();
//       try{
//           final WebApplicationContext ctx = getWebApplicationContext();
//        TeamAllocationReportService teamallocationreportService = (TeamAllocationReportService) ctx.getBean("TeamAllocationReportService");
//           TeamAllocationReportDTO formData = new TeamAllocationReportDTO();
//           String projectId = request.getParameter("projectId");
//           formData.setProjectId(projectId);
//         //String customerId =request.getParameter("customerId");
//         //formData.setCustomerId(customerId);
//           List<TeamAllocationReportDTO> customerList = teamallocationreportService.getcustomerList(formData);
//         //List<TeamAllocationReportDTO> projectList = teamallocationreportService.getprojectList(formData);
//           Iterator itr = customerList.iterator();
//           out.println("<option value=''>--Select--</option>");
//           while(itr.hasNext()){
//               TeamAllocationReportDTO dto = (TeamAllocationReportDTO)itr.next();
//               out.println("<option value="+dto.getCustomerId()+">"+dto.getCustomerName()+"</option>");
//           }
//       }catch(Exception e){
//           e.printStackTrace();
//       }
//       return mv;
//   }
   
   
 public ModelAndView loadProjectList(HttpServletRequest request, HttpServletResponse response)throws Exception{
       ModelAndView mv = null;
       PrintWriter out = response.getWriter();
       try{
           final WebApplicationContext ctx = getWebApplicationContext();
           TeamAllocationReportService teamallocationreportService = (TeamAllocationReportService) ctx.getBean("TeamAllocationReportService");
           TeamAllocationReportDTO formData = new TeamAllocationReportDTO();
           String customerId =request.getParameter("customerId");
           System.out.println("customerId : "+customerId);
           formData.setCustomerId(customerId);
         //List<TeamAllocationReportDTO> customerList = teamallocationreportService.getcustomerList(formData);
           List<TeamAllocationReportDTO> projectList = teamallocationreportService.getprojectList(formData);
           Iterator itr = projectList.iterator();
           out.println("<option value=''>--Select--</option>");
           while(itr.hasNext()){
           TeamAllocationReportDTO dto = (TeamAllocationReportDTO)itr.next();
           out.println("<option value="+dto.getProjectId()+">"+dto.getPrjName()+"</option>");
               
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       return mv;
   }
    
}
