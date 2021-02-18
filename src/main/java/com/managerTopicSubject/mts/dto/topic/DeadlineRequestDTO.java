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
    private String startDeadline;

    @NotNull @NotBlank
    private String endDeadline;

    @NotNull @NotBlank
    private String content;
}
