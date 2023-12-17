package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.dto.AddGroupDTO;
import com.btb.groupsservice.dto.UpdateGroupDTO;
import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.persistence.mapper.GroupMapper;
import com.btb.groupsservice.service.GroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public List<Group> getAllGroups() {
        return groupMapper.findAll();
    }

    @Override
    public void addGroup(AddGroupDTO addGroupDTO) {
        Group group = new Group();

        groupMapper.save(group);
    }

    @Override
    public Group getGroupById(Long groupId) {
        return groupMapper.findById(groupId);
    }

    @Override
    public void updateGroup(Long groupId, UpdateGroupDTO updateGroupDTO) {
        Group group = new Group();

        groupMapper.update(group);
    }

    @Override
    public void deleteGroup(Long groupId) {
        Group group = new Group();

        groupMapper.update(group);
    }
}
