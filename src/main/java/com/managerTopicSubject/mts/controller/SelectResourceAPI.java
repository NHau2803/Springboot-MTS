package com.managerTopicSubject.mts.controller;

import com.managerTopicSubject.mts.dto.select.SelectResponseDTO;
import com.managerTopicSubject.mts.dto.select.TopicOfFacultyResponseDTO;
import com.managerTopicSubject.mts.dto.student.StudentSearchResponseDTO;
import com.managerTopicSubject.mts.service.SelectResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class SelectResourceAPI {

    @Autowired
    private SelectResourceServices selectResourceServices;

    @GetMapping("/faculty/all")
    public List<SelectResponseDTO> getFacultyList(){
        return selectResourceServices.getFacultyList();
    }

    @GetMapping("/academy/all")
    public List<SelectResponseDTO> getAcademyList(){
        return selectResourceServices.getAcademyList();
    }

    @GetMapping("/position/all")
    public List<SelectResponseDTO> getPositionList(){
        return selectResourceServices.getPositionList();
    }

    @GetMapping("/typeTopic/all")
    public List<SelectResponseDTO> typeTopicList(){
        return selectResourceServices.getTypeTopicList();
    }

    @GetMapping("/topic/all")
    public ResponseEntity<?> search(){
        JsonResponse jsonResponse = new JsonResponse();
        try{
            jsonResponse.putSuccess(true);
            List<TopicOfFacultyResponseDTO> listResult = selectResourceServices.getAllTopicList();
            jsonResponse.putResult(listResult);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }


}
