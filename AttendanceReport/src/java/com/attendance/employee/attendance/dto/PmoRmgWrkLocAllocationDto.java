/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.attendance.dto;

/**
 *
 * @author 16365
 */
public class PmoRmgWrkLocAllocationDto {
    
    //For Authentication
    
    private String userName;
    private String password;
    private String idealUserName;
    private String idealLoginTime;
    public  String empId;
    private String groupId;
    private String userAccountId;
    private String moduleId;
    private String gCreate;
    private String gRead;
    private String gUpdate;
    private String gDelete;
    private String uCreate;
    private String uRead;
    private String uUpdate;
    private String uDelete;
    private String menuName;
    private String menuId;
    private String moduleName;
    private String employeeId;

    //To dispaly employee List
    
    private String empNumber;
    private String empName;
    private String baseLocation;
    private String workLocation;
    private String effectiveFrom;
    private String htlBaseLocationId;
    private String htlBaseLocationName;
    
    //To filter data
    private String managerSearch;
    private String baseLocationId;
    private String workLocationId;
    private String baseLocationName;
    private String workLocationName;
    private String employeeSearch;
    private String cusWrkLocAddress;
    private String cusWrkLocId;
    private String cusId;
    private String cusName;
    private String managerId;
    private String managerName;

    //To insert
    
    private String[] empLocChangeList;
    private String employeeWrkLocId;
    private String locationTable;
    private String employeeOldWrkLocId;
    private String fromDate;
    private String toDate;
    private String oldEffectiveFrom;
    private String calendar_id;
    private String calendarName;
    private String available_hours;
    private String oldAvailableHours;
    private String oldCalendar_id;
    private String created_by;
    private String reportingManager;
    private String flag;
    private String isAllocated;

    public String getIsAllocated() {
        return isAllocated;
    }

    public void setIsAllocated(String isAllocated) {
        this.isAllocated = isAllocated;
    }    
    
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }        
    
    public String getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(String reportingManager) {
        this.reportingManager = reportingManager;
    }
        
    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
    

    public String getOldCalendar_id() {
        return oldCalendar_id;
    }

    public void setOldCalendar_id(String oldCalendar_id) {
        this.oldCalendar_id = oldCalendar_id;
    }
        
    public String getOldAvailableHours() {
        return oldAvailableHours;
    }

    public void setOldAvailableHours(String oldAvailableHours) {
        this.oldAvailableHours = oldAvailableHours;
    }
    
    
    public String getAvailable_hours() {
        return available_hours;
    }

    public void setAvailable_hours(String available_hours) {
        this.available_hours = available_hours;
    }
    
    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    
    
    public String getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(String calendar_id) {
        this.calendar_id = calendar_id;
    }
        
    public String getOldEffectiveFrom() {
        return oldEffectiveFrom;
    }

    public void setOldEffectiveFrom(String oldEffectiveFrom) {
        this.oldEffectiveFrom = oldEffectiveFrom;
    }
    
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public String getEmployeeOldWrkLocId() {
        return employeeOldWrkLocId;
    }

    public void setEmployeeOldWrkLocId(String employeeOldWrkLocId) {
        this.employeeOldWrkLocId = employeeOldWrkLocId;
    }
    
    public String getHtlBaseLocationId() {
        return htlBaseLocationId;
    }

    public void setHtlBaseLocationId(String htlBaseLocationId) {
        this.htlBaseLocationId = htlBaseLocationId;
    }

    public String getHtlBaseLocationName() {
        return htlBaseLocationName;
    }

    public void setHtlBaseLocationName(String htlBaseLocationName) {
        this.htlBaseLocationName = htlBaseLocationName;
    }
    
    public String[] getEmpLocChangeList() {
        return empLocChangeList;
    }

    public void setEmpLocChangeList(String[] empLocChangeList) {
        this.empLocChangeList = empLocChangeList;
    }

    public String getLocationTable() {
        return locationTable;
    }

    public void setLocationTable(String locationTable) {
        this.locationTable = locationTable;
    }
    

    public String getEmployeeWrkLocId() {
        return employeeWrkLocId;
    }

    public void setEmployeeWrkLocId(String employeeWrkLocId) {
        this.employeeWrkLocId = employeeWrkLocId;
    }
    
    
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
    
    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }
    
    public String getCusWrkLocAddress() {
        return cusWrkLocAddress;
    }

    public void setCusWrkLocAddress(String cusWrkLocAddress) {
        this.cusWrkLocAddress = cusWrkLocAddress;
    }

    public String getCusWrkLocId() {
        return cusWrkLocId;
    }

    public void setCusWrkLocId(String cusWrkLocId) {
        this.cusWrkLocId = cusWrkLocId;
    }    
    
    public String getManagerSearch() {
        return managerSearch;
    }

    public void setManagerSearch(String managerSearch) {
        this.managerSearch = managerSearch;
    }

    public String getEmployeeSearch() {
        return employeeSearch;
    }

    public void setEmployeeSearch(String employeeSearch) {
        this.employeeSearch = employeeSearch;
    }
    
    public String getBaseLocationName() {
        return baseLocationName;
    }

    public void setBaseLocationName(String baseLocationName) {
        this.baseLocationName = baseLocationName;
    }

    public String getWorkLocationName() {
        return workLocationName;
    }

    public void setWorkLocationName(String workLocationName) {
        this.workLocationName = workLocationName;
    }
    
    public String getBaseLocationId() {
        return baseLocationId;
    }

    public void setBaseLocationId(String baseLocationId) {
        this.baseLocationId = baseLocationId;
    }

    public String getWorkLocationId() {
        return workLocationId;
    }

    public void setWorkLocationId(String workLocationId) {
        this.workLocationId = workLocationId;
    }
    
    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdealUserName() {
        return idealUserName;
    }

    public void setIdealUserName(String idealUserName) {
        this.idealUserName = idealUserName;
    }

    public String getIdealLoginTime() {
        return idealLoginTime;
    }

    public void setIdealLoginTime(String idealLoginTime) {
        this.idealLoginTime = idealLoginTime;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getgCreate() {
        return gCreate;
    }

    public void setgCreate(String gCreate) {
        this.gCreate = gCreate;
    }

    public String getgRead() {
        return gRead;
    }

    public void setgRead(String gRead) {
        this.gRead = gRead;
    }

    public String getgUpdate() {
        return gUpdate;
    }

    public void setgUpdate(String gUpdate) {
        this.gUpdate = gUpdate;
    }

    public String getgDelete() {
        return gDelete;
    }

    public void setgDelete(String gDelete) {
        this.gDelete = gDelete;
    }

    public String getuCreate() {
        return uCreate;
    }

    public void setuCreate(String uCreate) {
        this.uCreate = uCreate;
    }

    public String getuRead() {
        return uRead;
    }

    public void setuRead(String uRead) {
        this.uRead = uRead;
    }

    public String getuUpdate() {
        return uUpdate;
    }

    public void setuUpdate(String uUpdate) {
        this.uUpdate = uUpdate;
    }

    public String getuDelete() {
        return uDelete;
    }

    public void setuDelete(String uDelete) {
        this.uDelete = uDelete;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    
}
