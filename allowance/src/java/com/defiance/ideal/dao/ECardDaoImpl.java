    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.dao;

import com.defiance.ideal.dto.ECardDto;
import com.defiance.ideal.dto.EmpEngagementDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16221
 */
public class ECardDaoImpl extends SqlMapClientDaoSupport implements ECardDao  {
    
    @Override
    public List<ECardDto> getCardLists(){
        List<ECardDto> EcardList = null;
        try{
            EcardList = getSqlMapClientTemplate().queryForList("eCardMap.getEcardList");
        }catch(Exception e){
            e.printStackTrace();
        }
        return EcardList;
    }
    
    @Override
    public List<ECardDto> getEmployeeSearch(String val){
        List<ECardDto> EcardList = null;
        String key = "%"+val+"%";
        try{
            EcardList = getSqlMapClientTemplate().queryForList("eCardMap.getEmployeeSearch", key);
        }catch(Exception e){
            e.printStackTrace();
        }
        return EcardList;
    }
    
    @Override
    public int giveEcard(ECardDto filterData){
        String last_id = "";
        try{
            last_id = (String)getSqlMapClientTemplate().insert("eCardMap.giveEcard", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return Integer.parseInt(last_id);
    }
    
    @Override
    public ECardDto getEmployeeName(String id){
        ECardDto employee_name = null;
        try{
            employee_name = (ECardDto)getSqlMapClientTemplate().queryForObject("eCardMap.getEmployeeName", id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return employee_name;
    }
    
    @Override
    public List<ECardDto> getGivenCardLists(String val){
        List<ECardDto> EcardList = null;
        try{
            EcardList = getSqlMapClientTemplate().queryForList("eCardMap.getGivenCardLists", val);
        }catch(Exception e){
            e.printStackTrace();
        }
        return EcardList;
    }
    
    @Override
    public List<ECardDto> getReceivedCardLists(String val){
        List<ECardDto> EcardList = null;
        try{
            EcardList = getSqlMapClientTemplate().queryForList("eCardMap.getReceivedCardLists", val);
        }catch(Exception e){
            e.printStackTrace();
        }
        return EcardList;
    }
    
    @Override
    public ECardDto getEcardDetails(String card_id){
        ECardDto EcardList = null;
        try{
            EcardList = (ECardDto)getSqlMapClientTemplate().queryForObject("eCardMap.getEcardDetails", card_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return EcardList;
    }
    
    @Override
    public String getMailTo(String id){
        String mail_to = null;
        try{
            mail_to = (String)getSqlMapClientTemplate().queryForObject("eCardMap.getMailTo", id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mail_to;
    }
    
    @Override
    public String getMailCcList(String id){
        String mail_cc_list = null;
        try{
            mail_cc_list = (String)getSqlMapClientTemplate().queryForObject("eCardMap.getMailCcList", id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mail_cc_list;
    }
    
    @Override
    public String getMailCc(String id){
        String mail_cc_list = null;
        try{
            mail_cc_list = (String)getSqlMapClientTemplate().queryForObject("eCardMap.getMailCc", id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mail_cc_list;
    }
    public ArrayList<ECardDto> getMailCredentials(){
        ArrayList<ECardDto> mail_credentials = null;
        try{
            mail_credentials = (ArrayList<ECardDto>) getSqlMapClientTemplate().queryForList("eCardMap.getConfigValueData","10");
        }catch(Exception e){
            e.printStackTrace();
        }
        return mail_credentials;
    }
    public List<ECardDto> getEcardReport(ECardDto filterData){
        List<ECardDto> EcardList = null;
        try{
            EcardList = getSqlMapClientTemplate().queryForList("eCardMap.getExcelReport", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return EcardList;
    }
    public List<ECardDto> getUnitName(){
        List<ECardDto> unit_list = null;
        try{
            unit_list = getSqlMapClientTemplate().queryForList("eCardMap.getUnitName");
        }catch(Exception e){
            e.printStackTrace();
        }
        return unit_list;
    }
    public List<EmpEngagementDto> getAreaofInterest(){
        List<EmpEngagementDto> area_list = null;
        try{
            area_list = getSqlMapClientTemplate().queryForList("eCardMap.getAreaofInterest");
        }catch(Exception e){
            e.printStackTrace();
        }
        return area_list;
    }
    public List<EmpEngagementDto> getInterestLevel(){
        List<EmpEngagementDto> area_list = null;
        try{
            area_list = getSqlMapClientTemplate().queryForList("eCardMap.getInterestLevel");
        }catch(Exception e){
            e.printStackTrace();
        }
        return area_list;
    }
    @Override
    public List<EmpEngagementDto> getCommitmentLevel(){
        List<EmpEngagementDto> area_list = null;
        try{
            area_list = getSqlMapClientTemplate().queryForList("eCardMap.getCommitmentLevel");
        }catch(Exception e){
            e.printStackTrace();
        }
        return area_list;
    }
    @Override
    public int submitEmployeeEngagement(EmpEngagementDto filterData){
        String last_id = "";
        try{
            last_id = (String)getSqlMapClientTemplate().insert("eCardMap.submitEmployeeEngagement", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return Integer.parseInt(last_id);
    }
    
    @Override
    public int submitEmployeeEngagementDetails(EmpEngagementDto filterData){
        int last_id = 0;
        try{
            getSqlMapClientTemplate().insert("eCardMap.submitEmployeeEngagementDetails", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return last_id;
    }
    
    @Override
    public EmpEngagementDto getLastSubmittedData(String employee_id){
        EmpEngagementDto data = null;
        try{
            data = (EmpEngagementDto)getSqlMapClientTemplate().queryForObject("eCardMap.getLastSubmittedData", employee_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
    
    public List<EmpEngagementDto> getInterestLists(String ee_id){
        List<EmpEngagementDto> interest_list = null;
        try{
            interest_list = getSqlMapClientTemplate().queryForList("eCardMap.getInterestLists", ee_id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return interest_list;
    }
}
