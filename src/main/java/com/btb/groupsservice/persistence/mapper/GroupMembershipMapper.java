package com.btb.groupsservice.persistence.mapper;

import com.btb.groupsservice.entity.GroupMembership;

public interface GroupMembershipMapper extends CommonMapper<GroupMembership, Long> {

    boolean checkMembership(Long groupId, Long userId, String role);

    boolean isMember(Long groupId, Long userId);

}
