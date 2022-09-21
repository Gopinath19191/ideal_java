/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.BillableHoursFilterDTO;
import com.defiance.ideal.reports.dto.FilterDTO;
import com.defiance.ideal.reports.dto.FullReportDTO;
import com.defiance.ideal.reports.dto.PhaseTaskDTO;
import com.defiance.ideal.reports.dto.ReportEmployeeExperienceDTO;
import com.defiance.ideal.reports.dto.ReportsDTO;
import com.defiance.ideal.reports.dto.ReportsDataDTO;
import com.defiance.ideal.reports.dto.ReportsFilterDTO;
import com.defiance.ideal.reports.dto.ReportsTimeDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14053
 */
public class ReportsDaoImpl extends SqlMapClientDaoSupport implements ReportsDao {
private String sbuId;

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }

    public synchronized List<ReportsDTO> getEmployeeList() {

        List<ReportsDTO> template = null;

        try {

            template = getSqlMapClientTemplate().queryForList("ReportsMap.getUsersList");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public synchronized Map<Integer, String> getProjects(String employeeId) {
        Map<Integer, String> projectList = new LinkedHashMap<Integer, String>();
        try {
//            String employeeId = (String) getSqlMapClientTemplate().queryForObject("ReportsMap.getEmployeeId", pmId);
            projectList = getSqlMapClientTemplate().queryForMap("ReportsMap.getProjectsList",employeeId,"projectId","projectName");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projectList;
    }

    public synchronized List<ReportsDataDTO> fetchReport(ReportsFilterDTO filterData) {
        List<ReportsDataDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("ReportsMap.getUsersList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public synchronized List<ReportsDataDTO> fetchAssociateDetails(ReportsFilterDTO filterData) {

        List<ReportsDataDTO> associateList = null;

        try {

            associateList = getSqlMapClientTemplate().queryForList("ReportsMap.getAssociateList", filterData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return associateList;

    }

    public synchronized ReportsTimeDTO fetchWorkDetails(ReportsFilterDTO filterData) {
        ReportsTimeDTO workDetails = null;
//            System.out.println("IN workDetails");
        try {
            workDetails = (ReportsTimeDTO) getSqlMapClientTemplate().queryForObject("ReportsMap.getWorkDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workDetails;
    }

    public synchronized ReportsTimeDTO fetchLeaveDetails(ReportsFilterDTO filterData) {
        ReportsTimeDTO leaveDetails = null;
//        System.out.println("IN leaveDetails");
        try {
//            System.out.println("Assoc Id"+filterData.getAssocId());
            leaveDetails = (ReportsTimeDTO) getSqlMapClientTemplate().queryForObject("ReportsMap.getLeaveDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return leaveDetails;
    }

    public synchronized ReportsTimeDTO fetchAllocDetails(ReportsFilterDTO filterData) {
        ReportsTimeDTO allocDetails = null;
//        System.out.println("IN allocDetails");
        try {
            allocDetails = (ReportsTimeDTO) getSqlMapClientTemplate().queryForObject("ReportsMap.getAllocationDetails", filterData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allocDetails;
    }
    public synchronized List<FilterDTO> getList(){
       // System.out.println("Inside DAO");
        List<FilterDTO> list = new ArrayList<FilterDTO>();
        try{
         list = (List<FilterDTO>) getSqlMapClientTemplate().queryForList("ReportsMap.getList");
        }catch(Exception e){
       //     System.out.println(e);
        }
        return list;
    }
     public synchronized List<PhaseTaskDTO> getPhaseTaskList(){
       // System.out.println("Inside DAO");
        List<PhaseTaskDTO> list = new ArrayList<PhaseTaskDTO>();
        try{
         list = (List<PhaseTaskDTO>) getSqlMapClientTemplate().queryForList("ReportsMap.getPhaseTaskList");
         //   System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+list.size());
        }catch(Exception e){
          //  System.out.println(e);
        }
        return list;
    }
    public synchronized List<FilterDTO> getSortedList(FullReportDTO dataTo){
        FullReportDTO dto=new FullReportDTO();
        Map map=new HashMap();
        List<FilterDTO> finalList=new ArrayList<FilterDTO>();
        try{
            System.out.println("sbu id "+dataTo.getSbu());
              finalList = (List<FilterDTO>) getSqlMapClientTemplate().queryForList("ReportsMap.finalList",dataTo);
             // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+finalList.size()+"###########"+dto.getOwnership());
        }catch(Exception e){
           // System.out.println("#######################"+e);
        }
        return finalList;
    }
    public synchronized List<PhaseTaskDTO> getPhaseTaskSortedList(FullReportDTO dataTo){
        FullReportDTO dto=new FullReportDTO();
       // System.out.println("###########"+dataTo.getStatus());
        Map map=new HashMap();
        List<PhaseTaskDTO> finalList=new ArrayList<PhaseTaskDTO>();
        try {

            finalList = (List<PhaseTaskDTO>) getSqlMapClientTemplate().queryForList("ReportsMap.finalPhaseTaskList", dataTo);
            System.out.println("!!!!!!!!!!!!!!!!!!phaseTask!!!!!!!!!!!!!!!!!!!!!" + finalList.size());
        } catch (Exception e) {
           // System.out.println("#######################" + e);
        }
        return finalList;
    }


    public synchronized ReportsDataDTO fetchAccrualDetails(ReportsFilterDTO filterData) {
//        System.out.println("IN accrualDetails");
        ReportsDataDTO accrualDet = null;
        try {
            accrualDet = (ReportsDataDTO) getSqlMapClientTemplate().queryForObject("ReportsMap.getAccrualDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accrualDet;
    }
             

//    public List<ReportsDTO> getMonthsList() {
//        List<ReportsDTO> template = null;
//
//        try {
//            template = getSqlMapClientTemplate().queryForList("ReportsMap.g");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return template;
//    }

    public Map<String, String> getSbuList() {
        Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
            sbuList = getSqlMapClientTemplate().queryForMap("ReportsMap.getSbuList",sbuId,"sbuId", "sbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
    }

    public List<ReportsDTO> getProjectList(String empId, String projectDept, String projectType) {
        List<ReportsDTO> projectList = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        if(projectType == null){
            projectType = "";
        }
        map.put("empId",empId);
        map.put("projectDept",projectDept);
        map.put("projectType",projectType);
        System.out.println("Project Dept: "+projectDept);
        try {
            projectList = getSqlMapClientTemplate().queryForList("ReportsMap.getProjectList",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<ReportsDTO> getEmployeeList(String projId) {
        HashMap map = new HashMap();
        map.put("projId", projId);
        List<ReportsDTO> empList = getSqlMapClientTemplate().queryForList("ReportsMap.getEmpList",map);
        return empList;
    }

    public List<ReportsDataDTO> getTimesheetHoursData(ReportsFilterDTO filterData) {
        if(filterData.getEmployeeNameFilter() == null || "0".equals(filterData.getEmployeeNameFilter())){
            filterData.setEmployeeNameFilter("");
        }
        if(filterData.getMonthFilter() == null){
            filterData.setMonthFilter("");
        }
        if(filterData.getYearFilter() == null){
            filterData.setYearFilter("");
        }
        String projectModel = (String)getSqlMapClientTemplate().queryForObject("ReportsMap.getProjectType", filterData.getNameFilter());
        System.out.println("monthFilter "+filterData.getMonthFilter());
        System.out.println("yearFilter "+filterData.getYearFilter());
        if(projectModel == null){
            projectModel = "";
        }
        List<ReportsDataDTO> timesheetHoursData = null;
        if(projectModel.equals("wpo")){
        timesheetHoursData = getSqlMapClientTemplate().queryForList("ReportsMap.getTimesheetHoursData",filterData);
        }else{
            timesheetHoursData = getSqlMapClientTemplate().queryForList("ReportsMap.getTimesheetHoursData1",filterData);
        }
        return timesheetHoursData;
    }

    public List<ReportsFilterDTO> getProjectTypeList() {
        List<ReportsFilterDTO> projectTypeList = getSqlMapClientTemplate().queryForList("ReportsMap.getProjectTypeList");
        return projectTypeList;
    }

    public int getTimesheetHoursDataCount(ReportsFilterDTO filterData) {
        int timesheetHoursDataCount = 0;
        try{
            if(filterData.getEmployeeNameFilter() == null || "0".equals(filterData.getEmployeeNameFilter())){
            filterData.setEmployeeNameFilter("");
        }
        if(filterData.getMonthFilter() == null){
            filterData.setMonthFilter("");
        }
        if(filterData.getYearFilter() == null){
            filterData.setYearFilter("");
        }
        List<ReportsDataDTO> timesheetHoursData = getSqlMapClientTemplate().queryForList("ReportsMap.getTimesheetHoursDataCount",filterData);
        timesheetHoursDataCount = timesheetHoursData.size();
        }catch(Exception e){
            e.printStackTrace();
        }
        return timesheetHoursDataCount;
    }
    
    public List<ReportEmployeeExperienceDTO> getEmployeeExperienceReport(ReportEmployeeExperienceDTO filterData) {
         List<ReportEmployeeExperienceDTO> employeeReport = null;
        try {
            employeeReport = getSqlMapClientTemplate().queryForList("ReportsMap.getEmployeeExperienceReport", filterData);
            System.out.println("count   " + employeeReport.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeReport;
    }
}
