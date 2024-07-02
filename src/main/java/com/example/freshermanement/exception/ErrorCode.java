package com.example.freshermanement.exception;

public enum ErrorCode {

    // Example error codes
    USER_NOT_FOUND(404, "User not found"),
    UNAUTHORIZED_ACCESS(401, "Unauthorized access"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    UNCATEGORIZED_EXCEPTION(1000, "Uncategorized exception"),
    INVALID_KEY(1001, "Invalid request parameter or path variable"),
    USER_NOT_EXISTED(1002, "User does not exist"),
    UNAUTHENTICATED(1006, "Unauthenticated");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}