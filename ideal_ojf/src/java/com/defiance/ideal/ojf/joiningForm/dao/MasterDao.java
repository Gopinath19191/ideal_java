/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.joiningForm.dao;

import com.defiance.ideal.ojf.joiningForm.dto.MasterDataDTO;
import com.defiance.ideal.ojf.joiningForm.dto.SourcehireDTO;
import com.defiance.ideal.ojf.joiningForm.dto.VenderIdNameDTO;
import java.util.List;

/**
 *
 * @author 15858
 */
public interface MasterDao {

      public List<MasterDataDTO> getvendorDetaillist();
      public String checkexistsvenname(String vendname);
      public void savevendorinfo(String vendorname);
      public void updatepartvendorinfo(VenderIdNameDTO venderIdName );
      public  List<MasterDataDTO> getpartvendetails(String venid);
      public void deletepartvendorinfo(String venid);
      
      public List<MasterDataDTO> getStructureDetails(int parentId);
      public List<MasterDataDTO> getBandDetails(String parentId);
      public List<MasterDataDTO> getDesignationDetails();
      public List<MasterDataDTO> getPracticeGroup(String structureId);
      public List<MasterDataDTO> getSubPracticeGroup(String practiceGroupId);
      public List<MasterDataDTO> getEmployeeDetailsFromId(String reportingManager);
      public List<SourcehireDTO> getsourcehirelisst(String sorceid);
      public String getportalidbyname(String portalname);
      public List<SourcehireDTO> getjobportallist(String portalid);
       public List<MasterDataDTO> getMasterDataList(String configId);
       public List<MasterDataDTO> getCmpLocationList();
    
     
}
