/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.shared;

import com.defiance.ideal.ojf.joiningForm.dao.JoinerDAO;
import com.defiance.ideal.ojf.joiningForm.dto.JoinerFormDTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class CommonFunctions {
//    private static String s;

    public static final String changeDateFormatToDB(String providedDate) throws ParseException {
        if (!("").equals(providedDate) && providedDate != null) {
            String[] proDateArray = providedDate.split("-");
            String formattedDate = null;
            if ((proDateArray[2] == null ? "0000" != null : !proDateArray[2].equals("0000")) && (proDateArray[1] == null ? "00" != null : !proDateArray[1].equals("00")) && (proDateArray[0] == null ? "00" != null : !proDateArray[0].equals("00"))) {
                formattedDate = proDateArray[2] + "-" + proDateArray[1] + "-" + proDateArray[0];
            }
            return formattedDate;
        } else {
            return providedDate = null;
        }
    }

    public static final LinkedHashMap statusList() {
        LinkedHashMap mapObj = new LinkedHashMap();
        mapObj.put("0", "Trigger Mail To Candidate");
        mapObj.put("1", "Joining Formalities Initiated");
        mapObj.put("3", "Joining Formalities Completed check details");
        mapObj.put("4", "Send Back to employee");
        mapObj.put("5", "Data Verified");
        mapObj.put("6", "JF Data Added to iDeal");
        return mapObj;
    }

    public static final LinkedHashMap bloodGroupList() {
        LinkedHashMap mapObj = new LinkedHashMap();
        mapObj.put("AB+", "AB+");
        mapObj.put("AB-", "AB-");
        mapObj.put("A+", "A+");
        mapObj.put("A-", "A-");
        mapObj.put("B+", "B+");
        mapObj.put("B-", "B-");
        mapObj.put("O+", "O+");
        mapObj.put("O-", "O-");
        mapObj.put("A1+", "A1+");
        return mapObj;
    }

    public static void TrimSpace(Object pageClass) {
        try {
            Class c = Class.forName(pageClass.getClass().getCanonicalName());
            Method[] method = c.getDeclaredMethods();
            for (int i = 0; i < method.length; i++) {
                if (method[i].getName().startsWith("g")) {
                    Object obj = method[i].invoke(pageClass);
                    String methodName = method[i].getName();
                    methodName = methodName.substring(3, methodName.length());
                    if (obj != null) {
                        if (!(method[i].getReturnType().toString().equals("int"))) {
                            String st = obj.toString().trim();
                            Class[] types = new Class[]{String.class};
                            Method setterMethod = c.getMethod("set" + methodName, types);
                            setterMethod.invoke(pageClass, st);
                        }

                    }
                }
            }
        } catch (Exception ex) {
            //   Logger.getLogger(TrimSpaces.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printRequest(HttpServletRequest requestObj) {
        try {
            Enumeration myReq = requestObj.getParameterNames();
            List<String> requestParameterNames = Collections.list((Enumeration<String>) requestObj.getParameterNames());
            for (String parameterName : requestParameterNames) {
                System.out.println("parameterName = " + parameterName + "\n");
                //System.out.println("requestParameterNames = " + requestParameterNames+"\n");
            }
        } catch (Exception ex) {
            System.out.println("print Request exception = " + ex);
        }
    }

    public static LinkedHashMap getEmployeeType() {
        LinkedHashMap mapObj = new LinkedHashMap();
        try {
            //mapObj.put("d", "Direct Contract");
            //mapObj.put("v", "Vendor Contract");
            mapObj.put("p", "Permanent");
            //mapObj.put("g", "Permanent GmbH");
            //mapObj.put("c", "Contract GmbH");
            //mapObj.put("a", "AL Employees");
        } catch (Exception ex) {
            System.out.println("print Request exception = " + ex);
        }
        return mapObj;
    }

    public void fileUpload(MultipartFile fileprop, String jfId, String referenceName, String moduleName, JoinerDAO jfi, String file_id) throws IOException {
        try {
            System.out.println("" + fileprop);
            //          System.out.println(""+fileprop.getOriginalFilename());
            if (fileprop != null || !fileprop.getOriginalFilename().equals(" ")) {
                String contentType = fileprop.getContentType();
                String fileName = jfId + "~~" + referenceName + "~~" + fileprop.getOriginalFilename();
                String fileNameLive = jfId + "~~" + referenceName + "~~" + fileprop.getOriginalFilename();
//              byte[] fileData = fileprop.getFileData();
                String filePath = CommonConfigurations.fileUploadPath;
                String filePathLive = CommonConfigurations.fileUploadPathLive;
                if (!fileName.equals("")) {
                    File fileToCreate = new File(filePath, fileName);
                    if (!fileToCreate.exists()) {
                        FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                        byte[] fileData = fileprop.getBytes();
                        fileOutStream.write(fileData);
                        fileOutStream.flush();
                        fileOutStream.close();
                    }
                }
                if (referenceName.equals("joinerPhotoProof")) {
                    if (!fileNameLive.equals("")) {
                        File fileToCreate = new File(filePathLive, fileNameLive);
                        if (!fileToCreate.exists()) {
                            FileOutputStream fileOutStream = new FileOutputStream(fileToCreate);
                            byte[] fileData = fileprop.getBytes();
                            fileOutStream.write(fileData);
                            fileOutStream.flush();
                            fileOutStream.close();
                        }
                    }
                }
                if ("bankProof".equals(referenceName) || "joinerSignatureProof".equals(referenceName) || "joinerPhotoProof".equals(referenceName)) {
                    if (fileprop != null || !fileprop.getOriginalFilename().equals("")) {
                        Integer id = jfi.getBankProofFileId(jfId, referenceName);
                        if (id == null || id == 0) {
                            jfi.addFileDb(file_id, fileName, contentType, referenceName, jfId, moduleName);
                        } else {
                            jfi.updateFileDb(fileName, contentType, id);
                        }
                    }
                } else {
                    jfi.addFileDb(file_id, fileName, contentType, referenceName, jfId, moduleName);
                }
            }
        } catch (Exception ex) {
            System.out.println("print Request exception = " + ex);
            ex.printStackTrace();
        }
    }
//    SimpleDateFormat normal= null;
//          Date dtcreditExpiryDate = new Date();
//          String strcreditExpiryDate;
//            dfcreditExpiryDate = new SimpleDateFormat("dd-MM-yyyy");
//            strcreditExpiryDate = accountForm.getCreditExpiryDate() ;
//            try {
//                dtcreditExpiryDate = dfcreditExpiryDate.parse(strcreditExpiryDate);
//              } catch (ParseException pe) {
//                log.error("ParseException: " + pe);
//             }
//            account.setCreditExpiryDate(dtcreditExpiryDate);
//       mgr.saveObject(account);
}
