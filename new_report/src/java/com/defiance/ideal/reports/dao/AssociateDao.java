/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import java.util.List;

/**
 *
 * @author 14517
 */
public interface  AssociateDao {
    public List<AssociateFilterDTO> fetchAssociate(AssociateDataDTO dto);
    public int fetchAssociateListCount(AssociateDataDTO dto);
}
