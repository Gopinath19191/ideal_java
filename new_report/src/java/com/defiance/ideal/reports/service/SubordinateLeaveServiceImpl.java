/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.SubordinateLeaveDao;
import com.defiance.ideal.reports.dto.SubordinateLeaveDataDTO;
import com.defiance.ideal.reports.dto.SubordinateLeaveFilterDTO;
import java.util.List;

/**
 *
 * @author 16047
 */
public class SubordinateLeaveServiceImpl implements SubordinateLeaveService{
    public SubordinateLeaveDao subordinate_leaveDao;

    public SubordinateLeaveDao getSubordinate_leaveDao() {
        return subordinate_leaveDao;
    }

    public void setSubordinate_leaveDao(SubordinateLeaveDao subordinate_leaveDao) {
        this.subordinate_leaveDao = subordinate_leaveDao;
    }
    

    public List<SubordinateLeaveDataDTO> getSubordinateLeaveRecord(SubordinateLeaveFilterDTO filterData) {
        List<SubordinateLeaveDataDTO> subordinateLeaveRecord = null;
        try{
        subordinateLeaveRecord = subordinate_leaveDao.getSubordinateLeaveRecord(filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return subordinateLeaveRecord;
    }
    
    public List<SubordinateLeaveDataDTO> getSearchList(String empVal,String rmId){
        List<SubordinateLeaveDataDTO> employeeList = null;
        try{
            employeeList = subordinate_leaveDao.getSearchList(empVal,rmId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeList;
    }
    
    public int getSubordinateLeaveRecordCount(SubordinateLeaveFilterDTO filterData){
        int recordCount = 0;
        try{
            recordCount = subordinate_leaveDao.getSubordinateLeaveRecordCount(filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return recordCount;
    }
    
}
