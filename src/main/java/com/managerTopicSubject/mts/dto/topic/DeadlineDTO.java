package com.managerTopicSubject.mts.dto.topic;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
public class DeadlineDTO {

    @Id
    private Long id;

    @NotNull @NotBlank
    private String startDeadline;

    @NotNull @NotBlank
    private String endDeadline;

    @NotNull @NotBlank
    private String content;




}
