package com.btb.groupsservice.persistence.mapper;

import com.btb.groupsservice.entity.GroupCanalMessage;

import java.util.List;

public interface GroupCanalMessageMapper extends CommonMapper<GroupCanalMessage, Long> {

    List<GroupCanalMessage> findByCanalId(Long canalId);

}
