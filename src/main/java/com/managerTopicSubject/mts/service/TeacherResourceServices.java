package com.managerTopicSubject.mts.service;

import com.managerTopicSubject.mts.dto.teacher.TeacherCreateRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherInfoResponseDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherSearchResponseDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherUpdateDTO;
import com.managerTopicSubject.mts.model.Teacher;

import java.util.List;

public interface TeacherResourceServices {
    Teacher create(TeacherCreateRequestDTO dto);
    List<TeacherSearchResponseDTO> search();
    Teacher update(TeacherUpdateDTO dto);
    TeacherUpdateDTO find(Long id);
    TeacherInfoResponseDTO info(Long id);
    Boolean delete(Long id);
}
