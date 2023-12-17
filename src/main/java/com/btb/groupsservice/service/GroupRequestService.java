package com.btb.groupsservice.service;

import com.btb.groupsservice.dto.SendRequestDTO;
import com.btb.groupsservice.entity.GroupRequest;

import java.util.List;

public interface GroupRequestService {

    List<GroupRequest> findRequestsByUserId(Long userId);

    void sendRequest(Long groupId, SendRequestDTO sendRequestDTO);

    void acceptRequest(Long requestId);

    void rejectRequest(Long requestId);

}
