/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.VarianceDao;
import com.defiance.ideal.reports.dto.VarianceDataDTO;
import com.defiance.ideal.reports.dto.VarianceFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 16364
 */
public class VarianceServiceImpl implements VarianceService{
    public VarianceDao varianceDao;

    public VarianceDao getVarianceDao() {
        return varianceDao;
    }

    public void setVarianceDao(VarianceDao varianceDao) {
        this.varianceDao = varianceDao;
    }

    
    public Map<String,String> getSbuList(){
        return varianceDao.getSbuList();
    }
    public List<VarianceDataDTO> getVarianceRecord(VarianceFilterDTO filterData){
        List<VarianceDataDTO> dataDto=varianceDao.getVarianceRecords(filterData);
        return dataDto;
    }
public List<VarianceDataDTO> getProjectList(VarianceFilterDTO empDet) {
        List<VarianceDataDTO> projectList = null;
        try {
            projectList = varianceDao.getProjectList(empDet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
   
    public List<VarianceDataDTO> getSearchList(String empval){
        return varianceDao.getSearchList(empval);
    }

   public List<VarianceDataDTO> getEmployeeList(VarianceFilterDTO appDTO) {
        List<VarianceDataDTO> projectList = null;
        try {
            projectList = varianceDao.getEmployeeList(appDTO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

   

}
