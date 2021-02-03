package com.managerTopicSubject.mts.controller;

import com.managerTopicSubject.mts.dto.teacher.TeacherCreateRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherInfoRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherSearchRequestDTO;
import com.managerTopicSubject.mts.dto.teacher.TeacherUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Teacher;
import com.managerTopicSubject.mts.service.FunctionResourceServices;
import com.managerTopicSubject.mts.service.TeacherResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class TeacherResourceAPI {

    @Autowired
    private TeacherResourceServices teacherResourceServices;
    @Autowired
    private FunctionResourceServices functionResourceServices;

    @GetMapping("/api/teacher/search")
    public ResponseEntity<?> search(){
        JsonResponse jsonResponse = new JsonResponse();
        try{
            jsonResponse.putSuccess(true);
            List<TeacherSearchRequestDTO> listResult = teacherResourceServices.search();
            jsonResponse.putResult(listResult);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }


    @GetMapping("/teacher/{id}")
    public ResponseEntity<JsonResponse> find(@PathVariable Long id){ //todo: find to update
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            TeacherUpdateRequestDTO dto = teacherResourceServices.find(id);
            jsonResponse.putResult(dto);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/teacher/{id}/info")
    public ResponseEntity<JsonResponse> info(@PathVariable Long id){ //todo: find to update
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            TeacherInfoRequestDTO dto = teacherResourceServices.info(id);
            jsonResponse.putResult(dto);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/teacher")
    public ResponseEntity<JsonResponse> create(@RequestBody TeacherCreateRequestDTO dto){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Teacher teacher = teacherResourceServices.create(dto);
            jsonResponse.putResult(teacher);
            return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/teacher/{id}")
    public ResponseEntity<JsonResponse> update(@RequestBody TeacherUpdateRequestDTO dto){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Teacher teacher = teacherResourceServices.update(dto);
            jsonResponse.putResult(teacher);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/teacher/{id}/delete")
    public ResponseEntity<JsonResponse> delete(@PathVariable Long id){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Boolean delete = teacherResourceServices.delete(id);
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
