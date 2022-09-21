/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.ApprovalDTO;
import java.util.List;

/**
 *
 * @author 16364
 */
public interface ApprovalDAO {
     public List<ApprovalDTO> getProjectList(ApprovalDTO empDet);
      public List<ApprovalDTO> filterDataList(ApprovalDTO appDTO);
         public List<ApprovalDTO> getPrjDetails(ApprovalDTO appDTO);
}
