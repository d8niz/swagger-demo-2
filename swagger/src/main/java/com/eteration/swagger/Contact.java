package com.eteration.swagger;

import io.swagger.annotations.ApiModelProperty;

public class Contact {

    @ApiModelProperty(notes = "Id of the contact")
    private String id;
    @ApiModelProperty(notes = "Name of the contact")
    private String name;
    @ApiModelProperty(notes = "Phone number of the contact")
    private String phone;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    
}