package com.project.xircle.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckEmailRequestDto {
    @NotBlank(message = "email is empty")
    private String email;
    @NotBlank(message = "code is empty")
    @Pattern(regexp = "\\d{6}",message = "code is not 6 digit")
    private String code;
}
