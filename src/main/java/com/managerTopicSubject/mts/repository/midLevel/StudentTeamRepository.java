package com.managerTopicSubject.mts.repository.midLevel;

import com.managerTopicSubject.mts.model.midLevel.StudentTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentTeamRepository extends JpaRepository<StudentTeam, Long> {
    List<StudentTeam> findByStudentId(Long id);
    List<StudentTeam> findByTeamId(Long id);

}
