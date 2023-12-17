package com.btb.groupsservice.exception;

public class GroupRequestException extends CommonApiException {

    public GroupRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}
