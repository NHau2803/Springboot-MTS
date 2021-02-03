package com.managerTopicSubject.mts.controller;

import com.managerTopicSubject.mts.dto.topic.TopicCreateRequestDTO;
import com.managerTopicSubject.mts.dto.topic.TopicSearchRequestDTO;
import com.managerTopicSubject.mts.dto.topic.TopicUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Topic;
import com.managerTopicSubject.mts.service.TopicResourceServices;
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
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        } catch (Exception e) {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
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
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/topic/search")
    public ResponseEntity<JsonResponse> search(){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            List<TopicSearchRequestDTO> listResult = topicResourceServices.search();
            jsonResponse.putResult(listResult);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }


}
