/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.CollectionReportDao;
import com.defiance.ideal.reports.dao.CollectionReportDaoImpl;
import com.defiance.ideal.reports.dto.CollectionReportDataDTO;
import com.defiance.ideal.reports.dto.CollectionReportFilterDTO;
import com.defiance.ideal.reports.dto.ReimbursementsData;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public class CollectionReportServiceImpl implements CollectionReportService{
    public CollectionReportDaoImpl collectionReportDaoImpl;

    public CollectionReportDaoImpl getCollectionReportDaoImpl() {
        return collectionReportDaoImpl;
    }

    public void setCollectionReportDaoImpl(CollectionReportDaoImpl collectionReportDaoImpl) {
        this.collectionReportDaoImpl = collectionReportDaoImpl;
    }

   public List<CollectionReportDataDTO> fetchCollectionReport(CollectionReportFilterDTO filterData){
       List<CollectionReportDataDTO> projectList = collectionReportDaoImpl.fetchCollectionReport(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;       
   }
   public List<CollectionReportDataDTO> fetchLegalEntity(CollectionReportFilterDTO filterData){
       List<CollectionReportDataDTO> projectList = collectionReportDaoImpl.fetchLegalEntity(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;
   }
   public List<CollectionReportDataDTO> fetchBusinessLeader(CollectionReportFilterDTO filterData){
       List<CollectionReportDataDTO> projectList = collectionReportDaoImpl.fetchBusinessLeader(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;
   }
   public List<CollectionReportDataDTO> fetchBdmName(CollectionReportFilterDTO filterData){
       List<CollectionReportDataDTO> projectList = collectionReportDaoImpl.fetchBdmName(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;
   }
   public List<CollectionReportDataDTO> fetchBdmId(CollectionReportFilterDTO filterData){
       List<CollectionReportDataDTO> projectList = collectionReportDaoImpl.fetchBdmId(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;
   }
   public List<CollectionReportDataDTO> fetchBdmNameList(CollectionReportFilterDTO filterData){
       List<CollectionReportDataDTO> projectList = collectionReportDaoImpl.fetchBdmNameList(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;
   }
   public List<CollectionReportDataDTO> fetchCustomerName(CollectionReportFilterDTO filterData){
       List<CollectionReportDataDTO> projectList = collectionReportDaoImpl.fetchCustomerName(filterData);
       System.out.println("Inside service size"+projectList.size());
       return projectList;
   }
   public List<CollectionReportFilterDTO> getInvoiceDetails(CollectionReportFilterDTO filterData){
       List<CollectionReportFilterDTO> invoiceList = collectionReportDaoImpl.getInvoiceDetails(filterData);
       System.out.println("Inside service size"+invoiceList.size());
       return invoiceList;
   }
   
   public CollectionReportFilterDTO getInvoiceAverage(CollectionReportFilterDTO filterData){
       CollectionReportFilterDTO invoiceAverage = collectionReportDaoImpl.getInvoiceAverage(filterData);
       return invoiceAverage;
   }
   
   public List<ReimbursementsData> getReimbursementsList(){
       List<ReimbursementsData> reimbursementsList = collectionReportDaoImpl.getReimbursementsList();
       return reimbursementsList;
   }
   
   public List<ReimbursementsData> getReimbursementReport(ReimbursementsData filteData){
       List<ReimbursementsData> reimbursementsList = collectionReportDaoImpl.getReimbursementReport(filteData);
       return reimbursementsList;
   }
}
