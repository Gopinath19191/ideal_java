/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.FranceLeaveReportDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 16656
 */
public class FranceLeaveReportDaoImpl extends SqlMapClientDaoSupport implements FranceLeaveReportDao {

    public List<FranceLeaveReportDTO> getFranceLeaveDetails(FranceLeaveReportDTO dto) {
        // System.out.println("Inside Dao");
        List<FranceLeaveReportDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("ReportMap.getFranceLeaveList", dto);
            // System.out.println("template size"+template.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<FranceLeaveReportDTO> getSearchList(String empVal) {
        List<FranceLeaveReportDTO> searchList = null;
        try {
            String key = empVal + "%";
            System.out.println("in daoimpl::::" + key);
            searchList = getSqlMapClientTemplate().queryForList("ReportMap.getSearchList", key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public int getLeaveCount(FranceLeaveReportDTO dto) {
        int leaveCount = 0;
        try {
            leaveCount = (Integer) getSqlMapClientTemplate().queryForObject("ReportMap.getFranceLeaveCount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaveCount;
    }
}
