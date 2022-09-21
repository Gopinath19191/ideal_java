/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.CustomerGroupDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface CustomerGroupDao {

    public List<CustomerGroupDTO> fetchCustomerGroupData(CustomerGroupDTO filterData);

    public List<CustomerGroupDTO> getDuplicateCustomerGroupName(CustomerGroupDTO filterData);

    public List<CustomerGroupDTO> fetchSelectedCustomerGroup(CustomerGroupDTO filterData);

    public void insertCustomerGroupDetails(CustomerGroupDTO filterData);

}
