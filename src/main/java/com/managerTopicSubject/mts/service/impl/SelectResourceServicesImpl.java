package com.managerTopicSubject.mts.service.impl;

import com.managerTopicSubject.mts.dto.select.TopicOfFacultyResponseDTO;
import com.managerTopicSubject.mts.dto.select.SelectResponseDTO;
import com.managerTopicSubject.mts.model.*;
import com.managerTopicSubject.mts.repository.*;
import com.managerTopicSubject.mts.service.SelectResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SelectResourceServicesImpl implements SelectResourceServices {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private AcademyRepository academyRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private TypeTopicRepository typeTopicRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Override
    @Transactional
    public List<SelectResponseDTO> getFacultyList() {
        List<SelectResponseDTO> facultiesResult = new ArrayList<>();
        List<Faculty> faculties = facultyRepository.findAll();
        for(Faculty faculty : faculties){
            facultiesResult.add(
                    new SelectResponseDTO(faculty.getId(), faculty.getName())
            );
        }
        return facultiesResult;
    }

    @Override
    public List<SelectResponseDTO> getAcademyList() {
        List<SelectResponseDTO> academiesResult = new ArrayList<>();
        List<Academy> academies =  academyRepository.findAll();
        for(Academy academy : academies) {
            academiesResult.add(
                    new SelectResponseDTO(academy.getId(), academy.getName())
            );
        }
        return academiesResult;
    }

    @Override
    public List<SelectResponseDTO> getPositionList() {
        List<SelectResponseDTO> positionsResult = new ArrayList<>();
        List<Position> positions =  positionRepository.findAll();
        for(Position position : positions) {
            positionsResult.add(
                    new SelectResponseDTO(position.getId(), position.getName())
            );
        }
        return positionsResult;
    }

    @Override
    public List<SelectResponseDTO> getTypeTopicList() {
        List<SelectResponseDTO> positionsResult = new ArrayList<>();
        List<TypeTopic> typeTopics =  typeTopicRepository.findAll();
        for(TypeTopic typeTopic : typeTopics) {
            positionsResult.add(
                    new SelectResponseDTO(typeTopic.getId(), typeTopic.getName())
            );
        }
        return positionsResult;
    }

    @Override
    public List<TopicOfFacultyResponseDTO> getAllTopicList() {
        List<TopicOfFacultyResponseDTO> topicsResult = new ArrayList<>();
        List<Faculty> faculties = facultyRepository.findAll();
        for(Faculty faculty : faculties){
            List<Topic> topics = topicRepository.findByFacultyId(faculty.getId());
            List<Map<String, Object>> list = new ArrayList<>();
            for(Topic topic : topics){
                Map<String, Object> map = new HashMap<>();
                map.put("topicId",topic.getId());
                map.put("topicName",topic.getName());
                list.add(map);
            }
            topicsResult.add(
                    new TopicOfFacultyResponseDTO(faculty.getName(), list)
            );

        }
        return topicsResult;
    }
}
