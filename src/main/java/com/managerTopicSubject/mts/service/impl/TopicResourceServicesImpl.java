package com.managerTopicSubject.mts.service.impl;

import com.managerTopicSubject.mts.dto.topic.TopicCreateRequestDTO;
import com.managerTopicSubject.mts.dto.topic.TopicSearchRequestDTO;
import com.managerTopicSubject.mts.dto.topic.TopicUpdateRequestDTO;
import com.managerTopicSubject.mts.model.*;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import com.managerTopicSubject.mts.repository.*;
import com.managerTopicSubject.mts.service.FunctionResourceServices;
import com.managerTopicSubject.mts.service.TopicResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TopicResourceServicesImpl implements TopicResourceServices {

    @Autowired
    private FunctionResourceServices functionResourceServices;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TypeTopicRepository typeTopicRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    @Transactional
    public Topic create(TopicCreateRequestDTO dto) {

        Topic topic = new Topic();
        topic.setStatus(StatusModel.DOING);
        topic.setCode(dto.getCode());
        topic.setName(dto.getName());
        topic.setStartTime(
                functionResourceServices.CovertStringToTime(dto.getStartTime())
        );
        topic.setEndTime(
                functionResourceServices.CovertStringToTime(dto.getEndTime())
        );
        Optional<Teacher> teacherModel = teacherRepository.findById(dto.getTeacherId());
        if(teacherModel.isPresent()){
            topic.setTeacher(teacherModel.get());
        }
        Optional<TypeTopic> typeTopicModel = typeTopicRepository.findById(dto.getTypeTopicId());
        if(typeTopicModel.isPresent()){
            topic.setTypeTopic(typeTopicModel.get());
        }
        Topic theTopic = topicRepository.save(topic);
        /********************************************************************************************/

        try {
            String[] deadlines = dto.getDeadlines();
            for (String deadLine : deadlines) {
                Progress progress = functionResourceServices.changeStringToProgressModel(deadLine);
                progress.setTopic(theTopic);
                progressRepository.save(progress);
            }
        }catch (Exception e){
            System.out.println("Save progress error: "+e);
        }

        return topic;

    }

    @Override
    @Transactional
    public Topic update(TopicUpdateRequestDTO dto) {
        Optional<Topic> topicModelResult = topicRepository.findById(dto.getId());
        if(!topicModelResult.isPresent()){
            return null;
        }
        Topic topicUpdate = topicModelResult.get();
        topicUpdate.setCode(dto.getCode());
        topicUpdate.setName(dto.getName());
        topicUpdate.setStartTime(
                functionResourceServices.CovertStringToTime(dto.getStartTime())
        );
        topicUpdate.setEndTime(
                functionResourceServices.CovertStringToTime(dto.getEndTime())
        );
        Map<String, Object> map = dto.getMap();
        Long teacherId = ((Number) map.get("teacherId")).longValue();
        Optional<Teacher> teacherModel = teacherRepository.findById(teacherId);
        if (teacherModel.isPresent()) {
            topicUpdate.setTeacher(teacherModel.get());
        }
        Long typeTopicId = ((Number) map.get("typeTopicId")).longValue();
        Optional<TypeTopic> typeTopicModel = typeTopicRepository.findById(typeTopicId);
        if (typeTopicModel.isPresent()) {
            topicUpdate.setTypeTopic(typeTopicModel.get());
        }
        Topic theTopic = topicRepository.save(topicUpdate);
        /********************************************************************************************/

        try {
            String[] deadlines = dto.getDeadlines();
            for (String deadLine : deadlines) {
                Progress progress = functionResourceServices.changeStringToProgressModelUpdate(deadLine);
                progress.setTopic(theTopic);
                progressRepository.save(progress);
            }
        } catch (Exception e) {
            System.out.println("Save progress error: " + e);
        }
        return topicUpdate;

    }

    @Override
    @Transactional
    public TopicUpdateRequestDTO find(Long id) {
        Optional<Topic> topicModelResult = topicRepository.findById(id);
        if(!topicModelResult.isPresent()){
            return null;
        }
        Topic topic = topicModelResult.get();
        TopicUpdateRequestDTO dto = new TopicUpdateRequestDTO();
        dto.setId(topic.getId());
        dto.setCode(topic.getCode());
        dto.setName(topic.getName());
        dto.setStartTime(
                functionResourceServices.CovertTimeToString(topic.getStartTime())
        );
        dto.setEndTime(
                functionResourceServices.CovertTimeToString(topic.getEndTime())
        );
        Map<String, Object> map = dto.getMap();
        map.put("teacherId", topic.getTeacher().getId());
        map.put("teacherName", topic.getTeacher().getName());
        map.put("typeTopicId", topic.getTypeTopic().getId());
        map.put("typeTopicName", topic.getTypeTopic().getName());
        dto.setMap(map);
        return dto;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Topic> topicModelResult = topicRepository.findById(id);
        if(!topicModelResult.isPresent()){
            return false;
        }
        Topic topic = topicModelResult.get();
        topic.setStatus(StatusModel.DELETED);
        topicRepository.save(topic);
        return true;
    }

    @Override
    @Transactional
    public List<TopicSearchRequestDTO> search() {
        List<Topic> topicList = topicRepository.findAll();
        List<TopicSearchRequestDTO> listResult = new ArrayList<>();
        for(Topic topic : topicList){
            TopicSearchRequestDTO dto = new TopicSearchRequestDTO();
            dto.setId(topic.getId());
            dto.setName(topic.getName());
            dto.setStartTime(
                    functionResourceServices.CovertTimeToString(topic.getStartTime())
            );
            dto.setEndTime(
                    functionResourceServices.CovertTimeToString(topic.getEndTime())
            );
            Map<String, Object> teacher = new HashMap<>();
            teacher.put("teacherId", topic.getTeacher().getId());
            teacher.put("teacherName", topic.getTeacher().getName());
            dto.setTeacher(teacher);
            /********************************************************************************************/
            List<Team> teamModels = teamRepository.findByTopicId(topic.getId());
            List<String> teams = new ArrayList<>();
            for(Team team : teamModels){
                teams.add(team.getId()+"**"+ team.getName());
            }
            dto.setTeamList(teams);
            listResult.add(dto);
        }
        return listResult;
    }


}
