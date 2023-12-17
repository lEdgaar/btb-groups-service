package com.btb.groupsservice.service;

public interface GroupMembershipService {

    boolean checkMembership(Long groupId, Long userId, String role);

    boolean isMember(Long groupId, Long userId);
}
