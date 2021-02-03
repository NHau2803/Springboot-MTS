package com.managerTopicSubject.mts.model;

import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusModel status;

    @NotNull @NotBlank @Size(min = 5, max = 50)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "point")
    private Float point;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Team(StatusModel status, @NotNull @NotBlank @Size(min = 5, max = 50) String name, String link, Float point, Topic topic) {
        this.status = status;
        this.name = name;
        this.link = link;
        this.point = point;
        this.topic = topic;
    }
}
