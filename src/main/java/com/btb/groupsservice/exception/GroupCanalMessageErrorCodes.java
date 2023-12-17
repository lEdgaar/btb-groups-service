package com.btb.groupsservice.exception;

public enum GroupCanalMessageErrorCodes implements ErrorCode {

    MESSAGE_ID_NOT_NULL(1, "message.id.not.null"),
    MESSAGE_NOT_FOUND(2, "message.not.found");

    private final String code;
    private final String message;

     GroupCanalMessageErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getKey() {
        return null;
    }
}
