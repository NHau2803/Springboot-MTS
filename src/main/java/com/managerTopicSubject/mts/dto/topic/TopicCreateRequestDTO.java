package com.managerTopicSubject.mts.dto.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.managerTopicSubject.mts.model.Progress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicCreateRequestDTO {

    @NotNull @NotBlank @Size(min = 1, max = 20)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 100)
    private String name;

    @NotNull @NotBlank
    private String startTime;

    @NotNull @NotBlank
    private String endTime;

    @Id
    private Long facultyId;

    @Id
    private Long teacherId;

    @Id
    private Long typeTopicId;

    /********************************************************************************************/

    @NotNull @NotBlank //format: [start_date**end_date**content, start_date**end_date**content,...]
    private String[] deadlines;

//    @NotNull @NotBlank
//    private List<Progress> progresses;
}
