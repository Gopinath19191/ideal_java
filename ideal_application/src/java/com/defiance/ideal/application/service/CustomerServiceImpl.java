/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.service;

import com.defiance.ideal.application.dao.CustomerDao;
import com.defiance.ideal.application.dao.CustomerDaoImpl;
import com.defiance.ideal.application.dto.CustomerAddDTO;
import com.defiance.ideal.application.dto.CustomerDataDTO;
import com.defiance.ideal.application.dto.CustomerGroupDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import static org.apache.commons.lang.StringUtils.trim;

/**
 *
 * @author 14053
 */
public class CustomerServiceImpl implements CustomerService {

    public CustomerDao customerDao;

    public int addCustomerDetails(CustomerAddDTO filterData) {
        int insertedCount = 0;
        HashMap<String, List<CustomerAddDTO>> mapObject = new HashMap<String, List<CustomerAddDTO>>();
        List<CustomerAddDTO> custmerContact_new = new ArrayList<CustomerAddDTO>();
        List<CustomerAddDTO> custmerFinanceContact_new = new ArrayList<CustomerAddDTO>();
        List<CustomerAddDTO> custmerDunningContact_new = new ArrayList<CustomerAddDTO>();
        List<CustomerAddDTO> custmerWorkLoc_new = new ArrayList<CustomerAddDTO>();
        List<CustomerAddDTO> custmerBilling_new = new ArrayList<CustomerAddDTO>();
        CustomerAddDTO customerBean = null;

        // customer contact details
        try {
            if (!filterData.getStatus().equals("s")) {
                if (filterData.getCustomerContactPersonNew() != null
                        && filterData.getCustomerDesignationNew() != null
                        && filterData.getCustomerContactEmailNew() != null
                        && filterData.getCustomerContactPhoneNew() != null) {

                    Map<String, List> newHash = new HashMap<String, List>();
                    if (filterData.getCustomerContactPersonNew().length == filterData.getCustomerContactPersonNew().length
                            && filterData.getCustomerDesignationNew().length == filterData.getCustomerDesignationNew().length
                            && filterData.getCustomerContactEmailNew().length == filterData.getCustomerContactEmailNew().length
                            && filterData.getCustomerContactPhoneNew().length == filterData.getCustomerContactPhoneNew().length) {
                        for (int i = 0; i < filterData.getCustomerContactPersonNew().length; i++) {

                            if (((filterData.getCustomerContactPersonNew()[i] != null) && (filterData.getCustomerContactPersonNew()[i] != ""))
                                    && ((filterData.getCustomerDesignationNew()[i] != null) && (filterData.getCustomerDesignationNew()[i] != ""))
                                    && ((filterData.getCustomerContactEmailNew()[i] != null) && (filterData.getCustomerContactEmailNew()[i] != ""))
                                    && ((filterData.getCustomerContactPhoneNew()[i] != null) && (filterData.getCustomerContactPhoneNew()[i] != ""))) {
                                List<String> newcontact = new ArrayList<String>();
                                customerBean = new CustomerAddDTO();
                                customerBean.setContactPerson(filterData.getCustomerContactPersonNew()[i]);
                                customerBean.setContactDesignation(filterData.getCustomerDesignationNew()[i]);
                                customerBean.setContactEmail(filterData.getCustomerContactEmailNew()[i]);
                                customerBean.setContactPhone(filterData.getCustomerContactPhoneNew()[i]);

                                custmerContact_new.add(customerBean);

                                newcontact.add(filterData.getCustomerContactPersonNew()[i]);
                                newcontact.add(filterData.getCustomerDesignationNew()[i]);
                                newcontact.add(filterData.getCustomerContactEmailNew()[i]);
                                newcontact.add(filterData.getCustomerContactPhoneNew()[i]);
                                newcontact.add("Business Contact");
                                newHash.put("Customer Contact " + (i + 1), newcontact);
                                filterData.setNewlyAddedData(newHash);
                            }
                        }

                    }
                }
            } else {
                for (int i = 0; i < filterData.getCustomerContactPersonNew().length; i++) {
                    Map<String, List> newHash = new HashMap<String, List>();

                    if ((filterData.getCustomerContactPersonNew()[i] != null && filterData.getCustomerContactPersonNew()[i].isEmpty() != true)
                            || (filterData.getCustomerDesignationNew()[i] != null && filterData.getCustomerDesignationNew()[i].isEmpty() != true)
                            || (filterData.getCustomerContactEmailNew()[i] != null && filterData.getCustomerContactEmailNew()[i].isEmpty() != true)
                            || (filterData.getCustomerContactPhoneNew()[i] != null && filterData.getCustomerContactPhoneNew()[i].isEmpty() != true)) {

                        List<String> newcontact = new ArrayList<String>();
                        customerBean = new CustomerAddDTO();

                        customerBean.setContactPerson(filterData.getCustomerContactPersonNew()[i]);
                        customerBean.setContactDesignation(filterData.getCustomerDesignationNew()[i]);
                        customerBean.setContactEmail(filterData.getCustomerContactEmailNew()[i]);
                        customerBean.setContactPhone(filterData.getCustomerContactPhoneNew()[i]);

                        custmerContact_new.add(customerBean);
                        //                    newcontact.add(filterData.getCustomerContactPersonNew()[i]);
                        //                    newcontact.add(filterData.getCustomerDesignationNew()[i]);
                        //                    newcontact.add(filterData.getCustomerContactEmailNew()[i]);
                        //                    newcontact.add(filterData.getCustomerContactPhoneNew()[i]);
                        //                    newcontact.add("Business Contact");
                        //                    newHash.put("Customer Contact "+(i+1),newcontact);
                        //                    filterData.setNewlyAddedData(newHash);
                    }
                }
            }

            // finance contact
            if (!filterData.getStatus().equals("s")) {
                if (filterData.getCustomerFinanceContactPersonNew() != null
                        && filterData.getCustomerFinanceDesignationNew() != null
                        && filterData.getCustomerFinanceContactEmailNew() != null
                        && filterData.getCustomerFinanceContactPhoneNew() != null) {
                    System.out.println("inside finance add");
                    Map<String, List> newHash = new HashMap<String, List>();
                    if (filterData.getCustomerFinanceContactPersonNew().length == filterData.getCustomerFinanceContactEmailNew().length
                            && filterData.getCustomerFinanceContactPersonNew().length == filterData.getCustomerFinanceDesignationNew().length
                            && filterData.getCustomerFinanceContactPersonNew().length == filterData.getCustomerFinanceContactPhoneNew().length) {
                        for (int i = 0; i < filterData.getCustomerFinanceContactPersonNew().length; i++) {
                            if (((filterData.getCustomerFinanceContactPersonNew()[i] != null) && (filterData.getCustomerFinanceContactPersonNew()[i].isEmpty() != true))
                                    && ((filterData.getCustomerFinanceDesignationNew()[i] != null) && (filterData.getCustomerFinanceDesignationNew()[i].isEmpty() != true))
                                    && ((filterData.getCustomerFinanceContactEmailNew()[i] != null) && (filterData.getCustomerFinanceContactEmailNew()[i].isEmpty() != true))
                                    && ((filterData.getCustomerFinanceContactPhoneNew()[i] != null) && (filterData.getCustomerFinanceContactPhoneNew()[i].isEmpty() != true))) {
                                List<String> newcontact = new ArrayList<String>();
                                customerBean = new CustomerAddDTO();
                                customerBean.setContactPerson(filterData.getCustomerFinanceContactPersonNew()[i]);
                                customerBean.setContactDesignation(filterData.getCustomerFinanceDesignationNew()[i]);
                                customerBean.setContactEmail(filterData.getCustomerFinanceContactEmailNew()[i]);
                                customerBean.setContactPhone(filterData.getCustomerFinanceContactPhoneNew()[i]);
                                custmerFinanceContact_new.add(customerBean);
                                newcontact.add(filterData.getCustomerFinanceContactPersonNew()[i]);
                                newcontact.add(filterData.getCustomerFinanceDesignationNew()[i]);
                                newcontact.add(filterData.getCustomerFinanceContactEmailNew()[i]);
                                newcontact.add(filterData.getCustomerFinanceContactPhoneNew()[i]);
                                newcontact.add("Finance Contact");
                                newHash.put("Customer Finance Contact " + (i + 1), newcontact);
                                filterData.setNewlyAddedfinance(newHash);
                            }
                        }
                    }
                }
            } else {
                Map<String, List> newHash = new HashMap<String, List>();
                for (int i = 0; i < filterData.getCustomerFinanceContactPersonNew().length; i++) {
                    if ((filterData.getCustomerFinanceContactPersonNew()[i] != null && filterData.getCustomerFinanceContactPersonNew()[i].isEmpty() != true)
                            || (filterData.getCustomerFinanceDesignationNew()[i] != null && filterData.getCustomerFinanceDesignationNew()[i].isEmpty() != true)
                            || (filterData.getCustomerFinanceContactEmailNew()[i] != null && filterData.getCustomerFinanceContactEmailNew()[i].isEmpty() != true)
                            || (filterData.getCustomerFinanceContactPhoneNew()[i] != null && filterData.getCustomerFinanceContactPhoneNew()[i].isEmpty() != true)) {
                        System.out.println("finance inside null exception");
                        List<String> newcontact = new ArrayList<String>();
                        customerBean = new CustomerAddDTO();
                        customerBean.setContactPerson(filterData.getCustomerFinanceContactPersonNew()[i]);
                        customerBean.setContactDesignation(filterData.getCustomerFinanceDesignationNew()[i]);
                        customerBean.setContactEmail(filterData.getCustomerFinanceContactEmailNew()[i]);
                        customerBean.setContactPhone(filterData.getCustomerFinanceContactPhoneNew()[i]);
                        custmerFinanceContact_new.add(customerBean);
                        //                    newcontact.add(filterData.getCustomerFinanceContactPersonNew()[i]);
                        //                    newcontact.add(filterData.getCustomerFinanceDesignationNew()[i]);
                        //                    newcontact.add(filterData.getCustomerFinanceContactEmailNew()[i]);
                        //                    newcontact.add(filterData.getCustomerFinanceContactPhoneNew()[i]);
                        //                    newcontact.add("Finance Contact");
                        //                    newHash.put("Customer Finance Contact "+(i+1),newcontact);
                        //                    filterData.setNewlyAddedfinance(newHash);
                    }
                }
            }
            
            ///dunning
            if (!filterData.getStatus().equals("s")) {
                if (filterData.getCustomerDunningContactPersonNew() != null
                        && filterData.getCustomerDunningDesignationNew() != null
                        && filterData.getCustomerDunningContactEmailNew() != null
                        && filterData.getCustomerDunningContactPhoneNew() != null) {
                    System.out.println("inside Dunning add");
                    Map<String, List> newHash = new HashMap<String, List>();
                    if (filterData.getCustomerDunningContactPersonNew().length == filterData.getCustomerDunningContactEmailNew().length
                            && filterData.getCustomerDunningContactPersonNew().length == filterData.getCustomerDunningDesignationNew().length
                            && filterData.getCustomerDunningContactPersonNew().length == filterData.getCustomerDunningContactPhoneNew().length) {
                        for (int i = 0; i < filterData.getCustomerDunningContactPersonNew().length; i++) {
                            if (((filterData.getCustomerDunningContactPersonNew()[i] != null) && (filterData.getCustomerDunningContactPersonNew()[i].isEmpty() != true))
                                    && ((filterData.getCustomerDunningDesignationNew()[i] != null) && (filterData.getCustomerDunningDesignationNew()[i].isEmpty() != true))
                                    && ((filterData.getCustomerDunningContactEmailNew()[i] != null) && (filterData.getCustomerDunningContactEmailNew()[i].isEmpty() != true))
                                    && ((filterData.getCustomerDunningContactPhoneNew()[i] != null) && (filterData.getCustomerDunningContactPhoneNew()[i].isEmpty() != true))) {
                                List<String> newcontact = new ArrayList<String>();
                                customerBean = new CustomerAddDTO();
                                customerBean.setContactPerson(filterData.getCustomerDunningContactPersonNew()[i]);
                                customerBean.setContactDesignation(filterData.getCustomerDunningDesignationNew()[i]);
                                customerBean.setContactEmail(filterData.getCustomerDunningContactEmailNew()[i]);
                                customerBean.setContactPhone(filterData.getCustomerDunningContactPhoneNew()[i]);
                                custmerDunningContact_new.add(customerBean);
                                newcontact.add(filterData.getCustomerDunningContactPersonNew()[i]);
                                newcontact.add(filterData.getCustomerDunningDesignationNew()[i]);
                                newcontact.add(filterData.getCustomerDunningContactEmailNew()[i]);
                                newcontact.add(filterData.getCustomerDunningContactPhoneNew()[i]);
                                newcontact.add("Finance Contact");
                                newHash.put("Customer Finance Contact " + (i + 1), newcontact);
                                filterData.setNewlyAddedfinance(newHash);
                            }
                        }
                    }
                }
            } else {
                Map<String, List> newHash = new HashMap<String, List>();
                for (int i = 0; i < filterData.getCustomerDunningContactPersonNew().length; i++) {
                    if ((filterData.getCustomerDunningContactPersonNew()[i] != null && filterData.getCustomerDunningContactPersonNew()[i].isEmpty() != true)
                            || (filterData.getCustomerDunningDesignationNew()[i] != null && filterData.getCustomerDunningDesignationNew()[i].isEmpty() != true)
                            || (filterData.getCustomerDunningContactEmailNew()[i] != null && filterData.getCustomerDunningContactEmailNew()[i].isEmpty() != true)
                            || (filterData.getCustomerDunningContactPhoneNew()[i] != null && filterData.getCustomerDunningContactPhoneNew()[i].isEmpty() != true)) {
                        System.out.println("finance inside null exception");
                        List<String> newcontact = new ArrayList<String>();
                        customerBean = new CustomerAddDTO();
                        customerBean.setContactPerson(filterData.getCustomerDunningContactPersonNew()[i]);
                        customerBean.setContactDesignation(filterData.getCustomerDunningDesignationNew()[i]);
                        customerBean.setContactEmail(filterData.getCustomerDunningContactEmailNew()[i]);
                        customerBean.setContactPhone(filterData.getCustomerDunningContactPhoneNew()[i]);
                        custmerDunningContact_new.add(customerBean);
                    }
                }
            }
            

            if (!filterData.getStatus().equals("s")) {
                System.out.println("submitt work location");
                if ((filterData.getCustomerWorkAddressNew() != null
                        && filterData.getCustomerWorkShortCodeNew() != null
                        && filterData.getCustomerWorkCountryNew() != null
                        && filterData.getCustomerWorkRegionNew() != null
                        && filterData.getCustomerWorkCityNew() != null
                        && filterData.getCustomerWorkPincodeNew() != null
                        && filterData.getWorkingHoursPerDayNew() != null)) {
                    Map<String, List> newHash = new HashMap<String, List>();
                    if (filterData.getCustomerWorkAddressNew().length == filterData.getCustomerWorkCountryNew().length
                            && filterData.getCustomerWorkAddressNew().length == filterData.getCustomerWorkShortCodeNew().length
                            && filterData.getCustomerWorkAddressNew().length == filterData.getCustomerWorkRegionNew().length
                            && filterData.getCustomerWorkAddressNew().length == filterData.getCustomerWorkCityNew().length
                            && filterData.getCustomerWorkAddressNew().length == filterData.getCustomerWorkPincodeNew().length
                            && filterData.getCustomerWorkAddressNew().length == filterData.getWorkingHoursPerDayNew().length) {

                        for (int i = 0; i < filterData.getCustomerWorkAddressNew().length; i++) {
                            if (((filterData.getCustomerWorkAddressNew()[i] != null) && (filterData.getCustomerWorkAddressNew()[i] != ""))
                                    && ((filterData.getCustomerWorkShortCodeNew()[i] != null) && (filterData.getCustomerWorkShortCodeNew()[i] != ""))
                                    && ((filterData.getCustomerWorkCountryNew()[i] != null) && (filterData.getCustomerWorkCountryNew()[i] != ""))
                                    && ((filterData.getCustomerWorkRegionNew()[i] != null) && (filterData.getCustomerWorkRegionNew()[i] != ""))
                                    && ((filterData.getCustomerWorkCityNew()[i] != null) && (filterData.getCustomerWorkCityNew()[i] != ""))
                                    && ((filterData.getCustomerWorkPincodeNew()[i] != null) && (filterData.getCustomerWorkPincodeNew()[i] != ""))
                                    && ((filterData.getWorkingHoursPerDayNew()[i] != null) && (filterData.getWorkingHoursPerDayNew()[i] != ""))) {

                                List<String> newcontact = new ArrayList<String>();
                                customerBean = new CustomerAddDTO();
                                customerBean.setCustomerAddress(filterData.getCustomerWorkAddressNew()[i]);
                                customerBean.setAddressShortCode(filterData.getCustomerWorkShortCodeNew()[i]);
                                customerBean.setCountryID(filterData.getCustomerWorkCountryNew()[i]);
                                customerBean.setRegionID(filterData.getCustomerWorkRegionNew()[i]);
                                customerBean.setCityID(filterData.getCustomerWorkCityNew()[i]);
                                customerBean.setPincode(filterData.getCustomerWorkPincodeNew()[i]);
                                customerBean.setWorkLocationWorkingHours(filterData.getWorkingHoursPerDayNew()[i]);
                                customerBean.setIsCompanyLocation(filterData.getWorkLocationIscompanyLocationNew()[i]);
                                custmerWorkLoc_new.add(customerBean);

                                List<CustomerAddDTO> countryName = getCountryName(filterData.getCustomerWorkCountryNew()[i]);
                                List<CustomerAddDTO> regionName = getRegionName(filterData.getCustomerWorkRegionNew()[i]);
                                List<CustomerAddDTO> cityName = getCityName(filterData.getCustomerWorkCityNew()[i]);
                                newcontact.add(filterData.getCustomerWorkAddressNew()[i]);
                                newcontact.add(filterData.getCustomerWorkShortCodeNew()[i]);
                                if (countryName.size() > 0) {
                                    newcontact.add(countryName.get(0).getCountryName());
                                }
                                if (regionName.size() > 0) {
                                    newcontact.add(regionName.get(0).getRegionName());
                                }
                                if (cityName.size() > 0) {
                                    newcontact.add(cityName.get(0).getCityName());
                                }

                                newcontact.add(filterData.getCustomerWorkPincodeNew()[i]);
                                newcontact.add(filterData.getWorkingHoursPerDayNew()[i]);
                                newHash.put("Customer Work Location " + (i + 1), newcontact);
                                filterData.setNewlyAddedwork(newHash);
                            }
                        }
                    }
                }
            } else if (filterData.getStatus().equals("s")) {

                System.out.println("work location else");
                Map<String, List> newHash = new HashMap<String, List>();
                for (int i = 0; i < filterData.getCustomerWorkAddressNew().length; i++) {
                    List<String> newcontact = new ArrayList<String>();
                    if ((filterData.getCustomerWorkAddressNew()[i] != null && filterData.getCustomerWorkAddressNew()[i].isEmpty() != true)
                            || (filterData.getCustomerWorkShortCodeNew()[i] != null && filterData.getCustomerWorkShortCodeNew()[i].isEmpty() != true)
                            || (filterData.getCustomerWorkCountryNew()[i] != null && filterData.getCustomerWorkCountryNew()[i].isEmpty() != true)
                            || (filterData.getCustomerWorkRegionNew()[i] != null && filterData.getCustomerWorkRegionNew()[i].isEmpty() != true)
                            || (filterData.getCustomerWorkCityNew()[i] != null && filterData.getCustomerWorkCityNew()[i].isEmpty() != true)
                            || (filterData.getCustomerWorkPincodeNew()[i] != null && filterData.getCustomerWorkPincodeNew()[i].isEmpty() != true)
                            || (filterData.getWorkingHoursPerDayNew()[i] != null && filterData.getWorkingHoursPerDayNew()[i].isEmpty() != true)) {

                        customerBean = new CustomerAddDTO();

                        customerBean.setCustomerAddress(filterData.getCustomerWorkAddressNew()[i]);
                        customerBean.setAddressShortCode(filterData.getCustomerWorkShortCodeNew()[i]);
                        customerBean.setCountryID(filterData.getCustomerWorkCountryNew()[i]);
                        customerBean.setRegionID(filterData.getCustomerWorkRegionNew()[i]);
                        customerBean.setCityID(filterData.getCustomerWorkCityNew()[i]);
                        customerBean.setPincode(filterData.getCustomerWorkPincodeNew()[i]);
                        customerBean.setWorkLocationWorkingHours(filterData.getWorkingHoursPerDayNew()[i]);
                        customerBean.setIsCompanyLocation(filterData.getWorkLocationIscompanyLocationNew()[i]);
                        custmerWorkLoc_new.add(customerBean);

                    }

                }

            }

            if (!filterData.getStatus().equals("s")) {

                if (filterData.getCustomerBillingAddressNew() != null
                        && filterData.getCustomerBillingShortCodeNew() != null
                        && filterData.getCustomerBillingCountryNew() != null
                        && filterData.getCustomerBillingRegionNew() != null
                        && filterData.getCustomerBillingCityNew() != null
                        && filterData.getCustomerBillingPincodeNew() != null) {
                    System.out.println("here in billing address deetails>>>>>>>>" + filterData.getCustomerBillingAddressNew().length);
                    Map<String, List> newHash = new HashMap<String, List>();
                    if (filterData.getCustomerBillingAddressNew().length == filterData.getCustomerBillingCountryNew().length
                            && filterData.getCustomerBillingAddressNew().length == filterData.getCustomerBillingShortCodeNew().length
                            && filterData.getCustomerBillingAddressNew().length == filterData.getCustomerBillingRegionNew().length
                            && filterData.getCustomerBillingAddressNew().length == filterData.getCustomerBillingCityNew().length
                            && filterData.getCustomerBillingAddressNew().length == filterData.getCustomerBillingPincodeNew().length) {

                        for (int i = 0; i < filterData.getCustomerBillingAddressNew().length; i++) {
                            if (((filterData.getCustomerBillingAddressNew()[i] != null) && (filterData.getCustomerBillingAddressNew()[i] != ""))
                                    && ((filterData.getCustomerBillingShortCodeNew()[i] != null) && (filterData.getCustomerBillingShortCodeNew()[i] != ""))
                                    && ((filterData.getCustomerBillingCountryNew()[i] != null) && (filterData.getCustomerBillingCountryNew()[i] != ""))
                                    && ((filterData.getCustomerBillingRegionNew()[i] != null) && (filterData.getCustomerBillingRegionNew()[i] != ""))
                                    && ((filterData.getCustomerBillingCityNew()[i] != null) && (filterData.getCustomerBillingCityNew()[i] != ""))
                                    && ((filterData.getCustomerBillingPincodeNew()[i] != null) && (filterData.getCustomerBillingPincodeNew()[i] != ""))) {

                                List<String> newcontact = new ArrayList<String>();
                                customerBean = new CustomerAddDTO();
                                customerBean.setCustomerAddress(filterData.getCustomerBillingAddressNew()[i]);
                                customerBean.setAddressShortCode(filterData.getCustomerBillingShortCodeNew()[i]);
                                customerBean.setCountryID(filterData.getCustomerBillingCountryNew()[i]);
                                customerBean.setRegionID(filterData.getCustomerBillingRegionNew()[i]);
                                customerBean.setCityID(filterData.getCustomerBillingCityNew()[i]);
                                customerBean.setPincode(filterData.getCustomerBillingPincodeNew()[i]);
                                customerBean.setGstCode(filterData.getCustomerBillingGstCodeNew()[i]);
                                customerBean.setGstNumber(filterData.getCustomerBillingGstNumberNew()[i]);
                                custmerBilling_new.add(customerBean);

                                List<CustomerAddDTO> countryName = getCountryName(filterData.getCustomerBillingCountryNew()[i]);
                                List<CustomerAddDTO> regionName = getRegionName(filterData.getCustomerBillingRegionNew()[i]);
                                List<CustomerAddDTO> cityName = getCityName(filterData.getCustomerBillingCityNew()[i]);
                                newcontact.add(filterData.getCustomerBillingAddressNew()[i]);
                                newcontact.add(filterData.getCustomerBillingShortCodeNew()[i]);
                                if (countryName.size() > 0) {
                                    newcontact.add(countryName.get(0).getCountryName());
                                }
                                if (regionName.size() > 0) {
                                    newcontact.add(regionName.get(0).getRegionName());
                                }
                                if (cityName.size() > 0) {
                                    newcontact.add(cityName.get(0).getCityName());
                                }

                                newcontact.add(filterData.getCustomerBillingPincodeNew()[i]);
                                newHash.put("Customer Billing Address " + (i + 1), newcontact);
                                filterData.setNewlyAddedbilling(newHash);
                            }
                        }
                    }
                }
            } else {

                Map<String, List> newHash = new HashMap<String, List>();
                for (int i = 0; i < filterData.getCustomerBillingAddressNew().length; i++) {
                    List<String> newcontact = new ArrayList<String>();
                    customerBean = new CustomerAddDTO();

                    if ((filterData.getCustomerBillingAddressNew()[i] != null && filterData.getCustomerBillingAddressNew()[i].isEmpty() != true)
                            || (filterData.getCustomerBillingCountryNew()[i] != null && filterData.getCustomerBillingCountryNew()[i].isEmpty() != true)
                            || (filterData.getCustomerBillingShortCodeNew()[i] != null && filterData.getCustomerBillingCountryNew()[i].isEmpty() != true)
                            || (filterData.getCustomerBillingRegionNew()[i] != null && filterData.getCustomerBillingRegionNew()[i].isEmpty() != true)
                            || (filterData.getCustomerBillingCityNew()[i] != null && filterData.getCustomerBillingCityNew()[i].isEmpty() != true)
                            || (filterData.getCustomerBillingPincodeNew()[i] != null && filterData.getCustomerBillingPincodeNew()[i].isEmpty() != true)) {

                        customerBean.setCustomerAddress(filterData.getCustomerBillingAddressNew()[i]);
                        customerBean.setAddressShortCode(filterData.getCustomerBillingShortCodeNew()[i]);
                        customerBean.setCountryID(filterData.getCustomerBillingCountryNew()[i]);
                        customerBean.setRegionID(filterData.getCustomerBillingRegionNew()[i]);
                        customerBean.setCityID(filterData.getCustomerBillingCityNew()[i]);
                        customerBean.setPincode(filterData.getCustomerBillingPincodeNew()[i]);
                        customerBean.setGstCode(filterData.getCustomerBillingGstCodeNew()[i]);
                        customerBean.setGstNumber(filterData.getCustomerBillingGstNumberNew()[i]);
                        custmerBilling_new.add(customerBean);
                    }
                }

            }

            if (custmerContact_new != null) {
                if (custmerContact_new.size() > 0) {
                    mapObject.put("customerContact", custmerContact_new);

                }
            }
            if (custmerFinanceContact_new != null) {
                if (custmerFinanceContact_new.size() > 0) {
                    mapObject.put("customerFinanceContact", custmerFinanceContact_new);

                }
            }
            if (custmerDunningContact_new != null) {
                if (custmerDunningContact_new.size() > 0) {
                    mapObject.put("customerDunningContact", custmerDunningContact_new);

                }
            }
            if (custmerWorkLoc_new != null) {
                if (custmerWorkLoc_new.size() > 0) {
                    mapObject.put("customerWorkLoc", custmerWorkLoc_new);

                }
            }
            if (custmerBilling_new != null) {
                if (custmerBilling_new.size() > 0) {
                    mapObject.put("customerBilling", custmerBilling_new);

                }
            }
            if (mapObject != null) {
                insertedCount = customerDao.addCustomerDetails(mapObject, filterData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return insertedCount;
    }

    public String addFileDb(String fileName, String fileType, String referenceName, int referenceId, String moduleName) {
//        List<CustomerAddDTO> customerData = new ArrayList<CustomerAddDTO>();
        String lastFileInsertId = null;
        try {
            lastFileInsertId = (String) customerDao.addFileDb(fileName, fileType, referenceName, referenceId, moduleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastFileInsertId;
    }

    public void triggerMail(String[] toMailApprovalModules, CustomerAddDTO customerData,
            String mailSubReason, String mailProcessReason, String resEmpId) {
        customerDao.triggerMail(toMailApprovalModules, customerData,
                mailSubReason, mailProcessReason, resEmpId);
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Map<String, String> getSbuList() {
        return customerDao.getSbuList();
    }

    public List<CustomerDataDTO> fetchCustomerData(CustomerDataDTO filterData) {
        List<CustomerDataDTO> customerData = new ArrayList<CustomerDataDTO>();
        customerData = customerDao.fetchCustomerData(filterData);
        return customerData;
    }

    public List<CustomerDataDTO> fetchActiveInactiveCustomer(CustomerDataDTO filterData) {
        List<CustomerDataDTO> customerData = new ArrayList<CustomerDataDTO>();
        customerData = customerDao.fetchActiveInactiveCustomerData(filterData);
        return customerData;
    }

    public void updateCustomerActiveInactive(CustomerDataDTO filterData) {
        try {
            System.out.println("inside service impl");
            customerDao.updateCustomerActiveInactive(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomerCode(CustomerAddDTO filterData) {
        try {
            System.out.println("inside service impl");
            customerDao.updateCustomerCode(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCustomerActivatingCode(String customerId) {
        String customerCode = "";
        try {
            System.out.println("inside code activating impl");
            customerCode = customerDao.getCustomerActivatingCode(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerCode;
    }

    public List<CustomerDataDTO> fetchCustomerDataForExcel(CustomerDataDTO filterData) {
        List<CustomerDataDTO> customerData = new ArrayList<CustomerDataDTO>();
        customerData = customerDao.fetchCustomerDataForExcel(filterData);
        return customerData;
    }

    public List<CustomerDataDTO> fetchCustomerApproveData(CustomerDataDTO filterData) {
        List<CustomerDataDTO> customerData = new ArrayList<CustomerDataDTO>();
        customerData = customerDao.fetchCustomerApproveData(filterData);
        return customerData;
    }

    public List<CustomerDataDTO> fetchCustomerActiveInactive() {
        List<CustomerDataDTO> customerDeletedStatus = new ArrayList<CustomerDataDTO>();
        customerDeletedStatus = customerDao.fetchCustomerActiveInactive();
        return customerDeletedStatus;
    }

    public List<CustomerAddDTO> getMaxCustomerCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerMaxCode = new ArrayList<CustomerAddDTO>();
        try {
            customerMaxCode = customerDao.getMaxCustomerCode(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerMaxCode;
    }

    public List<CustomerAddDTO> getDivisionCount(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerDivisionCount = new ArrayList<CustomerAddDTO>();
        try {
            customerDivisionCount = customerDao.getDivisionCount(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDivisionCount;
    }

    public List<CustomerAddDTO> getCustomerCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerCode = new ArrayList<CustomerAddDTO>();
        try {
            customerCode = customerDao.getCustomerCode(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerCode;
    }

    public List<CustomerAddDTO> getCustomerDivisionApprovedCount(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerDivisionCount = new ArrayList<CustomerAddDTO>();
        try {
            customerDivisionCount = customerDao.getCustomerDivisionApprovedCount(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDivisionCount;
    }

    public List<CustomerAddDTO> getCustomerCodeMaxLike(CustomerAddDTO filterData) {
        List<CustomerAddDTO> customerCountMax = new ArrayList<CustomerAddDTO>();
        try {
            customerCountMax = customerDao.getCustomerCodeMaxLike(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerCountMax;
    }

    public List<CustomerAddDTO> getConfigValueData(String parentId) {
        List<CustomerAddDTO> configList = null;
        try {
            configList = customerDao.getConfigValueData(parentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configList;
    }

    public List<CustomerAddDTO> fetchSelectedCustomer(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = new ArrayList<CustomerAddDTO>();
        selectedCustomerData = customerDao.fetchSelectedCustomer(filterData);
        return selectedCustomerData;
    }

    public List<CustomerAddDTO> fetchSelectedCustomerDivisions(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerDivision = new ArrayList<CustomerAddDTO>();
        selectedCustomerDivision = customerDao.fetchSelectedCustomerDivisions(filterData);
        return selectedCustomerDivision;
    }

    public List<CustomerAddDTO> fetchBillingAddress(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = null;
        selectedCustomerData = customerDao.fetchBillingAddress(filterData);
        return selectedCustomerData;
    }

    public List<CustomerAddDTO> fetchCustomerWorkLocations(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = null;
        selectedCustomerData = customerDao.fetchCustomerWorkLocations(filterData);
        return selectedCustomerData;
    }

    public List<CustomerAddDTO> fetchCustomerContactDetails(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = null;
        selectedCustomerData = customerDao.fetchCustomerContactDetails(filterData);
        return selectedCustomerData;
    }

    public List<CustomerAddDTO> fetchCustomerFinanceContactDetails(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = null;
        selectedCustomerData = customerDao.fetchCustomerFinanceContactDetails(filterData);
        return selectedCustomerData;
    }
    public List<CustomerAddDTO> fetchCustomerDunningContactDetails(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = null;
        selectedCustomerData = customerDao.fetchCustomerDunningContactDetails(filterData);
        return selectedCustomerData;
    }
    public List<CustomerAddDTO> fetchWorkLocationShortCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = null;
        selectedCustomerData = customerDao.fetchWorkLocationShortCode(filterData);
        return selectedCustomerData;
    }

    public List<CustomerAddDTO> fetchBillingShortCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = null;
        selectedCustomerData = customerDao.fetchBillingShortCode(filterData);
        return selectedCustomerData;
    }

    public void customersAdd(CustomerAddDTO filterData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<CustomerDataDTO> getCustomerGroup() {
        List<CustomerDataDTO> customerGroup = null;
        try {
            customerGroup = customerDao.getCustomerGroup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerGroup;
    }

    public String getCustomerGroupName(String val) {
        String customerGroup = null;
        try {
            customerGroup = customerDao.getCustomerGroupName(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerGroup;
    }

    public String getSalesPersonName(String val) {
        String salesPersonName = null;
        try {
            salesPersonName = customerDao.getSalesPersonName(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesPersonName;
    }

    public List<CustomerAddDTO> getSalesPersonRef(CustomerAddDTO filterData) {
        List<CustomerAddDTO> salesPerson = null;
        try {
            salesPerson = customerDao.getSalesPersonRef(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesPerson;
    }

    public List<CustomerDataDTO> getSalesPersonRefId() {
        List<CustomerDataDTO> salesPerson = null;
        try {
            salesPerson = customerDao.getSalesPersonRefId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesPerson;
    }

    public List<CustomerAddDTO> getCurrencyList() {
        List<CustomerAddDTO> currencyList = null;
        try {
            currencyList = customerDao.getCurrencyList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyList;
    }

    public List<CustomerAddDTO> getCustomerGroupList() {
        List<CustomerAddDTO> customerGroupList = null;
        try {
            customerGroupList = customerDao.getcustomerGroupList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerGroupList;
    }

    public List<CustomerAddDTO> getBusinessLeader() {
        List<CustomerAddDTO> businessLeader = null;
        try {
            businessLeader = customerDao.getBusinessLeader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return businessLeader;
    }

    public List<CustomerAddDTO> getRBUList() {
        List<CustomerAddDTO> rbuList = null;
        try {
            rbuList = customerDao.getRBUList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rbuList;
    }

    public List<CustomerAddDTO> getSBUList() {
        List<CustomerAddDTO> sbuList = null;
        try {
            sbuList = customerDao.getSBUList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbuList;
    }

    public List<CustomerAddDTO> getCountryList() {
        List<CustomerAddDTO> countryList = null;
        try {
            countryList = customerDao.getCountryList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryList;
    }

    public List<CustomerAddDTO> getCountryName(String Val) {
        List<CustomerAddDTO> country = null;
        try {
            country = customerDao.getCountryName(Val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    public List<CustomerAddDTO> getRegionName(String Val) {
        List<CustomerAddDTO> region = null;
        try {
            region = customerDao.getRegionName(Val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return region;
    }

    public List<CustomerAddDTO> getCityName(String Val) {
        List<CustomerAddDTO> city = null;
        try {
            city = customerDao.getCityName(Val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public List<CustomerAddDTO> getRegionList(CustomerAddDTO filterData) {
        List<CustomerAddDTO> regionList = null;
        try {
            regionList = customerDao.getRegionList(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public List<CustomerAddDTO> getRegionListFull(CustomerAddDTO filterData) {
        List<CustomerAddDTO> regionList = null;
        try {
            regionList = customerDao.getRegionListFull(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public List<CustomerAddDTO> getCityList(CustomerAddDTO filterData) {
        List<CustomerAddDTO> cityList = null;
        try {
            cityList = customerDao.getCityList(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public String getGstCode(CustomerAddDTO filterData) {
        String gstCode = null;
        try {
            gstCode = customerDao.getGstCode(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gstCode;
    }

    public String getSalesPersonPhoneNumber(CustomerAddDTO filterData) {
        String PhoneNumber = null;
        try {
            PhoneNumber = customerDao.getSalesPersonPhoneNumber(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PhoneNumber;
    }

    public List<CustomerDataDTO> customerProjectMapping(CustomerDataDTO filterData) {
        List<CustomerDataDTO> projectList = null;
        try {
            projectList = customerDao.customerProjectMapping(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public String insertCustomerDetails(CustomerAddDTO filterData) {
        String lastInsertId = null;
        try {
            System.out.println("In service impl:::::::::::" + filterData.getCustomerName());
            lastInsertId = customerDao.insertCustomerDetails(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastInsertId;
    }

//    public String insertCustomerDivision(CustomerAddDTO filterData) {
//        String lastInsertId = null;
//        try {
//            lastInsertId = customerDao.insertCustomerDivision(filterData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return lastInsertId;
//    }
    /* public void insertCustomerWrokDetails(CustomerAddDTO filterData) {
     try {
     System.out.println("In service impl:::::::::::"+filterData.getCustomerName());
     customerDao.insertCustomerWorkDetails(filterData);
     } catch (Exception e) {
     e.printStackTrace();
     }
     }
     public void insertCustomerBillingDetails(CustomerAddDTO filterData) {
     try {
     System.out.println("In service impl:::::::::::"+filterData.getCustomerName());
     customerDao.insertCustomerBillingDetails(filterData);
     } catch (Exception e) {
     e.printStackTrace();
     }
     }*/
    public List<CustomerAddDTO> getCustomerStatus(CustomerAddDTO filterData) {
        List<CustomerAddDTO> status = null;
        try {
            status = customerDao.getCustomerStatus(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public void approveCustomerDetails(CustomerAddDTO filterData) {
        try {
//            System.out.println("In service impl:::::::::::"+filterData.getCustomerID());
            customerDao.approveCustomerDetails(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rmgApproveCustomerDetails(CustomerAddDTO filterData) {
        try {
//            System.out.println("In service impl:::::::::::"+filterData.getCustomerID());
            customerDao.rmgApproveCustomerDetails(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CustomerAddDTO> fetchInvoiceCode(CustomerAddDTO filterData) {
        List<CustomerAddDTO> invoiceCodeList = null;
        try {
            invoiceCodeList = new ArrayList<CustomerAddDTO>();
            invoiceCodeList = customerDao.fetchInvoiceCode(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceCodeList;
    }

    public List<CustomerAddDTO> getDuplicateCustomerName(CustomerAddDTO filterData) {
        List<CustomerAddDTO> regionList = null;
        try {
            regionList = customerDao.getDuplicateCustomerName(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public List<CustomerAddDTO> getsubRBUList(CustomerAddDTO filterData) {
        List<CustomerAddDTO> subRBUList = null;
        try {
            subRBUList = customerDao.getsubRBUList(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subRBUList;
    }

//    public List<CustomerAddDTO> fetchSelectedCustomerData(CustomerAddDTO filterData) {
//        List<CustomerDataDTO> customerData = new ArrayList<CustomerDataDTO>();
//        customerData = customerDao.fetchCustomerData(filterData);
//        return customerData;
//    }
    public void updateCustomerMaster(CustomerAddDTO filterData) {
        try {
            customerDao.updateCustomerMaster(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateParentId(CustomerAddDTO filterData) {
        try {
            customerDao.updateParentId(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomerDetails(CustomerAddDTO filterData) {
        HashMap<String, List<CustomerAddDTO>> mapObject = new HashMap<String, List<CustomerAddDTO>>();
        List<CustomerAddDTO> custmerContact = new ArrayList<CustomerAddDTO>();
        List<CustomerAddDTO> custmerFinanceContact = new ArrayList<CustomerAddDTO>();
        List<CustomerAddDTO> custmerDunningContact = new ArrayList<CustomerAddDTO>();
        List<CustomerAddDTO> custmerWorkLoc = new ArrayList<CustomerAddDTO>();
        List<CustomerAddDTO> custmerBilling = new ArrayList<CustomerAddDTO>();
        CustomerAddDTO customerBean = null;
        if (filterData.getCustomerContactID() != null
                && filterData.getCustomerContactPhone() != null
                && filterData.getCustomerContactDesignation() != null
                && filterData.getCustomerContactEmail() != null
                && filterData.getCustomerContactPerson() != null) {

            if (filterData.getCustomerContactID().length == filterData.getCustomerContactPhone().length
                    && filterData.getCustomerContactID().length == filterData.getCustomerContactDesignation().length
                    && filterData.getCustomerContactID().length == filterData.getCustomerContactEmail().length
                    && filterData.getCustomerContactID().length == filterData.getCustomerContactPerson().length) {
                for (int i = 0; i < filterData.getCustomerContactID().length; i++) {
                    if (((filterData.getCustomerContactPerson()[i] != null) && (filterData.getCustomerContactPerson()[i] != ""))
                            && ((filterData.getCustomerContactEmail()[i] != null) && (filterData.getCustomerContactEmail()[i] != ""))
                            && ((filterData.getCustomerContactDesignation()[i] != null) && (filterData.getCustomerContactDesignation()[i] != ""))
                            && ((filterData.getCustomerContactPhone()[i] != null) && (filterData.getCustomerContactPhone()[i] != ""))
                            && ((filterData.getCustomerContactID()[i] != null) && (filterData.getCustomerContactID()[i] != ""))) {

                        customerBean = new CustomerAddDTO();
                        customerBean.setId(filterData.getCustomerContactID()[i]);
                        customerBean.setContactPerson(filterData.getCustomerContactPerson()[i]);
                        customerBean.setContactDesignation(filterData.getCustomerContactDesignation()[i]);
                        customerBean.setContactEmail(filterData.getCustomerContactEmail()[i]);
                        customerBean.setContactPhone(filterData.getCustomerContactPhone()[i]);
                        custmerContact.add(customerBean);
                    }
                }
            }
        }
        System.out.println("inside update" + filterData.getCustomerFinanceContactID());
        if (filterData.getCustomerFinanceContactID() != null
                && filterData.getCustomerFinanceContactPerson() != null
                && filterData.getCustomerFinanceContactDesignation() != null
                && filterData.getCustomerFinanceContactEmail() != null
                && filterData.getCustomerFinanceContactPhone() != null) {

            if (filterData.getCustomerFinanceContactID().length == filterData.getCustomerFinanceContactPerson().length
                    && filterData.getCustomerFinanceContactID().length == filterData.getCustomerFinanceContactDesignation().length
                    && filterData.getCustomerFinanceContactID().length == filterData.getCustomerFinanceContactEmail().length
                    && filterData.getCustomerFinanceContactID().length == filterData.getCustomerFinanceContactPhone().length) {
                for (int i = 0; i < filterData.getCustomerFinanceContactID().length; i++) {
                    if (((filterData.getCustomerFinanceContactPerson()[i] != null) && (filterData.getCustomerFinanceContactPerson()[i] != ""))
                            && ((filterData.getCustomerFinanceContactDesignation()[i] != null) && (filterData.getCustomerFinanceContactDesignation()[i] != ""))
                            && ((filterData.getCustomerFinanceContactID()[i] != null) && (filterData.getCustomerFinanceContactID()[i] != ""))
                            && ((filterData.getCustomerFinanceContactEmail()[i] != null) && (filterData.getCustomerFinanceContactEmail()[i] != ""))
                            && ((filterData.getCustomerFinanceContactPhone()[i] != null) && (filterData.getCustomerFinanceContactPhone()[i] != ""))) {
                        customerBean = new CustomerAddDTO();
                        customerBean.setId(filterData.getCustomerFinanceContactID()[i]);
                        customerBean.setContactPerson(filterData.getCustomerFinanceContactPerson()[i]);
                        customerBean.setContactDesignation(filterData.getCustomerFinanceContactDesignation()[i]);
                        customerBean.setContactEmail(filterData.getCustomerFinanceContactEmail()[i]);
                        customerBean.setContactPhone(filterData.getCustomerFinanceContactPhone()[i]);
                        custmerFinanceContact.add(customerBean);
                    }
                }
            }
        }
        //dunning contact
        if (filterData.getCustomerDunningContactID() != null
                && filterData.getCustomerDunningContactPerson() != null
                && filterData.getCustomerDunningContactDesignation() != null
                && filterData.getCustomerDunningContactEmail() != null
                && filterData.getCustomerDunningContactPhone() != null) {

            if (filterData.getCustomerDunningContactID().length == filterData.getCustomerDunningContactPerson().length
                    && filterData.getCustomerDunningContactID().length == filterData.getCustomerDunningContactDesignation().length
                    && filterData.getCustomerDunningContactID().length == filterData.getCustomerDunningContactEmail().length
                    && filterData.getCustomerDunningContactID().length == filterData.getCustomerDunningContactPhone().length) {
                for (int i = 0; i < filterData.getCustomerDunningContactID().length; i++) {
                    if (((filterData.getCustomerDunningContactPerson()[i] != null) && (filterData.getCustomerDunningContactPerson()[i] != ""))
                            && ((filterData.getCustomerDunningContactDesignation()[i] != null) && (filterData.getCustomerDunningContactDesignation()[i] != ""))
                            && ((filterData.getCustomerDunningContactID()[i] != null) && (filterData.getCustomerDunningContactID()[i] != ""))
                            && ((filterData.getCustomerDunningContactEmail()[i] != null) && (filterData.getCustomerDunningContactEmail()[i] != ""))
                            && ((filterData.getCustomerDunningContactPhone()[i] != null) && (filterData.getCustomerDunningContactPhone()[i] != ""))) {
                        customerBean = new CustomerAddDTO();
                        customerBean.setId(filterData.getCustomerDunningContactID()[i]);
                        customerBean.setContactPerson(filterData.getCustomerDunningContactPerson()[i]);
                        customerBean.setContactDesignation(filterData.getCustomerDunningContactDesignation()[i]);
                        customerBean.setContactEmail(filterData.getCustomerDunningContactEmail()[i]);
                        customerBean.setContactPhone(filterData.getCustomerDunningContactPhone()[i]);
                        custmerDunningContact.add(customerBean);
                    }
                }
            }
        }
        if (filterData.getCustomerWorkLocationID() != null
                && filterData.getCustomerWorkAddress() != null
                && filterData.getCustomerWorkCountry() != null
                && filterData.getCustomerWorkRegion() != null
                && filterData.getCustomerWorkCity() != null
                && filterData.getCustomerWorkPincode() != null
                && filterData.getWorkingHoursPerDay() != null) {
//              System.out.println("filterData.getCustomerWorkRegion()[i]--->" + filterData.getWorkingHoursPerDay()[0]);
            if (filterData.getCustomerWorkLocationID().length == filterData.getCustomerWorkAddress().length
                    && filterData.getCustomerWorkLocationID().length == filterData.getCustomerWorkCountry().length
                    && filterData.getCustomerWorkLocationID().length == filterData.getCustomerWorkRegion().length
                    && filterData.getCustomerWorkLocationID().length == filterData.getCustomerWorkCity().length
                    && filterData.getCustomerWorkLocationID().length == filterData.getCustomerWorkPincode().length
                    && filterData.getCustomerWorkLocationID().length == filterData.getWorkingHoursPerDay().length) {
//                      System.out.println("filterData.getCustomerWorkRegion()[i]--->" + filterData.getWorkingHoursPerDay()[0]);
                for (int i = 0; i < filterData.getCustomerWorkLocationID().length; i++) {
                    if (((filterData.getCustomerWorkAddress()[i] != null) && (filterData.getCustomerWorkAddress()[i] != ""))
                            && ((filterData.getCustomerWorkCountry()[i] != null) && (filterData.getCustomerWorkCountry()[i] != ""))
                            && ((filterData.getCustomerWorkRegion()[i] != null) && (filterData.getCustomerWorkRegion()[i] != ""))
                            && ((filterData.getCustomerWorkCity()[i] != null) && (filterData.getCustomerWorkCity()[i] != ""))
                            && ((filterData.getCustomerWorkPincode()[i] != null) && (filterData.getCustomerWorkPincode()[i] != ""))
                            && ((filterData.getCustomerWorkLocationID()[i] != null) && (filterData.getCustomerWorkLocationID()[i] != ""))
                            && ((filterData.getCustomerWorkLocationID()[i] != null) && (filterData.getWorkingHoursPerDay()[i] != ""))) {

                        customerBean = new CustomerAddDTO();
                        customerBean.setId(filterData.getCustomerWorkLocationID()[i]);
                        customerBean.setCustomerAddress(filterData.getCustomerWorkAddress()[i]);
                        customerBean.setAddressShortCode(filterData.getCustomerWorkShortCode()[i]);
                        customerBean.setCountryID(filterData.getCustomerWorkCountry()[i]);
                        customerBean.setRegionID(filterData.getCustomerWorkRegion()[i]);
                        customerBean.setCityID(filterData.getCustomerWorkCity()[i]);
                        customerBean.setPincode(filterData.getCustomerWorkPincode()[i]);
                        customerBean.setWorkLocationWorkingHours(filterData.getWorkingHoursPerDay()[i]);
                        customerBean.setWorkLocationIscmpyLocation(filterData.getIsworklocationcompanylocation()[i]);
                        custmerWorkLoc.add(customerBean);
                    }
                }
            }
        }
        if (filterData.getCustomerBillingID() != null
                && filterData.getCustomerBillingAddress() != null
                && filterData.getCustomerBillingCountry() != null
                && filterData.getCustomerBillingRegion() != null
                && filterData.getCustomerBillingCity() != null
                && filterData.getCustomerBillingPincode() != null) {

            if (filterData.getCustomerBillingID().length == filterData.getCustomerBillingAddress().length
                    && filterData.getCustomerBillingID().length == filterData.getCustomerBillingCountry().length
                    && filterData.getCustomerBillingID().length == filterData.getCustomerBillingRegion().length
                    && filterData.getCustomerBillingID().length == filterData.getCustomerBillingCity().length
                    && filterData.getCustomerBillingID().length == filterData.getCustomerBillingPincode().length) {
                for (int i = 0; i < filterData.getCustomerBillingID().length; i++) {
                    if (((filterData.getCustomerBillingID()[i] != null) && (filterData.getCustomerBillingID()[i] != ""))
                            && ((filterData.getCustomerBillingAddress()[i] != null) && (filterData.getCustomerBillingAddress()[i] != ""))
                            && ((filterData.getCustomerBillingCountry()[i] != null) && (filterData.getCustomerBillingCountry()[i] != ""))
                            && ((filterData.getCustomerBillingRegion()[i] != null) && (filterData.getCustomerBillingRegion()[i] != ""))
                            && ((filterData.getCustomerBillingCity()[i] != null) && (filterData.getCustomerBillingCity()[i] != ""))) {

                        customerBean = new CustomerAddDTO();
                        customerBean.setId(filterData.getCustomerBillingID()[i]);
                        customerBean.setCustomerAddress(filterData.getCustomerBillingAddress()[i]);
                        customerBean.setAddressShortCode(filterData.getCustomerBillingShortCode()[i]);
                        customerBean.setCountryID(filterData.getCustomerBillingCountry()[i]);
                        customerBean.setRegionID(filterData.getCustomerBillingRegion()[i]);
                        customerBean.setCityID(filterData.getCustomerBillingCity()[i]);
                        customerBean.setPincode(filterData.getCustomerBillingPincode()[i]);
                        customerBean.setGstCode(filterData.getCustomerBillingGstCode()[i]);
                        customerBean.setGstNumber(filterData.getCustomerBillingGstNumber()[i]);
                        custmerBilling.add(customerBean);
                    }
                }
            }
        }


        if (custmerContact != null) {
            if (custmerContact.size() > 0) {
                mapObject.put("customerContact", custmerContact);
            }
        }
        if (custmerFinanceContact != null) {
            if (custmerFinanceContact.size() > 0) {
                mapObject.put("customerFinanceContact", custmerFinanceContact);
            }
        }
        if (custmerFinanceContact != null) {
            if (custmerFinanceContact.size() > 0) {
                mapObject.put("customerDunningContact", custmerDunningContact);
            }
        }
        if (custmerWorkLoc != null) {
            if (custmerWorkLoc.size() > 0) {
                mapObject.put("customerWorkLoc", custmerWorkLoc);
            }
        }
        if (custmerBilling != null) {
            if (custmerBilling.size() > 0) {
                mapObject.put("customerBilling", custmerBilling);
            }
        }
        if (mapObject != null) {
            customerDao.updateCustomerDetails(mapObject, filterData);
        }
    }
//
//    public int insertParentCustomer(CustomerAddDTO customerFormData) {
//        int parentId = 0;
//        parentId = customerDao.insertParentCustomer(customerFormData);
//        
//        return parentId;
//    }

    public int insertParentCustomer(CustomerAddDTO customerFormData) {
        int parentId = 0;
        try {
            //System.out.println("In service impl:::::::::::"+filterData.getCustomerName());
            parentId = customerDao.insertParentCustomer(customerFormData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return parentId;
    }

    public List<CustomerAddDTO> fetchSelectedParent(CustomerAddDTO filterData) {
        List<CustomerAddDTO> selectedCustomerData = new ArrayList<CustomerAddDTO>();
        selectedCustomerData = customerDao.fetchSelectedParent(filterData);
        return selectedCustomerData;
    }

    public List<CustomerAddDTO> getDuplicateCustomerDivisionName(CustomerAddDTO filterData) {
        List<CustomerAddDTO> regionList = null;
        try {
            regionList = customerDao.getDuplicateCustomerDivisionName(filterData);
            System.out.println("        duplicate name" + regionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionList;
    }

    public void insertCustomerInfoHistory(CustomerAddDTO filterData) {

        try {
            customerDao.insertCustomerInfoHistory(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<String> getApproverMailId() {
        List<String> mailId = null;
        try {
            mailId = customerDao.getApproverMailId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mailId;
    }

    public List<CustomerAddDTO> getInvoiceList() {
        List<CustomerAddDTO> invoiceList = null;
        try {
            invoiceList = customerDao.getInvoiceList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    public List<String> getInvoiceValue(String key) {
        List<String> invoiceValue = null;
        try {
            invoiceValue = customerDao.getInvoiceValue(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceValue;
    }
    public String getDunningMaxDate() {
        String maxDate = null;
        try {
            maxDate = customerDao.getDunningMaxDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxDate;
    }

    public List<CustomerGroupDTO> getDunningList(CustomerGroupDTO filter_date) {
        List<CustomerGroupDTO> dunningList = null;
        try {
            dunningList = customerDao.getDunningList(filter_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dunningList;
    }

    public List<CustomerGroupDTO> getDunningDate(CustomerGroupDTO filter_date) {
        List<CustomerGroupDTO> date = null;
        try {
            date = customerDao.getDunningDate(filter_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public List<CustomerGroupDTO> getDunningCustomerList(CustomerGroupDTO filterData) {
        List<CustomerGroupDTO> customer_lsit = null;
        try {
            customer_lsit = customerDao.getDunningCustomerList(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer_lsit;
    }

    public List<CustomerGroupDTO> getBdmList() {
        List<CustomerGroupDTO> bdm_list = null;
        try {
            bdm_list = customerDao.getBdmList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bdm_list;
    }

    public void updateDunningStatus(CustomerGroupDTO filterData){
        try {
            customerDao.updateDunningStatus(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<CustomerGroupDTO> getDebtorsExcel(CustomerGroupDTO filterData) {
        List<CustomerGroupDTO> debtors_list = null;
        try {
            debtors_list = customerDao.getDebtorsExcel(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return debtors_list;
    }
}
