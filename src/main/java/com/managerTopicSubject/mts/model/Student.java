package com.managerTopicSubject.mts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.managerTopicSubject.mts.model.enumModel.GenderModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "student")

@Data
@NoArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank @Size(min = 10, max = 20)
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private GenderModel gender;

    @Column(name = "birthday", nullable = false)
    private Instant birthday;

    @Email @NotNull @NotBlank @Size(min = 10, max = 50)
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull @NotBlank @Size(min = 10, max = 15)
    @Column(name = "phone", nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    public Student(@NotNull @NotBlank @Size(min = 10, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 50) String name, GenderModel gender, Instant birthday, @Email @NotNull @NotBlank @Size(min = 10, max = 50) String email, @NotNull @NotBlank @Size(min = 10, max = 15) String phone, Faculty faculty, User user) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.faculty = faculty;
        this.user = user;
    }
}
