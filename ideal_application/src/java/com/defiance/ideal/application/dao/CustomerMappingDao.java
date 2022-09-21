/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.CustomerMappingDTO;
import java.util.List;

/**
 *
 * @author 14053
 */
public interface CustomerMappingDao {

    public void updateCustMap(CustomerMappingDTO formData);
    public void insertCustMap(CustomerMappingDTO formData);
    public CustomerMappingDTO getCustMapDetails(CustomerMappingDTO formData);
    public List<CustomerMappingDTO> fetchCustMapList(CustomerMappingDTO formData);
    public List<CustomerMappingDTO> getCustomerList(CustomerMappingDTO formData);
    public List<CustomerMappingDTO> getBdmList();
    public List<CustomerMappingDTO> getLeaderList();
    public List<CustomerMappingDTO> getRBUList();

    public List<CustomerMappingDTO> getSubRbuList(CustomerMappingDTO formData);
}
