package com.company.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateUserResponse {
    private String name;
    private String job;
    private String id;

    @JsonProperty("createdAt")
    private String createdAt;
}