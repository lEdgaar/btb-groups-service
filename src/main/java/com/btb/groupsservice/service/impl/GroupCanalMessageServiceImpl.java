package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.dto.AddMessageDTO;
import com.btb.groupsservice.dto.UpdateMessageDTO;
import com.btb.groupsservice.entity.GroupCanalMessage;
import com.btb.groupsservice.service.GroupCanalMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class GroupCanalMessageServiceImpl implements GroupCanalMessageService {
    @Override
    public List<GroupCanalMessage> getMessages(Long canalId) {
        return null;
    }

    @Override
    public void addMessage(Long canalId, AddMessageDTO addMessageDTO) {

    }

    @Override
    public void updateMessage(Long messageId, UpdateMessageDTO updateMessageDTO) {

    }
}
