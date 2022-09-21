/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.service;

import com.ideal.hrMaster.dao.BulkUploadDao;
import com.ideal.hrMaster.dto.AuditListDto;
import com.ideal.hrMaster.dto.EmployeHistoryDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 16113
 */
public class BulkUploadService {

    BulkUploadDao dao = new BulkUploadDao();

    public BulkUploadDao getDao() {
        return dao;
    }

    public void setDao(BulkUploadDao dao) {
        this.dao = dao;
    }

    public ArrayList getBands() {
        return dao.getBands();
    }

    public ArrayList getDesignations() {
        return dao.getDesignations();
    }

    public ArrayList getEmployeNumbers() {
        return dao.getEmployeNumbers();
    }
    
    public EmployeHistoryDto getSerlize(String empNumber) {
        return dao.getSerlize(empNumber);

    }
    
    public String getDesignationid(String designation) {
        return dao.getDesignationid(designation);
    }

    public String getBandid(String band) {
        return dao.getBandid(band);
    }
    
    public String getEmpid(String empNumber) {
        return dao.getEmpid(empNumber);
    }
    
    public int updateBandDesignation(EmployeHistoryDto dto) {
        return dao.updateBandDesignation(dto);
    }
    
     public EmployeHistoryDto getSourceE(EmployeHistoryDto dto) {
        return dao.getSourceE(dto);
    }
    
    public int updateManager(EmployeHistoryDto dto) {
        return dao.updateManager(dto);
    }
    
    public void insertEmpHistory(EmployeHistoryDto dto) {
        dao.insertEmpHistory(dto);
    }

    public EmployeHistoryDto getEmployeId(String authEmpid) {
        return dao.getEmployeId(authEmpid);
    }

    public EmployeHistoryDto getSingleEmployee(String employee_number) {
        return dao.getSingleEmployee(employee_number);
    }
    
    public String getSingleLocation(String location) {
        return dao.getSingleLocation(location);
    }

//    public void saveAuditHistory(AuditListDto list) {
//        dao.saveAuditHistory(list);
//    }

    public List<AuditListDto> showAuditList(AuditListDto dto) {
        return dao.showAuditList(dto);
    }

    public List<AuditListDto> getDistinctEmployees() {
        return dao.getDistinctEmployees();
    }
}
