/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.CustagingReportDataDTO;
import com.defiance.ideal.reports.dto.CustagingReportFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface CustagingReportDao {
   public void updateInvoiceDates(CustagingReportFilterDTO formData);
   public String maxDateOfReport();
}
