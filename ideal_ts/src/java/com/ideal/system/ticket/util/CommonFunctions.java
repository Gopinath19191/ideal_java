/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.system.ticket.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class CommonFunctions {

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

    public void fileUpload(MultipartFile fileprop, String file_id) throws IOException {
        try {
            String fileName = fileprop.getOriginalFilename();
            String filePath = CommonConfigurations.fileUploadPath;
            if (fileprop != null || !fileprop.getOriginalFilename().equals(" ")) {
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
            }
        } catch (Exception ex) {
          
            ex.printStackTrace();
        }
    }

    
}
