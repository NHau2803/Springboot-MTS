package com.managerTopicSubject.mts.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateAccountRequestDTO {

    @NotNull @NotBlank
    private String username;

    @NotNull @NotBlank @Size(min = 9, max = 50)
    private String passwordOld;

    @NotNull @NotBlank @Size(min = 9, max = 50)
    private String passwordNew;

    @NotNull @NotBlank @Size(min = 9, max = 50)
    private String passwordConfirm;
}
