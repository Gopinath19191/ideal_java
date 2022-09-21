/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dao;

import com.ideal.hrMaster.dto.AuditListDto;
import com.ideal.hrMaster.dto.EmployeHistoryDto;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16113
 */
public class BulkUploadDao extends SqlMapClientDaoSupport {

    public ArrayList getBands() {
        return (ArrayList) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getBands");
    }

    public ArrayList getDesignations() {
        return (ArrayList) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getDesignations");
    }

    public ArrayList getEmployeNumbers() {
        return (ArrayList) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getEmployeNumber");
    }
    
    public EmployeHistoryDto getSerlize(String empNumber) {
        return (EmployeHistoryDto) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getSerlize", empNumber);
    }

    public String getDesignationid(String designation) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getDesignationid", designation);
    }

    public String getBandid(String band) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getBandid", band);
    }
    
    public String getEmpid(String empNumber) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getEmpid", empNumber);
    }
    public String getSingleLocation(String loc) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getSingleLocation", loc);
    }
    
     public EmployeHistoryDto getSourceE(EmployeHistoryDto dto) {
        return (EmployeHistoryDto) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getSourceData", dto);
    }
    public int updateBandDesignation(EmployeHistoryDto dto) {
        System.out.println("dtooo "+dto.getEmployee_number()+"datae "+dto.getDate());
        EmployeHistoryDto tempData = (EmployeHistoryDto)getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getEmpData", dto);
        int i = 0;
        if(tempData!=null){
            System.out.println("update excel second2222222");
            i =  getSqlMapClientTemplate().update("BulkUploadingMap.updateEmployeExcel", dto); 
        }else{
            System.out.println("insert");
            i =  getSqlMapClientTemplate().update("BulkUploadingMap.updateEmploye", dto);
        }
        return i;
    }
    
    public int updateManager(EmployeHistoryDto dto) {
        return getSqlMapClientTemplate().update("BulkUploadingMap.updateEmployeeManager", dto);
    }
    
    public void insertEmpHistory(EmployeHistoryDto dto) {
        getSqlMapClientTemplate().insert("BulkUploadingMap.insertHistory", dto);
    }

    public EmployeHistoryDto getEmployeId(String authEmpid) {
        return (EmployeHistoryDto) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getEmployeId", authEmpid);
    }

    public EmployeHistoryDto getSingleEmployee(String employee_number) {
        return (EmployeHistoryDto) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getSingleEmploye", employee_number);
    }
    
    

//    public void saveAuditHistory(AuditListDto list) {
//        getSqlMapClientTemplate().insert("BulkUploadingMap.insertAudithistory", list);
//    }

    public List<AuditListDto> showAuditList(AuditListDto dto) {
        List<AuditListDto> list = (List<AuditListDto>) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getAuditList", dto);
        return list;
    }

    public List getDistinctEmployees() {
        List<AuditListDto> emplist = (List<AuditListDto>) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getDistinctEmp");
        return emplist;
    }
}
