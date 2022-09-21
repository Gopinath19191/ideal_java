/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.RollOutDTO;
import java.util.List;

/**
 *
 * @author 14578
 */
public interface RollOutDao {

    public List<RollOutDTO> getRollOutreport(RollOutDTO formData);
    public List<RollOutDTO> getprojectList();
    public List<RollOutDTO> getSbuList(RollOutDTO sbuId);
    public List<RollOutDTO> getPmNameList();
    public List<RollOutDTO> getsubSbuList(String sbuId);

    

}
