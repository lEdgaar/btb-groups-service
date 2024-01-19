package com.btb.groupsservice.persistence.mapper;

import com.btb.groupsservice.entity.Group;

import java.util.List;

public interface GroupMapper extends CommonMapper<Group, Long> {

    List<Group> getGroupsByUserId(Long userId);

}
