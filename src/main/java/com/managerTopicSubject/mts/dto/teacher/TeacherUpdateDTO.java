package com.managerTopicSubject.mts.dto.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
public class TeacherUpdateDTO {
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

    @Email
    @NotNull @NotBlank @Size(min = 10, max = 50)
    private String email;

    @NotNull @NotBlank @Size(min = 10, max = 15)
    private String phone;

    @Id
    private Long academyId;

    @Id
    private Long positionId;

    @Id
    private Long facultyId;

    public TeacherUpdateDTO(@NotNull @NotBlank @Size(min = 10, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 200) String name, String gender, @NotNull @NotBlank String birthday, @Email @NotNull @NotBlank @Size(min = 10, max = 50) String email, @NotNull @NotBlank @Size(min = 10, max = 15) String phone, Long academyId, Long positionId, Long facultyId) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.academyId = academyId;
        this.positionId = positionId;
        this.facultyId = facultyId;
    }

    //    @NotNull @NotBlank
//    private Map<String, Object> map;
//
//    @NotNull @NotBlank
//    private String roles;
//
//    public TeacherUpdateDTO(@NotNull @NotBlank @Size(min = 10, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 200) String name, String gender, @NotNull @NotBlank Instant birthday, @Email @NotNull @NotBlank @Size(min = 10, max = 50) String email, @NotNull @NotBlank @Size(min = 10, max = 15) String phone, @NotNull @NotBlank Map<String, Object> map, @NotNull @NotBlank String roles) {
//        this.code = code;
//        this.name = name;
//        this.gender = gender;
//        this.birthday = birthday;
//        this.email = email;
//        this.phone = phone;
//        this.map = map;
//        this.roles = roles;
//    }
}
