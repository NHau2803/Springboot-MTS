package com.managerTopicSubject.mts.dto.team;

import com.managerTopicSubject.mts.model.Topic;
import com.managerTopicSubject.mts.model.enumModel.StatusModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewTeamResponseDTO {

    @Id
    private Long id;

    @NotNull @NotBlank
    private String status;

    @NotNull @NotBlank
    private String name;

    private String link;

    private Float point;

    @NotNull @NotBlank
    private String topicName;

    private List<Map<String, Object>> students;


}
