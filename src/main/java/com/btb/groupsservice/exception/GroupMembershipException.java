package com.btb.groupsservice.exception;

public class GroupMembershipException extends CommonApiException {

    public GroupMembershipException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}
