package com.btb.groupsservice.controller;

import com.btb.groupsservice.dto.AddCanalDTO;
import com.btb.groupsservice.dto.UpdateCanalDTO;
import com.btb.groupsservice.entity.Canal;
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
    public void addCanal(@RequestBody AddCanalDTO addCanalDTO) {

        canalService.addCanal(addCanalDTO);
    }

    @GetMapping("/group/{groupdId}")
    public @ResponseBody List<Canal> getCanals(@PathVariable("groupId") Long groupId) {

        return canalService.getCanals(groupId);
    }

    @GetMapping("/{canalId}")
    public Canal getCanal(@PathVariable("canalId") Long canalId) {

        return canalService.getCanal(canalId);
    }

    @PutMapping("/{canalId}")
    public void updateCanal(@PathVariable("canalId") Long canalId, @RequestBody UpdateCanalDTO updateCanalDTO) {

        canalService.updateCanal(canalId, updateCanalDTO);
    }

    @DeleteMapping("/{canalId}")
    public void deleteCanal(@PathVariable("canalId") Long canalId) {

        canalService.deleteCanal(canalId);
    }

}
