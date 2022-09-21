/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.master.ticket.controller;

import com.ideal.master.ticket.dto.FeedbackMasterDto;
import com.ideal.master.ticket.service.TicMasterService;
import com.ideal.system.ticket.dto.TicSystemDataDTO;
import com.ideal.system.ticket.service.TicSystemServiceImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16113
 */
public class TicMasterController extends MultiActionController {
    public static int pageCount = 15;
    
    public synchronized ModelAndView showFeedback(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mvc = new ModelAndView("/feedback_master");
         final WebApplicationContext ctx = getWebApplicationContext();
       
        TicMasterService s = (TicMasterService) ctx.getBean("TicMasterService");
        List<FeedbackMasterDto> al = s.showFeedback();
        
        mvc.addObject("itype", al);
        return mvc;    
}
    
    public synchronized ModelAndView search_feedback_user(HttpServletRequest request, HttpServletResponse response, TicSystemDataDTO dto) {
        ModelAndView mvc = new ModelAndView("/feedback_master");

        HttpSession session = request.getSession();
       
        final WebApplicationContext ctx = getWebApplicationContext();
        TicSystemServiceImpl bo = (TicSystemServiceImpl) ctx.getBean("TicSystemService");
        String emp = (String) session.getAttribute("EMP_ID");
        int empidd = Integer.valueOf(emp);
        dto.setId(empidd);
        List<TicSystemDataDTO> details = bo.search_feedback_user(dto);
        mvc.addObject("details", details);
        List<TicSystemDataDTO> statusList = bo.getStatusList(dto);
        mvc.addObject("statusList", statusList);
        return mvc;
}
    
    public synchronized ModelAndView add_issuetype(HttpServletRequest request, HttpServletResponse response)
    {
         ModelAndView mvc = new ModelAndView("/add_issue");
         return mvc;
}
     
    public synchronized ModelAndView insertIssue(HttpServletRequest request, HttpServletResponse response)
    {
         String issueName = request.getParameter("issueName");
         String status[] = request.getParameterValues("status");
          ModelAndView mvc = new ModelAndView("/feedback_master");
          final WebApplicationContext ctx = getWebApplicationContext();
       
        TicMasterService s = (TicMasterService) ctx.getBean("TicMasterService");
        s.addIssue(issueName, status);
        List<FeedbackMasterDto> al = s.showFeedback();
        
       request.setAttribute("itype", al);
        mvc.addObject("itype", al);
        return mvc;
}
     
    public synchronized ModelAndView editIssue(HttpServletRequest request, HttpServletResponse response)
    {
         String issueId = request.getParameter("issueId");
          ModelAndView mvc = new ModelAndView("/issue_editor");
          final WebApplicationContext ctx = getWebApplicationContext();       
        TicMasterService s = (TicMasterService) ctx.getBean("TicMasterService");
        FeedbackMasterDto singleDto = s.editIssue(issueId);
        List<FeedbackMasterDto> statusList = s.getStatusList(issueId);
       
        mvc.addObject("issueData",singleDto);
        mvc.addObject("statusList",statusList);
        return mvc;
}
    
    public synchronized ModelAndView deleteIssue(HttpServletRequest request, HttpServletResponse response)
    {
          String issueId = request.getParameter("issueId");
           ModelAndView mvc = new ModelAndView("/feedback_master");
          final WebApplicationContext ctx = getWebApplicationContext();       
        TicMasterService s = (TicMasterService) ctx.getBean("TicMasterService");
        s.deleteIssue(issueId);
         List<FeedbackMasterDto> al = s.showFeedback();
        
       request.setAttribute("itype", al);
        mvc.addObject("itype", al);
        return mvc;
}
      
    public synchronized ModelAndView updateIssue(HttpServletRequest request, HttpServletResponse response)
    {
         String oldStatus[] = request.getParameterValues("oldStatus");
         String curStatus[] = request.getParameterValues("status");
         String issueName = request.getParameter("issueName");
         String issueId = request.getParameter("issueId");
         String oldIssue = request.getParameter("oldIssue");
         String stausId[] = request.getParameterValues("stausId");
         String newstatus[] = request.getParameterValues("newstatus");
           ModelAndView mvc = new ModelAndView("/feedback_master");
         final WebApplicationContext ctx = getWebApplicationContext();       
        TicMasterService service = (TicMasterService) ctx.getBean("TicMasterService");
        FeedbackMasterDto issueObject = null;
        List<FeedbackMasterDto> updateList = new ArrayList<FeedbackMasterDto>();
        
         List<FeedbackMasterDto> insertList = new ArrayList<FeedbackMasterDto>();
        FeedbackMasterDto statusObj = null;
        if(!oldIssue.equals(issueName)){
            issueObject = new FeedbackMasterDto();
              String confKey = issueName.substring(0, 1);
               issueObject.setConfiguration_key(confKey);
                issueObject.setConfiguration_value(issueName);
                issueObject.setParent_id("0");
                issueObject.setId(issueId);                 
                 service.updateIssue(issueObject);
                
        }   
           for(int i=0;i<curStatus.length;i++){
                    
                if(!oldStatus[i].equals(curStatus[i]) )
                {
                    statusObj = new FeedbackMasterDto();
                    
                    String statusval = curStatus[i];
                    if(statusval!="" || statusval != null)
                    {       
                        String statusKey = statusval.substring(0, 2);
                            statusObj.setConfiguration_key(statusKey);
                                   statusObj.setConfiguration_value(statusval);
                                    statusObj.setParent_id(issueId);
                                    statusObj.setId(stausId[i]);
                             updateList.add(statusObj);
                     }
                  }
             }
        service.updateStaus(updateList);
        if(newstatus!=null  ){
            for(int i=0;i<newstatus.length;i++){
               statusObj = new FeedbackMasterDto();
                String statusval = newstatus[i];
                 if(statusval!="" || statusval != null)
                    { 
                        String statusKey = statusval.substring(0, 2);
                        statusObj.setConfiguration_key(statusKey); 
                        statusObj.setConfiguration_value(statusval);
                        statusObj.setParent_id(issueId);
                        insertList.add(statusObj);
                    }
            }
        }
        service.insertStatus(insertList);
         List<FeedbackMasterDto> al = service.showFeedback();
         mvc.addObject("itype", al);
        return mvc;
}
      
}
