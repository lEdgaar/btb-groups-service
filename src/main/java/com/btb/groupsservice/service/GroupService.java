package com.btb.groupsservice.service;

import com.btb.groupsservice.dto.AddGroupDTO;
import com.btb.groupsservice.dto.InfoGroupDTO;
import com.btb.groupsservice.dto.UpdateGroupDTO;
import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.exception.GroupException;
import com.btb.groupsservice.exception.GroupMembershipException;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();

    void addGroup(AddGroupDTO addGroupDTO, String authorizationHeader) throws GroupException;

    InfoGroupDTO getGroupById(String authorizationHeader, Long groupId) throws GroupException;

    void updateGroup(String authorizationHeader, Long groupId, UpdateGroupDTO updateGroupDTO) throws GroupException, GroupMembershipException;

    void deleteGroup(String authorizationHeader, Long groupId) throws GroupException, GroupMembershipException;

    List<Group> getGroupsByUserId(Long userId);

}
