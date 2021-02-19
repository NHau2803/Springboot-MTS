package com.managerTopicSubject.mts.services.impl;

import com.managerTopicSubject.mts.dto.team.*;
import com.managerTopicSubject.mts.model.Progress;
import com.managerTopicSubject.mts.model.Student;
import com.managerTopicSubject.mts.model.Team;
import com.managerTopicSubject.mts.model.Topic;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import com.managerTopicSubject.mts.model.midLevel.StudentTeam;
import com.managerTopicSubject.mts.repository.ProgressRepository;
import com.managerTopicSubject.mts.repository.StudentRepository;
import com.managerTopicSubject.mts.repository.TeamRepository;
import com.managerTopicSubject.mts.repository.TopicRepository;
import com.managerTopicSubject.mts.repository.midLevel.StudentTeamRepository;
import com.managerTopicSubject.mts.services.FunctionResourceServices;
import com.managerTopicSubject.mts.services.TeamResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TeamResourceServicesImpl implements TeamResourceServices {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private FunctionResourceServices functionResourceServices;
    @Autowired
    private StudentTeamRepository studentTeamRepository;
    @Autowired
    private ProgressRepository progressRepository;

    @Override
    @Transactional
    public Team create(TeamCreateRequestDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        Optional<Topic> topicModel = topicRepository.findById(dto.getTopicId());
        if(topicModel.isPresent()) {
            team.setTopic(topicModel.get());
        }
        team.setStatus(StatusModel.ACTIVE);
        teamRepository.save(team);
        return team;
    }

    @Override
    @Transactional
    public Boolean join(JoinTeamRequestDTO dto) {
        try {
            StudentTeam studentTeam = new StudentTeam();
            Optional<Student> studentModel = studentRepository.findById(dto.getStudentId());
            if (studentModel.isPresent()) {
                studentTeam.setStudent(studentModel.get());
            }
            Optional<Team> teamModel = teamRepository.findById(dto.getTeamId());
            if (teamModel.isPresent()) {
                studentTeam.setTeam(teamModel.get());
            }
            studentTeam.setTimeJoin(
                    functionResourceServices.changeISOToInstant(dto.getTimeJoin())
            );
            studentTeamRepository.save(studentTeam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional
    public List<TeamSearchResponseDTO> search() {
        List<Team> teams = teamRepository.findAll();
        List<TeamSearchResponseDTO> listResult = new ArrayList<>();
        for(Team team : teams){
            TeamSearchResponseDTO dto = new TeamSearchResponseDTO();
            dto.setId(team.getId());
            dto.setName(team.getName());
            dto.setFacultyName(team.getTopic().getFaculty().getName());
            dto.setTopicName(team.getTopic().getName());

            List<StudentTeam> studentTeams = studentTeamRepository.findByTeamId(team.getId());
            int count = studentTeams.size();
            dto.setStudentTotal(count);
            dto.setStatus(team.getStatus().name());
            listResult.add(dto);
        }
        return listResult;
    }

    @Override
    @Transactional
    public TeamUpdateDTO find(Long id) {
        Optional<Team> teamResult = teamRepository.findById(id);
        if(!teamResult.isPresent()){
            return null;
        }
        Team team = teamResult.get();
        TeamUpdateDTO dto = new TeamUpdateDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setTopicId(team.getTopic().getId());
        dto.setTopicName(team.getTopic().getName());
        return dto;
    }

    @Override
    @Transactional
    public Team update(TeamUpdateDTO dto) {
        Optional<Team> teamResult = teamRepository.findById(dto.getId());
        if(!teamResult.isPresent()){
            return null;
        }
        Team teamUpdate = teamResult.get();
        teamUpdate.setName(dto.getName());
//        Long topicId = ((Number)dto.getTopicId().get("topicId")).longValue();
        Optional<Topic> topic = topicRepository.findById(dto.getTopicId());
        if(topic.isPresent()){
            teamUpdate.setTopic(topic.get());
        }
        teamUpdate.setStatus(StatusModel.ACTIVE);
        return teamUpdate;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Team> teamResult = teamRepository.findById(id);
        if(!teamResult.isPresent()){
            return false;
        }
        Team team = teamResult.get();
        team.setStatus(StatusModel.DELETED);
        teamRepository.save(team);
        return true;
    }

    @Override
    public Boolean submitLink(SubmitRequestDTO dto) {
        Optional<Team> teamResult = teamRepository.findById(dto.getId());
        if(!teamResult.isPresent()){
            return false;
        }
        Team team = teamResult.get();
        team.setLink(dto.getLink());
        teamRepository.save(team);
        return true;
    }

    @Override
    @Transactional
    public List<TeamSearchResponseDTO> searchByTopicId(Long id) {
        List<Team> teams = teamRepository.findByTopicId(id);
        List<TeamSearchResponseDTO> listResult = new ArrayList<>();
        for(Team team : teams){
            TeamSearchResponseDTO dto = new TeamSearchResponseDTO();
            dto.setId(team.getId());
            dto.setName(team.getName());
            dto.setFacultyName(team.getTopic().getFaculty().getName());
            dto.setTopicName(team.getTopic().getName());

            List<StudentTeam> studentTeams = studentTeamRepository.findByTeamId(team.getId());
            int count = studentTeams.size();
            dto.setStudentTotal(count);
            dto.setStatus(team.getStatus().name());
            listResult.add(dto);
        }
        return listResult;
    }

    @Override
    @Transactional
    public ViewTeamResponseDTO viewTeam(Long id) {
        Optional<Team> teamResult = teamRepository.findById(id);
        ViewTeamResponseDTO dto = new ViewTeamResponseDTO();
        dto.setId(id);
        dto.setName(teamResult.get().getName());
        dto.setStatus(teamResult.get().getStatus().name());
        dto.setLink(teamResult.get().getLink());
        dto.setPoint(teamResult.get().getPoint());
        dto.setTopicName(teamResult.get().getTopic().getName());

        List<StudentTeam> studentTeams = studentTeamRepository.findByTeamId(id);
        List<Map<String, Object>> students = new ArrayList<>();
        int count = 1;
        for(StudentTeam studentTeam : studentTeams){
            Map<String, Object> map = new HashMap<>();
            map.put("studentName",studentTeam.getStudent().getName());
            map.put("timeJoin",
                    functionResourceServices.changeInstantToString(
                            studentTeam.getTimeJoin()
                    ));
            map.put("count", count++);
            students.add(map);
        }
        dto.setStudents(students);

        count=1;
        Long topicId = teamResult.get().getTopic().getId();
        List<Progress> progresses = progressRepository.findByTopicId(topicId);
        List<Map<String, Object>> deadlines = new ArrayList<>();
        for(Progress progress : progresses){
            Map<String, Object> map = new HashMap<>();
            map.put("startDeadline",
                    functionResourceServices.changeInstantToString(
                            progress.getStartTime()
                    ));
            map.put("endDeadline",
                    functionResourceServices.changeInstantToString(
                            progress.getEndTime()
                    ));
            map.put("content", progress.getContent());
            map.put("count", count++);
            deadlines.add(map);
        }
        dto.setDeadlines(deadlines);

        return dto;
    }


}
