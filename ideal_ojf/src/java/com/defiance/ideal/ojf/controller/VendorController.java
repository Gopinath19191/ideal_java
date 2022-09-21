/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.controller;

import com.defiance.ideal.ojf.dao.LoginDAOImpl;
import com.defiance.ideal.ojf.dto.ApplicantDTO;
import com.defiance.ideal.ojf.dto.LoginDTO;
import com.defiance.ideal.ojf.joiningForm.dao.MasterDaoImpl;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerFormDTO;
import com.defiance.ideal.ojf.joiningForm.dto.MasterDataDTO;
import com.defiance.ideal.ojf.joiningForm.dto.VenderIdNameDTO;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 15850
 */
public class VendorController extends MultiActionController {
// @RequestMapping("/vendordetails")p-
    public ModelAndView vendordetails(HttpServletRequest requestObj,HttpServletResponse response) throws Exception {
         ModelAndView mv = null;
     //    HttpServletRequest requestObj = this.getRequest();
         List<MasterDataDTO> vendorList =  ((MasterDaoImpl)this.getBean("MasterDao")).getvendorDetaillist();
        System.out.println("vendorList++++++++++++++++"+vendorList);

            requestObj.setAttribute("vendorList", vendorList);
        return new ModelAndView("vendordetails");
        
    }
   
     // @RequestMapping("/vendorsave")
    public ModelAndView vendorsave(HttpServletRequest requestObj,HttpServletResponse response) throws Exception {
         ModelAndView mv = null;
       
        System.out.println("%%%:"+requestObj.getParameter("newvendorName"));
        String existvenname=((MasterDaoImpl)this.getBean("MasterDao")).checkexistsvenname(requestObj.getParameter("newvendorName"));
        System.out.println("existvenname::::"+existvenname);
        if(existvenname!=null)
        {
            System.out.println("if");
           //controlObj.updatepartvendorinfo(existvenname,requestObj.getParameter("newvendorName"));
           requestObj.setAttribute("saveindication", "Exists");
        }
         else
        {
            System.out.println("else"+requestObj.getParameter("newvendorName"));
       ((MasterDaoImpl)this.getBean("MasterDao")).savevendorinfo(requestObj.getParameter("newvendorName"));
            System.out.println("hhhhh");
          requestObj.setAttribute("saveindication", "save");
        }
         List<MasterDataDTO> vendorList =  ((MasterDaoImpl)this.getBean("MasterDao")).getvendorDetaillist();
         System.out.println("vendorList++++++"+vendorList);
         requestObj.setAttribute("vendorList", vendorList);
        return new ModelAndView("vendordetails");
    }
    
    //updateparticularvendor()
     public ModelAndView updateparticularvendor(HttpServletRequest requestObj,HttpServletResponse responseObj) throws Exception {
         ModelAndView mv = null;
         System.out.println("%%%:"+requestObj.getParameter("selectedvenid"));
       System.out.println("%%%:"+requestObj.getParameter("selectedvenname"));
       String venid=requestObj.getParameter("selectedvenid");
       String venname=requestObj.getParameter("selectedvenname");
       
      VenderIdNameDTO venderIdNameDTO =new VenderIdNameDTO();
      venderIdNameDTO.setVenid(venid);
      venderIdNameDTO.setVenname(venname);
      
      String existvenname=((MasterDaoImpl)this.getBean("MasterDao")).checkexistsvenname(requestObj.getParameter("venname"));
       String exchk=null;
        if(existvenname==null || existvenname.equals(venid))
        {
            System.out.println("if++++");
        ((MasterDaoImpl)this.getBean("MasterDao")).updatepartvendorinfo(venderIdNameDTO);
        exchk="update";
        }
        else
        {
            System.out.println("else");
          exchk="notupdate";
        }//done
       List<MasterDataDTO> vendorList =  ((MasterDaoImpl)this.getBean("MasterDao")).getpartvendetails(venid);
            Iterator itr = vendorList.iterator();
       
        while (itr.hasNext()) {
          MasterDataDTO vendornameid = (MasterDataDTO)itr.next();
                responseObj.getOutputStream().write(vendornameid.getVendorId().getBytes());
                responseObj.getOutputStream().write(",".getBytes());
                responseObj.getOutputStream().write(vendornameid.getVendorName().getBytes());
                responseObj.getOutputStream().write(",".getBytes());
                responseObj.getOutputStream().write(exchk.getBytes());
        }
         return mv;
     }
  
     
   public ModelAndView deleteparticularvendor(HttpServletRequest requestObj,HttpServletResponse responseObj) throws Exception {
         ModelAndView mv = null;  
         System.out.println("%%%:"+requestObj.getParameter("selectvenid"));
       String venid=requestObj.getParameter("selectvenid");
        ((MasterDaoImpl)this.getBean("MasterDao")).deletepartvendorinfo(venid);
         List<MasterDataDTO> vendorList =  ((MasterDaoImpl)this.getBean("MasterDao")).getvendorDetaillist();
         System.out.println("############:"+vendorList.size());
         
          Iterator itr = vendorList.iterator();
       
        while (itr.hasNext()) {
          MasterDataDTO vendornameid = (MasterDataDTO)itr.next();
                responseObj.getOutputStream().write(vendornameid.getVendorId().getBytes());
                responseObj.getOutputStream().write(",".getBytes());
                responseObj.getOutputStream().write(vendornameid.getVendorName().getBytes());
                responseObj.getOutputStream().write(",".getBytes());
                
        }
        return mv;
   
   }   
   
   
     
     
     
     
     
    public Object getBean(String beanName) {
        Object o = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            return ctx.getBean(beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
