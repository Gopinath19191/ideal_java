/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dao;

import com.defiance.ideal.travelplan.dto.LocalConveyanceDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14578
 */
public class LocalConveyanceDaoImpl extends SqlMapClientDaoSupport implements LocalConveyanceDao {

    public LocalConveyanceDto getEmpDetails(String empId) {
        LocalConveyanceDto employeeDetails = null;
        try {
            employeeDetails = (LocalConveyanceDto) getSqlMapClientTemplate().queryForObject("LocalConveyance.getEmpDetails", empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeDetails;
    }

    public List<LocalConveyanceDto> getConfigValues(String parentId) {
        List<LocalConveyanceDto> configValues = null;
        try {
            configValues = getSqlMapClientTemplate().queryForList("LocalConveyance.getConfigValues", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configValues;
    }

    public List<LocalConveyanceDto> getProjectList(String empId) {
        List<LocalConveyanceDto> projectDetails = null;
        try {
            projectDetails = getSqlMapClientTemplate().queryForList("LocalConveyance.getProjectList", empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectDetails;
    }

    public List<LocalConveyanceDto> getCityList(String countryId) {
        List<LocalConveyanceDto> cityList = null;
        try {
            cityList = getSqlMapClientTemplate().queryForList("LocalConveyance.getCityList", countryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public List<LocalConveyanceDto> getTravelPoints(String countryId) {
        List<LocalConveyanceDto> travelPoints = null;
        try {
            travelPoints = getSqlMapClientTemplate().queryForList("LocalConveyance.getTravelPoints", countryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return travelPoints;
    }

    public LocalConveyanceDto getLastInsertLCId() {
        LocalConveyanceDto lastInsertTpId = null;
        try {
            lastInsertTpId = (LocalConveyanceDto) getSqlMapClientTemplate().queryForObject("LocalConveyance.getLastInsertLCId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertTpId;
    }

    public String insertLocalConveyance(LocalConveyanceDto formDtoObj) {
        String tpMasterId = null;
        try {
            System.out.println("req date====" + formDtoObj.getReqDate()+"&&&&&&&&&&&&&"+formDtoObj.getProject());
            tpMasterId = (String) getSqlMapClientTemplate().insert("LocalConveyance.insertTpMaster", formDtoObj);
            System.out.println("tpMaster Id====" + tpMasterId);
            formDtoObj.setTpMasterId(tpMasterId);
            if(formDtoObj != null){
                if(formDtoObj.getReqRemarks() == null){
                    formDtoObj.setReqRemarks("");
                }
            }
            getSqlMapClientTemplate().insert("LocalConveyance.insertTpLocalConveyance", formDtoObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tpMasterId;
    }

    public String getLastInsertId() {
        String tpLastInsertMasterId = (String) getSqlMapClientTemplate().queryForObject("LocalConveyance.getLastInsertTpMasterId");
        System.out.println("tpMaster Id====" + tpLastInsertMasterId);
        return tpLastInsertMasterId;
    }

    public LocalConveyanceDto getReferenceId(String uniqueId) {
        LocalConveyanceDto refId = null;
        try {
            refId = (LocalConveyanceDto) getSqlMapClientTemplate().queryForObject("LocalConveyance.getReferenceId", uniqueId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return refId;
    }

    public void updateLocalConveyance(String LCUniqueId, LocalConveyanceDto updateDto) {
        try {
            System.out.println("Update Local conveyance==");
            updateDto.setTpUniqueId(LCUniqueId);
            getSqlMapClientTemplate().update("LocalConveyance.updateTpmaster", updateDto);
            getSqlMapClientTemplate().update("LocalConveyance.updateLC", updateDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertHistories(String LCUniqueId, LocalConveyanceDto historyDto) {
        LocalConveyanceDto oldTpMasterValue = null;
        LocalConveyanceDto oldLocalConveyanceValues = null;
        try {
            oldTpMasterValue = (LocalConveyanceDto) getSqlMapClientTemplate().queryForObject("LocalConveyance.selectRecordInTpMaster", LCUniqueId);
            System.out.println("old Dto Values===" + oldTpMasterValue.getGuestBooking());
            getSqlMapClientTemplate().insert("LocalConveyance.insertTpHistories", oldTpMasterValue);
            historyDto.setTpUniqueId(LCUniqueId);
            System.out.println("********************" + historyDto.getTpUniqueId());
            System.out.println("historyDto values====" + historyDto.getGuestBooking() + "----");
            getSqlMapClientTemplate().update("LocalConveyance.updateTpmaster", historyDto);
            oldLocalConveyanceValues = (LocalConveyanceDto) getSqlMapClientTemplate().queryForObject("LocalConveyance.selectRecordInLC", LCUniqueId);
            getSqlMapClientTemplate().insert("LocalConveyance.insertLCHistory", oldLocalConveyanceValues);
            getSqlMapClientTemplate().update("LocalConveyance.updateLC", historyDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<LocalConveyanceDto> getHistoryDetails(String tpUniqueId) {
        List<LocalConveyanceDto> historyDetails = null;
        try {
            historyDetails = getSqlMapClientTemplate().queryForList("LocalConveyance.getHistoryDetails", tpUniqueId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historyDetails;
    }

    public LocalConveyanceDto getLCViewDetails(String tpUniqueId) {
        LocalConveyanceDto lcDetails = null;
        try {
            lcDetails = (LocalConveyanceDto) getSqlMapClientTemplate().queryForObject("LocalConveyance.getLCViewDetails", tpUniqueId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lcDetails;
    }
}
