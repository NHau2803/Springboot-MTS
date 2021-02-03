package com.managerTopicSubject.mts.model.midLevel;

import com.managerTopicSubject.mts.model.Progress;
import com.managerTopicSubject.mts.model.Team;
import com.managerTopicSubject.mts.model.Topic;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "progress_teams", uniqueConstraints ={
        @UniqueConstraint(columnNames = {"team_id", "progress_id"})
})
@Data
@NoArgsConstructor
public class ProgressTeam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "progress_id", nullable = false)
    private Progress progress;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public ProgressTeam(Team team, Progress progress, Topic topic) {
        this.team = team;
        this.progress = progress;
        this.topic = topic;
    }
}
