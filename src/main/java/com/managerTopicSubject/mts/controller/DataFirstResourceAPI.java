package com.managerTopicSubject.mts.controller;

import com.managerTopicSubject.mts.dto.student.StudentCreateRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherCreateRequestDTO;
import com.managerTopicSubject.mts.dto.team.JoinTeamRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamCreateRequestDTO;
import com.managerTopicSubject.mts.dto.topic.TopicCreateRequestDTO;
import com.managerTopicSubject.mts.model.*;
import com.managerTopicSubject.mts.model.enumModel.RoleNameModel;
import com.managerTopicSubject.mts.repository.*;
import com.managerTopicSubject.mts.services.StudentResourceServices;
import com.managerTopicSubject.mts.services.TeacherResourceServices;
import com.managerTopicSubject.mts.services.TeamResourceServices;
import com.managerTopicSubject.mts.services.TopicResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class DataFirstResourceAPI {

    @Autowired
    private AcademyRepository academyRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private TypeTopicRepository typeTopicRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StudentResourceServices studentResourceServices;
    @Autowired
    private TopicResourceServices topicResourceServices;
    @Autowired
    private TeacherResourceServices teacherResourceServices;
    @Autowired
    private TeamResourceServices teamResourceServices;

    @Transactional
    @PostMapping("/create-data")
    public String createDataFirst(){
        try {
            /*********************************************************************************/
            List<Academy> academies = new ArrayList<>();
            academies.add(new Academy("Ths", "Thạch sĩ"));
            academies.add(new Academy("Ts", "Tiến sĩ"));
            academies.add(new Academy("Pgs", "Phó giáo sư"));
            academies.add(new Academy("Gs", "Giáo sư"));
            for (Academy academy : academies) {
                academyRepository.save(academy);
            }

            /*********************************************************************************/
            List<Faculty> faculties = new ArrayList<>();
            faculties.add(new Faculty("CNTT", "Công nghệ thông tin"));
            faculties.add(new Faculty("XD", "Xây Dựng"));
            faculties.add(new Faculty("KT", "Kế toán"));
            faculties.add(new Faculty("QTKD", "Quản trị kinh doanh"));
            faculties.add(new Faculty("MT", "Mỹ thuật"));
            faculties.add(new Faculty("TKTT", "Thế kế thời trang"));
            for (Faculty faculty : faculties) {
                facultyRepository.save(faculty);
            }

            /*******************************************************************************/
            List<TypeTopic> typeTopics = new ArrayList<>();
            typeTopics.add(new TypeTopic("DA-1", "Đồ án 1"));
            typeTopics.add(new TypeTopic("DA-2", "Đồ án 2"));
            typeTopics.add(new TypeTopic("DA-TL", "Đồ án tiểu luận"));
            typeTopics.add(new TypeTopic("DA-TT", "Đồ án thực tập"));
            typeTopics.add(new TypeTopic("DA-TH", "Đồ án thực hành"));
            typeTopics.add(new TypeTopic("DA-TN", "Đồ án tốt nghiệp"));
            for (TypeTopic typeTopic : typeTopics) {
                typeTopicRepository.save(typeTopic);
            }

            /*****************************************************************************/
            List<Position> positions = new ArrayList<>();
            positions.add(new Position("PH.P", "Phó phòng"));
            positions.add(new Position("TR.P", "Trưởng phòng"));
            positions.add(new Position("KT", "Kế toán"));
            positions.add(new Position("NV", "Nhân viên"));
            positions.add(new Position("GD", "Giám đốc"));
            positions.add(new Position("PH.GD", "Phó giám đốc"));
            positions.add(new Position("QL", "Quản lí"));
            positions.add(new Position("PH.K", "Phó khoa"));
            positions.add(new Position("TK", "Trưởng khoa"));
            positions.add(new Position("CN.K", "Chủ nghiệm khoa"));
            for (Position position : positions) {
                positionRepository.save(position);
            }

            /*****************************************************************************/
            List<Role> roles = new ArrayList<>();
            roles.add(new Role("Admin", RoleNameModel.ADMIN));
            roles.add(new Role("Teacher", RoleNameModel.TEACHER));
            roles.add(new Role("Student", RoleNameModel.STUDENT));
            roles.add(new Role("Accountant", RoleNameModel.ACCOUNTANT));
            roles.add(new Role("Technical", RoleNameModel.TECHNICAL));
            for (Role role : roles) {
                roleRepository.save(role);
            }

            return "OK!";

        } catch (Exception e){
            return "ERROR" + e;
        }
    }


    @Transactional
    @PostMapping("/create-students")
    public String forStudent(@RequestBody List<StudentCreateRequestDTO> dtoList){
        try{
            for(StudentCreateRequestDTO dto: dtoList){
                studentResourceServices.create(dto);
            }
            return "OK!";
        }catch (Exception e){
            return "ERROR" + e;
        }
    }

    @Transactional
    @PostMapping("/create-teachers")
    public String forTeacher(@RequestBody List<TeacherCreateRequestDTO> dtoList){
        try{
            for(TeacherCreateRequestDTO dto: dtoList){
                teacherResourceServices.create(dto);
            }
            return "OK!";
        }catch (Exception e){
            return "ERROR" + e;
        }
    }

    @Transactional
    @PostMapping("/create-topics")
    public String forTopic(@RequestBody List<TopicCreateRequestDTO> dtoList){
        try{
            for(TopicCreateRequestDTO dto : dtoList){
                topicResourceServices.create(dto);
            }
            return "OK!";
        }catch (Exception e){
            return "ERROR" + e;
        }
    }

    @Transactional
    @PostMapping("/create-teams")
    public String forTeam(@RequestBody List<TeamCreateRequestDTO> dtoList){
        try {
            for(TeamCreateRequestDTO dto : dtoList){
                teamResourceServices.create(dto);
            }
            return "OK!";
        }catch (Exception e){
            return "ERROR" + e;
        }
    }

    @Transactional
    @PostMapping("/create-join-teams")
    public String forJoinTeam(@RequestBody List<JoinTeamRequestDTO> dtoList){
        try {
            for(JoinTeamRequestDTO dto : dtoList){
                teamResourceServices.join(dto);
            }
            return "OK!";
        }catch (Exception e){
            return "ERROR" + e;
        }
    }

    @Transactional
    @PostMapping("/test")
    public String test(@RequestBody TopicCreateRequestDTO dto){
        try {
            topicResourceServices.create(dto);
            return "OK!";
        }catch (Exception e){
            return "ERROR" + e;
        }
    }




}
