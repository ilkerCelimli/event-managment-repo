package org.portifolyo.response;

public record UserInfo(
        String id,
        String name,
        String surname,
        String email,
        String birtday
) {
}
