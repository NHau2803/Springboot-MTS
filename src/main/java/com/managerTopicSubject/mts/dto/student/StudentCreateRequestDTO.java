package com.managerTopicSubject.mts.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequestDTO {

    @NotNull @NotBlank @Size(min = 10, max = 20)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 200)
    private String name;

    @NotNull @NotBlank @Size(min = 1, max= 10)
    private String gender;

    @NotNull @NotBlank
    private String birthday;

    @Email @NotNull @NotBlank @Size(min = 10, max = 50)
    private String email;

    @NotNull @NotBlank @Size(min = 10, max = 15)
    private String phone;

    @Id
    private Long facultyId;

    //****************************************************//

    @NotNull @NotBlank @Size(min = 5, max = 50)
    private String username;

    @NotNull @NotBlank
    private String password;

    //status = active
    //roles = student

}
