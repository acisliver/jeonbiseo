package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserPreferenceDto {
    private String os; //ios, android
    private String appWeight; //무거움, 가벼움
    private String appSize; //큼, 작음
    private String usePen; //가능, 불가능
    private String network; //LTE, Bluetooth
}
