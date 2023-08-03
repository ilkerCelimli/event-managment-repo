package org.portifolyo.requests.organizercompanyservice;

public enum InputEnum {
    SOUTH("SOUTH"),
    WEST("WEST"),
    NORTH("NORTH"),
    EAST("EAST");

    InputEnum(String value) {
        this.value = value;
    }

    String value;

    public String getValue(){
        return this.value;
    }
}
