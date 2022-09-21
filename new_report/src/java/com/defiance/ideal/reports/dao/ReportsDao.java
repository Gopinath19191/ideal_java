/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.FilterDTO;
import com.defiance.ideal.reports.dto.FullReportDTO;
import com.defiance.ideal.reports.dto.PhaseTaskDTO;
import com.defiance.ideal.reports.dto.ReportEmployeeExperienceDTO;
import com.defiance.ideal.reports.dto.ReportsDTO;
import com.defiance.ideal.reports.dto.ReportsDataDTO;
import com.defiance.ideal.reports.dto.ReportsFilterDTO;
import com.defiance.ideal.reports.dto.ReportsTimeDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface ReportsDao {

    public List<ReportsDTO> getEmployeeList();

    public Map<Integer, String> getProjects(String pmId);

    public List<ReportsDataDTO> fetchReport(ReportsFilterDTO filterData);

    public List<ReportsDataDTO> fetchAssociateDetails(ReportsFilterDTO filterData);

    public ReportsTimeDTO fetchWorkDetails(ReportsFilterDTO filterData);

    public ReportsTimeDTO fetchLeaveDetails(ReportsFilterDTO filterData);

    public ReportsTimeDTO fetchAllocDetails(ReportsFilterDTO filterData);


    public ReportsDataDTO fetchAccrualDetails(ReportsFilterDTO filterData);

    public List<FilterDTO> getList();

    public List<PhaseTaskDTO> getPhaseTaskList();

    public List<FilterDTO> getSortedList(FullReportDTO dataTo);

    public List<PhaseTaskDTO> getPhaseTaskSortedList(FullReportDTO dataTo);
    public Map<String, String> getSbuList();
    public List getProjectList(String empId, String projectDept,String projectType);
    public List<ReportsDTO> getEmployeeList(String projId);
    public List<ReportsDataDTO> getTimesheetHoursData(ReportsFilterDTO filterData);
    public List<ReportsFilterDTO> getProjectTypeList();
    public int getTimesheetHoursDataCount(ReportsFilterDTO filterData);
    public List<ReportEmployeeExperienceDTO> getEmployeeExperienceReport(ReportEmployeeExperienceDTO filterData);
}