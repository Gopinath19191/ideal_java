/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.BenchReportDataDTO;
import com.defiance.ideal.reports.dto.BenchReportFilterDTO;
import java.util.List;
import java.util.Map;
/**
 *
 * @author 14619
 */
public interface BenchReportService {
    public List<BenchReportDataDTO> fetchBenchList(BenchReportFilterDTO filterData);

 public Map<String, String> getSbuList();
 public Map<String, String> getBandList();
}


