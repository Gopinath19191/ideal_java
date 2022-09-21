/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.HrPersonalDto;
import java.util.List;

/**
 *
 * @author 8000458
 */
public interface HrPersonalService {

    public int getPersonalCount(HrPersonalDto dto);

    public List<HrPersonalDto> getPersonalDetails(HrPersonalDto id) throws Exception;

    public List<HrPersonalDto> getSbu(HrPersonalDto parentId);

    public List<HrPersonalDto> getSubSbu(HrPersonalDto parentId);

    public List<HrPersonalDto> getPersonalDataXL(HrPersonalDto id);
}
