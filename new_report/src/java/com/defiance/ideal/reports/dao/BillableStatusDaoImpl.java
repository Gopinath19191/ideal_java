/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.BillableStatusDataDTO;
import com.defiance.ideal.reports.dto.BillableStatusFilterDTO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14053
 */
public class BillableStatusDaoImpl extends SqlMapClientDaoSupport implements BillableStatusDao {


    private String sbuId;
    
    public Map<String, String> getSbuList() {
        Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
            sbuList = getSqlMapClientTemplate().queryForMap("BillableMap.getSbuList",sbuId,"sbuId", "sbuName");
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

    public List<BillableStatusDataDTO> fetchBillableAssociateData(BillableStatusFilterDTO filterData) {

        List<BillableStatusDataDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("BillableMap.billableData",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
        
    }
/*
    public List<AccrualDataDTO> fetchAccrualData(AccrualFilterDTO filterData) {
        List<AccrualDataDTO> template = null;
        
        try {
            template = getSqlMapClientTemplate().queryForList("AccrualMap.fetchAccrualData",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }
*/
}
