/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.SkillsDomainsDao;
import com.defiance.ideal.reports.dto.SkillsDomainsDto;
import java.util.List;

/**
 *
 * @author 8000458
 */
public class SkillsDomainsServiceImpl implements SkillsDomainsService {

    SkillsDomainsDao dao;

    public SkillsDomainsDao getDao() {
        return dao;
    }

    public void setDao(SkillsDomainsDao dao) {
        this.dao = dao;
    }

    public int getSkillDomainCount(SkillsDomainsDto dto) {
        int momRecordCount = 0;
        try {
            momRecordCount = dao.getSkillDomainCount(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return momRecordCount;
    }

    public List<SkillsDomainsDto> getSkillDomainDetails(SkillsDomainsDto id) throws Exception {
        return dao.getSkillDomainDetails(id);
    }

    public List<SkillsDomainsDto> getSbu(SkillsDomainsDto parentId) {
        return dao.getSbu(parentId);
    }

    public List<SkillsDomainsDto> getSubSbu(SkillsDomainsDto parentId) {
        return dao.getSubSbu(parentId);
    }

    public List<SkillsDomainsDto> getSkillDomainDataXL(SkillsDomainsDto id) {
        return dao.getSkillDomainDataXL(id);
    }
}
