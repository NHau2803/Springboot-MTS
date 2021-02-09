package com.managerTopicSubject.mts.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequestDTO {

    @NotBlank
    @Size(min=8, max = 50)
    private String username;

    @NotBlank
    @Size(min=8, max = 50)
    private String password;

}
