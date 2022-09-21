/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.BillableHoursDataDTO;
import com.defiance.ideal.reports.dto.BillableHoursFilterDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.BillableHoursService;
import com.defiance.ideal.reports.service.BillableHoursServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.Calendar;
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
public class BillableHoursController extends MultiActionController {

    public BillableHoursController() {
    }

    public ModelAndView billablehoursreport(HttpServletRequest request, HttpServletResponse response, BillableHoursFilterDTO filterData) {
        ModelAndView mvc = null;
        Calendar calndr = Calendar.getInstance();
        calndr.get(Calendar.MONTH);
        calndr.get(Calendar.YEAR);
        try {

            final WebApplicationContext ctx = getWebApplicationContext();
            BillableHoursService billableService = (BillableHoursServiceImpl) ctx.getBean("BillableHoursService");
            Map<String, String> joinedMonthsMap = CommonMethods.getMonthsList();
            Map<Integer, Integer> joinedYearsMap = CommonMethods.getYearsList(2);
            Map<String, String> sbuMap = billableService.getSbuList();
            Map<String, String> custMap = billableService.getCustomerList();
            List<BillableHoursFilterDTO> pro = null;
            AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
           // List<AssociateFilterDTO> subSbu = bo.getSubSbu("9,10,11");
          //  List<AssociateFilterDTO> subSbu = bo.getSubSbu("2,5");
            String PES = CommonConfigurations.PES;
            String TS = CommonConfigurations.TS;
            String parentId = PES + ',' + TS;
            List<AssociateFilterDTO> subSbu = bo.getSubSbu(parentId);
                if (filterData.getSbuFilter() == null || filterData.getSbuFilter().equals("")) {

                pro = billableService.getProjectList("","");
            } else {

                pro = billableService.getProjectList(filterData.getSbuFilter(),filterData.getSubSbu());
            }
            System.out.println("###############" + sbuMap);
            Map<String, String> billableMap = CommonMethods.getBillableList();
            Map<String, String> locationMap = CommonMethods.getLocationList();
            Map<String, String> pricingMap = CommonMethods.getPricingList();
            Map<String, String> conditionMap = CommonMethods.getConditionList();
            if (filterData.getBillableFilter() == null) {
                filterData.setConditionFilter("1");
                filterData.setBillableFilter("0");
            }
//            if (null != filterData.getNameFilter()) {
//                if(!filterData.getNameFilter().equals("")){
//                String delimiter = "-";
//                String[] temp;
//                temp = filterData.getNameFilter().split(delimiter);
//                filterData.setSplitName(temp[1].trim());
//                }
//                // System.out.println("*******"+temp[1]);
//            }
            List<BillableHoursDataDTO> billableData = null;
            if (filterData.getHiddenFilter() == null) {
            } else {
                billableData = billableService.fetchBillableHoursData(filterData);
            }
            mvc = new ModelAndView("/billed");
            mvc.addObject("joinedMonthsMap", joinedMonthsMap);
            mvc.addObject("joinedYearsMap", joinedYearsMap);
            mvc.addObject("billable", billableMap);
            mvc.addObject("locationMap", locationMap);
            mvc.addObject("pricingMap", pricingMap);
            mvc.addObject("sbuMap", sbuMap);
            mvc.addObject("custMap", custMap);
            mvc.addObject("billableData", billableData);
            mvc.addObject("condition", conditionMap);
            mvc.addObject("filterData", filterData);
            mvc.addObject("project", pro);
            mvc.addObject("subSbu", subSbu);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView getsbuajax(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mvc = new ModelAndView("/ajax");
        final WebApplicationContext ctx = getWebApplicationContext();
        BillableHoursService billableService = (BillableHoursServiceImpl) ctx.getBean("BillableHoursService");
        String id = request.getParameter("id");
        String subid = request.getParameter("subId");
        List<BillableHoursFilterDTO> pro = billableService.getProjectList(id,subid);
//           String mainStr="";
//        for(int i=0;i<pro.size();i++){
//            //System.out.println("%%%%%%%%%%%"+projectList.size()+"******"+i);
//            if(i!=pro.size()-1){
//            mainStr=mainStr+pro.get(i).getProjId()+"|";
//            }else{
//                mainStr=mainStr+pro.get(i).getProjId();
//            }
//        }

        mvc.addObject("projectList", pro);
        return mvc;
    }

    public ModelAndView billablehoursexport(HttpServletRequest request, HttpServletResponse response, BillableHoursFilterDTO filterData) throws Exception {

        final WebApplicationContext ctx = getWebApplicationContext();
        BillableHoursService billableService = (BillableHoursServiceImpl) ctx.getBean("BillableHoursService");
        List<BillableHoursDataDTO> billableData = null;
        if (filterData.getHiddenFilter() == null) {
        } else {
            billableData = billableService.fetchBillableHoursData(filterData);
        }


        ArrayList entireList = new ArrayList();
        for (int i = 0; i < billableData.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + billableData.get(i).getCode()));
            rowDataList.add(new String("" + billableData.get(i).getName()));
            rowDataList.add(new String("" + billableData.get(i).getManager()));
            rowDataList.add(new String("" + billableData.get(i).getCustomer()));
            rowDataList.add(new String("" + billableData.get(i).getPricing()));
            rowDataList.add(new String("" + billableData.get(i).getSbu()));
            rowDataList.add(new String("" + billableData.get(i).getSubSbu()));
            rowDataList.add(new String("" + billableData.get(i).getLocation()));
            rowDataList.add(new String("" + billableData.get(i).getEmpId()));
            rowDataList.add(new String("" + billableData.get(i).getEmpName()));
            rowDataList.add(new String("" + billableData.get(i).getMonth()));
            rowDataList.add(new String("" + billableData.get(i).getYear()));
            if (billableData.get(i).getWorkedHrs() == null || billableData.get(i).getWorkedHrs() == "") {
                rowDataList.add(new String("" + billableData.get(i).getWorkedHrs()));
            } else {
                rowDataList.add(new String("" + billableData.get(i).getWorkedHrs().replace(":", ".").substring(0, billableData.get(i).getWorkedHrs().length() - 3)));
            }
            if (billableData.get(i).getApprovedHrs() == null || billableData.get(i).getApprovedHrs() == "") {
                rowDataList.add(new String("" + billableData.get(i).getApprovedHrs()));
            } else {
                rowDataList.add(new String("" + billableData.get(i).getApprovedHrs().replace(":", ".").substring(0, billableData.get(i).getApprovedHrs().length() - 3)));
            }
            rowDataList.add(new String("" + billableData.get(i).getAccrued().replace(":", ".")));
            rowDataList.add(new String("" + billableData.get(i).getInvoicedHrs()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "utilization_report_project_associate.xls", "Utilization Report", null);
        return null;
    }
}
