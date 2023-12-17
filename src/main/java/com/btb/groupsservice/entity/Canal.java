package com.btb.groupsservice.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Canal extends BaseModel<Long> {

    private static final long serialVersionUID = 1L;

    private long id;

    private String title; //NUEVO

    private String name;

    private Long userCreatedId;

    private String description;

    private Group group;

    private Long organizationId;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
