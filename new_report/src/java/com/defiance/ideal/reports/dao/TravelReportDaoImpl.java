/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.TravelReportDetails;
import com.defiance.ideal.reports.dto.TravelReportFilterDTO;
import com.defiance.ideal.reports.util.CommonConfigurations;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 15261
 */
public class TravelReportDaoImpl extends SqlMapClientDaoSupport implements TravelReportDao{

     public List<TravelReportDetails> getProjectDetailsList(){
         List<TravelReportDetails> list=null;
         try{
            list=getSqlMapClientTemplate().queryForList("TravelReportMap.travelProjectDetailsList");
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
     }
   
     public List<TravelReportDetails> getTravelReportList(TravelReportFilterDTO dto){
         List<TravelReportDetails> list=null;
         try{
            list=getSqlMapClientTemplate().queryForList("TravelReportMap.travelReportList",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
     }
     public List<TravelReportDetails> getSbuList(TravelReportFilterDTO dto){
         List<TravelReportDetails> list=null;
         try{
            // String sbuId = CommonConfigurations.SBU;
            // System.out.println("sbu::::"+dto);
            // dto.setSbuId(CommonConfigurations.SBU);
            list=getSqlMapClientTemplate().queryForList("TravelReportMap.SbuList",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
     }
     public List<TravelReportDetails> getSBUDescription(TravelReportFilterDTO dto){
         List<TravelReportDetails> list=null;
         try{
            list=getSqlMapClientTemplate().queryForList("TravelReportMap.sbuDesc",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;
     }

     public List<TravelReportDetails> getSubSBUDescription(TravelReportFilterDTO dto){
         List<TravelReportDetails> list=null;
         try{
            list=getSqlMapClientTemplate().queryForList("TravelReportMap.subSbuDesc",dto);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return list;

     }

}
