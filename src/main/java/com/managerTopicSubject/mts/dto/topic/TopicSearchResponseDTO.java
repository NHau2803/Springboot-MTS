package com.managerTopicSubject.mts.dto.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicSearchResponseDTO {

    @Id
    private Long id;

    @NotNull @NotBlank @Size(min = 5, max = 100)
    private String name;

    private List<String> teamNames;

    @NotNull @NotBlank
    private String startTime;

    @NotNull @NotBlank
    private String endTime;

    @NotNull @NotBlank
    private String teacherName;

    @NotNull @NotBlank
    private String facultyName;

    @NotNull @NotBlank
    private String status;


}
