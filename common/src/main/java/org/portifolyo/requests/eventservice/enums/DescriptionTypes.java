package org.portifolyo.requests.eventservice.enums;

public enum     DescriptionTypes {

        LINK("Link"),
        IMAGE("Image");

    DescriptionTypes(String image) {
    this.image = image;
    }
    private final String image;

    public String getText() {
        return this.image;
    }
}
