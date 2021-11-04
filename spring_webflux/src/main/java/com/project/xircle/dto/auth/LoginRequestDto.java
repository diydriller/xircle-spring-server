package com.project.xircle.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "displayName is empty")
    private String displayName;
    @NotBlank(message = "password is empty")
    private String password;
}
