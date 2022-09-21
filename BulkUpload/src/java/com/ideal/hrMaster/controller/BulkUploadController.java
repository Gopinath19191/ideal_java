/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.controller;

import com.ideal.hrMaster.dto.ApplicantDTO;
import com.ideal.hrMaster.dto.AuditListDto;
import com.ideal.hrMaster.dto.EmployeHistoryDto;
import com.ideal.hrMaster.service.BulkUploadService;
import com.ideal.hrMaster.util.CommonConfig;
import com.ideal.pc.ticket.controller.SendMailTLS;
import com.ideal.system.ticket.util.MailMessages;
import java.io.File;
import com.ideal.system.ticket.util.CommonMethods;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16113
 */
public class BulkUploadController extends MultiActionController {

    String returnMsg = null;

    public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response) {
        HttpSession HttpSessionsession = request.getSession();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Type", "application/force-download");

        ModelAndView mvc = new ModelAndView("/bulkUpload");
        try {
            FileInputStream fis = null;
            if (request.getParameter("fileName").equals("band")) {
                response.setHeader("Content-Disposition", "attachment;filename=BAND_DESIGNATION.xls");
                fis = new FileInputStream("D:/propertyfiles/bulkUpload_Template/BAND_DESIGNATION.xls");
            } else if (request.getParameter("fileName").equals("RM")) {
                response.setHeader("Content-Disposition", "attachment;filename=RM_CHANGE.xls");
                fis = new FileInputStream("D:/propertyfiles/bulkUpload_Template/RM_CHANGE.xls");
            } else if (request.getParameter("fileName").equals("PRACTICE")) {
                response.setHeader("Content-Disposition", "attachment;filename=PTRACTICE_MAPPING.xls");
                fis = new FileInputStream("D:/propertyfiles/bulkUpload_Template/PTRACTICE_MAPPING.xls");
            }
            byte[] bs = new byte[1024];
            int len = 0;
            while ((len = fis.read(bs, 0, bs.length)) != -1) {
                response.getOutputStream().write(bs, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public ModelAndView updateEmployeeBandDesigByExcel(HttpServletRequest request, HttpServletResponse response) {

        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        ModelAndView mvc = new ModelAndView("/bulkUpload");
        AuditListDto auditdto = new AuditListDto();
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map files = multiRequest.getFileMap();
            String uplodedFilename = null;
            HttpSession session = request.getSession();
            String authEmpid = (String) session.getAttribute("employee_no");
            auditdto.setEmployee_number(authEmpid);
            ArrayList bandList = service.getBands();
            ArrayList desiList = service.getDesignations();
            ArrayList empList = service.getEmployeNumbers();
//            System.out.println(" employ list " + empList);
            EmployeHistoryDto authDetails = service.getEmployeId(authEmpid);
            auditdto.setEmployee_number(authDetails.getId());
            File dir = new File(CommonConfig.FILE_UPLOAD_PATH);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            for (MultipartFile file : (Collection<MultipartFile>) files.values()) {
                POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
                HSSFSheet sheet = workBook.getSheetAt(0);
                Iterator rows = sheet.rowIterator();
                String[] mandtCells = {"0", "1", "2", "3"};
                int rowInSheet = 0;
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    // header validation
                    if (row.getRowNum() == 0) {
                        Iterator cells = row.cellIterator();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            if (currentCell == 0) {
                                if (!cellData.equals(CommonConfig.EMP_ID)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 1) {
                                if (!cellData.equals(CommonConfig.EMP_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 2) {
                                if (!cellData.equals(CommonConfig.BAND)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 3) {
                                if (!cellData.equals(CommonConfig.DESIGNATION)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                        }

                    }//header validation

                    // data validation
                    if (row.getRowNum() > 0) {
                        rowInSheet = 1;
                        Iterator cells = row.cellIterator();
                        //logic for mandatory cells
                        for (Short i = 0; i < mandtCells.length; i++) {
                            HSSFCell mandCell = row.getCell(i);
                            if (mandCell == null) {
                                returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + (mandtCells[i]) + " is mandatory ";
                                mvc.addObject("returnMsg", returnMsg);
                                return mvc;
                            }
                        }

                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();

                            if (currentCell == 0) {
                                // String inputParam = cellData.trim();

                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
//                                    System.out.println(" employee id value : " + splitcell);
                                }
                                if (!empList.contains(empnumber)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Employee id invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 2) {
                                String inputParam = cellData;
                                if (!bandList.contains(inputParam)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Band is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 3) {
                                String inputParam = cellData;
                                inputParam = inputParam.toUpperCase();
//                                System.out.println("input "+inputParam);
                                for (int i = 0; i < desiList.size(); i++) {
                                    String s = (String) desiList.get(i);
                                    String uppercase = s.toUpperCase();
//                                    System.out.println("des "+uppercase);
                                    desiList.remove(i);
                                    desiList.add(i, uppercase);
                                }
                                if (!desiList.contains(inputParam)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Designation is invalid(Designation in Upper Case)";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }

                        }//cell iteration

                    }//data validation
                }//row iteration
                if (rowInSheet == 0) {
                    returnMsg = " You are trying to upload empty sheet ";
                    mvc.addObject("returnMsg", returnMsg);
                    return mvc;
                }

                // Inserting Data to database
                Iterator rows1 = sheet.rowIterator();
                while (rows1.hasNext()) {
                    HSSFRow row = (HSSFRow) rows1.next();
                    EmployeHistoryDto dto = new EmployeHistoryDto();
                    EmployeHistoryDto singleEmployData = null;
                    String bandId = null;
                    String employeeId = null;
                    String desId = null;
                    int updateCount = 0;
                    long unixTime = System.currentTimeMillis() / 1000;
                    dto.setModified_time(unixTime + "");
                    Iterator cells = row.cellIterator();
                    if (row.getRowNum() > 0) {
                        dto.setReason("Bulk Upload");
                        dto.setAuthEmpId(authDetails.getId());
                        //dto.setFirst_name(authDetails.getFirst_name());
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            if (String.valueOf(currentCell).equals("0")) {
                                //  String employenumber = cellData.trim();
                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                    singleEmployData = service.getSingleEmployee(empnumber);
                                    employeeId = service.getEmpid(empnumber);
                                    dto.setEmployee_number(employeeId);
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
//                                    System.out.println(" employee id value : " + splitcell);
                                    singleEmployData = service.getSingleEmployee(empnumber);
                                    employeeId = service.getEmpid(empnumber);
                                    dto.setEmployee_number(employeeId);
                                }
                            }
                            if (String.valueOf(currentCell).equals("1")) {
                                dto.setEmployee_name(cellData);
                            }
                            if (String.valueOf(currentCell).equals("2")) {
                                bandId = service.getBandid(cellData);
                                dto.setBand(cellData);
                                dto.setBand_id(bandId);

                            }
                            if (String.valueOf(currentCell).equals("3")) {
                                desId = service.getDesignationid(cellData);
                                dto.setDesignation(cellData);
                                dto.setDesignation_id(desId);

                            }
                        } //cell iteration  
                        try {
//                            System.out.println(" this is before update in employees ");
                            updateCount = service.updateBandDesignation(dto);
//                            System.out.println(" update count : " + updateCount);
                        } catch (Exception e) {
                            e.printStackTrace();
                            mvc.addObject("returnMsg", "Upload failed while updating employee ");
                            return mvc;
                        }
                        if (updateCount > 0) {

                            try {
                                String oldband = singleEmployData.getBand_id();
                                String olddesigantion = singleEmployData.getDesignation_id();
                                if (Integer.parseInt(bandId) != (Integer.parseInt(oldband))) {
                                    dto.setChanged_from(oldband);
                                    dto.setChanged_to(bandId);
                                    dto.setField_changed("Band");
//                                    System.out.println(" this is before insert in employees info history1 for band ");
                                    service.insertEmpHistory(dto);
                                }
                                if (Integer.parseInt(desId) != (Integer.parseInt(olddesigantion))) {
                                    dto.setChanged_from(olddesigantion);
                                    dto.setChanged_to(desId);
                                    dto.setField_changed("Designation");
                                    service.insertEmpHistory(dto);
//                                    System.out.println(" this is before insert in employees info history2 for designation ");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                mvc.addObject("returnMsg", "Upload failed due to insertion ");
                                return mvc;
                            }
                        }//if
                    }


                }//while ending inserting data
                uplodedFilename = CommonConfig.commonCodeForFileUpload(dir, file);
                auditdto.setFile_name(uplodedFilename);
                Date d = new Date();
                SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
                String date = dateFormater.format(d);
                auditdto.setModified_date(date);
                auditdto.setModule_name("BAND & DESIGNATION");
                service.saveAuditHistory(auditdto);
            }
        } catch (Exception e) {
            returnMsg = e.getMessage();
            e.printStackTrace();
            mvc.addObject("returnMsg", "Upload failed ");
            return mvc;
        }
        mvc.addObject("successMsg", "successfully uploaded");
        return mvc;
    }

    public ModelAndView updateEmployeeMangerByExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        ModelAndView mvc = new ModelAndView("/bulkUpload");
        AuditListDto auditdto = new AuditListDto();
        ArrayList empList = service.getEmployeNumbers();

        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map files = multiRequest.getFileMap();
            String uplodedFilename = null;
            HttpSession session = request.getSession();
            String authEmpid = (String) session.getAttribute("employee_no");
            System.out.println("authEmpid @ ");
            auditdto.setEmployee_number(authEmpid);
            EmployeHistoryDto authDetails = service.getEmployeId(authEmpid);
            auditdto.setEmployee_number(authDetails.getId());
            File dir = new File(CommonConfig.FILE_UPLOAD_PATH);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            for (MultipartFile file : (Collection<MultipartFile>) files.values()) {
                POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
                HSSFSheet sheet = workBook.getSheetAt(0);
                Iterator rows = sheet.rowIterator();
                String[] mandtCells = {"0", "1", "2", "3"};
                int rowInSheet = 0;
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    // header validation
                    if (row.getRowNum() == 0) {
                        Iterator cells = row.cellIterator();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            if (currentCell == 0) {
                                if (!cellData.equals(CommonConfig.EMP_ID)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 1) {
                                if (!cellData.equals(CommonConfig.EMP_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 2) {
                                if (!cellData.equals(CommonConfig.RM_ID)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 3) {
                                if (!cellData.equals(CommonConfig.RM_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                        }

                    }//header validation

                    // data validation
                    if (row.getRowNum() > 0) {
                        rowInSheet = 1;
                        Iterator cells = row.cellIterator();
                        //logic for mandatory cells
                        for (Short i = 0; i < mandtCells.length; i++) {
                            HSSFCell mandCell = row.getCell(i);
                            if (mandCell == null) {
                                returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + (mandtCells[i]) + " is mandatory ";
                                mvc.addObject("returnMsg", returnMsg);
                                return mvc;
                            }
                        }

                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();

                            if (currentCell == 0) {
                                // String inputParam = cellData.trim();
//                                System.out.println("cell type "+cell.getCellType());
                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
//                                    System.out.println(" employee id value : " + splitcell);
                                }

                                if (!empList.contains(empnumber)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Employee id invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 2) {
                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
//                                    System.out.println(" employee id value : " + splitcell);
                                }
                                if (!empList.contains(empnumber)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Manager id is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }

                        }//cell iteration

                    }//data validation
                }//row iteration
                if (rowInSheet == 0) {
                    returnMsg = " You are trying to upload empty sheet ";
                    mvc.addObject("returnMsg", returnMsg);
                    return mvc;
                }

                // Inserting Data to database
                Iterator rows1 = sheet.rowIterator();
                while (rows1.hasNext()) {
                    HSSFRow row = (HSSFRow) rows1.next();
                    EmployeHistoryDto dto = new EmployeHistoryDto();
                    EmployeHistoryDto singleEmployData = null;
                    EmployeHistoryDto singleEmployData1 = null;
                    String employeeId = null;
                    String managerId = null;
                    String managerName = null;
                    String managerMail = null;
                    String oldmanagerName = null;
                    String oldmanagerMail = null;
                    String employeeName = null;
                    String employeeMail = null;
                    int updateCount = 0;
                    long unixTime = System.currentTimeMillis() / 1000;
                    dto.setModified_time(unixTime + "");
                    Iterator cells = row.cellIterator();
                    if (row.getRowNum() > 0) {
                        dto.setReason("Bulk Upload");
                        dto.setAuthEmpId(authDetails.getId());
                        //dto.setFirst_name(authDetails.getFirst_name());
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            if (String.valueOf(currentCell).equals("0")) {
                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                    singleEmployData1 = service.getSingleEmployee(empnumber);
                                    employeeId = service.getEmpid(empnumber);
                                    employeeName = service.getEmployeeName(empnumber);
                                    employeeMail = service.getEmployeeMail(empnumber);
                                    oldmanagerName = service.getOldManagerName(empnumber);
                                    oldmanagerMail = service.getOldManagerMail(empnumber);
                                    // old managerr nail = service .get odld mangaer mail(Empnumber);
                                    // String System.out.println("test sservere");
                                    // String sout ("employeee name employee number");

                                    dto.setEmployee_number(employeeId);
                                    dto.setEmployeeName(employeeName);
                                    dto.setOldManager(oldmanagerName);
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
//        System.out.println(" employee id value : " + splitcell);
                                    singleEmployData1 = service.getSingleEmployee(empnumber);
                                    employeeId = service.getEmpid(empnumber);
                                    employeeName = service.getEmployeeName(empnumber);
                                    employeeMail = service.getEmployeeMail(empnumber);
                                    oldmanagerName = service.getOldManagerName(empnumber);
                                    oldmanagerMail = service.getOldManagerMail(empnumber);
                                    dto.setEmployee_number(employeeId);
                                    dto.setEmployeeName(employeeName);
                                    dto.setOldManager(oldmanagerName);
                                }
//                                EmployeHistoryDto dto1 = service.getSerlize(employeeId);
//                                //serialize 
//                                try {
//                                    FileOutputStream fos = new FileOutputStream("sample");
//                                    ObjectOutputStream oos = new ObjectOutputStream(fos);
//                                    oos.writeObject(dto1);
//                                    //dto1 = (EmployeHistoryDto) ois.readObject();
//                                    oos.close();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//
//                                }
//                                EmployeHistoryDto dto2 = null;
//                                try {
//                                    dto2 = (EmployeHistoryDto) deserialize("sample");
//                                } catch (ClassNotFoundException ex) {
//                                    Logger.getLogger(BulkUploadController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                                System.out.println("empNew Object::=============" + dto2);

                            }
                            if (String.valueOf(currentCell).equals("1")) {
                                dto.setEmployee_name(cellData);
                            }
                            if (String.valueOf(currentCell).equals("2")) {
                                //  String employenumber = cellData.trim();
                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                    singleEmployData = service.getSingleEmployee(empnumber);
                                    managerId = service.getEmpid(empnumber);
                                    managerName = service.getEmployeeName(empnumber);
                                    managerMail = service.getManagerMail(empnumber);
                                    dto.setManager_id(managerId);
                                    dto.setManagerName(managerName);
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
                                    singleEmployData = service.getSingleEmployee(empnumber);
                                    managerId = service.getEmpid(empnumber);
                                    managerName = service.getEmployeeName(empnumber);
                                    managerMail = service.getManagerMail(empnumber);
                                    dto.setManager_id(managerId);
                                    dto.setManagerName(managerName);
                                }
                            }
                            if (String.valueOf(currentCell).equals("3")) {
                                dto.setManager_name(cellData);
                            }
                        } //cell iteration  
                        try {
//                            System.out.println(" this is before update in employees ");
                            System.out.println("REV %%  update manager");
                            updateCount = service.updateManager(dto);

                            // mail start
//                            Properties configFile = new Properties();
//                            InputStream input = null;
//                            input = new FileInputStream("D:\\propertyfiles\\bulkUpload_Template\\mail_configuration.properties");
//                            configFile.load(input);
//                            System.out.println("mail" + configFile.getProperty("MAIL_USER_NAME"));
//                            System.out.println("empnumber%%% " + dto.getEmployee_number());
//                            System.out.println("first_name %%% " + dto.getEmployeeName());
//                            SendMailTLS mailObject = new SendMailTLS();
//                            MailMessages mailMessageObj = new MailMessages();
//                            String mailTo = "";
//                            String mailSubject = mailMessageObj.getRmSubject("RMchange", dto.getEmployeeName());
//                            String mailMessage = mailMessageObj.getRmMessage(request, "RMchange", dto.getEmployeeName() + "#-#" + dto.getOldManager() + "#-#" + dto.getManagerName() + "#-#" + "test");
//                            String mailCC = "";
//
//                            mailTo = employeeMail;
//                            mailCC = oldmanagerMail + ',' + managerMail;
//                            String oldManger = singleEmployData1.getManager_id();
//                            if (Integer.parseInt(managerId) != (Integer.parseInt(oldManger))) {
//                                //mailObject.smtpMailRM(mailTo, mailSubject, mailMessage, mailCC);
//                            }

//                            System.out.println(" update count : " + updateCount);
                        } catch (Exception e) {
                            e.printStackTrace();
                            mvc.addObject("returnMsg", "Upload failed while updating employee ");
                            return mvc;
                        }
                        if (updateCount > 0) {

                            try {
                                String oldManger = singleEmployData1.getManager_id();
                                if (Integer.parseInt(managerId) != (Integer.parseInt(oldManger))) {
                                    dto.setChanged_from(oldManger);
                                    dto.setChanged_to(managerId);
                                    dto.setField_changed("manager");
//     System.out.println(" this is before insert in employees info history1 for band ");
                                    System.out.println("REV @@ insert emp history");
                                    service.insertEmpHistory(dto);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                mvc.addObject("returnMsg", "Upload failed due to insertion ");
                                return mvc;
                            }
                        }//if
                    }


                }//while ending inserting data
                uplodedFilename = CommonConfig.commonCodeForFileUpload(dir, file);
                auditdto.setFile_name(uplodedFilename);
                Date d = new Date();
                SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
                String date = dateFormater.format(d);
                auditdto.setModified_date(date);
                auditdto.setModule_name("RM CHANGE");
                System.out.println("REV ## insert audit history");
                service.saveAuditHistory(auditdto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mvc.addObject("returnMsg", "Upload failed due to insertion ");
            return mvc;
        }
        mvc.addObject("successMsg", "successfully uploaded");
        return mvc;
    }

    public ModelAndView showAuditList(HttpServletRequest request, HttpServletResponse response, AuditListDto dto) {

        HttpSession session = request.getSession();
        ModelAndView mvc = new ModelAndView("/auditList");
        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        List<AuditListDto> emplist = service.getDistinctEmployees();
//        System.out.println(emplist);
        List<AuditListDto> auditList = service.showAuditList(dto);
        mvc.addObject("auditList", auditList);
        mvc.addObject("emplist", emplist);
        return mvc;
    }

    public ModelAndView searchAuditList(HttpServletRequest request, HttpServletResponse response, AuditListDto dto) {
        ModelAndView mvc = new ModelAndView("/auditList");
        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        List<AuditListDto> emplist = service.getDistinctEmployees();
//        System.out.println(emplist);
        List<AuditListDto> auditList = service.showAuditList(dto);
        mvc.addObject("auditList", auditList);
        mvc.addObject("filter", dto);
        mvc.addObject("emplist", emplist);
        return mvc;
    }

    public ModelAndView downloadAuditExcel(HttpServletRequest request, HttpServletResponse response) {
        HttpSession HttpSessionsession = request.getSession();
        String filename = request.getParameter("filename");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Type", "application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        ModelAndView mvc = new ModelAndView("/bulkUpload");
        try {
            FileInputStream fis = new FileInputStream("D:/bulkUpload/" + filename);
            byte[] bs = new byte[1024];
            int len = 0;
            while ((len = fis.read(bs, 0, bs.length)) != -1) {
                response.getOutputStream().write(bs, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return null;
    }
//    public static Object deserialize(String fileName) throws IOException,
//            ClassNotFoundException {
//        FileInputStream fis = new FileInputStream(fileName);
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        Object obj = ois.readObject();
//        ois.close();
//        return obj;
//    }

    public ModelAndView uploadOjfDetailsByExcel(HttpServletRequest request, HttpServletResponse response) {

        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        ModelAndView mvc = new ModelAndView("/ojfUpload");
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map files = multiRequest.getFileMap();
            String uplodedFilename = null;
            HttpSession session = request.getSession();
            String authEmpid = (String) session.getAttribute("employee_no");
            AuditListDto auditdto = new AuditListDto();
            EmployeHistoryDto authDetails = service.getEmployeId(authEmpid);
            auditdto.setEmployee_number(authDetails.getId());
            ArrayList desiList = service.getDesignations();
            ArrayList sourceList = service.getSourceList();
            //ArrayList bandList = service.getBandList();
            //auditdto.setEmployee_number(authEmpid);
            File dir = new File(CommonConfig.FILE_UPLOAD_PATH);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            for (MultipartFile file : (Collection<MultipartFile>) files.values()) {
                POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
                HSSFSheet sheet = workBook.getSheetAt(0);
                //InputStream fis = file.getInputStream();
                //XSSFWorkbook workBook = new XSSFWorkbook(fis);                
                //XSSFSheet sheet = workBook.getSheetAt(0);
                Iterator rows = sheet.rowIterator();
                String[] mandtCells = {"0", "2", "3", "4", "5", "6", "7", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
                int rowInSheet = 0;
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    // header validation
                    if (row.getRowNum() == 0) {
                        Iterator cells = row.cellIterator();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            if (currentCell == 0) {
                                if (!cellData.equals(CommonConfig.F_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 1) {
                                if (!cellData.equals(CommonConfig.M_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 2) {
                                if (!cellData.equals(CommonConfig.L_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 3) {
                                if (!cellData.equals(CommonConfig.DOB)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 4) {
                                if (!cellData.equals(CommonConfig.PAN_NO)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 5) {
                                if (!cellData.equals(CommonConfig.P_CODE)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 6) {
                                if (!cellData.equals(CommonConfig.C_ID)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 7) {
                                if (!cellData.equals(CommonConfig.PERSONAL_EMAIL)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 8) {
                                if (!cellData.equals(CommonConfig.ALT_EMAIL)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 9) {
                                if (!cellData.equals(CommonConfig.EMP_STAT)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 10) {
                                if (!cellData.equals(CommonConfig.DESI)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 11) {
                                if (!cellData.equals(CommonConfig.RM_ID)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 12) {
                                if (!cellData.equals(CommonConfig.SRC_HIRE)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 13) {
                                if (!cellData.equals(CommonConfig.COMP_STRU)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 14) {
                                if (!cellData.equals(CommonConfig.PRA_GRP)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 15) {
                                if (!cellData.equals(CommonConfig.SPRA_GRP)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 16) {
                                if (!cellData.equals(CommonConfig.BAND)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 17) {
                                if (!cellData.equals(CommonConfig.SUB_BAND)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 18) {
                                if (!cellData.equals(CommonConfig.PASS_NO)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                        }

                    }//header validation

                    // data validation
                    if (row.getRowNum() > 0) {
                        rowInSheet = 1;
                        ApplicantDTO apdto = new ApplicantDTO();
                        Iterator cells = row.cellIterator();
                        //logic for mandatory cells
                        //String[] mandtCells = {"0", "2", "3","4","5","6"};
                        for (int i = 0; i < mandtCells.length; i++) {
                            HSSFCell mandCell = row.getCell(Short.valueOf(mandtCells[i]));
                            if (mandCell == null || mandCell.toString().equals("")) {
                                returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + (mandtCells[i]) + " is mandatory ";
                                mvc.addObject("returnMsg", returnMsg);
                                return mvc;
                            }
                        }
                        //cell iteration
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            //cell.setCellType(Cell.CELL_TYPE_STRING);
                            String cellData = cell.toString();
                            int currentCell = cell.getCellNum();
//                            if (currentCell == 9) {
//                                String inputParam = cellData;
//                                if (!sourceList.contains(inputParam)) {
//                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getColumnIndex()+ " Employment Type is invalid ";
//                                    mvc.addObject("returnMsg", returnMsg);
//                                    return mvc;
//                                }
//                            }                           
                            if (currentCell == 5) {
                                String mergedRrf = String.valueOf(cellData.toString());
                                if (mergedRrf.split("/").length != 3) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " Position Code is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 10) {
                                String desId = service.getDesignationid(cellData);
                                if (desId == null || desId.equals("")) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " Designation is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 12) {
                                String srcofhire = service.getSourceofHire(cellData);
                                if (srcofhire == null || srcofhire.equals("")) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " Source Of Hire is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 13) {
                                String structId = service.getStructureId(cellData);
                                if (structId == null || structId.equals("")) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " Structure is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                } else {
                                    apdto.setPid(structId);
                                }
                            }
                            if (currentCell == 14) {
                                apdto.setPname(cellData);
                                String practId = service.getpracticegrpId(apdto);
                                if (practId == null || practId.equals("")) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " Practice Group is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                } else {
                                    apdto.setPid(practId);
                                }
                            }
                            if (currentCell == 15) {
                                apdto.setPname(cellData);
                                String band = service.getpracticegrpId(apdto);
                                if (band == null || band.equals("")) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " Sub Practice Group is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 16) {
                                String band = service.getBandid(cellData);
                                if (band == null || band.equals("")) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " Band is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 17) {
                                String subBand = service.getBandid(cellData);
                                if (subBand == null || subBand.equals("")) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " Sub Band is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }


                        }//cell iteration  
                    }//data validation
                }//row iteration
                if (rowInSheet == 0) {
                    returnMsg = " You are trying to upload empty sheet ";
                    mvc.addObject("returnMsg", returnMsg);
                    return mvc;
                }

                // Inserting Data to database
                Iterator rows1 = sheet.rowIterator();
                while (rows1.hasNext()) {
                    HSSFRow row = (HSSFRow) rows1.next();
                    ApplicantDTO dto = new ApplicantDTO();
                    //status O for open and C for close                    
                    dto.setCurrentStatus("0");
                    //long unixTime = System.currentTimeMillis() / 1000;                   
                    Iterator cells = row.cellIterator();
                    if (row.getRowNum() > 0) {
                        ApplicantDTO qdto = new ApplicantDTO();
                        String mergedRrf = null;
                        String splitrrf = null;
                        String managerId = null;
                        boolean employment = false;
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            if (String.valueOf(currentCell).equals("0")) {
                                dto.setFirstName(cellData);
                            }
                            if (String.valueOf(currentCell).equals("1")) {
                                dto.setMiddleName(cellData);
                            }
                            if (String.valueOf(currentCell).equals("2")) {
                                dto.setLastName(cellData);
                            }
                            if (String.valueOf(currentCell).equals("3")) {
                                dto.setDateofBirth(CommonMethods.changeDateFormat(cellData));
                            }
                            if (String.valueOf(currentCell).equals("4")) {
                                String pannum = null;
                                if (cell.getCellType() == 1) {
                                    pannum = String.valueOf(cellData.toString());
                                    dto.setPanNo(String.valueOf(pannum));
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    pannum = String.valueOf(splitcell);
                                    dto.setPanNo(pannum);
                                }

                            }
                            if (String.valueOf(currentCell).equals("5")) {
                                mergedRrf = String.valueOf(cellData.toString());
                                mergedRrf = mergedRrf.split("/")[2];
                                dto.setPositionCode(mergedRrf);
                            }
                            if (String.valueOf(currentCell).equals("6")) {
                                if (cell.getCellType() == 1) {
                                    splitrrf = String.valueOf(cellData.toString());
                                    dto.setRrfId(mergedRrf.concat("-").concat(splitrrf));
                                    dto.setCandidateId(splitrrf);
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    splitrrf = String.valueOf(splitcell);
                                    dto.setRrfId(mergedRrf.concat("-").concat(splitrrf));
                                    dto.setCandidateId(splitrrf);
                                }

                            }
                            if (String.valueOf(currentCell).equals("7")) {
                                dto.setPersonalEmail(cellData);
                            }
                            if (String.valueOf(currentCell).equals("8")) {
                                dto.setAlternateEmail(cellData);
                            }
                            if (String.valueOf(currentCell).equals("9")) {
                                if (cellData.equalsIgnoreCase(CommonConfig.Employment)) {
                                    employment = true;
                                }
                                dto.setEmployeType(cellData);
                            }
                            if (String.valueOf(currentCell).equals("10")) {
                                dto.setDesignation(service.getDesignationid(cellData));

                            }
                            if (String.valueOf(currentCell).equals("11")) {

                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                    managerId = service.getEmpid(empnumber);
                                    dto.setRmId(managerId);
                                    dto.setRmEmpNumber(empnumber);
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
                                    managerId = service.getEmpid(empnumber);
                                    dto.setRmId(managerId);
                                    dto.setRmEmpNumber(empnumber);
                                }
                            }
                            if (String.valueOf(currentCell).equals("12")) {
                                String srcofhire = service.getSourceofHire(cellData);
                                dto.setSourceOfHire(srcofhire);

                            }
                            if (String.valueOf(currentCell).equals("13")) {
                                dto.setStructure(cellData);
                                String structId = service.getStructureId(cellData);
                                dto.setStructureId(structId);
                                qdto.setPid(structId);

                            }
                            if (String.valueOf(currentCell).equals("14")) {
                                dto.setPractGroup(cellData);
                                qdto.setPname(cellData);
                                String practId = service.getpracticegrpId(qdto);
                                dto.setPractGroupId(practId);
                                qdto.setPid(practId);
                            }
                            if (String.valueOf(currentCell).equals("15")) {
                                dto.setSubPraGroup(cellData);
                                qdto.setPname(cellData);
                                String practId = service.getpracticegrpId(qdto);
                                dto.setSubPraGroupId(practId);
                            }
                            if (String.valueOf(currentCell).equals("16")) {
                                dto.setBand(service.getBandid(cellData));
                            }
                            if (String.valueOf(currentCell).equals("17")) {
                                dto.setSubBand(service.getBandid(cellData.toString()));
                            }
                            if (String.valueOf(currentCell).equals("18")) {
                                String passport = null;
                                if (cell.getCellType() == 1) {
                                    passport = String.valueOf(cellData.toString());
                                    dto.setPassport(passport);

                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    passport = String.valueOf(splitcell);
                                    dto.setPassport(passport);
                                }
                            }

                        } //cell iteration

                        try {

                            String stat = service.checkRRFmaster(mergedRrf);
                            if (stat == null || stat.equals("")) {
                                dto.setBillable(CommonConfig.BIll);
                                dto.setBuhId(CommonConfig.BUHID);
                                dto.setRrfMasterStatus(CommonConfig.RRFMSTATUS);
                                service.addMasterRrf(dto);
                            }
                            String splitstat = service.checksplitRrf(dto);
                            if (splitstat == null || splitstat.equals("")) {
                                dto.setRecruitmentType(CommonConfig.RCRTYPE);
                                dto.setRrfSplitStatus(CommonConfig.RRFSTATUS);
                                dto.setRrfEMP(CommonConfig.RRFEMPID);
                                service.addSplitmaster(dto);
                            }
                            if (employment) {
                                dto.setStatus("O");
                            } else {
                                dto.setStatus("C");
                            }
                            service.uploadOjfDetailsByExcel(dto);
                        } catch (Exception e) {
                            e.printStackTrace();
                            mvc.addObject("returnMsg", "Upload failed ");
                            return mvc;
                        }
                    }

                }//while ending inserting data 
                uplodedFilename = CommonConfig.commonCodeForFileUpload(dir, file);
                Date d = new Date();
                SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
                String date = dateFormater.format(d);
                auditdto.setModified_date(date);
                auditdto.setFile_name(uplodedFilename);
                auditdto.setModule_name("OJF DETAILS");
                service.saveAuditHistory(auditdto);

            }
        } catch (Exception e) {
            returnMsg = e.getMessage();
            e.printStackTrace();
            mvc.addObject("returnMsg", "Upload failed ");
            return mvc;
        }
        mvc.addObject("successMsg", "successfully uploaded applicant details");
        return mvc;
    }

    public ModelAndView updatePracticeSubPractice(HttpServletRequest request, HttpServletResponse response) {

        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        ModelAndView mvc = new ModelAndView("/bulkUpload");
        AuditListDto auditdto = new AuditListDto();
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map files = multiRequest.getFileMap();
            String uplodedFilename = null;
            HttpSession session = request.getSession();
            String authEmpid = (String) session.getAttribute("employee_no");
            auditdto.setEmployee_number(authEmpid);
            ArrayList bandList = service.getBands();
            ArrayList desiList = service.getDesignations();
            ArrayList empList = service.getEmployeNumbers();
//            System.out.println(" employ list " + empList);
            EmployeHistoryDto authDetails = service.getEmployeId(authEmpid);
            auditdto.setEmployee_number(authDetails.getId());
            File dir = new File(CommonConfig.FILE_UPLOAD_PATH);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            for (MultipartFile file : (Collection<MultipartFile>) files.values()) {
                POIFSFileSystem fileSystem = new POIFSFileSystem(file.getInputStream());
                HSSFWorkbook workBook = new HSSFWorkbook(fileSystem);
                HSSFSheet sheet = workBook.getSheetAt(0);
                Iterator rows = sheet.rowIterator();
                String[] mandtCells = {"0", "1", "2", "3", "4"};
                int rowInSheet = 0;
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();
                    // header validation
                    if (row.getRowNum() == 0) {
                        Iterator cells = row.cellIterator();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            if (currentCell == 0) {
                                if (!cellData.equals(CommonConfig.EMP_ID)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 1) {
                                if (!cellData.equals(CommonConfig.EMP_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 2) {
                                if (!cellData.equals(CommonConfig.PRACTICE_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 3) {
                                if (!cellData.equals(CommonConfig.SUB_PRACTICE_NAME)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 4) {
                                if (!cellData.equals(CommonConfig.EFFECTIVE_DATE)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + currentCell + " can not change Header Value ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                        }
                    }//header validation
                    // data validation
                    if (row.getRowNum() > 0) {
                        rowInSheet = 1;
                        Iterator cells = row.cellIterator();
                        //logic for mandatory cells
                        for (Short i = 0; i < mandtCells.length; i++) {
                            HSSFCell mandCell = row.getCell(i);
                            if (mandCell == null) {
                                returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + (mandtCells[i]) + " is mandatory ";
                                mvc.addObject("returnMsg", returnMsg);
                                return mvc;
                            }
                        }

                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();

                            if (currentCell == 0) {
                                // String inputParam = cellData.trim();

                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
//                                    System.out.println(" employee id value : " + splitcell);
                                }
                                if (!empList.contains(empnumber)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Employee id invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 2) {
                                String inputParam = cellData;
                                if (!bandList.contains(inputParam)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Band is invalid ";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }
                            if (currentCell == 3) {
                                String inputParam = cellData;
                                inputParam = inputParam.toUpperCase();
//                                System.out.println("input "+inputParam);
                                for (int i = 0; i < desiList.size(); i++) {
                                    String s = (String) desiList.get(i);
                                    String uppercase = s.toUpperCase();
//                                    System.out.println("des "+uppercase);
                                    desiList.remove(i);
                                    desiList.add(i, uppercase);
                                }
                                if (!desiList.contains(inputParam)) {
                                    returnMsg = " Row No " + (row.getRowNum() + 1) + " Cell No " + cell.getCellNum() + " Designation is invalid(Designation in Upper Case)";
                                    mvc.addObject("returnMsg", returnMsg);
                                    return mvc;
                                }
                            }

                        }//cell iteration

                    }//data validation
                }//row iteration
                if (rowInSheet == 0) {
                    returnMsg = " You are trying to upload empty sheet ";
                    mvc.addObject("returnMsg", returnMsg);
                    return mvc;
                }

                // Inserting Data to database
                Iterator rows1 = sheet.rowIterator();
                while (rows1.hasNext()) {
                    HSSFRow row = (HSSFRow) rows1.next();
                    EmployeHistoryDto dto = new EmployeHistoryDto();
                    EmployeHistoryDto singleEmployData = null;
                    String bandId = null;
                    String employeeId = null;
                    String desId = null;
                    int updateCount = 0;
                    long unixTime = System.currentTimeMillis() / 1000;
                    dto.setModified_time(unixTime + "");
                    Iterator cells = row.cellIterator();
                    if (row.getRowNum() > 0) {
                        dto.setReason("Bulk Upload");
                        dto.setAuthEmpId(authDetails.getId());
                        //dto.setFirst_name(authDetails.getFirst_name());
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            String cellData = cell.toString();
                            short currentCell = cell.getCellNum();
                            if (String.valueOf(currentCell).equals("0")) {
                                //  String employenumber = cellData.trim();
                                String empnumber = null;
                                if (cell.getCellType() == 1) {
                                    empnumber = String.valueOf(cellData.toString());
                                    singleEmployData = service.getSingleEmployee(empnumber);
                                    employeeId = service.getEmpid(empnumber);
                                    dto.setEmployee_number(employeeId);
                                } else {
                                    double d = Double.parseDouble(cellData);
                                    int splitcell = (int) d / 1;
                                    empnumber = String.valueOf(splitcell);
//                                    System.out.println(" employee id value : " + splitcell);
                                    singleEmployData = service.getSingleEmployee(empnumber);
                                    employeeId = service.getEmpid(empnumber);
                                    dto.setEmployee_number(employeeId);
                                }
                            }
                            if (String.valueOf(currentCell).equals("1")) {
                                dto.setEmployee_name(cellData);
                            }
                            if (String.valueOf(currentCell).equals("2")) {
                                bandId = service.getBandid(cellData);
                                dto.setBand(cellData);
                                dto.setBand_id(bandId);

                            }
                            if (String.valueOf(currentCell).equals("3")) {
                                desId = service.getDesignationid(cellData);
                                dto.setDesignation(cellData);
                                dto.setDesignation_id(desId);

                            }
                        } //cell iteration  
                        try {
//                            System.out.println(" this is before update in employees ");
                            updateCount = service.updateBandDesignation(dto);
//                            System.out.println(" update count : " + updateCount);
                        } catch (Exception e) {
                            e.printStackTrace();
                            mvc.addObject("returnMsg", "Upload failed while updating employee ");
                            return mvc;
                        }
                        if (updateCount > 0) {

                            try {
                                String oldband = singleEmployData.getBand_id();
                                String olddesigantion = singleEmployData.getDesignation_id();
                                if (Integer.parseInt(bandId) != (Integer.parseInt(oldband))) {
                                    dto.setChanged_from(oldband);
                                    dto.setChanged_to(bandId);
                                    dto.setField_changed("Band");
//                                    System.out.println(" this is before insert in employees info history1 for band ");
                                    service.insertEmpHistory(dto);
                                }
                                if (Integer.parseInt(desId) != (Integer.parseInt(olddesigantion))) {
                                    dto.setChanged_from(olddesigantion);
                                    dto.setChanged_to(desId);
                                    dto.setField_changed("Designation");
                                    service.insertEmpHistory(dto);
//                                    System.out.println(" this is before insert in employees info history2 for designation ");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                mvc.addObject("returnMsg", "Upload failed due to insertion ");
                                return mvc;
                            }
                        }//if
                    }


                }//while ending inserting data
                uplodedFilename = CommonConfig.commonCodeForFileUpload(dir, file);
                auditdto.setFile_name(uplodedFilename);
                Date d = new Date();
                SimpleDateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
                String date = dateFormater.format(d);
                auditdto.setModified_date(date);
                auditdto.setModule_name("BAND & DESIGNATION");
                service.saveAuditHistory(auditdto);
            }
        } catch (Exception e) {
            returnMsg = e.getMessage();
            e.printStackTrace();
            mvc.addObject("returnMsg", "Upload failed ");
            return mvc;
        }
        mvc.addObject("successMsg", "successfully uploaded");
        return mvc;
    }

    public ModelAndView salesRevenueView(HttpServletRequest request, HttpServletResponse response, AuditListDto filterData)throws Exception  {
        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        ModelAndView mvc = new ModelAndView("/salesView");
        HttpSession session = request.getSession();
        String authEmpid = (String) session.getAttribute("EMP_ID");
        filterData.setBdm_id(authEmpid);
        Date date= new Date();
        DateFormat year = new SimpleDateFormat("yyyy");
        String yr=year.format(date);
        int yn=Integer.valueOf(yr);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont=mon.format(date);
        int mn=Integer.valueOf(mont);
        String financial_year = filterData.getFinancial_year();
        if(financial_year!=null){
            
        }else{
            if(mn<=3){
                financial_year = Integer.toString(yn-1)+"-"+Integer.toString(yn);
            }else{
                financial_year = Integer.toString(yn)+"-"+Integer.toString(yn+1);
            }
        }
        filterData.setFinancial_year(financial_year);
        System.out.println("financial_year "+financial_year);
        List financialYear = service.getFinancilaYear();
        List<AuditListDto> details = service.getSalesDetails(filterData);
        mvc.addObject("yearsList", financialYear);
        mvc.addObject("details", details);
        mvc.addObject("filterData", filterData);
        mvc.addObject("rsh", "0");
        return mvc;
    }
    
    public ModelAndView salesRevenueRshView(HttpServletRequest request, HttpServletResponse response, AuditListDto filterData)throws Exception  {
        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        ModelAndView mvc = new ModelAndView("/salesView");
        HttpSession session = request.getSession();
        String authEmpid = (String) session.getAttribute("EMP_ID");
        filterData.setBdm_id(authEmpid);
        Date date= new Date();
        DateFormat year = new SimpleDateFormat("yyyy");
        String yr=year.format(date);
        int yn=Integer.valueOf(yr);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont=mon.format(date);
        int mn=Integer.valueOf(mont);
        String financial_year = filterData.getFinancial_year();
        String bdm_id = service.getBdm_list(authEmpid);
        filterData.setBdm_id(bdm_id);
        List<AuditListDto> bdm_list = service.getBdm_list_Rsh(bdm_id);
        String selected_bdm = request.getParameter("selected_bdm_id");
        if(selected_bdm!=null){
            filterData.setBdm_id(selected_bdm);
        }else{
            filterData.setBdm_id(bdm_id);
        }
        if(financial_year!=null){
            
        }else{
            if(mn<=3){
                financial_year = Integer.toString(yn-1)+"-"+Integer.toString(yn);
            }else{
                financial_year = Integer.toString(yn)+"-"+Integer.toString(yn+1);
            }
        }
        filterData.setFinancial_year(financial_year);
        System.out.println("financial_year "+financial_year);
        List financialYear = service.getFinancilaYear();
        List<AuditListDto> details = service.getSalesDetails(filterData);
        mvc.addObject("yearsList", financialYear);
        mvc.addObject("details", details);
        mvc.addObject("filterData", filterData);
        mvc.addObject("bdm_list", bdm_list);
        mvc.addObject("rsh", "1");
        return mvc;
    }
    
    public ModelAndView salesRevenueUnitView(HttpServletRequest request, HttpServletResponse response, AuditListDto filterData)throws Exception  {
        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        ModelAndView mvc = new ModelAndView("/salesView");
        HttpSession session = request.getSession();
        String authEmpid = (String) session.getAttribute("EMP_ID");
        filterData.setBdm_id(authEmpid);
        Date date= new Date();
        DateFormat year = new SimpleDateFormat("yyyy");
        String yr=year.format(date);
        int yn=Integer.valueOf(yr);
        DateFormat mon = new SimpleDateFormat("MM");
        String mont=mon.format(date);
        int mn=Integer.valueOf(mont);
        String financial_year = filterData.getFinancial_year();
        String unit_list = service.getUnit_list(authEmpid);
        filterData.setUnit_id(unit_list);
        List<AuditListDto> bdm_list = service.getBdm_list_Rsh("");
        String selected_bdm = request.getParameter("selected_bdm_id");
        System.out.println("sekecte bdm "+selected_bdm);
        if(selected_bdm!=null && selected_bdm!=""){
            filterData.setBdm_id(selected_bdm);
        }else{
            String bdm_id = service.getBdm_list("");
            filterData.setBdm_id(bdm_id);
        }
        if(financial_year!=null){
            
        }else{
            if(mn<=3){
                financial_year = Integer.toString(yn-1)+"-"+Integer.toString(yn);
            }else{
                financial_year = Integer.toString(yn)+"-"+Integer.toString(yn+1);
            }
        }
        filterData.setFinancial_year(financial_year);
        System.out.println("financial_year "+financial_year);
        List financialYear = service.getFinancilaYear();
        List<AuditListDto> details = service.getSalesUnitDetails(filterData);
        mvc.addObject("yearsList", financialYear);
        mvc.addObject("details", details);
        mvc.addObject("filterData", filterData);
        mvc.addObject("bdm_list", bdm_list);
        mvc.addObject("rsh", "2");
        return mvc;
    }
    
    public ModelAndView exportDetails(HttpServletRequest request, HttpServletResponse response, AuditListDto filterData)throws Exception  {
        final WebApplicationContext ctx = getWebApplicationContext();
        BulkUploadService service = (BulkUploadService) ctx.getBean("BulkUploadService");
        String rsh = request.getParameter("rsh");
        String financial_year = request.getParameter("year");
        String bdm_id = request.getParameter("bdm_id");
        System.out.println("rsh "+rsh+" year "+financial_year+" bdm "+bdm_id);
        
        return null;
    }
}
