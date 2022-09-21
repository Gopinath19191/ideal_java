/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.controller;

import com.defiance.ideal.travelplan.dao.TravelSettlementDaoImpl;
import com.defiance.ideal.travelplan.dto.TravelSettlementDto;
import com.defiance.ideal.travelplan.utils.CommonConfigurations;
import com.lowagie.text.Phrase;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16113
 */
public class TravelSettlementController extends MultiActionController {

    ModelAndView mv = null;

    public ModelAndView adminList(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        ModelAndView mv = null;
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("travelSettlement/adminList");
                final WebApplicationContext ctx = getWebApplicationContext();
                TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
                //List<TravelSettlementDto> adminList = dao.adminList(employeeId);
                String approver_type = (String) request.getParameter("approver_type");
                String list_type = (String) request.getParameter("type");
                System.out.println(" data : " + list_type + "  " + approver_type);
                TravelSettlementDto dto = new TravelSettlementDto();
                dto.setEmployeeId(employeeId);
                dto.setApprover_type(approver_type);
                dto.setList_type(list_type);
                List<TravelSettlementDto> adminList = null;
                if (approver_type.equals("admin")) {
                    adminList = dao.adminList(dto);
                }
                mv.addObject("adminList", adminList);
                mv.addObject("approver_type", approver_type);
                mv.addObject("type", list_type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView adminTravelDetails(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        ModelAndView mv = null;
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            List<TravelSettlementDto> adminSetlmTicDetails = null;
            List<TravelSettlementDto> adminSetlmHotelDetails = null;
            List<TravelSettlementDto> adminSetlmConvDetails = null;
            List<TravelSettlementDto> vendorList = null;
            String ticketId = request.getParameter("ticketId");
            String approver_type = (String) request.getParameter("approver_type");
            String list_type = (String) request.getParameter("type");
            String masterId = null;
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("travelSettlement/adminTravelSettlement");
                TravelSettlementDto travelDetails = dao.travelDetails(ticketId);
                if (travelDetails != null) {
                    masterId = travelDetails.getMasterId();
                    System.out.println("mster id " + masterId);
                    adminSetlmTicDetails = dao.adminSettlemntTicDetails(masterId);
                    adminSetlmHotelDetails = dao.adminSettlemntHotelDetails(masterId);
                    adminSetlmConvDetails = dao.adminSettlemntConvDetails(masterId);
                    vendorList = dao.vendorList();
                    mv.addObject("adminSetlmTicDetails", adminSetlmTicDetails);
                    mv.addObject("adminSetlmHotelDetails", adminSetlmHotelDetails);
                    mv.addObject("vendorList", vendorList);
                    //mv.addObject("adminSetlmConvDetails", adminSetlmConvDetails);
                }
                mv.addObject("travelDetails", travelDetails);
                mv.addObject("approver_type", approver_type);
                mv.addObject("type", list_type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
//    
//     public ModelAndView editAdminSettlement(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
//        ModelAndView mv = null;
//        try {
//            HttpSession session = request.getSession();
//            String employeeId = (String) session.getAttribute("employeeId");
//            final WebApplicationContext ctx = getWebApplicationContext();
//            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
//            List<TravelSettlementDto> adminSetlmTicDetails = null;
//            List<TravelSettlementDto> adminSetlmHotelDetails = null;
//            List<TravelSettlementDto> adminSetlmConvDetails = null;
////            List<TravelSettlementDto> advanceDetails = null;
//            //String ticketId = request.getParameter("ticketId");
//            String approver_type = (String) request.getParameter("approver_type");
//            String list_type = (String) request.getParameter("type");
//            String ticketId = "11301";
//            String masterId = null;
//            if (employeeId == null || "".equals(employeeId)) {
//                mv = new ModelAndView("session_expired");
//            } else {
//                mv = new ModelAndView("travelSettlement/editAdminSettlement");
//                TravelSettlementDto travelDetails = dao.travelDetails(ticketId);
//                if (travelDetails != null) {
//                    masterId = travelDetails.getMasterId();
//                    System.out.println("mster id " + masterId);
////                    mv.addObject("advanceDetails", advanceDetails);
//                    adminSetlmTicDetails = dao.editAdminTicExpense(masterId);
//                    adminSetlmHotelDetails = dao.editAdminHotExpense(masterId);
//                    adminSetlmConvDetails = dao.adminSettlemntConvDetails(masterId);
//                    //catlist = dao.getCategories();
//                    mv.addObject("adminSetlmTicDetails", adminSetlmTicDetails);
//                    mv.addObject("adminSetlmHotelDetails", adminSetlmHotelDetails);
//                    mv.addObject("adminSetlmConvDetails", adminSetlmConvDetails);
//                }
//                mv.addObject("travelDetails", travelDetails);
//                mv.addObject("approver_type", approver_type);
//                mv.addObject("type", list_type);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }

    public ModelAndView addAdminSettlement(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        ModelAndView mv = null;
        TravelSettlementDto expencydto = new TravelSettlementDto();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<TravelSettlementDto> adminSetlmTicDetails = null;
        List<TravelSettlementDto> adminSetlmHotelDetails = null;
        String lastInsertId = null;
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            String emp_id = (String) request.getParameter("empId");
            expencydto.setEmp_id(emp_id);
            String masterId = filterdto.getMasterId();
            String approver_type = (String) request.getParameter("approver_type");
            String list_type = (String) request.getParameter("type");
            String ticketRefId = filterdto.getTicketRefId(); //String) request.getParameter("ticketRefId");
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("redirect:adminList.htm?type=" + list_type + "&approver_type=" + approver_type);
                //"redirect:listPendingApprovers.htm?type="+type+"&approver_type="+approver_type
                TravelSettlementDto settlementDto = new TravelSettlementDto();
                settlementDto.setStatus(request.getParameter(("actionbtn")));
                expencydto.setMasterId(masterId);
                expencydto.setEmpId(employeeId);
                expencydto.setAdminId(employeeId);
                settlementDto.setMasterId(masterId);
                settlementDto.setAdmin_id(employeeId);
                adminSetlmTicDetails = dao.adminSettlemntTicDetails(masterId);
                adminSetlmHotelDetails = dao.adminSettlemntHotelDetails(masterId);
                int ticTotal = 0;
                int company_total_tax = 0;
                int company_total_tax_total = 0;
                int ticTax = 0;
                int ticTaxTotal = 0;
                int lodTax = 0;
                int lodTaxTotal = 0;
                int lodTotal = 0;
                int conTotal = 0;
                int comTotal = 0;
                String delCount = request.getParameter("delCount");
                String ticdelCount = request.getParameter("ticdelCount");
                String hotdelCount = request.getParameter("hotdelCount");
                String ticCount = request.getParameter("ticCount");
                String hotCount = request.getParameter("hotCount");
                String conCount = request.getParameter("conCount");
                if (!ticdelCount.equals("0")) {
                    int amount = 0;
                    int tx = 0;
                    int tottx = 0;
                    for (int i = 0; i < filterdto.getTic_tot().length; i++) {
                        String total = filterdto.getTic_tot()[i];
                        if (total != null && !total.equals("")) {
                            tottx = Integer.parseInt(total);
                        }
                        ticTaxTotal = ticTaxTotal + tottx;
                        System.out.println(" ticTotal : " + ticTotal + " ticTax : " + ticTax + " ticTaxTotal : " + ticTaxTotal);
                    }
                    settlementDto.setTic_tot_tax_total(String.valueOf(ticTaxTotal));
                }
                if (!hotdelCount.equals("0")) {
                    int amount = 0;
                    int tx = 0;
                    int tottx = 0;
                    for (int i = 0; i < filterdto.getHot_tot().length; i++) {
                        String paidBy = filterdto.getPaid_by()[i];
                        if (paidBy.equals("c")) {
                            String total = filterdto.getHot_tot()[i];
                            if (total != null && !total.equals("")) {
                                tottx = Integer.parseInt(total);
                            }
                            lodTaxTotal = lodTaxTotal + tottx;
                            System.out.println(" lodTotal : " + lodTotal + " lodTax : " + lodTax + " lodTaxTotal : " + lodTaxTotal);
                        }
                    }
                    settlementDto.setLod_tot_tax_total(String.valueOf(lodTaxTotal));
                }
                if (!conCount.equals("0")) {
                    int tottx = 0;
                    for (int i = 0; i < filterdto.getCon_total().length; i++) {
                        String total = filterdto.getCon_total()[i];
                        if (total != null && !total.equals("")) {
                            tottx = Integer.parseInt(total);
                        }
                        conTotal = conTotal + tottx;
                        System.out.println(" lodTotal : " + lodTotal + " lodTax : " + lodTax + " lodTaxTotal : " + lodTaxTotal);
                    }
                    settlementDto.setCompany_conveyance_tax_total(String.valueOf(conTotal));
                }
                System.out.println(" comTotal : " + comTotal + " company_total_tax : " + company_total_tax + "  company_total_tax_total : " + company_total_tax_total);
                
                TravelSettlementDto checkSettlement = dao.checkSettlementMaster(masterId);
                if (checkSettlement == null) {
                    settlementDto.setAdmin_action("1");
                    settlementDto.setEmployee_action("0");
                    company_total_tax_total = ticTaxTotal + lodTaxTotal + conTotal;
                    settlementDto.setCompany_total_tax_total(String.valueOf(company_total_tax_total));
                    lastInsertId = dao.addAdminSettlement(settlementDto);
                    expencydto.setTp_settlement_id(lastInsertId);
                } else {
                    settlementDto.setAdmin_action("1");
                    settlementDto.setEmployee_action(checkSettlement.getEmployee_action());
                    company_total_tax_total = ticTaxTotal + lodTaxTotal + conTotal;
                    settlementDto.setCompany_total_tax_total(String.valueOf(company_total_tax_total));
                    String ticEliTot = checkSettlement.getTicEliTot(); //250 
                    String total_ticket_amount = checkSettlement.getTotal_ticket_amount(); //250     
                    String total_lodging_amount = checkSettlement.getTotal_lodging_amount();
                    String devtotamt = checkSettlement.getDeviation_total_amount();
                    String lodEliTot = checkSettlement.getLodEliTot();
                    String toteletot = checkSettlement.getTotEliTot();
                    String totexpense = checkSettlement.getOverall_total_amount();
                    String overdevper = checkSettlement.getOverall_deviation_percentage();
                    String deviation_total_amount = checkSettlement.getDeviation_total_amount();
                    int etot = 0;
                    int devtot = 0;
                    int total_ticket = 0;
                    int eleticket = 0;
                    int elelodtot = 0;
                    int totlod = 0;
                    int totele = 0;
                    int totexpen = 0;
                    int lodtot = 0;
                    int devper = 0;
                    if (toteletot != null && !toteletot.equals("")) {
                        totele = Integer.parseInt(toteletot);
                    }
                    if (totexpense != null && !totexpense.equals("")) {
                        totexpen = Integer.parseInt(totexpense);
                    }
                    if (ticEliTot != null && !ticEliTot.equals("")) {
                        eleticket = Integer.parseInt(ticEliTot);
                    }
                    if (total_ticket_amount != null && !total_ticket_amount.equals("")) {
                        total_ticket = Integer.parseInt(total_ticket_amount);
                    }
                    settlementDto.setTotEliTot(String.valueOf(totele + ticTaxTotal));
                    settlementDto.setTicEliTot(String.valueOf(eleticket + ticTaxTotal));
                    settlementDto.setTotal_ticket_amount(String.valueOf(total_ticket + ticTaxTotal));
                    settlementDto.setOverall_total_amount(String.valueOf(ticTaxTotal + totexpen + lodTaxTotal));
                    if (!hotdelCount.equals("0")) {
                        if (total_lodging_amount != null && !total_lodging_amount.equals("")) {
                            totlod = Integer.parseInt(total_lodging_amount);
                        }
                        settlementDto.setTotal_lodging_amount(String.valueOf(totlod + lodTaxTotal));
                    } else {
                        totlod = Integer.parseInt(total_lodging_amount);
                        settlementDto.setTotal_lodging_amount(String.valueOf(totlod + lodTaxTotal));
                    }
                    if (devtotamt != null && !devtotamt.equals("")) {
                        devtot = Integer.parseInt(devtotamt);
                    }
                    int totexp = ticTaxTotal + totexpen + lodTaxTotal;
                    int totelegble = totele + ticTaxTotal;
                    devtot = totexp - totelegble;
                    if (devtot > 0) {
                        settlementDto.setDeviation("Y");
                        settlementDto.setDeviation_total_amount(String.valueOf(devtot));
                        if (totelegble > 0) {
                            devper = (devtot * 100) / totelegble;
                            if (devper > 9) {
                                settlementDto.setCfo_action("Y");
                            }
                        }
                    } else {
                        settlementDto.setDeviation_total_amount("0");
                    }
                    settlementDto.setDeviationPercent(String.valueOf(devper));
                    settlementDto.setDeviation(checkSettlement.getDeviation());
                    settlementDto.setCfo_action(checkSettlement.getCfo_action());
                    lastInsertId = checkSettlement.getId();
                    settlementDto.setId(lastInsertId);
                    expencydto.setTp_settlement_id(lastInsertId);
                    dao.updateAdminSettlement(settlementDto);
                }
                if (lastInsertId != null && !lastInsertId.equals("")) {
                    dao.updateDeviation(settlementDto);
                }
                MultipartFile uploadedFile = null;
                if (!ticdelCount.equals("0")) {
                    for (int i = 0; i < filterdto.getRes_tic_billDate().length; i++) {
                        String delstatus = request.getParameter("tic_attachment_deleted_" + (i + 1));
                        System.out.println("delstatus  : " + delstatus);
                        if (delstatus.equals("0")) {
                            MultipartFile files = multipartRequest.getFile("tic_attachment_" + (i + 1));
                            System.out.println("file name : " + files);
                            if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                                String lastIns = dao.uploadFile(request, files, masterId, ticketRefId);
                                expencydto.setAttachmentId(lastIns);
                            }
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String ticid = filterdto.getRes_tic_id()[i];
                            expencydto.setCategory(CommonConfigurations.TICKET);
                            expencydto.setSettlementBy(CommonConfigurations.ADMIN);
                            expencydto.setBillNo(filterdto.getRes_tic_billNo()[i]);
                            if (filterdto.getRes_tic_billDate()[i] != null && !filterdto.getRes_tic_billDate()[i].equals("")) {
                                Date billDate = sdf1.parse(filterdto.getRes_tic_billDate()[i]);
                                expencydto.setBillDate(sdf.format(billDate));
                            }
                            expencydto.setRemarks(filterdto.getRes_tic_remarks()[i]);
                            expencydto.setInvoiceStat(filterdto.getTic_invoice()[i]);
                            expencydto.setTicTotal(filterdto.getTic_tot()[i]);
                            expencydto.setBill(CommonConfigurations.BILL);
                            expencydto.setVendor_name(filterdto.getTic_vendor()[i]);
                            String lastid = dao.addAdminExpency(expencydto);
                        }
                    }
                    if (adminSetlmTicDetails != null && adminSetlmTicDetails.size() > 0) {
                        System.out.println(" list size : " + adminSetlmTicDetails.size());
                        for (TravelSettlementDto travelSettlementDto : adminSetlmTicDetails) {
                            System.out.println("ID : " + travelSettlementDto.getTicketId());
                            expencydto.setSettlementAdded("y");
                            expencydto.setId(travelSettlementDto.getTicketId());
                            dao.updateTicDetails(expencydto);
                        }
                    }
                }
                if (!hotdelCount.equals("0")) {
                    for (int i = 0; i < filterdto.getPaid_by().length; i++) {
                        String lastid = null;
                        String delstatus = request.getParameter("hot_attachment_deleted_" + (i + 1));
                        if (delstatus.equals("0")) {
                            String paidBy = filterdto.getPaid_by()[i];
                            if (paidBy.equals("c")) {
                                MultipartFile files = multipartRequest.getFile("hot_attachment_" + (i + 1));
                                if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                                    String lastIns = dao.uploadFile(request, files, masterId, ticketRefId);
                                    expencydto.setAttachmentId(lastIns);
                                }
                            }
                            expencydto.setCategory(CommonConfigurations.LODGING);
                            expencydto.setSettlementBy(CommonConfigurations.ADMIN);
                            String ticId = filterdto.getRes_hot_id()[i];
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            expencydto.setInvoiceStat(filterdto.getHot_invoice()[i]);
                            expencydto.setRemarks(filterdto.getRes_hot_remarks()[i]);
                            expencydto.setTicTotal(filterdto.getHot_tot()[i]);
                            expencydto.setBill(CommonConfigurations.BILL);
                            if (paidBy.equals("c")) {
                                expencydto.setBillNo(filterdto.getRes_hot_billNo()[i]);
                                if (filterdto.getRes_hot_billDate()[i] != null && !filterdto.getRes_hot_billDate()[i].equals("")) {
                                    Date billDate = sdf1.parse(filterdto.getRes_hot_billDate()[i]);
                                    expencydto.setBillDate(sdf.format(billDate));
                                }
                                expencydto.setAmount(filterdto.getRes_hot_billAmount()[i]);
                                lastid = dao.addAdminExpency(expencydto);
                            }
                        }
                    }
                    if (adminSetlmHotelDetails != null && adminSetlmHotelDetails.size() > 0) {
                        System.out.println(" list size : " + adminSetlmHotelDetails.size());
                        for (TravelSettlementDto travelSettlementDto : adminSetlmHotelDetails) {
                            System.out.println("ID : " + travelSettlementDto.getTicketId());
                            expencydto.setSettlementAdded("y");
                            expencydto.setId(travelSettlementDto.getTicketId());
                            dao.updateHotDetails(expencydto);
                        }
                    }
                }
                for(int i=0;i<filterdto.getCon_vendor().length;i++){
                    String lastid = null;
                    if(filterdto.getCon_vendor()[i]!=null && filterdto.getCon_vendor()[i]!="" && filterdto.getCon_vendor()[i]!="0"){
                        MultipartFile files = multipartRequest.getFile("con_attachment_" + (i + 1));
                            System.out.println("file name : " + files);
                            if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                                String lastIns = dao.uploadFile(request, files, masterId, ticketRefId);
                                expencydto.setAttachmentId(lastIns);
                            }
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            expencydto.setCategory(CommonConfigurations.CONVEYANCE);
                            expencydto.setSettlementBy(CommonConfigurations.ADMIN);
                            expencydto.setBillNo(filterdto.getCon_billNo()[i]);
                            if (filterdto.getCon_billDate()[i] != null && !filterdto.getCon_billDate()[i].equals("")) {
                                Date billDate = sdf1.parse(filterdto.getCon_billDate()[i]);
                                expencydto.setBillDate(sdf.format(billDate));
                            }
                            expencydto.setRemarks(filterdto.getCon_remarks()[i]);
                            expencydto.setInvoiceStat(filterdto.getCon_invoice()[i]);
                            expencydto.setTicTotal(filterdto.getCon_total()[i]);
                            expencydto.setBill(CommonConfigurations.BILL);
                            expencydto.setVendor_name(filterdto.getCon_vendor()[i]);
                            if(!expencydto.getVendor_name().equals("0") && !expencydto.getTicTotal().equals("")){
                                lastid = dao.addAdminExpency(expencydto);
                            }
                            
                    }
                }
                expencydto.setTicketRefId(ticketRefId);
                dao.sendSettlementMail(expencydto, "11");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

//    public ModelAndView updateAdminSettlement(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
//        ModelAndView mv = null;
//        TravelSettlementDto expencydto = new TravelSettlementDto();
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        String lastInsertId = null;
//        try {
//            HttpSession session = request.getSession();
//            String employeeId = (String) session.getAttribute("employeeId");
//            String masterId = filterdto.getMasterId();
//            String approver_type = (String) request.getParameter("approver_type");
//            String list_type = (String) request.getParameter("type");
//            final WebApplicationContext ctx = getWebApplicationContext();
//            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
//            if (employeeId == null || "".equals(employeeId)) {
//                mv = new ModelAndView("session_expired");
//            } else {
//                mv = new ModelAndView("redirect:adminList.htm?type=" + list_type + "&approver_type=" + approver_type);
//                //"redirect:listPendingApprovers.htm?type="+type+"&approver_type="+approver_type
//                TravelSettlementDto settlementDto = new TravelSettlementDto();
//                settlementDto.setStatus(request.getParameter(("actionbtn")));
//                expencydto.setMasterId(masterId);
//                expencydto.setEmpId(employeeId);
//                expencydto.setAdminId(employeeId);
//                settlementDto.setMasterId(masterId);
//                settlementDto.setAdmin_id(employeeId);
//                int ticTotal = 0;
//                int lodTotal = 0;
//                int conTotal = 0;
//                int comTotal = 0;
//                String delCount = request.getParameter("delCount");
//                String ticdelCount = request.getParameter("ticdelCount");
//                String hotdelCount = request.getParameter("hotdelCount");
//                String ticCount = request.getParameter("ticCount");
//                String hotCount = request.getParameter("hotCount");
//                String conCount = request.getParameter("conCount");
//                if (!ticdelCount.equals("0")) {
//                    int amount = 0;
//                    for (int i = 0; i < filterdto.getRes_tic_billAmount().length; i++) {
//                        String billamount = filterdto.getRes_tic_billAmount()[i];
//                           if(billamount != null && billamount.equals("")){
//                               amount = Integer.parseInt(billamount);
//                           }
//                        ticTotal = ticTotal + amount;
//                    }
//                    settlementDto.setTicketTotal(String.valueOf(ticTotal));
//                }
//                if (!hotdelCount.equals("0")) {
//                    int amount = 0;
//                    for (int i = 0; i < filterdto.getRes_hot_billAmount().length; i++) {
//                        String paidBy = filterdto.getPaid_by()[i];
//                        if (paidBy.equals("c")) {
//                            String billamount = filterdto.getRes_hot_billAmount()[i];
//                           if(billamount != null && billamount.equals("")){
//                               amount = Integer.parseInt(billamount);
//                           }
//                            lodTotal = lodTotal + amount;
//                        }
//                    }
//                    settlementDto.setBoardingTotal(String.valueOf(lodTotal));
//                }
//                comTotal = ticTotal + lodTotal;
//                settlementDto.setTotalExpance(String.valueOf(comTotal));
//                TravelSettlementDto checkSettlement = dao.checkSettlementMaster(masterId);
//                if (checkSettlement == null) {
//                    lastInsertId = dao.addAdminSettlement(settlementDto);
//                    expencydto.setTp_settlement_id(lastInsertId);
//                } else {
//                    String ticEliTot = checkSettlement.getTicEliTot(); //250 
//                    String total_ticket_amount = checkSettlement.getTotal_ticket_amount(); //250
//                    String total_lodging_amount = checkSettlement.getTotal_lodging_amount();
//                    String devtotamt = checkSettlement.getDeviation_total_amount();
//                    String lodEliTot = checkSettlement.getLodEliTot();
//                    String toteletot = checkSettlement.getTotEliTot();
//                    String totexpense = checkSettlement.getOverall_total_amount();
//                    String overdevper = checkSettlement.getOverall_deviation_percentage();
//                    String deviation_total_amount = checkSettlement.getDeviation_total_amount();
//                    int etot = 0;
//                    int devtot = 0;
//                    int total_ticket = 0;
//                    int eleticket = 0;
//                    int elelodtot = 0;
//                    int totlod = 0;
//                    int totele = 0;
//                    int totexpen = 0;
//                    int lodtot = 0;
//                     int devper = 0;
//                    if (toteletot != null && !toteletot.equals("")) {
//                        totele = Integer.parseInt(toteletot);
//                    }
//                    if (totexpense != null && !totexpense.equals("")) {
//                        totexpen = Integer.parseInt(totexpense);
//                    }
//                    if (ticEliTot != null && !ticEliTot.equals("")) {
//                        eleticket = Integer.parseInt(ticEliTot);
//                    }
//                    if (total_ticket_amount != null && !total_ticket_amount.equals("")) {
//                        total_ticket = Integer.parseInt(total_ticket_amount);
//                    }
//                    settlementDto.setTotEliTot(String.valueOf(totele + ticTotal));
//                    settlementDto.setTicEliTot(String.valueOf(eleticket + ticTotal));
//                    settlementDto.setTotal_ticket_amount(String.valueOf(total_ticket + ticTotal));                    
//                    settlementDto.setOverall_total_amount(String.valueOf(ticTotal + totexpen + lodTotal));
//                    if (!hotdelCount.equals("0")) {
//                        if (total_lodging_amount != null && !total_lodging_amount.equals("")) {
//                            totlod = Integer.parseInt(total_lodging_amount);
//                        }
//                        settlementDto.setTotal_lodging_amount(String.valueOf(totlod + lodTotal));
//                    } else {
//                        settlementDto.setTotal_lodging_amount(String.valueOf(lodTotal));
//                    }
//                     if (devtotamt != null && !devtotamt.equals("")) {
//                        devtot = Integer.parseInt(devtotamt);
//                    }
//                     int totexp = ticTotal + totexpen + lodTotal;
//                     int totelegble = totele + ticTotal;
//                     devtot = totexp - totelegble;
//                     if(devtot > 0){
//                         settlementDto.setDeviation_total_amount(String.valueOf(devtot));
//                         if(totelegble > 0){
//                             devper =  (devtot * 100)/totelegble;
//                         }
//                         settlementDto.setDeviationPercent(String.valueOf(devper));
//                     }else{
//                         settlementDto.setDeviation_total_amount("0");
//                         settlementDto.setDeviationPercent("0");
//                     }
//                    lastInsertId = checkSettlement.getId();
//                    settlementDto.setId(lastInsertId);
//                    expencydto.setTp_settlement_id(lastInsertId);
//                    dao.updateAdminSettlement(settlementDto);
//                }
//                if (lastInsertId != null && !lastInsertId.equals("")) {
//                    dao.updateDeviation(settlementDto);
//                }
//                MultipartFile uploadedFile = null;
//                if (!ticdelCount.equals("0")) {
//                    for (int i = 0; i < filterdto.getRes_tic_billDate().length; i++) {
//                        if ((filterdto.getTicdelStat()[i] == "0" || filterdto.getTicdelStat()[i].equals("0"))
//                            && (filterdto.getRes_tic_id()[i] == "0" || filterdto.getRes_tic_id()[i].equals("0"))) {
//                        //String delstatus = request.getParameter("tic_attachment_deleted_" + (i + 1));
//                        //System.out.println("delstatus  : " + delstatus);
////                        if (delstatus.equals("0")) {
//                            MultipartFile files = multipartRequest.getFile("tic_attachment_" + (i + 1));
//                            System.out.println("file name : " + files);
//                            if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
//                                String lastIns = dao.uploadFile(request, files, masterId);
//                                expencydto.setAttachmentId(lastIns);
//                            }
//                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                            if(filterdto.getRes_tic_tdate()[i] != null && !filterdto.getRes_tic_tdate()[i].equals("")){
//                                 Date fromDate = sdf1.parse(filterdto.getRes_tic_tdate()[i]);
//                                 expencydto.setFromDate(sdf.format(fromDate));
//                                expencydto.setToDate(sdf.format(fromDate));
//                            }
//                            String ticid = filterdto.getRes_tic_id()[i];
//                            expencydto.setCategory(CommonConfigurations.TICKET);
//                            expencydto.setSettlementBy(CommonConfigurations.ADMIN);
//                             expencydto.setFcityId(filterdto.getRes_tic_fcity()[i]);
//                            expencydto.setBillNo(filterdto.getRes_tic_billNo()[i]);
//                            if(filterdto.getRes_tic_billDate()[i] != null && !filterdto.getRes_tic_billDate()[i].equals("")){
//                                Date billDate = sdf1.parse(filterdto.getRes_tic_billDate()[i]);
//                                expencydto.setBillDate(sdf.format(billDate));
//                            }
//                            expencydto.setAmount(filterdto.getRes_tic_billAmount()[i]);
//                            expencydto.setRemarks(filterdto.getRes_tic_remarks()[i]);
//                            expencydto.setInvoiceStat(filterdto.getInvoice()[i]);
//                            expencydto.setBill(CommonConfigurations.BILL);
//                            String lastid = dao.addAdminExpency(expencydto);
//                            if (lastid != null && !lastid.equals("")) {
//                                if (ticid != null && !ticid.equals("") && !ticid.equals("0")) {
//                                    expencydto.setSettlementAdded("y");
//                                    expencydto.setId(ticid);
//                                    dao.updateTicDetails(expencydto);
//                                }
//                            }
//                        
//                    }else{
//                            MultipartFile files = multipartRequest.getFile("tic_attachment_" + (i + 1));
//                            System.out.println("file name : " + files);
//                            if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
//                                String lastIns = dao.uploadFile(request, files, masterId);
//                                expencydto.setAttachmentId(lastIns);
//                            }else {
//                                expencydto.setAttachmentId(filterdto.getTicAttachmentId()[i]);
//                              }
//                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                            if(filterdto.getRes_tic_tdate()[i] != null && !filterdto.getRes_tic_tdate()[i].equals("")){
//                                 Date fromDate = sdf1.parse(filterdto.getRes_tic_tdate()[i]);
//                                 expencydto.setFromDate(sdf.format(fromDate));
//                                expencydto.setToDate(sdf.format(fromDate));
//                            }
//                            String ticid = filterdto.getRes_tic_id()[i];
//                            expencydto.setCategory(CommonConfigurations.TICKET);
//                            expencydto.setSettlementBy(CommonConfigurations.ADMIN);
//                             expencydto.setFcityId(filterdto.getRes_tic_fcity()[i]);
//                            expencydto.setBillNo(filterdto.getRes_tic_billNo()[i]);
//                            if(filterdto.getRes_tic_billDate()[i] != null && !filterdto.getRes_tic_billDate()[i].equals("")){
//                                Date billDate = sdf1.parse(filterdto.getRes_tic_billDate()[i]);
//                                expencydto.setBillDate(sdf.format(billDate));
//                            }
//                            expencydto.setAmount(filterdto.getRes_tic_billAmount()[i]);
//                            expencydto.setRemarks(filterdto.getRes_tic_remarks()[i]);
//                            expencydto.setInvoiceStat(filterdto.getInvoice()[i]);
//                            expencydto.setBill(CommonConfigurations.BILL);
//                            String lastid = dao.addAdminExpency(expencydto);
//                            if (lastid != null && !lastid.equals("")) {
//                                if (ticid != null && !ticid.equals("") && !ticid.equals("0")) {
//                                    expencydto.setSettlementAdded("y");
//                                    expencydto.setId(ticid);
//                                    dao.updateTicDetails(expencydto);
//                                }
//                            }
//                        }
//                    }
//                }
//                if (!hotdelCount.equals("0")) {
//                    for (int i = 0; i < filterdto.getRes_hot_billAmount().length; i++) {
//                        if ((filterdto.getHotdelStat()[i] == "0" || filterdto.getHotdelStat()[i].equals("0"))
//                            && (filterdto.getRes_hot_id()[i] == "0" || filterdto.getRes_hot_id()[i].equals("0"))) {
//                        String lastid = null;
////                        String delstatus = request.getParameter("hot_attachment_deleted_" + (i + 1));
////                        if (delstatus.equals("0")) {
//                            String paidBy = filterdto.getPaid_by()[i];
//                            if (paidBy.equals("c")) {
//                                MultipartFile files = multipartRequest.getFile("hot_attachment_" + (i + 1));
//                                if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
//                                    String lastIns = dao.uploadFile(request, files, masterId);
//                                    expencydto.setAttachmentId(lastIns);
//                                }
//                            }
//                            expencydto.setCategory(CommonConfigurations.LODGING);
//                            expencydto.setSettlementBy(CommonConfigurations.ADMIN);
//                            String ticId = filterdto.getRes_hot_id()[i];
//                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                             if(filterdto.getRes_hot_fdate()[i] != null && !filterdto.getRes_hot_fdate()[i].equals("")){
//                                 Date fromDate = sdf1.parse(filterdto.getRes_hot_fdate()[i]);
//                                expencydto.setFromDate(sdf.format(fromDate));
//                             }
//                            if(filterdto.getRes_hot_tdate()[i] != null && !filterdto.getRes_hot_tdate()[i].equals("")){
//                                Date toDate = sdf1.parse(filterdto.getRes_hot_tdate()[i]);
//                                expencydto.setToDate(sdf.format(toDate));
//                            }
//                            expencydto.setFcityId(filterdto.getRes_hot_city()[i]);
//                            expencydto.setRemarks(filterdto.getRes_hot_remarks()[i]);
//                            expencydto.setBill(CommonConfigurations.BILL);
//                            if (paidBy.equals("c")) {
//                                expencydto.setBillNo(filterdto.getRes_hot_billNo()[i]);
//                                if(filterdto.getRes_hot_tdate()[i] != null && !filterdto.getRes_hot_tdate()[i].equals("")){
//                                    Date billDate = sdf1.parse(filterdto.getRes_hot_billDate()[i]);
//                                    expencydto.setBillDate(sdf.format(billDate));
//                                }
//                                expencydto.setAmount(filterdto.getRes_hot_billAmount()[i]);
//                                lastid = dao.addAdminExpency(expencydto);
//                            }
//                            if (ticId != null && ticId != "" && !ticId.equals("0") && !ticId.equals("0")) {
//                                expencydto.setSettlementAdded("y");
//                                expencydto.setId(ticId);
//                                dao.updateHotDetails(expencydto);
//                            }
//                    }else{
//                             String lastid = null;
////                        String delstatus = request.getParameter("hot_attachment_deleted_" + (i + 1));
////                        if (delstatus.equals("0")) {
//                            String paidBy = filterdto.getPaid_by()[i];
//                            if (paidBy.equals("c")) {
//                                MultipartFile files = multipartRequest.getFile("hot_attachment_" + (i + 1));
//                                if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
//                                    String lastIns = dao.uploadFile(request, files, masterId);
//                                    expencydto.setAttachmentId(lastIns);
//                                }else {
//                                expencydto.setAttachmentId(filterdto.getHotAttachmentId()[i]);
//                              }
//                            }
//                            expencydto.setCategory(CommonConfigurations.LODGING);
//                            expencydto.setSettlementBy(CommonConfigurations.ADMIN);
//                            String ticId = filterdto.getRes_hot_id()[i];
//                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                             if(filterdto.getRes_hot_fdate()[i] != null && !filterdto.getRes_hot_fdate()[i].equals("")){
//                                 Date fromDate = sdf1.parse(filterdto.getRes_hot_fdate()[i]);
//                                expencydto.setFromDate(sdf.format(fromDate));
//                             }
//                            if(filterdto.getRes_hot_tdate()[i] != null && !filterdto.getRes_hot_tdate()[i].equals("")){
//                                Date toDate = sdf1.parse(filterdto.getRes_hot_tdate()[i]);
//                                expencydto.setToDate(sdf.format(toDate));
//                            }
//                            expencydto.setFcityId(filterdto.getRes_hot_city()[i]);
//                            expencydto.setRemarks(filterdto.getRes_hot_remarks()[i]);
//                            expencydto.setBill(CommonConfigurations.BILL);
//                            if (paidBy.equals("c")) {
//                                expencydto.setBillNo(filterdto.getRes_hot_billNo()[i]);
//                                if(filterdto.getRes_hot_tdate()[i] != null && !filterdto.getRes_hot_tdate()[i].equals("")){
//                                    Date billDate = sdf1.parse(filterdto.getRes_hot_billDate()[i]);
//                                    expencydto.setBillDate(sdf.format(billDate));
//                                }
//                                expencydto.setAmount(filterdto.getRes_hot_billAmount()[i]);                               
//                                lastid = dao.addAdminExpency(expencydto);
//                            }
//                            if (ticId != null && ticId != "" && !ticId.equals("0") && !ticId.equals("0")) {
//                                expencydto.setSettlementAdded("y");
//                                expencydto.setId(ticId);
//                                dao.updateHotDetails(expencydto);
//                            }  
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }
    public ModelAndView employeeTravelDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            List<TravelSettlementDto> advanceDetails = null;
            List<TravelSettlementDto> adminExpenseList = null;
            List<TravelSettlementDto> isSettlementAdded = null;
            TravelSettlementDto expenseList = null;
            TravelSettlementDto citiDto = null;
            List<TravelSettlementDto> catlist = null;
            List<TravelSettlementDto> citylist = null;
            String ticketId = request.getParameter("ticketId");
            //String ticketId = "11350";
            String masterId = null;
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                isSettlementAdded = dao.isSettlementAdded(ticketId);
                if (isSettlementAdded != null && isSettlementAdded.size() > 0) {
                    mv = new ModelAndView("redirect:editSettlement.htm?ticketId=" + ticketId);
                } else {
                    mv = new ModelAndView("travelSettlement/travelSettlementDashBoard");
                    TravelSettlementDto travelDetails = dao.getDetails(ticketId);
                    String totalAdvance = null;
                    if (travelDetails != null) {
                        masterId = travelDetails.getMasterId();
                        advanceDetails = dao.advanceDetails(masterId);
                        citiDto = dao.travelCities(masterId);
                        totalAdvance = dao.totalAdvance(masterId);
                        adminExpenseList = dao.adminExpenseList(masterId);
                        expenseList = dao.adminExpenseTotals(masterId);
                        citylist = dao.getCityList(travelDetails.getCountryId());
//                        String comTicTotal = dao.comSettlementTotal(masterId, CommonConfigurations.TICKET);
//                        String comLodTotal = dao.comSettlementTotal(masterId, CommonConfigurations.LODGING);
//                        String comConvTotal = dao.comSettlementTotal(masterId, CommonConfigurations.CONVEYANCE);
//                        String companyTotal = dao.comSettlementTotal(masterId, "");
                        catlist = dao.getCategories();
                        mv.addObject("advanceDetails", advanceDetails);
                        mv.addObject("totalAdvance", totalAdvance);
                        mv.addObject("compExpDetails", adminExpenseList);
                        mv.addObject("expenseList", expenseList);
                        mv.addObject("citiDto", citiDto);
                        mv.addObject("citylist", citylist);
//                        mv.addObject("comTicTotal", comTicTotal);
//                        mv.addObject("comLodTotal", comLodTotal);
//                        mv.addObject("comConvTotal", comConvTotal);
//                        mv.addObject("companyTotal", companyTotal);
                    }
                    mv.addObject("travelDetails", travelDetails);
                    mv.addObject("catList", catlist);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView addEmployeeSettlement(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        ModelAndView mv = null;
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        TravelSettlementDto expencydto = null;
        String lastInsertId = null;
        String actionbtn = request.getParameter("actionbtn");
        mv = new ModelAndView("redirect:getList.htm");
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            String ticketRefId = (String) request.getParameter("ticketRefId");
            String adminAction = (String) request.getParameter("admin_action");
            String masterId = filterdto.getMasterId();
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                expencydto = new TravelSettlementDto();
                expencydto.setMasterId(masterId);
                expencydto.setSettlementBy(CommonConfigurations.EMPLOYEE);
                expencydto.setEmpId(employeeId);
                expencydto.setEmp_id(employeeId);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                System.out.println("date formar " + dateFormat.format(date));
                filterdto.setStatus(actionbtn);
                filterdto.setBookingStatus(actionbtn);
                filterdto.setFromCity(request.getParameter("fromCity"));
                filterdto.setToCity(request.getParameter("toCity"));
                filterdto.setSubmittedDate(dateFormat.format(date));
                filterdto.setFromDate(request.getParameter("startdt"));
                filterdto.setToDate(request.getParameter("enddt"));
                filterdto.setTotal_boarding_amount(request.getParameter("borTotExp"));
                filterdto.setTotal_ticket_amount(request.getParameter("ticTotExp"));
                filterdto.setTotal_lodging_amount(request.getParameter("lodTotExp"));
                filterdto.setTotal_conveyance_amount(request.getParameter("conTotExp"));
                filterdto.setTotal_miscellaneous_amount(request.getParameter("misTotExp"));
                filterdto.setOverall_total_amount(request.getParameter("totTotExp"));
                filterdto.setEmployee_action("1");
                String cfo_action_required = "N";
                String devn = "N";
               System.out.println("settlement policy "+filterdto.getSettlementType());
                if(filterdto.getSettlementType().equals("h")){
                    if(Integer.parseInt(filterdto.getDeviationPercent())>0&&Integer.parseInt(filterdto.getDeviationPercent())<9){
                        devn = "Y";
                    }else if(Integer.parseInt(filterdto.getDeviationPercent())>9){
                        devn = "Y";
                        cfo_action_required = "N";
                    }else{
                        devn = "N";
                        cfo_action_required = "N";
                    }
                }else if(filterdto.getSettlementType().equals("c")){
                    devn = "Y";
                    cfo_action_required = "N";
                }
                
                filterdto.setDeviation(devn);expencydto.setDeviation(devn);
                expencydto.setSettlementType(filterdto.getSettlementType());
                filterdto.setDeviation(devn);expencydto.setDeviation(devn);
                filterdto.setCfo_action(cfo_action_required);expencydto.setCfo_action(cfo_action_required);
                TravelSettlementDto checkSettlement = dao.checkSettlementMaster(masterId);
                if(adminAction.equals("N")){
                    filterdto.setAdminAction("1");
                }else{
                    if(checkSettlement != null)
                        filterdto.setAdminAction(checkSettlement.getAdminAction());
                    else
                        filterdto.setAdminAction("0");
                }
                if (checkSettlement == null) {
                    lastInsertId = dao.addEmployeeSettlement(filterdto);
                    expencydto.setTp_settlement_id(lastInsertId);
                    dao.updateDeviationEmp(filterdto);
                } else {
                    lastInsertId = checkSettlement.getId();
                    filterdto.setId(lastInsertId);
                    expencydto.setTp_settlement_id(lastInsertId);
                    dao.updateEmployeeSettlement(filterdto);
                    dao.updateDeviationEmp(filterdto);
                }
                for (int i = 0; i < filterdto.getRes_category().length; i++) {
                    expencydto.setCategory(filterdto.getRes_category()[i]);
                    expencydto.setBill(filterdto.getRes_bill()[i]);
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    if ("y".equalsIgnoreCase(filterdto.getRes_bill()[i])) {
                        Date billDate = sdf1.parse(filterdto.getRes_billDate()[i]);
                        expencydto.setBillDate(sdf.format(billDate));
                        expencydto.setBillNo(filterdto.getRes_BillNo()[i]);
                    } else {
                        expencydto.setBillDate(null);
                        expencydto.setBillNo(null);
                    }
                    Date fromDate = sdf1.parse(filterdto.getRes_fromDate()[i]);
                    expencydto.setFromDate(sdf.format(fromDate));
                    Date toDate = sdf1.parse(filterdto.getRes_toDate()[i]);
                    expencydto.setToDate(sdf.format(toDate));
                    expencydto.setEligibiityAmount(filterdto.getRes_eligibility()[i]);
                    expencydto.setTax(filterdto.getTic_tax()[i]);
                    expencydto.setTicTotal(filterdto.getTic_tot()[i]);
                    expencydto.setAmount(filterdto.getRes_amount()[i]);
                    expencydto.setFcityId(filterdto.getRes_city_id()[i]);
                    expencydto.setRemarks(filterdto.getRes_remarks()[i]);
                    dao.addAdminExpency(expencydto);
                }
                MultipartFile uploadedFile = null;
                int file_length = Integer.parseInt(request.getParameter("attachmentCount"));
                for (int i = 0; i < file_length; i++) {
                    String delstatus = request.getParameter("attachment_deleted_" + (i + 1));//filterdto.getAttachment_deleted()[i];//
                    System.out.println("delstatus  : " + delstatus);
                    if (delstatus.equals("0")) {
                        MultipartFile files = multipartRequest.getFile("fileAttachment_" + (i + 1));
                        System.out.println("file name : " + files);
                        if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                            dao.uploadAttachment(request, files, masterId, request.getParameter("file_category_" + (i + 1)), ticketRefId);
                        }
                    }
                }
                if (actionbtn.equals("12")) {
                    dao.sendSettlementMail(expencydto, actionbtn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    private int dateDiff(String date1, String date2) throws Exception {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        Date fromDate = sdf1.parse(date1);
        Date toDate = sdf1.parse(date2);
        Calendar c = Calendar.getInstance();
        c.setTime(toDate);
        c.add(Calendar.DATE, 1);
        toDate = c.getTime();
        long milliseconds = toDate.getTime() - fromDate.getTime();
        int days = (int) milliseconds / (1000 * 60 * 60 * 24);
        return days;
    }

    public ModelAndView citySearch(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv;
        mv = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            String val = request.getParameter("q");
            String type = request.getParameter("type");
            List<TravelSettlementDto> result = dao.getAutocomplete(val, type);
            mv.addObject("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView employeeViewScreen(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        ModelAndView mv = null;
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            List<TravelSettlementDto> advanceDetails = null;
            List<TravelSettlementDto> adminExpenseList = null;
            List<TravelSettlementDto> empExpenseList = null;
            TravelSettlementDto expenseList = null;
            List<TravelSettlementDto> catlist = null;
            List<TravelSettlementDto> attachList = null;
            TravelSettlementDto approvelStatus = null;
            String ticRefId = request.getParameter("ticketId");
            String approver_type = (String) request.getParameter("approver_type");
            String list_type = (String) request.getParameter("type");
            //String refId = "11328";
            String masterId = null;
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("travelSettlement/travelSettlementEmpView");
                TravelSettlementDto travelDetails = dao.viewDetails(ticRefId);
                String totalAdvance = null;
                if (travelDetails != null) {
                    masterId = travelDetails.getMasterId();
                    advanceDetails = dao.advanceDetails(masterId);
                    totalAdvance = dao.totalAdvance(masterId);
                    expenseList = dao.AllexpenceTotals(masterId);
                    empExpenseList = dao.empExpenseList(masterId);
                    adminExpenseList = dao.adminExpenseList(masterId);
                    approvelStatus = dao.approvelStatus(masterId);
                    catlist = dao.getCategories();
                    mv.addObject("advanceDetails", advanceDetails);
                    mv.addObject("totalAdvance", totalAdvance);
                    mv.addObject("expenseList", expenseList);
                    attachList = dao.getAttachments(masterId);
                }
                Map<String, String> categorymap = new HashMap<String, String>();
                categorymap.put("250", "Ticket");
                categorymap.put("251", "Lodging");
                categorymap.put("252", "Boarding");
                categorymap.put("253", "Conveyance");
                categorymap.put("254", "Miscelanious");
                mv.addObject("categorymap", categorymap);
                mv.addObject("travelDetails", travelDetails);
                mv.addObject("attachList", attachList);
                mv.addObject("adminExpenseList", adminExpenseList);
                mv.addObject("empExpenseList", empExpenseList);
                mv.addObject("catList", catlist);
                mv.addObject("approver_type", approver_type);
                mv.addObject("type", list_type);
                mv.addObject("approvelStatus", approvelStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView getEligibilityAmount(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        ModelAndView mv = null;
        TravelSettlementDto expencydto = new TravelSettlementDto();
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                expencydto.setTravelTerm(request.getParameter("travelTerm"));
                expencydto.setTravelType(request.getParameter("travelType"));
                expencydto.setCurrencyId(request.getParameter("curreny"));
                String category = request.getParameter("category");
                String fromDate = request.getParameter("fromDate");
                String toDate = request.getParameter("toDate");
                String city = request.getParameter("city");
                expencydto.setBandId(request.getParameter("bandId"));
                expencydto.setBill(request.getParameter("bill"));
                String datediff = request.getParameter("datediff");
                int lodgingTotal = 0;
                String expAmount = null;
                if (category.equals("251")) {
                    expencydto.setCategory("l");
                } else if (category.equals("252")) {
                    expencydto.setCategory("b");
                } else if (category.equals("254")) {
                    expencydto.setCategory("m");
                }
                List<String> citylist = dao.citylist(expencydto);
                int days = Integer.parseInt(datediff);
                //int days = dateDiff(fromDate, toDate);
                if (citylist.contains(city)) {
                    expencydto.setCityId(city);
                } else {
                    expencydto.setCityId("0");
                }
                expAmount = dao.getEligibileAmount(expencydto);
                int multiAmount = 0;
                System.out.println("251 amount : " + expAmount);
                if (expAmount != null && expAmount != "") {
                    multiAmount = days * Integer.parseInt(expAmount);
                } else {
                    multiAmount = 0;
                }
                lodgingTotal = +multiAmount;
                System.out.println(" loadgingTotal  " + lodgingTotal);
                response.getWriter().print(lodgingTotal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ModelAndView settlementPendingApprovels(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("travelSettlement/approvelList");
                String approver_type = (String) request.getParameter("approver_type");
                String list_type = (String) request.getParameter("type");
                System.out.println(" data : " + list_type + "  " + approver_type);
                TravelSettlementDto dto = new TravelSettlementDto();
                dto.setEmployeeId(employeeId);
                dto.setApprover_type(approver_type);
                dto.setList_type(list_type);
                List<TravelSettlementDto> settlement_list = null;
                if (approver_type.equals("rm")) {
                    settlement_list = dao.getRMList(dto);
                } else if (approver_type.equals("buh")) {
                    settlement_list = dao.getBUHList(dto);
                } else if (approver_type.equals("finance")) {
                    settlement_list = dao.getFinanceList(dto);
                } else if (approver_type.equals("cfo")) {
                    settlement_list = dao.getCFOList(dto);
                } else if (approver_type.equals("treasury")) {
                    settlement_list = dao.getTreasuryList(dto);
                } else if (approver_type.equals("payroll")) {
                    settlement_list = dao.getPayrollList(dto);
                } else {
                }
                mv.addObject("settlement_details", settlement_list);
                mv.addObject("approver_type", approver_type);
                mv.addObject("type", list_type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView settlementView(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            List<TravelSettlementDto> attachList = null;
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            List<TravelSettlementDto> advanceDetails = null;
            List<TravelSettlementDto> companyExpencyDetails = null;
            TravelSettlementDto approverDetails = null;
            TravelSettlementDto approvelStatus = null;
            List<TravelSettlementDto> catlist = null;
            TravelSettlementDto expenseList = null;
            String ticketId = request.getParameter("ticketId");
            String masterId = null;
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("travelSettlement/approvelView");
                String approver_type = request.getParameter("approver_type");
                String list_type = (String) request.getParameter("type");
                TravelSettlementDto travelDetails = dao.viewDetails(ticketId);
                String totalAdvance = null;
                if (travelDetails != null) {
                    masterId = travelDetails.getMasterId();
                    advanceDetails = dao.advanceDetails(masterId);
                    approvelStatus = dao.approvelStatus(masterId);
                    totalAdvance = dao.totalAdvance(masterId);
                    expenseList = dao.ViewAllexpenceTotals(masterId);
                    approverDetails = dao.approverDetails(ticketId);
                    companyExpencyDetails = dao.allExpenses(masterId);
                    catlist = dao.getCategories();
                    mv.addObject("advanceDetails", advanceDetails);
                    mv.addObject("totalAdvance", totalAdvance);
                    mv.addObject("compExpDetails", companyExpencyDetails);
                    mv.addObject("expenseList", expenseList);
                    mv.addObject("approver_type", approver_type);
                    mv.addObject("type", list_type);
                    mv.addObject("approvelStatus", approvelStatus);
                }
                Map<String, String> categorymap = new HashMap<String, String>();
                categorymap.put("250", "Ticket");
                categorymap.put("251", "Lodging");
                categorymap.put("252", "Boarding");
                categorymap.put("253", "Conveyance");
                categorymap.put("254", "Miscelanious");
                attachList = dao.getAttachments(masterId);
                mv.addObject("categorymap", categorymap);
                mv.addObject("travelDetails", travelDetails);
                mv.addObject("attachList", attachList);
                mv.addObject("catList", catlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView approvelSettlement(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto filterdto) throws Exception {
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            String approver_type = request.getParameter("approver_type");
            String list_type = (String) request.getParameter("type");
            List<TravelSettlementDto> attachList = null;
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            List<TravelSettlementDto> advanceDetails = null;
            List<TravelSettlementDto> companyExpencyDetails = null;
            TravelSettlementDto approverDetails = null;
            TravelSettlementDto approvelStatus = null;
            List<TravelSettlementDto> catlist = null;
            TravelSettlementDto expenseList = null;
            TravelSettlementDto totalDto = null;
            String ticketId = request.getParameter("ticketId");
            String masterId = null;
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("travelSettlement/approvelDashboard");

                TravelSettlementDto travelDetails = dao.viewDetails(ticketId);
                String totalAdvance = null;
                if (travelDetails != null) {
                    masterId = travelDetails.getMasterId();
                    advanceDetails = dao.advanceDetails(masterId);
                    approvelStatus = dao.approvelStatus(masterId);
                    totalAdvance = dao.totalAdvance(masterId);
                    expenseList = dao.ViewAllexpenceTotals(masterId);
                    approverDetails = dao.approverDetails(ticketId);
                    companyExpencyDetails = dao.allExpenses(masterId);
                    totalDto = dao.totalForApprovals(masterId);
                    catlist = dao.getCategories();
                    int depo = 0;
                    int eletot = 0;
                    int diff = 0;
                    int totexp = 0;
                    int acttot = 0;
                    String isPayroll = "N";
                    if (approver_type.equals("finance") || approver_type.equals("treasury") || approver_type.equals("payroll")) {
                        if(approver_type.equals("finance")){
                            if(travelDetails.getBillReceived() == null){
                                mv = new ModelAndView("travelSettlement/billUpdate");
                            }
                        }
                        TravelSettlementDto balcal = dao.balCaliculation(masterId);
                        System.out.println("in side payroll cal");
                        String deposited = balcal.getDepositedAmount();
                        String eleAmount = balcal.getTotEliTot();
                        String totalExpance = balcal.getTotalExpance();
                        String overall_total_amount = balcal.getOverall_total_amount();
                        String totalAmount = balcal.getTotalAmount();
                        int overalltot = 0;
                        if (totalAmount != null && totalAmount != "") {
                            acttot = Integer.parseInt(totalAmount);
                        }
                        if (totalExpance != null && totalExpance != "") {
                            totexp = Integer.parseInt(totalExpance);
                        }
                        if (deposited != null && deposited != "") {
                            depo = Integer.parseInt(deposited);
                        } else {
                            depo = 0;
                        }
                        if (eleAmount != null && eleAmount != "") {
                            eletot = Integer.parseInt(eleAmount);
                        } else {
                            eletot = 0;
                        }
                        if (overall_total_amount != null && overall_total_amount != "") {
                            overalltot = Integer.parseInt(overall_total_amount);
                        } else {
                            overalltot = 0;
                        }
                        if (depo == 0) {
                            diff = acttot;
                        } else if (depo > 0) {
                            if (depo > acttot) {
                                diff = depo - acttot;
                                isPayroll = "Y";
                            }
                            if (acttot > depo) {
                                diff = acttot - depo;
                            }
                        }
                        System.out.println("in side payroll cal  " + isPayroll);
                        mv.addObject("diff", diff);
                        mv.addObject("depositAmount", depo);
                        mv.addObject("totalAmount", eletot);
                        mv.addObject("isPayroll", isPayroll);
                    }
                    Map<String, String> categorymap = new HashMap<String, String>();
                    categorymap.put("250", "Ticket");
                    categorymap.put("251", "Lodging");
                    categorymap.put("252", "Boarding");
                    categorymap.put("253", "Conveyance");
                    categorymap.put("254", "Miscelanious");
                    attachList = dao.getAttachments(masterId);
                    mv.addObject("advanceDetails", advanceDetails);
                    mv.addObject("approvelStatus", approvelStatus);
                    mv.addObject("totalAdvance", totalAdvance);
                    mv.addObject("compExpDetails", companyExpencyDetails);
                    mv.addObject("expenseList", expenseList);
                    mv.addObject("approver_type", approver_type);
                    mv.addObject("type", list_type);
                    mv.addObject("attachList", attachList);
                    mv.addObject("categorymap", categorymap);
                    mv.addObject("totalDto", totalDto);
                }
                mv.addObject("travelDetails", travelDetails);
                mv.addObject("catList", catlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView approveSettlement(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto formData) throws Exception {
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            String deviation = request.getParameter("deviation");
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            String structure_id = request.getParameter("practiceGroupId");
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("travelSettlement/approvelList");
                if (formData.getApprover_type().equals("rm")) {
                    String rm_id = (String) session.getAttribute("employeeId");
                    formData.setRm_id(rm_id);
                    dao.updateRmApproval(formData);
                    dao.updateDeviationEmp(formData);
                    String buh_id = dao.getBuh(structure_id);
                    if (rm_id.equals(buh_id) && formData.getStatus().equals("13")) {
                        formData.setBuh_id(buh_id);
                        formData.setBuh_remarks(formData.getRm_remarks());
                        formData.setStatus("15");
                        dao.updateBuhApproval(formData);
                        dao.updateDeviationEmp(formData);
                        String cfo_id = dao.getCfo();
                        if (buh_id.equals(cfo_id) && deviation.equals("Y") && formData.getCfo_action().equals("Y")) {
                            formData.setCfo_id(buh_id);
                            formData.setCfo_remarks(formData.getBuh_remarks());
                            formData.setStatus("17");
                            dao.updateCfoApproval(formData);
                            dao.updateDeviationEmp(formData);
                        }
                    }
                    dao.sendSettlementMail(formData, formData.getStatus());
                }
                if (formData.getApprover_type().equals("buh")) {
                    String buh_id = (String) session.getAttribute("employeeId");
                    formData.setBuh_id(buh_id);
                    dao.updateBuhApproval(formData);
                    dao.updateDeviationEmp(formData);
                    String cfo_id = dao.getCfo();
                    if (buh_id.equals(cfo_id) && formData.getCfo_action().equals("Y") && formData.getStatus().equals("15")) {
                        formData.setCfo_id(buh_id);
                        formData.setCfo_remarks(formData.getBuh_remarks());
                        formData.setStatus("17");
                        dao.updateCfoApproval(formData);
                        dao.updateDeviationEmp(formData);
                    }
                    dao.sendSettlementMail(formData, formData.getStatus());
                }
                if (formData.getApprover_type().equals("cfo")) {
                    String cfo_id = (String) session.getAttribute("employeeId");
                    formData.setCfo_id(cfo_id);
                    dao.updateCfoApproval(formData);
                    dao.updateDeviationEmp(formData);
                    dao.sendSettlementMail(formData, formData.getStatus());
                }
                if (formData.getApprover_type().equals("finance")) {
                    String finance_id = (String) session.getAttribute("employeeId");
                    formData.setFinance_id(finance_id);
                    dao.updateFinanceApproval(formData);
                    dao.updateDeviationEmp(formData);
                    dao.sendSettlementMail(formData, formData.getStatus());
                }
                if (formData.getApprover_type().equals("treasury")) {
                    String treasury_id = (String) session.getAttribute("employeeId");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date billDate = sdf1.parse(formData.getDepositedDate());
                    formData.setDepositedDate(sdf.format(billDate));
                    formData.setTreasury_id(treasury_id);
                    dao.updateTreasuryApproval(formData);
                    dao.updateDeviationEmp(formData);
                    dao.sendSettlementMail(formData, "21");
                }
                if (formData.getApprover_type().equals("payroll")) {
                    String payroll_id = (String) session.getAttribute("employeeId");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date billDate = sdf1.parse(formData.getRecoveredDate());
                    formData.setRecoveredDate(sdf.format(billDate));
                    formData.setPayroll_id(payroll_id);
                    dao.updatePayrollApproval(formData);
                    dao.updateDeviationEmp(formData);
                    dao.sendSettlementMail(formData, "22");
                }
                String type = formData.getList_type();
                String approver_type = formData.getApprover_type();
                mv = new ModelAndView("redirect:settlementPendingApprovels.htm?type=" + type + "&approver_type=" + approver_type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView editSettlement(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto formData) throws Exception {
        try {
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            List<TravelSettlementDto> advanceDetails = null;
            List<TravelSettlementDto> adminExpenseList = null;
            List<TravelSettlementDto> empExpenseList = null;
            List<TravelSettlementDto> catlist = null;
            List<TravelSettlementDto> attachList = null;
            TravelSettlementDto expenseList = null;
            String ticketId = request.getParameter("ticketId");
            String viewFileName = "y";
            String masterId = null;
            Map<String, String> calmap = new HashMap<String, String>();
            calmap.put("250", "tic_cal");
            calmap.put("251", "lod_cal");
            calmap.put("252", "boar_cal");
            calmap.put("253", "conv_cal");
            calmap.put("254", "mis_cal");

            Map<String, String> elimap = new HashMap<String, String>();
            elimap.put("250", "tic_eli_cal");
            elimap.put("251", "lod_eli_cal");
            elimap.put("252", "boar_eli_cal");
            elimap.put("253", "conv_eli_cal");
            elimap.put("254", "mis_eli_cal");

            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                mv = new ModelAndView("travelSettlement/editSettlement");
                TravelSettlementDto travelDetails = dao.getDetails(ticketId);
                List<TravelSettlementDto> citylist = dao.getCityList(travelDetails.getCountryId());
                String totalAdvance = null;
                if (travelDetails != null) {
                    masterId = travelDetails.getMasterId();
                    //System.out.println(" "+travelDetails.getTravelStartDate()+"  end "+);
//                    travelDetails.setTravelStartDate(travelDetails.getTravelStartDate().split(" ")[0]);
//                    travelDetails.setStartTime(travelDetails.getTravelStartDate().split(" ")[1]);
//                    travelDetails.setTravelEndDate(travelDetails.getTravelEndDate().split(" ")[0]);
//                    travelDetails.setEndTime(travelDetails.getTravelEndDate().split(" ")[1]);
                    advanceDetails = dao.advanceDetails(masterId);
                    empExpenseList = dao.empExpenseList(masterId);
                    adminExpenseList = dao.adminExpenseList(masterId);
                    totalAdvance = dao.totalAdvance(masterId);
                    expenseList = dao.AllexpenceTotals(masterId);
                    attachList = dao.getAttachments(masterId);
                    for (int i = 0; i < empExpenseList.size(); i++) {
                        System.out.println("sdfadsf " + empExpenseList.get(i).getTicTotal());
                    }
                    catlist = dao.getCategories();
                    mv.addObject("advanceDetails", advanceDetails);
                    mv.addObject("totalAdvance", totalAdvance);
                    mv.addObject("expenseList", expenseList);
                    mv.addObject("adminExpenseList", adminExpenseList);
                    mv.addObject("empExpenseList", empExpenseList);
                    mv.addObject("attachList", attachList);
                }

                mv.addObject("travelDetails", travelDetails);
                mv.addObject("catList", catlist);
                mv.addObject("calmap", calmap);
                mv.addObject("elimap", elimap);
                mv.addObject("viewFileName", viewFileName);
                mv.addObject("citylist", citylist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public JSONArray getJson(List<TravelSettlementDto> empExpenseList) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for (TravelSettlementDto data : empExpenseList) {
            obj.put("cat", data.getCategoryType());
            obj.put("fdate", data.getCityId());
            obj.put("fdate", data.getFromDate());
            obj.put("tdate", data.getToDate());
            obj.put("tdate", data.getBill());
            arr.add(obj);
            obj = new JSONObject();
        }
        return arr;
    }

    public ModelAndView updateSettlement(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto formData) throws Exception {
        try {
            mv = new ModelAndView("redirect:getList.htm");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("employeeId");
            String actionbtn = request.getParameter("actionbtn");
            String ticketRefId = (String) request.getParameter("ticketRefId");
            String lastInsertId = null;
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
            TravelSettlementDto expencydto = null;
            if (employeeId == null || "".equals(employeeId)) {
                mv = new ModelAndView("session_expired");
            } else {
                String masterId = formData.getMasterId();
                expencydto = new TravelSettlementDto();
                expencydto.setMasterId(masterId);
                expencydto.setSettlementBy(CommonConfigurations.EMPLOYEE);
                expencydto.setEmpId(employeeId);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                System.out.println("date formar " + dateFormat.format(date));
                formData.setStatus(actionbtn);
                formData.setBookingStatus(actionbtn);
                formData.setFromCity(request.getParameter("fromCity"));
                formData.setToCity(request.getParameter("toCity"));
                formData.setSubmittedDate(dateFormat.format(date));
                formData.setFromDate(request.getParameter("startDate"));
                formData.setToDate(request.getParameter("endDate"));
                System.out.println("startDate : " + formData.getFromDate() + "  endDate : " + formData.getToDate());
                formData.setTotal_boarding_amount(request.getParameter("borTotExp"));
                formData.setTotal_ticket_amount(request.getParameter("ticTotExp"));
                formData.setTotal_lodging_amount(request.getParameter("lodTotExp"));
                formData.setTotal_conveyance_amount(request.getParameter("conTotExp"));
                formData.setTotal_miscellaneous_amount(request.getParameter("misTotExp"));
                formData.setOverall_total_amount(request.getParameter("totTotExp"));
                String adminAction = request.getParameter("admin_action");
                System.out.println(" ticTot : " + formData.getTicTotal());
                String cfo_action_required = "N";
                String devn = "N";
                System.out.println("settlement policy "+formData.getSettlementType());
                if(formData.getSettlementType().equals("h")){
                    if(Integer.parseInt(formData.getDeviationPercent())>0&&Integer.parseInt(formData.getDeviationPercent())<9){
                        devn = "Y";
                    }else if(Integer.parseInt(formData.getDeviationPercent())>9){
                        devn = "Y";
                        cfo_action_required = "N";
                    }else{
                        devn = "N";
                        cfo_action_required = "N";
                    }
                }else if(formData.getSettlementType().equals("c")){
                    devn = "Y";
                    cfo_action_required = "N";
                }
                formData.setEmployee_action("1");
                formData.setDeviation(devn);expencydto.setDeviation(devn);
                expencydto.setSettlementType(formData.getSettlementType());
                formData.setCfo_action(cfo_action_required);expencydto.setCfo_action(cfo_action_required);
                TravelSettlementDto checkSettlement = dao.checkSettlementMaster(masterId);
                if(adminAction.equals("N")){
                    formData.setAdminAction("1");
                }else{
                    formData.setAdminAction(checkSettlement.getAdminAction());
                }
                if (checkSettlement == null) {
                    lastInsertId = dao.addEmployeeSettlement(formData);
                    dao.updateDeviationEmp(formData);
                    expencydto.setTp_settlement_id(lastInsertId);
                } else {
                    lastInsertId = checkSettlement.getId();
                    formData.setId(lastInsertId);
                    expencydto.setTp_settlement_id(lastInsertId);
                    dao.updateEmployeeSettlement(formData);
                    dao.updateDeviationEmp(formData);
                }
                for (int i = 0; i < formData.getExpenseId().length; i++) {
                    if ((formData.getDelStat()[i] == "0" || formData.getDelStat()[i].equals("0"))
                            && (formData.getExpenseId()[i] == "0" || formData.getExpenseId()[i].equals("0"))) {
                        expencydto.setCategory(formData.getRes_category()[i]);
                        expencydto.setBill(formData.getRes_bill()[i]);
                        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fromDate = sdf1.parse(formData.getRes_fromDate()[i]);
                        expencydto.setFromDate(sdf.format(fromDate));
                        Date toDate = sdf1.parse(formData.getRes_toDate()[i]);
                        expencydto.setToDate(sdf.format(toDate));
                        if ("y".equalsIgnoreCase(formData.getRes_bill()[i])) {
                            Date billDate = sdf1.parse(formData.getRes_billDate()[i]);
                            expencydto.setBillDate(sdf.format(billDate));
                            expencydto.setBillNo(formData.getRes_BillNo()[i]);
                        }else{
                            expencydto.setBillDate(null);
                            expencydto.setBillNo(null);
                        }
                        expencydto.setEligibiityAmount(formData.getRes_eligibility()[i]);
                        expencydto.setTax(formData.getTic_tax()[i]);
                        expencydto.setTicTotal(formData.getTic_tot()[i]);
                        expencydto.setAmount(formData.getRes_amount()[i]);
                        expencydto.setFcityId(formData.getRes_city_id()[i]);
                        expencydto.setDelstatus(formData.getDelStat()[i]);
                        expencydto.setRemarks(formData.getRes_remarks()[i]);
                        dao.addAdminExpency(expencydto);
                    } else {
                        if (!formData.getExpenseId()[i].equals("0")) {
                            expencydto.setId(formData.getExpenseId()[i]);
                            expencydto.setCategory(formData.getRes_category()[i]);
                            expencydto.setBill(formData.getRes_bill()[i]);
                            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            if ("y".equalsIgnoreCase(formData.getRes_bill()[i])) {
                                Date billDate = sdf1.parse(formData.getRes_billDate()[i]);
                                expencydto.setBillDate(sdf.format(billDate));
                                expencydto.setBillNo(formData.getRes_BillNo()[i]);
                            }else{
                                expencydto.setBillDate(null);
                                expencydto.setBillNo(null);
                            }
                            Date fromDate = sdf1.parse(formData.getRes_fromDate()[i]);
                            expencydto.setFromDate(sdf.format(fromDate));
                            Date toDate = sdf1.parse(formData.getRes_toDate()[i]);
                            expencydto.setToDate(sdf.format(toDate));
                            expencydto.setDelstatus(formData.getDelStat()[i]);
                            expencydto.setEligibiityAmount(formData.getRes_eligibility()[i]);
                            expencydto.setTax(formData.getTic_tax()[i]);
                            expencydto.setTicTotal(formData.getTic_tot()[i]);
                            expencydto.setAmount(formData.getRes_amount()[i]);
                            expencydto.setCityId(formData.getRes_city_id()[i]);
                            expencydto.setFcityId(formData.getRes_city_id()[i]);
                            expencydto.setRemarks(formData.getRes_remarks()[i]);
                            dao.updateExpenses(expencydto);
                        }
                    }
                }
                MultipartFile uploadedFile = null;
                int file_length = Integer.parseInt(request.getParameter("attachmentCount"));
                if(file_length>0){
                    for (int i = 0; i < formData.getAttachId().length; i++) {
                        if ((formData.getAttachdelStat()[i] == "0" || formData.getAttachdelStat()[i].equals("0"))
                                && (formData.getAttachId()[i] != "0" || !formData.getAttachId()[i].equals("0"))) {
                            MultipartFile files = multipartRequest.getFile("fileAttachment_" + (i + 1));
                            System.out.println("file name : " + files);
                            if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                                dao.uploadAttachment(request, files, masterId, formData.getFile_category()[i], ticketRefId);
                            } else {
                                String filename = formData.getAttachments()[i];
                                dao.updateFile(request, filename, masterId, formData.getFile_category()[i], formData.getAttachId()[i], formData.getAttachdelStat()[i]);
                            }
                        } else {
                            MultipartFile files = multipartRequest.getFile("fileAttachment_" + (i + 1));
                            if (!files.getOriginalFilename().equals("") && files.getSize() > 0) {
                                dao.updateAttachment(request, files, formData.getAttachId()[i], masterId, formData.getFile_category()[i], formData.getAttachdelStat()[i], ticketRefId);
                            } else {
                                String filename = formData.getAttachments()[i];
                                dao.updateFile(request, filename, masterId, formData.getFile_category()[i], formData.getAttachId()[i], formData.getAttachdelStat()[i]);

                            }
                        }
                    }
                }
                
                if (actionbtn.equals("12")) {
                    dao.sendSettlementMail(expencydto, actionbtn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto formData) throws Exception {
        String fileName = request.getParameter("file_name");
        String fileId = request.getParameter("file_id");
        try {
            response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
            File file = new File(CommonConfigurations.fileDownloadPath + "\\" + fileName);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            byte[] buf = new byte[4 * 1024];
            int bytesRead;
            while ((bytesRead = in.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public ModelAndView saveBillDetails(HttpServletRequest request, HttpServletResponse response, TravelSettlementDto formData) throws Exception {
        HttpSession session = request.getSession();
        String employeeId = (String) session.getAttribute("employeeId");
        String approver_type = request.getParameter("approver_type");
        String list_type = (String) request.getParameter("list_type");
        final WebApplicationContext ctx = getWebApplicationContext();
        TravelSettlementDaoImpl dao = (TravelSettlementDaoImpl) ctx.getBean("TravelSettlementDao");
        if (employeeId == null || "".equals(employeeId)) {
            mv = new ModelAndView("session_expired");
        } else {
            formData.setFinance_id(employeeId);
            if(formData.getBillReceived().equals("Yes")){
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date billDate = sdf1.parse(formData.getBillReceivedDate());
                formData.setBillReceivedDate(sdf.format(billDate));
            }else{
                formData.setBillReceivedDate(null);
            }
            dao.updateBillDetails(formData);
            mv = new ModelAndView("redirect:settlementPendingApprovels.htm?type=" + list_type + "&approver_type=" + approver_type);
        }
        return mv;
    }
}
