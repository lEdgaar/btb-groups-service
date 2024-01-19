package com.btb.groupsservice.service;

import com.btb.groupsservice.dto.AddCanalDTO;
import com.btb.groupsservice.dto.UpdateCanalDTO;
import com.btb.groupsservice.entity.Canal;
import com.btb.groupsservice.exception.CanalException;
import com.btb.groupsservice.exception.GroupException;
import com.btb.groupsservice.exception.GroupMembershipException;

import java.util.List;

public interface CanalService {

    void addCanal(String authorizationHeader, AddCanalDTO addCanalDTO) throws GroupException, GroupMembershipException;

    List<Canal> getCanals(String authorizationHeader, Long groupId) throws GroupException, GroupMembershipException;

    Canal getCanal(Long canalId) throws CanalException, GroupMembershipException;

    void updateCanal(Long canalId, UpdateCanalDTO updateCanalDTO) throws CanalException, GroupMembershipException;

    void deleteCanal(Long canalId) throws CanalException, GroupMembershipException;

}
