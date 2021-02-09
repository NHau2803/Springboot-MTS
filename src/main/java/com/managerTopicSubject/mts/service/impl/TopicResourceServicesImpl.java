package com.managerTopicSubject.mts.service.impl;

import com.managerTopicSubject.mts.dto.topic.*;
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
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    @Transactional
    public Topic create(TopicCreateRequestDTO dto) {
        Topic topic = new Topic();
        topic.setStatus(StatusModel.DOING);
        topic.setCode(dto.getCode());
        topic.setName(dto.getName());
        topic.setStartTime(
                functionResourceServices.changeISOToInstant(dto.getStartTime())
        );
        topic.setEndTime(
                functionResourceServices.changeISOToInstant(dto.getEndTime())
        );
        Optional<Teacher> teacherModel = teacherRepository.findById(dto.getTeacherId());
        if(teacherModel.isPresent()){
            topic.setTeacher(teacherModel.get());
        }
        Optional<Faculty> faculty = facultyRepository.findById(dto.getFacultyId());
        if(faculty.isPresent()){
            topic.setFaculty(faculty.get());
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
                functionResourceServices.changeISOToInstant(dto.getStartTime())
        );
        topicUpdate.setEndTime(
                functionResourceServices.changeISOToInstant(dto.getEndTime())
        );

        Optional<Teacher> teacherModel = teacherRepository.findById(dto.getTeacherId());
        if (teacherModel.isPresent()) {
            topicUpdate.setTeacher(teacherModel.get());
        }
        Optional<TypeTopic> typeTopicModel = typeTopicRepository.findById(dto.getTypeTopicId());
        if (typeTopicModel.isPresent()) {
            topicUpdate.setTypeTopic(typeTopicModel.get());
        }
        Optional<Faculty> faculty = facultyRepository.findById(dto.getFacultyId());
        if (faculty.isPresent()) {
            topicUpdate.setFaculty(faculty.get());
        }

        Topic theTopic = topicRepository.save(topicUpdate);
        /********************************************************************************************/
        List<DeadlineResponseDTO> deadlines = dto.getDeadlines();
        for(DeadlineResponseDTO deadline : deadlines){
            Progress progress = new Progress();
            progress.setId(deadline.getId());
            progress.setStartTime(
                    functionResourceServices.changeISOToInstant(deadline.getStartTime())
            );
            progress.setEndTime(
                    functionResourceServices.changeISOToInstant(deadline.getEndTime())
            );
            progress.setContent(deadline.getContent());
            progress.setTopic(theTopic);
            progressRepository.save(progress);
        }


//        try {
//            String[] deadlines = dto.getDeadlines();
//            for (String deadLine : deadlines) {
//                Progress progress = functionResourceServices.changeStringToProgressModelUpdate(deadLine);
//                progress.setTopic(theTopic);
//                progressRepository.save(progress);
//            }
//        } catch (Exception e) {
//            System.out.println("Save progress error: " + e);
//        }
        return topicUpdate;

    }

    @Override
    @Transactional
    public TopicUpdateRequestDTO find(Long id) {
        Optional<Topic> topicResult = topicRepository.findById(id);
        if(!topicResult.isPresent()){
            return null;
        }
        Topic topic = topicResult.get();
        TopicUpdateRequestDTO dto = new TopicUpdateRequestDTO();
        dto.setId(topic.getId());
        dto.setCode(topic.getCode());
        dto.setName(topic.getName());
        dto.setStartTime(
                functionResourceServices.changeInstantToString(topic.getStartTime())
        );
        dto.setEndTime(
                functionResourceServices.changeInstantToString(topic.getEndTime())
        );
        dto.setFacultyId(topic.getFaculty().getId());
        dto.setTeacherId(topic.getTeacher().getId());
        dto.setTypeTopicId(topic.getTypeTopic().getId());
        List<Progress> progresses = progressRepository.findByTopicId(topic.getId());
        List<DeadlineResponseDTO> deadlines = new ArrayList<>();
        for(Progress progress : progresses){
            DeadlineResponseDTO deadline = new DeadlineResponseDTO();
            deadline.setId(progress.getId());
            deadline.setStartTime(
                    functionResourceServices.changeInstantToString(progress.getStartTime())
            );
            deadline.setEndTime(
                    functionResourceServices.changeInstantToString(progress.getEndTime())
            );
            deadline.setContent(progress.getContent());
            deadlines.add(deadline);
        }
        dto.setDeadlines(deadlines);

        return dto;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Topic> topicResult = topicRepository.findById(id);
        if(!topicResult.isPresent()){
            return false;
        }
        Topic topic = topicResult.get();
        topic.setStatus(StatusModel.DELETED);
        topicRepository.save(topic);
        return true;
    }

    @Override
    @Transactional
    public List<TopicSearchResponseDTO> search() {
        List<Topic> topicList = topicRepository.findAll();
        List<TopicSearchResponseDTO> listResult = new ArrayList<>();
        for(Topic topic : topicList){
            TopicSearchResponseDTO dto = new TopicSearchResponseDTO();
            dto.setId(topic.getId());
            dto.setName(topic.getName());
            dto.setStartTime(
                    functionResourceServices.changeInstantToString(topic.getStartTime())
            );
            dto.setEndTime(
                    functionResourceServices.changeInstantToString(topic.getEndTime())
            );
//            Map<String, Object> teacher = new HashMap<>();
//            teacher.put("teacherId", topic.getTeacher().getId());
//            teacher.put("teacherName", topic.getTeacher().getName());
            dto.setTeacherName(topic.getTeacher().getName());
            /********************************************************************************************/
            List<Team> teams = teamRepository.findByTopicId(topic.getId());
            List<String> teamNames = new ArrayList<>();
            for(Team team : teams){
                teamNames.add(team.getName());
            }
            dto.setTeamNames(teamNames);
            dto.setStatus(topic.getStatus().name());
            listResult.add(dto);
        }
        return listResult;
    }


}
