package com.btb.groupsservice.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    private String firstName;

    private String surname;

    private Date dateOfBirth;

    private String gender;

}
