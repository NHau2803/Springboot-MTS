package com.managerTopicSubject.mts.service;

import com.managerTopicSubject.mts.model.Progress;
import com.managerTopicSubject.mts.model.Role;
import com.managerTopicSubject.mts.model.enumModel.GenderModel;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;

import java.util.Date;
import java.util.Set;

public interface FunctionResourceServices {
    Date CovertStringToDate(String time);
    String CovertDateToString(Date time);

    Date CovertStringToTime(String time);
    String CovertTimeToString(Date time);

    Set<Role> changeRoles(String role);
    String changeRoles(Set<Role> roleSet);

    GenderModel changeGender(String gender);
    //String changeGender(GenderModel gender);

    StatusModel changeStatus(String status);
   // String changeStatus(StatusModel status);

    //["student", "teacher"]  vs "student teacher"

    Progress changeStringToProgressModel(String deadline);
    Progress changeStringToProgressModelUpdate(String deadline);
}
