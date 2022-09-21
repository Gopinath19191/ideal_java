/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dao;

import com.ideal.hrMaster.dto.EmployeeDetailsDto;
import com.ideal.hrMaster.dto.ProcurementDto;
import com.ideal.hrMaster.dto.ProcurementPackagesDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface ProcurementDao {
    public List<ProcurementPackagesDto> getProcurementType();
    public List<ProcurementDto> getProcurementList(String filterData);
    public List<EmployeeDetailsDto> getEmployeeDetails(String filterData);
    public List<ProcurementPackagesDto> getBillableType();
    public List<ProcurementPackagesDto> getServiceType();
    public List<ProcurementPackagesDto> getOrderType();
    public List<ProcurementPackagesDto> getDocumentType();
    public List<ProcurementPackagesDto> getdeliveryLocation();
    public List<ProcurementPackagesDto> getCurrency();
    public List<ProcurementDto> getProcurementDetails(String filterData);
    public List<ProcurementPackagesDto> getIteamDetails(String filterData);
    public List<ProcurementPackagesDto> getAttachmentDetails(String filterData);
    public List<ProcurementPackagesDto> getPoAttachmentDetails(String filterData);
    public String insertProcurementDetails(ProcurementDto filterData);
    public void insertProcurementIteams(ProcurementDto filterData);
    public String addFileDb(String fileName, String fileType, String referenceName, int referenceId, String moduleName);
    public void insertProcurementAttachmentDetails(ProcurementDto filterData);
    public String updateProcurementDetails(ProcurementDto filterData);
    public void updateProcurementIteams(ProcurementDto filterData);
    public List<ProcurementDto> getProcurementDetailsView(String pp_id);
    public List<ProcurementDto> getProcurementRMList(ProcurementDto filterData);
    public List<ProcurementDto> getProcurementRMProcessedList(ProcurementDto filterData);
    public List<ProcurementDto> getProcurementBUHList(ProcurementDto filterData);
    public List<ProcurementDto> getProcurementBUHProcessedList(ProcurementDto filterData);
    public List<ProcurementDto> getProcurementAdminList(ProcurementDto filterData);
    public void approveRMProcurementDetails(ProcurementDto filterData);
    public void approveBUHProcurementDetails(ProcurementDto filterData);
    public void approveAdminProcurement(ProcurementDto filterData);
    public ArrayList<ProcurementPackagesDto> getMailDetails();
    public List<ProcurementPackagesDto> getApproverDetails(String emp_id);
    public String getEmployeeId(String pr_id);
    public String getProcurementCode(String id);
    public List<ProcurementPackagesDto> getPrAdminDetails();
    public String getBuhId(String pp_id);
    public String getManagerId(String emp_id);
    public List<ProcurementDto> getProcurementReport(String status);
}
