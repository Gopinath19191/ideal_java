/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.controller;

import com.defiance.ideal.dao.AuthenticatorDaoImpl;
import com.defiance.ideal.dao.EmployeeDaoImpl;
import com.defiance.ideal.dao.EmployeeDao;
import com.defiance.ideal.service.EmployeeServiceImpl;
import com.defiance.ideal.service.EmployeeService;
import com.defiance.ideal.dto.EmployeeDto;
import com.defiance.ideal.dto.DetailsDto;
import com.defiance.ideal.dto.LoginDTO;
import com.defiance.ideal.dto.kraDto;
import com.defiance.ideal.util.CommonFunctions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14053
 */
public class AuthenticationController extends MultiActionController {
    
    private ModelAndView mv;
    String returnMsg = null;
    String successMsg = null;

    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        mv = new ModelAndView("/unauthorised");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeDaoImpl dao = (EmployeeDaoImpl) ctx.getBean("EmployeeDao");
        EmployeeDto data = new EmployeeDto();
        String issueDate = null;
        if (request.getParameter("empId") == null) {
            mv.addObject("ErrorMessage", "Employee Id Not Present");
        } else {
            data = (EmployeeDto) dao.getUserDetails(request.getParameter("empId"));
            System.out.println("Employee Id = " + data.getEmpId());
        }
        session.setAttribute("empNumber",request.getParameter("empId"));
        session.setAttribute("accessId", data.getEmpId());
        session.setAttribute("accessType", request.getParameter("access"));
        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            data.setModuleId(request.getParameter("modId"));
            System.out.println("Module Id = " + data.getModuleId());
        }
        
        boolean authenticated = dao.authenticate(data);
        authenticated = true;
        if (authenticated) {
            session.setAttribute("empLoginDetails", data);
            session.setAttribute("employeeSearchID", "");
            session.setAttribute("EMP_ID", data.getEmpId());
            
            
            if ("734".equals(data.getModuleId())) {
                mv = new ModelAndView("redirect:listKra.htm");
                
            }
            if ("735".equals(data.getModuleId())) {
                mv = new ModelAndView("redirect:rmListKra.htm");
            }
            if ("736".equals(data.getModuleId())) {
                mv = new ModelAndView("redirect:reportListKra.htm");
            }
            //mv = new ModelAndView("redirect:listKra.htm");
            //mv = new ModelAndView("redirect:generalInfo.htm?actionName=employeeKra&page=1");

        } else {
            session.invalidate();
        }
//        request.getParameter(issueDate);
//        request.setAttribute("issueDate", issueDate);
        return mv;
    }
    
    public ModelAndView ajaxsearch(HttpServletRequest request, HttpServletResponse response) {
        mv = new ModelAndView("/com/defiance/employees/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
            String empVal = request.getParameter("q");
            List<DetailsDto> empRes = service.getEmployeeSearchList(empVal);
            System.out.println("size" + empRes.size());
            mv.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
    public ModelAndView listKra(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("employee id " + session.getAttribute("EMP_ID"));
        mv = new ModelAndView("/com/defiance/employees/employeeKraList");
        String employeeId = (String) session.getAttribute("EMP_ID");
        final WebApplicationContext ctx = getWebApplicationContext();
        String aa_status = request.getParameter("AA");
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        List<kraDto> details = service.getKraList(employeeId, null);
        mv.addObject("details", details);
        mv.addObject("employeeId", employeeId);
        if(aa_status != null){
            mv.addObject("successMsg","Annual Appraisal form is ready. Please go to ESS--> Appraisal & QPD --> Annual Appraisal.");
        }
        return mv;
    }
    
    public ModelAndView viewIndividualKra(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        
        String kraId = (String) request.getParameter("kraId");
        String reportingManager = (String) request.getParameter("reportingManager");
        String editValue = (String) request.getParameter("edit");
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        List<EmployeeDto> masterDetails = service.getKraList(null, kraId);
        List<EmployeeDto> kraDetails = service.getIndividualKraDetails(kraId);
        List<kraDto> quarterList = service.getQuarterList();
        String total_weightage = service.getTotalWeightage(kraId);
        String returnMsg=null;
        if (editValue.equals("0")) {
            mv = new ModelAndView("/com/defiance/employees/employeeKraView");
        }
        else if(editValue.equals("3")){
            mv = new ModelAndView("/com/defiance/employees/employeeKraView");
            returnMsg = "KRA is exist for selected Quarter";
            mv.addObject("returnMsg",returnMsg);
        }else{
            mv = new ModelAndView("/com/defiance/employees/rmKraView");
        }
        String quarterId=null;
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH);
        if (month >= 0 && month <= 2) {
            quarterId = "5";
        } else if (month >= 3 && month <= 5) {
            quarterId = "1";
        } else if (month >= 6 && month <= 8) {
            quarterId = "2";
        } else if (month >= 9 && month <= 11) {
            quarterId = "3";
        }
        mv.addObject("details", kraDetails);
        mv.addObject("masterDetails", masterDetails);
        mv.addObject("RmList", reportingManager);
        mv.addObject("quarterList",quarterList);
        mv.addObject("totalWeightage", total_weightage);
        mv.addObject("current_quarter", quarterId);
        return mv;
    }    
    
    public ModelAndView rmListKra(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        
        mv = new ModelAndView("/com/defiance/employees/rmKraList");
        String accessId = (String) session.getAttribute("accessId");
        formData.setEmployeeId(accessId);
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        
        String financeyear = null;
        String quarterId = null;
        financeyear = request.getParameter("financeYear");
        quarterId = request.getParameter("quarter");
        formData.setQuarterId(quarterId);
        formData.setYear(financeyear);
        if ((financeyear == null) || (quarterId == null)) {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            if (month < 3) {
                financeyear = Integer.toString(year - 1) + "-" + Integer.toString(year);
            } else {
                financeyear = Integer.toString(year) + "-" + Integer.toString(year + 1);
            }
            if (month >= 0 && month <= 2) {
                quarterId = "4";
            } else if (month >= 3 && month <= 5) {
                quarterId = "1";
            } else if (month >= 6 && month <= 8) {
                quarterId = "2";
            } else if (month >= 9 && month <= 11) {
                quarterId = "3";
            }
            formData.setQuarterId(quarterId);
            formData.setYear(financeyear);
        }
        List financialYear = new ArrayList<String>();
        List<kraDto> financialYearList = service.getRMFinancialYearList(accessId);
        if (financialYearList.size() == 0) {
            financialYear.add(financeyear);
        } else {
            for (int j = 0; j < financialYearList.size(); j++) {
                System.out.println("liter " + financialYearList.get(j).getFinancial_year());
                if (financeyear.equals(financialYearList.get(j).getFinancial_year())) {
                    
                } else {
                    financialYear.add(financialYearList.get(j).getFinancial_year());
                }
            }
            financialYear.add(financeyear);
        }
        
        List<kraDto> quarterList = service.getQuarterList();
        List<kraDto> details = service.getRmEmployeeList(formData);
        mv.addObject("details", details);
        mv.addObject("quarterList", quarterList);
        mv.addObject("current_financial_year", financeyear);
        mv.addObject("current_quarter", quarterId);
        mv.addObject("testFin", financialYear);
        return mv;
    }
    public ModelAndView reportListKra(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        
        mv = new ModelAndView("/com/defiance/employees/reportKraList");
        String accessId = (String) session.getAttribute("accessId");
        formData.setEmployeeId(accessId);
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        
        String financeyear = null;
        String quarterId = null;
        financeyear = request.getParameter("financeYear");
        quarterId = request.getParameter("quarter");
        formData.setQuarterId(quarterId);
        formData.setYear(financeyear);
        if ((financeyear == null) || (quarterId == null)) {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            if (month < 3) {
                financeyear = Integer.toString(year - 1) + "-" + Integer.toString(year);
            } else {
                financeyear = Integer.toString(year) + "-" + Integer.toString(year + 1);
            }
            if (month >= 0 && month <= 2) {
                quarterId = "4";
            } else if (month >= 3 && month <= 5) {
                quarterId = "1";
            } else if (month >= 6 && month <= 8) {
                quarterId = "2";
            } else if (month >= 9 && month <= 11) {
                quarterId = "3";
            }
            formData.setQuarterId(quarterId);
            formData.setYear(financeyear);
        }
        List financialYear = new ArrayList<String>();
        List<kraDto> financialYearList = service.getRMFinancialYearList(accessId);
        if (financialYearList.size() == 0) {
            financialYear.add(financeyear);
        } else {
            for (int j = 0; j < financialYearList.size(); j++) {
                System.out.println("liter " + financialYearList.get(j).getFinancial_year());
                if (financeyear.equals(financialYearList.get(j).getFinancial_year())) {
                    
                } else {
                    financialYear.add(financialYearList.get(j).getFinancial_year());
                }
            }
            financialYear.add(financeyear);
        }
        
        List<kraDto> quarterList = service.getQuarterList();
        List<kraDto> levelOneStructure = service.getStructureDetails(0);
        List<kraDto> pgList = service.getCmpStructData(""+formData.getCompanyStructureId());
        List<kraDto> cmpStructureList = service.getCmpStructData(CommonFunctions.cmpStructParentId);
        List<kraDto> details = service.getReportList(formData);
//        System.out.println("companyStructureId"+formData.getCompanyStructureId());
//        System.out.println("getPractice Group!!!"+request.getParameter("practiceGroup"));
        formData.setPracticeGroup(request.getParameter("practiceGroup"));
        mv.addObject("details", details);
        mv.addObject("quarterList", quarterList);
        mv.addObject("current_financial_year", financeyear);
        mv.addObject("current_quarter", quarterId);
        mv.addObject("testFin", financialYear);
        mv.addObject("levelOneStructure", levelOneStructure);
        mv.addObject("pgList", pgList);
        mv.addObject("cmpStructureList", cmpStructureList);
        mv.addObject("requestorDTO", formData);
          
        return mv;
    }
    public ModelAndView getCompanyStructureX(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        // String selectedStructure = request.getParameter("structureId");

        String selectedStructure = formData.getStructureId();
        System.out.println("selectedStructure = " + selectedStructure);
         final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        List< kraDto> structureList = new ArrayList();
        if (selectedStructure != null) {
            structureList = service.getStructureDetails(Integer.parseInt(selectedStructure));
        }
        request.setAttribute("getDataFor", "CompanyStructureName");
        Iterator itr = structureList.iterator();
        while (itr.hasNext()) {
            kraDto structureList1 = (kraDto) itr.next();
            response.getOutputStream().write(structureList1.getStructureId().getBytes());
            response.getOutputStream().write(",".getBytes());
            response.getOutputStream().write(structureList1.getStructureName().getBytes());
            response.getOutputStream().write(":".getBytes());
        }
        return null;
    }
    
    public ModelAndView copyKra(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        List<EmployeeDto> result = null;     
        List<kraDto> list =null;
        String kraId = (String) request.getParameter("kraId");
        String reportingManager = (String) request.getParameter("reportingManager");
        String financeyear = request.getParameter("financialYear");
        String quarterId = request.getParameter("quarter");
        String employeeSearchID = request.getParameter("employeeId");      
        String copyQuarterId = request.getParameter("copyQuarterId");
        final WebApplicationContext ctx = getWebApplicationContext();
        System.out.println("inputs "+financeyear+" "+quarterId+" "+employeeSearchID+"-"+kraId+"-"+copyQuarterId);
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        list = service.checkEmployeeKraDetails(employeeSearchID, financeyear, Integer.parseInt(copyQuarterId));
        String accessId = (String) session.getAttribute("accessId");        
        if(list !=null && (list.size()) > 0){   
            String kra = list.get(0).getKra_id();
            mv= new ModelAndView("redirect:/viewIndividualKra.htm?kraId="+kra+"&reportingManager=0&edit=3");                        
        }else{
            mv = new ModelAndView("/com/defiance/employees/copyKra");
            List financialYear =new ArrayList<String>();
            List<kraDto> financialYearList = service.getFinancialYearList(accessId);
            if(financialYearList.size()==0){
            financialYear.add(financeyear);
        }else{
            for(int j=0;j<financialYearList.size();j++){
            System.out.println("liter "+financialYearList.get(j).getFinancial_year());
            if(financeyear.equals(financialYearList.get(j).getFinancial_year())){                
            }else{
              financialYear.add(financialYearList.get(j).getFinancial_year());
              }
            }
            financialYear.add(financeyear);
        }        
        formData.setEmployeeId(accessId);
        formData.setSubmittedBy(accessId);
        List<EmployeeDto> associatesList = service.getAssociatesList(formData);
        mv.addObject("associatesList", associatesList); 
        formData.setYear(financeyear);
        formData.setQuarterId(quarterId);
        formData.setEmployeeId(employeeSearchID);
        String accessType = (String) session.getAttribute("accessType");        
        String empName=service.getEmployeeName(employeeSearchID);
        String qName=service.getQuarterName(copyQuarterId);
        String total_weightage = null;
        String kra_id =null;
        result = service.getEmployeeDetails(formData, "employeeKra");
        List<EmployeeDto> masterDetails=null;
        if(result !=null && result.size()>0){
            total_weightage = service.getTotalWeightage(result.get(0).getKra_id());
            kra_id=result.get(0).getKra_id();
           masterDetails = service.getKraList(null, kra_id);
        }else{
            
        }
        
        List<kraDto> quarterList = service.getQuarterList();
        String actionValueX = null;
        if (result != null) {
            Iterator it = result.iterator();
            while (it.hasNext()) {
                EmployeeDto dto = (EmployeeDto) it.next();
                actionValueX=dto.getActionValueX();
            }
        }        
        System.out.println("acttion kraId "+kra_id);
        mv.addObject("qName", qName); 
        mv.addObject("empName", empName); 
        mv.addObject("masterDetails", masterDetails);
        mv.addObject("employeeSearchID", employeeSearchID);
        mv.addObject("result", result);
        mv.addObject("quarterList",quarterList);
        mv.addObject("accessType", accessType);
        mv.addObject("actionName", "employeeKra");
        mv.addObject("actionValueX", actionValueX);
        mv.addObject("employeeId", accessId);
        mv.addObject("current_financial_year", financeyear);
        mv.addObject("current_quarter", copyQuarterId);
        mv.addObject("testFin", financialYear);
        mv.addObject("totalWeightage", total_weightage);
        mv.addObject("currentQuarter", total_weightage);
        }           
         return mv;  
    }
    public ModelAndView generalInfo(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        boolean flag = false;
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        List<EmployeeDto> result = null;
        List<EmployeeDto> resultSet = null;
        
        String buttonName = request.getParameter("excelbuttonName");
        String actionName = request.getParameter("actionName");
        String financeyear = request.getParameter("financeYear");
        String quarterId = request.getParameter("quarter");
        String errorMessage = request.getParameter("returnMsg");
        String successMessage = request.getParameter("successMsg");
        String employeeSearchID = request.getParameter("associateId");
        System.out.println("employeeSearchID !!!!!!!!!!! " + employeeSearchID);
        if ((financeyear == null) || (quarterId == null)) {
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH);
            if (month < 3) {
                financeyear = Integer.toString(year - 1) + "-" + Integer.toString(year);
            } else {
                financeyear = Integer.toString(year) + "-" + Integer.toString(year + 1);
            }
            if (month >= 0 && month <= 2) {
                quarterId = "5";
            } else if (month >= 3 && month <= 5) {
                quarterId = "1";
            } else if (month >= 6 && month <= 8) {
                quarterId = "2";
            } else if (month >= 9 && month <= 11) {
                quarterId = "3";
            }
        }
        if (actionName == null) {
            actionName = (String) session.getAttribute("actionName");
            flag = true;
        }
        System.out.println("actionName !!!!!!!!!!! " + actionName);
        mv = new ModelAndView("/com/defiance/employees/" + actionName);
        String accessId = (String) session.getAttribute("accessId");
        formData.setEmployeeId(accessId);
        formData.setSubmittedBy(accessId);
//        List<EmployeeDto> associatesList = service.getAssociatesList(formData);
//        mv.addObject("associatesList", associatesList);
                
         if (flag) {
            String success_msg = "";
            if (actionName.equals("employeeKra")) {
                success_msg = "Employee Kra details";
            }
            if (actionName.equals("certification")) {
                success_msg = "Certification details";
            }
            if (actionName.equals("education")) {
                success_msg = "Education details";
            }
            mv.addObject("success_msg", success_msg + " has been added / updated successfully");
        }
        List financialYear =new ArrayList<String>();
        List<kraDto> financialYearList = service.getFinancialYearList(accessId);
        if(financialYearList.size()==0){
            financialYear.add(financeyear);
        }else{
            for(int j=0;j<financialYearList.size();j++){
                System.out.println("filiter "+financialYearList.get(j).getFinancial_year());
                if(financeyear.equals(financialYearList.get(j).getFinancial_year())){
                
                }else{
                    financialYear.add(financialYearList.get(j).getFinancial_year());
                }
            }
            financialYear.add(financeyear);
        }
        
        String accessType = (String) session.getAttribute("accessType");
        
//        mv.addObject("employeeSearchName", "");
        formData.setStart_page(0);
        formData.setEnd_page(20);
        formData.setYear(financeyear);
        formData.setQuarterId(quarterId);
        formData.setEmployeeId(employeeSearchID);
//        if(employeeSearchID==null || "".equals(employeeSearchID)){
//            formData.setEmployeeId(associatesList.get(0).getEmpId());
//        }
        String empName=service.getEmployeeName(employeeSearchID);
        String qName=service.getQuarterName(quarterId);
        String total_weightage = null;
        String kra_id =null;
        result = service.getEmployeeDetails(formData, actionName);
        List<EmployeeDto> masterDetails=null;
        if(result !=null && result.size()>0){
            total_weightage = service.getTotalWeightage(result.get(0).getKra_id());
            kra_id=result.get(0).getKra_id();
           masterDetails = service.getKraList(null, kra_id);
        }else{
            
        }
        
        List<kraDto> quarterList = service.getQuarterList();

        String actionValueX = null;
        if (result != null) {
            Iterator it = result.iterator();
            while (it.hasNext()) {
                EmployeeDto dto = (EmployeeDto) it.next();
                actionValueX=dto.getActionValueX();
            }
        }
        System.out.println("acttion kraId "+kra_id);
        mv.addObject("qName", qName); 
        mv.addObject("empName", empName); 
        mv.addObject("masterDetails", masterDetails);
        mv.addObject("employeeSearchID", employeeSearchID);
        mv.addObject("result", result);
        mv.addObject("quarterList",quarterList);
        mv.addObject("accessType", accessType);
        mv.addObject("actionName", actionName);
        mv.addObject("actionValueX", actionValueX);
        mv.addObject("employeeId", accessId);
        mv.addObject("current_financial_year", financeyear);
        mv.addObject("current_quarter", quarterId);
        mv.addObject("testFin", financialYear);
        mv.addObject("totalWeightage", total_weightage);
        mv.addObject("returnMsg", errorMessage);
        mv.addObject("successMsg", successMessage);
        return mv;
    }
    
    public ModelAndView updateRmRemarks(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        String accessId = (String) session.getAttribute("accessId");
        String actionName = request.getParameter("actionName");
        String actionValue = request.getParameter("actionValue");
        formData.setKra_id(request.getParameter("kra_id"));
        formData.setActionValue(actionValue);
        System.out.println("actionValue " + actionValue);
        if (accessId != null) {
            formData.setEmployeeId(accessId);
            String recordCount = request.getParameter("recordCount");
            String[] certificationId = new String[Integer.parseInt(recordCount)];
            String[] qualification = new String[Integer.parseInt(recordCount)];
            System.out.println("RecordCount" + Integer.parseInt(recordCount));
            for (int i = 0; i < Integer.parseInt(recordCount); i++) {
                certificationId[i] = request.getParameter("certificationId_" + (i + 1));
                qualification[i] = request.getParameter("qualification_" + (i + 1));
                System.out.println("cert" + certificationId[i]);
                System.out.println(" qualification[i]" + qualification[i]);
            }
            session.setAttribute("actionName", actionName);
            service.updateRmRemarks(formData, certificationId, qualification, actionValue);
            mv = new ModelAndView("redirect:rmListKra.htm");
        } else {
            mv = new ModelAndView("redirect:authenticate.htm");
        }
        return mv;
    }
    
    public ModelAndView acceptKra(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        //String accessId = (String) session.getAttribute("accessId");
        //formData.setKra_id(request.getParameter("kra_id"));
        System.out.println("kra id " + request.getParameter("kra_id"));
        service.updateKraAccept(request.getParameter("kra_id"));
        mv = new ModelAndView("redirect:listKra.htm");
        
        return mv;
    }
    
    public ModelAndView loadStream(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        String streamid = (String) request.getParameter("selectedstream");
        List<DetailsDto> skillList = service.getSkillList(streamid);
        System.out.println("############:" + skillList.size());
        for (int i = 0; i < skillList.size(); i++) {
            response.getOutputStream().write(skillList.get(i).getSkillId().getBytes());
            response.getOutputStream().write(",".getBytes());
            response.getOutputStream().write(skillList.get(i).getSkillName().getBytes());
            response.getOutputStream().write(":".getBytes());
        }
        if (skillList.isEmpty()) {
            response.getOutputStream().write("".getBytes());
        }
        return null;
    }
    
    public ModelAndView checkUnique(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        response.setContentType("text/html");
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        String value = (String) request.getParameter("value");
        String type = (String) request.getParameter("type");
        String requestId = (String) request.getParameter("id");
        String count = service.checkUnique(formData, value, type, requestId);
        response.getOutputStream().write(count.getBytes());
        return null;
    }
    
    public ModelAndView loadState(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        String val = (String) request.getParameter("country_id");
        String region = (String) request.getParameter("region");
        List<DetailsDto> stateList = service.getStateList(val, region);
        System.out.println("############:" + stateList.size());
        for (int i = 0; i < stateList.size(); i++) {
            response.getOutputStream().write(stateList.get(i).getRegionId().getBytes());
            response.getOutputStream().write(",".getBytes());
            response.getOutputStream().write(stateList.get(i).getRegionName().getBytes());
            response.getOutputStream().write(":".getBytes());
        }
        if (stateList.isEmpty()) {
            response.getOutputStream().write("".getBytes());
        }
        return null;
    }
    public ModelAndView annualAppraisal(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        mv = new ModelAndView("redirect:listKra.htm");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        String kraId = (String) request.getParameter("kraId");
        System.out.println("kraaaaaaaaaaa "+kraId);
        List<kraDto> masterDetails = service.getKraList(null, kraId);
        List<kraDto> kraDetails = service.getIndividualKraDetails(kraId);
        String maxBandId = service.getMaxBandId();
        String financialYear = masterDetails.get(0).getFinancial_year();
        String finYear[] = financialYear.split("-");
        String year = finYear[1];
        formData.setEmpId(masterDetails.get(0).getEmployeeId());
        formData.setYear(year);
        String existing_kra = service.getAaExsting(formData);
        if(Integer.parseInt(existing_kra)==0){
            if(Integer.parseInt(maxBandId)<=100){
                maxBandId = "101";
            }else{
                maxBandId = Integer.toString(Integer.parseInt(maxBandId)+1);
            }
            kraDto appraisee_details = service.getAppraiseerDetails(formData);
            appraisee_details.setEmployeeId(masterDetails.get(0).getEmployeeId());
            appraisee_details.setQuarter_id("1");
            appraisee_details.setFinancial_year(year);
            appraisee_details.setStructureId(masterDetails.get(0).getStructureName());
            appraisee_details.setBand_id(maxBandId);
            String last_insert_id = service.insertIntoAaEligibleAssociates(appraisee_details);
            if(kraDetails.size()>0 && last_insert_id !=null){
                for(int i=0;i<kraDetails.size();i++){
                    formData.setCertificationIdX(kraDetails.get(i).getKra_description());
                    formData.setUniqueId(maxBandId);
                    String aa_kra_id = service.insertIntoAaKra(formData);
                    formData.setKra_id(aa_kra_id);
                    formData.setKratarget(kraDetails.get(i).getKra_target());
                    formData.setKrauom(kraDetails.get(i).getKra_uom());
                    formData.setPercentage(kraDetails.get(i).getKra_weightage());
                    String aa_master_kra_id = service.insertIntoAaKraMaster(formData);
                    formData.setKra_id(aa_master_kra_id);
                    formData.setQuarterId(masterDetails.get(0).getQuarter_id());
                    formData.setYear(year);
                    formData.setBandId(maxBandId);
                    formData.setStructureId(masterDetails.get(0).getStructureName());
                    service.insertIntoAaKraMapping(formData);
                    mv.addObject("AA", "1");
                }
            }
        }else{
            mv.addObject("AA", "1");
        }
        return mv;
    }
    public ModelAndView certificationSubmit(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        String accessId = (String) session.getAttribute("accessId");
        String actionName = request.getParameter("actionName");
        if (accessId != null) {
            formData.setEmployeeId(accessId);
            String recordCount = request.getParameter("recordCount");
            String[] certificationId = new String[Integer.parseInt(recordCount)];
            String[] degree = new String[Integer.parseInt(recordCount)];
            String[] qualification = new String[Integer.parseInt(recordCount)];
            String[] year_of_passing = new String[Integer.parseInt(recordCount)];
            String[] institution = new String[Integer.parseInt(recordCount)];
            String[] percentage = new String[Integer.parseInt(recordCount)];
            String[] remarks = new String[Integer.parseInt(recordCount)];
            int[] deletedTR = new int[Integer.parseInt(recordCount)];
            String[] university = new String[Integer.parseInt(recordCount)];
            System.out.println("RecordCount" + Integer.parseInt(recordCount));
            for (int i = 0; i < Integer.parseInt(recordCount); i++) {
                certificationId[i] = request.getParameter("certificationId_" + (i + 1));
                degree[i] = "c";
                qualification[i] = request.getParameter("qualification_" + (i + 1));
                year_of_passing[i] = request.getParameter("year_of_passing_" + (i + 1));
                institution[i] = request.getParameter("institution_" + (i + 1));
                percentage[i] = request.getParameter("percentage_" + (i + 1));
                remarks[i] = request.getParameter("remarks_" + (i + 1));
                deletedTR[i] = Integer.parseInt(request.getParameter("deletedTR_" + (i + 1)));
                university[i] = "";
                if (actionName.equals("education")) {
                    String dg = request.getParameter("degree_" + (i + 1));
                    degree[i] = dg;
                    university[i] = request.getParameter("university_" + (i + 1));
                    if (dg.equals("10") || dg.equals("12")) {
                        qualification[i] = request.getParameter("qualification1_" + (i + 1));
                        institution[i] = request.getParameter("institution1_" + (i + 1));
                        university[i] = request.getParameter("university1_" + (i + 1));
                    }
                }
                System.out.println("cert" + certificationId[i]);
            }
            session.setAttribute("actionName", actionName);
            service.updateCertification(formData, certificationId, degree, qualification, year_of_passing, institution, percentage, remarks, university, deletedTR);
            mv = new ModelAndView("redirect:generalInfo.htm");
        } else {
            mv = new ModelAndView("redirect:authenticate.htm");
        }
        return mv;
    }
    public ModelAndView employeeKraSubmit(HttpServletRequest request, HttpServletResponse response, EmployeeDto formData) throws Exception {
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        String accessId = (String) session.getAttribute("accessId");
        String actionName = request.getParameter("actionName");
        String actionValue = request.getParameter("actionValue");
        String associateId = request.getParameter("associate");
        formData.setAssociateId(associateId);
        formData.setSubmittedBy(accessId);
        formData.setKra_id(request.getParameter("kra_id"));
        System.out.println("actionValue "+request.getParameter("kra_id"));
        if (accessId != null) {
            formData.setEmployeeId(accessId);
            String recordCount = request.getParameter("recordCount");            
            String[] certificationId = new String[Integer.parseInt(recordCount)];
            String[] qualification = new String[Integer.parseInt(recordCount)];
            String[] krauom = new String[Integer.parseInt(recordCount)];
            String[] kratarget = new String[Integer.parseInt(recordCount)];
            String[] percentage = new String[Integer.parseInt(recordCount)];
           
            int[] deletedTR = new int[Integer.parseInt(recordCount)];
           
            System.out.println("RecordCount" + Integer.parseInt(recordCount));
            for (int i = 0; i < Integer.parseInt(recordCount); i++) {
                certificationId[i] = request.getParameter("certificationId_" + (i + 1));
                qualification[i] = request.getParameter("qualification_" + (i + 1));
                krauom[i]=request.getParameter("krauom_" + (i + 1));
                System.out.println("kra uom"+request.getParameter("krauom_" + (i + 1)));
                kratarget[i]=request.getParameter("kratarget_" + (i + 1));
                percentage[i] = request.getParameter("percentage_" + (i + 1));
               
                deletedTR[i] = Integer.parseInt(request.getParameter("deletedTR_" + (i + 1)));
                
                System.out.println("cert" + certificationId[i]);
                System.out.println(" qualification[i]" + qualification[i]);
            }
            session.setAttribute("actionName", actionName);
            service.updateEmployeeKra(formData,certificationId, qualification, krauom,kratarget,percentage,deletedTR,actionValue);
            if(formData.getQuarterId().equals("5")){
                String kra_id = service.getKraId(formData);
                mv = new ModelAndView("redirect:annualAppraisal.htm?kraId="+kra_id); 
            }else{
                mv = new ModelAndView("redirect:listKra.htm"); 
            }
            
//            mv = new ModelAndView("redirect:rmListKra.htm");
        } else {
            mv = new ModelAndView("redirect:authenticate.htm");
        }
        return mv;
    }
     public ModelAndView updateKraByExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        ModelAndView mvc = new ModelAndView("redirect:generalInfo.htm?actionName=uploadKra&page=1");
        EmployeeDto dto = new EmployeeDto();
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        String quarterId = null;
        if (month >= 0 && month <= 2) {
            quarterId = "4";
        } else if (month >= 3 && month <= 5) {
            quarterId = "1";
        } else if (month >= 6 && month <= 8) {
            quarterId = "2";
        } else if (month >= 9 && month <= 11) {
            quarterId = "3";
        }
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map files = multiRequest.getFileMap();
            String uplodedFilename = null;
            HttpSession session = request.getSession();
            String accessId = (String) session.getAttribute("accessId");
            String empNumber=(String) session.getAttribute("empNumber");
            ArrayList bandList = service.getBands();
             ArrayList empList = service.getEmployeNumbers();
             System.out.println("accessId !!! "+accessId);
            if (accessId != null) {
                dto.setEmployeeId(accessId);
                dto.setSubmittedBy(accessId);
                 
            } 
            dto.setYear(request.getParameter("financialYear"));
            dto.setQuarterId(request.getParameter("quarterId"));
            dto.setKra_id("");
            dto.setCertificationId(null);
            dto.setActionValue("m");
            File dir = new File(CommonFunctions.fileUploadPathKra);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            for (MultipartFile file : (Collection<MultipartFile>) files.values()) {
                POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
                HSSFSheet sheet = workBook.getSheetAt(0);
                Iterator rows = sheet.rowIterator();
                String[] mandtCells = {"0", "1", "2", "3", "4"};
                int rowInSheet = 0;
                int rowCount=0;
                int rowNum = sheet.getLastRowNum()+1;
                System.out.println("row length "+rowNum);
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    // header validation
                    if (row.getRowNum() == 0) {
                        Iterator cells = row.cellIterator();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                        }
                    }//header validation

                    // data validation
                    if (row.getRowNum() > 0){
                        rowInSheet = 1;
                        Iterator cells = row.cellIterator();
                        //logic for mandatory cells
                        for (Short i = 0; i < mandtCells.length; i++) {
                            HSSFCell mandCell = row.getCell(i);
                            if (mandCell == null) {
                                returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + (mandtCells[i]) + " is mandatory ";
                                mvc.addObject("returnMsg", returnMsg);
                                return mvc;
                            }
                        }
                        
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            
                            short currentCell = cell.getCellNum();
                            if (currentCell == 0) {
                                System.out.println(" inside band validation @@@@");
                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                  empnumber = String.valueOf(cellData.toString());
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
//                                    System.out.println(" employee id value : " + splitcell);
                                }
                                if (!empList.contains(empnumber)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Employee id invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                   
                                    return mvc;
                                }
                                String bandId = null;
                                //bandId = service.getEmpid(empnumber);
                                bandId = service.getBandid(empnumber);
                                if (!bandList.contains(bandId) ) {
                                    returnMsg = "Employee is not eligible for KRA the band is less than 'L'";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                                System.out.println("session employee ## "+empNumber);
                                System.out.println("excel empnumber ##"+empnumber);
                                if(empNumber.equals(empnumber)){
                                    returnMsg = "Employee cannot upload KRA for themselves";
                                     mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                        }//cell iteration
                        rowCount++;
                    }//data validation
                    
                }//row iteration
                if (rowInSheet == 0) {
                    returnMsg = " You are trying to upload empty sheet ";
                    mvc.addObject("returnMsg", returnMsg);
                    return mvc;
                }

                // Inserting Data to database
                Iterator rows1 = sheet.rowIterator();
                int i=0;
                ArrayList<List> rowList = new ArrayList<List>();

                while (rows1.hasNext()) {
//                for(int rowvalues = 0; rowvalues<sheet.getLastRowNum()+1;rowvalues++){
//                    System.out.println("inside for loopppppppppp "+rowvalues);
                    HSSFRow row = (HSSFRow) rows1.next();
                    String employeeId = null;
                    Iterator cells = row.cellIterator();
                    if (row.getRowNum() > 0) {
                        ArrayList<String> columnList = new ArrayList<String>();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            String newCell=null;
                            String newCell1=null;
                            short currentCell = cell.getCellNum();
                            if (String.valueOf(currentCell).equals("0")) {
                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                    employeeId = service.getEmpid(empnumber);
                                    dto.setEmployeeId(employeeId);
                                    dto.setAssociateId(employeeId);
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
                                    employeeId = service.getEmpid(empnumber);
                                    dto.setEmployeeId(employeeId);
                                    dto.setAssociateId(employeeId);
                                }
                                columnList.add(employeeId);
                            }
                            if (String.valueOf(currentCell).equals("1")) {
                                columnList.add(cellData);
                            }
                            if (String.valueOf(currentCell).equals("2")) {
                                columnList.add(cellData);
                            }
                            if (String.valueOf(currentCell).equals("3")) {
                                if(cellData.contains("%")){
                                         System.out.println("inside percie %%%%");
                                        newCell=  cellData.replaceAll("%","");
                                        System.out.println("after newCell"+newCell);
                                     columnList.add(newCell);
                                    }else{
                                    columnList.add(cellData);
                                }
                                
                            }
                            if (String.valueOf(currentCell).equals("4")) { 
                                if(cellData.contains("%")){
                                         System.out.println("inside percie 4444");
                                        newCell=  cellData.replaceAll("%","");
                                        System.out.println("after newCell"+newCell);
                                     columnList.add(newCell);
                                    }else{
                                    columnList.add(cellData);
                                }
                            }
                        } //cell iteration  
                        i++;
                        rowList.add(columnList);
                    }
                    
                }//while ending inserting data
                int araySize =1;
                
                for(int rlist =0;rlist<rowList.size();rlist++){
                    if(rlist>0){
                        if((rowList.get(rlist).get(0).equals(rowList.get(rlist-1).get(0))) && rlist!=rowList.size()-1){
                            araySize ++;
                        }else{
                            int mailrow =0;
                            System.out.println("array size "+araySize+rlist);
                            if(rlist==rowList.size()-1){
                                mailrow=rlist-araySize;
                            }else{
                                araySize--;
                                mailrow=rlist-araySize-1;
                            }
                            String[] qualification = new String[araySize+1];
                            String[] krauom = new String[araySize+1];
                            String[] kratarget = new String[araySize+1];
                            String[] percentage = new String[araySize+1];
                            String[] certificationId = new String[araySize+1];
                            int[] deletedTR = new int[araySize+1];
                            for(int clist = 0;clist<=araySize;clist++){
                                System.out.println("row count   "+mailrow+clist);
                                dto.setEmployeeId(rowList.get(mailrow+clist).get(0).toString());
                                dto.setAssociateId(rowList.get(mailrow+clist).get(0).toString());
                                qualification[clist]=rowList.get(mailrow+clist).get(1).toString();
                                krauom[clist]=rowList.get(mailrow+clist).get(2).toString();
                                kratarget[clist]=rowList.get(mailrow+clist).get(3).toString();
                                percentage[clist]=rowList.get(mailrow+clist).get(4).toString();
                                certificationId[clist]="";
                                deletedTR[clist]=0;
                            }
                            try {
                                String kraId = null;
                                kraId = service.getKraId(dto);
                                if(kraId!=null){
                                    mvc.addObject("returnMsg", "KRA already uploaded for the employee for the selected quarter");
                                    return mvc;
                                }else if(!quarterId.equals(dto.getQuarterId())){
                                    mvc.addObject("returnMsg", "KRA Can be uploaded only for current quarter");
                                    return mvc;
                                }else{
                                    service.updateEmployeeKra(dto,certificationId, qualification, krauom,kratarget,percentage,deletedTR,"m");
//                                    uplodedFilename = CommonFunctions.commonCodeForFileUpload(dir, file);
            //                        dto.setFile_name(uplodedFilename);
                                    dto.setKra_id("");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                mvc.addObject("returnMsg", "Upload failed while updating employee ");
                                return mvc;
                            }
                            araySize =1;
                        }
                    }
                }

            }
        }catch (Exception e) {
            e.printStackTrace();
            mvc.addObject("returnMsg", "Upload failed, Please upload .xls file and enter Employee Id in the first column");
            return mvc;
        }
        successMsg = "Successfully uploaded";
        mvc.addObject("successMsg", successMsg);
        return mvc;
        
    }
    public ModelAndView getRmKraExcelExport(HttpServletRequest request, HttpServletResponse response, kraDto filterData) throws IOException, Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
         HttpSession session = request.getSession();
        String employeeId = (String) session.getAttribute("EMP_ID");
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");        
        String financeyear = request.getParameter("financialYear");
        String quarterId = request.getParameter("quarter");              
        filterData.setEmployeeId(employeeId);
        filterData.setFinancial_year(financeyear);
        filterData.setQuarter_id(quarterId);
        List<kraDto> rmKraListForExcel = service.getRMKraForExcel(filterData);       
        ArrayList entireList = new ArrayList();
        ArrayList headerList1 = new ArrayList();        
            headerList1.add(("employee_number"));
            headerList1.add(("employee_name"));
            headerList1.add(("financial_year"));
            headerList1.add(("quarter_name"));
            headerList1.add(("kra_description"));
            headerList1.add(("kra_uom"));
            headerList1.add(("kra_target"));
            headerList1.add(("kra_weightage"));            
            headerList1.add(("STATUS"));
            entireList.add(headerList1);
        for (int i = 0; i < rmKraListForExcel.size(); i++) {
            ArrayList kraDataList = new ArrayList();
            kraDataList.add(("" + rmKraListForExcel.get(i).getEmployee_number()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getEmployee_name()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getFinancial_year()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getQuarter_name()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getKra_description()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getKra_uom()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getKra_target()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getKra_weightage()));            
            kraDataList.add(("" + rmKraListForExcel.get(i).getStatus()));
            entireList.add(kraDataList);
        }
        System.out.println("array size " + entireList.size());
        CommonFunctions.exportRMKraExcel(response, entireList,"RM_kra_details","RM_kra_details","");
        return null;
    }
    public ModelAndView getReportKraExcelExport(HttpServletRequest request, HttpServletResponse response, kraDto filterData) throws IOException, Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
         HttpSession session = request.getSession();
        String employeeId = (String) session.getAttribute("EMP_ID");
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");        
        String financeyear = request.getParameter("financialYear");
        String quarterId = request.getParameter("quarter");              
        filterData.setEmployeeId(employeeId);
        filterData.setFinancial_year(financeyear);
        filterData.setQuarter_id(quarterId);
       // List<kraDto> rmKraListForExcel = service.getRMKraForExcel(filterData);      
        List<kraDto> rmKraListForExcel = service.getReportKraForExcel(filterData);    
        ArrayList entireList = new ArrayList();
        ArrayList headerList1 = new ArrayList();        
            headerList1.add(("employee_number"  ));
            headerList1.add(("employee_name"  ));
            headerList1.add(("financial_year"  ));
            headerList1.add(("quarter_name"  ));
            headerList1.add(("kra_description" ));
            headerList1.add(("kra_uom"  ));
            headerList1.add(("kra_target"  ));
            headerList1.add(("kra_weightage"  ));            
            headerList1.add(("STATUS" ));
            entireList.add(headerList1);
        for (int i = 0; i < rmKraListForExcel.size(); i++) {
            ArrayList kraDataList = new ArrayList();
            System.out.println("inside array "+rmKraListForExcel.get(i).getEmployee_number());
            kraDataList.add(("" + rmKraListForExcel.get(i).getEmployee_number()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getEmployee_name()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getFinancial_year()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getQuarter_name()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getKra_description()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getKra_uom()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getKra_target()));
            kraDataList.add(("" + rmKraListForExcel.get(i).getKra_weightage()));            
            kraDataList.add(("" + rmKraListForExcel.get(i).getStatus()));
            entireList.add(kraDataList);
        }
        System.out.println("array size " + entireList.size());
        CommonFunctions.exportRMKraExcel(response, entireList,"Report_kra_details","Report_kra_details", "");
        return null;
    }
    public ModelAndView getDataExcel(HttpServletRequest request, HttpServletResponse response, kraDto filterData) throws IOException, Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        
        String financeyear = request.getParameter("financialYear");
        String quarterId = request.getParameter("quarterId");
        String employeeId = request.getParameter("employeeId");
        
        List<kraDto> masterDetails = service.getEmployeeKraForExcel(employeeId, financeyear, quarterId);
        List<kraDto> kraDetails = service.getIndividualKraDetails(masterDetails.get(0).getKra_id());
        String total_weightage = service.getTotalWeightage(masterDetails.get(0).getKra_id());
        ArrayList entireList = new ArrayList();
        ArrayList headerList1 = new ArrayList();
        ArrayList headerList2 = new ArrayList();
        ArrayList headerList3 = new ArrayList();
        ArrayList headerList4 = new ArrayList();
        ArrayList footerList = new ArrayList();
        headerList1.add(("" + "Employee Number"));
        headerList1.add(("" + masterDetails.get(0).getEmployee_number()));
        headerList1.add(("" + "Employee Name"));
        headerList1.add(("" + masterDetails.get(0).getEmployee_name()));
        headerList1.add(("" + "Financial Year"));
        headerList1.add(("" + masterDetails.get(0).getFinancial_year()));
        headerList1.add(("" + "KRA Period"));
        headerList1.add(("" + masterDetails.get(0).getQuarter_name()));
//        headerList1.add(new String("" + "Submitted On"));
//        headerList1.add(new String("" + masterDetails.get(0).getSubmitted_on()));
        entireList.add(headerList1);
//        headerList2.add(new String("" + "Approved By"));
//        headerList2.add(new String("" + masterDetails.get(0).getRm_name()));
//        headerList2.add(new String("" + "Approved On"));
//        headerList2.add(new String("" + masterDetails.get(0).getApproved_on()));
        headerList2.add(new String("" + "Submitted On"));
        headerList2.add(new String("" + masterDetails.get(0).getSubmitted_on()));
        headerList2.add(new String("" + "Accepted On"));
        headerList2.add(new String("" + masterDetails.get(0).getAccepted_on()));
        headerList2.add(new String("" + "Status"));
        headerList2.add(new String("" + masterDetails.get(0).getStatus()));
        entireList.add(headerList2);
        headerList3.add(new String("" + ""));
        entireList.add(headerList3);
        headerList4.add(new String("" + "KRA Description"));
        headerList4.add(new String("" + "UOM"));
        headerList4.add(new String("" + "Target"));
        headerList4.add(new String("" + "Weightage"));
        headerList4.add(new String("" + "Achieved"));
        headerList4.add(new String("" + "RM Remarks"));
        entireList.add(headerList4);
        for (int i = 0; i < kraDetails.size(); i++) {
            ArrayList kraDataList = new ArrayList();
            kraDataList.add(("" + kraDetails.get(i).getKra_description()));
            kraDataList.add(("" + kraDetails.get(i).getKra_uom()));
            kraDataList.add(("" + kraDetails.get(i).getKra_target()));
            kraDataList.add(("" + kraDetails.get(i).getKra_weightage()));
            kraDataList.add(("" + kraDetails.get(i).getKra_achieved()));
            kraDataList.add(new String("" + kraDetails.get(i).getRm_remarks()));
            
            entireList.add(kraDataList);
        }
        footerList.add(("" + ""));
        footerList.add(new String("" + ""));
        footerList.add(new String("" + "Total Weightage"));
        footerList.add(new String("" + total_weightage));
        footerList.add(new String("" + ""));
        footerList.add(new String("" + ""));
        entireList.add(footerList);
        System.out.println("array size " + entireList.size());
      //  CommonFunctions.exportToExcelApproval(response, entireList, masterDetails.get(0).getEmployee_number()+"-kra_details",masterDetails.get(0).getEmployee_number()+"-kra_details", "");
        CommonFunctions.exportToExcelApproval(response, entireList, "kra_details","kra_details","");
        return null;
    }

    public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response) {
        HttpSession HttpSessionsession = request.getSession();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Type", "application/force-download");
        
        ModelAndView mvc = new ModelAndView("/uploadKra");
        //  ModelAndView mvc = new ModelAndView("/employeeKra");
        try {
            FileInputStream fis = null;
            
            if (request.getParameter("fileName").equals("kra_buh")) {
                response.setHeader("Content-Disposition", "attachment;filename=kra_buh.xls");
                fis = new FileInputStream("D:/propertyfiles/kra_Template/BUH_TEMPLATE.xls");
            }            
            if (request.getParameter("fileName").equals("kra_sales")) {
                response.setHeader("Content-Disposition", "attachment;filename=kra_sales.xls");
                fis = new FileInputStream("D:/propertyfiles/kra_Template/SALES_TEMPLATE.xls");
            }            
            if (request.getParameter("fileName").equals("kra_practicehead")) {
                response.setHeader("Content-Disposition", "attachment;filename=kra_practicehead.xls");
                fis = new FileInputStream("D:/propertyfiles/kra_Template/PRACTICE_HEADS_TEMPLATE.xls");
            }            
            
            if (request.getParameter("fileName").equals("kra_subpractice")) {
                response.setHeader("Content-Disposition", "attachment;filename=kra_subpractice.xls");
                fis = new FileInputStream("D:/propertyfiles/kra_Template/SUB_PRACTICE_GROUP&PROJECT_MANAGERS_TEMPLATE.xls");
            }            
            if (request.getParameter("fileName").equals("kra_onsite")) {
                response.setHeader("Content-Disposition", "attachment;filename=kra_onsite.xls");
                fis = new FileInputStream("D:/propertyfiles/kra_Template/ONSITE_COORDINATORS_TEMPLATE.xls");
            }            
            
            if (request.getParameter("fileName").equals("kra_contributors")) {
                response.setHeader("Content-Disposition", "attachment;filename=kra_contributors.xls");
                fis = new FileInputStream("D:/propertyfiles/kra_Template/INDIVIDUAL_CONTRIBUTORS_TEMPLATE.xls");
            }            
            if (request.getParameter("fileName").equals("kra_operations")) {
                response.setHeader("Content-Disposition", "attachment;filename=kra_operations.xls");
                fis = new FileInputStream("D:/propertyfiles/kra_Template/BIZ_OPERATIONS_TEMPLATE.xls");
            }            
            if (request.getParameter("fileName").equals("kra_functionalhead")) {
                response.setHeader("Content-Disposition", "attachment;filename=kra_functionalhead.xls");
                fis = new FileInputStream("D:/propertyfiles/kra_Template/FUNCTIONAL_HEADS_TEMPLATE.xls");
            }            
            
            byte[] bs = new byte[1024];
            int len = 0;
            while ((len = fis.read(bs, 0, bs.length)) != -1) {
                response.getOutputStream().write(bs, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        return null;
    }

    public String changeDateFormat(String val) {
        String formattedDate = null;
        if (!val.equals("")) {
            String proDateArray[] = val.split("-");
            if ((proDateArray[2] == null ? "0000" != null : !proDateArray[2].equals("0000")) && (proDateArray[1] == null ? "00" != null : !proDateArray[1].equals("00")) && (proDateArray[0] == null ? "00" != null : !proDateArray[0].equals("00"))) {
                formattedDate = proDateArray[2] + "-" + proDateArray[1] + "-" + proDateArray[0];
            }
        }
        return formattedDate;
    }
    
    public int[] paging(String recordCount, int current_page) {
        int rows = 15;
        int start = 1;
        int end = 1;
        int next = current_page + 1;
        int prev = current_page - 1;
        int[] pageArr = new int[10];
        int totalPage = Integer.parseInt(recordCount) / rows;
        if (Integer.parseInt(recordCount) % rows != 0) {
            totalPage = totalPage + 1;
        }
        System.out.println("totalPage" + totalPage);
        if (totalPage > 9) {
            int minus = current_page - 4;
            int plus = current_page + 4;
            if (minus > 0) {
                start = minus;
            } else {
                start = 1;
            }
            if ((plus - start) < 8) {
                plus = start + 8;
            }
            if (plus > totalPage) {
                end = totalPage;
            } else {
                end = plus;
            }
        } else {
            start = 1;
            end = totalPage;
        }
        if (prev < 1) {
            prev = 1;
        }
        if (next > totalPage) {
            next = totalPage;
        }
        System.out.println("recordCount" + recordCount);
        System.out.println("current_page" + current_page);
        System.out.println("start" + start);
        System.out.println("end" + end);
        System.out.println("Totalpage++++" + totalPage);
        pageArr[0] = totalPage;
        pageArr[1] = current_page;
        pageArr[2] = start;
        pageArr[3] = end;
        pageArr[4] = prev;
        pageArr[5] = next;
        return pageArr;
    }
    
    public void downloadFile_user(HttpServletRequest requestObj, HttpServletResponse response, EmployeeDto formData) throws IOException {
        
        
        String fileName = requestObj.getParameter("filePath");
        String fileNameNew = requestObj.getParameter("originalName");
        String fileType = requestObj.getParameter("fileType");
        String filePath = CommonFunctions.fileUploadPath;
//        final WebApplicationContext ctx = getWebApplicationContext();
//        ((TicSystemServiceImpl) ctx.getBean("TicSystemService")).fileDownload(fileName, fileNameNew, filePath, fileType, response);

        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeService service = (EmployeeServiceImpl) ctx.getBean("EmployeeService");
        service.fileDownload(fileName, fileNameNew, filePath, fileType, response);
    }
}
