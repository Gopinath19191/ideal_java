/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.master.ticket.dto;

/**
 *
 * @author 16113
 */
public class FeedbackMasterDto {
    
        private String id;
        private String parent_id;
        private String configuration_key;
        private String configuration_value;
        
    public void setId(String id) {
        this.id = id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setConfiguration_key(String configuration_key) {
        this.configuration_key = configuration_key;
    }

    public void setConfiguration_value(String configuration_value) {
        this.configuration_value = configuration_value;
    }
   
    public String getId() {
        return id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getConfiguration_key() {
        return configuration_key;
    }

    public String getConfiguration_value() {
        return configuration_value;
    }
    
}
