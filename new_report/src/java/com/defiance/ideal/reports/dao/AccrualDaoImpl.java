/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AccrualDataDTO;
import com.defiance.ideal.reports.dto.AccrualFilterDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14053
 */
public class AccrualDaoImpl extends SqlMapClientDaoSupport implements AccrualDao {

    private String  sbuId;
    
    public Map<String, String> getSbuList() {
        Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
            sbuList = getSqlMapClientTemplate().queryForMap("AccrualMap.getSbuList",sbuId,"sbuId", "sbuName");
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }

        return sbuList;
    }

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }

    public List<AccrualDataDTO> fetchAccrualData(AccrualFilterDTO filterData) {
        List<AccrualDataDTO> template = null;
        
        try {
            template = getSqlMapClientTemplate().queryForList("AccrualMap.fetchAccrualData",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

}
