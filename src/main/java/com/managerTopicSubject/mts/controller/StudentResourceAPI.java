package com.managerTopicSubject.mts.controller;

import com.managerTopicSubject.mts.dto.student.StudentCreateRequestDTO;
import com.managerTopicSubject.mts.dto.student.StudentInfoResponseDTO;
import com.managerTopicSubject.mts.dto.student.StudentSearchResponseDTO;
import com.managerTopicSubject.mts.dto.student.StudentUpdateDTO;
import com.managerTopicSubject.mts.model.Student;
import com.managerTopicSubject.mts.service.FunctionResourceServices;
import com.managerTopicSubject.mts.service.StudentResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class StudentResourceAPI {

    @Autowired
    private StudentResourceServices studentResourceServices;
    @Autowired
    private FunctionResourceServices functionResourceServices;

    @GetMapping("/api/student/search")
    public ResponseEntity<?> search(){
        JsonResponse jsonResponse = new JsonResponse();
        try{
//              --TEST ERROR--
//            jsonResponse.putSuccess(false);
//            jsonResponse.put("message", "There is an error during...");

            jsonResponse.putSuccess(true);
            List<StudentSearchResponseDTO> listResult = studentResourceServices.search();
            jsonResponse.putResult(listResult);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }


    @GetMapping("/student/{id}")
    public ResponseEntity<JsonResponse> find(@PathVariable Long id){ //todo: find to update
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            StudentUpdateDTO dto = studentResourceServices.find(id);
            jsonResponse.putResult(dto);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/student/{id}/info")
    public ResponseEntity<JsonResponse> info(@PathVariable Long id){ //todo: find to update
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            StudentInfoResponseDTO dto = studentResourceServices.info(id);
            jsonResponse.putResult(dto);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/student")
    public ResponseEntity<JsonResponse> create(@RequestBody StudentCreateRequestDTO dto){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Student student = studentResourceServices.create(dto);
            jsonResponse.putResult(student);
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/student/{id}")
    public ResponseEntity<JsonResponse> update(@RequestBody StudentUpdateDTO dto){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Student student = studentResourceServices.update(dto);
            jsonResponse.putResult(student);
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/student/{id}/delete")
    public ResponseEntity<JsonResponse> delete(@PathVariable Long id){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Boolean delete = studentResourceServices.delete(id);
            jsonResponse.putResult(delete);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }



}
