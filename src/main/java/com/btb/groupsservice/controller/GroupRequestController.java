package com.btb.groupsservice.controller;

import com.btb.groupsservice.dto.SendRequestDTO;
import com.btb.groupsservice.entity.GroupRequest;
import com.btb.groupsservice.exception.GroupException;
import com.btb.groupsservice.exception.GroupMembershipException;
import com.btb.groupsservice.exception.GroupRequestException;
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
        log.trace("GET /request/user/{}", userId);

        log.info("Event: Find requests by userId: {}", userId);
        return groupRequestService.findRequestsByUserId(userId);
    }

    @PostMapping("/group/{groupId}")
    public void sendRequest(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("groupId") Long groupId, @RequestBody SendRequestDTO sendRequestDTO) throws GroupException, GroupMembershipException {
        log.trace("POST /group/{}", groupId);

        log.info("Event: Send request from user: {} to group: {} and userId {}", sendRequestDTO.getRequestSendedUserId(), groupId, sendRequestDTO.getGuestUserId());
        groupRequestService.sendRequest(authorizationHeader, groupId, sendRequestDTO);
    }

    @PutMapping("/{requestId}/accept")
    public void acceptRequest(@PathVariable("requestId") Long requestId) throws GroupRequestException {
        log.trace("PUT /request/{}/accept", requestId);

        log.info("Event: Accept request: {}", requestId);
        groupRequestService.acceptRequest(requestId);
    }

    @PutMapping("/{requestId}/reject")
    public void rejectRequest(@PathVariable("requestId") Long requestId) throws GroupRequestException {
        log.trace("PUT /request/{}/reject", requestId);

        log.info("Event: Reject request: {}", requestId);
        groupRequestService.rejectRequest(requestId);
    }

}
