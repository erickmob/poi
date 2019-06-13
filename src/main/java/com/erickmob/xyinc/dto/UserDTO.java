package com.erickmob.xyinc.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class UserDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
