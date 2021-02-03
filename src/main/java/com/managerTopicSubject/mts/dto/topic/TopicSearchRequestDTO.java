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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class TopicSearchRequestDTO {

    @Id
    private Long id;

    @NotNull @NotBlank @Size(min = 5, max = 100)
    private String name;

    private List<String> teamList;

    @NotNull @NotBlank
    private String startTime;

    @NotNull @NotBlank
    private String endTime;

    @NotNull @NotBlank
    private Map<String, Object> teacher;

    public TopicSearchRequestDTO(@NotNull @NotBlank @Size(min = 5, max = 100) String name, List<String> teamList, @NotNull @NotBlank String startTime, @NotNull @NotBlank String endTime, @NotNull @NotBlank Map<String, Object> teacher) {
        this.name = name;
        this.teamList = teamList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
    }
}
