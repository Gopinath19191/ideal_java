/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.pc.ticket.service;

import com.ideal.pc.ticket.dao.PcDaoImpl;
import com.ideal.pc.ticket.dto.PcDataDTO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 16364
 */
public class PcServiceImpl {
     PcDaoImpl daoImpl = new PcDaoImpl();

    public PcDaoImpl getDaoImpl() {
        return daoImpl;
    }

    public void setDaoImpl(PcDaoImpl daoImpl) {
        this.daoImpl = daoImpl;
    }
     public List<PcDataDTO> getConsultantList(PcDataDTO dto) {
        List<PcDataDTO> details = daoImpl.getConsultantList(dto);
        return details;
    }
     public List<PcDataDTO> getConsultantListByName(PcDataDTO dto) {
        List<PcDataDTO> details = daoImpl.getConsultantListByName(dto);
        return details;
    }
     
     public List<PcDataDTO> editConsultantById(PcDataDTO dto) {
         System.out.println("");
        List<PcDataDTO> details = getDaoImpl().editConsultantById(dto);
        return details;
    }
     public List<PcDataDTO> getCountryList() {
        List<PcDataDTO> countryList = null;
        try {
            countryList = daoImpl.getCountryList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }
     public List<PcDataDTO> getWorklocationList() {
        List<PcDataDTO> countryList = null;
        try {
            countryList = daoImpl.getWorklocationList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }
     
     public List<PcDataDTO> getRegionList(String countryId) {
        List<PcDataDTO> regionList = null;
        try {
            regionList = (List<PcDataDTO>) daoImpl.getRegionList(countryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public List<PcDataDTO> getCityList(String countryId, String regionId) {
        List<PcDataDTO> cityList = null;
        try {
            cityList = (List<PcDataDTO>) daoImpl.getCityList(countryId, regionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }
    
    public List<PcDataDTO> getCmpStructData(String parentId) {
        List<PcDataDTO> cmpStructList = null;
        try {
            cmpStructList = daoImpl.getCmpStructData(parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmpStructList;
    }
    
    public List<PcDataDTO> getPgData() {
        List<PcDataDTO> cmpStructList = null;
        try {
            cmpStructList = daoImpl.getPgData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmpStructList;
    }
    public List<PcDataDTO> getEmpDetails(String refVal) {
       
        List<PcDataDTO> refEmp = null;
          if(refVal!=null){
              refEmp = getDaoImpl().getEmpDetails(refVal);
        }
        return refEmp;
    }
    public List<PcDataDTO> getPcDetails(String refVal) {
       
        List<PcDataDTO> refEmp = null;
          if(refVal!=null){
              refEmp = getDaoImpl().getPcDetails(refVal);
        }
        return refEmp;
    }
    public List<PcDataDTO> getPcDetail(String refVal) {
       
        List<PcDataDTO> refEmp = null;
          if(refVal!=null){
              refEmp = getDaoImpl().getPcDetail(refVal);
        }
        return refEmp;
    }
    public List<PcDataDTO> getEmpDetail(String refVal) {
       
        List<PcDataDTO> refEmp = null;
          if(refVal!=null){
              refEmp = getDaoImpl().getEmpDetail(refVal);
        }
        return refEmp;
    }
    
      public List<PcDataDTO> getPrjDetails(String refVal) {
        List<PcDataDTO> refEmp = null;
        refEmp = getDaoImpl().getPrjDetails(refVal);
        return refEmp;
    }
      public String getPrjDetail(String refVal) {
        String refEmp = null;
        refEmp = getDaoImpl().getPrjDetail(refVal);
        return refEmp;
    }
      public PcDataDTO insertConsultant(PcDataDTO dto) {
        PcDataDTO details = getDaoImpl().insertConsultant(dto);
        return details;
    }
      public PcDataDTO updateConsultant(PcDataDTO dto) {
        PcDataDTO details = getDaoImpl().updateConsultant(dto);
        return details;
    }
      public PcDataDTO insertConsultantAddress(PcDataDTO dto) {
        PcDataDTO details = getDaoImpl().insertConsultantAddress(dto);
        return details;
    }
      public PcDataDTO updateConsultantAddress(PcDataDTO dto) {
        PcDataDTO details = getDaoImpl().updateConsultantAddress(dto);
        return details;
    }
       public PcDataDTO insertConsultantProof(PcDataDTO dto) {
        PcDataDTO details = getDaoImpl().insertConsultantProof(dto);
        return details;
    }
      public PcDataDTO updateConsultantProof(PcDataDTO dto) {
        PcDataDTO details = getDaoImpl().updateConsultantProof(dto);
        return details;
    }
       public PcDataDTO insertConsultantContact(PcDataDTO dto) {
        PcDataDTO details = getDaoImpl().insertConsultantContact(dto);
        return details;
    }
      public PcDataDTO updateConsultantContact(PcDataDTO dto) {
        PcDataDTO details = getDaoImpl().updateConsultantContact(dto);
        return details;
    }
        public int getHighestRefNo(PcDataDTO hr) {
        int highest_ref = getDaoImpl().getHighestRefNo(hr);
        return highest_ref;
    }
        
        public String getRMmail(String hr) {
        String mail = getDaoImpl().getRMmail(hr);
        return mail;
    }
         public String getNSmail() {
        String mail = getDaoImpl().getNSmail();
        return mail;
    }
          public String getpcid() {
        String mail = getDaoImpl().getpcid();
        return mail;
    }
          public void fileDownload(String fileName, String originalName,String filePath, String fileType, HttpServletResponse response) {
        try {
            response.setContentType(fileType);
            response.setHeader("Content-disposition", "attachment; filename=\"" + originalName + "\"");
            File file = new File(filePath + "\\" + fileName);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            byte[] buf = new byte[4 * 1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }
            in.close();
            out.close();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
