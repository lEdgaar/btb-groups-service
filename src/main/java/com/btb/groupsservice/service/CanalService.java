package com.btb.groupsservice.service;

import com.btb.groupsservice.dto.AddCanalDTO;
import com.btb.groupsservice.dto.UpdateCanalDTO;
import com.btb.groupsservice.entity.Canal;

import java.util.List;

public interface CanalService {

    void addCanal(AddCanalDTO addCanalDTO);

    List<Canal> getCanals(Long groupId);

    Canal getCanal(Long canalId);

    void updateCanal(Long canalId, UpdateCanalDTO updateCanalDTO);

    void deleteCanal(Long canalId);

}
