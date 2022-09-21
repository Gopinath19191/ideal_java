/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dto.ECardDto;
import com.defiance.ideal.dto.EmpEngagementDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 16221
 */
public interface ECardService {
    
    public List<ECardDto> getCardLists();
    public List<ECardDto> getEmployeeSearch(String val);
    public int giveEcard(ECardDto filterData);
    public String generateFile(ECardDto filterData);
    public List<ECardDto> getGivenCardLists(String val);
    public List<ECardDto> getReceivedCardLists(String val);
    public ECardDto getEcardDetails(String card_id);
    public String getMailTo(String id);
    public String getMailCcList(String id);
    public String getMailCc(String id);
    public ArrayList<ECardDto> getMailCredentials();
    public List<ECardDto> getEcardReport(ECardDto filterData);
    public List<ECardDto> getUnitName();
    public List<EmpEngagementDto> getAreaofInterest();
    public List<EmpEngagementDto> getInterestLevel();
    public List<EmpEngagementDto> getCommitmentLevel();
    public int submitEmployeeEngagement(EmpEngagementDto filterData);
    public EmpEngagementDto getLastSubmittedData(String employee_id);
    public List<EmpEngagementDto> getInterestLists(String ee_id);
    
}
