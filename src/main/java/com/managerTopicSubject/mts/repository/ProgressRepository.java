package com.managerTopicSubject.mts.repository;

import com.managerTopicSubject.mts.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

}
