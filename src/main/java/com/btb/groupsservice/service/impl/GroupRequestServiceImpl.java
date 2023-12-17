package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.entity.GroupRequest;
import com.btb.groupsservice.service.GroupRequestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class GroupRequestServiceImpl implements GroupRequestService {

    @Override
    public List<GroupRequest> findRequestsByUserId(Long userId) {
        return null;
    }

    @Override
    public void sendRequest(Long userId, Long groupId) {

    }

    @Override
    public void acceptRequest(Long requestId) {

    }

    @Override
    public void rejectRequest(Long requestId) {

    }
}
