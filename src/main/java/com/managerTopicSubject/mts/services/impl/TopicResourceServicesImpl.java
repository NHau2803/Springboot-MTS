package com.managerTopicSubject.mts.services.impl;

import com.managerTopicSubject.mts.dto.topic.*;
import com.managerTopicSubject.mts.model.*;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import com.managerTopicSubject.mts.model.midLevel.StudentTeam;
import com.managerTopicSubject.mts.repository.*;
import com.managerTopicSubject.mts.repository.midLevel.StudentTeamRepository;
import com.managerTopicSubject.mts.services.FunctionResourceServices;
import com.managerTopicSubject.mts.services.TopicResourceServices;
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
    @Autowired
    private StudentTeamRepository studentTeamRepository;

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
        Optional<Teacher> teacher = teacherRepository.findById(dto.getTeacherId());
        if(teacher.isPresent()){
            topic.setTeacher(teacher.get());
        }
        Optional<Faculty> faculty = facultyRepository.findById(dto.getFacultyId());
        if(faculty.isPresent()){
            topic.setFaculty(faculty.get());
        }
        Optional<TypeTopic> typeTopic = typeTopicRepository.findById(dto.getTypeTopicId());
        if(typeTopic.isPresent()){
            topic.setTypeTopic(typeTopic.get());
        }
        Topic theTopic = topicRepository.save(topic);
        /********************************************************************************************/

        List<DeadlineRequestDTO> deadlines = dto.getDeadlines();
        for(DeadlineRequestDTO deadline : deadlines){
            Progress progress = new Progress();
            progress.setTopic(theTopic);
            progress.setStartTime(
                    functionResourceServices.changeISOToInstant(deadline.getStartDeadline())
            );
            progress.setEndTime(
                    functionResourceServices.changeISOToInstant(deadline.getEndDeadline())
            );
            progress.setContent(deadline.getContent());
            progressRepository.save(progress);
        }

        return topic;
    }

    @Override
    @Transactional
    public Topic update(TopicUpdateRequestDTO dto) {
        Optional<Topic> topicResult = topicRepository.findById(dto.getId());
        if(!topicResult.isPresent()){
            return null;
        }
        Topic topicUpdate = topicResult.get();
        topicUpdate.setCode(dto.getCode());
        topicUpdate.setName(dto.getName());
        topicUpdate.setStartTime(
                functionResourceServices.changeISOToInstant(dto.getStartTime())
        );
        topicUpdate.setEndTime(
                functionResourceServices.changeISOToInstant(dto.getEndTime())
        );

        Optional<Teacher> teacher = teacherRepository.findById(dto.getTeacherId());
        if (teacher.isPresent()) {
            topicUpdate.setTeacher(teacher.get());
        }
        Optional<TypeTopic> typeTopic = typeTopicRepository.findById(dto.getTypeTopicId());
        if (typeTopic.isPresent()) {
            topicUpdate.setTypeTopic(typeTopic.get());
        }
        Optional<Faculty> faculty = facultyRepository.findById(dto.getFacultyId());
        if (faculty.isPresent()) {
            topicUpdate.setFaculty(faculty.get());
        }

        Topic theTopic = topicRepository.save(topicUpdate);
        /********************************************************************************************/


        Map<String, Object> deadlines = functionResourceServices.HandleDeadlines(
                topicUpdate.getId(), dto.getDeadlines());


        List<DeadlineDTO> deadlinesUpdate = (List<DeadlineDTO>) deadlines.get("deadlinesUpdate");
        for(DeadlineDTO deadlineUpdate : deadlinesUpdate){
            Progress progress = new Progress();
            progress.setId(deadlineUpdate.getId());
            progress.setStartTime(
                    functionResourceServices.changeISOToInstant(deadlineUpdate.getStartDeadline())
            );
            progress.setEndTime(
                    functionResourceServices.changeISOToInstant(deadlineUpdate.getEndDeadline())
            );
            progress.setContent(deadlineUpdate.getContent());
            progress.setTopic(theTopic);
            progressRepository.save(progress);
        }


        Set<Long> deleteProgressIds = (Set<Long>) deadlines.get("deleteProgressIds");
        for (Long id : deleteProgressIds){
            Optional<Progress> progressResults = progressRepository.findById(id);
            if(progressResults.isPresent()){
                progressRepository.delete(progressResults.get());
            }
        }

        List<DeadlineRequestDTO> deadlinesCreate = (List<DeadlineRequestDTO>) deadlines.get("deadlinesCreate");
        for(DeadlineRequestDTO deadline : deadlinesCreate){
            Progress progress = new Progress();
            progress.setStartTime(
                    functionResourceServices.changeISOToInstant(deadline.getStartDeadline())
            );
            progress.setEndTime(
                    functionResourceServices.changeISOToInstant(deadline.getEndDeadline())
            );
            progress.setContent(deadline.getContent());
            progress.setTopic(theTopic);
            progressRepository.save(progress);
        }

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
        List<DeadlineDTO> deadlines = new ArrayList<>();
        for(Progress progress : progresses){
            DeadlineDTO deadline = new DeadlineDTO();
            deadline.setId(progress.getId());
            deadline.setStartDeadline(
                    functionResourceServices.changeInstantToString(progress.getStartTime())
            );
            deadline.setEndDeadline(
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
            dto.setFacultyName(topic.getFaculty().getName());
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

    @Override
    public List<TopicSearchResponseDTO> searchByStudentId(Long id) {
        List<StudentTeam> studentTeams = studentTeamRepository.findByStudentId(id);
        List<Topic> topicList = new ArrayList<>();
        for(StudentTeam studentTeam : studentTeams){
            topicList.add(studentTeam.getTeam().getTopic());
        }

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
            dto.setFacultyName(topic.getFaculty().getName());
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
