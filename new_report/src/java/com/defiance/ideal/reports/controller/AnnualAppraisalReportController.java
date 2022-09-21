/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dao.AnnualAppraisalDao;
import com.defiance.ideal.reports.dao.AnnualAppraisalDaoImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.defiance.ideal.reports.dto.AnnualAppraisalDataDTO;
import com.defiance.ideal.reports.dto.AnnualAppraisalFilterDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 8000168
 */
public class AnnualAppraisalReportController extends MultiActionController {

	public ModelAndView annualAppraisalReport(HttpServletRequest request, HttpServletResponse response, AnnualAppraisalFilterDTO dto) {
		ModelAndView mvc = new ModelAndView("annualAppraisalReport");
		try {
			final WebApplicationContext ctx = getWebApplicationContext();
			AnnualAppraisalDao AnnualAppraisal = (AnnualAppraisalDaoImpl) ctx.getBean("AnnualAppraisalDaoImpl");
			List<AnnualAppraisalDataDTO> AppraisalYearList = AnnualAppraisal.getAppraisalYearList();
			List<AnnualAppraisalFilterDTO> subSbuList = null;
			List<AnnualAppraisalFilterDTO> SbuList= null;
			
			
			SbuList = AnnualAppraisal.getSbuList(CommonConfigurations.SBU+","+CommonConfigurations.SSU);
			if (dto.getSbu() != null) {
				subSbuList = AnnualAppraisal.getSubSbuList(dto.getSbu().equals("") ? CommonConfigurations.SBU+","+CommonConfigurations.SSU : dto.getSbu());
			}
			int page;
			int india_count = 0;
			if (request.getParameter("go") != null) {
				page = 1;
			} else {
				page = Integer.parseInt(request.getParameter("page"));
			}
			int rows = CommonConfigurations.pageCount;
			int start;
			start = ((page - 1) * rows);
			dto.setStart_page(start);
			dto.setEnd_page(rows);
			int recordCount = 0;
			List<AnnualAppraisalDataDTO> AnnualAppraisalList = AnnualAppraisal.getAnnualAppraisalReport(dto);
			india_count = AnnualAppraisal.getAnnualAppraisalListCount(dto);
			System.out.println("india_count" + india_count);
			recordCount = india_count;
			int end = (start + rows) - 1;
			int[] paging = CommonMethods.paging(recordCount, page, rows);
			mvc.addObject("paging", paging);
			mvc.addObject("AnnualAppraisalReport", AnnualAppraisalList);
			mvc.addObject("result", dto);
			mvc.addObject("AppraisalYearList", AppraisalYearList);
			mvc.addObject("subSbu", subSbuList);
			mvc.addObject("sbu", SbuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mvc;
	}

	public ModelAndView annualAppraisalExport(HttpServletRequest request, HttpServletResponse response, AnnualAppraisalFilterDTO dto) {
		ModelAndView mv = null;
		try {
			System.out.println("Inside Export method.");
			final WebApplicationContext ctx = getWebApplicationContext();
			AnnualAppraisalDao AnnualAppraisal = (AnnualAppraisalDaoImpl) ctx.getBean("AnnualAppraisalDaoImpl");
			List<AnnualAppraisalDataDTO> AnnualAppraisalList = AnnualAppraisal.getAnnualAppraisalReport(dto);
			ArrayList entireList = new ArrayList();
			for (int i = 0; i < AnnualAppraisalList.size(); i++) {
				ArrayList rowDataList = new ArrayList();
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getAppraiseeNumber()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getAppraiseeName()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getPracticeGroup()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getSubPracticeGroup()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getSubmittedStatus()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getAppriaserNumber()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getAppraiserName()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getReviewerNumber()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getReviewerName()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getNormalizerNumber()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getNormalizerName()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getAppraiserRating()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getReviewerRating()));
				rowDataList.add(new String("" + AnnualAppraisalList.get(i).getNormalisedReviewerRating()));
                                rowDataList.add(new String("" + AnnualAppraisalList.get(i).getAppraiserPromotion()));
                                rowDataList.add(new String("" + AnnualAppraisalList.get(i).getReviewerPromotion()));
                                rowDataList.add(new String("" + AnnualAppraisalList.get(i).getNormalizerPromotion()));
				entireList.add(rowDataList);
			}
			CommonMethods.exportToExcel(response, entireList, "annual_appraisal_report.xls", "Annual Appraisal Report", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	public ModelAndView ajaxGetManager(HttpServletRequest request, HttpServletResponse response, AnnualAppraisalDataDTO dto) {
		ModelAndView mvc = new ModelAndView("/ajaxsearch");
		try {
			dto.setKey("%" + request.getParameter("q") + "%");
			final WebApplicationContext ctx = getWebApplicationContext();
			AnnualAppraisalDao getManager = (AnnualAppraisalDaoImpl) ctx.getBean("AnnualAppraisalDaoImpl");
			List<AnnualAppraisalDataDTO> manager = getManager.ajaxGetManager(dto);
			mvc.addObject("empRes", manager);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mvc;
	}

	public ModelAndView ajaxGetReviewer(HttpServletRequest request, HttpServletResponse response, AnnualAppraisalDataDTO dto) {
		ModelAndView mvc = new ModelAndView("/ajaxsearch");
		try {
			dto.setKey("%" + request.getParameter("q") + "%");
			final WebApplicationContext ctx = getWebApplicationContext();
			AnnualAppraisalDao getReviewer = (AnnualAppraisalDaoImpl) ctx.getBean("AnnualAppraisalDaoImpl");
			List<AnnualAppraisalDataDTO> reviewer = getReviewer.ajaxGetReviewer(dto);
			mvc.addObject("empRes", reviewer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mvc;
	}

	public ModelAndView ajaxGetNormalizer(HttpServletRequest request, HttpServletResponse response, AnnualAppraisalDataDTO dto) {
		ModelAndView mvc = new ModelAndView("/ajaxsearch");
		try {
			dto.setKey("%" + request.getParameter("q") + "%");
			final WebApplicationContext ctx = getWebApplicationContext();
			AnnualAppraisalDao getNormalizer = (AnnualAppraisalDaoImpl) ctx.getBean("AnnualAppraisalDaoImpl");
			List<AnnualAppraisalDataDTO> normalizer = getNormalizer.ajaxGetNormalizer(dto);
			mvc.addObject("empRes", normalizer);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mvc;
	}
	
	
}
