package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.dto.AddCanalDTO;
import com.btb.groupsservice.dto.UpdateCanalDTO;
import com.btb.groupsservice.entity.Canal;
import com.btb.groupsservice.entity.Group;
import com.btb.groupsservice.exception.*;
import com.btb.groupsservice.md.GroupMembershipType;
import com.btb.groupsservice.persistence.mapper.CanalMapper;
import com.btb.groupsservice.service.CanalService;
import com.btb.groupsservice.service.GroupMembershipService;
import com.btb.groupsservice.service.GroupService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class CanalServiceImpl implements CanalService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMembershipService groupMembershipService;

    @Autowired
    private CanalMapper canalMapper;

    @Override
    public void addCanal(AddCanalDTO addCanalDTO) throws GroupException, GroupMembershipException {
        log.trace("Add canal: {} to group: {}", addCanalDTO.getName(), addCanalDTO.getGroupId());
        Group group = groupService.getGroupById(addCanalDTO.getGroupId());

        if (!groupMembershipService.checkMembership(addCanalDTO.getGroupId(), null, GroupMembershipType.MEMBER.name())) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION, GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION.getKey());
        }
        log.trace("User {} have permission to update group: {}", null, addCanalDTO.getGroupId());

        Canal canal = new Canal();
        canal.setName(addCanalDTO.getName());
        canal.setGroup(group);

        // TODO comprobar si el usuario existe
        canal.setUserCreatedId(addCanalDTO.getUserCreatedId());
        canal.setDescription(addCanalDTO.getDescription());
        canal.setOrganizationId(addCanalDTO.getOrganizationId());
        canal.setCreatedAt(new Date());

        canalMapper.save(canal);
        log.trace("Canal added: {} to group: {}", canal.getId(), addCanalDTO.getGroupId());
    }

    @Override
    public List<Canal> getCanals(Long groupId) throws GroupException, GroupMembershipException {
        groupService.getGroupById(groupId);
        log.trace("Get canals from group: {}", groupId);

        if (!groupMembershipService.isMember(groupId, null)) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_MEMBER_OF_GROUP, GroupMembershipErrorCodes.USER_NOT_MEMBER_OF_GROUP.getKey());
        }
        log.trace("User {} is member of group: {}", null, groupId);

        return canalMapper.findByGroupId(groupId);
    }

    @Override
    public Canal getCanal(Long canalId) throws CanalException, GroupMembershipException {
        log.trace("Get canal: {}", canalId);

        Canal canal = checkCanal(canalId);

        if (!groupMembershipService.isMember(canal.getGroup().getId(), null)) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_MEMBER_OF_GROUP, GroupMembershipErrorCodes.USER_NOT_MEMBER_OF_GROUP.getKey());
        }
        log.trace("User {} is member of group: {}", null, canal.getGroup().getId());

        return canal;
    }

    @Override
    public void updateCanal(Long canalId, UpdateCanalDTO updateCanalDTO) throws CanalException, GroupMembershipException {
        log.trace("Update canal: {}", canalId);

        Canal canal = checkCanal(canalId);

        if (!groupMembershipService.checkMembership(canal.getGroup().getId(), null, GroupMembershipType.MEMBER.name())) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION, GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION.getKey());
        }
        log.trace("User {} have permission to update group: {}", null, canal.getGroup().getId());

        canal.setTitle(updateCanalDTO.getTitle());
        canal.setDescription(updateCanalDTO.getDescription());
        canal.setModifiedAt(new Date());

        canalMapper.update(canal);
        log.trace("Canal updated: {}", canalId);
    }

    @Override
    public void deleteCanal(Long canalId) throws CanalException, GroupMembershipException {
        log.trace("Delete canal: {}", canalId);

        Canal canal = checkCanal(canalId);

        if (!groupMembershipService.checkMembership(canal.getGroup().getId(), null, GroupMembershipType.MEMBER.name())) {
            throw new GroupMembershipException(GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION, GroupMembershipErrorCodes.USER_NOT_HAVE_PERMISSION.getKey());
        }
        log.trace("User {} have permission to update group: {}", null, canal.getGroup().getId());


        canal.setDeleted(true);
        canal.setDeletedAt(new Date());

        canalMapper.update(canal);
        log.trace("Canal deleted: {}", canalId);
    }

    private Canal checkCanal(Long canalId) throws CanalException {
        log.trace("Getting canal: {}", canalId);
        if (canalId == null) {
            throw new CanalException(CanalErrorCodes.CANAL_ID_NOT_NULL, CanalErrorCodes.CANAL_ID_NOT_NULL.getKey());
        }

        Canal canal = canalMapper.findById(canalId);

        if (canal == null) {
            throw new CanalException(CanalErrorCodes.CANAL_NOT_FOUND, CanalErrorCodes.CANAL_NOT_FOUND.getKey());
        }

        log.trace("Canal found: {}", canal.getId());
        return canal;
    }
}
