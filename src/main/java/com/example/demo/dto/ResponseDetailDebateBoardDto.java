package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDetailDebateBoardDto<T,S> {
    T detailBoard;
    S percentageDto;
    int nullCheck;
}
