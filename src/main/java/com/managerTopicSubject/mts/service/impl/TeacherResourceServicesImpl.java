package com.managerTopicSubject.mts.service.impl;

import com.managerTopicSubject.mts.dto.teacher.TeacherCreateRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherInfoResponseDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherSearchResponseDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherUpdateDTO;
import com.managerTopicSubject.mts.model.*;
import com.managerTopicSubject.mts.model.enumModel.RoleNameModel;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import com.managerTopicSubject.mts.repository.*;
import com.managerTopicSubject.mts.service.FunctionResourceServices;
import com.managerTopicSubject.mts.service.TeacherResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TeacherResourceServicesImpl implements TeacherResourceServices {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private FunctionResourceServices functionResourceServices;
    @Autowired
    private AcademyRepository academyRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Override
    @Transactional
    public Teacher create(TeacherCreateRequestDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setCode(dto.getCode());
        teacher.setName(dto.getName());
        teacher.setGender(
             functionResourceServices.changeGender(dto.getGender())
        );
        teacher.setBirthday(
                functionResourceServices.changeISOToInstant(dto.getBirthday())
        );
        teacher.setEmail(dto.getEmail());
        teacher.setPhone(dto.getPhone());
        Optional<Academy> academy = academyRepository.findById(dto.getAcademyId());
        if(academy.isPresent()){
            teacher.setAcademy(academy.get());
        }
        Optional<Position> position = positionRepository.findById(dto.getPositionId());
        if(position.isPresent()){
            teacher.setPosition(position.get());
        }
        Optional<Faculty> faculty = facultyRepository.findById(dto.getFacultyId());
        if(faculty.isPresent()){
            teacher.setFaculty(faculty.get());
        }
        /********************************************************************************************/
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setStatus(StatusModel.ACTIVE);
        user.setRoles(
                functionResourceServices.changeRoles(RoleNameModel.TEACHER.name())
        );

        User theUser = userRepository.save(user);
        teacher.setUser(theUser);
        teacherRepository.save(teacher);

        return teacher;
    }

    @Override
    @Transactional
    public List<TeacherSearchResponseDTO> search() {
        List<Teacher> teacherList = teacherRepository.findAll();
        List<TeacherSearchResponseDTO> listResult = new ArrayList<>();
        for(Teacher teacher : teacherList){
            TeacherSearchResponseDTO dto = new TeacherSearchResponseDTO();
            dto.setId(teacher.getId());
            dto.setCode(teacher.getCode());
            dto.setName(teacher.getName());
            dto.setAcademyName(teacher.getAcademy().getName());
            dto.setPositionName(teacher.getPosition().getName());
            dto.setFacultyName(teacher.getFaculty().getName());

            List<Topic> topics = topicRepository.findByTeacherId(teacher.getId());
            List<String> topicNames =  new ArrayList<>();
            for(Topic topic : topics){
                topicNames.add(topic.getName());
            }
            dto.setTopicNames(topicNames);
            dto.setStatus(teacher.getUser().getStatus().name());
            listResult.add(dto);
        }
        return listResult;
    }

    @Override
    @Transactional
    public Teacher update(TeacherUpdateDTO dto) {
        Optional<Teacher> teacherResult = teacherRepository.findById(dto.getId());
        if(!teacherResult.isPresent()){
            return null;
        }
        Teacher teacherUpdate = teacherResult.get();
        teacherUpdate.setCode(dto.getCode());
        teacherUpdate.setName(dto.getName());
        teacherUpdate.setGender(
                functionResourceServices.changeGender(dto.getGender())
        );
        teacherUpdate.setBirthday(
                functionResourceServices.changeISOToInstant(dto.getBirthday())
        );
        teacherUpdate.setEmail(dto.getEmail());
        teacherUpdate.setPhone(dto.getPhone());

//        Map<String, Object> map = dto.getMap();
//        Long academyId = ((Number) map.get("academyId")).longValue();
//        Long positionId = ((Number) map.get("positionId")).longValue();
//        Long facultyId = ((Number) map.get("facultyId")).longValue();

        Optional<Academy> academy = academyRepository.findById(dto.getAcademyId());
        if(academy.isPresent()){
            teacherUpdate.setAcademy(academy.get());
        }
        Optional<Position> position = positionRepository.findById(dto.getPositionId());
        if(position.isPresent()){
            teacherUpdate.setPosition(position.get());
        }
        Optional<Faculty> faculty = facultyRepository.findById(dto.getFacultyId());
        if(faculty.isPresent()){
            teacherUpdate.setFaculty(faculty.get());
        }

//        /********************************************************************************************/
//        teacherUpdate.getUser().setRoles(
//                functionResourceServices.changeRoles(dto.getRoles())
//        );

        teacherRepository.save(teacherUpdate);
        return teacherUpdate;

    }

    @Override
    @Transactional
    public TeacherUpdateDTO find(Long id) {
        Optional<Teacher> teacherResult = teacherRepository.findById(id);
        if(!teacherResult.isPresent()){
            return null;
        }
        Teacher teacher = teacherResult.get();
        TeacherUpdateDTO dto = new TeacherUpdateDTO();
        dto.setId(teacher.getId());
        dto.setCode(teacher.getCode());
        dto.setName(teacher.getName());
        dto.setGender(teacher.getGender().name());
        dto.setBirthday(
                functionResourceServices.changeInstantToString(teacher.getBirthday())
        );
        dto.setEmail(teacher.getEmail());
        dto.setPhone(teacher.getPhone());

        dto.setAcademyId(teacher.getAcademy().getId());
        dto.setPositionId(teacher.getPosition().getId());
        dto.setFacultyId(teacher.getFaculty().getId());

//        Map<String, Object> map = new HashMap<>();
//        map.put("academyId", teacher.getAcademy().getId());
//        map.put("academyName", teacher.getAcademy().getName());
//        map.put("positionId", teacher.getPosition().getId());
//        map.put("positionNam", teacher.getPosition().getName());
//        map.put("facultyId", teacher.getFaculty().getId());
//        map.put("facultyName", teacher.getFaculty().getName());
//        dto.setMap(map);
//        dto.setRoles(
//                functionResourceServices.changeRoles(teacher.getUser().getRoles())
//        );
        return dto;
    }

    @Override
    @Transactional
    public TeacherInfoResponseDTO info(Long id) {
        Optional<Teacher> teacherResult = teacherRepository.findById(id);
        if(!teacherResult.isPresent()){
            return null;
        }
        Teacher teacher = teacherResult.get();
        TeacherInfoResponseDTO dto = new TeacherInfoResponseDTO();
        dto.setCode(teacher.getCode());
        dto.setName(teacher.getName());
        dto.setGender(teacher.getGender().name());
        dto.setBirthday(
                functionResourceServices.changeInstantToString(teacher.getBirthday())
        );
        dto.setEmail(teacher.getEmail());
        dto.setPhone(teacher.getPhone());
        dto.setAcademyName(teacher.getAcademy().getName());
        dto.setPositionName(teacher.getPosition().getName());
        dto.setFacultyName(teacher.getFaculty().getName());
        return  dto;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Teacher> teacherResult = teacherRepository.findById(id);
        if(!teacherResult.isPresent()){
            return false;
        }
        Teacher teacher = teacherResult.get();
        teacher.getUser().setStatus(StatusModel.INOPERATIVE);
        teacherRepository.save(teacher);
        return true;
    }
}
