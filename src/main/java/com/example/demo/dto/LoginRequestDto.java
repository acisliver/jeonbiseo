package com.example.demo.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LoginRequestDto {
    private String userName;

    private String password;
}
