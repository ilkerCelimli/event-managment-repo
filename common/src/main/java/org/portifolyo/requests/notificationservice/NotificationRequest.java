package org.portifolyo.requests.notificationservice;

import java.io.Serializable;
import java.util.Map;

public class NotificationRequest implements Serializable {

    private NotificationType type;

    private Map<String,String> info;


    public NotificationRequest(NotificationType type, Map<String, String> info) {
        this.type = type;
        this.info = info;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
