package org.portifolyo.requests.userservice;

public record UserInfo(
        String id,
        String name,
        String surname,
        String birtday
) {
}
