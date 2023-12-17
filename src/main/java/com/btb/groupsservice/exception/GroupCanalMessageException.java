package com.btb.groupsservice.exception;

public class GroupCanalMessageException extends CommonApiException {

    public GroupCanalMessageException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}
