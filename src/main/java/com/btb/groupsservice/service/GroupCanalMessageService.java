package com.btb.groupsservice.service;

import com.btb.groupsservice.dto.AddMessageDTO;
import com.btb.groupsservice.dto.UpdateMessageDTO;
import com.btb.groupsservice.entity.GroupCanalMessage;
import com.btb.groupsservice.exception.CanalException;
import com.btb.groupsservice.exception.GroupCanalMessageException;
import com.btb.groupsservice.exception.GroupMembershipException;

import java.util.List;

public interface GroupCanalMessageService {

    List<GroupCanalMessage> getMessages(Long canalId) throws GroupMembershipException, CanalException;

    void addMessage(Long canalId, AddMessageDTO addMessageDTO) throws GroupMembershipException, CanalException;

    void updateMessage(Long messageId, UpdateMessageDTO updateMessageDTO) throws GroupCanalMessageException;

}
