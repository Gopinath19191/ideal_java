/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dao.HolidayDao;
import com.defiance.ideal.dto.DetailsDto;
import com.defiance.ideal.dto.HolidayDto;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14053
 */
public class HolidayServiceImpl implements HolidayService {

    HolidayDao dao;

    public HolidayDao getDao() {
        return dao;
    }

    public void setDao(HolidayDao dao) {
        this.dao = dao;
    }

    public List getProjectList(HolidayDto formData) {
        List dataDto = dao.getProjectList(formData);
        return dataDto;
    }

    public List getHolidayList(HolidayDto formData) {
        List dataDto = dao.getHolidayList(formData);
        return dataDto;
    }

    public void updateHolidayAllowance(HolidayDto formData) {
        dao.updateHolidayAllowance(formData);
    }

    public List getAllowanceDetails(HolidayDto formData) {
        List dataDto = dao.getAllowanceDetails(formData);
        return dataDto;
    }

    public String getRecordCount(HolidayDto formData) {
        String dataDto = dao.getRecordCount(formData);
        return dataDto;
    }

    public Object getRequestData(String allowanceId) {
        Object dataDto = dao.getRequestData(allowanceId);
        return dataDto;
    }

    public List getEmployeeSearchList(String val) {
        List dataDto = dao.getEmployeeSearchList(val);
        return dataDto;
    }

    public String getEmployeeName(String val) {
        String dataDto = dao.getEmployeeName(val);
        return dataDto;
    }

    public List getTotalAllowanceDetails(HolidayDto formData) {
        List dataDto = dao.getTotalAllowanceDetails(formData);
        return dataDto;
    }

    public String getTotalRecordCount(HolidayDto formData) {
        String dataDto = dao.getTotalRecordCount(formData);
        return dataDto;
    }

    public List getWorkedDaysList(HolidayDto formData) {
        List dataDto = dao.getWorkedDaysList(formData);
        return dataDto;
    }

    //For RM Approval
    public List getRMApprovalDetails(HolidayDto formData) {
        List dataDto = dao.getRMApprovalDetails(formData);
        return dataDto;
    }

    public List<HolidayDto> getEmployeeList(String filterData) {
        List<HolidayDto> dataDto = dao.getEmployeeList(filterData);
        return dataDto;
    }

    public List<HolidayDto> getProjectName(HolidayDto formData) {
        List<HolidayDto> dataDto = dao.getProjectName(formData);
        return dataDto;
    }
//
//    public void updatePmStatus(HolidayDto formData) {
//        dao.updatePmStatus(formData);
//    }

    public String getPmRecordCount(HolidayDto formData) {
        String dataDto = dao.getPmRecordCount(formData);
        return dataDto;
    }

    public String getPMTotalRecordCount(HolidayDto formData) {
        String dataDto = dao.getPMTotalRecordCount(formData);
        return dataDto;
    }

    public List<HolidayDto> getConfigValues(String parentId) {
        List<HolidayDto> dataDto = null;
        try {
            dataDto = dao.getConfigValues(parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataDto;
    }
}
