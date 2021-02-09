package com.managerTopicSubject.mts.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoResponseDTO {

    @NotNull @NotBlank @Size(min = 10, max = 20)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    private String gender;

    @NotNull @NotBlank
    private String birthday;

    @Email @NotNull @NotBlank @Size(min = 10, max = 50)
    private String email;

    @NotNull @NotBlank @Size(min = 10, max = 15)
    private String phone;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String facultyName;

}
