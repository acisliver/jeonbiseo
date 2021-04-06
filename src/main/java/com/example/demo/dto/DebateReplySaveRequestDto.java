package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebateReplySaveRequestDto {
    private String content;
    private int reparentId;
    private int debateBoardId;
    private String prosAndCons;
}
