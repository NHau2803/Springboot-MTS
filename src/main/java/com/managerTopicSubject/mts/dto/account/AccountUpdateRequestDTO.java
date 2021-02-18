package com.managerTopicSubject.mts.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateRequestDTO {

    @Id
    private Long id;

    @NotNull @NotBlank
    private String newPassword;

    @NotNull @NotBlank
    private String status;

    @NotNull @NotBlank
    private String roles;

}
