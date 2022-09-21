/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.AddressDataDTO;
import com.defiance.ideal.reports.dto.AddressFilterDTO;
import java.util.List;

/**
 *
 * @author 8000246
 */
public interface AddressService {
    public List getAddressRecord(AddressFilterDTO filterData);
    public List<AddressDataDTO> getSearchList(String empval);
}
