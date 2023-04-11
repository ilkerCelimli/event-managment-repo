package org.portifolyo.response;


public class GenericResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;


    public GenericResponse(Integer statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
    public GenericResponse(Integer statusCode,String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
