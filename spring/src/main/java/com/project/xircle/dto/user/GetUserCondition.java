package com.project.xircle.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserCondition {
    private Integer age;
    private String university;
    private String gender;
}
