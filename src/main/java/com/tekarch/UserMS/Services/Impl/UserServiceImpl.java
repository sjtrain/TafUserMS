package com.tekarch.UserMS.Services.Impl;


import com.tekarch.UserMS.DTO.UserDTO;
import com.tekarch.UserMS.Services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final String datastoreServiceUrl;

    public UserServiceImpl(RestTemplate restTemplate, @Value("${datastore.service.url}") String datastoreServiceUrl) {
        this.restTemplate = restTemplate;
        this.datastoreServiceUrl = datastoreServiceUrl;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        // Ensure URL is correct without extra "/datastore/users" appended
        return restTemplate.postForObject(datastoreServiceUrl, user, UserDTO.class);  // Correct URL
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // Ensure URL is correct without extra "/datastore/users" appended
        UserDTO[] usersArray = restTemplate.getForObject(datastoreServiceUrl, UserDTO[].class);  // Correct URL
        return Arrays.asList(usersArray);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        // Ensure URL is correct without extra "/datastore/users" appended
        return restTemplate.getForObject(datastoreServiceUrl + "/" + userId, UserDTO.class);  // Correct URL
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO updatedUser) {
        restTemplate.put(datastoreServiceUrl + "/" + userId, updatedUser);  // Correct URL
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        restTemplate.delete(datastoreServiceUrl + "/" + userId);  // Correct URL
    }
}
