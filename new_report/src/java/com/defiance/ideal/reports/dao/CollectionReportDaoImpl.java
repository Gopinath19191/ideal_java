/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.CollectionReportDataDTO;
import com.defiance.ideal.reports.dto.CollectionReportFilterDTO;
import com.defiance.ideal.reports.dto.ReimbursementsData;
import com.defiance.ideal.reports.util.CommonConfigurations;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14517
 */
public class CollectionReportDaoImpl extends SqlMapClientDaoSupport implements CollectionReportDao{
     private String sbuId;

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }
    public List<CollectionReportDataDTO> fetchCollectionReport(CollectionReportFilterDTO filterData) {
        List<CollectionReportDataDTO> reportDetails = null;
        String PES=CommonConfigurations.BUH_FOR_PES;
        String  TS=CommonConfigurations.BUH_FOR_TS;
          if(filterData.getBusiness_leader_id()!=null)
          {
            if(filterData.getBusiness_leader_id().equals(PES))
            {
                filterData.setSBU(CommonConfigurations.PES);
            }
            else if(filterData.getBusiness_leader_id().equals(TS))
            {
                filterData.setSBU(CommonConfigurations.TS);
            }
          }
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CollectionReport.fetchCollectionReport",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }

    public List<CollectionReportDataDTO> fetchLegalEntity(CollectionReportFilterDTO filterData) {
        List<CollectionReportDataDTO> reportDetails = null;
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CollectionReport.fetchLegalEntity",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }
    public List<CollectionReportDataDTO> fetchBusinessLeader(CollectionReportFilterDTO filterData) {
        List<CollectionReportDataDTO> reportDetails = null;
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CollectionReport.fetchBusinessLeader",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }
    public List<CollectionReportDataDTO> fetchBdmName(CollectionReportFilterDTO filterData) {
        List<CollectionReportDataDTO> reportDetails = null;
        String PES=CommonConfigurations.BUH_FOR_PES;
        String  TS=CommonConfigurations.BUH_FOR_TS;
        System.out.println("Business Leader "+filterData.getBusiness_leader_id());
          if(filterData.getBusiness_leader_id()!=null)
          {
            if(filterData.getBusiness_leader_id().equals(PES))
            {
                filterData.setSBU(CommonConfigurations.PES);
            }
            else if(filterData.getBusiness_leader_id().equals(TS))
            {
                filterData.setSBU(CommonConfigurations.TS);
            }
          }
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CollectionReport.fetchBdmName",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }
    public List<CollectionReportDataDTO> fetchCustomerName(CollectionReportFilterDTO filterData) {
        List<CollectionReportDataDTO> reportDetails = null;
        String PES=CommonConfigurations.BUH_FOR_PES;
        String  TS=CommonConfigurations.BUH_FOR_TS;
          if(filterData.getBusiness_leader_id()!=null)
          {
            if(filterData.getBusiness_leader_id().equals(PES))
            {
                filterData.setSBU(CommonConfigurations.PES);
                
            }
            else if(filterData.getBusiness_leader_id().equals(TS))
            {
                filterData.setSBU(CommonConfigurations.TS);
            }
          }
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CollectionReport.fetchCustomerName",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }
     public List<CollectionReportDataDTO> fetchBdmId(CollectionReportFilterDTO filterData) {
        List<CollectionReportDataDTO> reportDetails = null;
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CollectionReport.fetchBdmId",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }
      public List<CollectionReportDataDTO> fetchBdmNameList(CollectionReportFilterDTO filterData) {
        List<CollectionReportDataDTO> reportDetails = null;
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CollectionReport.fetchBdmNameList",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }
    public List<CollectionReportFilterDTO> getInvoiceDetails(CollectionReportFilterDTO filterData) {
        List<CollectionReportFilterDTO> invoiceReport = null;
        try {
            invoiceReport = getSqlMapClientTemplate().queryForList("CollectionReport.getInvoiceDetails",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceReport;
    }
    public CollectionReportFilterDTO getInvoiceAverage(CollectionReportFilterDTO filterData){
        CollectionReportFilterDTO invoiceReport = null;
        try {
            invoiceReport = (CollectionReportFilterDTO)getSqlMapClientTemplate().queryForObject("CollectionReport.getInvoiceAverage",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceReport;
    }
    public List<ReimbursementsData> getReimbursementsList(){
       List<ReimbursementsData> reimbursementsList = null;
       try {
            reimbursementsList = getSqlMapClientTemplate().queryForList("CollectionReport.getReimbursementList");
        } catch (Exception e) {
            e.printStackTrace();
        }
       return reimbursementsList;
   }
   public List<ReimbursementsData> getReimbursementReport(ReimbursementsData filteData){
       List<ReimbursementsData> reimbursementList = null;
       try {
            reimbursementList = getSqlMapClientTemplate().queryForList("CollectionReport.getReimbursementReport", filteData);
            System.out.println("count   "+reimbursementList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
       return reimbursementList;
   }
}
