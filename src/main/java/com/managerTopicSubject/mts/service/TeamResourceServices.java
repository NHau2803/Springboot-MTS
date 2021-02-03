package com.managerTopicSubject.mts.service;

import com.managerTopicSubject.mts.dto.team.JoinTeamRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamCreateRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamSearchRequestDTO;
import com.managerTopicSubject.mts.dto.team.TeamUpdateRequestDTO;
import com.managerTopicSubject.mts.model.Team;

import java.util.List;

public interface TeamResourceServices {

    Team create(TeamCreateRequestDTO dto);
    Boolean join(JoinTeamRequestDTO dto);
    List<TeamSearchRequestDTO> search();
    TeamUpdateRequestDTO find(Long id);
    Team update(TeamUpdateRequestDTO dto);
    Boolean delete(Long id);

}
