package com.company.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserResponse {
    private String name;
    private String job;

    @JsonProperty("updatedAt")
    private String updatedAt;
}