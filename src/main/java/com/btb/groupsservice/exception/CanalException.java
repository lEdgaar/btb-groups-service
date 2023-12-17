package com.btb.groupsservice.exception;

public class CanalException extends CommonApiException {

    public CanalException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}
