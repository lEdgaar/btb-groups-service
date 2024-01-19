package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.dto.InfoGroupDTO;
import com.btb.groupsservice.dto.SendRequestDTO;
import com.btb.groupsservice.entity.GroupRequest;
import com.btb.groupsservice.exception.*;
import com.btb.groupsservice.md.GroupMembershipType;
import com.btb.groupsservice.md.RequestStatusMD;
import com.btb.groupsservice.persistence.mapper.GroupRequestMapper;
import com.btb.groupsservice.service.GroupMembershipService;
import com.btb.groupsservice.service.GroupRequestService;
import com.btb.groupsservice.service.GroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class GroupRequestServiceImpl implements GroupRequestService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMembershipService groupMembershipService;

    @Autowired
    private GroupRequestMapper groupRequestMapper;


    @Override
    public List<GroupRequest> findRequestsByUserId(Long userId) {
        log.trace("Getting requests by userId: {}", userId);

        return groupRequestMapper.findByUserId(userId);
    }

    @Override
    public void sendRequest(String authorizationHeader, Long groupId, SendRequestDTO sendRequestDTO) throws GroupException, GroupMembershipException {
        log.trace("Sending request from user: {} to group: {} by userAdminIdGroup: {}", sendRequestDTO.getGuestUserId(), groupId, sendRequestDTO.getRequestSendedUserId());

        InfoGroupDTO infoGroupDTO = groupService.getGroupById(authorizationHeader, groupId);

        if (!infoGroupDTO.isAdmin()) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION, GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION.getKey());
        }
        log.trace("User {} have permission to update group: {}", null, groupId);

        GroupRequest groupRequest = new GroupRequest();
        groupRequest.setGuestUserId(sendRequestDTO.getGuestUserId());
        groupRequest.setRequestSendedUserId(sendRequestDTO.getRequestSendedUserId());
        groupRequest.setRequestStatusMD(RequestStatusMD.PENDING.name());
        groupRequest.setGroup(infoGroupDTO.getGroup());
        groupRequest.setSendedAt(new Date());

        groupRequestMapper.save(groupRequest);
        log.trace("Request saved: {}", groupRequest);
    }

    @Override
    public void acceptRequest(Long requestId) throws GroupRequestException {
        log.trace("Accepting request: {}", requestId);

        GroupRequest groupRequest = checkGroupRequest(requestId);
        groupRequest.setRequestStatusMD(RequestStatusMD.ACCEPTED.name());

        groupRequestMapper.update(groupRequest);
        log.trace("Request updated: {}", groupRequest);
    }

    @Override
    public void rejectRequest(Long requestId) throws GroupRequestException {
        log.trace("Rejecting request: {}", requestId);

        GroupRequest groupRequest = checkGroupRequest(requestId);
        groupRequest.setRequestStatusMD(RequestStatusMD.REJECTED.name());

        groupRequestMapper.update(groupRequest);
        log.trace("Request updated: {}", groupRequest);
    }

    private GroupRequest checkGroupRequest(Long requestId) throws GroupRequestException {
        log.trace("Getting request: {}", requestId);
        if (requestId == null) {
            throw new GroupRequestException(GroupRequestErrorCodes.GROUP_REQUEST_ID_NOT_NULL, GroupRequestErrorCodes.GROUP_REQUEST_ID_NOT_NULL.getKey());
        }

        GroupRequest groupRequest = groupRequestMapper.findById(requestId);

        if (groupRequest == null) {
            throw new GroupRequestException(GroupRequestErrorCodes.GROUP_REQUEST_NOT_FOUND, GroupRequestErrorCodes.GROUP_REQUEST_NOT_FOUND.getKey());
        }

        log.trace("GroupRequest found: {}", groupRequest);
        return groupRequest;
    }

}
