/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.VarianceDataDTO;
import com.defiance.ideal.reports.dto.VarianceFilterDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16364
 */
public class VarianceDaoImpl extends SqlMapClientDaoSupport implements VarianceDao {
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
     
    

     public List<VarianceDataDTO> getVarianceRecords(VarianceFilterDTO filterData){
         List<VarianceDataDTO> template=null;
         try{
             int m=0;
              String mm="";
             int n=Integer.parseInt(filterData.getFilter_month());
             System.out.println("nn val"+n);
             if(n==11 || n==12){
                 m=n-1; 
                 mm=""+m;
                 System.out.println("inside if mm "+mm);
             }
             else{
                 m=n-1; 
                 mm="0"+m;
                 System.out.println("inside else mm "+mm);
             }
             
            filterData.setFilter_date_applied_pre(filterData.getFilter_year() + "-" + filterData.getFilter_month()+ "-" + "01");
               System.out.println("previo*****"+filterData.getFilter_date_applied_pre());
             filterData.setFilter_date_applied(filterData.getFilter_year() + "-" + filterData.getFilter_month()+ "-" + "31");
              System.out.println("current*****"+filterData.getFilter_date_applied());
            
         template=getSqlMapClientTemplate().queryForList("Variance.getVarianceRecords", filterData);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return template;
     }
public List<VarianceDataDTO> getEmployeeList(VarianceFilterDTO appDTO) {
        List<VarianceDataDTO> employeeList = null;
        List<VarianceDataDTO> temp = new ArrayList<VarianceDataDTO>();
        boolean contains;
        try {
            employeeList = (List<VarianceDataDTO>) getSqlMapClientTemplate().queryForList("Variance.getEmployeeList", appDTO);
            int i = 0;
            String resignedStatus[] = new String[]{"r", "t", "b", "q", "o"};
            Iterator itr = employeeList.iterator();
            while (itr.hasNext()) {
                VarianceDataDTO dto = (VarianceDataDTO) itr.next();
                contains = Arrays.asList(resignedStatus).contains(dto.getEmploymentStatus());
                if (!contains) {
                    temp.add(employeeList.get(i));
                }
                i++;
            }
            employeeList.clear();

        } catch (Exception e) {
        }
        return temp;
    }
    public List<VarianceDataDTO> getProjectList(VarianceFilterDTO empDet) {
        System.out.println("pm id"+empDet.getPmId());
        List<VarianceDataDTO> approverList = null;
        try {
            approverList = (List<VarianceDataDTO>) getSqlMapClientTemplate().queryForList("Variance.getProjectList", empDet);
        } catch (Exception e) {
        }
        return approverList;
    }

     public List<VarianceDataDTO> getSearchList(String empVal) {
//        Map<String, String> empList = new LinkedHashMap<String, String>();
        List<VarianceDataDTO> empList = null;
        try {
            String key= empVal+"%";
            System.out.println("in daoimpl::::"+key);
//            empList = getSqlMapClientTemplate().queryForMap("EarnedLeaveMap.getSearchList",empVal,"empId", "empName");

            empList = (List<VarianceDataDTO>) getSqlMapClientTemplate().queryForList("Variance.getSearchList",key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }


}

