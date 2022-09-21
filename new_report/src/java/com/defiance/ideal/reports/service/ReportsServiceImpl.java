/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.ReportsDao;
import com.defiance.ideal.reports.dao.ReportsDaoImpl;
import com.defiance.ideal.reports.dto.BillableHoursFilterDTO;
import com.defiance.ideal.reports.dto.FilterDTO;
import com.defiance.ideal.reports.dto.FullReportDTO;
import com.defiance.ideal.reports.dto.PhaseTaskDTO;
import com.defiance.ideal.reports.dto.ReportEmployeeExperienceDTO;
import com.defiance.ideal.reports.dto.ReportsDTO;
import com.defiance.ideal.reports.dto.ReportsDataDTO;
import com.defiance.ideal.reports.dto.ReportsFilterDTO;
import com.defiance.ideal.reports.dto.ReportsTimeDTO;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 *
 * @author 14053
 */
public class ReportsServiceImpl extends MultiActionController implements ReportsService {

    public ReportsDao reportsDao;
ReportsDaoImpl fullReportDAOImpl;

    public ReportsDaoImpl getFullReportDAOImpl() {
        return fullReportDAOImpl;
    }

    public void setFullReportDAOImpl(ReportsDaoImpl fullReportDAOImpl) {
        this.fullReportDAOImpl = fullReportDAOImpl;
    }

   
    public ReportsDao getLoginDao() {
        return reportsDao;
    }

    public void setLoginDao(ReportsDao loginDao) {
        this.reportsDao = loginDao;
    }

    public List<ReportsDTO> getEmployeeList() {
         return reportsDao.getEmployeeList();
    }

    public Map<Integer, String> getProjects(String pmId) {
        return reportsDao.getProjects(pmId);
    }

    public List<ReportsDataDTO> fetchReport(ReportsFilterDTO filterData) {
        List<ReportsDataDTO> associateData = new ArrayList<ReportsDataDTO>();
        ReportsDataDTO accrualData = new ReportsDataDTO();
//        List<ReportsDataDTO> reportData = new ArrayList<ReportsDataDTO>();
        ReportsTimeDTO tmeData,leaveData,allocData;
        String[] wrkdHrs = new String[3];
        try {
            
          System.out.println("filterData Month"+filterData.getMonthFilter());
          System.out.println("filterData Year"+filterData.getYearFilter());
          System.out.println("filterData Project"+filterData.getProjectFilter());

          associateData=reportsDao.fetchAssociateDetails(filterData);

          Calendar calendar = Calendar.getInstance();
          Calendar dayCal = Calendar.getInstance();
          calendar.set(Integer.parseInt(filterData.getYearFilter()),(Integer.parseInt(filterData.getMonthFilter())-1),1);
          int frstDate;
          int lstDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

              List<ReportsTimeDTO> timeData = new ArrayList<ReportsTimeDTO>();
          
              for(int assoc=0; assoc < associateData.size(); assoc++){

                        int totWorkedHrs = 0,totApprvdHrs = 0;
                        timeData=new ArrayList<ReportsTimeDTO>();
    //timeData.removeAll(timeData);
                        for(frstDate=1;frstDate<=lstDate;frstDate++){

                          dayCal.set(Integer.parseInt(filterData.getYearFilter()), (Integer.parseInt(filterData.getMonthFilter())-1), frstDate);

                          filterData.setDayFilter(String.format("%02d",frstDate));
                          filterData.setAssocId(associateData.get(assoc).getEmployeeId());
                          tmeData = reportsDao.fetchWorkDetails(filterData);
                          if(tmeData==null){
    //timeData.add(reportsDao.fetchWorkDetails(filterData));
                                        leaveData = new ReportsTimeDTO();
                                        tmeData = new ReportsTimeDTO();
                                        allocData = new ReportsTimeDTO();

                                leaveData = reportsDao.fetchLeaveDetails(filterData);
                                allocData = reportsDao.fetchAllocDetails(filterData);

                                if(allocData==null){
                                     tmeData.setWorkedHrs("N/A");
                                         tmeData.setApprovType("TRUE");
                                }else{
                                    if(leaveData==null){
                                     tmeData.setWorkedHrs("N");
                                        tmeData.setApprovType("TRUE");
                                    }else{
                                     tmeData.setWorkedHrs("L");
                                        tmeData.setApprovType("TRUE");
                                    }
                                }

                              timeData.add(tmeData);
                          }else{
//                              tmeData = reportsDao.fetchWorkDetails(filterData);

                              if(tmeData.getApprovedHrsSec()!=null){
                                totApprvdHrs += Integer.parseInt(tmeData.getApprovedHrsSec());
                              }
                              if(tmeData.getWorkedHrsSec()!=null){
                                totWorkedHrs += Integer.parseInt(tmeData.getWorkedHrsSec());
                              }
                              wrkdHrs = tmeData.getWorkedHrs().split(":");

                              if(" ".equals(wrkdHrs[1]) || "0".equals(wrkdHrs[1])|| "00".equals(wrkdHrs[1])){
                                tmeData.setWorkedHrs(wrkdHrs[0]);
                              }else{
                                tmeData.setWorkedHrs(wrkdHrs[0]+":"+wrkdHrs[1]);
                              }

    //System.out.println("tmeData.getWorkedHrs() - "+tmeData.getWorkedHrs());
                              timeData.add(tmeData);
                          }
                        }
                            accrualData = reportsDao.fetchAccrualDetails(filterData);

                            if(accrualData!=null){
                                if("a".equals(accrualData.getAccrStatus())){

                                    associateData.get(assoc).setAccrEffort(accrualData.getAccrEffort());

                                }else if("m".equals(accrualData.getAccrStatus())){

                                    associateData.get(assoc).setAccrEffort(accrualData.getAccrEffort());
                                    associateData.get(assoc).setInvEffort(accrualData.getAccrEffort());
                                
                                }
                            }

                associateData.get(assoc).setWorkDetails(timeData);
                associateData.get(assoc).setTotalApprovedHrs(CommonMethods.formatIntoHHMMSS(totApprvdHrs));
                associateData.get(assoc).setTotalWorkedHrs(CommonMethods.formatIntoHHMMSS(totWorkedHrs));

    //System.out.println(" ASSOC "+associateData.get(assoc).getEmployeeId());
    //System.out.println(" ASSOC "+associateData.get(assoc).getEmployeeNumber());
                
            }
//System.out.println("reportData.size()"+reportData.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return associateData;
    }
    
    public List<FilterDTO> getList() {
        System.out.println("Inside get List");
        List<FilterDTO> list=getFullReportDAOImpl().getList();
        return list;
    }
     public List<PhaseTaskDTO> getPhaseTaskList() {
        System.out.println("Inside get phase Task List");
        List<PhaseTaskDTO> list=getFullReportDAOImpl().getPhaseTaskList();
        return list;
    }
    public List<FilterDTO> getSortedList(FullReportDTO dataTo){
        List<FilterDTO> list=getFullReportDAOImpl().getSortedList(dataTo);
        return list;
    }
    public List<PhaseTaskDTO> getPhaseTaskSortedList(FullReportDTO dataTo){
        System.out.println("&&&&&&&&&&&&&"+dataTo.getStatus());
        List<PhaseTaskDTO> list=getFullReportDAOImpl().getPhaseTaskSortedList(dataTo);
        return list;
    }

    public Map<String, String> getSbuList() {
        return fullReportDAOImpl.getSbuList();
    }

    public List<ReportsDTO> getProjectList(String empId, String projectDept, String projectType) {
        List<ReportsDTO> projectList = fullReportDAOImpl.getProjectList(empId,projectDept,projectType);
      return projectList;
    }

    public List<ReportsDTO> getEmployeeList(String projId) {
        List<ReportsDTO> empList = fullReportDAOImpl.getEmployeeList(projId);
        return empList;
    }

    public List<ReportsDataDTO> getTimesheetHoursData(ReportsFilterDTO filterData) {
        List<ReportsDataDTO> timesheetHoursData = fullReportDAOImpl.getTimesheetHoursData(filterData);
        return timesheetHoursData;
    }

    public List<ReportsFilterDTO> getPojectTypeList() {
        List<ReportsFilterDTO> projectTypeList = fullReportDAOImpl.getProjectTypeList();
        return projectTypeList;
    }

    public int getTimesheetHoursDataCount(ReportsFilterDTO filterData) {
        return fullReportDAOImpl.getTimesheetHoursDataCount(filterData);
    }
    
    public List<ReportEmployeeExperienceDTO> getEmployeeExperienceReport(ReportEmployeeExperienceDTO filterData){
        List<ReportEmployeeExperienceDTO> employeeReportDTO = reportsDao.getEmployeeExperienceReport(filterData);
        return employeeReportDTO;
    }

}
