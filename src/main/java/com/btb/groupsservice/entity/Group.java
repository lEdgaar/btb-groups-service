package com.btb.groupsservice.entity;

import lombok.*;

import java.util.Date;

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

    private Date createdAt; // NUEVO

    private Date modifiedAt; // NUEVO

    private boolean isDeleted; // NUEVO

    private Date deletedAt; // NUEVO

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
