/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import java.util.List;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.PurchaseOrderReportDTO;
import com.defiance.ideal.reports.dto.PurchaseOrderReportDetails;

/**
 *
 * @author 15261
 */
public interface PurchaseOrderReportService {

    public List<PurchaseOrderReportDetails> getPurchaseOrderReportDetails(PurchaseOrderReportDetails getPurchaseOrderReportDetailsfilterData);

    public int getPurchaseOrderReportDetailsCount(PurchaseOrderReportDetails dto);
    
   }
