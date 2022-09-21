/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.SubordinateLeaveDataDTO;
import com.defiance.ideal.reports.dto.SubordinateLeaveFilterDTO;
import java.util.HashMap;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16047
 */
public class SubordinateLeaveDaoImpl extends SqlMapClientDaoSupport implements SubordinateLeaveDao{

    public List<SubordinateLeaveDataDTO> getSubordinateLeaveRecord(SubordinateLeaveFilterDTO filterData) {
        List<SubordinateLeaveDataDTO> subordinateLeaveRecord = null;
        try{
            if(filterData.getFromDate() == null){
                filterData.setFromDate("");
            }
            if(filterData.getToDate() == null){
                filterData.setToDate("");
            }
            if(filterData.getEmployee_id() == null){
                filterData.setEmployee_id("");
            }
            subordinateLeaveRecord = getSqlMapClientTemplate().queryForList("SubordinateLeaveMap.getSubordinateLeaveRecords", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return subordinateLeaveRecord;
    }

    public List<SubordinateLeaveDataDTO> getSearchList(String empVal,String rmId) {
        List<SubordinateLeaveDataDTO> employeeList = null;
        HashMap map = new HashMap();
        try{
            map.put("key", "%"+empVal+"%");
            map.put("rmId", rmId);
            employeeList = getSqlMapClientTemplate().queryForList("SubordinateLeaveMap.getEmployeeList", map);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employeeList;
    }

    public int getSubordinateLeaveRecordCount(SubordinateLeaveFilterDTO filterData) {
        int recordCount = 0;
        try{
            if(filterData.getFromDate() == null){
                filterData.setFromDate("");
            }
            if(filterData.getToDate() == null){
                filterData.setToDate("");
            }
            if(filterData.getEmployee_id() == null){
                filterData.setEmployee_id("");
            }
            recordCount = (Integer)getSqlMapClientTemplate().queryForObject("SubordinateLeaveMap.getSubordinateLeaveRecordCount", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return recordCount;
    }
    
}
