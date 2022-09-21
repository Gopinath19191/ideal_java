package com.mycompany.employee.entitiy;


public class ConfigurationValuesEntity {

    private long id;
    private long parentId;
    private String configurationKey;
    private String configurationValue;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getConfigurationKey() {
		return configurationKey;
	}
	public void setConfigurationKey(String configurationKey) {
		this.configurationKey = configurationKey;
	}
	public String getConfigurationValue() {
		return configurationValue;
	}
	public void setConfigurationValue(String configurationValue) {
		this.configurationValue = configurationValue;
	}
    
    
}
