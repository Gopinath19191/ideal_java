/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dao.CustomerGroupDao;
import com.defiance.ideal.application.dao.CustomerGroupDaoImpl;
import com.defiance.ideal.application.dto.CustomerGroupDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public class CustomerGroupServiceImpl implements CustomerGroupService {

    public CustomerGroupDao customerGroupDao;

    public CustomerGroupDao getCustomerGroupDao() {
        return customerGroupDao;
    }

    public void setCustomerGroupDao(CustomerGroupDao customerGroupDao) {
        this.customerGroupDao = customerGroupDao;
    }

    public List<CustomerGroupDTO> fetchCustomerGroupData(CustomerGroupDTO filterData) {
        List<CustomerGroupDTO> customerData = new ArrayList<CustomerGroupDTO>();
        customerData = customerGroupDao.fetchCustomerGroupData(filterData);
        return customerData;
    }

    public List<CustomerGroupDTO> getDuplicateCustomerGroupName(CustomerGroupDTO filterData) {
        List<CustomerGroupDTO> customerData = new ArrayList<CustomerGroupDTO>();
        customerData = customerGroupDao.getDuplicateCustomerGroupName(filterData);
        return customerData;
    }
   
    public List<CustomerGroupDTO> fetchSelectedCustomerGroup(CustomerGroupDTO filterData) {
        List<CustomerGroupDTO> selectedCustomerData = new ArrayList<CustomerGroupDTO>();
        selectedCustomerData = customerGroupDao.fetchSelectedCustomerGroup(filterData);
        return selectedCustomerData;
    }
    public void insertCustomerGroupDetails(CustomerGroupDTO filterData) {
        try {
            System.out.println("In service impl:::::::::::"+filterData.getGroupName());
            customerGroupDao.insertCustomerGroupDetails(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
