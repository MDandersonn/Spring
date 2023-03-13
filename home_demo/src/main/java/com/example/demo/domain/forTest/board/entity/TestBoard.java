package com.example.demo.domain.forTest.board.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class TestBoard {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

//    mappedBy는 참조 엔티티의 속성명을 의미한다.
//    즉,  comment 엔티티에서 testBoard 엔티티를 참조한 속성명 testBoard을 mappedBy에 전달해야 한다.

    //one: testBoard many: comments
    @OneToMany(mappedBy = "testBoard", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    public TestBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setComment (Comment comment) {
        comments.add(comment);
        comment.setTestBoard(this);
    }
}