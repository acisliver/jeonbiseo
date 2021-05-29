package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserPreferenceDto {
    private String os;
    private String appWeight;
    private String appSize;
    private String usePen;
    private String network;
}
