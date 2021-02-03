package com.managerTopicSubject.mts.model;

import com.managerTopicSubject.mts.model.enumModel.RoleNameModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank @Size(min = 1, max=20)
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", unique = true, nullable = false)
    private RoleNameModel name;

    public Role(@NotNull @NotBlank @Size(min = 1, max = 20) String code, RoleNameModel name) {
        this.code = code;
        this.name = name;
    }
}
