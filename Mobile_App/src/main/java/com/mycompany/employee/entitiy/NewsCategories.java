package com.mycompany.employee.entitiy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsCategories implements Serializable{
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deleted")
    @Expose
    private boolean isDeleted;
    @Expose
    private String dateCreated;
    @Expose
    private String dateUpdated;
    @Expose
    @SerializedName("messageCount")
    private long messageCount;
    @Expose
    @SerializedName("feedCount")
    private int feedCount;

}
