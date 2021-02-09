package com.managerTopicSubject.mts.service;

import com.managerTopicSubject.mts.dto.team.*;
import com.managerTopicSubject.mts.model.Team;

import java.util.List;

public interface TeamResourceServices {

    Team create(TeamCreateRequestDTO dto);
    Boolean join(JoinTeamRequestDTO dto);
    List<TeamSearchResponseDTO> search();
    TeamUpdateDTO find(Long id);
    Team update(TeamUpdateDTO dto);
    Boolean delete(Long id);
    List<TeamSearchResponseDTO> searchByTopicId(Long id);
    ViewTeamResponseDTO viewTeam(Long id);

}
