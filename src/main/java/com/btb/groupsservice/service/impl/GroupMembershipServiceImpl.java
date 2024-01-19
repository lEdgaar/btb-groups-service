package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.entity.GroupMembership;
import com.btb.groupsservice.entity.MembershipType;
import com.btb.groupsservice.md.GroupMembershipType;
import com.btb.groupsservice.persistence.mapper.GroupMembershipMapper;
import com.btb.groupsservice.persistence.mapper.MembershipTypeMapper;
import com.btb.groupsservice.service.GroupMembershipService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class GroupMembershipServiceImpl implements GroupMembershipService {

    @Autowired
    private GroupMembershipMapper groupMembershipMapper;

    @Autowired
    private MembershipTypeMapper membershipTypeMapper;

    @Override
    public boolean checkMembership(Group group, Long userId, String role) {
        log.trace("Checking membership for groupId: {}, userId: {}, role: {}", group.getId(), userId, role);

        MembershipType membershipType = new MembershipType();
        membershipType.setId(GroupMembershipType.ADMIN.getId());

        GroupMembership groupMembership = new GroupMembership();
        groupMembership.setGroup(group);
        groupMembership.setUserId(userId);
        groupMembership.setMembershipType(membershipTypeMapper.findByFilter(membershipType).stream().findFirst().orElse(null));

        if (!groupMembershipMapper.findByFilter(groupMembership).isEmpty()) {
            log.trace("Membership for groupId: {}, userId: {}, role: {} exists", group.getId(), userId, role);
            return true;
        }

        log.trace("Membership for groupId: {}, userId: {}, role: {} does not exist", group.getId(), userId, role);
        return false;
    }

    @Override
    public boolean isMember(Long groupId, Long userId) {
        log.trace("Checking membership for groupId: {}, userId: {}", groupId, userId);

        if (groupMembershipMapper.isMember(groupId, userId) ) {
            log.trace("Membership for groupId: {}, userId: {} exists", groupId, userId);
            return true;
        }

        log.trace("Membership for groupId: {}, userId: {} does not exist", groupId, userId);
        return false;
    }

    @Override
    public void addGroupMembership(Group group, Long userId, MembershipType membershipType) {
        GroupMembership groupMembership = new GroupMembership();
        groupMembership.setGroup(group);
        groupMembership.setUserId(userId);
        groupMembership.setMembershipType(membershipType);

        groupMembershipMapper.save(groupMembership);
    }

}
