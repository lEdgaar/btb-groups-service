package com.btb.groupsservice.entity;

public class PrivacyLevel extends BaseModel<Long>{

    private static final long serialVersionUID = 1L;

    private long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
