/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.ExitReportDTO;
import com.defiance.ideal.reports.dto.LeaveReportDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16113
 */
public class LeaveReportDaoImpl extends SqlMapClientDaoSupport implements LeaveReportDao{

    public List<LeaveReportDTO> monthlyLeaveDetails(LeaveReportDTO filterDto) {
        List<LeaveReportDTO> getReport = null;
        try {
            getReport = getSqlMapClientTemplate().queryForList("LeaveMap.monthlyLeaveDetails",filterDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getReport;
    }
      public List<LeaveReportDTO> getSubSbu(String parentId)
    {
        List<LeaveReportDTO> subSbu = null;
        try{           
           System.out.println("ParentId------>"+parentId);
            subSbu = getSqlMapClientTemplate().queryForList("LeaveMap.getSubSbu", parentId); 
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return subSbu;
    }
    
    public List<LeaveReportDTO> getSbu()
    {
        List<LeaveReportDTO> Sbu = null;
        try{
            Sbu = getSqlMapClientTemplate().queryForList("LeaveMap.getSbu"); 
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return Sbu;
    }
     public int getReportCount(LeaveReportDTO filterDto) {
        int getReport = 0;
        try {
            getReport = (Integer) getSqlMapClientTemplate().queryForObject("LeaveMap.getReportCount",filterDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getReport;
    }
   
}
