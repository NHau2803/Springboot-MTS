package com.managerTopicSubject.mts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;


@Entity
@Table(name = "progress")
@Data
@NoArgsConstructor
public class Progress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @NotNull @NotBlank
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public Progress(@NotNull @NotBlank Instant startTime, @NotNull @NotBlank Instant endTime, @NotNull @NotBlank String content, Topic topic) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.content = content;
        this.topic = topic;
    }
}
