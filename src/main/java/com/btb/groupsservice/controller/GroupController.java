package com.btb.groupsservice.controller;


import com.btb.groupsservice.dto.AddGroupDTO;
import com.btb.groupsservice.dto.UpdateGroupDTO;
import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.service.GroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/groups")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    public @ResponseBody List<Group> getGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/")
    public void addGroup(@RequestBody AddGroupDTO addGroupDTO) {

        groupService.addGroup(addGroupDTO);
    }

    @GetMapping("/{groupdId}")
    public @ResponseBody Group getGroup(@PathVariable("groupId") Long groupId) {

        return groupService.getGroupById(groupId);
    }

    @PutMapping("/{groupdId}")
    public void updateGroup(@PathVariable("groupId") Long groupId, @RequestBody UpdateGroupDTO updateGroupDTO) {

        groupService.updateGroup(groupId, updateGroupDTO);
    }

    @DeleteMapping("/{groupdId}")
    public void deleteGroup(@PathVariable("groupId") Long groupId) {

        groupService.deleteGroup(groupId);
    }

}
