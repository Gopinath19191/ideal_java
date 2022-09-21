/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.pc.ticket.dao;

import com.ideal.pc.ticket.dto.PcDataDTO;
import java.util.HashMap;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16364
 */
public class PcDaoImpl extends SqlMapClientDaoSupport{
    
    public List<PcDataDTO> getConsultantList(PcDataDTO dto) {
        List<PcDataDTO> details = null;
        try {
            details = (List<PcDataDTO>) getSqlMapClient().queryForList("PcMap.getConsultantList", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;

    }
    public List<PcDataDTO> getConsultantListByName(PcDataDTO dto) {
        List<PcDataDTO> details = null;
        try {
            details = (List<PcDataDTO>) getSqlMapClient().queryForList("PcMap.getConsultantListByName", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;

    }
    public List<PcDataDTO> editConsultantById(PcDataDTO dto) {
         logger.info("dao impl id"+dto.getPcId());
        List<PcDataDTO> details = null;
        try {
            details = (List<PcDataDTO>) getSqlMapClientTemplate().queryForList("PcMap.editConsultantById", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }  
     public List<PcDataDTO> getCountryList() {
        List<PcDataDTO> countryList = null;
        try {
            countryList = (List<PcDataDTO>) getSqlMapClientTemplate().queryForList("PcMap.getCountryList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }
     public List<PcDataDTO> getWorklocationList() {
        List<PcDataDTO> locationList = null;
        try {
            locationList = (List<PcDataDTO>) getSqlMapClientTemplate().queryForList("PcMap.getWorklocationList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationList;
    }
     
     public List<PcDataDTO> getRegionList(String cntyId) {
        List<PcDataDTO> regionList = null;
         System.out.println("cntyId @@@@@@b "+cntyId);
        try {
            regionList = (List<PcDataDTO>) getSqlMapClientTemplate().queryForList("PcMap.getRegionList", cntyId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public List<PcDataDTO> getCityList(String cntyId, String regId) {
        List<PcDataDTO> cityList = null;
        try {
            HashMap cityMap = new HashMap();
            cityMap.put("cntyId", cntyId.trim());
            cityMap.put("regId", regId.trim());
            cityList = (List<PcDataDTO>) getSqlMapClientTemplate().queryForList("PcMap.getCityList", cityMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }
    
    public List<PcDataDTO> getCmpStructData(String parentId) {
        List<PcDataDTO> cmpStructList = null;
        try {
            cmpStructList = (List<PcDataDTO>) getSqlMapClientTemplate().queryForList("PcMap.getCmpStructData", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmpStructList;
    }
    public List<PcDataDTO> getPgData() {
        List<PcDataDTO> cmpStructList = null;
        try {
            cmpStructList = (List<PcDataDTO>) getSqlMapClientTemplate().queryForList("PcMap.getPgData");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmpStructList;
    }
    public List<PcDataDTO> getEmpDetails(String refVal) {
        List<PcDataDTO> refEmp = null;
        int len = refVal.split("-").length;
        if(len > 1){
            refVal = refVal.split("-")[1].trim();
        }else{
        refVal = "'%" + refVal + "%'";
        }
        System.out.println("refVal !!"+refVal);
        refEmp = getSqlMapClientTemplate().queryForList("PcMap.getEmpIds", refVal);

        return refEmp;
    }
    public List<PcDataDTO> getPcDetails(String refVal) {
        List<PcDataDTO> refEmp = null;
        int len = refVal.split("-").length;
        if(len > 1){
            refVal = refVal.split("-")[1].trim();
        }else{
        refVal = "'%" + refVal + "%'";
        }
        System.out.println("PCCCCrefVal !!"+refVal);
        refEmp = getSqlMapClientTemplate().queryForList("PcMap.getPcIds", refVal);

        return refEmp;
    }
    public List<PcDataDTO> getPcDetail(String refVal) {
        List<PcDataDTO> refEmp = null;
       
        refVal = "'%" + refVal + "%'";
        
        System.out.println("PCCCCrefVal !!"+refVal);
        refEmp = getSqlMapClientTemplate().queryForList("PcMap.getPcIdd", refVal);

        return refEmp;
    }
    public List<PcDataDTO> getEmpDetail(String refVal) {
        List<PcDataDTO> refEmp = null;
        refVal = "'%" + refVal + "%'";
        refEmp = getSqlMapClientTemplate().queryForList("PcMap.getEmpId", refVal);

        return refEmp;
    }
   public List<PcDataDTO> getPrjDetails(String refVal) {
        List<PcDataDTO> refEmp = null;
        int len = refVal.split("-").length;
        if(len > 1){
            refVal = refVal.split("-")[1].trim();
        }else{
        refVal = "'%" + refVal + "%'";
        }
      
        refEmp = getSqlMapClientTemplate().queryForList("PcMap.getPrjIds", refVal);
  
        return refEmp;
    }
   public int getHighestRefNo(PcDataDTO hr) {
        int highest_ref = 0;
        try {
            highest_ref = (Integer) getSqlMapClient().queryForObject("PcMap.getHighestRefNo", hr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return highest_ref;
    }
   public String getRMmail(String rm) {
        String rmMail = null;
        try {
            rmMail = (String) getSqlMapClient().queryForObject("PcMap.getRMmail", rm);
        } catch (Exception e) {
        }
        return rmMail;
    }
   public String getNSmail() {
        String rmMail = null;
        try {
            rmMail = (String) getSqlMapClient().queryForObject("PcMap.getNSmail");
        } catch (Exception e) {
        }
        return rmMail;
    }
   public String getpcid() {
        String rmMail = null;
        try {
            rmMail = (String) getSqlMapClient().queryForObject("PcMap.getpcid");
        } catch (Exception e) {
        }
        return rmMail;
    }
   public String getPrjDetail(String prjVal) {
        String refEmp = null;
        prjVal = "'%" + prjVal + "%'";
       System.out.println("prject search val !!!"+prjVal);
        refEmp = (String) getSqlMapClientTemplate().queryForObject("PcMap.getPrjId", prjVal);
  System.out.println("prject search RESULTTTTTT !!!"+refEmp);
        return refEmp;
    }
   
   public PcDataDTO insertConsultant(PcDataDTO dto) {

        PcDataDTO details = new PcDataDTO();
        details = (PcDataDTO) getSqlMapClientTemplate().insert("PcMap.insertConsultant", dto);
        return details;
    }
   public PcDataDTO updateConsultant(PcDataDTO dto) {
        PcDataDTO ulist = new PcDataDTO();
       
        ulist = (PcDataDTO) getSqlMapClientTemplate().insert("PcMap.updateConsultant", dto);
      
        return ulist;
    }
   public PcDataDTO insertConsultantAddress(PcDataDTO dto) {

        PcDataDTO details = new PcDataDTO();
        System.out.println("dt values ###"+dto.getConsultant_empid());
        details = (PcDataDTO) getSqlMapClientTemplate().insert("PcMap.insertConsultantAddress", dto);
        return details;
    }
   public PcDataDTO updateConsultantAddress(PcDataDTO dto) {
        PcDataDTO ulist = new PcDataDTO();
       
        ulist = (PcDataDTO) getSqlMapClientTemplate().insert("PcMap.updateConsultantAddress", dto);
      
        return ulist;
    }
   public PcDataDTO insertConsultantProof(PcDataDTO dto) {

        PcDataDTO details = new PcDataDTO();
        details = (PcDataDTO) getSqlMapClientTemplate().insert("PcMap.insertConsultantProof", dto);
        return details;
    }
   public PcDataDTO updateConsultantProof(PcDataDTO dto) {
      
        PcDataDTO ulistProof = new PcDataDTO();
       
        ulistProof=(PcDataDTO) getSqlMapClientTemplate().insert("PcMap.updateConsultantProof", dto);
        return ulistProof;
    }
   
   public PcDataDTO insertConsultantContact(PcDataDTO dto) {

        PcDataDTO details = new PcDataDTO();
        details = (PcDataDTO) getSqlMapClientTemplate().insert("PcMap.insertConsultantContact", dto);
        return details;
    }
   public PcDataDTO updateConsultantContact(PcDataDTO dto) {
      
        PcDataDTO ulistProof = new PcDataDTO();
       
        ulistProof=(PcDataDTO) getSqlMapClientTemplate().insert("PcMap.updateConsultantContact", dto);
        return ulistProof;
    }
}
