package org.portifolyo.requests.organizercompanyservice;

import org.portifolyo.requests.userservice.UserRegisterRequest;

public record SaveOrganizerCompanyRequest(
        String taxNumber,
        String companyName,
        String phoneNumber,
        UserRegisterRequest userRegisterRequest,
        AdressRequest adressRequest

) {
}
