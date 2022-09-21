/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.AgencyDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14053
 */
public class AgencyDaoImpl extends SqlMapClientDaoSupport implements AgencyDao {
    
    public List<AgencyDTO> fetchAgencyList(AgencyDTO filterData) {
        List<AgencyDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("Agencymap.fetchAgencyList",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public void updateAgency(AgencyDTO formData) {
        getSqlMapClientTemplate().update("Agencymap.updateAgency",formData);
    }
    
    public AgencyDTO getAgencyDetails(AgencyDTO formData) {
        AgencyDTO template = null;
        try {
            template = (AgencyDTO) getSqlMapClientTemplate().queryForObject("Agencymap.getAgencyDetails",formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }


}
