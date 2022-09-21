/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.EarnedLeaveDataDTO;
import com.defiance.ideal.reports.dto.EarnedLeaveFilterDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14517
 */
public class EarnedLeaveDaoImpl extends SqlMapClientDaoSupport implements EarnedLeaveDao {
    private String sbuId;
    private String empVal;

    public String getEmpVal() {
        return empVal;
    }

    public void setEmpVal(String empVal) {
        this.empVal = empVal;
    }

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }
     public Map<String, String> getSbuList() {
        Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
           // sbuList = getSqlMapClientTemplate().queryForMap("EarnedLeaveMap.getSbuList",sbuId,"sbuId", "sbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
    }
     
     public List<EarnedLeaveDataDTO> getLeaveRecords(EarnedLeaveFilterDTO filterData){
         List<EarnedLeaveDataDTO> template=null;
         try{
         template=getSqlMapClientTemplate().queryForList("EarnedLeaveMap.getLeaveRecords", filterData);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return template;
     }

     public List<EarnedLeaveDataDTO> getEncashRecords(EarnedLeaveFilterDTO filterData){
         List<EarnedLeaveDataDTO> template=null;
         try{
             int m=0;
              String mm="";
             int n=Integer.parseInt(filterData.getFilter_month());
             if(n==11 || n==12){
                 m=n-1; 
                 mm=""+m;
             }
             else{
                 m=n-1; 
                 mm="0"+m;
             }
              filterData.setFilter_date_applied_pre(filterData.getFilter_year() + "-" + mm+ "-" + "18");
               System.out.println("previo*****"+filterData.getFilter_date_applied_pre());
             filterData.setFilter_date_applied(filterData.getFilter_year() + "-" + filterData.getFilter_month()+ "-" + "17");
              System.out.println("current*****"+filterData.getFilter_date_applied());
            
         template=getSqlMapClientTemplate().queryForList("EarnedLeaveMap.getEncashRecords", filterData);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return template;
     }

     public List<EarnedLeaveDataDTO> getLopRecords(EarnedLeaveFilterDTO filterData){
         List<EarnedLeaveDataDTO> template=null;
         try{
            
          
               System.out.println("previo*****"+filterData.getFilter_month());
            
              System.out.println("current*****"+filterData.getFilter_year());
         template=getSqlMapClientTemplate().queryForList("EarnedLeaveMap.getLopRecords", filterData);
             System.out.println("filter data:::"+template);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return template;
     }

     public List<EarnedLeaveDataDTO> getSearchList(String empVal) {
//        Map<String, String> empList = new LinkedHashMap<String, String>();
        List<EarnedLeaveDataDTO> empList = null;
        try {
            String key= empVal+"%";
            System.out.println("in daoimpl::::"+key);
//            empList = getSqlMapClientTemplate().queryForMap("EarnedLeaveMap.getSearchList",empVal,"empId", "empName");

            empList = (List<EarnedLeaveDataDTO>) getSqlMapClientTemplate().queryForList("EarnedLeaveMap.getSearchList",key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }


}
