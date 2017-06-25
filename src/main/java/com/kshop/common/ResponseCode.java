package com.kshop.common;

/**
 * [Des]      :     TODO
 * [Author]   :     KingJA
 * [Date]     :     2017/6/25
 * [email]    :     kingjavip@gmail.com
 */
public enum  ResponseCode {
    SUCCESS(0,"SUCCESS"),ERROR(1,"ERROR");
    private int status;
    private String message;

    ResponseCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
