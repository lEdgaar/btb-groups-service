package com.btb.groupsservice.service;

import com.btb.groupsservice.dto.SendRequestDTO;
import com.btb.groupsservice.entity.GroupRequest;
import com.btb.groupsservice.exception.GroupException;
import com.btb.groupsservice.exception.GroupMembershipException;
import com.btb.groupsservice.exception.GroupRequestException;

import java.util.List;

public interface GroupRequestService {

    List<GroupRequest> findRequestsByUserId(Long userId);

    void sendRequest(Long groupId, SendRequestDTO sendRequestDTO) throws GroupException, GroupMembershipException;

    void acceptRequest(Long requestId) throws GroupRequestException;

    void rejectRequest(Long requestId) throws GroupRequestException;

}
