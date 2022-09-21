/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.AgencyDTO;
import com.defiance.ideal.application.dto.CustomerDataDTO;
import com.defiance.ideal.application.dto.CustomerAddDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface AgencyDao {

    public List<AgencyDTO> fetchAgencyList(AgencyDTO filterData);
    public AgencyDTO getAgencyDetails(AgencyDTO formData);
    public void updateAgency(AgencyDTO formData);
}
