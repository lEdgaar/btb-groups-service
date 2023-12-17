package com.btb.groupsservice.controller;

import com.btb.groupsservice.dto.AddMessageDTO;
import com.btb.groupsservice.dto.UpdateMessageDTO;
import com.btb.groupsservice.entity.GroupCanalMessage;
import com.btb.groupsservice.service.GroupCanalMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/message")
public class MessageController {

    private GroupCanalMessageService groupCanalMessageService;

    @Autowired
    public MessageController(GroupCanalMessageService groupCanalMessageService) {
        this.groupCanalMessageService = groupCanalMessageService;
    }

    @GetMapping("/canal/{canalId}/")
    public @ResponseBody List<GroupCanalMessage> getMessages(@PathVariable("canalId") Long canalId) {
        log.trace("GET /message/canal/{}", canalId);

        log.info("Event: Get messages for canal: {}", canalId);
        return groupCanalMessageService.getMessages(canalId);
    }

    @PostMapping("/canal/{canalId}/")
    public void addMessage(@PathVariable("canalId") Long canalId, @RequestBody AddMessageDTO addMessageDTO) {
        log.trace("POST /message/canal/{}", canalId);

        log.info("Event: Add message for canal: {} by userId: {}", canalId, addMessageDTO.getUserId());
        groupCanalMessageService.addMessage(canalId, addMessageDTO);
    }

    @PutMapping("/{messageId}")
    public void updateMessage(@PathVariable("messageId") Long messageId, @RequestBody UpdateMessageDTO updateMessageDTO) {
        log.trace("PUT /message/{}", messageId);

        log.info("Event: Update message: {}", messageId);
        groupCanalMessageService.updateMessage(messageId, updateMessageDTO);
    }

}
