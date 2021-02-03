package com.managerTopicSubject.mts.service.impl;

import com.managerTopicSubject.mts.dto.student.StudentCreateRequestDTO;
import com.managerTopicSubject.mts.dto.student.StudentInfoRequestDTO;
import com.managerTopicSubject.mts.dto.student.StudentSearchRequestDTO;
import com.managerTopicSubject.mts.dto.student.StudentUpdateRequestDTO;
import com.managerTopicSubject.mts.model.*;
import com.managerTopicSubject.mts.model.enumModel.RoleNameModel;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import com.managerTopicSubject.mts.model.midLevel.StudentTeam;
import com.managerTopicSubject.mts.repository.FacultyRepository;
import com.managerTopicSubject.mts.repository.RoleRepository;
import com.managerTopicSubject.mts.repository.StudentRepository;
import com.managerTopicSubject.mts.repository.UserRepository;
import com.managerTopicSubject.mts.repository.midLevel.StudentTeamRepository;
import com.managerTopicSubject.mts.service.FunctionResourceServices;
import com.managerTopicSubject.mts.service.StudentResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class StudentResourceServicesImpl implements StudentResourceServices {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentTeamRepository studentTeamRepository;
    @Autowired
    private FunctionResourceServices functionResourceServices;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Student create(StudentCreateRequestDTO dto) {
        Student student = new Student();
        student.setCode(dto.getCode());
        student.setName(dto.getName());
        student.setGender(
               functionResourceServices.changeGender(dto.getGender())
        );
        student.setBirthday(
                functionResourceServices.CovertStringToDate(dto.getBirthday())
        );
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        Faculty faculty = facultyRepository.findById(dto.getFacultyId()).get();
        if(faculty !=null){
            student.setFaculty(faculty);
        }
        /********************************************************************************************/
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setStatus(StatusModel.ACTIVE);
        user.setRoles(
                functionResourceServices.changeRoles(RoleNameModel.STUDENT.name())
        );

        User theUser = userRepository.save(user);
        student.setUser(theUser);
        studentRepository.save(student);

        return student;
    }

    @Override
    @Transactional
    public List<StudentSearchRequestDTO> search() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentSearchRequestDTO> listResult = new ArrayList<>();
        for(Student student : studentList){
            StudentSearchRequestDTO dto = new StudentSearchRequestDTO();
            dto.setId(student.getId());
            dto.setCode(student.getCode());
            dto.setName(student.getName());
            dto.setFacultyName(student.getFaculty().getName());

            List<StudentTeam> studentTeams = studentTeamRepository.findByStudentId(student.getId());
            List<String> teamNames = new ArrayList<>();
            List<String> topicNames = new ArrayList<>();
            for(StudentTeam studentTeam : studentTeams){
                teamNames.add(studentTeam.getTeam().getName());
                topicNames.add(studentTeam.getTeam().getTopic().getName());
            }
            dto.setTeamNames(teamNames);
            dto.setTopicNames(topicNames);
            dto.setStatus(student.getUser().getStatus().name());
            listResult.add(dto);
        }
        return listResult;
    }

    @Override
    @Transactional
    public Student update(StudentUpdateRequestDTO dto) {
        Optional<Student> studentModelResult = studentRepository.findById(dto.getId());
        if(!studentModelResult.isPresent()) {
            return null;
        }
        Student studentUpdate = studentModelResult.get();
        studentUpdate.setCode(dto.getCode());
        studentUpdate.setName(dto.getName());
        studentUpdate.setGender(
                functionResourceServices.changeGender(dto.getGender())
        );
        studentUpdate.setBirthday(dto.getBirthday());
        studentUpdate.setEmail(dto.getEmail());
        studentUpdate.setPhone(dto.getPhone());

        Map<String, Object> faculty = dto.getFaculty();
        Long facultyId = ((Number) faculty.get("facultyId")).longValue();
        Optional<Faculty> facultyModel = facultyRepository.findById(facultyId);
        if(facultyModel.isPresent()){
            studentUpdate.setFaculty(facultyModel.get());
        }
        /********************************************************************************************/
        studentUpdate.getUser().setRoles(
                functionResourceServices.changeRoles(dto.getRoles())
        );
        studentRepository.save(studentUpdate);
        return studentUpdate;

    }

    @Override
    @Transactional
    public StudentUpdateRequestDTO find(Long id) {
        Optional<Student> studentModelResult = studentRepository.findById(id);
        if(!studentModelResult.isPresent()){
            return null;
        }
        Student student = studentModelResult.get();
        StudentUpdateRequestDTO dto = new StudentUpdateRequestDTO();
        dto.setId(student.getId());
        dto.setCode(student.getCode());
        dto.setName(student.getName());
        dto.setGender(student.getGender().name());
        dto.setBirthday(student.getBirthday());
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        Map<String, Object> faculty = new HashMap<>();
        faculty.put("facultyId", student.getFaculty().getId());
        faculty.put("facultyName", student.getFaculty().getName());
        dto.setFaculty(faculty);
        dto.setRoles(
                functionResourceServices.changeRoles(student.getUser().getRoles())
        );
        return dto;
    }

    @Override
    @Transactional
    public StudentInfoRequestDTO info(Long id) {
        Optional<Student> studentResult = studentRepository.findById(id);
        if(!studentResult.isPresent()){
            return null;
        }
        Student student = studentResult.get();
        StudentInfoRequestDTO dto = new StudentInfoRequestDTO();
        dto.setCode(student.getCode());
        dto.setName(student.getName());
        dto.setGender(student.getGender().name());
        dto.setBirthday(
                functionResourceServices.CovertDateToString(student.getBirthday())
        );
        dto.setEmail(student.getEmail());
        dto.setPhone(student.getPhone());
        dto.setFacultyName(student.getFaculty().getName());
        return  dto;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Student> studentResult = studentRepository.findById(id);
        if(!studentResult.isPresent()){
            return false;
        }
        Student student = studentResult.get();
        student.getUser().setStatus(StatusModel.INOPERATIVE);
        studentRepository.save(student);
        return true;
    }


}
