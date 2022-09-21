/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.CustagingReportDataDTO;
import com.defiance.ideal.reports.dto.CustagingReportFilterDTO;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 14517
 */
public class CustagingReportDaoImpl extends SqlMapClientDaoSupport implements CustagingReportDao {

    private String sbuId;

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }

    public List<CustagingReportDataDTO> fetchCustagingReport(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> reportDetails = null;
        String PES = CommonConfigurations.BUH_FOR_PES;
        String TS = CommonConfigurations.BUH_FOR_TS;
        if (filterData.getBusiness_leader_id() != null) {
            if (filterData.getBusiness_leader_id().equals(PES)) {
                filterData.setSBU(CommonConfigurations.PES);
            } else if (filterData.getBusiness_leader_id().equals(TS)) {
                filterData.setSBU(CommonConfigurations.TS);
            }
        }
        try {
            Date todayDate = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(todayDate);
            cal.add(Calendar.DATE, -1);
            String query = "";
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (filterData.getReportDate() != null) {
                filterData.setReportDate(outputFormat.format(inputFormat.parse(filterData.getReportDate())));
            } else {
                filterData.setReportDate(maxDateOfReport());
            }
            if (maxDateOfReport().equals(filterData.getReportDate().toString())) {
                query = "select crh.custaging_invoice_number,crh.expected_collection_date,crh.invoice_date_submission_customer  from  "
                        + "custaging_report_histories as crh where crh.id in(select max(id) as id from custaging_report_histories group by custaging_invoice_number )";
            } else {
                query = "select crh.custaging_invoice_number,crh.expected_collection_date,crh.invoice_date_submission_customer  from  custaging_report_histories as crh where crh.custaging_invoice_date = '" + filterData.getReportDate() + "'";
            }

            filterData.setDynamic_query(query);

            reportDetails = getSqlMapClientTemplate().queryForList("CustagingReport.fetchCustagingReport", filterData);




        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }

    public String maxDateOfReport() {
        String maxDateOFReport = (String) getSqlMapClientTemplate().queryForObject("CustagingReport.maxDateOfreport");
        return maxDateOFReport;
    }


    public List<CustagingReportDataDTO> fetchLegalEntity(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> reportDetails = null;
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CustagingReport.fetchLegalEntity", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }

    public List<CustagingReportDataDTO> fetchBusinessLeader(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> reportDetails = null;
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CustagingReport.fetchBusinessLeader", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }

    public List<CustagingReportDataDTO> fetchBdmId(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> reportDetails = null;
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CustagingReport.fetchBdmId", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }

    public List<CustagingReportDataDTO> fetchBdmName(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> reportDetails = null;
        System.out.println("leader id" + filterData.getBusiness_leader_id());
        String PES = CommonConfigurations.BUH_FOR_PES;
        String TS = CommonConfigurations.BUH_FOR_TS;
        if (filterData.getBusiness_leader_id() != null) {
            if (filterData.getBusiness_leader_id().equals(PES)) {
                filterData.setSBU(CommonConfigurations.PES);
            } else if (filterData.getBusiness_leader_id().equals(TS)) {
                filterData.setSBU(CommonConfigurations.TS);
            }
        }
        System.out.println("filterData.getBusiness_leader_id()" + filterData.getBusiness_leader_id());
        System.out.println("filterData.getSBU()" + filterData.getSBU());

        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CustagingReport.fetchBdmName", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reportDetails;
    }

    public List<CustagingReportDataDTO> fetchBdmNameList(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> reportDetails = null;
        try {
            String PES = CommonConfigurations.BUH_FOR_PES;
            String TS = CommonConfigurations.BUH_FOR_TS;
            if (filterData.getBusiness_leader_id() != null) {
                if (filterData.getBusiness_leader_id().equals(PES)) {
                    filterData.setSBU(CommonConfigurations.PES);
                } else if (filterData.getBusiness_leader_id().equals(TS)) {
                    filterData.setSBU(CommonConfigurations.TS);
                }
            }
             if (filterData.getReportDate() != null) {
            // filterData.setReportDate(outputFormat.format(inputFormat.parse(filterData.getReportDate())));
            System.out.println("filterDatabdm-----123->" + filterData.getReportDate());
        } else {
            filterData.setReportDate(maxDateOfReport());
            System.out.println("filterDatabdm------>" + filterData.getReportDate());
        }
            
            reportDetails = getSqlMapClientTemplate().queryForList("CustagingReport.fetchBdmNameList", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }

    public List<CustagingReportDataDTO> fetchCustomerName(CustagingReportFilterDTO filterData) throws ParseException {
        List<CustagingReportDataDTO> reportDetails = null;
        String PES = CommonConfigurations.BUH_FOR_PES;
        String TS = CommonConfigurations.BUH_FOR_TS;
        if (filterData.getBusiness_leader_id() != null) {
            if (filterData.getBusiness_leader_id().equals(PES)) {
                filterData.setSBU(CommonConfigurations.PES);

            } else if (filterData.getBusiness_leader_id().equals(TS)) {
                filterData.setSBU(CommonConfigurations.TS);
            }
        }
        //SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        //SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (filterData.getReportDate() != null) {
            // filterData.setReportDate(outputFormat.format(inputFormat.parse(filterData.getReportDate())));
            System.out.println("filterData-----123->" + filterData.getReportDate());
        } else {
            filterData.setReportDate(maxDateOfReport());
            System.out.println("filterData------>" + filterData.getReportDate());
        }
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CustagingReport.fetchCustomerName", filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDetails;
    }

    public List<CustagingReportDataDTO> fetchReportDates(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> reportDates = null;
        try {
            reportDates = getSqlMapClientTemplate().queryForList("CustagingReport.fetchReportDates", filterData);
            Iterator itr = reportDates.iterator();
            while (itr.hasNext()) {
                CustagingReportDataDTO dto = (CustagingReportDataDTO) itr.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDates;
    }

    public HashMap<String, String> fetchExchangeRate(CustagingReportFilterDTO filterData) {
        List<CustagingReportDataDTO> reportDetails = null;
        HashMap<String, String> map = new HashMap();
        try {
            reportDetails = getSqlMapClientTemplate().queryForList("CustagingReport.fetchExchangeRate", filterData);

            for (int i = 0; i < reportDetails.size(); i++) {
                map.put(reportDetails.get(i).getCurrency_code(), reportDetails.get(i).getExchange_rate());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public void updateInvoiceDates(CustagingReportFilterDTO formData) {
        String checkData = "";
        if (formData.getCustagingCheckList() != null) {
            for (int i = 0; i < formData.getCustagingCheckList().length; i++) {
                checkData += "#" + formData.getCustagingCheckList()[i] + "#";
            }
            for (int i = 0; i < formData.getInvoice_number().length; i++) {
                if (checkData.contains("#" + formData.getInvoice_number()[i] + "#")) {
                    formData.setInvoiceDateSubmitCustomerForUpdate(formData.getInvoiceDateSubmitCustomer()[i]);
                    formData.setExpectedCollectionDateForUpdate(formData.getExpectedCollectionDate()[i]);
                    formData.setSubmition_invoice_number(formData.getInvoice_number()[i]);
                    try {
                        formData.setSubmition_date(CommonMethods.changeDateFormatToDB(formData.getDate_of_report()[i]));
                    } catch (ParseException ex) {
                        Logger.getLogger(CustagingReportDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    getSqlMapClientTemplate().update("CustagingReport.updateInvoiceDates", formData);
                    int existingData = (Integer) getSqlMapClientTemplate().queryForObject("CustagingReport.checkInExistingDates", formData);
                    if (existingData == 0) {
                        getSqlMapClientTemplate().insert("CustagingReport.updateInvoiceDatesForHistory", formData);
                    }
                }
            }
        }
    }
}
