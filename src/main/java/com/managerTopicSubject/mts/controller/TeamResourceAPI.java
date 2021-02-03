package com.managerTopicSubject.mts.controller;

import com.managerTopicSubject.mts.dto.team.JoinTeamRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamCreateRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamSearchRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Team;
import com.managerTopicSubject.mts.service.TeamResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class TeamResourceAPI {

    @Autowired
    private TeamResourceServices teamResourceServices;

    @PostMapping("/team")
    public ResponseEntity<JsonResponse> create(@RequestBody TeamCreateRequestDTO dto) {
        try {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Team team = teamResourceServices.create(dto);
            jsonResponse.putResult(team);
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        } catch (Exception e) {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/team/{id}")
    public ResponseEntity<JsonResponse> update(@RequestBody TeamUpdateRequestDTO dto){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Team team = teamResourceServices.update(dto);
            jsonResponse.putResult(team);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/team/join")
    public ResponseEntity<JsonResponse> join(@RequestBody JoinTeamRequestDTO dto){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Boolean join = teamResourceServices.join(dto);
            jsonResponse.putResult(join);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/team/search")
    public ResponseEntity<JsonResponse> search(){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            List<TeamSearchRequestDTO> listResult = teamResourceServices.search();
            jsonResponse.putResult(listResult);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<JsonResponse> find(@PathVariable Long id){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            TeamUpdateRequestDTO dto = teamResourceServices.find(id);
            jsonResponse.putResult(dto);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/team/{id}/delete")
    public ResponseEntity<JsonResponse> delete(@PathVariable Long id){
        try{
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.putSuccess(true);
            Boolean delete = teamResourceServices.delete(id);
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
