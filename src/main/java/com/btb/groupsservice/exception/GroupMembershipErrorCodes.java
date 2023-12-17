package com.btb.groupsservice.exception;

public enum GroupMembershipErrorCodes implements ErrorCode {
    USER_NOT_HAVE_PERMISSION(1, "user.not.have.permission"),
    USER_NOT_MEMBER_OF_GROUP(2, "user.not.member.of.group");

    private final int code;

    private final String key;

    GroupMembershipErrorCodes(int code, String key) {
        this.code = code;
        this.key = key;
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
