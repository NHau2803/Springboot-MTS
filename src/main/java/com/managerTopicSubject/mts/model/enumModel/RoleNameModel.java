package com.managerTopicSubject.mts.model.enumModel;

public enum RoleNameModel {

    ADMIN, STUDENT, TEACHER, ACCOUNTANT, TECHNICAL  ;

     public static Boolean isMember(String roleName){

        RoleNameModel[] roleNameModels = RoleNameModel.values();
        for(RoleNameModel roleNameModel : roleNameModels){
            if(roleNameModel.name().equals(roleName)){
                return true;
            }
        }
        return false;
    }

}
