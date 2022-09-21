/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.UnBillabelHoursDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16113
 */
public class UnBillabelHoursDaoImpl extends SqlMapClientDaoSupport implements UnBillabelHoursDao{
    
    public List<UnBillabelHoursDTO> projectsUnbilledReport(UnBillabelHoursDTO filterData){
        List<UnBillabelHoursDTO> report =  getSqlMapClientTemplate().queryForList("unBillableMap.projectsUnbilledReport",filterData);
        return report;
    }    
    public UnBillabelHoursDTO getProjectBillDates(String projectId){
        return (UnBillabelHoursDTO)getSqlMapClientTemplate().queryForObject("unBillableMap.getProjectBillDates",projectId);
    }
    public UnBillabelHoursDTO monthlyReport(UnBillabelHoursDTO filterData){
        return (UnBillabelHoursDTO)getSqlMapClientTemplate().queryForObject("unBillableMap.monthlyReport",filterData);
    }    
   public List<UnBillabelHoursDTO> singleMonthReport(UnBillabelHoursDTO filterData){
        return getSqlMapClientTemplate().queryForList("unBillableMap.singleMonthReport",filterData);
    }
   public List<UnBillabelHoursDTO> employeeMonthReport(UnBillabelHoursDTO filterData){
        return getSqlMapClientTemplate().queryForList("unBillableMap.employeeMonthReport",filterData);
    }
}
