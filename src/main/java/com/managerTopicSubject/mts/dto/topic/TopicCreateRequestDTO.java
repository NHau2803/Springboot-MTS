package com.managerTopicSubject.mts.dto.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicCreateRequestDTO {

    @NotNull @NotBlank @Size(min = 1, max = 20)
    private String code;

    @NotNull @NotBlank @Size(min = 5, max = 100)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private String startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private String endTime;

    @Id
    private Long teacherId;

    @Id
    private Long typeTopicId;

    /********************************************************************************************/

    @NotNull @NotBlank //format: [start_date**end_date**content, start_date**end_date**content,...]
    private String[] deadlines;
}
