/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.MonthlyLeaveDao;
import com.defiance.ideal.reports.dto.MonthlyLeaveDataDTO;
import com.defiance.ideal.reports.dto.MonthlyLeaveFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public class MonthlyLeaveServiceImpl implements MonthlyLeaveService{
    public MonthlyLeaveDao monthlyLeaveDao;

    public MonthlyLeaveDao getMonthlyLeaveDao() {
        return monthlyLeaveDao;
    }

    public void setMonthlyLeaveDao(MonthlyLeaveDao monthlyLeaveDao) {
        this.monthlyLeaveDao = monthlyLeaveDao;
    }
    public Map<String,String> getSbuList(){
        return monthlyLeaveDao.getSbuList();
    }
    public List<MonthlyLeaveFilterDTO> getStructureList(){
        return monthlyLeaveDao.getStructureList();
    }
    public List<MonthlyLeaveDataDTO> getLeaveRecord(MonthlyLeaveFilterDTO filterData){
        List<MonthlyLeaveDataDTO> dataDto=monthlyLeaveDao.getLeaveRecords(filterData);
        return dataDto;
    }

    public String getEmployeeName(String employee_id) {
       return monthlyLeaveDao.getEmployeeName(employee_id);
    }

}
