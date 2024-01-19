package com.btb.groupsservice.service;

import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.entity.MembershipType;

public interface GroupMembershipService {

    boolean checkMembership(Group group, Long userId, String role);

    boolean isMember(Long groupId, Long userId);

    void addGroupMembership(Group group, Long userId, MembershipType membershipType);
}
