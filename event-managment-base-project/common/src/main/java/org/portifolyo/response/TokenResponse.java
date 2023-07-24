package org.portifolyo.response;
public class TokenResponse {

    private String token;

    public TokenResponse(){}
    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken(){
        return "Bearer "+ this.token;
    }
}
