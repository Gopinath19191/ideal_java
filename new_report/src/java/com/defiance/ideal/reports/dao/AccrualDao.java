/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AccrualDataDTO;
import com.defiance.ideal.reports.dto.AccrualFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public interface AccrualDao {

    public Map<String, String> getSbuList();

    public List<AccrualDataDTO> fetchAccrualData(AccrualFilterDTO filterData);
    
    

}
