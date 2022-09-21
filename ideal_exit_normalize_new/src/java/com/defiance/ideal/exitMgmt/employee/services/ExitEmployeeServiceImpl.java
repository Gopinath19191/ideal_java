/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.employee.services;

import com.defiance.ideal.exitMgmt.employee.dao.ExitEmployeeDAOImpl;
import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;
import java.util.List;

/**
 *
 * @author 14583
 */
public class ExitEmployeeServiceImpl implements ExitEmployeeService {

    private ExitEmployeeDAOImpl employeeDaoImplObj;

    public ExitEmployeeDAOImpl getEmployeeDaoImplObj() {
        return employeeDaoImplObj;
    }

    public void setEmployeeDaoImplObj(ExitEmployeeDAOImpl employeeDaoImplObj) {
        this.employeeDaoImplObj = employeeDaoImplObj;
    }


    public EmployeeDTO getEmployeeDetails(String employeeId) {
        EmployeeDTO employeeDetails = null;
        try {
            employeeDetails = employeeDaoImplObj.getEmployeeDetails(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeDetails;
    }
    public String getEmployeeReporteesCount(String employeeId){
        String reportees_count = null;
        try {
            reportees_count = employeeDaoImplObj.getEmployeeReporteesCount(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportees_count;
    }
    public List<String> getCompanyHolidays(EmployeeDTO employeeDetails) {
         List<String> empHoliday = null;
        try {
            empHoliday = employeeDaoImplObj.getCompanyHolidays(employeeDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empHoliday;
    }
    
    public EmployeeDTO getEmployeeAddress(EmployeeDTO formData) {
        EmployeeDTO empAddressDetails = null;
        try {
            empAddressDetails = employeeDaoImplObj.getEmployeeAddress(formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empAddressDetails;
    }
    
    public List<EmployeeDTO> getEmpAddressDetails(String empId) {
        List<EmployeeDTO> empAddress = null;
        try {
            empAddress = employeeDaoImplObj.getEmpAddressDetails(empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empAddress;
    }

    public List<EmployeeDTO> getCountryList() {
        List<EmployeeDTO> countryList = null;
        try {
            countryList = employeeDaoImplObj.getCountryList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }
    
    public List<EmployeeDTO> getRegnEmpList(String employeeId) {
        List<EmployeeDTO> employeeDetails = null;
        try {
            employeeDetails = employeeDaoImplObj.getRegnEmpList(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeDetails;
    }

    public String getEmpName(String employeeId) {
        String empName = null;
        try {
            empName = employeeDaoImplObj.getEmpName(employeeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empName;
    }

    public void insertExitEmpDetails(EmployeeDTO employeeFormData) {
        try {
            employeeDaoImplObj.insertExitEmpDetails(employeeFormData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    



}
