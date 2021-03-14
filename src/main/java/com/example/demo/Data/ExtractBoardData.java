package com.example.demo.Data;

import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtractBoardData {
    private int id;
    private String content;
    private User user;
}
