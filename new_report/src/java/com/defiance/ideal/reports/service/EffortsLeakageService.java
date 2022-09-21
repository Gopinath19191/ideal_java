/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.EffortsLeakageDataDTO;
import com.defiance.ideal.reports.dto.SearchDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 16221
 */
public interface EffortsLeakageService {
    public List<EffortsLeakageDataDTO> getData(EffortsLeakageDataDTO filterData);
    public List<EffortsLeakageDataDTO>getProjectUnbilledDetails(EffortsLeakageDataDTO filterData);
    public List<EffortsLeakageDataDTO>getRevenueLeakageReport(SearchDTO filterData);
    public List<SearchDTO> getCustomerList();
    public String getParent_id();
    public List<SearchDTO> getUnit();
    public List<SearchDTO> getSubUnit(String parent_id);
}
