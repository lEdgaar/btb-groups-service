package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.persistence.mapper.GroupMembershipMapper;
import com.btb.groupsservice.service.GroupMembershipService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class GroupMembershipServiceImpl implements GroupMembershipService {

    @Autowired
    private GroupMembershipMapper groupMembershipMapper;

    @Override
    public boolean checkMembership(Long groupId, Long userId, String role) {
        log.trace("Checking membership for groupId: {}, userId: {}, role: {}", groupId, userId, role);

        if (groupMembershipMapper.checkMembership(groupId, userId, role) ) {
            log.trace("Membership for groupId: {}, userId: {}, role: {} exists", groupId, userId, role);
            return true;
        }

        log.trace("Membership for groupId: {}, userId: {}, role: {} does not exist", groupId, userId, role);
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
}
