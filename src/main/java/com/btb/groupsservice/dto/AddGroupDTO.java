package com.btb.groupsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddGroupDTO {

    private String name;

    private String title;

    private String description;

    private String icon;

}
