package com.managerTopicSubject.mts.dto.team;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinTeamRequestDTO {

    @NotNull @NotBlank
    private String timeJoin;

    @Id
    private Long studentId;

    @Id
    private Long teamId;
}
