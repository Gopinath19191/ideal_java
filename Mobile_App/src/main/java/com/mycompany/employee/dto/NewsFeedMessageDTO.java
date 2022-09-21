package com.mycompany.employee.dto;

import java.io.Serializable;

public class NewsFeedMessageDTO implements Serializable {
	private int newsFeedId;
	private boolean isFeedReed = false;
	private String feedDescription;
	private String feedType;
	private String feedTitle;
	private boolean isFeedDeleted = false;
	private String startDate;
	private String endDate;
	private String feedCreatedDate;
	private String feedUpdatedDate;
	private String pictureSquare;
	private String pictureThumbnail;
	private String pictureFullImage;
	private int interActionCount;
	private int likeCount;
	private int interestCount;
	private String interactionType;
	private String publishedDate;
	private String expireDate;
	private int sectionId;
	private String sectionName;
	private String venueDetails;
	private String startTime;
	private String endTime;
}