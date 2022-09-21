/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.dto;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 16221
 */
public class ProcurementDto {
    private String id;
    private String pp_code;
    private String requestorId;
    private String employee_name;
    private String sbuId;
    private String subSbuId;
    private int requestor_id;
    private String requested_date;
    private String requestor_name;
    private int sbu_id;
    private int sub_sbu_id;
    private String sbu_name;
    private String sub_sbu_name;
    private String rm_id;
    private String rm_name;
    private String rm_approved_date;
    private String buh_id;
    private String buh_name;
    private String buh_approved_date;
    private String procurement_type_id;
    private String procurement_type_name;
    private String billable_id;
    private String billable_name;
    private String service_id;
    private String service_type;
    private String order_id;
    private String order_name;
    private String recipient_name;
    private String delivery_location_id;
    private String delivery_location_name;
    private String delivery_address;
    private String recipient_contact_number;
    private String recipient_email_id;
    private String expected_delivery_date;
    private String remarks;
    private String status;
    private String status_name;
    private String total;
    private String lastInsertId;
    private String rm_comments;
    private String buh_comments;
    private String iteam_id;
    private String iteam_description;
    private String iteam_quantity;
    private String iteam_amount;
    private String iteam_deleted;
    private String currency_id;
    private String currency_name;
    private String attachment_id;
    private String attachment_name;
    private String attachment_description;
    private String deleted;
    private String attachmentType;
    private String file_upload_id;
    
    private String[] iteams_ids;
    private String[] iteams_description;
    private String[] iteams_quantity;
    private String[] iteams_amount;
    private String[] iteams_deleted;
    
    private String[] attachments_id;
    private String[] attachments_name;
    private String[] attachments_description;
    private String[] attachments_type;
    private String[] attachments_deleted;
    private MultipartFile attachmentValue1;
    private MultipartFile attachmentValue2;
    private MultipartFile attachmentValue3;
    private MultipartFile attachmentValue4;
    private MultipartFile attachmentValue5;

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_name() {
        return currency_name;
    }

    public void setCurrency_name(String currency_name) {
        this.currency_name = currency_name;
    }
            
    public String getRm_comments() {
        return rm_comments;
    }

    public void setRm_comments(String rm_comments) {
        this.rm_comments = rm_comments;
    }

    public String getBuh_comments() {
        return buh_comments;
    }

    public void setBuh_comments(String buh_comments) {
        this.buh_comments = buh_comments;
    }
    
    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getFile_upload_id() {
        return file_upload_id;
    }

    public void setFile_upload_id(String file_upload_id) {
        this.file_upload_id = file_upload_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
    
    public MultipartFile getAttachmentValue1() {
        return attachmentValue1;
    }

    public void setAttachmentValue1(MultipartFile attachmentValue1) {
        this.attachmentValue1 = attachmentValue1;
    }

    public MultipartFile getAttachmentValue2() {
        return attachmentValue2;
    }

    public void setAttachmentValue2(MultipartFile attachmentValue2) {
        this.attachmentValue2 = attachmentValue2;
    }

    public MultipartFile getAttachmentValue3() {
        return attachmentValue3;
    }

    public void setAttachmentValue3(MultipartFile attachmentValue3) {
        this.attachmentValue3 = attachmentValue3;
    }

    public MultipartFile getAttachmentValue4() {
        return attachmentValue4;
    }

    public void setAttachmentValue4(MultipartFile attachmentValue4) {
        this.attachmentValue4 = attachmentValue4;
    }

    public MultipartFile getAttachmentValue5() {
        return attachmentValue5;
    }

    public void setAttachmentValue5(MultipartFile attachmentValue5) {
        this.attachmentValue5 = attachmentValue5;
    }
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPp_code() {
        return pp_code;
    }

    public void setPp_code(String pp_code) {
        this.pp_code = pp_code;
    }

    public String getIteam_deleted() {
        return iteam_deleted;
    }

    public void setIteam_deleted(String iteam_deleted) {
        this.iteam_deleted = iteam_deleted;
    }
    
    public String getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    public String getSbuId() {
        return sbuId;
    }

    public void setSbuId(String sbuId) {
        this.sbuId = sbuId;
    }

    public String getSubSbuId() {
        return subSbuId;
    }

    public void setSubSbuId(String subSbuId) {
        this.subSbuId = subSbuId;
    }
    
    public int getRequestor_id() {
        return requestor_id;
    }

    public void setRequestor_id(int requestor_id) {
        this.requestor_id = requestor_id;
    }

    public String getRequested_date() {
        return requested_date;
    }

    public void setRequested_date(String requested_date) {
        this.requested_date = requested_date;
    }

    public String getRequestor_name() {
        return requestor_name;
    }

    public void setRequestor_name(String requestor_name) {
        this.requestor_name = requestor_name;
    }

    public int getSbu_id() {
        return sbu_id;
    }

    public void setSbu_id(int sbu_id) {
        this.sbu_id = sbu_id;
    }

    public int getSub_sbu_id() {
        return sub_sbu_id;
    }

    public void setSub_sbu_id(int sub_sbu_id) {
        this.sub_sbu_id = sub_sbu_id;
    }
        
    public String getSbu_name() {
        return sbu_name;
    }

    public void setSbu_name(String sbu_name) {
        this.sbu_name = sbu_name;
    }

    public String getSub_sbu_name() {
        return sub_sbu_name;
    }

    public void setSub_sbu_name(String sub_sbu_name) {
        this.sub_sbu_name = sub_sbu_name;
    }

    public String getRm_id() {
        return rm_id;
    }

    public void setRm_id(String rm_id) {
        this.rm_id = rm_id;
    }

    public String getRm_name() {
        return rm_name;
    }

    public void setRm_name(String rm_name) {
        this.rm_name = rm_name;
    }

    public String getRm_approved_date() {
        return rm_approved_date;
    }

    public void setRm_approved_date(String rm_approved_date) {
        this.rm_approved_date = rm_approved_date;
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

    public String getBuh_approved_date() {
        return buh_approved_date;
    }

    public void setBuh_approved_date(String buh_approved_date) {
        this.buh_approved_date = buh_approved_date;
    }

    public String getProcurement_type_id() {
        return procurement_type_id;
    }

    public void setProcurement_type_id(String procurement_type_id) {
        this.procurement_type_id = procurement_type_id;
    }

    public String getProcurement_type_name() {
        return procurement_type_name;
    }

    public void setProcurement_type_name(String procurement_type_name) {
        this.procurement_type_name = procurement_type_name;
    }

    public String getBillable_id() {
        return billable_id;
    }

    public void setBillable_id(String billable_id) {
        this.billable_id = billable_id;
    }

    public String getBillable_name() {
        return billable_name;
    }

    public void setBillable_name(String billable_name) {
        this.billable_name = billable_name;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getDelivery_location_id() {
        return delivery_location_id;
    }

    public void setDelivery_location_id(String delivery_location_id) {
        this.delivery_location_id = delivery_location_id;
    }

    public String getDelivery_location_name() {
        return delivery_location_name;
    }

    public void setDelivery_location_name(String delivery_location_name) {
        this.delivery_location_name = delivery_location_name;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getRecipient_contact_number() {
        return recipient_contact_number;
    }

    public void setRecipient_contact_number(String recipient_contact_number) {
        this.recipient_contact_number = recipient_contact_number;
    }

    public String getRecipient_email_id() {
        return recipient_email_id;
    }

    public void setRecipient_email_id(String recipient_email_id) {
        this.recipient_email_id = recipient_email_id;
    }

    public String getExpected_delivery_date() {
        return expected_delivery_date;
    }

    public void setExpected_delivery_date(String expected_delivery_date) {
        this.expected_delivery_date = expected_delivery_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
    
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLastInsertId() {
        return lastInsertId;
    }

    public void setLastInsertId(String lastInsertId) {
        this.lastInsertId = lastInsertId;
    }
    
    public String getIteam_description() {
        return iteam_description;
    }

    public void setIteam_description(String iteam_description) {
        this.iteam_description = iteam_description;
    }

    public String getIteam_quantity() {
        return iteam_quantity;
    }

    public void setIteam_quantity(String iteam_quantity) {
        this.iteam_quantity = iteam_quantity;
    }

    public String getIteam_amount() {
        return iteam_amount;
    }

    public void setIteam_amount(String iteam_amount) {
        this.iteam_amount = iteam_amount;
    }

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public String getAttachment_description() {
        return attachment_description;
    }

    public void setAttachment_description(String attachment_description) {
        this.attachment_description = attachment_description;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getIteam_id() {
        return iteam_id;
    }

    public void setIteam_id(String iteam_id) {
        this.iteam_id = iteam_id;
    }

    public String getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(String attachment_id) {
        this.attachment_id = attachment_id;
    }

    public String[] getIteams_ids() {
        return iteams_ids;
    }

    public void setIteams_ids(String[] iteams_ids) {
        this.iteams_ids = iteams_ids;
    }

    public String[] getIteams_description() {
        return iteams_description;
    }

    public void setIteams_description(String[] iteams_description) {
        this.iteams_description = iteams_description;
    }

    public String[] getIteams_quantity() {
        return iteams_quantity;
    }

    public void setIteams_quantity(String[] iteams_quantity) {
        this.iteams_quantity = iteams_quantity;
    }

    public String[] getIteams_amount() {
        return iteams_amount;
    }

    public void setIteams_amount(String[] iteams_amount) {
        this.iteams_amount = iteams_amount;
    }

    public String[] getIteams_deleted() {
        return iteams_deleted;
    }

    public void setIteams_deleted(String[] iteams_deleted) {
        this.iteams_deleted = iteams_deleted;
    }

    public String[] getAttachments_id() {
        return attachments_id;
    }

    public void setAttachments_id(String[] attachments_id) {
        this.attachments_id = attachments_id;
    }

    public String[] getAttachments_name() {
        return attachments_name;
    }

    public void setAttachments_name(String[] attachments_name) {
        this.attachments_name = attachments_name;
    }

    public String[] getAttachments_description() {
        return attachments_description;
    }

    public void setAttachments_description(String[] attachments_description) {
        this.attachments_description = attachments_description;
    }

    public String[] getAttachments_type() {
        return attachments_type;
    }

    public void setAttachments_type(String[] attachments_type) {
        this.attachments_type = attachments_type;
    }

    public String[] getAttachments_deleted() {
        return attachments_deleted;
    }

    public void setAttachments_deleted(String[] attachments_deleted) {
        this.attachments_deleted = attachments_deleted;
    }
    
}
