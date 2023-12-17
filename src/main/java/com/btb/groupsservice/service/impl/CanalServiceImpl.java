package com.btb.groupsservice.service.impl;

import com.btb.groupsservice.dto.AddCanalDTO;
import com.btb.groupsservice.dto.UpdateCanalDTO;
import com.btb.groupsservice.entity.Canal;
import com.btb.groupsservice.service.CanalService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CanalServiceImpl implements CanalService {
    @Override
    public void addCanal(AddCanalDTO addCanalDTO) {

    }

    @Override
    public List<Canal> getCanals(Long groupId) {
        return null;
    }

    @Override
    public Canal getCanal(Long canalId) {
        return null;
    }

    @Override
    public void updateCanal(Long canalId, UpdateCanalDTO updateCanalDTO) {

    }

    @Override
    public void deleteCanal(Long canalId) {

    }
}
