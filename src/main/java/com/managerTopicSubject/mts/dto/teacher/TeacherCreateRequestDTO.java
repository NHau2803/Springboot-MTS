package com.managerTopicSubject.mts.dto.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreateRequestDTO {
    @NotNull
    @NotBlank
    @Size(min = 10, max = 20)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 200)
    private String name;

    @NotNull @NotBlank @Size(min = 1, max= 10)
    private String gender;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
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

    //****************************************************//

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String username;

    @NotNull @NotBlank
    private String password;

    //status = active
    //roles = teacher
}
