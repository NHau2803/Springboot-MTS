package com.managerTopicSubject.mts.services;

import com.managerTopicSubject.mts.dto.select.TopicOfFacultyResponseDTO;
import com.managerTopicSubject.mts.dto.select.SelectResponseDTO;

import java.util.List;

public interface SelectResourceServices {

    List<SelectResponseDTO> getFacultyList();
    List<SelectResponseDTO> getAcademyList();
    List<SelectResponseDTO> getPositionList();
    List<SelectResponseDTO> getTypeTopicList();
    List<TopicOfFacultyResponseDTO> getAllTopicList();

}
