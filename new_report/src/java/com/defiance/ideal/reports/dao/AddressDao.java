/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.AddressDataDTO;
import com.defiance.ideal.reports.dto.AddressFilterDTO;
import java.util.List;

/**
 *
 * @author 8000246
 */
public interface AddressDao {
   public List<AddressDataDTO> getAddressRecords(AddressFilterDTO filterData);
   public List<AddressDataDTO> getSearchList(String empVal);
}
