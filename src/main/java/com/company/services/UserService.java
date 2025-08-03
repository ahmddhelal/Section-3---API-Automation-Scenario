package com.company.services;

import com.company.models.requests.CreateUserRequest;
import com.company.models.requests.UpdateUserRequest;
import com.company.models.responses.CreateUserResponse;
import com.company.models.responses.GetUserResponse;
import com.company.models.responses.UpdateUserResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class UserService {

    private static final String BASE_URL = "https://reqres.in";

    public CreateUserResponse createUser(CreateUserRequest request) {
        RestAssured.useRelaxedHTTPSValidation();

        return given()
                .baseUri(BASE_URL)
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract()
                .as(CreateUserResponse.class);
    }

    public UpdateUserResponse updateUser(String userId, UpdateUserRequest request) {
        RestAssured.useRelaxedHTTPSValidation(); // optional

        return given()
                .baseUri(BASE_URL)
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put("/api/users/" + userId)
                .then()
                .statusCode(200)
                .extract()
                .as(UpdateUserResponse.class);
    }

    public GetUserResponse getUserById(String userId) {
        RestAssured.useRelaxedHTTPSValidation(); // optional for SSL issues

        return given()
                .baseUri(BASE_URL)
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/api/users/" + userId)
                .then()
                .statusCode(200)
                .extract()
                .as(GetUserResponse.class);
    }
    public void deleteUser(String userId) {
        RestAssured.useRelaxedHTTPSValidation(); // Optional for SSL

        given()
                .baseUri(BASE_URL)
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/api/users/" + userId)
                .then()
                .statusCode(204); // DELETE should return 204 No Content
    }

}