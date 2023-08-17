package org.portifolyo.requests.eventservice.messages;

public class EventAreaRequestMessages {

    private EventAreaRequestMessages(){}
    public static final String AREA_NAME_NOT_NULL = "Area name is not null";
    public static final String AREA_LAT_IS_NOT_NULL = "Area lat is not null";
    public static final String AREA_LAT_IS_NOT_NEGATIVE = "Area lat is not Negative";
    public static final String AREA_LAT_IS_RANGE = "Area lat is 0-180 range";
    public static final String AREA_LNG_IS_NOT_NULL = "Area lng is not null";
    public static final String AREA_LNG_IS_NOT_NEGATIVE = "Area lng is not negative";
    public static final String AREA_LNG_IS_RANGE = "Area lng is 0-180 range";
    public static final String OPEN_ADRESS_NOT_NULL = "Open adressRequest is not Null";
    public static final String OPEN_ADRESS_IS_NOT_BLANK ="Open adressRequest is not blank";
    public static final String AREA_CAPACITY_NOT_NEGATIVE = "Area capacity is not negative value";

}
