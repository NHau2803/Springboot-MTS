package com.managerTopicSubject.mts.dto.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@NoArgsConstructor
public class TeacherUpdateRequestDTO {
    @Id
    private Long id;

    @NotNull @NotBlank @Size(min = 10, max = 20)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    private String gender;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private String birthday;

    @Email
    @NotNull @NotBlank @Size(min = 10, max = 50)
    private String email;

    @NotNull @NotBlank @Size(min = 10, max = 15)
    private String phone;

    @NotNull @NotBlank
    private Map<String, Object> map;

    @NotNull @NotBlank
    private String roles;

    public TeacherUpdateRequestDTO(@NotNull @NotBlank @Size(min = 10, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 200) String name, String gender, String birthday, @Email @NotNull @NotBlank @Size(min = 10, max = 50) String email, @NotNull @NotBlank @Size(min = 10, max = 15) String phone, @NotNull @NotBlank Map<String, Object> map, @NotNull @NotBlank String roles) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.map = map;
        this.roles = roles;
    }
}
