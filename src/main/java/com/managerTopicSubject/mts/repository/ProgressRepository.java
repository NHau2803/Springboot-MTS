package com.managerTopicSubject.mts.repository;

import com.managerTopicSubject.mts.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByTopicId(Long id);

}
