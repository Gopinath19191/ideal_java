/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.CustomerMappingDTO;
import com.defiance.ideal.application.util.CommonConfigurations;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14053
 */
public class CustomerMappingDaoImpl extends SqlMapClientDaoSupport implements CustomerMappingDao {
    
    public List<CustomerMappingDTO> fetchCustMapList(CustomerMappingDTO formData) {
        List<CustomerMappingDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustMap.fetchCustMapList",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public void insertCustMap(CustomerMappingDTO formData) {
        if(formData.getLeaderId().equals("")){
            formData.setLeaderId("0");
        }
        getSqlMapClientTemplate().insert("CustMap.insertCustMap",formData);
        getSqlMapClientTemplate().update("CustMap.updateCustomerTableData",formData);
    }
    
    public void updateCustMap(CustomerMappingDTO formData) {
        getSqlMapClientTemplate().update("CustMap.updateCustMap",formData);
    }
    
    public CustomerMappingDTO getCustMapDetails(CustomerMappingDTO formData) {
        CustomerMappingDTO template = null;
        try {
            template = (CustomerMappingDTO) getSqlMapClientTemplate().queryForObject("CustMap.getCustMapDetails",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<CustomerMappingDTO> getCustomerList(CustomerMappingDTO formData) {
         List<CustomerMappingDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustMap.getCustomerList",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }
    
    public List<CustomerMappingDTO> getSubRbuList(CustomerMappingDTO formData) {
         List<CustomerMappingDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustMap.getSubRbuList",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<CustomerMappingDTO> getBdmList() {
         List<CustomerMappingDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustMap.getBdmList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<CustomerMappingDTO> getLeaderList() {
         List<CustomerMappingDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustMap.getLeaderList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<CustomerMappingDTO> getRBUList() {
         List<CustomerMappingDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustMap.getRBUList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }


}
