package com.mycompany.employee.entitiy;

import com.google.gson.annotations.SerializedName;

public class FeedImages {
    private int id;
    @SerializedName("news_feed_id")
    private long newsFeedId;
    @SerializedName("pic_square")
    private String pictureSquare;
    @SerializedName("pic_thumbnail")
    private String pictureThumbnail;
    @SerializedName("time_created")
    private String timeCreated;
    private boolean deleted;
}
