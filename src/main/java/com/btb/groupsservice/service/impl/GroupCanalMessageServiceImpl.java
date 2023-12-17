package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.dto.AddMessageDTO;
import com.btb.groupsservice.dto.UpdateMessageDTO;
import com.btb.groupsservice.entity.Canal;
import com.btb.groupsservice.entity.GroupCanalMessage;
import com.btb.groupsservice.exception.*;
import com.btb.groupsservice.persistence.mapper.GroupCanalMessageMapper;
import com.btb.groupsservice.service.CanalService;
import com.btb.groupsservice.service.GroupCanalMessageService;
import com.btb.groupsservice.service.GroupMembershipService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class GroupCanalMessageServiceImpl implements GroupCanalMessageService {

    @Autowired
    private CanalService canalService;

    @Autowired
    private GroupMembershipService groupMembershipService;

    @Autowired
    private GroupCanalMessageMapper groupCanalMessageMapper;

    @Override
    public List<GroupCanalMessage> getMessages(Long canalId) throws GroupMembershipException, CanalException {
        log.trace("GET /message/canal/{}", canalId);

        Canal canal = canalService.getCanal(canalId);

        if (!groupMembershipService.isMember(canal.getGroup().getId(), null)) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_MEMBER_OF_GROUP, GroupMembershipErrorCodes.USER_NOT_MEMBER_OF_GROUP.getKey());
        }
        log.trace("User {} is member of group: {}", null, canal.getGroup().getId());

        log.trace("Event: Get messages for canal: {}", canalId);
        return groupCanalMessageMapper.findByCanalId(canal.getId());
    }

    @Override
    public void addMessage(Long canalId, AddMessageDTO addMessageDTO) throws GroupMembershipException, CanalException {
        log.trace("POST /message/canal/{}", canalId);

        Canal canal = canalService.getCanal(canalId);

        if (!groupMembershipService.isMember(canal.getGroup().getId(), null)) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_MEMBER_OF_GROUP, GroupMembershipErrorCodes.USER_NOT_MEMBER_OF_GROUP.getKey());
        }
        log.trace("User {} is member of group: {}", null, canal.getGroup().getId());

        GroupCanalMessage groupCanalMessage = new GroupCanalMessage();
        groupCanalMessage.setCanal(canal);
        groupCanalMessage.setUserId(addMessageDTO.getUserId());
        groupCanalMessage.setMessage(addMessageDTO.getMessage());

        groupCanalMessageMapper.save(groupCanalMessage);
        log.trace("Event: Add message for canal: {} by userId: {}", canalId, addMessageDTO.getUserId());
    }

    @Override
    public void updateMessage(Long messageId, UpdateMessageDTO updateMessageDTO) throws GroupCanalMessageException {
        log.trace("PUT /message/{}", messageId);

        GroupCanalMessage groupCanalMessage = checkMessage(messageId);

        // TODO Comprobar que el usuario que ha escrito sea el mismo que el del token.

        groupCanalMessage.setMessage(updateMessageDTO.getMessage());

        groupCanalMessageMapper.update(groupCanalMessage);
        log.trace("Event: Update message: {}", messageId);
    }

    private GroupCanalMessage checkMessage(Long messageId) throws GroupCanalMessageException {
        log.trace("Getting message: {}", messageId);
        if (messageId == null) {
            throw new GroupCanalMessageException(GroupCanalMessageErrorCodes.MESSAGE_ID_NOT_NULL, GroupCanalMessageErrorCodes.MESSAGE_ID_NOT_NULL.getKey());
        }

        GroupCanalMessage groupCanalMessage = groupCanalMessageMapper.findById(messageId);

        if (groupCanalMessage == null) {
            throw new GroupCanalMessageException(GroupCanalMessageErrorCodes.MESSAGE_NOT_FOUND, GroupCanalMessageErrorCodes.MESSAGE_NOT_FOUND.getKey());
        }

        log.trace("Message found: {}", messageId);
        return groupCanalMessage;
    }

}
