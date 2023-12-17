package com.btb.groupsservice.exception;

public enum GroupErrorCodes implements ErrorCode {
    GROUP_ID_NOT_NULL(1, "group.id.not.null"),
    GROUP_NOT_FOUND(2, "group.not.found"),
    GROUP_NAME_ALREADY_EXISTS(3, "group.name.already.exists");

    private final int code;

    private final String key;

    GroupErrorCodes(int code, String key) {
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
