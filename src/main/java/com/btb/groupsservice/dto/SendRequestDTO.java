package com.btb.groupsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendRequestDTO {

    private Long guestUserId;

    private Long requestSendedUserId;

}
