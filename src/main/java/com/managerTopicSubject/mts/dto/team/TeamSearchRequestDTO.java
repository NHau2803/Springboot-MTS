package com.managerTopicSubject.mts.dto.team;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class TeamSearchRequestDTO {
    @Id
    private Long id;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String name;

    @NotNull @NotBlank @Size(min = 5, max = 100)
    private String topicName;

    private Integer studentTotal;

    public TeamSearchRequestDTO(@NotNull @NotBlank @Size(min = 5, max = 50) String name, @NotNull @NotBlank @Size(min = 5, max = 100) String topicName, Integer studentTotal) {
        this.name = name;
        this.topicName = topicName;
        this.studentTotal = studentTotal;
    }
}
