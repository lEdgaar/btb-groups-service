package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.client.OperationsServiceClient;
import com.btb.groupsservice.client.UserOrganizationServiceClient;
import com.btb.groupsservice.dto.AddGroupDTO;
import com.btb.groupsservice.dto.InfoGroupDTO;
import com.btb.groupsservice.dto.UpdateGroupDTO;
import com.btb.groupsservice.dto.request.SendEventDTO;
import com.btb.groupsservice.dto.response.UserDTO;
import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.entity.GroupMembership;
import com.btb.groupsservice.entity.MembershipType;
import com.btb.groupsservice.exception.GroupErrorCodes;
import com.btb.groupsservice.exception.GroupException;
import com.btb.groupsservice.exception.GroupMembershipErrorCodes;
import com.btb.groupsservice.exception.GroupMembershipException;
import com.btb.groupsservice.md.GroupMembershipType;
import com.btb.groupsservice.persistence.mapper.GroupMapper;
import com.btb.groupsservice.persistence.mapper.MembershipTypeMapper;
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

    @Autowired
    private OperationsServiceClient operationsServiceClient;

    @Autowired
    private UserOrganizationServiceClient userOrganizationServiceClient;

    @Autowired
    private MembershipTypeMapper membershipTypeMapper;


    @Override
    public List<Group> getAllGroups() {
        return groupMapper.findAll();
    }

    @Override
    public void addGroup(AddGroupDTO addGroupDTO, String authorizationHeader) throws GroupException {
        log.trace("Adding group: {}", addGroupDTO.getName());

        Group filter = new Group();
        filter.setName(addGroupDTO.getName());

        if (groupMapper.findByFilter(filter).size() > 0) {
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

        UserDTO userDTO = userOrganizationServiceClient.getBrokers(authorizationHeader);

        groupMembershipService.addGroupMembership(group, userDTO.getUserId(), membershipTypeMapper.findById(GroupMembershipType.ADMIN.getId()));

        SendEventDTO sendEventDTO = new SendEventDTO();
        sendEventDTO.setUserId(userDTO.getUserId());
        sendEventDTO.setDescription("Successfully grup created");

        operationsServiceClient.sendEvent(sendEventDTO);

        GroupMembership groupMembership = new GroupMembership();
        groupMembership.setGroup(group);
        groupMembership.setUserId(userDTO.getUserId());
        groupMembership.setMembershipType(membershipTypeMapper.findById(GroupMembershipType.ADMIN.getId()));

        groupMembershipService.addGroupMembership(group, userDTO.getUserId(), membershipTypeMapper.findById(GroupMembershipType.ADMIN.getId()));

    }

    @Override
    public InfoGroupDTO getGroupById(String authorizationHeader, Long groupId) throws GroupException {
        UserDTO userDTO = userOrganizationServiceClient.getBrokers(authorizationHeader);
        Group group = checkGroup(groupId);
        InfoGroupDTO infoGroupDTO = new InfoGroupDTO();
        infoGroupDTO.setGroup(group);
        infoGroupDTO.setAdmin(false);

        if (groupMembershipService.checkMembership(infoGroupDTO.getGroup(), userDTO.getUserId(), GroupMembershipType.ADMIN.getInternalCode())) {
            infoGroupDTO.setAdmin(true);
        }

        return infoGroupDTO;
    }

    @Override
    public void updateGroup(String authorizationHeader, Long groupId, UpdateGroupDTO updateGroupDTO) throws GroupException, GroupMembershipException {
        log.trace("Updating group: {}", groupId);

        UserDTO userDTO = userOrganizationServiceClient.getBrokers(authorizationHeader);
        Group group = checkGroup(groupId);

        if (!groupMembershipService.checkMembership(group, userDTO.getUserId(), GroupMembershipType.ADMIN.getInternalCode())) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION, GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION.getKey());
        }
        log.trace("User {} have permission to update group: {}", userDTO.getUserId(), groupId);

        group.setTitle(updateGroupDTO.getTitle());
        group.setDescription(updateGroupDTO.getDescription());
        group.setIcon(updateGroupDTO.getIcon());
        group.setModifiedAt(new Date());

        groupMapper.update(group);
        log.trace("Group updated: {}", groupId);
    }

    @Override
    public void deleteGroup(String authorizationHeader, Long groupId) throws GroupException, GroupMembershipException {
        log.trace("Deleting group: {}", groupId);

        UserDTO userDTO = getBrokerInfo(authorizationHeader);
        Group group = checkGroup(groupId);

        if (!groupMembershipService.checkMembership(group, userDTO.getUserId(), GroupMembershipType.ADMIN.getInternalCode())) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION, GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION.getKey());
        }
        log.trace("User {} have permission to update group: {}", userDTO.getUserId(), groupId);

        group.setDeleted(true);
        group.setDeletedAt(new Date());

        groupMapper.update(group);
        log.trace("Group deleted: {}", groupId);
    }

    @Override
    public List<Group> getGroupsByUserId(Long userId) {
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

    private UserDTO getBrokerInfo (String authorizationHeader) {
        return userOrganizationServiceClient.getBrokers(authorizationHeader);
    }
}
