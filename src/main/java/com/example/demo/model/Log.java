package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {

    @Id //private key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 방식을 따른다.
    private int id;

    @CreationTimestamp //시간이 자동 입력됨
    private Timestamp createDate;

    @Column(nullable = false, length = 100)
    private String level;

    @Column(nullable = false, length = 100)
    private String logger;

    @Column(nullable = false, length = 5000)
    private String message;
}
