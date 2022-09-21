/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dao;

import com.ideal.hrMaster.dto.EmployeeDetailsDto;
import com.ideal.hrMaster.dto.ProcurementDto;
import com.ideal.hrMaster.dto.ProcurementPackagesDto;
import com.lowagie.text.Phrase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16221
 */
public class ProcurementDaoImpl extends SqlMapClientDaoSupport implements ProcurementDao{
    
    public List<ProcurementPackagesDto> getProcurementType(){
        List<ProcurementPackagesDto> procurement_type = null;
        try{
            procurement_type = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementType");
        }catch(Exception e){
        }
        return procurement_type;
    }
    
    public List<ProcurementDto> getProcurementList(String filterData){
        List<ProcurementDto> procurement_list = null;
        try{
            procurement_list = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementList",filterData);
        }catch(Exception e){
        }
        return procurement_list;
    }
    
    public List<EmployeeDetailsDto> getEmployeeDetails(String filterData){
        List<EmployeeDetailsDto> employee_details = null;
        try{
            employee_details = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getEmployeeDetails",filterData);
        }catch(Exception e){
        }
        return employee_details;
    }
    
    public List<ProcurementPackagesDto> getBillableType(){
        List<ProcurementPackagesDto> billable_type = null;
        try{
            billable_type = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getBillableType");
        }catch(Exception e){
        }
        return billable_type;
    }
    
    public List<ProcurementPackagesDto> getServiceType(){
        List<ProcurementPackagesDto> service_type = null;
        try{
            service_type = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getServiceType");
        }catch(Exception e){
        }
        return service_type;
    }
    
    public List<ProcurementPackagesDto> getOrderType(){
        List<ProcurementPackagesDto> order_type = null;
        try{
            order_type = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getOrderType");
        }catch(Exception e){
        }
        return order_type;
    }
    
    public List<ProcurementPackagesDto> getDocumentType(){
        List<ProcurementPackagesDto> document_type = null;
        try{
            document_type = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getDocumentType");
        }catch(Exception e){
        }
        return document_type;
    }
    
    public List<ProcurementPackagesDto> getdeliveryLocation(){
        List<ProcurementPackagesDto> delivery_location = null;
        try{
            delivery_location = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getdeliveryLocation");
        }catch(Exception e){
        }
        return delivery_location;
    }
    
    public List<ProcurementPackagesDto> getCurrency(){
        List<ProcurementPackagesDto> currency = null;
        try{
            currency = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getCurrencytType");
        }catch(Exception e){
        }
        return currency;
    }
    
    public List<ProcurementDto> getProcurementDetails(String filterData){
        List<ProcurementDto> procurement_details = null;
        try{
            procurement_details = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementDetails",filterData);
        }catch(Exception e){
        }
        return procurement_details;
    }
    public List<ProcurementPackagesDto> getIteamDetails(String filterData){
        List<ProcurementPackagesDto> iteam_details = null;
        try{
            iteam_details = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getIteamDetails",filterData);
        }catch(Exception e){
        }
        return iteam_details;
    }
    public List<ProcurementPackagesDto> getAttachmentDetails(String filterData){
        List<ProcurementPackagesDto> attachment_details = null;
        try{
            attachment_details = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getAttachmentDetails",filterData);
        }catch(Exception e){
        }
        return attachment_details;
    }
    public List<ProcurementPackagesDto> getPoAttachmentDetails(String filterData){
        List<ProcurementPackagesDto> attachment_details = null;
        try{
            attachment_details = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getPoDetails",filterData);
        }catch(Exception e){
        }
        return attachment_details;
    }
    public String insertProcurementDetails(ProcurementDto filterData){
        String lastInsertId = null;
        String maxPpId = null;
        try{
            if(filterData.getStatus().equals("m")){
                maxPpId = (String) getSqlMapClientTemplate().queryForObject("PurchaseProcurement.getMaxProcurementCode");
                if(maxPpId!=null){
                    String tempId = maxPpId.substring(3);
                    Integer tempIdInteger = Integer.parseInt(tempId)+1;
                    String tempIdString = Integer.toString(tempIdInteger);
                    while (tempIdString.length() < 6) tempIdString = "0" + tempIdString;
                    tempId = "PR"+tempIdString;
                    filterData.setPp_code(tempId);
                    filterData.setLastInsertId(lastInsertId);
                }else{
                    filterData.setPp_code("PR000001");
                }
                
                
            }
            lastInsertId = (String) getSqlMapClientTemplate().insert("PurchaseProcurement.insertProcurementDetails",filterData);
        }catch(Exception e){
        }
        return lastInsertId;
    }
    
    public void insertProcurementIteams(ProcurementDto filterData){
        try{
            getSqlMapClientTemplate().insert("PurchaseProcurement.insertProcurementIteams",filterData);
        }catch(Exception e){
        }
    }
    
    public String addFileDb(String fileName, String fileType, String referenceName, int referenceId, String moduleName) {
        String lastFileInsertId = null;
        HashMap map = new HashMap();
        map.put("fileName", fileName);
        map.put("fileType", fileType);
        map.put("referenceName", referenceName);
        map.put("referenceId", referenceId);
        map.put("moduleName", moduleName);
        lastFileInsertId = (String)getSqlMapClientTemplate().insert("PurchaseProcurement.addFileDb", map);
        
        return lastFileInsertId;
    }
    
    public void insertProcurementAttachmentDetails(ProcurementDto filterData){
        try{
            getSqlMapClientTemplate().insert("PurchaseProcurement.insertProcurementAttachmentDetails",filterData);
        }catch(Exception e){
        }
    }
    
    public String updateProcurementDetails(ProcurementDto filterData){
        String lastInsertId = null;
        String maxPpId = null;
        try{
            if(filterData.getPp_code().length()==0){
                maxPpId = (String) getSqlMapClientTemplate().queryForObject("PurchaseProcurement.getMaxProcurementCode");
                if(maxPpId!=null){
                    String tempId = maxPpId.substring(3);
                    Integer tempIdInteger = Integer.parseInt(tempId)+1;
                    String tempIdString = Integer.toString(tempIdInteger);
                    while (tempIdString.length() < 6) tempIdString = "0" + tempIdString;
                    tempId = "PR"+tempIdString;
                    filterData.setPp_code(tempId);
                }else{
                    filterData.setPp_code("PR000001");
                }
            }
            getSqlMapClientTemplate().insert("PurchaseProcurement.updateProcurementDetails",filterData);
            lastInsertId = filterData.getId();
        }catch(Exception e){
        }
        return lastInsertId;
    }
    
    public void updateProcurementIteams(ProcurementDto filterData){
        try{
            getSqlMapClientTemplate().insert("PurchaseProcurement.updateProcurementIteams",filterData);
        }catch(Exception e){
        }
    }
    
    public List<ProcurementDto> getProcurementDetailsView(String pp_id){
        List<ProcurementDto> procurement_details = null;
        try{
            procurement_details = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementDetailsView",pp_id);
        }catch(Exception e){
        }
        return procurement_details;
    }
    
    public List<ProcurementDto> getProcurementRMList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = null;
        try{
            procurement_list = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementRMList",filterData);
        }catch(Exception e){
        }
        return procurement_list;
    }
    
    public List<ProcurementDto> getProcurementRMProcessedList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = null;
        try{
            procurement_list = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementRMProcessedList",filterData);
        }catch(Exception e){
        }
        return procurement_list;
    }
    
    public List<ProcurementDto> getProcurementBUHList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = null;
        try{
            procurement_list = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementBUHList",filterData);
        }catch(Exception e){
        }
        return procurement_list;
    }
    
    public List<ProcurementDto> getProcurementBUHProcessedList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = null;
        try{
            procurement_list = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementBUHProcessedList",filterData);
        }catch(Exception e){
        }
        return procurement_list;
    }
    
    public List<ProcurementDto> getProcurementAdminList(ProcurementDto filterData){
        List<ProcurementDto> procurement_list = null;
        try{
            procurement_list = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementAdminList",filterData);
        }catch(Exception e){
        }
        return procurement_list;
    }
    public void approveRMProcurementDetails(ProcurementDto filterData){
        try{
            getSqlMapClientTemplate().insert("PurchaseProcurement.approveRMProcurementDetails",filterData);
        }catch(Exception e){
        }
    }
    public void approveBUHProcurementDetails(ProcurementDto filterData){
        try{
            getSqlMapClientTemplate().insert("PurchaseProcurement.approveBUHProcurementDetails",filterData);
        }catch(Exception e){
        }
    }
    public void approveAdminProcurement(ProcurementDto filterData){
        try{
            getSqlMapClientTemplate().insert("PurchaseProcurement.approveAdminProcurementDetails",filterData);
        }catch(Exception e){
        }
    }
    public ArrayList<ProcurementPackagesDto> getMailDetails(){
        ArrayList mailDetails = null;
        try{
            mailDetails = (ArrayList<ProcurementPackagesDto>) getSqlMapClientTemplate().queryForList("PurchaseProcurement.getConfigValueData","10");
        }catch(Exception e){
        }
        return mailDetails;
    }
    
    public List<ProcurementPackagesDto> getApproverDetails(String emp_id){
        List<ProcurementPackagesDto> approver_details = null;
        try{
            approver_details = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getApproverDetails",emp_id);
        }catch(Exception e){
        }
        return approver_details;
    }
    
    public String getEmployeeId(String pr_id){
        String em_id = null;
        try{
            em_id = (String) getSqlMapClientTemplate().queryForObject("PurchaseProcurement.getEmployeeId",pr_id);
        }catch(Exception e){
        }
        return em_id;
    }
    public String getProcurementCode(String id){
        String pr_code = null;
        try{
            pr_code = (String) getSqlMapClientTemplate().queryForObject("PurchaseProcurement.getProcurementCode",id);
        }catch(Exception e){
        }
        return pr_code;
    }
    public List<ProcurementPackagesDto> getPrAdminDetails(){
        List<ProcurementPackagesDto> admin_details = null;
        try{
            admin_details = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getPrAdminDetails");
        }catch(Exception e){
        }
        return admin_details;
    }
    public String getBuhId(String pp_id){
        String buh_id = null;
        try{
            buh_id = (String) getSqlMapClientTemplate().queryForObject("PurchaseProcurement.getBuhId",pp_id);
        }catch(Exception e){
        }
        return buh_id;
    }
    public String getManagerId(String emp_id){
        String mng_id = null;
        try{
            mng_id = (String) getSqlMapClientTemplate().queryForObject("PurchaseProcurement.getMngId",emp_id);
        }catch(Exception e){
        }
        return mng_id;
    }
    public List<ProcurementDto> getProcurementReport(String status){
        List<ProcurementDto> report_list = null;
        try{
            report_list = getSqlMapClientTemplate().queryForList("PurchaseProcurement.getProcurementReport",status);
        }catch(Exception e){
        }
        return report_list;
    }
}
