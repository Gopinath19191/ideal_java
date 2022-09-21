/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dto.CustomerMappingDTO;
import java.util.List;

/**
 *
 * @author 14053
 */
public interface CustomerMappingService {

    public List<CustomerMappingDTO> fetchCustMapList(CustomerMappingDTO formData);
    public CustomerMappingDTO getCustMapDetails(CustomerMappingDTO formData);
    public void updateCustMapetails(CustomerMappingDTO formData);
    public void insertCustMap(CustomerMappingDTO formData);
    
}


