package com.btb.groupsservice.md;

public enum GroupMembershipType {
    OWNERSHIP(1L, "Ownership", "O"),
    ADMIN(2L, "Admin", "A"),
    MEMBER(3L, "Member", "M");

    private Long id;

    private String name;

    private String internalCode;

    GroupMembershipType(Long id, String name, String internalCode) {
        this.id = id;
        this.name = name;
        this.internalCode = internalCode;
    }

}
