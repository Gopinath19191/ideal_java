/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dto.CustomerDataDTO;
import com.defiance.ideal.application.dto.CustomerAddDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface CustomerService {

    public Map<String, String> getSbuList();

    public List<CustomerDataDTO> fetchCustomerData(CustomerDataDTO filterData);
    
    public void customersAdd(CustomerAddDTO filterData);
	
    public String addFileDb(String fileName, String contentType, String referenceName, int refId, String moduleName);
    
    public int addCustomerDetails(CustomerAddDTO filterData);
    
    public int insertParentCustomer(CustomerAddDTO customerFormData);
    
    public List<CustomerAddDTO> getInvoiceList();
    
}

