package com.managerTopicSubject.mts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.managerTopicSubject.mts.model.enumModel.GenderModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
public class Teacher {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "academy_id", nullable = false)
    private Academy academy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Teacher(@NotNull @NotBlank @Size(min = 10, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 50) String name, GenderModel gender, @NotNull @NotBlank Instant birthday, @Email @NotNull @NotBlank @Size(min = 10, max = 50) String email, @NotNull @NotBlank @Size(min = 10, max = 15) String phone, Academy academy, Position position, Faculty faculty, User user) {
        this.code = code;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.academy = academy;
        this.position = position;
        this.faculty = faculty;
        this.user = user;
    }
}
