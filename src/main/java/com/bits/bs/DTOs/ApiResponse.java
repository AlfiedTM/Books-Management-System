package com.bits.bs.DTOs;

public class ApiResponse <T> {
    //    Status code
    private int code;

    //    Response
    private String message;

    //    Data
    private T data;

    //    Request status
    private boolean success;

    public ApiResponse(int code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }
}
