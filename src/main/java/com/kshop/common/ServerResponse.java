package com.kshop.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * [Des]      :     TODO
 * [Author]   :     KingJA
 * [Date]     :     2017/6/25
 * [email]    :     kingjavip@gmail.com
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> {
    private int resutlCode;
    private String message;
    private T data;

    private ServerResponse(int resutlCode) {
        this.resutlCode = resutlCode;
    }

    public ServerResponse(int resutlCode, String message) {
        this.resutlCode = resutlCode;
        this.message = message;
    }

    private ServerResponse(int resutlCode, T data) {
        this.resutlCode = resutlCode;
        this.data = data;
    }

    private ServerResponse(int resutlCode, String message, T data) {
        this.resutlCode = resutlCode;
        this.message = message;
        this.data = data;
    }

    public static <T> ServerResponse<T> createSuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getStatus());
    }

    public static <T> ServerResponse<T> createSuccessMsg(String message) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getStatus(), message);
    }

    public static <T> ServerResponse<T> createSuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getStatus(), data);
    }

    public static <T> ServerResponse<T> createSuccess(String message, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getStatus(), message, data);
    }

    public static <T> ServerResponse<T> createError() {
        return new ServerResponse<T>(ResponseCode.ERROR.getStatus());
    }

    public static <T> ServerResponse<T> createError(String error) {
        return new ServerResponse<T>(ResponseCode.ERROR.getStatus(), error);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.resutlCode == ResponseCode.SUCCESS.getStatus();
    }

    public int getResutlCode() {
        return resutlCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static void main(String[] args) {
        ServerResponse.createSuccess("ddd");
    }
}
