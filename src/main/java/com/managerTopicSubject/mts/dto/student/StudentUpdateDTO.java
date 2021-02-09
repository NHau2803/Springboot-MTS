package com.managerTopicSubject.mts.dto.student;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class StudentUpdateDTO {

    @Id
    private Long id;

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

    @Id
    private Long facultyId;


    public StudentUpdateDTO(@NotNull @NotBlank @Size(min = 10, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 200) String name, String gender, @NotNull @NotBlank String birthday, @Email @NotNull @NotBlank @Size(min = 10, max = 50) String email, @NotNull @NotBlank @Size(min = 10, max = 15) String phone, Long facultyId) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.facultyId = facultyId;
    }
}
