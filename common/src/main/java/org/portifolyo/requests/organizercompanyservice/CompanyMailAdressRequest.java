package org.portifolyo.requests.organizercompanyservice;

public record CompanyMailAdressRequest(
        String companyId,
        String host,
        String email,
        String password,
        int port,
        boolean isTtsRequired
) {
}
