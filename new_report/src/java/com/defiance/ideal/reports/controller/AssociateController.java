/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dao.AssociateDao;
import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import com.lowagie.text.pdf.hyphenation.TernaryTree;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14517
 */
public class AssociateController extends MultiActionController {

	public AssociateController() {
	}

	public ModelAndView associatelist(HttpServletRequest request, HttpServletResponse response, AssociateDataDTO dto) throws Exception{
		// System.out.println("associatelist****************");             
		ModelAndView mvc = new ModelAndView("associate");
                int flag = 0;
		final WebApplicationContext ctx = getWebApplicationContext();
		AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
                List<AssociateFilterDTO> details = null;
                String status = dto.getStatus();
           
		String PES = CommonConfigurations.PES;
		String TS = CommonConfigurations.TS;
                String HR = CommonConfigurations.HR;
                String Operations = CommonConfigurations.Operations;
                String Finance = CommonConfigurations.Finance;
                String Corporate = CommonConfigurations.Corporate;
                String Sales = CommonConfigurations.Sales;
		String parentId = bo.getParent_id();
                
                List<AssociateFilterDTO> Sbu = bo.getSbu("1,12");
		List<AssociateFilterDTO> subSbu = bo.getSubSbu(parentId);
                List<AssociateFilterDTO> empStatus = bo.getEmpStatus();
		int page = 0;
		if ("Go".equals(request.getParameter("go"))) {
			page = 1;
                        flag = 1;
		} else if (request.getParameter("page") != null ) {
			
			System.out.println("page count"+dto.getPage());
			page = dto.getPage();
                        flag = 1;
			
		}else{
                     page = 1;
                     flag = 0;
                }
		int rows = CommonConfigurations.pageCount;
		int start;
		start = ((page - 1) * rows);
		dto.setStart_page(start);
		dto.setEnd_page(rows);
		int recordCount = 0;
                if(dto.getJoinedStartDate() != null && !"".equals(dto.getJoinedStartDate())){
                String[] start_date = dto.getJoinedStartDate().split("-");
                dto.setJoinedStartDate(start_date[2]+"-"+start_date[1]+"-"+start_date[0]);
                }
                if(dto.getJoinedEndDate() != null && !"".equals(dto.getJoinedEndDate())){
                String[] end_date = dto.getJoinedEndDate().split("-");
                dto.setJoinedEndDate(end_date[2]+"-"+end_date[1]+"-"+end_date[0]);
                }
                if(dto.getActiveEmpDate() != null && !"".equals(dto.getActiveEmpDate() )){
                    if(dto.getActiveEmpDate().length()>0){
                        String[] active_date = dto.getActiveEmpDate().split("-");
                        dto.setActiveEmpDate(active_date[2]+"-"+active_date[1]+"-"+active_date[0]);
                    }else{
                        dto.setActiveEmpDate(null);
                    }
                
                }else{
                    dto.setActiveEmpDate(null);
                }
                System.out.println("start date "+dto.getJoinedStartDate());
                System.out.println("end date "+dto.getJoinedEndDate());
                if(flag == 1){
		  details = bo.fetchAssociate(dto);
                  recordCount = bo.fetchAssociateListCount(dto);
                }
		
		int end = (start + rows) - 1;
		int[] paging = CommonMethods.paging(recordCount, page, rows);
		mvc.addObject("paging", paging);
		/*----------------*/
              
		mvc.addObject("list", details);
                if(dto.getJoinedStartDate() != null && !"".equals(dto.getJoinedStartDate())){
                String[] start_date = dto.getJoinedStartDate().split("-");
                dto.setJoinedStartDate(start_date[2]+"-"+start_date[1]+"-"+start_date[0]);
                }
                if(dto.getJoinedEndDate() != null && !"".equals(dto.getJoinedEndDate())){
                String[] end_date = dto.getJoinedEndDate().split("-");
                dto.setJoinedEndDate(end_date[2]+"-"+end_date[1]+"-"+end_date[0]);
                }
                if(dto.getActiveEmpDate() != null && !"".equals(dto.getActiveEmpDate())){
                String[] active_date = dto.getActiveEmpDate().split("-");
                dto.setActiveEmpDate(active_date[2]+"-"+active_date[1]+"-"+active_date[0]);
                }
		mvc.addObject("result", dto);
                mvc.addObject("sbu", Sbu);
		mvc.addObject("subSbu", subSbu);
                mvc.addObject("empStatus", empStatus);
                mvc.addObject("status", status);
		return mvc;

	}

	public ModelAndView excelexportAssociate(HttpServletRequest request, HttpServletResponse response, AssociateDataDTO dto) throws Exception {
		ModelAndView mvc = new ModelAndView("associate");
		final WebApplicationContext ctx = getWebApplicationContext();
		AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
                if(dto.getJoinedStartDate() != null && !"".equals(dto.getJoinedStartDate())){
                String[] start_date = dto.getJoinedStartDate().split("-");
                dto.setJoinedStartDate(start_date[2]+"-"+start_date[1]+"-"+start_date[0]);
                }
                if(dto.getJoinedEndDate() != null && !"".equals(dto.getJoinedEndDate())){
                String[] end_date = dto.getJoinedEndDate().split("-");
                dto.setJoinedEndDate(end_date[2]+"-"+end_date[1]+"-"+end_date[0]);
                }
                 if(dto.getActiveEmpDate() != null && !"".equals(dto.getActiveEmpDate())){
                String[] active_date = dto.getActiveEmpDate().split("-");
                dto.setActiveEmpDate(active_date[2]+"-"+active_date[1]+"-"+active_date[0]);
                }
                System.out.println("start date "+dto.getJoinedStartDate());
                System.out.println("end date "+dto.getJoinedEndDate());
		List<AssociateFilterDTO> details = bo.fetchAssociate(dto);
		ArrayList entireList = new ArrayList();

		for (int i = 0; i < details.size(); i++) {
			ArrayList rowDataList = new ArrayList();
			rowDataList.add(new String("" + details.get(i).getTitle()));
			rowDataList.add(new String("" + details.get(i).getEmpNumber()));
			rowDataList.add(new String("" + details.get(i).getEmpName()));
                        rowDataList.add(new String("" + details.get(i).getGender()));
			rowDataList.add(new String("" + details.get(i).getBirthDate()));                      
                        rowDataList.add(new String("" + details.get(i).getPanNumber()));
			rowDataList.add(new String("" + details.get(i).getJoinDate()));
			rowDataList.add(new String("" + details.get(i).getExpYears()));
			rowDataList.add(new String("" + details.get(i).getExpMonths()));
                        rowDataList.add(new String("" + details.get(i).getTotalYearExp()));
                        rowDataList.add(new String("" + details.get(i).getTotalMonthExp()));
                        rowDataList.add(new String("" + details.get(i).getCity()));
			rowDataList.add(new String("" + details.get(i).getCurrentWrkLocation()));
			rowDataList.add(new String("" + details.get(i).getOrgUnit()));
                        rowDataList.add(new String("" + details.get(i).getSbu()));
			rowDataList.add(new String("" + details.get(i).getSubSbu()));
                        rowDataList.add(new String("" + details.get(i).getSubPractice()));
                        rowDataList.add(new String("" + details.get(i).getMobile_number()));
			rowDataList.add(new String("" + details.get(i).getParentSubSbu()));
			rowDataList.add(new String("" + details.get(i).getRmId()));
			rowDataList.add(new String("" + details.get(i).getRmName()));
                        rowDataList.add(new String("" + details.get(i).getParentManagerId()));
			rowDataList.add(new String(""));
			rowDataList.add(new String("" + details.get(i).getDesignation()));                       
			rowDataList.add(new String("" + details.get(i).getStatus()));
			rowDataList.add(new String("" + details.get(i).getBillable()));
			rowDataList.add(new String("" + details.get(i).getResigned()));
			rowDataList.add(new String("" + details.get(i).getRelieved()));
			rowDataList.add(new String("" + details.get(i).getEmail()));
			rowDataList.add(new String("" + details.get(i).getBandName()));
			rowDataList.add(new String("" + details.get(i).getTransferredDate()));
			rowDataList.add(new String("" + details.get(i).getTransferedCountry()));
			rowDataList.add(new String("" + details.get(i).getTransferCity()));
                        rowDataList.add(new String("" + details.get(i).getSourceOfHire()));
                        rowDataList.add(new String("" + details.get(i).getExitStatus()));
                        rowDataList.add(new String("" + details.get(i).getFinalAdhar()));
                        rowDataList.add(new String("" + details.get(i).getContractExpiryDate()));
			entireList.add(rowDataList);
		}
                DateFormat dateFormat = new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss");
                Date date = new Date();
                CommonMethods.exportToExcel(response, entireList, "associate_report", "associate", dateFormat.format(date));

		return null;
	}

	public ModelAndView getSubPracticeGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final WebApplicationContext ctx = getWebApplicationContext();
		AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
		//  System.out.println("Id value In Associate Controller---->"+request.getParameter("id"));
		response.getWriter().println("<option value='All'>All</option>");
		for (AssociateFilterDTO dTO : ((bo).getSubSbu((request.getParameter("id"))))) {
			//   System.out.println("----" + dTO);
                    response.getWriter().println("<option value='" + dTO.getId() + "'>" + dTO.getName() + "</option>");
		}
		return null;
	}
}