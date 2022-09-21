/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.service;

import com.attendance.employee.attendance.dao.AttendanceDaoImpl;
import com.attendance.employee.attendance.dto.AttendanceDto;
import com.attendance.employee.attendance.dto.AttendancePmProjectReport;
import com.attendance.employee.attendance.dto.AttendancePmReportFilterDto;
import com.attendance.employee.attendance.dto.AttendanceReportDto;
import com.attendance.employee.attendance.dto.AttendanceReportFilterDto;
import com.attendance.employee.attendance.dto.EmpListDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 16047
 */
public class AttendanceServiceImpl implements AttendanceService{
    
    private AttendanceDaoImpl attendanceDao;

    public AttendanceDaoImpl getAttendanceDao() {
        return attendanceDao;
    }

    public void setAttendanceDao(AttendanceDaoImpl attendanceDao) {
        this.attendanceDao = attendanceDao;
    }
    

    public List<AttendanceDto> getSearchList(String empVal,String modId) {
        List<AttendanceDto> searchList = null;
        try{
            searchList = attendanceDao.getSearchList(empVal,modId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return searchList;
    }

    public List<AttendanceDto> getAttendanceDetails(AttendanceDto filterData) {
        List<AttendanceDto> attendanceDetails = null;
        try{
            attendanceDetails = attendanceDao.getAttendanceDetails(filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return attendanceDetails;
    }

    public String getEmployeeName(String employee_id) {
        String employeeName = null;
        try{
            employeeName = attendanceDao.getEmployeeName(employee_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeName;
    }

    public List<AttendanceDto> getEmployeeDetails(AttendanceDto filterData) {
        List<AttendanceDto> employeeDetails = null;
        try{
            employeeDetails = attendanceDao.getEmployeeDetails(filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeDetails;
    }

    public AttendanceDto getSwipeDetails(String empId, String from_date) {
        AttendanceDto swipeDetails = null;
        try{
            swipeDetails = attendanceDao.getSwipeDetails(empId,from_date);
        }catch(Exception e){
            e.printStackTrace();
        }
        return swipeDetails;
    }

    public List<String> getHolidayDetails(String empId,String fromDate,String toDate) {
        List<String> holidayDetails = null;
        try{
            holidayDetails = attendanceDao.getHolidayDetails(empId,fromDate,toDate);
        }catch(Exception e){
           e.printStackTrace();
        }
        return holidayDetails;
    }

    public AttendanceDto getMinMaxDates(AttendanceDto filterData) {
        AttendanceDto minMaxDates = null;
        try{
            minMaxDates = attendanceDao.getMinMaxDates(filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return minMaxDates;
    }

    public List<String> getEmpDates(String empId, String fromDate, String toDate) {
        List<String> empDates = null;
        try{
            empDates = attendanceDao.getEmpDates(empId,fromDate,toDate);
        }catch(Exception e){
            e.printStackTrace();
        }
        return empDates;
    }

    public int getAttendanceCount(AttendanceDto filterData) {
        int attendanceCount = 0;
        try{
            attendanceCount = attendanceDao.getAttendanceCount(filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return attendanceCount;
    }

    public List<AttendanceDto> getUnitList() {
        List<AttendanceDto> unitList = null;
        try{
            unitList = attendanceDao.getUnitList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return unitList;
    }

    public List<AttendanceDto> getLocationList() {
        List<AttendanceDto> locationList = null;
        try{
            locationList = attendanceDao.getLocationList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return locationList;
    }

    public List<AttendanceDto> getSubordinateAttendanceDetails(AttendanceDto filterData) {
        List<AttendanceDto> subordinateAttendanceDetails = null;
        try{
            subordinateAttendanceDetails = attendanceDao.getSubordinateAttendanceDetails(filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return subordinateAttendanceDetails;
    }

    public int getSubordinateAttendanceCount(AttendanceReportFilterDto filterData) {
        int count = 0;
        try{
            //count = attendanceDao.getSubordinateAttendanceCount(filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public List<AttendanceDto> getReasonList() {
        List<AttendanceDto> reasonList = null;
        try{
            reasonList = attendanceDao.getReasonList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return reasonList;
    }

    public int updateChangeTimeDetails(AttendanceDto dto) {
        int status = 0;
        try{
            status = attendanceDao.updateChangeTimeDetails(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public int updateRmRemarks(AttendanceDto dto) {
        int status = 0;
        try{
            status = attendanceDao.updateRmRemarks(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }

    public List<AttendanceDto> getDirectReportees(String rm_id) {
          List<AttendanceDto> directReportiees = null;
          try{
              directReportiees = attendanceDao.getDirectReportees(rm_id);
          }catch(Exception e){
              e.printStackTrace();
          }
          return directReportiees;
    }
    
     public int getAttendanceReportCount(AttendanceReportFilterDto oReportFilter) {
          int iReportRecordCount = 0;
          try{
              iReportRecordCount = attendanceDao.getAttendanceReportCount(oReportFilter);
          }catch(Exception e){
              e.printStackTrace();
          }
          return iReportRecordCount;
    }
    
    public List<AttendanceReportDto> getAttendanceReport(AttendanceReportFilterDto oReportFilter) {
          List<AttendanceReportDto> lstAttendanceRpt = null;
          try{
              lstAttendanceRpt = attendanceDao.getAttendanceReport(oReportFilter);
          }catch(Exception e){
              e.printStackTrace();
          }
          return lstAttendanceRpt;
    }
    public List<AttendancePmProjectReport> getPmProjectAttendanceReport(AttendancePmReportFilterDto pmAttReport){
         List<AttendancePmProjectReport> pmAttendanceReport = null;
        pmAttendanceReport = attendanceDao.getPmProjectAttendanceReport(pmAttReport);
        return pmAttendanceReport;
    }
    public String recordCount(AttendancePmReportFilterDto appDTO) {
        String recordCount = null;
        try {
            recordCount = attendanceDao.recordCount(appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordCount;
    }
     public List<AttendancePmProjectReport> getProjectList(AttendancePmReportFilterDto prjList) {
        List<AttendancePmProjectReport> projectList = null;
        try {
            projectList = attendanceDao.getProjectList(prjList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
    
      public List<EmpListDTO> getEmployeeList(EmpListDTO empListDTO) {
        List<EmpListDTO> projectList = null;
        try {
            projectList = attendanceDao.getEmployeeList(empListDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
      
//     public List<AttendancePmProjectReport> filterDataList(AttendancePmReportFilterDto appDTO) {
//        List<AttendancePmProjectReport> filterDataList = null;
//        try {
//            filterDataList = attendanceDao.filterDataList(appDTO);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return filterDataList;
//    } 
}
