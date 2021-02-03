package com.managerTopicSubject.mts.service.impl;

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
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FunctionResourceServiceImpl implements FunctionResourceServices {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProgressRepository progressRepository;

    @Transactional
    @Override
    public Date CovertStringToDate(String time) {
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
    public String CovertDateToString(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(time.getTime());
        String timeStr = format.format(date);
        return timeStr;
    }

    @Override
    public Date CovertStringToTime(String time) {
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
    public String CovertTimeToString(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(time.getTime());
        String timeStr = format.format(date);
        return timeStr;
    }

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
                CovertStringToTime(deadlineDetails[0])
        );
        progress.setEndTime(
                CovertStringToTime(deadlineDetails[1])
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
                    CovertStringToTime(deadlineDetail[1])
            );
            progressModel.get().setEndTime(
                    CovertStringToTime(deadlineDetail[2])
            );
            progressModel.get().setContent(deadlineDetail[3]);

            return progressModel.get();
        }else {
            return null;
        }
    }

//    @Override
//    public String changeStatus(StatusModel status) {
//        if(StatusModel.isMember(status.name())){
//
//        }
//    }


}
