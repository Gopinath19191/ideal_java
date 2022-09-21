/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.ProjectDetails;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDTO;
import com.defiance.ideal.reports.dto.ProjectReportFilterDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 15261
 */
public class ProjectReportDaoImpl extends SqlMapClientDaoSupport implements ProjectReportDao{

     public List<ProjectDetails> getProjectDetailsList(ProjectDetails dto){
         List<ProjectDetails> list=null;
         try{ 
            list=getSqlMapClientTemplate().queryForList("ProjectReportMap.projectDetailsList",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
     }
     public List<ProjectDetails> getEntireProjectDetail(ProjectReportFilterDTO dto){
         List<ProjectDetails> list  = null;
         try{            
             list = getSqlMapClientTemplate().queryForList("ProjectReportMap.entireProjectDetail",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         
         return list;
     }

     public List<ProjectDetails> getCustomerName(ProjectReportFilterDTO dto){
         List<ProjectDetails> list  = null;
         try{
             list = getSqlMapClientTemplate().queryForList("ProjectReportMap.getCustomerName",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
     }

     public List<ProjectDetails> getTeamAllocationList(ProjectReportFilterDTO dto){
         List<ProjectDetails> list  = null;
         try{
             list = getSqlMapClientTemplate().queryForList("ProjectReportMap.getTeamAllocationList",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
     }


    public List<ProjectDetails> getTotalEffort(ProjectReportFilterDTO dto){
        List<ProjectDetails> list  = null;
         try{
             list = getSqlMapClientTemplate().queryForList("ProjectReportMap.getTotalEffort",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
    }
    public List<ProjectDetails> getAccruedEffort(ProjectReportFilterDTO dto){
         List<ProjectDetails> list  = null;
         try{
             list = getSqlMapClientTemplate().queryForList("ProjectReportMap.getAccruedEffort",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
    }


    public List<ProjectDetails> getBillableAmtSum(ProjectReportFilterDTO dto){
        List<ProjectDetails> list  = null;
         try{
             list = getSqlMapClientTemplate().queryForList("ProjectReportMap.getBillableAmtSum",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
    }

    public List<ProjectDetails> getPoValue(ProjectReportFilterDTO dto){
         List<ProjectDetails> list  = null;
         try{
             list = getSqlMapClientTemplate().queryForList("ProjectReportMap.getPoValue",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
    }
    
    
    public List<ProjectDetails> getPoReceivedDetails(ProjectFinanceReportDTO dto){
         List<ProjectDetails> list  = null;
         try{
             list = getSqlMapClientTemplate().queryForList("ProjectReportMap.getPoReceivedDetails",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
    }
    

}
