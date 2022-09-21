/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.VarianceDataDTO;
import com.defiance.ideal.reports.dto.VarianceFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 16364
 */
public interface VarianceDao {
    public Map<String, String> getSbuList();
    
    public List<VarianceDataDTO> getVarianceRecords(VarianceFilterDTO filterData);
   public List<VarianceDataDTO> getProjectList(VarianceFilterDTO empDet);
public List<VarianceDataDTO> getEmployeeList(VarianceFilterDTO appDTO);
        
   
    public List<VarianceDataDTO> getSearchList(String empval);

}
