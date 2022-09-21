/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.SkillsDomainsDto;
import java.util.List;

/**
 *
 * @author 8000458
 */
public interface SkillsDomainsService {

    public List<SkillsDomainsDto> getSkillDomainDetails(SkillsDomainsDto id) throws Exception;

    public int getSkillDomainCount(SkillsDomainsDto dto);

    public List<SkillsDomainsDto> getSbu(SkillsDomainsDto parentId);

    public List<SkillsDomainsDto> getSubSbu(SkillsDomainsDto parentId);

    public List<SkillsDomainsDto> getSkillDomainDataXL(SkillsDomainsDto id);
}
