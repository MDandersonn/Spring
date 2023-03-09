package hello.hellospring.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //이제부터 이것은 JPA가 관리하는 엔티티가 되는것이다.
public class Member {
    @Id //PK라는 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동으로 ID를 생성해서 넣어주는 경우
    private Long id;
    //@Column(name="username") //db의 컬럼명은 username인경우 이렇게 맵핑가능.
    private String name;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
