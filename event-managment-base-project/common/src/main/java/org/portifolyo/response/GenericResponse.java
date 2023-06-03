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

    public static <T> GenericResponse<T> SUCCESS(T data){
        return new GenericResponse<>(200,"sucess",data);
    }
    public static <T> GenericResponse<T> SUCCESS(){
        return new GenericResponse<>(200,"success");
    }

    public static GenericResponse<Void> BAD_REQUEST(String message){
        return new GenericResponse<>(400,message);
    }


}
