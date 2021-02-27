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
//@DynamicInsert //spring에서 db에 query를 할 때, null인 값은 제외하여 query를 보냄.
//ColumnDefault을 사용하여 유저들의 role의 값을 user로 셋팅하기 위해
//DynamicInsert을 사용 하지 않을 시 query: (createDate, role, email, password, userName)
//DynamicInsert을 사용 했을 시 query: (createDate, email, password, userName)
public class User {

    @Id //private key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 방식을 따른다.
    private int id; //oracle은 시퀀스, mysql은 auto_increment방식. 이 프로젝트는 mysql의 auto_increment방식을 따름

    @Column(nullable = false, length = 100, unique = true)
    private String userName;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("'user'")
    //private String role; //Enum을 쓰면 도메인을 설정 할 수 있기 때문에, Enum을 쓰는것이 좋다.

    @Enumerated(EnumType.STRING)
    private RoleType role; //enum 타입으로 설정한 RoleType을 사용

    @CreationTimestamp //시간이 자동 입력됨
    private Timestamp createDate;

    private String oAuth;
}
