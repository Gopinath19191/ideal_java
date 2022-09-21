
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.controller;

import com.defiance.ideal.travelplan.dao.AuthenticateDaoImpl;
import com.defiance.ideal.travelplan.dao.CommonDaoImpl;
import com.defiance.ideal.travelplan.dto.AuthenticateDto;
import com.defiance.ideal.travelplan.dto.CommonDto;
import com.defiance.ideal.travelplan.utils.CommonConfigurations;
import com.defiance.ideal.travelplan.utils.CommonFunctions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14578
 */
public class AuthenticationController extends MultiActionController {

    private ModelAndView mv;

    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        mv = new ModelAndView("/unauthorised");
        System.out.println("here it comes");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        AuthenticateDaoImpl daoIndia = (AuthenticateDaoImpl) ctx.getBean("AuthenticateDao");
        AuthenticateDto data = new AuthenticateDto();

        if (request.getParameter("empId") == null) {
            mv.addObject("ErrorMessage", "Employee Id Not Present");
        } else {
            data = (AuthenticateDto) daoIndia.getUserDetails(request.getParameter("empId"));
            System.out.println("Employee Id = " + data.getEmpId());
        }

        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            data.setModuleId(request.getParameter("modId"));
            System.out.println("Module Id = " + data.getModuleId());
        }
        session.setAttribute("employeeId", data.getEmpId());
        session.setAttribute("employeeName", data.getEmployeeName());
        session.setAttribute("empStatus", data.getEmployment_status());
        session.setAttribute("moduleId", data.getModuleId());

        session.setAttribute("domesticModId", CommonConfigurations.domesticModId);
        session.setAttribute("internationalModId", CommonConfigurations.internationalModId);
        session.setAttribute("localConvModId", CommonConfigurations.localConvModId);
        session.setAttribute("rmModId", CommonConfigurations.rmModId);
        session.setAttribute("buhModId", CommonConfigurations.buhModId);
        session.setAttribute("mdModId", CommonConfigurations.mdModId);
        session.setAttribute("finModId", CommonConfigurations.finModId);
        session.setAttribute("cfoModId", CommonConfigurations.cfoModId);
        session.setAttribute("adminModId", CommonConfigurations.adminModId);
        session.setAttribute("treasuryModId", CommonConfigurations.treausryModId);


        //String currEmpStatus = CommonFunctions.getCurrentEmpStatus(data.getEmployment_status(),data.getTransferred_country_id());
        String currEmpStatus="I";
        session.setAttribute("currEmpStatus", currEmpStatus);
        //boolean authenticated = daoIndia.authenticate(data);
        boolean authenticated = true;
        if (authenticated) {
            if(data.getModuleId().equals(CommonConfigurations.domesticModId)) {
                mv = new ModelAndView("redirect:getList.htm");
            }else if(data.getModuleId().equals(CommonConfigurations.internationalModId) || data.getModuleId().equals(CommonConfigurations.localConvModId)){
                mv = new ModelAndView("redirect:getDashBoardList.htm?page=1");
            } else if(data.getModuleId().equals(CommonConfigurations.rmModId)) {
                mv = new ModelAndView("redirect:getApprovalDashBoardList.htm?page=1&approveType=pending");
//                mv = new ModelAndView("redirect:listPendingApprovers.htm?approver_type=rm&type=pending");
            } else if(data.getModuleId().equals(CommonConfigurations.buhModId)) {
                mv = new ModelAndView("redirect:getApprovalDashBoardList.htm?page=1&approveType=pending");
//                mv = new ModelAndView("redirect:listPendingApprovers.htm?approver_type=buh&type=pending");
            } else if(data.getModuleId().equals(CommonConfigurations.mdModId)) {
                mv = new ModelAndView("redirect:getApprovalDashBoardList.htm?page=1&approveType=pending");
            } else if(data.getModuleId().equals(CommonConfigurations.finModId)) {
                mv = new ModelAndView("redirect:getApprovalDashBoardList.htm?page=1&approveType=pending");
//                mv = new ModelAndView("redirect:listPendingApprovers.htm?approver_type=finance&type=pending");
            } else if(data.getModuleId().equals(CommonConfigurations.cfoModId)) {
                mv = new ModelAndView("redirect:getApprovalDashBoardList.htm?page=1&approveType=pending");
            } 
            else if(data.getModuleId().equals(CommonConfigurations.adminModId)) {
                mv = new ModelAndView("redirect:getApprovalDashBoardList.htm?page=1&approveType=pending");
            }
            else if(data.getModuleId().equals(CommonConfigurations.treausryModId)) {
                mv = new ModelAndView("redirect:getApprovalDashBoardList.htm?page=1&approveType=pending");
//                mv = new ModelAndView("redirect:listPendingApprovers.htm?approver_type=treasury&type=pending");
            }
//            else if(data.getModuleId().equals(CommonConfigurations.adminModId)) {
//                 mv = new ModelAndView("redirect:settlementPendingApprovels.htm?approver_type=payroll&type=pending");
//                  //mv = new ModelAndView("redirect:editSettlement.htm");
//            }
            else if(data.getModuleId().equals("801")) {
                 mv = new ModelAndView("redirect:adminList.htm?approver_type=admin&type=pending");
            }
             else if(data.getModuleId().equals("796")) {
                 mv = new ModelAndView("redirect:settlementPendingApprovels.htm?approver_type=rm&type=pending");
            }
            else if(data.getModuleId().equals("797")) {
                 mv = new ModelAndView("redirect:settlementPendingApprovels.htm?approver_type=buh&type=pending");
            }
            else if(data.getModuleId().equals("800")) {
                 mv = new ModelAndView("redirect:settlementPendingApprovels.htm?approver_type=cfo&type=pending");
            }
             else if(data.getModuleId().equals("799")) {
                 mv = new ModelAndView("redirect:settlementPendingApprovels.htm?approver_type=finance&type=pending");
            }
             else if(data.getModuleId().equals("802")) {
                 mv = new ModelAndView("redirect:settlementPendingApprovels.htm?approver_type=treasury&type=pending");
            }
             else if(data.getModuleId().equals("798")) {
                 mv = new ModelAndView("redirect:settlementPendingApprovels.htm?approver_type=payroll&type=pending");
            }
        }
        
        return mv;
    }

    public ModelAndView getDashBoardList(HttpServletRequest request, HttpServletResponse response,CommonDto formData) throws Exception {
        mv = new ModelAndView("/common/dashBoard");
        HttpSession session = request.getSession();
        String employeeId = (String)session.getAttribute("employeeId");
        String empStatus = (String)session.getAttribute("empStatus");
        String moduleId = (String)session.getAttribute("moduleId");
        String currEmpStatus = (String) session.getAttribute("currEmpStatus");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl daoIndia = (CommonDaoImpl) ctx.getBean("CommonDao");
         List<CommonDto> indiaList = new ArrayList<CommonDto>();
        List<CommonDto> fullList = new ArrayList<CommonDto>();

        int indianCnt= CommonFunctions.checkEmpDbAccess(empStatus, CommonConfigurations.Array);      
        formData.setEmployee_id(employeeId);

        int page = Integer.parseInt(request.getParameter("page"));
        int rows = CommonConfigurations.pageCount;
        int start;
        start = ((page - 1) * rows);
        formData.setStart_page(start);
        formData.setEnd_page(rows);

        int india_count = 0;
        int recordCount = 0;

        if(moduleId!=null && moduleId.equals(CommonConfigurations.domesticModId)){
            formData.setTravelType(CommonConfigurations.domesticCode);
            mv.addObject("travel_type",CommonConfigurations.domesticCode);
            mv.addObject("displayName", "Domestic Travel");
            mv.addObject("forwardPage", "addTravel.htm?travel_type=D");
            mv.addObject("advanceForwardPage", "getAdvanceTpList.htm?travel_type=D");
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.internationalModId)){
            formData.setTravelType(CommonConfigurations.internationalCode);
            mv.addObject("travel_type",CommonConfigurations.internationalCode);
            mv.addObject("displayName", "International Travel");
            mv.addObject("forwardPage", "getInternationalAddPage.htm?travel_type=I");
            mv.addObject("advanceForwardPage", "getAdvanceTpList.htm?travel_type=I");
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.localConvModId)){
            formData.setTravelType(CommonConfigurations.localConvCode);
            mv.addObject("travel_type",CommonConfigurations.localConvCode);
            mv.addObject("displayName", "Local Conveyance Travel");
            mv.addObject("forwardPage", "addNewLocalConveyance.htm?travel_type=L");
            mv.addObject("advanceForwardPage", "getAdvanceTpList.htm?travel_type=L");
        }

        if(indianCnt!=0){          
            india_count = Integer.parseInt(daoIndia.getDashBoardCount(formData));
            recordCount=india_count;
            indiaList = daoIndia.getDashBoardList(formData);
        }
              int end = (start+rows)-1;
             if((end+1) <= india_count) {
                indiaList = daoIndia.getDashBoardList(formData);
        }

        fullList.addAll(indiaList);

        int[] paging = CommonFunctions.paging(recordCount, page, rows);
        mv.addObject("paging", paging);
        mv.addObject("listObj", fullList);
        mv.addObject("currEmpStatus", currEmpStatus);
        return mv;
    }

    public ModelAndView getAdvanceTpList(HttpServletRequest request,HttpServletResponse response,CommonDto formData)throws Exception{
        mv = new ModelAndView("/common/dashBoardAdvance");
        HttpSession session = request.getSession();
        String employeeId = (String)session.getAttribute("employeeId");
        String empStatus = (String)session.getAttribute("empStatus");
        String moduleId = (String)session.getAttribute("moduleId");
        String currEmpStatus = (String) session.getAttribute("currEmpStatus");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl daoIndia = (CommonDaoImpl) ctx.getBean("CommonDao");
        List<CommonDto> indiaList = new ArrayList<CommonDto>();
        List<CommonDto> fullList = new ArrayList<CommonDto>();

        int indianCnt= CommonFunctions.checkEmpDbAccess(empStatus, CommonConfigurations.Array);

        formData.setEmployee_id(employeeId);

        int page = Integer.parseInt(request.getParameter("page"));
        int rows = CommonConfigurations.pageCount;
        int start;
        start = ((page - 1) * rows);
        formData.setStart_page(start);
        formData.setEnd_page(rows);

        int india_count = 0;
        int recordCount = 0;

        if(moduleId!=null && moduleId.equals(CommonConfigurations.domesticModId)){
            formData.setTravelType(CommonConfigurations.domesticCode);
            mv.addObject("travel_type",CommonConfigurations.domesticCode);
            mv.addObject("displayName", "Domestic Travel");
            mv.addObject("forwardPage", "addTravel.htm?travel_type=D");
            mv.addObject("advanceForwardPage", "getAdvanceTpList.htm?travel_type=D");
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.internationalModId)){
            formData.setTravelType(CommonConfigurations.internationalCode);
            mv.addObject("travel_type",CommonConfigurations.internationalCode);
            mv.addObject("displayName", "International Travel");
            mv.addObject("forwardPage", "getInternationalAddPage.htm?travel_type=I");
            mv.addObject("advanceForwardPage", "getAdvanceTpList.htm?travel_type=I");
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.localConvModId)){
            formData.setTravelType(CommonConfigurations.localConvCode);
            mv.addObject("travel_type",CommonConfigurations.localConvCode);
            mv.addObject("displayName", "Local Conveyance Travel");
            mv.addObject("forwardPage", "addNewLocalConveyance.htm?travel_type=L");
            mv.addObject("advanceForwardPage", "getAdvanceTpList.htm?travel_type=L");
        }

        if(indianCnt!=0){
            india_count = Integer.parseInt(daoIndia.getAdvanceDashBoardCount(formData));
            recordCount= india_count;
            indiaList = daoIndia.getAdvanceDashBoardList(formData);
             }
             int end = (start+rows)-1;
             if((end+1) <= india_count) {
             indiaList = daoIndia.getAdvanceDashBoardList(formData);
               }
        fullList.addAll(indiaList);

        int[] paging = CommonFunctions.paging(recordCount, page, rows);

        mv.addObject("paging", paging);
        mv.addObject("listObj", fullList);
        mv.addObject("currEmpStatus", currEmpStatus);
        return mv;
    }




    public ModelAndView getApprovalDashBoardList(HttpServletRequest request,HttpServletResponse response,CommonDto commonFormData)throws Exception{
        mv = new ModelAndView("/common/approvalDashBoard");
        HttpSession session = request.getSession();
        String moduleId = (String)session.getAttribute("moduleId");
        String employeeId = (String)session.getAttribute("employeeId");
        String currEmpStatus = (String)session.getAttribute("currEmpStatus");
        String approveType = request.getParameter("approveType");
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl daoIndia = (CommonDaoImpl) ctx.getBean("CommonDao");
        List<CommonDto> approvalList = new ArrayList<CommonDto>();
        List<CommonDto> approval = new ArrayList<CommonDto>();
        List<CommonDto> indiaList = new ArrayList<CommonDto>();
        List<CommonDto> india = new ArrayList<CommonDto>();
        commonFormData.setEmployee_id(employeeId);
        mv.addObject("approveType", "approveType");
        if(approveType!=null && approveType.equals("pending")){
            mv.addObject("listType", "pending");
            mv.addObject("nextClickType", "Processed");
            mv.addObject("nextClickType1", "All Active");
            mv.addObject("nextClickTypeVar", "processed");
            mv.addObject("nextClickTypeVar1", "all pending");
        }else if(approveType!=null && approveType.equals("processed")){
            mv.addObject("listType", "processed");
            mv.addObject("nextClickType", "Pending");
            mv.addObject("nextClickType1", "All Active");
            mv.addObject("nextClickTypeVar", "pending");
            mv.addObject("nextClickTypeVar1", "all pending");
        }else if(approveType!=null && approveType.equals("all pending")){
            mv.addObject("listType", "all pending");
            mv.addObject("nextClickType", "Pending");
            mv.addObject("nextClickType1", "Processed");
            mv.addObject("nextClickTypeVar", "pending");
            mv.addObject("nextClickTypeVar1", "processed");
        }

        if(commonFormData.getExportButton() == null) {
            if(moduleId!=null && moduleId.equals(CommonConfigurations.rmModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.rmActionCode);
                mv.addObject("displayName", "RM/PM");
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.buhModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.buhActionCode);
                mv.addObject("displayName", "BUH");
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.mdModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.mdActionCode);
                mv.addObject("displayName", "CEO");
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.finModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.financeActionCode);
                mv.addObject("displayName", "Finance");
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.cfoModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.cfoActionCode);
                mv.addObject("displayName", "CFO");
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.adminModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.adminActionCode);
                mv.addObject("displayName", "Admin");
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.treausryModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.treasuryActionCode);
                mv.addObject("displayName", "Treasury");
            }
            int page;
            int india_count = 0;
            if(commonFormData.getSubmitButton() != null)
                page = 1;
            else
                page = Integer.parseInt(request.getParameter("page"));
            int rows = CommonConfigurations.pageCount;
            int start;
            start = ((page - 1) * rows);
            commonFormData.setStart_page(start);
            commonFormData.setEnd_page(rows);

            int recordCount = 0;
            System.out.println("::modId::"+moduleId+"---"+approveType);
            commonFormData.setModuleId(moduleId);
            commonFormData.setApproveType(approveType);
            indiaList = daoIndia.getApprovalDashBoardList(commonFormData);
            india_count = daoIndia.getApprovalDashBoardCount(commonFormData);
            System.out.println("india_count"+india_count);
            recordCount = india_count;
            int end = (start+rows)-1;
            approvalList.addAll(indiaList);
            int[] paging = CommonFunctions.paging(recordCount, page, rows);
            mv.addObject("paging", paging);
            mv.addObject("listObj", approvalList);
          if(commonFormData.getFilter_employeeId() != null && !commonFormData.getFilter_employeeId().isEmpty() ) {
                mv.addObject("filter_employeeName",daoIndia.getEmployee(commonFormData.getFilter_employeeId()));
            } else {
                mv.addObject("filter_employeeName","");
            }
            mv.addObject("commonFormData", commonFormData);
            mv.addObject("currEmpStatus", currEmpStatus);
        } else {
            String[] methodNames = {"getTpReferenceId","getEmployeeNumber", "getEmployeeName","getTpRequestedDateDisplay", "getStartDate", "getEndDate", "getCurrency_type", "getAmount", "getTravelType", "getExportDesc"};
            String[] tableHeader = {"TP Reference Number","Employee Number","Employee Name","Requested Date", "Start Date", "End Date", "Currency", "Amount", "Travel Type", "Status"};
            if(moduleId!=null && moduleId.equals(CommonConfigurations.rmModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.rmActionCode);
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.buhModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.buhActionCode);
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.mdModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.mdActionCode);
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.finModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.financeActionCode);
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.cfoModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.cfoActionCode);
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.adminModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.adminActionCode);
            } else if(moduleId!=null && moduleId.equals(CommonConfigurations.treausryModId)) {
                commonFormData.setTravelActionCode(CommonConfigurations.treasuryActionCode);
            }
            int page;
            int india_count = 0;
            if(commonFormData.getSubmitButton() != null)
                page = 1;
            else
                page = Integer.parseInt(request.getParameter("page"));
            int rows = CommonConfigurations.pageCount;
            int start;
            start = ((page - 1) * rows);
            commonFormData.setStart_page(0);
            commonFormData.setEnd_page(0);
//            commonFormData.setStart_page(start);
//            commonFormData.setEnd_page(rows);

            int recordCount = 0;
            System.out.println("::modId::"+moduleId+"---"+approveType);
            commonFormData.setModuleId(moduleId);
            commonFormData.setApproveType(approveType);
           // indiaList = daoIndia.getApprovalDashBoardListForExport(commonFormData);
              india=daoIndia.getApprovalDashBoardListForExport(commonFormData);
              System.out.println("::::india::"+india.size());
            india_count = daoIndia.getApprovalDashBoardCount(commonFormData);
            System.out.println("india_count"+india_count);
            recordCount = india_count;
            int end = (start+rows)-1;
            /*Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-mm-dd");
            for(int i=0;i<indiaList.size();i++){
                if(indiaList.get(i).getTravelType().equals("D")){
                    indiaList.get(i).setTravelType("Domestic");
                }else if(indiaList.get(i).getTravelType().equals("I")){
                    indiaList.get(i).setTravelType("International");
                }else{
                    indiaList.get(i).setTravelType("Local Conveyance");
                }
               // indiaList.get(i).setTpRequestedDate(new Date(ft.format(indiaList.get(i).getTpRequestedDate())));
                if(indiaList.get(i).getStatus().equals("a")){
                    indiaList.get(i).setStatus("Money Deposited");
                }*
            }*/
            approval.addAll(india);

            CommonFunctions.drawTable(response, approval, tableHeader.length, tableHeader, methodNames, request, true, "Travel.xls");
        }
        return mv;
    }

    public ModelAndView ajaxsearch(HttpServletRequest request, HttpServletResponse response) {
        mv = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CommonDaoImpl daoIndia = (CommonDaoImpl) ctx.getBean("CommonDao");
            String val = request.getParameter("q");
            String type = request.getParameter("type");
            List<CommonDto> result = daoIndia.getAutocomplete(val,type);
            mv.addObject("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView loadProject(HttpServletRequest request, HttpServletResponse response) {
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CommonDaoImpl dao = (CommonDaoImpl) ctx.getBean("CommonDao");
            String customer_id = request.getParameter("customer_id");
            List<CommonDto> data = dao.getProjects(customer_id);
            for (int k = 0; k < data.size(); k++) {
                response.getOutputStream().write(data.get(k).getProject_id().getBytes());
                response.getOutputStream().write(",".getBytes());
                response.getOutputStream().write(data.get(k).getProject_name().getBytes());
                response.getOutputStream().write(":".getBytes());
            }
            response.getOutputStream().write("-1".getBytes());
            response.getOutputStream().write(",".getBytes());
            response.getOutputStream().write("Others".getBytes());
            response.getOutputStream().write(":".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ModelAndView getIndexPage(HttpServletRequest request,HttpServletResponse response)throws Exception{
        mv = new ModelAndView("index");
        return mv;
    }

    public  void commonCodeForApproval(String moduleId,String employeeId,CommonDto interFormData)throws Exception{
        final WebApplicationContext ctx = getWebApplicationContext();
        CommonDaoImpl commonDaoIndia = (CommonDaoImpl) ctx.getBean("CommonDao");
        CommonDto commonFormData = new CommonDto();
        commonFormData.setTpId(interFormData.getTpId());
        if(moduleId!=null && moduleId.equals(CommonConfigurations.rmModId)){
            commonFormData.setRejectCode(CommonConfigurations.rmRejectCode);
            commonFormData.setActionName(CommonConfigurations.rmActionCode);
            commonFormData.setColumnName(CommonConfigurations.rmColumnName);
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.buhModId)){
            commonFormData.setRejectCode(CommonConfigurations.buhRejectCode);
            commonFormData.setActionName(CommonConfigurations.buhActionCode);
            commonFormData.setColumnName(CommonConfigurations.buhColumnName);
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.mdModId)){
            commonFormData.setRejectCode(CommonConfigurations.mdRejectCode);
            commonFormData.setActionName(CommonConfigurations.mdActionCode);
            commonFormData.setColumnName(CommonConfigurations.mdColumnName);
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.finModId)){
            commonFormData.setRejectCode(CommonConfigurations.financeRejectCode);
            commonFormData.setActionName(CommonConfigurations.financeActionCode);
            commonFormData.setColumnName(CommonConfigurations.financeColumnName);
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.cfoModId)){
            commonFormData.setRejectCode(CommonConfigurations.cfoRejectCode);
            commonFormData.setActionName(CommonConfigurations.cfoActionCode);
            commonFormData.setColumnName(CommonConfigurations.cfoColumnName);
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.adminModId)){
            commonFormData.setRejectCode(CommonConfigurations.adminRejectCode);
            commonFormData.setActionName(CommonConfigurations.adminActionCode);
            commonFormData.setColumnName(CommonConfigurations.adminColumnName);
        }else if(moduleId!=null && moduleId.equals(CommonConfigurations.treausryModId)){
            commonFormData.setRejectCode(CommonConfigurations.treasuryRejectCode);
            commonFormData.setActionName(CommonConfigurations.treasuryActionCode);
            commonFormData.setColumnName(CommonConfigurations.treasuryColumnName);
        }
        commonFormData.setRemarks(interFormData.getRemarks());
        commonFormData.setApprovedBy(employeeId);
        commonFormData.setActionDate(new Date());
        commonFormData.setTpId(interFormData.getTpId());

        CommonDto approvalStatusFlow = new CommonDto();
        if(interFormData.getSystem()!=null && interFormData.getSystem().equals(CommonConfigurations.indiaEmpStatus)){
            System.out.println(":::: action name"+interFormData.getApproveAction()+"---"+interFormData.getSendBackAction());
            if(interFormData.getApproveAction()!=null){
            approvalStatusFlow = commonDaoIndia.getApprovalStatusFlowInfo(commonFormData);
            if(approvalStatusFlow!=null){
                commonFormData.setStatus(approvalStatusFlow.getStatus());
            }
            }else if(interFormData.getSendBackAction()!=null){
                String rejectStatus = commonDaoIndia.getRejectStatus(commonFormData.getRejectCode());
                System.out.println("rejectStatus = " + rejectStatus);
                commonFormData.setStatus(rejectStatus);
            }
            commonDaoIndia.updateApprovalStatus(commonFormData);
        }
    }

    public ModelAndView fileDownload(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        String fileType = request.getParameter("fileType");
        String filePath = CommonConfigurations.travelDocumentsPath;
        CommonFunctions.fileDownload(fileName, filePath, fileType, response);
        return mv;
    }

    public ModelAndView sessionExpired(HttpServletRequest request, HttpServletResponse response) throws Exception {
        mv = new ModelAndView("session_expired");
        return mv;
    }

}
