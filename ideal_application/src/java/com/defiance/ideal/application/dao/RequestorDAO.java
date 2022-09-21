/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.RequestorDTO;
import java.util.List;

/**
 *
 * @author 14355
 */
public interface RequestorDAO {
    public  List<RequestorDTO> getReferenceNo();
    public  void insertNewRequest();
  public  void insertNewQuality();
    public List<RequestorDTO> qualityCustomerList();
}
