package com.managerTopicSubject.mts.dto.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@NoArgsConstructor
public class TopicUpdateRequestDTO {

    @Id
    private Long id;

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

    @NotNull @NotBlank
    private Map<String, Object> map;


    /********************************************************************************************/

    @NotNull @NotBlank //format: [start_date**end_date**content, start_date**end_date**content,...]
    private String[] deadlines;

    public TopicUpdateRequestDTO(@NotNull @NotBlank @Size(min = 1, max = 20) String code, @NotNull @NotBlank @Size(min = 5, max = 100) String name, String startTime, String endTime, @NotNull @NotBlank Map<String, Object> map, @NotNull @NotBlank String[] deadlines) {
        this.code = code;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.map = map;
        this.deadlines = deadlines;
    }
}
