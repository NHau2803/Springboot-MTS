package com.managerTopicSubject.mts.dto.topic;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DeadlineRequestDTO {

    @NotNull @NotBlank
    private String startTime;

    @NotNull @NotBlank
    private String endTime;

    @NotNull @NotBlank
    private String content;
}
