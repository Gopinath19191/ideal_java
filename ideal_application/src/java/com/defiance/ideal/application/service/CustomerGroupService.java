/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dto.CustomerGroupDTO;
import com.defiance.ideal.application.dto.CustomerAddDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface CustomerGroupService {

    public List<CustomerGroupDTO> fetchCustomerGroupData(CustomerGroupDTO filterData);

    public List<CustomerGroupDTO> fetchSelectedCustomerGroup(CustomerGroupDTO filterData);
    
}

