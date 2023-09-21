package org.portifolyo.response;

public record CompanyResponse(
        String id,
        String name,
        String taxNumber,
        String phoneNumber,
        String companySuperAdminUserId,
        double commusionRate

) {
}
