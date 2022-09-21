/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AddressDataDTO;
import com.defiance.ideal.reports.dto.AddressFilterDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 8000246
 */
public class AddressDaoImpl extends SqlMapClientDaoSupport implements AddressDao{
//     private String empVal;
//
//    public String getEmpVal() {
//        return empVal;
//    }
//
//    public void setEmpVal(String empVal) {
//        this.empVal = empVal;
//    }
     
    public List<AddressDataDTO> getAddressRecords(AddressFilterDTO filterData){
        System.out.println("filterData@@@@@@@@@@@@@@@@@@@"+filterData.getEmployee_id());
         List<AddressDataDTO> template=null;
         try{
         template=getSqlMapClientTemplate().queryForList("Address.getAddressRecords", filterData);
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return template;
     }
   
    public List<AddressDataDTO> getSearchList(String empVal) {
//        Map<String, String> empList = new LinkedHashMap<String, String>();
        List<AddressDataDTO> empList = null;
        try {
            String key= empVal+"%";
            System.out.println("in daoimpl::::"+key);


            empList = (List<AddressDataDTO>) getSqlMapClientTemplate().queryForList("Address.getSearchList",key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }

   
}
