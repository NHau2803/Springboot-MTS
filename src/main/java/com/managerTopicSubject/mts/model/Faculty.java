package com.managerTopicSubject.mts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "faculty")
@Data
@NoArgsConstructor
public class Faculty {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank @Size(min = 1, max = 20)
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Faculty(@NotNull @NotBlank @Size(min = 1, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 50) String name) {
        this.code = code;
        this.name = name;
    }
}
