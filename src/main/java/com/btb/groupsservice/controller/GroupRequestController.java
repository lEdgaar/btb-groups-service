package com.btb.groupsservice.controller;

import com.btb.groupsservice.entity.GroupCanalMessage;
import com.btb.groupsservice.entity.GroupRequest;
import com.btb.groupsservice.service.GroupCanalMessageService;
import com.btb.groupsservice.service.GroupRequestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/request")
public class GroupRequestController {

    private GroupRequestService groupRequestService;

    @Autowired
    public GroupRequestController(GroupRequestService groupRequestService) {
        this.groupRequestService = groupRequestService;
    }

    @GetMapping("/user/{userId}")
    public List<GroupRequest> findRequestsByUserId(@PathVariable("userId") Long userId) {

        return groupRequestService.findRequestsByUserId(userId);
    }

    @PostMapping("/user/{userId}/group/{groupId}")
    public void sendRequest(@PathVariable("userId") Long userId, @PathVariable("groupId") Long groupId) {

        groupRequestService.sendRequest(userId, groupId);
    }

    @PutMapping("/{requestId}/accept")
    public void acceptRequest(@PathVariable("requestId") Long requestId) {

        groupRequestService.acceptRequest(requestId);
    }

    @PutMapping("/{requestId}/reject")
    public void rejectRequest(@PathVariable("requestId") Long requestId) {

        groupRequestService.rejectRequest(requestId);
    }

}
