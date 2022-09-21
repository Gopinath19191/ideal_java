/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.PurchaseOrderReportDetails;
import java.util.List;

import com.defiance.ideal.reports.dao.PurchaseOrderReportDaoImpl;
import com.defiance.ideal.reports.dto.PurchaseOrderReportDTO;
import com.defiance.ideal.reports.dto.PurchaseOrderReportDetails;

/**
 * 
 * @author 15261
 */
public class PurchaseOrderReportServiceImpl implements
        PurchaseOrderReportService {

    public PurchaseOrderReportDaoImpl purchaseOrderReportDaoImpl;

    public PurchaseOrderReportDaoImpl getPurchaseOrderReportDaoImpl() {
        return purchaseOrderReportDaoImpl;
    }

    public void setPurchaseOrderReportDaoImpl(PurchaseOrderReportDaoImpl purchaseOrderReportDaoImpl) {
        this.purchaseOrderReportDaoImpl = purchaseOrderReportDaoImpl;
    }

    public List<PurchaseOrderReportDetails> getPurchaseOrderReportDetails(PurchaseOrderReportDetails filterData) {
        return purchaseOrderReportDaoImpl.getPurchaseOrderReportDetails(filterData);
    }

    public int getPurchaseOrderReportDetailsCount(PurchaseOrderReportDetails dto) {
        int recordCount = 0;
        try{
            recordCount = purchaseOrderReportDaoImpl.getPurchaseOrderReportDetailsCount(dto);
        }catch(Exception e){
            e.printStackTrace();
        }
        return recordCount;
    }
}
