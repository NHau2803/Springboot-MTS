package com.managerTopicSubject.mts.dto.team;

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
public class TeamSearchResponseDTO {
    @Id
    private Long id;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String name;

    @Id
    private String facultyName;

    @NotNull @NotBlank @Size(min = 5, max = 100)
    private String topicName;

    private Integer studentTotal;

    @NotNull @NotBlank
    private String status;

}
