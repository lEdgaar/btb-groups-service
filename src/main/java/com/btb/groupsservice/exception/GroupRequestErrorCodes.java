package com.btb.groupsservice.exception;

public enum GroupRequestErrorCodes implements ErrorCode {
    GROUP_REQUEST_ID_NOT_NULL(1, "group.request.id.not.null"),
    GROUP_REQUEST_NOT_FOUND(2, "group.request.not.found"),
    ;

    private final int code;

    private final String key;

    GroupRequestErrorCodes(int code, String key) {
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
