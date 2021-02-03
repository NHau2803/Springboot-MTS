package com.managerTopicSubject.mts.model.enumModel;

public enum StatusModel {
    ACTIVE, INOPERATIVE , //todo: for account
    FINISHED, DOING, DELETED;

    public static Boolean isMember(String statusName){

        StatusModel[] statusModels = StatusModel.values();
        for(StatusModel roleNameModel : statusModels){
            if(roleNameModel.name().equals(statusName)){
                return true;
            }
        }
        return false;
    }
}
