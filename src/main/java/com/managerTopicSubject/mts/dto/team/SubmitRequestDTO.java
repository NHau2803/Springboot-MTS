package com.managerTopicSubject.mts.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitRequestDTO {

    @Id
    private Long id;

    @NotNull @NotBlank
    private String link;
}
