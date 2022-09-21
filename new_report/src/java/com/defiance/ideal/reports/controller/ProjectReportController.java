/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.defiance.ideal.reports.dto.ProjectDetails;
import com.defiance.ideal.reports.dto.ProjectReportFilterDTO;
import com.defiance.ideal.reports.service.ProjectReportService;
import com.defiance.ideal.reports.service.ProjectReportServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;

/**
 *
 * @author 15261
 */
public class ProjectReportController extends MultiActionController {

    public ModelAndView project_search(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/ajax");
        String projectString = request.getParameter("q");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            ProjectReportService projectReportService = (ProjectReportServiceImpl) ctx.getBean("ProjectReportService");
            ProjectDetails dto = new ProjectDetails();
            dto.setProject_code(projectString);
            dto.setProject_name(projectString);
            List<ProjectDetails> projectDetails = projectReportService.getProjectDetails(dto);
            mvc.addObject("projectDetails", projectDetails);
            request.setAttribute("projectDetailsList", projectDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView projectReport(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/projectReport");
        System.out.println("ProjectReport Method Called");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            ProjectReportService projectReportService = (ProjectReportServiceImpl) ctx.getBean("ProjectReportService");
            String projectString = request.getParameter("project_search");
            String projectUniqueId = request.getParameter("project_id");

            if (projectString != null) {
                String projectCode = null;
                ProjectReportFilterDTO dto = new ProjectReportFilterDTO();
                StringTokenizer str = new StringTokenizer(projectString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    projectCode = str.nextToken().trim();
                }
                // Get project details.
                dto.setProjectCode(projectCode);
                dto.setProjectId(projectUniqueId);
                List<ProjectDetails> list = projectReportService.getEntireProjectDetail(dto);
                ProjectDetails detail = new ProjectDetails();
                String projectId = null;
                if (list == null || list.size() == 0) {
                    mvc.addObject("projectString", projectString);
                    mvc.addObject("projectUniqueId", projectUniqueId);
                    return mvc;
                }
                detail = list.get(0);
                mvc.addObject("project_start_date", detail.getPlanned_start_date());
                mvc.addObject("currency_code", detail.getCurrency_code());
                mvc.addObject("project_end_date", detail.getPlanned_end_date());
                mvc.addObject("project_manager_name", detail.getFirst_name() + " " + detail.getLast_name());
                mvc.addObject("project_status", detail.getProject_status());
                mvc.addObject("configuration_value", detail.getConfiguration_value());
                mvc.addObject("project_approve_status", detail.getProjectStatus());
                projectId = detail.getProject_id();


                dto.setProjectId(projectId);

                //Get PO Value

                dto.setProjectCode(projectCode);
                List<ProjectDetails> billableAmtList = projectReportService.getBillableAmtSum(dto);
                String billableAmtStr = "";
                if (billableAmtList != null && billableAmtList.size() > 0) {
                    billableAmtStr = billableAmtList.get(0).getBillable_amt_sum();
                }
                mvc.addObject("billableAmtStr", billableAmtStr);

                // Get PO Value

                List<ProjectDetails> poValue = projectReportService.getPoValue(dto);
                String poValueStr = "";
                if (poValueStr != null && poValue.size() > 0) {
                    poValueStr = poValue.get(0).getPoValue();
                }
                mvc.addObject("poValueStr", poValueStr);

                // Get Customer name
                list = projectReportService.getCustomerName(dto);
                detail = new ProjectDetails();
                if (list != null && list.size() > 0) {
                    detail = list.get(0);
                }
                mvc.addObject("customer_name", detail.getCustomer_name());

                //Get associate details.
                list = projectReportService.getTeamAllocationList(dto);
                if (list != null && list.size() > 0) {
                    list = removeDuplicateAllocatedHrs(list);
                }
                mvc.addObject("associateList", list);
                mvc.addObject("projectString", projectString);
                mvc.addObject("projectUniqueId", projectUniqueId);


                // Get Total Effort
                list = projectReportService.getTotalEffort(dto);
                detail = new ProjectDetails();
                if (list != null && list.size() > 0) {
                    detail = list.get(0);
                }
                mvc.addObject("total_effort", detail.getTotal_effort());


                // Get Accrued Effort
                list = projectReportService.getAccruedEffort(dto);
                detail = new ProjectDetails();
                if (list != null && list.size() > 0) {
                    detail = list.get(0);
                } else {
                    detail.setEfforts_uom("");
                }
                mvc.addObject("accrued_effort", detail.getAccrued_effort());
                mvc.addObject("efforts_uom", detail.getEfforts_uom());


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView projectReportExport(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("projectReportExport Method Called");
        try {
            String projectString = request.getParameter("project_search");
            String projectUniqueId = request.getParameter("project_id");
            if (projectString != null) {
                double value, decimal;
                StringTokenizer st = null;
                java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
                nf.setMaximumFractionDigits(2);
                nf.setGroupingUsed(false);
                String projectCode = null;
                String hrs = null;
                ArrayList entireList = new ArrayList();
                ArrayList rowDataList;
                ProjectReportFilterDTO dto = new ProjectReportFilterDTO();
                StringTokenizer str = new StringTokenizer(projectString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    projectCode = str.nextToken().trim();
                }
                final WebApplicationContext ctx = getWebApplicationContext();
                ProjectReportService projectReportService = (ProjectReportServiceImpl) ctx.getBean("ProjectReportService");

                // Get project details.
                dto.setProjectCode(projectCode);
                dto.setProjectId(projectUniqueId);
                List<ProjectDetails> list = projectReportService.getEntireProjectDetail(dto);
                ProjectDetails detail = new ProjectDetails();
                String projectId = null, projectApproveStatus = null;
                String customerName = null, projectManagerName = null;
                String projectStartDate = null, projectEndDate = null, projectStatus = null;
                String totalEfforts = null, accruedEfforts = null;
                if (list != null && list.size() > 0) {
                    detail = list.get(0);
                    projectStartDate = detail.getPlanned_start_date();
                    projectEndDate = detail.getPlanned_end_date();
                    projectManagerName = detail.getFirst_name() + " " + detail.getLast_name();
                    projectStatus = detail.getConfiguration_value();
                    projectId = detail.getProject_id();
                    projectApproveStatus = detail.getProjectStatus();
                }

                dto.setProjectId(projectId);

                // Get Customer name
                list = projectReportService.getCustomerName(dto);
                detail = new ProjectDetails();
                if (list != null && list.size() > 0) {
                    detail = list.get(0);
                }
                customerName = detail.getCustomer_name();

                //Get associate details.
                List<ProjectDetails> associateList = projectReportService.getTeamAllocationList(dto);

                // Get Total Effort
                list = projectReportService.getTotalEffort(dto);
                detail = new ProjectDetails();
                if (list != null && list.size() > 0) {
                    detail = list.get(0);
                }
                totalEfforts = detail.getTotal_effort();


                // Get Accrued Effort
                list = projectReportService.getAccruedEffort(dto);
                detail = new ProjectDetails();
                if (list != null && list.size() > 0) {
                    detail = list.get(0);
                }
                accruedEfforts = detail.getAccrued_effort();
                String efforts_uom = detail.getEfforts_uom();

                //Get PO Value

                dto.setProjectCode(projectId);
                List<ProjectDetails> billableAmtList = projectReportService.getBillableAmtSum(dto);
                String billableAmtStr = "";
                if (billableAmtList != null && billableAmtList.size() > 0) {
                    billableAmtStr = billableAmtList.get(0).getBillable_amt_sum();
                }

                // Get PO Value

                List<ProjectDetails> poValue = projectReportService.getPoValue(dto);
                String poValueStr = "";
                if (poValueStr != null && poValue.size() > 0) {
                    poValueStr = poValue.get(0).getPoValue();
                }


                // Set all data in Arraylist and send to exportToExcel() function.

                rowDataList = new ArrayList();
                rowDataList.add(new String("Project Code/Name"));
                rowDataList.add(projectString);
                rowDataList.add(new String("Customer Name"));
                rowDataList.add(customerName);
                rowDataList.add(new String("Approval Status"));
                rowDataList.add(projectApproveStatus);
                entireList.add(rowDataList);

                rowDataList = new ArrayList();
                rowDataList.add(new String("Project Manager"));
                rowDataList.add(projectManagerName);
                rowDataList.add(new String("Project Start Date"));
                rowDataList.add(projectStartDate);
                rowDataList.add(new String("Project End Date"));
                rowDataList.add(projectEndDate);
                entireList.add(rowDataList);

                rowDataList = new ArrayList();
                rowDataList.add(new String("Project Status"));
                rowDataList.add(projectStatus);
                rowDataList.add(new String("Total Efforts"));
                hrs = totalEfforts;
                if (hrs != null) {
                    value = Double.valueOf(hrs);
                    st = new StringTokenizer(hrs, ".");
                    if (st.countTokens() > 1) {
                        st.nextToken();
                        decimal = Double.valueOf(st.nextToken());
                        if (decimal != 0) {
                            nf.setMinimumFractionDigits(2);
                        } else {
                            nf.setMinimumFractionDigits(0);
                        }
                    }
                    rowDataList.add("" + nf.format(value));
                }
                rowDataList.add(new String("Accrued Efforts"));
                hrs = accruedEfforts;
                if (hrs != null) {
                    value = Double.valueOf(hrs);
                    st = new StringTokenizer(hrs, ".");
                    if (st.countTokens() > 1) {
                        st.nextToken();
                        decimal = Double.valueOf(st.nextToken());
                        if (decimal != 0) {
                            nf.setMinimumFractionDigits(2);
                        } else {
                            nf.setMinimumFractionDigits(0);
                        }
                    }
                    rowDataList.add("" + nf.format(value) + " (" + efforts_uom + ")");
                }
                entireList.add(rowDataList);

                rowDataList = new ArrayList();
                rowDataList.add(new String("PO Value"));
                rowDataList.add(poValueStr);
                rowDataList.add(new String("Total Accrued Amount"));
                rowDataList.add(billableAmtStr);
                rowDataList.add(new String(""));
                rowDataList.add(new String(""));
                entireList.add(rowDataList);

                //empty row.
                rowDataList = new ArrayList();
                rowDataList.add(new String(""));
                rowDataList.add(new String(""));
                rowDataList.add(new String(""));
                rowDataList.add(new String(""));
                rowDataList.add(new String(""));
                rowDataList.add(new String(""));
                rowDataList.add(new String(""));
                entireList.add(rowDataList);

                //header row.
                rowDataList = new ArrayList();
                rowDataList.add(new String("Associate Id"));
                rowDataList.add(new String("Associate Name"));
                rowDataList.add(new String("Allocation Start Date"));
                rowDataList.add(new String("Allocation End Date"));
                rowDataList.add(new String("Role"));
                rowDataList.add(new String("Allocated Hours"));
                rowDataList.add(new String("Billed " + efforts_uom));
                entireList.add(rowDataList);

                java.text.SimpleDateFormat sdf = null;
                java.util.Date dt = null;

                for (int i = 0; i < associateList.size(); i++) {
                    rowDataList = new ArrayList();
                    rowDataList.add(new String("" + associateList.get(i).getEmployee_number()));
                    rowDataList.add(new String("" + associateList.get(i).getFirst_name() + " " + associateList.get(i).getLast_name()));
                    sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    dt = sdf.parse(associateList.get(i).getStart_date());
                    sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                    rowDataList.add("" + sdf.format(dt));
                    sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    dt = sdf.parse(associateList.get(i).getEnd_date());
                    sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                    rowDataList.add("" + sdf.format(dt));
                    rowDataList.add(new String("" + associateList.get(i).getRole_description()));
                    hrs = associateList.get(i).getAllocated_hours();
                    if (hrs != null) {
                        value = Double.valueOf(hrs);
                        st = new StringTokenizer(hrs, ".");
                        if (st.countTokens() > 1) {
                            st.nextToken();
                            decimal = Double.valueOf(st.nextToken());
                            if (decimal != 0) {
                                nf.setMinimumFractionDigits(2);
                            } else {
                                nf.setMinimumFractionDigits(0);
                            }
                        }
                        rowDataList.add("" + nf.format(value));
                    }
                    hrs = associateList.get(i).getBilling_hours();
                    if (hrs != null) {
                        value = Double.valueOf(hrs);
                        st = new StringTokenizer(hrs, ".");
                        if (st.countTokens() > 1) {
                            st.nextToken();
                            decimal = Double.valueOf(st.nextToken());
                            if (decimal != 0) {
                                nf.setMinimumFractionDigits(2);
                            } else {
                                nf.setMinimumFractionDigits(0);
                            }
                        }
                        rowDataList.add("" + nf.format(value));
                    }
                    entireList.add(rowDataList);
                }
                CommonMethods.exportToExcel(response, entireList, "projectReport.xls", "Project Report", null);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<ProjectDetails> removeDuplicateAllocatedHrs(List<ProjectDetails> list) {

        int count = 0;
        ProjectDetails pdMain = null, pdSub = null, pdData = null;
        for (int i = 0; i < list.size(); i++) {
            pdMain = list.get(i);
            for (int j = 0; j < list.size(); j++) {
                pdSub = list.get(j);
                if (pdMain.getEmployee_id().equalsIgnoreCase(pdSub.getEmployee_id())) {
                    count++;
                }
                if (count == 2) { //Means, employee id is repeating.So, reset the allocated hrs to zero for remaining records.
                    for (int x = j; x < list.size(); x++) {
                        pdData = list.get(x);
                        if (pdData.getEmployee_id().equalsIgnoreCase(pdSub.getEmployee_id())) {
                            pdData.setAllocated_hours("0");
                            list.set(x, pdData);
                        }
                    }
                    break;
                }
            }
            count = 0;
        }
        return list;
    }
}
