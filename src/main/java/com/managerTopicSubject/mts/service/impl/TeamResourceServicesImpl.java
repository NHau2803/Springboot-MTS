package com.managerTopicSubject.mts.service.impl;

import com.managerTopicSubject.mts.dto.team.JoinTeamRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamCreateRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamSearchRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Student;
import com.managerTopicSubject.mts.model.Team;
import com.managerTopicSubject.mts.model.Topic;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import com.managerTopicSubject.mts.model.midLevel.StudentTeam;
import com.managerTopicSubject.mts.repository.StudentRepository;
import com.managerTopicSubject.mts.repository.TeamRepository;
import com.managerTopicSubject.mts.repository.TopicRepository;
import com.managerTopicSubject.mts.repository.midLevel.StudentTeamRepository;
import com.managerTopicSubject.mts.service.FunctionResourceServices;
import com.managerTopicSubject.mts.service.TeamResourceServices;
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
                    functionResourceServices.CovertStringToTime(dto.getTimeJoin())
            );
            studentTeamRepository.save(studentTeam);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    @Transactional
    public List<TeamSearchRequestDTO> search() {
        List<Team> teams = teamRepository.findAll();
        List<TeamSearchRequestDTO> listResult = new ArrayList<>();
        for(Team team : teams){
            TeamSearchRequestDTO dto = new TeamSearchRequestDTO();
            dto.setId(team.getId());
            dto.setName(team.getName());
            dto.setTopicName(team.getTopic().getName());
            List<StudentTeam> studentTeams = studentTeamRepository.findByTeamId(team.getId());
            int count = studentTeams.size();
            dto.setStudentTotal(count);
            listResult.add(dto);
        }
        return listResult;
    }

    @Override
    @Transactional
    public TeamUpdateRequestDTO find(Long id) {
        Optional<Team> teamModelResult = teamRepository.findById(id);
        if(!teamModelResult.isPresent()){
            return null;
        }
        Team team = teamModelResult.get();
        TeamUpdateRequestDTO dto = new TeamUpdateRequestDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        Map<String, Object> topicId = new HashMap<>();
        topicId.put("topicId", team.getTopic().getId());
        topicId.put("topicName", team.getTopic().getName());
        dto.setTopicId(topicId);
        return dto;
    }

    @Override
    @Transactional
    public Team update(TeamUpdateRequestDTO dto) {
        Optional<Team> teamModelResult = teamRepository.findById(dto.getId());
        if(!teamModelResult.isPresent()){
            return null;
        }
        Team teamUpdate = teamModelResult.get();
        teamUpdate.setName(dto.getName());
        Long topicId = ((Number)dto.getTopicId().get("topicId")).longValue();
        Optional<Topic> topicModel = topicRepository.findById(topicId);
        if(topicModel.isPresent()){
            teamUpdate.setTopic(topicModel.get());
        }
        teamUpdate.setStatus(StatusModel.ACTIVE);
        return teamUpdate;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Team> teamModelResult = teamRepository.findById(id);
        if(!teamModelResult.isPresent()){
            return false;
        }
        Team team = teamModelResult.get();
        team.setStatus(StatusModel.DELETED);
        teamRepository.save(team);
        return true;
    }


}
