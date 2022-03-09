package com.example.basiccrud.dto;


import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private boolean status;
}
