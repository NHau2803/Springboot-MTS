package com.managerTopicSubject.mts.dto.login;

import lombok.Data;

@Data
public class JwtResponseDTO {
    private String token;
    private String type = "Bearer";

    public JwtResponseDTO(String accessToken) {
        this.token = accessToken;
    }


}
