package com.managerTopicSubject.mts.dto.select;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicOfFacultyResponseDTO {

    private String facultyName;
    private List<Map<String, Object>> topicList;
}
