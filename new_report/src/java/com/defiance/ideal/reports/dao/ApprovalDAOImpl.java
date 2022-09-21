/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.ApprovalDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16364
 */
public class ApprovalDAOImpl extends SqlMapClientDaoSupport implements ApprovalDAO {
    
    
   public List<ApprovalDTO> getProjectList(ApprovalDTO empDet) {
        List<ApprovalDTO> approverList = null;
        try {
            System.out.println(" empDet 33 "+empDet.getEmployeeId());
            approverList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getProjectList", empDet);
        } catch (Exception e) {
        }
        return approverList;
    }
   
   public List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO) {
        List<ApprovalDTO> employeeList = null;
        List<ApprovalDTO> temp = new ArrayList<ApprovalDTO>();
        boolean contains;
        try {
            employeeList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getEmployeeList", appDTO);
            int i = 0;
            String resignedStatus[] = new String[]{"r", "t", "b", "q", "o"};
            Iterator itr = employeeList.iterator();
            while (itr.hasNext()) {
                ApprovalDTO dto = (ApprovalDTO) itr.next();
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
   public List<ApprovalDTO> filterDataList(ApprovalDTO appDTO) {
        List<ApprovalDTO> filterDataList = null;
        try {
             System.out.println("selectedValue-------" + appDTO.getValue()+"pid=== "+appDTO.getProjectId());
            if (!appDTO.getProjectId().equals("Non_Project_Activity")) {
                filterDataList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getTimesheetEntryProjects", appDTO);
                System.out.println("inside !!!equal"+filterDataList.size());
            } else {
               
                filterDataList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getNonProjectTimesheetEntry", appDTO);
            }

        } catch (Exception e) {
        }
        return filterDataList;
    }
 public List<ApprovalDTO> filterDataListExport(ApprovalDTO appDTO) {
        List<ApprovalDTO> filterDataList = null;
        try {
             System.out.println("selectedValue-------" + appDTO.getValue()+"pid=== "+appDTO.getProjectId());
            if (!appDTO.getProjectId().equals("Non_Project_Activity")) {
                filterDataList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getTimesheetEntryProjectsExport", appDTO);
                System.out.println("inside !!!equal"+filterDataList.size());
            } else {
               
                filterDataList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getNonProjectTimesheetEntryExport", appDTO);
            }

        } catch (Exception e) {
        }
        return filterDataList;
    }
public List<ApprovalDTO> getWorkTypeList(ApprovalDTO empDet) {
        List<ApprovalDTO> workTypeList = null;
        try {
            workTypeList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getWorkTypeList", empDet);
        } catch (Exception e) {
        }
        return workTypeList;
    }
   public List<ApprovalDTO> getPrjDetails(ApprovalDTO appDTO) {
        List<ApprovalDTO> employeeList = null;
        try {
            // System.out.println("Project NAme====" + appDTO.getProjectName());
            employeeList = (List<ApprovalDTO>) getSqlMapClientTemplate().queryForList("queryMap.getPrjDetails", appDTO);
        } catch (Exception e) {
        }
        return employeeList;
    }
   public String recordCount(ApprovalDTO appDTO) {
        String recordCount = null;
        try {
            recordCount = (String) getSqlMapClientTemplate().queryForObject("queryMap.getTimesheetEntryCount", appDTO);

        } catch (Exception e) {
        }
        return recordCount;
    }
}
