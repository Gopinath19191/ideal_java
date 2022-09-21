/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 16113
 */
public class TravelSettlementDto {

    private String employeeNumber;
    private String employeeName;
    private String designationId;
    private String designationName;
    private String businessGroupId;
    private String businessGroupName;
    private String practiceGroupId;
    private String practiceGroupName;
    private String subPracticeGroupId;
    private String subPracticeGroupName;
    private String locationId;
    private String requestedDate;
    private String bandId;
    private String bandName;
    private String cityId;
    private String cityName;
    private String empId;
    private String configKey;
    private String configValue;
    private String parentId;
    private String projectId;
    private String projectName;
    private String projectCode;
    private String travelPoints;
    private String addressId;
    private String ticketRefId;
    private String contactNo;
    private String customerName;
    private String customerId;
    private String managerId;
    private String managerName;
    private String clientReimbursable;
    private String projectTravel;
    private String settlementType;
    private String travelStartDate;
    private String travelEndDate;
    private String fromCity;
    private String toCity;
    private String currencyId;
    private String currencyName;
    private String travelType;
    private String travelTerm;
    private String bill;
    private String category;
    private String city;
    private String amount;
    private String masterId;
    private String isSettlementAdded;
    private String fileId;
    private String bookingStatus;
    private String bookingType;
    private String travelDate;
    private String ticketId;
    private String fcityId;
    private String tcityId;
    private String ofcityId;
    private String ofcityName;
    private String otcityId;
    private String otcityName;
    private String adminAction;
    private String fromDate;
    private String toDate;
    private String billNo;
    private String billDate;
    private String remarks;
    private String res_tic_billDate[];
    private String res_tic_category[];
    private String res_tic_fcity[];
    private String res_tic_tcity[];
    private String res_tic_billNo[];
    private String res_tic_billAmount[];
    private String res_tic_fdate[];
    private String res_tic_tdate[];
    private String res_tic_remarks[];
    private String res_hot_remarks[];
    private String res_con_remarks[];
    private String res_hot_category[];
    private String res_hot_city[];
    private String res_hot_fdate[];
    private String res_hot_tdate[];
    private String res_hot_status[];
    private String res_hot_billDate[];
    private String res_hot_billNo[];
    private String res_hot_billAmount[];
    private String res_tic_id[];
    private String res_hot_id[];
    private String res_con_id[];
    private String id;
    private String adminId;
    private String attachmentId;
    private String ticketTotal;
    private String boardingTotal;
    private String conveyanceTotal;
    private String lodgingTotal;
    private String miscTotal;
    private String totalExpance;
    private String res_con_category[];
    private String res_con_city[];
    private String res_con_fdate[];
    private String res_con_tdate[];
    private String res_con_billAmount[];
    private String res_con_billDate[];
    private String res_con_billNo[];
    private String lastInsertId;
    private String attachment_name;
    private String tp_settlement_id;
    private String depositedAmount;
    private String tobeDeposited;
    private String tobeRecovered;
    private String currencyCode;
    private String settlementBy;
    private String con_deleted[];
    private String tic_deleted[];
    private String hot_deleted[];
    private MultipartFile res_tic_attachment[];
    private MultipartFile res_hot_attachment[];
    private MultipartFile res_con_attachment[];
    private String advanceId;
    private String requestedAmount;
    private String totalAdvance;
    private String categoryType;
    private String res_city[];
    private String res_city_id[];
    private String res_fromDate[];
    private String res_toDate[];
    private String res_bill[];
    private String res_billDate[];
    private String res_category[];
    private String res_eligibility[];
    private String lodEliTot;
    private String ticEliTot;
    private String boarEliTot;
    private String conEliTot;
    private String misEliTot;
    private String totEliTot;
    private String lodDeviation;
    private String borDeviation;
    private String misDeviation;
    private String totDeviation;
    private String deviationPercent;
    private String borTotExp;
    private String ticTotExp;
    private String lodTotExp;
    private String conTotExp;
    private String misTotExp;
    private String totTotExp;
    private String approver_type;
    private String list_type;
    private String statusDesc;
    private String rm_id;
    private String pm_id;
    private String rm_name;
    private String pm_name;
    private String buh_id;
    private String buh_name;
    private String treasury_id;
    private String treasury_name;
    private String rm_approved_date;
    private String rm_remarks;
    private String buh_approved_date;
    private String buh_remarks;
    private String treasury_approved_date;
    private String treasury_remarks;
    private String treasury_comments;
    private String payroll_id;
    private String payroll_name;
    private String payroll_remarks;
    private String payroll_approved_date;
    private String admin_id;
    private String admin_name;
    private String admin_action_date;
    private String admin_remarks;
    private String finance_id;
    private String finance_name;
    private String finance_approved_date;
    private String finance_remarks;
    private String status_name;
    private String cancel_remarks;
    private String cfo_id;
    private String cfo_name;
    private String cfo_approved_date;
    private String cfo_remarks;
    private String deviation;
    private String employeeId;
    private MultipartFile file_attachment[];
    private String file_category[];
    private String recoveredAmount;
    private String recoveredDate;
    private String depositedDate;
    private String eligibiityAmount;
    private String file_name;
    private String[] expenseId;
    private String[] delStat;
    private String delstatus;
    private String[] attachId;
    private String[] attachdelStat;
    private String[] attachments;
    private String settlemet_policy;
    private String travelCommenced;
    private String travelCompleted;
    private String amount_tobe_recovered;
    private String amount_tobe_deposited;
    private String payroll_comments;
    private String paid_by[];
    private String attachment_deleted[];
    private String invoice[];
    private String tic_invoice[];
    private String hot_invoice[];
    private String invoiceStat;
    private String hotdelStat[];
    private String ticdelStat[];
    private String ticAttachments[];
    private String hotAttachments[];
    private String ticAttachmentId[];
    private String hotAttachmentId[];
    private String hot_tax[];
    private String hot_tot[];
    private String tic_tax[];
    private String tic_tot[];
    private String hotTax;
    private String hotTotal;
    private String tax;
    private String ticTax;
    private String ticTotal;
    private String ticTaxTotal;
    private String ticTaxTotalAmount;
    private String lodTaxTotal;
    private String lodTaxTotalAmount;
    private String boarTaxTotal;
    private String boarTaxTotalAmount;
    private String conTaxTotal;
    private String conTaxTotalAmount;
    private String misTaxTotal;
    private String misTaxTotalAmount;
    private String totalTax;
    private String totalTotalAmount;
    private String lodDevPercentage;
    private String boarDevPercentage;
    private String misDevPercentage;
    private String cfo_action;
    private String tic_approved_amount;
    private String lod_approved_amount;
    private String employee_name;
    private String mail_id;
    private String emp_id;
    private String vendor_name;
    private String vendor_id;
    private String tic_vendor[];
    private String hot_vendor[];
    private String startTime;
    private String endTime;
    private String con_vendor[];
    private String con_billDate[];
    private String con_billNo[];
    private String con_invoice[];
    private String con_total[];
    private String con_remarks[];
    private String employee_action;
    private String admin_action;
    private String billReceived;
    private String billReceivedDate;
    
    public String getEmployee_action() {
        return employee_action;
    }

    public void setEmployee_action(String employee_action) {
        this.employee_action = employee_action;
    }

    public String getAdmin_action() {
        return admin_action;
    }

    public void setAdmin_action(String admin_action) {
        this.admin_action = admin_action;
    }
    
    public String[] getRes_city_id() {
        return res_city_id;
    }

    public void setRes_city_id(String[] res_city_id) {
        this.res_city_id = res_city_id;
    }
    
    public String[] getCon_vendor() {
        return con_vendor;
    }

    public void setCon_vendor(String[] con_vendor) {
        this.con_vendor = con_vendor;
    }

    public String[] getCon_billDate() {
        return con_billDate;
    }

    public void setCon_billDate(String[] con_billDate) {
        this.con_billDate = con_billDate;
    }

    public String[] getCon_billNo() {
        return con_billNo;
    }

    public void setCon_billNo(String[] con_billNo) {
        this.con_billNo = con_billNo;
    }

    public String[] getCon_invoice() {
        return con_invoice;
    }

    public void setCon_invoice(String[] con_invoice) {
        this.con_invoice = con_invoice;
    }

    public String[] getCon_total() {
        return con_total;
    }

    public void setCon_total(String[] con_total) {
        this.con_total = con_total;
    }

    public String[] getCon_remarks() {
        return con_remarks;
    }

    public void setCon_remarks(String[] con_remarks) {
        this.con_remarks = con_remarks;
    }
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getTic_vendor() {
        return tic_vendor;
    }

    public void setTic_vendor(String[] tic_vendor) {
        this.tic_vendor = tic_vendor;
    }

    public String[] getHot_vendor() {
        return hot_vendor;
    }

    public void setHot_vendor(String[] hot_vendor) {
        this.hot_vendor = hot_vendor;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getMail_id() {
        return mail_id;
    }

    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }

    public String getTic_approved_amount() {
        return tic_approved_amount;
    }

    public void setTic_approved_amount(String tic_approved_amount) {
        this.tic_approved_amount = tic_approved_amount;
    }

    public String getLod_approved_amount() {
        return lod_approved_amount;
    }

    public void setLod_approved_amount(String lod_approved_amount) {
        this.lod_approved_amount = lod_approved_amount;
    }

    public String getBoar_approved_amount() {
        return boar_approved_amount;
    }

    public void setBoar_approved_amount(String boar_approved_amount) {
        this.boar_approved_amount = boar_approved_amount;
    }

    public String getCon_approved_amount() {
        return con_approved_amount;
    }

    public void setCon_approved_amount(String con_approved_amount) {
        this.con_approved_amount = con_approved_amount;
    }

    public String getMis_approved_amount() {
        return mis_approved_amount;
    }

    public void setMis_approved_amount(String mis_approved_amount) {
        this.mis_approved_amount = mis_approved_amount;
    }

    public String getTot_approved_amount() {
        return tot_approved_amount;
    }

    public void setTot_approved_amount(String tot_approved_amount) {
        this.tot_approved_amount = tot_approved_amount;
    }
    private String boar_approved_amount;
    private String con_approved_amount;
    private String mis_approved_amount;
    private String tot_approved_amount;

    public String getCfo_action() {
        return cfo_action;
    }

    public void setCfo_action(String cfo_action) {
        this.cfo_action = cfo_action;
    }

    public String getLodDevPercentage() {
        return lodDevPercentage;
    }

    public void setLodDevPercentage(String lodDevPercentage) {
        this.lodDevPercentage = lodDevPercentage;
    }

    public String getBoarDevPercentage() {
        return boarDevPercentage;
    }

    public void setBoarDevPercentage(String boarDevPercentage) {
        this.boarDevPercentage = boarDevPercentage;
    }

    public String getMisDevPercentage() {
        return misDevPercentage;
    }

    public void setMisDevPercentage(String misDevPercentage) {
        this.misDevPercentage = misDevPercentage;
    }

    public String getTicTaxTotal() {
        return ticTaxTotal;
    }

    public void setTicTaxTotal(String ticTaxTotal) {
        this.ticTaxTotal = ticTaxTotal;
    }

    public String getTicTaxTotalAmount() {
        return ticTaxTotalAmount;
    }

    public void setTicTaxTotalAmount(String ticTaxTotalAmount) {
        this.ticTaxTotalAmount = ticTaxTotalAmount;
    }

    public String getLodTaxTotal() {
        return lodTaxTotal;
    }

    public void setLodTaxTotal(String lodTaxTotal) {
        this.lodTaxTotal = lodTaxTotal;
    }

    public String getLodTaxTotalAmount() {
        return lodTaxTotalAmount;
    }

    public void setLodTaxTotalAmount(String lodTaxTotalAmount) {
        this.lodTaxTotalAmount = lodTaxTotalAmount;
    }

    public String getBoarTaxTotal() {
        return boarTaxTotal;
    }

    public void setBoarTaxTotal(String boarTaxTotal) {
        this.boarTaxTotal = boarTaxTotal;
    }

    public String getBoarTaxTotalAmount() {
        return boarTaxTotalAmount;
    }

    public void setBoarTaxTotalAmount(String boarTaxTotalAmount) {
        this.boarTaxTotalAmount = boarTaxTotalAmount;
    }

    public String getConTaxTotal() {
        return conTaxTotal;
    }

    public void setConTaxTotal(String conTaxTotal) {
        this.conTaxTotal = conTaxTotal;
    }

    public String getConTaxTotalAmount() {
        return conTaxTotalAmount;
    }

    public void setConTaxTotalAmount(String conTaxTotalAmount) {
        this.conTaxTotalAmount = conTaxTotalAmount;
    }

    public String getMisTaxTotal() {
        return misTaxTotal;
    }

    public void setMisTaxTotal(String misTaxTotal) {
        this.misTaxTotal = misTaxTotal;
    }

    public String getMisTaxTotalAmount() {
        return misTaxTotalAmount;
    }

    public void setMisTaxTotalAmount(String misTaxTotalAmount) {
        this.misTaxTotalAmount = misTaxTotalAmount;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getTotalTotalAmount() {
        return totalTotalAmount;
    }

    public void setTotalTotalAmount(String totalTotalAmount) {
        this.totalTotalAmount = totalTotalAmount;
    }

    public String getHotTax() {
        return hotTax;
    }

    public void setHotTax(String hotTax) {
        this.hotTax = hotTax;
    }

    public String getHotTotal() {
        return hotTotal;
    }

    public void setHotTotal(String hotTotal) {
        this.hotTotal = hotTotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTicTax() {
        return ticTax;
    }

    public void setTicTax(String ticTax) {
        this.ticTax = ticTax;
    }

    public String getTicTotal() {
        return ticTotal;
    }

    public void setTicTotal(String ticTotal) {
        this.ticTotal = ticTotal;
    }

    public String[] getHot_tax() {
        return hot_tax;
    }

    public void setHot_tax(String[] hot_tax) {
        this.hot_tax = hot_tax;
    }

    public String[] getHot_tot() {
        return hot_tot;
    }

    public void setHot_tot(String[] hot_tot) {
        this.hot_tot = hot_tot;
    }

    public String[] getTic_tax() {
        return tic_tax;
    }

    public void setTic_tax(String[] tic_tax) {
        this.tic_tax = tic_tax;
    }

    public String[] getTic_tot() {
        return tic_tot;
    }

    public void setTic_tot(String[] tic_tot) {
        this.tic_tot = tic_tot;
    }

    public String[] getTic_invoice() {
        return tic_invoice;
    }

    public void setTic_invoice(String[] tic_invoice) {
        this.tic_invoice = tic_invoice;
    }

    public String[] getHot_invoice() {
        return hot_invoice;
    }

    public void setHot_invoice(String[] hot_invoice) {
        this.hot_invoice = hot_invoice;
    }

    public String[] getTicAttachmentId() {
        return ticAttachmentId;
    }

    public void setTicAttachmentId(String[] ticAttachmentId) {
        this.ticAttachmentId = ticAttachmentId;
    }

    public String[] getHotAttachmentId() {
        return hotAttachmentId;
    }

    public void setHotAttachmentId(String[] hotAttachmentId) {
        this.hotAttachmentId = hotAttachmentId;
    }

    public String[] getTicAttachments() {
        return ticAttachments;
    }

    public void setTicAttachments(String[] ticAttachments) {
        this.ticAttachments = ticAttachments;
    }

    public String[] getHotAttachments() {
        return hotAttachments;
    }

    public void setHotAttachments(String[] hotAttachments) {
        this.hotAttachments = hotAttachments;
    }

    public String[] getHotdelStat() {
        return hotdelStat;
    }

    public void setHotdelStat(String[] hotdelStat) {
        this.hotdelStat = hotdelStat;
    }

    public String[] getTicdelStat() {
        return ticdelStat;
    }

    public void setTicdelStat(String[] ticdelStat) {
        this.ticdelStat = ticdelStat;
    }

    public String getInvoiceStat() {
        return invoiceStat;
    }

    public void setInvoiceStat(String invoiceStat) {
        this.invoiceStat = invoiceStat;
    }

    public String[] getInvoice() {
        return invoice;
    }

    public void setInvoice(String[] invoice) {
        this.invoice = invoice;
    }

    public String getDelstatus() {
        return delstatus;
    }

    public void setDelstatus(String delstatus) {
        this.delstatus = delstatus;
    }

    public String[] getAttachment_deleted() {
        return attachment_deleted;
    }

    public void setAttachment_deleted(String[] attachment_deleted) {
        this.attachment_deleted = attachment_deleted;
    }

    public String[] getPaid_by() {
        return paid_by;
    }

    public void setPaid_by(String[] paid_by) {
        this.paid_by = paid_by;
    }

    public String getPayroll_comments() {
        return payroll_comments;
    }

    public void setPayroll_comments(String payroll_comments) {
        this.payroll_comments = payroll_comments;
    }

    public String getAmount_tobe_recovered() {
        return amount_tobe_recovered;
    }

    public void setAmount_tobe_recovered(String amount_tobe_recovered) {
        this.amount_tobe_recovered = amount_tobe_recovered;
    }

    public String getAmount_tobe_deposited() {
        return amount_tobe_deposited;
    }

    public void setAmount_tobe_deposited(String amount_tobe_deposited) {
        this.amount_tobe_deposited = amount_tobe_deposited;
    }

    public String getTravelCommenced() {
        return travelCommenced;
    }

    public void setTravelCommenced(String travelCommenced) {
        this.travelCommenced = travelCommenced;
    }

    public String getTravelCompleted() {
        return travelCompleted;
    }

    public void setTravelCompleted(String travelCompleted) {
        this.travelCompleted = travelCompleted;
    }

    public String getSettlemet_policy() {
        return settlemet_policy;
    }

    public void setSettlemet_policy(String settlemet_policy) {
        this.settlemet_policy = settlemet_policy;
    }

    public String[] getAttachments() {
        return attachments;
    }

    public void setAttachments(String[] attachments) {
        this.attachments = attachments;
    }

    public String getAdmin_action_date() {
        return admin_action_date;
    }

    public void setAdmin_action_date(String admin_action_date) {
        this.admin_action_date = admin_action_date;
    }

    public String[] getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String[] expenseId) {
        this.expenseId = expenseId;
    }

    public String[] getDelStat() {
        return delStat;
    }

    public void setDelStat(String[] delStat) {
        this.delStat = delStat;
    }

    public String[] getAttachId() {
        return attachId;
    }

    public void setAttachId(String[] attachId) {
        this.attachId = attachId;
    }

    public String[] getAttachdelStat() {
        return attachdelStat;
    }

    public void setAttachdelStat(String[] attachdelStat) {
        this.attachdelStat = attachdelStat;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getEligibiityAmount() {
        return eligibiityAmount;
    }

    public void setEligibiityAmount(String eligibiityAmount) {
        this.eligibiityAmount = eligibiityAmount;
    }

    public String[] getRes_eligibility() {
        return res_eligibility;
    }

    public void setRes_eligibility(String[] res_eligibility) {
        this.res_eligibility = res_eligibility;
    }

    public String getRecoveredDate() {
        return recoveredDate;
    }

    public void setRecoveredDate(String recoveredDate) {
        this.recoveredDate = recoveredDate;
    }

    public String getDepositedDate() {
        return depositedDate;
    }

    public void setDepositedDate(String depositedDate) {
        this.depositedDate = depositedDate;
    }

    public String getTobeDeposited() {
        return tobeDeposited;
    }

    public void setTobeDeposited(String tobeDeposited) {
        this.tobeDeposited = tobeDeposited;
    }

    public String getTobeRecovered() {
        return tobeRecovered;
    }

    public void setTobeRecovered(String tobeRecovered) {
        this.tobeRecovered = tobeRecovered;
    }

    public String getRecoveredAmount() {
        return recoveredAmount;
    }

    public void setRecoveredAmount(String recoveredAmount) {
        this.recoveredAmount = recoveredAmount;
    }

    public String getPayroll_id() {
        return payroll_id;
    }

    public void setPayroll_id(String payroll_id) {
        this.payroll_id = payroll_id;
    }

    public String getPayroll_name() {
        return payroll_name;
    }

    public void setPayroll_name(String payroll_name) {
        this.payroll_name = payroll_name;
    }

    public String getPayroll_remarks() {
        return payroll_remarks;
    }

    public void setPayroll_remarks(String payroll_remarks) {
        this.payroll_remarks = payroll_remarks;
    }

    public String getPayroll_approved_date() {
        return payroll_approved_date;
    }

    public void setPayroll_approved_date(String payroll_approved_date) {
        this.payroll_approved_date = payroll_approved_date;
    }

    public String getTreasury_remarks() {
        return treasury_remarks;
    }

    public void setTreasury_remarks(String treasury_remarks) {
        this.treasury_remarks = treasury_remarks;
    }

    public MultipartFile[] getFile_attachment() {
        return file_attachment;
    }

    public void setFile_attachment(MultipartFile[] file_attachment) {
        this.file_attachment = file_attachment;
    }

    public String[] getFile_category() {
        return file_category;
    }

    public void setFile_category(String[] file_category) {
        this.file_category = file_category;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCfo_id() {
        return cfo_id;
    }

    public void setCfo_id(String cfo_id) {
        this.cfo_id = cfo_id;
    }

    public String getCfo_name() {
        return cfo_name;
    }

    public void setCfo_name(String cfo_name) {
        this.cfo_name = cfo_name;
    }

    public String getCfo_approved_date() {
        return cfo_approved_date;
    }

    public void setCfo_approved_date(String cfo_approved_date) {
        this.cfo_approved_date = cfo_approved_date;
    }

    public String getCfo_remarks() {
        return cfo_remarks;
    }

    public void setCfo_remarks(String cfo_remarks) {
        this.cfo_remarks = cfo_remarks;
    }

    public String getDeviation() {
        return deviation;
    }

    public void setDeviation(String deviation) {
        this.deviation = deviation;
    }

    public String getRm_id() {
        return rm_id;
    }

    public void setRm_id(String rm_id) {
        this.rm_id = rm_id;
    }

    public String getPm_id() {
        return pm_id;
    }

    public void setPm_id(String pm_id) {
        this.pm_id = pm_id;
    }

    public String getRm_name() {
        return rm_name;
    }

    public void setRm_name(String rm_name) {
        this.rm_name = rm_name;
    }

    public String getPm_name() {
        return pm_name;
    }

    public void setPm_name(String pm_name) {
        this.pm_name = pm_name;
    }

    public String getBuh_id() {
        return buh_id;
    }

    public void setBuh_id(String buh_id) {
        this.buh_id = buh_id;
    }

    public String getBuh_name() {
        return buh_name;
    }

    public void setBuh_name(String buh_name) {
        this.buh_name = buh_name;
    }

    public String getTreasury_id() {
        return treasury_id;
    }

    public void setTreasury_id(String treasury_id) {
        this.treasury_id = treasury_id;
    }

    public String getTreasury_name() {
        return treasury_name;
    }

    public void setTreasury_name(String treasury_name) {
        this.treasury_name = treasury_name;
    }

    public String getRm_approved_date() {
        return rm_approved_date;
    }

    public void setRm_approved_date(String rm_approved_date) {
        this.rm_approved_date = rm_approved_date;
    }

    public String getRm_remarks() {
        return rm_remarks;
    }

    public void setRm_remarks(String rm_remarks) {
        this.rm_remarks = rm_remarks;
    }

    public String getBuh_approved_date() {
        return buh_approved_date;
    }

    public void setBuh_approved_date(String buh_approved_date) {
        this.buh_approved_date = buh_approved_date;
    }

    public String getBuh_remarks() {
        return buh_remarks;
    }

    public void setBuh_remarks(String buh_remarks) {
        this.buh_remarks = buh_remarks;
    }

    public String getTreasury_approved_date() {
        return treasury_approved_date;
    }

    public void setTreasury_approved_date(String treasury_approved_date) {
        this.treasury_approved_date = treasury_approved_date;
    }

    public String getTreasury_comments() {
        return treasury_comments;
    }

    public void setTreasury_comments(String treasury_comments) {
        this.treasury_comments = treasury_comments;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_remarks() {
        return admin_remarks;
    }

    public void setAdmin_remarks(String admin_remarks) {
        this.admin_remarks = admin_remarks;
    }

    public String getFinance_id() {
        return finance_id;
    }

    public void setFinance_id(String finance_id) {
        this.finance_id = finance_id;
    }

    public String getFinance_name() {
        return finance_name;
    }

    public void setFinance_name(String finance_name) {
        this.finance_name = finance_name;
    }

    public String getFinance_approved_date() {
        return finance_approved_date;
    }

    public void setFinance_approved_date(String finance_approved_date) {
        this.finance_approved_date = finance_approved_date;
    }

    public String getFinance_remarks() {
        return finance_remarks;
    }

    public void setFinance_remarks(String finance_remarks) {
        this.finance_remarks = finance_remarks;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getCancel_remarks() {
        return cancel_remarks;
    }

    public void setCancel_remarks(String cancel_remarks) {
        this.cancel_remarks = cancel_remarks;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getApprover_type() {
        return approver_type;
    }

    public void setApprover_type(String approver_type) {
        this.approver_type = approver_type;
    }

    public String getList_type() {
        return list_type;
    }

    public void setList_type(String list_type) {
        this.list_type = list_type;
    }

    public String getBorTotExp() {
        return borTotExp;
    }

    public void setBorTotExp(String borTotExp) {
        this.borTotExp = borTotExp;
    }

    public String getTicTotExp() {
        return ticTotExp;
    }

    public void setTicTotExp(String ticTotExp) {
        this.ticTotExp = ticTotExp;
    }

    public String getLodTotExp() {
        return lodTotExp;
    }

    public void setLodTotExp(String lodTotExp) {
        this.lodTotExp = lodTotExp;
    }

    public String getConTotExp() {
        return conTotExp;
    }

    public void setConTotExp(String conTotExp) {
        this.conTotExp = conTotExp;
    }

    public String getMisTotExp() {
        return misTotExp;
    }

    public void setMisTotExp(String misTotExp) {
        this.misTotExp = misTotExp;
    }

    public String getTotTotExp() {
        return totTotExp;
    }

    public void setTotTotExp(String totTotExp) {
        this.totTotExp = totTotExp;
    }

    public String getDeviationPercent() {
        return deviationPercent;
    }

    public void setDeviationPercent(String deviationPercent) {
        this.deviationPercent = deviationPercent;
    }

    public String getLodEliTot() {
        return lodEliTot;
    }

    public void setLodEliTot(String lodEliTot) {
        this.lodEliTot = lodEliTot;
    }

    public String getTicEliTot() {
        return ticEliTot;
    }

    public void setTicEliTot(String ticEliTot) {
        this.ticEliTot = ticEliTot;
    }

    public String getBoarEliTot() {
        return boarEliTot;
    }

    public void setBoarEliTot(String boarEliTot) {
        this.boarEliTot = boarEliTot;
    }

    public String getConEliTot() {
        return conEliTot;
    }

    public void setConEliTot(String conEliTot) {
        this.conEliTot = conEliTot;
    }

    public String getMisEliTot() {
        return misEliTot;
    }

    public void setMisEliTot(String misEliTot) {
        this.misEliTot = misEliTot;
    }

    public String getTotEliTot() {
        return totEliTot;
    }

    public void setTotEliTot(String totEliTot) {
        this.totEliTot = totEliTot;
    }

    public String getLodDeviation() {
        return lodDeviation;
    }

    public void setLodDeviation(String lodDeviation) {
        this.lodDeviation = lodDeviation;
    }

    public String getBorDeviation() {
        return borDeviation;
    }

    public void setBorDeviation(String borDeviation) {
        this.borDeviation = borDeviation;
    }

    public String getTotDeviation() {
        return totDeviation;
    }

    public void setTotDeviation(String totDeviation) {
        this.totDeviation = totDeviation;
    }

    public String getMisDeviation() {
        return misDeviation;
    }

    public void setMisDeviation(String misDeviation) {
        this.misDeviation = misDeviation;
    }

    public String getSettlementAdded() {
        return settlementAdded;
    }

    public void setSettlementAdded(String settlementAdded) {
        this.settlementAdded = settlementAdded;
    }
    private String res_BillNo[];
    private String res_amount[];
    private String res_remarks[];
    private String totalDays;
    private String submittedDate;
    private String settlementAdded;

    public String[] getRes_tic_remarks() {
        return res_tic_remarks;
    }

    public void setRes_tic_remarks(String[] res_tic_remarks) {
        this.res_tic_remarks = res_tic_remarks;
    }

    public String[] getRes_hot_remarks() {
        return res_hot_remarks;
    }

    public void setRes_hot_remarks(String[] res_hot_remarks) {
        this.res_hot_remarks = res_hot_remarks;
    }

    public String[] getRes_con_remarks() {
        return res_con_remarks;
    }

    public void setRes_con_remarks(String[] res_con_remarks) {
        this.res_con_remarks = res_con_remarks;
    }
    private String lodtotal;
    private String boartotal;
    private String convtotal;
    private String mistotal;
    private String totalAmount;
    private String travelPeriod;
    private String countryId;
    private String keyRes;
    private String valueRes;
    private String configuration_key;
    private String configuration_value;
    private String ticTaxtotal;
    private String lodTaxtotal;
    private String tic_tot_tax_total;
    private String lod_tot_tax_total;
    private String actual_lodging_tax_total;
    private String actual_ticket_tax;
    private String actual_lodging_tax;
    private String actual_ticket_tax_total;
    private String actual_boarding_tax;
    private String actual_boarding_tax_total;
    private String actual_conveyance_tax;
    private String actual_conveyance_tax_total;
    private String actual_miscellaneous_tax;
    private String actual_miscellaneous_tax_total;
    private String company_lodging_tax;
    private String company_lodging_tax_total;
    private String company_ticket_tax;
    private String company_ticket_tax_total;
    private String company_boarding_tax;
    private String company_boarding_tax_total;
    private String company_conveyance_tax;
    private String company_conveyance_tax_total;
    private String company_miscellaneous_tax;
    private String company_miscellaneous_tax_total;
    private String company_total_tax;
    private String company_total_tax_total;
    private String actual_total_tax;
    private String actual_total_tax_total;
    private String company_total_amount;

    public String getActual_lodging_tax() {
        return actual_lodging_tax;
    }

    public void setActual_lodging_tax(String actual_lodging_tax) {
        this.actual_lodging_tax = actual_lodging_tax;
    }

    public String getCompany_total_amount() {
        return company_total_amount;
    }

    public void setCompany_total_amount(String company_total_amount) {
        this.company_total_amount = company_total_amount;
    }

    public String getCompany_total_tax() {
        return company_total_tax;
    }

    public void setCompany_total_tax(String company_total_tax) {
        this.company_total_tax = company_total_tax;
    }

    public String getCompany_total_tax_total() {
        return company_total_tax_total;
    }

    public void setCompany_total_tax_total(String company_total_tax_total) {
        this.company_total_tax_total = company_total_tax_total;
    }

    public String getActual_total_tax() {
        return actual_total_tax;
    }

    public void setActual_total_tax(String actual_total_tax) {
        this.actual_total_tax = actual_total_tax;
    }

    public String getActual_total_tax_total() {
        return actual_total_tax_total;
    }

    public void setActual_total_tax_total(String actual_total_tax_total) {
        this.actual_total_tax_total = actual_total_tax_total;
    }

    public String getActual_lodging_tax_total() {
        return actual_lodging_tax_total;
    }

    public void setActual_lodging_tax_total(String actual_lodging_tax_total) {
        this.actual_lodging_tax_total = actual_lodging_tax_total;
    }

    public String getActual_ticket_tax() {
        return actual_ticket_tax;
    }

    public void setActual_ticket_tax(String actual_ticket_tax) {
        this.actual_ticket_tax = actual_ticket_tax;
    }

    public String getActual_ticket_tax_total() {
        return actual_ticket_tax_total;
    }

    public void setActual_ticket_tax_total(String actual_ticket_tax_total) {
        this.actual_ticket_tax_total = actual_ticket_tax_total;
    }

    public String getActual_boarding_tax() {
        return actual_boarding_tax;
    }

    public void setActual_boarding_tax(String actual_boarding_tax) {
        this.actual_boarding_tax = actual_boarding_tax;
    }

    public String getActual_boarding_tax_total() {
        return actual_boarding_tax_total;
    }

    public void setActual_boarding_tax_total(String actual_boarding_tax_total) {
        this.actual_boarding_tax_total = actual_boarding_tax_total;
    }

    public String getActual_conveyance_tax() {
        return actual_conveyance_tax;
    }

    public void setActual_conveyance_tax(String actual_conveyance_tax) {
        this.actual_conveyance_tax = actual_conveyance_tax;
    }

    public String getActual_conveyance_tax_total() {
        return actual_conveyance_tax_total;
    }

    public void setActual_conveyance_tax_total(String actual_conveyance_tax_total) {
        this.actual_conveyance_tax_total = actual_conveyance_tax_total;
    }

    public String getActual_miscellaneous_tax() {
        return actual_miscellaneous_tax;
    }

    public void setActual_miscellaneous_tax(String actual_miscellaneous_tax) {
        this.actual_miscellaneous_tax = actual_miscellaneous_tax;
    }

    public String getActual_miscellaneous_tax_total() {
        return actual_miscellaneous_tax_total;
    }

    public void setActual_miscellaneous_tax_total(String actual_miscellaneous_tax_total) {
        this.actual_miscellaneous_tax_total = actual_miscellaneous_tax_total;
    }

    public String getCompany_lodging_tax() {
        return company_lodging_tax;
    }

    public void setCompany_lodging_tax(String company_lodging_tax) {
        this.company_lodging_tax = company_lodging_tax;
    }

    public String getCompany_lodging_tax_total() {
        return company_lodging_tax_total;
    }

    public void setCompany_lodging_tax_total(String company_lodging_tax_total) {
        this.company_lodging_tax_total = company_lodging_tax_total;
    }

    public String getCompany_ticket_tax() {
        return company_ticket_tax;
    }

    public void setCompany_ticket_tax(String company_ticket_tax) {
        this.company_ticket_tax = company_ticket_tax;
    }

    public String getCompany_ticket_tax_total() {
        return company_ticket_tax_total;
    }

    public void setCompany_ticket_tax_total(String company_ticket_tax_total) {
        this.company_ticket_tax_total = company_ticket_tax_total;
    }

    public String getCompany_boarding_tax() {
        return company_boarding_tax;
    }

    public void setCompany_boarding_tax(String company_boarding_tax) {
        this.company_boarding_tax = company_boarding_tax;
    }

    public String getCompany_boarding_tax_total() {
        return company_boarding_tax_total;
    }

    public void setCompany_boarding_tax_total(String company_boarding_tax_total) {
        this.company_boarding_tax_total = company_boarding_tax_total;
    }

    public String getCompany_conveyance_tax() {
        return company_conveyance_tax;
    }

    public void setCompany_conveyance_tax(String company_conveyance_tax) {
        this.company_conveyance_tax = company_conveyance_tax;
    }

    public String getCompany_conveyance_tax_total() {
        return company_conveyance_tax_total;
    }

    public void setCompany_conveyance_tax_total(String company_conveyance_tax_total) {
        this.company_conveyance_tax_total = company_conveyance_tax_total;
    }

    public String getCompany_miscellaneous_tax() {
        return company_miscellaneous_tax;
    }

    public void setCompany_miscellaneous_tax(String company_miscellaneous_tax) {
        this.company_miscellaneous_tax = company_miscellaneous_tax;
    }

    public String getCompany_miscellaneous_tax_total() {
        return company_miscellaneous_tax_total;
    }

    public void setCompany_miscellaneous_tax_total(String company_miscellaneous_tax_total) {
        this.company_miscellaneous_tax_total = company_miscellaneous_tax_total;
    }

    public String getTicTaxtotal() {
        return ticTaxtotal;
    }

    public void setTicTaxtotal(String ticTaxtotal) {
        this.ticTaxtotal = ticTaxtotal;
    }

    public String getLodTaxtotal() {
        return lodTaxtotal;
    }

    public void setLodTaxtotal(String lodTaxtotal) {
        this.lodTaxtotal = lodTaxtotal;
    }

    public String getTic_tot_tax_total() {
        return tic_tot_tax_total;
    }

    public void setTic_tot_tax_total(String tic_tot_tax_total) {
        this.tic_tot_tax_total = tic_tot_tax_total;
    }

    public String getLod_tot_tax_total() {
        return lod_tot_tax_total;
    }

    public void setLod_tot_tax_total(String lod_tot_tax_total) {
        this.lod_tot_tax_total = lod_tot_tax_total;
    }

    public String getConfiguration_key() {
        return configuration_key;
    }

    public void setConfiguration_key(String configuration_key) {
        this.configuration_key = configuration_key;
    }

    public String getConfiguration_value() {
        return configuration_value;
    }

    public void setConfiguration_value(String configuration_value) {
        this.configuration_value = configuration_value;
    }

    public String getKeyRes() {
        return keyRes;
    }

    public void setKeyRes(String keyRes) {
        this.keyRes = keyRes;
    }

    public String getValueRes() {
        return valueRes;
    }

    public void setValueRes(String valueRes) {
        this.valueRes = valueRes;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getTravelPeriod() {
        return travelPeriod;
    }

    public void setTravelPeriod(String travelPeriod) {
        this.travelPeriod = travelPeriod;
    }

//    public String getTictotal() {
//        return tictotal;
//    }
//
//    public void setTictotal(String tictotal) {
//        this.tictotal = tictotal;
//    }
    public String getLodtotal() {
        return lodtotal;
    }

    public void setLodtotal(String lodtotal) {
        this.lodtotal = lodtotal;
    }

    public String getBoartotal() {
        return boartotal;
    }

    public void setBoartotal(String boartotal) {
        this.boartotal = boartotal;
    }

    public String getConvtotal() {
        return convtotal;
    }

    public void setConvtotal(String convtotal) {
        this.convtotal = convtotal;
    }

    public String getMistotal() {
        return mistotal;
    }

    public void setMistotal(String mistotal) {
        this.mistotal = mistotal;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getLodgingTotal() {
        return lodgingTotal;
    }

    public void setLodgingTotal(String lodgingTotal) {
        this.lodgingTotal = lodgingTotal;
    }

    public String getMiscTotal() {
        return miscTotal;
    }

    public void setMiscTotal(String miscTotal) {
        this.miscTotal = miscTotal;
    }

    public String getTotalExpance() {
        return totalExpance;
    }

    public void setTotalExpance(String totalExpance) {
        this.totalExpance = totalExpance;
    }

    public String[] getRes_city() {
        return res_city;
    }

    public void setRes_city(String[] res_city) {
        this.res_city = res_city;
    }

    public String[] getRes_fromDate() {
        return res_fromDate;
    }

    public void setRes_fromDate(String[] res_fromDate) {
        this.res_fromDate = res_fromDate;
    }

    public String[] getRes_toDate() {
        return res_toDate;
    }

    public void setRes_toDate(String[] res_toDate) {
        this.res_toDate = res_toDate;
    }

    public String[] getRes_bill() {
        return res_bill;
    }

    public void setRes_bill(String[] res_bill) {
        this.res_bill = res_bill;
    }

    public String[] getRes_billDate() {
        return res_billDate;
    }

    public void setRes_billDate(String[] res_billDate) {
        this.res_billDate = res_billDate;
    }

    public String[] getRes_category() {
        return res_category;
    }

    public void setRes_category(String[] res_category) {
        this.res_category = res_category;
    }

    public String[] getRes_BillNo() {
        return res_BillNo;
    }

    public void setRes_BillNo(String[] res_BillNo) {
        this.res_BillNo = res_BillNo;
    }

    public String[] getRes_amount() {
        return res_amount;
    }

    public void setRes_amount(String[] res_amount) {
        this.res_amount = res_amount;
    }

    public String[] getRes_remarks() {
        return res_remarks;
    }

    public void setRes_remarks(String[] res_remarks) {
        this.res_remarks = res_remarks;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getTotalAdvance() {
        return totalAdvance;
    }

    public void setTotalAdvance(String totalAdvance) {
        this.totalAdvance = totalAdvance;
    }

    public String getAdvanceId() {
        return advanceId;
    }

    public void setAdvanceId(String advanceId) {
        this.advanceId = advanceId;
    }

    public String getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(String requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String[] getRes_tic_fdate() {
        return res_tic_fdate;
    }

    public void setRes_tic_fdate(String[] res_tic_fdate) {
        this.res_tic_fdate = res_tic_fdate;
    }

    public String[] getRes_con_category() {
        return res_con_category;
    }

    public void setRes_con_category(String[] res_con_category) {
        this.res_con_category = res_con_category;
    }

    public String[] getRes_con_city() {
        return res_con_city;
    }

    public void setRes_con_city(String[] res_con_city) {
        this.res_con_city = res_con_city;
    }

    public String[] getRes_con_fdate() {
        return res_con_fdate;
    }

    public void setRes_con_fdate(String[] res_con_fdate) {
        this.res_con_fdate = res_con_fdate;
    }

    public String[] getRes_con_tdate() {
        return res_con_tdate;
    }

    public void setRes_con_tdate(String[] res_con_tdate) {
        this.res_con_tdate = res_con_tdate;
    }

    public String[] getTic_deleted() {
        return tic_deleted;
    }

    public void setTic_deleted(String[] tic_deleted) {
        this.tic_deleted = tic_deleted;
    }

    public String[] getHot_deleted() {
        return hot_deleted;
    }

    public void setHot_deleted(String[] hot_deleted) {
        this.hot_deleted = hot_deleted;
    }

    public String[] getCon_deleted() {
        return con_deleted;
    }

    public void setCon_deleted(String[] con_deleted) {
        this.con_deleted = con_deleted;
    }

    public String getSettlementBy() {
        return settlementBy;
    }

    public void setSettlementBy(String settlementBy) {
        this.settlementBy = settlementBy;
    }

    public String getDepositedAmount() {
        return depositedAmount;
    }

    public void setDepositedAmount(String depositedAmount) {
        this.depositedAmount = depositedAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTp_settlement_id() {
        return tp_settlement_id;
    }

    public void setTp_settlement_id(String tp_settlement_id) {
        this.tp_settlement_id = tp_settlement_id;
    }

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public MultipartFile[] getRes_tic_attachment() {
        return res_tic_attachment;
    }

    public void setRes_tic_attachment(MultipartFile[] res_tic_attachment) {
        this.res_tic_attachment = res_tic_attachment;
    }

    public MultipartFile[] getRes_hot_attachment() {
        return res_hot_attachment;
    }

    public void setRes_hot_attachment(MultipartFile[] res_hot_attachment) {
        this.res_hot_attachment = res_hot_attachment;
    }

    public MultipartFile[] getRes_con_attachment() {
        return res_con_attachment;
    }

    public void setRes_con_attachment(MultipartFile[] res_con_attachment) {
        this.res_con_attachment = res_con_attachment;
    }

    public String[] getRes_con_billDate() {
        return res_con_billDate;
    }

    public void setRes_con_billDate(String[] res_con_billDate) {
        this.res_con_billDate = res_con_billDate;
    }

    public String[] getRes_con_billNo() {
        return res_con_billNo;
    }

    public void setRes_con_billNo(String[] res_con_billNo) {
        this.res_con_billNo = res_con_billNo;
    }

    public String getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(String lastInsertId) {
        this.lastInsertId = lastInsertId;
    }

    public String[] getRes_con_billAmount() {
        return res_con_billAmount;
    }
// adding eligibiity     
    private String actual_lodging_amount;
    private String actual_ticket_amount;
    private String actual_boarding_amount;
    private String actual_conveyance_amount;
    private String actual_miscellaneous_amount;
    private String actual_total_amount;
    private String total_boarding_amount;
    private String total_ticket_amount;
    private String total_lodging_amount;
    private String total_conveyance_amount;
    private String total_miscellaneous_amount;
    private String overall_total_amount;
    private String deviation_lodging_amount;
    private String deviation_ticket_amount;
    private String deviation_boarding_amount;
    private String deviation_conveyance_amount;
    private String deviation_miscellaneous_amount;
    private String deviation_total_amount;
    private String overall_deviation_percentage;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getActual_lodging_amount() {
        return actual_lodging_amount;
    }

    public void setActual_lodging_amount(String actual_lodging_amount) {
        this.actual_lodging_amount = actual_lodging_amount;
    }

    public String getActual_ticket_amount() {
        return actual_ticket_amount;
    }

    public void setActual_ticket_amount(String actual_ticket_amount) {
        this.actual_ticket_amount = actual_ticket_amount;
    }

    public String getActual_boarding_amount() {
        return actual_boarding_amount;
    }

    public void setActual_boarding_amount(String actual_boarding_amount) {
        this.actual_boarding_amount = actual_boarding_amount;
    }

    public String getActual_conveyance_amount() {
        return actual_conveyance_amount;
    }

    public void setActual_conveyance_amount(String actual_conveyance_amount) {
        this.actual_conveyance_amount = actual_conveyance_amount;
    }

    public String getActual_miscellaneous_amount() {
        return actual_miscellaneous_amount;
    }

    public void setActual_miscellaneous_amount(String actual_miscellaneous_amount) {
        this.actual_miscellaneous_amount = actual_miscellaneous_amount;
    }

    public String getActual_total_amount() {
        return actual_total_amount;
    }

    public void setActual_total_amount(String actual_total_amount) {
        this.actual_total_amount = actual_total_amount;
    }

    public String getTotal_boarding_amount() {
        return total_boarding_amount;
    }

    public void setTotal_boarding_amount(String total_boarding_amount) {
        this.total_boarding_amount = total_boarding_amount;
    }

    public String getTotal_ticket_amount() {
        return total_ticket_amount;
    }

    public void setTotal_ticket_amount(String total_ticket_amount) {
        this.total_ticket_amount = total_ticket_amount;
    }

    public String getTotal_lodging_amount() {
        return total_lodging_amount;
    }

    public void setTotal_lodging_amount(String total_lodging_amount) {
        this.total_lodging_amount = total_lodging_amount;
    }

    public String getTotal_conveyance_amount() {
        return total_conveyance_amount;
    }

    public void setTotal_conveyance_amount(String total_conveyance_amount) {
        this.total_conveyance_amount = total_conveyance_amount;
    }

    public String getTotal_miscellaneous_amount() {
        return total_miscellaneous_amount;
    }

    public void setTotal_miscellaneous_amount(String total_miscellaneous_amount) {
        this.total_miscellaneous_amount = total_miscellaneous_amount;
    }

    public String getOverall_total_amount() {
        return overall_total_amount;
    }

    public void setOverall_total_amount(String overall_total_amount) {
        this.overall_total_amount = overall_total_amount;
    }

    public String getDeviation_lodging_amount() {
        return deviation_lodging_amount;
    }

    public void setDeviation_lodging_amount(String deviation_lodging_amount) {
        this.deviation_lodging_amount = deviation_lodging_amount;
    }

    public String getDeviation_ticket_amount() {
        return deviation_ticket_amount;
    }

    public void setDeviation_ticket_amount(String deviation_ticket_amount) {
        this.deviation_ticket_amount = deviation_ticket_amount;
    }

    public String getDeviation_boarding_amount() {
        return deviation_boarding_amount;
    }

    public void setDeviation_boarding_amount(String deviation_boarding_amount) {
        this.deviation_boarding_amount = deviation_boarding_amount;
    }

    public String getDeviation_conveyance_amount() {
        return deviation_conveyance_amount;
    }

    public void setDeviation_conveyance_amount(String deviation_conveyance_amount) {
        this.deviation_conveyance_amount = deviation_conveyance_amount;
    }

    public String getDeviation_miscellaneous_amount() {
        return deviation_miscellaneous_amount;
    }

    public void setDeviation_miscellaneous_amount(String deviation_miscellaneous_amount) {
        this.deviation_miscellaneous_amount = deviation_miscellaneous_amount;
    }

    public String getDeviation_total_amount() {
        return deviation_total_amount;
    }

    public void setDeviation_total_amount(String deviation_total_amount) {
        this.deviation_total_amount = deviation_total_amount;
    }

    public String getOverall_deviation_percentage() {
        return overall_deviation_percentage;
    }

    public void setOverall_deviation_percentage(String overall_deviation_percentage) {
        this.overall_deviation_percentage = overall_deviation_percentage;
    }

    public void setRes_con_billAmount(String[] res_con_billAmount) {
        this.res_con_billAmount = res_con_billAmount;
    }

    public String getTicketTotal() {
        return ticketTotal;
    }

    public void setTicketTotal(String ticketTotal) {
        this.ticketTotal = ticketTotal;
    }

    public String getBoardingTotal() {
        return boardingTotal;
    }

    public void setBoardingTotal(String boardingTotal) {
        this.boardingTotal = boardingTotal;
    }

    public String getConveyanceTotal() {
        return conveyanceTotal;
    }

    public void setConveyanceTotal(String conveyanceTotal) {
        this.conveyanceTotal = conveyanceTotal;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getRes_tic_billDate() {
        return res_tic_billDate;
    }

    public void setRes_tic_billDate(String[] res_tic_billDate) {
        this.res_tic_billDate = res_tic_billDate;
    }

    public String[] getRes_tic_category() {
        return res_tic_category;
    }

    public void setRes_tic_category(String[] res_tic_category) {
        this.res_tic_category = res_tic_category;
    }

    public String[] getRes_tic_fcity() {
        return res_tic_fcity;
    }

    public void setRes_tic_fcity(String[] res_tic_fcity) {
        this.res_tic_fcity = res_tic_fcity;
    }

    public String[] getRes_tic_tcity() {
        return res_tic_tcity;
    }

    public void setRes_tic_tcity(String[] res_tic_tcity) {
        this.res_tic_tcity = res_tic_tcity;
    }

    public String[] getRes_tic_billNo() {
        return res_tic_billNo;
    }

    public void setRes_tic_billNo(String[] res_tic_billNo) {
        this.res_tic_billNo = res_tic_billNo;
    }

    public String[] getRes_tic_billAmount() {
        return res_tic_billAmount;
    }

    public void setRes_tic_billAmount(String[] res_tic_billAmount) {
        this.res_tic_billAmount = res_tic_billAmount;
    }

    public String[] getRes_tic_tdate() {
        return res_tic_tdate;
    }

    public void setRes_tic_tdate(String[] res_tic_tdate) {
        this.res_tic_tdate = res_tic_tdate;
    }

    public String[] getRes_hot_category() {
        return res_hot_category;
    }

    public void setRes_hot_category(String[] res_hot_category) {
        this.res_hot_category = res_hot_category;
    }

    public String[] getRes_hot_city() {
        return res_hot_city;
    }

    public void setRes_hot_city(String[] res_hot_city) {
        this.res_hot_city = res_hot_city;
    }

    public String[] getRes_hot_fdate() {
        return res_hot_fdate;
    }

    public void setRes_hot_fdate(String[] res_hot_fdate) {
        this.res_hot_fdate = res_hot_fdate;
    }

    public String[] getRes_hot_tdate() {
        return res_hot_tdate;
    }

    public void setRes_hot_tdate(String[] res_hot_tdate) {
        this.res_hot_tdate = res_hot_tdate;
    }

    public String[] getRes_hot_status() {
        return res_hot_status;
    }

    public void setRes_hot_status(String[] res_hot_status) {
        this.res_hot_status = res_hot_status;
    }

    public String[] getRes_hot_billDate() {
        return res_hot_billDate;
    }

    public void setRes_hot_billDate(String[] res_hot_billDate) {
        this.res_hot_billDate = res_hot_billDate;
    }

    public String[] getRes_hot_billNo() {
        return res_hot_billNo;
    }

    public void setRes_hot_billNo(String[] res_hot_billNo) {
        this.res_hot_billNo = res_hot_billNo;
    }

    public String[] getRes_hot_billAmount() {
        return res_hot_billAmount;
    }

    public void setRes_hot_billAmount(String[] res_hot_billAmount) {
        this.res_hot_billAmount = res_hot_billAmount;
    }

    public String[] getRes_tic_id() {
        return res_tic_id;
    }

    public void setRes_tic_id(String[] res_tic_id) {
        this.res_tic_id = res_tic_id;
    }

    public String[] getRes_hot_id() {
        return res_hot_id;
    }

    public void setRes_hot_id(String[] res_hot_id) {
        this.res_hot_id = res_hot_id;
    }

    public String[] getRes_con_id() {
        return res_con_id;
    }

    public void setRes_con_id(String[] res_con_id) {
        this.res_con_id = res_con_id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getOfcityId() {
        return ofcityId;
    }

    public void setOfcityId(String ofcityId) {
        this.ofcityId = ofcityId;
    }

    public String getOfcityName() {
        return ofcityName;
    }

    public void setOfcityName(String ofcityName) {
        this.ofcityName = ofcityName;
    }

    public String getOtcityId() {
        return otcityId;
    }

    public void setOtcityId(String otcityId) {
        this.otcityId = otcityId;
    }

    public String getOtcityName() {
        return otcityName;
    }

    public void setOtcityName(String otcityName) {
        this.otcityName = otcityName;
    }

    public String getAdminAction() {
        return adminAction;
    }

    public void setAdminAction(String adminAction) {
        this.adminAction = adminAction;
    }

    public String getFcityId() {
        return fcityId;
    }

    public void setFcityId(String fcityId) {
        this.fcityId = fcityId;
    }

    public String getTcityId() {
        return tcityId;
    }

    public void setTcityId(String tcityId) {
        this.tcityId = tcityId;
    }

    public String getIsSettlementAdded() {
        return isSettlementAdded;
    }

    public void setIsSettlementAdded(String isSettlementAdded) {
        this.isSettlementAdded = isSettlementAdded;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getTravelTerm() {
        return travelTerm;
    }

    public void setTravelTerm(String travelTerm) {
        this.travelTerm = travelTerm;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(String requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getTravelStartDate() {
        return travelStartDate;
    }

    public void setTravelStartDate(String travelStartDate) {
        this.travelStartDate = travelStartDate;
    }

    public String getTravelEndDate() {
        return travelEndDate;
    }

    public void setTravelEndDate(String travelEndDate) {
        this.travelEndDate = travelEndDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getClientReimbursable() {
        return clientReimbursable;
    }

    public void setClientReimbursable(String clientReimbursable) {
        this.clientReimbursable = clientReimbursable;
    }

    public String getProjectTravel() {
        return projectTravel;
    }

    public void setProjectTravel(String projectTravel) {
        this.projectTravel = projectTravel;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public String getSubPracticeGroupId() {
        return subPracticeGroupId;
    }

    public void setSubPracticeGroupId(String subPracticeGroupId) {
        this.subPracticeGroupId = subPracticeGroupId;
    }

    public String getSubPracticeGroupName() {
        return subPracticeGroupName;
    }

    public void setSubPracticeGroupName(String subPracticeGroupName) {
        this.subPracticeGroupName = subPracticeGroupName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getTravelPoints() {
        return travelPoints;
    }

    public void setTravelPoints(String travelPoints) {
        this.travelPoints = travelPoints;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getTicketRefId() {
        return ticketRefId;
    }

    public void setTicketRefId(String ticketRefId) {
        this.ticketRefId = ticketRefId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignationId() {
        return designationId;
    }

    public void setDesignationId(String designationId) {
        this.designationId = designationId;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public String getBusinessGroupId() {
        return businessGroupId;
    }

    public void setBusinessGroupId(String businessGroupId) {
        this.businessGroupId = businessGroupId;
    }

    public String getBusinessGroupName() {
        return businessGroupName;
    }

    public void setBusinessGroupName(String businessGroupName) {
        this.businessGroupName = businessGroupName;
    }

    public String getPracticeGroupId() {
        return practiceGroupId;
    }

    public void setPracticeGroupId(String practiceGroupId) {
        this.practiceGroupId = practiceGroupId;
    }

    public String getPracticeGroupName() {
        return practiceGroupName;
    }

    public void setPracticeGroupName(String practiceGroupName) {
        this.practiceGroupName = practiceGroupName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getBillReceived() {
        return billReceived;
    }

    public void setBillReceived(String billReceived) {
        this.billReceived = billReceived;
    }

    public String getBillReceivedDate() {
        return billReceivedDate;
    }

    public void setBillReceivedDate(String billReceivedDate) {
        this.billReceivedDate = billReceivedDate;
    }
    
}
