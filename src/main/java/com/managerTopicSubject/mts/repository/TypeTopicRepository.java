package com.managerTopicSubject.mts.repository;

import com.managerTopicSubject.mts.model.TypeTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTopicRepository extends JpaRepository<TypeTopic, Long> {
}
