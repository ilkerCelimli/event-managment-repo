package org.portifolyo.requests.eventservice.enums;

public enum EventType {

    CONCERT("Concert"),
    FAIR("Fair"),
    SYMPOSIUM("Symposium"),
    SPEECH("Speech"),
    MEETING("Meeting");

    String text;
    EventType(String text) {
        this.text = text;
    }

    public String getName() {
        return this.text;
    }

}
