/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.VarianceDataDTO;
import com.defiance.ideal.reports.dto.VarianceFilterDTO;
import com.defiance.ideal.reports.service.VarianceService;
import com.defiance.ideal.reports.service.VarianceServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16364
 */
public class VarianceController extends MultiActionController {
    public VarianceController() {
    }

    public ModelAndView ajaxsearch(HttpServletRequest request, HttpServletResponse response, VarianceFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            VarianceService earnedService = (VarianceServiceImpl) ctx.getBean("VarianceService");
            String empVal = request.getParameter("q");
           // System.out.println("fefefe"+empVal);
//            Map<String, String> empRes = earnedService.getSearchList(empVal);
            List<VarianceDataDTO> empRes = earnedService.getSearchList(empVal);
           // System.out.println("dgdgdg"+empRes.size());
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

  
    public ModelAndView variance(HttpServletRequest request, HttpServletResponse response, VarianceFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/variance");
         HttpSession session = request.getSession();
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        List<VarianceDataDTO> varianceRecord = null;
          List<VarianceDataDTO> projectList = null;
        String pmId="";
        pmId = (String)session.getAttribute("EMP_ID");
         Map<String, String> monthsList = CommonMethods.getMonthsList();
        Map<Integer, Integer> yearsList = CommonMethods.getYearsList(2);
          Calendar cal = Calendar.getInstance();
          System.out.println("Current Month is : " + (cal.get(Calendar.MONTH) - 1));
        try {
            if (null == filterData.getFilter_month()) {
               
                filterData.setFilter_month((months[cal.get(Calendar.MONTH)]));
            }
            if (null == filterData.getFilter_year()) {
                filterData.setFilter_year(Integer.toString(cal.get(Calendar.YEAR)));
            }
            filterData.setEmployeeId(pmId);
            filterData.setPmId(pmId);
             filterData.setProjectId(filterData.getProjectName());
              filterData.setEmpId(filterData.getEmpName());
               String empId=request.getParameter("empName");
               System.out.println("selected employee id*** "+empId);
               System.out.println("project id---"+filterData.getProjectId());
               filterData.setEmpId(empId);
            final WebApplicationContext ctx = getWebApplicationContext();
            VarianceService earnedService = (VarianceServiceImpl) ctx.getBean("VarianceService");
            Map<String, String> sbuMap = earnedService.getSbuList();
           
            varianceRecord = earnedService.getVarianceRecord(filterData);
             projectList = earnedService.getProjectList(filterData);
           
              
            mvc.addObject("sbuMap", sbuMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("requsetorDTO", filterData);
         mvc.addObject("projectList", projectList);
          mvc.addObject("employeeList", this.getEmployeeList(filterData));
        mvc.addObject("filterData", filterData);
        mvc.addObject("empName", filterData.getEmployee_search());
        mvc.addObject("variance", varianceRecord);
        mvc.addObject("monthsList", monthsList);
        mvc.addObject("yearsList", yearsList);
        return mvc;
    }

    public ModelAndView getVarianceXL(HttpServletRequest request, HttpServletResponse response, VarianceFilterDTO filterData) throws Exception {
        try {
            System.out.println("inside getVarianceXL ");
             HttpSession session = request.getSession();
            final WebApplicationContext ctx = getWebApplicationContext();
            VarianceService earnedService = (VarianceServiceImpl) ctx.getBean("VarianceService");
             String pmId="";
        pmId = (String)session.getAttribute("EMP_ID");
            filterData.setEmployeeId(pmId);
            filterData.setPmId(pmId);
             filterData.setProjectId(filterData.getProjectName());
              filterData.setEmpId(filterData.getEmpName());
               String empId=request.getParameter("empName");
               System.out.println("selected employee id*** "+empId);
               System.out.println("project id---"+filterData.getProjectId());
               filterData.setEmpId(empId);
            List<VarianceDataDTO> varianceRecord = earnedService.getVarianceRecord(filterData);
              Iterator itr=varianceRecord.iterator();
while (itr.hasNext()) {
                 VarianceDataDTO dto=(VarianceDataDTO)itr.next();
                    String projects=dto.getTotalAccruedHours();
                    System.out.println("project anme--"+dto.getProject_id());
                    System.out.println("worked hours--"+dto.getTotalWorkedHours());
                    System.out.println("approved hours --"+dto.getTotalApprovedHours());
                    System.out.println("accrued hours---"+projects);
             }
            ArrayList entireList = new ArrayList();
            for (int i = 0; i < varianceRecord.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + varianceRecord.get(i).getProject_id()));
                rowDataList.add(new String("" + varianceRecord.get(i).getEmployeeName()));
                  rowDataList.add(new String("" + varianceRecord.get(i).getMonthName()));
                rowDataList.add(new String("" + varianceRecord.get(i).getTotalWorkedHours()));
                rowDataList.add(new String("" + varianceRecord.get(i).getTotalApprovedHours()));
                rowDataList.add(new String("" + varianceRecord.get(i).getTotalAccruedHours()));
              
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "variance_report.xls", "Variance Report", null);
        } catch(Exception e) {
             e.printStackTrace();
        }
        return null;
    }
public synchronized ModelAndView getEmployeeList(HttpServletRequest request, HttpServletResponse response, VarianceFilterDTO appDTO) throws Exception {
        try {

            HttpSession session = request.getSession();
            String projectId = request.getParameter("prjId");
            System.out.println("projectId  "+projectId);
           // String listId = request.getParameter("selectedList");
          //  String employeeId = (String) session.getAttribute("accessId");
           String employeeId= (String)session.getAttribute("EMP_ID");
            System.out.println("employeeId @@@"+employeeId);
            appDTO.setEmployeeId(employeeId);
            appDTO.setProjectId(projectId);
            
            response.getWriter().println("<option value=''>-   Select Employee  -</option>");
            if (projectId != "") {
                for (VarianceDataDTO dTO : this.getEmployeeList(appDTO)) {
                    if (dTO.getEmpId() != null) {
                        response.getWriter().println("<option title = '" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "' value='" + dTO.getEmpId() + "'>" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "</option>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public synchronized List<VarianceDataDTO> getEmployeeList(VarianceFilterDTO appDTO) {
        List<VarianceDataDTO> employeeList = ((VarianceServiceImpl) getWebApplicationContext().getBean("VarianceService")).getEmployeeList(appDTO);
        return employeeList;
    }

 

}
