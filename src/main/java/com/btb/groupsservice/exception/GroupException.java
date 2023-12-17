package com.btb.groupsservice.exception;

public class GroupException extends CommonApiException {

    public GroupException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}
