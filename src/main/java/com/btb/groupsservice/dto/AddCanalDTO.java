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
public class AddCanalDTO {

    private String name;

    private Long userCreatedId;

    private String description;

    private Long groupId;

    private Long organizationId;

}
