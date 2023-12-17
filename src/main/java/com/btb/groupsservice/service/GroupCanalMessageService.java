package com.btb.groupsservice.service;

import com.btb.groupsservice.dto.AddMessageDTO;
import com.btb.groupsservice.dto.UpdateMessageDTO;
import com.btb.groupsservice.entity.GroupCanalMessage;

import java.util.List;

public interface GroupCanalMessageService {

    List<GroupCanalMessage> getMessages(Long canalId);

    void addMessage(Long canalId, AddMessageDTO addMessageDTO);

    void updateMessage(Long messageId, UpdateMessageDTO updateMessageDTO);

}
