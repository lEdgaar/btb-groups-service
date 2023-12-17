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
        log.trace("GET /groups");

        log.info("Event: Get all groups");
        return groupService.getAllGroups();
    }

    @PostMapping("/")
    public void addGroup(@RequestBody AddGroupDTO addGroupDTO) {
        log.trace("POST /groups {}", addGroupDTO.getName());

        log.info("Event: Add group: {}", addGroupDTO.getName());
        groupService.addGroup(addGroupDTO);
    }

    @GetMapping("/{groupdId}")
    public @ResponseBody Group getGroup(@PathVariable("groupId") Long groupId) {
        log.trace("GET /groups/{}", groupId);

        log.info("Event: Get group: {}", groupId);
        return groupService.getGroupById(groupId);
    }

    @PutMapping("/{groupdId}")
    public void updateGroup(@PathVariable("groupId") Long groupId, @RequestBody UpdateGroupDTO updateGroupDTO) {
        log.trace("PUT /groups/{}", groupId);

        log.info("Event: Update group: {}", groupId);
        groupService.updateGroup(groupId, updateGroupDTO);
    }

    @DeleteMapping("/{groupdId}")
    public void deleteGroup(@PathVariable("groupId") Long groupId) {
        log.trace("DELETE /groups/{}", groupId);

        log.info("Event: Delete group: {}", groupId);
        groupService.deleteGroup(groupId);
    }

}
