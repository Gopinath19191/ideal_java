/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.AssociateDaoImpl;
import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import java.util.List;

/**
 *
 * @author 14517
 */
public class AssociateServiceImpl {
AssociateDaoImpl daoImpl=new AssociateDaoImpl();

    public AssociateDaoImpl getDaoImpl() {
        return daoImpl;
    }

    public void setDaoImpl(AssociateDaoImpl daoImpl) {
        this.daoImpl = daoImpl;
    }
    public List<AssociateFilterDTO> fetchAssociate(AssociateDataDTO dto){
        List<AssociateFilterDTO> details=getDaoImpl().fetchAssociate(dto);
        return details;
    }
     public List<AssociateFilterDTO> getEmpStatus(){
        List<AssociateFilterDTO> details=getDaoImpl().getEmpStatus();
        return details;
    }
     public List<AssociateFilterDTO> getSubSbu(String parentId)
     {
         List<AssociateFilterDTO> subSbu = getDaoImpl().getSubSbu(parentId);
         return subSbu;
     }
    public List<AssociateFilterDTO> getSbu(String parentId)
     {
         List<AssociateFilterDTO> Sbu = getDaoImpl().getSbu(parentId);
         return Sbu;
     }
    public String getParent_id(){
        String parent_id = getDaoImpl().getParent_id();
        return parent_id;
    }
    public int fetchAssociateListCount(AssociateDataDTO dto){
            return getDaoImpl().fetchAssociateListCount(dto);
    }
}
