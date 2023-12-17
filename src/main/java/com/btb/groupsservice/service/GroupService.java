package com.btb.groupsservice.service;

import com.btb.groupsservice.dto.AddGroupDTO;
import com.btb.groupsservice.dto.UpdateGroupDTO;
import com.btb.groupsservice.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();

    void addGroup(AddGroupDTO addGroupDTO);

    Group getGroupById(Long groupId);

    void updateGroup(Long groupId, UpdateGroupDTO updateGroupDTO);

    void deleteGroup(Long groupId);

}
