package com.tekarch.UserMS.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}