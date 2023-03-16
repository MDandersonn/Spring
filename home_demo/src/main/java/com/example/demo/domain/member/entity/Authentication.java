package com.example.demo.domain.member.entity;
/*
lazy로하면db에서 미리 땡겨오지않음 lazy가 좋다. 근본은 lazy임

*/

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString(exclude = "member")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "authentication_type")
public abstract class Authentication {

    public static final String BASIC_AUTH = "BASIC";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "authentication_type", nullable = false, insertable = false, updatable = false)
    private String authenticationType;

    public Authentication (Member member, String authenticationType) {
        this.member = member;
        this.authenticationType = authenticationType;
    }
}