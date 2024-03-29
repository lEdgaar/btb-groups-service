package com.btb.groupsservice.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GroupCanalMessage extends BaseModel<Long>{

    private static final long serialVersionUID = 1L;

    private long id;

    private Long userId;

    private Canal canal;

    private String message;

    private Date createdAt;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
