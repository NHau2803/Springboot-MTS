package com.managerTopicSubject.mts.model.midLevel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.managerTopicSubject.mts.model.Student;
import com.managerTopicSubject.mts.model.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "students_teams", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id","team_id"})
})
@Data
@NoArgsConstructor
public class StudentTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    @Column(name="time_join", nullable = false)
    private Date timeJoin;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public StudentTeam(Date timeJoin, Student student, Team team) {
        this.timeJoin = timeJoin;
        this.student = student;
        this.team = team;
    }
}
