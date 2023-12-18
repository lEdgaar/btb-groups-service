package com.btb.groupsservice.controller;

import com.btb.groupsservice.dto.AddCanalDTO;
import com.btb.groupsservice.dto.UpdateCanalDTO;
import com.btb.groupsservice.entity.Canal;
import com.btb.groupsservice.exception.CanalException;
import com.btb.groupsservice.exception.GroupException;
import com.btb.groupsservice.exception.GroupMembershipException;
import com.btb.groupsservice.service.CanalService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/canals")
public class CanalController {

    private CanalService canalService;

    @Autowired
    public CanalController(CanalService canalService) {
        this.canalService = canalService;
    }

    @PostMapping("/")
    public void addCanal(@RequestBody AddCanalDTO addCanalDTO) throws GroupException, GroupMembershipException {
        log.trace("POST /canals {}", addCanalDTO.getName());

        log.info("Event: Add canal: {}", addCanalDTO.getName());
        canalService.addCanal(addCanalDTO);
    }

    @GetMapping("/group/{groupdId}")
    public @ResponseBody List<Canal> getCanals(@PathVariable("groupId") Long groupId) throws GroupException, GroupMembershipException {
        log.trace("GET /canals/group/{}", groupId);

        log.info("Event: Get canals from group: {}", groupId);
        return canalService.getCanals(groupId);
    }

    @GetMapping("/{canalId}")
    public Canal getCanal(@PathVariable("canalId") Long canalId) throws CanalException, GroupMembershipException {
        log.trace("GET /canals/{}", canalId);

        log.info("Event: Get canal: {}", canalId);
        return canalService.getCanal(canalId);
    }

    @PutMapping("/{canalId}")
    public void updateCanal(@PathVariable("canalId") Long canalId, @RequestBody UpdateCanalDTO updateCanalDTO) throws CanalException, GroupMembershipException {
        log.trace("PUT /canals/{}", canalId);

        log.info("Event: Update canal: {}", canalId);
        canalService.updateCanal(canalId, updateCanalDTO);
    }

    @DeleteMapping("/{canalId}")
    public void deleteCanal(@PathVariable("canalId") Long canalId) throws CanalException, GroupMembershipException {
        log.trace("DELETE /canals/{}", canalId);

        log.info("Event: Delete canal: {}", canalId);
        canalService.deleteCanal(canalId);
    }

}
