package com.managerTopicSubject.mts.services;

import com.managerTopicSubject.mts.dto.account.AccountSearchResponseDTO;
import com.managerTopicSubject.mts.dto.account.AccountUpdateRequestDTO;
import com.managerTopicSubject.mts.dto.account.UserUpdateAccountRequestDTO;

import java.util.List;

public interface AccountResourceServices {
    List<AccountSearchResponseDTO> search();
    Boolean updateAccount(AccountUpdateRequestDTO dto);
    Boolean userUpdateAccount(UserUpdateAccountRequestDTO dto);
}
