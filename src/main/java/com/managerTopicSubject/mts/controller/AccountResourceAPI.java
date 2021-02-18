package com.managerTopicSubject.mts.controller;

import com.managerTopicSubject.mts.dto.account.AccountSearchResponseDTO;
import com.managerTopicSubject.mts.dto.account.AccountUpdateRequestDTO;
import com.managerTopicSubject.mts.dto.account.UserUpdateAccountRequestDTO;
import com.managerTopicSubject.mts.services.AccountResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class AccountResourceAPI {

    @Autowired
    private AccountResourceServices accountResourceServices;

    @GetMapping("/account/search")
    public ResponseEntity<?> search(){
        JsonResponse jsonResponse = new JsonResponse();
        try{
            jsonResponse.putSuccess(true);
            List<AccountSearchResponseDTO> listResult = accountResourceServices.search();
            jsonResponse.putResult(listResult);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/account/update")
    public ResponseEntity<?> update(@RequestBody AccountUpdateRequestDTO dto){
        JsonResponse jsonResponse = new JsonResponse();
        try{
            jsonResponse.putSuccess(true);
            Boolean success = accountResourceServices.updateAccount(dto);
            jsonResponse.putResult(success);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/student/account/update")
    public ResponseEntity<?> userUpdateAccount(@RequestBody UserUpdateAccountRequestDTO dto){
        JsonResponse jsonResponse = new JsonResponse();
        try{
            jsonResponse.putSuccess(true);
            Boolean success = accountResourceServices.userUpdateAccount(dto);
            jsonResponse.putResult(success);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }catch (Exception e){
            jsonResponse.putSuccess(false);
            jsonResponse.put("message", "There is an error during...");
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
        }
    }
}
