/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.LeaveReportDao;
import com.defiance.ideal.reports.dao.LeaveReportDaoImpl;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.LeaveReportDTO;
import java.util.List;



/**
 *
 * @author 16113
 */
public class LeaveReportServiceImpl implements LeaveReportService {

    public LeaveReportDaoImpl dao;

    public LeaveReportDaoImpl getDao() {
        return dao;
    }

    public void setDao(LeaveReportDaoImpl dao) {
        this.dao = dao;
    }   
    public List<LeaveReportDTO> monthlyLeaveDetails(LeaveReportDTO filterData){
        List<LeaveReportDTO> dataDto = dao.monthlyLeaveDetails(filterData);
        return dataDto;        
    }
    
    public List<LeaveReportDTO> getSubSbu(String parentId)
     {
         List<LeaveReportDTO> subSbu = dao.getSubSbu(parentId);
         return subSbu;
     }
    public List<LeaveReportDTO> getSbu()
     {
         List<LeaveReportDTO> Sbu = dao.getSbu();
         return Sbu;
     }
     public int getReportCount(LeaveReportDTO filterDto) {
      
         return dao.getReportCount(filterDto);
    }
    
   
}
