package com.managerTopicSubject.mts.service;

import com.managerTopicSubject.mts.model.Progress;
import com.managerTopicSubject.mts.model.Role;
import com.managerTopicSubject.mts.model.enumModel.GenderModel;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

public interface FunctionResourceServices {



    /***************************************************/
    Set<Role> changeRoles(String role);
    String changeRoles(Set<Role> roleSet);

    /***************************************************/
    GenderModel changeGender(String gender);

    /***************************************************/
    StatusModel changeStatus(String status);

    /***************************************************/
    Progress changeStringToProgressModel(String deadline);
    Progress changeStringToProgressModelUpdate(String deadline);

    /***************************************************/
    String changeInstantToString(Instant i);
    Instant changeISOToInstant(String iso);


    /***************************************************/
    Date covertStringToDate(String time);
    String covertDateToString(Date time);
    Date covertStringToTime(String time);
    String covertTimeToString(Date time);

}
