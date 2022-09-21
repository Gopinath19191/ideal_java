/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.HrPersonalDao;
import com.defiance.ideal.reports.dao.HrPersonalDaoImpl;
import com.defiance.ideal.reports.dto.HrPersonalDto;
import java.util.List;

/**
 *
 * @author 8000458
 */
public class HrPersonalServiceImpl implements HrPersonalService {

    HrPersonalDao dao;

    public HrPersonalDao getDao() {
        return dao;
    }

    public void setDao(HrPersonalDao dao) {
        this.dao = dao;
    }

    public int getPersonalCount(HrPersonalDto dto) {
        int momRecordCount = 0;
        try {
            momRecordCount = dao.getPersonalCount(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return momRecordCount;
    }

    public List<HrPersonalDto> getPersonalDetails(HrPersonalDto id) throws Exception {
        return dao.getPersonalDetails(id);
    }

    public List<HrPersonalDto> getSbu(HrPersonalDto parentId) {
        return dao.getSbu(parentId);
    }

    public List<HrPersonalDto> getSubSbu(HrPersonalDto parentId) {
        return dao.getSubSbu(parentId);
    }

    public List<HrPersonalDto> getPersonalDataXL(HrPersonalDto id) {
        return dao.getPersonalDataXL(id);
    }
}
