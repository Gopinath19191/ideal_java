/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.AssociateDaoImpl;
import com.defiance.ideal.reports.dao.EmployeeBasicReportDaoImpl;
import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.EmployeeBasicReportDTO;
import java.util.List;

/**
 *
 * @author 16113
 */
public class EmployeeBasicReportServiceImpl implements EmployeeBasicReportService{
EmployeeBasicReportDaoImpl daoImpl=new EmployeeBasicReportDaoImpl();

    public EmployeeBasicReportDaoImpl getDaoImpl() {
        return daoImpl;
    }

    public void setDaoImpl(EmployeeBasicReportDaoImpl daoImpl) {
        this.daoImpl = daoImpl;
    }
    public List<EmployeeBasicReportDTO> fetchAssociate(AssociateDataDTO dto){
        List<EmployeeBasicReportDTO> details=getDaoImpl().fetchAssociate(dto);
        return details;
    }
    public List<EmployeeBasicReportDTO> fetchEmployeeCount(){
        List<EmployeeBasicReportDTO> details=getDaoImpl().fetchEmployeeCount();
        return details;
    }
    public List<EmployeeBasicReportDTO> fetchEmployeeTotalCount(){
        List<EmployeeBasicReportDTO> details=getDaoImpl().fetchEmployeeTotalCount();
        return details;
    }
    public List<EmployeeBasicReportDTO> getJoinerDetails(){
        List<EmployeeBasicReportDTO> details=getDaoImpl().getJoinerDetails();
        return details;
    }
    public List<EmployeeBasicReportDTO> getRelievedDetails(){
        List<EmployeeBasicReportDTO> details=getDaoImpl().getRelievedDetails();
        return details;
    }
     public List<EmployeeBasicReportDTO> getEmpStatus(){
        List<EmployeeBasicReportDTO> details=getDaoImpl().getEmpStatus();
        return details;
    }
     public List<EmployeeBasicReportDTO> getSubSbu(String parentId)
     {
         List<EmployeeBasicReportDTO> subSbu = getDaoImpl().getSubSbu(parentId);
         return subSbu;
     }
    public List<EmployeeBasicReportDTO> getSbu(String parentId)
     {
         List<EmployeeBasicReportDTO> Sbu = getDaoImpl().getSbu(parentId);
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
