package com.managerTopicSubject.mts.service.impl;

import com.managerTopicSubject.mts.dto.teacher.TeacherCreateRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherInfoRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherSearchRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherUpdateRequestDTO;
import com.managerTopicSubject.mts.model.*;
import com.managerTopicSubject.mts.model.enumModel.RoleNameModel;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import com.managerTopicSubject.mts.repository.*;
import com.managerTopicSubject.mts.service.FunctionResourceServices;
import com.managerTopicSubject.mts.service.TeacherResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TeacherResourceServicesImpl implements TeacherResourceServices {

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
                functionResourceServices.CovertStringToDate(dto.getBirthday())
        );
        teacher.setEmail(dto.getEmail());
        teacher.setPhone(dto.getPhone());
        Optional<Academy> academyModel = academyRepository.findById(dto.getAcademyId());
        if(academyModel.isPresent()){
            teacher.setAcademy(academyModel.get());
        }
        Optional<Position> positionModel = positionRepository.findById(dto.getPositionId());
        if(positionModel.isPresent()){
            teacher.setPosition(positionModel.get());
        }
        Optional<Faculty> facultyModel = facultyRepository.findById(dto.getFacultyId());
        if(facultyModel.isPresent()){
            teacher.setFaculty(facultyModel.get());
        }
        /********************************************************************************************/
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
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
    public List<TeacherSearchRequestDTO> search() {
        List<Teacher> teacherList = teacherRepository.findAll();
        List<TeacherSearchRequestDTO> listResult = new ArrayList<>();
        for(Teacher teacher : teacherList){
            TeacherSearchRequestDTO dto = new TeacherSearchRequestDTO();
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
    public Teacher update(TeacherUpdateRequestDTO dto) {
        Optional<Teacher> teacherModelResult = teacherRepository.findById(dto.getId());
        if(!teacherModelResult.isPresent()){
            return null;
        }
        Teacher teacherUpdate = teacherModelResult.get();
        teacherUpdate.setCode(dto.getCode());
        teacherUpdate.setName(dto.getName());
        teacherUpdate.setGender(
                functionResourceServices.changeGender(dto.getGender())
        );
        teacherUpdate.setBirthday(
                functionResourceServices.CovertStringToDate(dto.getBirthday())
        );
        teacherUpdate.setEmail(dto.getEmail());
        teacherUpdate.setPhone(dto.getPhone());

        Map<String, Object> map = dto.getMap();
        Long academyId = ((Number) map.get("academyId")).longValue();
        Long positionId = ((Number) map.get("positionId")).longValue();
        Long facultyId = ((Number) map.get("facultyId")).longValue();

        Optional<Academy> academyModel = academyRepository.findById(facultyId);
        if(academyModel.isPresent()){
            teacherUpdate.setAcademy(academyModel.get());
        }
        Optional<Position> positionModel = positionRepository.findById(positionId);
        if(positionModel.isPresent()){
            teacherUpdate.setPosition(positionModel.get());
        }
        Optional<Faculty> facultyModel = facultyRepository.findById(facultyId);
        if(facultyModel.isPresent()){
            teacherUpdate.setFaculty(facultyModel.get());
        }
        /********************************************************************************************/

        teacherUpdate.getUser().setRoles(
                functionResourceServices.changeRoles(dto.getRoles())
        );
        teacherRepository.save(teacherUpdate);
        return teacherUpdate;

    }

    @Override
    @Transactional
    public TeacherUpdateRequestDTO find(Long id) {
        Optional<Teacher> teacherModelResult = teacherRepository.findById(id);
        if(!teacherModelResult.isPresent()){
            return null;
        }
        Teacher teacher = teacherModelResult.get();
        TeacherUpdateRequestDTO dto = new TeacherUpdateRequestDTO();
        dto.setId(teacher.getId());
        dto.setCode(teacher.getCode());
        dto.setName(teacher.getName());
        dto.setGender(teacher.getGender().name());
        dto.setBirthday(
                functionResourceServices.CovertDateToString(teacher.getBirthday())
        );
        dto.setEmail(teacher.getEmail());
        dto.setPhone(teacher.getPhone());
        Map<String, Object> map = new HashMap<>();
        map.put("academyId", teacher.getAcademy().getId());
        map.put("academyName", teacher.getAcademy().getName());
        map.put("positionId", teacher.getPosition().getId());
        map.put("positionNam", teacher.getPosition().getName());
        map.put("facultyId", teacher.getFaculty().getId());
        map.put("facultyName", teacher.getFaculty().getName());
        dto.setMap(map);
        dto.setRoles(
                functionResourceServices.changeRoles(teacher.getUser().getRoles())
        );
        return dto;
    }

    @Override
    @Transactional
    public TeacherInfoRequestDTO info(Long id) {
        Optional<Teacher> teacherResult = teacherRepository.findById(id);
        if(!teacherResult.isPresent()){
            return null;
        }
        Teacher teacher = teacherResult.get();
        TeacherInfoRequestDTO dto = new TeacherInfoRequestDTO();
        dto.setCode(teacher.getCode());
        dto.setName(teacher.getName());
        dto.setGender(teacher.getGender().name());
        dto.setBirthday(
                functionResourceServices.CovertDateToString(teacher.getBirthday())
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
