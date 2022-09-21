/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dao;

import com.attendance.employee.attendance.dto.AttendanceDto;
import com.attendance.employee.attendance.dto.AttendancePmProjectReport;
import com.attendance.employee.attendance.dto.AttendancePmReportFilterDto;
import com.attendance.employee.attendance.dto.AttendanceReportDto;
import com.attendance.employee.attendance.dto.AttendanceReportFilterDto;
import com.attendance.employee.attendance.dto.EmpListDTO;
import com.attendance.employee.util.CommonConfigurations;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16047
 */
public class AttendanceDaoImpl extends SqlMapClientDaoSupport implements AttendanceDao {

    public List<AttendanceDto> getSearchList(String empVal, String modId) {
        List<AttendanceDto> searchList = null;
        try {
            String key = empVal + "%";
            System.out.println("in daoimpl::::" + key);
            if (modId.equals("663")) {
                searchList = getSqlMapClientTemplate().queryForList("AttendanceMap.getSubordinateSearList", key);
            } else if (modId.equals("664")) {
                searchList = getSqlMapClientTemplate().queryForList("AttendanceMap.getSearchList", key);
            } else {
                searchList = getSqlMapClientTemplate().queryForList("AttendanceMap.getSearchList", key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public List<AttendanceDto> getAttendanceDetails(AttendanceDto filterData) {
        List<AttendanceDto> attendanceDetails = null;
        try {
            if (filterData.getUnit() == null || "".equals(filterData.getUnit())) {
                filterData.setUnit("All");
            }
            if (filterData.getLocation() == null || "".equals(filterData.getLocation())) {
                filterData.setLocation("All");
            }
            if ("".equals(filterData.getRm_id())) {
                filterData.setRm_id(null);
            }
            Map map = new HashMap();
            map.put("fromDate", filterData.getFromDate());
            map.put("toDate", filterData.getToDate());
            map.put("id", 1);
            System.out.println("fromDate : " + map.get("fromDate"));
            System.out.println("toDate : " + map.get("toDate"));
            System.out.println("EmpID : " + filterData.getEmployee_id());
//            getSqlMapClientTemplate().queryForObject("AttendanceMap.getWorkDates",map);
//            System.out.println("attendanceDetails : "+attendanceDetails);
            attendanceDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getAttendanceDetails", filterData);
            System.out.println("attendanceDetails : " + attendanceDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendanceDetails;
    }

    public String getEmployeeName(String employee_id) {
        String employeeName = null;
        try {
            employeeName = (String) getSqlMapClientTemplate().queryForObject("AttendanceMap.getEmployeeName", employee_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeName;
    }

    public List<AttendanceDto> getEmployeeDetails(AttendanceDto filterData) {
        List<AttendanceDto> employeeDetails = null;
        try {
            employeeDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getEmployeeDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeDetails;
    }

    public AttendanceDto getSwipeDetails(String empId, String from_date) {
        AttendanceDto swipeDetails = null;
        try {
            Map map = new HashMap();
            map.put("empId", empId);
            map.put("from_date", from_date);
            swipeDetails = (AttendanceDto) getSqlMapClientTemplate().queryForObject("AttendanceMap.getSwipeDetails", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return swipeDetails;
    }

    public List<String> getHolidayDetails(String empId, String fromDate, String toDate) {
        List<String> holidayDetails = null;
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String from_date = sdf.format(date1);
            String cityId = (String) getSqlMapClientTemplate().queryForObject("AttendanceMap.getEmpLocation", empId);
            Map map = new HashMap();
            map.put("cityId", cityId);
            map.put("from_date", fromDate);
            map.put("to_date", toDate);
            holidayDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getHolidayDetails", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return holidayDetails;
    }

    public AttendanceDto getMinMaxDates(AttendanceDto filterData) {
        AttendanceDto minMaxDates = null;
        try {
            minMaxDates = (AttendanceDto) getSqlMapClientTemplate().queryForObject("AttendanceMap.getMinMaxDates", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minMaxDates;
    }

    public List<String> getEmpDates(String empId, String fromDate, String toDate) {
        List<String> empDates = null;
        try {
            Map map = new HashMap();
            map.put("empId", empId);
            map.put("fromDate", fromDate);
            map.put("toDate", toDate);
            empDates = getSqlMapClientTemplate().queryForList("AttendanceMap.getEmpDates", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empDates;
    }

    public int getAttendanceCount(AttendanceDto filterData) {
        int attendanceCount = 0;
        try {
            if (filterData.getUnit() == null || "".equals(filterData.getUnit())) {
                filterData.setUnit("All");
            }
            if (filterData.getLocation() == null || "".equals(filterData.getLocation())) {
                filterData.setLocation("All");
            }
            if ("".equals(filterData.getRm_id())) {
                filterData.setRm_id(null);
            }
            Map map = new HashMap();
            map.put("fromDate", filterData.getFromDate());
            map.put("toDate", filterData.getToDate());
            map.put("id", 1);
            System.out.println("fromDate : " + map.get("fromDate"));
            System.out.println("toDate : " + map.get("toDate"));
//            getSqlMapClientTemplate().queryForObject("AttendanceMap.getWorkDates",map);
//            System.out.println("attendanceDetails : "+attendanceCount);
            attendanceCount = (Integer) getSqlMapClientTemplate().queryForObject("AttendanceMap.getAttendanceCount", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendanceCount;
    }

    public List<AttendanceDto> getUnitList() {
        List<AttendanceDto> unitList = null;
        try {
            unitList = getSqlMapClientTemplate().queryForList("AttendanceMap.getUnitList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitList;
    }

    public List<AttendanceDto> getLocationList() {
        List<AttendanceDto> locationList = null;
        try {
            locationList = getSqlMapClientTemplate().queryForList("AttendanceMap.getLocationList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationList;
    }

    public List<AttendanceDto> getSubordinateAttendanceDetails(AttendanceDto filterData) {
        List<AttendanceDto> subordinateAttendanceDetails = null;
        try {

            subordinateAttendanceDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getSubordinateAttendanceDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subordinateAttendanceDetails;
    }

    private void getSubordinateDetails(String emp_ids, AttendanceReportFilterDto filterData) {
        try {
            List<String> subordinateDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getSubordinateDetails", emp_ids);
            if (subordinateDetails != null) {
                getSqlMapClientTemplate().insert("AttendanceMap.insertEmpTemp", emp_ids);
                Iterator itr = subordinateDetails.iterator();
                emp_ids = "";
                while (itr.hasNext()) {
                    String val = (String) itr.next();
                    emp_ids = emp_ids + "'" + val + "',";
                }
                if (!"".equals(emp_ids)) {
                    emp_ids = emp_ids + "'0'";
                    getSubordinateDetails(emp_ids, filterData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public int getSubordinateAttendanceCount(AttendanceDto filterData) {
//        int count = 0;
//        List<String> subordinateDetails = null;
//        try {
//            String emp_ids = "";
//            getSqlMapClientTemplate().insert("AttendanceMap.dropEmpTemp");
//            //getSqlMapClientTemplate().insert("AttendanceMap.insertRmId",filterData.getRm_id());
//            if (filterData.getDirectReportee() != null && !"".equals(filterData.getDirectReportee())) {
//                subordinateDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getSubordinateDetails", filterData.getDirectReportee());
//            } else {
//                subordinateDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getSubordinateDetails", filterData.getRm_id());
//            }
//            if (subordinateDetails != null) {
//                if (filterData.getDirectReportee() != null && !"".equals(filterData.getDirectReportee())) {
//                    getSqlMapClientTemplate().insert("AttendanceMap.insertEmpTemp", filterData.getDirectReportee());
//                } else {
//                    getSqlMapClientTemplate().insert("AttendanceMap.insertEmpTemp", filterData.getRm_id());
//                }
//                Iterator itr = subordinateDetails.iterator();
//                while (itr.hasNext()) {
//                    String val = (String) itr.next();
//                    emp_ids = emp_ids + "'" + val + "',";
//                }
//                if (!"".equals(emp_ids)) {
//                    emp_ids = emp_ids + "'0'";
//                    getSubordinateDetails(emp_ids, filterData);
//                }
//            }
//            count = (Integer) getSqlMapClientTemplate().queryForObject("AttendanceMap.getSubordinateAttendanceCount", filterData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
    public List<AttendanceDto> getReasonList() {
        List<AttendanceDto> reasonList = null;
        try {
            reasonList = getSqlMapClientTemplate().queryForList("AttendanceMap.getReasonList", reasonList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reasonList;
    }

    public int updateChangeTimeDetails(AttendanceDto dto) {
        int status = 0;
        try {
            dto.setRevisedStatus(CommonConfigurations.revised_time_submitted);
            status = getSqlMapClientTemplate().update("AttendanceMap.updateChangeTimeDetails", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public int updateRmRemarks(AttendanceDto dto) {
        int status = 0;
        try {
            status = getSqlMapClientTemplate().update("AttendanceMap.updateRmRemarks", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<AttendanceDto> getDirectReportees(String rm_id) {
        List<AttendanceDto> directReportees = null;
        try {
            directReportees = getSqlMapClientTemplate().queryForList("AttendanceMap.getDirectReportees", rm_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directReportees;
    }

    public int getAttendanceReportCount(AttendanceReportFilterDto oReportFilter) {
        List<String> subordinateDetails = null;
        int iReportRecordCount = 0;
        try {
            if (oReportFilter.getModuleId().equals("663")) {
                String emp_ids = "";
                getSqlMapClientTemplate().insert("AttendanceMap.dropEmpTemp");
                //getSqlMapClientTemplate().insert("AttendanceMap.insertRmId",filterData.getRm_id());
                if (oReportFilter.getRmId() != null && !"".equals(oReportFilter.getRmId())) {
                    subordinateDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getSubordinateDetails", oReportFilter.getRmId());
                } else {
                    subordinateDetails = getSqlMapClientTemplate().queryForList("AttendanceMap.getSubordinateDetails", oReportFilter.getLoggedInempId());
                }
                if (subordinateDetails != null) {
                    if (oReportFilter.getRmId() != null && !"".equals(oReportFilter.getRmId())) {
                        getSqlMapClientTemplate().insert("AttendanceMap.insertEmpTemp", oReportFilter.getRmId());
                    } else {
                        getSqlMapClientTemplate().insert("AttendanceMap.insertEmpTemp", oReportFilter.getLoggedInempId());
                    }
                    Iterator itr = subordinateDetails.iterator();
                    while (itr.hasNext()) {
                        String val = (String) itr.next();
                        emp_ids = emp_ids + "'" + val + "',";
                    }
                    if (!"".equals(emp_ids)) {
                        emp_ids = emp_ids + "'0'";
                        getSubordinateDetails(emp_ids, oReportFilter);
                    }
                }
                //
            }
            //

            Object objOutput = getSqlMapClientTemplate().queryForObject("AttendanceMap.getAttendanceReportCount", oReportFilter);
            if (objOutput != null) {
                iReportRecordCount = Integer.parseInt(objOutput.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iReportRecordCount;
    }

    public List<AttendanceReportDto> getAttendanceReport(AttendanceReportFilterDto oReportFilter) {
        List<AttendanceReportDto> lstAttendanceRpt = null;
        try {
            lstAttendanceRpt = getSqlMapClientTemplate().queryForList("AttendanceMap.getAttendanceReport", oReportFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstAttendanceRpt;
    }
    
//     Added for PM Attendance Report
    public List<AttendancePmProjectReport> getPmProjectAttendanceReport(AttendancePmReportFilterDto pmAttReport){
        List<AttendancePmProjectReport> pmAttendanceReport = null;
        pmAttendanceReport = getSqlMapClientTemplate().queryForList("AttendanceMap.getPmAttReport", pmAttReport);
        return pmAttendanceReport;
    }
    public String recordCount(AttendancePmReportFilterDto appDTO) {
        String recordCount = null;
        try {
            recordCount = (String) getSqlMapClientTemplate().queryForObject("AttendanceMap.getTimesheetEntryCount", appDTO);

        } catch (Exception e) {
        }
        return recordCount;
    }
    
    public List<AttendancePmProjectReport> getProjectList(AttendancePmReportFilterDto prjList) {
        List<AttendancePmProjectReport> projectList = null;
        try {
            projectList = (List<AttendancePmProjectReport>) getSqlMapClientTemplate().queryForList("AttendanceMap.getProjectList",prjList);
        } catch (Exception e) {
        }
        return projectList;
    }
    
     public List<EmpListDTO> getEmployeeList(EmpListDTO empListDTO) {
        List<EmpListDTO> employeeList = null;
        List<EmpListDTO> temp = new ArrayList<EmpListDTO>();
        boolean contains;
        try {
            employeeList = (List<EmpListDTO>) getSqlMapClientTemplate().queryForList("AttendanceMap.getEmpList", empListDTO);
            int i = 0;
            String resignedStatus[] = new String[]{"r", "t", "b", "q", "o"};
            Iterator itr = employeeList.iterator();
            while (itr.hasNext()) {
                EmpListDTO dto = (EmpListDTO) itr.next();
                contains = Arrays.asList(resignedStatus).contains(dto.getEmploymentStatus());
                if (!contains) {
                    temp.add(employeeList.get(i));
                }
                i++;
            }
            employeeList.clear();

        } catch (Exception e) {
        }
        return temp;
    }
//      public List<AttendancePmProjectReport> filterDataList(AttendancePmReportFilterDto appDTO) {
//        List<AttendancePmProjectReport> filterDataList = null;
//        try {
//            // System.out.println("selectedValue-------" + appDTO.getValue());
//            if (!appDTO.getProjectId().equals("Non_Project_Activity")) {
//                filterDataList = (List<AttendancePmProjectReport>) getSqlMapClientTemplate().queryForList("AttendanceMap.getPmAttReport", appDTO);
//            } 
//            else {
//                filterDataList = (List<AttendancePmProjectReport>) getSqlMapClientTemplate().queryForList("AttendanceMap.getPmAttReport", appDTO);
//            }
//
//        } catch (Exception e) {
//        }
//        return filterDataList;
//    }
   
}
