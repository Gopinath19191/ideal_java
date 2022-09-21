/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.service;

import com.defiance.ideal.dao.ECardDao;
import com.defiance.ideal.dto.ECardDto;
import com.defiance.ideal.dto.EmpEngagementDto;
import com.defiance.ideal.util.CommonFunctions;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author 16221
 */
public class ECardServiceImpl implements ECardService{
    ECardDao dao;

    public ECardDao getDao() {
        return dao;
    }

    public void setDao(ECardDao dao) {
        this.dao = dao;
    }
    
    public List<ECardDto> getCardLists(){
        List<ECardDto> EcardList = dao.getCardLists();
        return EcardList;
    }
    public List<ECardDto> getEmployeeSearch(String val){
        List<ECardDto> employeeList = dao.getEmployeeSearch( val);
        return employeeList;
    }
    
    public int giveEcard(ECardDto filterData){
        int insert_id = dao.giveEcard(filterData);
        return insert_id;
    }
    
    public String generateFile(ECardDto filterData){
        String file_name = "";
        filterData.getFile_name();
        try {
            String path = CommonFunctions.fileUploadPath;
            //System.out.println("path "+path+filterData.getFile_name());
            ECardDto to = dao.getEmployeeName(filterData.getGiven_to());
            ECardDto from = dao.getEmployeeName(filterData.getCreated_by());
            final BufferedImage image = ImageIO.read(new File(path+filterData.getFile_name()));
            Graphics g = image.getGraphics();
            g.setFont(new Font("Calibri", Font.PLAIN, 20));
            g.setColor(Color.DARK_GRAY);
            g.drawString(to.getEmployee_name(), 250, 515);
            g.drawString(from.getEmployee_name(), 250, 558);
            g.dispose();
            Date d = new Date();
            
            file_name = from.getEmployee_id()+"_"+to.getEmployee_id()+"_"+d.getTime()+".jpg";
            ImageIO.write(image, "jpg", new File(path+file_name));
        } catch (IOException ex) {
            Logger.getLogger(ECardServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file_name;
    }
    
    public List<ECardDto> getGivenCardLists(String val){
        List<ECardDto> EcardList = dao.getGivenCardLists(val);
        return EcardList;
    }
    public List<ECardDto> getReceivedCardLists(String val){
        List<ECardDto> EcardList = dao.getReceivedCardLists(val);
        return EcardList;
    }
    public ECardDto getEcardDetails(String card_id){
        ECardDto card_details = dao.getEcardDetails(card_id );
        return card_details;
    }
    public String getMailTo(String id){
        String mail_to = dao.getMailTo(id);
        return mail_to;
    }
    public String getMailCcList(String id){
        String mail_to = dao.getMailCcList(id);
        return mail_to;
    }
    public String getMailCc(String id){
        String mail_cc = dao.getMailCc(id);
        return mail_cc;
    }
    public ArrayList<ECardDto> getMailCredentials(){
        ArrayList<ECardDto> mail_credentials = dao.getMailCredentials();
        return mail_credentials;
    }
    public List<ECardDto> getEcardReport(ECardDto filterData){
        List<ECardDto> EcardList = dao.getEcardReport(filterData);
        return EcardList;
    }
    public List<ECardDto> getUnitName(){
        List<ECardDto> EcardList = dao.getUnitName();
        return EcardList;
    }
    public List<EmpEngagementDto> getAreaofInterest(){
        List<EmpEngagementDto> AreaList = dao.getAreaofInterest();
        return AreaList;
    }
    public List<EmpEngagementDto> getInterestLevel(){
        List<EmpEngagementDto> interest_level = dao.getInterestLevel();
        return interest_level;
    }
    public List<EmpEngagementDto> getCommitmentLevel(){
        List<EmpEngagementDto> commitment_level = dao.getCommitmentLevel();
        return commitment_level;
    }
    public int submitEmployeeEngagement(EmpEngagementDto filterData){
        int insert_id = dao.submitEmployeeEngagement(filterData);
        
        if(insert_id>0){
            if(filterData.getWillingness().equals("y")){
                filterData.setEe_id(Integer.toString(insert_id));
                for(int i=0; i<filterData.getArea_interest().length; i++){
                    filterData.setInterest("");
                    filterData.setInterest_level("");
                    filterData.setInterest_description("");
                    System.out.println("insert int "+insert_id);
                    filterData.setInterest(filterData.getArea_interest()[i]);
                    filterData.setInterest_level(filterData.getCurrent_level()[i]);
                    filterData.setInterest_description(filterData.getDescription()[i]);
                    dao.submitEmployeeEngagementDetails(filterData);
                }
            }else{
                System.out.println("not willing");
            }
        }
        return insert_id;
    }
    
    public EmpEngagementDto getLastSubmittedData(String employee_id){
        EmpEngagementDto data = dao.getLastSubmittedData(employee_id);
        return data;
    }
    
    public List<EmpEngagementDto> getInterestLists(String ee_id){
        List<EmpEngagementDto> interest_list = dao.getInterestLists(ee_id);
        return interest_list;
    }
}
