package com.btb.groupsservice.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PrivacyLevel extends BaseModel<Long>{

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private String description;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
