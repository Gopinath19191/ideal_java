/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDetails;

/**
 * 
 * @author 15261
 */
public class ProjectFinanceReportDaoImpl extends SqlMapClientDaoSupport
		implements ProjectFinanceReportDao {

	public List<ProjectFinanceReportDetails> getProjectDetailsList(String searchKey) {
		List<ProjectFinanceReportDetails> list = null;
		try {
          searchKey = searchKey+"%";
			list = getSqlMapClientTemplate().queryForList(
					"ProjectFinanceReportMap.projectDetailsList",searchKey);
		} catch (Exception e) {
			;
			e.printStackTrace();
		}
		return list;
	}

	public List<ProjectFinanceReportDetails> getSbuList() {
		List<ProjectFinanceReportDetails> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList("ProjectFinanceReportMap.SbuList");
//                        System.out.println("list::"+ list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ProjectFinanceReportDetails> getRbuList() {
		List<ProjectFinanceReportDetails> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList(
					"ProjectFinanceReportMap.RbuList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ProjectFinanceReportDetails> getPMList(String searchKey) {
		List<ProjectFinanceReportDetails> list = null;
		try {
                    searchKey = searchKey+"%";
			list = getSqlMapClientTemplate().queryForList(
					"ProjectFinanceReportMap.projectManagerList",searchKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
        
	public List<ProjectFinanceReportDetails> getProjectModelList() {
		List<ProjectFinanceReportDetails> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList(
					"ProjectFinanceReportMap.getProjectModelList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ProjectFinanceReportDetails> getCustomerDetails(String searchKey) {
		List<ProjectFinanceReportDetails> list = null;
		try {
                        searchKey = searchKey+"%";
			list = getSqlMapClientTemplate().queryForList(
					"ProjectFinanceReportMap.customerDetailsList",searchKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<ProjectFinanceReportDetails> getReportDetails(
			ProjectFinanceReportDTO dto) {
		List<ProjectFinanceReportDetails> list = null;
		try {
			list = getSqlMapClientTemplate().queryForList(
					"ProjectFinanceReportMap.reportDetails", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<ProjectFinanceReportDetails> getSubRbu(String parentId){
		List<ProjectFinanceReportDetails> subRbu = null;
		try {
			subRbu = getSqlMapClientTemplate().queryForList(
					"ProjectFinanceReportMap.getSubRbu", parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subRbu;
	}

    public int getReportDetailsCount(ProjectFinanceReportDTO dto) {
        int recordCount = 0;
        try{
            recordCount = (Integer)getSqlMapClientTemplate().queryForObject("ProjectFinanceReportMap.reportDetailsCount", dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return recordCount;
    }
        
        

}
