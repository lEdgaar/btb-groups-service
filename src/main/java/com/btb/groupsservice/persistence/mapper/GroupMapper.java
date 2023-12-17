package com.btb.groupsservice.persistence.mapper;

import com.btb.groupsservice.entity.Group;

import java.util.List;

public interface GroupMapper extends CommonMapper<Group, Long> {

    boolean existsByName(String name);

    List<Group> getGroupsByUserId(Long userId);

}
