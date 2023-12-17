package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.dto.AddGroupDTO;
import com.btb.groupsservice.dto.UpdateGroupDTO;
import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.entity.MembershipType;
import com.btb.groupsservice.exception.GroupErrorCodes;
import com.btb.groupsservice.exception.GroupException;
import com.btb.groupsservice.exception.GroupMembershipErrorCodes;
import com.btb.groupsservice.exception.GroupMembershipException;
import com.btb.groupsservice.md.GroupMembershipType;
import com.btb.groupsservice.persistence.mapper.GroupMapper;
import com.btb.groupsservice.service.GroupMembershipService;
import com.btb.groupsservice.service.GroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMembershipService groupMembershipService;

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Group> getAllGroups() {
        return groupMapper.findAll();
    }

    @Override
    public void addGroup(AddGroupDTO addGroupDTO) throws GroupException {
        log.trace("Adding group: {}", addGroupDTO.getName());

        if (groupMapper.existsByName(addGroupDTO.getName())) {
            throw new GroupException(GroupErrorCodes.GROUP_NAME_ALREADY_EXISTS, GroupErrorCodes.GROUP_NAME_ALREADY_EXISTS.getKey());
        }

        Group group = new Group();
        group.setName(addGroupDTO.getName());
        group.setTitle(addGroupDTO.getTitle());
        group.setDescription(addGroupDTO.getDescription());
        group.setIcon(addGroupDTO.getIcon());
        group.setCreatedAt(new Date());

        groupMapper.save(group);
        log.trace("Group added: {}", addGroupDTO.getName());
    }

    @Override
    public Group getGroupById(Long groupId) throws GroupException {
        return checkGroup(groupId);
    }

    @Override
    public void updateGroup(Long groupId, UpdateGroupDTO updateGroupDTO) throws GroupException, GroupMembershipException {
        log.trace("Updating group: {}", groupId);

        if (!groupMembershipService.checkMembership(groupId, null, GroupMembershipType.MEMBER.name())) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION, GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION.getKey());
        }
        log.trace("User {} have permission to update group: {}", null, groupId);

        Group group = checkGroup(groupId);

        group.setTitle(updateGroupDTO.getTitle());
        group.setDescription(updateGroupDTO.getDescription());
        group.setIcon(updateGroupDTO.getIcon());
        group.setModifiedAt(new Date());

        groupMapper.update(group);
        log.trace("Group updated: {}", groupId);
    }

    @Override
    public void deleteGroup(Long groupId) throws GroupException, GroupMembershipException {
        log.trace("Deleting group: {}", groupId);

        if (!groupMembershipService.checkMembership(groupId, null, GroupMembershipType.MEMBER.name())) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION, GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION.getKey());
        }
        log.trace("User {} have permission to update group: {}", null, groupId);

        Group group = checkGroup(groupId);
        group.setDeleted(true);
        group.setCreatedAt(new Date());

        groupMapper.update(group);
        log.trace("Group deleted: {}", groupId);
    }

    @Override
    public List<Group> getGroupsByUserId(Long userId) {
        // TODO Comprobar si el usuario existe
        log.trace("Getting groups by userId: {}", userId);
        return groupMapper.getGroupsByUserId(userId);
    }

    private Group checkGroup(Long groupId) throws GroupException {
        log.trace("Getting group: {}", groupId);
        if (groupId == null) {
            throw new GroupException(GroupErrorCodes.GROUP_ID_NOT_NULL, GroupErrorCodes.GROUP_ID_NOT_NULL.getKey());
        }

        Group group = groupMapper.findById(groupId);

        if (group == null) {
            throw new GroupException(GroupErrorCodes.GROUP_NOT_FOUND, GroupErrorCodes.GROUP_NOT_FOUND.getKey());
        }

        log.trace("Group found: {}", groupId);
        return group;
    }
}
