package com.managerTopicSubject.mts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusModel status;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull @NotBlank @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();

    public User(StatusModel status, @NotNull @NotBlank @Size(min = 5, max = 50) String username, @NotNull @NotBlank String password, Set<Role> roles) {
        this.status = status;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
