package com.mycompany.employee.entitiy;

import com.google.gson.annotations.SerializedName;

public class FeedSchedules {

    private int id;
    @SerializedName("news_feed_id")
    private int newsFeedId;
    @SerializedName("date_pubish")
    private long datePublish;
    @SerializedName("date_expired")
    private long dateExpired;
}
