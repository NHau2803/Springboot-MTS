package com.managerTopicSubject.mts.dto.teacher;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class TeacherSearchResponseDTO {
    @Id
    private Long id;

    @NotNull @NotBlank @Size(min = 10, max = 20)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 200)
    private String name;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String academyName;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String positionName;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String facultyName;

    private List<String> topicNames;

    @NotNull @NotBlank
    private String status;
}
