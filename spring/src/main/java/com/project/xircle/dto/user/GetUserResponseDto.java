package com.project.xircle.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserResponseDto {
    private String displayName;
    private Long id;
}
