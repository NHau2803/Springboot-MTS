package com.managerTopicSubject.mts.repository;

import com.managerTopicSubject.mts.model.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {
}
