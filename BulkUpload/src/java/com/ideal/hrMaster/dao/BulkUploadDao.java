/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dao;

import com.ideal.hrMaster.dto.ApplicantDTO;
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

    public String getSourceofHire(String sourceOfHire) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getSoucreofhireId", sourceOfHire);
    }

    public String getEmpid(String empNumber) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getEmpid", empNumber);
    }

    public String getEmployeeName(String empNumber) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getEmployeeName", empNumber);
    }

    public String getManagerMail(String empNumber) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getManagerMail", empNumber);
    }

    public String getEmployeeMail(String empNumber) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getEmployeeMail", empNumber);
    }

    public String getOldManagerName(String oldManagerName) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getOldManagerName", oldManagerName);
    }

    public String getOldManagerMail(String oldManagerName) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getOldManagerMail", oldManagerName);
    }

    public int updateBandDesignation(EmployeHistoryDto dto) {
        return getSqlMapClientTemplate().update("BulkUploadingMap.updateEmploye", dto);
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

    public void saveAuditHistory(AuditListDto list) {
        getSqlMapClientTemplate().insert("BulkUploadingMap.insertAudithistory", list);
    }

    public List<AuditListDto> showAuditList(AuditListDto dto) {
        List<AuditListDto> list = (List<AuditListDto>) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getAuditList", dto);
        return list;
    }

    public List getDistinctEmployees() {
        List<AuditListDto> emplist = (List<AuditListDto>) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getDistinctEmp");
        return emplist;
    }

    public void uploadOjfDetailsByExcel(ApplicantDTO dto) {
        getSqlMapClientTemplate().insert("BulkUploadingMap.insertApplicantDetails", dto);
    }

    public ArrayList getSourceList() {
        return (ArrayList) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getSourceLIst");
    }

    public ArrayList getBandList() {
        return (ArrayList) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getBandList");
    }

    public String getStructureId(String strucName) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getStructureId", strucName);
    }

    public String getpracticegrpId(ApplicantDTO dto) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getpracticegrpId", dto);
    }

    public String checkRRFmaster(String rrf) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.checkRRFmaster", rrf);
    }

    public void addMasterRrf(ApplicantDTO dto) {
        getSqlMapClientTemplate().insert("BulkUploadingMap.addMasterRrf", dto);
    }

    public String checksplitRrf(ApplicantDTO dto) {
        return (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.checksplitRrf", dto);
    }

    public void addSplitmaster(ApplicantDTO dto) {
        getSqlMapClientTemplate().insert("BulkUploadingMap.addSplitmaster", dto);
    }

    public List getFinancilaYear() {
        return (List) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getFinancilaYear");
    }

    public List<AuditListDto> getSalesDetails(AuditListDto bdm_id) {
        List<AuditListDto> details = (List<AuditListDto>) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getSalesDetails", bdm_id);
        return details;
    }
    
    public List<AuditListDto> getSalesUnitDetails(AuditListDto bdm_id){
        List<AuditListDto> details = (List<AuditListDto>) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getSalesUnitDetails", bdm_id);
        return details;
    }
    
    public String getBdm_list(String bdm_id){
        String bdm_list = null;
        System.out.println("hre it comewsss "+bdm_id);
        if(bdm_id.equals("")){
            bdm_list = (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getAll_Bdm");
        }else{
            bdm_list = (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getBdm_list", bdm_id);
        }
        return bdm_list;
    }
    
    public String getUnit_list(String bdm_id){
        String unit_list = (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getUnit_list", bdm_id);
        return unit_list;
    }
    
    public List<AuditListDto> getBdm_list_Rsh(String bdm_id){
        if(bdm_id.equals("")){
            bdm_id = (String) getSqlMapClientTemplate().queryForObject("BulkUploadingMap.getAll_Bdm");
        }
        List<AuditListDto> details = (List<AuditListDto>) getSqlMapClientTemplate().queryForList("BulkUploadingMap.getBdm_list_Rsh", bdm_id);
        return details;
    }
}