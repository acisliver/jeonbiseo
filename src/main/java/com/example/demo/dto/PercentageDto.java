package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PercentageDto {
    int pros;
    int cons;

    Double precentagePres;
    Double precentageCons;
    //Double precentageNegative;
}
