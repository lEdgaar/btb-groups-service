package com.btb.groupsservice.dto.response;

import com.btb.groupsservice.common.validation.annotations.NotNullOrEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoTokenDTO {

    @NotNullOrEmpty
    private String email;

    @NotNullOrEmpty
    private String role;

}
