package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {
    private int userId;
    private int boardId;
    private int reparentId;
    private int redepth;
    private int reorder;
    private String content;
}
