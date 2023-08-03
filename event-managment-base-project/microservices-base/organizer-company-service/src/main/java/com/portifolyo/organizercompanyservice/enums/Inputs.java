package com.portifolyo.organizercompanyservice.enums;

public enum Inputs {

    SOUTH("SOUTH"),
    WEST("WEST"),
    NORTH("NORTH"),
    EAST("EAST");

    Inputs(String value) {
        this.value = value;
    }

    String value;

    public String getValue(){
        return this.value;
    }
}
