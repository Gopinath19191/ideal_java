/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.ProjectCompletionDataDTO;
import com.defiance.ideal.reports.dto.ProjectCompletionFilterDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.ProjectCompletionService;
import com.defiance.ideal.reports.service.ProjectCompletionServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/** 
 *
 * @author 14517
 */
public class ProjectCompletionController extends MultiActionController {

    public ProjectCompletionController() {
    }

    public ModelAndView projectcompletion(HttpServletRequest request, HttpServletResponse response, ProjectCompletionFilterDTO filterData) {
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/projectcompletion");
            final WebApplicationContext ctx = getWebApplicationContext();
            ProjectCompletionService projectService = (ProjectCompletionServiceImpl) ctx.getBean("ProjectCompletionService");
            AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
           // List<AssociateFilterDTO> subSbu = bo.getSubSbu("9,10,11");
            String PES = CommonConfigurations.PES;
            String TS = CommonConfigurations.TS;
            String parentId = PES + ',' + TS;
            if(filterData != null){
            if(!"".equals(filterData.getSbuFilter()) && filterData.getSbuFilter() != null){
                parentId = filterData.getSbuFilter();
            }
            }
            // System.out.println("parent id   :::: "+ parentId);
            List<AssociateFilterDTO> subSbu = bo.getSubSbu(parentId);
            if (filterData.getStatusFilter() == null) {
                filterData.setStatusFilter("e");
            }
            List<ProjectCompletionDataDTO> projectList = projectService.fetchProjectCompletionList(filterData);
            Map<String, String> sbuMap = projectService.getSbuList();
            Map<String, String> statusList = CommonMethods.getPrjtCompStatusList();

            mvc.addObject("statusMap", statusList);
            mvc.addObject("sbuMap", sbuMap);
            mvc.addObject("filterData", filterData);
            mvc.addObject("project", projectList);
            mvc.addObject("subSbu", subSbu);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mvc;

    }

    public ModelAndView getProjectCompletionXL(HttpServletRequest request, HttpServletResponse response, ProjectCompletionFilterDTO filterData) throws Exception {

        final WebApplicationContext ctx = getWebApplicationContext();
        ProjectCompletionService projectService = (ProjectCompletionServiceImpl) ctx.getBean("ProjectCompletionService");
        List<ProjectCompletionDataDTO> projectList = projectService.fetchProjectCompletionList(filterData);

        ArrayList entireList = new ArrayList();
        for (int i = 0; i < projectList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + projectList.get(i).getCode()));
            rowDataList.add(new String("" + projectList.get(i).getName()));
            rowDataList.add(new String("" + projectList.get(i).getPlannedStartDate()));
            rowDataList.add(new String("" + projectList.get(i).getPlannedEndDate()));
            rowDataList.add(new String("" + projectList.get(i).getCustomerName()));
            rowDataList.add(new String("" + projectList.get(i).getSbu()));
            rowDataList.add(new String("" + projectList.get(i).getSubSbu()));
            rowDataList.add(new String("" + projectList.get(i).getStatus()));
            rowDataList.add(new String("" + projectList.get(i).getPoValue()));
            rowDataList.add(new String("" + projectList.get(i).getAccruedSoFar()));
            rowDataList.add(new String("" + projectList.get(i).getCurrency()));
            rowDataList.add(new String("" + projectList.get(i).getCompletion()));

            entireList.add(rowDataList);
        }
        Calendar cal = Calendar.getInstance();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String month = null;
        if (cal.get(Calendar.MONTH) == 0) {
            month = months[(cal.get(Calendar.MONTH))];
        } else {
            month = months[(cal.get(Calendar.MONTH)) - 1];
        }
        String year = Integer.toString(cal.get(Calendar.YEAR));
        CommonMethods.exportToExcel(response, entireList, "FB_project_completion_percentage_as_on_", "Project Completion Percentage", month + "_" + year);
        return null;
    }
}
