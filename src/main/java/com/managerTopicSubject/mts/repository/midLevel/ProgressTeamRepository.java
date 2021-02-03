package com.managerTopicSubject.mts.repository.midLevel;

import com.managerTopicSubject.mts.model.midLevel.ProgressTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressTeamRepository extends JpaRepository<ProgressTeam, Long> {
}
