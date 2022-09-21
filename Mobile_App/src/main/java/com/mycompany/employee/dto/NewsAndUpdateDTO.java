package com.mycompany.employee.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class NewsAndUpdateDTO implements Serializable {
    @Expose
    private int id;
    @Expose
    private String employeeId;
    @Expose
    private int empId;
    @Expose
    private String tokenNo;
    @Expose
    private int feedId;
    @Expose
    private int sectionId;
    @Expose
    private long firstNode;
    @Expose
    private long lastNode;
    @Expose
    private int publisherId;
    @Expose
    private String interactionType;
    @Expose
    private String pictureSquareLocation;
    @Expose
    private String pictureThumbnailLocation;
    @Expose
    private String pictureLocation;

    public NewsAndUpdateDTO() {
    }

    public NewsAndUpdateDTO(String employeeId, String tokenNo) {
        this.employeeId = employeeId;
        this.tokenNo = tokenNo;
    }

    public NewsAndUpdateDTO(String employeeId, String tokenNo, int categoryId, long firstNode, long lastNode) {
        this.employeeId = employeeId;
        this.tokenNo = tokenNo;
        this.sectionId = categoryId;
        this.firstNode = firstNode;
        this.lastNode = lastNode;
    }

    public NewsAndUpdateDTO(String employeeId, int empId, String tokenNo, int feedId, int sectionId, int publisherId) {
        this.employeeId = employeeId;
        this.empId = empId;
        this.tokenNo = tokenNo;
        this.feedId = feedId;
        this.sectionId = sectionId;
        this.publisherId = publisherId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public long getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(long firstNode) {
        this.firstNode = firstNode;
    }

    public long getLastNode() {
        return lastNode;
    }

    public void setLastNode(long lastNode) {
        this.lastNode = lastNode;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureSquareLocation() {
        return pictureSquareLocation;
    }

    public void setPictureSquareLocation(String pictureSquareLocation) {
        this.pictureSquareLocation = pictureSquareLocation;
    }

    public String getPictureThumbnailLocation() {
        return pictureThumbnailLocation;
    }

    public void setPictureThumbnailLocation(String pictureThumbnailLocation) {
        this.pictureThumbnailLocation = pictureThumbnailLocation;
    }

    public String getPictureLocation() {
        return pictureLocation;
    }

    public void setPictureLocation(String pictureLocation) {
        this.pictureLocation = pictureLocation;
    }
}
