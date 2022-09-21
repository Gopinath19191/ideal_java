/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.SkillsDomainsDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 8000458
 */
public class SkillsDomainsDaoImpl extends SqlMapClientDaoSupport implements SkillsDomainsDao {

    public List<SkillsDomainsDto> getSkillDomainDetails(SkillsDomainsDto id) throws Exception {

        List<SkillsDomainsDto> list = new ArrayList<SkillsDomainsDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("SkillsDomainsMap.getSkillDomainDetails", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getSkillDomainCount(SkillsDomainsDto dto) {
        int Count = 0;
        try {
            Count = (Integer) getSqlMapClientTemplate().queryForObject("SkillsDomainsMap.getSkillDomainCount", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Count;
    }

    public List<SkillsDomainsDto> getSubSbu(SkillsDomainsDto parentId) {
        List<SkillsDomainsDto> subsbu = null;
        try {
            subsbu = getSqlMapClientTemplate().queryForList("SkillsDomainsMap.getSubSbu", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subsbu;
    }

    public List<SkillsDomainsDto> getSbu(SkillsDomainsDto parentId) {
        List<SkillsDomainsDto> sbu = null;
        try {
            sbu = getSqlMapClientTemplate().queryForList("SkillsDomainsMap.getSbu", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbu;
    }

    public List<SkillsDomainsDto> getSkillDomainDataXL(SkillsDomainsDto id) {

        List<SkillsDomainsDto> list = new ArrayList<SkillsDomainsDto>();
        try {
            list = getSqlMapClientTemplate().queryForList("SkillsDomainsMap.getSkillDomainDataXL", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
