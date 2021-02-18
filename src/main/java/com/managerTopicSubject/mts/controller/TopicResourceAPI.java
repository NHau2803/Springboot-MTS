package com.managerTopicSubject.mts.controller;

import com.managerTopicSubject.mts.dto.topic.TopicCreateRequestDTO;
import com.managerTopicSubject.mts.dto.topic.TopicSearchResponseDTO;
import com.managerTopicSubject.mts.dto.topic.TopicUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Topic;
import com.managerTopicSubject.mts.services.TopicResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class TopicResourceAPI {

    @Autowired
    private TopicResourceServices topicResourceServices;

    @PostMapping("/topic")
    public ResponseEntity<JsonResponse> create(@RequestBody TopicCreateRequestDTO dto) {
        try {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Topic topic = topicResourceServices.create(dto);
            jsonResponse.putResult(topic);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        } catch (Exception e) {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/topic/{id}")
    public ResponseEntity<JsonResponse> update(@RequestBody TopicUpdateRequestDTO dto){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Topic topic = topicResourceServices.update(dto);
            jsonResponse.putResult(topic);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/topic/search")
    public ResponseEntity<JsonResponse> search(){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            List<TopicSearchResponseDTO> listResult = topicResourceServices.search();
            jsonResponse.putResult(listResult);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }
    @GetMapping("/topic/search/{id}")
    public ResponseEntity<JsonResponse> searchByStudentId(@PathVariable Long id){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            List<TopicSearchResponseDTO> listResult = topicResourceServices.searchByStudentId(id);
            jsonResponse.putResult(listResult);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<JsonResponse> find(@PathVariable Long id){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            TopicUpdateRequestDTO dto = topicResourceServices.find(id);
            jsonResponse.putResult(dto);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/api/topic/{id}/delete")
    public ResponseEntity<JsonResponse> delete(@PathVariable Long id){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Boolean delete = topicResourceServices.delete(id);
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
