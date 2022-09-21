/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AnnualAppraisalDataDTO;
import com.defiance.ideal.reports.dto.AnnualAppraisalFilterDTO;
import java.util.List;

/**
 *
 * @author 8000168
 */
public interface AnnualAppraisalDao {
	public List<AnnualAppraisalDataDTO> getAnnualAppraisalReport(AnnualAppraisalDataDTO filterData);
	public List<AnnualAppraisalDataDTO>  ajaxGetManager(AnnualAppraisalDataDTO filterData);

	public List<AnnualAppraisalDataDTO> getAnnualAppraisalReport(AnnualAppraisalFilterDTO dto);

	public List<AnnualAppraisalDataDTO> getAppraisalYearList();

	public List<AnnualAppraisalFilterDTO> getSubSbuList(String sbuId);

	public int getAnnualAppraisalListCount(AnnualAppraisalFilterDTO dto);

	public List<AnnualAppraisalDataDTO> ajaxGetReviewer(AnnualAppraisalDataDTO dto);

	public List<AnnualAppraisalDataDTO> ajaxGetNormalizer(AnnualAppraisalDataDTO dto);
	public List<AnnualAppraisalFilterDTO> getSbuList(String sbuAndSsu);
	
}
