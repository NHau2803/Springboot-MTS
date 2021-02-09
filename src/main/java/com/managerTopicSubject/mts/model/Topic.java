package com.managerTopicSubject.mts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "topic")
@Data
@NoArgsConstructor
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusModel status;

    @NotNull @NotBlank @Size(min = 1, max = 20)
    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 100)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_topic_id", nullable = false)
    private TypeTopic typeTopic;

    public Topic(StatusModel status, @NotNull @NotBlank @Size(min = 1, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 100) String name, @NotNull @NotBlank Instant startTime, @NotNull @NotBlank Instant endTime, Teacher teacher, Faculty faculty, TypeTopic typeTopic) {
        this.status = status;
        this.code = code;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teacher = teacher;
        this.faculty = faculty;
        this.typeTopic = typeTopic;
    }
}
