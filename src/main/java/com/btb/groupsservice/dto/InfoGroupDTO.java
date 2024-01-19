package com.btb.groupsservice.dto;

import com.btb.groupsservice.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoGroupDTO {
    private Group group;

    private boolean isAdmin;
}
