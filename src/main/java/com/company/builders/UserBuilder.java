package com.company.builders;

import com.company.models.requests.CreateUserRequest;
import com.company.models.requests.UpdateUserRequest;

public class UserBuilder {
    public static CreateUserRequest buildMorpheusUser(String name, String job) {
        return CreateUserRequest.builder()
                .name(name)
                .job(job)
                .build();
    }

    public static UpdateUserRequest buildUpdatedMorpheusUser(String name, String job) {
        return UpdateUserRequest.builder()
                .name(name)
                .job(job)
                .build();
    }
}