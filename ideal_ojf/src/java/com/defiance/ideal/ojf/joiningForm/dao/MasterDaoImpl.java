/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.joiningForm.dao;

import com.defiance.ideal.ojf.dto.LoginDTO;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerFormDTO;
import com.defiance.ideal.ojf.joiningForm.dto.MasterDataDTO;
import com.defiance.ideal.ojf.joiningForm.dto.SourcehireDTO;
import com.defiance.ideal.ojf.joiningForm.dto.VenderIdNameDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 15850
 */
public class MasterDaoImpl extends SqlMapClientDaoSupport implements MasterDao  {
    
    
    
     public List<MasterDataDTO> getvendorDetaillist(){
         
       List<MasterDataDTO>  list= (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getvendorDetaillist");
        return list;
     
     }
     
     public String checkexistsvenname(String vendname){
      String    checkexistsvenname=(String)getSqlMapClientTemplate().queryForObject("LoginMap.getCheckexistsvenname",
                    vendname);
        return checkexistsvenname;
     }
     
      public void savevendorinfo(String vendorname){
          System.out.println("vendorname+++"+vendorname);
          getSqlMapClientTemplate().insert("LoginMap.getSavevendorinfo",vendorname);
      
      }
      
      public void updatepartvendorinfo(VenderIdNameDTO objectParameter){
          
         // getSqlMapClientTemplate().update("LoginMap.getUpdatepartvendorinfo", vendorname,Integer.parseInt(venid));
          getSqlMapClientTemplate().update("LoginMap.getUpdatepartvendorinfo", objectParameter);
      }
      
       public  List<MasterDataDTO> getpartvendetails(String venid){
      List<MasterDataDTO>  list=    (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getpartvendetails",venid);
        return list;
       
       }
       
        public void deletepartvendorinfo(String venid){
            
        getSqlMapClientTemplate().delete("LoginMap.getDeletepartvendorinfo", venid);
        }
        
    public List<MasterDataDTO> getStructureDetails(int parentId)
    {
        List<MasterDataDTO>  list = null;
        
              list = (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getLevelOneStructure", parentId);
        
        return list;
        
    }
          public List<MasterDataDTO> getBandDetails(String parentId){
              System.out.println("inside masterdaoimpl++++");
         //List<MasterDataDTO> 
            if(parentId.equals("0")){
                return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getBandDetails");   
                //selectedValue = selectedValue + " and parent_id IS NULL ";
                //selectedValue="NULL";
                
            }else if(!parentId.equals("0")){
                //selectedValue = selectedValue + " and parent_id=" + parentId +" ";
                //selectedValue= parentId;
                return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getBandDetails1",parentId);
            }
        return null;
            
        
    }
         
         
      public List<MasterDataDTO> getDesignationDetails(){
        List<MasterDataDTO>  list= (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getDesignationDetails");
        return list;
          
          }
      
      public List<MasterDataDTO> getPracticeGroup(String structureId){
       List<MasterDataDTO>  list= (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getPracticeGroup",structureId);
        return list;
      }
      
      public List<MasterDataDTO> getSubPracticeGroup(String practiceGroupId){
      
          List<MasterDataDTO>  list= (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getSubPracticeGroup",practiceGroupId);
        return list;
      }
      public List<MasterDataDTO> getEmployeeDetailsFromId(String reportingManager){
      
           List<MasterDataDTO>  list= (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getEmployeeDetailsFromId",reportingManager);
        return list;
      }
      public List<SourcehireDTO> getsourcehirelisst(String sorceid){
      
          List<SourcehireDTO>  list= (List<SourcehireDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getsourcehirelisst",sorceid);
        return list;
      }
      public String getportalidbyname(String portalname){
     
          String    getportalidbyname=(String)getSqlMapClientTemplate().queryForObject("LoginMap.getportalidbyname",portalname);
         return getportalidbyname;
      }
      public List<SourcehireDTO> getjobportallist(String portalid){
        
          List<SourcehireDTO>  list= (List<SourcehireDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getjobportallist",portalid);
        return list;
      }
      
      public List<MasterDataDTO> getMasterDataList(String configId){
      return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getMasterDataList",configId);
    }
      public List<MasterDataDTO> getCmpLocationList(){
      return (List<MasterDataDTO>) getSqlMapClientTemplate().queryForList("LoginMap.getCmpLocationList");
      }
      
}
