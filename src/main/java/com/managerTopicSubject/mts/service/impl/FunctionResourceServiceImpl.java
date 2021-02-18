package com.managerTopicSubject.mts.service.impl;

import com.managerTopicSubject.mts.dto.topic.DeadlineDTO;
import com.managerTopicSubject.mts.dto.topic.DeadlineRequestDTO;
import com.managerTopicSubject.mts.model.Progress;
import com.managerTopicSubject.mts.model.Role;
import com.managerTopicSubject.mts.model.enumModel.GenderModel;
import com.managerTopicSubject.mts.model.enumModel.RoleNameModel;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import com.managerTopicSubject.mts.repository.ProgressRepository;
import com.managerTopicSubject.mts.repository.RoleRepository;
import com.managerTopicSubject.mts.service.FunctionResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class FunctionResourceServiceImpl implements FunctionResourceServices {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProgressRepository progressRepository;



    @Transactional
    @Override
    public Set<Role> changeRoles(String role) {
        Set<Role> roleSet = new HashSet<>();
        String[] Roles = role.split(" ");
        for(String name : Roles){
            if(RoleNameModel.isMember(name)){
                Optional<Role> roleModel = roleRepository.findByName(
                        RoleNameModel.valueOf(name)
                );
                if(roleModel.isPresent()){
                    roleSet.add(roleModel.get());
                }
            }
        }
        if (!roleSet.isEmpty()){
            return roleSet;
        }
        return null;
    }

    @Transactional
    @Override
    public String changeRoles(Set<Role> roleSet) {
        String roleStr = ""; //todo: change arr[] to string
        for(Role role : roleSet){
            roleStr += role.getName() + " ";
        }
        return roleStr;
    }

    @Transactional
    @Override
    public GenderModel changeGender(String gender) {
        if(GenderModel.isMember(gender)){
            return GenderModel.valueOf(gender);
        }
        return null;
    }

//    @Transactional
//    @Override
//    public String changeGender(GenderModel gender) {
//        if(gender.equals(GenderModel.MALE)){
//            return "male";
//        }
//        return "female";
//    }

    @Override
    public StatusModel changeStatus(String status) {
        if(StatusModel.isMember(status)){
            return StatusModel.valueOf(status);
        }
        return null;
    }

    @Override
    @Transactional
    public Progress changeStringToProgressModel(String deadline) {
        Progress progress = new Progress();
        String[] deadlineDetails = deadline.split("\\*\\*");
        progress.setStartTime(
                changeISOToInstant(deadlineDetails[0])
        );
        progress.setEndTime(
                changeISOToInstant(deadlineDetails[1])
        );
        progress.setContent(deadlineDetails[2]);
        return progress;
    }

    @Override
    public Progress changeStringToProgressModelUpdate(String deadline) {
        String[] deadlineDetail = deadline.split("\\*\\*");
        Long progressId = Long.parseLong(deadlineDetail[0]);
        Optional<Progress> progressModel = progressRepository.findById(progressId);
        if (progressModel.isPresent()) {
            progressModel.get().setId(progressId);
            progressModel.get().setStartTime(
                    Instant.parse(deadlineDetail[1])
            );
            progressModel.get().setEndTime(
                    Instant.parse(deadlineDetail[2])
            );
            progressModel.get().setContent(deadlineDetail[3]);

            return progressModel.get();
        }else {
            return null;
        }
    }

    @Override
    public String changeInstantToString(Instant i) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant( i , ZoneId.of("Asia/Saigon"));
        return localDateTime.toString();
    }

    @Override
    public Instant changeISOToInstant(String iso) {
        return Instant.parse(iso);
    }

    @Transactional
    @Override
    public Date covertStringToDate(String time) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateSql = new Date(date.getTime());
        return dateSql;
    }

    @Transactional
    @Override
    public String covertDateToString(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(time.getTime());
        String timeStr = format.format(date);
        return timeStr;
    }

    @Override
    public Date covertStringToTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        java.util.Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateSql = new Date(date.getTime());
        return dateSql;
    }

    @Override
    public String covertTimeToString(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(time.getTime());
        String timeStr = format.format(date);
        return timeStr;
    }


    @Override
    public Map<String, Object> HandleDeadlines(Long topicId, List<DeadlineDTO> dto) {

        Map<String, Object> result = new HashMap<>();

        Set<Long> progressIds = new HashSet<>();
        Set<Long> dtoIds = new HashSet<>();

        List<Progress> progressResult = progressRepository.findByTopicId(topicId);
        for(Progress progress: progressResult) {
            progressIds.add(progress.getId());
        }

        for(DeadlineDTO deadline : dto) {
            dtoIds.add(deadline.getId());
        }

        Set<Long> updateProgressIds = new HashSet<>();
        for(Long dtoId: dtoIds){
            for(Long progressId: progressIds){
                if(dtoId.equals(progressId)){
                    updateProgressIds.add(dtoId);
                }
            }
        }

        //todo: id like => id need update
        List<DeadlineDTO> deadlinesUpdate = new ArrayList<>();
        for(DeadlineDTO deadline : dto) {
            for(Long id : updateProgressIds){
                if(deadline.getId().equals(id)){
                    deadlinesUpdate.add(deadline);
                }
            }
        }
        result.put("deadlinesUpdate", deadlinesUpdate);

        //todo: progress_id not update => id need delete
        progressIds.removeAll(updateProgressIds);
        result.put("deleteProgressIds", progressIds);

        //todo: dto_id not update => create new a progress
        List<DeadlineRequestDTO> deadlinesCreate = new ArrayList<>();
        dtoIds.removeAll(updateProgressIds);
        Set<Long> createProgressIds = dtoIds;

        for(DeadlineDTO deadline : dto) {
            for(Long id : createProgressIds){
                if(deadline.getId().equals(id)){
                    DeadlineRequestDTO deadlineRequestDTO = new DeadlineRequestDTO();
                    deadlineRequestDTO.setStartDeadline(deadline.getStartDeadline());
                    deadlineRequestDTO.setEndDeadline(deadline.getEndDeadline());
                    deadlineRequestDTO.setContent(deadline.getContent());
                    deadlinesCreate.add(deadlineRequestDTO);
                }
            }
        }
        result.put("deadlinesCreate", deadlinesCreate);

        return result;
    }


}
