package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebateReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    //한 게시물에는 여러개의 답글이 있을 수 있다.
    @ManyToOne
    @JoinColumn(name = "debateId")
    private Debate debate;

    //한 유저는 여러개의 답글을 달 수 있다.
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    //부모노드 확인
    @Column(nullable = false)
    private int reparent;

    //깊이
    @Column(nullable = false)
    private int redepth;

    //순서
    @Column(nullable = false)
    private int reorder;

    //찬성, 반대, negative
    @Column(nullable = false)
    private String prosAndConsAndNegative;

}
