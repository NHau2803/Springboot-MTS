package com.managerTopicSubject.mts.services.impl;

import com.managerTopicSubject.mts.dto.account.AccountSearchResponseDTO;
import com.managerTopicSubject.mts.dto.account.AccountUpdateRequestDTO;
import com.managerTopicSubject.mts.dto.account.UserUpdateAccountRequestDTO;
import com.managerTopicSubject.mts.model.User;
import com.managerTopicSubject.mts.repository.UserRepository;
import com.managerTopicSubject.mts.services.AccountResourceServices;
import com.managerTopicSubject.mts.services.FunctionResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountResourceServicesImpl implements AccountResourceServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FunctionResourceServices functionResourceServices;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<AccountSearchResponseDTO> search() {
        List<AccountSearchResponseDTO> listResult = new ArrayList<>();

        List<User> userList = userRepository.findAll();
        for(User user : userList){
            AccountSearchResponseDTO dto = new AccountSearchResponseDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setStatus(user.getStatus().name());
            dto.setRoles(
                    functionResourceServices.changeRoles(user.getRoles())
            );
            listResult.add(dto);
        }
        return listResult;
    }

    @Override
    public Boolean updateAccount(AccountUpdateRequestDTO dto) {
        Optional<User> user = userRepository.findById(dto.getId());
        if(user.isPresent()){
            User userUpdate = user.get();
            userUpdate.setRoles(
                    functionResourceServices.changeRoles(dto.getRoles())
            );
            userUpdate.setStatus(
                    functionResourceServices.changeStatus(dto.getStatus())
            );
            userUpdate.setPassword(encoder.encode(dto.getNewPassword()));
            return true;
        }
        return false;
    }

    @Override
    public Boolean userUpdateAccount(UserUpdateAccountRequestDTO dto) {
        Optional<User> user = userRepository.findByUsername(dto.getUsername());
        if(user.isPresent()){
            User userUpdate = user.get();
//            if(userUpdate.getPassword()
//                    .equals(encoder.encode(dto.getPasswordOld()))
//                    && dto.getPasswordNew().equals(dto.getPasswordConfirm())){
//
//                userUpdate.setPassword(dto.getPasswordConfirm());
//                System.out.println("a");
//                userRepository.save(userUpdate);
//            }
            System.out.println(userUpdate.getPassword());
            System.out.println(dto.getPasswordOld());
            System.out.println(encoder.encode(dto.getPasswordOld()));
            return true;
        }
        return false;
    }
}
