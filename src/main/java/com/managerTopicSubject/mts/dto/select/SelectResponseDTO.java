package com.managerTopicSubject.mts.dto.select;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectResponseDTO {

    @Id
    private Long id;

    @NotNull @NotBlank
    private String title;

}
