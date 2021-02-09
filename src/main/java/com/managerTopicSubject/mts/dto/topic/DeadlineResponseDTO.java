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
public class DeadlineResponseDTO {

    @Id
    private Long id;

    @NotNull @NotBlank
    private String startTime;

    @NotNull @NotBlank
    private String endTime;

    @NotNull @NotBlank
    private String content;


}
