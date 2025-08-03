package com.company.tests;
import com.company.builders.UserBuilder;
import com.company.constants.GeneralConstants;
import com.company.constants.TestDataConstants;
import com.company.models.requests.CreateUserRequest;
import com.company.models.requests.UpdateUserRequest;
import com.company.models.responses.CreateUserResponse;
import com.company.models.responses.GetUserResponse;
import com.company.models.responses.UpdateUserResponse;
import com.company.services.UserService;
import com.company.utilities.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

public class UserTest {

    public static String createdUserId;
    PropertiesReader propertiesReader = new PropertiesReader();
    Properties testDataConfigurations = propertiesReader.loadPropertiesFromFile(GeneralConstants.TEST_DATA_FILE);
    String userName = testDataConfigurations.getProperty(TestDataConstants.USER_NAME);
    String userJob = testDataConfigurations.getProperty(TestDataConstants.USER_JOB);
    String updatedUserJob = testDataConfigurations.getProperty(TestDataConstants.USER_UPDATED_JOB);

    @Test
    public void testCreateUserSuccessfully() {
        // Arrange
        CreateUserRequest request = UserBuilder.buildMorpheusUser(userName, userJob);
        UserService userService = new UserService();

        // Act
        CreateUserResponse response = userService.createUser(request);

        // Assert
        Assert.assertNotNull(response.getId(), "User ID should not be null");
        Assert.assertEquals(response.getName(), userName, "User name should match");
        Assert.assertEquals(response.getJob(), userJob, "User job should match");

        createdUserId = response.getId();
        System.out.println("Created User ID: " + createdUserId);
    }

    @Test(dependsOnMethods = {"testCreateUserSuccessfully"})
    public void testUpdateUserSuccessfully() {
        // Arrange
        String userId = createdUserId;
        UpdateUserRequest request = UserBuilder.buildUpdatedMorpheusUser(userName, updatedUserJob);
        UserService userService = new UserService();

        // Act
        UpdateUserResponse response = userService.updateUser(userId, request);

        // Assert
        Assert.assertEquals(response.getName(), userName, "User name should match the original name");
        Assert.assertEquals(response.getJob(), updatedUserJob, "User job should be updated");
        Assert.assertNotNull(response.getUpdatedAt(), "updatedAt should not be null");

        System.out.println("User updated at: " + response.getUpdatedAt());
    }

    @Test(dependsOnMethods = {"testUpdateUserSuccessfully"})
    public void testGetUserByIdSuccessfully() {
        // Arrange
        String userId = "2"; // There is a backend issue when using the createdUserId directly
        UserService userService = new UserService();

        // Act
        GetUserResponse response = userService.getUserById(userId);

        // Assert
        Assert.assertNotNull(response.getData(), "User data should not be null");
        Assert.assertEquals(response.getData().getId(), Integer.parseInt(userId));
        Assert.assertNotNull(response.getData().getEmail());
        System.out.println("User Name: " + response.getData().getFirstName() + " " + response.getData().getLastName());
    }


    @Test (dependsOnMethods = {"testGetUserByIdSuccessfully"})
    public void testDeleteUserSuccessfully() {
        // Arrange
        String userId = createdUserId;
        UserService userService = new UserService();

        // Act & Assert
        userService.deleteUser(userId);

        System.out.println("User with ID " + userId + " deleted successfully.");
    }
}