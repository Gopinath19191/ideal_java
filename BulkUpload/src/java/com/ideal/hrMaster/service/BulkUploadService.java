/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.service;

import com.ideal.hrMaster.dao.BulkUploadDao;
import com.ideal.hrMaster.dto.ApplicantDTO;
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
    public String getSourceofHire(String srcofHire) {
        return dao.getSourceofHire(srcofHire);
    }

    public String getBandid(String band) {
        return dao.getBandid(band);
    }
    
    public String getEmpid(String empNumber) {
        return dao.getEmpid(empNumber);
    }
    
    public String getEmployeeName(String employeeName) {
        return dao.getEmployeeName(employeeName);
    }
    public String getManagerMail(String employeeName) {
        return dao.getManagerMail(employeeName);
    }
    public String getEmployeeMail(String employeeMail) {
        return dao.getEmployeeMail(employeeMail);
    }
    
    public String getOldManagerName(String oldManagerName) {
        return dao.getOldManagerName(oldManagerName);
    }
    
    public String getOldManagerMail(String oldManagerMail) {
        return dao.getOldManagerMail(oldManagerMail);
    }
    
    public int updateBandDesignation(EmployeHistoryDto dto) {
        return dao.updateBandDesignation(dto);
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

    public void saveAuditHistory(AuditListDto list) {
        dao.saveAuditHistory(list);
    }

    public List<AuditListDto> showAuditList(AuditListDto dto) {
        return dao.showAuditList(dto);
    }

    public List<AuditListDto> getDistinctEmployees() {
        return dao.getDistinctEmployees();
    }
    public void uploadOjfDetailsByExcel(ApplicantDTO dto) {
         dao.uploadOjfDetailsByExcel(dto);
    }
    public ArrayList getSourceList() {
        return dao.getSourceList();
    }
    
    public ArrayList getBandList() {
    return dao.getBandList();
    }
    public String getStructureId(String strucName) {
        return dao.getStructureId(strucName);
    }
    public String getpracticegrpId(ApplicantDTO dto) {
        return dao.getpracticegrpId(dto);
    }
    public String checkRRFmaster(String rrf) {
        return dao.checkRRFmaster(rrf);
    }
    public void addMasterRrf(ApplicantDTO dto) {
         dao.addMasterRrf(dto);
    }
    public String checksplitRrf(ApplicantDTO dto) {
        return dao.checksplitRrf(dto);
    }
    public void addSplitmaster(ApplicantDTO dto) {
         dao.addSplitmaster(dto);
    }
    public List getFinancilaYear() {
        return dao.getFinancilaYear();
    }
    
    public List<AuditListDto> getSalesDetails(AuditListDto bdm_id){
        List<AuditListDto> details = dao.getSalesDetails(bdm_id);
        return details;
    }
    public List<AuditListDto> getSalesUnitDetails(AuditListDto bdm_id){
        List<AuditListDto> details = dao.getSalesUnitDetails(bdm_id);
        return details;
    }
    
    public String getBdm_list(String bdm_id){
        String bdm_list = dao.getBdm_list(bdm_id);
        return bdm_list;
    }
    public String getUnit_list(String bdm_id){
        String bdm_list = dao.getUnit_list(bdm_id);
        return bdm_list;
    }
    public List<AuditListDto> getBdm_list_Rsh(String bdm_id){
        List<AuditListDto> details = dao.getBdm_list_Rsh(bdm_id);
        return details;
    }
}