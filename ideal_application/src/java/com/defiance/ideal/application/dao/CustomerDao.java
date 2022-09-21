/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.CustomerAddDTO;
import com.defiance.ideal.application.dto.CustomerDataDTO;
import com.defiance.ideal.application.dto.CustomerGroupDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author 14053
 */
public interface CustomerDao {

    public Map<String, String> getSbuList();

    public String addFileDb(String fileName, String fileType, String referenceName, int referenceId, String moduleName);

    public List<CustomerDataDTO> fetchCustomerData(CustomerDataDTO filterData);

    public List<CustomerDataDTO> fetchActiveInactiveCustomerData(CustomerDataDTO filterData);
            
    public List<CustomerDataDTO> fetchCustomerApproveData(CustomerDataDTO filterData);
            
    public List<CustomerDataDTO> fetchCustomerActiveInactive();

    public List<CustomerAddDTO> fetchSelectedCustomer(CustomerAddDTO filterData);
            
    public List<CustomerAddDTO> getMaxCustomerCode(CustomerAddDTO filterData);
            
    public List<CustomerAddDTO> getDivisionCount(CustomerAddDTO filterData);
            
    public List<CustomerAddDTO> getCustomerCode(CustomerAddDTO filterData);
            
    public List<CustomerAddDTO> getCustomerDivisionApprovedCount(CustomerAddDTO filterData);
            
    public List<CustomerAddDTO> getCustomerCodeMaxLike(CustomerAddDTO filterData);
            
    public List<CustomerAddDTO> fetchSelectedCustomerDivisions(CustomerAddDTO filterData);

    public List<CustomerAddDTO> fetchBillingAddress(CustomerAddDTO filterData);

    public List<CustomerAddDTO> fetchCustomerWorkLocations(CustomerAddDTO filterData);

    public List<CustomerAddDTO> fetchCustomerContactDetails(CustomerAddDTO filterData);

    public List<CustomerDataDTO> getCustomerGroup();
    
    public String getCustomerGroupName(String val);
            
    public String getSalesPersonName(String val);

    public List<CustomerAddDTO> getSalesPersonRef(CustomerAddDTO filterData);

    public List<CustomerDataDTO> getSalesPersonRefId();

    public List<CustomerAddDTO> getCurrencyList();

    public List<CustomerAddDTO> getcustomerGroupList();

    public List<CustomerAddDTO> getBusinessLeader();

    public List<CustomerAddDTO> getRBUList();

    public List<CustomerAddDTO> getSBUList();

    public List<CustomerAddDTO> getCountryList();
    
    public List<CustomerAddDTO> getCountryName(String Val);
            
    public List<CustomerAddDTO> getRegionName(String Val);
    
    public List<CustomerAddDTO> getCityName(String Val);

    public List<CustomerAddDTO> getRegionList(CustomerAddDTO filterData);
            
    public List<CustomerAddDTO> getRegionListFull(CustomerAddDTO filterData);

    public List<CustomerAddDTO> getCityList(CustomerAddDTO filterData);

    public String getGstCode(CustomerAddDTO filterData);
    
    public String getSalesPersonPhoneNumber(CustomerAddDTO filterData);
            
    public List<CustomerDataDTO> customerProjectMapping(CustomerDataDTO filterData);

    public List<CustomerAddDTO> fetchInvoiceCode(CustomerAddDTO filterData);

    public String insertCustomerDetails(CustomerAddDTO filterData);
            
//    public String insertCustomerDivision(CustomerAddDTO filterData);

    public void triggerMail(String[] toMailApprovalModules, CustomerAddDTO customerData,
            String mailSubReason, String mailProcessReason, String resEmpId);

    public void insertCustomerWorkDetails(CustomerAddDTO filterData);

    public void insertCustomerBillingDetails(CustomerAddDTO filterData);

    public List<CustomerAddDTO> getCustomerStatus(CustomerAddDTO filterData);

    public void approveCustomerDetails(CustomerAddDTO filterData);

    public void rmgApproveCustomerDetails(CustomerAddDTO filterData);

    public List<CustomerAddDTO> getDuplicateCustomerName(CustomerAddDTO filterData);

    public List<CustomerAddDTO> getConfigValueData(String parentId);

    public List<CustomerAddDTO> getsubRBUList(CustomerAddDTO filterData);

    public List<CustomerAddDTO> fetchCustomerFinanceContactDetails(CustomerAddDTO filterData);
    
    public List<CustomerAddDTO> fetchCustomerDunningContactDetails(CustomerAddDTO filterData);
    
    public List<CustomerAddDTO> fetchWorkLocationShortCode(CustomerAddDTO filterData);
            
    public List<CustomerAddDTO> fetchBillingShortCode(CustomerAddDTO filterData);

    public List<CustomerDataDTO> fetchCustomerDataForExcel(CustomerDataDTO filterData);
    
    public String getCustomerActivatingCode(String customerId);

    public int addCustomerDetails(HashMap<String, List<CustomerAddDTO>> data, CustomerAddDTO customerBean);

    public void updateCustomerDetails(HashMap<String, List<CustomerAddDTO>> data, CustomerAddDTO customerBean);

    public void updateCustomerMaster(CustomerAddDTO filterData);
            
    public void updateCustomerActiveInactive(CustomerDataDTO filterData);
            
    public void updateCustomerCode(CustomerAddDTO filterData);

    public int insertParentCustomer(CustomerAddDTO customerFormData);

    public List<CustomerAddDTO> fetchSelectedParent(CustomerAddDTO filterData);

    public List<CustomerAddDTO> getDuplicateCustomerDivisionName(CustomerAddDTO filterData);

    public void updateParentId(CustomerAddDTO filterData);
    
    public void insertCustomerInfoHistory(CustomerAddDTO filterData);
            
    public List<String> getApproverMailId();
    
    public List<CustomerAddDTO> getInvoiceList();
    
    public List<String> getInvoiceValue(String key);
    
    public String getDunningMaxDate();
            
    public List<CustomerGroupDTO> getDunningList(CustomerGroupDTO fileter_date);
    
    public List<CustomerGroupDTO> getDunningDate(CustomerGroupDTO filter_date);
    
    public List<CustomerGroupDTO> getDunningCustomerList(CustomerGroupDTO filterData);
    
    public List<CustomerGroupDTO> getBdmList();
    
    public void updateDunningStatus(CustomerGroupDTO filterData);
    
    public List<CustomerGroupDTO> getDebtorsExcel(CustomerGroupDTO filterData);
}
