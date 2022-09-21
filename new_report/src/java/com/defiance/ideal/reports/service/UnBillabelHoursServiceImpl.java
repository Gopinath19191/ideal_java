/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.UnBillabelHoursDaoImpl;
import com.defiance.ideal.reports.dto.UnBillabelHoursDTO;
import java.util.List;

/**
 *
 * @author 16113
 */
public class UnBillabelHoursServiceImpl implements UnBillabelHoursService{
    public UnBillabelHoursDaoImpl dao;

    public UnBillabelHoursDaoImpl getDao() {
        return dao;
    }

    public void setDao(UnBillabelHoursDaoImpl dao) {
        this.dao = dao;
    }
    public List<UnBillabelHoursDTO> projectsUnbilledReport(UnBillabelHoursDTO filterdata){
        return dao.projectsUnbilledReport(filterdata);
    }
    public UnBillabelHoursDTO getProjectBillDates(String projectId){
        return dao.getProjectBillDates(projectId);
    }
    public UnBillabelHoursDTO monthlyReport(UnBillabelHoursDTO filterData){
         return dao.monthlyReport(filterData);
    }
    public List<UnBillabelHoursDTO> singleMonthReport(UnBillabelHoursDTO filterData){
        return dao.singleMonthReport(filterData);
    }
   public List<UnBillabelHoursDTO> employeeMonthReport(UnBillabelHoursDTO filterData){
       return dao.employeeMonthReport(filterData);
   }
    
}
