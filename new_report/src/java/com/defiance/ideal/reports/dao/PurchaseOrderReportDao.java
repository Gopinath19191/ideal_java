/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.PurchaseOrderReportDetails;
import java.util.List;


/**
 *
 * @author 15261
 */
public interface PurchaseOrderReportDao {
	
    public List<PurchaseOrderReportDetails> getPurchaseOrderReportDetails(PurchaseOrderReportDetails filterData);

}
