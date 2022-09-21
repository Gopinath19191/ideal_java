/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.CustomerGroupDTO;
import com.defiance.ideal.application.util.CommonConfigurations;
import com.ibatis.sqlmap.client.event.RowHandler;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14053
 */
public class CustomerGroupDaoImpl extends SqlMapClientDaoSupport implements CustomerGroupDao {
    private RowHandler rowHandler;

    
    public List<CustomerGroupDTO> fetchCustomerGroupData(CustomerGroupDTO filterData) {
        List<CustomerGroupDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerGroupMap.fetchCustomerGroupData",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerGroupDTO> getDuplicateCustomerGroupName(CustomerGroupDTO filterData) {
        List<CustomerGroupDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerGroupMap.getDuplicateCustomerGroupName",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerGroupDTO> fetchSelectedCustomerGroup(CustomerGroupDTO filterData) {
        List<CustomerGroupDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("CustomerGroupMap.fetchSelectedCustomerGroup",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public void insertCustomerGroupDetails(CustomerGroupDTO customerData) {
        try {
            //customerData.setSubmitDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
            System.out.println("inside daoimpl 1 ==============="+customerData.getGroupId());
            if(customerData.getGroupId()== null || customerData.getGroupId().equals("")){
                List<CustomerGroupDTO> checkDuplication = null;
                System.out.println("inside daoimpl 2 ==============="+customerData.getGroupId());
                checkDuplication = getSqlMapClientTemplate().queryForList("CustomerGroupMap.checkCustomerGroupDuplication", customerData);
                if(checkDuplication.isEmpty()) {
                    getSqlMapClientTemplate().insert("CustomerGroupMap.insertCustomerGroupDetails", customerData);
                } else {
                    String returnString = "Group Name already Exists";
                }
            } else {
                getSqlMapClientTemplate().update("CustomerGroupMap.updateCustomerGroupDetails", customerData);
            }
        } catch (Exception e) {
        }
        //return returnString;
    }
}
