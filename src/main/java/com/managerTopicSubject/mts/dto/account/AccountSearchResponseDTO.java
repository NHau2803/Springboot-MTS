package com.managerTopicSubject.mts.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSearchResponseDTO {

    @Id
    private Long id;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String username;

    @NotNull @NotBlank
    private String status;

    @NotNull @NotBlank
    private String roles;

}
