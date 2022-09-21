/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.EmployeeBasicReportDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16113
 */
public class EmployeeBasicReportDaoImpl extends SqlMapClientDaoSupport implements EmployeeBasicReportDAO {

    public List<EmployeeBasicReportDTO> fetchAssociate(AssociateDataDTO dto) {

        List<EmployeeBasicReportDTO> details = null;
        try {
            details = (List<EmployeeBasicReportDTO>) getSqlMapClientTemplate().queryForList("EmployeeMap.getDetails", dto);
            System.out.println("details.size()" + details.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public List<EmployeeBasicReportDTO> getEmpStatus() {
        List<EmployeeBasicReportDTO> details = null;
        String update = "";
        try {
            details = (List<EmployeeBasicReportDTO>) getSqlMapClientTemplate().queryForList("EmployeeMap.getEmpStatus");
            update = (String) getSqlMapClientTemplate().queryForObject("EmployeeMap.updateExperienceDetails");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    public List<EmployeeBasicReportDTO> getSubSbu(String parentId) {
        List<EmployeeBasicReportDTO> subSbu = null;
        try {

            System.out.println("ParentId------>" + parentId);
            subSbu = getSqlMapClientTemplate().queryForList("EmployeeMap.getSubSbu", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subSbu;
    }

    public List<EmployeeBasicReportDTO> getSbu(String parentId) {
        List<EmployeeBasicReportDTO> Sbu = null;
        try {

            System.out.println("ParentId------>" + parentId);
            Sbu = getSqlMapClientTemplate().queryForList("EmployeeMap.getSbu", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Sbu;
    }
    public String getParent_id(){
        String Sbu = null;
        try{
            Sbu = (String)getSqlMapClientTemplate().queryForObject("EmployeeMap.getParent_id"); 
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return Sbu;
    }
    public int fetchAssociateListCount(AssociateDataDTO dto) {
        int AssociateCount = 0;
        try {
            AssociateCount = (Integer) getSqlMapClientTemplate().queryForObject("EmployeeMap.fetchAssociateListCount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AssociateCount;
    }

    public List<EmployeeBasicReportDTO> fetchEmployeeCount() {
        List<EmployeeBasicReportDTO> details = null;
        try {
            details = (List<EmployeeBasicReportDTO>) getSqlMapClientTemplate().queryForList("EmployeeMap.getEmployeeCount");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }
    public List<EmployeeBasicReportDTO> fetchEmployeeTotalCount(){
        List<EmployeeBasicReportDTO> details = null;
        try {
            details = (List<EmployeeBasicReportDTO>) getSqlMapClientTemplate().queryForList("EmployeeMap.getEmployeeHeadCount");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }
    public List<EmployeeBasicReportDTO> getJoinerDetails(){
        List<EmployeeBasicReportDTO> details = null;
        try {
            details = (List<EmployeeBasicReportDTO>) getSqlMapClientTemplate().queryForList("EmployeeMap.getJoiner");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }
    public List<EmployeeBasicReportDTO> getRelievedDetails(){
        List<EmployeeBasicReportDTO> details = null;
        try {
            details = (List<EmployeeBasicReportDTO>) getSqlMapClientTemplate().queryForList("EmployeeMap.getRelieved");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }
}
