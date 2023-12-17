package com.btb.groupsservice.md;

public enum GroupPrivacyLevelMD {
    PUBLIC(1L, "Public", "Anyone can see the group, its members and their posts."),
    PRIVATE(2L, "Private", "All group information is only visible to members."),
    ;

    private Long id;

    private String name;

    private String description;

    GroupPrivacyLevelMD(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
