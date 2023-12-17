package com.btb.groupsservice.persistence.mapper;

import com.btb.groupsservice.entity.GroupRequest;

import java.util.List;

public interface GroupRequestMapper extends CommonMapper<GroupRequest, Long> {

    List<GroupRequest> findByUserId(Long userId);

}
