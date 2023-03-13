package com.example.demo.domain.forTest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")//@JoinColumn(name="TEAM_ID") : 외래 키 매핑 (생량 가능) name 속성에는 매핑할 외래 키 이름 지정
    private TestBoard testBoard;

    public Comment (String content) {
        this.content = content;
    }
}