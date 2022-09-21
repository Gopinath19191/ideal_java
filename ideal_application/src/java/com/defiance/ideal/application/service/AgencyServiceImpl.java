/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dao.AgencyDao;
import com.defiance.ideal.application.dao.AgencyDaoImpl;
import com.defiance.ideal.application.dto.AgencyDTO;
import com.defiance.ideal.application.dto.CustomerAddDTO;
import com.defiance.ideal.application.dto.CustomerDataDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public class AgencyServiceImpl implements AgencyService {

    public AgencyDao agencyDao;
    
    public AgencyDao getAgencyDao() {
        return agencyDao;
    }

    public void setAgencyDao(AgencyDao agencyDao) {
        this.agencyDao = agencyDao;
    }

    public List<AgencyDTO> fetchAgencyList(AgencyDTO filterData) {
        List<AgencyDTO> agencyList = new ArrayList<AgencyDTO>();
        agencyList = agencyDao.fetchAgencyList(filterData);
        return agencyList;
    }
    
    public void updateAgency(AgencyDTO formData) {
        agencyDao.updateAgency(formData);
    }

    
    public AgencyDTO getAgencyDetails(AgencyDTO formData) {
         AgencyDTO agencyData = new AgencyDTO();
        agencyData = agencyDao.getAgencyDetails(formData);
        return agencyData;
    }

}
