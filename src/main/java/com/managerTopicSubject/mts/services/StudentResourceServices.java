package com.managerTopicSubject.mts.services;

import com.managerTopicSubject.mts.dto.student.StudentCreateRequestDTO;
import com.managerTopicSubject.mts.dto.student.StudentInfoResponseDTO;
import com.managerTopicSubject.mts.dto.student.StudentSearchResponseDTO;
import com.managerTopicSubject.mts.dto.student.StudentUpdateDTO;
import com.managerTopicSubject.mts.model.Student;

import java.util.List;

public interface StudentResourceServices {
    Student create(StudentCreateRequestDTO dto);
    List<StudentSearchResponseDTO> search();
    Student update(StudentUpdateDTO dto);
    StudentUpdateDTO find(Long id);
    StudentInfoResponseDTO info(Long id);
    Boolean delete(Long id);

}
