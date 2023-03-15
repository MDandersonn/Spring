package org.africalib.gallery.africabackend.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="items")//items테이블이랑 이 클래스랑 맵핑하겠다는 의미
public class Item {
    @Id// 프라이머리키라는 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동증가
    private int id;
    @Column(length=50, nullable=false)
    private String name;
    @Column(length=100)
    private String imgPath;//img_path와맵핑가능
    @Column
    private int price;
    @Column
    private int discountPer;

}
