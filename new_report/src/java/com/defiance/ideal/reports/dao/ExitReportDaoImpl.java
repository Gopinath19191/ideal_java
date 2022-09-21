/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.ExitReportDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14578
 */
public class ExitReportDaoImpl extends SqlMapClientDaoSupport {

    public List<ExitReportDTO> getExitReport(String exitTriggerDate) {
        List<ExitReportDTO> getExitReport = null;
        try {
            getExitReport = getSqlMapClientTemplate().queryForList("ExitReportMap.fetchExitReport",exitTriggerDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getExitReport;
    }
}
