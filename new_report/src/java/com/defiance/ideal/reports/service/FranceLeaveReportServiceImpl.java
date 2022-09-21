/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.FranceLeaveReportDao;
import com.defiance.ideal.reports.dto.FranceLeaveReportDTO;
import java.util.List;

/**
 *
 * @author 16656
 */
public class FranceLeaveReportServiceImpl implements FranceLeaveReportService {

    public FranceLeaveReportDao franceLeaveReportDao;

    public FranceLeaveReportDao getFranceLeaveReportDao() {
        return franceLeaveReportDao;
    }

    public void setFranceLeaveReportDao(FranceLeaveReportDao franceLeaveReportDao) {
        this.franceLeaveReportDao = franceLeaveReportDao;
    }

    public List<FranceLeaveReportDTO> getFranceLeaveDetails(FranceLeaveReportDTO dto) {
        List<FranceLeaveReportDTO> projectList = franceLeaveReportDao.getFranceLeaveDetails(dto);
        System.out.println("Inside service size" + projectList.size());
        return projectList;
    }

    public List<FranceLeaveReportDTO> getSearchList(String empVal) {
        List<FranceLeaveReportDTO> searchList = null;
        try {
            searchList = franceLeaveReportDao.getSearchList(empVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public int getLeaveCount(FranceLeaveReportDTO dto) {
        int franceLeaveCount = 0;
        try {
            franceLeaveCount = franceLeaveReportDao.getLeaveCount(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return franceLeaveCount;
    }
}
