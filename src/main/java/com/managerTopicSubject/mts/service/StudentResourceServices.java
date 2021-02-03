package com.managerTopicSubject.mts.service;

import com.managerTopicSubject.mts.dto.student.StudentCreateRequestDTO;
import com.managerTopicSubject.mts.dto.student.StudentInfoRequestDTO;
import com.managerTopicSubject.mts.dto.student.StudentSearchRequestDTO;
import com.managerTopicSubject.mts.dto.student.StudentUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Student;

import java.util.List;

public interface StudentResourceServices {
    Student create(StudentCreateRequestDTO dto);
    List<StudentSearchRequestDTO> search();
    Student update(StudentUpdateRequestDTO dto);
    StudentUpdateRequestDTO find(Long id);
    StudentInfoRequestDTO info(Long id);
    Boolean delete(Long id);

}
