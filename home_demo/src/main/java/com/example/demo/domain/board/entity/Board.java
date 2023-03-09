package com.example.demo.domain.board.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data //게터세터생성자투스트링
@Entity //데이터베이스(Database, DB) 에 쓰일 필드와 여러 엔티티간 연관관계를 정의한다
/*
클래스 위에 선언하여 이 클래스가 엔티티임을 알려준다. 이렇게 되면 JPA에서 정의된 필드들을 바탕으로 데이터베이스에 테이블을 만들어준다.
필드에 final, enum, interface, class를 사용할 수 없다.
생성자중 기본 생성자가 반드시 필요하다.
*/

public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
해당 엔티티의 주요 키(Primary Key, PK)가 될 값을 지정해주는 것이 @Id 이다.
@GeneratedValue는 이 PK가 자동으로 1씩 증가하는 형태로 생성될지 등을 결정해주는 어노테이션이다.
*/
    private Long boardId;

    @Column(length = 128, nullable = false)
//   @Column 어노테이션은 객체 필드와 DB 테이블 컬럼을 맵핑한다.
/*@Column의 속성
1. name : 맵핑할 테이블의 컬럼 이름을 지정
2. insertable : 엔티티 저장시 선언된 필드도 같이 저장
3. updateable : 엔티티 수정시 이 필드를 함께 수정
4. table : 지정한 필드를 다른 테이블에 맵핑
5. nullable : NULL을 허용할지, 허용하지 않을지 결정
6. unique : 제약조건을 걸 때 사용
7. columnDefinition : DB 컬럼 정보를 직접적으로 지정할 때 사용
8. length : varchar의 길이를 조정합니다. 기본값으로 255가 입력
9. precsion, scale : BigInteger, BigDecimal 타입에서 사용, 각각 소수점 포함 자리수, 소수의 자리수를 의미
*
* */
    private String title;

    @Column(length = 32, nullable = false)
    private String writer;

    @Lob
    /*
    * 0. LOB은 가변길의를 갖는 큰 데이터를 저장하는데 사용하는 데이터형이다.
          0-1 CLOB은 문자기반을 데이터를 저장하는데 사용된다.
             0-2 BLOB은 binary 데이터를 저장하는데 사용된다.
    1. @Lob은 일반적인 데이터베이스에서 저장하는 길이인 255개 이상의 문자를 저장하고 싶을 때 지정한다.
    * */
    private String content;

    @CreationTimestamp
    /*
    * @CreationTimeStamp는 INSERT 쿼리가 발생할 때, 현재 시간을 값으로 채워서 쿼리를 생성한다.*/
    private Date regDate;

    @UpdateTimestamp
    /*
    * @UpdateTimestamp는 UPDATE 쿼리가 발생할 때, 현재 시간을 값으로 채워서 쿼리를 생성한다.*/
    private Date updDate;
}
