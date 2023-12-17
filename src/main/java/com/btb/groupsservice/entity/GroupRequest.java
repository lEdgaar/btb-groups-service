package com.btb.groupsservice.entity;

import com.btb.groupsservice.md.RequestStatusMD;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest extends BaseModel<Long> {

    private static final long serialVersionUID = 1L;

    private long id;

    private Group group;

    private Long guestUserId;

    private Long requestSendedUserId;

    private String requestStatusMD;

    private Date sendedAt;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
