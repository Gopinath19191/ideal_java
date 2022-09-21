package com.mycompany.employee.dto;

import com.mycompany.employee.entitiy.ConfigurationValuesEntity;

import java.io.Serializable;
import java.util.List;

public class FeedMessageListDTO implements Serializable {
    private List<NewsFeedMessageDTO> newsFeedMessageDTOList;
    private List<ConfigurationValuesEntity> configurationValuesEntity;

    public List<NewsFeedMessageDTO> getNewsFeedMessageDTOList() {
        return newsFeedMessageDTOList;
    }

    public void setNewsFeedMessageDTOList(List<NewsFeedMessageDTO> newsFeedMessageDTOList) {
        this.newsFeedMessageDTOList = newsFeedMessageDTOList;
    }

    public List<ConfigurationValuesEntity> getConfigurationValuesEntity() {
        return configurationValuesEntity;
    }

    public void setConfigurationValuesEntity(List<ConfigurationValuesEntity> configurationValuesEntity) {
        this.configurationValuesEntity = configurationValuesEntity;
    }
}
