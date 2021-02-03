package com.managerTopicSubject.mts.model.enumModel;

public enum GenderModel {

    MALE, FEMALE ;

    public static Boolean isMember(String genderName){

        GenderModel[] genderModels = GenderModel.values();
        for(GenderModel genderModel : genderModels){
            if(genderModel.name().equals(genderName)){
                return true;
            }
        }
        return false;
    }

}
