package com.mycompany.employee.entitiy;

import com.google.gson.annotations.SerializedName;
import org.apache.abdera.model.DateTime;

import javax.persistence.*;

public class NewsFeeds {

    private long id;
    @SerializedName("news_category_id")
    private long newsCategoryId;
    private String description;
    @SerializedName("can_like")
    private String canLike;
    @SerializedName("feed_type")
    private String feedType;
    @SerializedName("location")
    private String location;
    @SerializedName("start_datetime")
    private DateTime startDateTime;
    @SerializedName("end_datetime")
    private DateTime endDateTime;
    private boolean deleted;
    private String status;
    @SerializedName("date_created")
    private DateTime dateCreated;
    @SerializedName("date_updated")
    private DateTime dateUpdated;

}
