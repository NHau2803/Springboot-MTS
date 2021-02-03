package com.managerTopicSubject.mts.service;

import com.managerTopicSubject.mts.dto.teacher.TeacherCreateRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherInfoRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherSearchRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Teacher;

import java.util.List;

public interface TeacherResourceServices {
    Teacher create(TeacherCreateRequestDTO dto);
    List<TeacherSearchRequestDTO> search();
    Teacher update(TeacherUpdateRequestDTO dto);
    TeacherUpdateRequestDTO find(Long id);
    TeacherInfoRequestDTO info(Long id);
    Boolean delete(Long id);
}
