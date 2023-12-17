package com.btb.groupsservice.exception;

public enum CanalErrorCodes implements ErrorCode {
    CANAL_ID_NOT_NULL(1, "canal.id.not.null"),
    CANAL_NOT_FOUND(2, "canal.not.found");

    private final int code;

    private final String key;

    CanalErrorCodes(int code, String key) {
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
