/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AnnualAppraisalDataDTO;
import com.defiance.ideal.reports.dto.AnnualAppraisalFilterDTO;
import java.util.Iterator;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 8000168
 */
public class AnnualAppraisalDaoImpl extends SqlMapClientDaoSupport implements AnnualAppraisalDao {
	
 public List<AnnualAppraisalDataDTO> getAnnualAppraisalReport(AnnualAppraisalDataDTO filterData) {
        List<AnnualAppraisalDataDTO> reportList = null;
        try {
			System.out.println("filterData.getAppraiseeName()"+filterData.getAppraiseeName());
			System.out.println("filterData.getAppraiseeNumber()"+filterData.getAppraiseeName());
			System.out.println(filterData.getAppraiseeNumber());
           reportList = getSqlMapClientTemplate().queryForList("AnnualAppraisalReport.getAnnualAppraisalReport", filterData);
		   
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportList;
    }

public List<AnnualAppraisalDataDTO> ajaxGetManager(AnnualAppraisalDataDTO filterData) {
        List<AnnualAppraisalDataDTO> reportList = null;
        try {
			System.out.println("KEY Value "+filterData.getKey());
           reportList = getSqlMapClientTemplate().queryForList("AnnualAppraisalReport.getAppraiser", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportList;
    }

	public List<AnnualAppraisalDataDTO> getAnnualAppraisalReport(AnnualAppraisalFilterDTO dto) {
		List<AnnualAppraisalDataDTO> annualAppraisalList = null;
		try{
			System.out.println("Appraiser ID "+dto.getEmployee_id());
			System.out.println("SBU "+dto.getSbu());
			if("All".equals(dto.getSubSbu())){
				dto.setSubSbu("");
			}
			System.out.println("SUBSBU "+dto.getSubSbu());
		annualAppraisalList = getSqlMapClientTemplate().queryForList("AnnualAppraisalReport.getAnnualAppraisalReport", dto);
			System.out.println("annualAppraisalList "+annualAppraisalList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return annualAppraisalList;
	}

	public List<AnnualAppraisalDataDTO> getAppraisalYearList() {
		List<AnnualAppraisalDataDTO> AppraisalYearList = null;
		try{
			AppraisalYearList = getSqlMapClientTemplate().queryForList("AnnualAppraisalReport.getAppraisalYearList");
			System.out.println("AppraisalYearList "+AppraisalYearList);
			Iterator it = AppraisalYearList.iterator();
			while(it.hasNext()){
				AnnualAppraisalDataDTO obj = (AnnualAppraisalDataDTO)it.next();
				System.out.println("AppraisalYear "+obj.getAppraisalYear());
				obj.setAppraisalYear(obj.getAppraisalYear().split("-")[0]);
				System.out.println("AppraisalYear "+obj.getAppraisalYear());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return AppraisalYearList;
	}

	public List<AnnualAppraisalFilterDTO> getSubSbuList(String sbuId) {
		List<AnnualAppraisalFilterDTO> subSbuList = null;
		try{
			System.out.println("sbuId=========>"+sbuId);
			subSbuList = getSqlMapClientTemplate().queryForList("AnnualAppraisalReport.getSubSbuList", sbuId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return subSbuList;
	}

	public int getAnnualAppraisalListCount(AnnualAppraisalFilterDTO dto) {
		int annualAppraisalCount = 0;
		try{
			annualAppraisalCount = (Integer)getSqlMapClientTemplate().queryForObject("AnnualAppraisalReport.getAnnualAppraisalListCount", dto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return annualAppraisalCount;
	}

	public List<AnnualAppraisalDataDTO> ajaxGetReviewer(AnnualAppraisalDataDTO dto) {
		List<AnnualAppraisalDataDTO> reviewerList = null;
        try {
			System.out.println("KEY Value "+dto.getKey());
           reviewerList = getSqlMapClientTemplate().queryForList("AnnualAppraisalReport.getReviewer", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reviewerList;
	}

	public List<AnnualAppraisalDataDTO> ajaxGetNormalizer(AnnualAppraisalDataDTO dto) {
		List<AnnualAppraisalDataDTO> normalizerList = null;
        try {
			System.out.println("KEY Value "+dto.getKey());
           normalizerList = getSqlMapClientTemplate().queryForList("AnnualAppraisalReport.getNormalizer", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return normalizerList;
	}
  public List<AnnualAppraisalFilterDTO> getSbuList(String sbuAndSsu){
	  List<AnnualAppraisalFilterDTO>  sbuList = null;
	  try {
		  System.out.println("sbuId=========>"+sbuAndSsu);
	   sbuList = getSqlMapClientTemplate().queryForList("AnnualAppraisalReport.getSbuList",sbuAndSsu);
	   } catch (Exception e) {
            e.printStackTrace();
        }
	  return sbuList;
  }
}
