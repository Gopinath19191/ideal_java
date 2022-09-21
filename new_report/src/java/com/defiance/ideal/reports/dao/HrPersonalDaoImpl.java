/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.HrPersonalDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 8000458
 */
public class HrPersonalDaoImpl extends SqlMapClientDaoSupport implements HrPersonalDao {

    public List<HrPersonalDto> getPersonalDetails(HrPersonalDto id) throws Exception {

        List<HrPersonalDto> list = new ArrayList<HrPersonalDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("personalMap.getPersonalDetails", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getPersonalCount(HrPersonalDto dto) {
        int Count = 0;
        try {
            Count = (Integer) getSqlMapClientTemplate().queryForObject("personalMap.getPersonalCount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Count;
    }

    public List<HrPersonalDto> getSubSbu(HrPersonalDto parentId) {
        List<HrPersonalDto> subsbu = null;
        try {
            subsbu = getSqlMapClientTemplate().queryForList("personalMap.getSubSbu", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subsbu;
    }

    public List<HrPersonalDto> getSbu(HrPersonalDto parentId) {
        List<HrPersonalDto> sbu = null;
        try {
            sbu = getSqlMapClientTemplate().queryForList("personalMap.getSbu", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbu;
    }

    public List<HrPersonalDto> getPersonalDataXL(HrPersonalDto id) {

        List<HrPersonalDto> list = new ArrayList<HrPersonalDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("personalMap.getPersonalDataXL", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
