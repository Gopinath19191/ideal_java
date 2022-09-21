/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dao.HrPersonalDao;
import com.defiance.ideal.reports.dao.HrPersonalDaoImpl;
import com.defiance.ideal.reports.dto.HrPersonalDto;
import com.defiance.ideal.reports.service.HrPersonalServiceImpl;
import com.defiance.ideal.reports.service.HrPersonalService;
import java.io.IOException;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.defiance.ideal.reports.util.CommonConfigurations;
/**
 *
 * @author 8000458
 */
public class HrPersonalController extends MultiActionController {
  
    public ModelAndView getPersonalDetails(HttpServletRequest request, HttpServletResponse response, HrPersonalDto dto) throws Exception {
        ModelAndView mv = new ModelAndView("HrPersonal");
        WebApplicationContext context = getWebApplicationContext();
        HrPersonalService service = (HrPersonalService) context.getBean("HrPersonalService");
        int page = 0;
        String pg = request.getParameter("page");
        if (pg == null || pg.equals("")) {
            dto.setParent_id("0");
            dto.setParentId("1,12,52");
            page = 1;
        }
        else
        { 
            String delivery = CommonConfigurations.delivery;
            String EnablingFunctions = CommonConfigurations.enabling;
            String sales =CommonConfigurations.sales;
            String parentId = "";
            if(dto.getSbu()==null || "".equals(dto.getSbu()) ||"All".equals(dto.getSbu())){   
                parentId = delivery + ',' + EnablingFunctions + ',' +sales;
            }
            else if(dto.getSbu().equals("Delivery")){
                parentId ="1" ;
            }
            else if(dto.getSbu().equals("Enabling Functions")){
                parentId ="12" ;
            }
            else if(dto.getSbu().equals("Sales")){
                parentId ="52" ;
            }
            dto.setParentId(parentId);
            page = Integer.parseInt(pg);
        }
        int rows = 15;
        int start;
        start = ((page - 1) * rows);

        dto.setStart_page(Integer.toString(start));
        dto.setEnd_page(Integer.toString(rows));

        int recordCount = service.getPersonalCount(dto);//list.size();
        int[] pageNo = paging(rows, Integer.toString(recordCount), page);
        System.out.println("page no is :" + page);
        List<HrPersonalDto> personal = service.getPersonalDetails(dto);
        mv.addObject("details", personal);
        List<HrPersonalDto> sbu = service.getSbu(dto);
        mv.addObject("sbu", sbu);
        mv.addObject("selectedsbu", dto.getSbu());
        mv.addObject("selectedsubsbu", dto.getSubsbu());
        mv.addObject("paging", pageNo);
        List<HrPersonalDto> subsbu = service.getSubSbu(dto);
        mv.addObject("subsbu", subsbu);
        return mv;
    }
       public ModelAndView hrFilter(HttpServletRequest request, HttpServletResponse response, final HrPersonalDto dto) {
        ModelAndView mvc = new ModelAndView("HrPersonal");
        final WebApplicationContext ctx = getWebApplicationContext();
        HrPersonalServiceImpl bo = (HrPersonalServiceImpl) ctx.getBean("HrPersonalService");
        String delivery = CommonConfigurations.delivery;
        String EnablingFunctions = CommonConfigurations.enabling;
        String sales =CommonConfigurations.sales;
        String parentId = "";
            if(dto.getSbu()==null || "".equals(dto.getSbu()) ||"All".equals(dto.getSbu())){   
                parentId = delivery + ',' + EnablingFunctions + ',' +sales;
            }
            else if(dto.getSbu().equals("Delivery")){
                parentId ="1" ;
            }
            else if(dto.getSbu().equals("Enabling Functions")){
                parentId ="12" ;
            }
            else if(dto.getSbu().equals("Sales")){
                parentId ="52" ;
            }
        System.out.println("parentid"+parentId);
        dto.setParentId(parentId);
        List<HrPersonalDto> sbu = bo.getSbu(dto);
        List<HrPersonalDto> subsbu = bo.getSubSbu(dto);
        System.out.println("sbu "+parentId);
        mvc.addObject("result", dto);
        mvc.addObject("subsbu", subsbu);
        mvc.addObject("sbu", sbu);
        return mvc;
    }
   
     public ModelAndView getPersonalGroup(HttpServletRequest request, HttpServletResponse response,HrPersonalDto dto) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        HrPersonalServiceImpl dao = (HrPersonalServiceImpl) ctx.getBean("HrPersonalService");
        String name=request.getParameter("sbu");
        String pid = request.getParameter("parentId");
        System.out.println("parent id "+pid + "  unit name "+name);

         dto.setParentId(pid);
         response.setHeader("Content-type", "text/html");
         for (HrPersonalDto dTO : dao.getSubSbu(dto)) {
         System.out.println("----" + dTO.getName());
         response.getWriter().println("<option value='" + dTO.getId() + "'>" + dTO.getName() + "</option>");
     }
           return null;
   }
    public ModelAndView getPersonalDataXL(HttpServletRequest request, HttpServletResponse response, final HrPersonalDto dto) throws IOException, Exception {

        final WebApplicationContext ctx = getWebApplicationContext();
        HrPersonalServiceImpl dao = (HrPersonalServiceImpl) ctx.getBean("HrPersonalService");
        List<HrPersonalDto> personalData = null;
        personalData = dao.getPersonalDataXL(dto);
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < personalData.size(); i++) {
        ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + personalData.get(i).getTitle()));
            rowDataList.add(new String("" + personalData.get(i).getEmployee_id()));
            rowDataList.add(new String("" + personalData.get(i).getEmployee_name()));
            rowDataList.add(new String("" + personalData.get(i).getDob()));
            rowDataList.add(new String("" + personalData.get(i).getGender()));
            rowDataList.add(new String("" + personalData.get(i).getMarital_status()));
            rowDataList.add(new String("" + personalData.get(i).getBlood_Group()));
            rowDataList.add(new String("" + personalData.get(i).getPan_card()));
            rowDataList.add(new String("" + personalData.get(i).getAdhar_card()));
            rowDataList.add(new String("" + personalData.get(i).getJoined_date()));
            rowDataList.add(new String("" + personalData.get(i).getResigned_date()));
            rowDataList.add(new String("" + personalData.get(i).getRelieving_date()));
            rowDataList.add(new String("" + personalData.get(i).getStatus()));
            rowDataList.add(new String("" + personalData.get(i).getLocation()));
            rowDataList.add(new String("" + personalData.get(i).getEmail_id()));
            rowDataList.add(new String("" + personalData.get(i).getMobile_number()));
            rowDataList.add(new String("" + personalData.get(i).getAddress1()));
            rowDataList.add(new String("" + personalData.get(i).getAddress2()));
            rowDataList.add(new String("" + personalData.get(i).getPin_code()));
            rowDataList.add(new String("" + personalData.get(i).getAccount_Number()));
            rowDataList.add(new String("" + personalData.get(i).getBank_Name()));
            rowDataList.add(new String("" + personalData.get(i).getBranch_Name()));
            
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "hr_personal_report.xls", "Hr_personal_details", null);
        return null;
    }
        public int[] paging(int rows, String recordCount, int current_page) {
        int start = 1;
        int end = 1;
        int next = current_page + 1;
        int prev = current_page - 1;
        int[] pageArr = new int[10];
        int totalPage = Integer.parseInt(recordCount) / rows;
        if (Integer.parseInt(recordCount) % rows != 0) {
            totalPage = totalPage + 1;
        }
        if (totalPage > 9) {
            int minus = current_page - 4;
            int plus = current_page + 4;
            if (minus > 0) {
                start = minus;
            } else {
                start = 1;
            }
            if ((plus - start) < 8) {
                plus = start + 8;
            }
            if (plus > totalPage) {
                end = totalPage;
            } else {
                end = plus;
            }
        } else {
            start = 1;
            end = totalPage;
        }
        if (prev < 1) {
            prev = 1;
        }
        if (next > totalPage) {
            next = totalPage;
        }
        pageArr[0] = totalPage;
        pageArr[1] = current_page;
        pageArr[2] = start;
        pageArr[3] = end;
        pageArr[4] = prev;
        pageArr[5] = next;
        return pageArr;
    }
}