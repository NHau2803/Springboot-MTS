package com.managerTopicSubject.mts.services;

import com.managerTopicSubject.mts.dto.topic.TopicCreateRequestDTO;
import com.managerTopicSubject.mts.dto.topic.TopicSearchResponseDTO;
import com.managerTopicSubject.mts.dto.topic.TopicUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Topic;

import java.util.List;

public interface TopicResourceServices {

    Topic create(TopicCreateRequestDTO dto);

    Topic update(TopicUpdateRequestDTO dto);

    TopicUpdateRequestDTO find(Long id);

    Boolean delete(Long id);

    List<TopicSearchResponseDTO> search();

    List<TopicSearchResponseDTO> searchByStudentId(Long id);


}
