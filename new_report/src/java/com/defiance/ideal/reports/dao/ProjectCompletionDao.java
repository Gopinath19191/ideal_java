/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.ProjectCompletionDataDTO;
import com.defiance.ideal.reports.dto.ProjectCompletionFilterDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 14517
 */
public interface ProjectCompletionDao {
    public List<ProjectCompletionDataDTO> fetchProjectCompletionReport(ProjectCompletionFilterDTO filterData);

    public Map<String, String> getSbuList();

}
