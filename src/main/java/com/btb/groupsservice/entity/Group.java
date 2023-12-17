package com.btb.groupsservice.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Group extends BaseModel<Long> {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private String title; // NUEVO

    private String description;

    private String icon;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
