package com.btb.groupsservice.controller;


import com.btb.groupsservice.dto.AddGroupDTO;
import com.btb.groupsservice.dto.InfoGroupDTO;
import com.btb.groupsservice.dto.UpdateGroupDTO;
import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.exception.GroupException;
import com.btb.groupsservice.exception.GroupMembershipException;
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
    public void addGroup(@RequestHeader("Authorization") String authorizationHeader, @RequestBody AddGroupDTO addGroupDTO) throws GroupException {
        log.trace("POST /groups {}", addGroupDTO.getName());

        log.info("Event: Add group: {}", addGroupDTO.getName());
        groupService.addGroup(addGroupDTO, authorizationHeader);
    }

    @GetMapping("/{groupId}")
    public @ResponseBody InfoGroupDTO getGroup(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("groupId") Long groupId) throws GroupException {
        log.trace("GET /groups/{}", groupId);

        log.info("Event: Get group: {}", groupId);
        return groupService.getGroupById(authorizationHeader, groupId);
    }

    @PutMapping("/{groupdId}")
    public void updateGroup(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("groupId") Long groupId, @RequestBody UpdateGroupDTO updateGroupDTO) throws GroupException, GroupMembershipException {
        log.trace("PUT /groups/{}", groupId);

        log.info("Event: Update group: {}", groupId);
        groupService.updateGroup(authorizationHeader, groupId, updateGroupDTO);
    }

    @DeleteMapping("/{groupdId}")
    public void deleteGroup(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("groupId") Long groupId) throws GroupException, GroupMembershipException {
        log.trace("DELETE /groups/{}", groupId);

        log.info("Event: Delete group: {}", groupId);
        groupService.deleteGroup(authorizationHeader, groupId);
    }

    @GetMapping("/user/{userId}")
    public @ResponseBody List<Group> getGroupByUserId(@PathVariable("userId") Long userId) throws GroupException {
        log.trace("GET /groups/{}", userId);

        log.info("Event: Get groups by userId: {}", userId);
        return groupService.getGroupsByUserId(userId);
    }

}
