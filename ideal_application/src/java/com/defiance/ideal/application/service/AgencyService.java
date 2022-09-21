/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dto.AgencyDTO;
import java.util.List;

/**
 *
 * @author 14053
 */
public interface AgencyService {

    public List<AgencyDTO> fetchAgencyList(AgencyDTO formData);
    public AgencyDTO getAgencyDetails(AgencyDTO formData);
    public void updateAgency(AgencyDTO formData);
    
}

