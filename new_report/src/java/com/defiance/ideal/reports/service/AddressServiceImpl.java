/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.AddressDaoImpl;
import com.defiance.ideal.reports.dto.AddressDataDTO;
import com.defiance.ideal.reports.dto.AddressFilterDTO;
import java.util.List;

/**
 *
 * @author 8000246
 */
public class AddressServiceImpl implements AddressService{
  AddressDaoImpl addressImpl;  

    public AddressDaoImpl getAddressImpl() {
        return addressImpl;
    }

    public void setAddressImpl(AddressDaoImpl addressImpl) {
        this.addressImpl = addressImpl;
    }

   
  public List<AddressDataDTO> getAddressRecord(AddressFilterDTO filterData){
        List<AddressDataDTO> dataDto=addressImpl.getAddressRecords(filterData);
        return dataDto;
    }
  public List<AddressDataDTO> getSearchList(String empval){
        return addressImpl.getSearchList(empval);
    }
  
}
