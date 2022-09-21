/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;
import com.defiance.ideal.reports.dao.SkillsDomainsDao;
import com.defiance.ideal.reports.dao.SkillsDomainsDaoImpl;
import com.defiance.ideal.reports.dto.SkillsDomainsDto;
import com.defiance.ideal.reports.service.SkillsDomainsServiceImpl;
import com.defiance.ideal.reports.service.SkillsDomainsService;
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
public class SkillsDomainsController extends MultiActionController {
 

  
    public ModelAndView getSkillDomainDetails(HttpServletRequest request, HttpServletResponse response, SkillsDomainsDto dto) throws Exception {
        ModelAndView mv = new ModelAndView("SkillsDomains");
        WebApplicationContext context = getWebApplicationContext();
        SkillsDomainsService service = (SkillsDomainsService) context.getBean("SkillsDomainsService");
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

        int recordCount = service.getSkillDomainCount(dto);//list.size();
        int[] pageNo = paging(rows, Integer.toString(recordCount), page);
        System.out.println("page no is :" + page);
        List<SkillsDomainsDto> personal = service.getSkillDomainDetails(dto);
        mv.addObject("details", personal);
        List<SkillsDomainsDto> sbu = service.getSbu(dto);
        mv.addObject("sbu", sbu);
        mv.addObject("selectedsbu", dto.getSbu());
        mv.addObject("selectedsubsbu", dto.getSubsbu());
        mv.addObject("paging", pageNo);
        List<SkillsDomainsDto> subsbu = service.getSubSbu(dto);
        mv.addObject("subsbu", subsbu);
        return mv;
    }
       public ModelAndView SkillDomainFilter(HttpServletRequest request, HttpServletResponse response, final SkillsDomainsDto dto) {
        ModelAndView mvc = new ModelAndView("SkillsDomains");
        final WebApplicationContext ctx = getWebApplicationContext();
        SkillsDomainsServiceImpl bo = (SkillsDomainsServiceImpl) ctx.getBean("SkillsDomainsService");
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
        List<SkillsDomainsDto> sbu = bo.getSbu(dto);
        List<SkillsDomainsDto> subsbu = bo.getSubSbu(dto);
        System.out.println("sbu "+parentId);
        mvc.addObject("result", dto);
        mvc.addObject("subsbu", subsbu);
        mvc.addObject("sbu", sbu);
        return mvc;
    }
   
     public ModelAndView getSkillDomainGroup(HttpServletRequest request, HttpServletResponse response,SkillsDomainsDto dto) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
       SkillsDomainsServiceImpl dao = (SkillsDomainsServiceImpl) ctx.getBean("SkillsDomainsService");
        String name=request.getParameter("sbu");
        String pid = request.getParameter("parentId");
        System.out.println("parent id "+pid + "  unit name "+name);

         dto.setParentId(pid);
         response.setHeader("Content-type", "text/html");
         for (SkillsDomainsDto dTO : dao.getSubSbu(dto)) {
         System.out.println("----" + dTO.getName());
         response.getWriter().println("<option value='" + dTO.getId() + "'>" + dTO.getName() + "</option>");
     }
           return null;
   }
    public ModelAndView getSkillDomainDataXL(HttpServletRequest request, HttpServletResponse response, final SkillsDomainsDto dto) throws IOException, Exception {

        final WebApplicationContext ctx = getWebApplicationContext();
        SkillsDomainsServiceImpl dao = (SkillsDomainsServiceImpl) ctx.getBean("SkillsDomainsService");
        List<SkillsDomainsDto> personalData = null;
        personalData = dao.getSkillDomainDetails(dto);
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < personalData.size(); i++) {
        ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + personalData.get(i).getEmployee_number()));
            rowDataList.add(new String("" + personalData.get(i).getEmployee_name()));
            rowDataList.add(new String("" + personalData.get(i).getSbu()));
            rowDataList.add(new String("" + personalData.get(i).getSubsbu()));
            rowDataList.add(new String("" + personalData.get(i).getSkills()));
            rowDataList.add(new String("" + personalData.get(i).getDomains()));
            
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "Skills_domains_report.xls", "Skills_domains", null);
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


