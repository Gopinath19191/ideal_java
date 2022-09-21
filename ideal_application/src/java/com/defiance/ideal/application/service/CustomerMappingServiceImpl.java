/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dao.CustomerMappingDao;
import com.defiance.ideal.application.dto.CustomerMappingDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 14053
 */
public class CustomerMappingServiceImpl implements CustomerMappingService {

    public CustomerMappingDao customerMappingDao;

    public CustomerMappingDao getCustomerMappingDao() {
        return customerMappingDao;
    }

    public void setCustomerMappingDao(CustomerMappingDao customerMappingDao) {
        this.customerMappingDao = customerMappingDao;
    }

  
    public void insertCustMap(CustomerMappingDTO formData) {
        customerMappingDao.insertCustMap(formData);
    }
    
    public void updateCustMapetails(CustomerMappingDTO formData) {
        customerMappingDao.updateCustMap(formData);
    }

    public CustomerMappingDTO getCustMapDetails(CustomerMappingDTO formData) {
         CustomerMappingDTO custMapData = new CustomerMappingDTO();
        custMapData = customerMappingDao.getCustMapDetails(formData);
        return custMapData;
    }

    public List<CustomerMappingDTO> fetchCustMapList(CustomerMappingDTO formData) {
        List<CustomerMappingDTO> custMapList = new ArrayList<CustomerMappingDTO>();
        custMapList = customerMappingDao.fetchCustMapList(formData);
        return custMapList;
    }

    public List<CustomerMappingDTO> getCustomerList(CustomerMappingDTO formData) {
        List<CustomerMappingDTO> custMapList = new ArrayList<CustomerMappingDTO>();
        custMapList = customerMappingDao.getCustomerList(formData);
        return custMapList;
    }

    public List<CustomerMappingDTO> getBdmList() {
        List<CustomerMappingDTO> custMapList = new ArrayList<CustomerMappingDTO>();
        custMapList = customerMappingDao.getBdmList();
        return custMapList;
    }

    public List<CustomerMappingDTO> getLeaderList() {
        List<CustomerMappingDTO> custMapList = new ArrayList<CustomerMappingDTO>();
        custMapList = customerMappingDao.getLeaderList();
        return custMapList;
    }

    public List<CustomerMappingDTO> getRBUList() {
        List<CustomerMappingDTO> custMapList = new ArrayList<CustomerMappingDTO>();
        custMapList = customerMappingDao.getRBUList();
        return custMapList;
    }
    
    public List<CustomerMappingDTO> getSubRbuList(CustomerMappingDTO formData) {
        List<CustomerMappingDTO> subRBUList = new ArrayList<CustomerMappingDTO>();
        subRBUList = customerMappingDao.getSubRbuList(formData);
        return subRBUList;
    }

}
