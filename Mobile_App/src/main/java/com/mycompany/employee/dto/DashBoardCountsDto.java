package com.mycompany.employee.dto;

import com.google.gson.annotations.SerializedName;

public class DashBoardCountsDto {
	
	@SerializedName("sendBackEntryCount")
	private int sendBackEntryCount;
	@SerializedName("newsUpdateCount")
	private int newsUpdateCount;
}
