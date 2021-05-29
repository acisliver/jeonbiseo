package com.example.demo.model;

import lombok.*;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob //대용량 데이터
    private String url;

    @Column
    private String brand;

    @Column
    private String productName;

    @Column
    private String os;

    @Column
    private String appWeight;

    @Column
    private String appSize;

    @Column
    private String usePen;

    @Column
    private String network;
}
