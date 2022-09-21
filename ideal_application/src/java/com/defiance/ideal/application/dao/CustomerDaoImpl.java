/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.dao;

import com.defiance.ideal.application.dto.CustomerAddDTO;
import com.defiance.ideal.application.dto.CustomerDataDTO;
import com.defiance.ideal.application.dto.CustomerGroupDTO;
import com.defiance.ideal.application.util.CommonConfigurations;

import com.defiance.ideal.application.util.MailMessages;
import com.defiance.ideal.application.util.SendMail;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapTransactionManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 *
 * @author 14053
 */
public class CustomerDaoImpl extends SqlMapClientDaoSupport implements CustomerDao {

    public String addFileDb(String fileName, String fileType, String referenceName, int referenceId, String moduleName) {
        String lastFileInsertId = null;
        HashMap map = new HashMap();
        map.put("fileName", fileName);
        map.put("fileType", fileType);
        map.put("referenceName", referenceName);
        map.put("referenceId", referenceId);
        map.put("moduleName", moduleName);
        lastFileInsertId = (String) getSqlMapClientTemplate().insert("CustomerMap.addFileDb", map);

        return lastFileInsertId;
    }
    private String sbuId;
    private SqlMapTransactionManager sqlMaptransaction, sqlMaptransaction1, sqlMaptransaction2;

    public Map<String, String> getSbuList() {
        Map<String, String> sbuList = new LinkedHashMap<String, String>();
        try {
            sbuList = getSqlMapClientTemplate().queryForMap("CustomerMap.getSbuList", sbuId, "sbuId", "sbuName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
    }

    public List<CustomerDataDTO> fetchCustomerData(CustomerDataDTO filterData) {
        List<CustomerDataDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchCustomerData", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerDataDTO> fetchActiveInactiveCustomerData(CustomerDataDTO filterData) {
        List<CustomerDataDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchActiveInactiveCustomerData", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerDataDTO> fetchCustomerDataForExcel(CustomerDataDTO filterData) {
        List<CustomerDataDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchCustomerDataForExcel", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public String getCustomerActivatingCode(String customerId) {
        String template = null;

        try {
            template = (String) getSqlMapClientTemplate().queryForObject("CustomerMap.getCustomerActivatingCode", customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerDataDTO> fetchCustomerApproveData(CustomerDataDTO filterData) {
        List<CustomerDataDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchCustomerApproveData", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerAddDTO> fetchSelectedCustomer(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchSelectedCustomer", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerAddDTO> getMaxCustomerCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerCode = null;

        try {
            customerCode = getSqlMapClientTemplate().queryForList("CustomerMap.getMaxCustomerCode", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerCode;
    }

    public List<CustomerAddDTO> getDivisionCount(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerDivisionCount = null;

        try {
            customerDivisionCount = getSqlMapClientTemplate().queryForList("CustomerMap.getDivisionCount", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerDivisionCount;
    }

    public List<CustomerAddDTO> getCustomerCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerCode = null;

        try {
            customerCode = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerCode", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerCode;
    }

    public List<CustomerAddDTO> getCustomerDivisionApprovedCount(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerCode = null;

        try {
            customerCode = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerDivisionApprovedCount", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerCode;
    }

    public List<CustomerAddDTO> getCustomerCodeMaxLike(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerCode = null;

        try {
            customerCode = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerCodeMaxLike", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerCode;
    }

    public List<CustomerAddDTO> fetchSelectedParent(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchSelectedParent", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;

    }

    public List<CustomerAddDTO> fetchSelectedCustomerDivisions(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;

        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchSelectedCustomerDivisions", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerAddDTO> fetchBillingAddress(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;
        HashSet country = new HashSet();

        HashMap<String, List<CustomerAddDTO>> map = new HashMap<String, List<CustomerAddDTO>>();
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchBillingAddress", filterData);
            Iterator itr = template.iterator();
            int i = 0;
            while (itr.hasNext()) {
                CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                System.out.println("test------------->" + dto.getCustomerAddress());
                System.out.println("test------------->" + dto.getCountry());
                System.out.println("test------------->" + dto.getCity());
                System.out.println("test------------->" + dto.getRegion());
                //System.out.println("test------------->"+dto.getPincode());
                country.add(dto.getCountry());
            }
            System.out.println("test-ccc------>" + country);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerAddDTO> fetchCustomerWorkLocations(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchCustomerWorkLocations", filterData);
            Iterator itr = template.iterator();
            while (itr.hasNext()) {
                CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                System.out.println("test1------------->" + dto.getCustomerAddress());
                System.out.println("test1------------->" + dto.getCountry());
                System.out.println("test1------------->" + dto.getCity());
                System.out.println("test1------------->" + dto.getRegion());
                //System.out.println("test1------------->"+dto.getPincode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerAddDTO> fetchCustomerContactDetails(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchBusinessContactDetails", filterData);
            Iterator itr = template.iterator();
            while (itr.hasNext()) {
                CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                System.out.println("te------------>" + dto.getContactPerson());
                System.out.println("te------------->" + dto.getContactPhone());
                System.out.println("te------------->" + dto.getContactEmail());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }

    public List<CustomerAddDTO> fetchCustomerFinanceContactDetails(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchFinanceContactDetails", filterData);
            Iterator itr = template.iterator();
            while (itr.hasNext()) {
                CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                System.out.println("te------------>" + dto.getContactPerson());
                System.out.println("te------------->" + dto.getContactPhone());
                System.out.println("te------------->" + dto.getContactEmail());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;

    }
    
    public List<CustomerAddDTO> fetchCustomerDunningContactDetails(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchDunningContactDetails", filterData);
            Iterator itr = template.iterator();
            while (itr.hasNext()) {
                CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                System.out.println("te------------>" + dto.getContactPerson());
                System.out.println("te------------->" + dto.getContactPhone());
                System.out.println("te------------->" + dto.getContactEmail());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;

    }

    public List<CustomerAddDTO> fetchWorkLocationShortCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchWorkLocationShortCode", filterData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public List<CustomerAddDTO> fetchBillingShortCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> template = null;
        try {
            template = getSqlMapClientTemplate().queryForList("CustomerMap.fetchBillingShortCode", filterData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    public void customersAdd(CustomerAddDTO employeeFormData) {
        try {
            if (employeeFormData.getCustomerID() == null || employeeFormData.getCustomerID().equals("")) {
                getSqlMapClientTemplate().insert("EmployeeDetails.insertExitEmpDetails", employeeFormData);
            } else {
                getSqlMapClientTemplate().update("EmployeeDetails.updateExitEmpDetails", employeeFormData);
            }// Condtion to check the mail part
            if (employeeFormData.getCustomerSubmitButton() != null) {
                // Here Mail Code has to come
//                String[] toMailApprovalModules = {Integer.toString(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID)};
//                this.triggerMail(rmModuleList, toMailApprovalModules, employeeFormData, "empToRM", "empToRM", "empToRM", CommonConfigurations.rmModuleName, employeeFormData.getEmpId());
                List<String> approvalMails = getApproverMailId();
                String[] toMailApprovalMails = {approvalMails.get(0)};
                this.triggerMail(toMailApprovalMails, employeeFormData, "customerApproved", "Approve", employeeFormData.getSalesPersonId());
                System.out.println("triggered success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CustomerDataDTO> getCustomerGroup() {
        List<CustomerDataDTO> customerGroup = null;
        try {
            customerGroup = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerGroup");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerGroup;
    }

    public String getCustomerGroupName(String val) {
        String customerGroupName = (String) getSqlMapClientTemplate().queryForObject("CustomerMap.getCustomerGroupName", val);
        return customerGroupName;
    }

    public String getSalesPersonName(String val) {
        String customerGroupName = (String) getSqlMapClientTemplate().queryForObject("CustomerMap.getSalesPersonNameWithID", val);
        return customerGroupName;
    }

//    public String getAttachmentTypeVal(String val) {
//        String attachmentTypeVal = (String) getSqlMapClientTemplate().queryForObject("CustomerMap.getSalesPersonNameWithID", val);
//        return attachmentTypeVal;
//    }
    public List<CustomerAddDTO> getCountryName(String Val) {
        List<CustomerAddDTO> country = null;
        try {
            country = getSqlMapClientTemplate().queryForList("CustomerMap.getcountryNameBasedId", Val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    public List<CustomerAddDTO> getSalesPersonRef(CustomerAddDTO filterData) {
        List<CustomerAddDTO> salesPerson = null;
        try {
            salesPerson = getSqlMapClientTemplate().queryForList("CustomerMap.getSalesPersonRef", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesPerson;
    }

    public List<CustomerDataDTO> getSalesPersonRefId() {
        List<CustomerDataDTO> salesPerson = null;
        try {
            salesPerson = getSqlMapClientTemplate().queryForList("CustomerMap.getSalesPersonRefId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesPerson;
    }

    public List<CustomerAddDTO> getCurrencyList() {
        List<CustomerAddDTO> currencyList = null;
        try {
            currencyList = getSqlMapClientTemplate().queryForList("CustomerMap.getCurrencyList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyList;
    }

    public List<CustomerDataDTO> fetchCustomerActiveInactive() {
        List<CustomerDataDTO> activeInactiveList = null;
        try {
            activeInactiveList = getSqlMapClientTemplate().queryForList("CustomerMap.fetchCustomerActiveInactive");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activeInactiveList;
    }
//     public List<CustomerAddDTO> getCustomerGroupList(){
//         List<CustomerAddDTO> customerGroupList = null;
//        try {
//            customerGroupList = getSqlMapClientTemplate().queryForList("CustomerMap.getCurrencyList");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return customerGroupList;
//     }

    public List<CustomerAddDTO> getBusinessLeader() {
        List<CustomerAddDTO> businessLeader = null;
        try {
            String bandId = CommonConfigurations.bandId;
            System.out.println("bandId is:::" + bandId);
            businessLeader = getSqlMapClientTemplate().queryForList("CustomerMap.getBusinessLeader", bandId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return businessLeader;
    }

    public List<CustomerAddDTO> getRBUList() {
        List<CustomerAddDTO> rbuList = null;
        try {
            rbuList = getSqlMapClientTemplate().queryForList("CustomerMap.getRBUList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rbuList;
    }

    public List<CustomerAddDTO> getSBUList() {
        List<CustomerAddDTO> sbuList = null;
        try {
            sbuId = CommonConfigurations.SBU;
            sbuList = getSqlMapClientTemplate().queryForList("CustomerMap.getSBUList", sbuId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
    }

    public List<CustomerAddDTO> getCountryList() {
        List<CustomerAddDTO> countryList = null;
        try {
            countryList = getSqlMapClientTemplate().queryForList("CustomerMap.getCountryList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public List<CustomerAddDTO> getRegionList(CustomerAddDTO filterData) {
        List<CustomerAddDTO> regionList = null;
        try {
            regionList = getSqlMapClientTemplate().queryForList("CustomerMap.getRegionList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public List<CustomerAddDTO> getRegionListFull(CustomerAddDTO filterData) {
        List<CustomerAddDTO> regionList = null;
        try {
            regionList = getSqlMapClientTemplate().queryForList("CustomerMap.getRegionListFull", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public List<CustomerAddDTO> getCityList(CustomerAddDTO filterData) {
        List<CustomerAddDTO> cityList = null;
        try {
            cityList = getSqlMapClientTemplate().queryForList("CustomerMap.getCityList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public String getGstCode(CustomerAddDTO filterData) {
        String gstCode = null;
        try {
            gstCode = (String) getSqlMapClientTemplate().queryForObject("CustomerMap.getGstCode", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gstCode;
    }

    public String getSalesPersonPhoneNumber(CustomerAddDTO filterData) {
        String PhoneNumber = null;
        try {
            PhoneNumber = (String) getSqlMapClientTemplate().queryForObject("CustomerMap.getSalesPersonPhoneNumber", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PhoneNumber;
    }

    public List<CustomerAddDTO> getsubRBUList(CustomerAddDTO filterData) {
        List<CustomerAddDTO> subRBUList = null;
        try {
            subRBUList = getSqlMapClientTemplate().queryForList("CustomerMap.getsubRBUList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subRBUList;
    }

    public List<CustomerDataDTO> customerProjectMapping(CustomerDataDTO filterData) {
        List<CustomerDataDTO> projectList = null;
        try {
            projectList = getSqlMapClientTemplate().queryForList("CustomerMap.getcustomerProjectMapping", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<CustomerAddDTO> getConfigValueData(String parentId) {
        List<CustomerAddDTO> configList = null;
        try {
            configList = getSqlMapClientTemplate().queryForList("CustomerMap.getConfigValueData", parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configList;
    }

    public List<CustomerAddDTO> getDuplicateCustomerName(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerList = null;
        try {
            customerList = getSqlMapClientTemplate().queryForList("CustomerMap.getDuplicateCustomerName", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public List<CustomerAddDTO> getDuplicateCustomerDivisionName(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerList = null;
        try {
            customerList = getSqlMapClientTemplate().queryForList("CustomerMap.getDuplicateCustomerDivisionName", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public List<CustomerAddDTO> fetchInvoiceCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> cityList = null;
        try {
            cityList = getSqlMapClientTemplate().queryForList("CustomerMap.getInvoiceCode", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public void insertCustomerInfoHistory(CustomerAddDTO customerData) {
        System.out.println("inside Customer Info History>>>>>>>>>>>>>>");
        try {
            getSqlMapClientTemplate().insert("CustomerMap.insertCustomerInfoHistory", customerData);
            System.out.println("insert sucessfully");
        } catch (Exception e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String insertCustomerDetails(CustomerAddDTO customerData) {
        String lastInsertId = null;
        System.out.println("inside insert customer details");
        try {
            lastInsertId = (String) getSqlMapClientTemplate().insert("CustomerMap.insertCustomerDetails", customerData);
//                
        } catch (Exception e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return lastInsertId;
    }

//    public String insertCustomerDivision(CustomerAddDTO customerData) {
//        String lastInsertId = null;
//        try
//            {
//                System.out.println("division>>>>>"+customerData.getCustomerDivisionListName());
//                System.out.println("length>>>>>>>"+customerData.getCustomerDivisionListName().length);
//                if(customerData.getCustomerDivisionListName()!=null){
//                    for(int i=0;i<customerData.getCustomerDivisionListName().length;i++){
//                        customerData.setCustomerDivisionName(customerData.getCustomerDivisionListName()[i]);
//                        lastInsertId = (String) getSqlMapClientTemplate().insert("CustomerMap.insertCustomerDivisionDetails", customerData);
//                        //customerData.setDivisionId(lastInsertId);
//                    }
//                }else{
//                    customerData.setDivisionId("");
//                }
//            }catch(Exception e){
//                Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
//            }
//         return lastInsertId;
//    }
    public void approveCustomerDetails(CustomerAddDTO customerData) {
        try {
            //customerData.setSubmitDate(CommonFunctions.getSystemDate(CommonConfigurations.MYSQL_DATE_INSERT));
            System.out.println(":DAO IMPL::" + customerData.getCustomerApproveButton() + "::SUBMIT::" + customerData.getCustomerRejectButton());
            if (customerData.getCustomerID() != null) {
                if (customerData.getCustomerApproveButton() != null) {
                    System.out.println("::CUSTOMERCODE::" + customerData.getCustomerCode());
                    if ((customerData.getCustomerCode() == null || customerData.getCustomerCode().equals("")) && (customerData.getStatus().equals("m"))) {
//                        List<CustomerAddDTO> legalEntityid = getSqlMapClientTemplate().queryForList("CustomerMap.getlegalEntityId",customerData);
//                        System.out.println("::INVOICE ID::"+legalEntityid.get(0).getLegalInvoiceCodeId());
//                        customerData.setLegalInvoiceCodeId(legalEntityid.get(0).getLegalInvoiceCodeId());
//                        System.out.println("::LL ID"+(customerData.getLegalInvoiceCodeId()));
                        List<CustomerAddDTO> customerCodeId = getSqlMapClientTemplate().queryForList("CustomerMap.getMaxCustomerCode", customerData);
                        System.out.println(customerCodeId + "::MAXID ::" + customerCodeId.get(0).getCustomerMaxId() + ":COND:");
                        String customerMaxId = null;
                        if (customerCodeId.get(0).getCustomerMaxId() == null || customerCodeId.get(0).getCustomerMaxId().equals("")) {
                            customerCodeId = getSqlMapClientTemplate().queryForList("CustomerMap.getFirstCustomerCode", customerData);
                            System.out.println("gggg>>>" + customerCodeId);
//                            System.out.println("::MAXID IN IF ::"+customerCodeId.get(0).getCustomerMaxId());
                            //customerMaxId = "C00001";
                            customerMaxId = CommonConfigurations.customerCode;
                        } else {
                            /**
                             * ********C suffix code generation*************
                             */
//                             String customerMaxTempId = null;
//                            customerMaxTempId = customerCodeId.get(0).getCustomerMaxId();
//                            System.out.println("customerMaxTempId....................."+customerMaxTempId+"customerCodeId....."+customerCodeId);
//                             String[] temp = customerMaxTempId.split("[a-zA-Z]");
//                            String[] tempNum = customerMaxTempId.split("\\d");
//                            System.out.println("length of temp number "+tempNum.length+"temp...."+temp);
//                            Integer customerCodeInt = Integer.parseInt(temp[1])+1;
//                            System.out.println("customerCodeInt....."+customerCodeInt);
//                            String outputCustomerCode = Integer.toString(customerCodeInt);
//                            System.out.println("outputCustomerCode........"+outputCustomerCode);
//                            while (outputCustomerCode.length() < 5) outputCustomerCode = "0" + outputCustomerCode;
//                            customerMaxId = tempNum[0] + outputCustomerCode;
//                            System.out.println("customerMaxId....."+customerMaxId);
//                            System.out.println(":1 len:"+temp.length+":2 len:"+tempNum+":FIRST:"+temp[1]+":SEC:"+tempNum+":CALC:"+customerCodeInt+"::FINAL:"+outputCustomerCode);
                            /**
                             * **************1 suffix code generation ************
                             */
                            String customerMaxTempId = null;
                            customerMaxTempId = customerCodeId.get(0).getCustomerMaxId();
                            System.out.println("new code....................." + customerMaxTempId);
                            System.out.println("fffffff......." + customerMaxTempId.charAt(0) + customerMaxTempId.replace(customerMaxTempId.charAt(0), '1'));

//                            String suf1 = customerMaxTempId.replace(customerMaxTempId.charAt(0),'1');
//                            System.out.println("length of temp "+suf1);
//                            String[] tempNum = customerMaxTempId.split("\\d");
//                            System.out.println("length of temp number "+tempNum.length);
                            Integer customerCodeInt = Integer.parseInt(customerMaxTempId) + 1;
                            System.out.println("customerCodeInt new ....." + customerCodeInt);
                            String outputCustomerCode = Integer.toString(customerCodeInt);
                            System.out.println("outputCustomerCode......." + outputCustomerCode);
                            while (outputCustomerCode.length() < 5) {
                                outputCustomerCode = "0" + outputCustomerCode;
                            }
                            customerMaxId = outputCustomerCode;
                            System.out.println("jjjjjjjjj new code" + customerMaxId);
                            System.out.println(":1 len:" + ":2 len:" + ":FIRST:" + ":CALC:" + customerCodeInt + "::FINAL:" + outputCustomerCode);
                        }
                        List<CustomerAddDTO> customerDivisionCount = getSqlMapClientTemplate().queryForList("CustomerMap.getDivisionCount", customerData);
                        System.out.println("div count.........." + customerDivisionCount + "count check...." + customerDivisionCount.get(0).getCustomerDivision());
                        if (Integer.parseInt(customerDivisionCount.get(0).getCustomerDivision()) > 1) {
                            System.out.println("inside main loop");
                            List<CustomerAddDTO> customerCodeName = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerCode", customerData);
                            List<CustomerAddDTO> customerDivisionApproved = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerDivisionApprovedCount", customerData);
                            System.out.println("count>>>>>>>>>>>" + Integer.parseInt(customerDivisionCount.get(0).getCustomerDivision()) + "code>>>>>>>" + customerCodeName.get(0).getCustomerCode());
                            if (Integer.parseInt(customerDivisionApproved.get(0).getCustomerDivision()) > 0) {
                                System.out.println("inside main loop greater than 0");
                                customerData.setCustomerCodelike(customerCodeName.get(0).getCustomerCode());
                                List<CustomerAddDTO> customerMaxLength = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerCodeMaxLike", customerData);
                                String[] splitCode = customerMaxLength.get(0).getCustomerCode().split("-");
                                System.out.println("splitcode>>" + splitCode[0]);
                                System.out.println("splitcode length" + splitCode.length);
                                if (splitCode.length == 1) {
                                    customerMaxId = customerCodeName.get(0).getCustomerCode();
                                    System.out.println("splitCode>>>>>>>" + customerMaxId);
                                } else {
                                    System.out.println("inside main loop greater than 0");
//                                    customerMaxId = customerCodeName.get(0).getCustomerCode()+"-"+(Integer.parseInt(customerDivisionApproved.get(0).getCustomerDivision())+1);
                                    customerMaxId = customerCodeName.get(0).getCustomerCode();
                                    System.out.println("splitCode1>>>>>>>" + customerMaxId);
                                }
                            } else {

                                /**
                                 * ********C suffix code generation*************
                                 */
                                System.out.println("else of main div");
                                String customerMaxTempId = null;
                                if (customerCodeName.get(0).getCustomerCode() != null) {
                                    System.out.println("existing code");
                                    customerMaxTempId = customerCodeName.get(0).getCustomerCode();
                                    customerMaxId = customerMaxTempId;
                                } else {
                                    System.out.println("customer code equal to null");
                                    customerMaxTempId = customerCodeId.get(0).getCustomerMaxId();
                                    System.out.println("customerMaxTempId>>>> div" + customerMaxTempId);
                                    Integer customerCodeInt = Integer.parseInt(customerMaxTempId) + 1;
                                    System.out.println("customerCodeInt>>>>>div" + customerCodeInt);
                                    String outputCustomerCode = Integer.toString(customerCodeInt);
                                    while (outputCustomerCode.length() < 5) {
                                        outputCustomerCode = "0" + outputCustomerCode;
                                    }
                                    customerMaxId = outputCustomerCode;
                                    customerMaxId = customerMaxId;
                                }
//                                customerMaxTempId = customerCodeId.get(0).getCustomerMaxId();
//                                System.out.println("customerMaxTempId>>>> div"+customerMaxTempId);
////                                String[] temp = customerMaxTempId.split("[a-zA-Z]");
////                                System.out.println("length of temp "+temp.length);
//                                //String suf1 = customerMaxTempId.replace(customerMaxTempId.charAt(0),'1');
////                                String[] tempNum = customerMaxTempId.split("\\d");
////                                System.out.println("length of temp number "+tempNum.length);
////                                Integer customerCodeInt = Integer.parseInt(temp[1])+1;
//                                Integer customerCodeInt = Integer.parseInt(customerMaxTempId)+1;
//                                System.out.println("customerCodeInt>>>>>div"+customerCodeInt);
//                                String outputCustomerCode = Integer.toString(customerCodeInt);
//                                while (outputCustomerCode.length() < 5) outputCustomerCode = "0" + outputCustomerCode;
//                                //customerMaxId = tempNum[0] + outputCustomerCode;
//                                customerMaxId = outputCustomerCode;
//                                customerMaxId = customerMaxId+"-1";
                            }
                        }

                        //                    for(int i =0; i < tempNum.length ; i++) {
                        //                        System.out.println(":SECOND:"+tempNum[i]);
                        //                    }
                        System.out.println("::::CUST MAX Res ID:::" + customerMaxId);
                        customerData.setCustomerCode(customerMaxId);
                    }
                }
            }
            customerData.setSalesPerson(customerData.getSalesPerson());
            customerData.setCustomerName(customerData.getCustomerName());
            if (customerData.getCustomerApproveButton() != null) {
                customerData.setStatus(CommonConfigurations.CUSTOMER_APPROVED_STATUS);
            } else if (customerData.getCustomerRejectButton() != null) {
                customerData.setStatus(CommonConfigurations.CUSTOMER_REJECTED_STATUS);
            }// To check the new record or existing
            if (customerData.getCustomerID() != null) {
                getSqlMapClientTemplate().insert("CustomerMap.approveCustomerDetails", customerData);
            }
            // Condtion to check the mail part
            System.out.println("sales person id>>>>>> " + customerData.getSalesPerson() + ">>>>>>>>" + customerData.getCustomerName());
            if (customerData.getCustomerApproveButton() != null) {
                // Here Mail Code has to come
                List<String> approvalMails = getApproverMailId();
                String[] toMailApprovalMails = {approvalMails.get(0)};
                this.triggerMail(toMailApprovalMails, customerData, "customerApproved", "Approve", customerData.getSalesPerson());
            } else if (customerData.getCustomerRejectButton() != null) {
                List<String> approvalMails = getApproverMailId();
                String[] toMailApprovalMails = {approvalMails.get(0)};
                System.out.println(":SALES:" + customerData.getSalesPersonId());
                this.triggerMail(toMailApprovalMails, customerData, "customerRejected", "Reject", customerData.getSalesPerson());
            }
        } catch (Exception e) {
        }
    }

    public void rmgApproveCustomerDetails(CustomerAddDTO customerData) {
        try {
//            System.out.println(":DAO IMPL::"+customerData.getCustomerApproveButton()+"::SUBMIT::"+customerData.getCustomerRejectButton());
//            if(customerData.getCustomerID() != null) {
//                getSqlMapClientTemplate().insert("CustomerMap.rmgApproveCustomerDetails", customerData);
//            }
        } catch (Exception e) {
        }
    }

    public List<CustomerAddDTO> getCustomerStatus(CustomerAddDTO filterData) {
        List<CustomerAddDTO> status = null;
        try {
            status = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerStatus", filterData);
        } catch (Exception e) {
        }
        return status;
    }

    public void insertCustomerWorkDetails(CustomerAddDTO customerData) {
        try {
            //getSqlMapClientTemplate().insert("CustomerMap.insertCustomerWorkLocation", customerData);
            getSqlMapClientTemplate().insert("CustomerMap.insertCustomerAddress", customerData);
        } catch (Exception e) {
        }
    }

    public void insertCustomerBillingDetails(CustomerAddDTO customerData) {
        try {
            getSqlMapClientTemplate().insert("CustomerMap.insertCustomerBillingAddress", customerData);
        } catch (Exception e) {
        }
    }

    public void triggerMail(String[] toMailApprovalModules, CustomerAddDTO customerData,
            String mailSubReason, String mailProcessReason, String resEmpId) {
        MailMessages mailMsg = new MailMessages();
        String mailTo = "";
        String mailCc = "";
        String mailSubject = "";
        String mailMessage = "";
        List mailToApprovalIds = new ArrayList();
        List mailCcApprovalIds = new ArrayList();
        try {
            System.out.println("inside mail triggering " + resEmpId);
            SendMail mailObj = new SendMail();
            CustomerAddDTO getCustomerDetails = (CustomerAddDTO) getSqlMapClientTemplate().queryForObject("CustomerMap.getEmailAddress", resEmpId);
            System.out.println("getCustomerDetails : " + getCustomerDetails);
            CustomerAddDTO getCountryDet = new CustomerAddDTO();
            CustomerAddDTO getSalesPersonDet = new CustomerAddDTO();
            if (mailProcessReason.equals("submit")) {
                getSalesPersonDet = (CustomerAddDTO) getSqlMapClientTemplate().queryForObject("CustomerMap.getSalesPersonName", customerData.getSalesPerson());
                getCountryDet = (CustomerAddDTO) getSqlMapClientTemplate().queryForObject("CustomerMap.getcountryName", customerData.getCustomerID());
            } else {
                System.out.println("custoer name" + customerData.getCustomerName() + "   " + customerData.getSalesPerson());
                getSalesPersonDet = (CustomerAddDTO) getSqlMapClientTemplate().queryForObject("CustomerMap.getSalesPersonName", customerData.getSalesPerson());
                getCountryDet = (CustomerAddDTO) getSqlMapClientTemplate().queryForObject("CustomerMap.getcountryName", customerData.getCustomerID());
            }
            if (mailProcessReason.equals("submit") || mailProcessReason.equals("Resubmitted") || mailProcessReason.equals("Amended")) {
                mailToApprovalIds.addAll(Arrays.asList(toMailApprovalModules));
                for (Object o : mailToApprovalIds) {
                    mailTo += o.toString();
                    mailTo += ",";
                }
                mailCc = getCustomerDetails.getEmpWorkMail();
                //mailCc = "kiranmayi.atmakuri@hindujatech.com";
            } else if (mailProcessReason.equals("Approve") || mailProcessReason.equals("Reject")) {
                mailTo = getCustomerDetails.getEmpWorkMail();
                // mailTo = "kiranmayi.atmakuri@hindujatech.com";
                mailCcApprovalIds.addAll(Arrays.asList(toMailApprovalModules));
                for (Object o : mailCcApprovalIds) {
                    mailCc += o.toString();
                    mailCc += ",";
                }
            }
            mailSubject = mailMsg.getSubject(mailSubReason, mailProcessReason);
            if (mailProcessReason.equals("submit")) {
//                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, customerData.getCustomerName() + "#~~#" + getCustomerDetails.getEmployeeName()+ "#~~#" +  customerData.getAboutCustomer() +"#~~#" +getCurrencyCodeDet.getCurrencyCode()+"#~~#" +((getCountryDet == null)?"":getCountryDet.getCountryName()));
                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, customerData.getCustomerName() + "#~~#" + getSalesPersonDet.getSalesPersonName() + "#~~#" + getCountryDet.getCountryName());
            } else if (mailProcessReason.equals("Approve")) {
//                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, customerData.getCustomerName() + "#~~#" + getCustomerDetails.getEmployeeName()+ "#~~#" +getCurrencyCodeDet.getCurrencyCode()+"#~~#" +((getCountryDet == null)?"":getCountryDet.getCountryName())+"#~~#" +customerData.getCustomerCode()+"#~~#" +(getGroupNameDet == null?" ":getGroupNameDet.getCustomerGroupName()));
                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, customerData.getCustomerName() + "#~~#" + getSalesPersonDet.getSalesPersonName() + "#~~#" + customerData.getCustomerCode() + "#~~#" + ((getCountryDet == null) ? "" : getCountryDet.getCountryName()));
            } else if (mailProcessReason.equals("Amended")) {
                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, customerData.getCustomerName() + "#~~#" + getSalesPersonDet.getSalesPersonName() + "#~~#" + ((getCountryDet == null) ? "" : getCountryDet.getCountryName()));

                if (customerData.getChangedData() != null) {
                    mailMessage += "Below are the Following Changes Made by the BDM/Sales person.<BR><BR>"
                            + "<table style='border: 1px solid #808080; border-collapse: collapse;'><tr><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Field </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Old Record </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> New Record </th></tr>";
                    for (Map.Entry<String, List> entry : customerData.getChangedData().entrySet()) {
                        String keyvalue = entry.getKey();
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>" + keyvalue + "  </td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(0) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(1) + "</td></tr>";
                    }
                    mailMessage += "</table><br><br>";
                }
                if (customerData.getNewlyAddedData() != null
                        || customerData.getNewlyAddedfinance() != null
                        || customerData.getNewlyAddedwork() != null
                        || customerData.getNewlyAddedbilling() != null) {
                    mailMessage += "Below are the new details added by the BDM/Sales person.<BR><BR>";
                }
                if (customerData.getNewlyAddedData() != null) {
                    mailMessage += "<table style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>";
                    for (Map.Entry<String, List> entry : customerData.getNewlyAddedData().entrySet()) {
                        String keyvalue = entry.getKey();
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse'><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Field </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Name </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Designation </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Email </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Phone No </th></tr>";
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>" + keyvalue + "  </td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(0) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(1) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(2) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(3) + "</td></tr>";
                    }
                    mailMessage += "</table><br><br>";
                }
                if (customerData.getNewlyAddedfinance() != null) {
                    mailMessage += "<table style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>";
                    for (Map.Entry<String, List> entry : customerData.getNewlyAddedfinance().entrySet()) {
                        String keyvalue = entry.getKey();
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse'><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Field </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Name </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Designation </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Email </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Phone No </th></tr>";
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>" + keyvalue + "  </td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(0) + "</td><td>  " + entry.getValue().get(1) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(2) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(3) + "</td></tr>";
                    }
                    mailMessage += "</table><br><br>";
                }
                if (customerData.getNewlyAddedwork() != null) {
                    mailMessage += "<table style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>";
                    for (Map.Entry<String, List> entry : customerData.getNewlyAddedwork().entrySet()) {
                        String keyvalue = entry.getKey();
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse'><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Field </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Address </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Short Code </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Country </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Region </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> City </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Pincode </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Working Hrs </th></tr>";
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>" + keyvalue + "  </td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(0) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(1) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(2) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(3) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(4) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(5) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(6) + "</td></tr>";
                    }
                    mailMessage += "</table><br><br>";
                }
                if (customerData.getNewlyAddedbilling() != null) {
                    mailMessage += "<table style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>";
                    for (Map.Entry<String, List> entry : customerData.getNewlyAddedbilling().entrySet()) {
                        String keyvalue = entry.getKey();
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse'><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Field </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Address </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Short Code </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Country </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Region </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> City </th><th style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'> Pincode </th></tr>";
                        mailMessage += "<tr style='border: 1px solid #808080; border-collapse: collapse'><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>" + keyvalue + "  </td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(0) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(1) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(2) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(3) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(4) + "</td><td style='border: 1px solid #808080; border-collapse: collapse; padding:5px;'>  " + entry.getValue().get(5) + "</td></tr>";
                    }
                    mailMessage += "</table><br><br>";
                }
                mailMessage += "<br><br>Regards,<BR>"
                        + "Ideal Admin,<BR>http://ideal.hindujatech.com/users/login";
            } else if (mailProcessReason.equals("Resubmitted")) {
                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, customerData.getCustomerName() + "#~~#" + getSalesPersonDet.getSalesPersonName() + "#~~#" + getCountryDet.getCountryName());
            } else {
                //mailMessage = mailMsg.getOthersMailMessage(mailSubReason, customerData.getCustomerName() + "#~~#" + getCustomerDetails.getEmployeeName()+ "#~~#" +getCurrencyCodeDet.getCurrencyCode()+"#~~#" +((getCountryDet == null)?"":getCountryDet.getCountryName())+"#~~#" +customerData.getRemarks());
                mailMessage = mailMsg.getOthersMailMessage(mailSubReason, customerData.getCustomerName() + "#~~#" + getSalesPersonDet.getSalesPersonName() + "#~~#" + getCountryDet.getCountryName() + "#~~#" + customerData.getRemarks());
            }
            System.out.println(":mailSubject:" + mailMessage);
            mailObj.smtpMail(mailTo, mailSubject, mailMessage, mailCc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CustomerAddDTO> getcustomerGroupList() {
        List<CustomerAddDTO> customerGroupList = null;
        try {
            customerGroupList = getSqlMapClientTemplate().queryForList("CustomerMap.getCustomerGroupList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerGroupList;

    }

    @Override
    public int addCustomerDetails(HashMap<String, List<CustomerAddDTO>> data, CustomerAddDTO customerBean) {
        List<CustomerAddDTO> customerContact = data.get("customerContact");
        List<CustomerAddDTO> customerFinanceContact = data.get("customerFinanceContact");
        List<CustomerAddDTO> customerDunningContact = data.get("customerDunningContact");
        List<CustomerAddDTO> customerWorkLoc = data.get("customerWorkLoc");
        List<CustomerAddDTO> customerBilling = data.get("customerBilling");
        String customerId = customerBean.getCustomerID();
        int insertCount = 0;
        if (customerContact != null) {
            if (customerContact.size() > 0) {
                Iterator itr = customerContact.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
//                    System.out.println("n-contact person ===>" + dto.getContactPerson());
//                    System.out.println("n-contact phone===>" + dto.getContactPhone());
//                    System.out.println("n- email===>" + dto.getContactEmail());
                    dto.setContactType("bu");
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().insert("CustomerMap.insertCustomerContact", dto);
                    insertCount++;
                }
            }
        }

        if (customerFinanceContact != null) {
            if (customerFinanceContact.size() > 0) {
                Iterator itr = customerFinanceContact.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
//                    System.out.println("nf-comntact person===>" + dto.getContactPerson());
//                    System.out.println("nf-contact phone ===>" + dto.getContactPhone());
//                    System.out.println("nf-contact email===>" + dto.getContactEmail());
                    dto.setContactType("f");
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().insert("CustomerMap.insertCustomerFinanceContact", dto);
                    insertCount++;
                }
            }
        }
        if (customerDunningContact != null) {
            if (customerDunningContact.size() > 0) {
                Iterator itr = customerDunningContact.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
//                    System.out.println("nf-comntact person===>" + dto.getContactPerson());
//                    System.out.println("nf-contact phone ===>" + dto.getContactPhone());
//                    System.out.println("nf-contact email===>" + dto.getContactEmail());
                    dto.setContactType("d");
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().insert("CustomerMap.insertCustomerDunningContact", dto);
                    insertCount++;
                }
            }
        }
        if (customerWorkLoc != null) {
            if (customerWorkLoc.size() > 0) {
                Iterator itr = customerWorkLoc.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
//                    System.out.println("n-cust address===>" + dto.getCustomerAddress());
//                    System.out.println("n-cntry===>" + dto.getCountryID());
//                    System.out.println("n-region===>" + dto.getRegionID());
//                    System.out.println("n- city===>" + dto.getCityID());
//                    System.out.println("jkjk---------------->"+dto.getWorkLocationWorkingHours());
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().insert("CustomerMap.insertCustomerWorkLoc", dto);
                    System.out.println("customer Name " + customerBean.getCustomerName());
                    dto.setCustomerCalName(customerBean.getCustomerName() + "_" + dto.getAddressShortCode() + "_" + dto.getWorkLocationWorkingHours());
//                    dto.setLastInsertId(lastInsertId);customerName
//                    dto.setCustomerCalName(CONCAT(customerName,"_",addressShortCode,"_",workLocationWorkingHours));insertCustomerCalendar
//                            CONCAT(customerName,'_',addressShortCode,'_',workLocationWorkingHours)
                    getSqlMapClientTemplate().insert("CustomerMap.insertCustomerCalendar", dto);
                    insertCount++;
                }
            }
        }

        if (customerBilling != null) {
            if (customerBilling.size() > 0) {
                Iterator itr = customerBilling.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
//                    System.out.println("nb-custaddrse===>" + dto.getCustomerAddress());
//                    System.out.println("nb-cntry===>" + dto.getCountryID());
//                    System.out.println("nb-region===>" + dto.getRegionID());
//                    System.out.println("nb-city===>" + dto.getCityID());
                    if(dto.getGstCode()==null){
                        dto.setGstCode("");
                    }
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().insert("CustomerMap.insertCustomerBilling", dto);
                    insertCount++;
                }
            }
        }
        return insertCount;
    }

    @Override
    public void updateCustomerDetails(HashMap<String, List<CustomerAddDTO>> data, CustomerAddDTO customerBean) {
        List<CustomerAddDTO> customerContact = data.get("customerContact");
        List<CustomerAddDTO> customerFinanceContact = data.get("customerFinanceContact");
        List<CustomerAddDTO> customerDunningContact = data.get("customerDunningContact");
        List<CustomerAddDTO> customerWorkLoc = data.get("customerWorkLoc");
        List<CustomerAddDTO> customerBilling = data.get("customerBilling");
        String customerId = customerBean.getCustomerID();
        if (customerContact != null) {
            if (customerContact.size() > 0) {
                Iterator itr = customerContact.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                    dto.setContactType("bu");
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().update("CustomerMap.updateCustomerContact", dto);
                }
            }
        }
        if (customerFinanceContact != null) {
            if (customerFinanceContact.size() > 0) {
                Iterator itr = customerFinanceContact.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                    dto.setContactType("f");
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().update("CustomerMap.updateCustomerFinanceContact", dto);
                }
            }
        }
        if (customerDunningContact != null) {
            if (customerDunningContact.size() > 0) {
                Iterator itr = customerDunningContact.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                    dto.setContactType("d");
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().update("CustomerMap.updateCustomerDunningContact", dto);
                }
            }
        }
        if (customerWorkLoc != null) {
            if (customerWorkLoc.size() > 0) {
                Iterator itr = customerWorkLoc.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().update("CustomerMap.updateCustomerWorkLoc", dto);
                    dto.setCustomerCalName(customerBean.getCustomerName() + "_" + dto.getAddressShortCode() + "_" + dto.getWorkLocationWorkingHours());
                    getSqlMapClientTemplate().update("CustomerMap.updateCustomerCalendar", dto);
                }
            }
        }

        if (customerBilling != null) {
            if (customerBilling.size() > 0) {
                Iterator itr = customerBilling.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                    dto.setCustomerID(customerId);
                    getSqlMapClientTemplate().update("CustomerMap.updateCustomerBilling", dto);
                }
            }
        }

    }

    @Override
    public void updateCustomerMaster(CustomerAddDTO filterData) {
        try {
            if (filterData.getAttachmentType() != null && filterData.getAttchedFileName() != null
                    && filterData.getAttchedFileName() != "" && filterData.getAttachmentType() != "") {

                System.out.println("rrr==========>");
                String query = ",attachment_type = '" + filterData.getAttachmentType() + "', attachments = '" + filterData.getAttchedFileName() + "'";
                System.out.println("query-->" + query);
                filterData.setQueryString(query);
            }
            System.out.println("~~~~~~~~~~~~~~~attachment_type =" + filterData.getAttachmentType() + "~~~~~~attachments =" + filterData.getAttchedFileName());
            getSqlMapClientTemplate().update("CustomerMap.updateCustomerMasterDetails", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomerActiveInactive(CustomerDataDTO filterData) {
        try {
            if (filterData.getCustID() != null) {
                getSqlMapClientTemplate().update("CustomerMap.updateCustomerActiveInactive", filterData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomerCode(CustomerAddDTO filterData) {
        try {
            if (filterData.getCustomerID() != null) {
                getSqlMapClientTemplate().update("CustomerMap.updateCustomerCode", filterData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateParentId(CustomerAddDTO filterData) {
        try {
            if (filterData.getParentId() != 0) {
                getSqlMapClientTemplate().update("CustomerMap.updateParentId", filterData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CustomerAddDTO> getRegionName(String Val) {
        List<CustomerAddDTO> region = null;
        try {
            region = getSqlMapClientTemplate().queryForList("CustomerMap.getRegionNameBasedId", Val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public List<CustomerAddDTO> getCityName(String Val) {
        List<CustomerAddDTO> city = null;
        try {
            city = getSqlMapClientTemplate().queryForList("CustomerMap.getCityNameBasedId", Val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public int insertParentCustomer(CustomerAddDTO customerFormData) {
        int parentId = 0;
        try {
            parentId = (Integer) getSqlMapClientTemplate().insert("CustomerMap.insertParentCustomer", customerFormData);

            System.out.println("parent id inserted>>>>>>>>>>>>>>>" + parentId);
        } catch (Exception e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return parentId;
    }

    public List<String> getApproverMailId() {
        List<String> approverMailId = null;
        try {
            approverMailId = getSqlMapClientTemplate().queryForList("CustomerMap.getApproverMailId");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return approverMailId;
    }

    public List<CustomerAddDTO> getInvoiceList() {
        List<CustomerAddDTO> invoiceList = null;
        try {
            invoiceList = getSqlMapClientTemplate().queryForList("CustomerMap.getInvoiceList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    public List<String> getInvoiceValue(String key) {
        List<String> invoiceValue = null;
        try {
            invoiceValue = getSqlMapClientTemplate().queryForList("CustomerMap.getInvoiceValue", key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceValue;
    }
    
    public String getDunningMaxDate(){
        String dunning_max_date = null;
        try {
            dunning_max_date = (String)getSqlMapClientTemplate().queryForObject("CustomerMap.getDunningMaxDate");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dunning_max_date;
    }
    
    public List<CustomerGroupDTO> getDunningList(CustomerGroupDTO filter_date){
        List<CustomerGroupDTO> dunningList = null;
        try {
            dunningList = getSqlMapClientTemplate().queryForList("CustomerMap.getDunningList", filter_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dunningList;
    }
    
    public List<CustomerGroupDTO> getDunningDate(CustomerGroupDTO filter_date){
        List<CustomerGroupDTO> dunningList = null;
        try {
            dunningList = getSqlMapClientTemplate().queryForList("CustomerMap.getDunningDate", filter_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dunningList;
    }
    public List<CustomerGroupDTO> getDunningCustomerList(CustomerGroupDTO filterData){
        List<CustomerGroupDTO> dunningCustomerList = null;
        try {
            dunningCustomerList = getSqlMapClientTemplate().queryForList("CustomerMap.getDunningCustomerList",filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dunningCustomerList;
    }
    public List<CustomerGroupDTO> getBdmList(){
        List<CustomerGroupDTO> dunningCustomerList = null;
        try {
            String bdm_id = (String)getSqlMapClientTemplate().queryForObject("CustomerMap.getBdmId");
            dunningCustomerList = getSqlMapClientTemplate().queryForList("CustomerMap.getBdmList",bdm_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dunningCustomerList;
    }
    public void updateDunningStatus(CustomerGroupDTO filterData){
        try {
            if (filterData.getCustomer_id() != null) {
                getSqlMapClientTemplate().update("CustomerMap.updateDunningStatus", filterData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<CustomerGroupDTO> getDebtorsExcel(CustomerGroupDTO filterData){
        List<CustomerGroupDTO> debtors_excel = null;
        try {
            debtors_excel = getSqlMapClientTemplate().queryForList("CustomerMap.debtorsReport");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return debtors_excel;
    }
}
