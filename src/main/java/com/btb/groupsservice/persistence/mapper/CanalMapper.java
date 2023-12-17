package com.btb.groupsservice.persistence.mapper;

import com.btb.groupsservice.entity.Canal;

import java.util.List;

public interface CanalMapper extends CommonMapper<Canal, Long> {

    List<Canal> findByGroupId(Long groupid);

}
