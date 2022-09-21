/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.controller;

import com.defiance.ideal.application.dto.LoginDTO;
import com.defiance.ideal.application.dto.RequestorDTO;
import com.defiance.ideal.application.service.RequestServiceImpl;
import com.defiance.ideal.application.util.CommonConfigurations;
import com.defiance.ideal.application.util.CommonMethods;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14355
 */
public class RequestorController extends MultiActionController {
    
    HttpSession session;
    ModelAndView mv = null;
    
    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
    public DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public ModelAndView qualityCustomerList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<RequestorDTO> dashBoardList = null;
        RequestorDTO empInfo = null;
        String moduleId = null;
        String list = null;
        String commonConfigModuleId = CommonConfigurations.QUALITY_RESPONSE_MODULE_ID;
        try {
            String selectedValue = null;
            list = request.getParameter("list");
            String empId = (String) session.getAttribute("EMP_ID");
            moduleId = (String) session.getAttribute("Module_ID");
            RequestServiceImpl serviceImpl = (RequestServiceImpl) getWebApplicationContext().getBean("RequestorService");
            dashBoardList = serviceImpl.getdashBoardList(empId, moduleId, selectedValue, list);
            empInfo = serviceImpl.getEmpDetails(empId);
            session.setAttribute("empInfo", empInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (moduleId.equals(CommonConfigurations.QUALITY_REQUEST_MODULE_ID) || (moduleId.equals(CommonConfigurations.QUALITY_RESPONSE_MODULE_ID) && list.equalsIgnoreCase("pending"))) {
            mv = new ModelAndView("/qualityCustomerList");
        } else if (moduleId.equals(CommonConfigurations.QUALITY_RESPONSE_MODULE_ID) && list.equalsIgnoreCase("processed")) {
            mv = new ModelAndView("/qualityCustomerList");
        } else if (moduleId.equals(CommonConfigurations.QUALITY_REPORT_MODULE_ID)) {
            mv = new ModelAndView("/qualityreportList");
        }
        mv.addObject("dashBoardList", dashBoardList);
        mv.addObject("moduleId", moduleId);
        mv.addObject("commonConfigModuleId", commonConfigModuleId);
        mv.addObject("feedbackType", empInfo.getFeedbackValues());
        mv.addObject("listType", list);
        return mv;
    }
    
    public ModelAndView newRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestorDTO requestformData) throws Exception {

//        LoginDTO sessionObj = this.getSessionValues(request);
        RequestorDTO empInfo = null;
        String empId = null;
        try {
            empId = (String) session.getAttribute("EMP_ID");
            empInfo = (RequestorDTO) session.getAttribute("empInfo");
            mv = new ModelAndView("/newRequest");
            mv.addObject("empDetails", empInfo.getEmpInfo());
            mv.addObject("feedbackType", empInfo.getFeedbackValues());
            mv.addObject("improvementType", empInfo.getImprovementvalues());
            mv.addObject("processType", empInfo.getProcessValues());
            mv.addObject("employeeId", empId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return mv;
    }
    
    public ModelAndView addNewRequest(HttpServletRequest request, HttpServletResponse response, RequestorDTO formData) {
        String listType = null;
        try {
//            String empId = request.getParameter("employeeId");
//            String description = request.getParameter("description");
//            String reqDate = request.getParameter("reqDate");
            listType = request.getParameter("list");
            RequestServiceImpl serviceImpl = (RequestServiceImpl) getWebApplicationContext().getBean("RequestorService");
            serviceImpl.insertNewRequest(formData);
            Integer lastInsertId = serviceImpl.getLastInsert();
            String maxInserId = lastInsertId.toString();


            //Update reference Id in Master Table
            String updatedRefId = null;
            int reqYear = CommonMethods.getCurrentYear();
            Calendar cal = Calendar.getInstance();
            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
            String month = null;
            month = months[(cal.get(Calendar.MONTH))];
            String refDate = reqYear + month;
            if (maxInserId.length() == 1) {
                updatedRefId = refDate + "0000" + lastInsertId;
            } else if (maxInserId.length() == 2) {
                updatedRefId = refDate + "000" + lastInsertId;
            } else if (maxInserId.length() == 3) {
                updatedRefId = refDate + "00" + lastInsertId;
            } else if (maxInserId.length() == 4) {
                updatedRefId = refDate + "0" + lastInsertId;
            } else if (maxInserId.length() == 5) {
                updatedRefId = refDate + lastInsertId;
            }            
            serviceImpl.updateReferenceId(updatedRefId, lastInsertId);
            
            // For File Upload
            MultipartFile file = formData.getFileName();
            if (file != null) {
                if (!"".equals(file.getOriginalFilename())) {
                    String temp = file.getOriginalFilename();
                    File file1 = new File(CommonConfigurations.fileUploadPath + updatedRefId + temp);
                    file1.createNewFile();
                    file.transferTo(file1);
                    formData.setFile(updatedRefId + file.getOriginalFilename());
                    formData.setContentType(file.getContentType());
                    formData.setModuleName("QF");
                    formData.setReferenceName("Quality_FeedBack");
                    formData.setRequestId(updatedRefId);
                    serviceImpl.insertFile(formData);
                }
            }
            serviceImpl.triggerMailAction(formData, updatedRefId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:qualityCustomerList.htm?list=" + listType);
    }
    
    public ModelAndView getRequestDetails(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        RequestorDTO individualRequestDetails = null;
        RequestorDTO empInfo = null;
        RequestorDTO qualityEmpDetails = null;
        RequestorDTO fileDetails = null;
        List<RequestorDTO> focusId = null;
        String listType = request.getParameter("list");
        String empId = (String) session.getAttribute("EMP_ID");
        String moduleId = (String) session.getAttribute("Module_ID");
        String commonConfigModuleId = CommonConfigurations.QUALITY_RESPONSE_MODULE_ID;
        RequestServiceImpl serviceImpl = (RequestServiceImpl) getWebApplicationContext().getBean("RequestorService");
        try {
            String refId = request.getParameter("refId");
            empInfo = (RequestorDTO) session.getAttribute("empInfo");
            individualRequestDetails = serviceImpl.getRequestDetails(refId);
            fileDetails = serviceImpl.getFiledownload(refId);
            qualityEmpDetails = serviceImpl.qualityInformation(empId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (individualRequestDetails.getQualityId() != null) {
            if (moduleId.equals(commonConfigModuleId)) {
                if (individualRequestDetails.getFeedbackType().equalsIgnoreCase("p")) {
                    if (individualRequestDetails.getEvalStatus().equalsIgnoreCase("a") && individualRequestDetails.getReqStatus().equalsIgnoreCase("c")) {
                        mv = new ModelAndView("/qualityProcessedList");
                    } else {
                        mv = new ModelAndView("/qualityCustomerView");
                    }
                } else if (individualRequestDetails.getFeedbackType().equalsIgnoreCase("c") || individualRequestDetails.getFeedbackType().equalsIgnoreCase("q")) {
                    mv = new ModelAndView("/qualityProcessedList");
                }
                
            } else if (!moduleId.equals(commonConfigModuleId)) {
                mv = new ModelAndView("/qualityCustomerView");
            }
            
        } else if (individualRequestDetails.getQualityId() == null) {
            mv = new ModelAndView("/qualityCustomerView");
        }
        mv.addObject("empDetails", individualRequestDetails);
        mv.addObject("fileDetails", fileDetails);
        mv.addObject("feedbackType", empInfo.getFeedbackValues());
        mv.addObject("evalStatus", empInfo.getEvalStatusValues());
        mv.addObject("reqStatus", empInfo.getReqStatusValues());
        mv.addObject("moduleId", moduleId);
        mv.addObject("empId", empId);
        mv.addObject("commonConfigModuleId", commonConfigModuleId);
        mv.addObject("listType", listType);
        mv.addObject("qualityEmpDetails", qualityEmpDetails);
        if (individualRequestDetails.getFeedbackType() != null) {
            if (individualRequestDetails.getFeedbackType().equalsIgnoreCase("p")) {
                mv.addObject("improvementType", empInfo.getImprovementvalues());
                mv.addObject("processType", empInfo.getProcessValues());
                focusId = serviceImpl.getFocusArea(individualRequestDetails.getProcessArea());
                mv.addObject("focusType", focusId);
            }
        }
        return mv;
    }
    
    public ModelAndView getFocusArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("json.jsp");
        response.getWriter().println("<option value=''>--Select--</option>");
        for (RequestorDTO dTO : ((RequestServiceImpl) getWebApplicationContext().getBean("RequestorService")).getFocusArea((request.getParameter("id")))) {
            response.getWriter().println("<option value='" + dTO.getConfigKey() + "'>" + dTO.getConfigValue() + "</option>");
        }
        return null;
    }
    
    public ModelAndView fileDownload(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            FileInputStream fis = new FileInputStream(CommonConfigurations.fileUploadPath + fileName);
            byte[] bs = new byte[1024];
            int len = 0;
            while ((len = fis.read(bs, 0, bs.length)) != -1) {
                response.getOutputStream().write(bs, 0, len);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RequestorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RequestorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ModelAndView updateRequest(HttpServletRequest request, HttpServletResponse response, RequestorDTO formData) {
        String listType = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            listType = request.getParameter("list");
            RequestServiceImpl serviceImpl = (RequestServiceImpl) getWebApplicationContext().getBean("RequestorService");
            //System.out.println("-----------"+formData.getEvalStatus());
            
            String requestClosedDate = null;
            if (formData.getFeedbackType().equals("p")) {
                if (formData.getEvalStatus() != "0") {
                    if (formData.getEvalStatus().equals("a")) {
                        requestClosedDate = request.getParameter("acceptexpClosureDate");                        
                    } else if (formData.getEvalStatus().equals("r")) {
                        requestClosedDate = request.getParameter("rejectexpClosureDate");                        
                    } else if (formData.getEvalStatus().equals("d")) {
                        requestClosedDate = request.getParameter("defferedexpClosureDate");                        
                    }
                    formData.setExpClosureDate(dateFormat.parse(requestClosedDate));
                    System.out.println("closed date-----" + formData.getExpClosureDate());
                }
            }
            serviceImpl.updateRequest(formData);
            serviceImpl.triggerMailAction(formData, formData.getReferenceId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:qualityCustomerList.htm?list=" + listType);
    }
    
    public ModelAndView getReportList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<RequestorDTO> reportList = null;
        try {
            String selectedValue = null;
            String list = request.getParameter("list");
            String empId = (String) session.getAttribute("EMP_ID");
            String moduleId = (String) session.getAttribute("Module_ID");
            RequestServiceImpl serviceImpl = (RequestServiceImpl) getWebApplicationContext().getBean("RequestorService");
            reportList = serviceImpl.getdashBoardList(empId, moduleId, selectedValue, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv = new ModelAndView("/qualityreportList");
        mv.addObject("dashBoardList", reportList);
        return mv;
    }
    
    public ModelAndView getEmployeeList(HttpServletRequest request, HttpServletResponse response, RequestorDTO filterData) {
        mv = new ModelAndView("/ajax");
        try {
            RequestServiceImpl serviceImpl = (RequestServiceImpl) getApplicationContext().getBean("RequestorService");
            String empVal = request.getParameter("q");
            List<RequestorDTO> empRes = serviceImpl.getEmployeeList(empVal);
            mv.addObject("employeeList", empRes);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
    public ModelAndView generateReport(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestorDTO requestorDTO) {
        List<RequestorDTO> reportList = null;
        RequestorDTO feedbacktype = (RequestorDTO) session.getAttribute("empInfo");
        try {
            String list = request.getParameter("list");
            String selectedValue = " ";
            String empId = (String) session.getAttribute("EMP_ID");
            String moduleId = (String) session.getAttribute("Module_ID");
            if (requestorDTO.getEmployee_id() != null && !"".equals(requestorDTO.getEmployee_id())) {
                selectedValue = selectedValue + "AND emp.id=" + requestorDTO.getEmployee_id() + " ";
            }
            if (requestorDTO.getFeedbackType() != null && !"".equals(requestorDTO.getFeedbackType())) {
                selectedValue = selectedValue + "AND feedback_type='" + requestorDTO.getFeedbackType() + "' ";
            }
            RequestServiceImpl serviceImpl = (RequestServiceImpl) getWebApplicationContext().getBean("RequestorService");
            reportList = serviceImpl.getdashBoardList(empId, moduleId, selectedValue, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv = new ModelAndView("/qualityreportList");
        mv.addObject("dashBoardList", reportList);
        mv.addObject("feedbackType", feedbacktype.getFeedbackValues());
        return mv;
        
    }
    
    public ModelAndView getRequestExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session, RequestorDTO requestorDTO) throws Exception {
        String empId = (String) session.getAttribute("EMP_ID");
        String moduleId = (String) session.getAttribute("Module_ID");
        String selectedValue = "";
        String list = request.getParameter("list");
        if (requestorDTO.getEmployee_id() != null && !"".equals(requestorDTO.getEmployee_id())) {
            selectedValue = selectedValue + "AND emp.id=" + requestorDTO.getEmployee_id() + " ";
        }
        if (requestorDTO.getFeedbackType() != null && !"".equals(requestorDTO.getFeedbackType())) {
            selectedValue = selectedValue + "AND feedback_type='" + requestorDTO.getFeedbackType() + "' ";
        }
        List<RequestorDTO> reportList = ((RequestServiceImpl) getWebApplicationContext().getBean("RequestorService")).getdashBoardList(empId, moduleId, selectedValue, list);
        RequestorDTO empInfo = (RequestorDTO) session.getAttribute("empInfo");
        List<RequestorDTO> feedBackValues = empInfo.getFeedbackValues();
        List<RequestorDTO> improvementProcess = empInfo.getImprovementvalues();
        List<RequestorDTO> processArea = empInfo.getProcessValues();
        List<RequestorDTO> evaluationStatus = empInfo.getEvalStatusValues();
        List<RequestorDTO> requuestStatus = empInfo.getReqStatusValues();
        RequestServiceImpl serviceImpl = (RequestServiceImpl) getWebApplicationContext().getBean("RequestorService");
        try {
            ArrayList reviewerInfo = new ArrayList();
            ArrayList headerData = new ArrayList();
            headerData.add(new String("Reference Number"));
            headerData.add(new String("Requestor Number"));
            headerData.add(new String("Requestor Name"));
            headerData.add(new String("Requestor Location"));
            headerData.add(new String("Request Raised Date"));
            headerData.add(new String("FeedBack Type"));
            headerData.add(new String("Description"));
            headerData.add(new String("Improvement Request Type"));
            headerData.add(new String("Process Area"));
//            headerData.add(new String("Focus Area"));
            headerData.add(new String("Justification"));
            headerData.add(new String("Expected Closure Date"));
            headerData.add(new String("Request Closed Date"));
            headerData.add(new String("Responded Date"));
            headerData.add(new String("Evaluation Status"));
            headerData.add(new String("Request Status"));
            headerData.add(new String("Root Cause Analysis"));
            reviewerInfo.add(headerData);
            ArrayList entireList = new ArrayList();
            
            
            
            for (int i = 0; i < reportList.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + reportList.get(i).getReferenceId()));
                rowDataList.add(new String("" + reportList.get(i).getEmployeeId()));
                rowDataList.add(new String("" + reportList.get(i).getEmployeeName()));
                if (reportList.get(i).getEmpLocation() != null) {
                    rowDataList.add(new String("" + reportList.get(i).getEmpLocation()));
                } else if (reportList.get(i).getEmpLocation() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getReqDate() != null) {
                    rowDataList.add(new String("" + dateFormat.format(reportList.get(i).getReqDate())));
                } else if (reportList.get(i).getReqDate() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getFeedbackType() != null && !"".equalsIgnoreCase(reportList.get(i).getFeedbackType())) {
                    int fed;
                    for (fed = 0; fed < feedBackValues.size(); fed++) {
                        if (reportList.get(i).getFeedbackType().equalsIgnoreCase(feedBackValues.get(fed).getConfigKey())) {
                            rowDataList.add((new String("" + feedBackValues.get(fed).getConfigValue())));
                        }
                    }
                } else if (reportList.get(i).getFeedbackType() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getDescription() != null) {
                    rowDataList.add(new String("" + reportList.get(i).getDescription()));
                } else if (reportList.get(i).getDescription() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getImprovementRequest() != null) {
                    for (int pi = 0; pi < improvementProcess.size(); pi++) {
                        if (reportList.get(i).getImprovementRequest().equalsIgnoreCase(improvementProcess.get(pi).getConfigKey())) {
                            rowDataList.add((new String("" + improvementProcess.get(pi).getConfigValue())));
                        }
                    }
                    
                } else if (reportList.get(i).getImprovementRequest() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getProcessArea() != null) {
                    for (int pa = 0; pa < processArea.size(); pa++) {
                        if (reportList.get(i).getProcessArea().equalsIgnoreCase(processArea.get(pa).getParentId())) {
                            rowDataList.add((new String("" + processArea.get(pa).getConfigValue())));
                        }
                    }
                } else if (reportList.get(i).getProcessArea() == null) {
                    rowDataList.add(new String("---"));
                }
//                if (reportList.get(i).getFocusArea() != null) {
//                    List<RequestorDTO> focesArea = serviceImpl.getFocusArea(reportList.get(i).getProcessArea());
//                    for (int fa = 0; fa < focesArea.size(); fa++) {
//                        if (reportList.get(i).getFocusArea().equalsIgnoreCase(focesArea.get(fa).getConfigKey())) {
//                            rowDataList.add((new String("" + focesArea.get(fa).getConfigValue())));
//                        }
//                    }
//                } else if (reportList.get(i).getFocusArea() == null) {
//                    rowDataList.add(new String("---"));
//                }
                if (reportList.get(i).getJustification() != null) {
                    rowDataList.add(new String("" + reportList.get(i).getJustification()));
                } else if (reportList.get(i).getJustification() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getExpClosureDate() != null) {
                    rowDataList.add(new String("" + dateFormat.format(reportList.get(i).getExpClosureDate())));
                } else if (reportList.get(i).getExpClosureDate() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getReqClosedDate() != null) {
                    rowDataList.add(new String("" + dateFormat.format(reportList.get(i).getReqClosedDate())));
                } else if (reportList.get(i).getReqClosedDate() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getQualClosedDate() != null) {
                    rowDataList.add(new String("" + dateFormat.format(reportList.get(i).getQualClosedDate())));
                } else if (reportList.get(i).getQualClosedDate() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getEvalStatus() != null) {
                    for (int ev = 0; ev < evaluationStatus.size(); ev++) {
                        if (reportList.get(i).getEvalStatus().equalsIgnoreCase(evaluationStatus.get(ev).getConfigKey())) {
                            rowDataList.add((new String("" + evaluationStatus.get(ev).getConfigValue())));
                        }
                    }
                } else if (reportList.get(i).getEvalStatus() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getReqStatus() != null) {
                    for (int ev = 0; ev < evaluationStatus.size(); ev++) {
                        if (reportList.get(i).getReqStatus().equalsIgnoreCase(requuestStatus.get(ev).getConfigKey())) {
                            rowDataList.add((new String("" + requuestStatus.get(ev).getConfigValue())));
                        }
                    }
                } else if (reportList.get(i).getReqStatus() == null) {
                    rowDataList.add(new String("---"));
                }
                if (reportList.get(i).getRootCauseAnalysis() != null) {
                    rowDataList.add(new String("" + reportList.get(i).getRootCauseAnalysis()));
                } else if (reportList.get(i).getRootCauseAnalysis() == null) {
                    rowDataList.add(new String("---"));
                }
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcelQuality(response, reviewerInfo, entireList, "Quality_Report");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public LoginDTO getSessionValues(HttpServletRequest request) {
        LoginDTO sessionObj = new LoginDTO();
        String employeeId = (String) request.getSession().getAttribute("employeeId");
        String moduleId = (String) request.getSession().getAttribute("moduleId");
        sessionObj.setEmpId(employeeId);
        sessionObj.setModuleId(moduleId);
        return sessionObj;
    }
    
    public Object getBean(String beanName) {
        Object o = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            return ctx.getBean(beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
//    private ModelAndView getRequestList(HttpServletRequest request, HttpServletResponse response, RequestorDTO filterData) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
}
