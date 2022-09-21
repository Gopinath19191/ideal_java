/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14517
 */
public class AssociateDaoImpl extends SqlMapClientDaoSupport implements AssociateDao {

    public List<AssociateFilterDTO> fetchAssociate(AssociateDataDTO dto) {

        List<AssociateFilterDTO> details = null;
        try {
            details = (List<AssociateFilterDTO>) getSqlMapClientTemplate().queryForList("AssociateMap.getDetails",dto);
//            System.out.println("details.size()"+details.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }
     public List<AssociateFilterDTO> getEmpStatus() {
        List<AssociateFilterDTO> details = null;
        String update = "";
        try {
            details = (List<AssociateFilterDTO>) getSqlMapClientTemplate().queryForList("AssociateMap.getEmpStatus");
            update = (String) getSqlMapClientTemplate().queryForObject("AssociateMap.updateExperienceDetails");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }
    
    public List<AssociateFilterDTO> getSubSbu(String parentId)
    {
        List<AssociateFilterDTO> subSbu = null;
        try{
           
           System.out.println("ParentId------>"+parentId);
            subSbu = getSqlMapClientTemplate().queryForList("AssociateMap.getSubSbu", parentId); 
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return subSbu;
    }
    
    public List<AssociateFilterDTO> getSbu(String parentId)
    {
        List<AssociateFilterDTO> Sbu = null;
        try{
           
           System.out.println("ParentId------>"+parentId);
            Sbu = getSqlMapClientTemplate().queryForList("AssociateMap.getSbu", parentId); 
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return Sbu;
    }
    
    public String getParent_id(){
        String Sbu = null;
        try{
            Sbu = (String)getSqlMapClientTemplate().queryForObject("AssociateMap.getParent_id"); 
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return Sbu;
    }
	
	public int fetchAssociateListCount(AssociateDataDTO dto) {
		int AssociateCount = 0;
		try{
			AssociateCount = (Integer)getSqlMapClientTemplate().queryForObject("AssociateMap.fetchAssociateListCount", dto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return AssociateCount;
	}
}
