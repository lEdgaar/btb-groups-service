package com.btb.groupsservice.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GroupMembership extends BaseModel<Long> {

    private static final long serialVersionUID = 1L;

    private long id;

    private Group group;

    private Long userId;

    private MembershipType membershipType;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
